package plugin.activity.clanwars;

import java.util.ArrayList;
import java.util.List;

import org.crandor.game.component.Component;
import org.crandor.game.content.activity.ActivityPlugin;
import org.crandor.game.content.skill.member.summoning.familiar.Familiar;
import org.crandor.game.content.skill.member.summoning.pet.Pet;
import org.crandor.game.interaction.Option;
import org.crandor.game.interaction.OptionHandler;
import org.crandor.game.node.Node;
import org.crandor.game.node.entity.Entity;
import org.crandor.game.node.entity.combat.CombatStyle;
import org.crandor.game.node.entity.impl.PulseManager;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.entity.player.link.ConfigurationManager.Configuration;
import org.crandor.game.node.object.GameObject;
import org.crandor.game.node.object.ObjectBuilder;
import org.crandor.game.system.communication.ClanEntry;
import org.crandor.game.system.communication.ClanRepository;
import org.crandor.game.system.task.Pulse;
import org.crandor.game.world.GameWorld;
import org.crandor.game.world.map.Location;
import org.crandor.game.world.map.RegionManager;
import org.crandor.game.world.map.build.DynamicRegion;
import org.crandor.game.world.map.zone.RegionZone;
import org.crandor.game.world.map.zone.ZoneBorders;
import org.crandor.game.world.map.zone.ZoneType;
import org.crandor.game.world.map.zone.impl.MultiwayCombatZone;
import org.crandor.game.world.update.flag.chunk.AnimateObjectUpdateFlag;
import org.crandor.game.world.update.flag.context.Animation;
import org.crandor.plugin.Plugin;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.tools.RandomFunction;

/**
 * Handles the clan wars activity.
 * @author Emperor
 */
@InitializablePlugin
public final class ClanWarsActivityPlugin extends ActivityPlugin {

	/**
	 * The first clan.
	 */
	private ClanRepository firstClan;

	/**
	 * The second clan.
	 */
	private ClanRepository secondClan;

	/**
	 * The first clan's players.
	 */
	private List<Player> firstClanPlayers = new ArrayList<>();

	/**
	 * The second clan's players.
	 */
	private List<Player> secondClanPlayers = new ArrayList<>();

	/**
	 * The list of viewing players.
	 */
	private List<Player> viewingPlayers = new ArrayList<>();

	/**
	 * The amount of ticks.
	 */
	private int ticks;

	/**
	 * The updating pulse.
	 */
	private Pulse pulse;

	/**
	 * The attack option.
	 */
	private static final Option ATTACK_OPTION = new Option("Attack", 0).setHandler(new OptionHandler() {

		@Override
		public boolean handle(Player player, Node node, String option) {
			player.getPulseManager().clear("interaction:attack:" + node.hashCode());
			player.getProperties().getCombatPulse().attack(node);
			return true;
		}

		@Override
		public boolean isWalk() {
			return false;
		}

		@Override
		public Plugin<Object> newInstance(Object arg) throws Throwable {
			return this;
		}

		@Override
		public boolean isDelayed(Player player) {
			return false;
		}

	});

	/**
	 * Constructs a new {@code ClanWarsActivityPlugin} {@code Object}.
	 */
	public ClanWarsActivityPlugin() {
		super("Clan wars", true, false, true);
	}

	@Override
	public boolean start(Player player, boolean login, Object... args) {
		Player other = (Player) args[0];
		firstClan = player.getCommunication().getClan();
		secondClan = other.getCommunication().getClan();
		firstClan.setClanWar(this);
		secondClan.setClanWar(this);
		sendWarDeclaration(firstClan.getPlayers());
		sendWarDeclaration(secondClan.getPlayers());
		handleWall();
		join(player);
		join(other);
		return true;
	}

	/**
	 * Handles the wall in the middle of the battlefield.
	 */
	private void handleWall() {
		int offset = 0;
		for (int x = 5; x < 54; x++) {
			offset = (offset + 1) % 3;
			ObjectBuilder.add(new GameObject(28174 + offset, base.transform(x, 64, 0)));
		}
		GameWorld.Pulser.submit(pulse = new Pulse(200) {
			@Override
			public boolean pulse() {
				for (int x = 5; x < 54; x++) {
					Location l = base.transform(x, 64, 0);
					GameObject object = RegionManager.getObject(l);
					if (object != null) {
						Animation anim = Animation.create(7386 + ((object.getId() - 28174) % 3));
						anim.setObject(object);
						RegionManager.getRegionChunk(l).flag(new AnimateObjectUpdateFlag(anim));
					}
				}
				GameWorld.Pulser.submit(new Pulse(5) {
					@Override
					public boolean pulse() {
						for (int x = 5; x < 54; x++) {
							Location l = base.transform(x, 64, 0);
							GameObject object = RegionManager.getObject(l);
							if (object != null) {
								ObjectBuilder.remove(object);
							}
						}
						return true;
					}
				});
				super.setTicksPassed(250);
				sendGameData();
				if (firstClanPlayers.isEmpty() || secondClanPlayers.isEmpty()) {
					finishWar();
				}
				return true;
			}
		});
	}

	/**
	 * Ends the war.
	 */
	public void finishWar() {
		firstClan.setClanWar(null);
		secondClan.setClanWar(null);
		int firstInterfaceId = firstClanPlayers.isEmpty() ? 650 : 651;
		int secondInterfaceId = firstClanPlayers.isEmpty() ? 651 : 650;
		String[] message = new String[] { "Your clan has been defeated!", "Your clan is victorious!" };
		for (Player p : firstClanPlayers) {
			p.getProperties().setTeleportLocation(getLeaveLocation());
			p.getInterfaceManager().openComponent(firstInterfaceId);
			p.getPacketDispatch().sendMessage(message[firstInterfaceId - 650]);
			p.fullRestore();
			PulseManager.cancelDeathTask(p);
		}
		for (Player p : secondClanPlayers) {
			p.getProperties().setTeleportLocation(getLeaveLocation());
			p.getInterfaceManager().openComponent(secondInterfaceId);
			p.getPacketDispatch().sendMessage(message[secondInterfaceId - 650]);
			p.fullRestore();
			PulseManager.cancelDeathTask(p);
		}
		for (Player p : viewingPlayers) {
			p.getProperties().setTeleportLocation(getLeaveLocation());
			if (p.getCommunication().getClan() == firstClan) {
				p.getInterfaceManager().openComponent(firstInterfaceId);
				p.getPacketDispatch().sendMessage(message[firstInterfaceId - 650]);
			} else {
				p.getInterfaceManager().openComponent(secondInterfaceId);
				p.getPacketDispatch().sendMessage(message[secondInterfaceId - 650]);
			}
		}
	}

	/**
	 * Sends the game data for all players in the game.
	 */
	public void sendGameData() {
		for (Player p : firstClanPlayers) {
			sendGameData(p);
		}
		for (Player p : secondClanPlayers) {
			sendGameData(p);
		}
		for (Player p : viewingPlayers) {
			sendGameData(p);
		}
	}

	/**
	 * Sends the game data for the player.
	 * @param p The player.
	 */
	public void sendGameData(Player p) {
		int value = 0;
		boolean first = p.getCommunication().getClan() == firstClan;
		String name = firstClan.getName();
		if (first) {
			value |= firstClanPlayers.size() << 5;
			value |= secondClanPlayers.size() << 14;
		} else {
			name = secondClan.getName();
			value |= secondClanPlayers.size() << 5;
			value |= firstClanPlayers.size() << 14;
		}
		if (pulse.getTicksPassed() < 200) {
			value |= (200 - pulse.getTicksPassed()) << 23;
		}
		p.getConfigManager().set(Configuration.CLAN_WAR_DATA, value);
		p.getPacketDispatch().sendString(name, 265, 2);
	}

	@Override
	public boolean teleport(Entity e, int type, Node node) {
		if (type != -1 && type != 2 && e instanceof Player) {
			((Player) e).getPacketDispatch().sendMessage("You can't teleport away from a war.");
			return false;
		}
		return true;
	}

	@Override
	public boolean enter(Entity e) {
		if (e instanceof Player) {
			Player player = (Player) e;
			player.getInteraction().set(ATTACK_OPTION);
			player.getInteraction().remove(Option._P_ASSIST);
			player.getSkullManager().setSkullCheckDisabled(true);
			player.getSkullManager().setWilderness(true);
			player.getInterfaceManager().openOverlay(new Component(265));
		} else if (e instanceof Familiar && !(e instanceof Pet)) {
			Familiar familiar = (Familiar) e;
			if (familiar.isCombatFamiliar()) {
				familiar.transform(familiar.getOriginalId() + 1);
			}
		}
		return true;
	}

	@Override
	public boolean death(Entity e, Entity killer) {
		if (e instanceof Player) {
			return enterViewingRoom((Player) e);
		}
		return false;
	}

	/**
	 * Enters the viewing room.
	 * @param player The player.
	 * @return {@code True} if successfully entered the viewing room.
	 */
	public boolean enterViewingRoom(Player player) {
		Location destination = null;
		if (player.getCommunication().getClan() == firstClan) {
			remove(player, firstClanPlayers);
			destination = base.transform(55 + RandomFunction.randomize(3), 51 + RandomFunction.randomize(11), 0);
		} else if (player.getCommunication().getClan() == secondClan) {
			remove(player, secondClanPlayers);
			destination = base.transform(55 + RandomFunction.randomize(3), 66 + RandomFunction.randomize(11), 0);
		} else {
			return false;
		}
		player.getProperties().setTeleportLocation(destination);
		viewingPlayers.add(player);
		sendGameData();
		return true;
	}

	@Override
	public boolean continueAttack(Entity e, Node target, CombatStyle style, boolean message) {
		if (e instanceof Familiar) {
			e = ((Familiar) e).getOwner();
		}
		if (target instanceof Familiar) {
			target = ((Familiar) target).getOwner();
		}
		if (e instanceof Player && target instanceof Player) {
			Player player = (Player) e;
			Player other = (Player) target;
			ClanRepository clan = player.getCommunication().getClan();
			if (pulse.isRunning()) {
				player.getPacketDispatch().sendMessage("The war hasn't started yet.");
				return false;
			}
			if (!firstClanPlayers.contains(player) && !secondClanPlayers.contains(player)) {
				return false;
			}
			if (other.getCommunication().getClan() == clan) {
				player.getPacketDispatch().sendMessage("You can only attack players in a different clan.");
				return false;
			}
			if (!firstClanPlayers.contains(other) && !secondClanPlayers.contains(other)) {
				player.getPacketDispatch().sendMessage("You can't attack this player.");
				return false;
			}
		}
		return true;
	}

	/**
	 * Removes the player from the game.
	 * @param player The player.
	 * @param players The players list.
	 */
	public void remove(Player player, List<Player> players) {
		players.remove(player);
		if (!pulse.isRunning() && players.isEmpty()) {
			finishWar();
		}
	}

	@Override
	public boolean interact(Entity e, Node target, Option option) {
		if (target instanceof GameObject) {
			GameObject object = (GameObject) target;
			if (object.getId() == 28214 || object.getId() == 28140) {
				e.getProperties().setTeleportLocation(getLeaveLocation());
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean leave(Entity e, boolean logout) {
		if (e instanceof Player) {
			Player player = (Player) e;
			player.getInterfaceManager().closeOverlay();
			player.getInteraction().remove(ATTACK_OPTION);
			if (firstClanPlayers.contains(player)) {
				remove(player, firstClanPlayers);
			} else if (secondClanPlayers.contains(player)) {
				remove(player, secondClanPlayers);
			} else {
				viewingPlayers.remove(player);
			}
			sendGameData();
			player.getSkullManager().setSkullCheckDisabled(false);
			player.getSkullManager().setWilderness(false);
			if (logout) {
				e.setLocation(getLeaveLocation());
			}
		} else if (e instanceof Familiar && !(e instanceof Pet)) {
			Familiar familiar = (Familiar) e;
			if (familiar.isCombatFamiliar()) {
				familiar.reTransform();
			}
		}
		return true;
	}

	@Override
	public Object fireEvent(String identifier, Object... args) {
		if (identifier.equals("join")) {
			join((Player) args[0]);
			return true;
		}
		if (identifier.equals("leavefc")) {
			Player p = (Player) args[0];
			p.getProperties().setTeleportLocation(getLeaveLocation());
			return true;
		}
		return null;
	}

	/**
	 * Gets the leaving location.
	 * @return The leaving location.
	 */
	private static Location getLeaveLocation() {
		Location l = Location.create(3265 + RandomFunction.randomize(13), 3675 + RandomFunction.randomize(18), 0);
		while (!RegionManager.isTeleportPermitted(l)) {
			l = l.transform(1, 1, 0);
		}
		return l;
	}

	/**
	 * Makes the player join the game.
	 * @param player The player.
	 */
	public void join(Player player) {
		if (!pulse.isRunning()) {
			enterViewingRoom(player);
			return;
		}
		boolean first = player.getCommunication().getClan() == firstClan;
		if (first) {
			if (firstClanPlayers.contains(player)) {
				return;
			}
			firstClanPlayers.add(player);
			player.getProperties().setTeleportLocation(base.transform(34 + RandomFunction.randomize(4), 10, 0));
		} else {
			if (secondClanPlayers.contains(player)) {
				return;
			}
			secondClanPlayers.add(player);
			player.getProperties().setTeleportLocation(base.transform(26 + RandomFunction.randomize(4), 118, 0));
		}
		sendGameData();
	}

	/**
	 * Sends messages to the list of players.
	 * @param players The players list.
	 */
	private static void sendWarDeclaration(List<ClanEntry> players) {
		Location check = Location.create(3272, 3682, 0);
		for (ClanEntry e : players) {
			Player p = e.getPlayer();
			if (p.getLocation().withinDistance(check, 128)) {
				p.getPacketDispatch().sendMessage("<col=ff0000>Your clan has been challenged to a clan war!");
				p.getPacketDispatch().sendMessage("<col=ff0000>Step through the purple portal in the Challenge Hall.");
				p.getPacketDispatch().sendMessage("<col=ff0000>Battle will commence in 2 minutes.");
			}
		}
	}

	@Override
	public void configure() {
		setZoneType(ZoneType.SAFE.getId());
		DynamicRegion[] regions = DynamicRegion.create(new ZoneBorders(3264, 3712, 3328, 3840));
		setRegionBase(regions);
		int x = base.getX();
		int y = base.getY() + 20;
		RegionZone multi = new RegionZone(MultiwayCombatZone.getInstance(), new ZoneBorders(x, y, x + 63, y + 88));
		for (DynamicRegion r : regions) {
			r.setMulticombat(true);
			r.getRegionZones().add(multi);
			r.setRegionTimeOut(250);
			r.setMusicId(442);
		}
	}

	@Override
	public Location getSpawnLocation() {
		return null;
	}

	@Override
	public ActivityPlugin newInstance(Player p) throws Throwable {
		return new ClanWarsActivityPlugin();
	}

	/**
	 * Gets the ticks.
	 * @return The ticks.
	 */
	public int getTicks() {
		return ticks;
	}

	/**
	 * Sets the ticks.
	 * @param ticks The ticks to set.
	 */
	public void setTicks(int ticks) {
		this.ticks = ticks;
	}

}
