package core.game.world.map.zone;

import core.game.interaction.Option;
import core.game.node.Node;
import core.game.node.entity.Entity;
import core.game.node.entity.combat.CombatStyle;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.music.MusicZone;
import core.game.node.entity.player.link.request.RequestType;
import core.game.world.map.Location;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Handles the zones for an entity.
 * @author Emperor
 */
public final class ZoneMonitor {

	/**
	 * The entity.
	 */
	private final Entity entity;

	/**
	 * The currently entered zones.
	 */
	private final List<RegionZone> zones = new ArrayList<>();

	/**
	 * The currently entered music zones.
	 */
	private final List<MusicZone> musicZones = new ArrayList<>();

	/**
	 * Constructs a new {@code ZoneMonitor} {@code Object}.
	 * @param entity The entity.
	 */
	public ZoneMonitor(Entity entity) {
		this.entity = entity;
	}

	/**
	 * Gets the zone type.
	 * @return The zone type.
	 */
	public int getType() {
		for (RegionZone zone : zones) {
			if (zone.getZone().getZoneType() != 0) {
				return zone.getZone().getZoneType();
			}
		}
		return 0;
	}

	/**
	 * Checks if the player can logout.
	 * @return {@code True} if so.
	 */
	public boolean canLogout() {
		for (RegionZone z : zones) {
			if (!z.getZone().canLogout((Player) entity)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Checks if the restriction was flagged.
	 * @param restriction The restriction flag.
	 * @return {@code True} if so.
	 */
	public boolean isRestricted(ZoneRestriction restriction) {
		return isRestricted(restriction.getFlag());
	}

	/**
	 * Checks if the restriction was flagged.
	 * @param flag The restriction flag.
	 * @return {@code True} if so.
	 */
	public boolean isRestricted(int flag) {
		for (RegionZone z : zones) {
			if (z.getZone().isRestricted(flag)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Handles a death.
	 * @param killer The killer.
	 * @return {@code True} if the death got handled.
	 */
	public boolean handleDeath(Entity killer) {
		for (RegionZone z : zones) {
			if (z.getZone().death(entity, killer)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Checks if the entity is able to continue attacking the target.
	 * @param target The target.
	 * @param style The combat style used.
	 * @return {@code True} if so.
	 */
	public boolean continueAttack(Node target, CombatStyle style) {
		if (target instanceof Entity) {
			if (!entity.continueAttack((Entity) target, style)) {
				return false;
			}
		}
		for (RegionZone z : zones) {
			if (!z.getZone().continueAttack(entity, target, style, true)) {
				return false;
			}
		}
		if (entity instanceof Player && target instanceof Player) {
			if (!((Player) entity).getSkullManager().isWilderness() || !((Player) target).getSkullManager().isWilderness()) {
				((Player) entity).getPacketDispatch().sendMessage("You can only attack other players in the wilderness.");
				return false;
			}
		}
		if (target instanceof Entity && !MapZone.checkMulti(entity, (Entity) target, true)) {
			return false;
		}
		return true;
	}

	/**
	 * Checks if the entity can interact with the target.
	 * @param target The target to interact with.
	 * @param option The option.
	 * @return {@code True} if the option got handled.
	 */
	public boolean interact(Node target, Option option) {
		for (RegionZone z : zones) {
			if (z.getZone().interact(entity, target, option)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Checks if the player handled the reward button using a map zone.
	 * @param interfaceId The interface id.
	 * @param buttonId The button id.
	 * @param slot The slot.
	 * @param itemId The item id.
	 * @param opcode The packet opcode.
	 * @return {@code True} if the button got handled.
	 */
	public boolean clickButton(int interfaceId, int buttonId, int slot, int itemId, int opcode) {
		for (RegionZone z : zones) {
			if (z.getZone().actionButton((Player) entity, interfaceId, buttonId, slot, itemId, opcode)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Checks if multiway combat zone rules should be ignored.
	 * @param victim The victim.
	 * @return {@code True} if this entity can attack regardless of multiway
	 * combat zone.
	 */
	public boolean isIgnoreMultiBoundaries(Entity victim) {
		for (RegionZone z : zones) {
			if (z.getZone().ignoreMultiBoundaries(entity, victim)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Checks if the entity can teleport.
	 * @param type The teleport type (0=spell, 1=item, 2=object, 3=npc -1= force)
	 * @return {@code True} if so.
	 */
	public boolean teleport(int type, Node node) {
		if (type != -1 && entity.isTeleBlocked()) {
			if (entity.isPlayer()) {
				entity.asPlayer().sendMessage("A magical force has stopped you from teleporting.");
			}
			return false;
		}
		for (RegionZone z : zones) {
			if (!z.getZone().teleport(entity, type, node)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Checks if the death should start for an entity.
	 * @param entity the entity.
	 * @param killer the killer.
	 * @return {@code True} if so.
	 */
	public boolean startDeath(Entity entity, Entity killer) {
		for (RegionZone z : zones) {
			if (!z.getZone().startDeath(entity, killer)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Checks if the entity can fire a random event.
	 * @return {@code True} if so.
	 */
	public boolean canFireRandomEvent() {
		for (RegionZone z : zones) {
			if (!z.getZone().isFireRandoms()) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Clears the zones.
	 * @return {@code True} if the entity successfully left all regions.
	 */
	public boolean clear() {
		for (RegionZone z : zones) {
			if (!z.getZone().leave(entity, true)) {
				return false;
			}
		}
		for (MusicZone z : musicZones) {
			z.leave(entity, true);
		}
		zones.clear();
		musicZones.clear();
		return true;
	}

	/**
	 * Checks if the entity can move.
	 * @param location The destination location.
	 * @param destination The destination location.
	 * @return {@code True} if so.
	 */
	public boolean move(Location location, Location destination) {
		for (RegionZone z : zones) {
			if (!z.getZone().move(entity, location, destination)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Handles a location update.
	 * @param last The last location of the entity.
	 * @return {@code false} If the entity could not enter/leave a region.
	 */
	public boolean updateLocation(Location last) {
		if(entity instanceof Player && !entity.asPlayer().isArtificial()) {
			checkMusicZones();
		}
		entity.updateLocation(last);
		for (Iterator<RegionZone> it = zones.iterator(); it.hasNext();) {
			RegionZone zone = it.next();
			if (!zone.getBorders().insideBorder(entity)) {
				if (zone.getZone().isDynamicZone()) {
					continue;
				}
				if (!zone.getZone().leave(entity, false)) {
					return false;
				}
				it.remove();
			}
		}
		for (RegionZone zone : entity.getViewport().getRegion().getRegionZones()) {
			if (!zone.getBorders().insideBorder(entity)) {
				continue;
			}
			boolean alreadyEntered = false;
			for (RegionZone z : zones) {
				if (z.getZone() == zone.getZone()) {
					alreadyEntered = true;
					break;
				}
			}
			if (alreadyEntered) {
				zone.getZone().locationUpdate(entity, last);
				continue;
			}
			if (!zone.getZone().enter(entity)) {
				return false;
			}
			zones.add(zone);
			zone.getZone().locationUpdate(entity, last);
		}
		return true;
	}

	/**
	 * Checks the music zones.
	 */
	public void checkMusicZones() {
		if (!(entity instanceof Player)) {
			return;
		}
		Player player = (Player) entity;
		Location l = player.getLocation();
		for (Iterator<MusicZone> it = musicZones.iterator(); it.hasNext();) {
			MusicZone zone = it.next();
			if (!zone.getBorders().insideBorder(l.getX(), l.getY())) {
				zone.leave(player, false);
				it.remove();
			}
		}
		for (MusicZone zone : player.getViewport().getRegion().getMusicZones()) {
			if (!zone.getBorders().insideBorder(l.getX(), l.getY())) {
				continue;
			}
			if (!musicZones.contains(zone)) {
				zone.enter(player);
				musicZones.add(zone);
			}
		}
		if (musicZones.isEmpty() && !player.getMusicPlayer().isPlaying()) {
			player.getMusicPlayer().playDefault();
		}
	}

	/**
	 * Parses commands in a certain zone.
	 * @param player the player.
	 * @param name the name.
	 * @param arguments the arguments.
	 * @return {@code True} if parsed.
	 */
	public boolean parseCommand(Player player, String name, String[] arguments) {
		for (RegionZone zone : zones) {
			if (zone.getZone().parseCommand(player, name, arguments)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Checks if a request can be made in this zone.
	 * @param type the type.
	 * @param target the target.
	 * @return {@code True} if so.
	 */
	public boolean canRequest(RequestType type, Player target) {
		for (RegionZone zone : zones) {
			if (!zone.getZone().canRequest(type, entity.asPlayer(), target)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Checks if the entity is in a zone.
	 * @param name The name of the zone.
	 * @return {@code True} if so.
	 */
	public boolean isInZone(String name) {
		int uid = name.hashCode();
		for (RegionZone zone : zones) {
			if (zone.getZone().getUid() == uid) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Removes the proper region zone for the given map zone.
	 * @param zone The map zone.
	 */
	public void remove(MapZone zone) {
		for (Iterator<RegionZone> it = zones.iterator(); it.hasNext();) {
			if (it.next().getZone() == zone) {
				it.remove();
				break;
			}
		}
	}

	/**
	 * Gets the zones list.
	 * @return The list of region zones the entity is in.
	 */
	public List<RegionZone> getZones() {
		return zones;
	}

	/**
	 * Gets the musicZones.
	 * @return The musicZones.
	 */
	public List<MusicZone> getMusicZones() {
		return musicZones;
	}

}