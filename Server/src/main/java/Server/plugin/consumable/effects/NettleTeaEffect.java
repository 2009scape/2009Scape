package plugin.consumable.effects;

import core.game.node.entity.player.Player;
import plugin.consumable.ConsumableEffect;

public class NettleTeaEffect extends ConsumableEffect {

    private final static int healing = 3;

    @Override
    public void activate(Player p) {
        ConsumableEffect effect;
        if (p.getSkills().getLifepoints() < p.getSkills().getMaximumLifepoints()) {
            effect = new MultiEffect(new HealingEffect(3), new EnergyEffect(5));
        } else {
            effect = new HealingEffect(3);
        }
        effect.activate(p);
    }

    @Override
    public int getHealthEffectValue(Player player) {
        return healing;
    }
}
