package plugin.dialogue;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.node.entity.player.Player;

/**
 * Represents the dialogue plugin used for zambo.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class ZamboDialogue extends DialoguePlugin {

	/**
	 * Constructs a new {@code ZamboDialogue} {@code Object}.
	 */
	public ZamboDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code ZamboDialogue} {@code Object}.
	 * @param player the player.
	 */
	public ZamboDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new ZamboDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Hey, are you wanting to try some of my fine wines", "and spririts? All brewed locally on Karamja island.");
		stage = 0;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			interpreter.sendOptions("Select an Option", "Yes, please.", "No, thank you");
			stage = 1;
			break;
		case 1:
			switch (buttonId) {
			case 1:
				end();
				npc.openShop(player);
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "No, thank you.");
				stage = 20;
				break;
			}
			break;
		case 20:
			end();
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 568 };
	}
}
