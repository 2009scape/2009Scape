package plugin.skill.farming;

import core.cache.def.impl.ItemDefinition;
import core.cache.def.impl.ObjectDefinition;
import core.game.component.Component;
import core.game.component.ComponentDefinition;
import core.game.component.ComponentPlugin;
import core.game.node.entity.player.link.diary.DiaryType;
import core.game.world.map.Location;
import core.game.world.map.RegionManager;
import plugin.skill.Skills;
import plugin.skill.farming.patch.Allotments;
import plugin.skill.farming.patch.PatchProtection;
import plugin.skill.farming.pot.Saplings;
import plugin.skill.farming.tool.PatchTool;
import plugin.skill.farming.wrapper.PatchWrapper;
import core.game.interaction.NodeUsageEvent;
import core.game.interaction.OptionHandler;
import core.game.interaction.UseWithHandler;
import core.game.node.Node;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.RunScript;
import core.game.node.item.Item;
import core.game.node.object.GameObject;
import core.plugin.InitializablePlugin;
import core.plugin.Plugin;
import core.plugin.PluginManager;
import core.tools.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the option plugin used to handle farming interactions.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class FarmingPlugin extends OptionHandler {

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ObjectDefinition.setOptionHandler("inspect", this);
		ObjectDefinition.setOptionHandler("guide", this);
		ObjectDefinition.setOptionHandler("harvest", this);
		ObjectDefinition.setOptionHandler("check-health", this);
		addPickingObject(FarmingPatch.FLOWER, this);
		addPickingObject(FarmingPatch.HERB, this);
		addPickingObject(FarmingPatch.BUSHES, this);
		addPickingObject(FarmingPatch.FRUIT_TREE, this);
		addPickingObject(FarmingPatch.EVIL_TURNIP, this);
		addPickingObject(FarmingPatch.CACTUS, this);
		addPickingObject(FarmingPatch.BELLADONNA, this);
		addPickingObject(FarmingPatch.CALQUAT, this);
		PluginManager.definePlugin(new OptionHandler() {

			@Override
			public Plugin<Object> newInstance(Object arg) throws Throwable {
				ItemDefinition.forId(6032).getHandlers().put("option:empty", this);
				ItemDefinition.forId(6034).getHandlers().put("option:empty", this);
				ItemDefinition.forId(6036).getHandlers().put("option:empty", this);
				return this;
			}

			@Override
			public boolean handle(Player player, Node node, String option) {
				if (node.getId() == 6036) {
					player.getInventory().replace(new Item(229), ((Item) node).getSlot());
				} else {
					player.getInventory().replace(new Item(1925), ((Item) node).getSlot());
				}
				return true;
			}

			@Override
			public boolean isWalk() {
				return false;
			}

		});
		new FarmingEquipmentStore().newInstance(arg);
		new CompostPlugin().newInstance(arg);
		new FarmToolPlugin().newInstance(arg);
		new PlantSeedPlugin().newInstance(arg);
		new ScarecrowPlugin().newInstance(arg);
		new PotSaplingPlugin().newInstance(arg);
		return this;
	}

	@Override
	public boolean handle(Player player, Node node, String option) {
		final GameObject object = (GameObject) node;
		final PatchWrapper wrapper = player.getFarmingManager().getPatchWrapper(object.getWrapper().getId());
		if (wrapper == null) {
			return false;
		}
		switch (option) {
		case "inspect":
			wrapper.getInteractor().inspect();
			break;
		case "guide":
			wrapper.getInteractor().openGuide();
			break;
		case "harvest":
			wrapper.getInteractor().harvest();
			break;
		case "pick":
		case "pick-from":
		case "pick-spine":
		case "pick-apple":
		case "pick-banana":
		case "pick-orange":
		case "pick-curry":
		case "pick-pineapple":
		case "pick-papaya":
		case "pick-coconut":
		case "pick-leaf":
		case "pick-fruit":
			wrapper.getInteractor().pick();
			break;
		case "check-health":
			wrapper.getInteractor().checkHealth();
			break;
		}
		return true;
	}

	/**
	 * Method used to add a picking object.
	 * @param patch the patch.
	 * @param handler the handler.
	 */
	public static void addPickingObject(final FarmingPatch patch, final OptionHandler handler) {
		String[] fruits = new String[] { "apple", "banana", "orange", "curry", "pineapple", "papaya", "coconut", "leaf", "fruit" };
		for (int ids : patch.getWrapperIds()) {
			int[] children = handler.getValidChildren(ids);
			for (int id : children) {
				ObjectDefinition.forId(id).getHandlers().put("option:pick", handler);
				ObjectDefinition.forId(id).getHandlers().put("option:pick-from", handler);
				ObjectDefinition.forId(id).getHandlers().put("option:pick-spine", handler);
				ObjectDefinition.forId(id).getHandlers().put("option:pick-fruit", handler);
				if (patch == FarmingPatch.FRUIT_TREE) {
					for (String s : fruits) {
						ObjectDefinition.forId(id).getHandlers().put("option:pick-" + s, handler);
					}
				}
			}
		}
	}

	/**
	 * Represents the planting of a seed plugin.
	 * @author 'Vexia
	 * @version 1.0
	 */
	public static final class PlantSeedPlugin extends UseWithHandler {

		/**
		 * Constructs a new {@code PlantSeedPlugin} {@code Object}.
		 */
		public PlantSeedPlugin() {
			super(getSeeds());
		}

		@Override
		public Plugin<Object> newInstance(Object arg) throws Throwable {
			for (FarmingPatch patch : FarmingPatch.values()) {
				for (int i : patch.getWrapperIds()) {
					int[] childs = getValidChildren(i);
					for (int child : childs) {
						addHandler(child, OBJECT_TYPE, this);
					}
				}
			}
			return this;
		}

		@Override
		public boolean handle(NodeUsageEvent event) {
			final Player player = event.getPlayer();
			final PatchWrapper wrapper = player.getFarmingManager().getPatchWrapper(((GameObject) event.getUsedWith()).getWrapper().getId());
			if (wrapper != null && player != null && event.getUsedItem() != null) {// Splinter
				wrapper.getInteractor().plant(event.getUsedItem());
			}
			return true;
		}

		/**
		 * Gets the seeds.
		 * @return the seeds.
		 */
		private static int[] getSeeds() {
			List<Integer> ids = new ArrayList<>();
			for (FarmingPatch patch : FarmingPatch.values()) {
				for (FarmingNode node : patch.getNodes()) {
					ids.add(node.getSeed().getId());
				}
			}
			int[] array = new int[ids.size()];
			for (int i = 0; i < ids.size(); i++) {
				array[i] = ids.get(i);
			}
			return array;
		}
	}

	/**
	 * Represents the plugin used to handle a farm tool interaction with a
	 * patch.
	 * @author 'Vexia
	 * @version 1.0
	 */
	public static final class FarmToolPlugin extends UseWithHandler {

		/**
		 * Constructs a new {@code FarmToolPlugin} {@code Object}.
		 */
		public FarmToolPlugin() {
			super(getTools());
		}

		@Override
		public Plugin<Object> newInstance(Object arg) throws Throwable {
			for (FarmingPatch patch : FarmingPatch.values()) {
				for (int i : patch.getWrapperIds()) {
					int[] childs = getValidChildren(i);
					for (int child : childs) {
						addHandler(child, OBJECT_TYPE, this);
					}
				}
			}
			return this;
		}

		@Override
		public boolean handle(NodeUsageEvent event) {
			final Player player = event.getPlayer();
			final PatchWrapper wrapper = player.getFarmingManager().getPatchWrapper(((GameObject) event.getUsedWith()).getWrapper().getId());
			wrapper.getInteractor().handleToolInteraction(event.getUsedItem(), "force");
			return true;
		}

		/**
		 * Gets the tools.
		 * @return the tools.
		 */
		private static int[] getTools() {
			List<Integer> ids = new ArrayList<>();
			ids.add(6032);// compost
			ids.add(6034);// super compost
			for (PatchTool tool : PatchTool.values()) {
				for (Item i : tool.getTools()) {
					ids.add(i.getId());
				}
			}
			int[] tools = new int[ids.size()];
			for (int i = 0; i < tools.length; i++) {
				tools[i] = ids.get(i);
			}
			return tools;
		}
	}

	/**
	 * Represents the plugin used to interact with compost.
	 * @author 'Vexia
	 * @version 1.0
	 */
	public static final class CompostPlugin extends UseWithHandler {

		/**
		 * Represents the compost object wrapper ids.
		 */
		private static final int[] WRAPPER_IDS = new int[] { 7836, 7837, 7838, 7839 };

		/**
		 * Constructs a new {@code CompostPlugin} {@code Object}.
		 */
		public CompostPlugin() {
			super(FarmingConstant.getFarmingProducts());
		}

		@Override
		public Plugin<Object> newInstance(Object arg) throws Throwable {
			for (int i : WRAPPER_IDS) {
				int[] childs = getValidChildren(i);
				for (int child : childs) {
					addHandler(child, OBJECT_TYPE, this);
				}
			}
			new CompostOptionPlugin().newInstance(arg);
			new CompostBucketPlugin().newInstance(arg);
			return this;
		}

		@Override
		public boolean handle(NodeUsageEvent event) {
			final Player player = event.getPlayer();
			player.getFarmingManager().getCompostManager().fill(player, event.getUsedItem(), (GameObject) event.getUsedWith(), "bin", 1);
			return true;
		}

		/**
		 * Represents the compost bucket plugin use with handler.
		 * @author 'Vexia
		 * @version 1.0
		 */
		public static final class CompostBucketPlugin extends UseWithHandler {

			/**
			 * Represents the ids to use.
			 */
			private static final int[] IDS = new int[] { 7814, 7815, 7816, 7817, 7824, 7825, 7826, 7827 };

			/**
			 * Constructs a new {@code CompostBucketPlugin} {@code Object}.
			 */
			public CompostBucketPlugin() {
				super(1925);
			}

			@Override
			public Plugin<Object> newInstance(Object arg) throws Throwable {
				for (int i : IDS) {
					addHandler(i, OBJECT_TYPE, this);
				}
				return this;
			}

			@Override
			public boolean handle(NodeUsageEvent event) {
				final Player player = event.getPlayer();
				player.getFarmingManager().getCompostManager().fill(player, event.getUsedItem(), (GameObject) event.getUsedWith(), "bucket", 1);
				return true;
			}

		}

		/**
		 * Represents the compost option plugin.
		 * @author 'Vexia
		 * @version 1.0
		 */
		public static final class CompostOptionPlugin extends OptionHandler {

			/**
			 * Represents the ids to use.
			 */
			private static final int[] IDS = new int[] { 7810, 7812, 7813, 7820, 7822, 7823, 7829, 7830, 7831, 7833, 7834, 7835 };

			/**
			 * Represents the options to use.
			 */
			private static final String[] OPTIONS = new String[] { "close", "open", "take-tomato" };

			@Override
			public Plugin<Object> newInstance(Object arg) throws Throwable {
				for (int i : IDS) {
					for (String option : OPTIONS) {
						ObjectDefinition.forId(i).getHandlers().put("option:" + option, this);
					}
				}
				return this;
			}

			@Override
			public boolean handle(Player player, Node node, String option) {
				switch (option) {
				case "close":
					player.getFarmingManager().getCompostManager().getBin((GameObject) node).close(player);
					break;
				case "open":
					player.getFarmingManager().getCompostManager().getBin((GameObject) node).open(player);
					break;
				case "take-tomato":
					player.getFarmingManager().getCompostManager().getBin((GameObject) node).takeTomato(player);
					break;
				}
				return true;
			}

		}
	}

	/**
	 * Represents the component plugin used for the farming store.
	 * @author afaroutdude
	 */
	public final class FarmingEquipmentStore extends ComponentPlugin {

		@Override
		public Plugin<Object> newInstance(Object arg) throws Throwable {
			ComponentDefinition.put(125, this);
			ComponentDefinition.put(126, this);
			return this;
		}

		@Override
		public boolean handle(final Player player, Component component, int opcode, int button, final int slot, int itemId) {
			// Opcodes: 155 (store 1), 196 (store 5), 124 (store all), 199 (store X)

			switch (component.getId()) {
			case 125: // Component, so player is trying to withdraw
				switch (opcode) {
					case 155:
						return player.getFarmingManager().getEquipment().remove(player, button - 33, 1);
					case 196:
						return player.getFarmingManager().getEquipment().remove(player, button - 33, 5);
					case 124:
						return player.getFarmingManager().getEquipment().remove(player, button - 33, 28);
					case 199:
						player.setAttribute("runscript", new RunScript() {
							@Override
							public boolean handle() {
								player.getFarmingManager().getEquipment().remove(player, button - 33, (int) getValue());
								return true;
							}
						});
						player.getDialogueInterpreter().sendInput(false, "Enter amount:");
						return true;
				}
			case 126: // Tab, so player is trying to store
				switch (opcode) {
					case 155:
						return player.getFarmingManager().getEquipment().store(player, button - 18, 1);
					case 196:
						return player.getFarmingManager().getEquipment().store(player, button - 18, 5);
					case 124:
						return player.getFarmingManager().getEquipment().store(player, button - 18, 28);
					case 199:
						player.setAttribute("runscript", new RunScript() {
							@Override
							public boolean handle() {
								player.getFarmingManager().getEquipment().store(player, button - 18, (int) getValue());
								return true;
							}
						});
						player.getDialogueInterpreter().sendInput(false, "Enter amount:");
						return true;
				}
			}
			return false;
		}
	}

	/**
	 * Represents the plugin used to make a scarecrow.
	 * @author 'Vexia
	 * @version 1.0
	 */
	public static final class ScarecrowPlugin extends UseWithHandler {

		/**
		 * Constructs a new {@code ScarecrowPlugin} {@code Object}.
		 */
		public ScarecrowPlugin() {
			super(6059);
		}

		@Override
		public Plugin<Object> newInstance(Object arg) throws Throwable {
			for (int flower : FarmingPatch.FLOWER.getWrapperIds()) {
				int[] children = getValidChildren(flower);
				for (int id : children) {
					addHandler(id, OBJECT_TYPE, this);
				}
			}
			new ScarecrowBuildHandler().newInstance(arg);
			return this;
		}

		@Override
		public boolean handle(NodeUsageEvent event) {
			final Player player = event.getPlayer();
			final PatchWrapper wrapper = player.getFarmingManager().getPatchWrapper(((GameObject) event.getUsedWith()).getWrapper().getId());
			if (wrapper.getInteractor().addScarecrow()) {
				// Achievement diary
				if (event.getUsedWith().getLocation().equals(PatchProtection.FALADOR.getFlowerLocation())
						&& !player.getAchievementDiaryManager().getDiary(DiaryType.FALADOR).isComplete(1,7)) {
					// Check for nearby corn
					Location[] allotmentLocations = PatchProtection.FALADOR.getAllotmentLocations();
					boolean sweetcorn = false;
					for (Location allotmentLocation : allotmentLocations) {
						GameObject allotment = RegionManager.getObject(allotmentLocation);
						if (allotment == null) { continue; }
						PatchWrapper allotmentWrapper = player.getFarmingManager().getPatchWrapper(allotment.getWrapper().getId());
						if (allotmentWrapper == null) { continue; }
						if (wrapper.getNode() == Allotments.SWEETCORN.getFarmingNode()) {
							sweetcorn = true;
							break;
						}
					}
					if (sweetcorn) {
						player.getAchievementDiaryManager().getDiary(DiaryType.FALADOR).updateTask(player, 1, 7, true);
					}
				}
			}
			return true;
		}

		/**
		 * Represents the class used to build the scarecrow.
		 * @author 'Vexia
		 * @version 1.0
		 */
		public static final class ScarecrowBuildHandler extends UseWithHandler {

			/**
			 * Represents the scarecrow item.
			 */
			private static final Item SCARECROW = new Item(6059);

			/**
			 * Represents the hay sack item.
			 */
			private static final Item HAY_SACK = new Item(6057);

			/**
			 * Represents the bronze spear item.
			 */
			private static final Item BRONZE_SPEAR = new Item(1237);

			/**
			 * Represents the watermelon item.
			 */
			private static final Item WATERMELON = new Item(5982);

			/**
			 * Constructs a new {@code ScarecrowBuildHandler} {@code Object}.
			 */
			public ScarecrowBuildHandler() {
				super(HAY_SACK.getId(), 6058);
			}

			@Override
			public Plugin<Object> newInstance(Object arg) throws Throwable {
				addHandler(WATERMELON.getId(), ITEM_TYPE, this);
				addHandler(BRONZE_SPEAR.getId(), ITEM_TYPE, this);
				return this;
			}

			@Override
			public boolean handle(NodeUsageEvent event) {
				final Player player = event.getPlayer();
				final Item first = event.getUsedItem();
				final Item second = (Item) event.getUsedWith();
				if (player.getSkills().getLevel(Skills.FARMING) < 23) {
					player.getPacketDispatch().sendMessage("You need a Farming level of at least 23 to do this.");
					return true;
				}
				if (first.getId() == HAY_SACK.getId() && second.getId() == BRONZE_SPEAR.getId()) {
					if (player.getInventory().remove(first, second)) {
						player.getInventory().add(new Item(6058));
						player.getPacketDispatch().sendMessage("You stick the bronze spear through the sack.");
						return true;
					}
				} else if (first.getId() == 6058 && second.getId() == WATERMELON.getId()) {
					if (player.getInventory().remove(first, second)) {
						player.getInventory().add(SCARECROW);
						player.getSkills().addExperience(Skills.FARMING, 25, true);
						player.getPacketDispatch().sendMessage("You stick a watermelon on top of the hay sack.");
						return true;
					}
				}
				return false;
			}
		}

	}

	/**
	 * Represents the plugin used to make a sapling.
	 * @author 'Vexia
	 * @version 1.0
	 */
	public static final class PotSaplingPlugin extends UseWithHandler {

		/**
		 * Constructs a new {@code PotSaplingPlugin} {@code Object}.
		 */
		public PotSaplingPlugin() {
			super(5354);
		}

		@Override
		public Plugin<Object> newInstance(Object arg) throws Throwable {
			for (Saplings sap : Saplings.values()) {
				addHandler(sap.getSeed().getId(), ITEM_TYPE, this);
			}
			new PotWateringPlugin().newInstance(arg);
			return this;
		}

		@Override
		public boolean handle(NodeUsageEvent event) {
			final Saplings sapling = Saplings.forSeed((Item) event.getUsedWith());
			final Player player = event.getPlayer();
			if (player.getSkills().getLevel(Skills.FARMING) < sapling.getLevel()) {
				player.getPacketDispatch().sendMessage("You need a Farming level of at least " + sapling.getLevel() + " to do that.");
				return true;
			}
			if (player.getInventory().remove(sapling.getSeed(), event.getUsedItem())) {
				player.getInventory().add(sapling.getSeedling());
				player.getPacketDispatch().sendMessage("You sow " + (StringUtils.isPlusN(sapling.getSeed().getName()) ? "an" : "a") + " " + sapling.getSeed().getName().toLowerCase() + " in the plantpot.");
				player.getPacketDispatch().sendMessage("It needs watering before it will grow.");
			}
			return true;
		}

		/**
		 * Represents the plugin used to water a plant pot.
		 * @author 'Vexia
		 * @version 1.0
		 */
		public final static class PotWateringPlugin extends UseWithHandler {

			/**
			 * Constructs a new {@code PotWateringPlugin} {@code Object}.
			 */
			public PotWateringPlugin() {
				super(getWateringCans());
			}

			@Override
			public Plugin<Object> newInstance(Object arg) throws Throwable {
				for (Saplings sap : Saplings.values()) {
					addHandler(sap.getSeedling().getId(), ITEM_TYPE, this);
				}
				return this;
			}

			@Override
			public boolean handle(NodeUsageEvent event) {
				final Player player = event.getPlayer();
				final Item can = event.getUsedItem().getName().contains("can") ? event.getUsedItem() : (Item) event.getUsedWith();
				final Item seedling = event.getUsedItem().getName().contains("seedling") ? event.getUsedItem() : (Item) event.getUsedWith();
				final Saplings sapling = Saplings.forSeedling(seedling);
				if (can.getId() == 5331) {
					return false;
				}
				if (seedling.getCharge() > 1000) {
					player.getPacketDispatch().sendMessage("The seedling has already been watered.");
					return true;
				}
				player.getFarmingManager().getSeedlingManager().addSeedling(seedling);
				Item newCan = getNextCan(can);
				if (newCan != null) {
					player.getInventory().replace(newCan, can.getSlot());
				}
				player.getPacketDispatch().sendMessage("You water the " + sapling.getSeed().getName().toLowerCase() + ".");
				return true;
			}

			/**
			 * Gets the watering cans.
			 * @return the cans.
			 */
			private static final int[] getWateringCans() {
				int[] cans = new int[PatchTool.WATERING_CAN.getTools().length];
				for (int i = 0; i < PatchTool.WATERING_CAN.getTools().length; i++) {
					cans[i] = PatchTool.WATERING_CAN.getTools()[i].getId();
				}
				return cans;
			}

			/**
			 * Gets the next can item.
			 * @return the next can.
			 */
			private Item getNextCan(Item tool) {
				Item can = null;
				for (int i = 0; i < PatchTool.WATERING_CAN.getTools().length; i++) {
					if (PatchTool.WATERING_CAN.getTools()[i].getId() == tool.getId()) {
						return PatchTool.WATERING_CAN.getTools()[(i + 1)];
					}
				}
				return can;
			}
		}
	}
}
