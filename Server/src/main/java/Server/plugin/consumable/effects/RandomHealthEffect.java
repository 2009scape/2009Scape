package plugin.consumable.effects;

import core.game.node.entity.player.Player;
import core.tools.RandomFunction;
import plugin.consumable.ConsumableEffect;

public class RandomHealthEffect extends ConsumableEffect {

    private final int a, b;

    public RandomHealthEffect(final int a, final int b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public void activate(Player p) {
        ConsumableEffect effect;
        final int healthEffectValue = getHealthEffectValue(p);
        if (healthEffectValue > 0) {
            effect = new HealingEffect(healthEffectValue);
        } else {
            effect = new DamageEffect(healthEffectValue, false);
        }
        effect.activate(p);
    }

    @Override
    public int getHealthEffectValue(Player player) {
        return RandomFunction.random(a, b);
    }
}
