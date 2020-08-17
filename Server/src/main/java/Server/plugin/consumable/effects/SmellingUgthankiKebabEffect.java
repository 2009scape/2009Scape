package plugin.consumable.effects;

import core.game.node.entity.player.Player;
import core.tools.RandomFunction;
import plugin.consumable.ConsumableEffect;

/**
 * According to the OSRS wiki, the smelling ugthanki kebab will usually heal nothing when eaten, but sometimes heals for 9.
 * As the chances of healing are unknown, the percentage is set to 10%.
 */
public class SmellingUgthankiKebabEffect extends ConsumableEffect {

    private static final int percentage = 10;

    private static final int healing = 9;

    private static final HealingEffect effect = new HealingEffect(healing);

    @Override
    public void activate(Player p) {
        if (RandomFunction.nextInt(100) < percentage) {
            effect.activate(p);
        }
    }

    @Override
    public int getHealthEffectValue(Player player) {
        return RandomFunction.nextInt(100) < percentage ? healing : 0;
    }
}
