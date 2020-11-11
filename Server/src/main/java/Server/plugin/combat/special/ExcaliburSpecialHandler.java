package plugin.combat.special;

import core.game.container.impl.EquipmentContainer;
import plugin.skill.Skills;
import core.game.node.entity.Entity;
import core.game.node.entity.combat.BattleState;
import core.game.node.entity.combat.CombatStyle;
import core.game.node.entity.combat.handlers.MeleeSwingHandler;
import core.game.node.entity.impl.Animator.Priority;
import core.game.node.entity.player.Player;
import core.game.world.update.flag.context.Animation;
import core.game.world.update.flag.context.Graphics;
import core.plugin.InitializablePlugin;
import core.plugin.Plugin;

/**
 * Handles the excalibur special attack.
 *
 * @author Emperor
 * @version 1.0
 */
@InitializablePlugin
public final class ExcaliburSpecialHandler extends MeleeSwingHandler implements Plugin<Object> {

    /**
     * The special energy required.
     */
    private static final int SPECIAL_ENERGY = 100;

    /**
     * The attack animation.
     */
    private static final Animation ANIMATION = new Animation(1057, Priority.HIGH);

    /**
     * The graphic.
     */
    private static final Graphics GRAPHIC = new Graphics(247);

    @Override
    public Object fireEvent(String identifier, Object... args) {
        switch (identifier) {
            case "instant_spec":
            case "ncspec":
                return true;
        }
        return null;
    }

    @Override
    public Plugin<Object> newInstance(Object arg) throws Throwable {
        CombatStyle.MELEE.getSwingHandler().register(35, this);
        CombatStyle.MELEE.getSwingHandler().register(14632, this);
        return this;
    }

    @Override
    public int swing(Entity entity, Entity victim, BattleState state) {
        Player p = (Player) entity;
        if (!p.getSettings().drainSpecial(SPECIAL_ENERGY))
            return -1;
        p.sendChat("For Camelot!");
        p.visualize(ANIMATION, GRAPHIC);
        switch(p.getEquipment().get(EquipmentContainer.SLOT_WEAPON).getId()) {
            case 35: // Regular ol' excalibur
                p.getSkills().updateLevel(Skills.DEFENCE, 8, p.getSkills().getStaticLevel(Skills.DEFENCE) + 8);
                break;
            case 14632: // enhanced excalibur
                p.getSkills().updateLevel(Skills.DEFENCE,
                        (int)(p.getSkills().getStaticLevel(Skills.DEFENCE)*0.15),
                        (int)(p.getSkills().getStaticLevel(Skills.DEFENCE)*1.15));
        }

        return -1;
    }

}
