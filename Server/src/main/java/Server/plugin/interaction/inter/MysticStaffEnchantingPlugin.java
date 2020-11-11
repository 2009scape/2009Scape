package plugin.interaction.inter;

import core.game.component.Component;
import core.game.component.ComponentDefinition;
import core.game.component.ComponentPlugin;
import core.game.content.ItemNames;
import core.game.node.entity.player.Player;
import core.game.node.item.Item;
import core.plugin.Plugin;
import core.plugin.InitializablePlugin;
import core.tools.StringUtils;

import java.util.HashMap;

/**
 * Represents the plugin used to handle the agility ticket interface.
 * @author afaroutdude
 */
@InitializablePlugin
public final class MysticStaffEnchantingPlugin extends ComponentPlugin {

    private final Component COMPONENT = new Component(332);
    private final int cost = 40000;

    protected enum EnchantedStaff {
        AIR(ItemNames.MYSTIC_AIR_STAFF, ItemNames.AIR_BATTLESTAFF, 21),
        WATER(ItemNames.MYSTIC_WATER_STAFF, ItemNames.WATER_BATTLESTAFF, 22),
        EARTH(ItemNames.MYSTIC_EARTH_STAFF, ItemNames.EARTH_BATTLESTAFF, 23),
        FIRE(ItemNames.MYSTIC_FIRE_STAFF, ItemNames.FIRE_BATTLESTAFF, 24),
        LAVA(ItemNames.MYSTIC_LAVA_STAFF_3054, ItemNames.LAVA_BATTLESTAFF, 25),
        MUD(ItemNames.MYSTIC_MUD_STAFF_6563, ItemNames.MUD_BATTLESTAFF, 26),
        STEAM(ItemNames.MYSTIC_STEAM_STAFF_11789, ItemNames.STEAM_BATTLESTAFF, 27);

        public final int enchanted;
        public final int basic;
        public final int child;

        private static final HashMap<Integer, Integer> basicToEnchanted = new HashMap<>();
        private static final HashMap<Integer, Integer> childToBasic = new HashMap<>();

        static {
            for (EnchantedStaff staff : EnchantedStaff.values()) {
                basicToEnchanted.put(staff.basic, staff.enchanted);
                childToBasic.put(staff.child, staff.basic);
            }
        }

        EnchantedStaff(int enchantedId, int basicId, int childId) {
            this.enchanted = enchantedId;
            this.basic = basicId;
            this.child = childId;
        }
    }

    @Override
    public Plugin<Object> newInstance(Object arg) throws Throwable {
        ComponentDefinition.forId(332).setPlugin(this);
        return this;
    }

    @Override
    public void open(Player player, Component component) {
        super.open(player, component);
        // zoom doesn't work, but based on https://youtu.be/qxxhhCdxBsQ?t=75 seems correct anyway
        for (EnchantedStaff staff : EnchantedStaff.values()) {
            player.getPacketDispatch().sendItemZoomOnInterface(staff.basic, 240, COMPONENT.getId(), staff.child);
        }
    }

    @Override
    public boolean handle(Player player, Component component, int opcode, int buttonId, int slot, int itemId) {
        player.getPacketDispatch().sendMessage("op: " + opcode + " button: " + buttonId + " slot: "+ slot + " item: " + itemId);

        if (EnchantedStaff.childToBasic.containsKey(buttonId)) {
            Item basicStaff = new Item(EnchantedStaff.childToBasic.get(buttonId));
            Item enchantedStaff = new Item(EnchantedStaff.basicToEnchanted.get(basicStaff.getId()));

            if (!player.getInventory().containsItem(basicStaff)) {
                player.getPacketDispatch().sendMessage("You don't have a" + (StringUtils.isPlusN(basicStaff.getName()) ? "n " : " ") + basicStaff.getName() + " to enchant.");
                return true;
            }
            if (!player.getInventory().contains(995, cost)) {
                player.getInterfaceManager().close();
                player.getDialogueInterpreter().sendDialogues(389, null, "I need 40,000 coins for materials. Come", "back when you have the money!");
                return true;
            }
            if (player.getInventory().remove(basicStaff, new Item(995, cost))) {
                player.getInterfaceManager().close();
                player.getDialogueInterpreter().sendDialogues(389, null, "Just a moment... hang on... hocus pocus abra-", "cadabra... there you go! Enjoy your enchanted staff!");
                player.getInventory().add(enchantedStaff);
            }
        }

        return true;
    }
}
