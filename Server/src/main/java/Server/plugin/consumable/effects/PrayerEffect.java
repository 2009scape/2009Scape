package plugin.consumable.effects;

import core.game.node.entity.player.Player;
import plugin.consumable.ConsumableEffect;
import plugin.skill.Skills;

public class PrayerEffect extends ConsumableEffect {
    double base, bonus;
    public PrayerEffect(double base, double bonus){
        this.base = base;
        this.bonus = bonus;
    }
    @Override
    public void activate(Player p) {
        int level = p.getSkills().getStaticLevel(Skills.PRAYER);
        double amt = base + (level * bonus);
        p.getSkills().incrementPrayerPoints(amt);
    }
}
