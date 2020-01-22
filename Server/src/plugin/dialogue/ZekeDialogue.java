package plugin.dialogue;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.node.entity.player.Player;

/**
 * Represents the dialogue plugin used for zeke.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class ZekeDialogue extends DialoguePlugin {

	/**
	 * Constructs a new {@code ZekeDialogue} {@code Object}.
	 */
	public ZekeDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code ZekeDialogue} {@code Object}.
	 * @param player the player.
	 */
	public ZekeDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new ZekeDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "A thousand greetings, sir.");
		stage = 0;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			interpreter.sendOptions("Select an Option", "Do you want to trade?", "Nice cloak.", "Could you sell me a dragon scimitar?");
			stage = 1;
			break;
		case 1:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Do you want to trade?");
				stage = 10;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Nice cloak.");
				stage = 20;
				break;
			case 3:
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Could you sell me a dragon scimitar?");
				stage = 30;
				break;
			}
			break;
		case 10:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Yes, certainly. I deal in scimitars.");
			stage = 11;
			break;
		case 11:
			npc.openShop(player);
			end();
			break;
		case 20:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Thank you.");
			stage = 21;
			break;
		case 21:
			end();
			break;
		case 30:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "A dragon scimitar? A DRAGON scimitar?");
			stage = 31;
			break;
		case 31:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "No way, man!");
			stage = 32;
			break;
		case 32:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "The banana-brained nitwits who make them would never", "dream of selling any to to me.");
			stage = 33;
			break;
		case 33:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Seriously, you'll be a monkey's uncle before you'll ever", "hold a dragon scimitar.");
			stage = 34;
			break;
		case 34:
			interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Hmmm, funny you should say that...");
			stage = 35;
			break;
		case 35:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Perhaps you'd lie to take a look at my stock?");
			stage = 36;
			break;
		case 36:
			interpreter.sendOptions("Select an Option", "Yes, please, Zeke.", "Not today, thank you.");
			stage = 37;
			break;
		case 37:
			switch (buttonId) {
			case 1:
				npc.openShop(player);
				end();
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "No today, thank you.");
				stage = 2000;
				break;
			}
			break;
		case 2000:
			end();
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 541 };
	}

}
