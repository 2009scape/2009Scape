package plugin.interaction.item.withitem;

import org.crandor.game.component.Component;
import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.interaction.NodeUsageEvent;
import org.crandor.game.interaction.UseWithHandler;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.entity.player.link.RunScript;
import org.crandor.game.node.item.Item;
import org.crandor.game.world.update.flag.context.Animation;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.plugin.Plugin;

/**
 * Represents the fruit cutting plugin.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class FruitCuttingPlugin extends UseWithHandler {

	/**
	 * Represents the cutting banana animation.
	 */
	private static final Animation ANIMATION = new Animation(1192);

	/**
	 * Constructs a new {@code FruitCuttingPlugin} {@code Object}.
	 */
	public FruitCuttingPlugin() {
		super(946);
	}

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		for (Fruit fruit : Fruit.values()) {
			addHandler(fruit.getBase().getId(), ITEM_TYPE, this);
		}
		new FruitCuttingDialogue().init();
		return this;
	}

	@Override
	public boolean handle(NodeUsageEvent event) {
		final Player player = event.getPlayer();
		final Fruit fruit = Fruit.forBase(event.getUsedItem());
		if (fruit == Fruit.BANANA) {
			if (player.getInventory().remove(fruit.getBase())) {
				player.lock(2);
				player.animate(ANIMATION);
				player.getInventory().add(fruit.getSliced());
				player.getPacketDispatch().sendMessage("You deftly chop the bananas into slices.");
			}
			return true;
		}
		player.getDialogueInterpreter().open(31237434, fruit);
		return true;
	}

	/**
	 * Represents the a fruit to cut.
	 * @author 'Vexia
	 * @date 30/11/2013
	 */
	public enum Fruit {
		PINEAPPLE(new Item(2114), new Item(2116), new Item(2118, 4)), BANANA(new Item(1963), null, new Item(3162)), ORANGE(new Item(2108), new Item(2110), new Item(2112));

		/**
		 * Constructs a new {@code FruitCuttingPlugin.java} {@code Object}.
		 * @param base the base item.
		 * @param diced the diced item.
		 * @param sliced the sliced item.
		 */
		Fruit(Item base, Item diced, Item sliced) {
			this.base = base;
			this.diced = diced;
			this.sliced = sliced;
		}

		/**
		 * Represents the base item.
		 */
		private final Item base;

		/**
		 * Represents the diced item.
		 */
		private final Item diced;

		/**
		 * Represents the sliced item.
		 */
		private final Item sliced;

		/**
		 * Gets the base.
		 * @return The base.
		 */
		public Item getBase() {
			return base;
		}

		/**
		 * Gets the diced.
		 * @return The diced.
		 */
		public Item getDiced() {
			return diced;
		}

		/**
		 * Gets the sliced.
		 * @return The sliced.
		 */
		public Item getSliced() {
			return sliced;
		}

		/**
		 * Method used to get the fruit for the base item.
		 * @param item the item.
		 * @return the fruit.
		 */
		public static Fruit forBase(final Item item) {
			for (Fruit fruit : values()) {
				if (fruit.getBase().getId() == item.getId()) {
					return fruit;
				}
			}
			return null;
		}
	}

	/**
	 * Represents the dialogue plugin used to cut fruit.
	 * @author 'Vexia
	 * @version 1.0
	 */
	public static final class FruitCuttingDialogue extends DialoguePlugin {

		/**
		 * Represents the component interface.
		 */
		private static final Component COMPONENT = new Component(140);

		/**
		 * Represents the fruit.
		 */
		private Fruit fruit;

		/**
		 * Represents the fruit to cut.
		 */
		private Item item;

		/**
		 * Represents if its a sliced cut.
		 */
		private boolean slice;

		/**
		 * Constructs a new {@code FruitCuttingDialogue} {@code Object}.
		 */
		public FruitCuttingDialogue() {
			/**
			 * empty.
			 */
		}

		/**
		 * Constructs a new {@code FruitCuttingDialogue} {@code Object}.
		 * @param player the player.
		 */
		public FruitCuttingDialogue(Player player) {
			super(player);
		}

		@Override
		public DialoguePlugin newInstance(Player player) {
			return new FruitCuttingDialogue(player);
		}

		@Override
		public boolean open(Object... args) {
			fruit = ((Fruit) args[0]);
			player.getPacketDispatch().sendString("Would you like to...", 140, 4);
			player.getPacketDispatch().sendItemOnInterface(fruit.getSliced().getId(), 1, 140, 5);
			player.getPacketDispatch().sendItemOnInterface(fruit.getDiced().getId(), 1, 140, 6);
			player.getPacketDispatch().sendString("Slice the " + fruit.getBase().getName().toLowerCase() + ".", 140, 2);
			player.getPacketDispatch().sendString("Dice the " + fruit.getBase().getName().toLowerCase() + ".", 140, 3);
			player.getInterfaceManager().openChatbox(COMPONENT);
			stage = 0;
			return true;
		}

		@Override
		public boolean handle(int interfaceId, int buttonId) {
			switch (stage) {
			case 0:
				player.getInterfaceManager().openChatbox(309);
				player.getPacketDispatch().sendItemZoomOnInterface(1, 160, 309, 2);
				slice = buttonId == 2 ? false : true;
				item = buttonId == 2 ? fruit.getDiced() : fruit.getSliced();
				player.getPacketDispatch().sendString("<br><br><br><br>" + item.getName(), 309, 6);
				player.getPacketDispatch().sendItemZoomOnInterface(item.getId(), 175, 309, 2);
				stage = 1;
				break;
			case 1:
				int amount = 0;
				switch (buttonId) {
				case 6:
					amount = 1;
					break;
				case 5:
					amount = 5;
					break;
				case 4:
					player.setAttribute("runscript", new RunScript() {
						@Override
						public boolean handle() {
							int ammount = (int) value;
							cut(player, ammount, slice);
							return false;
						}
					});
					player.getDialogueInterpreter().sendInput(false, "Enter the amount");
					return true;
				case 3:
					amount = player.getInventory().getAmount(fruit.getBase());
					break;
				}
				cut(player, amount, slice);
				break;
			}
			return true;
		}

		@Override
		public int[] getIds() {
			return new int[] { 31237434 };
		}

		/**
		 * Method used to cut a fruit.
		 * @param player the player.
		 * @param amount the amount.
		 * @param slice if its sliced.
		 */
		private final void cut(final Player player, int amount, boolean slice) {
			if (amount > player.getInventory().getAmount(fruit.getBase())) {
				amount = player.getInventory().getAmount(fruit.getBase());
			}
			end();
			final Item remove = new Item(fruit.getBase().getId(), amount);
			if (!player.getInventory().containsItem(remove)) {
				return;
			}
			if (player.getInventory().remove(remove)) {
				player.getPacketDispatch().sendMessage("You cut the " + fruit.name().toLowerCase() + " into " + (slice ? fruit == Fruit.PINEAPPLE ? "rings" : "slices" : "chunks") + ".");
				for (int i = 0; i < amount; i++) {
					player.getInventory().add(slice ? fruit.getSliced() : fruit.getDiced());
				}
			}
		}

	}
}
