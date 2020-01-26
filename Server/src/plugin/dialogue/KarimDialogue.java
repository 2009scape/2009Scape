package plugin.dialogue;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.game.node.entity.player.Player;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.node.item.Item;

/**
 * Represents the karim dialogue plugin.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class KarimDialogue extends DialoguePlugin {

	/**
	 * Represents the coins item.
	 */
	private static final Item COINS = new Item(995, 1);

	/**
	 * Represents the mud kebab item.
	 */
	private static final Item KEBAB = new Item(1971, 1);

	/**
	 * Constructs a new {@code KarimDialogue} {@code Object}.
	 */
	public KarimDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code KarimDialogue} {@code Object}.
	 * @param player the player.
	 */
	public KarimDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new KarimDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Would you like to buy a nice kebab? Only one gold.");
		stage = 0;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			interpreter.sendOptions("Select an Option", "I think I'll give it a miss.", "Yes please.");
			stage = 1;
			break;
		case 1:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "I think I'll give it a miss.");
				stage = 10;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Yes please.");
				stage = 20;
				break;

			}
			break;
		case 10:
			end();
			break;
		case 20:
			if (player.getInventory().freeSlots() == 0) {
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "I don't have enough room, sorry.");
				stage = 21;
			} else if (!player.getInventory().contains(995, 1)) {
				end();
				player.getPacketDispatch().sendMessage("You need 1 gp to buy a kebab.");
			} else {
				player.getInventory().remove(COINS);
				player.getInventory().add(KEBAB);
				end();
			}
			break;
		case 21:
			end();
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 543 };
	}
}
