package core.game.world.map.zone;

import plugin.skill.summoning.familiar.Familiar;
import core.game.interaction.Option;
import core.game.node.Node;
import core.game.node.entity.Entity;
import core.game.node.entity.combat.CombatStyle;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.request.RequestType;
import core.game.node.item.Item;
import core.game.world.map.Location;
import core.game.world.map.Region;
import core.game.world.map.RegionManager;

import java.util.Iterator;

/**
 * Represents a map zone.
 * @author Emperor
 */
public abstract class MapZone implements Zone {

	/**
	 * The map zone uid.
	 */
	private int uid;

	/**
	 * The zone name.
	 */
	private String name;

	/**
	 * If the map zone can be overlapped by another zone.
	 */
	private boolean overlappable;

	/**
	 * If random events should be fired in this zone.
	 */
	protected boolean fireRandomEvents;

	/**
	 * If restriction flag.
	 */
	private int restriction;

	/**
	 * The zone type (used for items kept on death).
	 */
	private int zoneType;

	/**
	 * Constructs a new {@code MapZone} {@code Object}.
	 * @param name The name.
	 * @param overlappable If the zone can be overlapped.
	 */
	public MapZone(String name, boolean overlappable, ZoneRestriction... restrictions) {
		this.name = name;
		this.overlappable = overlappable;
		for (ZoneRestriction restriction : restrictions) {
			addRestriction(restriction.getFlag());
		}
	}

	@Override
	public boolean enter(Entity e) {
		if (e instanceof Player) {
			Player p = (Player) e;
		} else if (e instanceof NPC) {
			NPC npc = (NPC) e;
			if (e instanceof Familiar && isRestricted(ZoneRestriction.FOLLOWERS.getFlag())) {
				npc.setInvisible(true);
			}
		}
		return true;
	}

	@Override
	public boolean leave(Entity e, boolean logout) {
		return true;
	}

	/**
	 * Checks if the player can logout.
	 * @param p The player.
	 * @return {@code True} if so.
	 */
	public boolean canLogout(Player p) {
		return true;
	}

	/**
	 * Called when an entity dies.
	 * @param e The entity dying.
	 * @param killer The killer.
	 * @return {@code True} if the death got handled by this zone handler.
	 */
	public boolean death(Entity e, Entity killer) {
		return false;
	}

	/**
	 * Handles an interaction.
	 * @param e The entity.
	 * @param target The target to interact with.
	 * @param option The option.
	 * @return {@code True} if the option got handled.
	 */
	public boolean interact(Entity e, Node target, Option option) {
		return false;
	}

	/**
	 * Handles an reward button.
	 * @param player The player.
	 * @param interfaceId The interface id.
	 * @param buttonId The button id.
	 * @param slot The slot.
	 * @param itemId The item id.
	 * @param opcode The packet opcode.
	 * @return {@code True} if the button got handled.
	 */
	public boolean actionButton(Player player, int interfaceId, int buttonId, int slot, int itemId, int opcode) {
		return false;
	}

	/**
	 * Checks if the entity is able to continue attacking the target.
	 * @param e the attacking entity.
	 * @param target The target.
	 * @param style The combat style used.
	 * @param message If a message should be send.
	 * @return {@code True} if so.
	 */
	public boolean continueAttack(Entity e, Node target, CombatStyle style, boolean message) {
		return true;
	}

	/**
	 * If multi-zone boundaries should be ignored.
	 * @param attacker The attacker.
	 * @param victim The victim.
	 * @return {@code True} if the entity can attack regardless of multicombat.
	 */
	public boolean ignoreMultiBoundaries(Entity attacker, Entity victim) {
		return false;
	}

	/**
	 * Checks if the entity can attack the target.
	 * @param e The attacking entity.
	 * @param t The target.
	 * @param message If a message should be sent.
	 * @return {@code True} if the entity can continue attacking.
	 */
	public static boolean checkMulti(Entity e, Entity t, boolean message) {
		long time = System.currentTimeMillis();
		boolean multi = t.getProperties().isMultiZone() && e.getProperties().isMultiZone();
		if (multi || e.isIgnoreMultiBoundaries(t) || e.getZoneMonitor().isIgnoreMultiBoundaries(t)) {
			return true;
		}
		Entity target = t.getAttribute("combat-attacker", e);
		if (t.getAttribute("combat-time", -1L) > time && target != e && target.isActive()) {
			if (message && e instanceof Player) {
				((Player) e).getPacketDispatch().sendMessage("Someone else is already fighting this" + (t instanceof Player ? " player." : "."));
			}
			return false;
		}
		if (e.getAttribute("combat-time", -1L) > time && (target = e.getAttribute("combat-attacker", t)) != t && target.isActive()) {
			if (t.getId() == 1614 || t.getId() == 1613) {
				return true;
			}
			if (message && e instanceof Player) {
				((Player) e).getPacketDispatch().sendMessage("You're already under attack!");
			}
			return false;
		}
		return true;
	}

	/**
	 * Checks if the entity can teleport.
	 * @param e The entity.
	 * @param type The teleport type (0=spell, 1=item, 2=object, 3=npc -1=
	 * force)
	 * @return {@code True} if so.
	 */
	public boolean teleport(Entity e, int type, Node node) {
		return true;
	}

	/**
	 * Checks if the death should start.
	 * @param e the entity.
	 * @param killer the killer.
	 * @return {@code True} if so.
	 */
	public boolean startDeath(Entity e, Entity killer) {
		return true;
	}

	/**
	 * Checks if a request can be made in this zone.
	 * @param type the type.
	 * @param target the target.
	 * @return {@code True} if so.
	 */
	public boolean canRequest(RequestType type, Player player, Player target) {
		return true;
	}

	/**
	 * Checks if the entity can move.
	 * @param e The entity.
	 * @param from The current location.
	 * @param to The destination location.
	 * @return {@code True} if so.
	 */
	public boolean move(Entity e, Location from, Location to) {
		return true;
	}

	/**
	 * Parses a command in this map zone.
	 * @param player the player.
	 * @param name the name.
	 * @param arguments the arguments.
	 * @return {@code True} if so.
	 */
	public boolean parseCommand(Player player, String name, String[] arguments) {
		return false;
	}

	/**
	 * Called when an entity changed location.
	 * @param e The entity.
	 * @param last The previous location the entity was standing on.
	 */
	public void locationUpdate(Entity e, Location last) {

	}

	/**
	 * Configures this map zone.
	 */
	public abstract void configure();

	/**
	 * Cleans items from a players inventory, equipment and bank.
	 * @param player the player.
	 * @param items the items.
	 */
	public void cleanItems(Player player, Item[] items) {
		if (player == null) {
			return;
		}
		for (Item item : items) {
			if (item == null) {
				continue;
			}
			if (player.getInventory().containsItem(item)) {
				player.getInventory().remove(new Item(item.getId(), player.getInventory().getAmount(item)));
			}
			if (player.getEquipment().containsItem(item)) {
				player.getEquipment().remove(new Item(item.getId(), player.getEquipment().getAmount(item)));
			}
			if (player.getBank().containsItem(item)) {
				player.getBank().remove(new Item(item.getId(), player.getBank().getAmount(item)));
			}
		}
	}

	/**
	 * Sends a message to the entity (if it is a player).
	 * @param e The entity.
	 * @param message The message.
	 */
	protected static void message(Entity e, String message) {
		if (!(e instanceof Player)) {
			return;
		}
		((Player) e).getPacketDispatch().sendMessage(message);
	}

	/**
	 * Registers this mapzone in the appropriate regions.
	 * @param borders The borders of this zone.
	 */
	public void register(ZoneBorders borders) {
		for (Integer id : borders.getRegionIds()) {
			Region r = RegionManager.forId(id);
			if (r != null) {
				r.add(new RegionZone(this, borders));
			}
		}
	}

	/**
	 * Registers this zone in the region for the given id.
	 * @param regionId The region id.
	 */
	public void registerRegion(int regionId) {
		register(ZoneBorders.forRegion(regionId));
	}

	/**
	 * Registers a region with zone borders and the region id.
	 * @param regionId the region id.
	 * @param borders the borders.
	 */
	public void registerRegion(int regionId, ZoneBorders borders) {
		Region r = RegionManager.forId(regionId);
		if (r != null) {
			r.add(new RegionZone(this, borders));
		}
	}

	/**
	 * Unregisters the borders in a region.
	 * @param regionId The region id.
	 */
	public void unregisterRegion(int regionId) {
		Region r = RegionManager.forId(regionId);
		if (r != null) {
			for (Iterator<RegionZone> it = r.getRegionZones().iterator(); it.hasNext();) {
				if (it.next().getZone() == this) {
					it.remove();
				}
			}
		}
	}

	/**
	 * Disables the firing of random events in this zone.
	 */
	public void disableRandomEvents() {
		this.fireRandomEvents = false;
	}

	/**
	 * Gets the name.
	 * @return The name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 * @param name The name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the overlappable.
	 * @return The overlappable.
	 */
	public boolean isOverlappable() {
		return overlappable;
	}

	/**
	 * Sets the overlappable.
	 * @param overlappable The overlappable to set.
	 */
	public void setOverlappable(boolean overlappable) {
		this.overlappable = overlappable;
	}

	/**
	 * Gets the uid.
	 * @return The uid.
	 */
	public int getUid() {
		return uid;
	}

	/**
	 * Sets the uid.
	 * @param uid The uid to set.
	 */
	public void setUid(int uid) {
		this.uid = uid;
	}

	/**
	 * Gets the fireRandoms.
	 * @return The fireRandoms.
	 */
	public boolean isFireRandoms() {
		return fireRandomEvents;
	}

	/**
	 * If the zone is dynamicly added and removed. Non-location based zones will
	 * typicly return true.
	 * @return {@code false} on default.
	 */
	public boolean isDynamicZone() {
		return false;
	}

	/**
	 * Adds a restriction flag.
	 * @param restriction The restriction flag.
	 */
	public void addRestriction(ZoneRestriction restriction) {
		addRestriction(restriction.getFlag());
	}

	/**
	 * Adds a restriction flag.
	 * @param flag The restriction flag.
	 */
	public void addRestriction(int flag) {
		restriction |= flag;
	}

	/**
	 * Checks if the restriction was flagged.
	 * @param flag The restriction flag.
	 * @return {@code True} if so.
	 */
	public boolean isRestricted(int flag) {
		return (restriction & flag) != 0;
	}

	/**
	 * Gets the restriction.
	 * @return The restriction.
	 */
	public int getRestriction() {
		return restriction;
	}

	/**
	 * Gets the type.
	 * @return The type.
	 */
	public int getZoneType() {
		return zoneType;
	}

	/**
	 * Sets the type.
	 * @param type The type to set.
	 */
	public void setZoneType(int type) {
		this.zoneType = type;
	}

}