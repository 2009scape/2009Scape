//package plugin.consumable.food;
//
//import org.crandor.game.content.global.consumable.ConsumableProperties;
//import org.crandor.game.content.global.consumable.Consumables;
//import org.crandor.game.content.global.consumable.CookingProperties;
//import org.crandor.game.content.global.consumable.Food;
//import org.crandor.game.content.global.tutorial.TutorialSession;
//import org.crandor.game.node.Node;
//import org.crandor.game.node.entity.player.Player;
//import org.crandor.game.node.object.GameObject;
//import org.crandor.plugin.InitializablePlugin;
//import org.crandor.plugin.Plugin;
//
///**
// * Represents the meat consumable food.
// * @author 'Vexia
// * @version 1.0
// */
//@InitializablePlugin
//public final class MeatPlugin extends Food {
//
//	/**
//	 * Represents the cooking dialogue id.
//	 */
//	private static final int DIALOGUE_ID = 43989;
//
//	/**
//	 * Constructs a new {@code MeatPlugin} {@code Object}.
//	 */
//	public MeatPlugin() {
//		/**
//		 * empty.
//		 */
//	}
//
//	@Override
//	public Plugin<Object> newInstance(Object arg) throws Throwable {
//		Consumables.add(new MeatPlugin(2142, 2132, 2146, new ConsumableProperties(3), new MeatProperty(1, 30, 30, "You cook a piece of beef.", "You accidentally burn the meat.")));
//		Consumables.add(new MeatPlugin(9436, 2132, 2146, null, new MeatProperty(1, 30, 30, "You dry a piece of beef and extract the sinew.", "You accidentally burn the meat.")));
//		Consumables.add(new MeatPlugin(2142, 2134, 2146, new ConsumableProperties(3), new MeatProperty(1, 30, 30, "You cook a piece of rat meat.", "You accidentally burn the meat.")));
//		Consumables.add(new MeatPlugin(2142, 2136, 2146, new ConsumableProperties(3), new MeatProperty(1, 30, 30, "You cook a piece of bear meat.", "You accidentally burn the meat.")));
//		return this;
//	}
//
//	/**
//	 * Constructs a new {@code Meat} {@code Object}.
//	 * @param item the item.
//	 * @param raw the raw item.
//	 * @param burnt the burnt item.
//	 * @param foodProperties the food properties.
//	 * @param cookingProperties the cooking properties.
//	 */
//	public MeatPlugin(int item, int raw, int burnt, ConsumableProperties foodProperties, CookingProperties cookingProperties) {
//		super(item, raw, burnt, foodProperties, cookingProperties);
//	}
//
//	@Override
//	public boolean interact(final Player player, final Node node) {
//		int stage = TutorialSession.getExtension(player).getStage();
//		if (stage < TutorialSession.MAX_STAGE) {
//			cook(player, (GameObject) node, 1);
//			return false;
//		}
//		if (TutorialSession.getExtension(player).getStage() < TutorialSession.MAX_STAGE) {
//			return true;
//		} else {
//			if (!super.interact(player, node)) {
//				return false;
//			}
//			if (this.getRaw().getId() == 2132 && node.getName().toLowerCase().contains("range")) {
//				player.getDialogueInterpreter().open(DIALOGUE_ID, this, node, true);
//				return false;
//			} else {
//				player.getDialogueInterpreter().open(DIALOGUE_ID, this, node);
//			}
//		}
//		return super.interact(player, node);
//	}
//
//	/**
//	 * Represents the meat properties used to override for sinew.
//	 * @author 'Vexia
//	 * @date 23/12/2013
//	 */
//	public static class MeatProperty extends CookingProperties {
//
//		/**
//		 * Constructs a new {@code Meat} {@code Object}.
//		 * @param level the level.
//		 * @param experience the experience.
//		 * @param burnLevel the burn level.
//		 * @param messages the messages.
//		 */
//		public MeatProperty(int level, double experience, int burnLevel, String... messages) {
//			super(level, experience, burnLevel, false, messages);
//		}
//
//		@Override
//		public boolean cook(final Food food, final Player player, final GameObject object) {
//			return super.cook(food, player, object);
//		}
//	}
//}
