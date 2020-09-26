package plugin.dialogue;

import core.game.content.global.Skillcape;
import plugin.skill.Skills;
import core.game.node.entity.npc.NPC;
import core.plugin.InitializablePlugin;
import core.game.node.entity.player.Player;

/**
 * Represents the kaqemeex dialogue plugin.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class KaqemeexDialogue extends DialoguePlugin {

	/**
	 * Constructs a new {@code KaqemeexDialogue} {@code Object}.
	 */
	public KaqemeexDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code KaqemeexDialogue} {@code Object}.
	 * @param player the player.
	 */
	public KaqemeexDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new KaqemeexDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		if (args.length >= 2) {
			interpreter.sendDialogues(npc, null, "I will now explain the fundamentals of Herblore:");
			stage = 1000;
			return true;
		}
		interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Hello there.");
		stage = 0;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			if (player.getQuestRepository().isComplete("Drudic Ritual")) {
				interpreter.sendDialogues(npc, null, "Hello again. How is the Herblore going?");
				stage = 600;
				break;
			}
			if (player.getQuestRepository().getQuest("Drudic Ritual").getStage(player) == 10) {
				interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Hello again.");
				stage = 40;
				break;
			}
			if (player.getQuestRepository().getQuest("Drudic Ritual").getStage(player) == 99) {
				interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "I have word from Sanfew that you have been very", "helpful in assisting him with his preparations for the", "purification ritual. As promised I will now teach you the", "ancient arts of Herblore.");
				stage = 200;
				break;
			}
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "What brings you to our holy monument?");
			stage = 1;
			break;
		case 1:
			if (player.getQuestRepository().getQuest("Drudic Ritual").isStarted(player)) {
				if (Skillcape.isMaster(player, Skills.HERBLORE)) {
					interpreter.sendOptions("Select an Option", "Can I buy a Skillcape of Herblore?", "Who are you?", "Did you build this?");
					stage = 800;
				} else {
					interpreter.sendOptions("Select an Option", "Who are you?", "Did you build this?");
					stage = 500;
				}
				break;
			} else {
				interpreter.sendOptions("Select an Option", "Who are you?", "I'm in search of a quest.", "Did you build this?");
				stage = 2;
			}
			break;
		case 2:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Who are you?");
				stage = 10;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "I'm in search of a quest.");
				stage = 20;
				break;
			case 3:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Did you build this?");
				stage = 30;
				break;
			}
			break;
		case 10:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "We are the druids of Guthix. We worship our god at", "our famous stone circles. You will find them located", "throghout these lands.");
			stage = 11;
			break;
		case 11:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Well, I'll be on my way now.");
			stage = 12;
			break;
		case 12:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Goodbye adventurer. I feel we shall meet again.");
			stage = 13;
			break;
		case 13:
			end();
			break;
		case 30:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "What, personally? No, ofcoure I didn't. However, our", "four fathers did. The first Druids of Guthix built many", "stone circles across these lands over eight hundred", "years ago.");
			stage = 31;
			break;
		case 31:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Unfortunately we only know of two remaining and of", "those only one is usuable by us anymore.");
			stage = 32;
			break;
		case 32:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Well, I'll be on my way now.");
			stage = 12;
			break;
		case 20:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Hmm. I think I may have a worthwhile quest for you", "actually. I don't know if you are familiar with the stone", "circle south of Varrock or not, but...");
			stage = 21;
			break;
		case 21:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "That used to be our stone circle. Unfortunately,", "many years ago, dark wizards cast a wicked spell", "upon it so that they could corrupt its power for their", "own evil ends.");
			stage = 22;
			break;
		case 22:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "When they cursed the rocks for their rituals they made", "them useless to us and our magics. We require a brave", "adevnturer to go on a quest for us to help purify the", "circle of Varrock.");
			stage = 23;
			break;
		case 23:
			interpreter.sendOptions("Select an Option", "Ok, I will try and help.", "No, that doesn't sound very interesting.");
			stage = 24;
			break;
		case 24:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Ok, I will try and help.");
				stage = 26;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "No, that doesn't sound very interesting.");
				stage = 25;
				break;
			}
			break;
		case 25:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "I will not try and change your mind adventurer. Some", "day when you have matured you may reconsider your", "position. We will wait until then.");
			stage = 13;
			break;
		case 26:
			player.getQuestRepository().getQuest("Drudic Ritual").start(player);
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Excellent. Go to the village south of this place and speak", "to my fellow Sanfew who is working on the purification", "ritual. He knows better than I what is required to", "complete it.");
			stage = 27;
			break;
		case 27:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Will do.");
			stage = 28;
			break;
		case 28:
			end();
			break;
		case 40:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "You will need to speak to my fellow druid Sanfew in", "the village south of here to continue in your quest", "adventurer.");
			stage = 41;
			break;
		case 41:
			end();
			break;
		case 200:
			end();
			player.getQuestRepository().getQuest("Drudic Ritual").finish(player);
			break;
		case 500:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Who are you?");
				stage = 10;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Did you build this?");
				stage = 30;
				break;
			}
			break;
		case 501:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Can I buy a Skillcape of Herblore?");
				stage = 800;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Who are you?");
				stage = 10;
				break;
			case 3:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Did you build this?");
				stage = 30;
				break;
			}
			break;
		case 600:
			interpreter.sendDialogues(player, null, "Good good!");
			stage = 601;
			break;
		case 601:
			if (Skillcape.isMaster(player, Skills.HERBLORE)) {
				interpreter.sendOptions("Select an Option", "Can I buy a Skillcape of Herblore?", "Who are you?", "Did you build this?");
				stage = 501;
			} else {
				interpreter.sendOptions("Select an Option", "Who are you?", "Did you build this?");
				stage = 500;
			}
			break;
		case 1000:
			interpreter.sendDialogues(npc, null, "Herblore is the skill of working with herbs and other", "ingredients, to make useful potions and poison.");
			stage = 1001;
			break;
		case 1001:
			interpreter.sendDialogues(npc, null, "First you will need a vial, which can be found or made", "with the crafting skill.");
			stage = 1002;
			break;
		case 1002:
			interpreter.sendDialogues(npc, null, "Then you must gather the herbs needed to make the", "potion you want.");
			stage = 1003;
			break;
		case 1003:
			interpreter.sendDialogues(npc, null, "Refer to the Council's instructions in the Skills section", "of the website for the items needed to make a particular", "kind of potion.");
			stage = 1004;
			break;
		case 1004:
			interpreter.sendDialogues(npc, null, "You must fill your vial with water and add the", "ingredients you need. There are normally 2 ingredients", "to each type of potion.");
			stage = 1005;
			break;
		case 1005:
			interpreter.sendDialogues(npc, null, "Bear in mind you must first identify each herb, to see", "what it is.");
			stage = 1006;
			break;
		case 1006:
			interpreter.sendDialogues(npc, null, "You may also have to grind some herbs before you can", "use them. You will need a pestle and mortar in order", "to do this.");
			stage = 1007;
			break;
		case 1007:
			interpreter.sendDialogues(npc, null, "Herbs can be found on the ground, and are also", "dropped by some monsters when you kill them.");
			stage = 1008;
			break;
		case 1008:
			interpreter.sendDialogues(npc, null, "Mix these in your water-filled vial, and you will produce", "an Attack potion.");
			stage = 1009;
			break;
		case 1009:
			interpreter.sendDialogues(npc, null, "Drink this poition to increase your Attack level.");
			stage = 1010;
			break;
		case 1010:
			interpreter.sendDialogues(npc, null, "Different potions also require different Herblore levels", "before you can make them.");
			stage = 1011;
			break;
		case 1011:
			interpreter.sendDialogues(npc, null, "Once again, check the instructions found on the", "Council's website for the levels needed to make a", "particulur potion.");
			stage = 1012;
			break;
		case 1012:
			interpreter.sendDialogues(npc, null, "Good luck with your Herblore practices, Good day", "Adventurer.");
			stage = 1013;
			break;
		case 1013:
			interpreter.sendDialogues(player, null, "Thanks for your help.");
			stage = 1014;
			break;
		case 1014:
			end();
			break;
		case 800:
			npc("Certainly! Right when you give me 99000 coins.");
			stage = 801;
			break;
		case 801:
			options("Yes, here you go,", "No, thanks.");
			stage = 802;
			break;
		case 802:
			switch (buttonId) {
			case 1:
				player("Yes, here you go.");
				stage = 803;
				break;
			case 2:
				end();
				break;
			}
			break;
		case 803:
			if (Skillcape.purchase(player, Skills.HERBLORE)) {
				npc("There you go! Enjoy.");
			}
			stage = 804;
			break;
		case 804:
			end();
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 455 };
	}
}
