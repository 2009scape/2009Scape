package plugin.quest.free.dragonslayer;

import core.game.node.entity.Entity;
import core.game.node.entity.combat.BattleState;
import core.game.node.entity.combat.CombatStyle;
import core.game.node.entity.combat.CombatSwingHandler;
import core.game.node.entity.combat.InteractionType;
import core.game.node.entity.combat.equipment.ArmourSet;
import core.game.node.entity.combat.equipment.FireType;
import core.game.node.entity.impl.Projectile;
import core.game.node.entity.impl.Animator.Priority;
import core.game.node.entity.npc.AbstractNPC;
import core.game.node.entity.player.Player;
import core.game.node.item.GroundItemManager;
import core.game.node.object.GameObject;
import core.game.node.object.ObjectBuilder;
import core.game.system.task.Pulse;
import core.game.world.GameWorld;
import core.game.world.map.Direction;
import core.game.world.map.Location;
import core.game.world.map.RegionManager;
import core.game.world.update.flag.context.Animation;
import core.game.world.update.flag.context.Graphics;
import core.tools.RandomFunction;

/**
 * Represents the elvar npc.
 * @author 'Vexia
 * @version 1.0
 */
public final class ElvargNPC extends AbstractNPC {

	/**
	 * The NPC ids of NPCs using this plugin.
	 */
	private static final int[] ID = { 742 };

	/**
	 * Represents the animations to use.
	 */
	private static final Animation[] ANIMATIONS = new Animation[] { new Animation(6654), new Animation(6655) };

	/**
	 * Represents the combat swing handler.
	 */
	private final CombatSwingHandler combatHandler = new ElvargCombatSwingHandler();

	/**
	 * Constructs a new {@code MeldarMadNPC} {@code Object}.
	 */
	public ElvargNPC() {
		super(0, null);
	}

	/**
	 * Constructs a new {@code MeldarMadNPC} {@code Object}.
	 * @param id The NPC id.
	 * @param location The location.
	 */
	private ElvargNPC(int id, Location location) {
		super(id, location);
	}

	@Override
	public AbstractNPC construct(int id, Location location, Object... objects) {
		return new ElvargNPC(id, location);
	}

	@Override
	public void commenceDeath(Entity killer) {
		final Direction direction = Direction.getLogicalDirection(getLocation(), killer.getLocation());
		GameWorld.Pulser.submit(new Pulse(1, this) {
			@Override
			public boolean pulse() {
				faceLocation(getCenterLocation().transform(direction.getStepX() * 3, direction.getStepY() * 3, 0));
				return true;
			}
		});
		setDirection(direction);
	}

	@Override
	public void finalizeDeath(final Entity killer) {
		super.finalizeDeath(killer);
		final GameObject object = new GameObject(25202, getLocation(), getRotation());
		ObjectBuilder.add(object);
		killer.faceLocation(object.getCenterLocation());
		killer.lock();
		GameWorld.Pulser.submit(new Pulse(1) {
			int counter = 0;

			@Override
			public boolean pulse() {
				counter++;
				if (counter == 1) {
					killer.animate(ANIMATIONS[0]);
				} else if (counter == 4) {
					final Player player = ((Player) killer);
					ObjectBuilder.replace(object, object.transform(25203));
					if (!player.getInventory().add(DragonSlayer.ELVARG_HEAD)) {
						GroundItemManager.create(DragonSlayer.ELVARG_HEAD, player);
					}
					killer.animate(ANIMATIONS[1]);
					killer.unlock();
				} else if (counter == 12) {
					ObjectBuilder.remove(RegionManager.getObject(object.getLocation()));
					return true;
				}
				return false;
			}

		});
	}

	@Override
	public CombatSwingHandler getSwingHandler(boolean swing) {
		return combatHandler;
	}

	@Override
	public int[] getIds() {
		return ID;
	}

	/**
	 * Method used to get the rotation.
	 * @return the rotation.
	 */
	public int getRotation() {
		switch (getDirection()) {
		case EAST:
			return 3;
		case NORTH:
			return 2;
		case WEST:
			return 1;
		case SOUTH:
			return 0;
		default:
			break;
		}
		return 0;
	}

	@Override
	public boolean isAttackable(Entity entity, CombatStyle style) {
		if (!(entity instanceof Player)) {
			return super.isAttackable(entity, style);
		}
		final Player player = (Player) entity;
		if (player.getQuestRepository().getQuest("Dragon Slayer").getStage(player) == 40 && (player.getInventory().containsItem(DragonSlayer.ELVARG_HEAD) || player.getInventory().containsItem(DragonSlayer.ELVARG_HEAD))) {
			player.getPacketDispatch().sendMessage("You have already slain the dragon. Now you just need to return to Oziach for");
			player.getPacketDispatch().sendMessage("your reward!");
			return true;
		}
		if (player.getQuestRepository().getQuest("Dragon Slayer").getStage(player) > 40) {
			player.getPacketDispatch().sendMessage("You have already slain Elvarg.");
			return false;
		}
		return super.isAttackable(entity, style);
	}

	@Override
	public int getDragonfireProtection(boolean fire) {
		return 0x2 | 0x4 | 0x8;
	}

	/**
	 * Handles the elvar combat swing handler.
	 * @author Emperor
	 */
	static class ElvargCombatSwingHandler extends CombatSwingHandler {

		/**
		 * The style.
		 */
		private CombatStyle style = CombatStyle.RANGE;

		/**
		 * The melee attack animation.
		 */
		private static final Animation MELEE_ATTACK = new Animation(80, Priority.HIGH);

		/**
		 * The fire type.
		 */
		private final FireType fireType = FireType.FIERY_BREATH;

		/**
		 * Constructs a new {@code KBDCombatSwingHandler} {@Code Object}.
		 *  The combat style.
		 */
		public ElvargCombatSwingHandler() {
			super(CombatStyle.RANGE);
		}

		@Override
		public void adjustBattleState(Entity entity, Entity victim, BattleState state) {
			if (style == CombatStyle.RANGE) {
				fireType.getTask().exec(victim, entity);
				if (victim.hasProtectionPrayer(CombatStyle.MAGIC)) {
					state.setEstimatedHit((int) (state.getEstimatedHit() * 0.6));
				}
				state.setEstimatedHit(formatHit(victim, state.getEstimatedHit()));
				return;
			}
			style.getSwingHandler().adjustBattleState(entity, victim, state);
		}

		@Override
		public int calculateAccuracy(Entity entity) {
			if (style == CombatStyle.MELEE) {
				return style.getSwingHandler().calculateAccuracy(entity);
			}
			return 600;
		}

		@Override
		public int calculateDefence(Entity entity, Entity attacker) {
			return style.getSwingHandler().calculateDefence(entity, attacker);
		}

		@Override
		public int calculateHit(Entity entity, Entity victim, double modifier) {
			if (style == CombatStyle.MELEE) {
				return style.getSwingHandler().calculateHit(entity, victim, modifier);
			}
			int max = 56;
			int damage = max;
			if (victim instanceof Player) {
				int val = victim.getDragonfireProtection(true);
				if ((val & 0x2) != 0) {
					damage *= 0.5;
				}
				if ((val & 0x4) != 0) {
					damage -= (int) (damage * 0.85);
				}
				if ((val & 0x6) != 0) {
					damage *= 0.6;
				}
				if (damage < 3) {
					damage = 3;
				}
			}
			if (fireType != FireType.FIERY_BREATH) {
				damage += 11;
			}
			return damage;
		}

		@Override
		public InteractionType canSwing(Entity entity, Entity victim) {
			if (!isProjectileClipped(entity, victim, false)) {
				return InteractionType.NO_INTERACT;
			}
			if (victim.getCenterLocation().withinDistance(entity.getCenterLocation(), getCombatDistance(entity, victim, 9)) && super.canSwing(entity, victim) != InteractionType.NO_INTERACT) {
				entity.getWalkingQueue().reset();
				return InteractionType.STILL_INTERACT;
			}
			return InteractionType.NO_INTERACT;
		}

		@Override
		public ArmourSet getArmourSet(Entity e) {
			return style.getSwingHandler().getArmourSet(e);
		}

		@Override
		public double getSetMultiplier(Entity e, int skillId) {
			return style.getSwingHandler().getSetMultiplier(e, skillId);
		}

		@Override
		public void impact(Entity entity, Entity victim, BattleState state) {
			style.getSwingHandler().impact(entity, victim, state);
		}

		@Override
		public int swing(Entity entity, Entity victim, BattleState state) {
			style = CombatStyle.RANGE;
			int hit = 0;
			int ticks = 1;
			if (victim.getCenterLocation().withinDistance(entity.getCenterLocation(), getCombatDistance(entity, victim, 1)) && RandomFunction.random(10) < 7) {
				style = CombatStyle.MELEE;
			} else {
				ticks += (int) Math.ceil(entity.getLocation().getDistance(victim.getLocation()) * 0.3);
			}
			state.setStyle(style);
			if (isAccurateImpact(entity, victim)) {
				int max = calculateHit(entity, victim, 1.0);
				state.setMaximumHit(max);
				hit = RandomFunction.random(max);
			}
			state.setEstimatedHit(hit);
			return ticks;
		}

		@Override
		public void visualize(Entity entity, Entity victim, BattleState state) {
			switch (style) {
			case MELEE:
				entity.animate(MELEE_ATTACK);
				break;
			case RANGE:
				Projectile.ranged(entity, victim, 450, 20, 36, 50, 15).send();
				entity.animate(fireType.getAnimation());
				break;
			default:
				break;
			}
		}

		@Override
		public void visualizeImpact(Entity entity, Entity victim, BattleState state) {
			style.getSwingHandler().visualizeImpact(entity, victim, state);
			if (style != CombatStyle.MELEE && victim instanceof Player) {
				Player p = (Player) victim;
				p.getPacketDispatch().sendMessage(getDragonfireMessage(victim.getAttribute("fire_resistance", 0), fireType.name().toLowerCase().replaceAll("_", " ")));
				p.graphics(new Graphics(346, 100, 1));
			}
		}

	}

}