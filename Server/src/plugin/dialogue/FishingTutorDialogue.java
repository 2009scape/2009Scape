package plugin.dialogue;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.node.entity.player.Player;

/**
 * Represents the fishing tutor dialogue plugin.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class FishingTutorDialogue extends DialoguePlugin {

	/**
	 * Constructs a new {@code FishingTutorDialogue} {@code Object}.
	 */
	public FishingTutorDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code FishingTutorDialogue} {@code Object}.
	 * @param player the player.
	 */
	public FishingTutorDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new FishingTutorDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendOptions("Select an Option", "Can you teach me the basics of fishing please?", "Tell me about different fish.", "Where and what should I fish?", "Goodbye.");
		stage = 1;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 1:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Can you teach me the basics of fishing please?");
				stage = 10;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Tell me about different fish.");
				stage = 20;
				break;
			case 3:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Where and what should I fish?");
				stage = 30;
				break;
			case 4:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Goodbye.");
				stage = 40;
				break;
			}
			break;
		case 10:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Ahoy, to fish, you click on your net in your inventory,", "then on the fishin' spot to put it in. Then you pull the", "net out and see if you got any shrimp.");
			stage = 11;
			break;
		case 11:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "I see.. is that it?");
			stage = 12;
			break;
		case 12:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "There's far more as you progress, not just shrimps,", "you get more equipment, bigger fish and other things", "too...");
			stage = 13;
			break;
		case 13:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "When you have a full inventory, you can cook it or", "take it to the bank. You can find a bank on the roof of", "the castle in Lumbridge and a cookin' range in the", "castles kitchen.");
			stage = 14;
			break;
		case 14:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Arrr.. if yer lookin' fer quests you should head to", "the Mountain Dwarf who lies north-west of Taverely", "and have a chat with him, I'm sure he can point you in", "the right direction.");
			stage = 15;
			break;
		case 15:
			interpreter.sendOptions("Select an Option", "Can you teach me the basics of fishing please?", "Tell me about different fish.", "Where and what should I fish?", "Goodbye.");
			stage = 1;
			break;
		case 20:
			interpreter.sendOptions("Select an Option", "Small Net Fish", "Big Net Fish", "Rod and Fly Fishing", "Tell me about...");
			stage = 21;
			break;
		case 21:
			switch (buttonId) {
			case 1:
				interpreter.sendItemMessage(303, "Ahoy, small net fishin' you can do just south of Draynor Village and in these very spots here. Aye.");
				stage = 210;
				break;
			case 2:
				interpreter.sendItemMessage(305, "Aye, you can net yourself some big fish in Catherby, which is a good place to fish for most things, Gar!");
				stage = 220;
				break;
			case 3:
				interpreter.sendItemMessage(307, "Aye, rod fishin' can be practiced here at these spots, as well as south of Draynor Village and in the Lumbridge river, depending upon your experience. You can get bait at any fishin' shop, there be one in Port Sarim.");
				stage = 230;
				break;
			case 4:
				interpreter.sendOptions("Select an Option", "Can you teach me the basics of fishing please?", "Tell me about different fish.", "Where and what should I fish?", "Goodbye.");
				stage = 1;
				break;
			}
			break;
		case 210:
			interpreter.sendItemMessage(315, "Shrimps and anchovies can be caught with your small fishin' net");
			stage = 20;
			break;
		case 220:
			interpreter.sendItemMessage(355, "Mackerel and Cod will form the backbone of your catch when big net fishin'.. except for the added extras...");
			stage = 221;
			break;
		case 221:
			interpreter.sendDialogues(npc, null, "Some rich rewards for big net fishin', make sure you be", "using a big net fishing spot though...");
			stage = 20;
			break;
		case 230:
			interpreter.sendItemMessage(351, "With a rod you can catch pike, sardines and herring. Good eating on them.");
			stage = 231;
			break;
		case 231:
			interpreter.sendItemMessage(309, "The art of fly fishin' can be done in rivers, so the Lumbridge river here would suffice.");
			stage = 232;
			break;
		case 232:
			interpreter.sendItemMessage(329, "Aye, you can catch yourself a delicious trout or salmon.");
			stage = 20;
			break;
		case 30:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Herrin' can be fished from Catherby and some other", "places when you reach level 10.");
			stage = 31;
			break;
		case 31:
			interpreter.sendOptions("Select an Option", "Can you teach me the basics of fishing please?", "Tell me about different fish.", "Where and what should I fish?", "Goodbye.");
			stage = 1;
			break;
		case 40:
			end();
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 4901 };
	}
}
