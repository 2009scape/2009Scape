package plugin.activity.pestcontrol;

import java.util.ArrayList;
import java.util.List;

import plugin.ai.AIPlayer;
import plugin.ai.pvmbots.CombatBotAssembler;
import plugin.ai.AIPlayer.*;
import core.ServerConstants;
import core.game.component.Component;
import plugin.activity.ActivityManager;
import plugin.activity.ActivityPlugin;
import core.game.interaction.Option;
import core.game.node.Node;
import core.game.node.entity.Entity;
import core.game.node.entity.impl.PulseManager;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.info.Rights;
import core.game.node.entity.state.EntityState;
import core.game.node.item.GroundItemManager;
import core.game.node.item.Item;
import core.game.system.task.Pulse;
import core.game.world.GameWorld;
import core.game.world.map.Location;
import core.game.world.map.build.DynamicRegion;
import core.game.world.map.zone.RegionZone;
import core.game.world.map.zone.ZoneBuilder;
import core.game.world.map.zone.ZoneRestriction;
import core.plugin.PluginManager;
import core.tools.RandomFunction;
import core.plugin.InitializablePlugin;
import core.tools.StringUtils;
import plugin.activity.pestcontrol.monsters.*;

/**
 * Handles the Pest Control activity.
 * @author Emperor
 */
@InitializablePlugin
public final class PestControlActivityPlugin extends ActivityPlugin {

	/**
	 * The minimum team size.
	 */
	protected static final int MIN_TEAM_SIZE = 5; //GameWorld.getSettings().isDevMode() ? 1 : 5;

	/**
	 * The maximum team size.
	 */
	protected static final int MAX_TEAM_SIZE = 25;

	/**
	 * The amount of ticks passed.
	 */
	private int ticks;

	/**
	 * The boat type.
	 */
	private final BoatType type;

	/**
	 * The waiting players.
	 */
	private final List<Player> waitingPlayers = new ArrayList<>();

	/**
	 * The active game sessions.
	 */
	private final List<PestControlSession> sessions = new ArrayList<>();

	/**
	 * The game updating pulse.
	 */
	private final Pulse pulse = new Pulse(1) {

		@Override
		public boolean pulse() {
			sessions.removeIf(session -> session != null && session.update());
			ticks++;
			if (waitingPlayers.size() >= MAX_TEAM_SIZE && ticks < 475)
			{
				ticks = 495;
			}
			if ((ticks < 450 && ticks % 100 == 0) || (ticks % 50 == 0) || ticks == 495) {
				for (Player p : waitingPlayers) {
					updateTime(p);
				}
			}
			if (ticks >= 500) {
				if (waitingPlayers.size() >= MIN_TEAM_SIZE) {
					PestControlActivityPlugin.this.start();
				} else {
					ticks = 400;
				}
			}
			return false;
		}
	};

	/**
	 * Starts a new pest control session.
	 */
	public void start() {
		PestControlSession session = new PestControlSession(DynamicRegion.create(10536), this);
		session.startGame(waitingPlayers);
		session.getRegion().getRegionZones().add(new RegionZone(this, session.getRegion().getBorders()));
		sessions.add(session);
		ticks = 0;
		updatePlayerCount();
	}

	/**
	 * Ends the pest control session.
	 * @param session The session to end.
	 * @param success If the mission was successful.
	 */
	public void end(PestControlSession session, boolean success) {
		if (!session.isActive()) {
			return;
		}
		for (final Player p : session.getRegion().getPlanes()[0].getPlayers()) {
			p.getProperties().setTeleportLocation(getLeaveLocation());
			if (!success) {
				p.getDialogueInterpreter().open(3781, true, 0, true);// default,
				// type,
				// default
			} else if (success && p.getAttribute("pc_zeal", 0) >= 50) {
				int amount = type.ordinal() + 2;
				p.getSavedData().getActivityData().increasePestPoints(amount);
				Item coins = new Item(995, p.getProperties().getCurrentCombatLevel() * 10);
				if (!p.getInventory().add(coins)) {
					GroundItemManager.create(coins, p);
				}
				// default, type, name
				p.getDialogueInterpreter().open(3781, true, 1, type.ordinal() == 0 ? "two" : type.ordinal() == 1 ? "three" : "four");
			} else {
				// default type, default
				p.getDialogueInterpreter().open(3781, true, 2, true);
			}
			p.removeAttribute("pc_zeal");
			p.removeExtension(PestControlSession.class);
			p.fullRestore();
			if (p.getStateManager().hasState(EntityState.POISONED)) {
				p.getStateManager().remove(EntityState.POISONED);
			}
			PulseManager.cancelDeathTask(p);
			GameWorld.getPulser().submit(new Pulse(1, p) {
				@Override
				public boolean pulse() {
					p.getSkills().restore();
					return true;
				}
			});
		}
		session.getRegion().getRegionZones().clear();
		session.setActive(false);
	}

	/**
	 * Gets the location the player should teleport to when leaving the game.
	 * @return {@code 
	 */
	public Location getLeaveLocation() {
		switch (type) {
		case NOVICE:
			return Location.create(2657, 2639, 0);
		case INTERMEDIATE:
			return Location.create(2644, 2644, 0);
		case VETERAN:
			return Location.create(2638, 2653, 0);
		}
		return ServerConstants.HOME_LOCATION;
	}

	/**
	 * Constructs a new {@code PestControlActivityPlugin} {@code Object}.
	 */
	public PestControlActivityPlugin() {
		this(BoatType.NOVICE);
	}

	/**
	 * Constructs a new {@code PestControlActivityPlugin} {@code Object}.
	 * @param type The boat type.
	 */
	public PestControlActivityPlugin(BoatType type) {
		super("pest control " + type.name().toLowerCase(), false, true, true, ZoneRestriction.CANNON);
		this.type = type;
	}

	@Override
	public boolean leave(Entity e, boolean logout) {
		if (e instanceof Player) {
			Player p = (Player) e;
			if (!logout) {
				p.getInterfaceManager().closeOverlay();
			} else {
				e.setLocation(getLeaveLocation());
				e.getProperties().setTeleportLocation(getLeaveLocation());
			}
			waitingPlayers.remove(p);
			updatePlayerCount();
		}
		return super.leave(e, logout);
	}

	@Override
	public void register() {
		if (type == BoatType.NOVICE) {
			PestControlActivityPlugin[] activities = new PestControlActivityPlugin[] { this, new PestControlActivityPlugin(BoatType.INTERMEDIATE), new PestControlActivityPlugin(BoatType.VETERAN) };
			ActivityManager.register(activities[1]);
			ActivityManager.register(activities[2]);
			// Load abstract NPC plugins
			PluginManager.definePlugin(new PCPortalNPC());
			PluginManager.definePlugin(new PCSquireNPC());
			PluginManager.definePlugin(new PCTorcherNPC());
			PluginManager.definePlugin(new PCDefilerNPC());
			PluginManager.definePlugin(new PCRavagerNPC());
			PluginManager.definePlugin(new PCShifterNPC());
			PluginManager.definePlugin(new PCSplatterNPC());
			PluginManager.definePlugin(new PCSpinnerNPC());
			PluginManager.definePlugin(new PCObjectHandler());
			PluginManager.definePlugin(new PestControlSquire());
			PluginManager.definePlugin(new VoidSealPlugin());
			ZoneBuilder.configure(new PCLanderZone(activities));
			ZoneBuilder.configure(new PCIslandZone());
		}
		pulse.start();
		GameWorld.getPulser().submit(pulse);
	}

	@Override
	public boolean interact(Entity e, Node target, Option option) {
		return super.interact(e, target, option);
	}

	@Override
	public boolean start(Player p, boolean login, Object... args) {
		if (p.getProperties().getCurrentCombatLevel() < type.getRequirement() && p.getRights() != Rights.ADMINISTRATOR) {
			p.getPacketDispatch().sendMessage("You need a combat level of " + type.getRequirement() + " or higher to board this lander.");
			return false;
		}
		waitingPlayers.add(p);
		openLanderInterface(p);
		return true;
	}

	/**
	 * Updates the lander interface.
	 * @param p The player.
	 */
	public void openLanderInterface(Player p) {
		p.getInterfaceManager().openOverlay(new Component(407));
		updateTime(p);
		updatePlayerCount();
		p.getPacketDispatch().sendString("Points: " + p.getSavedData().getActivityData().getPestPoints(), 407, 16);
		p.getPacketDispatch().sendString(StringUtils.formatDisplayName(type.name()), 407, 3);
	}

	/**
	 * Updates the current time left.
	 * @param p The player.
	 */
	public void updateTime(Player p) {
		int ticks = 500 - this.ticks;
		if (ticks > 99) {
			p.getPacketDispatch().sendString("Next Departure: " + (ticks / 100) + " min", 407, 13);
		} else if (ticks > 50) {
			p.getPacketDispatch().sendString("Next Departure: 1 min", 407, 13);
		} else {
			p.getPacketDispatch().sendString("Next Departure: 30 seconds", 407, 13);
		}
	}

	/**
	 * Updates the player count for all players in the lander.
	 */
	public void updatePlayerCount() {
		for (Player p : waitingPlayers) {
			p.getPacketDispatch().sendString("Players Ready: " + waitingPlayers.size(), 407, 14);
		}
	}

	@Override
	public boolean death(Entity e, Entity killer) {
		if (e instanceof Player && e.getViewport().getRegion().getRegionId() == 10536) {
			PestControlSession session = e.getExtension(PestControlSession.class);
			if (session != null) {
				Location l = session.getRegion().getBaseLocation();
				e.getProperties().setTeleportLocation(l.transform(32 + RandomFunction.RANDOM.nextInt(4), 49 + RandomFunction.RANDOM.nextInt(6), 0));
				return true;
			}
		}
		return super.death(e, killer);
	}

	@Override
	public ActivityPlugin newInstance(Player p) throws Throwable {
		return this;
	}

	@Override
	public Location getSpawnLocation() {
		return ServerConstants.HOME_LOCATION;
	}

	@Override
	public void configure() {
		registerRegion(10536);
	}

	/**
	 * Gets the list of waiting players.
	 * @return The list of waiting players.
	 */
	public List<Player> getWaitingPlayers() {
		return waitingPlayers;
	}

	/**
	 * Gets the boat type.
	 * @return The boat type.
	 */
	public BoatType getType() {
		return type;
	}

	/**
	 * The boat types.
	 * @author Emperor
	 */
	public static enum BoatType {

		NOVICE(40), INTERMEDIATE(70), VETERAN(100);

		/**
		 * The combat level requirement.
		 */
		private final int requirement;

		/**
		 * Constructs a new {@code BoatType} {@code Object}.
		 * @param requirement The combat level requirement.
		 */
		private BoatType(int requirement) {
			this.requirement = requirement;
		}

		/**
		 * Gets the requirement.
		 * @return The requirement.
		 */
		public int getRequirement() {
			return requirement;
		}
	}
}
