package core.game.node.entity.player.link.diary;

import core.game.component.Component;
import core.game.container.impl.EquipmentContainer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import plugin.skill.smithing.smelting.Bar;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.info.login.SavingModule;
import core.game.node.item.Item;

import java.nio.ByteBuffer;

/**
 * Manages the achievement diary of a player.
 * @author Vexia
 */
public class AchievementDiaryManager implements SavingModule {

    /**
     * The achievement diarys.
     */
    private final AchievementDiary[] diarys = new AchievementDiary[] { new AchievementDiary(DiaryType.KARAMJA), new AchievementDiary(DiaryType.VARROCK), new AchievementDiary(DiaryType.LUMBRIDGE) };

	/**
	 * The player instance.
	 */
	private final Player player;

	/**
	 * Constructs a new {@Code AchievementDiary} {@Code Object}
	 * @param player the player.
	 */
	public AchievementDiaryManager(Player player) {
		this.player = player;
	}

	@Override
	public void save(ByteBuffer buffer) {
		buffer.put((byte) 1).put(((byte) diarys.length));
		for (AchievementDiary diary : diarys) {
			diary.save(buffer);
		}
		buffer.put((byte) 0);
	}

	public void parse(JSONArray data){
		for(int i = 0; i < data.size(); i++){
			diarys[i].parse((JSONObject) data.get(i));
		}
	}

	@Override
	public void parse(ByteBuffer buffer) {
		int opcode;
		while ((opcode = buffer.get()) != 0) {
			switch (opcode) {
			case 1:
				int length = buffer.get() & 0xFF;
				for (int i = 0; i < length; i++) {
					diarys[i].parse(buffer);
				}
				break;
			}
		}
	}

	/**
	 * Opens the achievement diary tab.
	 */
	public void openTab() {
		player.getInterfaceManager().openTab(2, new Component(259));
		for (AchievementDiary diary : diarys) {
			diary.drawStatus(player);
		}
	}

	/**
	 * Induces a task update.
	 * @param player the player.
	 * @param type the diary type.
	 * @param level the level.
	 * @param index the index of the task.
	 * @param complete if it's completed.
	 */
	public void updateTask(Player player, DiaryType type, int level, int index, boolean complete) {
		getDiary(type).updateTask(player, level, index, complete);
	}

	/**
	 * Checks if a task has been completed.
	 * @param type the diary type.
	 * @param level the level.
	 * @param index the index.
	 * @return {@code True} if completed.
	 */
	public boolean hasCompletedTask(DiaryType type, int level, int index) {
		return getDiary(type).isComplete(level, index);
	}

	/**
	 * Sets the diary at a level as started.
	 * @param type the type of diary.
	 * @param level the level.
	 */
	public void setStarted(DiaryType type, int level) {
		getDiary(type).setStarted(level);
	}

	/**
	 * Sets the diarys achievement as completed.
	 * @param type the type.
	 * @param level the level.
	 * @param index the index.
	 */
	public void setCompleted(DiaryType type, int level, int index) {
		getDiary(type).setCompleted(level, index);
	}

	/**
	 * Gets the achievement diary for the type.
	 * @param type the type.
	 * @return the diary object.
	 */
	public AchievementDiary getDiary(DiaryType type) {
		if (type == null) {
			return null;
		}
		for (AchievementDiary diary : diarys) {
			if (diary.getType() == type) {
				return diary;
			}
		}
		return null;
	}

	/**
	 * Gets the karamja glove level.
	 * @return the level of the glove.
	 */
	public int getKaramjaGlove() {
		if (!hasGlove()) {
			return -1;
		}
		for (int i = 0; i < 3; i++) {
			if (player.getEquipment().containsItem(DiaryType.KARAMJA.getRewards()[i][0])) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Gets the varrock armour level.
	 * @return the level of the armour.
	 */
	public int getArmour() {
		if (!hasArmour()) {
			return -1;
		}
		for (int i = 0; i < 3; i++) {
			if (player.getEquipment().containsItem(DiaryType.VARROCK.getRewards()[i][0])) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Checks the if the reward is valid for double.
	 * @param reward the reward.
	 * @return {@code True} if validated.
	 */
	public boolean checkMiningReward(int reward) {
		int level = player.getAchievementDiaryManager().getArmour();
		if (level == -1) {
			return false;
		}
		if (reward == 453) {
			return true;
		}
		return level == 0 && reward <= 442 || level == 1 && reward <= 447 || level == 2 && reward <= 449;
	}

	/**
	 * Checks the if the reward is valid for double.
	 * @param type the bar type.
	 * @return {@code True} if validated.
	 */
	public boolean checkSmithReward(Bar type) {
		int level = player.getAchievementDiaryManager().getArmour();
		if (level == -1) {
			return false;
		}
		return level == 0 && type.ordinal() <= Bar.STEEL.ordinal() || level == 1 && type.ordinal() <= Bar.MITHRIL.ordinal() || level == 2 && type.ordinal() <= Bar.ADAMANT.ordinal();
	}

	/**
	 * Checks if the player has karamja gloves.
	 * @return the gloves.
	 */
	public boolean hasGlove() {
		Item glove = player.getEquipment().get(EquipmentContainer.SLOT_HANDS);
		return glove != null && (glove.getId() == DiaryType.KARAMJA.getRewards()[0][0].getId() || glove.getId() == DiaryType.KARAMJA.getRewards()[1][0].getId() || glove.getId() == DiaryType.KARAMJA.getRewards()[2][0].getId());
	}

	/**
	 * Checks if the player has varrock armour.
	 * @return {@code True} if so.
	 */
	public boolean hasArmour() {
		Item plate = player.getEquipment().get(EquipmentContainer.SLOT_CHEST);
		return plate != null && (plate.getId() == DiaryType.VARROCK.getRewards()[0][0].getId() || plate.getId() == DiaryType.VARROCK.getRewards()[1][0].getId() || plate.getId() == DiaryType.VARROCK.getRewards()[2][0].getId());
	}

	/**
	 * Checks if a diary is complete.
	 * @param type the diary type.
	 * @return {@code True} if so.
	 */
	public boolean isComplete(DiaryType type) {
		return diarys[type.ordinal()].isComplete();
	}

	/**
	 * Gets the player.
	 * @return the player
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * Gets the diarys.
	 * @return the diarys
	 */
	public AchievementDiary[] getDiarys() {
		return diarys;
	}

}
