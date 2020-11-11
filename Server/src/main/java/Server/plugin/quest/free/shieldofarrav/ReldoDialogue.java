package plugin.quest.free.shieldofarrav;

import plugin.dialogue.DialoguePlugin;
import plugin.dialogue.FacialExpression;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.diary.AchievementDiary;
import core.game.node.entity.player.link.diary.DiaryType;
import core.game.node.entity.player.link.quest.Quest;
import core.game.node.item.GroundItemManager;
import core.game.node.item.Item;

/**
 * Represents the dialogue to handle reldo.
 *
 * @author 'Vexia
 * @dtae 27/12/2013
 */
public class ReldoDialogue extends DialoguePlugin {

    /**
     * Represents the knight sword quest instance.
     */
    private Quest knightSword;

    /**
     * Represents the shield of arrav quest instance.
     */
    private Quest shieldArrav;

    /**
     * The achievement diary.
     */
    private AchievementDiary diary;

    /**
     * If w'ere chatting about our diary.
     */
    private boolean isDiary;

    private final int level = 1;

    /**
     * Constructs a new {@code ReldoDialogue} {@code Object}.
     */
    public ReldoDialogue() {
        /**
         * empty.
         */
    }

    /**
     * Constructs a new {@code ReldoDialogue} {@code Object}.
     *
     * @param player the player.
     */
    public ReldoDialogue(Player player) {
        super(player);
    }

    @Override
    public boolean open(Object... args) {
        npc = (NPC) args[0];
        knightSword = player.getQuestRepository().getQuest("The Knight's Sword");
        shieldArrav = player.getQuestRepository().getQuest("Shield of Arrav");
        if (args.length == 2 && ((String) args[1]).equals("book")) {
            player("Aha! 'The Shield of Arrav'! Exactly what I was looking", "for.");
            stage = 3;
            return true;
        }
        options("Hello stranger.", "I have a question about my Achievement Diary.");
        stage = -1;
        return true;
    }

    @Override
    public boolean handle(int interfaceId, int buttonId) {
        if (isDiary) {
        	// https://www.youtube.com/watch?v=2q-29WxUl1U
            switch (stage) {
                case 999:
                    end();
                    break;
                case -1:
                	// when available, "Can I change my Varrock teleport point?" is option 2
                    options("What is the Achievement Diary?", "What are the rewards?", "How do I claim the rewards?", "See you later.");
                    stage++;
                    break;
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
                            player("See you later.");
                            stage = 999;
                            break;
                    }
                    break;
                case 440:
                    npc("Really? How extraordinary! Well, that certainly is a ", "worthy achievement indeed.");
                    stage++;
                    break;
                case 441:
                    player("Thanks. Was there a reward?");
                    stage++;
                    break;
                case 442:
                    npc("A reward? Well, yes, I suppose I can help you there.");
                    stage = 444;
                    break;
                case 444:
					diary.setLevelRewarded(level);
					for (Item i : diary.getType().getRewards(level)) {
						player.getInventory().add(i, player);
					}
                    sendDialogue("Reldo takes the Varrock armour and attaches some more plate metal", "to it, filling it out to look like a proper suit of armour. He etches", "some words into the armour, which glows slightly before fading.");
                    stage++;
                    break;
                case 445:
                    npc("I have enhanced your body armour with a spell so that,", "when using the Edgeville furnace, you'll have the chance", "of smelting an extra bar up to mithril. It will also give", "you the chance of an extra ore when Mining up to");
                    stage++;
                    break;
                case 446:
                    npc("mithril as well.");
                    stage++;
                    break;
                case 447:
                    npc("Smithing can take time, so I have placed a small speed", "enhancement on the armour. When you smith with this armour on, there is a chance that you will be able to", "smith faster each time and thus decrease the time it");
                    stage++;
                    break;
                case 448:
                    npc("takes to produce forged items.");
                    stage++;
                    break;
                case 449:
                    npc("I can also change your Varrock Teleport spell so that it", "takes you to the Grand Exchange, if you'd find that more convenient.");
                    stage++;
                    break;
                case 450:
                    npc("As an extra reward I have also given you a magical lamp to help with your skills.");
                    stage++;
                    break;
                case 451:
                    player("Wow, thanks! What if I lose the armour?");
                    stage++;
                    break;
                case 452:
                    npc("Oh, that's not a problem. You can come back to see me", "and I'll get you another set.");
                    stage = -1;
                    break;

                case 460:
                    player.getInventory().add(diary.getType().getRewards(level)[0], player);
                    npc("You better be more careful this time.");
                    stage = 999;
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
                    stage = 999;
                    break;
                case 420:
                    npc("Well, there's three different levels of Varrock Armour,", "which match up with the three levels of difficulty. Each", "has the same rewards as the previous level, and an", "additional one too... but I won't spoil your surprise.");
                    stage++;
                    break;
                case 421:
                    npc("Rest assured, the people of Varrock are happy to see", "you visiting the land.");
                    stage = 999;
                    break;
                case 430:
                    npc("Just complete the tasks so they're all ticked off, then", "you can claim your reward. Most of them are", "straightforward; you might find some require quests to", "be started, if not finished.");
                    stage++;
                    break;
                case 431:
                    npc("To claim the different Varrock Armour, speak to Vannaka", "Rat Burgis, and myself.");
                    stage = 999;
                    break;
            }
            return true;
        }
        if (stage == -1) {
            switch (buttonId) {
                case 1:
                    npc("Hello stranger.");
                    stage = 0;
                    break;
                case 2:
                	player("I have a question about my Achievement Diary.");
                	stage = 900;
                	break;
            }
            return true;
        }
        if (stage == 900) {
        	sendDiaryDialogue();
        	return true;
		}
        if (knightSword.getStage(player) == 10) {
            switch (stage) {
                default:
                    handleKnightSword(buttonId);
                    handleQuest(buttonId);
                    break;
            }
            return true;
        }
        switch (shieldArrav.getStage(player)) {
            case 20:
                switch (stage) {
                    case 0:
                        player("Ok. I've read the book. Do you know where I can find", "the Phoenix Gang?");
                        stage = 1;
                        break;
                    case 1:
                        npc("No, I don't. I think I know someone who might", "however.");
                        stage = 2;
                        break;
                    case 2:
                        npc("If I were you I would talk to Baraeck, the fur trader in", "the market place. I've heard he has connections with the", "Phoenix Gang.");
                        stage = 3;
                        break;
                    case 3:
                        player("Thanks, I'll try that!");
                        stage = 4;
                        break;
                    case 4:
                        shieldArrav.setStage(player, 30);
                        end();
                        break;
                }
                break;
            case 10:
                switch (stage) {
                    case 0:
                        player("About that book... where is it again?");
                        stage = 1;
                        break;
                    case 1:
                        npc("I'm not sure where it is exactly... but I'm sure it's", "around here somewhere.");
                        stage = 2;
                        break;
                    case 2:
                        end();
                        break;
                    case 14:
                        end();
                        break;
                    case 3:
                        interpreter.sendDialogue("You take the book from the bookcase.");
                        stage = 4;
                        break;
                    case 4:
                        if (!player.getInventory().add(ShieldofArrav.BOOK)) {
                            GroundItemManager.create(ShieldofArrav.BOOK, player);
                        }
                        end();
                        break;
                }
                break;
            case 0:
                switch (stage) {
                    case 0:
                        options("I'm in search  of a quest.", "Do you have anything to trade?", "What do you do?");
                        stage = 1;
                        break;
                    case 1:
                        switch (buttonId) {
                            case 1:
                                player("I'm in search of a quest.");
                                stage = 10;
                                break;
                            case 2:
                                player("Do you have anything to trade?");
                                stage = 20;
                                break;
                            case 3:
                                player("What do you do?");
                                stage = 30;
                                break;
                        }
                        break;
                    case 30:
                        npc("I am the palace librarian.");
                        stage = 31;
                        break;
                    case 31:
                        player("Ah. That's why you're in the library then.");
                        stage = 32;
                        break;
                    case 32:
                        npc("Yes.");
                        stage = 33;
                        break;
                    case 33:
                        end();
                        break;
                    case 20:
                        npc("Only knowledge.");
                        stage = 21;
                        break;
                    case 21:
                        player("How much do you want for that then?");
                        stage = 22;
                        break;
                    case 22:
                        npc("No, sorry, that was just my little joke. I'm not the", "trading type.");
                        stage = 23;
                        break;
                    case 23:
                        player("Ah well.");
                        stage = 24;
                        break;
                    case 24:
                        end();
                        break;
                    default:
                        handleQuest(buttonId);
                        break;
                }
                break;
            default:
                switch (stage) {
                    case 0:
                        options("Do you have anything to trade?", "What do you do?");
                        stage = 1;
                        break;
                    default:
                        regular(buttonId);
                        break;
                }
                break;
        }
        return true;
    }

    /**
     * Method used to handle the quest.
     */
    public final void handleQuest(int buttonId) {
        switch (stage) {
            case 10:
                npc("Hmmm. I don't... believe there are any here...");
                stage = 11;
                break;
            case 11:
                npc("Let me think actually...");
                stage = 12;
                break;
            case 12:
                npc("Ah yes. I know. If you look in a book called 'The Shield", "of Arrav', you'll find a quest in there.");
                stage = 13;
                break;
            case 13:
                shieldArrav.start(player);
                npc("I'm not not sure where the book is mind you... but I'm", "sure it's around here somewhere.");
                stage = 14;
                break;
            case 14:
                player("Thank you.");
                stage = 15;
                break;
            case 15:
                end();
                break;
        }
    }

    /**
     * Method used to handle his regular chat.
     *
     * @param buttonId the buttonId.
     */
    public void regular(int buttonId) {
        switch (stage) {
            case 1:
                switch (buttonId) {
                    case 1:
                        player("Do you have anything to trade?");
                        stage = 20;
                        break;
                    case 2:
                        player("What do you do?");
                        stage = 30;
                        break;
                }
                break;
            case 30:
                npc("I am the palace librarian.");
                stage = 31;
                break;
            case 31:
                player("Ah. That's why you're in the library then.");
                stage = 32;
                break;
            case 32:
                npc("Yes.");
                stage = 33;
                break;
            case 33:
                end();
                break;
            case 20:
                npc("Only knowledge.");
                stage = 21;
                break;
            case 21:
                player("How much do you want for that then?");
                stage = 22;
                break;
            case 22:
                npc("No, sorry, that was just my little joke. I'm not the", "trading type.");
                stage = 23;
                break;
            case 23:
                player("Ah well.");
                stage = 24;
                break;
            case 24:
                end();
                break;
        }
    }

    /**
     * Handles the knight sword dial.
     *
     * @param buttonId the button Id.
     */
    public void handleKnightSword(int buttonId) {
        switch (stage) {
            case 0:
                options("Do you have anything to trade?", "What do you do?", "What do you know about Imcando dwarves?");
                stage = 1;
                break;
            case 1:
                switch (buttonId) {
                    case 1:
                        player("Do you have anything to trade?");
                        stage = 20;
                        break;
                    case 2:
                        player("What do you do?");
                        stage = 30;
                        break;
                    case 3:
                        player("What do you know about Imcando dwarves?");
                        stage = 40;
                        break;
                }
                break;
            case 30:
                npc("I am the palace librarian.");
                stage = 31;
                break;
            case 31:
                player("Ah. That's why you're in the library then.");
                stage = 32;
                break;
            case 32:
                npc("Yes.");
                stage = 33;
                break;
            case 33:
                end();
                break;
            case 20:
                npc("Only knowledge.");
                stage = 21;
                break;
            case 21:
                player("How much do you want for that then?");
                stage = 22;
                break;
            case 22:
                npc("No, sorry, that was just my little joke. I'm not the", "trading type.");
                stage = 23;
                break;
            case 23:
                player("Ah well.");
                stage = 24;
                break;
            case 24:
                end();
                break;
            case 40:
                npc("The Imcando dwarves, you say?");
                stage = 41;
                break;
            case 41:
                npc("Ah yes... for many hundreds of years they were the", "world's most skilled smiths. They used secret smithing", "knowledge passed down from generation to generation.");
                stage = 42;
                break;
            case 42:
                npc("Unfortunately, about a century ago, the once thriving", "race was wiped out during the barbarian invasions of", "that time.");
                stage = 43;
                break;
            case 43:
                player("So are there any Imcando left at all?");
                stage = 44;
                break;
            case 44:
                npc("I believe a few of them survived, but with the bulk of", "their population destroyed their numbers have dwindled", "even further.");
                stage = 45;
                break;
            case 45:
                npc("I believe I remember a couple living in Asgarnia near", "the cliffs on the Asgarnian southern peninusla, but they", "DO tend to keep to themselves.");
                stage = 46;
                break;
            case 46:
                npc("They tend not to tell people that they're the", "descendants of the Imcando, which is why people think", "that the tribe has died out totally, but you may well", "have more luck talking to them if you bring them some");
                stage = 47;
                break;
            case 47:
                npc("redberry pie. They REALLY like redberry pie.");
                stage = 48;
                break;
            case 48:
                knightSword.setStage(player, 20);
                end();
                break;
        }
    }

    /**
     * Sends the diary dialogue.
     */
    private void sendDiaryDialogue() {
        isDiary = true;
        if (diary == null) {
            diary = player.getAchievementDiaryManager().getDiary(DiaryType.VARROCK);
        }
        if (diary.isComplete(level) && !diary.isLevelRewarded(level)) {
            player("I've finished all the medium tasks in my Varrock", "Achievement Diary.");
            stage = 440;
            return;
        }
        if (diary.isLevelRewarded(level) && diary.isComplete(level) && !player.hasItem(diary.getType().getRewards(level)[0])) {
            player("I've seemed to have lost my armour...");
            stage = 460;
            return;
        }
        options("What is the Achievement Diary?", "What are the rewards?", "How do I claim the rewards?", "See you later.");
        stage = 0;
    }

    @Override
    public DialoguePlugin newInstance(Player player) {
        return new ReldoDialogue(player);
    }

    @Override
    public int[] getIds() {
        return new int[]{2660, 2661};
    }
}