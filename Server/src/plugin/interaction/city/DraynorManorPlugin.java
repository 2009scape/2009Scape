package plugin.interaction.city;

import org.crandor.cache.def.impl.ObjectDefinition;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.content.global.action.ClimbActionHandler;
import org.crandor.game.content.global.action.DoorActionHandler;
import org.crandor.game.content.skill.member.agility.AgilityHandler;
import org.crandor.game.interaction.OptionHandler;
import org.crandor.game.node.Node;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.item.GroundItemManager;
import org.crandor.game.node.item.Item;
import org.crandor.game.node.object.GameObject;
import org.crandor.game.node.object.ObjectBuilder;
import org.crandor.game.system.task.Pulse;
import org.crandor.game.world.GameWorld;
import org.crandor.game.world.map.Location;
import org.crandor.game.world.map.RegionManager;
import org.crandor.game.world.map.path.Pathfinder;
import org.crandor.game.world.update.flag.context.Animation;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.plugin.Plugin;

/**
 * Represents the plugin used to handle node interactions at draynor manor.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class DraynorManorPlugin extends OptionHandler {

	/**
	 * Represents the lever animation.
	 */
	private static final Animation LEVER_ANIMATION = new Animation(834);

	/**
	 * Represents the basement location.
	 */
	private static final Location BASEMENT = new Location(3117, 9753, 0);

	/**
	 * Represents the digging animation.
	 */
	private static final Animation DIG_ANIM = new Animation(830);

	/**
	 * Represents the searching animation.
	 */
	private static final Animation SEARCH_ANIM = new Animation(881);

	/**
	 * Represents the spade item.
	 */
	private static final Item SPADE = new Item(952);

	/**
	 * Represents the key item.
	 */
	private static final Item KEY = new Item(275);

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ObjectDefinition.forId(156).getConfigurations().put("option:search", this);
		ObjectDefinition.forId(155).getConfigurations().put("option:search", this);
		ObjectDefinition.forId(160).getConfigurations().put("option:pull", this);
		ObjectDefinition.forId(131).getConfigurations().put("option:open", this);
		ObjectDefinition.forId(133).getConfigurations().put("option:climb-down", this);
		ObjectDefinition.forId(134).getConfigurations().put("option:open", this);
		ObjectDefinition.forId(135).getConfigurations().put("option:open", this);
		ObjectDefinition.forId(152).getConfigurations().put("option:search", this);
		ObjectDefinition.forId(153).getConfigurations().put("option:search", this);
		ObjectDefinition.forId(11498).getConfigurations().put("option:climb-up", this);
		ObjectDefinition.forId(37703).getConfigurations().put("option:squeeze-through", this);
		return this;
	}

	@Override
	public boolean handle(final Player player, Node node, String option) {
		int id = ((GameObject) node).getId();
		switch (id) {
		case 11498:
			ClimbActionHandler.climb(player, new Animation(828), Location.create(3108, 3366, 1));
			break;
		case 160:
		case 156:
		case 155:
			handleBookCase(player, ((GameObject) node));
			break;
		case 133:
			ClimbActionHandler.climb(player, new Animation(827), BASEMENT);
			break;
		case 134:// big doors
		case 135:
			if (player.getLocation().getY() >= 3354) {
				player.getPacketDispatch().sendMessage("The doors won't open.");
				return true;
			}
			player.getPacketDispatch().sendMessage("The doors slam shut behind you.");
			DoorActionHandler.handleDoor(player, (GameObject) node);
			return true;
		case 131:
			if (!player.getInventory().containsItem(KEY)) {
				player.getPacketDispatch().sendMessage("The door is locked.");
			} else {
				DoorActionHandler.handleAutowalkDoor(player, (GameObject) node);
			}
			break;
		case 152:
			if (!player.getInventory().containsItem(SPADE)) {
				player.getDialogueInterpreter().sendDialogues(player, FacialExpression.FURIOUS, "I'm not looking through that with my hands!");
				return true;
			}
			player.lock(3);
			player.animate(DIG_ANIM);
			player.getPacketDispatch().sendMessage("You dig throug the compost...");
			if (!player.getInventory().containsItem(KEY)) {
				player.getPacketDispatch().sendMessage("... and find a small key.");
				if (!player.getInventory().add(KEY)) {
					GroundItemManager.create(KEY, player);
				}
			} else {
				player.getPacketDispatch().sendMessage("... but you find nothing of interest.");
			}
			break;
		case 153:
			player.lock(3);
			player.animate(SEARCH_ANIM);
			player.getDialogueInterpreter().open(3954922);
			break;
		case 37703:
			AgilityHandler.walk(player, 0, player.getLocation(), node.getLocation().transform(player.getLocation().getX() <= 3085 ? 1 : 0, 0, 0), new Animation(1426), 0, null);
			break;
		}
		return true;
	}

	/**
	 * Method used to handle the secret book case opening.
	 * @param player the player.
	 */
	private final void handleBookCase(final Player player, final GameObject object) {
		Location dest = null;
		if (RegionManager.getObject(Location.create(3097, 3359, 0)) == null || RegionManager.getObject(Location.create(3097, 3358, 0)) == null) {
			return;
		}
		if (player.getLocation().getX() > 3096) {
			if (player.getLocation().getY() >= 3359) {
				dest = Location.create(3096, 3359, 0);
			} else {
				dest = Location.create(3096, 3358, 0);
			}
		} else {
			if (object.getId() != 160) {
				return;
			}
			if (player.getLocation().getY() >= 3359) {
				dest = Location.create(3098, 3359, 0);
			} else {
				dest = Location.create(3098, 3358, 0);
			}
		}
		final Location destination = dest;
		if (object.getId() == 160) {
			GameWorld.submit(new Pulse(1, player) {
				int counter = 0;

				@Override
				public boolean pulse() {
					switch (counter++) {
					case 1:
						player.getPacketDispatch().sendMessage("The lever opens the secret door!");
						player.animate(LEVER_ANIMATION);
						break;
					case 2:
						ObjectBuilder.replace(object, object.transform(161), 6);
						break;
					case 3:
						Pathfinder.find(player, Location.create(3096, 3358, 0)).walk(player);
						break;
					case 4:
						player.faceLocation(destination);
						break;
					case 5:
						ObjectBuilder.remove(RegionManager.getObject(Location.create(3097, 3359, 0)));
						ObjectBuilder.remove(RegionManager.getObject(Location.create(3097, 3358, 0)));
						ObjectBuilder.add(new GameObject(156, new Location(3097, 3357, 0)));
						ObjectBuilder.add(new GameObject(157, new Location(3097, 3360, 0)));
						break;
					case 6:
						AgilityHandler.walk(player, -1, player.getLocation(), destination, null, 0.0, null);
						break;
					case 8:
						ObjectBuilder.remove(RegionManager.getObject(new Location(3097, 3360, 0)));
						ObjectBuilder.remove(RegionManager.getObject(new Location(3097, 3357, 0)));
						ObjectBuilder.add(new GameObject(155, new Location(3097, 3359, 0)));
						ObjectBuilder.add(new GameObject(156, new Location(3097, 3358, 0)));
						break;
					}
					return false;
				}
			});
			return;
		}
		GameWorld.submit(new Pulse(1, player) {
			int count = 0;

			@Override
			public boolean pulse() {
				switch (count) {
				case 0:
					player.getPacketDispatch().sendMessage("You've found a secret door!");
					ObjectBuilder.remove(RegionManager.getObject(Location.create(3097, 3359, 0)));
					ObjectBuilder.remove(RegionManager.getObject(Location.create(3097, 3358, 0)));
					ObjectBuilder.add(new GameObject(156, new Location(3097, 3357, 0)));
					ObjectBuilder.add(new GameObject(157, new Location(3097, 3360, 0)));
					break;
				case 1:
					AgilityHandler.walk(player, -1, player.getLocation(), destination, null, 0.0, null);
					break;
				case 3:
					ObjectBuilder.remove(RegionManager.getObject(new Location(3097, 3360, 0)));
					ObjectBuilder.remove(RegionManager.getObject(new Location(3097, 3357, 0)));
					ObjectBuilder.add(new GameObject(155, new Location(3097, 3359, 0)));
					ObjectBuilder.add(new GameObject(156, new Location(3097, 3358, 0)));
					break;
				}
				count++;
				return count == 4;
			}

		});
	}

	@Override
	public Location getDestination(Node node, Node n) {
		if (n instanceof GameObject) {
			final int id = ((GameObject) n).getId();
			switch (id) {
			case 155:
				return Location.create(3098, 3359, 0);
			case 156:
				return Location.create(3098, 3358, 0);
			case 160:
				return Location.create(3096, 3357, 0);
			}
		}
		return null;
	}
}
