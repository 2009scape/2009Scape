package plugin.quest.miniquest.barcrawl;

import core.game.node.entity.player.Player;
import core.game.system.task.Pulse;
import core.game.world.GameWorld;
import core.plugin.InitializablePlugin;
import plugin.dialogue.DialogueInterpreter;
import plugin.dialogue.DialoguePlugin;

/**
 * The barcrawl diaogue plugin.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class BarcrawlDialogue extends DialoguePlugin {

    /**
     * The npc id being used.
     */
    private BarcrawlPlugin.BarcrawlType type;

    /**
     * The npc id.
     */
    private int npcId;

    /**
     * Constructs a new {@code BarcrawlDialogue} {@code Object}.
     * @param player the player.
     */
    public BarcrawlDialogue(final Player player) {
        super(player);
    }

    /**
     * Constructs a new {@code BarcrawlDialogue} {@code Object}.
     */
    public BarcrawlDialogue() {
        /**
         * empty.
         */
    }

    @Override
    public DialoguePlugin newInstance(Player player) {
        return new BarcrawlDialogue(player);
    }

    @Override
    public boolean open(Object... args) {
        npcId = (Integer) args[0];
        type = (BarcrawlPlugin.BarcrawlType) args[1];
        player("I'm doing Alfred Grimhand's Barcrawl.");
        return true;
    }

    @Override
    public boolean handle(int interfaceId, int buttonId) {
        switch (stage) {
            case 0:
                interpreter.sendDialogues(npcId, null, type.getDialogue()[0]);
                stage = type.getDialogue().length > 1 ? 1 : 2;
                break;
            case 1:
                interpreter.sendDialogues(npcId, null, type.getDialogue()[1]);
                stage++;
                break;
            case 2:
                end();
                if (!player.getInventory().containsItem(type.getCoins())) {
                    break;
                }
                type.message(player, true);
                player.getInventory().remove(type.getCoins());
                player.getBarcrawlManager().complete(type.ordinal());
                player.lock(6);
                GameWorld.Pulser.submit(new Pulse(6, player) {
                    @Override
                    public boolean pulse() {
                        type.message(player, false);
                        type.effect(player);
                        return true;
                    }
                });
                break;
        }
        return true;
    }

    @Override
    public int[] getIds() {
        return new int[] { DialogueInterpreter.getDialogueKey("barcrawl dialogue") };
    }

}