package plugin.quest.members.merlinscrystal;

import core.game.content.ItemNames;
import core.game.node.entity.player.link.diary.DiaryType;
import core.game.node.item.Item;
import plugin.dialogue.DialoguePlugin;
import plugin.dialogue.FacialExpression;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.quest.Quest;

/**
 * Represents the dialogue plugin used for king arthur.
 *
 * @author 'Vexia
 * @author Splinter
 * @version 2.0
 */
public final class KingArthurDialogue extends DialoguePlugin {
    final static Item POISON_CHALICE = new Item(ItemNames.POISON_CHALICE_197);

    /**
     * Constructs a new {@code KingArthurDialogue} {@code Object}.
     */
    public KingArthurDialogue() {
        /**
         * empty.
         */
    }

    /**
     * Constructs a new {@code KingArthurDialogue} {@code Object}.
     *
     * @param player the player.
     */
    public KingArthurDialogue(Player player) {
        super(player);
    }

    @Override
    public DialoguePlugin newInstance(Player player) {
        return new KingArthurDialogue(player);
    }

    @Override
    public boolean open(Object... args) {
        npc = (NPC) args[0];
        if (args.length > 1
                && args[1] instanceof Item
                && ((Item) args[1]).equals(POISON_CHALICE)
                && player.getInventory().remove(POISON_CHALICE)) {
            if (!player.getAchievementDiaryManager().getDiary(DiaryType.SEERS_VILLAGE).isComplete(0, 3)) {
                player.getAchievementDiaryManager().getDiary(DiaryType.SEERS_VILLAGE).updateTask(player, 0, 3, true);
            }
            npc("You have chosen... poorly.");
            stage = 80;
            return true;
        } else {
            Quest quest = player.getQuestRepository().getQuest("Merlin's Crystal");
            if (quest.getStage(player) == 99) {
                player("I have freed Merlin from his crystal!");
                stage = 900;
                return true;
            }
            if (quest.getStage(player) == 100) {
                npc("Thank you for all of your help!");
                stage = 999;
                return true;
            }
            if (quest.getStage(player) <= 0) {
                npc("Welcome to my court. I am King Arthur.");
                stage = 0;
            } else {
                npc("If you're having any troubles talk to the other", "knights around the room.");
                stage = 9;
            }
        }
        return true;
    }

    @Override
    public boolean handle(int interfaceId, int buttonId) {
        Quest quest = player.getQuestRepository().getQuest("Merlin's Crystal");
        switch (stage) {
            case 900:
                end();
                quest.finish(player);
                break;
            case 999:
                end();
                break;
            case 0:
                player("I want to become a knight of the round table!");
                stage = 1;
                break;
            case 1:
                npc("Well, in that case I think you need to go on a quest to", "prove yourself worthy.");
                stage = 2;
                break;
            case 2:
                npc("My knights all appreciate a good quest.");
                stage = 3;
                break;
            case 3:
                interpreter.sendDialogues(npc, FacialExpression.DISGUSTED, "Unfortunately, our current quest is to rescue Merlin.");
                stage = 4;
                break;
            case 4:
                interpreter.sendDialogues(npc, FacialExpression.THINKING, "Back in England, he got himself trapped in some sort of",
                        "magical Crystal. We've moved him from the cave we",
                        "found him in and now he's upstairs in his tower.");
                stage = 5;
                break;
            case 5:
                player("I will see what I can do then.");
                quest.start(player);
                stage = 6;
                break;
            case 6:
                npc("Talk to my knights if you need any help.");
                stage = 999;
                break;
            case 80:
                player("Excuse me?");
                stage++;
                break;
            case 81:
                npc("Sorry, I meant to say 'thank you.' Most refreshing.");
                stage++;
                break;
            case 82:
                player("Are you sure that stuff is safe to drink?");
                stage++;
                break;
            case 83:
                npc("Oh yes, Stankers' creations may be dangerous for those",
                        "with weak constitutions, but, personally, I find",
                        "them rather invigorating.");
                stage = 999;
                break;
        }
        return true;
    }

    @Override
    public int[] getIds() {
        return new int[]{251};
    }
}