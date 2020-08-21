package plugin.quest.free;

import core.game.node.entity.npc.AbstractNPC;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.quest.Quest;
import core.game.node.item.GroundItemManager;
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
	 * Represents the oil can item.
	 */
	private static final Item OIL_CAN = new Item(277);

	/**
	 * Represents the pressure guage item.
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
	 * @param player the player.
	 */
	public ErnestTheChicken() {
		super("Ernest the Chicken", 19, 18, 4, 32, 0, 1, 3);
	}

	@Override
	public Quest newInstance(Object object) {
		PluginManager.definePlugins(new ErnestNPC(), new ErnestChickenNPC());
		return this;
	}

	@Override
	public void drawJournal(Player player, int stage) {
		super.drawJournal(player, stage);
		if (getStage(player) == 0) {
			player.getPacketDispatch().sendString(BLUE + "I can start this quest by speaking to " + RED+ "Veronica " + BLUE + "who is", 275, 4+ 7);
			player.getPacketDispatch().sendString(BLUE + "outside " + RED+ "Draynor Manor", 275, 5+ 7);
			player.getPacketDispatch().sendString(BLUE + "There aren't any requirements for this quest.", 275, 7+ 7);
		} else if (stage == 10) {
			line(player, "<str>I have spoken to Veronica", 4+ 7);
			line(player, BLUE + "I need to find " + RED + "Ernest", 6+ 7);
			line(player,  BLUE + "He went into " + RED+ "Draynor Manor " + BLUE + "and hasn't returned", 7+ 7);
		} else if (stage == 20) {
			line(player, "<str>I have spoken to Veronica", 4+ 7);
			line(player, "<str>I've spoken to Dr Oddenstein, and discovered Ernest is a", 6+ 7);
			line(player, "<str>chicken", 7+ 7);
			line(player, BLUE + "I need to bring " + RED+ "Dr Oddenstein " + BLUE + "parts for his machine", 9+ 7);
			line(player, player.getInventory().containsItem(OIL_CAN) ? "<str>1 Oil Can" : RED+ "1 Oil Can", 10);
			line(player,  player.getInventory().containsItem(PRESSURE_GAUGE) ? "<str>1 Pressure Gauge" : RED+ "1 Pressure Gauge", 11);
			line(player,  player.getInventory().containsItem(RUBBER_TUBE) ? "<str>1 Rubber Tube" : RED+ "1 Rubber Tube", 12);
		} else if (stage == 100) {
			line(player, "<str>I have spoken to Veronica", 4+ 7);
			line(player, "<str>I have collected all the parts for the machine", 6+ 7);
			line(player, "<str>Dr Oddenstein thanked me for helping fix his machine", 8+ 7);
			line(player, "<str>We turned Ernest back to normal and he rewarded me", 9+ 7);
			line(player, "<col=FF0000>QUEST COMPLETE!</col>", 10+ 7);
		}
	}

	@Override
	public void finish(Player player) {
		player.unlock();
		player.getPacketDispatch().sendMessage("Ernest hands you 300 coins.");
		super.finish(player);
		player.getPacketDispatch().sendString("4 Quest Points", 277, 8 + 2);
		player.getPacketDispatch().sendString("300 coins", 277, 9 + 2);
		player.getPacketDispatch().sendString("You have completed the Ernest The Chicken Quest!", 277, 2 + 2);
		player.getGameAttributes().removeAttribute("piranhas-killed");
		player.getGameAttributes().removeAttribute("pressure-gauge");
		player.getPacketDispatch().sendItemZoomOnInterface(314, 230, 277, 3 + 2);
		if (!player.getInventory().add(COINS)) {
			GroundItemManager.create(COINS, player.getLocation(), player);
		}
	}
	
	/**
	 * Represents the abstact npc class to handle ernest the chicken.
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
			return player.getQuestRepository().getQuest("Ernest the Chicken").getStage(player) == 100 || player.getAttribute("ernest-hide", false);
		}

		@Override
		public int[] getIds() {
			return ID;
		}

	}
	
	/**
	 * Represents the abstact npc class to handle ernest the chicken.
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
			if (target != null && target.getQuestRepository().getQuest("Ernest the Chicken").getStage(player) == 100) {
				clear();
				return super.isHidden(player);
			}
			return target == null ? true : player != target;
		}

		@Override
		public int[] getIds() {
			return ID;
		}
	}
}
