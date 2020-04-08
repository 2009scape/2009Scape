//package plugin.consumable.food;
//
//import org.crandor.game.content.global.consumable.ConsumableProperties;
//import org.crandor.game.content.global.consumable.CookingProperties;
//import org.crandor.game.content.global.consumable.Food;
//import org.crandor.game.content.global.tutorial.TutorialSession;
//import org.crandor.game.content.global.tutorial.TutorialStage;
//import org.crandor.game.node.Node;
//import org.crandor.game.node.entity.player.Player;
//import org.crandor.plugin.InitializablePlugin;
//import org.crandor.game.node.object.GameObject;
//
///**
// * Represents the shrimp food represented in it's own class because of the
// * details it contains for tutorial island this is an example class on how this
// * consumable system works. We can do our conditions in this class instead of
// * cluttering the {@code CookPulse#reward} method.
// * @author 'Vexia
// * @date 22/12/2013
// */
//@InitializablePlugin
//public class ShrimpsPlugin extends Food {
//
//	/**
//	 * Represents the shrimp properties.
//	 */
//	private static final ShrimpProperties PROPERTIES = new ShrimpProperties();
//
//	/**
//	 * Constructs a new {@code Shrimps} {@code Object}.
//	 */
//	public ShrimpsPlugin() {
//		super(315, 317, 7954, new ConsumableProperties(3), PROPERTIES);
//	}
//
//	@Override
//	public boolean interact(final Player player, final Node node) {
//		int stage = TutorialSession.getExtension(player).getStage();
//		if (stage < TutorialSession.MAX_STAGE) {
//			cook(player, (GameObject) node, 1);
//			return false;
//		}
//		return super.interact(player, node);
//	}
//
//	/**
//	 * Represents the cooking properties of shirmps.
//	 * @author 'Vexia
//	 * @date 22/12/2013
//	 */
//	public static class ShrimpProperties extends CookingProperties {
//
//		/**
//		 * Constructs a new {@code Shrimps} {@code Object}.
//		 */
//		public ShrimpProperties() {
//			super(1, 45, 35, "You successfully cook some shrimps.", CookingProperties.FAIL_MESSAGE);
//		}
//
//		@Override
//		public boolean cook(final Food food, final Player player, final GameObject object) {
//			if (TutorialSession.getExtension(player).getStage() == 14) {
//				TutorialStage.load(player, 15, false);
//				return super.cook(food, player, object, true);
//			} else if (TutorialSession.getExtension(player).getStage() == 15) {
//				TutorialStage.load(player, 16, false);
//				return super.cook(food, player, object, false);
//			}
//			return super.cook(food, player, object);
//		}
//	}
//}
