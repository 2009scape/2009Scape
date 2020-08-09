package plugin.consumable.effects;

import core.game.node.entity.combat.ImpactHandler;
import core.game.node.entity.player.Player;
import plugin.consumable.PotionEffect;

public class DamageEffect extends PotionEffect {
    final double amt;
    final boolean isPercent;

    public DamageEffect(double amt,boolean isPercent){
        this.amt = amt;
        this.isPercent = isPercent;
    }

    @Override
    public void activate(Player p) {
        double amount = amt;
        if(isPercent){
            amount *= p.getSkills().getLifepoints();
        }
        p.getImpactHandler().manualHit(p,(int)amount, ImpactHandler.HitsplatType.NORMAL);
    }
}
