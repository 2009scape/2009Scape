package plugin.activity.mta.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.crandor.game.interaction.Option;
import org.crandor.game.node.Node;
import org.crandor.game.node.entity.Entity;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.item.GroundItem;
import org.crandor.game.node.item.GroundItemManager;
import org.crandor.game.node.item.Item;
import org.crandor.game.node.object.GameObject;
import org.crandor.game.system.task.Pulse;
import org.crandor.game.world.GameWorld;
import org.crandor.game.world.map.Location;
import org.crandor.game.world.map.RegionManager;
import org.crandor.game.world.map.zone.ZoneBorders;
import org.crandor.game.world.update.flag.context.Animation;
import org.crandor.tools.RandomFunction;

import plugin.activity.mta.MTAZone;

/**
 * Handles the enchanting zone.
 * @author Vexia
 */
public class EnchantingZone extends MTAZone {

	/**
	 * The singleton of this zone.
	 */
	public static final EnchantingZone ZONE = new EnchantingZone();

	/**
	 * The bonus shape.
	 */
	public static Shapes BONUS_SHAPE = RandomFunction.getRandomElement(Shapes.values());

	/**
	 * The random runes to earn.
	 */
	private static final Item[] RUNES = new Item[] { new Item(560, 3), new Item(565, 3), new Item(564, 3) };

	/**
	 * The location of dragon stones.
	 */
	private static final Location[] STONES = new Location[] { Location.create(3354, 9645, 0), Location.create(3353, 9635, 0), Location.create(3359, 9632, 0), Location.create(3375, 9633, 0), Location.create(3374, 9643, 0), Location.create(3373, 9651, 0) };

	/**
	 * The orb item.
	 */
	private static final Item ORB = new Item(6902);

	/**
	 * The players in the zone.
	 */
	private static final List<Player> PLAYERS = new ArrayList<>();

	/**
	 * The guardian.
	 */
	private static NPC guardian;

	/**
	 * The list of d spawns.
	 */
	private static Map<String, List<GroundItem>> DSPAWNS = new HashMap<>();

	/**
	 * The pulse.
	 */
	private static final Pulse PULSE = new Pulse(36) {
		@Override
		public boolean pulse() {
			if (PLAYERS.isEmpty()) {
				return true;
			}
			Shapes shape = null;
			while (shape == null) {
				shape = RandomFunction.getRandomElement(Shapes.values());
				if (shape == BONUS_SHAPE) {
					shape = null;
				}
			}
			BONUS_SHAPE = shape;
			for (Player player : PLAYERS) {
				if (player == null || !player.isActive()) {
					continue;
				}
				BONUS_SHAPE.setAsBonus(player);
			}
			if (guardian != null) {
				guardian.sendChat("The bonus shape has changed to the " + BONUS_SHAPE.name().toLowerCase().replace("_", " ").trim() + ".");
			}
			return false;
		}

	};

	/**
	 * Constructs a new {@Code EnchantingZone} {@Code Object}
	 */
	public EnchantingZone() {
		super("Enchantment Chamber", new Item[] { new Item(6899), new Item(6898), new Item(6900), new Item(6901), new Item(6903), new Item(6902) });
	}

	@Override
	public boolean leave(Entity e, boolean logout) {
		if (e instanceof Player && PLAYERS.contains(e)) {
			PLAYERS.remove(e);
		}
		return super.leave(e, logout);
	}

	@Override
	public boolean enter(Entity e) {
		if (guardian == null) {
			guardian = RegionManager.getNpc(new Location(3361, 9647, 0), 3100, 20);
		}
		if (e instanceof Player && !PLAYERS.contains(e)) {
			PLAYERS.add(e.asPlayer());
			if (!PULSE.isRunning()) {
				PULSE.restart();
				PULSE.start();
				GameWorld.Pulser.submit(PULSE);
			}
			createGroundSpawns(e.asPlayer());
			BONUS_SHAPE.setAsBonus(e.asPlayer());
			update(e.asPlayer());
		}
		return super.enter(e);
	}

	@Override
	public boolean interact(Entity e, Node target, Option option) {
		if (e instanceof Player) {
			Player player = e.asPlayer();
			if (target.getId() >= 10799 && target.getId() <= 10802) {
				Shapes.forId(target.getId()).take(player, target.asObject());
				return true;
			}
			if (target.getId() == 10803) {
				deposit(player);
				return true;
			}
		}
		return super.interact(e, target, option);
	}

	/**
	 * Creates the ground spawns.
	 * @param player
	 */
	public void createGroundSpawns(final Player player) {
		if (DSPAWNS.containsKey(player.getName())) {
			List<GroundItem> items = getGroundSpawns(player);
			for (GroundItem item : items) {
				item.setDropper(player);
			}
			DSPAWNS.put(player.getName(), items);
			return;
		}
		List<GroundItem> items = getGroundSpawns(player);
		for (Location location : STONES) {
			final GroundItem item = new GroundItem(new Item(6903), location, player) {

				@Override
				public boolean isAutoSpawn() {
					return true;
				}

				@Override
				public boolean isActive() {
					return true;
				}

				@Override
				public void respawn() {
					GameWorld.Pulser.submit(getRespawnPulse(this));
				}
			};
			items.add(item);
			GroundItemManager.create(item);
		}
		DSPAWNS.put(player.getName(), items);
	}

	/**
	 * Gets the respawn pulse.
	 * @param item the item.
	 * @return the pulse.
	 */
	public Pulse getRespawnPulse(final GroundItem item) {
		return new Pulse(GameWorld.getSettings().isDevMode() ? 45 : RandomFunction.random(700, 800)) {
			@Override
			public boolean pulse() {
				GroundItemManager.create(item);
				return true;
			}
		};
	}

	/**
	 * Removes the ground spawns.
	 * @param player the player.
	 */
	public void removeGroundSpawns(Player player) {
		List<GroundItem> items = getGroundSpawns(player);
		for (GroundItem item : items) {
			GroundItemManager.destroy(item);
		}
	}

	/**
	 * Gets the ground item spawns.
	 * @param player the player.
	 * @return the items.
	 */
	public List<GroundItem> getGroundSpawns(Player player) {
		List<GroundItem> items = DSPAWNS.get(player.getName());
		if (items == null) {
			items = new ArrayList<GroundItem>();
		}
		DSPAWNS.put(player.getName(), items);
		return items;
	}

	/**
	 * Deposits the players enchanted orbs.
	 * @param player the player.
	 */
	private void deposit(Player player) {
		if (!player.getInventory().containsItem(ORB)) {
			player.sendMessage("You don't have any orbs to deposit.");
			return;
		}
		int amount = player.getInventory().getAmount(ORB);
		player.animate(Animation.create(832));
		player.getInventory().remove(new Item(ORB.getId(), amount));
		int prevAmt = player.getAttribute("mta-orb", 0);
		prevAmt += amount;
		if (prevAmt >= 20) {
			prevAmt = 0;
			player.getInventory().add(RandomFunction.getRandomElement(RUNES), player);
			player.getDialogueInterpreter().sendDialogue("Congratulations! You've been rewarded with an item for your efforts.");
		}
		player.setAttribute("mta-orb", prevAmt);
	}

	@Override
	public void configure() {
		PULSE.stop();
		register(new ZoneBorders(3335, 9612, 3389, 9663, 0, true));
	}

	/**
	 * The shapes around the room to enchant.
	 * @author Vexia
	 */
	public enum Shapes {
		CUBE(10799, new Item(6899)), CYLINDER(10800, new Item(6898)), PENTAMID(10802, new Item(6901)), ICOSAHEDRON(10801, new Item(6900));

		/**
		 * The object of the shape.
		 */
		private final int objectId;

		/**
		 * The item for the shape.
		 */
		private final Item item;

		/**
		 * Constructs a new {@Code Shapes} {@Code Object}
		 * @param objectId
		 * @param item
		 */
		private Shapes(int objectId, Item item) {
			this.objectId = objectId;
			this.item = item;
		}

		/**
		 * Takes from the shape object.
		 * @param player the player.
		 */
		public void take(Player player, GameObject object) {
			if (!player.getInventory().hasSpaceFor(item)) {
				player.sendMessage("You have no space left in your inventory.");
				return;
			}
			player.lock(1);
			player.getInventory().add(item);
			player.animate(Animation.create(827));
		}

		/**
		 * Sets it as the bonus type.
		 * @param player the player.
		 */
		public void setAsBonus(Player player) {
			for (Shapes s : values()) {
				player.getPacketDispatch().sendInterfaceConfig(195, s.getChild(), true);
			}
			player.getPacketDispatch().sendInterfaceConfig(195, getChild(), false);
		}

		/**
		 * Gets the child.
		 * @return the child.
		 */
		public int getChild() {
			return 1 + ordinal();
		}

		/**
		 * Gets the shape by the item.
		 * @param item the item.
		 * @return the shape.
		 */
		public static Shapes forItem(Item item) {
			for (Shapes s : values()) {
				if (s.getItem().getId() == item.getId()) {
					return s;
				}
			}
			return null;
		}

		/**
		 * Gets a shape for the id.
		 * @param id the id.
		 * @return the shape.
		 */
		public static Shapes forId(int id) {
			for (Shapes s : values()) {
				if (s.getObjectId() == id) {
					return s;
				}
			}
			return null;
		}

		/**
		 * Gets the objectId.
		 * @return the objectId
		 */
		public int getObjectId() {
			return objectId;
		}

		/**
		 * Gets the item.
		 * @return the item
		 */
		public Item getItem() {
			return item;
		}

	}
}
