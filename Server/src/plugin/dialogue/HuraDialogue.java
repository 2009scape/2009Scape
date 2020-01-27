package plugin.dialogue;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.node.entity.player.Player;

/**
 * Represents the dialogue plugin used for the hura npc.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class HuraDialogue extends DialoguePlugin {

	/**
	 * Constructs a new {@code HuraDialogue} {@code Object}.
	 */
	public HuraDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code HuraDialogue} {@code Object}.
	 * @param player the player.
	 */
	public HuraDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new HuraDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "'Ello " + player.getUsername() + ".");
		stage = 0;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Hello, what's that you've got there?");
			stage = 1;
			break;
		case 1:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "A crossbow, are you interested?");
			stage = 2;
			break;
		case 2:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Maybe, are they any good?");
			stage = 3;
			break;
		case 3:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Are they any good?! They're dwarven engrineering at", "its best!");
			stage = 4;
			break;
		case 4:
			interpreter.sendOptions("Select an Option", "How do I make one for myself?", "What about ammo?", "Thanks for teling me. Bye!");
			stage = 5;
			break;
		case 5:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "How do I make one for myself?");
				stage = 10;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "What about ammo?");
				stage = 20;
				break;
			case 3:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Thanks for telling me. Bye!");
				stage = 30;
				break;
			}
			break;
		case 10:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Well, firstly you'll need to chop yourself some wood,", "then use a knife on the wood to whittle out a nice", "crossbow stock like these here.");
			stage = 11;
			break;
		case 11:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Wood fletched into stock... check.");
			stage = 12;
			break;
		case 12:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Then get yourself some metal and a hammer and smith", "yourself some limbs for the bow, mind that you use the", "right metals and wood though as some wood is too light", "to use with some metal and vice versa.");
			stage = 13;
			break;
		case 13:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Which goes with which?");
			stage = 14;
			break;
		case 14:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Wood and Bronze as they're basic materials, Oak and", "Blurite, Willow and Iron, Steel and Teak, Mithril and", "Maple, Adamantite and Mahogany and finally Runite", "and Yew.");
			stage = 15;
			break;
		case 15:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Ok, so I have my stock and a pair of limbs... what now?");
			stage = 16;
			break;
		case 16:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Simply take a hammer and smack the limbs firmly onto", "the stock. You'll need a string, only they're not", "the same as normal bows. You'll need to dry some large", "animal's meat to get sinew, then spin that on a spinning");
			stage = 17;
			break;
		case 17:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "wheel, it's the only thing we've found to be strong", "enough for a crossbow.");
			stage = 18;
			break;
		case 18:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Thanks for telling me. Bye!");
			stage = 30;
			break;
		case 30:
			end();
			break;
		case 20:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "You can smith yourself lots of different bolts, don't", "forget to flight them with feathers like you do arrows", "though. You can poison any untipped bolt but there's", "also the option of tipping them with gems then");
			stage = 21;
			break;
		case 21:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "enchanting them with runes. This can have some pretty", "powerful effects.");
			stage = 22;
			break;
		case 22:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Oh my poor bank, how will I store all those?!");
			stage = 23;
			break;
		case 23:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Find Hirko in Keldagrim, he also sells crossbow parts", "and I'm sure he has something you can use to store", "bolts in.");
			stage = 24;
			break;
		case 24:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Thanks for the info.");
			stage = 25;
			break;
		case 25:
			end();
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 4563 };
	}
}
