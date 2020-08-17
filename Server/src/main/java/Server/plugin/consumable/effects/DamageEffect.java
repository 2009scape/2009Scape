package plugin.consumable.effects;

import core.game.node.entity.combat.ImpactHandler;
import core.game.node.entity.player.Player;
import plugin.consumable.ConsumableEffect;

public class DamageEffect extends ConsumableEffect {
    final double amt;
    final boolean isPercent;

    public DamageEffect(double amt,boolean isPercent){
        this.amt = amt;
        this.isPercent = isPercent;
    }

    @Override
    public void activate(Player p) {
        p.getImpactHandler().manualHit(p,-getHealthEffectValue(p), ImpactHandler.HitsplatType.NORMAL);
    }

    @Override
    public int getHealthEffectValue(Player player) {
        double amount = amt;
        if (isPercent) {
            amount /= 100;
            return (int) -(amount * player.getSkills().getLifepoints());
        }
        return (int) -amt;
    }
}
