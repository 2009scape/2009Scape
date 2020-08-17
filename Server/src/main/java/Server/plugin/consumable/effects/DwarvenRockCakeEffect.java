package plugin.consumable.effects;

import core.game.node.entity.player.Player;
import plugin.consumable.ConsumableEffect;

public class DwarvenRockCakeEffect extends ConsumableEffect {

    private static final DamageEffect effect = new DamageEffect(1, false);

    @Override
    public void activate(Player p) {
        if (p.getSkills().getLifepoints() > 2) {
            effect.activate(p);
        }
    }

    @Override
    public int getHealthEffectValue(Player player) {
        return player.getSkills().getLifepoints() > 2 ? -1 : 0;
    }
}
