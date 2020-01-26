package plugin.dialogue;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.game.node.entity.player.Player;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.node.item.Item;

/**
 * Represents the dialogue plugin used for the lumbridge cook.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class LumbridgeCookDialogue extends DialoguePlugin {

	/**
	 * Represents the milk item.
	 */
	private static final Item MILK = new Item(1927, 1);

	/**
	 * Represents the flour item.
	 */
	private static final Item FLOUR = new Item(1933, 1);

	/**
	 * Represents the eggs item.
	 */
	private static final Item EGG = new Item(1944, 1);

	/**
	 * Constructs a new {@code LumbridgeCookDialogue} {@code Object}.
	 */
	public LumbridgeCookDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code LumbridgeCookDialogue} {@code Object}.
	 * @param player the player.
	 */
	public LumbridgeCookDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new LumbridgeCookDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		if (player.getQuestRepository().getQuest("Cook's Assistant").getStage(player) == 10) {
			if (player.getSavedData().getQuestData().getCookAssist("milk") && player.getSavedData().getQuestData().getCookAssist("flour") && player.getSavedData().getQuestData().getCookAssist("egg")) {
				interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "You've brought me everything I need! I am saved!", "Thank you!");
				stage = 956;
				return true;
			}
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "How are you getting on with finding the ingredients?");
			stage = 1000;
			return true;
		}
		if (player.getQuestRepository().getQuest("Cook's Assistant").getStage(player) == 100) {
			npc("Hello friend, how is the adventuring going?");
			stage = 0;
		} else {
			interpreter.sendOptions("Select an Option", "Can you make me a cake?", "You don't look very happy.", "Nice hat!");
			stage = 9563;
			return true;
		}
		stage = 0;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			if (player.getQuestRepository().getQuest("Cook's Assistant").getStage(player) == 0) {
				interpreter.sendOptions("Select an Option", "What's wrong?", "Can you make me a cake?", "You don't look very happy.", "Nice hat!");
				stage = 1;
			} else {
				player("I am getting strong and mighty. Grrr");
				stage = 93;
			}
			break;
		case 9563:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "You're a cook, why don't you bake me a cake?");
				stage = 20;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "You don't look very happy.");
				stage = 30;
				break;
			case 3:
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Nice hat!");
				stage = 40;
				break;
			}
			break;
		case 1:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "What's wrong?");
				stage = 10;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "You're a cook, why don't you bake me a cake?");
				stage = 20;
				break;
			case 3:
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "You don't look very happy.");
				stage = 30;
				break;
			case 4:
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Nice hat!");
				stage = 40;
				break;

			}
			break;
		case 93:
			npc("Glad to hear it.");
			stage = 94;
			break;
		case 94:
			end();
			break;
		case 40:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Err thank you. It's a pretty ordinary cooks hat really.");
			stage = 41;
			break;
		case 41:
			interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Still, suits you. The trousers are pretty special too.");
			stage = 42;
			break;
		case 42:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "It's all standard cook's issue uniform...");
			stage = 43;
			break;
		case 43:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "The whole hat, apron, stripey trousers ensemble - it", "works. It make you looks like a real cook.");
			stage = 44;
			break;
		case 44:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "I am a real cook! I haven't got time to be chatting", "about Culinary Fashion. I am in desperate need of help!");
			stage = 45;
			break;
		case 45:
			interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "What's wrong?");
			stage = 10;
			break;
		case 30:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "No, I'm not. The world is caving in around me - I am", "overcome by dark feelings of impending doom.");
			stage = 31;
			break;
		case 31:
			interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "What's wrong?");
			stage = 10;
			break;
		case 20:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "*sniff* Don't talk to me about cakes...");
			stage = 21;
			break;
		case 21:
			interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "What's wrong?");
			stage = 10;
			break;
		case 10:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Oh dear, oh dear, oh dear, I'm in a terrible", "mess! It's the Duke's birthday today, and I should be", "making him a lovely big birthday cake.");
			stage = 11;
			break;
		case 11:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "I've forgotten to buy the ingredients. I'll never get", "them in time now. He'll sack me! What will I do? I have", "four children and a goat to look after. Would you help", "me? Please?");
			stage = 12;
			break;
		case 12:
			interpreter.sendOptions("Select an Option", "I'm always happy to help a cook in distress.", "I can't right now, Maybe later.");
			stage = 13;
			break;
		case 13:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Yes, I'll help you.");
				stage = 678;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "No, I don't feel like it. Maybe later.");
				stage = 14;
				break;

			}
			break;
		case 14:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Fine. I always knew you Adventurer types were callous", "beasts. Go on your merry way!");
			stage = 15;
			break;
		case 15:
			end();
			break;
		case 678:
			player.getQuestRepository().getQuest("Cook's Assistant").start(player);
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Oh thank you, thank you. I need milk, an egg and", "flour. I'd be very grateful if you can get them for me.");
			stage = 679;
			break;
		case 679:
			interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Okay, I'm on it.");
			stage = 680;
			break;
		case 680:
			interpreter.sendOptions("Select an Option", "Where do I find some flour?", "How about milk?", "And eggs? Where are they found?", "Actually, I know where to find this stuff.");
			stage = 681;
			break;
		case 681:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "There is a Mill fairly close, Go North and then West.", "Mill Lane Mill is just off the road to Draynor. I", "usually get my flour from there.");
				stage = 6000;
				break;
			case 2:
				interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "There is a cattle field on the other side of the river,", "just across the road from the Groats' Farm.");
				stage = 7000;
				break;
			case 3:
				interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "I normally get my eggs from the Groats' farm, on the", "other side of the river.");
				stage = 8000;
				break;
			case 4:
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Actually, I know where to find this stuff.");
				stage = 9000;
				break;
			}
			break;
		case 9000:
			end();
			break;
		case 8000:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "But any chicken should lay eggs.");
			stage = 8001;
			break;
		case 8001:
			interpreter.sendOptions("Select an Option", "Where do I find some flour?", "How about milk?", "And eggs? Where are they found?", "Actually, I know where to find this stuff.");
			stage = 681;
			break;
		case 7000:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Talk to Gillie Groats, she looks after the Dairy cows '", "she'll tell you everything you need to know about", "milking cows!");
			stage = 70001;
			break;
		case 70001:
			interpreter.sendOptions("Select an Option", "Where do I find some flour?", "How about milk?", "And eggs? Where are they found?", "Actually, I know where to find this stuff.");
			stage = 681;
			break;
		case 6000:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Talk to Millie, she'll help, she's a lovely girl and a fine", "Miller..");
			stage = 6001;
			break;
		case 6001:
			interpreter.sendOptions("Select an Option", "Where do I find some flour?", "How about milk?", "And eggs? Where are they found?", "Actually, I know where to find this stuff.");
			stage = 681;
			break;
		case 1000:
			boolean gave = false;
			if (player.getInventory().contains(1927, 1) && !player.getAttribute("gaveMilk", false)) {
				player.getInventory().remove(MILK);
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Here's a bucket of milk.");
				player.getSavedData().getQuestData().setCooksAssistant("milk", true);
				player.getSavedData().getQuestData().setCooksAssistant("gave", true);
				player.setAttribute("gaveMilk", true);
				gave = true;
				break;
			}
			if (player.getInventory().contains(1933, 1) && !player.getAttribute("gaveFlour", false)) {
				player.getInventory().remove(FLOUR);
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Here's a pot of flour.");
				player.getSavedData().getQuestData().setCooksAssistant("flour", true);
				player.getSavedData().getQuestData().setCooksAssistant("gave", true);
				player.setAttribute("gaveFlour", true);
				gave = true;
				break;
			}
			if (player.getInventory().contains(1944, 1) && !player.getAttribute("gaveEgg", false)) {
				player.getInventory().remove(EGG);
				player.getSavedData().getQuestData().setCooksAssistant("egg", true);
				player.getSavedData().getQuestData().setCooksAssistant("gave", true);
				player.setAttribute("gaveEgg", true);
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Here's a fresh egg.");
				gave = true;
				break;
			}
			if (!gave) {
				if (!player.getSavedData().getQuestData().getCookAssist("gave")) {
					interpreter.sendDialogue("You still need to get:", "A bucket of milk. A pot of flour. An egg.");
					stage = 1110;
				} else {
					interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Thanks for the ingredients you have got so far please get", "the rest quickly. I'm running out of time! The Duke", "will throw me into the streets!");
					stage = 10002;
				}
				break;
			}
			stage = 10001;
			break;
		case 1110:
			interpreter.sendOptions("Select an Option", "I'll get right on it.", "Can you remind me how to find these things again?");
			stage = 1111;
			break;
		case 1111:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "I'll get right on it.");
				stage = 15;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "So where do I find these ingredients then?");
				stage = 490;
				break;
			}
			break;
		case 490:
			interpreter.sendOptions("Select an Option", "Where do I find some flour?", "How about milk?", "And eggs? Where are they found?", "Actually, I know where to find this stuff.");
			stage = 681;
			break;
		case 10001:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Thanks for the ingredients you have got so far please get", "the rest quickly. I'm running out of time! The Duke", "will throw me into the streets!");
			stage = 10002;
			break;
		case 10002:
			String[] messages = new String[4];
			messages[0] = "You still need to get:";
			if (!player.getSavedData().getQuestData().getCookAssist("milk")) {
				messages[1] = "A bucket of milk. ";
			}
			if (!player.getSavedData().getQuestData().getCookAssist("flour")) {
				messages[2] = "A pot of flour. ";
			}
			if (!player.getSavedData().getQuestData().getCookAssist("egg")) {
				messages[3] = "An egg. ";
			}
			StringBuilder builder = new StringBuilder();
			if (messages[1] != null) {
				builder.append(messages[1]);
			}
			if (messages[2] != null) {
				builder.append(messages[2]);
			}
			if (messages[3] != null) {
				builder.append(messages[3]);
			}
			if (builder.length() != 0) {
				interpreter.sendDialogue(messages[0], builder.toString());
				stage = 1110;
			} else {
				interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "You've brought me everything I need! I am saved!", "Thank you!");
				stage = 956;
			}
			break;
		case 956:
			interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "So do I get to go to the Duke's Party?");
			stage = 957;
			break;
		case 957:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "I'm afraid not, only the big cheeses get to dine with the", "Duke.");
			stage = 958;
			break;
		case 958:
			interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Well, maybe one day I'll be important enough to sit on", "the Duke's table.");
			stage = 959;
			break;
		case 959:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Maybe, but I won't be holding my breath.");
			stage = 960;
			break;
		case 960:
			end();
			player.getQuestRepository().getQuest("Cook's Assistant").finish(player);
			break;
		case 768:

			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 278 };
	}
}
