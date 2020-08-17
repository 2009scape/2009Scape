package plugin.consumable.effects;

import core.game.node.entity.player.Player;
import core.tools.RandomFunction;
import plugin.consumable.ConsumableEffect;
import plugin.skill.Skills;

/**
 * According to the OSRS wiki, a kebab has the following possible outcomes:
 *     66% chance to heal 10% of total Hitpoints level, rounded down. Accompanied with the message "It heals some health."
 *     21% chance to heal 10–20 Hitpoints. Accompanied with the message "That was a good kebab. You feel a lot better."
 *     9% chance to heal nothing. Accompanied with the message "That kebab didn't seem to do a lot."
 *         In addition, there is a chance that a non-combat skill is lowered by 3 (with the message "That tasted a bit dodgy. You feel a bit ill."), or that melee skills are lowered by 3 (with the message "That tasted very dodgy. You feel very ill.").
 *     4% chance to heal 30 Hitpoints and gain 1–3 levels in Attack, Strength and Defence. Accompanied with the message "Wow, that was an amazing kebab! You feel really invigorated."
 */
public class KebabEffect extends ConsumableEffect {

    @Override
    public void activate(Player p) {
        final int randomNumber = RandomFunction.nextInt(100);
        ConsumableEffect effect;
        String message;
        if (randomNumber < 66) {
            effect = new PercentageHealthEffect(10);
            final int initialLifePoints = p.getSkills().getLifepoints();
            effect.activate(p);
            if (p.getSkills().getLifepoints() > initialLifePoints) {
                message = "It heals some health.";
                p.getPacketDispatch().sendMessage(message);
            }
        } else if (randomNumber < 87) {
            effect = new RandomHealthEffect(10, 20);
            effect.activate(p);
            message = "That was a good kebab. You feel a lot better.";
            p.getPacketDispatch().sendMessage(message);
        } else if (randomNumber < 96) {
            if (RandomFunction.nextInt(100) < 50) { // As the probability of lowering by 3 a non-combat skill or all melee skills is not specified, 50% is the percentage that was chosen.
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
                p.getPacketDispatch().sendMessage(message);
                effect.activate(p);
            } else {
                message = "That kebab didn't seem to do a lot.";
                p.getPacketDispatch().sendMessage(message);
            }
        } else {
            effect = new MultiEffect(new HealingEffect(30), new RandomSkillEffect(Skills.ATTACK, 1, 3), new RandomSkillEffect(Skills.DEFENCE, 1, 3), new RandomSkillEffect(Skills.STRENGTH, 1, 3));
            effect.activate(p);
            message = "Wow, that was an amazing kebab! You feel really invigorated.";
            p.getPacketDispatch().sendMessage(message);
        }
    }
}
