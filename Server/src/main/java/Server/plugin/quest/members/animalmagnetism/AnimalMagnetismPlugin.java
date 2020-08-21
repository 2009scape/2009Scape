package plugin.quest.members.animalmagnetism;

import java.util.HashMap;
import java.util.Map;

import core.cache.def.impl.ItemDefinition;
import core.cache.def.impl.NPCDefinition;
import core.cache.def.impl.ObjectDefinition;
import core.game.component.Component;
import core.game.component.ComponentDefinition;
import core.game.component.ComponentPlugin;
import plugin.skill.Skills;
import plugin.skill.gather.SkillingTool;
import core.game.interaction.NodeUsageEvent;
import core.game.interaction.OptionHandler;
import core.game.interaction.UseWithHandler;
import core.game.node.Node;
import core.game.node.entity.impl.Animator.Priority;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.TeleportManager.TeleportType;
import core.game.node.entity.player.link.quest.Quest;
import core.game.node.item.Item;
import core.game.system.task.Pulse;
import core.game.world.GameWorld;
import core.game.world.map.Direction;
import core.game.world.map.Location;
import core.game.world.map.zone.MapZone;
import core.game.world.map.zone.ZoneBorders;
import core.game.world.map.zone.ZoneBuilder;
import core.game.world.update.flag.context.Animation;
import core.plugin.InitializablePlugin;
import core.plugin.Plugin;
import core.plugin.PluginManager;
import core.tools.RandomFunction;

/**
 * Handles the animal magnetism plugin.
 * @author Vexia
 */
@InitializablePlugin
public final class AnimalMagnetismPlugin extends OptionHandler {

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ItemDefinition.forId(688).getConfigurations().put("option:polish", this);
		ItemDefinition.forId(4251).getConfigurations().put("option:empty", this);
		ItemDefinition.forId(4251).getConfigurations().put("option:drop", this);
		ItemDefinition.forId(4252).getConfigurations().put("option:drop", this);
		NPCDefinition.forId(5198).getConfigurations().put("option:trade", this);
		ObjectDefinition.forId(5167).getConfigurations().put("option:push", this);
		AnimalMagnetism.RESEARCH_NOTES.getDefinition().getConfigurations().put("option:translate", this);
		ItemDefinition.forId(AnimalMagnetism.CRONE_AMULET.getId()).getConfigurations().put("option:wear", this);
		ItemDefinition.forId(AnimalMagnetism.CRONE_AMULET.getId()).getConfigurations().put("option:equip", this);
		return this;
	}

	@Override
	public boolean handle(Player player, Node node, String option) {
		switch (node.getId()) {
		case 5167:
			player.teleport(new Location(3577, 9927));
			break;
		case 5198:
		case 5199:
			if (player.getQuestRepository().getQuest(AnimalMagnetism.NAME).getStage(player) == 0) {
				player.getDialogueInterpreter().sendDialogues((NPC) node, null, "Hello there, I'm busy with my research. Come back in a", "bit, could you?");
				break;
			}
			node.asNpc().openShop(player);
			break;
		case 10500:
			player.getPacketDispatch().sendMessage("Perhaps you should wait a few hundred years or so?");
			break;
		case 4251:
		case 4252:
			if (option.equalsIgnoreCase("empty")) {
				handleEctophial(player, (Item) node);
			} else {
				player.getInventory().remove(node.asItem());
				player.sendMessage("The ectophial disappears as it hits the ground...");
			}
			break;
		case 10492:
			open(player);
			break;
		case 688:
			player.lock(1);
			if (player.getSkills().getLevel(Skills.CRAFTING) < 3) {
				player.getPacketDispatch().sendMessage("You need a Crafting level of at least 3 in order to do that.");
				return true;
			}
			player.getSkills().addExperience(Skills.CRAFTING, 5, true);
			player.getInventory().replace(AnimalMagnetism.POLISHED_BUTTONS, ((Item) node).getSlot());
			break;
		}
		return true;
	}
	
	/**
	 * Clears the note cache.
	 * @param player the player.
	 */
	private void clearCache(Player player) {
		player.removeAttribute("note-cache");
		player.removeAttribute("note-disabled");
	}
	
	/**
	 * Opens the interface.
	 * @param player the player.
	 */
	public void open(Player player) {
		clearCache(player);
		player.getInterfaceManager().open(new Component(480));
		player.getPacketDispatch().sendMessage("You fiddle with the notes.");
	}

	/**
	 * Handles the ectophial emptying.
	 * @param player the player.
	 * @param item the item.
	 */
	private void handleEctophial(final Player player, final Item item) {
		player.getInventory().replace(new Item(4252), item.getSlot());
		player.sendMessage("...and the world changes around you.", 4);
		player.getTeleporter().send(Location.create(3658, 3517, 0), TeleportType.ECTOPHIAL);
		player.sendMessage("You empty the ectoplasm onto the ground around your feet...");
		player.getPulseManager().run(new Pulse(9, player) {
			@Override
			public boolean pulse() {
				player.faceLocation(new Location(3659, 3519, 0));
				if (player.getInventory().containsItem(new Item(4252))) {
					player.animate(Animation.create(1652));
					player.getInventory().remove(new Item(4252));
					player.getInventory().add(item);
					player.sendMessage("Your ectophial vial automatically refills.");
				}
				return true;
			}
		});
	}

	/**
	 * Handles the hammering of a magnet.
	 * @author Vexia
	 */
	public static final class HammerMagnetPlugin extends UseWithHandler {

		/**
		 * The animation used when hammering a magnet.
		 */
		private static final Animation ANIMATION = new Animation(5365);

		/**
		 * Constructs a new {@code HammerMagnetPlugin} {@code Object}.
		 */
		public HammerMagnetPlugin() {
			super(2347);
		}

		@Override
		public Plugin<Object> newInstance(Object arg) throws Throwable {
			ZoneBuilder.configure(new MapZone("rimmington mine", true) {
				@Override
				public void configure() {
					register(new ZoneBorders(2970, 3230, 2984, 3249));
				}
			});
			addHandler(AnimalMagnetism.SELECTED_IRON.getId(), ITEM_TYPE, this);
			return this;
		}

		@Override
		public boolean handle(final NodeUsageEvent event) {
			final Player player = event.getPlayer();
			player.animate(ANIMATION);
			player.lock(ANIMATION.getDefinition().getDurationTicks());
			GameWorld.Pulser.submit(new Pulse(ANIMATION.getDefinition().getDurationTicks(), player) {
				@Override
				public boolean pulse() {
					if (!player.getZoneMonitor().isInZone("rimmington mine")) {
						player.getPacketDispatch().sendMessage("You aren't in the right area for this to work.");
					} else {
						if (player.getDirection() != Direction.NORTH) {
							player.getPacketDispatch().sendMessage("You think that facing North might work better.");
						} else {
							player.getInventory().replace(AnimalMagnetism.BAR_MAGNET, event.getUsedItem().getSlot());
							player.getPacketDispatch().sendMessage("You hammer the iron bar and create a magnet.");
						}
					}
					return true;
				}
			});
			return true;
		}

	}

	/**
	 * Handles the axe on a undead tree.
	 * @author Vexia
	 */
	public static final class UndeadTreePlugin extends UseWithHandler {

		/**
		 * The axe ids.
		 */
		private final int[] IDS = new int[] { 1355, 1357, 1359, 6739 };

		/**
		 * Constructs a new {@code UndeadTreePlugin} {@code Object}.
		 */
		public UndeadTreePlugin() {
			super(1355, 1357, 1359, 6739);
		}

		@Override
		public Plugin<Object> newInstance(Object arg) throws Throwable {
			PluginManager.definePlugin(new OptionHandler() {
				@Override
				public Plugin<Object> newInstance(Object arg) throws Throwable {
					NPCDefinition.forId(5208).getConfigurations().put("option:chop", this);
					return this;
				}

				@Override
				public boolean handle(Player player, Node node, String option) {
					final Quest quest = player.getQuestRepository().getQuest(AnimalMagnetism.NAME);
					if (quest.getStage(player) <= 28) {
						SkillingTool tool = SkillingTool.getHatchet(player);
						if (tool == null || tool.ordinal() < 4) {
							player.getPacketDispatch().sendMessage("You don't have the required axe in order to do that.");
							return true;
						}
						final Animation animation = getAnimation(tool.getId());
						player.animate(animation, 2);
						if (quest.getStage(player) == 28) {
							quest.setStage(player, 29);
						}
						player.sendMessage("The axe bounces off the undead wood." + (quest.getStage(player) == 28 || quest.getStage(player) == 29 ? " I should report this to Ava." : ""));
						return true;
					}
					if (player.getInventory().freeSlots() < 1) {
						player.sendMessage("Your inventory is full right now.");
						return true;
					}
					if (!player.getInventory().containsItem(AnimalMagnetism.BLESSED_AXE) && !player.getEquipment().containsItem(AnimalMagnetism.BLESSED_AXE)) {
						player.getPacketDispatch().sendMessage("You need a blessed axe in order to do that.");
						return true;
					}
					Animation animation = getAnimation(1355);
					player.lock(animation.getDefinition().getDurationTicks());
					if (RandomFunction.random(10) < 3) {
						player.sendMessage("You almost remove a suitable twig, but you don't quite manage it.");
					} else {
						player.getInventory().add(AnimalMagnetism.UNDEAD_TWIGS);
						player.sendMessage("You cut some undead twigs.");
					}
					player.animate(animation, 2);
					return true;
				}

			});
			addHandler(5208, NPC_TYPE, this);
			addHandler(152, NPC_TYPE, this);
			return this;
		}

		@Override
		public boolean handle(NodeUsageEvent event) {
			final Player player = event.getPlayer();
			final Animation animation = getAnimation(event.getUsedItem().getId());
			final Quest quest = player.getQuestRepository().getQuest(AnimalMagnetism.NAME);
			player.animate(animation, 2);
			if (quest.getStage(player) == 28) {
				quest.setStage(player, 29);
			}
			player.sendMessage("The axe bounces off the undead wood." + (quest.getStage(player) == 28 || quest.getStage(player) == 29 ? " I should report this to Ava." : ""));
			return true;
		}

		/**
		 * Gets the animation id.
		 * @param itemId the item id.
		 * @return {@code Animation} the animation.
		 */
		public Animation getAnimation(int itemId) {
			for (int i = 0; i < IDS.length; i++) {
				if (IDS[i] == itemId) {
					return new Animation(5366 + i, Priority.HIGH);
				}
			}
			return null;
		}
	}

	/**
	 * Handles the resarch note handler.
	 * @author Vexia
	 * @version 1.0
	 */
	public static final class ResearchNoteHandler extends ComponentPlugin {

		/**
		 * The button ids.
		 */
		private final int[][] BUTTONS = new int[][] { { 40, 39, 6 }, { 42, 41, 3 }, { 44, 43, 7 }, { 46, 45, 8}, { 48, 47, 4 }, { 50, 49, 9 }, { 52, 51, 10 }, { 54, 53, 11 }, { 56, 55, 5 } };

		@Override
		public Plugin<Object> newInstance(Object arg) throws Throwable {
			ComponentDefinition.forId(480).setPlugin(this);
			return this;
		}

		@Override
		public boolean handle(Player player, Component component, int opcode, int button, int slot, int itemId) {
			if (player.getAttribute("note-disabled", false)) {
				return true;
			}
			final Object[] data = getIndex(button);
			final boolean toggled = (boolean) data[1];
			final int[] configs = getConfigs((int) data[0]);
			final Quest quest = player.getQuestRepository().getQuest(AnimalMagnetism.NAME);
			player.getPacketDispatch().sendInterfaceConfig(480, configs[0], !toggled);
			player.getPacketDispatch().sendInterfaceConfig(480, (int) data[2], toggled);
			if (quest.getStage(player) == 33) {
				setNoteCache(player, (int) data[0], !toggled);
				if (isTranslated(player)) {
					if (player.getInventory().remove(AnimalMagnetism.RESEARCH_NOTES)) {
						player.setAttribute("note-disabled", true);
						player.getInventory().add(AnimalMagnetism.TRANSLATED_NOTES);
						player.getPacketDispatch().sendMessage("It suddenly all makes sense.");
					}
				}
			}
			return true;
		}

		/**
		 * Sets the note in the cache.
		 * @param player the player.
		 * @param index the index.
		 * @param toggled if toggled.
		 */
		private void setNoteCache(Player player, int index, boolean toggled) {
			Map<Integer, Boolean> cache = getNoteCache(player);
			cache.put(index, toggled);
			player.setAttribute("note-cache", cache);
		}

		/**
		 * Checks if the notes are translated.
		 * @param player the player.
		 * @return {@code True} if so.
		 */
		private boolean isTranslated(Player player) {
			Map<Integer, Boolean> cache = getNoteCache(player);
			int[] correct = new int[] { 0, 2, 3, 5, 6, 7 };
			int[] wrong = new int[] { 1, 4, 8 };
			for (int i : correct) {
				if (cache.get(i).booleanValue()) {
					return false;
				}
			}
			for (int i : wrong) {
				if (!cache.get(i).booleanValue()) {
					return false;
				}
			}
			return true;
		}

		/**
		 * Gets the note cache.
		 * @param player the player.
		 * @return the cache of toggled buttons.
		 */
		private Map<Integer, Boolean> getNoteCache(Player player) {
			Map<Integer, Boolean> cache = player.getAttribute("note-cache", null);
			if (cache == null) {
				cache = new HashMap<Integer, Boolean>();
				for (int i = 0; i < BUTTONS.length; i++) {
					cache.put(i, true);
				}
			}
			return cache;
		}


		/**
		 * Gets the hidden config ids.
		 * @param index the index.
		 * @return the configs.
		 */
		private int[] getConfigs(int index) {
			return new int[] { 21 + index, 0 };
		}

		/**
		 * Gets the index by the button id.
		 * @param buttonId the buttonId.
		 * @return the object data.
		 */
		private Object[] getIndex(int buttonId) {
			for (int i = 0; i < BUTTONS.length; i++) {
				for (int k = 0; k < BUTTONS[i].length - 1; k++) {
					if (buttonId == BUTTONS[i][k]) {
						return new Object[] { i, k == 0, BUTTONS[i][2] };
					}
				}
			}
			return new Object[] { 0, true };
		}
	}

	/**
	 * Handles the creating of a container.
	 * @author Vexia
	 */
	public static final class ContainerHandler extends UseWithHandler {

		/**
		 * Constructs a new {@code ContainerHandler} {@code Object}.
		 */
		public ContainerHandler() {
			super(10496, 1743);
		}

		@Override
		public Plugin<Object> newInstance(Object arg) throws Throwable {
			addHandler(AnimalMagnetism.PATTERN.getId(), ITEM_TYPE, this);
			return this;
		}

		@Override
		public boolean handle(NodeUsageEvent event) {
			final Player player = event.getPlayer();
			if (!player.getInventory().containsItem(AnimalMagnetism.HARD_LEATHER)) {
				player.sendMessage("You need hard leather as well as these 2 items.");
				return true;
			}
			if (!player.getInventory().containsItem(AnimalMagnetism.POLISHED_BUTTONS)) {
				player.sendMessage("You need polished buttons as well as these 2 items.");
				return true;
			}
			if (player.getInventory().remove(AnimalMagnetism.HARD_LEATHER, AnimalMagnetism.POLISHED_BUTTONS, AnimalMagnetism.PATTERN)) {
				player.getInventory().add(AnimalMagnetism.CONTAINER);
			}
			return true;
		}

	}

	@Override
	public boolean isWalk(Player player, Node node) {
		return !(node instanceof Item);
	}

	@Override
	public boolean isWalk() {
		return false;
	}
}
