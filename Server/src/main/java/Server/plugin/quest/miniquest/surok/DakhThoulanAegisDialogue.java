package plugin.quest.miniquest.surok;

import plugin.dialogue.DialoguePlugin;
import core.game.node.entity.player.Player;

/**
 * Handles the mishkaun dorn dialogue.
 * @author Vexia
 */
public class DakhThoulanAegisDialogue extends DialoguePlugin {

	/**
	 * Constructs a new {@Code MishkalunDornDialogue} {@Code
	 * Object}
	 * @param player the player.
	 */
	public DakhThoulanAegisDialogue(Player player) {
		super(player);
	}

	/**
	 * Constructs a new {@Code MishkalunDornDialogue} {@Code
	 * Object}
	 */
	public DakhThoulanAegisDialogue() {
		/**
		 * empty.
		 */
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new DakhThoulanAegisDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		player("Hi there.");
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			npc("Hello, you one. What brings you to our humble", "dwelling?");
			stage++;
			break;
		case 1:
			player("I was wondering what this place was?");
			stage++;
			break;
		case 2:
			npc("These are the Tunnels of Chaos. They radiate", "with the energy of chaos magic. At the far end of the", "tunnel, you will find a portal to the Chaos Altar itself", "where chaos runes are crafted!");
			stage++;
			break;
		case 3:
			player("Thanks for your time.");
			stage++;
			break;
		case 4:
			end();
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 5840 };
	}

}
