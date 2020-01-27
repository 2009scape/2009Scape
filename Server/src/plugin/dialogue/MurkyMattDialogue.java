package plugin.dialogue;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.content.eco.ge.GEGuidePrice;
import org.crandor.game.content.eco.ge.GEGuidePrice.GuideType;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.node.entity.player.Player;

/**
 * Represents the murly matt dialogue plugin.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class MurkyMattDialogue extends DialoguePlugin {

	/**
	 * Constructs a new {@code MurkyMattDialogue} {@code Object}.
	 */
	public MurkyMattDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code MurkyMattDialogue} {@code Object}.
	 * @param player the player.
	 */
	public MurkyMattDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new MurkyMattDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "A pirate!");
		stage = 0;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Arrrr, How'd ye be guessing that, me-lad?");
			stage = 1;
			break;
		case 1:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "You're kidding, right?");
			stage = 2;
			break;
		case 2:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Nay! Now, what is it that ye be wantin?", "I can tell ye all about the prices of runes, I can.");
			stage = 3;
			break;
		case 3:
			interpreter.sendOptions("Select an Option", "What's a pirate doing here?", "Tell me about the prices of runes.", "I got to go, erm, swab some decks! Yar!");
			stage = 4;
			break;
		case 4:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "What's a pirate doing here?");
				stage = 10;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Tell me about the prices of runes.");
				stage = 20;
				break;
			case 3:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "I got to go, erm, swab some decks! Yarr!");
				stage = 30;
				break;
			}
			break;
		case 10:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "By my sea-blistered skin, I could ask the same of you!");
			stage = 11;
			break;
		case 11:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "But... I'm not a pirate?");
			stage = 12;
			break;
		case 12:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "No? Then what's that smell? The smell o'", "someone spent too long at sea without a bath!");
			stage = 13;
			break;
		case 13:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "I think that's probably you.");
			stage = 14;
			break;
		case 14:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Har har har!");
			stage = 15;
			break;
		case 15:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "We've got a stern landlubber 'ere'! Well, let", "me tell ye, I'm here for the Grand Exchange!");
			stage = 16;
			break;
		case 16:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Don't you just want to sell it in a shop or trade", "it to someone specific?");
			stage = 17;
			break;
		case 17:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "By my wave-battered bones! Not when I can sell to", "the whole world from this very spot!");
			stage = 18;
			break;
		case 18:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "You pirates are nothing but trouble!");
			stage = 19;
			break;
		case 19:
			end();
			break;
		case 20:
			end();
			GEGuidePrice.open(player, GuideType.RUNES);
			break;
		case 30:
			end();
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 6525 };
	}
}
