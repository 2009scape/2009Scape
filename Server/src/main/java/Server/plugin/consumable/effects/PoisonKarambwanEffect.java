package plugin.consumable.effects;

import core.game.node.entity.player.Player;
import plugin.consumable.ConsumableEffect;

public class PoisonKarambwanEffect extends ConsumableEffect {

    private final PoisonEffect effect = new PoisonEffect(5);

    @Override
    public void activate(Player p) {
        if (p.getSkills().getLifepoints() > 5) {
            effect.activate(p);
        }
    }

    @Override
    public int getHealthEffectValue(Player player) {
        return -5;
    }
}
