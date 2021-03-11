package core.game.interaction.city;

import core.cache.def.impl.ObjectDefinition;
import core.game.node.entity.skill.Skills;
import core.game.node.entity.skill.agility.AgilityHandler;
import core.game.interaction.OptionHandler;
import core.game.node.Node;
import core.game.node.entity.impl.ForceMovement;
import core.game.node.entity.player.Player;
import core.game.node.object.GameObject;
import core.game.node.object.ObjectBuilder;
import core.game.system.task.Pulse;
import core.game.world.GameWorld;
import core.game.world.map.Direction;
import core.game.world.map.Location;
import core.game.world.update.flag.context.Animation;
import core.plugin.Initializable;
import core.plugin.Plugin;

/**
 * Handles Gnome stronghold options.
 * @author Emperor
 */
@Initializable
public final class GnomeStrongholdPlugin extends OptionHandler {

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ObjectDefinition.forId(190).getHandlers().put("option:open", this);
		ObjectDefinition.forId(1967).getHandlers().put("option:open", this);
		ObjectDefinition.forId(1968).getHandlers().put("option:open", this);
		ObjectDefinition.forId(9316).getHandlers().put("option:climb",this);
		ObjectDefinition.forId(9317).getHandlers().put("option:climb",this);
		return this;
	}

	@Override
	public boolean handle(Player player, Node node, String option) {
		GameObject object = (GameObject) node;
		switch (object.getId()) {
		case 9316:
		case 9317:
			final boolean scale = player.getLocation().getY() <= object.getLocation().getY();
			final Location end = object.getLocation().transform(scale ? 3 : -3, scale ? 6 : -6, 0);
			if (!player.getSkills().hasLevel(Skills.AGILITY, 37)) {
				player.getPacketDispatch().sendMessage("You must be level 37 agility or higher to climb down the rocks.");
				break;
			}
			if (!scale) {
				ForceMovement.run(player, player.getLocation(), end, Animation.create(740), Animation.create(740), Direction.SOUTH, 13).setEndAnimation(Animation.RESET);
			} else {
				ForceMovement.run(player, player.getLocation(), end, Animation.create(1148), Animation.create(1148), Direction.SOUTH, 13).setEndAnimation(Animation.RESET);
			}
			break;
		case 1967:
		case 1968:
			openTreeDoor(player, object);
			return true;
		case 190:
			openGates(player, object);
			return true;
		}
		return true;
	}

	/**
	 * Opens the tree doors.
	 * @param player the player.
	 * @param object the object.
	 */
	private void openTreeDoor(final Player player, final GameObject object) {
		if (object.getCharge() == 88) {
			return;
		}
		object.setCharge(88);
		ObjectBuilder.replace(object, object.transform(object.getId() == 1967 ? 1969 : 1970), 4);
		AgilityHandler.walk(player, -1, player.getLocation(), player.getLocation().transform(0, player.getLocation().getY() <= 3491 ? 2 : -2, 0), new Animation(1426), 0, null);
		GameWorld.getPulser().submit(new Pulse(4) {
			@Override
			public boolean pulse() {
				object.setCharge(1000);
				return true;
			}
		});
	}

	/**
	 * Opens the stronghold gates.
	 * @param player The player.
	 * @param object The door.
	 */
	private void openGates(Player player, final GameObject object) {
		if (object.getCharge() == 88) {
			return;
		}
		object.setCharge(88);
		ObjectBuilder.replace(object, object.transform(191), 4);
		ObjectBuilder.add(new GameObject(192, Location.create(2462, 3383, 0)), 4);
		Location start = Location.create(2461, 3382, 0);
		Location end = Location.create(2461, 3385, 0);
		if (player.getLocation().getY() > object.getLocation().getY()) {
			Location s = start;
			start = end;
			end = s;
		}
		AgilityHandler.walk(player, -1, start, end, new Animation(1426), 0, null);
		GameWorld.getPulser().submit(new Pulse(4) {
			@Override
			public boolean pulse() {
				object.setCharge(1000);
				return true;
			}
		});
	}

	@Override
	public Location getDestination(Node node, Node n) {
		if (n instanceof GameObject) {
			switch (((GameObject) n).getId()) {
			case 190:
				if (node.getLocation().getY() < n.getLocation().getY()) {
					return Location.create(2461, 3382, 0);
				}
				return Location.create(2461, 3385, 0);
			}
		}
		return null;
	}

}
