package plugin.consumable.effects;

import core.game.node.entity.player.Player;
import plugin.consumable.ConsumableEffect;
import plugin.skill.Skills;

public class DraynorCabbageEffect extends ConsumableEffect {

    @Override
    public void activate(Player p) {
        final HealingEffect effect = new HealingEffect(getHealthEffectValue(p));
        effect.activate(p);
    }

    @Override
    public int getHealthEffectValue(Player player) {
        return player.getSkills().getLevel(Skills.DEFENCE) > 50 ? 3 : 4;
    }
}
