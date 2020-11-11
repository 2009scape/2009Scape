package plugin.skill.crafting;

import core.cache.def.impl.ObjectDefinition;
import core.game.node.entity.player.link.diary.DiaryType;
import core.game.world.map.Location;
import plugin.dialogue.SkillDialogueHandler;
import plugin.dialogue.SkillDialogueHandler.SkillDialogue;
import plugin.skill.SkillPulse;
import plugin.skill.Skills;
import core.game.interaction.OptionHandler;
import core.game.node.Node;
import core.game.node.entity.player.Player;
import core.game.node.item.Item;
import core.game.node.object.GameObject;
import core.game.world.update.flag.context.Animation;
import core.plugin.InitializablePlugin;
import core.plugin.Plugin;
import core.tools.StringUtils;

/**
 * Represents the plugin used for weaving.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class WeaveOptionPlugin extends OptionHandler {

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ObjectDefinition.setOptionHandler("weave", this);
		return this;
	}

	@Override
	public boolean handle(final Player player, final Node node, String option) {
		new SkillDialogueHandler(player, SkillDialogue.THREE_OPTION, WeavingItem.SACK.getProduct(), WeavingItem.BASKET.getProduct(), WeavingItem.CLOTH.getProduct()) {
			@Override
			public void create(int amount, int index) {
				player.getPulseManager().run(new WeavePulse(player, (GameObject) node, WeavingItem.values()[index], amount));
			}
		}.open();
		return true;
	}

	/**
	 * Represents the weaving pulse.
	 * @author 'Vexia
	 * @version 1.0
	 */
	public static final class WeavePulse extends SkillPulse<GameObject> {

		/**
		 * Represents the animation to use.
		 */
		private static final Animation ANIMATION = new Animation(2270);

		/**
		 * Represents the weaving item.
		 */
		private final WeavingItem type;

		/**
		 * Represents the amount.
		 */
		private int amount;

		/**
		 * Represents the ticks passed.
		 */
		private int ticks;

		/**
		 * Constructs a new {@code WeavePulse} {@code Object}.
		 * @param player the player.
		 * @param node the node.
		 * @param amount the amount.
		 */
		public WeavePulse(Player player, GameObject node, final WeavingItem type, final int amount) {
			super(player, node);
			this.type = type;
			this.amount = amount;
		}

		@Override
		public boolean checkRequirements() {
			if (player.getSkills().getLevel(Skills.CRAFTING) < type.getLevel()) {
				player.getPacketDispatch().sendMessage("You need a Crafting level of at least " + type.getLevel() + " in order to do this.");
				return false;
			}
			if (!player.getInventory().containsItem(type.getRequired())) {
				player.getPacketDispatch().sendMessage("You need " + type.getRequired().getAmount() + " " + type.getRequired().getName().toLowerCase().replace("ball", "balls") + "" + (type == WeavingItem.SACK ? "s" : type == WeavingItem.CLOTH ? "" : "es") + " to weave " + (StringUtils.isPlusN(type.getProduct().getName().toLowerCase()) ? "an" : "a") + " " + type.getProduct().getName().toLowerCase() + ".");
				return false;
			}
			return true;
		}

		@Override
		public void animate() {
			if (ticks % 5 == 0) {
				player.animate(ANIMATION);
			}
		}

		@Override
		public boolean reward() {
			if (++ticks % 5 != 0) {
				return false;
			}
			if (player.getInventory().remove(type.getRequired())) {
				player.getInventory().add(type.getProduct());
				player.getSkills().addExperience(Skills.CRAFTING, type.getExperience(), true);
				player.getPacketDispatch().sendMessage("You weave the "
						+ type.getRequired().getName().toLowerCase().replace("ball", "balls")
						+ "" + (type == WeavingItem.SACK ? "s" : type == WeavingItem.CLOTH ? "" : "es")
						+ " into " + (StringUtils.isPlusN(type.getProduct().getName().toLowerCase()) ? "an" : "a")
						+ " " + type.getProduct().getName().toLowerCase() + ".");
				if (type == WeavingItem.BASKET && node.getId() == 8717 && player.getLocation().withinDistance(new Location(3039,3287,0))
					&& !player.getAchievementDiaryManager().getDiary(DiaryType.FALADOR).isComplete(1,0)) {
					player.getAchievementDiaryManager().getDiary(DiaryType.FALADOR).updateTask(player,1,0, true);
				}
			}
			amount--;
			return amount < 1;
		}

	}

	/**
	 * Represents a weaving item.
	 * @author 'Vexia
	 * @version 1.0
	 */
	public enum WeavingItem {
		SACK(new Item(5418), new Item(5931, 4), 21, 38),
		BASKET(new Item(5376), new Item(5933, 6), 36, 56),
		CLOTH(new Item(3224), new Item(1759, 4), 10, 12);

		/**
		 * Represents the product.
		 */
		private final Item product;

		/**
		 * Represents the required required.
		 */
		private final Item required;

		/**
		 * Represents the level needed.
		 */
		private final int level;

		/**
		 * Represents the experience gained.
		 */
		private final double experience;

		/**
		 * Constructs a new {@code WeavingItem} {@code Object}.
		 * @param product the product.
		 * @param level the level.
		 * @param required the required.
		 * @param experience the experience.
		 */
		private WeavingItem(Item product, final Item required, int level, double experience) {
			this.product = product;
			this.required = required;
			this.level = level;
			this.experience = experience;
		}

		/**
		 * Gets the product.
		 * @return The product.
		 */
		public Item getProduct() {
			return product;
		}

		/**
		 * Gets the required.
		 * @return The required
		 */
		public Item getRequired() {
			return required;
		}

		/**
		 * Gets the level.
		 * @return The level.
		 */
		public int getLevel() {
			return level;
		}

		/**
		 * Gets the experience.
		 * @return The experience.
		 */
		public double getExperience() {
			return experience;
		}
	}
}
