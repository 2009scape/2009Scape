package plugin.quest.miniquest.surok;

import plugin.dialogue.DialoguePlugin;
import core.game.node.entity.player.Player;

/**
 * Handles the mishkaun dorn dialogue.
 * @author Vexia
 */
public class MishkalunDornDialogue extends DialoguePlugin {

	/**
	 * Constructs a new {@Code MishkalunDornDialogue} {@Code
	 * Object}
	 * @param player the player.
	 */
	public MishkalunDornDialogue(Player player) {
		super(player);
	}

	/**
	 * Constructs a new {@Code MishkalunDornDialogue} {@Code
	 * Object}
	 */
	public MishkalunDornDialogue() {
		/**
		 * empty.
		 */
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new MishkalunDornDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc("You are excused. And you are welcome.");
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			player("Excuse me...er..thanks.");
			stage++;
			break;
		case 1:
			npc("We are the Order of the Dagon'hai.");
			stage++;
			break;
		case 2:
			player("Who are you?");
			stage++;
			break;
		case 3:
			npc("Through my magic, I can see a short way into", "the future.");
			stage++;
			break;
		case 4:
			player("How do you seem to know what i'm going to", "say? ...Er...oh.");
			stage++;
			break;
		case 5:
			npc("These are the Tunnels of Chaos.");
			stage++;
			break;
		case 6:
			player("What is...uh..aha! I'm not going to ask that. So you got it", "wrong!");
			stage++;
			break;
		case 7:
			npc("Indeed. You are very clever.");
			stage++;
			break;
		case 8:
			player("So I won!");
			stage++;
			break;
		case 9:
			npc("Yes.");
			stage++;
			break;
		case 10:
			end();
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 5839 };
	}

}
