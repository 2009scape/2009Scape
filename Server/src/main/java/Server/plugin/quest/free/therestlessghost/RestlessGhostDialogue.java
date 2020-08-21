package plugin.quest.free.therestlessghost;

import core.game.node.entity.npc.NPC;
import core.plugin.InitializablePlugin;
import core.game.node.entity.player.Player;
import plugin.dialogue.DialoguePlugin;
import plugin.dialogue.FacialExpression;

/**
 * Handles the RestlessGhostDialogue dialogue.
 * @author 'Vexia
 */
@InitializablePlugin
public class RestlessGhostDialogue extends DialoguePlugin {

	public RestlessGhostDialogue() {

	}

	public RestlessGhostDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {

		return new RestlessGhostDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Hello ghost, how are you?");
		stage = 0;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			if (!player.getEquipment().contains(552, 1)) {
				interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Wooo wooo wooooo!");
				stage = 1;
			} else {
				if (player.getQuestRepository().getQuest("The Restless Ghost").getStage(player) == 20) {
					interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Not very good actually.");
					stage = 500;
					break;
				}
				if (player.getQuestRepository().getQuest("The Restless Ghost").getStage(player) == 30) {
					interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "How are you doing finding my skull?");
					stage = 520;
					break;
				}
				if (player.getQuestRepository().getQuest("The Restless Ghost").getStage(player) == 40) {
					interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "How are you doing finding my skull?");
					stage = 550;
					break;
				}
				interpreter.sendDialogues(npc, null, "Fine, thanks.");
				stage = 990;
			}
			break;
		case 990:
			end();
			break;
		case 500:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "What's the problem then?");
			stage = 501;
			break;
		case 501:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Did you just understand what I said???");
			stage = 502;
			break;
		case 502:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Yep, now tell me what the problem is.");
			stage = 503;
			break;
		case 503:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "WOW! This is INCREDIBLE! I didn't expect anyone", "to ever understand me again!");
			stage = 504;
			break;
		case 504:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Ok, Ok, I can understand you!");
			stage = 505;
			break;
		case 505:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "But have you any idea WHY you're doomed to be a", "ghost?");
			stage = 506;
			break;
		case 506:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Well, to be honest... I'm not sure.");
			stage = 507;
			break;
		case 507:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "I've been told a certain task may need to be completed", "so you can rest in peace.");
			stage = 508;
			break;
		case 508:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "I should think it is probably because a warlock has come", "along and stolen my skull. If you look inside my coffin", "there, you'll find my corpse without a head on it.");
			stage = 509;
			break;
		case 509:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Do you know where this warlock might be now?");
			stage = 510;
			break;
		case 510:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "I think it was one of the warlocks who lives in the big", "tower by the sea south-west from here.");
			stage = 511;
			break;
		case 511:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Ok. I will try and get the skull back for you, then you", "can rest in peace.");
			player.getQuestRepository().getQuest("The Restless Ghost").setStage(player, 30);
			stage = 512;
			break;
		case 512:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Ooh, thank you. That would be such a great relief!");
			stage = 513;
			break;
		case 513:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "It is so dull being a ghost...");
			stage = 514;
			break;
		case 514:
			end();
			break;
		case 520:
			if (player.getInventory().contains(964, 1)) {

				break;
			} else {
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Sorry, I can't find it at the moment.");
				stage = 521;
			}
			break;
		case 521:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Ah well. Keep on looking.");
			stage = 522;
			break;
		case 522:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "I'm pretty sure it's somewhere in the tower south-west", "from here. There's a lot of levels to the tower, though. I", "suppose it might take a little while to find.");
			stage = 523;
			break;
		case 523:
			end();
			break;
		case 550:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "I have found it!");
			stage = 551;
			break;
		case 551:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Hurah! Now I can stop being a ghost! You just need", "to put it on my coffin there, and I will be free!");
			stage = 523;
			break;
		case 1:
			interpreter.sendOptions("Select an Option", "Sorry, I don't speak ghost.", "Ooh... THAT'S interesting.", "Any hints where I can find some treasure?");
			stage = 2;
			break;
		case 2:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Sorry, I don't speak ghost.");
				stage = 10;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Ooh... THAT'S interesting.");
				stage = 20;
				break;
			case 3:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Any hints wher I can find some treasure?");
				stage = 30;
				break;

			}
			break;
		case 10:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Woo woo?");
			stage = 11;
			break;
		case 11:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Nope, still don't understand you.");
			stage = 12;
			break;
		case 12:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "WOOOOOOOOO!");
			stage = 13;
			break;
		case 13:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Never mind.");
			stage = 14;
			break;
		case 14:
			end();
			break;
		case 30:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Wooooooo woo! Wooooo woo wooooo woowoowoo wooo", "Woo woooo. Wooooo woo woo? Wooooooooooooooooooo!");
			stage = 31;
			break;
		case 31:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Sorry, I don't speak ghost.");
			stage = 32;
			break;
		case 32:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Woo woo?");
			stage = 11;
			break;
		case 20:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Woo woooo. Woooooooooooooooooo!");
			stage = 21;
			break;
		case 21:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Did he really?");
			stage = 22;
			break;
		case 22:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Woo.");
			stage = 23;
			break;
		case 23:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "My brother had EXACTLY the same problem.");
			stage = 24;
			break;
		case 24:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Woo Wooooo!");
			stage = 25;
			break;
		case 25:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Wooooo Woo woo woo!");
			stage = 26;
			break;
		case 26:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Goodbye. Thanks for the chat.");
			stage = 27;
			break;
		case 27:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Wooo wooo?");
			stage = 28;
			break;
		case 28:
			end();
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 457 };
	}
}
