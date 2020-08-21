package plugin.skill.cooking;

import plugin.dialogue.DialoguePlugin;
import plugin.quest.tutorials.tutorialisland.TutorialSession;
import plugin.quest.tutorials.tutorialisland.TutorialStage;
import core.game.interaction.NodeUsageEvent;
import core.game.interaction.UseWithHandler;
import core.game.node.entity.player.Player;
import core.game.node.item.Item;
import core.plugin.InitializablePlugin;
import core.plugin.Plugin;

/**
 * Represents the plugin used to make dough.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class MakeDoughPlugin extends UseWithHandler {

	/**
	 * Represents the water sources.
	 */
	private static final Item[][] SOURCES = new Item[][] { { new Item(1929), new Item(1925) }, { new Item(1921), new Item(1923) }, { new Item(1937), new Item(1935) } };

	/**
	 * Constructs a new {@code MakeDoughPlugin} {@code Object}.
	 */
	public MakeDoughPlugin() {
		super(1933);
	}

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		for (int i = 0; i < SOURCES.length; i++) {
			addHandler(SOURCES[i][0].getId(), ITEM_TYPE, this);
		}
		new MakeDoughDialogue().init();
		return this;
	}

	@Override
	public boolean handle(NodeUsageEvent event) {
		final Player player = event.getPlayer();
		if (player.getAttributes().containsKey("tut-island")) {
			return handleTutorialIsland(player, event);
		}
		Item[] data = null;
		for (int i = 0; i < SOURCES.length; i++) {
			if (SOURCES[i][0].getId() == event.getUsedItem().getId() || SOURCES[i][0].getId() == event.getBaseItem().getId()) {
				data = SOURCES[i];
				break;
			}
		}
		player.getDialogueInterpreter().open(59 << 16 | 1, (Object) data);
		return false;
	}

	/**
	 * Method used to handle the tutorial isalnd making dough.
	 * @param player the player.
	 * @param event the event.
	 */
	private boolean handleTutorialIsland(final Player player, final NodeUsageEvent event) {
		if (player.getInventory().freeSlots() == 0) {
			player.getPacketDispatch().sendMessage("You don't have enough inventory space.");
			return true;
		}
		player.lock(3);
		player.getInventory().remove(event.getBaseItem(), new Item(1933, 1));
		player.getInventory().add(new Item(3727, 1), new Item(1931, 1), new Item(2307, 1));
		if (TutorialSession.getExtension(player).getStage() == 19) {
			TutorialStage.load(player, 20, false);
		}
		return true;
	}

	/**
	 * Represents the plugin used to make dough..
	 * @author 'Vexia
	 * @version 1.0
	 */
	public static final class MakeDoughDialogue extends DialoguePlugin {

		/**
		 * Represents the dough items to make.
		 */
		private static final Item[] DOUGHS = new Item[] { new Item(2307), new Item(1953), new Item(2283), new Item(1863) };

		/**
		 * Represents the pot of flour item.
		 */
		private static final Item FLOUR = new Item(1933);

		/**
		 * Represents the water source data.
		 */
		private Item[] waterData;

		/**
		 * Constructs a new {@code MakeDoughDialogue} {@code Object}.
		 */
		public MakeDoughDialogue() {
			/**
			 * empty.
			 */
		}

		/**
		 * Constructs a new {@code MakeDoughDialogue} {@code Object}.
		 * @param player the player.
		 */
		public MakeDoughDialogue(Player player) {
			super(player);
		}

		@Override
		public DialoguePlugin newInstance(Player player) {
			return new MakeDoughDialogue(player);
		}

		@Override
		public boolean open(Object... args) {
			waterData = (Item[]) args[0];
			interpreter.sendOptions("What do you wish to make?", "Bread dough.", "Pastry dough.", "Pizza dough.", "Pitta dough.");
			return true;
		}

		@Override
		public boolean handle(int interfaceId, int buttonId) {
			switch (stage) {
			case 0:
				makeDough(DOUGHS[buttonId - 1]);
				end();
				break;
			}
			return true;
		}

		/**
		 * Method used to make a dough item.
		 * @param dough the dough.
		 */
		private void makeDough(final Item dough) {
			if (player.getInventory().remove(waterData[0]) && player.getInventory().remove(FLOUR)) {
				player.getInventory().add(waterData[1]);
				player.getInventory().add(dough);
				player.getInventory().add(new Item(1931), player);
				player.getPacketDispatch().sendMessage("You mix the flower and water to make some " + dough.getName().toLowerCase() + ".");
			}
		}

		@Override
		public int[] getIds() {
			return new int[] { 59 << 16 | 1 };
		}

	}

}
