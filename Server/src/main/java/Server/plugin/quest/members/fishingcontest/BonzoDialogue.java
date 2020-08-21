package plugin.quest.members.fishingcontest;

import core.game.node.entity.player.link.quest.QuestRepository;
import core.game.node.item.Item;
import core.plugin.InitializablePlugin;
import core.game.node.entity.player.Player;
import plugin.activity.ActivityManager;
import plugin.dialogue.DialoguePlugin;


@InitializablePlugin
public final class BonzoDialogue extends DialoguePlugin {

    /**
     * Constructs a new {@code BonzoDialogue} {@code Object}.
     */
    public BonzoDialogue() {
        /**
         * empty.
         */
    }

    /**
     * Constructs a new {@code BonzoDialogue} {@code Object}.
     * @param player
     */
    public BonzoDialogue(Player player) {
        super(player);
    }

    @Override
    public boolean open(Object... args) {
        if(args.length < 2) {
            if(player.getInventory().containsItem(FishingContest.FISHING_ROD)) {
                npc("Roll up, roll up! Enter the great Hemenster", "Fishing Contest! Only 5gp entrance fee!");
                stage = 0;
            } else {
                npc("Sorry, lad, but you need a fishing","rod to compete.");
                stage = 100;
            }
        } else {
            npc("Ok folks, time's up! Let's see who caught","the biggest fish!");
            stage = 1000;
        }
        return true;
    }

    @Override
    public boolean handle(int interfaceId, int buttonId) {
        switch (stage) {
            case 0:
                options("I'll enter the competition please.", "No thanks, I'll just watch the fun.");
                stage++;
                break;
            case 1:
                switch (buttonId) {
                    case 1:
                        player("I'll enter the competition please.");
                        if (player.getAttribute("fishing_competition:garlic-stuffed", false)) {
                            stage = 50;
                        } else {
                            stage = 10;
                        }
                        break;
                    case 2:
                        player("No thanks, I'll just watch the fun.");
                        stage = 100;
                        break;
                }
                break;
            case 10:
                npc("Marvelous!");
                stage++;
                break;
            case 11:
                if (player.getInventory().remove(new Item(995, 5))) {
                    stage = 20;
                    player.getDialogueInterpreter().sendDialogue("You pay Bonzo 5 coins");
                    break;
                } else {
                    player("I don't have the 5gp though...");
                    stage++;
                    break;
                }
            case 12:
                npc("No pay, no play.");
                stage = 100;
                break;
            case 20:
                npc("Ok, we've got all the fishermen! It's time", "to roll! Ok, nearly everyone is in their", "place already. You fish in the spot by the");
                stage++;
                break;
            case 21:
                npc("willow tree, and the Sinister Stranger, you fish by the pipes.");
                stage++;
                break;
            case 22:
                player.getDialogueInterpreter().sendDialogue("Your fishing competition spot is by the willow tree.");
                stage++;
                break;
            case 23:
                ActivityManager.start(player, "Fishing Contest Cutscene", false);
                end();
                break;
            case 100:
                end();
                break;
            case 1000:
                if (player.getInventory().containsItem(FishingContest.RAW_GIANT_CARP)) {
                    player("I have a fish.");
                    stage++;
                } else {
                    npc("And our winner is... the stranger who", "was fishing over by the pipes!");
                    stage = 100;
                }
                break;
            case 1001:
                player.getDialogueInterpreter().sendDialogue("You hand over your fish.");
                stage++;
                break;
            case 1002:
                npc("We have a new winner! The", "heroic-looking person who was fishing", "by the pipes has caught the biggest carp I've", "seen since Grandpa Jack used to compete!");
                stage++;
                break;
            case 1003:
                player.getDialogueInterpreter().sendDialogue("You are given the Hemenester fishing trophy!");
                player.getInventory().add(FishingContest.FISHING_TROPHY);
                player.getInventory().remove(FishingContest.RAW_GIANT_CARP);
                player.getQuestRepository().setStage(QuestRepository.getQuests().get("Fishing Contest"),20);
                stage = 100;
                break;
        }
        return true;
    }

    @Override
    public DialoguePlugin newInstance(Player player) {

        return new BonzoDialogue(player);
    }

    @Override
    public int[] getIds() {
        return new int[] { 225 };
    }
}
