package plugin.dialogue;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.node.entity.player.Player;

/**
 * Represents the longow ben dialogue plugin.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class LongbowBenDialogue extends DialoguePlugin {

	/**
	 * Constructs a new {@code LongbowBenDialogue} {@code Object}.
	 */
	public LongbowBenDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code LongbowBenDialogue} {@code Object}.
	 * @param player the player.
	 */
	public LongbowBenDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new LongbowBenDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Arrr, matey!");
		stage = 0;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			interpreter.sendOptions("What would you like to say?", "Why are you called Longbow Ben?", "Have you got any quests I could do?");
			stage = 1;
			break;
		case 1:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Why are you called Longbow Ben?");
				stage = 100;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Have you got any quests I could do?");
				stage = 200;
				break;
			}
			break;
		case 10:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "I was to be marooned, ye see. A srurvy troublemaker had", "taken my ship, and he put  me ashore on a little island.");
			stage = 11;
			break;
		case 11:
			interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Gosh, how did you escape?");
			stage = 12;
			break;
		case 12:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Arrr, ye see, he made on mistake! Before he sailed", "he gave me a bow and one arrow so that I wouldn't have", "to die slowly.");
			stage = 13;
			break;
		case 13:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "So I shot him and took my ship back.");
			stage = 14;
			break;
		case 14:
			interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Right...");
			stage = 15;
			break;
		case 15:
			end();
			break;
		case 100:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Arrr, that's a strange yarn.");
			stage = 10;
			break;
		case 200:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Nay, I've nothing for ye to do.");
			stage = 201;
			break;
		case 201:
			interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Thanks.");
			stage = 202;
			break;
		case 202:
			end();
			break;
		}

		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 2691 };
	}
}
