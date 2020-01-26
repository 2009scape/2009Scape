package plugin.dialogue;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.node.entity.player.Player;

/**
 * Represents the dialogue plugin used for the grillie groats npc.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class GrillieGroatsDialogue extends DialoguePlugin {

	/**
	 * Constructs a new {@code GrillieGroatsDialogue} {@code Object}.
	 */
	public GrillieGroatsDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code GrillieGroatsDialogue} {@code Object}.
	 * @param player the player.
	 */
	public GrillieGroatsDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new GrillieGroatsDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		boolean milk = false;
		if (args.length == 2)
			milk = true;
		if (milk) {
			interpreter.sendDialogues(3807, FacialExpression.NO_EXPRESSION, "Tee hee! You've never milked a cow before, have you?");
			stage = 900;
			return true;
		}
		interpreter.sendDialogues(3807, FacialExpression.NO_EXPRESSION, "Hello, Im Gillie the Milkmaid. What can I do for you?");
		stage = 0;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 900:
			interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Erm... No. How could you tell?");
			stage = 901;
			break;
		case 901:
			interpreter.sendDialogues(3807, FacialExpression.NO_EXPRESSION, "Because you're spilling milk all over the floor. What a", "waste ! You need something to hold the milk.");
			stage = 902;
			break;
		case 902:
			interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Ah yes, I really should have guessed that one, shouldn't", "I?");
			stage = 903;
			break;
		case 903:
			interpreter.sendDialogues(3807, FacialExpression.NO_EXPRESSION, "You're from the city, aren't you... Try it again with an", "empty bucket.");
			stage = 904;
			break;
		case 904:
			interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Right, I'll do that.");
			stage = 905;
			break;
		case 905:
			end();
			break;
		case 0:
			interpreter.sendOptions("Select an Option", "Who are you?", "Can you tell me how to milk a cow?", "I'm fine, thanks.");
			stage = 1;
			break;
		case 1:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(3807, FacialExpression.NO_EXPRESSION, "My name's Gillie Groats. My father is a farmer and I", "milk the cows for him.");
				stage = 10;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "So how do you get milk from a cow then?");
				stage = 20;
				break;
			case 3:
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "I'm fine thanks.");
				stage = 30;
				break;
			}
			break;
		case 30:
			end();
			break;
		case 10:
			interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Do you have any buckets of milk spare?");
			stage = 11;
			break;
		case 11:
			interpreter.sendDialogues(3807, FacialExpression.NO_EXPRESSION, "I'm afraid not. We need all of our milk to sell to", "market, but you can milk the cow yourself if you need", "milk.");
			stage = 12;
			break;
		case 12:
			interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Thanks.");
			stage = 13;
			break;
		case 13:
			end();
			break;
		case 20:
			interpreter.sendDialogues(3807, FacialExpression.NO_EXPRESSION, "It's very easy. First you need an empty bucket to hold", "the milk.");
			stage = 21;
			break;
		case 21:
			interpreter.sendDialogues(3807, FacialExpression.NO_EXPRESSION, "Then find a dairy cow to milk - you can't milk just", "any cow.");
			stage = 22;
			break;
		case 22:
			interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "How do I find a dairy cow?");
			stage = 23;
			break;
		case 23:
			interpreter.sendDialogues(3807, FacialExpression.NO_EXPRESSION, "They are easy to spot - they are dark brown and", "white, unlike beef cows, which are light brown and white.", "We also tether them to a post to stop them wandering", "around all over the place.");
			stage = 24;
			break;
		case 24:
			interpreter.sendDialogues(3807, FacialExpression.NO_EXPRESSION, "There are a couple very near, in this field.");
			stage = 25;
			break;
		case 25:
			interpreter.sendDialogues(3807, FacialExpression.NO_EXPRESSION, "Then just milk the cow and your bucket will fill with", "tasty, nutritious milk.");
			stage = 26;
			break;
		case 26:
			end();
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 3807 };
	}
}
