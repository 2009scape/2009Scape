package plugin.consumable;

import core.game.node.entity.player.Player;
import core.game.node.item.Item;

public class BarbarianMix extends Potion {

    public BarbarianMix(int[] ids, ConsumableEffect effect, String... messages) {
        super(ids, effect, messages);
    }

    @Override
    protected void sendDefaultMessages(Player player, Item item) {
        player.getPacketDispatch().sendMessage("You drink the lumpy potion.");
    }
}
