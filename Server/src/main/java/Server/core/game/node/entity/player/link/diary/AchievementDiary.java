package core.game.node.entity.player.link.diary;

import core.cache.def.impl.NPCDefinition;
import core.game.component.Component;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.info.login.SavingModule;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.nio.ByteBuffer;

/**
 * Represents an achievement diary.
 * @author Vexia
 */
public class AchievementDiary implements SavingModule {

	/**
	 * The component id of the diary.
	 */
	public static final int DIARY_COMPONENT = 275;

	/**
	 * Represents the red color code string.
	 */
	private static final String RED = "<col=8A0808>";

	/**
	 * Represents the blue color code string.
	 */
	private static final String BLUE = "<col=08088A>";

	/**
	 * Represents the yellow color code string.
	 */
	private static final String YELLOW = "<col=F7FE2E>";

	/**
	 * Represets the green color code string.
	 */
	private static final String GREEN = "<col=3ADF00>";

	/**
	 * The diary type.
	 */
	private final DiaryType type;

	/**
	 * The task types started.
	 */
	private final boolean[] levelStarted = new boolean[3];

	/**
	 * If the rewards have been given.
	 */
	private final boolean[] levelRewarded = new boolean[3];

	/**
	 * The completed achievements.
	 */
	private final boolean[][] taskCompleted;

	/**
	 * Constructs a new {@code AchievementDiary} {@code Object}
	 * @param type the diary type.
	 */
	public AchievementDiary(DiaryType type) {
		this.type = type;
		this.taskCompleted = new boolean[type.getAchievements().length][25];
	}

	/**
	 * Open the achievement diary.
	 * @param player the player.
	 */
	public void open(Player player) {
		clear(player);
		sendString(player, "<red>Achievement Diary - " + type.getName(), 2);
		int child = 12;

		sendString(player, (isComplete() ? GREEN : isStarted() ? YELLOW : "<red>") + type.getName() + " Area Tasks", child++);
		//child++;

		if (!type.getInfo().isEmpty() && !this.isStarted()) {
			sendString(player, type.getInfo(), child++);
			child += type.getInfo().split("<br><br>").length;
		}
		child++;

		boolean complete;
		String line;
		for (int level = 0; level < type.getAchievements().length; level++) {
			sendString(player, getStatus(level) + getLevel(level) + "", child++);
			child++;
			for (int i = 0; i < type.getAchievements(level).length; i++) {
				complete = isComplete(level, i);
				line = type.getAchievements(level)[i];
				if (line.contains("<br><br>")) {
					String[] lines = line.split("<br><br>");
					for (String l : lines) {
						sendString(player, complete ? "<str><str>" + l : l, child++);
					}
				} else {
					sendString(player, complete ? "<str><str>" + line : line, child++);
				}
				sendString(player, "*", child++);
			}
			child++;
		}
		//	sendString(player, builder.toString(), 11);
		//Changes the size of the scroll bar
		//player.getPacketDispatch().sendRunScript(1207, "i", new Object[] { 330 });
		//sendString(player, builder.toString(), 11);
		if (!player.getInterfaceManager().isOpened()) {
			player.getInterfaceManager().open(new Component(DIARY_COMPONENT));
		}
	}

	/**
	 * Clears the diary screen.
	 * @param player the player.
	 */
	private void clear(Player player) {
		for (int i = 0; i < 311; i++) {
			player.getPacketDispatch().sendString("", DIARY_COMPONENT, i);
		}
	}

	@Override
	public void save(ByteBuffer buffer) {
		buffer.put((byte) 1);
		for (int i = 0; i < 3; i++) {
			buffer.put((byte) (levelStarted[i] ? 1 : 0));
		}
		buffer.put((byte) 2).put((byte) taskCompleted.length);
		for (int i = 0; i < taskCompleted.length; i++) {
			buffer.put((byte) type.getAchievements(i).length);
			for (int x = 0; x < type.getAchievements(i).length; x++) {
				buffer.put((byte) (taskCompleted[i][x] ? 1 : 0));
			}
		}
		buffer.put((byte) 3).put((byte) levelRewarded.length);
		for (int i = 0; i < levelRewarded.length; i++) {
			buffer.put((byte) (levelRewarded[i] ? 1 : 0));
		}
		buffer.put((byte) 0);
	}

	public void parse(JSONObject data){
		JSONArray startedArray = (JSONArray) data.get("startedLevels");
		for(int i = 0; i < startedArray.size(); i++){
			levelStarted[i] = (boolean) startedArray.get(i);
		}
		JSONArray completedArray = (JSONArray) data.get("completedLevels");
		for(int i = 0; i < completedArray.size(); i++){
			JSONArray level = (JSONArray) completedArray.get(i);
			for(int j = 0; j < level.size(); j++){
				taskCompleted[i][j] = (boolean) level.get(j);
			}
		}
		JSONArray rewardedArray = (JSONArray) data.get("rewardedLevels");
		for(int i = 0; i < rewardedArray.size(); i++){
			levelRewarded[i] = (boolean) rewardedArray.get(i);
		}
	}

	@Override
	public void parse(ByteBuffer buffer) {
		int opcode, size;
		while ((opcode = buffer.get()) != 0) {
			switch (opcode) {
			case 1:
				for (int i = 0; i < 3; i++) {
					levelStarted[i] = buffer.get() == 1;
				}
				break;
			case 2:
				size = buffer.get() & 0xFF;
				for (int i = 0; i < size; i++) {
					int size_ = buffer.get() & 0xFF;
					for (int x = 0; x < size_; x++) {
						taskCompleted[i][x] = buffer.get() == 1;
					}
				}
				break;
			case 3:
				size = buffer.get() & 0xFF;
				for (int i = 0; i < size; i++) {
					levelRewarded[i] = buffer.get() == 1;
				}
				break;
			}
		}
	}

	/**
	 * Draws the status of the diary.
	 * @param player the player.
	 */
	public void drawStatus(Player player) {
		if (isStarted()) {
			player.getPacketDispatch().sendString((isComplete() ? GREEN : YELLOW) + type.getName(), 259, type.getChild());
			for (int i = 0; i < 3; i++) {
				player.getPacketDispatch().sendString((isComplete(i) ? GREEN : isStarted(i) ? YELLOW : "<col=FF0000>") + getLevel(i), 259, type.getChild() + (i + 1));
			}
		}
	}

	/**
	 * Induces a task update.
	 * @param player the player.
	 * @param level the level.
	 * @param index the index of the task.
	 * @param complete if it's completed.
	 */
	public void updateTask(Player player, int level, int index, boolean complete) {
		if (!levelStarted[level]) {
			levelStarted[level] = true;
		}
		if (!complete) {
			player.sendMessage("Well done! A " + type.getName() + " task has been updated.");
		} else {
			taskCompleted[level][index] = true;
			int tempLevel = this.type == DiaryType.LUMBRIDGE ? level - 1 : level;
			player.sendMessages("Well done! You have completed "
					+ (tempLevel == -1 ? "a beginner" : tempLevel == 0 ? "an easy" : tempLevel == 1 ? "a medium" : "a hard")
					+ " task in the " + type.getName() + " area. Your", "Achievement Diary has been updated.");
		}
		if (isComplete(level)) {
			player.sendMessages("Congratulations! You have completed all of the " + getLevel(level).toLowerCase()
					+ " tasks in the " + type.getName() + " area.", "Speak to "
					+ NPCDefinition.forId(type.getNpc(level)).getName() + " to claim your reward.");
		}
		drawStatus(player);
	}

	public void finishTask(Player player, int level, int index) {
		if (!this.isComplete(level, index)) {
			this.updateTask(player, level, index, true);
		}
	}

	/**
	 * Resets a task to un-start
	 */
	public void resetTask(Player player, int level, int index) {
		taskCompleted[level][index] = false;
		if (!isStarted(level)) {
			this.levelStarted[level] = false;
		}
		if (!isComplete(level)) {
			this.levelRewarded[level] = false;
		}
		drawStatus(player);
	}

	/**
	 * Sends a string on the diary interface.
	 * @param player the player.
	 * @param string the string.
	 * @param child the child.
	 */
	private void sendString(Player player, String string, int child) {
		player.getPacketDispatch().sendString(string.replace("<blue>", BLUE).replace("<red>", RED), DIARY_COMPONENT, child);
	}

	/**
	 * Sets the diary for the level as started.
	 * @param level the level.
	 */
	public void setLevelStarted(int level) {
		this.levelStarted[level] = true;
	}

	/**
	 * Sets an achievement as completed.
	 * @param level the level.
	 * @param index the index.
	 */
	public void setCompleted(int level, int index) {
		this.taskCompleted[level][index] = true;
	}

	/**
	 * Checks if the achievement level is started.
	 * @param level the level.
	 * @return {@code True} if so.
	 */
	public boolean isStarted(int level) {
		return this.levelStarted[level];
	}

	/**
	 * Checks if the diary is started.
	 * @return {@code True} if so.
	 */
	public boolean isStarted() {
		for (int j = 0; j < type.getLevelNames().length; j++) {
			if (isStarted(j)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Checks if an achievement task is completed.
	 * @param level the level.
	 * @param index the index.
	 * @return {@code True} if an achievement is completed.
	 */
	public boolean isComplete(int level, int index) {
		return taskCompleted[level][index];
	}

	/**
	 * Checks if the achievement level is completed.
	 * @param level the level.
	 * @return {@code True} if so.
	 */
	public boolean isComplete(int level) {
		for (int i = 0; i < type.getAchievements(level).length; i++) {
			if (!taskCompleted[level][i]) {
				return false;
			}
		}
		return true;
	}

	public boolean isComplete(int level, boolean cumulative) {
		if (isComplete(level)) {
			return !cumulative || level <= 0 || isComplete(level - 1, true);
		} else {
			return false;
		}
	}

	/**
	 * Checks if an achievement diary is complete.
	 * @return {@code True} if completed.
	 */
	public boolean isComplete() {
		for (int i = 0; i < taskCompleted.length; i++) {
			for (int x = 0; x < type.getAchievements(i).length; x++) {
				if (!taskCompleted[i][x]) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Gets the level of completion.
	 * @return the level.
	 */
	public int getLevel() {
		return isComplete(2) ? 2 : isComplete(1) ? 1 : isComplete(0) ? 0 : -1;
	}

	/**
	 * Gets the level of reward.
	 * @return the level.
	 */
	public int getReward() {
		return isLevelRewarded(2) ? 2 : isLevelRewarded(1) ? 1 : isLevelRewarded(0) ? 0 : -1;
	}

	/**
	 * Gets the level string.
	 * @param level the level.
	 * @return the string format.
	 */
	public String getLevel(int level) {
		return type.getLevelNames()[level];
	}

	/**
	 * Gets the status for a level of completion of the achievement.
	 * @param level the level.
	 * @return the string color status.
	 */
	public String getStatus(int level) {
		return !isStarted(level) ? RED : isComplete(level) ? GREEN : YELLOW;
	}

	/**
	 * Sets the level as rewarded.
	 * @param level the level.
	 */
	public void setLevelRewarded(int level) {
		this.levelRewarded[level] = true;
	}

	/**
	 * Checks if the reward has been given.
	 * @param level the level.
	 * @return {@code True} if so.
	 */
	public boolean isLevelRewarded(int level) {
		return levelRewarded[level];
	}

	/**
	 * Gets the completed.
	 * @return the completed
	 */
	public boolean[][] getTaskCompleted() {
		return taskCompleted;
	}

	/**
	 * Gets the type.
	 * @return the type
	 */
	public DiaryType getType() {
		return type;
	}

	/**
	 * Gets the started.
	 * @return the started
	 */
	public boolean[] getLevelStarted() {
		return levelStarted;
	}

	/**
	 * Gets the rewarded.
	 * @return the rewarded
	 */
	public boolean[] getLevelRewarded() {
		return levelRewarded;
	}

}
