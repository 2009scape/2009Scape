package plugin.dialogue;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.game.node.entity.player.Player;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.node.item.Item;

/**
 * Represents the dialogue plugin used for the lucy npc.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class LucyPartyRoomDialoguePlugin extends DialoguePlugin {

	/**
	 * Represents the npc ids.
	 */
	private static final int[] NPC_IDS = { 662 };

	/**
	 * Represents the coins item.
	 */
	private static final Item COINS = new Item(995, 2);

	/**
	 * Represents the beer item.
	 */
	private static final Item BEER = new Item(1917, 1);

	/**
	 * Constructs a new {@code LucyPartyRoomDialoguePlugin} {@code Object}.
	 */
	public LucyPartyRoomDialoguePlugin() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code LucyPartyRoomDialoguePlugin} {@code Object}.
	 * @param player the player.
	 */
	public LucyPartyRoomDialoguePlugin(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new LucyPartyRoomDialoguePlugin(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Hi! I'm Lucy. Welcome to the Party Room!");
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Hi.");
			stage = 1;
			break;
		case 1:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Would you like to buy a beer?");
			stage = 2;
			break;
		case 2:
			interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "How much do they cost?");
			stage = 3;
			break;
		case 3:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Just 2 gold pieces.");
			stage = 4;
			break;
		case 4:
			interpreter.sendOptions("Select an Option", "Yes please!", " No thanks, I can't afford that.");
			stage = 5;
			break;
		case 5:
			switch (interfaceId) {
			case 228:
				switch (buttonId) {
				case 1:
					interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Yes please!");
					stage = 10;
					break;
				case 2:
					interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "No thanks, I can't afford that.");
					stage = 69;
					break;
				}
				break;
			}
			break;
		case 10:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Coming right up sir!");
			stage = 11;
			break;
		case 11:
			if (!player.getInventory().containsItem(COINS)) {
				end();
				return true;
			}
			if (player.getInventory().remove(COINS)) {
				interpreter.sendItemMessage(1917, "Lucy has given you a beer.");
				player.getInventory().add(BEER);
				stage = 100;
			} else {
				end();
				player.getPacketDispatch().sendMessage("You don't have enough coins.");
			}
			break;
		case 69:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "I see. Well, come and see me if you change your mind. YOu", "know where I am!");
			stage = 100;
			break;
		case 100:
			end();
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return NPC_IDS;
	}
}
