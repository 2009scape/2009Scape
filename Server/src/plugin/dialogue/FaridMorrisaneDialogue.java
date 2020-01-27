package plugin.dialogue;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.content.eco.ge.GEGuidePrice;
import org.crandor.game.content.eco.ge.GEGuidePrice.GuideType;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.node.entity.player.Player;

/**
 * Represents the farid morrisane dialogue plugin.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class FaridMorrisaneDialogue extends DialoguePlugin {

	/**
	 * Constructs a new {@code FaridMorrisaneDialogue} {@code Object}.
	 */
	public FaridMorrisaneDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code FaridMorrisaneDialogue} {@code Object}.
	 * @param player the player.
	 */
	public FaridMorrisaneDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new FaridMorrisaneDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Hello, little boy.");
		stage = 0;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "I would prefer it if you didn't speak to me in such a", "manner. I'll have you know I'm an accomplished", "merchant.");
			stage = 1;
			break;
		case 1:
			interpreter.sendOptions("Select an Option", "Calm down, junior.", "Can you help me out with the prices for ores?", "I best go and speak with someone my height.");
			stage = 2;
			break;
		case 2:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Calm down, junior.");
				stage = 10;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Can you help me out with the prices for ores?");
				stage = 20;
				break;
			case 3:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "I best go and speak with someone more my height.");
				stage = 30;
				break;
			}
			break;
		case 10:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Don't tell me to calm down! And don't call me 'junior'.");
			stage = 11;
			break;
		case 11:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "I'll have you know I am Farid Morrisane, son of Ali", "Morrisane, the worlds greatest merchant!");
			stage = 12;
			break;
		case 12:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Then why are you here and not him?");
			stage = 13;
			break;
		case 13:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "My dad has given me the responsibility of", "expanding our business here.");
			stage = 14;
			break;
		case 14:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "And you're up to the task?");
			stage = 15;
			break;
		case 15:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "What a grown up boy you are! Mummy and", "daddy must be very pleased!");
			stage = 16;
			break;
		case 16:
			end();
			break;
		case 20:
			end();
			GEGuidePrice.open(player, GuideType.ORES);
			break;
		case 30:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Then I shall not stop you, mister. I've too", "much work to do.");
			stage = 31;
			break;
		case 31:
			end();
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 6523 };
	}
}
