package plugin.interaction.item.withnpc;

import core.game.content.ItemNames;
import core.game.interaction.NodeUsageEvent;
import core.game.interaction.UseWithHandler;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.game.node.item.Item;
import core.game.world.map.Location;
import core.game.world.map.zone.ZoneBorders;
import core.plugin.InitializablePlugin;
import core.plugin.Plugin;

/**
 * Represents the plugin used to "poison" King Arthur.
 * @author afaroutdude
 */
@InitializablePlugin
public final class ForestersArmsCiderPlugin extends UseWithHandler {
    private static final Item CIDER = new Item(ItemNames.CIDER_5763);

    /**
     * Constructs a new {@code LadyKeliRopePlugin} {@code Object}.
     */
    public ForestersArmsCiderPlugin() {
        super(ItemNames.CIDER_5763);
    }

    @Override
    public Plugin<Object> newInstance(Object arg) throws Throwable {
        int[] ids = {1,2,3,4,5};
        for (int id : ids) {
            addHandler(id, NPC_TYPE, this);
        }
        return this;
    }

    @Override
    public boolean handle(NodeUsageEvent event) {
        final Player player = event.getPlayer();
        if (!new ZoneBorders(2689, 3488, 2700, 3498, 0).insideBorder(player)) {
            return true;
        }
        final NPC npc = event.getUsedWith() instanceof NPC ? event.getUsedWith().asNpc() : null;
        if (npc != null) {
            player.getDialogueInterpreter().open(npc.getId(), npc, CIDER);
        }
        return true;
    }

}
