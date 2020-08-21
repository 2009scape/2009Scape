package plugin.quest.tutorials.tutorialisland;

import core.plugin.InitializablePlugin;
import plugin.dialogue.DialoguePlugin;
import plugin.dialogue.book.Book;
import plugin.dialogue.book.BookLine;
import plugin.dialogue.book.Page;
import plugin.dialogue.book.PageSet;
import core.game.node.entity.player.Player;

/**
 * Handles the introductory after-tutorial book.
 * @author Splinter
 */
@InitializablePlugin
public final class TutorialBook extends Book {

	/**
	 * Represents the book id
	 */
	public static int ID = 387454;

	/**
	 * Represents the array of pages for this book.
	 */
	private static final PageSet[] PAGES = new PageSet[] { 
		new PageSet(
				new Page(
						new BookLine("THIS BOOK NEEDS TO HAVE ITS TEXT CHANGED Starting off", 55), new BookLine("", 56), new BookLine("    It is a good idea", 57), new BookLine("to raid the two strongholds", 58),
						new BookLine("in Barbarian Village in order", 59), new BookLine("to gain a ludicrous amount of", 60), new BookLine("coins to start with. Also", 61),
						new BookLine("Consider collecting skilling", 62), new BookLine("materials to sell in bulk", 63), new BookLine("on the Grand Exchange.", 64), 
						new BookLine("Raw meat, dropped from", 65)),
						new Page(
								new BookLine("cows, is useful in the", 66), new BookLine("Summoning skill and can", 67),
								new BookLine("be easily sold for coins.", 68), new BookLine(" ", 69), new BookLine("    Credits/Double XP", 70), new BookLine("Credits are our way", 71), 
								new BookLine("of thanking you for voting.", 72), new BookLine("You may spend credits", 73), new BookLine("in the online store at:", 74), 
								new BookLine("www.2009Scape-pk.com/shop or", 75), new BookLine("by using our in-game ::shop.", 76))),
								new PageSet(
										new Page(
												new BookLine("    Suggestions", 55), 
												new BookLine("Please suggest new features", 56), new BookLine("you'd like to see via", 57), new BookLine("the forums located at", 58), 
												new BookLine("www.2009Scape-pk.com.", 59), new BookLine("We wholeheartedly look over", 60), new BookLine("and consider all suggestions.", 61))),

	};

	/**
	 * Constructs a new {@code ShieldofArravBook} {@code Object}.
	 */
	public TutorialBook(final Player player) {
		super(player, "Early Guide Book", 1856, PAGES);
	}

	/**
	 * Constructs a new {@code ShieldofArravBook} {@code Object}.
	 */
	public TutorialBook() {
		/**
		 * empty.
		 */
	}

	@Override
	public void finish() {
	}

	@Override
	public void display(Page[] set) {
		player.lock();
		player.getInterfaceManager().open(getInterface());
		player.getPacketDispatch().sendString("Previous", getInterface().getId(), 77);
		player.getPacketDispatch().sendString("Next", getInterface().getId(), 78);
		for (int i = 55; i < 77; i++) {
			player.getPacketDispatch().sendString("", getInterface().getId(), i);
		}
		player.getPacketDispatch().sendString(getName(), getInterface().getId(), 6);
		for (Page page : set) {
			for (BookLine line : page.getLines()) {
				player.getPacketDispatch().sendString(line.getMessage(), getInterface().getId(), line.getChild());
			}
		}
		boolean lastPage = index == sets.length - 1;
		if (lastPage) {
			finish();
		}
		player.unlock();
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new TutorialBook(player);
	}

	@Override
	public int[] getIds() {
		return new int[] { ID };
	}
}
