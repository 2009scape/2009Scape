package plugin.quest.free.goblindiplomacy;

import core.cache.def.impl.ItemDefinition;
import core.cache.def.impl.ObjectDefinition;
import core.game.interaction.NodeUsageEvent;
import core.game.interaction.OptionHandler;
import core.game.interaction.UseWithHandler;
import core.game.node.Node;
import core.game.node.entity.player.Player;
import core.game.node.item.GroundItemManager;
import core.game.node.item.Item;
import core.game.node.object.GameObject;
import core.game.world.GameWorld;
import core.plugin.Plugin;
import core.plugin.PluginManager;
import core.tools.StringUtils;

/**
 * Represents the plugin used to handle node interactions related to goblin
 * diplomacy.
 * @author Vexia
 * @version 1.0
 */
public final class GoblinDiplomacyPlugin extends OptionHandler {

	/**
	 * Represents the goblin mail item.
	 */
	private static final Item GOBLIN_MAIL = new Item(288);

	/**
	 * Represents the creates item.
	 */
	private static final int[] CRATES = new int[] { 16557, 16561, 16560, 16559 };

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ItemDefinition.forId(288).getConfigurations().put("option:wear", this);
		for (int object : CRATES) {
			ObjectDefinition.forId(object).getConfigurations().put("option:search", this);
		}
		for (GoblinMailPlugin.GoblinMail mail : GoblinMailPlugin.GoblinMail.values()) {
			ItemDefinition.forId(mail.getProduct().getId()).getConfigurations().put("option:wear", this);
		}
		PluginManager.definePlugin(new GoblinMailPlugin());
		return this;
	}

	@Override
	public boolean handle(Player player, Node node, String option) {
		int id = node instanceof Item ? ((Item) node).getId() : ((GameObject) node).getId();
		switch (option) {
		case "wear":
			player.getPacketDispatch().sendMessage("That armour is to small for a human.");
			break;
		}
		switch (id) {
		case 16559:
		case 16557:
		case 16561:
		case 16560:
			if (player.getAttribute("crate:" + id, 0) < GameWorld.getTicks()) {
				player.setAttribute("crate:" + id, GameWorld.getTicks() + 500);
				if (!player.getInventory().add(GOBLIN_MAIL)) {
					GroundItemManager.create(GOBLIN_MAIL, player);
				}
				player.getDialogueInterpreter().sendItemMessage(GOBLIN_MAIL.getId(), "You find some goblin armour.");
				return true;
			}
			player.getPacketDispatch().sendMessage("You search the crate but find nothing.");
			break;
		}
		return true;
	}

	@Override
	public boolean isWalk() {
		return false;
	}

	@Override
	public boolean isWalk(final Player player, final Node node) {
		return !(node instanceof Item);
	}

	/**
	 * Represents the goblin mail plugin.
	 * @author 'Vexia
	 * @version 1.0
	 */
	public static final class GoblinMailPlugin extends UseWithHandler {

		/**
		 * Constructs a new {@code GoblinMailPlugin} {@code Object}.
		 */
		public GoblinMailPlugin() {
			super(1763, 1769, 1765, 1771, 1767, 1773, 4622, 11808, 6955);
		}

		@Override
		public Plugin<Object> newInstance(Object arg) throws Throwable {
			addHandler(288, ITEM_TYPE, this);
			int[] ids = new int[] { 1763, 1769, 1765, 1771, 1767, 1773, 4622, 11808, 6955 };
			for (int i : ids) {
				addHandler(i, ITEM_TYPE, this);
			}
			return this;
		}

		@Override
		public boolean handle(NodeUsageEvent event) {
			final Player player = event.getPlayer();
			final GoblinMail mail = GoblinMail.forItem(event.getUsedItem());
			final Dyes dye = Dyes.forItem(event.getUsedItem(), event.getBaseItem());
			if (dye != null && (event.getUsedItem().getId() != 288 || event.getBaseItem().getId() != 288)) {
				handleDyeMix(player, dye, event);
				return true;
			}
			if (mail == null || (event.getUsedItem().getId() != 288 && event.getBaseItem().getId() != 288)) {
				return false;
			}
			if (player.getInventory().remove(mail.getDye(), event.getBaseItem())) {
				player.getInventory().add(mail.getProduct());
				player.getPacketDispatch().sendMessage("You dye the goblin armour " + mail.name().toLowerCase() + ".");
			}
			return true;
		}

		/**
		 * Handles the mixing of dyes.
		 * @param player the player.
		 * @param dye the dye.
		 * @param event the event.
		 */
		public void handleDyeMix(final Player player, Dyes dye, NodeUsageEvent event) {
			if (dye == null) {
				player.getPacketDispatch().sendMessage("Those dyes dont mix together.");
				return;
			}
			if (player.getInventory().remove(dye.getMaterials())) {
				player.getInventory().add(dye.getProduct());
			}
			player.getPacketDispatch().sendMessage("You mix the two dyes and make " + (StringUtils.isPlusN(dye.getProduct().getName().toLowerCase().replace("dye", "").trim()) ? "an " : "a ") + dye.getProduct().getName().toLowerCase().replace("dye", "").trim() + " one.");

		}

		/**
		 * Represents the goblin mail.
		 * @author 'Vexia
		 */
		public enum GoblinMail {
			RED(new Item(1763), new Item(9054)), ORANGE(new Item(1769), new Item(286)), YELLOW(new Item(1765), new Item(9056)), GREEN(new Item(1771), new Item(9057)), BLUE(new Item(1767), new Item(287)), PURPLE(new Item(1773), new Item(9058)), BLACK(new Item(4622), new Item(9055)), WHITE(new Item(11808), new Item(11791)), PINK(new Item(6955), new Item(9059));

			/**
			 * Represents the dye needed.
			 */
			private final Item dye;

			/**
			 * Represents the product.
			 */
			private final Item product;

			/**
			 * Constructs a new {@code GoblinMailPlugin} {@code Object}.
			 * @param dye the dye.
			 * @param product the product.
			 */
			GoblinMail(Item dye, Item product) {
				this.dye = dye;
				this.product = product;
			}

			/**
			 * Gets the dye.
			 * @return The dye.
			 */
			public Item getDye() {
				return dye;
			}

			/**
			 * Gets the product.
			 * @return The product.
			 */
			public Item getProduct() {
				return product;
			}

			/**
			 * Method used to get the goblin mail.
			 * @param item the item.
			 * @return the goblin mail.
			 */
			public static GoblinMail forItem(final Item item) {
				for (GoblinMail mail : GoblinMail.values()) {
					if (mail.getDye().getId() == item.getId()) {
						return mail;
					}
				}
				return null;
			}
		}

		/**
		 * Represents the dye types.
		 * @author 'Vexia
		 */
		public static enum Dyes {
			ORANGE(new Item(1769), new Item(1763), new Item(1765)), GREEN(new Item(1771), new Item(1765), new Item(1767)), PURPLE(new Item(1773), new Item(1767), new Item(1763));

			/**
			 * Constructs a new {@code MixDyePlugin} {@code Object}.
			 * @param product the product.
			 * @param materials the materials.
			 */
			Dyes(Item product, Item... materials) {
				this.product = product;
				this.materials = materials;
			}

			/**
			 * Represents the product item.
			 */
			private final Item product;

			/**
			 * Represents the materials.
			 */
			private final Item[] materials;

			/**
			 * Gets the product.
			 * @return The product.
			 */
			public Item getProduct() {
				return product;
			}

			/**
			 * Gets the materials.
			 * @return The materials.
			 */
			public Item[] getMaterials() {
				return materials;
			}

			/**
			 * Method used to get the dye by item.
			 * @param item the item.
			 * @param second the second.
			 * @return the dye.
			 */
			public static Dyes forItem(final Item item, final Item second) {
				for (Dyes dye : Dyes.values()) {
					if (dye.getMaterials()[0].getId() == item.getId() && dye.getMaterials()[1].getId() == second.getId() || dye.getMaterials()[0].getId() == second.getId() && dye.getMaterials()[1].getId() == item.getId()) {
						return dye;
					}
				}
				return null;
			}
		}

	}
}
