package plugin.dialogue;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.node.entity.player.Player;

/**
 * Handles the OllieTheCamelDialogue dialogue.
 * @author 'Vexia
 */
@InitializablePlugin
public class OllieTheCamelDialogue extends DialoguePlugin {

	public OllieTheCamelDialogue() {

	}

	public OllieTheCamelDialogue(Player player) {
		super(player);
	}

	@Override
	public int[] getIds() {
		return new int[] { 2811 };
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			end();
			player.getPacketDispatch().sendMessage("The camel tries to stamp on your foot, but you pull it back quickly.");
			break;
		}
		return true;
	}

	@Override
	public DialoguePlugin newInstance(Player player) {

		return new OllieTheCamelDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "I wonder if that camel has fleas...");
		stage = 0;
		return true;
	}
}
