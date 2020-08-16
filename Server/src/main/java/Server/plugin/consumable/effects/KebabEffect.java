package plugin.consumable.effects;

import core.game.node.entity.player.Player;
import core.tools.RandomFunction;
import plugin.consumable.ConsumableEffect;
import plugin.skill.Skills;

public class KebabEffect extends ConsumableEffect {

    @Override
    public void activate(Player p) {
        final int randomNumber = RandomFunction.nextInt(100);
        ConsumableEffect effect;
        String message;
        if (randomNumber < 66) {
            effect = new PercentageHealthEffect(10);
            effect.activate(p);
            message = "It heals some health.";
        } else if (randomNumber < 87) {
            effect = new RandomHealthEffect(10, 20);
            effect.activate(p);
            message = "That was a good kebab. You feel a lot better.";
        } else if (randomNumber < 96) {
            if (RandomFunction.nextInt(100) < 50) {
                final int affectedSkillSlot = RandomFunction.nextInt(Skills.NUM_SKILLS - 1);
                switch (affectedSkillSlot) {
                    case Skills.ATTACK:
                    case Skills.DEFENCE:
                    case Skills.STRENGTH:
                        effect = new MultiEffect(new SkillEffect(Skills.ATTACK, -3, 0), new SkillEffect(Skills.DEFENCE, -3, 0), new SkillEffect(Skills.STRENGTH, -3, 0));
                        break;
                    default:
                        effect = new SkillEffect(affectedSkillSlot, -3, 0);
                }
                message = "That tasted a bit dodgy. You feel a bit ill.";
                effect.activate(p);
            } else {
                message = "That kebab didn't seem to do a lot.";
            }
        } else {
            effect = new MultiEffect(new HealingEffect(30), new RandomSkillEffect(Skills.ATTACK, 1, 3), new RandomSkillEffect(Skills.DEFENCE, 1, 3), new RandomSkillEffect(Skills.STRENGTH, 1, 3));
            effect.activate(p);
            message = "Wow, that was an amazing kebab! You feel really invigorated.";
        }
        p.getPacketDispatch().sendMessage(message);
    }
}
