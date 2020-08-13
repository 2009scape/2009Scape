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

	/**
	 * The constant representing the quest reward component.
	 */
	public static final int REWARD_COMPONENT = 277;
	
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
	public Quest(String name, int index, int buttonId, int questPoints, int...configs) {
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
	 * Draws the text on the quest journal.
	 * @param player The player.
	 * @param stage The stage to draw.
	 */
	public void drawJournal(Player player, int stage) {
		for (int i = 0; i < 311; i++) {
			player.getPacketDispatch().sendString("" , JOURNAL_COMPONENT, i);
		}
		player.getPacketDispatch().sendString("<col=8A0808>" + getName() + "</col>", JOURNAL_COMPONENT, 2);
	
	}

	/**
	 * Finishes the quest.
	 * @param player The player.
	 */
	public void finish(Player player) {
		for (int i = 0; i < 18; i++) {
			if (i == 9 || i == 3 || i == 6) {
				continue;
			}
			player.getPacketDispatch().sendString("", 277, i);
		}
		player.getQuestRepository().setStage(this, 100);
		player.getQuestRepository().incrementPoints(getQuestPoints());
		player.getQuestRepository().syncronizeTab(player);
		player.getConfigManager().set(101, player.getQuestRepository().getPoints());
		player.getInterfaceManager().open(new Component(277));
		player.getPacketDispatch().sendString("" + player.getQuestRepository().getPoints() + "", 277, 7);
		player.getPacketDispatch().sendString("You have completed the " + getName() + " Quest!", 277, 4);
		player.getPacketDispatch().sendMessage("Congratulations! Quest complete!");
		player.getPacketDispatch().sendTempMusic(152);
	}

	/**
	 * Draws a line on the journal component.
	 * @param player The player.
	 * @param message The message.
	 * @param line The line.
	 */
	public void line(Player player, String message, int line) {
		String send = BLUE + "" + message.replace("<n>", "<br><br>").replace("<blue>", BLUE).replace("<red>", RED);
		if (send.contains("<br><br>") || send.contains("<n>")) {
			String[] lines = send.split(send.contains("<br><br>") ? "<br><br>" : "<n>");
			for (int i = 0; i < lines.length; i++) {
				line(player, lines[i].replace("<br><br>", "").replace("<n>", ""), line, false);
				line++;
			}
		} else {
			send = send.replace("!!",RED).replace("??",BLUE).replace("---",BLACK + "<str>").replace("/--", BLUE);
			line(player, send, line, false);
		}
	}

	/**
	 * Draws a line on the quest journal component.
	 * @param player The player.
	 * @param message The message.
	 * @param line The line number.
	 * @param crossed True if the message should be crossed out.
	 */
	public void line(Player player, String message, int line, final boolean crossed) {
		String send = message;
		if(!crossed){
			send = BLUE + "" + message.replace("<n>", "<br><br>").replace("<blue>", BLUE).replace("<red>", RED);
			send = send.replace("!!",RED).replace("??",BLUE);
		} else {
			send = BLUE + "" + message.replace("??","").replace("!!","");
		}
		player.getPacketDispatch().sendString(crossed ? "<str>" + send + "</str>" : send, JOURNAL_COMPONENT, line);
	}

	/**
	 * Draws text on the quest reward component.
	 * @param player The player.
	 * @param string The string to draw.
	 * @param line The line number to draw on.
	 */
	public void drawReward(Player player, final String string, final int line) {
		player.getPacketDispatch().sendString(string, REWARD_COMPONENT, line);
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
	 * Checks the requirements for the quest.
	 * @param player The player
	 * @return {@code True} if so.
	 */
	public boolean hasRequirements(Player player) {
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

	@Override
	public String toString() {
		return "Quest [name=" + name + ", index=" + index + ", buttonId=" + buttonId + ", questPoints=" + questPoints + ", configs=" + Arrays.toString(configs) + "]";
	}

}