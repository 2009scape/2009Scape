package plugin.dialogue;

import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.diary.AchievementDiary;
import core.game.node.entity.player.link.diary.DiaryType;
import core.game.node.item.GroundItemManager;
import core.plugin.InitializablePlugin;
import core.game.node.item.Item;

/**
 * Handles the chemist dialogue.
 *
 * @author Vexia
 */
@InitializablePlugin
public final class ChemistDialogue extends DialoguePlugin {
    private boolean replacementReward = false;
    private AchievementDiary diary;
    private int level = 1;

    /**
     * Constructs a new {@code ChemistDialogue} {@code Object}.
     */
    public ChemistDialogue() {
        /**
         * empty.
         */
    }

    /**
     * Constructs a new {@code ChemistDialogue} {@code Object}.
     *
     * @param player the player.
     */
    public ChemistDialogue(Player player) {
        super(player);
    }

    @Override
    public DialoguePlugin newInstance(Player player) {
        return new ChemistDialogue(player);
    }

    @Override
    public boolean open(Object... args) {
        interpreter.sendOptions("Do you want to talk about lamps?", "Yes.", "No.", "No, I'm more interested in impling jars.", "Falador Achievement Diary");
        stage = 0;
        AchievementDiary diary = player.getAchievementDiaryManager().getDiary(DiaryType.FALADOR);
        replacementReward = diary.isLevelRewarded(level)
                && diary.isComplete(level, true)
                && !player.hasItem(diary.getType().getRewards(level)[0]);
        return true;
    }

    @Override
    public boolean handle(int interfaceId, int buttonId) {
        switch (stage) {
            case 0:
                switch (buttonId) {
                    case 1:
                        player("Hi, I need fuel for a lamp.");
                        stage = 10;
                        break;
                    case 2:
                        player("Hello.");
                        stage = 20;
                        break;
                    case 3:
                        player("I have a slightly odd question.");
                        stage = 30;
                        break;
                    case 4:
                        npc("How are you getting on with the Achievement Diary?");
                        stage = -1;
                        break;
                }
                break;
            case 10:
                npc("Hello there, the fuel you need is lamp oil, do you need", "help making it?");
                stage++;
                break;
            case 11:
                player("Yes, please.");
                stage++;
                break;
            case 12:
                npc("It's really quite simple. You use the small still in here.", "It's all set up, so there's no fiddling around with dials...");
                stage++;
                break;
            case 13:
                npc("Just put ordinary swamp tar in, and then use a lantern", "or lamp to get the oil out.");
                stage++;
                break;
            case 14:
                player("Thanks.");
                stage++;
                break;
            case 15:
                end();
                break;
            case 20:
                npc("Oh.. hello, how's it going?");
                stage++;
                break;
            case 21:
                player("Good thanks.");
                stage++;
                break;
            case 22:
                npc("Good to hear, sorry but I have a few things to do", "now.");
                stage++;
                break;
            case 23:
                player("Well I'd better let you get on then.");
                stage++;
                break;
            case 24:
                end();
                break;
            case 30:
                npc("Jolly good, the odder the better. I like oddities.");
                stage++;
                break;
            case 31:
                player("Do you know how I might distill a mix of anchovy oil", "and flowers so that it forms a layer on the inside of a", "butterfly jar?");
                stage++;
                break;
            case 32:
                npc("That is an odd question. I commend you for it. Why", "would you want to do that?");
                stage++;
                break;
            case 33:
                player("Apparently, if I can make a jar like this it will be useful", "for storing implings in.");
                stage++;
                break;
            case 34:
                npc("My lamp oil still may be able to do what you want. Use the", "oil and flower mix on the still.");
                stage++;
                break;
            case 35:
                npc("Once that's done. Use one of those butterfly jars to", "collect the distillate.");
                stage++;
                break;
            case 36:
                player("Thanks!");
                stage++;
                break;
            case 37:
                options("So how do you make anchovy oil?", "Do you have a sieve I can use?", "I'd better go and get the repellent.");
                stage++;
                break;
            case 38:
                switch (buttonId) {
                    case 1:
                        player("So, how do you make anchovy oil?");
                        stage = 50;
                        break;
                    case 2:
                        player("Do you have a sieve I can use?");
                        stage = 55;
                        break;
                    case 3:
                        player("I'd better go and get the repellent.");
                        stage = 51;
                        break;
                }
                break;
            case 50:
                npc("Anchovies are pretty oily fish. I'd have thought you", "could just grind them up and sieve out the bits. You'd", "probably want to remove any water first - Cooking", "should do that pretty well.");
                stage++;
                break;
            case 51:
                end();
                break;
            case 55:
                if (player.hasItem(new Item(6097))) {
                    npc("Errm, yes. But you already have one. Two sieves is a ", "bit exessive, don't you think?");
                    stage = 60;
                    break;
                }
                npc("Errm, yes. Here have this one. It's only been used for", "sieving dead rats out of sewer water.");
                stage++;
                break;
            case 56:
                player("Err, why? Actually, on second thoughts I don't want to", "know.");
                stage++;
                break;
            case 57:
                npc("Well, it would be ideally suited to your task.");
                stage++;
                break;
            case 58:
                player("Fair enough.");
                stage++;
                break;
            case 59:
                player.getInventory().add(new Item(6097), player);
                end();
                break;
            case 60:
                end();
                break;

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
            // https://www.youtube.com/watch?v=ZW9k1922Ggk
            case 105:
                if (!diary.isLevelRewarded(1)) {
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
                npc("This is the second stage of the Falador shield: a kite", "shield. It grants you all the benefits fo the buckler, but", "with increased Prayer restore, and Farming experience", "when using the patches near Falador.");
                stage = 151;
                break;
            case 151:
                npc("As before, Prayer restore can only be used once per", "day, but it now restores half of your Prayer points.");
                stage = 152;
                break;
            case 152:
                npc("The increased Farming experience is only available at", "the allotments, flower and herb patches found just north", "of Port Sarim.");
                stage = 153;
                break;
            case 153:
                npc("As well as all of these features, the shield is pretty", "handy in combat, and gives you a good Prayer boost.");
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
                npc("This is the second stage of the Falador shield: a kite", "shield. It grants you all the benefits fo the buckler, but", "with increased Prayer restore, and Farming experience", "when using the patches near Falador.");
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
                npc("As before, Prayer restore can only be used once per", "day, but it now restores half of your Prayer points.");
                stage = 205;
                break;
            case 205:
                npc("The increased Farming experience is only available at", "the allotments, flower and herb patches found just north", "of Port Sarim.");
                stage = 206;
                break;
            case 206:
                npc("As well as all of these features, the shield is pretty", "handy in combat, and gives you a good Prayer boost.");
                stage = 207;
                break;
            case 207:
                npc("I've even thrown in an old lamp I found. Try as I", "might, I can't fill it with fuel or get it to light.");
                stage = 208;
                break;
            case 208:
                interpreter.sendDialogues(player, FacialExpression.AMAZED, "Wow, thanks!");
                stage = 209;
                break;
            case 209:
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
        return true;
    }

    @Override
    public int[] getIds() {
        return new int[]{367};
    }

}
