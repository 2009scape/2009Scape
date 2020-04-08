package plugin.dialogue;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.node.entity.player.Player;

/**
 * Represents the dialogue plugin used for the npc Ahav.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class AhabDialogue extends DialoguePlugin {

	/**
	 * Constructs a new {@code AhabDialogue} {@code Object}.
	 */
	public AhabDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code AhabDialogue} {@code Object}.
	 * @param player the player.
	 */
	public AhabDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new AhabDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		if(args.length > 1) {
			boolean isTelegrab = (boolean) args[1];
			player.debug("" + isTelegrab);
			if (!isTelegrab) {
				npc(FacialExpression.FURIOUS, "Oi! Get yer hands off me beer!");
			} else {
				npc(FacialExpression.FURIOUS,"Don't ye go castin' spells on me beer!");
			}
			stage = 301;
		} else {
			npc("Arrr, matey!");
			stage = 0;
		}
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			interpreter.sendOptions("Select an Option", "Arrr!", "Are you going to sit there all day?", "Do you have anything for trade?");
			stage = 1;
			break;
		case 1:

			switch (buttonId) {
			case 1:
				player("Arrr!");
				stage = 10;
				break;
			case 2:
				player("Are you going to sit there all day?");
				stage = 20;
				break;
			case 3:
				player("Do you have anything for trade?");
				stage = 300;
				break;
			}

			break;
		case 10:
			npc("Arrr, matey!");
			stage = 0;
			break;
		case 20:
			npc("Aye, I am. I canna walk, ye see.");
			stage = 21;
			break;
		case 21:
			player("What's stopping you from walking?");
			stage = 22;
			break;
		case 22:
			npc("Arrr, I'ave only the one leg! I lost its twin when my last", "ship went down.");
			stage = 23;
			break;
		case 23:
			player("But I can see both your legs!");
			stage = 24;
			break;
		case 24:
			npc("Nay, young laddie, this be a false leg. For years I had me a", "sturdy wooden peg-leg, but now I wear this dainty little", "feller.");
			stage = 25;
			break;
		case 25:
			npc("Yon peg-leg kept getting stuck in the floorboards.");
			stage = 26;
			break;
		case 26:
			player("Right...");
			stage = 27;
			break;
		case 27:
			npc("Perhaps a bright young laddie like yerself would like to", "help me? I be needing another ship to go a-hunting my", "enemy.");
			stage = 28;
			break;
		case 28:
			if (player.getQuestRepository().isComplete("Dragon Slayer")) {
				player("Well, I do have a ship that I'm not using.", "It's the Lady Lumbridge.");
				stage = 29;
			} else {
				player("No, I don't have a ship.");
				stage = 294;
			}
			break;
		case 294:
			npc("Arr matey... You make me sad.");
			stage = 295;
			break;
		case 295:
			end();
			break;
		case 29:
			npc("Arrr! That ship be known to me, and a fine lass she is.");
			stage = 30;
			break;
		case 30:
			player("I suppose she might be...");
			stage = 31;
			break;
		case 31:
			npc("So would ye be kind enough to let", "me take her out to sea?");
			stage = 32;
			break;
		case 32:
			player("I had to pay 2000gp for that ship! ", "Have you got that much?");
			stage = 33;
			break;
		case 33:
			npc("Nay, I have nary a penny to my name. All my wordly goods", "went down with me old ship.");
			stage = 34;
			break;
		case 34:
			player("So you're actually asking me to give you a free ship.");
			stage = 35;
			break;
		case 35:
			npc("Arrr! Would ye be so kind?");
			stage = 36;
			break;
		case 36:
			player("No I jolly well wouldn't!");
			stage = 37;
			break;
		case 37:
			npc("Arrr.");
			stage = 38;
			break;
		case 38:
			end();
			break;
		case 300:
			npc("Nothin' at the moment, but then again the Customs", "Agents are on the warpath right now.");
			stage = 301;
			break;
		case 301:
			end();
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 2692 };
	}
}
