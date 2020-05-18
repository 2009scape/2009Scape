package plugin.activity.mta;

import org.crandor.ServerConstants;
import org.crandor.game.interaction.Option;
import org.crandor.game.node.Node;
import org.crandor.game.node.entity.Entity;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.entity.player.link.request.RequestType;
import org.crandor.game.node.item.Item;
import org.crandor.game.world.map.Location;
import org.crandor.game.world.map.zone.MapZone;
import org.crandor.game.world.map.zone.ZoneRestriction;
import org.crandor.game.world.map.zone.ZoneType;

/**
 * Represents a magic training arena zone.
 * @author Vexia
 */
public class MTAZone extends MapZone {

	/**
	 * The items.
	 */
	private final Item[] items;

	/**
	 * The mage training arena type.
	 */
	private MTAType type;

	/**
	 * Constructs a new {@Code MTAZone} {@Code Object}
	 * @param name the name.
	 * @param items the items.
	 */
	public MTAZone(String name, Item[] items) {
		super(name, false, ZoneRestriction.RANDOM_EVENTS, ZoneRestriction.FOLLOWERS);
		this.items = items;
		this.setZoneType(ZoneType.SAFE.getId());
	}

	@Override
	public boolean enter(Entity entity) {
		if (entity instanceof Player) {
			Player player = entity.asPlayer();
			if (player == null) {
				return true;
			}
			if (type == null) {
				type = MTAType.forZone(this);
			}
			if (type != null) {
				player.getInterfaceManager().openOverlay(type.getOverlay());
				update(player);
			}
			player.getProperties().setSpawnLocation(new Location(3363, 3302, 0));
		}
		return super.enter(entity);
	}

	@Override
	public boolean canRequest(RequestType type, Player player, Player target) {
		player.getDialogueInterpreter().sendDialogue("You can't do that right now.");
		return false;
	}

	@Override
	public boolean interact(Entity e, Node target, Option option) {
		if (e instanceof Player) {
			if (target.getId() == 10782) {
				getType().exit(e.asPlayer());
				return true;
			}
		}
		return super.interact(e, target, option);
	}

	@Override
	public boolean teleport(Entity e, int type, Node node) {
		if (e instanceof Player) {
			if (type != -1) {
				e.asPlayer().sendMessage("You can't teleport out of the training arena!");
				return false;
			}
		}
		return super.teleport(e, type, node);
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean leave(Entity entity, boolean logout) {
		if (entity instanceof Player) {
			Player player = entity.asPlayer();
			if (logout) {
				player.setLocation(new Location(3363, 3302, 0));
			} else {
				player.getProperties().setSpawnLocation(ServerConstants.HOME_LOCATION);
			}
			cleanItems(player);
			player.getInterfaceManager().closeOverlay();
		}
		return super.leave(entity, logout);
	}

	@Override
	public void configure() {
	}

	/**
	 * Cleans the items.
	 */
	private void cleanItems(Player player) {
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
		}
	}

	/**
	 * Increments the pizazz points of a player.
	 * @param player the player.
	 * @param index the index.
	 * @param amount the amount.
	 */
	public void incrementPoints(Player player, int index, int amount) {
		player.getSavedData().getActivityData().incrementPizazz(index, amount);
		update(player);
	}

	/**
	 * Updates the player.
	 * @param player the player.
	 */
	public void update(Player player) {
		if (type == null) {
			return;
		}
		player.getPacketDispatch().sendString("" + player.getSavedData().getActivityData().getPizazzPoints(type.ordinal()), type.getOverlay().getId(), 9);
	}

	/**
	 * Gets the items.
	 * @return the items
	 */
	public Item[] getItems() {
		return items;
	}

	/**
	 * Gets the type.
	 * @return the type
	 */
	public MTAType getType() {
		return type;
	}

}
