package core.game.content.dialogue;

import core.game.container.impl.EquipmentContainer;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.plugin.Initializable;
import core.game.node.item.Item;

/**
 * Represents the dialogue plugin used for the del monty npc.
 * @author 'Vexia
 * @version 1.0
 */
@Initializable
public final class DelMontyDialogue extends DialoguePlugin {

	/**
	 * Constructs a new {@code DelMontyDialogue} {@code Object}.
	 */
	public DelMontyDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code DelMontyDialogue} {@code Object}.
	 * @param player the player.
	 */
	public DelMontyDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new DelMontyDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		player("Hey kitty!");
		stage = 1;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 100:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Meow.");
			stage = 0;
			break;
		default:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "\"Meow.\"");
			stage = 0;
			break;
		}
		return true;
	}

	/**
	 * Method used to check if a player has a cat speak amulet.
	 * @param player the player.
	 * @return {@code True} so.
	 */
	public static boolean hasCatAmulet(Player player) {
		Item item = player.getEquipment().get(EquipmentContainer.SLOT_AMULET);
		if (item == null)
			return false;
		return item.getId() == 4677 || item.getId() == 6544;
	}

	@Override
	public int[] getIds() {
		return new int[] { 5563 };
	}

}
