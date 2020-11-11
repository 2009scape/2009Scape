package plugin.interaction.object;

import core.cache.def.impl.ObjectDefinition;
import core.game.content.ItemNames;
import core.game.interaction.NodeUsageEvent;
import core.game.interaction.OptionHandler;
import core.game.interaction.UseWithHandler;
import core.game.node.Node;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.diary.DiaryType;
import core.game.node.item.Item;
import core.game.node.object.GameObject;
import core.plugin.InitializablePlugin;
import core.plugin.Plugin;
import core.plugin.PluginManager;


/**
 * Plugin that enables getting flour from barrel in Sinclair mansion kitchen.
 * @author afaroutdude
 */
@InitializablePlugin
public final class SinclairFlourBarrelPlugin extends OptionHandler {
    private final static Item EMPTY_POT = new Item(ItemNames.POT_1931);
    private static final Item FLOUR = new Item(ItemNames.POT_OF_FLOUR);

    @Override
    public boolean handle(Player player, Node node, String option) {
        return getFlour(player, (GameObject) node);
    }

    public boolean getFlour(final Player player, final GameObject object) {
        if (!player.getInventory().containsItem(EMPTY_POT)) {
            player.getPacketDispatch().sendMessage("I need an empty pot to hold the flour in.");
            return true;
        }
        if (player.getInventory().remove(EMPTY_POT)) {
            player.lock(3);
            player.getInventory().add(FLOUR);
            player.getPacketDispatch().sendMessage("You take some flour from the barrel.");

            // Seers achievement diary
            if (!player.getAchievementDiaryManager().getDiary(DiaryType.SEERS_VILLAGE).isComplete(0,5)) {
                if (player.getAttribute("diary:seers:sinclair-flour", 0) >= 4) {
                    player.setAttribute("/save:diary:seers:sinclair-flour", 5);
                    player.getAchievementDiaryManager().getDiary(DiaryType.SEERS_VILLAGE).updateTask(player,0,5,true);
                } else {
                    player.setAttribute("/save:diary:seers:sinclair-flour", player.getAttribute("diary:seers:sinclair-flour", 0) + 1);
                }
            }

            player.getPacketDispatch().sendMessage("There's still plenty of flour left.");

            return true;
        }
        return false;
    }

    @Override
    public Plugin<Object> newInstance(Object arg) throws Throwable {
        ObjectDefinition.forId(26122).getHandlers().put("option:take from", this);
        PluginManager.definePlugin(new FlourHandler());
        return this;
    }

    private class FlourHandler extends UseWithHandler {

        public FlourHandler() {
            super(EMPTY_POT.getId());
        }

        @Override
        public Plugin<Object> newInstance(Object arg) throws Throwable {
            addHandler(26122, OBJECT_TYPE, this);
            return this;
        }

        @Override
        public boolean handle(NodeUsageEvent event) {
            return getFlour(event.getPlayer(), event.getUsedWith().asObject());
        }
    }
}