package plugin.dialogue;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.content.eco.ge.GrandExchangeDatabase;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.node.entity.player.Player;

/**
 * Handles the GrandExchangeClerk dialogue.
 * @author 'Vexia
 * @author Emperor
 * @version 1.0
 */
@InitializablePlugin
public final class GrandExchangeClerk extends DialoguePlugin {

	/**
	 * Constructs a new {@code GrandExchangeClerk} {@code Object}.
	 */
	public GrandExchangeClerk() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code GrandExchangeClerk} {@code Object}.
	 * @param player The player.
	 */
	public GrandExchangeClerk(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new GrandExchangeClerk(player);
	}

	@Override
	public boolean open(Object... args) {
		if (args.length > 0 && args[0] instanceof NPC) {
			npc = (NPC) args[0];
		}
		interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Hi there.");
		stage = 0;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		if (stage < 50 && stage > 5 && !GrandExchangeDatabase.hasInitialized()) {
			npc("The Grand Exchange is currently unavailable,", "it will be back soon!");
			stage = 51;
			return true;
		}
		switch (stage) {
		case 0:
			npc("Good day to you, sir, How can I help?");
			stage = 1;
			break;
		case 1:
			interpreter.sendOptions("Select an Option", "I want to access the Grand Exchange, please.", "I want to collect my items.", "Can I see a history of my offers?", "Can you help me with item sets?", "I'm fine, actually.");
			stage = 2;
			break;
		case 2:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "I want to access the Grand Exchange, please.");
				stage = 10;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "I want to collect my items.");
				stage = 20;
				break;
			case 3:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Can I see history of my offers?");
				stage = 30;
				break;
			case 4:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Can you help me with item sets?");
				stage = 40;
				break;
			case 5:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "I'm fine actually.");
				stage = 50;
				break;
			}
			break;
		case 10:
			npc("Only too happy to help you, sir.");
			stage = 11;
			break;
		case 11:
			end();
			player.getGrandExchange().open();
			break;
		case 20:
			npc("As you wish, sir.");
			stage = 21;
			break;
		case 21:
			end();
			player.getGrandExchange().openCollectionBox();
			break;
		case 30:
			npc("If that is your wish.");
			stage = 31;
			break;
		case 31:
			end();
			player.getGrandExchange().openHistoryLog(player);
			break;
		case 40:
			npc("It would be my pleasure, sir.");
			stage = 41;
			break;
		case 41:
			end();
			player.getGrandExchange().openItemSets();
			break;
		case 50:
			npc("If you say so, sir.");
			stage = 51;
			break;
		case 51:
			end();
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 6528, 6529, 6530, 6531 };
	}
}
