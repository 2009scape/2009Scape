package plugin.dialogue;

import core.game.content.ItemNames;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.diary.DiaryType;
import core.plugin.InitializablePlugin;
import core.game.node.item.Item;

/**
 * Handles the Galahad dialogue
 * @author afaroutdude
 */
@InitializablePlugin
public class GalahadDialogue extends DialoguePlugin {

    /**
     * Represnets the bow item.
     */
    private final Item TEA = new Item(ItemNames.CUP_OF_TEA_712);

    public GalahadDialogue() {}

    public GalahadDialogue(Player player) {
        super(player);
    }

    @Override
    public DialoguePlugin newInstance(Player player) {
        return new GalahadDialogue(player);
    }

    @Override
    public boolean open(Object... args) {
        npc = (NPC) args[0];
        npc("Welcome to my home. It's rare for me to have guests!", "Would you like a cup of tea? I'll just put the kettle on.");
        stage = 2;
        return true;
    }

    @Override
    public boolean handle(int interfaceId, int buttonId) {
        switch (stage) {
            case 999:
                end();
                break;
            case 2:
                sendDialogue("Brother Galahad hangs a kettle over the fire.");
                stage++;
                break;
            case 3:
                // This stage seems to break off based on the quest Holy Grail
                // https://www.youtube.com/watch?v=XPTkSDyKpWs
                player("Do you get lonely out here on your own?");
                stage++;
                break;
            case 4:
                npc("Sometimes I do, yes. Still not many people to share my",
                        "solidarity with, as most of the religious men around here",
                        "are worshippers of Saradomin. Half a moment, your cup",
                        "of tea is ready.");
                stage++;
                break;
            case 5:
                sendDialogue("Brother Galahad gives you a cup of tea.");
                player.getInventory().add(TEA);
                player.getAchievementDiaryManager().finishTask(player, DiaryType.SEERS_VILLAGE, 0, 2);
                stage = 999;
                break;
        }
        return true;
    }

    @Override
    public int[] getIds() {
        return new int[] { 218 };
    }
}
