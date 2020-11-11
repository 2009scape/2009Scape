package plugin.dialogue;

import core.game.content.ItemNames;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.diary.DiaryType;
import core.game.node.item.Item;
import core.plugin.InitializablePlugin;
import core.tools.RandomFunction;

/**
 * Handles the man dialogues.
 *
 * @author 'Vexia
 */
@InitializablePlugin
public class ManDialoguePlugin extends DialoguePlugin {
    private static final Item CIDER = new Item(ItemNames.CIDER_5763);

    /**
     * The NPC ids that use this dialogue plugin.
     */
    private static final int[] NPC_IDS = {1, 2, 3, 4, 5, 6, 16, 24, 25, 170, 351, 352, 353, 354, 359, 360, 361, 362, 363, 663, 726, 727, 728, 729, 730, 1086, 2675, 2776, 3224, 3225, 3227, 5923, 5924,};

    public ManDialoguePlugin() {
    }

    public ManDialoguePlugin(Player player) {
        super(player);
    }

    @Override
    public DialoguePlugin newInstance(Player player) {
        return new ManDialoguePlugin(player);
    }

    @Override
    public boolean open(Object... args) {
        npc = (NPC) args[0];
        if (npc == null)
            return false;
        if (args.length > 1
                && args[1] instanceof Item
                && ((Item) args[1]).equals(CIDER)
                && player.getInventory().remove(CIDER)) {

            // Seers achievement diary
            if (!player.getAchievementDiaryManager().getDiary(DiaryType.SEERS_VILLAGE).isComplete(0, 6)) {
                if (player.getAttribute("diary:seers:pub-cider", 0) >= 4) {
                    player.setAttribute("/save:diary:seers:pub-cider", 5);
                    player.getAchievementDiaryManager().getDiary(DiaryType.SEERS_VILLAGE).updateTask(player, 0, 6, true);
                } else {
                    player.setAttribute("/save:diary:seers:pub-cider", player.getAttribute("diary:seers:pub-cider", 0) + 1);
                }
            }

            npc("Ah, a glass of cider, that's very generous of you. I", "don't mind if I do. Thanks!");
            stage = 999;
            return true;
        }
        interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Hello, how's it going?");
        stage = RandomFunction.random(0, 5);
        if (stage == 1) {
            stage = 0;
        }
        return true;
    }

    @Override
    public boolean handle(int interfaceId, int buttonId) {
        switch (stage) {
            case 0:
                interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "I'm very well thank you.");
                stage = 999;
                break;
            case 999:
                end();
                break;
            case 2:
                interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Who are you?");
                stage = 20;
                break;
            case 3:
                interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "I'm fine, how are you?");
                stage = 30;
                break;
            case 4:
                interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "No, I don't want to buy anything!");
                stage = 999;
                break;
            case 5:
                interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "I think we need a new king. The one we've got isn't", "very good.");
                stage = 999;
                break;
            case 20:
                interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "I'm a bold adventurer.");
                stage = 21;
                break;
            case 21:
                interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Ah, a very noble profession.");
                stage = 999;
                break;
            case 30:
                interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Very well thank you.");
                stage = 999;
                break;
        }
        return true;
    }

    @Override
    public int[] getIds() {
        return NPC_IDS;
    }
}
