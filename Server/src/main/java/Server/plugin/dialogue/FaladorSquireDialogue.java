package plugin.dialogue;

import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.diary.AchievementDiary;
import core.game.node.entity.player.link.diary.DiaryType;
import core.game.node.entity.player.link.quest.Quest;
import core.game.node.item.GroundItemManager;
import core.game.node.item.Item;
import core.plugin.InitializablePlugin;
import core.game.world.GameWorld;

/**
 * Represents the falador squire dialogue plugin.
 *
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class FaladorSquireDialogue extends DialoguePlugin {
    private boolean useDiaryDialogueTree = false;
    private boolean replacementReward = false;
    private AchievementDiary diary;
    private int level = 2;

    /**
     * Represents the blurite sword item.
     */
    private static final Item BLURITE_SWORD = new Item(667);

    /**
     * Represents the quest instance.
     */
    private Quest quest;

    /**
     * Constructs a new {@code FaladorSquireDialogue} {@code Object}.
     */
    public FaladorSquireDialogue() {
        /**
         * empty.
         */
    }

    /**
     * Constructs a new {@code FaladorSquireDialogue} {@code Object}.
     *
     * @param player the player.
     */
    public FaladorSquireDialogue(Player player) {
        super(player);
    }

    @Override
    public DialoguePlugin newInstance(Player player) {
        return new FaladorSquireDialogue(player);
    }

    @Override
    public boolean open(Object... args) {
        npc = (NPC) args[0];
        quest = player.getQuestRepository().getQuest("The Knight's Sword");
        interpreter.sendOptions("What do you want to do?", "Chat", "Talk about the Falador Achievement Diary");
        stage = -1;
		AchievementDiary diary = player.getAchievementDiaryManager().getDiary(DiaryType.FALADOR);
		replacementReward = diary.isLevelRewarded(level)
				&& diary.isComplete(level, true)
				&& !player.hasItem(diary.getType().getRewards(level)[0]);
        return true;
    }

    @Override
    public boolean handle(int interfaceId, int buttonId) {
        if (useDiaryDialogueTree || (stage == -1 && buttonId == 2)) { // Achievement Diary
            useDiaryDialogueTree = true;
            switch (stage) {
				case -1:
					if (replacementReward) {
						player("I seem to have lost my Falador shield...");
						stage = 80;
					} else {
						npc("How are you getting on with the Achievement Diary?");
						stage = 90;
					}
					break;
				case 80:
					player.getInventory().add(diary.getType().getRewards(level)[0], player);
					npc("Here's your replacement. Please be more careful.");
					stage = 999;
					break;
				case 90:
					options("I've come for my reward.", "I'm doing good.", "I have a question.");
					stage = 91;
					break;
				case 91:
					switch (buttonId) {
						case 1:
							player("I've come for my reward.");
							stage = 200;
							break;
						case 2:
							player("I'm doing good.");
							stage = 220;
							break;
						case 3:
							player("I have a question.");
							stage = 105;
							break;
					}
					break;
				case 105:
					if (!diary.isLevelRewarded(level)) {
						options("What is the Achievement Diary?", "What are the rewards?", "How do I claim the rewards?", "See you later.");
						stage = 106;
					} else {
						options("Can you remind me what my Falador shield does, please?", "What is the Achievement Diary?", "What are the rewards?", "How do I claim the rewards?", "See you later.");
						stage = 107;
					}
					break;
				case 106:
					switch (buttonId) {
						case 1:
							player("What is the Achievement Diary?");
							stage = 110;
							break;
						case 2:
							player("What are the rewards?");
							stage = 120;
							break;
						case 3:
							player("How do I claim the rewards?");
							stage = 130;
							break;
						case 4:
							player("See you later.");
							stage = 999;
							break;
					}
					break;

				case 107:
					switch (buttonId) {
						case 1:
							player("Can you remind me what my Falador shield does, please?");
							stage = 150;
							break;
						case 2:
							player("What is the Achievement Diary?");
							stage = 110;
							break;
						case 3:
							player("What are the rewards?");
							stage = 120;
							break;
						case 4:
							player("How do I claim the rewards?");
							stage = 130;
							break;
						case 5:
							player("See you later.");
							stage = 999;
							break;
					}
					break;

				case 110:
					npc("Ah, well it's a diary that helps you keep track of", "particular achievements you've made here on", "2009Scape.");
					stage = 111;
					break;
				case 111:
					npc("If you manage to complete a particular set of tasks,", "you will be rewarded for your explorative efforts.");
					stage = 112;
					break;
				case 112:
					npc("You can access your Achievement Diary by going to", "the Quest Journal, then clicking on the green star icon", "in the top-right hand corner.");
					stage = 105;
					break;

				case 120:
					npc("Ah, well there are different rewards for each", "Achievement Diary. For completing each stage of the", "Falador diary, you are presented with a Falador shield.");
					stage = 121;
					break;
				case 121:
					npc("There are three shields available, one for each difficulty", "level.");
					stage = 122;
					break;
				case 122:
					npc("When you are presented with your rewards, you will", "be told of their uses.");
					stage = 105;
					break;

				case 130:
					npc("You need to complete all of the tasks in a particular", "difficulty, then you can claim your reward.");
					stage = 131;
					break;
				case 131:
					npc("Some of Falador's tasks are simple, some will require", "certain skill levels, and some might require quests to be", "started or completed.");
					stage = 132;
					break;
				case 132:
					npc("To claim your Falador Achievement Diary rewards,", "speak to Redbeard the Pirate in Port Sarim, Sir Vyvin's", "squire in the White Knight's Castle, or myself.");
					stage = 105;
					break;

				case 150:
					npc("This is the final stage of the Falador shield: a tower", "shield. It grants you all the benefits fo the buckler", "and kiteshield did, full Prayer restore, and access to", "some interesting new seeds that my friend Wyson has");
					stage = 151;
					break;
				case 151:
					npc("found.");
					stage = 152;
					break;
				case 152:
					npc("As before, Prayer restore can only be used once per", "day, but this time it restores all of your Prayer points!");
					stage = 153;
					break;
				case 153:
					npc("The new seeds I mentioned were discovered by Wyson,", "who can be found in Falador Park. He'll give you some", "new seeds in return for the skin of the giant mole", "beneath Falador Park..");
					stage = 154;
					break;
				case 154:
					npc("He'll only offer them to you if you're wielding the", "Falador shield, though.");
					stage = 155;
					break;
				case 155:
					npc("As well as all these features, the shield is pretty handy", "in combat, and gives you a big Prayer boost.");
					stage = 105;
					break;

				case 200:
					if (diary.isLevelRewarded(level)) {
            			npc("But you've already gotten yours!");
            			stage = 105;
            		} else if (diary.isComplete(level, true)) {
                        npc("So, you've finished. Well done! I believe congratulations", "are in order.");
                        stage = 201;
                    } else {
                        npc("But you haven't finished!");
                        stage = 105;
                    }
                    break;
                case 201:
                    player("I believe rewards are in order.");
                    stage = 202;
                    break;
                case 202:
                    npc("Right you are.");
                    stage = 203;
                    break;
                case 203:
                    npc("This is the final stage of the Falador shield: a tower", "shield. It grants you all the benefits fo the buckler", "and kiteshield did, full Prayer restore, and access to", "some interesting new seeds that my friend Wyson has");
                    if (!diary.isLevelRewarded(level)) {
                        for (Item i : diary.getType().getRewards(level)) {
                            if (!player.getInventory().add(i, player)) {
                                GroundItemManager.create(i, player);
                            }
                        }
                        diary.setLevelRewarded(level);
                    }
                    stage = 204;
                    break;
                case 204:
                    npc("found.");
                    stage = 205;
                    break;
                case 205:
					npc("As before, Prayer restore can only be used once per", "day, but this time it restores all of your Prayer points!");
					stage = 206;
                    break;
                case 206:
                    npc("The new seeds I mentioned were discovered by Wyson,", "who can be found in Falador Park. He'll give you some", "new seeds in return for the skin of the giant mole", "beneath Falador Park..");
                    stage = 207;
                    break;
                case 207:
                    npc("He'll only offer them to you if you're wielding the", "Falador shield, though.");
                    stage = 208;
                    break;
                case 208:
                    npc("As well as all these features, the shield is pretty handy", "in combat, and gives you a big Prayer boost.");
                    stage = 209;
                    break;
                case 209:
                    interpreter.sendDialogues(player, FacialExpression.AMAZED, "Wow, thanks!");
                    stage = 210;
                    break;
                case 210:
                    npc("If you should lose your shield, come back and see me", "for another one.");
                    stage = 105;
                    break;
				case 220:
					npc("Keep it up!");
					stage = 105;
					break;

                case 999:
                    end();
                    break;
            }
        } else { // chat

            switch (quest.getStage(player)) {
                case 100:
                    switch (stage) {
                        case -1:
                            npc("Hello friend! Many thanks for all of your help! Vyvin", "never even realised it was a different sword, and I still", "have my job!");
                            stage = 0;
                            break;
                        case 0:
                            end();
                            break;
                    }
                    break;
                case 60:
                    switch (stage) {
                        case -1:
                            npc("So how are you doing getting a sword?");
                            stage = 0;
                            break;
                        case 0:
                            if (!player.getInventory().containsItem(BLURITE_SWORD)) {
                                player("I've found a dwarf who will make the sword, I've just", "got to find the materials for it now!");
                                stage = 1;
                            } else {
                                player("I have retrieved your sword for you.");
                                stage = 2;
                            }
                            break;
                        case 1:
                            end();
                            break;
                        case 2:
                            npc("Thank you, thank you, thank you! I was seriously", "worried I would have to own up to Sir Vyvin!");
                            stage = 3;
                            break;
                        case 3:
                            interpreter.sendDialogue("You give the sword to the squire.");
                            stage = 4;
                            break;
                        case 4:
                            if (player.getInventory().remove(BLURITE_SWORD)) {
                                quest.finish(player);
                                end();
                            }
                            break;
                    }
                    break;
                case 50:
                    switch (stage) {
                        case -1:
                            npc("So how are you doing getting a sword?");
                            stage = 0;
                            break;
                        case 0:
                            if (player.getInventory().contains(666, 1)) {
                                player("I have the picture, I'll just take it to the dwarf now!");
                                stage = 3;
                            } else {
                                player("I didn't get the picture yet...");
                                stage = 1;
                            }
                            break;
                        case 1:
                            npc("Please try and get it quickly... I am scared Sir Vyvin", "will find out!");
                            stage = 2;
                            break;
                        case 2:
                            end();
                            break;
                        case 3:
                            npc("Please hurry!");
                            stage = 2;
                            break;
                    }
                    break;
                case 40:
                    switch (stage) {
                        case -1:
                            npc("So how are you doing getting a sword?");
                            stage = 0;
                            break;
                        case 0:
                            player("I have found an Imcando dwarf but he needs a picture", "of the sword before he can make it.");
                            stage = 1;
                            break;
                        case 1:
                            npc("A picture eh? Hmmm.... The only one I can think of is", "in a small portrait of Sir Vyvin's father... Sir Vyvin", "keeps it in a cupboard in is room I think.");
                            stage = 2;
                            break;
                        case 2:
                            player("Ok, I'll try to get that then.");
                            stage = 3;
                            break;
                        case 3:
                            npc("Please don't let him catch you! He MUSTN'T know", "what happened!");
                            stage = 4;
                            break;
                        case 4:
                            quest.setStage(player, 50);
                            end();
                            break;
                    }
                    break;
                case 30:
                    switch (stage) {
                        case -1:
                            npc("So how are you doing getting a sword?");
                            stage = 0;
                            break;
                        case 0:
                            player("I have found an Imcando Dwarf named Thurgo!", "I have given him Redberry pie, I hope he will", "help me now.");
                            stage = 1;
                            break;
                        case 1:
                            end();
                            break;
                    }
                    break;
                case 20:
                case 10:
                    switch (stage) {
                        case -1:
                            npc("So how are you doing getting a sword?");
                            stage = 0;
                            break;
                        case 0:
                            player("I'm still looking for Imcando dwarves to help me...");
                            stage = 1;
                            break;
                        case 1:
                            npc("Please try and find them quickly... I am scared Sir", "Vyvin will find out!");
                            stage = 2;
                            break;
                        case 2:
                            end();
                            break;
                        case 166:
                            end();
                            break;
                    }
                    break;
                case 0:
                    switch (stage) {
                        case -1:
                            npc("Hello. I am the squire to Sir Vyvin.");
                            stage++;
                            break;
                        case 0:
                            options("And how is life as a squire?", "Wouldn't you prefer to be a squire for me?");
                            stage = 1;
                            break;
                        case 1:
                            switch (buttonId) {
                                case 1:
                                    player("And how is life as a squire?");
                                    stage = 10;
                                    break;
                                case 2:
                                    player("Wouldn't you prefer to be a squire for me?");
                                    stage = 20;
                                    break;
                            }
                            break;
                        case 10:
                            npc("Well, Sir Vyvin is a good guy to work for, however,", "I'm in a spot of trouble today. I've gone and lost Sir", "Vyvin's sword!");
                            stage = 11;
                            break;
                        case 11:
                            options("Do you know where you lost it?", "I can make a new sword if you like...", "Is he angry?");
                            stage = 12;
                            break;
                        case 12:
                            switch (buttonId) {
                                case 1:
                                    player("Do you know where you lost it?");
                                    stage = 100;
                                    break;
                                case 2:
                                    player("I can make a new sword if you like...");
                                    stage = 120;
                                    break;
                                case 3:
                                    player("Is he angry?");
                                    stage = 130;
                                    break;
                            }
                            break;
                        case 20:
                            npc("No, sorry, I'm loyal to Sir Vyvin.");
                            stage = 999;
                            break;
                        case 100:
                            npc("Well now, if I knew THAT it wouldn't be lost, now", "would it?");
                            stage = 999;
                            break;
                        case 120:
                            npc("Thanks for the offer. I'd be surprised if you could", "though.");
                            stage = 121;
                            break;
                        case 121:
                            npc("The thing is, this sword is a family heirloom. It has been", "passed down through Vyvin's family for five", "generations! It was originally made by the Imcando", "dwarves, who were");
                            stage = 122;
                            break;
                        case 122:
                            npc("a particularly skilled tribe of dwarven smiths. I doubt", "anyone could make it in the style they do.");
                            stage = 123;
                            break;
                        case 123:
                            options("So would these dwarves make another one?", "Well I hope you find it soon.");
                            stage = 134;
                            break;
                        case 134:
                            switch (buttonId) {
                                case 1:
                                    player("So would these dwarves make another one?");
                                    stage = 160;
                                    break;
                                case 2:
                                    player("Well I hope you find it soon.");
                                    stage = 170;
                                    break;
                            }
                            break;
                        case 160:
                            npc("I'm not a hundred percent sure the Imcando tribe", "exists anymore. I should think Reldo, the palace", "librarian in Varrock, will know; he has done a lot of", "research on the races of " + GameWorld.getSettings().getName() + ".");
                            stage = 161;
                            break;
                        case 161:
                            npc("I don't suppose you could try and track down the", "Imcando dwarves for me? I've got so much work to", "do...");
                            stage = 162;
                            break;
                        case 162:
                            options("Ok, I'll give it a go.", "No, I've got lots of mining work to do.");
                            stage = 163;
                            break;
                        case 163:
                            switch (buttonId) {
                                case 1:
                                    player("Ok, I'll give it a go.");
                                    stage = 165;
                                    break;
                                case 2:
                                    player("No, I've got lots of mining work to do.");
                                    stage = 164;
                                    break;
                            }
                            break;
                        case 164:
                            npc("Oh man... I'm in such trouble...");
                            stage = 999;
                            break;
                        case 165:
                            npc("Thank you very much! As I say, the best place to start", "should be with Reldo...");
                            stage = 999;
                            quest.setStage(player, 10);
                            player.getQuestRepository().syncronizeTab(player);
                            break;
                        case 170:
                            npc("Yes, me too. I'm not looking forward to telling Vyvin", "I've lost it. He's going to want it for the parade next", "week as well.");
                            stage = 999;
                            break;
                        case 130:
                            npc("He doesn't know yet. I was hoping I could think of", "something to do before he does find out, But I find", "myself at a loss.");
                            stage = 999;
                            break;
                        case 999:
                            end();
                            break;
                    }
                    break;
            }
        }
        return true;
    }

    @Override
    public int[] getIds() {
        return new int[]{606};
    }
}
