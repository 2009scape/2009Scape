package plugin.skill.magic;

import core.game.node.Node;
import core.game.node.entity.Entity;
import core.game.node.entity.combat.BattleState;
import core.game.node.entity.combat.CombatSpell;
import core.game.node.entity.combat.equipment.SpellType;
import core.game.node.entity.impl.Animator.Priority;
import core.game.node.entity.impl.Projectile;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.SpellBookManager.SpellBook;
import core.game.node.entity.player.link.prayer.PrayerType;
import core.game.node.entity.state.EntityState;
import core.game.node.item.Item;
import core.game.world.update.flag.context.Animation;
import core.game.world.update.flag.context.Graphics;
import core.plugin.InitializablePlugin;
import core.plugin.Plugin;

/**
 * Handles the teleportation block spell in the modern spellbook.
 * @author Splinter
 * 
 */
@InitializablePlugin
public final class TeleblockSpell extends CombatSpell {

	/**
	 * The projectile for the teleblock spell.
	 */
	private static final Projectile TELEBLOCK_ORB = Projectile.create((Entity) null, null, 1842, 40, 36, 52, 75, 15, 11);
	
	/**
	 * The ending graphic for the teleblock spell.
	 */
	private static final Graphics TELEBLOCK_SUCCESS = new Graphics(1843, 0);
	
	/**
	 * The starting graphic for the teleblock spell.
	 */
	private static final Graphics TELEBLOCK_START = new Graphics(1841, 0);
	
	/**
	 * Constructs a new {@code TeleblockSpell} {@code Object}.
	 */
	public TeleblockSpell() {
		/*
		 * empty.
		 */
	}
	
	/**
	 * Constructs a new {@code TeleblockSpell} {@code Object}.
	 * @param level - the level needed to cast this spell
	 * @param baseExperience - the base amount of experience we need to give
	 * @param impactSound -the sound played on successful impact
	 * @param anim - the animation to play
	 * @param start - the starting graphic
	 * @param projectile - the projectile to send
	 * @param end - the ending graphic
	 */
	public TeleblockSpell(SpellType type, int level, double baseExperience, int impactSound, Animation anim, Graphics start, Projectile projectile, Graphics end, Item... runes) {
		super(type, SpellBook.MODERN, level, baseExperience, 202, 203, anim, TELEBLOCK_START, TELEBLOCK_ORB, TELEBLOCK_SUCCESS, runes);
	}

	@Override
	public Plugin<SpellType> newInstance(SpellType arg) throws Throwable {
		SpellBook.MODERN.register(63, new TeleblockSpell(SpellType.TELEBLOCK, 85, 80, 203, new Animation(10503, Priority.HIGH), TELEBLOCK_START, TELEBLOCK_ORB, TELEBLOCK_SUCCESS, Runes.LAW_RUNE.getItem(1), Runes.DEATH_RUNE.getItem(1), Runes.CHAOS_RUNE.getItem(1)));
		return this;
	}
	
	@Override
	public void visualize(Entity entity, Node target) {
		entity.graphics(graphic);
		if (projectile != null) {
			projectile.transform(entity, (Entity) target, false, 58, 10).send();
		}
		entity.animate(animation);
		sendAudio(entity, audio);
	}
	
	@Override
	public boolean cast(Entity entity, Node target) {
		if (!(target instanceof Player)) {
			entity.asPlayer().sendMessage("You can only cast this spell on another player.");
			return false;
		}
		if (!entity.getZoneMonitor().isInZone("Wilderness") || !((Player) target).getZoneMonitor().isInZone("Wilderness")) {
			entity.asPlayer().sendMessage("You and your opponent must both be in the wilderness for you to use this spell.");
			return false;
		}
		if (((Player) target).isTeleBlocked()) {
			entity.asPlayer().sendMessage("That player is already affected by this spell.");
			return false;
		}
		if (!meetsRequirements(entity, true, false)) {
			return false;
		}
		return super.cast(entity, target);
	}

	@Override
	public void visualizeImpact(Entity entity, Entity target, BattleState state) {
		super.visualizeImpact(entity, target, state);
	}

	@Override
	public int getMaximumImpact(Entity entity, Entity victim, BattleState state) {
		return 0;
	}
	
	@Override
	public void fireEffect(Entity entity, Entity victim, BattleState state) {
		if(!victim.isTeleBlocked() && victim instanceof Player && state.getStyle().getSwingHandler().isAccurateImpact(entity, victim) && state.getEstimatedHit() > 0){
			int ticks = 500;
			if(((Player) victim).getPrayer().get(PrayerType.PROTECT_FROM_MAGIC)){
				ticks /= 2;
			}
			victim.getStateManager().set(EntityState.TELEBLOCK, ticks);
		} else if(victim.isTeleBlocked()){
			entity.asPlayer().sendMessage("Your target is already blocked from teleporting.");
		}
	}
}
