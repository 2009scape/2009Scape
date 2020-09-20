package core.game.node.entity.player.link.quest;

import core.game.component.Component;
import core.game.node.entity.player.Player;
import core.plugin.Plugin;
import core.plugin.PluginManifest;
import core.plugin.PluginType;

import java.util.Arrays;

/**
 * A skeleton plugin for a quest.
 * @author Vexia
 * 
 */
@PluginManifest(type = PluginType.QUEST)
public abstract class Quest implements Plugin<Object> {

	/**
	 * Represents the red string.
	 */
	public static final String RED = "<col=8A0808>";

	/**
	 * Represents the blue string.
	 */
	public static final String BLUE = "<col=08088A>";

	/**
	 * Represents the black string.
	 */
	public static final String BLACK = "<col=000000>";

	/**
	 * The constant representing the journal component.
	 */
	public static final int JOURNAL_COMPONENT = 275;

	public static final int JOURNAL_TITLE_LINE = 2;
	public static final int JOURNAL_TEXT_START = 4 + 7;

	/**
	 * The constant representing the quest reward component.
	 */
	public static final int REWARD_COMPONENT = 277;

	public static final int REWARD_TEXT_START = 10;

	/**
	 * The name of the quest.
	 */
	private final String name;

	/**
	 * The index id of the quest.
	 */
	private final int index;
	
	/**
	 * The button id of the quest.
	 */
	private final int buttonId;

	/**
	 * The rewarded quest points.
	 */
	private final int questPoints;
	
	/**
	 * The config values based on stage.
	 */
	private final int[] configs;

	/**
	 * Constructs a new {@Code Quest} {@Code Object}
	 * @param name The name.
	 * @param index The index.
	 * @param buttonId The button Id.
	 * @param questPoints The rewarded quest points.
	 * @param configs The configs.
	 */
	public Quest(String name, int index, int buttonId, int questPoints, int... configs) {
		this.name = name;
		this.index = index;
		this.buttonId = buttonId;
		this.questPoints = questPoints;
		this.configs = configs;
	}

	@Override
	public Object fireEvent(String identifier, Object... args) {
		return null;
	}

	@Override
	public abstract Quest newInstance(Object object);

	/**
	 * Starts this quest.
	 * @param player The player.
	 */
	public void start(Player player) {
		player.getQuestRepository().setStage(this, 10);
		player.getQuestRepository().syncronizeTab(player);
	}

	/**
	 * Resets the quest journal.
	 * @param player The player.
	 * @param stage The stage to draw.
	 */
	public void drawJournal(Player player, int stage) {
		for (int i = 0; i < 311; i++) {
			player.getPacketDispatch().sendString("" , JOURNAL_COMPONENT, i);
		}
		player.getPacketDispatch().sendString("<col=8A0808>" + getName() + "</col>", JOURNAL_COMPONENT, JOURNAL_TITLE_LINE);
	}

	/**
	 * Finishes the quest.
	 * @param player The player.
	 */
	public void finish(Player player) {
		// Reset component
		for (int i = 0; i < 18; i++) {
			if (i == 9 || i == 3 || i == 6) {
				continue;
			}
			player.getPacketDispatch().sendString("", REWARD_COMPONENT, i);
		}

		player.getQuestRepository().setStage(this, 100);
		player.getQuestRepository().incrementPoints(getQuestPoints());
		player.getQuestRepository().syncronizeTab(player);
		player.getConfigManager().set(101, player.getQuestRepository().getPoints());
		player.getInterfaceManager().open(new Component(REWARD_COMPONENT));
		player.getPacketDispatch().sendMessage("Congratulations! Quest complete!");
		player.getPacketDispatch().sendTempMusic(152);

		// Item shown on reward component
		QuestRewardComponentItem rewardComponentItem = getRewardComponentItem();
		if (rewardComponentItem != null) {
			player.getPacketDispatch().sendItemZoomOnInterface(rewardComponentItem.itemId, rewardComponentItem.itemAmount, rewardComponentItem.zoom, REWARD_COMPONENT, 5);
		}

		// "Congratulations!"

		// Title
		player.getPacketDispatch().sendString(
			getRewardComponentTitle(),
			REWARD_COMPONENT,
			4
		);

		// "Quest Points: #"
		player.getPacketDispatch().sendString(player.getQuestRepository().getPoints() + "", 277, 7);

		// "You are awarded:"
		int line = REWARD_TEXT_START;
		if (getQuestPoints() > 0) {
			player.getPacketDispatch().sendString(getQuestPoints() + " Quest Point" + (getQuestPoints() > 1 ? "s" : ""), REWARD_COMPONENT, line++);
		}
		for (QuestReward reward: getQuestRewards(player)) {
			player.getPacketDispatch().sendString(reward.toString(), REWARD_COMPONENT, line++);
			if (reward.getType() == QuestReward.QuestRewardType.ITEM) {
				player.getInventory().add(reward.getItem(), player);
			} else {
				player.getSkills().addExperience(reward.getSkill(), reward.getExperience());
			}
		}
	}

	/**
	 * Writes texts to the player's journal.
	 *
	 * @param player the player
	 * @param lineNumber line number to start on
	 * @param texts the lines of text
	 * @return next journal line number to be used
	 */
	public int writeJournal(Player player, int lineNumber, String... texts) {
		for (String text: texts) {
			player.getPacketDispatch().sendString(processString(text), JOURNAL_COMPONENT, lineNumber++);
		}
		return lineNumber;
	}

	/**
	 * Writes texts to the player's journal.
	 *
	 * @param player the player
	 * @param texts the lines of text
	 * @return next journal line number to be used
	 */
	public int writeJournal(Player player, String... texts) {
		return writeJournal(player, JOURNAL_TEXT_START, texts);
	}

	/**
	 * Writes texts to the player's journal.
	 *
	 * @param player the player
	 * @param lineNumber line number to start on
	 * @param str whether to strikethrough the lines
	 * @param texts the lines of text
	 * @return next journal line number to be used
	 */
	public int writeJournal(Player player, int lineNumber, boolean str, String... texts) {
		for (String text: texts) {
			player.getPacketDispatch().sendString(processString(text, str), JOURNAL_COMPONENT, lineNumber++);
		}
		return lineNumber;
	}

	/**
	 * Gets the quest's journal entries
	 */
	public String[][] getJournalEntries() {
		return new String[][]{};
	}

	/**
	 * Writes journal entries from the quest's {@link #getJournalEntries} to the player's journal.
	 *
	 * @param player the player
	 * @param lineNumber line number to start on
	 * @param str whether to strikethrough the lines
	 * @param toEntryIndex Writes entries up to and including {@code toEntryIndex}
	 * @return next journal line number to be used
	 */
	public int writeJournalEntries(Player player, int lineNumber, boolean str, int toEntryIndex) {
		String[][] je = getJournalEntries();
		for (int i = 0; i <= toEntryIndex; i++) {
			lineNumber = writeJournal(player, lineNumber, str, je[i]);
		}
		return lineNumber;
	}

	/**
	 * Writes texts to the player's journal.
	 *
	 * @param player the player
	 * @param strikethrough whether to strikethrough the lines
	 * @param texts the lines of text
	 * @return next journal line number to be used
	 */
	public int writeJournal(Player player, boolean strikethrough, String... texts) {
		return writeJournal(player, JOURNAL_TEXT_START, strikethrough, texts);
	}

	/**
	 * Replaces tags in text for sending to the client. Not limited to quests.
	 *
	 * @param text text to run tag replacements on
	 * @return processed text to send to the client
	 */
	public static String processString(String text) {
		return processString(text, false);
	}

	/**
	 * Strikes through and replaces tags in text for sending to the client. Not limited to quests.
	 *
	 * @param text text to run tag replacements on
	 * @param str whether the text should be struck out
	 * @return processed text to send to the client
	 */
	public static String processString(String text, boolean str) {
		// Default to blue text
		text = (str ? "<str>" : "") + (text.startsWith("<") ? "" : BLUE) + text
			// Add replacements below
			.replace("<blue>", BLUE)
			.replace("<red>", RED)
			.replace("<black>", BLACK);
		return text;
	}

	/**
	 * Sets the player instanced stage.
	 * @param player The player.
	 * @param stage The stage to set.
	 */
	public void setStage(Player player, int stage) {
		player.getQuestRepository().setStage(this, stage);
	}
	
	/**
	 * Gets the config id based on the stage.
	 * @param player The player.
	 * @param stage The stage.
	 * @return The config data.
	 */
	public int[] getConfig(Player player, int stage) {
		if (configs.length < 4) {
			throw new IndexOutOfBoundsException("Quest -> " + name + " configs array length was not valid. config length = " + configs.length + "!");
		}
		return new int[] {configs[0], stage == 0 ? configs[1] : stage >= 100 ? configs[3] : configs[2]};
	}
	
	/**
	 * Checks if the quest is in progress.
	 * @param player The player.
	 * @return {@code True} if so.
	 */
	public boolean isStarted(Player player) {
		return getStage(player) > 0  && getStage(player) < 100;
	}
	
	/**
	 * Checks if the quest is completed.
	 * @param player The player.
	 * @return {@code True} if so.
	 */
	public boolean isCompleted(Player player) {
		return getStage(player) >= 100;
	}

	/**
	 * Gets the player instanced stage of this quest.
	 * @param player The player.
	 * @return The stage.
	 */
	public int getStage(Player player) {
		return player.getQuestRepository().getStage(this);
	}

	/**
	 * Whether the player can start the quest
	 *
	 * @param player The player.
	 * @return {@code True} if so.
	 */
	public boolean hasRequirements(Player player) {
		for (QuestRequirement requirement: getQuestRequirements(player)) {
			if (!requirement.check(player)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Gets the name.
	 * @return the name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the index.
	 * @return the index.
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * Gets the buttonId.
	 * @return the buttonId.
	 */
	public int getButtonId() {
		return buttonId;
	}

	/**
	 * Gets the questPoints.
	 * @return the questPoints.
	 */
	public int getQuestPoints() {
		return questPoints;
	}

	/**
	 * Gets the configs.
	 * @return the configs.
	 */
	public int[] getConfigs() {
		return configs;
	}

	/**
	 * Gets the title shown in the reward component
	 */
	public String getRewardComponentTitle() {
		return "You have completed the " + getName() + " Quest!";
	}

	/**
	 * Gets the item shown in the reward component
	 * @return the item
	 */
	abstract public QuestRewardComponentItem getRewardComponentItem();

	/**
	 * Gets the quest's requirements
	 * @return QuestRequirements
	 */
	public QuestRequirement[] getQuestRequirements(Player player) {
		return new QuestRequirement[0];
	}

	public String[] getQuestRequirementsJournal(Player player) {
		QuestRequirement[] questRequirements = getQuestRequirements(player);
		String[] text = new String[questRequirements.length];
		for (int i = 0; i < questRequirements.length; i++) {
			text[i] = questRequirements[i].toJournalString(player);
		}
		return text;
	}

	/**
	 * Gets the quest's rewards
	 *
	 * @return QuestRewards
	 */
	abstract public QuestReward[] getQuestRewards(Player player);

	@Override
	public String toString() {
		return "Quest [name=" + name + ", index=" + index + ", buttonId=" + buttonId + ", questPoints=" + questPoints + ", configs=" + Arrays.toString(configs) + "]";
	}
}
