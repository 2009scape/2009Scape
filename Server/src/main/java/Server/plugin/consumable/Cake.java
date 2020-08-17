package plugin.consumable;

import core.game.node.entity.player.Player;
import core.game.node.item.Item;

/**
 * Represents any cake item that is made of three even slices.
 */
public class Cake extends Food {

    public Cake(int[] ids, ConsumableEffect effect, String... messages) {
        super(ids, effect, messages);
    }

    @Override
    public void consume(Item item, Player player) {
        executeConsumptionActions(player);
        final int nextItemId = getNextItemId(item.getId());
        if (nextItemId != -1) {
            player.getInventory().replace(new Item(nextItemId), item.getSlot());
        } else {
            player.getInventory().remove(item);
        }
        final int initialLifePoints = player.getSkills().getLifepoints();
        Consumables.getConsumableById(item.getId()).effect.activate(player);
        sendMessages(player, initialLifePoints, item, messages);
    }

    @Override
    protected void sendMessages(Player player, int initialLifePoints, Item item, String[] messages) {
        if (messages.length == 0) {
            sendDefaultMessages(player, item);
        } else {
            sendCustomMessages(player, messages, item.getId());
        }
        sendHealingMessage(player, initialLifePoints);
    }

    private void sendCustomMessages(final Player player, final String[] messages, int itemId) {
        int i = 0;
        while (ids[i] != itemId) {
            i++;
        }
        player.getPacketDispatch().sendMessage(messages[i]);
    }
}
