package plugin.dialogue;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.node.entity.player.Player;

/**
 * Represents the dialogue plugin used for the gee npc.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class GeeDialogue extends DialoguePlugin {

	/**
	 * Constructs a new {@code GeeDialogue} {@code Object}.
	 */
	public GeeDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code GeeDialogue} {@code Object}.
	 * @param player the player.
	 */
	public GeeDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new GeeDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Hello there, can I help you?");
		stage = 0;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			interpreter.sendOptions("Select an Option", "What's up?", "Are there any quests I can do here?", "Can I buy your stick?");
			stage = 1;
			break;
		case 1:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "What's up?");
				stage = 10;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Do you know of any quests I can do?");
				stage = 20;
				break;
			case 3:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Can I buy your stick?");
				stage = 30;
				break;

			}
			break;
		case 10:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "I assume the sky is up..");
			stage = 11;
			break;
		case 11:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "You assume?");
			stage = 12;
			break;
		case 12:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Yeah, unfortunately I don't seem to be able to look up.");
			stage = 13;
			break;
		case 13:
			end();
			break;
		case 20:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Nope, sorry.");
			stage = 21;
			break;
		case 21:
			end();
			break;
		case 30:
			interpreter.sendDialogues(npc, FacialExpression.FURIOUS, "It's not a stick! I'll have you know it's a very powerful", "staff!");
			stage = 31;
			break;
		case 31:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Really? Show me what it can do!");
			stage = 32;
			break;
		case 32:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Um..It's a bit low on power at the moment..");
			stage = 33;
			break;
		case 33:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "It's a stick isn't it?");
			stage = 34;
			break;
		case 34:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "...Ok it's a stick.. But only while I save up for a staff.", "Zaff in Varrock square sells them in his shop.");
			stage = 35;
			break;
		case 35:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Well good luck with that.");
			stage = 36;
			break;
		case 36:
			end();
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 2237 };
	}
}
