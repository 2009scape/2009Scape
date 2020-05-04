package org.crandor.game.node.entity.combat.handlers;

import org.crandor.game.container.Container;
import org.crandor.game.container.impl.EquipmentContainer;
import org.crandor.game.content.global.SkillcapePerks;
import org.crandor.game.content.global.tutorial.TutorialSession;
import org.crandor.game.content.global.tutorial.TutorialStage;
import org.crandor.game.content.skill.Skills;
import org.crandor.game.content.skill.member.summoning.familiar.Familiar;
import org.crandor.game.node.entity.Entity;
import org.crandor.game.node.entity.combat.BattleState;
import org.crandor.game.node.entity.combat.CombatStyle;
import org.crandor.game.node.entity.combat.CombatSwingHandler;
import org.crandor.game.node.entity.combat.InteractionType;
import org.crandor.game.node.entity.combat.equipment.*;
import org.crandor.game.node.entity.impl.Projectile;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.entity.player.info.portal.Perks;
import org.crandor.game.node.entity.state.EntityState;
import org.crandor.game.node.item.GroundItem;
import org.crandor.game.node.item.GroundItemManager;
import org.crandor.game.node.item.Item;
import org.crandor.game.system.task.Pulse;
import org.crandor.game.world.GameWorld;
import org.crandor.game.world.map.Location;
import org.crandor.game.world.map.RegionManager;
import org.crandor.game.world.update.flag.context.Graphics;
import org.crandor.tools.RandomFunction;

import java.util.ArrayList;

/**
 * Handles the range combat swings.
 * @author Emperor
 */
public class RangeSwingHandler extends CombatSwingHandler {

	/**
	 * Constructs a new {@code RangeSwingHandler} {@Code Object}.
	 */
	public RangeSwingHandler() {
		super(CombatStyle.RANGE);
	}

	@Override
	public InteractionType canSwing(Entity entity, Entity victim) {
		if (!isProjectileClipped(entity, victim, false)) {
			return InteractionType.NO_INTERACT;
		}
		if (entity.getAttribute("tut-island", false)) {
			if (TutorialSession.getExtension(((Player) entity)).getStage() == 70) {
				return InteractionType.NO_INTERACT;
			}
		}
		int distance = 7;
		if (entity instanceof Player && ((WeaponInterface) entity.getExtension(WeaponInterface.class)).getWeaponInterface().getInterfaceId() == 91) {
			distance -= 2;
		}
		if (entity.getProperties().getAttackStyle().getStyle() == WeaponInterface.STYLE_LONG_RANGE) {
			distance += 2;
		}
		boolean goodRange = victim.getCenterLocation().withinDistance(entity.getCenterLocation(), getCombatDistance(entity, victim, distance));
		InteractionType type = InteractionType.STILL_INTERACT;
		if (victim.getWalkingQueue().isMoving() && !goodRange) {
			goodRange = victim.getCenterLocation().withinDistance(entity.getCenterLocation(), getCombatDistance(entity, victim, ++distance));
			type = InteractionType.MOVE_INTERACT;
		}
		if (goodRange && super.canSwing(entity, victim) != InteractionType.NO_INTERACT) {
			if (type == InteractionType.STILL_INTERACT) {
				entity.getWalkingQueue().reset();
			}
			return type;
		}
		return InteractionType.NO_INTERACT;
	}

	@Override
	public int swing(Entity entity, Entity victim, BattleState state) {
		configureRangeData(entity, state);
		if (state.getWeapon() == null || !hasAmmo(entity, state)) {
			entity.getProperties().getCombatPulse().stop();
			return -1;
		}
		int hit = 0;
		if (isAccurateImpact(entity, victim, CombatStyle.RANGE)) {
			int max = calculateHit(entity, victim, 1.0);
			state.setMaximumHit(max);
			hit = RandomFunction.random(max);
		}
		state.setEstimatedHit(hit);
		if (state.getWeapon().getType() == Weapon.WeaponType.DOUBLE_SHOT) {
			if (isAccurateImpact(entity, victim, CombatStyle.RANGE)) {
				hit = RandomFunction.random(calculateHit(entity, victim, 1.0));
			}
			state.setSecondaryHit(hit);
		}
		if (entity == null || state == null || victim.getLocation() == null) {
			return -1;
		}
		useAmmo(entity, state, victim.getLocation());
		return 1 + (int) Math.ceil(entity.getLocation().getDistance(victim.getLocation()) * 0.3);
	}

	/**
	 * Configures the range data.
	 * @param entity The entity.
	 * @param state The battle state.
	 */
	public void configureRangeData(Entity entity, BattleState state) {
		state.setStyle(CombatStyle.RANGE);
		Weapon w = null;
		if (entity instanceof Player) {
			Player p = (Player) entity;
			RangeWeapon rw = RangeWeapon.get(p.getEquipment().getNew(3).getId());
			if (rw == null) {
				System.err.println("Unhandled range weapon used - [item id=" + p.getEquipment().getNew(3).getId() + "].");
				return;
			}
			w = new Weapon(p.getEquipment().get(3), rw.getAmmunitionSlot(), p.getEquipment().getNew(rw.getAmmunitionSlot()));
			w.setType(rw.getWeaponType());
			state.setRangeWeapon(rw);
			state.setAmmunition(Ammunition.get(w.getAmmunition().getId()));
		} else {
			w = new Weapon(null);
		}
		state.setWeapon(w);
	}

	@Override
	public void adjustBattleState(Entity entity, Entity victim, BattleState state) {
		if (state.getAmmunition() != null && entity instanceof Player) {
			int damage = state.getAmmunition().getPoisonDamage();
			if (state.getEstimatedHit() > 0 && damage > 8 && RandomFunction.random(10) < 4) {
				victim.getStateManager().register(EntityState.POISONED, false, damage, entity);
			}
		}
		super.adjustBattleState(entity, victim, state);
	}

	@Override
	public void addExperience(Entity entity, Entity victim, BattleState state) {
		if (entity instanceof Player) {
			if (victim instanceof Player && entity.asPlayer().getIronmanManager().isIronman()) {
				return;
			}
			int hit = state.getEstimatedHit();
			if (hit < 0) {
				hit = 0;
			}
			if (state.getSecondaryHit() > 0) {
				hit += state.getSecondaryHit();
			}
			Player p = entity.asPlayer();
			boolean famExp = entity.getAttribute("fam-exp", false) && p.getFamiliarManager().hasFamiliar();
			if (famExp) {
				Familiar fam = p.getFamiliarManager().getFamiliar();
				int skill = Skills.RANGE;
				switch (fam.getAttackStyle()) {
				case WeaponInterface.STYLE_DEFENSIVE:
					skill = Skills.DEFENCE;
					break;
				case WeaponInterface.STYLE_ACCURATE:
					skill = Skills.ATTACK;
					break;
				case WeaponInterface.STYLE_AGGRESSIVE:
					skill = Skills.STRENGTH;
					break;
				case WeaponInterface.STYLE_RANGE_ACCURATE:
					skill = Skills.RANGE;
					break;
				case WeaponInterface.STYLE_CONTROLLED:
					double experience = hit * EXPERIENCE_MOD;
					experience /= 3;
					entity.getSkills().addExperience(Skills.ATTACK, experience, true);
					entity.getSkills().addExperience(Skills.STRENGTH, experience, true);
					entity.getSkills().addExperience(Skills.DEFENCE, experience, true);
					return;
				case WeaponInterface.STYLE_CAST:
					skill = Skills.MAGIC;
					break;
				}
				entity.getSkills().addExperience(skill, hit * EXPERIENCE_MOD, true);
				return;
			}
			entity.getSkills().addExperience(Skills.HITPOINTS, hit * 1.33, true);
			if (entity.getProperties().getAttackStyle().getStyle() == WeaponInterface.STYLE_LONG_RANGE) {
				entity.getSkills().addExperience(Skills.RANGE, hit * (EXPERIENCE_MOD / 2), true);
				entity.getSkills().addExperience(Skills.DEFENCE, hit * (EXPERIENCE_MOD / 2), true);
			} else {
				entity.getSkills().addExperience(Skills.RANGE, hit * EXPERIENCE_MOD, true);
			}
		}
	}

	/**
	 * Checks if the entity has the ammunition needed to proceed.
	 * @param e The entity.
	 * @param state The battle state.
	 * @return {@code True} if so.
	 */
	public static boolean hasAmmo(Entity e, BattleState state) {
		if (!(e instanceof Player)) {
			return true;
		}
		Player p = (Player) e;
		Weapon.WeaponType type = state.getWeapon().getType();
		int amount = type == Weapon.WeaponType.DOUBLE_SHOT ? 2 : 1;
		if (type == Weapon.WeaponType.DEGRADING) {
			return true;
		}
		Item item = p.getEquipment().get(state.getWeapon().getAmmunitionSlot());
		if (item != null && item.getAmount() >= amount) {
			if (!state.getRangeWeapon().getAmmunition().contains(item.getId())) {
				p.getPacketDispatch().sendMessage("You can't use this type of ammunition with this bow.");
				return false;
			}
			return true;
		}
		if (type == Weapon.WeaponType.DOUBLE_SHOT) {
			state.getWeapon().setType(Weapon.WeaponType.DEFAULT);
			return hasAmmo(e, state);
		}
		p.getPacketDispatch().sendMessage("You do not have enough ammo left.");
		return false;
	}

	/**
	 * Uses the ammunition for the range weapon.
	 * @param e The entity.
	 * @param state The battle state.
	 * @param dropLocation The drop location.
	 */
	@SuppressWarnings("unchecked")
	public static void useAmmo(Entity e, BattleState state, Location dropLocation) {
		if (!(e instanceof Player)) {
			return;
		}
		Player p = (Player) e;
		Weapon.WeaponType type = state.getWeapon().getType();
		int amount = type == Weapon.WeaponType.DOUBLE_SHOT ? 2 : 1;
		if (type == Weapon.WeaponType.DEGRADING) {
			degrade(p, state, amount);
			return;
		}
		Item ammo = state.getWeapon().getAmmunition();
		if (state.getWeapon().getAmmunitionSlot() == -1 || ammo == null) {
			return;
		}
		double dropRate = getDropRate(e);
		if (dropRate == -1) {
			return;
		}
		if (dropLocation == null) {
			return;
		}
		int flag = RegionManager.getClippingFlag(dropLocation);
		if ((flag & 0x200000) != 0) {//Water
			dropLocation = null;
		}
		p.getEquipment().replace(new Item(ammo.getId(), ammo.getAmount() - amount, ammo.getCharge()), state.getWeapon().getAmmunitionSlot());
		if (dropLocation != null && state.getRangeWeapon().isDropAmmo()) {
			double rate = 5 * (1.0 + p.getSkills().getLevel(Skills.RANGE) * 0.01) * dropRate;
			if (RandomFunction.randomize((int) rate) != 0) {
				GroundItem drop = GroundItemManager.increase(new Item(ammo.getId(), amount), dropLocation, p);
				if (drop != null) {
					if (e.getAttribute("duel:ammo", null) != null) {
						((ArrayList<GroundItem>) e.getAttribute("duel:ammo")).add(drop);
					}
				}
			}
		}
		if (p.getEquipment().get(state.getRangeWeapon().getAmmunitionSlot()) == null) {
			p.getPacketDispatch().sendMessage("You have no ammo left in your quiver!");
		}
	}

	/**
	 * Checks if ammunition should be saved.
	 * @param e The entity.
	 * @return {@code True} if so.
	 */
	public static double getDropRate(Entity e) {
		if (e instanceof Player) {
			Player player = (Player) e;
			Item cape = player.getEquipment().get(EquipmentContainer.SLOT_CAPE);
			Item weapon = player.getEquipment().get(EquipmentContainer.SLOT_WEAPON);
			if (cape != null && (cape.getId() == 10498 || cape.getId() == 10499 || SkillcapePerks.hasSkillcapePerk(player, SkillcapePerks.RANGING)) && (weapon != null && weapon.getId() != 10034) && weapon.getId() != 10033) {
				int rate = (cape.getId() == 10498 || SkillcapePerks.hasSkillcapePerk(player, SkillcapePerks.RANGING)) ? 70 : 80;
				if (RandomFunction.random(100) < rate) {
					Item torso = player.getEquipment().get(EquipmentContainer.SLOT_CHEST);
					int modelId = torso == null ? -1 : torso.getDefinition().getMaleWornModelId1();
					if (modelId == 301 || modelId == 306 || modelId == 3379) {
						player.getPacketDispatch().sendMessage("Your armour interferes with Ava's device.");
						return 1.0;
					}
					return -1;
				}
				return 0.33;
			}
		}
		return 1.0;
	}

	/**
	 * Degrades the player's range weapon used.
	 * @param p The player.
	 * @param state The battle state.
	 * @param amount The amount of shots to degrade.
	 */
	private static void degrade(Player p, BattleState state, int amount) {
		if (p.hasPerk(Perks.UNBREAKABLE_CRYSTAL)) {
			return;
		}
		if (state.getWeapon().getItem().getId() == 4212) { // New crystal bow.
			p.getPacketDispatch().sendMessage("Your crystal bow has degraded!");
			p.getEquipment().replace(new Item(4214, 1, 996), 3);
			return;
		}
		int charge = state.getWeapon().getItem().getCharge() - (amount * 4);
		state.getWeapon().getItem().setCharge(charge);
		if (charge < 1) {
			int id = state.getWeapon().getId() + 1;
			if (id < 4224) {
				p.getPacketDispatch().sendMessage("Your crystal bow has degraded!");
				p.getEquipment().replace(new Item(id, 1, 999), 3);
			} else {
				Item replace = null;
				if (!p.getInventory().add(new Item(4207))) {
					replace = new Item(4207);
				}
				p.getEquipment().replace(replace, 3);
				p.getPacketDispatch().sendMessage("Your crystal bow has degraded to a small crystal seed.");
			}
		}
	}

	@Override
	public void visualize(Entity entity, Entity victim, BattleState state) {
		Graphics start = null;
		if (state.getAmmunition() != null) {
			start = state.getAmmunition().getStartGraphics();
			state.getAmmunition().getProjectile().copy(entity, victim, 5).send();
			if (state.getWeapon().getType() == Weapon.WeaponType.DOUBLE_SHOT && state.getAmmunition().getDarkBowGraphics() != null) {
				start = state.getAmmunition().getDarkBowGraphics();
				int speed = (int) (55 + (entity.getLocation().getDistance(victim.getLocation()) * 10));
				Projectile.create(entity, victim, state.getAmmunition().getProjectile().getProjectileId(), 40, 36, 41, speed, 25).send();
			}
		} else if (entity instanceof NPC) {
			NPC n = (NPC) entity;
			if (n.getDefinition().getCombatGraphics()[0] != null) {
				start = n.getDefinition().getCombatGraphics()[0];
			}
			Graphics g = n.getDefinition().getCombatGraphics()[1];
			if (g != null) {
				Projectile.ranged(entity, victim, g.getId(), g.getHeight(), 36, 41, 5).send();
			}
		}
		RangeWeapon weapon;
		int anim = entity.getProperties().getAttackAnimation().getId();
		if ((anim == 422 || anim == 423) && state.getWeapon().getId() > 0 && (weapon = RangeWeapon.get(state.getWeapon().getId())) != null) {
			entity.visualize(weapon.getAnimation(), start);
			return;
		}
		entity.visualize(entity.getProperties().getAttackAnimation(), start);
	}

	@Override
	public void impact(final Entity entity, final Entity victim, final BattleState state) {
		if (state.getAmmunition() != null && state.getAmmunition().getEffect() != null && state.getAmmunition().getEffect().canFire(state)) {
			state.getAmmunition().getEffect().impact(state);
		}
		int hit = state.getEstimatedHit();
		if (entity instanceof Player) {
			if (TutorialSession.getExtension(((Player) entity)).getStage() == 51) {
				TutorialStage.load(((Player) entity), 52, false);
			}
			if (TutorialSession.getExtension(((Player) entity)).getStage() == 52) {
				TutorialStage.load(((Player) entity), 53, false);
			}
		}
		victim.getImpactHandler().handleImpact(entity, hit, CombatStyle.RANGE, state);
		if (state.getSecondaryHit() > -1) {
			final int hitt = state.getSecondaryHit();
			GameWorld.Pulser.submit(new Pulse(1, victim) {
				@Override
				public boolean pulse() {
					victim.getImpactHandler().handleImpact(entity, hitt, CombatStyle.RANGE, state);
					return true;
				}
			});
		}
	}

	@Override
	public void visualizeImpact(Entity entity, Entity victim, BattleState state) {
		victim.animate(victim.getProperties().getDefenceAnimation());
	}

	@Override
	public int calculateAccuracy(Entity entity) {
		int baseLevel = entity.getSkills().getStaticLevel(Skills.RANGE);
		int weaponRequirement = baseLevel;
		if (entity instanceof Player) {
			Item weapon = ((Player) entity).getEquipment().get(3);
			if (weapon != null) {
				weaponRequirement = weapon.getDefinition().getRequirement(Skills.RANGE);
			} else {
				weaponRequirement = 1;
			}
		}
		double weaponBonus = 0.0;
		if (baseLevel > weaponRequirement) {
			weaponBonus = (baseLevel - weaponRequirement) * .3;
		}
		int level = entity.getSkills().getLevel(Skills.RANGE);
		double prayer = 1.0;
		if (entity instanceof Player) {
			prayer += ((Player) entity).getPrayer().getSkillBonus(Skills.RANGE);
		}
		double additional = 1.0; // Slayer helmet/salve/...
		int styleBonus = 0;
		if (entity.getProperties().getAttackStyle().getStyle() == WeaponInterface.STYLE_RANGE_ACCURATE) {
			styleBonus = 3;
		}
		double effective = Math.floor(((level * prayer) * additional) + styleBonus + weaponBonus);
		int bonus = entity.getProperties().getBonuses()[WeaponInterface.BONUS_RANGE];
		return (int) Math.floor(((effective + 8) * (bonus + 64)) / 10);
	}

	@Override
	public int calculateHit(Entity entity, Entity victim, double modifier) {
		int level = entity.getSkills().getLevel(Skills.RANGE);
		int bonus = entity.getProperties().getBonuses()[14];
		double prayer = 1.0;
		if (entity instanceof Player) {
			prayer += ((Player) entity).getPrayer().getSkillBonus(Skills.RANGE);
		}
		double cumulativeStr = Math.floor(level * prayer);
		if (entity.getProperties().getAttackStyle().getStyle() == WeaponInterface.STYLE_RANGE_ACCURATE) {
			cumulativeStr += 3;
		} else if (entity.getProperties().getAttackStyle().getStyle() == WeaponInterface.STYLE_LONG_RANGE) {
			cumulativeStr += 1;
		}
		cumulativeStr *= getSetMultiplier(entity, Skills.RANGE);
		return (int) ((14 + cumulativeStr + (bonus / 8) + ((cumulativeStr * bonus) * 0.016865)) * modifier) / 10 + 1;
	}

	@Override
	public int calculateDefence(Entity entity, Entity attacker) {
		WeaponInterface.AttackStyle style = entity.getProperties().getAttackStyle();
		int styleBonus = 0;
		if (style.getStyle() == WeaponInterface.STYLE_DEFENSIVE || style.getStyle() == WeaponInterface.STYLE_LONG_RANGE) {
			styleBonus = 3;
		} else if (style.getStyle() == WeaponInterface.STYLE_CONTROLLED) {
			styleBonus = 1;
		}
		int level = entity.getSkills().getLevel(Skills.DEFENCE);
		double prayer = 1.0;
		if (entity instanceof Player) {
			prayer += ((Player) entity).getPrayer().getSkillBonus(Skills.DEFENCE);
		}
		double effective = Math.floor((level * prayer) + styleBonus);
		int equipment = entity.getProperties().getBonuses()[WeaponInterface.BONUS_RANGE + 5];
		return (int) Math.floor(((effective + 8) * (equipment + 64)) / 10);
	}

	@Override
	public double getSetMultiplier(Entity e, int skillId) {
		if (e instanceof Player) {
			Container c = ((Player) e).getEquipment();
			if (containsVoidSet(c) && c.getNew(EquipmentContainer.SLOT_HAT).getId() == 11664) {
				return 1.1;
			}
		}
		return 1.0;
	}

	@Override
	public ArmourSet getArmourSet(Entity e) {
		if (ArmourSet.KARIL.isUsing(e)) {
			return ArmourSet.KARIL;
		}
		return super.getArmourSet(e);
	}

}