package plugin.dialogue;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.content.skill.Skills;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.game.node.entity.player.Player;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.node.item.Item;

/**
 * Represents the woodsman tutor dialogue.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class WoodsmanTutorDialogue extends DialoguePlugin {

	/**
	 * Constructs a new {@code WoodsmanTutorDialogue} {@code Object}.
	 */
	public WoodsmanTutorDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code WoodsmanTutorDialogue} {@code Object}.
	 * @param player the player.
	 */
	public WoodsmanTutorDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new WoodsmanTutorDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		if (player.getSkills().getLevel(Skills.WOODCUTTING) >= 99) {
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Wow! It's not often I meet somebody as accomplished", "as me in Woodcutting! Seeing as youre so skilled,", "maybe you are interested in buying a Skillcape of", "Woodcutting?");
			stage = 100;
		} else {
			interpreter.sendOptions("Select an Option", "Tell me about different trees and axes.", "What is that cape you're wearing?", "Goodbye.");
			stage = 500;
		}
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Who are you?");
				stage = 10;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "What is that cape you're wearing?");
				stage = 20;
				break;

			}
			break;
		case 10:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "My name is Wilfred and I'm the best woodsman in", "Asgarnia! I've spent my life studying the best methods for", "woodcutting. That's why I have this cape, the Skillcape of", "Woodcutting.");
			stage = 11;
			break;
		case 11:
			end();
			break;
		case 20:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "This is a Skillcape of Woodcutting. Only a person who has", "achieved the highest possible level in a skill can wear one.");
			stage = 21;
			break;
		case 21:
			end();
			break;
		case 100:
			interpreter.sendOptions("Select an Option", "Yes, please.", "No, thank you.");
			stage = 101;
			break;
		case 101:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Yes, please.");
				stage = 1000;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "No, thank you.");
				stage = 2000;
				break;
			}
			break;
		case 2000:
			interpreter.sendOptions("Select an Option", "Tell me about different trees and axes.", "What is that cape you're wearing?", "Goodbye.");
			stage = 500;
			break;
		case 2002:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Bye!");
			stage = 2003;
			break;
		case 2003:
			end();
			break;
		case 1000:
			if (player.getInventory().freeSlots() == 1) {
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Sorry, I don't have enough inventory space.");
				stage = 99;
			}
			if (player.getInventory().contains(995, 99000)) {
				player.getInventory().remove(new Item(995, 99000));
				player.getInventory().add(new Item(9807 + (player.getSkills().getMasteredSkills() > 1 ? 1 : 0)));
				player.getInventory().add(new Item(9809, 1));
				interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Excellent! Wear that cape with pride my friend.");
				stage = 107;
			} else {
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Sorry, I don't seem to have enough coins.");
				stage = 160;
			}
			break;
		case 99:
			end();
			break;
		case 160:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Very well, farewell adventurer.");
			stage = 2001;
			break;
		case 107:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Will do, Wilfred.");
			stage = 108;
			break;
		case 108:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Very well, farewell adventurer.");
			stage = 2001;
			break;
		case 2001:
			end();
			break;
		case 500:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, null, "Tell me about different trees and axes.");
				stage = 510;
				break;
			case 2:
				interpreter.sendDialogues(player, null, "What is that cape you're wearing?");
				stage = 520;
				break;
			case 3:
				interpreter.sendDialogues(player, null, "Goodbye.");
				stage = 530;
				break;
			}
			break;
		case 510:
			interpreter.sendOptions("Trees", "Oak and Willow", "Maple and Yew", "Magic and other trees", "Axes", "Go back to teaching");
			stage = 511;
			break;
		case 511:
			switch (buttonId) {
			case 1:
				interpreter.sendDoubleItemMessage(1511, 1521, "Almost every tree can be chopped down. Normal logs will be produced by chopping 'Trees' and Oak logs will come from chopping 'Oak Trees'. You can find Oak trees in amongst normal trees scatterd about the");
				stage = 5100;
				break;
			case 2:
				interpreter.sendItemMessage(1517, "Maple logs can be gleaned from Maple trees. You'll usually find Maple trees standing alone amongst other trees.");
				stage = 5200;
				break;
			case 3:
				interpreter.sendItemMessage(1513, "Magic trees are... magic. A difficult wood to work with, but worth it for the rewards. Find them in the areas south of Seers village or on the East side of the Mage arena.");
				stage = 5300;
				break;
			case 4:
				interpreter.sendItemMessage(1351, "Bronze axes are easy to get, simply go visit Bob in his shop in Lumbridge, or talk to me if you have mislaid yours.");
				stage = 5400;
				break;
			case 5:
				interpreter.sendOptions("Select an Option", "Tell me about different trees and axes.", "What is that cape you're wearing?", "Goodbye.");
				stage = 500;

				break;
			}
			break;
		case 5400:
			interpreter.sendDialogues(npc, null, "As you progress in your combat skill you will find you", "can wield your woodcutting axe as a weapon, it's not", "very effective, but it frees up a slot for another log.");
			stage = 5401;
			break;
		case 5401:
			interpreter.sendDoubleItemMessage(1349, 1353, "As your woodcutting skill increases you will find yourself able to use better axes to chop trees faster.... anything up to steel you can buy from Bob's axe shop.");
			stage = 5402;
			break;
		case 5402:
			interpreter.sendItemMessage(1359, "Rune axes can be player made with very high level smithing and mining. They can also be obtained through killing one of the fearsome tree spirits, though this is very rare.");
			stage = 510;
			break;
		case 5300:
			interpreter.sendDialogues(npc, null, "Hollow trees can be found in the Haunted Woods east", "of Canifis, but be careful of the leeches.");
			stage = 510;
			break;
		case 5200:
			interpreter.sendItemMessage(1515, "Yew trees are few and far between. We do our best to cultivate them. Look for the tree icon on your mini map to find rare trees. Try North of Port Sarim.");
			stage = 510;
			break;
		case 5100:
			interpreter.sendDoubleItemMessage(1511, 1521, "lands.");
			stage = 5101;
			break;
		case 5101:
			interpreter.sendItemMessage(1519, "Willow trees will yield willow logs. You'll find willows like to grow near water, you can find some south of Draynor.");
			stage = 5102;
			break;
		case 5102:
			interpreter.sendOptions("Trees", "Oak and Willow", "Maple and Yew", "Magic and other trees", "Axes", "Go back to teaching");
			stage = 511;
			break;
		case 520:
			interpreter.sendDialogues(npc, null, "This is a Skillcape of Woodcutting. Only a person who", "has achieved the highest possible level in a skill can wear", "one.");
			stage = 530;
			break;
		case 530:
			end();
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 4906 };
	}
}
