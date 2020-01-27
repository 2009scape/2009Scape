package plugin.dialogue;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.item.GroundItemManager;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.node.item.Item;

/**
 * Represents the dialogue plugin used for the dward cart conductor.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class CartConductorDialogue extends DialoguePlugin {

	/**
	 * Represents the coins item.
	 */
	private static final Item COINS = new Item(995, 150);

	/**
	 * Represents the ticket item.
	 */
	private static final Item TICKET = new Item(5020);

	/**
	 * Constructs a new {@code CartConductorDialogue} {@code Object}.
	 */
	public CartConductorDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code CartConductorDialogue} {@code Object}.
	 * @param player the player.
	 */
	public CartConductorDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new CartConductorDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendOptions("Select an Option", "Who are you?", "Where can you take me?", "I'd like to buy a ticket.", "I have to go.");
		stage = 0;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Who are you?");
				stage = 10;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Where can you take me?");
				stage = 20;
				break;
			case 3:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "I'd like to buy a ticket.");
				stage = 30;
				break;
			case 4:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "I have to go.");
				stage = 40;
				break;
			case 10:
				interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "I'm an employee of Keldagrim Carts. I make sure the", "carts in this area run on time and that people pay their", "fares.");
				stage = 11;
				break;
			case 11:
				end();
				break;
			case 20:
				interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "I don't think I'm allowed to take you into the city of", "Keldagrim, human. Perhaps when you find another way", "into the city and talk to someone of importance there", "you will be allowed to.");
				stage = 21;
				break;
			case 21:
				end();
				break;
			case 30:
				interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "One ticket to Keldagrim, that's 150 coins then.");
				stage = 31;
				break;
			case 31:
				end();
				if (player.getInventory().containsItem(COINS) && player.getInventory().remove(COINS)) {
					if (!player.getInventory().add(TICKET)) {
						GroundItemManager.create(TICKET, player);
					}
				} else {
					player.getPacketDispatch().sendMessage("You don't have enough coins.");
				}
				break;
			case 40:
				end();
				break;
			}
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 2180 };
	}
}
