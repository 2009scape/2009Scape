package plugin.skill.smithing;

import core.cache.def.impl.ObjectDefinition;
import plugin.quest.tutorials.tutorialisland.TutorialSession;
import plugin.quest.tutorials.tutorialisland.TutorialStage;
import plugin.skill.Skills;
import plugin.skill.smithing.smelting.Bar;
import core.game.interaction.NodeUsageEvent;
import core.game.interaction.OptionHandler;
import core.game.interaction.UseWithHandler;
import core.game.node.Node;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.diary.DiaryType;
import core.game.node.item.Item;
import core.game.node.object.GameObject;
import core.game.system.task.Pulse;
import core.game.world.GameWorld;
import core.game.world.map.Location;
import core.game.world.update.flag.context.Animation;
import core.plugin.InitializablePlugin;
import core.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the plugin used for the furnace.
 * @author Vexia
 */
@InitializablePlugin
public final class FurnaceOptionPlugin extends OptionHandler {

	/**
	 * Represents the animation to use.
	 */
	private static final Animation ANIMATION = new Animation(833);

	/**
	 * Represents the items used for the tutorial island (ores).
	 */
	private static final Item[] ITEMS = new Item[] { new Item(438, 1), new Item(436, 1) };

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ObjectDefinition.setOptionHandler("smelt", this);
		ObjectDefinition.setOptionHandler("smelt-ore", this);
		ObjectDefinition.forId(3044).getConfigurations().put("option:use", this);
		ObjectDefinition.forId(21303).getConfigurations().put("option:use", this);
		new SmeltUseWithHandler().newInstance(arg);
		return this;
	}

	@Override
	public boolean handle(final Player player, Node node, String option) {
		switch (((GameObject) node).getId()) {
		case 3044:
			handleTutorialIsland(player);
			return true;
		}
		if (node.getId() == 26814 && !player.getAchievementDiaryManager().getDiary(DiaryType.VARROCK).isComplete(0)) {
			player.sendMessage("You need to have completed the easy tasks in the Varrock Diary in order to use this.");
			return true;
		}
		show(player);
		return true;
	}

	/**
	 * Shows the items.
	 * @param player the player.
	 */
	private static void show(final Player player) {
		player.getInterfaceManager().openChatbox(311);
		player.getPacketDispatch().sendItemZoomOnInterface(2349, 150, 311, 4);
		if (player.getQuestRepository().isComplete("The Knight's Sword")) {
			player.getPacketDispatch().sendString("<br><br><br><br><col=000000>Blurite", 311, 20);
		}
		player.getPacketDispatch().sendItemZoomOnInterface(Bar.BLURITE.getProduct().getId(), 150, 311, 5);
		player.getPacketDispatch().sendItemZoomOnInterface(Bar.IRON.getProduct().getId(), 150, 311, 6);
		player.getPacketDispatch().sendItemZoomOnInterface(2355, 150, 311, 7);
		player.getPacketDispatch().sendItemZoomOnInterface(2353, 150, 311, 8);
		player.getPacketDispatch().sendItemZoomOnInterface(2357, 150, 311, 9);
		player.getPacketDispatch().sendItemZoomOnInterface(2359, 150, 311, 10);
		player.getPacketDispatch().sendItemZoomOnInterface(2361, 150, 311, 11);
		player.getPacketDispatch().sendItemZoomOnInterface(2363, 150, 311, 12);
	}

	/**
	 * Method used to handle the tutorial island interaction.
	 * @param player the player.
	 */
	private final void handleTutorialIsland(final Player player) {
		if (player.getInventory().containItems(438, 436)) {
			player.animate(ANIMATION);
			GameWorld.Pulser.submit(new Pulse(2, player) {
				@Override
				public boolean pulse() {
					player.getInventory().remove(ITEMS);
					player.getInventory().add(Bar.BRONZE.getProduct());
					player.getSkills().addExperience(Skills.SMITHING, Bar.BRONZE.getExperience());
					if (TutorialSession.getExtension(player).getStage() == 39) {
						TutorialStage.load(player, 40, false);
					}
					return true;
				}

			});
		}
	}

	/**
	 * Represents the plugin used to handle the ore on the furance.
	 * @author 'Vexia
	 * @version 1.0
	 */
	public final static class SmeltUseWithHandler extends UseWithHandler {

		/**
		 * Represents the ids.
		 */
		private static final int[] IDS = new int[] { 4304, 6189, 11010, 11666, 12100, 12809, 14921, 18497, 26814, 30021, 30510, 36956, 37651 };

		/**
		 * Constructs a new {@code SmeltUseWithHandler} {@code Object}.
		 */
		public SmeltUseWithHandler() {
			super(getIds());
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
			if (event.getUsedWith().getId() == 26814 && !event.getPlayer().getAchievementDiaryManager().getDiary(DiaryType.VARROCK).isComplete(0)) {
				event.getPlayer().sendMessage("You need to have completed the easy tasks in the Varrock Diary in order to use this.");
				return true;
			}
			show(event.getPlayer());
			return true;
		}

		/**
		 * Gets the ore ids.
		 * @return the ids.
		 */
		public static final int[] getIds() {
			List<Integer> ids = new ArrayList<>();
			for (Bar bar : Bar.values()) {
				for (Item i : bar.getOres()) {
					ids.add(i.getId());
				}
			}
			int[] array = new int[ids.size()];
			for (int i = 0; i < ids.size(); i++) {
				array[i] = ids.get(i);
			}
			return array;
		}
	}

	@Override
	public Location getDestination(Node node, Node n) {
		if (node instanceof Player && ((Player) node).getZoneMonitor().isInZone("Donator Zone")) {
			return n.getLocation().transform(1, 2, 0);
		}
		return null;
	}
}
