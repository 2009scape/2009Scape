package plugin.consumable.effects;

import core.game.node.entity.player.Player;
import plugin.consumable.ConsumableEffect;
import plugin.skill.Skills;

public class SkillEffect extends ConsumableEffect {
    private final int skill_slot;
    private final double base;
    private final double bonus;
    public SkillEffect(int skill_slot, double base, double bonus){
        this.skill_slot = skill_slot;
        this.base = base;
        this.bonus = bonus;
    }
    @Override
    public void activate(Player p) {
        Skills skills = p.getSkills();
        int level = skills.getLevel(skill_slot);
        int slevel = skills.getStaticLevel(skill_slot);
        skills.updateLevel(skill_slot,(int)(base + (bonus * slevel)),slevel + (int)(base + (bonus * slevel)));
    }
}
