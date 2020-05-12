package plugin.combat.spell;

import org.crandor.game.content.skill.free.magic.Runes;
import org.crandor.game.node.Node;
import org.crandor.game.node.entity.Entity;
import org.crandor.game.node.entity.combat.BattleState;
import org.crandor.game.node.entity.combat.CombatSpell;
import org.crandor.game.node.entity.combat.equipment.SpellType;
import org.crandor.game.node.entity.impl.Projectile;
import org.crandor.game.node.entity.impl.Animator.Priority;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.entity.player.link.SpellBookManager.SpellBook;
import org.crandor.game.world.update.flag.context.Animation;
import org.crandor.game.world.update.flag.context.Graphics;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.plugin.Plugin;

/**
 * Handles the crumble undead spell.
 * @author Emperor
 * @version 1.0
 */
@InitializablePlugin
public final class CrumbleUndead extends CombatSpell {

	/**
	 * Constructs a new {@code CrumbleUndead} {@code Object}.
	 */
	public CrumbleUndead() {
		super(SpellType.CRUMBLE_UNDEAD, SpellBook.MODERN, 39, 24.5, 122, 123, new Animation(724, Priority.HIGH), new Graphics(145, 96), Projectile.create((Entity) null, null, 146, 40, 36, 52, 75, 15, 11), new Graphics(147, 96), Runes.EARTH_RUNE.getItem(2), Runes.AIR_RUNE.getItem(2), Runes.CHAOS_RUNE.getItem(1));
	}

	@Override
	public boolean cast(Entity entity, Node target) {
		NPC npc = target instanceof NPC ? (NPC) target : null;
		if (npc == null || npc.getTask() == null || !npc.getTask().undead) {
			((Player) entity).getPacketDispatch().sendMessage("This spell only affects the undead.");
			return false;
		}
		return super.cast(entity, target);
	}

	@Override
	public Plugin<SpellType> newInstance(SpellType arg) throws Throwable {
		SpellBook.MODERN.register(22, this);
		return this;
	}

	@Override
	public int getMaximumImpact(Entity entity, Entity victim, BattleState state) {
		return type.getImpactAmount(entity, victim, 0);
	}

}
