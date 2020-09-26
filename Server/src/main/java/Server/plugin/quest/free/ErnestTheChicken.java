package plugin.quest.free;

import core.game.node.entity.npc.AbstractNPC;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.quest.Quest;
import core.game.node.entity.player.link.quest.QuestReward;
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
		int line;
		switch (getStage(player)) {
			case 0:
				writeJournal(player,
					"I can start this quest by speaking to <red>Veronica <blue>who is",
					"outside <red>Draynor Manor",
					"",
					"There aren't any requirements for this quest.");
				break;
			case 10:
				line = writeJournal(player, true,
					"I have spoken to Veronica",
					"");
				writeJournal(player, line,
					"I need to find <red>Ernest",
					"He went into <red>Draynor Manor <blue>and hasn't returned");
				break;
			case 20:
				line = writeJournal(player, true,
					"I have spoken to Veronica",
					"",
					"I've spoken to Dr Oddenstein, and discovered Ernest is a",
					"chicken.",
					"");
				writeJournal(player, line,
					"I need to bring <red>Dr Oddenstein <blue>parts for his machine:",
					(player.getInventory().containsItem(OIL_CAN) ? "<str>" : "<red>") + "1 Oil Can",
					(player.getInventory().containsItem(PRESSURE_GAUGE) ? "<str>" : "<red>") + "1 Pressure Gauge",
					(player.getInventory().containsItem(RUBBER_TUBE) ? "<str>" : "<red>") + "1 Rubber Tube"
				);
				break;
			case 100:
				line = writeJournal(player, true,
					"I have spoken to Veronica",
					"",
					"I have collected all the parts for the machine",
					"",
					"Dr Oddenstein thanked me for helping fix his machine",
					"We turned Ernest back to normal and he rewarded me");
				writeJournal(player, line,
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
	public Item getRewardComponentItem() {
		return new Item(314);
	}

	@Override
	public QuestReward[] getQuestRewards() {
		return new QuestReward[]{
			// Ernest's 300 coins
			new QuestReward(COINS),
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
