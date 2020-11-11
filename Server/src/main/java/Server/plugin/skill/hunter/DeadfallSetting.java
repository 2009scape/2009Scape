package plugin.skill.hunter;

import plugin.skill.firemaking.Log;
import core.game.node.Node;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.game.node.item.Item;
import core.game.node.object.GameObject;
import core.game.node.object.ObjectBuilder;
import core.game.world.map.Direction;
import core.game.world.map.Location;
import core.game.world.update.flag.context.Animation;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles a deadfall trap.
 * @author Vexia
 */
public final class DeadfallSetting extends TrapSetting {

	/**
	 * Constructs a new {@code DeadfallSetting} {@code Object}.
	 */
	public DeadfallSetting() {
		super(new int[] { 28935, 19205 },
				new Item[] { new Item(946) },
				new int[] { 28937, 19206 },
				new int[] { 10138, 6006, 12574, 341, 2132 },
				"set-trap", 23, -1, new Animation(5208), new Animation(9726), true);
	}

	@Override
	public boolean hasItems(Player player) {
		if (!super.hasItems(player)) {
			player.getPacketDispatch().sendMessage("You need a knife in order to set a deadfall trap.");
			return false;
		}
		for (Log log : Log.values()) {
			if (player.getInventory().contains(log.getLogId(), 1)) {
				return true;
			}
		}
		player.getPacketDispatch().sendMessage("You need logs in order to set a deadfall trap.");
		return false;
	}

	@Override
	public TrapHook createHook(TrapWrapper wrapper) {
		return new TrapHook(wrapper, getLocations(wrapper.getObject()).toArray(new Location[] {}));
	}

	@Override
	public void reward(Player player, Node node, TrapWrapper wrapper) {
		player.getInventory().remove(new Item(getLog(player).getLogId()));
	}

	@Override
	public boolean clear(TrapWrapper wrapper, int type) {
		if (super.clear(wrapper, type)) {
			ObjectBuilder.add(wrapper.getObject().transform(getNodeForObjectId(wrapper.isCaught() ? wrapper.getOriginalId() : wrapper.getObject().getId())));
			return true;
		}
		return false;
	}

	@Override
	public boolean canCatch(TrapWrapper wrapper, NPC npc) {
		int x = wrapper.getObject().getLocation().getX(), y = wrapper.getObject().getLocation().getY();
		Direction direction = wrapper.getObject().getDirection();
		int dir = 0;
		if (direction == Direction.NORTH) {
			if (npc.getLocation().getY() < y) {
				dir = 1;
			} else {
				dir = 0;
			}
		} else if (direction == Direction.SOUTH) {
			if (npc.getLocation().getY() < y) {
				dir = 0;
			} else {
				dir = 1;
			}
		} else if (direction == Direction.WEST) {
			if (npc.getLocation().getX() > x) {
				dir = 1;
			} else {
				dir = 0;
			}
		} else {
			if (npc.getLocation().getX() > x) {
				dir = 0;
			} else {
				dir = 1;
			}
		}
		npc.faceLocation(wrapper.getObject().getLocation());
		wrapper.getObject().getAttributes().setAttribute("kebbit-dir", dir);
		return true;
	}

	@Override
	public boolean isSuccess(Player player, final TrapNode node) {
		return true;
	}

	@Override
	public int getTransformId(TrapWrapper wrapper, TrapNode node) {
		int dir = wrapper.getObject().getAttributes().getAttribute("kebbit-dir", 0);
		int id = dir == 0 ? node.getObjectIds()[0] : node.getObjectIds()[1];
		return id;
	}

	@Override
	public int getFinalId(TrapWrapper wrapper, TrapNode node) {
		return node.getObjectIds()[2];
	}

	@Override
	public GameObject buildObject(Player player, Node node) {
		return node.asObject().transform(getObjectForNode(node));
	}

	@Override
	public String getLimitMessage(Player player) {
		return "You can only have one deadfall trap at a time.";
	}

	@Override
	public boolean exceedsLimit(Player player) {
		return player.getHunterManager().getTrapAmount() > 0 ? true : false;
	}

	/**
	 * Gets the list of locations.
	 * @return the locations.
	 */
	private List<Location> getLocations(GameObject object) {
		List<Location> locs = new ArrayList<>();
		if (object.getDirection() == Direction.NORTH) {
			locs.add(object.getLocation().transform(1, -1, 0));
			locs.add(object.getLocation().transform(1, 1, 0));
		} else if (object.getDirection() == Direction.SOUTH) {
			locs.add(object.getLocation().transform(0, 1, 0));
			locs.add(object.getLocation().transform(0, -1, 0));
		} else if (object.getDirection() == Direction.WEST) {
			locs.add(object.getLocation().transform(1, 1, 0));
			locs.add(object.getLocation().transform(-1, 1, 0));
		} else {
			locs.add(object.getLocation().transform(-1, 0, 0));
			locs.add(object.getLocation().transform(1, 0, 0));
		}
		return locs;
	}

	/**
	 * Gets the log the player has.
	 * @param player the player.
	 * @return the log.
	 */
	private Log getLog(Player player) {
		for (Log log : Log.values()) {
			if (player.getInventory().contains(log.getLogId(), 1)) {
				return log;
			}
		}
		return null;
	}
}