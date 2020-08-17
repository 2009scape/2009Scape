package plugin.consumable.effects;

import core.game.node.entity.player.Player;
import core.tools.RandomFunction;
import plugin.consumable.ConsumableEffect;
import plugin.skill.Skills;


public class SuperKebabEffect extends ConsumableEffect {

    private static final MultiEffect healingEffect = new MultiEffect(new HealingEffect(3), new PercentageHealthEffect(7));

    @Override
    public void activate(Player p) {
        if (RandomFunction.nextInt(8) < 5) {
            healingEffect.activate(p);
        }
        if (RandomFunction.nextInt(32) < 1) {
            final SkillEffect effect = new SkillEffect(RandomFunction.nextInt(Skills.NUM_SKILLS), -1, 0);
            effect.activate(p);
        }
    }

    @Override
    public int getHealthEffectValue(Player player) {
        return RandomFunction.nextInt(8) < 5 ? (int) (3 + (player.getSkills().getMaximumLifepoints() * 0.07)) : 0;
    }
}
