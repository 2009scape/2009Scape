package plugin.interaction.item.withobject;

import core.game.content.ItemNames;
import core.game.interaction.NodeUsageEvent;
import core.game.interaction.UseWithHandler;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.diary.DiaryType;
import core.game.node.item.Item;
import core.game.node.object.GameObject;
import core.game.system.task.Pulse;
import core.game.world.update.flag.context.Animation;
import core.plugin.InitializablePlugin;
import core.plugin.Plugin;

/**
 * Represents the plugin used to fill a bucket.
 * @author 'Vexia
 * @version 1.5
 */
@InitializablePlugin
public final class WaterSourcePlugin extends UseWithHandler {

	/**
	 * Represents the objects to use the buckets on.
	 */
	private static final int[] OBJECTS = new int[] { 16302, 6827, 11661, 24160, 34577, 15936, 15937, 15938, 23920, 35469, 24265, 153, 879, 880, 2864, 6232, 10436, 10437, 10827, 11007, 11759, 21764, 22973, 24161, 24214, 24265, 28662, 30223, 30820, 34579, 36781, 873, 874, 4063, 6151, 8699, 9143, 9684, 10175, 12279, 12974, 13563, 13564, 14868, 14917, 15678, 16704, 16705, 20358, 22715, 24112, 24314, 25729, 25929, 26966, 29105, 33458, 34082, 34411, 34496, 34547, 34566, 35762, 36971, 37154, 37155, 878, 884, 3264, 3305, 3359, 4004, 4005, 6097, 6249, 6549, 8747, 8927, 11793, 12201, 12897, 24166, 26945, 31359, 32023, 32024, 34576, 35671, 13561, 13563, 13559 };

	/**
	 * Represents the animation to use.
	 */
	private static final Animation ANIMATION = new Animation(832);

	/**
	 * Constructs a new {@code FillBucketPlugin} {@code Object}.
	 */
	public WaterSourcePlugin() {
		super(WaterRecipient.getIds());
	}

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		for (int i : OBJECTS) {
			addHandler(i, OBJECT_TYPE, this);
		}
		return this;
	}

	@Override
	public boolean handle(NodeUsageEvent event) {
		final WaterRecipient recipient = WaterRecipient.forItem(event.getUsedItem());
		recipient.handle(event.getUsedWith().asObject(), event.getPlayer());
		return true;
	}

	/**
	 * Represents a water recipient.
	 * @author 'Vexia
	 * @version 1.0
	 */
	public enum WaterRecipient {
		BUCKET(new Item(1925), new Item(1929)),
		VIAL(new Item(229), new Item(227)),
		JUG(new Item(1935), new Item(1937)),
		BOWL(new Item(1923), new Item(1921)),
		CLAY(new Item(434), new Item(1761), "You mix the clay and water. You now have some soft, workable clay."),
		WATERING_CAN(new Item(5331), new Item(5340), "You fill the watering can."),
		WATERING_CAN7(new Item(5339), new Item(5340), "You fill the watering can."),
		WATERING_CAN6(new Item(5338), new Item(5340), "You fill the watering can."),
		WATERING_CAN5(new Item(5337), new Item(5340), "You fill the watering can."),
		WATERING_CAN4(new Item(5336), new Item(5340), "You fill the watering can."),
		WATERING_CAN3(new Item(5335), new Item(5340), "You fill the watering can."),
		WATERING_CAN2(new Item(5334), new Item(5340), "You fill the watering can."),
		WATERING_CAN1(new Item(5333), new Item(5340), "You fill the watering can."),
		WATER_SKIN0(new Item(1831), new Item(1823), "You fill the waterskin."),
		WATER_SKIN1(new Item(1829),new Item(1823),"You fill the waterskin."),
		WATER_SKIN2(new Item(1827), new Item(1823), "You fill the waterskin."),
		FISHBOWL(new Item(ItemNames.EMPTY_FISHBOWL_6667), new Item(ItemNames.FISHBOWL));

		/**
		 * Represents the required item.
		 */
		private final Item required;

		/**
		 * Represents the product item.
		 */
		private final Item product;

		/**
		 * Represents the message to display.
		 */
		private final String message;

		/**
		 * Constructs a new {@code WaterSourcePlugin} {@code Object}.
		 * @param required the required item.
		 * @param product the product item.
		 * @param message the message item.
		 */
		WaterRecipient(final Item required, final Item product, final String message) {
			this.required = required;
			this.product = product;
			this.message = message;
		}

		/**
		 * Constructs a new {@code WaterSourcePlugin} {@code Object}.
		 * @param required the required item.
		 * @param product the product.
		 */
		WaterRecipient(final Item required, final Item product) {
			this(required, product, "You fill the " + required.getName().toLowerCase() + " with water.");
		}

		/**
		 * Method used to handle the interaction.
		 * @param player the player.
		 */
		public final void handle(final GameObject object, final Player player) {
			if (object.getId() == 11661
					&& required.getId() == ItemNames.BUCKET_1925
					&& !player.getAchievementDiaryManager().getDiary(DiaryType.FALADOR).isComplete(0,7)) {
				player.getPulseManager().run(new Pulse(2, player) {
					@Override
					public boolean pulse() {
						if (player.getInventory().remove(getRequired())) {
							player.animate(ANIMATION);
							player.getPacketDispatch().sendMessage(getMessage());
							player.getInventory().add(getProduct());
							player.getAchievementDiaryManager().getDiary(DiaryType.FALADOR).updateTask(player, 0, 7, true);
						}
						return !player.getInventory().containsItem(getRequired());
					}
				});
			} else {
				player.getPulseManager().run(new Pulse(1, player) {
					@Override
					public boolean pulse() {
						if (player.getInventory().remove(getRequired())) {
							player.animate(ANIMATION);
							player.getPacketDispatch().sendMessage(getMessage());
							player.getInventory().add(getProduct());
						}
						return !player.getInventory().containsItem(getRequired());
					}

					@Override
					public void stop() {
						super.stop();
					}
				});
			}
		}

		/**
		 * Gets the required.
		 * @return The required.
		 */
		public Item getRequired() {
			return required;
		}

		/**
		 * Gets the product.
		 * @return The product.
		 */
		public Item getProduct() {
			return product;
		}

		/**
		 * Gets the message.
		 * @return The message.
		 */
		public String getMessage() {
			return message;
		}

		/**
		 * Gets the recipient ids.
		 * @return the ids.
		 */
		public static int[] getIds() {
			int[] ids = new int[values().length];
			for (int i = 0; i < ids.length; i++) {
				ids[i] = values()[i].getRequired().getId();
			}
			return ids;
		}

		/**
		 * Gets the water recipient for the item.
		 * @param item the item.
		 * @return the {@code WaterRecipient} {@code Object}.
		 */
		public static WaterRecipient forItem(final Item item) {
			for (WaterRecipient recipient : values()) {
				if (recipient.getRequired().getId() == item.getId()) {
					return recipient;
				}
			}
			return null;
		}
	}
}
