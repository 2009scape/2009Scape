package plugin.dialogue;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.node.entity.player.Player;

/**
 * Represents the hiylikmyna dialogue plugin.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class HiylikMynaDialogue extends DialoguePlugin {

	/**
	 * Constructs a new {@code HiylikMynaDialogue} {@code Object}.
	 */
	public HiylikMynaDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code HiylikMynaDialogue} {@code Object}.
	 * @param player the player.
	 */
	public HiylikMynaDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new HiylikMynaDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendOptions("Select an Option", "Can you tell me why you're here?", "Why is there a mini-game sign here?", "Ok, thanks.");
		stage = 0;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Can you tell me why you're here?");
				stage = 10;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Why is there a mini-game sign here?");
				stage = 20;
				break;
			case 3:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Ok, thanks.");
				stage = 30;
				break;

			}
			break;
		case 10:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Of course. I plan to assist the forces of Saradomin who", "will come out to aid, and fight against the legion of", "vamypyres that infest this foul place. These brave", "mercenaries shall need a guide.");
			stage = 11;
			break;
		case 20:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Mini-game sign? Surely sir it is confused and not at all", "in their right mind.");
			stage = 21;
			break;
		case 30:
			end();
			break;
		case 21:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "This is the collection area for the forces of Saradomin.", "If you know of the plight of the Myreque, you should", "understand what is at stake! Aid the Myreque if you", "can and attempt to draw back the darkness of");
			stage = 22;
			break;
		case 22:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Hallowvale.");
			stage = 23;
			break;
		case 23:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Then perhaps you can then be a guiding light for the", "forces of Saradomin, as they enter the lair of the beast.");
			stage = 24;
			break;
		case 24:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Ok, thanks.");
			stage = 25;
			break;
		case 25:
			end();
			break;
		case 11:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "When a guide arrives, I will aid the mercenary by", "holding their wealth for their eventual return. The", "mercenary will repay the guide by giving him a token", "for items which I am holding.");
			stage = 12;
			break;
		case 12:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "I shall give such items to the guide as a payment for their", "services once I'm handed the token.");
			stage = 13;
			break;
		case 13:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "What items will you hold?");
			stage = 14;
			break;
		case 14:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Oh, the range of items is very varied. Mercenaries tend", "to hold raw materials in high esteem and so will deposit", "those with me. They also seem to be good battering", "items.");
			stage = 15;
			break;
		case 15:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Generally, things like uncooked lobsters, coal, silver bars,", "bow strings. Things which are useful! Plus the odd", "tome of experience, which are considered incredibly", "useful.");
			stage = 16;
			break;
		case 16:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Okay, thanks.");
			stage = 17;
			break;
		case 17:
			end();
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 1514 };
	}
}
