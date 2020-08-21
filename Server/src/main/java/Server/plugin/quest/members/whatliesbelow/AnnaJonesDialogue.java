package plugin.quest.members.whatliesbelow;

import plugin.dialogue.DialoguePlugin;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.quest.Quest;
import core.game.node.item.Item;

/**
 * Handles the anna jones dialogue.
 * @author Vexia
 */
public class AnnaJonesDialogue extends DialoguePlugin {

	/**
	 * The quest.
	 */
	private Quest quest;

	/**
	 * Constructs a new {@Code AnnaJonesDialogue} {@Code Object}
	 */
	public AnnaJonesDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@Code AnnaJonesDialogue} {@Code Object}
	 * @param player the player.
	 */
	public AnnaJonesDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new AnnaJonesDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		quest = player.getQuestRepository().getQuest(WhatLiesBelow.NAME);
		switch (quest.getStage(player)) {
		default:
			if (args.length >= 2) {
				npc("Excuse me. I am working on that statue at the moment", "Please don't touch it.");
				stage = 50;
				break;
			}
			npc("Yes? Can I help you?");
			break;
		case 30:
			npc("Ah. You must be " + player.getUsername() + ", right? I have a bronze", "pickaxe here for you.");
			break;
		case 40:
			if (args.length >= 2) {
				npc("You did it! oh, well done! How exciting!");
				stage = 10;
				break;
			}
			npc("You opened the tunnel; well done!");
			stage++;
			break;
		}
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (quest.getStage(player)) {
		default:
			switch (stage) {
			case 0:
				options("Who are you?", "What are you doing here?", "Who does this statue represent?", "Okay, I'd better go.");
				stage++;
				break;
			case 1:
				switch (buttonId) {
				case 1:
					player("Who are you?");
					stage = 10;
					break;
				case 2:
					player("What are you doing here?");
					stage = 30;
					break;
				case 3:
					player("Who does this statue represent?");
					stage = 20;
					break;
				case 4:
					player("Okay, I'd better go.");
					stage = 40;
					break;
				}
				break;
			case 10:
				npc("Well, now. Do you always go around asking about people", "like that? It's very rude, you know.");
				stage++;
				break;
			case 11:
				player("Sorry! I didn't mean to pry!");
				stage++;
				break;
			case 12:
				npc("That's alright. My name is Louisiana Jones, although", "most people call me Anna. I'm an archaeologist.");
				stage++;
				break;
			case 13:
				player("Oh. Do you work for the Varrock Museum?");
				stage++;
				break;
			case 14:
				npc("Hah! No. I used to, but now I prefer to work", "freelance for independent employers.");
				stage++;
				break;
			case 15:
				player("I see.");
				stage++;
				break;
			case 16:
				end();
				break;
			case 20:
				npc("That, my dear, is the statue of the great god Saradomin", "himself. Stand and admire in awe, for you are in the", "presence of greatness!");
				stage++;
				break;
			case 21:
				end();
				break;
			case 30:
				npc("I'm investigating something for someone.");
				stage++;
				break;
			case 31:
				player("That doesn't really explain anything!");
				stage++;
				break;
			case 32:
				npc("I never said it would.");
				stage++;
				break;
			case 33:
				end();
				break;
			case 40:
				end();
				break;
			case 50:
				player("You are? But you're just sitting there.");
				stage++;
				break;
			case 51:
				npc("Yes. I'm on a break.");
				stage++;
				break;
			case 52:
				player("Oh, I see. When does your break finish?");
				stage++;
				break;
			case 53:
				npc("When I decide to start working again. Right now, I'm", "enjoying sitting on this bench.");
				stage++;
				break;
			case 54:
				end();
				break;
			}
			break;
		case 30:
			switch (stage) {
			case 0:
				if (!player.getInventory().contains(1265, 1)) {
					player.getInventory().add(new Item(1265), player);
					player("Thank you very much!");
					stage++;
				} else {
					player("Thanks, but I already have one.");
					stage = 1;
				}
				break;
			case 1:
				npc("My employer, Surok Magis, sent word to me that you", "may come to use the tunnel. You will need something to", "help you get in there. The pickaxe will help.");
				stage++;
				break;
			case 2:
				player("Uh, thanks.");
				stage++;
				break;
			case 3:
				npc("Okay, then. The tunnel awaits...");
				stage++;
				break;
			case 4:
				options("What tunnel?", "Sorry, who are you?", "Who does this statue represent?", "What are you doing here?", "Okay, I'd better go.");
				stage++;
				break;
			case 5:
				switch (buttonId) {
				case 1:
					player("What tunnel?");
					stage = 10;
					break;
				case 2:
					player("Sorry, who are you?");
					stage = 20;
					break;
				case 3:
					player("Who does this statue represent?");
					stage = 30;
					break;
				case 4:
					player("What are you doing here?");
					stage = 40;
					break;
				case 5:
					player("Okay, I'd better go.");
					stage = 50;
					break;
				}
				break;
			case 10:
				npc("Why, the Chaos Tunnel of course! I imagine Surok will", "have told you of it.");
				stage = 41;
				break;
			case 20:
				npc("My name is Louisiana Jones, although most people", "call me Anna. I'm an archaeologist.");
				stage = 41;
				break;
			case 30:
				npc("That, my dear, is the statue of the great god Saradomin", "himself. Stand and admire in awe for you are in the", "presence of greatness!");
				stage = 41;
				break;
			case 40:
				npc("Well story and rumour has it that Dragon'hai built a", "tunnel under this statue of Saradomin that would allow", "them to visit the Chaos Altar without having to go", "through the Wilderness.");
				stage++;
				break;
			case 41:
				end();
				break;
			case 50:
				end();
				break;
			}
			break;
		case 40:
			switch (stage) {
			case 1:
				end();
				break;
			case 10:
				player("Right, well, I better see what's down there, then.");
				stage++;
				break;
			case 11:
				end();
				break;
			}
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 5837 };
	}

}
