package plugin.consumable.potion.effects;

import core.game.node.entity.player.Player;
import plugin.consumable.potion.PotionEffect;

public class MultiEffect extends PotionEffect {
    private PotionEffect[] effects;
    public MultiEffect(PotionEffect... effects){
        this.effects = effects;
    }
    @Override
    public void activate(Player p) {
        for(PotionEffect e : effects){
            e.activate(p);
        }
    }
}
