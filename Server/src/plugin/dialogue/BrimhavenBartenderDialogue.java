package plugin.dialogue;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.game.node.entity.player.Player;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.node.item.Item;

/**
 * Represents the dialogue plugin used for the brimhaven bartender.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class BrimhavenBartenderDialogue extends DialoguePlugin {

	/**
	 * Represents the grog item.
	 */
	private static final Item GROG = new Item(1915);

	/**
	 * Represents the rum item.
	 */
	private static final Item RUM = new Item(8940);

	/**
	 * Represents the coins item.
	 */
	private static final Item COINS = new Item(995, 3);

	/**
	 * Represents the amount of coins to buy a pin of rum.
	 */
	private static final Item RUM_COINS = new Item(995, 27);

	/**
	 * Constructs a new {@code BrimhavenBartenerDialogue.java} {@code Object}.
	 */
	public BrimhavenBartenderDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code BrimhavenBartenerDialogue.java} {@code Object}.
	 * @param player the player.
	 */
	public BrimhavenBartenderDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new BrimhavenBartenderDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Yohoho me hearty what would you like to drink?");
		stage = 99;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 99:
			interpreter.sendOptions("Select an Option", "Nothing, thank you.", "A pint of Grog please.", "A bottle of rum please.");
			stage = 0;
			break;
		case 0:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Nothing, thank you.");
				stage = 10;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "A pint of Grog please..");
				stage = 20;
				break;
			case 3:
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "A bottle of rum please.");
				stage = 30;
				break;
			}
			break;
		case 10:
			end();
			break;
		case 20:
			interpreter.sendDialogues(npc, null, "One grog coming right up, that'll be three coins.");
			stage = 21;
			break;
		case 21:
			if (!player.getInventory().containsItem(COINS)) {
				interpreter.sendDialogues(player, null, "Sorry, I don't seem to have enough coins.");
				stage = 22;
				return true;
			}
			if (!player.getInventory().remove(COINS)) {
				interpreter.sendDialogues(player, null, "Sorry, I don't seem to have enough coins.");
				stage = 22;
				return true;
			}
			if (player.getInventory().add(GROG)) {
				player.getPacketDispatch().sendMessage("You buy a pint of Grog.");
				end();
			}
			break;
		case 22:
			end();
			break;
		case 30:
			interpreter.sendDialogues(npc, null, "That'll be 27 coins.");
			stage = 31;
			break;
		case 31:
			if (!player.getInventory().remove(RUM_COINS)) {
				interpreter.sendDialogues(player, null, "Sorry, I don't seem to have enough coins.");
				stage = 22;
				return true;
			}
			if (player.getInventory().add(RUM)) {
				end();
				player.getPacketDispatch().sendMessage("You buy a bottle of rum.");
			}
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 735 };
	}
}
