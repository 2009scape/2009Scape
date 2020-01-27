package plugin.dialogue;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.node.entity.player.Player;

/**
 * Represents the dialogue plugin used for the Gillie Groats npc.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class GillieGroatsDialogue extends DialoguePlugin {

	/**
	 * Constructs a new {@code GillieGroatsDialogue} {@code Object}.
	 */
	public GillieGroatsDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code GillieGroatsDialogue} {@code Object}.
	 * @param player the player.
	 */
	public GillieGroatsDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new GillieGroatsDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		boolean milk = false;
		if (args.length == 2)
			milk = true;
		if (milk) {
			interpreter.sendDialogues(3807, FacialExpression.LAUGH, "Tee hee! You've never milked a cow before, have you?");
			stage = 100;
			return true;
		}
		interpreter.sendDialogues(3807, FacialExpression.HAPPY, "Hello, Im Gillie the Milkmaid. What can I do for you?");
		stage = 0;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
			case 100:
				interpreter.sendDialogues(player, FacialExpression.ASKING, "Erm... No. How could you tell?");
				stage++;
				break;
			case 101:
				interpreter.sendDialogues(3807, FacialExpression.FRIENDLY, "Because you're spilling milk all over the floor. What a", "waste ! You need something to hold the milk.");
				stage++;
				break;
			case 102:
				interpreter.sendDialogues(player, FacialExpression.NEUTRAL, "Ah yes, I really should have guessed that one, shouldn't", "I?");
				stage++;
				break;
			case 103:
				interpreter.sendDialogues(3807, FacialExpression.FRIENDLY, "You're from the city, aren't you... Try it again with an", "empty bucket.");
				stage++;
				break;
			case 104:
				interpreter.sendDialogues(player, FacialExpression.NEUTRAL, "Right, I'll do that.");
				stage = 13;
				break;

			//Main Dialogue Options for Gillie
			case 0:
				interpreter.sendOptions("Select an Option", "Who are you?", "Can you tell me how to milk a cow?", "I'm fine, thanks.");
				stage = 1;
				break;
			case 1:
				switch (buttonId) {
					case 1:
						interpreter.sendDialogues(3807, FacialExpression.HAPPY, "My name's Gillie Groats. My father is a farmer and I", "milk the cows for him.");
						stage = 10;
						break;
					case 2:
						interpreter.sendDialogues(player, FacialExpression.ASKING, "So how do you get milk from a cow then?");
						stage = 20;
						break;
					case 3:
						interpreter.sendDialogues(player, FacialExpression.NEUTRAL, "I'm fine thanks.");
						stage = 13;
						break;
				}
				break;

			//Who are you?
			case 10:
				interpreter.sendDialogues(player, FacialExpression.ASKING, "Do you have any buckets of milk spare?");
				stage++;
				break;
			case 11:
				interpreter.sendDialogues(3807, FacialExpression.NEUTRAL, "I'm afraid not. We need all of our milk to sell to", "market, but you can milk the cow yourself if you need", "milk.");
				stage++;
				break;
			case 12:
				interpreter.sendDialogues(player, FacialExpression.HAPPY, "Thanks.");
				stage++;
				break;

			//I'm fine thanks and Endpoint for other conversations
			case 13:
				end();
				break;

			//How to Milk a Cow
			case 20:
				interpreter.sendDialogues(3807, FacialExpression.FRIENDLY, "It's very easy. First you need an empty bucket to hold", "the milk.");
				stage++;
				break;
			case 21:
				interpreter.sendDialogues(3807, FacialExpression.FRIENDLY, "Then find a dairy cow to milk - you can't milk just", "any cow.");
				stage++;
				break;
			case 22:
				interpreter.sendDialogues(player, FacialExpression.ASKING, "How do I find a dairy cow?");
				stage++;
				break;
			case 23:
				interpreter.sendDialogues(3807, FacialExpression.FRIENDLY, "They are easy to spot - they are dark brown and", "white, unlike beef cows, which are light brown and white.", "We also tether them to a post to stop them wandering", "around all over the place.");
				stage++;
				break;
			case 24:
				interpreter.sendDialogues(3807, FacialExpression.FRIENDLY, "There are a couple very near, in this field.");
				stage++;
				break;
			case 25:
				interpreter.sendDialogues(3807, FacialExpression.FRIENDLY, "Then just milk the cow and your bucket will fill with", "tasty, nutritious milk.");
				stage = 13;
				break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 3807 };
	}
}
