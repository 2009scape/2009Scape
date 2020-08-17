package plugin.consumable.effects;

import core.game.node.entity.player.Player;
import plugin.consumable.ConsumableEffect;
import plugin.skill.Skills;

public class MatureWmbEffect extends ConsumableEffect {

    private final static int healing = 1;

    @Override
    public void activate(Player p) {
        final int magicLevelBoost = p.getSkills().getLevel(Skills.MAGIC) > 50 ? 4 : 3;
        final MultiEffect effect = new MultiEffect(new SkillEffect(Skills.MAGIC, magicLevelBoost, 0), new HealingEffect(healing), new SkillEffect(Skills.ATTACK, -5, 0), new SkillEffect(Skills.STRENGTH, -5, 0), new SkillEffect(Skills.DEFENCE, -5, 0));
        effect.activate(p);
    }

    @Override
    public int getHealthEffectValue(Player player) {
        return healing;
    }
}
