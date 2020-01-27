package plugin.activity.stronghold;

import org.crandor.game.container.Container;
import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.entity.player.link.emote.Emotes;
import org.crandor.game.node.item.Item;

/**
 * Represents the dialogue plugin used for the cradle of life.
 * @author 'Vexia
 * @version 1.0
 */
public final class CradleOfLifeDialogue extends DialoguePlugin {

	/**
	 * Represents the items related to the cradle of life.
	 */
	private static final Item[] ITEMS = new Item[] { new Item(9005, 1), new Item(9006, 1) };

	/**
	 * Constructs a new {@code CradleOfLife} {@code Object}.
	 */
	public CradleOfLifeDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code CradleOfLife} {@code Object}.
	 * @param player the player.
	 */
	public CradleOfLifeDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new CradleOfLifeDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		if (player.getSavedData().getGlobalData().getStrongHoldRewards()[3]) {
			player.getEmoteManager().unlock(Emotes.STOMP);
		}
		if (player.getInventory().freeSlots() == 0) {
			player.getPacketDispatch().sendMessage("You don't have enough inventory space.");
			end();
			return true;
		}
		if (player.getSavedData().getGlobalData().getStrongHoldRewards()[3]) {
			interpreter.sendDialogue("As your hand touches the cradle, you hear a voice in your head of a", "million dead adventurers....");
			stage = 100;
			return true;
		}
		interpreter.sendDialogue("As your hand touches the cradle, you hear a voice in your head of a", "million dead adventurers....");
		stage = 0;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			interpreter.sendDialogue("....welcome adventurer... you have a choice....");
			stage = 1;
			break;
		case 1:
			interpreter.sendDoubleItemMessage(9005, 9006, "You can choose between these two pair of boots.");
			stage = 2;
			break;
		case 2:
			interpreter.sendDialogue("They will both protect your feet exactly the same, however they look", "very different. You can always come back and get another pair if", "you lose them, or even swap them for the other style!");
			stage = 4;
			break;
		case 4:
			interpreter.sendOptions("Choose your style of boots", "I'll take the colourful ones!", "I'll take the fighting ones!");
			stage = 5;
			break;
		case 5:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "I'll take the colourful ones!");
				player.getInventory().add(ITEMS[0]);
				player.getEmoteManager().unlock(Emotes.STOMP);
				stage = 6;
				player.getSavedData().getGlobalData().getStrongHoldRewards()[3] = true;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "I'll take the fighting ones!");
				player.getInventory().add(ITEMS[1]);
				player.getEmoteManager().unlock(Emotes.STOMP);
				player.getSavedData().getGlobalData().getStrongHoldRewards()[3] = true;
				stage = 6;
				break;
			}
			break;
		case 6:
			interpreter.sendDialogue("Congratulations! You have succesfully navigated the Stronghold of", "Security and learned to secure your account. You have unlocked", "the 'Stamp Foot' emote. Remember to keep your account secure in", "the future!");
			stage = 7;
			break;
		case 7:
			end();
			break;

		case 100:
			if (!player.getInventory().contains(9005, 1) && !player.getBank().contains(900, 1) && !player.getEquipment().contains(9005, 1) && !player.getInventory().contains(9006, 1) && !player.getBank().contains(9006, 1) && !player.getEquipment().contains(9006, 1)) {
				interpreter.sendDialogue("You appear to have lost your boots!");
				stage = 101;
			} else {
				interpreter.sendOptions("Select an Option", "Yes, I'd like the other pair instead please!", "No thanks, I'll keep these!");
				stage = 200;
			}
			break;
		case 200:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Yes, I'd like the other pair instead please!");
				stage = 800;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "No thanks, I'll keep these!");
				stage = 900;
				break;
			}
			break;
		case 800:
			if (!swap(player.getInventory())) {
				if (!swap(player.getEquipment())) {
					swap(player.getBank());
				}
			}
			end();
			break;
		case 900:
			end();
			break;
		case 101:
			interpreter.sendDialogue("....welcome adventurer... you have a choice....");
			stage = 1;
			break;
		}
		return true;
	}

	/**
	 * Method used to swap a pair of boots.
	 * @param container the container.
	 * @return {@code True} if swapped.
	 */
	public boolean swap(Container container) {
		if (container.contains(9005, 1)) {
			container.replace(new Item(9006), container.getSlot(ITEMS[0]));
			return true;
		}
		if (container.contains(9006, 1)) {
			container.replace(ITEMS[0], container.getSlot(ITEMS[1]));
			return true;
		}
		return false;
	}

	@Override
	public int[] getIds() {
		return new int[] { 96873 };
	}
}