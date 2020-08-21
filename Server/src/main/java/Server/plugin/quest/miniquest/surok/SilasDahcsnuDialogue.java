package plugin.quest.miniquest.surok;

import plugin.dialogue.DialoguePlugin;
import core.game.node.entity.player.Player;

/**
 * Handles the mishkaun dorn dialogue.
 * @author Vexia
 */
public class SilasDahcsnuDialogue extends DialoguePlugin {

	/**
	 * Constructs a new {@Code SilasDahcsnuDialogue} {@Code
	 * Object}
	 * @param player the player.
	 */
	public SilasDahcsnuDialogue(Player player) {
		super(player);
	}

	/**
	 * Constructs a new {@Code SilasDahcsnuDialogue} {@Code
	 * Object}
	 */
	public SilasDahcsnuDialogue() {
		/**
		 * empty.
		 */
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new SilasDahcsnuDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		player("Hello there.");
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			npc("Can't you see that I'm busy here?");
			stage++;
			break;
		case 1:
			player("Oh, Sorry, you don't look very busy.");
			stage++;
			break;
		case 2:
			npc("Don't look busy? I've got a lot of important work to do", "here.");
			stage++;
			break;
		case 3:
			player("Really? What do you do?");
			stage++;
			break;
		case 4:
			npc("That doesn't concern you. What are you doing", "here anyway?");
			stage++;
			break;
		case 5:
			player("None of your business!");
			stage++;
			break;
		case 6:
			end();
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 5841 };
	}

}
