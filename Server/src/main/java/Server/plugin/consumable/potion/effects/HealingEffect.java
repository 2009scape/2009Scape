package plugin.consumable.potion.effects;

import core.game.node.entity.player.Player;
import plugin.consumable.potion.PotionEffect;

public class HealingEffect extends PotionEffect {
    int amt;
    public HealingEffect(int amount){
        this.amt = amount;
    }
    @Override
    public void activate(Player p) {
        p.getSkills().heal(amt);
    }
}
