package plugin.interaction.item.withitem;

import core.game.content.ItemNames;
import core.game.interaction.NodeUsageEvent;
import core.game.interaction.UseWithHandler;
import core.game.node.entity.player.Player;
import core.game.node.item.Item;
import core.game.system.task.Pulse;
import core.game.world.update.flag.context.Animation;
import core.plugin.InitializablePlugin;
import core.plugin.Plugin;

import java.util.Arrays;

/**
 * Represents the poison fish food making plugin.
 * @author afaroutdude
 */
@InitializablePlugin
public final class FishfoodPlugin extends UseWithHandler {

	/**
	 * Represents the poisoned fish food item.
	 */
	private static final int FISH_FOOD = ItemNames.FISH_FOOD_272;
	private static final int POISON = ItemNames.POISON_273;
	private static final int POISONED_FISH_FOOD = ItemNames.POISONED_FISH_FOOD_274;

	protected enum FishFoodUses{
		POISONED(POISON, FISH_FOOD, POISONED_FISH_FOOD, "You poison the fish food."),
		GUAMBOX(ItemNames.GROUND_GUAM_6681, ItemNames.AN_EMPTY_BOX_6675, ItemNames.GUAM_IN_A_BOX_6677, "You put the ground Guam into the box."),
		SEAWEEDBOX(ItemNames.GROUND_SEAWEED_6683, ItemNames.AN_EMPTY_BOX_6675, ItemNames.SEAWEED_IN_A_BOX_6679, "You put the ground Seaweed into the box."),
		FOOD1(ItemNames.GROUND_SEAWEED_6683, ItemNames.GUAM_IN_A_BOX_6677, FISH_FOOD, "You put the ground Seaweed into the box and make Fish Food."),
		FOOD2(ItemNames.GROUND_GUAM_6681, ItemNames.SEAWEED_IN_A_BOX_6679, FISH_FOOD, "You put the ground Guam into the box and make Fish Food."),
		FISHBOWL(ItemNames.FISHBOWL, ItemNames.SEAWEED, ItemNames.FISHBOWL_6669, "You place the seaweed in the bowl.");


		private final Item used;
		private final Item with;
		private final Item product;
		private final String msg;

		static protected int[] usables = Arrays.stream(FishFoodUses.values())
				.mapToInt(v -> v.used.getId())
				.toArray();

		FishFoodUses(int used, int with, int product, String msg) {
			this.used = new Item(used);
			this.with = new Item(with);
			this.product = new Item(product);
			this.msg = msg;
		}

		protected static Item productFor(Item used, Item with) {
			for (FishFoodUses value : values()) {
				if (value.used.equals(used) && value.with.equals(with)) {
					return value.product;
				}
			}
			return null;
		}

		protected static String msgFor(Item used, Item with) {
			for (FishFoodUses value : values()) {
				if (value.used.equals(used) && value.with.equals(with)) {
					return value.msg;
				}
			}
			return null;
		}
	}


	/**
	 * Constructs a new {@code FishfoodPlugin} {@code Object}.
	 */
	public FishfoodPlugin() {
		super(FishFoodUses.usables);
	}

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		for (FishFoodUses value : FishFoodUses.values()) {
			addHandler(value.with.getId(), ITEM_TYPE, this);
		}
		return this;
	}

	@Override
	public boolean handle(NodeUsageEvent event) {
		final Player player = event.getPlayer();
		Item used = event.getUsedItem();
		Item with = event.getBaseItem();
		Item product = FishFoodUses.productFor(used, with);

		player.getPulseManager().run(new Pulse(1, player) {
			@Override
			public boolean pulse() {
				if (player.getInventory().remove(with, used)) {
					player.animate(new Animation(1309));
					player.getPacketDispatch().sendMessage(FishFoodUses.msgFor(used, with));
					player.getInventory().add(product);
					player.lock(2);
				}
				return true;
			}

			@Override
			public void stop() {
				super.stop();
			}
		});

		return true;
	}

}
