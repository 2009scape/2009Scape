package plugin.consumable.effects;

import core.game.node.entity.player.Player;
import core.tools.RandomFunction;
import plugin.consumable.ConsumableEffect;

public class RandomHealthEffect extends ConsumableEffect {

    private final int a, b;

    private int nextHealthValue;

    public RandomHealthEffect(final int a, final int b) {
        this.a = a;
        this.b = b;
        this.nextHealthValue = RandomFunction.random(a, b);
    }

    @Override
    public void activate(Player p) {
        ConsumableEffect effect;
        if (nextHealthValue > 0) {
            effect = new HealingEffect(nextHealthValue);
        } else {
            effect = new DamageEffect(nextHealthValue, false);
        }
        effect.activate(p);
        nextHealthValue = RandomFunction.random(a, b);
    }

    @Override
    public int getHealthEffectValue(Player player) {
        return nextHealthValue;
    }
}
