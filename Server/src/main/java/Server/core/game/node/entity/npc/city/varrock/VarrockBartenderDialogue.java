package core.game.node.entity.npc.city.varrock;

import core.game.content.dialogue.DialoguePlugin;
import core.game.content.dialogue.FacialExpression;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.plugin.Initializable;
import core.game.node.item.Item;

/**
 * The plugin used to handle the dialogue for the varrock bartender.
 * @author 'Vexia
 * @version 1.0
 */
@Initializable
public final class VarrockBartenderDialogue extends DialoguePlugin {

	/**
	 * Constructs a new {@code VarrockBartenderDialogue} {@code Object}.
	 */
	public VarrockBartenderDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code VarrockBartenderDialogue} {@code Object}.
	 * @param player the player.
	 */
	public VarrockBartenderDialogue(Player player) {
		super(player);
	}

	@Override
	public int[] getIds() {
		return new int[] { 732, 731 };
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Good day to you, brave adventurer. Can I get you a", "refreshing beer?");
			stage = 1;
			break;
		case 1:
			interpreter.sendOptions("Select an Option", "Yes please!", "No thanks.", "How much?");
			stage = 2;
			break;
		case 2:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Yes please!");
				stage = 10;
				break;

			case 2:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "No thanks.");
				stage = 20;
				break;

			case 3:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "How much?");
				stage = 30;
				break;
			}

			break;
		case 10:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Ok then, that's two gold coins please.");
			stage = 11;
			break;
		case 11:
			if (player.getInventory().contains(995, 2)) {
				player.getInventory().remove(new Item(995, 2));
				player.getInventory().add(new Item(1917, 1));
				interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Cheers!");
				stage = 12;
			} else {
				end();
				player.getPacketDispatch().sendMessage("You need two gold coins to buy a beer.");
			}
			break;
		case 12:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Cheers!");
			stage = 13;
			break;
		case 13:
			end();
			break;
		case 20:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Let me know if you change your mind.");
			stage = 21;
			break;
		case 21:
			end();
			break;
		case 30:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Two gold pieces a pint. So, what do you say?");
			stage = 31;
			break;
		case 31:
			interpreter.sendOptions("Select an Option", "Yes please!", "No thanks.");
			stage = 32;
			break;
		case 32:

			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Yes please!");
				stage = 10;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "No thanks.");
				stage = 20;
				break;
			}

			break;
		}

		return true;
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new VarrockBartenderDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Hello.");
		stage = 0;
		return true;
	}
}
