package plugin.dialogue;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.game.node.entity.player.Player;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.node.item.Item;

/**
 * Handles the RangedTutorDialogue dialogue.
 * @author 'Vexia
 */
@InitializablePlugin
public class RangedTutorDialogue extends DialoguePlugin {

	/**
	 * Represnets the bow item.
	 */
	private final Item BOW = new Item(9705);

	/**
	 * Represents the arrow item.
	 */
	private final Item ARROW = new Item(9706);

	public RangedTutorDialogue() {

	}

	public RangedTutorDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {

		return new RangedTutorDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		if (args.length == 2) {
			if (player.getSavedData().getGlobalData().getTutorClaim() > System.currentTimeMillis()) {
				interpreter.sendDialogues(npc, null, "I work with the Ranged Combat tutor to give out", "consumable items that you may need for combat such", "as arrows and runes. However we have had some", "cheeky people try to take both!");
				stage = 200;
				return true;
			}
			stage = 99;
			if (player.getInventory().containsItem(BOW)) {
				interpreter.sendDialogues(npc, null, "You have a training bow in your inventory.");
				return true;
			}
			if (player.getBank().containsItem(BOW)) {
				interpreter.sendDialogues(npc, null, "You have a training bow in your bank.");
				return true;
			}
			if (player.getEquipment().containsItem(BOW)) {
				interpreter.sendDialogues(npc, null, "You're wielding your training bow.");
				return true;
			}
			if (player.getInventory().containsItem(ARROW)) {
				interpreter.sendDialogues(npc, null, "You have a training arrows in your inventory.");
				return true;
			}
			if (player.getBank().containsItem(ARROW)) {
				interpreter.sendDialogues(npc, null, "You have a training arrows in your bank.");
				return true;
			}
			if (player.getEquipment().containsItem(ARROW)) {
				interpreter.sendDialogues(npc, null, "You're wielding training arrows.");
				return true;
			}
			if (player.getInventory().freeSlots() < 2) {
				end();
				player.getPacketDispatch().sendMessage("Not enough inventory space.");
				return true;
			}
			if (player.getInventory().add(BOW)) {
				interpreter.sendItemMessage(BOW, "Nemarti gives you a Training Shortbow. It can only be used with Training Arrows.");
				stage = 230;
				player.getSavedData().getGlobalData().setTutorClaim(System.currentTimeMillis() + 1800000);
				return true;
			}
			return true;
		}
		interpreter.sendOptions("Select an Option", "Can you teach me the basics please?", "What about fletching?", "Goodbye.");
		stage = 1;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 230:
			final Item arrows = new Item(ARROW.getId(), 25);
			if (player.getInventory().add(arrows)) {
				interpreter.sendItemMessage(arrows, "Nemarti gives you 25 training arrows. They can only be used with the Training Shortbow.");
				stage = 231;
				player.getSavedData().getGlobalData().setTutorClaim(System.currentTimeMillis() + 1800000);
				return true;
			}
			break;
		case 231:
			end();
			break;
		case 99:
			end();
			break;
		case 1:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Can you teach me the basics please?");
				stage = 10;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "What about fletching?");
				stage = 20;
				break;
			case 3:
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Goodbye.");
				stage = 30;
				break;

			}
			break;
		case 10:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "To start with you'll need a bow and arrows.");
			stage = 11;
			break;
		case 11:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Mikasi, the Magic Combat tutor and I both give you", "items every 30 minutes, however you must choose", "wether you want runes or ranged equipment. To", "claim ranged equipment, right-click on me and choose");
			stage = 12;
			break;
		case 12:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Claim, to claim runes right-click on the Magic Combat", "tutor and select Claim.");
			stage = 13;
			break;
		case 13:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "When you have both bow and arrows, wield them by", "right-clicking on them in your inventory and selecting", "wield.");
			stage = 14;
			break;
		case 14:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "To set the way you shoot, click on the crossed swords", "above your inventory. This will open the combat", "interface where you can pick how you shoot your bow.", "Accurate means that you will shoot less often but be");
			stage = 15;
			break;
		case 15:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "more likely to hit, rapid means you shoot more often", "but might not hit so often and long range means just", "that, it increases your range. I prefer rapid personally,", "experiment and try it out!");
			stage = 16;
			break;
		case 16:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "The Training Shortbow and Training Arrows can only", "be used together. Remember to pick up your arrows,", "re-use them and come back when you need more.");
			stage = 17;
			break;
		case 17:
			end();
			break;
		case 20:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Ahh the art of making your own bow and arrows. It's", "quite simple really. You'll need an axe to cut some logs", "from trees and a knife. Knives can be found in and", "arround the Lumbridge castle and in the Varrock");
			stage = 21;
			break;
		case 21:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "General store upstairs.");
			stage = 22;
			break;
		case 22:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Use your knife on the logs, this will bring up a list of", "items you can make. Right-click on the item of your", "choice and choose the amount to fletch.");
			stage = 23;
			break;
		case 23:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "For arrows you will need to smith some arrow heads", "and kill some chickens for feathers. Add the feathers", "and heads to the shafts to make arrows you can use.");
			stage = 24;
			break;
		case 24:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "You'll need to find a flax field, there's one south of", "Seer's Village. Gather flax, then spin it on a spinning", "wheel, there's one in Seers' Village too. This makes bow", "strings which you can then use on the unstrung bows");
			stage = 25;
			break;
		case 25:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "to make a working bow!");
			stage = 26;
			break;
		case 26:
			interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Brilliant. If I forget anything I'll come talk to you", "again.");
			stage = 27;
			break;
		case 27:
			interpreter.sendOptions("Select an Option", "Can you teach me the basics please?", "What about fletching?", "Goodbye.");
			stage = 1;
			break;
		case 30:
			end();
			break;
		case 200:
			interpreter.sendDialogues(npc, null, "So, every half an hour, you may come back and claim", "either arrows OR runes, but not both. Come back in a", "while for runes, or simply make your own.");
			stage = 201;
			break;
		case 201:
			end();
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 1861 };
	}
}
