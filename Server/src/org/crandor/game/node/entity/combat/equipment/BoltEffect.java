package org.crandor.game.node.entity.combat.equipment;

import org.crandor.game.content.skill.Skills;
import org.crandor.game.node.entity.Entity;
import org.crandor.game.node.entity.combat.BattleState;
import org.crandor.game.node.entity.combat.ImpactHandler.HitsplatType;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.entity.player.link.audio.Audio;
import org.crandor.game.node.entity.state.EntityState;
import org.crandor.game.world.GameWorld;
import org.crandor.game.world.update.flag.context.Graphics;
import org.crandor.tools.RandomFunction;

/**
 * Represents a bolt effect.
 * @author Vexia
 * @author Aero
 */
public enum BoltEffect {
	OPAL(9236, Graphics.create(749), new Audio(2918)) {

		@Override
		public void impact(BattleState state) {
			state.setEstimatedHit(state.getEstimatedHit() + RandomFunction.random(3, 20));
			if (state.getEstimatedHit() > 29) {
				state.setEstimatedHit(21);
			}
			super.impact(state);
		}

	},
	JADE(9237, new Graphics(755), new Audio(2916)) {

		@Override
		public void impact(BattleState state) {
			if (state.getVictim() instanceof Player) {
				Player p = state.getVictim().asPlayer();
				p.lock(2);
			}
			super.impact(state);
		}

		@Override
		public boolean canFire(BattleState state) {
			boolean success = false;
			if (state.getVictim() instanceof Player) {
				Player p = state.getVictim().asPlayer();
				double level = p.getSkills().getLevel(Skills.AGILITY);
				double req = 80;
				double successChance = Math.ceil((level * 50 - req * 15) / req / 3 * 4);
				int roll = RandomFunction.random(99);
				success = successChance >= roll;
			}
			return super.canFire(state) && !success;
		}

	},
	PEARL(9238, Graphics.create(750), new Audio(2920)) {

		@Override
		public void impact(BattleState state) {
			state.setEstimatedHit(state.getEstimatedHit() + RandomFunction.random(3, 20));
			if (state.getEstimatedHit() > 29) {
				state.setEstimatedHit(21);
			}
			super.impact(state);
		}

		@Override
		public boolean canFire(BattleState state) {
			if (state.getVictim() instanceof Player) {
				if (state.getVictim().asPlayer().getEquipment().contains(1383, 1) || state.getVictim().asPlayer().getEquipment().contains(1395, 1)) {
					return false;
				}
			}
			return super.canFire(state);
		}
	},
	TOPAZ(9239, Graphics.create(757), new Audio(2914)) {
		@Override
		public void impact(BattleState state) {
			if (state.getVictim() instanceof Player) {
				Player p = state.getVictim().asPlayer();
				int level = (int) ((int) p.getSkills().getLevel(Skills.MAGIC) * 0.03);
				p.getSkills().updateLevel(Skills.MAGIC, -level, 0);
			}
			super.impact(state);
		}
	},
	SAPPHIRE(9240, new Graphics(759, 100), new Audio(2912)) {
		@Override
		public void impact(BattleState state) {
			if (state.getVictim() instanceof Player && state.getAttacker() instanceof Player) {
				Player p = state.getVictim().asPlayer();
				Player player = state.getAttacker().asPlayer();
				int give = (int) (p.getSkills().getPrayerPoints() * 0.05);
				if (give > 0) {
					p.getSkills().decrementPrayerPoints(give);
					player.getSkills().incrementPrayerPoints(give);
				}
			}
			super.impact(state);
		}
	},
	EMERALD(9241, new Graphics(752), new Audio(2919)) {
		@Override
		public void impact(BattleState state) {
			state.getVictim().getStateManager().register(EntityState.POISONED, false, 68, state.getAttacker());
			super.impact(state);
		}
	},
	RUBY(9242, new Graphics(754), new Audio(2915)) {

		@Override
		public void impact(BattleState state) {
			int victimPoints = (int) (state.getVictim().getSkills().getLifepoints() * 0.20);
			int playerPoints = (int) (state.getAttacker().getSkills().getLifepoints() * 0.10);
			if (victimPoints >= 35) {
				victimPoints = 35;
			}
			int total = state.getEstimatedHit() + victimPoints;
			if (total >= 35) {
				total = 35;
			}
			state.setEstimatedHit(total);
			state.getAttacker().getImpactHandler().manualHit(state.getVictim(), playerPoints, HitsplatType.NORMAL);
			super.impact(state);
		}

		@Override
		public boolean canFire(BattleState state) {
			int playerPoints = (int) (state.getAttacker().getSkills().getLifepoints() * 0.10);
			if (playerPoints < 1) {
				return false;
			}
			return super.canFire(state) && state.getAttacker().getSkills().getLifepoints() - playerPoints >= 1;
		}
	},
	DIAMOND(9243, new Graphics(758), new Audio(2913)) {
		@Override
		public void impact(BattleState state) {
			state.setEstimatedHit(state.getEstimatedHit() + RandomFunction.random(5, 14));
			super.impact(state);
		}
	},
	DRAGON(9244, new Graphics(756), new Audio(2915)) {

		@Override
		public void impact(BattleState state) {
			state.setEstimatedHit(state.getEstimatedHit() + RandomFunction.random(17, 29));
			super.impact(state);
		}

		@Override
		public boolean canFire(BattleState state) {
			if (state.getVictim() instanceof NPC) {
				NPC n = (NPC) state.getVictim();
				if (n.getName().toLowerCase().contains("fire") || n.getName().toLowerCase().contains("dragon")) {
					return false;
				}
			}
			if (state.getVictim() instanceof Player) {
				if (state.getVictim().asPlayer().getEquipment().contains(1540, 1) || state.getVictim().asPlayer().getEquipment().contains(11283, 1) || state.getVictim().hasFireResistance()) {
					return false;
				}
			}
			return super.canFire(state);
		}

	},
	ONYX(9245, new Graphics(753), new Audio(2917)) {

		@Override
		public void impact(BattleState state) {
			int newDamage = (int) (state.getEstimatedHit() * 0.25);
			state.setEstimatedHit(state.getEstimatedHit() + newDamage);
			state.getAttacker().getSkills().heal((int) (state.getEstimatedHit() * 0.25));
			state.getAttacker().setAttribute("onyx-effect", GameWorld.getTicks() + 12);
			super.impact(state);
		}

		@Override
		public boolean canFire(BattleState state) {
			if (state.getAttacker().getAttribute("onyx-effect", 0) > GameWorld.getTicks()) {
				return false;
			}
			if (state.getVictim() instanceof NPC) {
				NPC n = (NPC) state.getVictim();
				if (n.getTask() != null && n.getTask().undead) {
					return false;
				}
			}
			return super.canFire(state);
		}
	};

	/**
	 * The item id of the bolt.
	 */
	private final int itemId;

	/**
	 * The graphics to send.
	 */
	private final Graphics graphics;

	/**
	 * The sound to send.
	 */
	private final Audio sound;

	/**
	 * Constructs a new {@Code BoltEffect} {@Code Object}
	 * @param itemId the item id.
	 * @param graphics the graphics.
	 * @param sound the sound.
	 */
	private BoltEffect(int itemId, Graphics graphics, Audio sound) {
		this.itemId = itemId;
		this.graphics = graphics;
		this.sound = sound;
	}

	/**
	 * Constructs a new {@Code BoltEffect} {@Code Object}
	 * @param itemId the id.
	 * @param graphics the graphics.
	 */
	private BoltEffect(int itemId, Graphics graphics) {
		this(itemId, graphics, null);
	}

	/**
	 * Handles the impact.
	 * @param state the battle state.
	 */
	@SuppressWarnings("unused")
	public void impact(BattleState state) {
		Entity entity = state.getAttacker();
		Entity victim = state.getVictim();
		if (sound != null && victim != null && victim instanceof Player) {
			sound.send(victim.asPlayer(), true);
		}
		if (graphics != null) {
			victim.graphics(graphics);
		}
	}

	/**
	 * Checks if the effect can fire.
	 * @param state the state.
	 * @return {@code True} if so.
	 */
	public boolean canFire(BattleState state) {
		return RandomFunction.random(13) == 5;
	}

	/**
	 * Gets an effect by the id.
	 * @param id the id.
	 * @return the effect.
	 */
	public static BoltEffect forId(int id) {
		for (BoltEffect effect : values()) {
			if (effect.getItemId() == id) {
				return effect;
			}
		}
		return null;
	}

	/**
	 * Gets the itemId.
	 * @return the itemId
	 */
	public int getItemId() {
		return itemId;
	}

}
