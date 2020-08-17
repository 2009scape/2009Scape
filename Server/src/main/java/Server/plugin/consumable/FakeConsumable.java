package plugin.consumable;

import core.game.node.entity.player.Player;
import core.game.node.item.Item;

/**
 * Represents a special type of consumable that cannot be consumed unless it is transformed into another item.
 * During its 'consumption', only a message is sent to the player.
 */
public class FakeConsumable extends Consumable {

    public FakeConsumable(final int id, final String[] messages) {
        super(new int[] {id}, null, messages);
    }

    @Override
    public void consume(Item item, Player player) {
        sendDefaultMessages(player, item);
    }

    @Override
    protected void sendDefaultMessages(Player player, Item item) {
        for (String message : messages) {
            player.getPacketDispatch().sendMessage(message);
        }
    }

    @Override
    protected void executeConsumptionActions(Player player) {
    }
}
