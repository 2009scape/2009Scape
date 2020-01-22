package plugin.dialogue;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.node.entity.player.Player;

/**
 * Handles the UliziusDialogue dialogue.
 * @author 'Vexia
 */
@InitializablePlugin
public class UliziusDialogue extends DialoguePlugin {

	public UliziusDialogue() {

	}

	public UliziusDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {

		return new UliziusDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Hello there.");
		stage = 0;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "What... Oh, don't creep up on me like that... I thought", "you were a Ghast!");
			stage = 1;
			break;
		case 1:
			interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Can I go through the gate please?");
			stage = 2;
			break;
		case 2:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Absolutely not. I've been given strict instructions not to", "let anyone through. It's just too dangerous. No one", "gets in without Drezels say so!");
			stage = 3;
			break;
		case 3:
			interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Where is Drezel?");
			stage = 4;
			break;
		case 4:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Oh, he's in the temple, just go back over the bridge,", "down the ladder and along the hallway, you can't miss", "him.");
			stage = 5;
			break;
		case 5:
			end();
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 1054 };
	}
}
