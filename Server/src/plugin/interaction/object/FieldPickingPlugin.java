package plugin.interaction.object;

import org.crandor.cache.def.impl.ObjectDefinition;
import org.crandor.game.container.impl.EquipmentContainer;
import org.crandor.game.interaction.OptionHandler;
import org.crandor.game.node.Node;
import org.crandor.game.node.entity.combat.ImpactHandler.HitsplatType;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.entity.player.link.diary.DiaryType;
import org.crandor.game.node.item.Item;
import org.crandor.game.node.object.GameObject;
import org.crandor.game.node.object.ObjectBuilder;
import org.crandor.game.system.task.Pulse;
import org.crandor.game.world.GameWorld;
import org.crandor.game.world.update.flag.context.Animation;
import org.crandor.plugin.Plugin;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.tools.RandomFunction;

/**
 * Handles the pick option of interactive scenery (non-farming cabbages,
 * potatoes, bananas, etc)
 * @author Emperor
 * @version 1.0
 */
@InitializablePlugin
public final class FieldPickingPlugin extends OptionHandler {

	/**
	 * The picking animation to use.
	 */
	private static final Animation ANIMATION = new Animation(827);

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ObjectDefinition.setOptionHandler("pick", this);
		return this;
	}

	@Override
	public boolean handle(final Player player, Node node, String option) {
		if (player.getAttribute("delay:picking", -1) > GameWorld.getTicks()) {
			return true;
		}
		final GameObject object = (GameObject) node;
		final PickingPlant plant = PickingPlant.forId(object.getId());
		if (plant == null) {
			return false;
		}
		if (!object.isActive()) {
			return true;
		}
		final Item reward = new Item(plant == PickingPlant.POTATO && RandomFunction.random(10) == 0 ? 5318 : plant.reward);
		if (!player.getInventory().hasSpaceFor(reward)) {
			player.getPacketDispatch().sendMessage("Not enough space in your inventory!");
			return true;
		}
		if (player.getInventory().freeSlot() == -1) {
			return true;
		}
		player.lock(1);
		player.setAttribute("delay:picking", GameWorld.getTicks() + (plant == PickingPlant.FLAX ? 2 : 3));
		player.animate(ANIMATION);
		if (plant.name().startsWith("NETTLES") && (player.getEquipment().get(EquipmentContainer.SLOT_HANDS) == null || player.getEquipment().get(EquipmentContainer.SLOT_HANDS) != null && !player.getEquipment().get(EquipmentContainer.SLOT_HANDS).getName().contains("glove"))) {
			player.getPacketDispatch().sendMessage("You have been stung by the nettles!");
			player.getImpactHandler().manualHit(player, 2, HitsplatType.POISON);
			return true;
		}
		if (plant.respawn != -1 && plant != PickingPlant.FLAX) {
			object.setActive(false);
		}
		GameWorld.Pulser.submit(new Pulse(1, player) {
			@Override
			public boolean pulse() {
				if (!player.getInventory().add(reward)) {
					player.getPacketDispatch().sendMessage("Not enough space in your inventory!");
					return true;
				}
				if (plant == PickingPlant.FLAX) {
					handleFlaxPick(player, object, plant);
					return true;
				}
				boolean banana = plant.name().startsWith("BANANA");
				GameObject full = null;
				if (plant == PickingPlant.BANANA_TREE_4) {
					full = object.transform(2073);
					ObjectBuilder.replace(object, full);
				}
				if (plant == PickingPlant.FUNGI_ON_LOG) {
					full = object.transform(3508);
					ObjectBuilder.replace(object, full);
				}
				if (plant != PickingPlant.FUNGI_ON_LOG) {
					ObjectBuilder.replace(plant == PickingPlant.BANANA_TREE_4 ? full : object, object.transform(banana ? plant.respawn : 0), banana ? 300 : plant.respawn);
				}
				if (!plant.name().startsWith("NETTLES")) {
					player.getPacketDispatch().sendMessage("You pick a " + reward.getName().toLowerCase() + ".");
				} else {
					player.getPacketDispatch().sendMessage("You pick a handful of nettles.");
				}
				if (banana && !player.getAchievementDiaryManager().hasCompletedTask(DiaryType.KARAMJA, 0, 0)) {
					int picked = player.getAttribute("b-picked", 0);
					picked++;
					player.setAttribute("b-picked", picked);
					player.getAchievementDiaryManager().updateTask(player, DiaryType.KARAMJA, 0, 0, picked == 5);
				}
				return true;
			}
		});
		return true;
	}

	/**
	 * Method used to handle the flax picking.
	 * @param player the player.
	 * @param object the object.
	 * @param plant the plank.
	 */
	private void handleFlaxPick(final Player player, final GameObject object, final PickingPlant plant) {
		int charge = object.getCharge();
		player.getAudioManager().send(2581);
		player.getPacketDispatch().sendMessage("You pick some flax.");
		player.getStatisticsManager().getFLAX_PICKED().incrementAmount();
		if (charge > 1000 + RandomFunction.random(2, 8)) {
			object.setActive(false);
			object.setCharge(1000);
			ObjectBuilder.replace(object, object.transform(0), plant.respawn);
			return;
		}
		object.setCharge(charge + 1);
	}

	/**
	 * Represents a plant to be picked.
	 * @author Emperor
	 */
	private static enum PickingPlant {
		POTATO(312, 1942, 30), WHEAT_0(313, 1947, 30), WHEAT_1(5583, 1947, 30), WHEAT_2(5584, 1947, 30), WHEAT_3(5585, 1947, 30), WHEAT_4(15506, 1947, 30), WHEAT_5(15507, 1947, 30), WHEAT_6(15508, 1947, 30), WHEAT_7(22300, 1947, 30), WHEAT_8(22473, 1947, 30), WHEAT_9(22474, 1947, 30), WHEAT_10(22475, 1947, 30), WHEAT_11(22476, 1947, 30), CABBAGE_0(1161, 1965, 30), CABBAGE_1(11494, 1967, 30), CABBAGE_2(22301, 1965, 30), NETTLES_0(1181, 4241, 30), NETTLES_1(5253, 4241, 30), NETTLES_2(5254, 4241, 30), NETTLES_3(5255, 4241, 30), NETTLES_4(5256, 4241, 30), NETTLES_5(5257, 4241, 30), NETTLES_6(5258, 4241, 30), PINEAPPLE_PLANT_0(1408, 2114, 30), PINEAPPLE_PLANT_1(1409, 2114, 30), PINEAPPLE_PLANT_2(1410, 2114, 30), PINEAPPLE_PLANT_3(1411, 2114, 30), PINEAPPLE_PLANT_4(1412, 2114, 30), PINEAPPLE_PLANT_5(1413, 2114, 30), PINEAPPLE_PLANT_6(4827, 2114, 30), BANANA_TREE_0(2073, 1963, 2074), BANANA_TREE_1(2074, 1963, 2075), BANANA_TREE_2(2075, 1963, 2076), BANANA_TREE_3(2076, 1963, 2077), BANANA_TREE_4(2077, 1963, 2078), BANANA_TREE_5(12606, 1963, 2079), BANANA_TREE_6(12607, 1963, 2080), FLAX(2646, 1779, 30), ONION_0(3366, 1957, 30), ONION_1(5538, 1957, 30), FUNGI_ON_LOG(3509, 2970, -1), GOLDEN_PEAR_BUSH(3513, 2974, 30), GLOWING_FUNGUS_0(4932, 4075, 30), GLOWING_FUNGUS_1(4933, 4075, 30), RARE_FLOWERS(5006, 2460, 30), BLACK_MUSHROOMS(6311, 4620, 30), KELP(12478, 7516, 30), RED_BANANA_TREE(12609, 7572, 30), BUSH(12615, 7573, 30), RED_FLOWERS(15846, 8938, 30), BLUE_FLOWERS(15872, 8936, 30), HARDY_GOUTWEED(18855, 3261, 30), HERBS_0(21668, 199, 30), HERBS_1(21669, 201, 30), HERBS_2(21670, 203, 30), HERBS_3(21671, 205, 30), FEVER_GRASS(29113, 12574, 30), LAVENDER(29114, 12572, 30), TANSYMUM(29115, 12576, 30), PRIMWEED(29116, 12588, 30), STINKBLOOM(29117, 12590, 30), TROLLWEISS_FLOWERS(37328, 4086, 30), HOLLOW_LOG(37830, 10968, 30);

		/**
		 * The object id.
		 */
		final int objectId;

		/**
		 * The reward item id.
		 */
		final int reward;

		/**
		 * The respawn duration.
		 */
		final int respawn;

		/**
		 * Constructs a new {@code FieldPickingPlugin {@code Object}.
		 * @param objectId the object id.
		 * @param reward the reward.
		 * @param respawn the resapwn.
		 */
		private PickingPlant(int objectId, int reward, int respawn) {
			this.objectId = objectId;
			this.reward = reward;
			this.respawn = respawn;
		}

		/**
		 * Gets the picking plant by the id.
		 * @param objectId the id.
		 * @return the picking plant.
		 */
		static PickingPlant forId(int objectId) {
			for (PickingPlant plant : values())
				if (plant.objectId == objectId)
					return plant;
			return null;
		}
	}
}
