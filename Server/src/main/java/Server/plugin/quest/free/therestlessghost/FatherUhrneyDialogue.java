package plugin.quest.free.therestlessghost;

import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.diary.DiaryType;
import core.plugin.InitializablePlugin;
import core.game.node.item.Item;
import plugin.dialogue.DialoguePlugin;

/**
 * Represents the father urgney dialogue plugin.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class FatherUhrneyDialogue extends DialoguePlugin {

	/**
	 * Constructs a new {@code FatherUhrneyDialogue} {@code Object}.
	 */
	public FatherUhrneyDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code FatherUhrneyDialogue} {@code Object}.
	 * @param player the player.
	 */
	public FatherUhrneyDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new FatherUhrneyDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		npc("Go away! I'm meditating!");
		stage = 0;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			if (player.getQuestRepository().getQuest(RestlessGhost.NAME).getStage(player) == 0) {
				options("Well, that's friendly.", "I've come to respossess your house.");
				stage = 1;
			} else if (player.getQuestRepository().getQuest(RestlessGhost.NAME).getStage(player) == 10) {
				options("Well, that's friendly.", "I've come to respossess your house.", "Father Aereck sent me to talk to you.");
				stage = 500;
			} else if (player.getGameAttributes().getAttributes().containsKey("restless-ghost:urhney") || player.getQuestRepository().isComplete(RestlessGhost.NAME)) {
				options("Well, that's friendly.", "I've come to respossess your house.", "I've lost the Amulet of Ghostspeak.");
				stage = 514;
			}
			break;
		case 500:
			switch (buttonId) {
			case 1:
				player("Well, that's friendly.");
				stage = 10;
				break;
			case 2:
				player("I've come to repossess your house.");
				stage = 20;
				break;
			case 3:
				player("Father Aereck sent me to talk to you.");
				stage = 501;
				break;
			}
			break;
		case 501:
			npc("I suppose I'd better talk to you then. What problems", "has he got himself into this time?");
			stage = 502;
			break;
		case 502:
			player("He's got a ghost haunting his graveyard.");
			stage = 503;
			break;
		case 503:
			npc("Oh, the silly fool.");
			stage = 504;
			break;
		case 504:
			npc("I leave town for just five months, and ALREADY he", "can't manage.");
			stage = 505;
			break;
		case 505:
			npc("(sigh)");
			stage = 506;
			break;
		case 506:
			npc("Well, I can't go back and exorcise it. I vowed not to", "leave this place. Until I had done a full two years of", "prayer and meditation.");
			stage = 507;
			break;
		case 507:
			npc("Tell you what I can do though; take this amulet.");
			stage = 508;
			break;
		case 508:
			if (player.getInventory().freeSlots() == 0) {
				end();
				player.getPacketDispatch().sendMessage("You don't have enough inventory space to accept this amulet.");
				break;
			}
			interpreter.sendItemMessage(552, "Father Urhney hands you an amulet.");
			player.getInventory().add(new Item(552, 1));
			player.getQuestRepository().getQuest(RestlessGhost.NAME).setStage(player, 20);
			player.getGameAttributes().setAttribute("/save:restless-ghost:urhney", true);
			stage = 509;
			break;
		case 509:
			npc("It is an Amulet of Ghostspeak.");
			stage = 510;
			break;
		case 510:
			npc("So called, because when you wear it you can speak to", "ghosts. A lot of ghosts are doomed to be ghosts because", "they have left some important task uncompleted.");
			stage = 511;
			break;
		case 511:
			npc("Maybe if you know what this task is, you can get rid of", "the ghost. I'm not making any gurantees mind you,", "but it is the best I can do right now.");
			stage = 512;
			break;
		case 512:
			player("Thank you. I'll give it a try!");
			stage = 513;
			break;
		case 513:
			end();
			break;
		case 514:
			switch (buttonId) {
			case 1:
				player("Well, that's friendly.");
				stage = 10;
				break;
			case 2:
				player("I've come to repossess your house.");
				stage = 20;
				break;
			case 3:
				player("I've lost the Amulet of Ghostpeak.");
				stage = 515;
				break;
			}
			break;
		case 515:
			if (player.getInventory().contains(552, 1) || player.getEquipment().contains(552, 1)) {
				interpreter.sendDialogue("Father Urhney sighs.");
				stage = 516;
				break;
			}
			if (player.getBank().contains(552, 1)) {
				interpreter.sendDialogue("Father Urhney sighs.");
				stage = 517;
				break;
			}
			interpreter.sendDialogue("Father Urhney sighs.");
			stage = 519;
			break;
		case 516:
			npc("What are you talking about? I can see you've got it", "with you!");
			stage = 518;
			break;
		case 517:
			npc("What are you talking about? I can see you've got it", "in your bank!");
			stage = 518;
			break;
		case 518:
			end();
			break;
		case 519:
			npc("How careless can you get? Those things aren't easy to", "come by you know! It's a good job I've got a spare.");
			stage = 520;
			break;
		case 520:
			player.getInventory().add(new Item(552));
			interpreter.sendItemMessage(552, "Father Urhney hands you an amulet.");
			player.getAchievementDiaryManager().finishTask(player, DiaryType.LUMBRIDGE, 1, 12);
			stage = 521;
			break;
		case 521:
			npc("Be more careful this time.");
			stage = 522;
			break;
		case 522:
			player("Ok, I'll try to be.");
			stage = 523;
			break;
		case 523:
			end();
			break;
		case 1:
			switch (buttonId) {
			case 1:
				player("Well, that's friendly.");
				stage = 10;
				break;
			case 2:
				player("I've come to repossess your house.");
				stage = 20;
				break;

			}
			break;
		case 10:
			npc("I SAID go AWAY.");
			stage = 11;
			break;
		case 11:
			player("Ok, ok... sheesh, what a grouch.");
			stage = 12;
			break;
		case 12:
			end();
			break;
		case 20:
			npc("Under what grounds???");
			stage = 21;
			break;
		case 21:
			options("Repeated failure on mortgage repayments.", "I don't know, I just wanted this house.");
			stage = 22;
			break;
		case 22:
			switch (buttonId) {
			case 1:
				player("Repeated failure on mortgage repayments.");
				stage = 100;
				break;
			case 2:
				player("I don't know. I just wanted this house...");
				stage = 200;
				break;

			}
			break;
		case 100:
			npc("What?");
			stage = 101;
			break;
		case 101:
			npc("I don't have a mortgage! I built this house.");
			stage = 102;
			break;
		case 102:
			player("Sorry. I mus thave got the wrong address. All the", "houses look the same around here.");
			stage = 103;
			break;
		case 103:
			npc("What? What houses? What ARE you talking about???");
			stage = 104;
			break;
		case 104:
			player("Never mind.");
			stage = 105;
			break;
		case 105:
			end();
			break;
		case 200:
			npc("Oh... go away and stop wasting my time!");
			stage = 201;
			break;
		case 201:
			end();
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 458 };
	}
}
