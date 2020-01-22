package plugin.dialogue;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.node.entity.player.Player;

/**
 * Handles the ScoolgirlMuseumDialogue dialogue.
 * @author 'Vexia
 */
@InitializablePlugin
public class ScoolgirlMuseumDialogue extends DialoguePlugin {

	public ScoolgirlMuseumDialogue() {

	}

	public ScoolgirlMuseumDialogue(Player player) {
		super(player);
	}

	@Override
	public int[] getIds() {
		return new int[] { 5984 };
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {

		switch (stage) {
		case 0:
			end();
			break;
		}

		return true;
	}

	@Override
	public DialoguePlugin newInstance(Player player) {

		return new ScoolgirlMuseumDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "I wanna be an archaeologist when I grow up!");
		stage = 0;
		return true;
	}
}
