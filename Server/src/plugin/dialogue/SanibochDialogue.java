package plugin.dialogue;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.game.node.entity.player.Player;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.node.item.Item;

/**
 * Handles the SanibochDialogue dialogue.
 * @author Emperor
 * @author 'Vexia
 */
@InitializablePlugin
public class SanibochDialogue extends DialoguePlugin {

	/**
	 * Constructs a new {@code SanibochDialogue} {@code Object}.
	 */
	public SanibochDialogue() {

	}

	/**
	 * Constructs a new {@code SanibochDialogue} {@code Object}.
	 * @param player The player.
	 */
	public SanibochDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new SanibochDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		stage = args.length > 1 ? (Integer) args[1] : 0;
		if (stage == 0) {
			interpreter.sendDialogues(1595, FacialExpression.NO_EXPRESSION, "Good day to you bwana.");
		} else {
			handle(0, 0);
		}
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			interpreter.sendOptions("Select an Option", "Can I go through that door please?", "Where does this strange entrance lead?", "Good day to you too.", "I'm impressed, that tree  is growing on that shed.");
			stage = 1;
			break;
		case 1:
			switch (buttonId) {
			case 1:
				player("Can I go through that door please?");
				stage = 10;
				break;
			case 2:
				player("Where does this strange entrance lead?");
				stage = 20;
				break;
			case 3:
				player("Good day to you too.");
				stage = -1;
				break;
			case 4:
				player("I'm impressed, that tree  is growing on that shed.");
				stage = 40;
				break;
			}
			break;
		case 10:
			if (player.getAttribute("saniboch:paid", false)) {
				npc("Most certainly, you have already given me lots of nice", "coins.");
				stage = -1;
				break;
			}
			npc("Most certainly, but I must charge you the sum of 875", "coins first.");
			stage = 11;
			break;
		case 11:
			if (!player.getInventory().contains(995, 875)) {
				player("I don't have the money on me at the moment.");
				stage = -1;
				break;
			}
			options("Ok, here's 875 coins.", "Never mind.", "Why is it worth the entry cost?");
			stage = 12;
			break;
		case 12:
			switch (buttonId) {
			case 1:
				player("Ok, here's 875 coins.");
				stage = 32;
				break;
			case 2:
				player("Never mind.");
				stage = -1;
				break;
			case 3:
				player("Why is it worth the entry cost?");
				stage = 30;
				break;
			}
			break;
		case 20:
			npc("To a huge fearsome dungeon, populated by giants and", "strange dogs. Adventurers come from all around to", "explore its depths.");
			stage = 21;
			break;
		case 21:
			npc("I know not what lies deeper in myself, for my skills in", "agility and woodcutting are inadequate.");
			stage = -1;
			break;
		case 30:
			npc("It leads to a huge fearsome dungeon, populated by", "giants and strange dogs. Adventurers come from all", "around to explore its depths.");
			stage = 31;
			break;
		case 31:
			npc("I know not what lies deeper in myself, for my skills in", "agility and woodcutting are inadequate, but I hear tell", "of even greater dangers deeper in.");
			stage = -1;
			break;
		case 32:
			Item item = new Item(995, 875);
			if (player.getInventory().remove(item)) {
				player.getPacketDispatch().sendMessage("You pay Saniboch 875 coins.");
				player.setAttribute("saniboch:paid", true);
				interpreter.sendItemMessage(item, "You give Saniboch 875 coins.");
				stage = 33;
				break;
			}
			end();
			break;
		case 33:
			npc("Many thanks. You may now pass the door. May your", "death be a glorious one!");
			stage = -1;
			break;
		case 35:
			if (player.getAttribute("saniboch:paid", false)) {
				npc("You have already given me lots of nice coins, you may", "go in.");
				stage = -1;
				break;
			}
			if (!player.getInventory().contains(995, 875)) {
				player("I don't have the money on me at the moment.");
				stage = 36;
				break;
			}
			stage = 12;
			handle(interfaceId, 1);
			break;
		case 36:
			npc("Well this is a dungeon for the more wealthy discerning", "adventurer, be gone with your riff raff.");
			stage = 37;
			break;
		case 37:
			player("But you don't even have clothes, how can you seriously", "call anyone riff raff.");
			stage = 38;
			break;
		case 38:
			npc("Hummph.");
			stage = -1;
			break;
		case 40:
			npc("My employer tells me it is an uncommon sort of tree", "called the Fyburglars tree.");
			stage = -1;
			break;
		case -1:
			end();
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 1595 };
	}
}
