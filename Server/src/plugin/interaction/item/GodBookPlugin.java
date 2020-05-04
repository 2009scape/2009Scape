package plugin.interaction.item;

import org.crandor.game.content.dialogue.DialogueAction;
import org.crandor.game.content.dialogue.DialogueInterpreter;
import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.global.GodBook;
import org.crandor.game.content.skill.Skills;
import org.crandor.game.interaction.NodeUsageEvent;
import org.crandor.game.interaction.OptionHandler;
import org.crandor.game.interaction.UseWithHandler;
import org.crandor.game.node.Node;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.item.GroundItem;
import org.crandor.game.node.item.Item;
import org.crandor.game.node.item.ItemPlugin;
import org.crandor.game.system.task.Pulse;
import org.crandor.game.world.GameWorld;
import org.crandor.game.world.update.flag.context.Animation;
import org.crandor.plugin.Plugin;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.plugin.PluginManager;

/**
 * Handles the god books.
 * @author Vexia
 */
@InitializablePlugin
public class GodBookPlugin extends OptionHandler {

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		for (GodBook book : GodBook.values()) {
			book.getDamagedBook().getDefinition().getConfigurations().put("option:check", this);
			book.getBook().getDefinition().getConfigurations().put("option:preach", this);
		}
		PluginManager.definePlugin(new PageHandler(), new GodBookDialogue(), new GodBookItem(), new SymbolBlessHandler());
		return this;
	}

	@Override
	public boolean handle(Player player, Node node, String option) {
		GodBook book = GodBook.forItem(node.asItem(), option.equalsIgnoreCase("check"));
		if (book != null) {
			switch (option) {
			case "check":
				String[] messages = new String[4];
				for (int i = 0; i < messages.length; i++) {
					messages[i] = book.hasPage(player, node.asItem(), i + 1) ? "The " + getNumberName(i + 1) + " page is in the book." : "The " + getNumberName(i + 1) + " page is missing.";
				}
				player.getDialogueInterpreter().sendDialogue(messages);
				return true;
			case "preach":
				player.getDialogueInterpreter().open("god-book", book);
				return true;
			}
		}
		return true;
	}

	/**
	 * Handles the blessing of a symbol with a god book.
	 * @author Vexia
	 */
	public class SymbolBlessHandler extends UseWithHandler {

		/**
		 * Constructs a new {@code SymbolBlessHandler} {@code Object}
		 */
		public SymbolBlessHandler() {
			super(1716);
		}

		@Override
		public Plugin<Object> newInstance(Object arg) throws Throwable {
			for (GodBook book : GodBook.values()) {
				addHandler(book.getBook().getId(), ITEM_TYPE, this);
			}
			return this;
		}

		@Override
		public boolean handle(NodeUsageEvent event) {
			Player player = event.getPlayer();
			GodBook book = GodBook.forItem(event.getUsedItem(), false);
			if (book == null) {
				return false;
			}
			final Item symbol = event.getUsedWith().asItem();
			if (player.getSkills().getLevel(Skills.PRAYER) < 50) {
				player.sendMessage("You need a Prayer level of at least 50 in order to do this.");
				return true;
			}
			if (player.getSkills().getPrayerPoints() < 4) {
				player.sendMessage("You need at least 4 prayer points in order to do this.");
				return true;
			}
			if (book == GodBook.BOOK_OF_BALANCE) {
				player.getDialogueInterpreter().sendOptions("Select an Option", "Unholy symbol", "Holy symbol");
				player.getDialogueInterpreter().addAction(new DialogueAction() {

					@Override
					public void handle(Player player, int buttonId) {
						bless(player, symbol, buttonId == 1 ? GodBook.UNHOLY_BOOK : GodBook.HOLY_BOOK);
					}

				});
				return true;
			}
			bless(player, symbol, book);
			return true;
		}

		/**
		 * Blesses a symbol.
		 * @param player the player.
		 * @param book the book.
		 */
		private void bless(Player player, Item symbol, GodBook book) {
			if (!player.getInventory().containsItem(symbol)) {
				return;
			}
			if (player.getInventory().get(symbol.getSlot()) == null) {
				return;
			}
			player.getInventory().replace(book.getBlessItem()[0], symbol.getSlot());
			player.getSkills().decrementPrayerPoints(4);
		}

	}

	/**
	 * A god book item.
	 * @author Vexia
	 */
	public class GodBookItem extends ItemPlugin {

		@Override
		public Plugin<Object> newInstance(Object arg) throws Throwable {
			for (GodBook book : GodBook.values()) {
				register(book.getDamagedBook().getId());
			}
			return this;
		}

		@Override
		public boolean canPickUp(Player player, GroundItem item, int type) {
			if (player.hasItem(item.asItem())) {
				player.sendMessage("You do not need more than one incomplete book.");
				return false;
			}
			return true;
		}

	}

	/**
	 * Handles the god book dialogue.
	 * @author Vexia
	 */
	public static class GodBookDialogue extends DialoguePlugin {

		/**
		 * The partnership messages.
		 */
		private static final String[][] PARTNERSHIPS = new String[][] { { "In the name of Saradomin,", "protector of us all,", " I now join you in the eyes of Saradomin." }, { "Light and dark,", "day and night,", "Balance arises from constrast.", "I unify thee in the name of Guthix." }, { "May your union not be harmonious,", "but may your conflicts make you stronger,", "in Zamorak's name,", "now two are one." } };

		/**
		 * The last rite messages.
		 */
		private static final String[][] LAST_RITES = new String[][] { { "Thy cause was false,", "thy skills did lack;", "See you in Lumbridge when you get back." }, { "Thy death was not in vain,", "For it brought some balance to the world.", "May guthix bring thee rest." }, { "The weak deserve to die,", "so the strong may flourish.", "This is the creed of Zamorak." } };

		/**
		 * The blessing messages.
		 */
		private static final String[][] BLESSINGS = new String[][] { { "Go in peace in the name of Saradomin;", "may his glory shine upon you like the sun." }, { "Mayest thou walk the path,", "and never fall,", "For Guthix walks beside thee on thy journey.", "May Guthix bring thee peace." }, { "May you reject all safe paths,", "and embrace all challenges.", "Zamorak bring you strength." } };

		/**
		 * The preaching messages.
		 */
		private static final String[][] PREACH = new String[][] { { "Protect yourself,", "protect your friends.", "Mine is the glory that never ends.", "This is Saradomin's wisdom." }, { "Thee trees,", "the earth,", "the sky,", "the waters:", "All play their part upon this land.", "May Guthix bring thee balance." }, { "Battles are not lost and won;", "They simply remove the weak from the equation.", "Zamorak give me strength!" } };

		private static final Animation[] ANIMATIONS = new Animation[] { new Animation(1335), new Animation(1337), new Animation(1336) };

		/**
		 * The god book.
		 */
		private GodBook book;

		/**
		 * Constructs a new {@code GodBookDialogue} {@code Object}
		 */
		public GodBookDialogue() {
			/**
			 * empty.
			 */
		}

		/**
		 * Constructs a new {@code GodBookDialogue} {@code Object}
		 * @param player the player.
		 */
		public GodBookDialogue(Player player) {
			super(player);
		}

		@Override
		public DialoguePlugin newInstance(Player player) {
			return new GodBookDialogue(player);
		}

		@Override
		public boolean open(Object... args) {
			book = (GodBook) args[0];
			options("Wedding Ceremony", "Last Rites", "Blessings", "Preach");
			return true;
		}

		@Override
		public boolean handle(int interfaceId, int buttonId) {
			switch (stage) {
			case 0:
				String[][] data = null;
				switch (buttonId) {
				case 1:
					data = PARTNERSHIPS;
					break;
				case 2:
					data = LAST_RITES;
					break;
				case 3:
					data = BLESSINGS;
					break;
				case 4:
					data = PREACH;
					break;
				}
				say(data);
				end();
				break;
			}
			return true;
		}

		/**
		 * Says the messages for the type.
		 * @param data the data.
		 */
		private void say(String[][] data) {
			if (player.inCombat()) {
				player.sendMessage("You can't do that while in combat.");
				return;
			}
			final String[] messages = data[book.ordinal()];
			final Animation animation = ANIMATIONS[book.ordinal()];
			player.animate(animation);
			player.lock();
			GameWorld.Pulser.submit(new Pulse(2, player) {
				int index = 0;

				@Override
				public boolean pulse() {
					player.animate(animation);
					player.sendChat(messages[index]);
					return index++ >= messages.length - 1;
				}

				@Override
				public void stop() {
					super.stop();
					player.unlock();
				}

			});
		}

		@Override
		public int[] getIds() {
			return new int[] { DialogueInterpreter.getDialogueKey("god-book") };
		}

	}

	/**
	 * The page handler.
	 * @author Vexia
	 */
	public class PageHandler extends UseWithHandler {

		/**
		 * Constructs a new {@code PageHandler} {@code Object}
		 */
		public PageHandler() {
			super(3839, 3841, 3843);
		}

		@Override
		public Plugin<Object> newInstance(Object arg) throws Throwable {
			for (GodBook book : GodBook.values()) {
				for (Item i : book.getPages()) {
					addHandler(i.getId(), ITEM_TYPE, this);
				}
			}
			return this;
		}

		@Override
		public boolean handle(NodeUsageEvent event) {
			GodBook book = GodBook.forItem(event.getUsedItem(), true);
			Player player = event.getPlayer();
			if (book != null && book.isPage(event.getUsedWith().asItem())) {
				book.insertPage(player, event.getUsedItem(), event.getUsedWith().asItem());
				return true;
			}
			return false;
		}

	}

	/**
	 * Gets the number name.
	 * @param the integer to check.
	 * @return the number name.
	 */
	private String getNumberName(int i) {
		return i == 1 ? "first" : i == 2 ? "second" : i == 3 ? "third" : "fourth";
	}

}
