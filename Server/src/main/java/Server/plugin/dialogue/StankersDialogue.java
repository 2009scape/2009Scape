package plugin.dialogue;

import core.game.content.ItemNames;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.diary.AchievementDiary;
import core.game.node.entity.player.link.diary.DiaryType;
import core.plugin.InitializablePlugin;
import core.game.node.item.Item;

/**
 * Handles the Galahad dialogue
 * @author afaroutdude
 * https://chisel.weirdgloop.org/dialogue/content/486329
 * https://www.youtube.com/watch?v=XPTkSDyKpWs
 */
@InitializablePlugin
public class StankersDialogue extends DialoguePlugin {

    private final Item POISON_CHALICE = new Item(ItemNames.POISON_CHALICE_197);

    public StankersDialogue() {}

    public StankersDialogue(Player player) {
        super(player);
    }

    @Override
    public DialoguePlugin newInstance(Player player) {
        return new StankersDialogue(player);
    }

    @Override
    public boolean open(Object... args) {
        npc = (NPC) args[0];
        npc("Hello bold adventurer.");
        stage = 1;
        return true;
    }

    @Override
    public boolean handle(int interfaceId, int buttonId) {
        AchievementDiary diary = player.getAchievementDiaryManager().getDiary(DiaryType.SEERS_VILLAGE);
        int level = 1;

        switch (stage) {
            case 999:
                end();
                break;
            case 1:
                options("Are these your trucks?", "Hello Stankers.","Talk about Achievement Diary.");
                stage++;
                break;
            case 2:
                switch (buttonId) {
                    case 1:
                        player("Are these your trucks?");
                        stage = 10;
                        break;
                    case 2:
                        player("Hello Mr. Stankers.");
                        stage = 20;
                        break;
                    case 3:
                        if (!diary.isStarted(level)) {
                            player("Do you have an Achievement Diary for me?");
                            stage = 100;
                            break;
                        }
                        else if (diary.isLevelRewarded(level) && !player.hasItem(diary.getType().getRewards()[level][0])) {
                            player("I seem to have lost my seers' headband...");
                            stage = 80;
                            break;
                        }
                        else if (diary.isLevelRewarded(level)) {
                            player("Can you remind me what my headband does?");
                            stage = 90;
                            break;
                        }
                        else if (diary.isComplete(level, true)) {
                            player("Hi. I've completed the Medium tasks in my Achievement", "Diary. Can I have a reward?");
                            stage = 200;
                            break;
                        }
                        else {
                            player("Hi! Can you help me out with the Achievement Diary", "tasks?");
                            stage = 101;
                            break;
                        }
                }
                break;
            case 10:
                npc("Yes, I use them to transport coal over the river.",
                        "I will let other people use them too, I'm a nice",
                        "person like that...");
                stage++;
                break;
            case 11:
                npc("Just put coal in a truck and I'll move it down to","my depot over the river.");
                stage = 999;
                break;
            case 20:
                npc("Would you like a poison chalice?");
                stage++;
                break;
            case 21:
                options("Yes please.","What's a poison chalice?","No thank you.");
                stage++;
                break;
            case 22:
                switch (buttonId) {
                    case 1:
                        player("Yes please.");
                        stage = 30;
                        break;
                    case 2:
                        player("What's a poison chalice?");
                        stage = 40;
                        break;
                    case 3:
                        player("No thank you.");
                        stage = 1;
                        break;
                }
                break;
            case 30:
                end();
                player.getInventory().add(POISON_CHALICE);
                player.sendMessage("Stankers hands you a glass of strangely coloured liquid...");
                break;
            case 40:
                npc("It's an exciting drink I've invented. I don't know",
                        "what it tastes like, I haven't tried it myself.");
                stage++;
                break;
            case 41:
                options("Yes please.","No thank you.");
                stage++;
                break;
            case 42:
                switch (buttonId) {
                    case 1:
                        player("Yes please.");
                        stage = 30;
                        break;
                    case 2:
                        player("No thank you.");
                        stage = 1;
                        break;
                }
                break;

            case 80:
                player.getInventory().add(diary.getType().getRewards(level)[0], player);
                npc("Here's your replacement. Please be more careful.");
                stage = 999;
                break;
            case 90:
                npc("Your headband will help you get experience when", "woodcutting maple trees, and an extra log or two when", "cutting normal trees. I've also told Geoff to increase");
                stage++;
            case 91:
                npc("your flax allowance in acknowledgement of your", "standing.");
                stage = 999;
                break;
            case 100:
                npc("I certainly do - we have a set of tasks spanning Seers'", "Village, Catherby, Hemenster and the Sinclair Mansion.", "Just complete the tasks listed in the Achievement Diary", "and they will be ticked off automatically.");
                stage = 999;
                break;
            case 101:
                npc("I'm afraid not. It is important that adventurers", "complete the tasks unaided. That way, only the truly", "worthy collect the spoils.");
                stage = 999;
                break;
            case 200:
                npc("Well done! Yes, I have a reward for you. I'll just", "anoint your headband with one of my mixtures. Oh and", "here's an old lamp I had lying around.");
                stage++;
                break;
            case 201:
                if (!player.hasItem(diary.getType().getRewards()[level][0])) {
                    npc("I need your headband to anoint it! Come back when", "you have it.");
                    stage = 999;
                } else {
                    diary.setLevelRewarded(level);
                    player.getInventory().add(diary.getType().getRewards()[level][1]);
                    sendDialogue("Stankers produces a chalice containing a vile-looking concoction that", "he pours all over your headband.");
                    stage++;
                }
                break;
            case 202:
                player("Erm, thank you... I guess.");
                stage++;
                break;
            case 203:
                npc("Don't worry, it may be a little sticky for a while, but", "now your headband will help you get experience when", "woodcutting maple trees, and an extra log or two when", "cutting normal trees. I'll also tell Geoff - erm - Flax to");
                stage++;
                break;
            case 204:
                npc("increase your flax allowance in acknowledgement of your", "standing.");
                stage=999;
                break;
        }
        return true;
    }

    @Override
    public int[] getIds() {
        return new int[] { 383 };
    }
}
