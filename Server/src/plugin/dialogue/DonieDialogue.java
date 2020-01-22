package plugin.dialogue;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.game.node.entity.player.Player;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.world.GameWorld;

/**
 * Represents the dialogue plugin used for the donie npc.
 * @author 'Vexia
 * @version 1.00
 */
@InitializablePlugin
public final class DonieDialogue extends DialoguePlugin {

	/**
	 * Constructs a new {@code DonieDialogue} {@code Object}.
	 */
	public DonieDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code DonieDialogue} {@code Object}.
	 * @param player the player.
	 */
	public DonieDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new DonieDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Hello there, can I help you?");
		stage = 0;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			interpreter.sendOptions("Select an Option", "Where am I?", "How are you today?", "Your shoe lace is untied.");
			stage = 1;
			break;
		case 1:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Where am I?");
				stage = 10;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "How are you today?");
				stage = 20;
				break;
			case 3:
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Your shoe lace is untied.");
				stage = 30;
				break;
			}
			break;
		case 10:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "This is the town of Lumbridge my friend.");
			stage = 11;
			break;
		case 11:
			interpreter.sendOptions("Select an Option", "Where am I?", "How are you today?", "Your shoe lace is untied.");
			stage = 1;
			break;
		case 20:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Aye, not too bad thank you. Lovely weather in", "" + GameWorld.getName() + " this fine day.");
			stage = 21;
			break;
		case 21:
			interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Weather?");
			stage = 22;
			break;
		case 22:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Yes weather, you know.");
			stage = 23;
			break;
		case 23:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "The state or condition of the atmosphere at a time and", "place, with respect to variables such as temperature,", "moisture, wind velocity, and barometric pressure.");
			stage = 24;
			break;
		case 24:
			interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "...");
			stage = 25;
			break;
		case 25:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Not just a pretty face eh? Ha ha ha.");
			stage = 26;
			break;
		case 26:
			end();
			break;
		case 30:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "No it's not!");
			stage = 31;
			break;
		case 31:
			interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "No you're right. I have nothing to back that up.");
			stage = 32;
			break;
		case 32:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Fool! Leave me alone!");
			stage = 33;
			break;
		case 33:
			end();
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 2238 };
	}
}
