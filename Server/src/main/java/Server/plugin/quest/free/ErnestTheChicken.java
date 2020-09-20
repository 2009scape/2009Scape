package plugin.quest.free;

import core.game.node.entity.npc.AbstractNPC;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.quest.Quest;
import core.game.node.entity.player.link.quest.QuestReward;
import core.game.node.entity.player.link.quest.QuestRewardComponentItem;
import core.game.node.item.Item;
import core.game.world.map.Location;
import core.plugin.InitializablePlugin;
import core.plugin.PluginManager;

/**
 * Represents the ernest the chicken quest.
 * @author 'Vexia
 */
@InitializablePlugin
public final class ErnestTheChicken extends Quest {

	/**
	 * The name of this quest.
	 */
	public static String NAME = "Ernest the Chicken";

	/**
	 * Represents the oil can item.
	 */
	private static final Item OIL_CAN = new Item(277);

	/**
	 * Represents the pressure gauge item.
	 */
	private static final Item PRESSURE_GAUGE = new Item(271);

	/**
	 * Represents the rubber tube item.
	 */
	private static final Item RUBBER_TUBE = new Item(276);

	/**
	 * Represents the coins item.
	 */
	private static final Item COINS = new Item(995, 300);

	/**
	 * Constructs a new {@code ErnestTheChicken} {@code Object}.
	 */
	public ErnestTheChicken() {
		super(
			NAME,
			19,
			18,
			4,
			32, 0, 1, 3
		);
	}

	@Override
	public Quest newInstance(Object object) {
		PluginManager.definePlugins(new ErnestNPC(), new ErnestChickenNPC());
		return this;
	}

	@Override
	public void drawJournal(Player player, int stage) {
		super.drawJournal(player, stage);
		switch (getStage(player)) {
			case 0:
				writeJournal(player,
					BLUE + "I can start this quest by speaking to " + RED + "Veronica " + BLUE + "who is",
					BLUE + "outside " + RED + "Draynor Manor",
					"",
					BLUE + "There aren't any requirements for this quest.");
				break;
			case 10:
				writeJournal(player,
					"<str>I have spoken to Veronica",
					"",
					BLUE + "I need to find " + RED + "Ernest",
					BLUE + "He went into " + RED + "Draynor Manor " + BLUE + "and hasn't returned");
				break;
			case 20:
				writeJournal(player,
					"<str>I have spoken to Veronica",
					"",
					"<str>I've spoken to Dr Oddenstein, and discovered Ernest is a",
					"<str>chicken.",
					"",
					BLUE + "I need to bring " + RED + "Dr Oddenstein " + BLUE + "parts for his machine:",
					(player.getInventory().containsItem(OIL_CAN) ? "<str>" : RED) + "1 Oil Can",
					(player.getInventory().containsItem(PRESSURE_GAUGE) ? "<str>" : RED) + "1 Pressure Gauge",
					(player.getInventory().containsItem(RUBBER_TUBE) ? "<str>" : RED) + "1 Rubber Tube"
				);
				break;
			case 100:
				writeJournal(player,
					"<str>I have spoken to Veronica",
					"",
					"<str>I have collected all the parts for the machine",
					"",
					"<str>Dr Oddenstein thanked me for helping fix his machine",
					"<str>We turned Ernest back to normal and he rewarded me",
					"<col=FF0000>QUEST COMPLETE!</col>");
				break;
		}
	}

	@Override
	public void finish(Player player) {
		player.unlock();
		player.getPacketDispatch().sendMessage("Ernest hands you 300 coins.");
		super.finish(player);
		player.getGameAttributes().removeAttribute("piranhas-killed");
		player.getGameAttributes().removeAttribute("pressure-gauge");
	}

	@Override
	public QuestRewardComponentItem getRewardComponentItem() {
		return new QuestRewardComponentItem(314, 230);
	}

	@Override
	public QuestReward[] getQuestRewards(Player player) {
		return new QuestReward[] {
			// Ernest's 300 coins
			new QuestReward(new Item(995, 300)),
		};
	}

	/**
	 * Represents the abstract npc class to handle ernest the chicken.
	 * @author 'Vexia
	 * @version 1.0
	 */
	public final static class ErnestChickenNPC extends AbstractNPC {

		/**
		 * The NPC ids of NPCs using this plugin.
		 */
		private static final int[] ID = { 288 };

		/**
		 * Constructs a new {@code ErnestChickenNPC} {@code Object}.
		 */
		public ErnestChickenNPC() {
			super(0, null, false);
		}

		/**
		 * Constructs a new {@code ErnestChickenNPC} {@code Object}.
		 * @param id The NPC id.
		 * @param location The location.
		 */
		private ErnestChickenNPC(int id, Location location) {
			super(id, location, false);
		}

		@Override
		public AbstractNPC construct(int id, Location location, Object... objects) {
			return new ErnestChickenNPC(id, location);
		}

		@Override
		public boolean isHidden(final Player player) {
			return player.getQuestRepository().getQuest(NAME).getStage(player) == 100 || player.getAttribute("ernest-hide", false);
		}

		@Override
		public int[] getIds() {
			return ID;
		}

	}
	
	/**
	 * Represents the abstract npc class to handle ernest the chicken.
	 * @author 'Vexia
	 * @version 1.0
	 */
	public final static class ErnestNPC extends AbstractNPC {

		/**
		 * The NPC ids of NPCs using this plugin.
		 */
		private static final int[] ID = { 287 };

		/**
		 * Constructs a new {@code ErnestChickenNPC} {@code Object}.
		 */
		public ErnestNPC() {
			super(0, null, false);
		}

		/**
		 * Constructs a new {@code ErnestChickenNPC} {@code Object}.
		 * @param id The NPC id.
		 * @param location The location.
		 */
		private ErnestNPC(int id, Location location) {
			super(id, location, false);
		}

		@Override
		public AbstractNPC construct(int id, Location location, Object... objects) {
			return new ErnestNPC(id, location);
		}

		@Override
		public boolean isHidden(final Player player) {
			Player target = getAttribute("target", null);
			if (target != null && target.getQuestRepository().getQuest(NAME).getStage(player) == 100) {
				clear();
				return super.isHidden(player);
			}
			return target == null || player != target;
		}

		@Override
		public int[] getIds() {
			return ID;
		}
	}
}
