package core.game.node.entity.npc.city.alkharid;

import core.game.content.dialogue.DialoguePlugin;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.plugin.Initializable;
import core.game.node.item.Item;

/**
 * Represents the dialogue plugin used for ali the leaflet dropper npc.
 * @author 'Vexia
 * @version 1.9
 */
@Initializable
public final class AliTheLeafletDropper extends DialoguePlugin {

	/**
	 * Represents the flyer item.
	 */
	private static final Item FLYER = new Item(7922);

	/**
	 * Constructs a new {@code AliTheLeafletDropper} {@code Object}.
	 */
	public AliTheLeafletDropper() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code AliTheLeafletDropper} {@code Object}.
	 * @param player the player.
	 */
	public AliTheLeafletDropper(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new AliTheLeafletDropper(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		if (args.length == 2) {
			if (player.getInventory().freeSlots() == 0) {
				npc("Looks like your hands are full. come back when you", "have space for my flyer.");
				stage = 0;
				return true;
			}
			if (player.getInventory().contains(7922, 1) || player.getBank().contains(7922, 1)) {
				npc("Are you trying to be funny or has age turned your", "brain to mush? You already have a flyer!");
				stage = 0;
				return true;
			}
		}
		npc("I don't have time to talk right now! Ali Morrisane is", "paying me to hand out these flyers.");
		stage = 700;
		if (args.length == 2) {
			player.getInventory().add(FLYER);
			npc("Here take one and let me get back to work!");
			stage = 600;
			return true;
		}
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			end();
			break;
		case 600:
			npc("I still have hundreds of these flyers to hand out, I", "wonder if Ali would notice if I quietly dumped them", "somewhere?");
			stage = 601;
			break;
		case 601:
			end();
			break;
		case 700:
			interpreter.sendOptions("Select an Option", "Who is Ali Morrisane?", "What are the flyers for?", "What is there to do round here, boy?");
			stage = 701;
			break;
		case 701:
			switch (buttonId) {
			case 1:
				player("Who is Ali Morrisane?");
				stage = 710;
				break;
			case 2:
				player("What are the flyers for?");
				stage = 720;
				break;
			case 3:
				player("What is there to do round here, boy?");
				stage = 300;
				break;
			}
			break;
		case 710:
			npc("Ali Morrisane is the greatest merchant in the east!");
			stage = 711;
			break;
		case 711:
			player("Were you paid to say that?");
			stage = 712;
			break;
		case 712:
			npc("Of course I was! You can find him on the north edge", "of town");
			stage = 713;
			break;
		case 713:
			end();
			break;
		case 720:
			npc("Well, ali Morrisane isn't too popular with the other", "traders in Al Kharid, mainly because he's from", "Pollnivneach and they feel he has no business trading in", "their town.");
			stage = 721;
			break;
		case 721:
			npc("I think they're just sour because he's better at making", "money than them.");
			stage = 722;
			break;
		case 722:
			npc("The flyers advertises the different shops you can find in", "Al Kharid.");
			stage = 723;
			break;
		case 723:
			npc("It also entitles you to money off your next purchase in", "any of the shops listed on it. It's Ali's way of getting on", "the good side of the traders.");
			stage = 724;
			break;
		case 724:
			player("Which shops?");
			stage = 725;
			break;
		case 725:
			npc("The flyer will dicuss them all.");
			stage = 0;
			break;
		case 300:
			npc("I'm very busy, so listen carefully! I shall say this only", "once.");
			stage = 301;
			break;
		case 301:
			npc("Apart from a busy and wonderous market place in Al", "Kharid to the south, there is the Duel Arena to the", "south-east where you can challenge other players to a", "fight.");
			stage = 302;
			break;
		case 302:
			npc("If you're here to make money, there is a mine to the", "south.");
			stage = 303;
			break;
		case 303:
			npc("Watch out for scorpions though, they'll take a pop at", "you if you go too near them. To avoid them just follow", "the western fence as you travel south.");
			stage = 304;
			break;
		case 304:
			npc("If you're in the mood for a little rest and relaxation,", "there are a couple of nice fishing spots south of the", "town.");
			stage = 305;
			break;
		case 305:
			player("Thanks for the help!");
			stage = 306;
			break;
		case 306:
			end();
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 3680 };
	}
}
