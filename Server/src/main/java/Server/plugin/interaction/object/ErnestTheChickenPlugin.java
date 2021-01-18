package plugin.interaction.object;

import java.awt.Point;
import java.util.Arrays;

import core.cache.def.impl.ObjectDefinition;
import core.game.content.global.action.DoorActionHandler;
import plugin.skill.agility.AgilityHandler;
import core.game.interaction.OptionHandler;
import core.game.node.Node;
import core.game.node.entity.Entity;
import core.game.node.entity.player.Player;
import core.game.node.object.GameObject;
import core.game.node.object.ObjectBuilder;
import core.game.system.task.Pulse;
import core.game.world.GameWorld;
import core.game.world.map.Location;
import core.game.world.map.zone.MapZone;
import core.game.world.map.zone.ZoneBuilder;
import core.game.world.update.flag.context.Animation;
import core.plugin.InitializablePlugin;
import core.plugin.Plugin;

/**
 * Represents the ernest the chicken plugin to handle node interactions.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class ErnestTheChickenPlugin extends OptionHandler {

	/**
	 * Represents the pulling down animation.
	 */
	private static final Animation DOWN_ANIMATION = new Animation(2140);

	/**
	 * Represents the pulling up animation.
	 */
	private static final Animation UP_ANIMATION = new Animation(2139);

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ObjectDefinition.forId(11450).getHandlers().put("option:open", this);
		for (Lever lever : Lever.values()) {
			for (int i : lever.getObjectIds()) {
				ObjectDefinition.forId(i).getHandlers().put("option:pull", this);
				ObjectDefinition.forId(i).getHandlers().put("option:inspect", this);
			}
		}
		/** doors */
		ZoneBuilder.configure(new LeverZone());
		return this;
	}

	@Override
	public boolean handle(final Player player, Node node, String option) {
		final GameObject object = ((GameObject) node);
		final LeverCacheExtension extension = LeverCacheExtension.extend(player);
		Lever lever = Lever.forObject(object.getId());
		switch (option) {
		case "pull":
			lever = Lever.forObject(object.getId());
			extension.pull(lever, object);
			break;
		case "inspect":
			extension.inspect(lever);
			break;
		case "open":
			extension.walk(object);
			return true;
		}
		return true;
	}

	/**
	 * Represents an extension class of cached levers for the player.
	 * @author 'Vexia
	 * @date 24/12/2013
	 */
	public static class LeverCacheExtension {

		/**
		 * Represents the config id for the lever.
		 */
		private static final int LEVER_CONFIG = 33;

		/**
		 * Represents the config id for the doors.
		 */
		private static final int DOOR_CONFIG = 668;

		/**
		 * Represents the player.
		 */
		private final Player player;

		/**
		 * Represents the cached levers.
		 */
		private boolean[] levers = new boolean[Lever.values().length];

		/**
		 * Constructs a new {@code ErnestTheChickenPlugin} {@code Object}.
		 * @param player the player.
		 */
		public LeverCacheExtension(final Player player) {
			this.player = player;
		}

		/**
		 * Method used to extend a lever cache extension.
		 * @param player the player.
		 * @return the lever cache extension.
		 */
		public static LeverCacheExtension extend(final Player player) {
			LeverCacheExtension extension = player.getExtension(LeverCacheExtension.class);
			if (extension == null) {
				extension = new LeverCacheExtension(player);
				player.addExtension(LeverCacheExtension.class, extension);
			}
			return extension;
		}

		/**
		 * Method used to pull a lever.
		 * @param lever the lever.
		 * @param object the object.
		 */
		public final void pull(final Lever lever, final GameObject object) {
			final boolean up = isUp(lever);
			levers[lever.ordinal()] = !up;
			player.animate(!up ? UP_ANIMATION : DOWN_ANIMATION);
			GameWorld.getPulser().submit(new Pulse(1) {
				@Override
				public boolean pulse() {
					updateConfigs();
					player.getPacketDispatch().sendMessage("You pull lever " + lever.name().replace("LEVER_", "").trim() + " " + (up ? "down" : "up") + ".");
					player.getPacketDispatch().sendMessage("You hear a clunk.");
					return true;
				}
			});
		}

		/**
		 * Method used to inspect a cached lever.
		 * @param lever the lever.
		 */
		public final void inspect(final Lever lever) {
			player.getPacketDispatch().sendMessage("The lever is " + (isUp(lever) ? "up" : "down") + ".");
		}

		/**
		 * Method used to walk through a door.
		 * @param object the object.
		 */
		public final void walk(final GameObject object) {
			player.lock(4);
			GameWorld.getPulser().submit(new Pulse(1, player, object) {
				@Override
				public boolean pulse() {
					Point p = (Point) getWalkData()[0];
					int[] rotation = (int[]) getWalkData()[1];
					Location destination = (Location) getWalkData()[2];
					final GameObject opened = object.transform(object.getId(), rotation[0]);
					opened.setCharge(88);
					opened.setLocation(object.getLocation().transform((int) p.getX(), (int) p.getY(), 0));
					final GameObject second = DoorActionHandler.getSecondDoor(object, player);
					ObjectBuilder.replace(object, opened, 2);
					if (second != null) {
						final GameObject secondOpened = second.transform(second.getId(), rotation[1]);
						secondOpened.setCharge(88);
						secondOpened.setLocation(second.getLocation().transform((int) p.getX(), (int) p.getY(), 0));
						ObjectBuilder.replace(second, secondOpened, 2);
					}
					AgilityHandler.walk(player, 0, player.getLocation(), destination, null, 0, null);
					return true;
				}
			});
		}

		/**
		 * Method used to update the players config states.
		 */
		public final void updateConfigs() {
			player.getConfigManager().set(LEVER_CONFIG, calculateLeverConfig());
			player.getConfigManager().set(DOOR_CONFIG, calculateDoorConfig());
			save();
		}

		/**
		 * Method used to save the lever states.
		 */
		public final void save() {
			boolean value = false;
			for (int index = 0; index < levers.length; index++) {
				value = levers[index];
				if (!value) {// down.
					player.getSavedData().getQuestData().getDraynorLevers()[index] = false;
				}
			}
		}

		/**
		 * Method used to read the lever states.
		 */
		public final void read() {
			boolean value = false;
			for (int i = 0; i < Lever.values().length; i++) {
				value = player.getSavedData().getQuestData().getDraynorLevers()[i];
				levers[i] = value;
			}
			updateConfigs();
		}

		/**
		 * Method used to calculate the lever config value.
		 * @return the value.
		 */
		public final int calculateLeverConfig() {
			int value = 0;
			for (int i = 0; i < levers.length; i++) {
				if (!levers[i]) {
					value += Math.pow(2, (i + 1));
				}
			}
			return value;
		}

		/**
		 * Method used to calculate the door config value.
		 * @return the value.
		 */
		public final int calculateDoorConfig() {
			final int downCount = getDownCount();
			int value = 0;
			boolean up = false;
			Lever lever = null;
			for (int i = 0; i < levers.length; i++) {
				up = levers[i];// if its up.
				lever = Lever.values()[i];
				if (downCount == 0 || downCount == 1 && !isUp(Lever.LEVER_B) && lever == Lever.LEVER_B && levers[0]) {// translation:
					// no
					// down
					// lever
					// and
					// lever
					// b
					// is
					// on/off
					// just
					// send
					// 0.
					value = 0;
					break;
				}
				if (downCount == 1 && !isUp(Lever.LEVER_A)) {
					return 4;
				}
				if (downCount == 1 && !isUp(Lever.LEVER_D)) {
					return 328;
				}
				if (downCount == 2 && !isUp(Lever.LEVER_D) && !isUp(Lever.LEVER_E)) {
					return 290;
				}
				if (downCount == 2 && !isUp(Lever.LEVER_D) && !isUp(Lever.LEVER_F)) {
					return 64;
				}
				if (downCount == 3 && !isUp(Lever.LEVER_F) && !isUp(Lever.LEVER_D) && !isUp(Lever.LEVER_C) && isUp(Lever.LEVER_A)) {
					return 306;
				}
				if (downCount == 3 && !isUp(Lever.LEVER_E) && !isUp(Lever.LEVER_D) && !isUp(Lever.LEVER_C) && isUp(Lever.LEVER_F)) {
					return 64;
				}
				if (downCount == 5 && !isUp(Lever.LEVER_F) && !isUp(Lever.LEVER_D) && !isUp(Lever.LEVER_C) && !isUp(Lever.LEVER_A) && !isUp(Lever.LEVER_B)) {
					return 304;
				}
				if (!isUp(Lever.LEVER_F) && !isUp(Lever.LEVER_D) && !isUp(Lever.LEVER_C) && isUp(Lever.LEVER_E) && lever == Lever.LEVER_A) {
					return 306;
				}
				if (!isUp(Lever.LEVER_F) && !isUp(Lever.LEVER_D) && !isUp(Lever.LEVER_C) && isUp(Lever.LEVER_E) && isUp(Lever.LEVER_A) && !isUp(Lever.LEVER_B)) {
					return 304;
				}
				if (!isUp(Lever.LEVER_F) && !isUp(Lever.LEVER_D) && !isUp(Lever.LEVER_C) && isUp(Lever.LEVER_E) && !isUp(Lever.LEVER_A) && !isUp(Lever.LEVER_B)) {
					return 304;
				}
				if (downCount == 4 && !isUp(Lever.LEVER_D) && !isUp(Lever.LEVER_C) && !isUp(Lever.LEVER_A) && !isUp(Lever.LEVER_B)) {
					return 264;
				}
				if (downCount == 3 && !isUp(Lever.LEVER_D) && !isUp(Lever.LEVER_F) && !isUp(Lever.LEVER_E)) {
					return 3;
				}
				if (downCount == 2 && !isUp(Lever.LEVER_E) && !isUp(Lever.LEVER_F) && isUp(Lever.LEVER_D)) {
					return 1;
				}
				if (downCount == 4 && !isUp(Lever.LEVER_D) && !isUp(Lever.LEVER_F) && !isUp(Lever.LEVER_E) && !isUp(Lever.LEVER_C)) {
					return 19;
				}
				if (downCount == 3 && !isUp(Lever.LEVER_E) && !isUp(Lever.LEVER_D) && !isUp(Lever.LEVER_C)) {
					return 306;
				}
				if (downCount == 3 && isUp(Lever.LEVER_E) && !isUp(Lever.LEVER_D) && !isUp(Lever.LEVER_C) && !isUp(Lever.LEVER_F)) {
					return 306;
				}
				if (downCount == 2 && !isUp(Lever.LEVER_B) && !isUp(Lever.LEVER_D)) {
					return 64;
				}
				if (downCount == 2 && !isUp(Lever.LEVER_A) && !isUp(Lever.LEVER_D)) {
					return 328;
				}
				if (lever == Lever.LEVER_A && up && !isUp(Lever.LEVER_B)) {
					value -= 132;
					continue;
				} else if (lever == Lever.LEVER_B && up && !isUp(Lever.LEVER_A)) {
					value -= 64;
					continue;
				}
				if (!up) {// if toggled.
					if (lever == Lever.LEVER_A) {
						value += 4;
					} else if (lever == Lever.LEVER_B) {
						value += 128;
					} else if (lever == Lever.LEVER_D) {
						value += 264;
					} else if (lever == Lever.LEVER_C) {
						value -= 132;
					} else if (lever == Lever.LEVER_F) {
						value -= 38;
					} else if (lever == Lever.LEVER_E) {
						value -= 287;
					}
				}
			}
			return value;
		}

		/**
		 * Method used to get the walking data.
		 * @return the data.
		 */
		public final Object[] getWalkData() {
			Object[] data = null;
			final int x = player.getLocation().getX();
			final int y = player.getLocation().getY();
			if (x == 3108 && y == 9757) {
				data = new Object[] { new Point(-1, 0), new int[] { 2, 0 }, Location.create(3108, 9759, 0) };
			} else if (x == 3108 && y == 9759) {
				data = new Object[] { new Point(-1, 0), new int[] { 2, 0 }, Location.create(3108, 9757, 0) };
			} else if (x == 3106 && y == 9760) {
				data = new Object[] { new Point(0, 1), new int[] { 3, 0 }, Location.create(3104, 9760, 0) };
			} else if (x == 3104 && y == 9760) {
				data = new Object[] { new Point(0, 1), new int[] { 3, 0 }, Location.create(3106, 9760, 0) };
			} else if (x == 3104 && y == 9765) {
				data = new Object[] { new Point(0, 1), new int[] { 3, 0 }, Location.create(3106, 9765, 0) };
			} else if (x == 3106 && y == 9765) {
				data = new Object[] { new Point(0, 1), new int[] { 3, 0 }, Location.create(3104, 9765, 0) };
			} else if (x == 3102 && y == 9764) {
				data = new Object[] { new Point(-1, 0), new int[] { 2, 0 }, Location.create(3102, 9762, 0), };
			} else if (x == 3102 && y == 9762) {
				data = new Object[] { new Point(-1, 0), new int[] { 2, 0 }, Location.create(3102, 9764, 0) };
			} else if (x == 3102 && y == 9759) {
				data = new Object[] { new Point(-1, 0), new int[] { 2, 0 }, Location.create(3102, 9757, 0) };
			} else if (x == 3102 && y == 9757) {
				data = new Object[] { new Point(-1, 0), new int[] { 2, 0 }, Location.create(3102, 9759, 0) };
			} else if (x == 3101 && y == 9760) {
				data = new Object[] { new Point(0, 1), new int[] { 3, 0 }, Location.create(3099, 9760, 0) };
			} else if (x == 3099 && y == 9760) {
				data = new Object[] { new Point(0, 1), new int[] { 3, 0 }, Location.create(3101, 9760, 0) };
			} else if (x == 3101 && y == 9765) {
				data = new Object[] { new Point(0, 1), new int[] { 3, 0 }, Location.create(3099, 9765, 0) };
			} else if (x == 3099 && y == 9765) {
				data = new Object[] { new Point(0, 1), new int[] { 3, 0 }, Location.create(3101, 9765, 0) };
			} else if (x == 3097 && y == 9762) {
				data = new Object[] { new Point(-1, 0), new int[] { 2, 0 }, Location.create(3097, 9764, 0) };
			} else if (x == 3097 && y == 9764) {
				data = new Object[] { new Point(-1, 0), new int[] { 2, 0 }, Location.create(3097, 9762, 0) };
			} else if (x == 3101 && y == 9755) {
				data = new Object[] { new Point(0, 1), new int[] { 3, 0 }, Location.create(3099, 9755, 0) };
			} else if (x == 3099 && y == 9755) {
				data = new Object[] { new Point(0, 1), new int[] { 3, 0 }, Location.create(3101, 9755, 0) };
			}
			return data == null ? new Object[] { new Point(0, 0), new int[] { 0, 0 }, player.getLocation() } : data;
		}

		/**
		 * Method ued to reset the levers.
		 */
		public void reset() {
			Arrays.fill(levers, true);
			Arrays.fill(player.getSavedData().getQuestData().getDraynorLevers(), true);
			updateConfigs();
		}

		/**
		 * Gets the count of down levers.
		 * @return the count.
		 */
		public final int getDownCount() {
			int count = 0;
			for (int i = 0; i < levers.length; i++) {
				count += !levers[i] ? 1 : 0;
			}
			return count;
		}

		/**
		 * Method used to check if a lever is up.
		 * @param lever the lever.
		 * @return <code>True</code> if so.
		 */
		public final boolean isUp(final Lever lever) {
			return levers[lever.ordinal()];
		}

		/**
		 * Gets the player.
		 * @return the player.
		 */
		public Player getPlayer() {
			return player;
		}

	}

	/**
	 * Represents the lever zone map area.
	 * @author 'Vexia
	 * @date 26/12/2013
	 */
	public class LeverZone extends MapZone {

		/**
		 * Constructs a new {@code LeverZone} {@code Object}.
		 */
		public LeverZone() {
			super("Draynor lever zone", true);
		}

		@Override
		public void configure() {
			registerRegion(12440);
		}

		@Override
		public boolean enter(final Entity entity) {
			if (!(entity instanceof Player)) {
				return super.enter(entity);
			}
			final Player player = ((Player) entity);
			final LeverCacheExtension extension = LeverCacheExtension.extend(player);
			extension.read();
			return super.enter(entity);
		}

		@Override
		public boolean leave(final Entity entity, boolean logout) {
			if (entity instanceof Player) {
				final Player player = ((Player) entity);
				final LeverCacheExtension extension = LeverCacheExtension.extend(player);
				extension.save();
			}
			return super.leave(entity, logout);
		}
	}

	/**
	 * Represents a lever.
	 * @author 'Vexia
	 * @date 24/12/2013
	 */
	public enum Lever {
		LEVER_A(11451, 11452), LEVER_B(11453, 11454), LEVER_C(11455, 11456), LEVER_D(11457, 11458), LEVER_E(11459, 11460), LEVER_F(11461, 11462);

		/**
		 * Represents the object id.
		 */
		private final int[] objectIds;

		/**
		 * Constructs a new {@code ErnestTheChickenPlugin.java} {@code Object}.
		 * @param objectId
		 */
		Lever(final int... objectId) {
			this.objectIds = objectId;
		}

		/**
		 * Gets the objectId.
		 * @return The objectId.
		 */
		public int[] getObjectIds() {
			return objectIds;
		}

		/**
		 * Gets the lever from the object id.
		 * @param objectId the objectId.
		 * @return the lever.
		 */
		public static Lever forObject(final int objectId) {
			for (Lever lever : Lever.values()) {
				for (int i : lever.getObjectIds()) {
					if (i == objectId) {
						return lever;
					}
				}
			}
			return null;
		}
	}

	@Override
	public Location getDestination(final Node node, final Node n) {
		if (n instanceof GameObject) {
			final Player player = ((Player) node);
			final int x = player.getLocation().getX();
			final int y = player.getLocation().getY();
			Location loc = ((GameObject) n).getLocation();
			if (loc.equals(new Location(3108, 9758, 0))) {
				if (y <= 9757) {
					return Location.create(3108, 9757, 0);
				} else {
					return Location.create(3108, 9759, 0);
				}
			}
			if (loc.equals(new Location(3105, 9760, 0))) {
				if (x >= 3105) {
					return Location.create(3106, 9760, 0);
				} else {
					return Location.create(3104, 9760, 0);
				}
			}
			if (loc.equals(new Location(3105, 9765, 0))) {
				if (x >= 3106) {
					return Location.create(3106, 9765, 0);
				} else {
					return Location.create(3104, 9765, 0);
				}
			}
			if (loc.equals(new Location(3102, 9763, 0))) {
				if (y >= 9764) {
					return Location.create(3102, 9764, 0);
				} else {
					return Location.create(3102, 9762, 0);
				}
			}
			if (loc.equals(new Location(3102, 9758, 0))) {
				if (y >= 9759) {
					return Location.create(3102, 9759, 0);
				} else {
					return Location.create(3102, 9757, 0);
				}
			}
			if (loc.equals(new Location(3100, 9760, 0))) {
				if (x >= 3100) {
					return Location.create(3101, 9760, 0);
				} else {
					return Location.create(3099, 9760, 0);
				}
			}
			if (loc.equals(new Location(3100, 9765, 0))) {
				if (x >= 3100) {
					return Location.create(3101, 9765, 0);
				} else {
					return Location.create(3099, 9765, 0);
				}
			}
			if (loc.equals(new Location(3097, 9763, 0))) {
				if (y <= 9763) {
					return Location.create(3097, 9762, 0);
				} else {
					return Location.create(3097, 9764, 0);
				}
			}
			if (loc.equals(new Location(3100, 9755, 0))) {
				if (x >= 3100) {
					return Location.create(3101, 9755, 0);
				} else {
					return Location.create(3099, 9755, 0);
				}
			}
		}
		return null;
	}

}
