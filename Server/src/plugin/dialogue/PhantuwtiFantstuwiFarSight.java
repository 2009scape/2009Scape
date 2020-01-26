package plugin.dialogue;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.node.entity.player.Player;

/**
 * Handles the PhantuwtiFantstuwiFarSight dialogue.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class PhantuwtiFantstuwiFarSight extends DialoguePlugin {

	/**
	 * Constructs a new {@code PhantuwtiFantstuwiFarSight} {@code Object}.
	 */
	public PhantuwtiFantstuwiFarSight() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code PhantuwtiFantstuwiFarSight} {@code Object}.
	 * @param player the player.
	 */
	public PhantuwtiFantstuwiFarSight(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new PhantuwtiFantstuwiFarSight(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendOptions("Select an Option", "Hello, what is this place?", "What do you do here?", "Do you have any quests?", "Ok, thanks.");
		stage = 0;
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 1798 };
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Hello, what is this place?");
				stage = 10;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "What do you do here?");
				stage = 20;
				break;
			case 3:
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Do you have any quests?");
				stage = 30;
				break;
			case 4:
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Ok, thanks.");
				stage = 40;
				break;

			}
			break;
		case 10:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "This is Seers Village! We're an organisation of mystically", "gifted people with the power of foresight...we see things", "that have yet to come to pass.");
			stage = 11;
			break;
		case 11:
			interpreter.sendOptions("Select an Option", "Hello, what is this place?", "What do you do here?", "Do you have any quests?", "Ok, thanks.");
			stage = 0;
			break;
		case 20:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "A lot of our time is spent adressing everyday sorts of", "things, plus we meditate a lot and ehhance our", "powers of mystical foresight.");
			stage = 31;
			break;
		case 30:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Unfortunately no, sorry, but if adventure is what you", "seek, try checking through your quest list!");
			stage = 31;
			break;
		case 31:
			interpreter.sendOptions("Select an Option", "Hello, what is this place?", "What do you do here?", "Do you have any quests?", "Ok, thanks.");
			stage = 0;
			break;
		case 40:
			end();
			break;
		}
		return true;
	}

}
