package org.crandor.game.content.skill.member.summoning;

import org.crandor.cache.def.impl.CS2Mapping;
import org.crandor.game.component.Component;
import org.crandor.game.content.skill.SkillPulse;
import org.crandor.game.content.skill.Skills;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.item.Item;
import org.crandor.game.node.object.GameObject;
import org.crandor.game.world.map.Location;
import org.crandor.game.world.map.RegionManager;
import org.crandor.game.world.update.flag.context.Animation;

/**
 * Represents a utility class of creating summoning nodes.
 * @author Vexia
 */
public final class SummoningCreator {

	/**
	 * Represents the animation used when creating a node.
	 */
	private static final Animation ANIMATION = new Animation(9068);

	/**
	 * Represents the params used for the accessmask on the pouch creating
	 * interface.
	 */
	private static final Object[] POUCH_PARAMS = new Object[] { "List<col=FF9040>", "Infuse-X<col=FF9040>", "Infuse-All<col=FF9040>", "Infuse-10<col=FF9040>", "Infuse-5<col=FF9040>", "Infuse<col=FF9040>", 20, 4, 669 << 16 | 15 };

	/**
	 * Represents the params used for the accessmask on the scrollc reating
	 * interface.
	 */
	private static final Object[] SCROLL_PARAMS = new Object[] { "Transform-X<col=ff9040>", "Transform-All<col=ff9040>", "Transform-10<col=ff9040>", "Transform-5<col=ff9040>", "Transform<col=ff9040>", 20, 4, 673 << 16 | 15 };

	/**
	 * Represents the summoning component.
	 */
	private static final Component SUMMONING_COMPONENT = new Component(669);

	/**
	 * Represents the scroll component.
	 */
	private static final Component SCROLL_COMPONENT = new Component(673);

	/**
	 * Method used to open the creation screen.
	 * @param player the player.
	 */
	public static final void open(final Player player, final boolean pouch) {
		configure(player, pouch);
	}

	/**
	 * Method used to configure a creation interface.
	 * @param player the player.
	 * @param pouch the pouch.
	 */
	public static final void configure(final Player player, final boolean pouch) {
		player.getInterfaceManager().open(pouch ? SUMMONING_COMPONENT : SCROLL_COMPONENT);
		player.getPacketDispatch().sendRunScript(pouch ? 757 : 765, pouch ? "Iiissssss" : "Iiisssss", pouch ? POUCH_PARAMS : SCROLL_PARAMS);
		player.getPacketDispatch().sendAccessMask(pouch ? 190 : 126, 15, pouch ? 669 : 673, 0, 78);
	}

	/**
	 * Method used to create a summoning node type.
	 * @param player the player.
	 * @param amount the amount.
	 * @param node the node.
	 */
	public static void create(final Player player, final int amount, Object node) {
		if (node == null) {
			return;
		}
		player.getPulseManager().run(new CreatePulse(player, null, SummoningNode.parse(node), amount));
	}

	/**
	 * Method used to list the items needed for a pouch.
	 * @param pouch the pouch.
	 */
	public static void list(final Player player, final SummoningPouch pouch) {
		player.getPacketDispatch().sendMessage((String) CS2Mapping.forId(1186).getMap().get(pouch.getPouchId()));
	}

	/**
	 * Represents the skill pulse used to create a summoning node.
	 * @author 'Vexia
	 */
	public static final class CreatePulse extends SkillPulse<Item> {

		/**
		 * Represents the summoning node type.
		 */
		private final SummoningCreator.SummoningNode type;

		/**
		 * Represents the object.
		 */
		private GameObject object;

		/**
		 * Represents the amount to make.
		 */
		private int amount;

		/**
		 * Constructs a new {@code SummoningCreator} {@code Object}.
		 * @param player the player.
		 * @param node the node.
		 * @param type the type.
		 * @param amount the amount.
		 */
		public CreatePulse(Player player, Item node, final SummoningCreator.SummoningNode type, final int amount) {
			super(player, node);
			this.type = type;
			this.amount = amount;
			this.object = RegionManager.getObject(new Location(2209, 5344, 0));

		}

		@Override
		public boolean checkRequirements() {
			player.getInterfaceManager().close();
			if (player.getSkills().getLevel(Skills.SUMMONING) < type.getLevel()) {
				player.getPacketDispatch().sendMessage("You need a Summoning level of at least " + type.getLevel() + " in order to do this.");
				return false;
			}
			if (amount == 0) {
				player.getPacketDispatch().sendMessage("You don't have the required item(s) to make this.");
				return false;
			}
			for (Item i : type.getRequired()) {
				if (!player.getInventory().containsItem(i)) {
					player.getPacketDispatch().sendMessage("You don't have the required item(s) to make this.");
					return false;
				}
			}
			return true;
		}

		@Override
		public void animate() {
			player.lock(3);
			player.animate(ANIMATION);
		}

		@Override
		public void stop() {
			super.stop();
			player.getPacketDispatch().sendObjectAnimation(object, new Animation(8510));
		}

		@Override
		public boolean reward() {
			if (getDelay() == 1) {
				setDelay(4);
				player.getPacketDispatch().sendObjectAnimation(object, Animation.create(8509));
				return false;
			}
			player.getPacketDispatch().sendObjectAnimation(object, Animation.create(8510));
			for (int i = 0; i < amount; i++) {
				for (Item item : type.getRequired()) {
					if (!player.getInventory().containsItem(item)) {
						return true;
					}
				}
				if (player.getInventory().remove(type.getRequired())) {
					final Item item = type.getProduct();
				    player.getInventory().add(item);
					player.getSkills().addExperience(Skills.SUMMONING, type.getExperience(), true);
				}
			}
			return true;
		}

	}

	/**
	 * Represents a summoning node type.
	 * @author 'Vexia
	 */
	public static class SummoningNode {

		/**
		 * Represents the base object.
		 */
		private final Object base;

		/**
		 * Represents the required items.
		 */
		private final Item[] required;

		/**
		 * Represents the product.
		 */
		private final Item product;

		/**
		 * Represents the experience.
		 */
		private final double experience;

		/**
		 * Represents the level.
		 */
		private final int level;

		/**
		 * Constructs a new {@code SummoningCreator} {@code Object}.
		 * @param required the required items.
		 * @param product the product.
		 * @param experience the experience.
		 * @param level the level.
		 */
		public SummoningNode(final Object base, Item[] required, Item product, double experience, int level) {
			this.base = base;
			this.required = required;
			this.product = product;
			this.experience = experience;
			this.level = level;
		}

		/**
		 * Gets the required.
		 * @return The required.
		 */
		public Item[] getRequired() {
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
		 * Gets the experience.
		 * @return The experience.
		 */
		public double getExperience() {
			return experience;
		}

		/**
		 * Gets the level.
		 * @return The level.
		 */
		public int getLevel() {
			return level;
		}

		/**
		 * Gets the base.
		 * @return The base.
		 */
		public Object getBase() {
			return base;
		}

		/**
		 * Method used to check if the base is a pouch.
		 * @return the pouch.
		 */
		public boolean isPouch() {
			return base instanceof SummoningPouch;
		}

		/**
		 * Method used to parse a summoning node.
		 * @param node the node.
		 * @return the summoning node.
		 */
		public static SummoningNode parse(final Object node) {
			final Item[] required = node instanceof SummoningPouch ? ((SummoningPouch) node).getItems() : createList(((SummoningScroll) node).getItems());
			final Item product = node instanceof SummoningPouch ? new Item(((SummoningPouch) node).getPouchId(), 1) : new Item(((SummoningScroll) node).getItemId(), 10);
			final int level = node instanceof SummoningPouch ? ((SummoningPouch) node).getLevelRequired() : ((SummoningScroll) node).getLevel();
			final double experience = node instanceof SummoningPouch ? ((SummoningPouch) node).getCreateExperience() : ((SummoningScroll) node).getExperience();
			return new SummoningNode(node, required, product, experience, level);
		}

		/**
		 * Method used to create the list.
		 * @param ids the ids.
		 * @return the array of items.
		 */
		private static final Item[] createList(final int... ids) {
			Item[] list = new Item[ids.length];
			for (int i = 0; i < ids.length; i++) {
				list[i] = new Item(ids[i], 1);
			}
			return list;
		}
	}
}