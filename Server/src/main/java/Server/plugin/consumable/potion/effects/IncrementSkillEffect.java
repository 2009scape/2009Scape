package plugin.consumable.potion.effects;

import core.game.node.entity.player.Player;
import plugin.consumable.potion.PotionEffect;
import plugin.skill.Skills;

public class IncrementSkillEffect extends PotionEffect {
    private final int skill_slot;
    private final double base;
    private final double bonus;
    public IncrementSkillEffect(int skill_slot, double base, double bonus){
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
