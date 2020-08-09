package plugin.consumable.effects;

import core.game.node.entity.player.Player;
import plugin.consumable.ConsumableEffect;

public class MultiEffect extends ConsumableEffect {
    private ConsumableEffect[] effects;
    public MultiEffect(ConsumableEffect... effects){
        this.effects = effects;
    }
    @Override
    public void activate(Player p) {
        for(ConsumableEffect e : effects){
            e.activate(p);
        }
    }
}
