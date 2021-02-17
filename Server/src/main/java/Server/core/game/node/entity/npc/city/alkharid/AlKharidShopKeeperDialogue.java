package core.game.node.entity.npc.city.alkharid;

import core.game.content.dialogue.DialoguePlugin;
import core.game.content.dialogue.FacialExpression;
import core.game.node.entity.npc.NPC;
import core.plugin.Initializable;
import core.game.node.entity.player.Player;

/**
 * Represents the dialogue plugin used for the al kharid shop keepers.
 * @author 'Vexia
 * @version 1.0
 */
@Initializable
public final class AlKharidShopKeeperDialogue extends DialoguePlugin {

	/**
	 * Constructs a new {@code AlKahridShopKeeperDialogue} {@code Object}.
	 */
	public AlKharidShopKeeperDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code AlKahridShopKeeperDialogue} {@code Object}.
	 * @param player the player.
	 */
	public AlKharidShopKeeperDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new AlKharidShopKeeperDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Can I help you at all?");
		stage = 0;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			interpreter.sendOptions("Select an Option", "Yes, please. What are you selling?", "How should I use your shop?", "No, thanks.");
			stage = 1;
			break;
		case 1:
			switch (buttonId) {
			case 1:
				end();
				npc.openShop(player);
				break;
			case 2:
				interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "I'm glad you ask! You can buy as many of the items", "stocked as you wish. You can also sell most items to the", "shop.");
				stage = 20;
				break;
			case 3:
				end();
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
		return new int[] { 525, 524 };
	}

}
