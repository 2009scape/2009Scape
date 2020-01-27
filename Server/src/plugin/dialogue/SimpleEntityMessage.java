package plugin.dialogue;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.node.entity.player.Player;

/**
 * Handles the SimpleEntityMessage dialogue.
 * @author 'Vexia
 */
@InitializablePlugin
public class SimpleEntityMessage extends DialoguePlugin {

	public SimpleEntityMessage() {

	}

	public SimpleEntityMessage(Player player) {
		super(player);
	}

	@Override
	public int[] getIds() {
		return new int[] { 8000 };
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		end();
		return true;
	}

	@Override
	public DialoguePlugin newInstance(Player player) {

		return new SimpleEntityMessage(player);
	}

	@Override
	public boolean open(Object... args) {
		String[] messages = new String[args.length];
		for (int i = 0; i < messages.length; i++)
			messages[i] = (String) args[i];
		interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, messages);
		return true;
	}
}
