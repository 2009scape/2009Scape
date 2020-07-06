package plugin.zone.phasmatys;

import plugin.dialogue.DialoguePlugin;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.game.node.item.Item;

/**
 * Handles the dialogue used for a ghost disciple.
 * @author Vexia
 */
public final class GhostDiscipleDialogue extends DialoguePlugin {

	/**
	 * Constructs a new {@code GhostDiscipleDialogue} {@code Object}.
	 */
	public GhostDiscipleDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code GhostDiscipleDialogue} {@code Object}.
	 * @param player the player.
	 */
	public GhostDiscipleDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new GhostDiscipleDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		if(args.length > 1){
			final int amount = player.getSavedData().getGlobalData().getEctoCharges() * 5;
			final Item tokens = new Item(4278, amount);
			if (!player.getInventory().hasSpaceFor(tokens)) {
				player("Sorry, I don't have enough inventory space.");
				return true;
			}
			if(amount > 0) {
				player.getInventory().add(tokens);
				player.getSavedData().getGlobalData().setEctoCharges(0);
				npc("Certainly, mortal. Here's " + (amount) + " ectotokens.");
			} else {
				npc("I'm sorry, but you haven't earned any.");
			}
			stage = 81;
			return true;
		}
		if (player.getSavedData().getGlobalData().getEctoCharges() > 0) {
			player("Can I have the tokens I have earned?");
			stage = 80;
			return true;
		}
		if (player.getLocation().getZ() > 0) {
			if (args.length > 1) {
				npc("What are you doing going in there?");
				stage = 70;
				return true;
			}
			player("How do I use the bone grinder?");
			stage = 60;
			return true;
		}
		player("What is this strange fountain?");
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			npc("This is the Ectofuntus, the most marvellous creation of", "Necrovarus, our glorious leader.");
			stage++;
			break;
		case 1:
			options("What is the Ectofunuts?", "Where do I get ectoplasm from?", "How do I grind bones?", "How do I receive Ectotokens?", "Thanks for your time.");
			stage++;
			break;
		case 2:
			switch (buttonId) {
			case 1:
				player("What is the Ectofunuts?");
				stage = 10;
				break;
			case 2:
				player("Where do I get ectoplasm from?");
				stage = 20;
				break;
			case 3:
				player("How do I grind bones?");
				stage = 30;
				break;
			case 4:
				player("How do I receive Ectotokens?");
				stage = 40;
				break;
			case 5:
				player("Thanks for your time.");
				stage = 50;
				break;
			}
			break;
		case 10:
			npc("It provides the power to keep us ghosts from passing", "over into the next plane of existence.");
			stage++;
			break;
		case 11:
			player("And how does it work?");
			stage++;
			break;
		case 12:
			npc("You have to pour a bucket of ectoplasm into the", "fountain, a pot of ground bones, and then worship at", "the Ectofuntus. A unit of unholy power will then be", "created.");
			stage = 1;
			break;
		case 20:
			npc("Necrovarus sensed the power bubbling beneath our feet,", "and we delved long and deep beneath Port Phasmatys,", "until we found a pool of natural ectoplasm. You may", "find it by using the trapdoor over there.");
			stage = 1;
			break;
		case 30:
			npc("There is a bone grinding machine upstairs. Put bones", "of any type into the machine's hopper, and then turn", "the handle to grind them. You will need a pot to empty", "the machine of ground up bones.");
			stage = 1;
			break;
		case 40:
			npc("We disciples keep track of how many units of power", "have been produced. Just talk to us once you have", "generated some and we will reimburse you with the", "correct amount of Ectotokens.");
			stage++;
			break;
		case 41:
			player("How do I generate units of power?");
			stage++;
			break;
		case 42:
			npc("You have to pour a bucket of ectoplasm into the", "fountain and then worship at the Ectofuntus with a pot", "of ground bones. This will create a unit of unholy", "power.");
			stage = 1;
			break;
		case 50:
			end();
			break;
		case 60:
			npc("Put bones of any type into the machine's hopper, and", "then turn the handle to grind them. You will need a pot", "to empty the machine of ground up bones.");
			stage++;
			break;
		case 61:
			end();
			break;
		case 70:
			player("Err, I was just curious...");
			stage++;
			break;
		case 71:
			npc("Inside that room is a coffin, inside which lie the mortal", "remains of our most glorious master. Necrovarus.", "None may enter.");
			stage++;
			break;
		case 72:
			end();
			break;
		case 80: {
			final int amount = player.getSavedData().getGlobalData().getEctoCharges() * 5;
			final Item tokens = new Item(4278, amount);
			if (!player.getInventory().hasSpaceFor(tokens)) {
				player("Sorry, I don't have enough inventory space.");
				stage++;
				break;
			}
			stage++;
			player.getInventory().add(tokens);
			player.getSavedData().getGlobalData().setEctoCharges(0);
			npc("Certainly, mortal. Here's " + (amount) + " ectotokens.");
			break;
		}
		case 81:
			end();
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 1686 };
	}

}
