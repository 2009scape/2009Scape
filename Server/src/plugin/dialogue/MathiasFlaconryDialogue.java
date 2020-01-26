package plugin.dialogue;

import org.crandor.game.container.impl.EquipmentContainer;
import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.content.skill.Skills;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.game.node.entity.player.Player;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.node.item.Item;

/**
 * Handles the MathiasFlaconryDialogue dialogue.
 * @author 'Vexia
 */
@InitializablePlugin
public class MathiasFlaconryDialogue extends DialoguePlugin {

	public MathiasFlaconryDialogue() {
	}

	public MathiasFlaconryDialogue(Player player) {
		super(player);
	}

	@Override
	public int[] getIds() {
		return new int[] { 5093 };
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 90:
			interpreter.sendOptions("Select an Option", "Ok, that seems reasonable.", "I'm not interested then, thanks.");
			stage = 91;
			break;
		case 91:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Ok, that seems reasonable.");
				stage = 95;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "I'm not interested then, thanks.");
				stage = 323;
				break;

			}
			break;
		case 323:
			end();
			break;
		case 95:
			if (player.getBank().containsItem(FALCON) || player.getEquipment().containsItem(FALCON) || player.getInventory().containsItem(FALCON)) {
				interpreter.sendDialogues(5093, FacialExpression.NO_EXPRESSION, "You already have a falcon!");
				stage = 99;
				return true;
			}
			if (player.getEquipment().get(EquipmentContainer.SLOT_HANDS) != null || player.getEquipment().get(EquipmentContainer.SLOT_SHIELD) != null || player.getEquipment().get(EquipmentContainer.SLOT_WEAPON) != null) {
				interpreter.sendDialogues(5093, FacialExpression.NO_EXPRESSION, "Sorry, free your hands, weapon, and shield slot first.");
				stage = 99;
				break;
			}
			if (player.getInventory().contains(995, 500)) {
				player.getInventory().remove(new Item(995, 500));
				player.getEquipment().add(new Item(10024), true, false);
				interpreter.sendDialogue("The falconer gives you a large leather glove and brings one of the", "smaller birds over to land on it.");
				stage = 97;
			} else {
				end();
				player.getPacketDispatch().sendMessage("You need 500 gold goins.");
			}
			break;
		case 97:
			interpreter.sendDialogues(5093, FacialExpression.NO_EXPRESSION, "Don't worry: I'll keep and eye on you to make sure", "you don't upset it roo much.");
			stage = 99;
			break;
		case 99:
			end();
			break;
		case 500:
			interpreter.sendDialogues(5093, FacialExpression.NO_EXPRESSION, "Greetings. Can I help you at all?");
			stage = 501;
			break;
		case 501:
			interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Could I have a go with your bird?");
			stage = 502;
			break;
		case 502:
			if (player.getSkills().getLevel(Skills.HUNTER) < 43) {
				npc("Try coming back when you're more experienced", "I wouldn't want my birds being injured.");
				stage = 967;
				return true;
			}
			interpreter.sendDialogues(5093, FacialExpression.NO_EXPRESSION, "Training falcons is a lot of work and I", "doubt you're up to the task. However, I suppose", "I could let you try hunting with one.");
			stage = 503;
			break;
		case 503:
			interpreter.sendDialogues(5093, FacialExpression.NO_EXPRESSION, "I have some tamer birds that I occasionally lend to rich", "noblemen who consider it a sufficiently refined sport for", "their tastes. and you look like the kind who might", "appreciate a good hunt.");
			stage = 90;
			break;
		case 900:
			interpreter.sendOptions("Select an Option", "Yes, please.", "No thank you.");
			stage = 901;
			break;
		case 901:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, null, "Yes, please.");
				stage = 920;
				break;
			case 2:
				end();
				break;
			}
			break;
		case 920:
			player.getBank().remove(FALCON, new Item(10023));
			player.getInventory().remove(new Item(10023), FALCON);
			player.getEquipment().add(FALCON, true, false);
			interpreter.sendDialogue("The falconer gives you a large leather glove and brings one of the", "smaller birds over to land on it.");
			stage = 97;
			break;
		case 967:
			end();
			break;
		}
		return true;
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new MathiasFlaconryDialogue(player);
	}

	private final Item FALCON = new Item(10024);

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		boolean quick = false;
		if (player.getEquipment().contains(10023, 1)) {
			interpreter.sendDialogues(5093, null, "Oh, it looks like you've lost your falcon.", "Would you like a new one?");
			stage = 900;
			return true;
		}
		if (args.length == 2)
			quick = true;
		if (quick) {
			interpreter.sendDialogues(5093, FacialExpression.NO_EXPRESSION, "If you wish to try falconry, I request a small fee. How", "does 500 gold coins sound?");
			stage = 90;
			return true;
		}
		interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Hello there.");
		stage = 500;
		return true;
	}
}
