package plugin.skill.slayer;

import core.game.world.GameWorld;
import plugin.dialogue.DialoguePlugin;
import plugin.dialogue.FacialExpression;
import core.game.content.global.Skillcape;
import plugin.skill.Skills;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.diary.AchievementDiary;
import core.game.node.entity.player.link.diary.DiaryType;
import core.game.node.entity.player.link.quest.Quest;
import core.game.node.item.Item;
import core.plugin.InitializablePlugin;

/**
 * Represents the dialogue plugin used for a slayer master.
 *
 * @author Vexia
 */
@InitializablePlugin
public final class SlayerMasterDialogue extends DialoguePlugin {

    /**
     * The enchanted gem item.
     */
    private static final Item GEM = new Item(4155, 1);

    /**
     * The mithril axe item.
     */
    private static final Item MITHRIL_AXE = new Item(1355);

    /**
     * The holy symbol item.
     */
    private static final Item HOLY_SYMBOL = new Item(1718);

    /**
     * Represents the items to use.
     */
    private static final Item[] ITEMS = new Item[]{new Item(9813), new Item(9814)};

    /**
     * Represents the coins item.
     */
    private static final Item COINS = new Item(995, 99000);

    /**
     * Represents the master talking.
     */
    private Master master;

    /**
     * The quest instance.
     */
    private Quest quest;

    /**
     * The achievement diary.
     */
    private AchievementDiary diary;

    /**
     * If we're chatting about our diary.
     */
    private boolean isDiary;

    private final int level = 2;

    /**
     * Constructs a new {@code SlayerMasterDialogue} {@code Object}.
     */
    public SlayerMasterDialogue() {
        /**
         * empty.
         */
    }

    /**
     * Constructs a new {@code SlayerMasterDialogue} {@code Object}.
     *
     * @param player the player.
     */
    public SlayerMasterDialogue(Player player) {
        super(player);
    }

    @Override
    public DialoguePlugin newInstance(Player player) {
        return new SlayerMasterDialogue(player);
    }

    @Override
    public boolean open(Object... args) {
        if (args[0] instanceof NPC) {
            npc = (NPC) args[0];
        }
        master = Master.forId(args[0] instanceof NPC ? ((NPC) args[0]).getId() : (int) args[0]);
        quest = player.getQuestRepository().getQuest("Animal Magnetism");

        if (master == Master.DURADEL) {
            if (Skillcape.isMaster(player, Skills.SLAYER)) {
                options("Ask about Skillcape", "Something else");
                stage = 900;
                return true;
            }
        }
        interpreter.sendDialogues(master.getNpc(), getExpression(master), "'Ello, and what are you after, then?");
        if (master == Master.VANNAKA) {
            stage = -1;
        } else {
            stage = 0;
        }
        return true;
    }

    @Override
    public boolean handle(int interfaceId, int buttonId) {
        if (isDiary) {
            switch (stage) {
                case 999:
                    end();
                    break;
                case -1:
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
                    npc("I can imagine! I expect you'll be wanting a reward for", "your hard efforts, eh?");
                    stage++;
                    break;
                case 441:
                    player("Yes, please.");
                    stage++;
                    break;
                case 442:
                    diary.setLevelRewarded(level);
                    for (Item i : diary.getType().getRewards(level)) {
                        player.getInventory().add(i, player);
                    }
                    npc("I'm happy to say that you've done well, so I will reward", "you suitably for your work.");
                    stage++;
                    break;
                case 443:
                    player("Great! Thanks.");
                    stage++;
                    break;
                case 444:
                    sendDialogue("Vannaka takes the Varrock armour and carves some symbols into it.", "He waves his hands over the symbols and the armour appears to glow", "brilliantly.");
                    stage++;
                    break;
                case 450:
                    player.getInventory().add(diary.getType().getRewards(2)[0], player);
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
                    npc("To claim the different Varrock Armour, speak to Vannaka", "Rat Burgis, and myself.");
                    stage = 41;
                    break;
                case 50:
                    end();
                    break;
            }
            return true;
        }
        switch (stage) {
            case 999:
                end();
                break;
            case -1: // vannaka - has options for achievement diary
                if (!player.getSlayer().hasStarted()) {
                    options("Who are you?", "Do you have anything for trade?", "Er...nothing...", "I have a question about my Achievement Diary.");
                    stage = 1;
                } else {
                    options("I need another assignment.", "Do you have anything for trade?", "Er...nothing...", "I have a question about my Achievement Diary.");
                    stage = 690;
                }
                break;
            case 0: // not vannaka
				if (master == Master.TURAEL) { // only give option to talk about holy axe with Turael
					if (quest.getStage(player) == 30) {
						options("I need another assignment.", "Do you have anything for trade?", "Er...nothing...", "I'm here about a quest.");
						stage = 700;
						break;
					} else if (quest.getStage(player) == 31) {
						options("I need another assignment.", "Do you have anything for trade?", "Er...nothing...", "Hello, I'm here about those trees again.");
						stage = 700;
						break;
					}
				}
                if (!player.getSlayer().hasStarted()) {
                    options("Who are you?", "Do you have anything for trade?", "Er...nothing...");
                    stage = 1;
                } else {
                    options("I need another assignment.", "Do you have anything for trade?", "Er...nothing...");
                    stage = 700;
                }
                break;
            case 1:
                switch (buttonId) {
                    case 1:
                        player("Who are you?");
                        stage = 10;
                        break;
                    case 2:
                        player("Do you have anything for trade?");
                        stage = 20;
                        break;
                    case 3:
                        player("Er...nothing...");
                        stage = 999;
                        break;
                    case 4:
                        player("I have a question about my Achievement Diary.");
                        stage = 691;
                        break;
                }
                break;
            case 10:
                interpreter.sendDialogues(master.getNpc(), getExpression(master), "I'm " + (master.getNpc() == 8273 ? "the lowest level Slayer Master available." : "one of the elite Slayer Masters."),
                        master.getNpc() == 8273 ? "The other Slayer Masters are spread around the world." : "I can teach you about the ways of the Slayer.");
                stage = 11;
                break;
            case 11:
                options("What's a Slayer?", "Never heard of you...");
                stage = 12;
                break;
            case 12:
                switch (buttonId) {
                    case 1:
                        player("What's a Slayer?");
                        stage = 100;
                        break;
                    case 2:
                        player("Never heard of you...");
                        stage = 2000;
                        break;
                }
                break;
            case 20:
                interpreter.sendDialogues(master.getNpc(), getExpression(master), "I have a wide selection of Slayer equipment; take a look!");
                stage = 21;
                break;
            case 21:
                end();
                if (npc != null) {
                    npc.openShop(player);
                }
                break;
            case 100:
                interpreter.sendDialogues(master.getNpc(), getExpression(master), "Oh dear, what do they teach you in school?");
                stage = 101;
                break;
            case 101:
                player("Well....er...");
                stage = 102;
                break;
            case 102:
                interpreter.sendDialogues(master.getNpc(), getExpression(master), "I suppose I'll have to educate you, then. A Slayer is", "someone who is trained to fight specific creatures. They", "know those creatures' every weakenss and strength. As", "you can guess, it makes killing those creatures a lot");
                stage = 103;
                break;
            case 103:
                interpreter.sendDialogues(master.getNpc(), getExpression(master), "easier.");
                stage = 104;
                break;
            case 104:
                options("Wow, can you teach me?", "Sounds useless to me.");
                stage = 105;
                break;
            case 105:
                switch (buttonId) {
                    case 1:
                        player("Wow, can you teach me?");
                        stage = 500;
                        break;
                    case 2:
                        player("Sounds useless to me.");
                        stage = 1000;
                        break;
                }
                break;
            case 500:
                interpreter.sendDialogues(master.getNpc(), getExpression(master), "Hmmm, well, I'm not so sure...");
                stage = 501;
                break;
            case 501:
                player("Pleeeaasssse! I'll be your best friend!");
                stage = 502;
                break;
            case 502:
                if (!master.hasRequirements(player)) {
                    interpreter.sendDialogues(master.getNpc(), getExpression(master), "Sorry, but you're not strong enough to be taught by", "me.");
                    stage = 99;
                    break;
                }
                interpreter.sendDialogues(master.getNpc(), getExpression(master), "Oh, okay then; you twisted my arm. You'll have to", "train against specific groups of creatures.");
                stage = 503;
                break;
            case 503:
                player("Okay then, what's first?");
                stage = 504;
                break;
            case 504:
                if (player.getInventory().freeSlots() != 0) {
                    player.getInventory().add(GEM);
                    player.getSlayer().generate(master);
                    interpreter.sendDialogues(master.getNpc(), getExpression(master), "We'll start you off hunting " + player.getSlayer().getTaskName() + "'s, you'll need to", "kill " + player.getSlayer().getAmount() + " of them.");
                    stage = 510;
                } else if (player.getInventory().freeSlots() == 0) {
                    player("Sorry, I don't have enough inventory space.");
                    stage = 999;
                }
                break;
            case 510:
                interpreter.sendDialogues(master.getNpc(), getExpression(master), "You'll also need this enchanted gem. It allows Slayer", "Masters like myself to contact you and update you on", "your progress. Don't worry if you lose it; you can buy", "another from any Slayer Master.");
                stage = 511;
                break;
            case 511:
                player("Okay, great!");
                stage = 999;
                break;
            case 1000:
                interpreter.sendDialogues(master.getNpc(), getExpression(master), "That's what you think..");
                stage = 999;
                break;
            case 2000:
                interpreter.sendDialogues(master.getNpc(), getExpression(master), "I am one of the greatest Slayer masters!");
                stage = 999;
                break;
            case 690:
                switch (buttonId) {
                    case 1:
                        player("I need another assignment.");
                        stage = 701;
                        break;
                    case 2:
                        player("Do you have anything for trade?");
                        stage = 20;
                        break;
                    case 3:
                        player("Er...nothing...");
                        stage = 30;
                        break;
                    case 4:
                        player("I have a question about my Achievement Diary.");
                        stage = 691;
                        break;
                }
                break;
            case 691:
                sendDiaryDialogue();
                break;
            case 700:
                switch (buttonId) {
                    case 1:
                        player("I need another assignment.");
                        stage = 701;
                        break;
                    case 2:
                        player("Do you have anything for trade?");
                        stage = 20;
                        break;
                    case 3:
                        player("Er...nothing...");
                        stage = 30;
                        break;
                    case 4:
                        if (quest.getStage(player) == 30) {
                            player("I'm here about a quest. Ava said she saw you hanging", "around the moving trees near Draynor Manor.");
                            stage = 8000;
                        } else if (quest.getStage(player) == 31) {
                            player("Hello, I'm here about those trees again.");
                            stage = 8006;
                        }
                        break;
                }
                break;
            case 8000:// avas.
                npc("Ahh, you came to the right man, odd things, those trees.", "What is it you are needing exactly?");
                stage++;
                break;
            case 8001:
                player("I think I need some of the wood from them, but my", "axe just bounced off the trunk.");
                stage++;
                break;
            case 8002:
                npc("Sounds like you need a blessed axe. No one really", "makes them, though these days.");
                stage++;
                break;
            case 8003:
                npc("If you can give me a mithril axe and a holy symbol of", "Saradomin I can let you have my axe. I'll make myself", "a new one when no one is pestering me for Slayer", "tasks.");
                stage++;
                break;
            case 8004:
                player("Okay, so I'll see whether I can spare an axe and a", "symbol. Thanks.");
                stage++;
                break;
            case 8005:
                quest.setStage(player, 31);
                end();
                break;
            case 8006:
                if (player.hasItem(new Item(10491))) {
                    npc("You already have an axe.");
                    stage = 999;
                    break;
                }
                npc("I can make an axe for you now, if you wish.", "Remember, it will be no use for normal wooducutting", "after I have added the silver edge.");
                stage++;
                break;
            case 8007:
                player("I'd love one, thanks.");
                stage++;
                break;
            case 8008:
                if (!player.getInventory().containsItem(MITHRIL_AXE)) {
                    npc("You'll need to hand over both a mithril axe and a holy", "symbol of Saradomin. You don't have an axe in your", "pack, so I'm not able to help.");
                    stage = 999;
                    break;
                }
                if (!player.getInventory().containsItem(HOLY_SYMBOL)) {
                    npc("You'll need to hand over both a mithril axe and a holy", "symbol of Saradomin. You don't have a holy symbol in", "your pack, so I'm not able to help.");
                    stage = 999;
                    break;
                }
                if (player.getInventory().remove(MITHRIL_AXE, HOLY_SYMBOL)) {
                    player.getInventory().add(new Item(10491));
                    npc("Here's a new axe; may it serve you well.");
                    stage = 999;
                }
                break;
            case 701:
                if (!master.hasRequirements(player)) {
                    interpreter.sendDialogues(master.getNpc(), getExpression(master), "Sorry, but you're not strong enough to be taught by", "me.");
                    stage = 99;
                    break;
                }
                if (!player.getSlayer().hasTask()) {
                    player.getSlayer().generate(master);
                    if (player.getSlayer().getTask() == Tasks.JAD) {
                        interpreter.sendDialogues(master.getNpc(), getExpression(master), "Excellent, you're doing great. Your new task is to", "defeat the almighty TzTok-Jad.");
                    } else {
                        interpreter.sendDialogues(master.getNpc(), getExpression(master), "Excellent, you're doing great. Your new task is to kill", "" + player.getSlayer().getAmount() + " " + player.getSlayer().getTaskName() + "s.");
                    }
                    stage = 844;
                    break;
                }
                if (Master.hasSameTask(master, player)) {
                    interpreter.sendDialogues(master.getNpc(), getExpression(master), "You're still hunting something. Come back when you've", "finished your task.");
                    stage = 999;
                } else {
                    player.getSlayer().setTaskCount(0);
                    player.getSlayer().generate(master);
                    if (player.getSlayer().getTask() == Tasks.JAD) {
                        interpreter.sendDialogues(master.getNpc(), getExpression(master), "Excellent, you're doing great. Your new task is to", "defeat the almighty TzTok-Jad.");
                    } else {
                        interpreter.sendDialogues(master.getNpc(), getExpression(master), "Excellent, you're doing great. Your new task is to kill", "" + player.getSlayer().getAmount() + " " + player.getSlayer().getTaskName() + "'s.");
                    }
                    stage = 844;
                }
                break;
            case 844:
                if (GameWorld.getSettings().getAllow_slayer_reroll()) {
                    options("Got any tips for me?", "Okay, great!", "I'd like to re-roll that task.");
                } else {
                    options("Got any tips for me?", "Okay, great!");
                }
                stage = 854;
                break;
            case 854:
                switch (buttonId) {
                    case 1:
                        interpreter.sendDialogues(master.getNpc(), getExpression(master), player.getSlayer().getTask().getTip());
                        stage = 860;
                        break;
                    case 2:
                        player("Okay, great!");
                        stage = 999;
                        break;
                    case 3:
                        player("I'd like to re-roll this task.");
                        player.getSlayer().clear();
                        stage = 701;
                }
                break;
            case 860:
                player("Great, thanks!");
                stage = 999;
                break;
            case 900:
                switch (buttonId) {
                    case 1:
                        player("Can I buy a Skillcape of Slayer?");
                        stage = 901;
                        break;
                    case 2:
                        interpreter.sendDialogues(master.getNpc(), FacialExpression.HALF_GUILTY, "'Ello, and what are you after, then?");
                        stage = 0;
                        break;
                }
                break;
            case 901:
                interpreter.sendDialogues(Master.DURADEL.getNpc(), FacialExpression.HALF_GUILTY, "Certainly! Right when you give me 99000 coins.");
                stage = 902;
                break;
            case 902:
                options("Okay, here you go.", "No, thanks.");
                stage = 903;
                break;
            case 903:
                switch (buttonId) {
                    case 1:
                        player("Okay, here you go.");
                        stage = 904;
                        break;
                    case 2:
                        end();
                        break;
                }
                break;
            case 904:
                if (Skillcape.purchase(player, Skills.SLAYER)) {
                    interpreter.sendDialogues(Master.DURADEL.getNpc(), FacialExpression.HALF_GUILTY, "There you go! Enjoy.");
                }
                stage = 999;
                break;
            case 906:
                switch (buttonId) {
                    case 1:
                        player("May I buy a Quest Point cape?");
                        stage = 907;
                        break;
                    case 2:
                        interpreter.sendDialogues(master.getNpc(), FacialExpression.HALF_GUILTY, "'Ello, and what are you after, then?");
                        stage = 0;
                        break;
                }
                break;
            case 907:
                npc("You bet, " + player.getUsername() + "! Right when you give me 99000 coins.");
                stage = 908;
                break;
            case 908:
                options("Okay, here you go.", "No, thanks.");
                stage = 909;
                break;
            case 909:
                switch (buttonId) {
                    case 1:
                        player("Okay, here you go.");
                        stage = 910;
                        break;
                    case 2:
                        end();
                        break;
                }
                break;
            case 910:
                if (player.getInventory().freeSlots() < 2) {
                    player("I don't seem to have enough inventory space.");
                    stage = 999;
                    return true;
                }
                if (!player.getInventory().containsItem(COINS)) {
                    player("I don't seem to have enough coins with", "me at this time.");
                    stage = 999;
                    return true;
                }
                if (player.getInventory().remove(COINS) && player.getInventory().add(ITEMS)) {
                    npc("Have fun with it.");
                    stage = 999;
                } else {
                    player("I don't seem to have enough coins with", "me at this time.");
                    stage = 999;
                }
                break;
        }
        return true;
    }

    /**
     * Checks which expression to use.
     *
     * @param master the master.
     * @return the expression.
     */
    private FacialExpression getExpression(Master master) {
        if (master == Master.CHAELDAR) {
            return FacialExpression.OLD_NORMAL;
        }
        return FacialExpression.HALF_GUILTY;
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
            player("I've completed all the hard tasks in my Varrock", "Achievement Diary and, let me tell you, it wasn't an", "easy job.");
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
    public int[] getIds() {
        return new int[]{70, 1598, 1596, 1597, 1599, 8275, 8273, 8274, 8649};
    }

}
