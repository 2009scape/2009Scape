package core.game.node.entity.npc.city.alkharid;

import core.game.content.dialogue.DialoguePlugin;
import core.game.node.entity.npc.NPC;
import core.plugin.Initializable;
import core.game.node.entity.player.Player;

/**
 * Represents the ali morrisane dialogue.
 * @author 'Vexia
 * @version 1.0
 */
@Initializable
public final class AliMorrisaneDialogue extends DialoguePlugin {

	/**
	 * Constructs a new {@code AliMorrisaneDialogue} {@code Object}.
	 * @param player the player.
	 */
	public AliMorrisaneDialogue(final Player player) {
		super(player);
	}

	/**
	 * Constructs a new {@code AliMorrisaneDialogue} {@code Object}.
	 */
	public AliMorrisaneDialogue() {
		/**
		 * empty.
		 */
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new AliMorrisaneDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		npc("Good day and welcome back to Al Kharid.");
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			player("Hello to you too.");
			stage = 1;
			break;
		case 1:
			npc("My name is Ali Morrisane - the greatest salesman in", "the world.");
			stage = 2;
			break;
		case 2:
			options("If you are, then why are you still selling goods from a stall?", "So what are you selling then?");
			stage = 3;
			break;
		case 3:
			switch (buttonId) {
			case 1:
				player("If you are, then why are you still selling goods from a", "stall?");
				stage = 10;
				break;
			case 2:
				end();
				npc.openShop(player);
				break;
			}
			break;
		case 10:
			npc("Well one can only do and sell so much. If I had more", "staff I'd be able to sell more, rather than wasting my", "time on menial things I could get on with selling sand", "to the Bedabin and useless tourist trinkets to everyone.");
			stage = 11;
			break;
		case 11:
			options("I'm far too busy - adventuring is a full time job you know.", "I'd like to help you but....");
			stage = 12;
			break;
		case 12:
			switch (buttonId) {
			case 1:
				player("I'm far too busy - adventuring is a full time job you", "know.");
				stage = 15;
				break;
			case 2:
				player("I'd like to help you but.....");
				stage = 13;
				break;
			}
			break;
		case 13:
			npc("Yes I know, I know - the life of a shop keeper isn't", "slaying dragon and wooing damsels but it has its", "charms");
			stage = 14;
			break;
		case 14:
			end();
			break;
		case 15:
			npc("No problem my friend, perhaps another time.");
			stage = 16;
			break;
		case 16:
			npc("Anyway, have a look at my wares.");
			stage = 17;
			break;
		case 17:
			options("No, I'm really too busy.", "Okay.");
			stage = 18;
			break;
		case 18:
			switch (buttonId) {
			case 1:
				end();
				break;
			case 2:
				end();
				npc.openShop(player);
				break;
			}
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 1862 };
	}

}
