package plugin.activity.puropuro;

import java.util.ArrayList;
import java.util.List;

import org.crandor.cache.def.impl.ItemDefinition;
import org.crandor.cache.def.impl.NPCDefinition;
import org.crandor.cache.def.impl.ObjectDefinition;
import org.crandor.game.component.Component;
import org.crandor.game.content.skill.Skills;
import org.crandor.game.interaction.Option;
import org.crandor.game.interaction.OptionHandler;
import org.crandor.game.node.Node;
import org.crandor.game.node.entity.Entity;
import org.crandor.game.node.entity.impl.ForceMovement;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.entity.player.link.TeleportManager.TeleportType;
import org.crandor.game.node.item.Item;
import org.crandor.game.node.object.GameObject;
import org.crandor.game.node.object.ObjectBuilder;
import org.crandor.game.system.task.Pulse;
import org.crandor.game.world.GameWorld;
import org.crandor.game.world.map.Direction;
import org.crandor.game.world.map.Location;
import org.crandor.game.world.map.RegionManager;
import org.crandor.game.world.map.zone.MapZone;
import org.crandor.game.world.map.zone.ZoneBuilder;
import org.crandor.game.world.map.zone.ZoneType;
import org.crandor.game.world.update.flag.context.Animation;
import org.crandor.net.packet.PacketRepository;
import org.crandor.net.packet.context.MinimapStateContext;
import org.crandor.net.packet.out.MinimapState;
import org.crandor.plugin.Plugin;
import org.crandor.plugin.PluginManager;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.tools.RandomFunction;

/**
 * Handles the puro puro activity.
 * @author Vexia
 */
@InitializablePlugin
public final class PuroPuroPlugin extends MapZone implements Plugin<Object> {

	/**
	 * The moving wheat.
	 */
	private static final List<WheatSet> WHEAT = new ArrayList<>();

	/**
	 * The pulse.
	 */
	private static final Pulse PULSE = new Pulse(1) {
		@Override
		public boolean pulse() {
			for (WheatSet set : WHEAT) {
				if (set.canWhilt()) {
					set.whilt();
				}
			}
			return false;
		}
	};

	/**
	 * Constructs a new {@code PuroPuroActivity} {@code Object}.
	 */
	public PuroPuroPlugin() {
		super("puro puro", true);
		setZoneType(ZoneType.SAFE.getId());
	}

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		PULSE.stop();
		ZoneBuilder.configure(this);
		PluginManager.definePlugin(new FairyAerykaDialogue());
		PluginManager.definePlugin(new WanderingImplingDialogue());
		PluginManager.definePlugin(new ElnockInquisitorDialogue());
		PluginManager.definePlugin(new PuroOptionHandler());
		PluginManager.definePlugin(new ImpDefenderNPC());
		return this;
	}

	@Override
	public Object fireEvent(String identifier, Object... args) {
		return null;
	}

	@Override
	public boolean enter(Entity e) {
		if (e instanceof Player) {
			Player p = e.asPlayer();
			PacketRepository.send(MinimapState.class, new MinimapStateContext(p, 2));
		}
		if (!PULSE.isRunning()) {
			spawnWheat();
			PULSE.restart();
			PULSE.start();
			GameWorld.Pulser.submit(PULSE);
		}
		return super.enter(e);
	}

	@Override
	public boolean leave(Entity e, boolean logout) {
		if (e instanceof Player) {
			Player p = e.asPlayer();
			if (!logout) {
				p.getInterfaceManager().close();
				p.getInterfaceManager().closeOverlay();
				PacketRepository.send(MinimapState.class, new MinimapStateContext(p, 0));
			}
		}
		return super.leave(e, logout);
	}

	@Override
	public boolean interact(Entity e, Node target, Option option) {
		if (e instanceof Player) {
			Player p = (Player) e;
			switch (target.getId()) {
			case 25016:
			case 25029:
			case 25019:
			case 25018:
			case 25020:
			case 25021:
				pushThrough(p, (GameObject) target);
				return true;
			case 25014:
				p.getTeleporter().send(Location.create(2427, 4446, 0), TeleportType.PURO_PURO);
				return true;
			}
		}
		return super.interact(e, target, option);
	}

	/**
	 * Pushes through the wheat.
	 * @param player the player.
	 * @param object the object.
	 */
	private void pushThrough(final Player player, final GameObject object) {
		if (player.getSkills().getStaticLevel(Skills.HUNTER) < 17) {
			player.sendMessage("You need a Hunting level of at least 17 to enter the maze.");
			return;
		}
		if (hasImplingBox(player)) {
			player.getDialogueInterpreter().sendDialogue("Something prevents you from entering. You think the portal is", "offended by your imp boxes. They are not popular on imp", "and impling planes.");
			return;
		}
		final Location dest = object.getLocation().transform(Direction.getLogicalDirection(player.getLocation(), object.getLocation()), 1);
		if (RegionManager.getObject(dest) != null) {
			player.sendMessage("An object on the other side is in your way.");
			return;
		}
		if (RandomFunction.random(2) == 0) {
			player.sendMessage("You use your strength to push through the wheat.");
		} else {
			player.sendMessage("You use your strength to push through the wheat. It's hard work though.");
		}
		player.setAttribute("cantMove", true);
		GameWorld.Pulser.submit(new Pulse(1) {
			@Override
			public boolean pulse() {
				ForceMovement.run(player, player.getLocation(), dest, Animation.create(6594), Animation.create(6594), Direction.getLogicalDirection(player.getLocation(), object.getLocation()), 3, 3);
				return true;
			}
		});
	}

	/**
	 * Spawns the wheat.
	 */
	private void spawnWheat() {
		for (WheatSet set : WHEAT) {
			set.init();
		}
	}

	/**
	 * Checks if the player has an impling box.
	 * @param player the player.
	 * @return {@code True} if so.
	 */
	private boolean hasImplingBox(Player player) {
		return player.getInventory().contains(10025, 1) || player.getInventory().contains(10027, 1) || player.getInventory().contains(10028, 1);
	}

	@Override
	public void configure() {
		registerRegion(10307);
		WHEAT.add(new WheatSet(0, Location.create(2606, 4329, 0), Location.create(2606, 4328, 0)));
		WHEAT.add(new WheatSet(1, Location.create(2596, 4331, 0), Location.create(2597, 4331, 0)));
		WHEAT.add(new WheatSet(0, Location.create(2580, 4326, 0), Location.create(2580, 4325, 0)));
		WHEAT.add(new WheatSet(1, Location.create(2595, 4308, 0), Location.create(2596, 4308, 0)));
		WHEAT.add(new WheatSet(0, Location.create(2603, 4314, 0), Location.create(2603, 4313, 0)));
		WHEAT.add(new WheatSet(1, Location.create(2599, 4305, 0), Location.create(2600, 4305, 0)));
		WHEAT.add(new WheatSet(0, Location.create(2577, 4327, 0), Location.create(2577, 4328, 0)));
		WHEAT.add(new WheatSet(1, Location.create(2587, 4334, 0), Location.create(2586, 4334, 0)));
		WHEAT.add(new WheatSet(0, Location.create(2609, 4310, 0), Location.create(2609, 4309, 0)));
		WHEAT.add(new WheatSet(1, Location.create(2586, 4302, 0), Location.create(2587, 4302, 0)));
		WHEAT.add(new WheatSet(0, Location.create(2574, 4310, 0), Location.create(2574, 4311, 0)));
		WHEAT.add(new WheatSet(1, Location.create(2582, 4337, 0), Location.create(2581, 4337, 0)));
		WHEAT.add(new WheatSet(0, Location.create(2571, 4316, 0), Location.create(2571, 4315, 0)));
		WHEAT.add(new WheatSet(1, Location.create(2601, 4340, 0), Location.create(2602, 4340, 0)));
		WHEAT.add(new WheatSet(0, Location.create(2612, 4324, 0), Location.create(2612, 4323, 0)));
		WHEAT.add(new WheatSet(1, Location.create(2584, 4296, 0), Location.create(2583, 4296, 0)));
		WHEAT.add(new WheatSet(0, Location.create(2568, 4329, 0), Location.create(2568, 4330, 0)));
		WHEAT.add(new WheatSet(1, Location.create(2595, 4343, 0), Location.create(2596, 4343, 0)));
		WHEAT.add(new WheatSet(0, Location.create(2615, 4315, 0), Location.create(2615, 4314, 0)));
		WHEAT.add(new WheatSet(1, Location.create(2601, 4293, 0), Location.create(2600, 4293, 0)));
		WHEAT.add(new WheatSet(0, Location.create(2565, 4310, 0), Location.create(2565, 4311, 0)));
		WHEAT.add(new WheatSet(1, Location.create(2582, 4346, 0), Location.create(2583, 4346, 0)));
		WHEAT.add(new WheatSet(0, Location.create(2621, 4328, 0), Location.create(2621, 4329, 0)));
		WHEAT.add(new WheatSet(1, Location.create(2602, 4349, 0), Location.create(2603, 4349, 0)));
		WHEAT.add(new WheatSet(0, Location.create(2562, 4334, 0), Location.create(2562, 4333, 0)));
		WHEAT.add(new WheatSet(1, Location.create(2582, 4290, 0), Location.create(2581, 4290, 0)));
		WHEAT.add(new WheatSet(0, Location.create(2568, 4348, 0), Location.create(2568, 4347, 0)));
		WHEAT.add(new WheatSet(0, Location.create(2615, 4347, 0), Location.create(2615, 4348, 0)));
		WHEAT.add(new WheatSet(0, Location.create(2612, 4345, 0), Location.create(2612, 4344, 0)));
		WHEAT.add(new WheatSet(0, Location.create(2614, 4292, 0), Location.create(2614, 4291, 0)));
		WHEAT.add(new WheatSet(0, Location.create(2568, 4292, 0), Location.create(2568, 4291, 0)));
		WHEAT.add(new WheatSet(0, Location.create(2571, 4295, 0), Location.create(2571, 4294, 0)));
		WHEAT.add(new WheatSet(0, Location.create(2575, 4297, 0), Location.create(2575, 4298, 0)));
		WHEAT.add(new WheatSet(0, Location.create(2584, 4330, 0), Location.create(2584, 4329, 0)));
		WHEAT.add(new WheatSet(0, Location.create(2599, 4329, 0), Location.create(2599, 4330, 0)));
		WHEAT.add(new WheatSet(1, Location.create(2602, 4312, 0), Location.create(2601, 4312, 0)));
		WHEAT.add(new WheatSet(1, Location.create(2610, 4312, 0), Location.create(2611, 4312, 0)));
		WHEAT.add(new WheatSet(1, Location.create(2570, 4309, 0), Location.create(2569, 4309, 0)));
		WHEAT.add(new WheatSet(0, Location.create(2583, 4304, 0), Location.create(2583, 4303, 0)));
	}

	/**
	 * Handles the puro puro options.
	 * @author Vexia
	 */
	public class PuroOptionHandler extends OptionHandler {

		@Override
		public Plugin<Object> newInstance(Object arg) throws Throwable {
			NPCDefinition.forId(6070).getConfigurations().put("option:trade", this);
			ObjectDefinition.forId(24991).getConfigurations().put("option:enter", this);
			ItemDefinition.forId(11273).getConfigurations().put("option:toggle-view", this);
			ItemDefinition.forId(11258).getConfigurations().put("option:butterfly-jar", this);
			ItemDefinition.forId(11258).getConfigurations().put("option:impling-jar", this);
			ItemDefinition.forId(11258).getConfigurations().put("option:check", this);
			return this;
		}

		@Override
		public boolean handle(Player player, Node node, String option) {
			switch (node.getId()) {
			case 11258:
				handleJarGenerator(player, (Item) node, option);
				break;
			case 6070:
				ElnockInquisitorDialogue.openShop(player);
				break;
			case 24991:
				if (hasImplingBox(player)) {
					player.getDialogueInterpreter().sendDialogue("Something prevents you from entering. You think the portal is offended by", "your imp boxes. They are not popular on imp and impling planes.");
					break;
				}
				player.getTeleporter().send(Location.create(2591, 4320, 0), TeleportType.PURO_PURO);
				return true;
			case 11273:
				if (!player.getZoneMonitor().isInZone("puro puro")) {
					player.sendMessage("You can only use this in the Puro Puro Maze.");
					break;
				} else {
					if (player.getInterfaceManager().getOverlay() != null) {
						player.getInterfaceManager().closeOverlay();
						break;
					}
					player.getInterfaceManager().openOverlay(new Component(541));
				}
				return true;
			}
			return true;
		}

		/**
		 * Handles the jar generator.
		 * @param player the player.
		 * @param item the item.
		 * @param option the option.
		 */
		private void handleJarGenerator(Player player, Item item, String option) {
			switch (option) {
			case "butterfly-jar":
			case "impling-jar":
				generate(player, item, option);
				break;
			case "check":
				player.sendMessage("Your jar generator has a charge percentage of " + getPercent(item) + ".");
				break;
			}
		}

		/**
		 * Generates a new jar.
		 * @param player the player.
		 * @param item the item.
		 * @param option the option.
		 */
		private void generate(Player player, Item item, String option) {
			final Item jar = option.equals("butterfly-jar") ? new Item(10012) : new Item(11260);
			final int percent = jar.getId() == 10012 ? 1 : 3;
			if (!hasPercent(item, percent)) {
				player.sendMessage("Your jar generator doesn't have enough charges to make another " + jar.getName().toLowerCase() + ".");
				return;
			}
			player.lock(5);
			player.animate(new Animation(6592));
			player.getInventory().add(jar);
			setPercent(item, percent);
			player.sendMessage("Your jar generator generates a " + jar.getName().toLowerCase() + ".");
			if (getPercent(item) <= 0) {
				player.getInventory().remove(item);
				player.sendMessage("Your jar generator runs out of charges.");
			}
		}

		/**
		 * Checks if the player has the percent.
		 * @param item the item.
		 * @param percent the percent.
		 * @return
		 */
		private boolean hasPercent(Item item, int percent) {
			return getPercent(item) - percent >= 0;
		}

		/**
		 * The percent to set.
		 * @param item the item.
		 * @param percent the percent.
		 */
		private void setPercent(Item item, int percent) {
			item.setCharge(item.getCharge() - percent);
		}

		/**
		 * Gets the percent.
		 * @param item the item.
		 * @return the percent.
		 */
		private int getPercent(Item item) {
			int difference = item.getCharge() - 1000;
			return difference + 100;
		}

		@Override
		public boolean isWalk(Player p, Node n) {
			return !(n instanceof Item);

		}

		@Override
		public boolean isWalk() {
			return false;
		}
	}

	/**
	 * A wheat set.
	 * @author Vexia
	 */
	public static class WheatSet {

		/**
		 * The locations of the wheat.
		 */
		private final Location[] locations;

		/**
		 * The game objects.
		 */
		private GameObject[] objects = new GameObject[2];

		/**
		 * The rotation.
		 */
		private int rot;

		/**
		 * The time until the next whilt.
		 */
		private int nextWhilt;

		/**
		 * The busy ticks.
		 */
		private int busyTicks;

		/**
		 * If the wheat is removed.
		 */
		private boolean removed;

		/**
		 * Constructs a new {@code WheatSet} {@code Object}.
		 * @param locations the locations.
		 */
		public WheatSet(int rot, Location... locations) {
			this.rot = rot;
			this.locations = locations;
		}

		/**
		 * Initializes the wheat.
		 */
		public void init() {
			int index = 0;
			for (Location location : locations) {
				GameObject object = new GameObject(25021, location, 22, rot);
				ObjectBuilder.add(object);
				objects[index] = object;
				index++;
			}
			setNextWhilt();
		}

		/**
		 * Whilts the wheat.
		 */
		public void whilt() {
			busyTicks = GameWorld.getTicks() + 5;
			for (GameObject object : objects) {
				if (object == null) {
					continue;
				}
				if (removed) {
					ObjectBuilder.add(object);
					continue;
				}
				ObjectBuilder.remove(object);
			}
			removed = !removed;
			setNextWhilt();
		}

		/**
		 * Sets the object spawns.
		 */
		public void setObjects() {
			for (int i = 0; i < locations.length; i++) {
				objects[i] = RegionManager.getObject(locations[i]);
			}
		}

		/**
		 * Sets the next whilt.
		 */
		public void setNextWhilt() {
			this.nextWhilt = GameWorld.getTicks() + RandomFunction.random(40, 300);
		}

		/**
		 * Checks if the wheat can whilt.
		 * @return {@code True} if so.
		 */
		public boolean canWhilt() {
			return GameWorld.getTicks() > nextWhilt && GameWorld.getTicks() > busyTicks;
		}

		/**
		 * Gets the nextWhilt.
		 * @return The nextWhilt.
		 */
		public int getNextWhilt() {
			return nextWhilt;
		}

		/**
		 * Sets the nextWhilt.
		 * @param nextWhilt The nextWhilt to set.
		 */
		public void setNextWhilt(int nextWhilt) {
			this.nextWhilt = nextWhilt;
		}

		/**
		 * Gets the locations.
		 * @return The locations.
		 */
		public Location[] getLocations() {
			return locations;
		}

	}

}
