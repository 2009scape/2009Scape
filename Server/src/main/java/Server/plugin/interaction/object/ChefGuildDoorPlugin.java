package plugin.interaction.object;

import core.cache.def.impl.ObjectDefinition;
import core.game.content.ItemNames;
import core.game.content.global.action.DoorActionHandler;
import core.game.node.item.Item;
import plugin.dialogue.FacialExpression;
import plugin.skill.Skills;
import core.game.interaction.OptionHandler;
import core.game.node.Node;
import core.game.node.entity.player.Player;
import core.game.node.object.GameObject;
import core.game.world.map.Location;
import core.plugin.InitializablePlugin;
import core.plugin.Plugin;

/**
 * Represents the chef guild door plugin.
 *
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class ChefGuildDoorPlugin extends OptionHandler {
    private static final Item CHEFS_HAT = new Item(ItemNames.CHEFS_HAT);
    private static final Item COOKING_CAPE = new Item(ItemNames.COOKING_CAPE_9801);
    private static final Item COOKING_CAPE_T = new Item(ItemNames.COOKING_CAPET_9802);
    private static final Item VARROCK_ARMOUR_3 = new Item(11758);
    private static final Item[] ENTRANCE_ITEMS = {CHEFS_HAT, COOKING_CAPE, COOKING_CAPE_T, VARROCK_ARMOUR_3};
    private static final int CHEF_NPC = 847;

    @Override
    public boolean handle(Player player, Node node, String option) {
        final GameObject object = (GameObject) node;
        switch (object.getId()) {
            case 2712: // cooking guild front door
                if (player.getSkills().getLevel(Skills.COOKING) < 32) {
                    if (!player.getEquipment().containsOneItem(ENTRANCE_ITEMS)) {
                        player.getDialogueInterpreter().sendDialogues(CHEF_NPC, null, "Sorry. Only the finest chefs are allowed in here.", "Get your cooking level up to 32 and come back", "wearing a chef's hat.");
                    } else {
                        player.getDialogueInterpreter().sendDialogues(CHEF_NPC, null, "Sorry. Only the finest chefs are allowed in here.", "Get your cooking level up to 32.");
                    }
                    return true;
                } else if (!player.getEquipment().containsOneItem(ENTRANCE_ITEMS) && player.getLocation().getY() <= 3443) {
                    player.getDialogueInterpreter().sendDialogues(CHEF_NPC, null, "You can't come in here unless you're wearing a chef's", "hat or something like that.");
                    return true;
                } else {
                    if (player.getEquipment().containsOneItem(VARROCK_ARMOUR_3)) {
                        player.getDialogueInterpreter().sendDialogues(847, null, "My word! A master explorer of Varrock! Come in, come in! You are more than welcome in here, my friend!");
                    }
                    DoorActionHandler.handleAutowalkDoor(player, object);
                }
                break;
            case 26810: // cooking guild bank door
                if (!player.getEquipment().containsOneItem(VARROCK_ARMOUR_3) // player not wearing Varrock Armour 3
                        && player.getLocation().getX() <= 3143) { // outside bank area
                    player.getDialogueInterpreter().sendDialogues(CHEF_NPC, null, "The bank's closed. You just can't get the staff these days.");
                } else {
                    DoorActionHandler.handleAutowalkDoor(player, object);
                }
                break;
        }
        return true;
    }


    @Override
    public Plugin<Object> newInstance(Object arg) throws Throwable {
        ObjectDefinition.forId(2712).getHandlers().put("option:open", this);
        ObjectDefinition.forId(26810).getHandlers().put("option:open", this);
        return this;
    }

    @Override
    public Location getDestination(Node node, Node n) {
        return DoorActionHandler.getDestination(((Player) node), ((GameObject) n));
    }
}
