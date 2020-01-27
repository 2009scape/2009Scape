package plugin.dialogue;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.node.entity.player.Player;

/**
 * Represents the miss schism dialogue plugin.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class MissSchismDialogue extends DialoguePlugin {

	/**
	 * Constructs a new {@code MissSchismDialogue} {@code Object}.
	 */
	public MissSchismDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code MissSchismDialogue} {@code Object}.
	 * @param player the player.
	 */
	public MissSchismDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new MissSchismDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Oooh, my dear, have you heard the news?");
		stage = 0;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			interpreter.sendOptions("What would you like to say?", "Ok, tell me about the news.", "Who are you?", "I'm not talking to you, you horrible woman.");
			stage = 1;
			break;
		case 1:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Ok, tell me about the news.");
				stage = 10;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Who are you?");
				stage = 20;
				break;
			case 3:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "I'm not talking to you, you horrible woman.");
				stage = 30;
				break;
			}

			break;
		case 10:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Well, there's just to much to tell at once! What would", "you like to hear first: the vampire or the bank?");
			stage = 11;
			break;
		case 11:
			interpreter.sendOptions("Select an Option", "Tell me about the vampire.", "Tell me about the bank.");
			stage = 12;
			break;
		case 12:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Tell me about the vampire.");
				stage = 110;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "What about the bank?");
				stage = 210;
				break;
			}

			break;
		case 110:
			if (player.getQuestRepository().isComplete("Vampire Slayer")) {
				interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Well, there's nothing to tell NOW. You killed it.");
				stage = 111;
			} else {
				npc("There is an evil Vampire terrorizing the city!");
				stage = 119;
			}
			break;
		case 111:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "You could sound a little grateful.");
			stage = 112;
			break;
		case 112:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "I'm sure I could, but I don't see why. The vampie wasn't", "bothering me.");
			stage = 113;
			break;
		case 113:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "...");
			stage = 114;
			break;
		case 114:
			end();
			break;
		case 119:
			player("Oh, that's not good.");
			stage = 120;
			break;
		case 120:
			end();
			break;
		case 210:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "It's terrible, absolutely terrible! Those poor people!");
			stage = 211;
			break;
		case 211:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Ok, yeah.");
			stage = 212;
			break;
		case 212:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "And who'd have ever thought such a sweet old genleman", "would do such a thing?");
			stage = 213;
			break;
		case 213:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Are we talking about the bank robbery?");
			stage = 214;
			break;
		case 214:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Oh yes, my dear. It was terrible! TERRIBLE!");
			stage = 215;
			break;
		case 215:
			end();
			break;
		case 20:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "I, my dear, am a concerned citizen of Draynor Village.", "Ever since the Council allowed those farmers to set up", "their stalls here, we've had a constant flow of thieves and", "murderers through our fair village, and I decided that");
			stage = 21;
			break;
		case 21:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "someone HAD to stand up and", "keep an eye on the situation.");
			stage = 22;
			break;
		case 22:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "I also do voluntary work for the Draynor Manor", "Restoration Fund. We're campagning to have", "Draynor manor turned into a museum before the wet-rot", "destroys it completely.");
			stage = 23;
			break;
		case 23:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Well, now that I've cleared the vampire out of the manor,", "I guess you won't have to mutch trouble turning it into a", "museum.");
			stage = 24;
			break;
		case 24:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "That's all very well dear, but no vampire was ever going to", "stop me making it a museum.");
			stage = 25;
			break;
		case 25:
			end();
			break;
		case 30:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Oooh.");
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
		return new int[] { 2634 };
	}
}
