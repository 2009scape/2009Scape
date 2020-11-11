package plugin.interaction.object;

import core.cache.def.impl.ObjectDefinition;
import core.game.interaction.NodeUsageEvent;
import core.game.interaction.OptionHandler;
import core.game.interaction.UseWithHandler;
import core.game.node.Node;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.diary.DiaryType;
import core.game.node.item.Item;
import core.game.world.update.flag.context.Animation;
import core.plugin.InitializablePlugin;
import core.plugin.Plugin;

/**
 * Represents the flour making plugin.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class FlourMakingPlugin extends OptionHandler {

	/**
	 * Represents the animation of operating a hopper.
	 */
	private static final Animation ANIMATION = new Animation(3571);

	/**
	 * Represents the empty pot item.
	 */
	private static final Item EMPTY_POT = new Item(1931);

	/**
	 * Represents the pot of flour.
	 */
	private static final Item FLOUR = new Item(1933);

	/**
	 * Represents the config id.
	 */
	private static final int CONFIG = 695;

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		int controls[] = new int[] { 2718, 2720, 2721, 24072, 24070 };
		for (int i : controls) {
			ObjectDefinition.forId(i).getHandlers().put("option:operate", this);
		}
		ObjectDefinition.forId(36878).getHandlers().put("option:empty", this);
		ObjectDefinition.forId(22420).getHandlers().put("option:empty", this);
		ObjectDefinition.forId(5792).getHandlers().put("option:empty", this);
		ObjectDefinition.forId(1782).getHandlers().put("option:empty", this);
		ObjectDefinition.forId(1781).getHandlers().put("option:empty", this);
		ObjectDefinition.forId(22421).getHandlers().put("option:empty", this);
		ObjectDefinition.forId(24070).getHandlers().put("option:empty", this);
		new GrainHopperPlugin().newInstance(arg);
		new FillPotHandler().newInstance(arg);
		return this;
	}

	@Override
	public boolean handle(Player player, Node node, String option) {
		FlourExtension.extend(player);// doesn't set extension if already there.
		final FlourExtension extension = player.getExtension(FlourExtension.class);
		player.lock(3);
		switch (option) {
		case "operate":
			player.animate(ANIMATION);
			player.getAudioManager().send(3189);
			if (extension.getSemiCharges() < 1) {
				player.getPacketDispatch().sendMessage("You operate the empty hopper. Nothing interesting happens.");
			} else {
				extension.emptyChute();
				player.getPacketDispatch().sendMessage("You operate the hopper. The grain slides down the chute.");
				player.getConfigManager().set(CONFIG, 1);
				player.setAttribute("/save:milling:grain",player.getAttribute("milling:grain",0) + 1);
			}
			break;
		case "empty":
			return empty(player, extension);
		}
		return true;
	}

	/**
	 * Method used to empty the bin.
	 * @param player the player.
	 * @param extension the extension.
	 * @return {@code True} if emptied.
	 */
	private boolean empty(final Player player, final FlourExtension extension) {
		if (extension.getCharges() < 1) {
			return true;
		}
		if (!player.getInventory().containsItem(EMPTY_POT)) {
			player.getPacketDispatch().sendMessage("I need an empty pot to hold the flour in.");
			return true;
		}
		if (player.getInventory().remove(EMPTY_POT)) {
			player.getInventory().add(FLOUR);
			extension.decrement(1);
			player.getPacketDispatch().sendMessage(!extension.isEmpty() ? "You fill a pot with flour from the bin." : "You fill a pot with the last of the flour in the bin.");

			// Grind some flour in the windmill north of Lumbridge
			player.getAchievementDiaryManager().finishTask(player, DiaryType.LUMBRIDGE, 0, 20);

			if (extension.getCharges() < 1) {
				player.getConfigManager().set(CONFIG, 0);
			}
		}
		return true;
	}

	/**
	 * Represents the flour extension class.
	 * @author 'Vexia
	 * @version 1.0
	 */
	public static final class FlourExtension {

		/**
		 * Represents the player.
		 */
		private final Player player;

		/**
		 * Represents the amount of flour charges.
		 */
		private int charges;

		/**
		 * Represents the amount of semi charges.
		 */
		private int semiCharges;

		/**
		 * Constructs a new {@code FlourMakingPlugin} {@code Object}.
		 * @param player the player.
		 */
		FlourExtension(final Player player) {
			this.player = player;
		}

		/**
		 * Method used to extend a flour extension.
		 * @param player the player.
		 */
		public static void extend(final Player player) {
			if (player.getExtension(FlourExtension.class) == null) {
				player.addExtension(FlourExtension.class, new FlourExtension(player));
			}
		}

		/**
		 * Method used to increment the charges.
		 * @param increment the increment.
		 */
		public final void increment(final int increment, boolean semi) {
			if(player.getAttribute("milling:grain",0) < 30) {
				if (semi) {
					semiCharges += increment;
				} else {
					charges += increment;
				}
				player.setAttribute("/save:milling:grain",player.getAttribute("milling:grain",0) + 1);
			}
		}

		/**
		 * Method used to decrement charges.
		 * @param increment the incremend.
		 */
		public final void decrement(final int increment) {
			charges -= increment;
			if(!(player.getAttribute("milling:grain",0) <= 0)){
				player.setAttribute("/save:milling:grain",player.getAttribute("milling:grain",0) + 1);
			}
		}

		/**
		 * Method used to fill a chute.
		 */
		public final void fill() {
			semiCharges += 1;
		}

		/**
		 * Method used to swap charges.
		 */
		public final void emptyChute() {
			charges += semiCharges;
			semiCharges = 0;
		}

		/**
		 * Method used to check if the chute is empty.
		 * @return <code>True</code> if so.
		 */
		public final boolean isEmpty() {
			return charges < 1;
		}

		/**
		 * Gets the charges.
		 * @return The charges.
		 */
		public int getCharges() {
			return charges;
		}

		/**
		 * Gets the semiCharges.
		 * @return The semiCharges.
		 */
		public int getSemiCharges() {
			return semiCharges;
		}

		/**
		 * Sets the semiCharges.
		 * @param semiCharges The semiCharges to set.
		 */
		public void setSemiCharges(int semiCharges) {
			this.semiCharges = semiCharges;
		}

		/**
		 * Sets the charges.
		 * @param charges The charges to set.
		 */
		public void setCharges(int charges) {
			this.charges = charges;
		}

		/**
		 * Gets the player.
		 * @return The player.
		 */
		public Player getPlayer() {
			return player;
		}

	}

	/**
	 * Represents the grain hopper use with handler.
	 * @author 'Vexia
	 * @version 1.0
	 */
	public static final class GrainHopperPlugin extends UseWithHandler {

		/**
		 * Represents the grain item.
		 */
		private static final Item GRAIN = new Item(1947);

		/**
		 * Represents the animation of opperating a hopper.
		 */
		private static final Animation ANIMATION = new Animation(3571);

		/**
		 * Constructs a new {@code GrainHopperPlugin} {@code Object}.
		 */
		public GrainHopperPlugin() {
			super(1947);
		}

		@Override
		public Plugin<Object> newInstance(Object arg) throws Throwable {
			int[] hoppers = new int[] { 2713, 2714, 2716, 2717, 2718, 2720, 2721, 2722, 10170, 15847, 15848, 15849, 15850, 15851, 15852, 15853, 15854, 15873, 15874, 15875, 15876, 15877, 15878, 15879, 15880, 20260, 20261, 20262, 20264, 20265, 20266, 22422, 24071, 24072, 36881 };
			for (int i : hoppers) {
				addHandler(i, OBJECT_TYPE, this);
			}
			addHandler(24075, OBJECT_TYPE, this);// don't think it was added.
			return this;
		}

		@Override
		public boolean handle(NodeUsageEvent event) {
			final Player player = event.getPlayer();
			FlourExtension.extend(player);
			final FlourExtension extension = player.getExtension(FlourExtension.class);
			if (extension.getSemiCharges() > 0) {
				player.getPacketDispatch().sendMessage("There is already grain in the hopper.");
				return true;
			}
			if (player.getInventory().remove(GRAIN)) {
				player.animate(ANIMATION);
				extension.fill();
				player.getPacketDispatch().sendMessage("You put the grain in the hopper.");
			}
			return true;
		}

	}

	/**
	 * Represents the plugin used to fill a bucket by a use-with interaction.
	 * @author 'Vexia
	 * @version 1.0
	 */
	public final class FillPotHandler extends UseWithHandler {

		/**
		 * Constructs a new {@code FillPotHandler} {@code Object}.
		 */
		public FillPotHandler() {
			super(EMPTY_POT.getId());
		}

		@Override
		public Plugin<Object> newInstance(Object arg) throws Throwable {
			addHandler(36878, OBJECT_TYPE, this);
			return this;
		}

		@Override
		public boolean handle(NodeUsageEvent event) {
			final Player player = event.getPlayer();
			FlourExtension.extend(player);// doesn't set extension if already
			// there.
			final FlourExtension extension = player.getExtension(FlourExtension.class);
			player.lock(3);
			empty(player, extension);
			return true;
		}

	}
}
