package core.game.node.entity.skill.slayer;

import core.cache.def.impl.NPCDefinition;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import core.game.node.entity.skill.Skills;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.info.login.SavingModule;
import core.game.node.entity.player.link.diary.DiaryType;
import core.tools.RandomFunction;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Manages the players slayer task.
 * @author Vexia
 *
 */
public final class SlayerManager implements SavingModule {

	/**
	 * The player instance.
	 */
	private final Player player;

	/**
	 * The current slayer master used.
	 */
	private Master master;

	/**
	 * The current task.
	 */
	private Tasks task;

	/**
	 * The amount of the hunted killed.
	 */
	private int amount;

	/**
	 * The slayer points.
	 */
	private int slayerPoints;

	/**
	 * The task streak.
	 */
	private int taskCount;

	/**
	 * Total tasks completed
	 */
	private int taskTotal;

	/**
	 * The learned rewards.
	 */
	private final boolean[] learned = new boolean[3];

	/**
	 * The removed tasks.
	 */
	private final List<Tasks> removed = new ArrayList<>(4);

	/**
	 * Constructs a new {@Code SlayerManager} {@Code Object}
	 * @param player The player.
	 */
	public SlayerManager(Player player) {
		this.player = player;
	}

	/**
	 * If the player can earn points (taskTotal > 4)
	 */
	private boolean canEarnPoints = false;

	public void parse(JSONObject slayerData){
		Object m = slayerData.get("master");
		if(m != null)
			this.master = Master.forId(Integer.parseInt(m.toString()));
		Object t = slayerData.get("taskId");
		if(t != null)
			task = Tasks.values()[Integer.parseInt(t.toString())];
		Object a = slayerData.get("taskAmount");
		if(a != null)
			amount = Integer.parseInt(a.toString());
		slayerPoints = Integer.parseInt( slayerData.get("points").toString());
		taskCount = Integer.parseInt( slayerData.get("taskStreak").toString());
		JSONArray learnedArray = (JSONArray) slayerData.get("learned_rewards");
		for(int i = 0; i < learnedArray.size(); i++){
			learned[i] = (boolean) learnedArray.get(i);
 		}
		JSONArray removedTasks = (JSONArray) slayerData.get("removedTasks");
		if(removedTasks != null){
			for(int i = 0; i < removedTasks.size(); i++){
				removed.add(Tasks.values()[Integer.parseInt(removedTasks.get(i).toString())]);
			}
		}
		taskTotal = Integer.parseInt( slayerData.get("totalTasks").toString());
		canEarnPoints = (boolean) slayerData.get("canEarnPoints");
	}

	@Override
	public void parse(ByteBuffer buffer) {
		int opcode;
		while ((opcode = buffer.get() & 0xFF) != 0) {
			switch (opcode) {
			case 1:
				master = Master.forId(buffer.getInt());
				break;
			case 2:
				int taskId = buffer.getInt();
				if (taskId > Tasks.values().length -1) {
					System.err.println("Invalid task i for " + player.getUsername() + " taskId = " + taskId);
					break;
				}
				task = Tasks.values()[taskId];
				break;
			case 3:
				amount = buffer.getInt();
				break;
			case 4:
				slayerPoints = buffer.getInt();
				break;
			case 5:
				taskCount = buffer.getInt();
				break;
			case 6:
				int size = buffer.get();
				for (int i = 0; i < size; i++) {
					learned[i] = buffer.get() == 1;
				}
				break;
			case 7:
				size = buffer.get();
				for (int i = 0; i < size; i++) {
					removed.add(Tasks.values()[buffer.getInt()]);
				}
				break;
			case 8:
				taskTotal = buffer.getInt();
				break;
			case 9:
				int pointsEarnable = buffer.getInt();
				if(pointsEarnable == 1){
					canEarnPoints = true;
				}
				break;
			default:
				System.err.println("Error parsing Slayer Manager opcode = " + opcode);
				break;
			}
		}
	}

	@Override
	public void save(ByteBuffer buffer) {
		if (master != null) {
			buffer.put((byte) 1);
			buffer.putInt(master.getNpc());
		}
		if (task != null) {
			buffer.put((byte) 2);
			buffer.putInt(task.ordinal());
		}
		if (task != null) {
			buffer.put((byte) 3);
			buffer.putInt(getAmount());
		}
		if (slayerPoints != 0) {
			buffer.put((byte) 4).putInt(slayerPoints);
		}
		if (taskCount != 0) {
			buffer.put((byte) 5).putInt(taskCount);
		}
		for (int i = 0; i < learned.length; i++) {
			if (learned[i] != false) {
				buffer.put((byte) 6).put((byte) learned.length);
				for (int k = 0; k < learned.length; k++) {
					buffer.put((byte) (learned[k] ? 1 : 0));
				}
				break;
			}
		}
		if (!removed.isEmpty()) {
			buffer.put((byte) 7).put((byte) removed.size());
			for (Tasks task : removed)  {
				buffer.putInt(task.ordinal());
			}
		}
		if (taskTotal != 0){
			buffer.put((byte) 8);
			buffer.putInt(taskTotal);
		}
		if(canEarnPoints){
			buffer.put((byte) 9);
			buffer.putInt(1);
		}
		buffer.put((byte) 0);
	}

	/**
	 * Called when a hunted creature dies.
	 * @param player The player.
	 * @param npc The NPC. You're currently
	 */
	public void finalizeDeath(Player player, NPC npc) {
		player.getSkills().addExperience(Skills.SLAYER,npc.getSkills().getMaximumLifepoints());
		decrementAmount(1);
		if (!hasTask()) {
			clear();
			taskCount++;
			taskTotal++;
			if ((taskCount > 4 || canEarnPoints ) && master != Master.TURAEL && slayerPoints < 64000) {
				int points = master.getTaskPoints()[0];
				if (taskCount % 10 == 0) {
					points = master.getTaskPoints()[1];
				} else if (taskCount % 50 == 0) {
					points = master.getTaskPoints()[2];
				}
				slayerPoints += points;
				if (slayerPoints > 64000) {
					slayerPoints = 64000;
				}
				player.sendMessages("You've completed " + taskCount + " tasks in a row and received " + points + " points, with a total of " + player.getSlayer().getSlayerPoints(),"You have completed " + taskTotal + " tasks in total. Return to a Slayer master.");
			} else if(taskCount == 4){
				player.sendMessage("You've completed your task; you will start gaining points on your next task!");
				canEarnPoints = true;
			} else {
				player.sendMessages("You've completed your task; Complete " + (4 - taskCount) + " more task(s) to start gaining points.", "Return to a Slayer master.");
			}
		} else {
			//player.sendMessage("You're assigned to kill " + NPCDefinition.forId((player.getSlayer().getTask().getNpcs()[0])).getName().toLowerCase() + "s; Only " + getAmount() + " more to go.");
		}
	}

	/**
	 * Assigns a task to the manager.
	 * @param task the task.
	 * @param master the master.
	 */
	public void assign(Tasks task, final Master master) {
		setMaster(master);
		setTask(task);
		setAmount(getRandomAmount(master.assignment_range));
		if (master == Master.DURADEL) {
			player.getAchievementDiaryManager().finishTask(player, DiaryType.KARAMJA, 2, 8);
		} else if (master == Master.VANNAKA) {
			player.getAchievementDiaryManager().finishTask(player, DiaryType.VARROCK, 1, 14);
		}
	}

	/**
	 * Method used to assign a new task for a player.
	 * @param master the master to give the task.
	 */
	public void generate(Master master) {
		final List<Master.Task> tasks = new ArrayList<>();
		final int[] taskWeightSum = {0};
		master.tasks.stream().filter(task -> canBeAssigned(task.task) && task.task.combatCheck <= player.getProperties().getCurrentCombatLevel()).forEach(task -> {
					taskWeightSum[0] += task.weight;
					tasks.add(task);
		});
		Collections.shuffle(tasks, RandomFunction.RANDOM);
		int rnd = RandomFunction.random(taskWeightSum[0]);
		for(Master.Task task : tasks){
			if(rnd < task.weight)
				assign(task.task,master);
			rnd -= task.weight;
		}
	}

	public boolean canBeAssigned(Tasks task){
		return player.getSkills().getLevel(Skills.SLAYER) >= task.levelReq && !removed.contains(task);
	}

	/**
	 * Clears the task.
	 */
	public void clear() {
		setTask(null);
		setAmount(0);
	}

	/**
	 * Gets a random amount.
	 * @param ranges the ranges.
	 * @return the amt.
	 */
	private int getRandomAmount(int[] ranges) {
		return RandomFunction.random(ranges[0], ranges[1]);
	}

	/**
	 * Gets the task name.
	 * @return the name.
	 */
	public String getTaskName() {
		if (task == null) {
			return "null";
		}
		if (task.getNpcs() == null) {
			return "no npcs report me";
		}
		if (task.getNpcs().length < 1) {
			return "npc length to small report me";
		}
		return NPCDefinition.forId(task.getNpcs()[0]).getName().toLowerCase();
	}

	/**
	 * Gets the task.
	 * @return The task.
	 */
	public Tasks getTask() {
		return task;
	}

	/**
	 * Sets the task.
	 * @param task The task to set.
	 */
	public void setTask(Tasks task) {
		this.task = task;
	}

	/**
	 * Gets the player.
	 * @return The player.
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * Gets the master.
	 * @return The master.
	 */
	public Master getMaster() {
		return master;
	}

	/**
	 * Sets the master.
	 * @param master The master to set.
	 */
	public void setMaster(Master master) {
		this.master = master;
	}

	/**
	 * Checks if a <b>Player</b> contains a task.
	 * @return {@code True} if so.
	 */
	public boolean hasTask() {
		if (task == null) {
			return false;
		}
		if (getAmount() == 0) {
			return false;
		}
		return true;
	}

	/**
	 * Method used to check if the task is completed.
	 * @return <code>True</code> if so.
	 */
	public boolean isCompleted() {
		return amount <= 0;
	}

	/**
	 * Gets the amount.
	 * @return The amount.
	 */
	public int getAmount() {
		return amount;
	}

	/**
	 * Sets the amount.
	 * @param amount The amount to set.
	 */
	public void setAmount(int amount) {
		this.amount = amount;
	}

	/**
	 * Method used to decrement an amount.
	 * @param amount the amount.
	 */
	public void decrementAmount(int amount) {
		this.amount -= amount;
	}

	/**
	 * Method used to check if the player has started slayer.
	 * @return {@code True} if so.
	 */
	public boolean hasStarted() {
		return master != null;
	}

	/**
	 * Gets the slayerPoints.
	 * @return the slayerPoints.
	 */
	public int getSlayerPoints() {
		return slayerPoints;
	}

	/**
	 * Sets the slayerPoints.
	 * @param slayerPoints the slayerPoints to set
	 */
	public void setSlayerPoints(int slayerPoints) {
		this.slayerPoints = slayerPoints;
	}

	/**
	 * Gets the taskCount.
	 * @return the taskCount.
	 */
	public int getTaskCount() {
		return taskCount;
	}

	/**
	 * Sets the taskCount.
	 * @param taskCount the taskCount to set
	 */
	public void setTaskCount(int taskCount) {
		this.taskCount = taskCount;
	}

	/**
	 * Gets the learned.
	 * @return the learned.
	 */
	public boolean[] getLearned() {
		return learned;
	}

	/**
	 * Gets the removed.
	 * @return the removed.
	 */
	public List<Tasks> getRemoved() {
		return removed;
	}

	public int getTotalTasks() {return taskTotal;}

	public boolean isCanEarnPoints(){
		return canEarnPoints;
	}

}
