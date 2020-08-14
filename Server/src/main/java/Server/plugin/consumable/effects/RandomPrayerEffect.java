package plugin.consumable.effects;

import core.game.node.entity.player.Player;
import core.tools.RandomFunction;
import plugin.consumable.ConsumableEffect;

public class RandomPrayerEffect extends ConsumableEffect {

    private final int a, b;

    public RandomPrayerEffect(final int a, final int b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public void activate(Player p) {
        final PrayerEffect effect = new PrayerEffect(RandomFunction.random(a, b), 0);
        effect.activate(p);
    }
}
