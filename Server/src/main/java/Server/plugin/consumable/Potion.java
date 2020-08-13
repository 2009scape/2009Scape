package plugin.consumable;

import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.audio.Audio;
import core.game.node.item.Item;

public class Potion extends Drink {

    private static final int VIAL = 229;

    private static final Audio SOUND = new Audio(2401, 1, 1);

    public Potion(final int[] ids, final ConsumableEffect effect, final String... messages) {
        super(ids, effect, messages);
    }

    @Override
    public void consume(Item item, Player player) {
        executeConsumptionActions(player);
        final int nextItemId = getNextItemId(item.getId());
        if (nextItemId != -1) {
            player.getInventory().replace(new Item(nextItemId), item.getSlot());
        } else {
            if (player.getBarcrawlManager().isFinished()) {
                player.getInventory().remove(item);
            } else {
                player.getInventory().replace(new Item(VIAL), item.getSlot());
            }
        }
        Consumables.getConsumableById(item.getId()).effect.activate(player);
    }

    @Override
    protected void executeConsumptionActions(Player player) {
        player.animate(animation);
        player.getAudioManager().send(SOUND);
    }

    public static int getDose(Item potion){
        return Integer.parseInt(potion.getName().replaceAll("[^\\d.]",""));
    }
}
