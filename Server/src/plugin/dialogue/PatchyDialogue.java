package plugin.dialogue;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.node.entity.player.Player;

/**
 * Represents the dialouge plugin used for the dward shop.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class PatchyDialogue extends DialoguePlugin {

	/**
	 * Constructs a new {@code DwarfShopDialogue} {@code Object}.
	 */
	public PatchyDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code DwarfShopDialogue} {@code Object}.
	 * @param player the player.
	 */
	public PatchyDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new PatchyDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Aye, can I help ye at all?");
		stage = 0;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			interpreter.sendOptions("Select an Option", "Yes please, what are you selling?", "No thanks.");
			stage = 1;
			break;
		case 1:
			switch (buttonId) {
			case 1:
				end();
				npc.openShop(player);
				break;
			case 2:
				end();
				break;
			}
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 4359 };
	}
}
