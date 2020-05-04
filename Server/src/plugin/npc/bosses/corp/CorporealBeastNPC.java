package plugin.npc.bosses.corp;

import java.util.ArrayList;
import java.util.List;

import org.crandor.game.content.global.BossKillCounter;
import org.crandor.game.content.skill.Skills;
import org.crandor.game.node.entity.Entity;
import org.crandor.game.node.entity.combat.BattleState;
import org.crandor.game.node.entity.combat.CombatStyle;
import org.crandor.game.node.entity.combat.CombatSwingHandler;
import org.crandor.game.node.entity.combat.ImpactHandler.HitsplatType;
import org.crandor.game.node.entity.combat.equipment.SwitchAttack;
import org.crandor.game.node.entity.combat.handlers.MultiSwingHandler;
import org.crandor.game.node.entity.impl.Projectile;
import org.crandor.game.node.entity.npc.AbstractNPC;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.system.task.Pulse;
import org.crandor.game.world.GameWorld;
import org.crandor.game.world.map.Location;
import org.crandor.game.world.map.RegionManager;
import org.crandor.game.world.update.flag.context.Animation;
import org.crandor.game.world.update.flag.context.Graphics;
import org.crandor.plugin.Plugin;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.tools.RandomFunction;

/**
 * Handles the Corporeal beast NPC.
 * @author Emperor
 *
 */
@InitializablePlugin
public final class CorporealBeastNPC extends AbstractNPC {

	/**
	 * The combat handler.
	 */
	private final MultiSwingHandler combatHandler = new CombatHandler();
	
	/**
	 * The dark energy core NPC.
	 */
	private NPC darkEnergyCore;
	
	/**
	 * Constructs a new {@code CorporealBeastNPC} {@code Object}.
	 */
	public CorporealBeastNPC() {
		this(8133, Location.create(2993, 4380, 2));
	}
	
	/**
	 * Constructs a new {@code CorporealBeastNPC} {@code Object}.
	 * @param id The NPC id.
	 * @param location The location.
	 */
	public CorporealBeastNPC(int id, Location location) {
		super(id, location);
	}
	
	@Override
	public void init() {
		super.init();
		configureBossData();
	}
	
	@Override
	public void handleTickActions() {
		for(Player player : getViewport().getCurrentPlane().getPlayers()){
			if(player.getFamiliarManager().hasFamiliar() && RandomFunction.random(100) < 10){
				int heal = player.getFamiliarManager().getFamiliar().getSkills().getLifepoints() / 4;
				super.getSkills().heal(heal);
				player.sendMessage("<col=990000>Your familiar was devoured by the Corporeal Beast, healing it by "+heal+" HP.");
				player.getFamiliarManager().dismiss();
			}
		}
		if (getSkills().getLifepoints() < getSkills().getMaximumLifepoints()) {
			if (getViewport().getCurrentPlane().getPlayers().isEmpty()) {
				super.fullRestore();
				if (darkEnergyCore != null) {
					darkEnergyCore.clear();
					darkEnergyCore = null;
				}
			}
		}
		super.handleTickActions();
	}
	
	@Override
	public CombatSwingHandler getSwingHandler(boolean swing) {
		return combatHandler;
	}

	@Override
	public AbstractNPC construct(int id, Location location, Object... objects) {
		return new CorporealBeastNPC(id, location);
	}
	
	@Override
	public double getFormatedHit(BattleState state, int hit) {
		if (hit > 100) {
			hit = 100;
		}
		return super.getFormatedHit(state, hit);
	}

	@Override
	public int[] getIds() {
		return new int[] { 8133 };
	}	
	
	@Override
	public void finalizeDeath(Entity killer) {
		super.finalizeDeath(killer);
		BossKillCounter.addtoKillcount((Player) killer, this.getId());
		if (darkEnergyCore != null) {
			darkEnergyCore.clear();
			darkEnergyCore = null;
		}
	}
	
	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		init();
		return super.newInstance(arg);
	}

	/**
	 * Handles the Corporeal beast's combat.
	 * @author Emperor
	 *
	 */
	static class CombatHandler extends MultiSwingHandler {

		
		/**
		 * Constructs a new {@code CombatHandler} {@code Object}.
		 */
		public CombatHandler() {
			super(
					//Melee (crush)
					new SwitchAttack(CombatStyle.MELEE.getSwingHandler(), Animation.create(10057)).setMaximumHit(52),
					//Melee (slash)
					new SwitchAttack(CombatStyle.MELEE.getSwingHandler(), Animation.create(10058)).setMaximumHit(51),
					//Magic (drain skill)
					new SwitchAttack(CombatStyle.MAGIC.getSwingHandler(), Animation.create(10410), null, null, Projectile.create(null, null, 1823, 60, 36, 41, 46)).setMaximumHit(55),
					//Magic (location based)
					new SwitchAttack(CombatStyle.MAGIC.getSwingHandler(), Animation.create(10410), null, null, Projectile.create(null, null, 1824, 60, 36, 41, 46)).setMaximumHit(42),
					//Magic (hit through prayer)
					new SwitchAttack(CombatStyle.MAGIC.getSwingHandler(), Animation.create(10410), null, null, Projectile.create(null, null, 1825, 60, 36, 41, 46)).setMaximumHit(66)
					);
		}

		@Override
		public int swing(Entity entity, Entity victim, BattleState state) {
			spawnDarkCore((CorporealBeastNPC) entity, victim);
			if (doStompAttack(entity)) {
				entity.getProperties().getCombatPulse().setNextAttack(entity.getProperties().getAttackSpeed());
				return -1;
			}
			//Location based attack.
			if (super.next.getProjectile() != null && super.next.getProjectile().getProjectileId() == 1824) {
				current = next;
				CombatStyle style = current.getStyle();
				setType(style);
				int index = RandomFunction.randomize(super.getAttacks().length);
				SwitchAttack pick = getNext(entity, victim, state, index);
				next = pick;
				fireLocationBasedAttack(entity, victim.getLocation());
				entity.getProperties().getCombatPulse().setNextAttack(entity.getProperties().getAttackSpeed());
				return -1;
			}
			return super.swing(entity, victim, state);
		}
		
		/**
		 * Spawns a dark core.
		 * @param npc The corporeal beast NPC.
		 * @param victim The victim.
		 */
		private void spawnDarkCore(final CorporealBeastNPC npc, Entity victim) {
			if (npc.darkEnergyCore != null && npc.darkEnergyCore.isActive()) {
				return;
			}
			double max = npc.getSkills().getMaximumLifepoints() * (0.3 + (npc.getViewport().getCurrentPlane().getPlayers().size() * 0.05));
			if (npc.getSkills().getLifepoints() > max) {
				return;
			}
			Location l = RegionManager.getTeleportLocation(victim.getLocation(), 3);
			npc.darkEnergyCore = NPC.create(8127, l, npc);
			npc.darkEnergyCore.setActive(true);
			Projectile.create(npc.getLocation().transform(2, 2, 0), l, 1828, 60, 0, 0, 60, 20, 0).send();
			GameWorld.Pulser.submit(new Pulse(2, npc) {
				@Override
				public boolean pulse() {
					npc.darkEnergyCore.init();
					return true;
				}
			});
		}

		/**
		 * Fires the location based magic attack.
		 * @param entity The corporeal beast.
		 * @param location The location.
		 */
		private void fireLocationBasedAttack(final Entity entity, final Location location) {
			entity.animate(current.getAnimation());
			Projectile.create(entity, null, 1824, 60, 0, 41, 0).transform(entity, location, true, 46, 10).send();
			int ticks = 1 + (int) Math.ceil(entity.getLocation().getDistance(location) * 0.5);
			GameWorld.Pulser.submit(new Pulse(ticks) {
				boolean secondStage = false;
				List<Player> players = RegionManager.getLocalPlayers(entity);
				Location[] locations = null;
				@Override
				public boolean pulse() {
					if (!secondStage) {
						for (Player p : players) {
							if (p.getLocation().equals(location)) {
								hit(p);
							}
						}
						locations = new Location[4 + RandomFunction.random(3)];
						for (int i = 0; i < locations.length; i++) {
							locations[i] = location.transform(-2 + RandomFunction.random(5), -2 + RandomFunction.random(5), 0);
							Projectile.create(location, locations[i], 1824, 60, 0, 25, 56, 0, 0).send();
						}
						setDelay(2);
						secondStage = true;
						return false;
					}
					for (int i = 0; i < locations.length; i++) {
						Location l = locations[i];
						Graphics.send(Graphics.create(1806), l.transform(-1, -1, 0));
						for (Player p : players) {
							if (p.getLocation().equals(l)) {
								hit(p);
							}
						}
					}
					players.clear();
					players = null;
					locations = null;
					return true;
				}
				private void hit(Player p) {
					int max = p.hasProtectionPrayer(CombatStyle.MAGIC) ? 13 : 42;
					int hit = 0;
					if (isAccurateImpact(entity, p)) {
						hit = RandomFunction.random(max);
					}
					p.getImpactHandler().handleImpact(entity, hit, CombatStyle.MAGIC);
				}
			});
		}

		/**
		 * Attempts to do a stomp attack if needed.
		 * @param entity The corporeal beast.
		 * @return {@code True} if a stomp attack is being done.
		 */
		public boolean doStompAttack(Entity entity) {
			Location l = entity.getLocation();
			List<Player> stompTargets = null;
			for (Player player : RegionManager.getLocalPlayers(entity, 5)) {
				Location p = player.getLocation();
				if (p.getX() >= l.getX() && p.getY() >= l.getY() && p.getX() < l.getX() + entity.size() && p.getY() < l.getY() + entity.size()) {
					if (stompTargets == null) {
						stompTargets = new ArrayList<>();
					}
					stompTargets.add(player);
				}
			}
			if (stompTargets != null) {
				entity.visualize(Animation.create(10496), Graphics.create(1834));
				for (Player p : stompTargets) {
					p.getImpactHandler().manualHit(entity, RandomFunction.random(52), HitsplatType.NORMAL, 1);
				}
				return true;
			}
			return false;
		}
		
		@Override
		public void adjustBattleState(Entity entity, Entity victim, BattleState state) {
			super.adjustBattleState(entity, victim, state);
			if (current.getProjectile() != null && current.getProjectile().getProjectileId() == 1823) {
				if (state.getEstimatedHit() > 0) {
					int random = RandomFunction.random(3);
					int skill = random == 0 ? Skills.PRAYER : random == 1 ? Skills.MAGIC : Skills.SUMMONING;
					int drain = 1 + RandomFunction.random(6);
					if ((skill == Skills.PRAYER ? victim.getSkills().getPrayerPoints() : victim.getSkills().getLevel(skill)) < 1) {
						victim.getImpactHandler().manualHit(entity, drain, HitsplatType.NORMAL,2);
						((Player) victim).getPacketDispatch().sendMessage("Your Hitpoints have been slightly drained!");
					}
					else {
						if (skill == Skills.PRAYER) {
							victim.getSkills().decrementPrayerPoints(drain);
						} else {
							victim.getSkills().updateLevel(skill, -drain, 0);
						}
						if (victim instanceof Player) {
							((Player) victim).getPacketDispatch().sendMessage("Your " + Skills.SKILL_NAME[skill] + " has been slightly drained!");
						}
					}
				}
			}
		}
		
		@Override
		protected int getFormatedHit(Entity entity, Entity victim, BattleState state, int hit) {
			if (current.getProjectile() == null || current.getProjectile().getProjectileId() != 1825) {
				hit = (int) entity.getFormatedHit(state, hit);
			}
			return formatHit(entity, victim, hit);
		}
	}
}
