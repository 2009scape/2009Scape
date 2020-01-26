package org.crandor.game.content.dialogue;

import org.crandor.game.component.Component;
import org.crandor.game.node.entity.Entity;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.game.node.entity.player.Player;
import org.crandor.plugin.Plugin;
import org.crandor.plugin.PluginManifest;
import org.crandor.plugin.PluginType;

/**
 * Represents a dialogue plugin.
 * @author Emperor
 */
@PluginManifest(type = PluginType.DIALOGUE)
public abstract class DialoguePlugin implements Plugin<Player> {

	/**
	 * Represents the red string.
	 */
	protected static final String RED = "<col=8A0808>";

	/**
	 * Represents the blue string.
	 */
	protected static final String BLUE = "<col=08088A>";

	/**
	 * The player.
	 */
	protected Player player;

	/**
	 * The dialogue interpreter.
	 */
	protected DialogueInterpreter interpreter;

	/**
	 * Two options interface.
	 */
	protected final int TWO_OPTIONS = 228;

	/**
	 * Three options interface.
	 */
	protected final int THREE_OPTIONS = 230;

	/**
	 * Four options interface.
	 */
	protected final int FOUR_OPTIONS = 232;

	/**
	 * Five options interface.
	 */
	protected final int FIVE_OPTIONS = 234;

	/**
	 * The NPC the player is talking with.
	 */
	protected NPC npc;

	/**
	 * The current dialogue stage.
	 */
	protected int stage;

	/**
	 * If the dialogue is finished.
	 */
	protected boolean finished;

	/**
	 * Constructs a new {@code DialoguePlugin} {@code Object}.
	 */
	public DialoguePlugin() {
		/*
		 * empty.
		 */
	}
	
	public String pirateGender() {
		return (player.isMale() ? "lad" : "lass");
		
	}

	/**
	 * Constructs a new {@code DialoguePlugin} {@code Object}.
	 * @param player The player.
	 */
	public DialoguePlugin(Player player) {
		this.player = player;
		if (player != null) {
			this.interpreter = player.getDialogueInterpreter();
		}
	}

	/**
	 * Initializes this plugin.
	 */
	public void init() {
		for (int id : getIds()) {
			DialogueInterpreter.add(id, this);
		}
	}

	/**
	 * Closes <b>(but does not end)</b> the dialogue.
	 * @return {@code True} if the dialogue succesfully closed.
	 */
	public boolean close() {
		player.getInterfaceManager().closeChatbox();
		player.getInterfaceManager().openChatbox(137);
		finished = true;
		return true;
	}

	public void sendNormalDialogue(Entity entity, FacialExpression expression, String... messages) {
		interpreter.sendDialogues(entity, expression, messages);
	}

	/**
	 * Increments the stage variable.
	 */
	public void increment() {
		stage++;
	}


	/**
	 * Increments the stage variable.
	 * @return The stage variable.
	 */
	public int getAndIncrement() {
		return stage++;
	}

	/**
	 * Ends the dialogue.
	 */
	public void end() {
		if (interpreter != null) {
			interpreter.close();
		}
	}

	@Override
	public Object fireEvent(String identifier, Object... args) {
		return null;
	}

	@Override
	public abstract DialoguePlugin newInstance(Player player);

	/**
	 * Opens the dialogue.
	 * @param args The arguments.
	 * @return {@code True} if the dialogue plugin succesfully opened.
	 */
	public abstract boolean open(Object... args);

	/**
	 * Handles the progress of this dialogue..
	 * @return {@code True} if the dialogue has started.
	 */
	public abstract boolean handle(int interfaceId, int buttonId);

	/**
	 * Gets the ids of the NPCs using this dialogue plugin.
	 * @return The array of NPC ids.
	 */
	public abstract int[] getIds();
	
	/**
	 * Method wrapper to send an npc dial.
	 * @return the component.
	 */
	public Component npc(final String... messages) {
		if (npc == null) {
			return interpreter.sendDialogues(getIds()[0], getIds()[0] > 8591 ? FacialExpression.OSRS_NORMAL : FacialExpression.NO_EXPRESSION, messages);
		}
		return interpreter.sendDialogues(npc, getIds()[0] > 8591 ? FacialExpression.OSRS_NORMAL : FacialExpression.NO_EXPRESSION, messages);
	}

	/**
	 * Method wrapper to send an npc dial.
	 * @param id the id.
	 * @return the component.
	 */
	public Component npc(int id, final String... messages) {
		return interpreter.sendDialogues(id, FacialExpression.NO_EXPRESSION, messages);
	}
	
	/**
	 * Method wrapper to send an npc dial.
	 * @return the component.
	 */
	public Component npc(FacialExpression expression, final String... messages) {
		if (npc == null) {
			return interpreter.sendDialogues(getIds()[0], FacialExpression.NO_EXPRESSION, messages);
		}
		return interpreter.sendDialogues(npc, expression, messages);
	}

	/**
	 * Method wrapper to send a player dial.
	 * @return the component.
	 */
	public Component player(final String... messages) {
		return interpreter.sendDialogues(player, null, messages);
	}
	/**
	 * Method wrapper to send a player dial.
	 * @return the component.
	 */
	public Component player(FacialExpression expression, final String... messages) {
		return interpreter.sendDialogues(player, expression, messages);
	}

	/**
	 * Method used to send options.
	 * @param options the options.
	 */
	public void options(final String... options) {
		interpreter.sendOptions("Select an Option", options);
	}

	/**
	 * Checks if the dialogue plugin is finished.
	 * @return {@code True} if so.
	 */
	public boolean isFinished() {
		return finished;
	}

	/**
	 * Gets the player.
	 * @return The player.
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * Sets the stage.
	 * @param i the stage.
	 */
	public void setStage(int i) {
		this.stage = i;
	}

	public void next() {
		this.stage += 1;
	}

}