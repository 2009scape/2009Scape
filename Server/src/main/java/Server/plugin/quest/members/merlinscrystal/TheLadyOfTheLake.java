package plugin.quest.members.merlinscrystal;

import plugin.dialogue.DialoguePlugin;
import plugin.dialogue.FacialExpression;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.quest.Quest;
import core.game.node.item.Item;

/**
 * Handles the Toolleprechaun dialogue.
 * @author Vexia
 * @author Splinter
 */
public class TheLadyOfTheLake extends DialoguePlugin {

	/**
	 * Constructs a new {@code TheLadyOfTheLake} {@code Object}
	 */
	public TheLadyOfTheLake() {
		/*
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code TheLadyOfTheLake} {@code Object}
	 * @param player the player.
	 */
	public TheLadyOfTheLake(Player player) {
		super(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		if (!player.hasItem(new Item(35, 1))) {
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Good day to you, sir.");
			stage = 0;
		} else {
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Good day to you, sir.");
			stage = 699;
		}
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		final Quest quest = player.getQuestRepository().getQuest("Merlin's Crystal");
		switch (stage) {
		case 0:
			interpreter.sendOptions("What would you like to say?", "Who are you?", "I seek the sword Excalibur.", "Good day.");
			stage = 1;
			break;
		case 1:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Who are you?");
				stage = 100;
				break;
			case 2:
				if (quest.getStage(player) < 50) {
					interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "I seek the sword Excalibur.");
					stage = 250;
				} else {
					interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "I seek the sword Excalibur.");
					stage = 161;
				}
				break;
			case 3:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Good day.");
				stage = 703;
				break;

			}
			break;
		case 250:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "I am afraid I do not know what you are talking about.");
			stage = 171;
			break;
		case 100:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "I am the Lady of the Lake.");
			stage = 102;
			break;
		case 102:
			interpreter.sendOptions("What would you like to say?", "I seek the sword Excalibur.", "Good day.");
			stage = 150;
			break;
		case 150:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "I seek the sword Excalibur.");
				stage = 161;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Good day.");
				stage = 703;
				break;

			}
			break;
		case 161:
			if (quest.getStage(player) == 50 || quest.getStage(player) == 60) {// they
				// haven't
				// proven
				// themselves
				// yet
				interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Aye, I have that aretefact in my possession.");
				stage = 300;
			} else if (quest.getStage(player) >= 70) {
				interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "I recall you have already proven yourself to be worthy of", "wielding it. I shall return it to you if you can prove", "yourself to still be worthy.");
				stage = 162;
			}
			break;
		case 162:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "And how can I do that?");
			stage = 163;
			break;
		case 163:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Why, by proving youself to be above material goods.");
			stage = 164;
			break;
		case 164:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "... And how can I do that?");
			stage = 165;
			break;
		case 165:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "500 coins ought to do it.");
			stage = 166;
			break;
		case 166:
			interpreter.sendOptions("What would you like to say?", "Okay, here you go.", "500 coins? No thanks!");
			stage = 167;
			break;
		case 167:
			switch (buttonId) {
			case 1:

				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Okay, here you go.");
				stage = 168;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "500 coins? No thanks.");
				stage = 456;
				break;
			}
			break;
		case 168:
			if (player.getInventory().freeSlots() == 0) {
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Sorry, I don't seem to have enough inventory space.");
				stage = 169;
			} else if (player.getInventory().contains(995, 500)) {
				player.getInventory().remove(new Item(995, 500));
				player.getInventory().add(new Item(35, 1));
				interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Thank's for the cash!", "I'm saving up for a new dress.");
				stage = 170;
			} else {
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Sorry, I don't seem to have enough conins.");
				stage = 169;
			}
			break;
		case 169:
			end();
			break;
		case 170:
			interpreter.sendDialogue("The lady of the Lake hands you Excalibur.");
			stage = 171;
			break;
		case 171:
			end();
			break;
		case 300:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "'Tis very valuable, and not an artefact to be given", "away lightly.");
			stage = 301;
			break;
		case 301:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "I would want to give it away only to one who is worthy", "and good.");
			stage = 302;
			break;
		case 302:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "And how am I meant to prove that?");
			stage = 303;
			break;
		case 303:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "I shall set a test for you.");
			stage = 304;
			break;
		case 304:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "First I need you to travel to Port Sarim. Then go to", "the upstairs room of the jeweller's shop there.");
			stage = 305;
			break;
		case 305:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Ok. That seems easy enough.");
			quest.setStage(player, 60);
			stage = 171;
			break;
		case 699:
			interpreter.sendOptions("What would you like to say?", "Who are you?", "Good day.");
			stage = 700;
			break;
		case 700:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Who are you?");
				stage = 720;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Good day.");
				stage = 703;
				break;
			}
			break;
		case 720:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "I am the Lady of the Lake.");
			stage = 721;
			break;
		case 721:
			end();
			break;
		case 456:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Your materialistic attitude saddens me.");
			stage = 457;
			break;
		case 457:
			end();
			break;
		case 703:
			end();
			break;
		}
		return true;
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new TheLadyOfTheLake(player);
	}

	@Override
	public int[] getIds() {
		return new int[] { 250 };
	}
}