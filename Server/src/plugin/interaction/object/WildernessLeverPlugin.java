package plugin.interaction.object;

import org.crandor.cache.def.impl.ObjectDefinition;
import org.crandor.game.content.dialogue.DialogueInterpreter;
import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.interaction.OptionHandler;
import org.crandor.game.node.Node;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.entity.player.link.TeleportManager;
import org.crandor.game.node.entity.player.link.audio.Audio;
import org.crandor.game.node.object.GameObject;
import org.crandor.game.node.object.ObjectBuilder;
import org.crandor.game.system.task.Pulse;
import org.crandor.game.world.GameWorld;
import org.crandor.game.world.map.Location;
import org.crandor.game.world.update.flag.context.Animation;
import org.crandor.plugin.Plugin;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.plugin.PluginManager;

/**
 * Handles wilderness levers.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class WildernessLeverPlugin extends OptionHandler {

	/**
	 * The animation used when pulling a lever.
	 */
	private static final Animation PULL_ANIMATION = new Animation(2140);

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		for (LeverSets set : LeverSets.values()) {
			for (int id : set.getIds()) {
				ObjectDefinition.forId(id).getConfigurations().put("option:pull", this);
			}
		}
		PluginManager.definePlugin(new LeverDialogue());
		return this;
	}

	@Override
	public boolean handle(Player player, Node node, String option) {
		final GameObject object = (GameObject) node;
		final LeverSets set = LeverSets.forId(object.getId());
		if (!set.canPull(player, object)) {
			return true;
		}
		set.pull(player, set.getIndex(object.getId()));
		if (set.getTransformId() != -1) {
			ObjectBuilder.replace(object, object.transform(set.getTransformId()), 2);
		}
		return true;
	}

	/**
	 * Method used to pull a lever.
	 * @param player the player.
	 * @param lever the lever.
	 */
	private static void pullLever(final Player player, final LeverSets lever, final int index) {
		player.lock(2);
		player.animate(lever.getAnimation());
		player.getAudioManager().send(new Audio(2400));
		GameWorld.submit(new Pulse(2, player) {
			@Override
			public boolean pulse() {
				lever.message(player, index);
				lever.teleport(player, lever.getLocation(index));
				return true;
			}
		});
	}

	/**
	 * Represents a set of levers.
	 * @author 'Vexia
	 */
	public enum LeverSets {
		ARDY("wilderness", 1814, 1815, Location.create(2561, 3311, 0), Location.create(3154, 3923, 0)) {
			@Override
			public void pull(final Player player, final int index, boolean force) {
				if (index == 0 && !force) {
					player.getDialogueInterpreter().open("wilderness-lever", this, getId(index));
				} else {
					pullLever(player, this, index);
				}
			}
		},
		
		MAGE_BANK("mage's cave", 5959, 5960, Location.create(3090, 3956, 0), Location.create(2539, 4712, 0)), ARENA("arena", 9706, 9707, Location.create(3105, 3956, 0), Location.create(3105, 3951, 0)) {
			@Override
			public boolean canPull(Player player, GameObject object) {
				if (!player.getSavedData().getActivityData().hasKilledKolodion() && object.getId() == 9706) {
					player.getDialogueInterpreter().sendDialogues(905, null, "You're not allowed in there. Come downstairs if you", "want to enter my arena.");
					return false;
				}
				return super.canPull(player, object);
			}

			@Override
			public Animation getAnimation() {
				return new Animation(2142);
			}

			@Override
			public int getTransformId() {
				return -1;
			}
		};

		/**
		 * The name.
		 */
		private final String name;

		/**
		 * The lever ids.
		 */
		private final int ids[];

		/**
		 * The locations to teleport to.
		 */
		private final Location[] locations;

		/**
		 * Constructs a new {@code LeverSets} {@code Object}.
		 * @param ids the ids.
		 * @param location the location.
		 */
		private LeverSets(String name, int[] ids, Location... locations) {
			this.name = name;
			this.ids = ids;
			this.locations = locations;
		}

		/**
		 * Constructs a new {@code LeverSets} {@code Object}.
		 * @param first the first id.
		 * @param second the second id.
		 * @param locations the locations.
		 */
		private LeverSets(String name, int first, int second, Location... locations) {
			this(name, new int[] { first, second }, locations);
		}

		/**
		 * Called when a lever object is interacted with.
		 * @param player the player.
		 * @param index the index.
		 * @param force if a forced teleport.
		 */
		public void pull(final Player player, final int index, boolean force) {
			pullLever(player, this, index);
		}

		/**
		 * Used as a wrapper method for {@link #pull(Player, int)}.
		 * @param player the player.
		 * @param index the index.
		 */
		public void pull(final Player player, final int index) {
			pull(player, index, false);
		}

		/**
		 * Sends the lever messages on teleporting.
		 * @param player the player.
		 * @param index the index.
		 */
		public void message(final Player player, final int index) {
			player.getPacketDispatch().sendMessages("You pull the lever...", "...And teleport " + (index == 0 ? "into" : "out of") + " the " + name + ".");
		}

		/**
		 * Teleports a player to a location.
		 * @param player the player.
		 * @param location the location.
		 */
		public void teleport(final Player player, Location location) {
			player.getTeleporter().send(location, TeleportManager.TeleportType.NORMAL, TeleportManager.WILDY_TELEPORT);
		}

		/**
		 * Checks if the player can pull the lever.
		 * @param player the player.
		 * @return {@code True} if so.
		 */
		public boolean canPull(Player player, GameObject object) {
			return true;
		}

		/**
		 * Gets the animation.
		 * @return the animation.
		 */
		public Animation getAnimation() {
			return PULL_ANIMATION;
		}

		/**
		 * Gets the transform id.
		 * @return the id.
		 */
		public int getTransformId() {
			return 1817;
		}

		/**
		 * Gets the index by the id.
		 * @param id the id.
		 * @return the index.
		 */
		public int getIndex(int id) {
			for (int i = 0; i < ids.length; i++) {
				if (ids[i] == id) {
					return i;
				}
			}
			return -1;
		}

		/**
		 * Gets a lever set by the id.
		 * @param id the id.
		 * @return the set.
		 */
		public static LeverSets forId(int id) {
			for (LeverSets set : values()) {
				for (int i : set.getIds()) {
					if (i == id) {
						return set;
					}
				}
			}
			return null;
		}

		/**
		 * Gets the location.
		 * @param index the index.
		 * @return the location.
		 */
		public Location getLocation(int index) {
			return locations[index == 0 ? 1 : 0];
		}

		/**
		 * Gets the id.
		 * @param index the index.
		 * @return the id.
		 */
		public int getId(int index) {
			return ids[index];
		}

		/**
		 * Gets the ids.
		 * @return The ids.
		 */
		public int[] getIds() {
			return ids;
		}

		/**
		 * Gets the location.
		 * @return The location.
		 */
		public Location[] getLocations() {
			return locations;
		}

	}

	/**
	 * Handles the wilderness lever dialogues.
	 * @author 'Vexia
	 * @version 1.0
	 */
	public static final class LeverDialogue extends DialoguePlugin {

		/**
		 * The lever set.
		 */
		private LeverSets lever;

		/**
		 * The object id.
		 */
		private int id;

		/**
		 * Constructs a new {@code LeverDialogue} {@code Object}.
		 */
		public LeverDialogue() {
			/**
			 * empty.
			 */
		}

		/**
		 * Constructs a new {@code LeverDialogue} {@code Object}.
		 * @param player the player.
		 */
		public LeverDialogue(final Player player) {
			super(player);
		}

		@Override
		public DialoguePlugin newInstance(Player player) {
			return new LeverDialogue(player);
		}

		@Override
		public boolean open(Object... args) {
			lever = (LeverSets) args[0];
			id = (int) args[1];
			interpreter.sendDialogue("Warning! Pulling the lever will teleport you deep into the wilderness.");
			return true;
		}

		@Override
		public boolean handle(int interfaceId, int buttonId) {
			switch (stage) {
			case 0:
				options("Yes I'm brave.", "Eep! The wilderness... No thank you.");
				stage++;
				break;
			case 1:
				switch (buttonId) {
				case 1:
					lever.pull(player, lever.getIndex(id), true);
					end();
					break;
				case 2:
					end();
					break;
				}
				break;
			}
			return true;
		}

		@Override
		public int[] getIds() {
			return new int[] { DialogueInterpreter.getDialogueKey("wilderness-lever") };
		}

	}
}
