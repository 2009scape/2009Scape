package plugin.consumable.effects;

import core.game.node.entity.player.Player;
import plugin.consumable.ConsumableEffect;
import plugin.skill.Skills;

public class KegOfBeerEffect extends ConsumableEffect {

    private final static int healing = 15;

    @Override
    public void activate(Player p) {
        final int attackLevelReduction = (p.getSkills().getLevel(Skills.ATTACK) * 65) / 99;
        final MultiEffect effect = new MultiEffect(new HealingEffect(15), new RandomSkillEffect(Skills.STRENGTH, 2, 10), new SkillEffect(Skills.ATTACK, attackLevelReduction, 0));
        effect.activate(p);
    }

    @Override
    public int getHealthEffectValue(Player player) {
        return healing;
    }
}
