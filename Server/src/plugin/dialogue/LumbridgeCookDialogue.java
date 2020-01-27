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
				interpreter.sendDialogues(npc, FacialExpression.HAPPY, "You've brought me everything I need! I am saved!", "Thank you!");
				stage = 956;
				return true;
			}
			interpreter.sendDialogues(npc, FacialExpression.SAD, "How are you getting on with finding the ingredients?");
			stage = 1000;
			return true;
		} if (player.getQuestRepository().getQuest("Cook's Assistant").getStage(player) == 0) {
			interpreter.sendDialogues(npc, FacialExpression.SAD, "What am I to do?");
			stage = 1;
			return true;
		} if (player.getQuestRepository().getQuest("Cook's Assistant").getStage(player) == 100) {
			interpreter.sendDialogues(npc, FacialExpression.HAPPY,"Hello friend, how is the adventuring going?");
			stage = 0;
		}
		stage = 0;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
			case 0: //Dialogue for after Cook's Assistant
				interpreter.sendOptions("Select an Option", "I am getting strong and mighty.", "I keep on dying.", "Can I use your range?");
				stage = 5000;
				break;
			case 1:
				if (player.getQuestRepository().getQuest("Cook's Assistant").getStage(player) == 0) {
					interpreter.sendOptions("Select an Option", "What's wrong?", "Can you make me a cake?", "You don't look very happy.", "Nice hat!");
					stage++;
					break;
				}
			case 2:
				switch (buttonId) {
					case 1:
						interpreter.sendDialogues(player, FacialExpression.NEUTRAL, "What's wrong?");
						stage = 10;
						break;
					case 2:
						interpreter.sendDialogues(player, FacialExpression.ASKING, "You're a cook, why don't you bake me a cake?");
						stage = 20;
						break;
					case 3:
						interpreter.sendDialogues(player, FacialExpression.NEUTRAL, "You don't look very happy.");
						stage = 30;
						break;
					case 4:
						interpreter.sendDialogues(player, FacialExpression.HAPPY, "Nice hat!");
						stage = 40;
						break;

				}
				break;

			//Dialogues for 'Can you bake me a cake?'
			case 20:
				interpreter.sendDialogues(npc, FacialExpression.SAD, "*sniff* Don't talk to me about cakes...");
				stage++;
				break;
			case 21:
				interpreter.sendDialogues(player, FacialExpression.NEUTRAL, "What's wrong?");
				stage = 10;
				break;

			//Dialogue for 'You don't look very happy'
			case 30:
				interpreter.sendDialogues(npc, FacialExpression.SAD, "No, I'm not. The world is caving in around me - I am", "overcome by dark feelings of impending doom.");
				stage = 21;
				break;


			//Dialogue for 'Nice Hat!'
			case 40:
				interpreter.sendDialogues(npc, FacialExpression.SAD, "Er, thank you. It's a pretty ordinary cook's hat, really.");
				stage++;
				break;
			case 41:
				interpreter.sendDialogues(player, FacialExpression.HAPPY, "Still, it suits you. The trousers are pretty special too.");
				stage++;
				break;
			case 42:
				interpreter.sendDialogues(npc, FacialExpression.SAD, "It's all standard-issue cook's uniform.");
				stage++;
				break;
			case 43:
				interpreter.sendDialogues(player, FacialExpression.HAPPY, "The whole hat, apron, stripy trousers ensemble. It", "works. It makes you looks like a real cook.");
				stage++;
				break;
			case 44:
				interpreter.sendDialogues(npc, FacialExpression.ANGRY, "I AM a real cook! I haven't got time to be chatting", "about culinary fashion. I'm in desperate need of help!");
				stage = 21;
				break;

			//Main Dialogue
			case 10:
				interpreter.sendDialogues(npc, FacialExpression.SCARED, "Oh dear, oh dear, oh dear, I'm in a terrible terrible", "mess! It's the Duke's birthday today, and I should be", "making him a lovely big birthday cake.");
				stage++;
				break;
			case 11:
				interpreter.sendDialogues(npc, FacialExpression.SAD, "I've forgotten to buy the ingredients. I'll never get", "them in time now. He'll sack me! What will I do? I have", "four children and a goat to look after. Would you help", "me? Please?");
				stage++;
				break;
			case 12:
				interpreter.sendOptions("Select an Option", "I'm always happy to help a cook in distress.", "I can't right now, Maybe later.");
				stage++;
				break;
			case 13:
				switch (buttonId) {
					case 1:
						interpreter.sendDialogues(player, FacialExpression.HAPPY, "Yes, I'll help you.");
						stage = 100;
						break;
					case 2:
						interpreter.sendDialogues(player, FacialExpression.NEUTRAL, "No, I don't feel like it. Maybe later.");
						stage = 14;
						break;

				}
				break;

			//If the Player does not want to help the Chef
			case 14:
				interpreter.sendDialogues(npc, FacialExpression.SAD, "Fine. I always knew you Adventurer types were callous", "beasts. Go on your merry way!");
				stage++;
				break;
			case 15:
				end();
				break;

			//If the Player does help the Chef
			case 100:
				player.getQuestRepository().getQuest("Cook's Assistant").start(player);
				interpreter.sendDialogues(npc, FacialExpression.HAPPY, "Oh thank you, thank you. I need milk, an egg and", "flour. I'd be very grateful if you can get them for me.");
				stage++;
				break;
			case 101:
				interpreter.sendDialogues(player, FacialExpression.NEUTRAL, "So where do I find these ingredients then?");
				stage++;
				break;

			//Where do I get the Ingredients?
			case 102:
				interpreter.sendOptions("Select an Option", "Where do I find some flour?", "How about milk?", "And eggs? Where are they found?", "Actually, I know where to find this stuff.");
				stage++;
				break;
			case 103:
				switch (buttonId) {
					case 1:
						interpreter.sendDialogues(npc, FacialExpression.HAPPY, "There is a Mill fairly close, Go North and then West.", "Mill Lane Mill is just off the road to Draynor. I", "usually get my flour from there.");
						stage = 110;
						break;
					case 2:
						interpreter.sendDialogues(npc, FacialExpression.HAPPY, "There is a cattle field on the other side of the river,", "just across the road from the Groats' Farm.");
						stage = 120;
						break;
					case 3:
						interpreter.sendDialogues(npc, FacialExpression.HAPPY, "I normally get my eggs from the Groats' farm, on the", "other side of the river.");
						stage = 130;
						break;
					case 4:
						interpreter.sendDialogues(player, FacialExpression.HAPPY, "Actually, I know where to find this stuff.");
						stage = 15;
						break;
				}
				break;

			case 110:
				interpreter.sendDialogues(npc, FacialExpression.HAPPY, "Talk to Millie, she'll help, she's a lovely girl and a fine", "Miller..");
				stage = 140;
				break;
			case 120:
				interpreter.sendDialogues(npc, FacialExpression.HAPPY, "Talk to Gillie Groats, she looks after the Dairy cows -", "she'll tell you everything you need to know about", "milking cows!");
				stage = 140;
				break;
			case 130:
				interpreter.sendDialogues(npc, FacialExpression.HAPPY, "But any chicken should lay eggs.");
				stage = 140;
				break;

			//There is alternative dialogue as the last option, this could be condensed, but this is the easiest solution right now.
			case 140:
				interpreter.sendOptions("Select an Option", "Where do I find some flour?", "How about milk?", "And eggs? Where are they found?", "I've got all the information I need. Thanks.");
				stage++;
				break;
			case 141:
				switch (buttonId) {
					case 1:
						interpreter.sendDialogues(npc, FacialExpression.HAPPY, "There is a Mill fairly close, Go North and then West.", "Mill Lane Mill is just off the road to Draynor. I", "usually get my flour from there.");
						stage = 110;
						break;
					case 2:
						interpreter.sendDialogues(npc, FacialExpression.HAPPY, "There is a cattle field on the other side of the river,", "just across the road from the Groats' Farm.");
						stage = 120;
						break;
					case 3:
						interpreter.sendDialogues(npc, FacialExpression.HAPPY, "I normally get my eggs from the Groats' farm, on the", "other side of the river.");
						stage = 130;
						break;
					case 4:
						interpreter.sendDialogues(player, FacialExpression.HAPPY, "I've got all the information I need. Thanks.");
						stage = 15;
						break;
				}
				break;

			//Code for giving the chef the items
			case 1000:
				boolean gave = false;
				if (player.getInventory().contains(1927, 1) && !player.getAttribute("gaveMilk", false)) {
					player.getInventory().remove(MILK);
					interpreter.sendDialogues(player, FacialExpression.HAPPY, "Here's a bucket of milk.");
					player.getSavedData().getQuestData().setCooksAssistant("milk", true);
					player.getSavedData().getQuestData().setCooksAssistant("gave", true);
					player.setAttribute("gaveMilk", true);
					gave = true;
					break;
				}
				if (player.getInventory().contains(1933, 1) && !player.getAttribute("gaveFlour", false)) {
					player.getInventory().remove(FLOUR);
					interpreter.sendDialogues(player, FacialExpression.HAPPY, "Here's a pot of flour.");
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
					interpreter.sendDialogues(player, FacialExpression.HAPPY, "Here's a fresh egg.");
					gave = true;
					break;
				}
				if (!gave) {
					if (!player.getSavedData().getQuestData().getCookAssist("gave")) {
						interpreter.sendDialogue("You still need to get:", "A bucket of milk. A pot of flour. An egg.");
						stage = 1100;
					} else {
						interpreter.sendDialogues(npc, FacialExpression.SAD, "Thanks for the ingredients you have got so far please get", "the rest quickly. I'm running out of time! The Duke", "will throw me into the streets!");
						stage = 1201;
					}
					break;
				}
				stage = 1200;
				break;

			//If the player has not found any ingredients or if only some of the ingredients have been given to the chef
			case 1100:
				interpreter.sendOptions("Select an Option", "I'll get right on it.", "Can you remind me how to find these things again?");
				stage++;
				break;
			case 1101:
				switch (buttonId) {
					case 1:
						interpreter.sendDialogues(player, FacialExpression.HAPPY, "I'll get right on it.");
						stage = 15;
						break;
					case 2:
						interpreter.sendDialogues(player, FacialExpression.NEUTRAL, "Can you remind me how to find these things again?.");
						stage = 140;
						break;
				}
				break;
			case 1200:
				interpreter.sendDialogues(npc, FacialExpression.SAD, "Thanks for the ingredients you have got so far please get", "the rest quickly. I'm running out of time! The Duke", "will throw me into the streets!");
				stage++;
				break;
			case 1201:
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
					stage = 1100;
				} else {
					interpreter.sendDialogues(npc, FacialExpression.HAPPY, "You've brought me everything I need! I am saved!", "Thank you!");
					stage = 956;
				}
				break;

			//Final Cooks Assistant Dialogue
			case 956:
				interpreter.sendDialogues(player, FacialExpression.HAPPY, "So do I get to go to the Duke's Party?");
				stage++;
				break;
			case 957:
				interpreter.sendDialogues(npc, FacialExpression.SAD, "I'm afraid not, only the big cheeses get to dine with the", "Duke.");
				stage++;
				break;
			case 958:
				interpreter.sendDialogues(player, FacialExpression.NEUTRAL, "Well, maybe one day I'll be important enough to sit on", "the Duke's table.");
				stage++;
				break;
			case 959:
				interpreter.sendDialogues(npc, FacialExpression.NEUTRAL, "Maybe, but I won't be holding my breath.");
				stage++;
				break;

			//End Quest 'Cook's Assistant'
			case 960:
				end();
				player.getQuestRepository().getQuest("Cook's Assistant").finish(player);
				break;

			//Dialogue options after Cook's Assistant
			case 5000:
				switch (buttonId) {
					case 1:
						interpreter.sendDialogues(player, FacialExpression.HAPPY, "I am getting strong and mighty. Grr.");
						stage = 5100;
						break;
					case 2:
						interpreter.sendDialogues(player, FacialExpression.SAD, "I keep on dying.");
						stage = 5200;
						break;
					case 3:
						interpreter.sendDialogues(player, FacialExpression.ASKING, "Can I use your range?");
						stage = 5300;
						break;
				}
				break;

			case 5100:
				interpreter.sendDialogues(npc, FacialExpression.HAPPY, "Glad to hear it!");
				stage = 15;
				break;
			case 5200:
				interpreter.sendDialogues(npc, FacialExpression.HAPPY, "Ah, well, at least you keep coming back to life too!");
				stage = 15;
				break;
			case 5300:
				interpreter.sendDialogues(npc, FacialExpression.HAPPY, "Go ahead! It's a very good range; it's better than most" ,"other ranges.");
				stage++;
				break;
			case 5301:
				interpreter.sendDialogues(npc, FacialExpression.HAPPY, "It's called the Cook-o-Matic 100 and it uses a combination","of state-of-the-art temperature regulation and magic.");
				stage++;
				break;
			case 5302:
				interpreter.sendDialogues(player, FacialExpression.ASKING, "Will it mean my food will burn less often?");
				stage++;
				break;
			case 5303:
				interpreter.sendDialogues(npc, FacialExpression.HAPPY, "As long as the food is fairly easy to cook in the first place!");
				stage = 15;
				break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 278 };
	}
}
