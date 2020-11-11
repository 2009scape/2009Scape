package plugin.interaction.item.withnpc;

import core.game.content.ItemNames;
import core.game.interaction.NodeUsageEvent;
import core.game.interaction.UseWithHandler;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.game.node.item.Item;
import core.plugin.InitializablePlugin;
import core.plugin.Plugin;

/**
 * Represents the plugin used to "poison" King Arthur.
 * @author afaroutdude
 */
@InitializablePlugin
public final class KingArthurPoisonChalicePlugin extends UseWithHandler {
    private static final Item POISON_CHALICE = new Item(ItemNames.POISON_CHALICE_197);

    /**
     * Constructs a new {@code LadyKeliRopePlugin} {@code Object}.
     */
    public KingArthurPoisonChalicePlugin() {
        super(POISON_CHALICE.getId());
    }

    @Override
    public Plugin<Object> newInstance(Object arg) throws Throwable {
        addHandler(251, NPC_TYPE, this);
        return new KingArthurPoisonChalicePlugin();
    }

    @Override
    public boolean handle(NodeUsageEvent event) {
        final Player player = event.getPlayer();
        final NPC npc = event.getUsedWith() instanceof NPC ? event.getUsedWith().asNpc() : null;
        if (npc != null) {
            player.getDialogueInterpreter().open(npc.getId(), npc, POISON_CHALICE);
        }
        return true;
    }

}
