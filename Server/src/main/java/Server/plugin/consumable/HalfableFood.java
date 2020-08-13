package plugin.consumable;

import core.game.node.entity.player.Player;
import core.game.node.item.Item;

public class HalfableFood extends Food {

    public HalfableFood(int[] ids, ConsumableEffect effect, String... messages) {
        super(ids, effect, messages);
    }

    @Override
    protected void sendDefaultMessages(Player player, Item item) {
        if (item.getId() == ids[0]) {
            player.getPacketDispatch().sendMessage("You eat half the "+ getFormattedName(item) + ".");
        } else if (item.getId() == ids[1]) {
            player.getPacketDispatch().sendMessage("You eat the remaining " + getFormattedName(item) + ".");
        } else {
            super.sendDefaultMessages(player, item);
        }
    }

    @Override
    public String getFormattedName(Item item) {
        return item.getName().replace("1/2", "").replace("Half an", "").replace("Half a", "").trim().toLowerCase();
    }
}
