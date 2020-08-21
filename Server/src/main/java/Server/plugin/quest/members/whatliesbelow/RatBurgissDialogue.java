package plugin.quest.members.whatliesbelow;

import plugin.dialogue.DialoguePlugin;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.diary.AchievementDiary;
import core.game.node.entity.player.link.diary.DiaryType;
import core.game.node.entity.player.link.quest.Quest;
import core.game.node.item.Item;

/**
 * The rat burgiss dialogue.
 * @author Vexia
 */
public class RatBurgissDialogue extends DialoguePlugin {

	/**
	 * The achievement diary.
	 */
	private AchievementDiary diary;

	/**
	 * The quest.
	 */
	private Quest quest;

	/**
	 * If w'ere chatting about our diary.
	 */
	private boolean isDiary;

	/**
	 * Constructs a new {@Code RatBurgissDialogue} {@Code Object}
	 * @param player the player.
	 */
	public RatBurgissDialogue(Player player) {
		super(player);
	}

	/**
	 * 
	 */
	public RatBurgissDialogue() {
		/**
		 * empty.
		 */
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new RatBurgissDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		quest = player.getQuestRepository().getQuest(WhatLiesBelow.NAME);
		switch (quest.getStage(player)) {
		case 0:
			options("Hello!", "I have a question about my Achievement Diary.");
			stage = -1;
			break;
		default:
			options("Hello!", "I have a question about my Achievement Diary.");
			stage = -1;
			break;
		}
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		if (isDiary) {
			switch (stage) {
			case 0:
				if (diary == null) {
					diary = player.getAchievementDiaryManager().getDiary(DiaryType.VARROCK);
				}
				switch (buttonId) {
				case 1:
					player("What is the Achievement Diary?");
					stage = 410;
					break;
				case 2:
					player("What are the rewards?");
					stage = 420;
					break;
				case 3:
					player("How do I claim the rewards?");
					stage = 430;
					break;
				case 4:
					end();
					break;
				}
				break;
			case 41:
				end();
				break;
			case 440:
				npc("Yes I see that, you'll be wanting your", "reward then I assume?");
				stage++;
				break;
			case 441:
				player("Yes please.");
				stage++;
				break;
			case 442:
				diary.setRewarded(0);
				for (Item i : diary.getType().getRewards(0)) {
					player.getInventory().add(i, player);
				}
				npc("This armour is a representation of the adventures you", "went on to complete your tasks.");
				stage += 2;
				break;
			case 444:
				player("Wow, thanks!");
				stage = 41;
				break;
			case 450:
				player.getInventory().add(diary.getType().getRewards(0)[0], player);
				npc("You better be more careful this time.");
				stage = 41;
				break;
			case 410:
				npc("It's a diary that helps you keep track of particular", "achievements. Here in Varrock it can help you", "discover some quite useful things. Eventually, with", "enough exploration, the people of Varrock will reward");
				stage++;
				break;
			case 411:
				npc("you.");
				stage++;
				break;
			case 412:
				npc("You can see what tasks you have listed by clicking on", "the green button in the Quest List.");
				stage = 41;
				break;
			case 420:
				npc("Well, there's three different levels of Varrock Armour,", "which match up with the three levels of difficulty. Each", "has the same rewards as the previous level, and an", "additional one too... but I won't spoil your surprise.");
				stage++;
				break;
			case 421:
				npc("Rest assured, the people of Varrock are happy to see", "you visiting the land.");
				stage = 41;
				break;
			case 430:
				npc("Just complete the tasks so they're all ticked off, then", "you can claim your reward. Most of them are", "straightforward; you might find some require quests to", "be started, if not finished.");
				stage++;
				break;
			case 431:
				npc("To claim the different Varrock Armour, speak to Vannaka", "Reldo, and myself.");
				stage = 41;
				break;
			case 50:
				end();
				break;
			}
			return true;
		}
		if (stage == -1) {
			switch (buttonId) {
			case 1:
				player("Hello!");
				stage = 5;
				break;
			case 2:
				sendDiaryDialogue();
				break;
			}
			return true;
		}
		switch (quest.getStage(player)) {
		case 0:
			switch (stage) {
			/*
			 * case 0: interpreter.sendDialogues(npc, FacialExpression.NORMAL,
			 * "Oh, hello. I'd love to chat right now, but I'm a bit busy.",
			 * "Perhaps you could come back and chat another time?"); stage = 1;
			 * break; case 1: interpreter.sendDialogues(player,
			 * FacialExpression.NORMAL, "Of course. Sorry to bother you!");
			 * stage = 2; break; case 2: interpreter.sendDialogues(npc,
			 * FacialExpression.NORMAL,
			 * "No problem! Say, have you been to see the wizard's",
			 * "tower in the south yet? It's an amazing sight! you",
			 * "should go and see it!"); stage = 3; break; case 3:
			 * interpreter.sendDialogues(player, FacialExpression.NORMAL,
			 * "Thanks! I will!"); stage = 4; break; case 4: end(); break;
			 */
			case 5:
				npc("Oh, hello. I'm Rat.");
				stage++;
				break;
			case 6:
				player("You're a what?");
				stage++;
				break;
			case 7:
				npc("No, no. My name is Rat. Rat Burgiss.");
				stage++;
				break;
			case 8:
				player("Ohhhhh, well, what's up, Ratty?");
				stage++;
				break;
			case 9:
				npc("It's Rat, thank you. And I, uh..heh...I seem to", "be in a bit of trouble here, as", "you can probably see.");
				stage++;
				break;
			case 10:
				player("Why, what seems to be the matter?");
				stage++;
				break;
			case 11:
				npc("Well, I'm a trader by nature and I was on the way to", "Varrock with my cart here when I was set upon by", "outlaws! They ransacked my cart and stole", "some very important papers that I must get back.");
				stage++;
				break;
			case 12:
				player("Shall I get them back for you?");
				stage++;
				break;
			case 13:
				npc("You mean you want to help?");
				stage++;
				break;
			case 14:
				player("Of course! Tell me what you need me to do.");
				stage++;
				break;
			case 15:
				npc("Right, now I heard those outlaws say something about", "having a small campsite somewhere to the west of the", "Grand Exchange. They headed off to the north-west of", "here, taking five pages with them.");
				stage++;
				break;
			case 16:
				npc("Kill the outlaws and get those papers back from them for", "me. Here's a folder in which you can put the", "pages. Be careful, though; those outlaws are tough.");
				stage++;
				break;
			case 17:
				npc("When you find all 5 pages, put them back in the folder", "and bring them back to me!");
				stage++;
				break;
			case 18:
				player("Don't worry, Ratty! I won't let you down!");
				stage++;
				break;
			case 19:
				npc("...");
				stage++;
				break;
			case 20:
				quest.start(player);
				end();
				break;
			}
			break;
		case 10:
			switch (stage) {
			case 0:
				npc("Hello again! How are things going?");
				stage++;
				break;
			case 1:
				if (player.getInventory().containsItem(WhatLiesBelow.FULL_FOLDER)) {
					player("I got your pages back!");
					stage = 5;
					break;
				}
				if (!player.hasItem(WhatLiesBelow.EMPTY_FOLDER) && !player.hasItem(WhatLiesBelow.USED_FOLDER) && !player.hasItem(WhatLiesBelow.FULL_FOLDER)) {
					player("I lost the folder you gave me. Do you have another", "one?");
					stage = 3;
					break;
				} else {
					player("Good!");
				}
				stage++;
				break;
			case 2:
				end();
				break;
			case 3:
				player.getInventory().add(WhatLiesBelow.EMPTY_FOLDER);
				npc("Sure. Here you go. I'll add it to your account.");
				stage++;
				break;
			case 4:
				end();
				break;
			case 5:
				npc("Excellent! I knew you could help! Let me take", "those from you, there.");
				stage++;
				break;
			case 6:
				npc("Now, I liked the way you handled yourself on that last", "little 'mission' I gave you there, so I'm going to let", "you in on a little secret!");
				stage++;
				break;
			case 7:
				player("Wait! Wait! Let me guess! You're a actually a rich prince", "in disguise who wants to help poor people", "like me!?");
				stage++;
				break;
			case 8:
				npc("Uhhh...no. No, that's not it. You know, on second thought. I", "think I'll keep my secret for now. Look, instead", "you can do another job for me.");
				stage++;
				break;
			case 9:
				player("All work and no play makes " + player.getUsername() + " a dull adventurer!");
				stage++;
				break;
			case 10:
				npc("Yes, well, I'm sure that may be the case. However, what", "I want you to do is take this letter to someone", "for me. It's in a different language so, trust me you", "won't be able to read it.");
				stage++;
				break;
			case 11:
				npc("Take it to a wizard named Surok Magis who resides in", "the Varrock Palace Library. I'll see about some sort of", "reward for your work when I get myself sorted out here.");
				stage++;
				break;
			case 12:
				player("Letter. Wizard. Varrock. Library. Got it!");
				stage++;
				break;
			case 13:
				npc("Yes, good luck then.");
				stage++;
				break;
			case 14:
				player.getInventory().remove(WhatLiesBelow.FULL_FOLDER);
				player.getInventory().add(WhatLiesBelow.RATS_LETTER);
				quest.setStage(player, 20);
				end();
				break;
			}
			break;
		case 20:
			switch (stage) {
			case 0:
				npc("Ah, hello. How is your task going?");
				stage++;
				break;
			case 1:
				if (!player.hasItem(WhatLiesBelow.RATS_LETTER)) {
					player("I think I lost that letter you gave me!");
					stage = 3;
					break;
				}
				player("Good!");
				stage++;
				break;
			case 2:
				end();
				break;
			case 3:
				npc("Goodness me! Not much of a messenger, are you? Here's", "another one; try not to lose it this time! I've charged the", "parchment to your account.");
				stage++;
				break;
			case 4:
				player("Will you take cheque?");
				stage++;
				break;
			case 5:
				npc("No thanks. I prefer tartan.");
				stage++;
				break;
			case 6:
				if (!player.getInventory().contains(WhatLiesBelow.RATS_LETTER.getId(), 1)) {
					player.getInventory().add(WhatLiesBelow.RATS_LETTER, player);
				}
				end();
				break;
			}
			break;
		default:
			end();
			break;
		case 50:
			switch (stage) {
			case 0:
				npc("Ah, " + player.getUsername() + "! You've returned!");
				stage++;
				break;
			case 1:
				if (!player.getInventory().containsItem(WhatLiesBelow.SUROKS_LETTER)) {
					player("I'm still searching!");
					stage = 2;
					break;
				}
				player("Yes! I have a letter for you.");
				stage = 3;
				break;
			case 2:
				end();
				break;
			case 3:
				npc("A letter for me? Let me see.");
				stage++;
				break;
			case 4:
				if (player.getInventory().containsItem(WhatLiesBelow.SUROKS_LETTER)) {
					npc("This letter is treasonous! This does indeed confirm my", "worst fears. It is time I let you into my", "secret and hopefully this will answer any questions", "you may have.");
					stage++;
				}
				break;
			case 5:
				player("Okay, go on.");
				stage++;
				break;
			case 6:
				npc("I am not really a trader. I am the Commander of", "the Varrock Palace Secret Guard, VPSG for short.");
				stage++;
				break;
			case 7:
				player("Okay, I had a feeling you weren't a real trader due to", "the fact that you had nothing to sell! So why", "the secrecy?");
				stage++;
				break;
			case 8:
				npc("I'm just getting to that. A short while ago, we receieved", "word that Surok had discovered a powerful mind-control", "spell and intended to use it on King Roald himself!");
				stage++;
				break;
			case 9:
				npc("He could control the whole kingdom that way!");
				stage++;
				break;
			case 10:
				player("I think I can believe that. Surok's not the nicest", "person in Misthalin!");
				stage++;
				break;
			case 11:
				npc("Yes, but until now, the spell has been useless to him", "as he is currently under guard at the palace and not", "allowed to leave. He could not get the tools for the spell", "because if he left the palace he would be arrested!");
				stage++;
				break;
			case 12:
				player("Uh oh! I think I may have helped him by mistake, here.", "He promised me a big reward if I collected some items for", "him..but he said it was for a spell to make gold!");
				stage++;
				break;
			case 13:
				npc("I assume you did not know of his plans; that is", "why you weren't arrested!");
				stage++;
				break;
			case 14:
				player("Thank you! How can I help fix this mistake?");
				stage++;
				break;
			case 15:
				npc("Okay, here's what I need you to do. One of my contacts", "has devised a spell that he is sure will be able to", "counteract the effects of the mind-control spell. I need", "you to visit him.");
				stage++;
				break;
			case 16:
				player("Okay, who is it?");
				stage++;
				break;
			case 17:
				npc("His name is Zaff. He runs a staff shop in Varrock. Go", "and speak to him and he will tell you what you should", "do. I will send word to him to let him know that", "you are coming.");
				stage++;
				break;
			case 18:
				player("Yes, sir! I'm on my way!");
				stage++;
				break;
			case 19:
				player.getInventory().remove(WhatLiesBelow.SUROKS_LETTER);
				quest.setStage(player, 60);
				end();
				break;
			}
			break;
		case 60:
		case 70:
			switch (stage) {
			case 0:
				npc("Yes, " + player.getUsername() + "?");
				stage++;
				break;
			case 1:
				player("Nevermind.");
				stage++;
				break;
			case 2:
				end();
				break;
			}
			break;
		case 80:
		case 90:
			switch (stage) {
			case 0:
				npc("Well, " + player.getUsername() + ", how did it go?");
				stage++;
				break;
			case 1:
				player("You should have been there! There was this...and Surok", "was like...and I was...and then King...and", "and...uh...ahem! The mission was accomplished and the", "king has been saved.");
				stage++;
				break;
			case 2:
				npc("I take it that it went alright, then? That's great news!");
				stage++;
				break;
			case 3:
				npc("Zaff has already briefed me on the events. We will", "arrange for Surok to be fed and watched. I think he", "will not to be a problem any more.");
				stage++;
				break;
			case 4:
				player("You know, one thing bother's me. He's now stuck in the", "library, but wasn't that the reason we were in this mess", "in the first place?");
				stage++;
				break;
			case 5:
				npc("Yes, you are right. But rest assured, we will be", "watching him much more closely from now on.");
				stage++;
				break;
			case 6:
				npc("You've done very well and have been a credit to the", "VPSG; perhaps one day there may be a place for you", "here!");
				stage++;
				break;
			case 7:
				npc("In the meantime, let me reward you for what you've", "done. I will be sure to call on you if we ever need help", "in the future.");
				stage++;
				break;
			case 8:
				quest.finish(player);
				end();
				break;
			}
			break;
		case 100:
			switch (stage) {
			case 0:
				npc("Ah, " + player.getUsername() + "! You did a fine service for us. You might", "make a good member of the VPSG one day. With a little", "training and a bit more muscle!");
				stage++;
				break;
			case 1:
				end();
				break;
			}
			break;
		}
		return true;
	}

	/**
	 * Sends the diary dialogue.
	 */
	private void sendDiaryDialogue() {
		isDiary = true;
		if (diary == null) {
			diary = player.getAchievementDiaryManager().getDiary(DiaryType.VARROCK);
		}
		if (diary.isComplete(0) && !diary.hasReward(0)) {
			player("I've done all the easy tasks in my Varrock", "Achievement Diary.");
			stage = 440;
			return;
		}
		if (diary.hasReward(0) && diary.isComplete(0) && !player.hasItem(diary.getType().getRewards(0)[0])) {
			player("I've seemed to have lost my armour...");
			stage = 450;
			return;
		}
		options("What is the Achievement Diary?", "What are the rewards?", "How do I claim the rewards?", "See you later.");
		stage++;
	}

	@Override
	public int[] getIds() {
		return new int[] { 5833 };
	}

	/**
	 * Gets the isDiary.
	 * @return the isDiary
	 */
	public boolean isDiary() {
		return isDiary;
	}

	/**
	 * Sets the isDiary.
	 * @param isDiary the isDiary to set.
	 */
	public void setDiary(boolean isDiary) {
		this.isDiary = isDiary;
	}

}
