package plugin.consumable.effects;

import core.game.node.entity.player.Player;
import plugin.consumable.ConsumableEffect;

public class PercentageHealthEffect extends ConsumableEffect {

    private final int percentage;

    public PercentageHealthEffect(final int percentage) {
        this.percentage = percentage / 100;
    }

    @Override
    public void activate(Player p) {
        final HealingEffect effect = new HealingEffect(getHealthEffectValue(p));
        effect.activate(p);
    }

    @Override
    public int getHealthEffectValue(Player player) {
        return player.getSkills().getMaximumLifepoints() * percentage;
    }
}
