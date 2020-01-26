package plugin.dialogue;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.node.entity.player.Player;

/**
 * Handles the RanaelDialogue dialogue.
 * @author 'Vexia
 */
@InitializablePlugin
public class RanaelDialogue extends DialoguePlugin {

	public RanaelDialogue() {

	}

	public RanaelDialogue(Player player) {
		super(player);
	}

	@Override
	public int[] getIds() {
		return new int[] { 544 };
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			interpreter.sendOptions("Select an Option", "Yes, please.", "No, thank you, that's not my scene.");
			stage = 1;
			break;
		case 1:
			switch (buttonId) {
			case 1:
				end();
				npc.openShop(player);
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "No, thank you, that's not my scene.");
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
	public DialoguePlugin newInstance(Player player) {

		return new RanaelDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Do you want to buy any armoured skirts? Designed", "especially for ladies who like to fight.");
		stage = 0;
		return true;
	}
}
