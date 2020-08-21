package plugin.quest.free.shieldofarrav;

import plugin.dialogue.DialoguePlugin;
import plugin.dialogue.book.Book;
import plugin.dialogue.book.BookLine;
import plugin.dialogue.book.Page;
import plugin.dialogue.book.PageSet;
import core.game.node.entity.player.Player;

/**
 * Represents the shield of arrav book.
 * @author 'Vexia
 * @author Empathy
 * @version 2.0
 */
public final class ShieldofArravBook extends Book {

	/**
	 * Represents the shield of arrav book id.
	 */
	public static int ID = 49610758;

	/**
	 * Represents the array of pages for this book.
	 */
	private static final PageSet[] PAGES = new PageSet[] { new PageSet(new Page(new BookLine("The Shield of Arrav", 55), new BookLine("by A.R Wright", 57), new BookLine("Arrav is probably the best", 61), new BookLine("known hero of the 4th", 62), new BookLine("age. Many legends are", 63), new BookLine("told of his heroics. One", 64), new BookLine("surviving artefact from", 65)), new Page(new BookLine("the 4th age is a fabulous", 66), new BookLine("shield.", 67), new BookLine("This shield is believed to", 69), new BookLine("have once belonged to", 70), new BookLine("Arrav and is now indeed", 71), new BookLine("known as the Shield of", 72), new BookLine("Arrav. For over 150", 73), new BookLine("years it was the prize", 74), new BookLine("piece in the royal", 75), new BookLine("museum of Varrock.", 76))), new PageSet(new Page(new BookLine("However, in the year 143", 55), new BookLine("of the fith age a gang of", 56), new BookLine("thieves called the Phoenix", 57), new BookLine("Gang broke into the", 58), new BookLine("museum and stole the", 59), new BookLine("shield in a daring raid. As", 60), new BookLine("a result, the current", 61), new BookLine("ruler, King Roald, put a", 62), new BookLine("1200 gold bounty (a", 63), new BookLine("massive sum of money in", 64), new BookLine("those days) on the return", 65)), new Page(new BookLine("of the shield, hoping that", 66), new BookLine("one of the culprits would", 67), new BookLine("betray his fellows out of", 68), new BookLine("greed.", 69))), new PageSet(new Page(new BookLine("This tactic did not work", 55), new BookLine("however, and the thieves", 56), new BookLine("who stole the shield have", 57), new BookLine("since gone on to become", 58), new BookLine("the most powerful crime", 59), new BookLine("gang in Varrock, despite", 60), new BookLine("making an enemy of the", 61), new BookLine("Royal Family many", 62), new BookLine("years ago.", 63)), new Page(new BookLine("The reward for the", 66), new BookLine("return of the shield still", 67), new BookLine("stands.", 68))) };

	/**
	 * Constructs a new {@code ShieldofArravBook} {@code Object}.
	 */
	public ShieldofArravBook(final Player player) {
		super(player, "The Shield of Arrav", ShieldofArrav.BOOK.getId(), PAGES);
	}

	/**
	 * Constructs a new {@code ShieldofArravBook} {@code Object}.
	 */
	public ShieldofArravBook() {
		/**
		 * empty.
		 */
	}

	@Override
	public void finish() {
		if (player.getQuestRepository().getQuest("Shield of Arrav").getStage(player) == 10) {
			player.getQuestRepository().getQuest("Shield of Arrav").setStage(player, 20);
		}
	}

	@Override
	public void display(Page[] set) {
		player.lock();
		player.getInterfaceManager().open(getInterface());
		for (int i = 55; i < 77; i++) {
			player.getPacketDispatch().sendString("", getInterface().getId(), i);
		}
		player.getPacketDispatch().sendString(getName(), getInterface().getId(), 6);
		player.getPacketDispatch().sendString("", getInterface().getId(), 77);
		player.getPacketDispatch().sendString("", getInterface().getId(), 78);
		for (Page page : set) {
			for (BookLine line : page.getLines()) {
				player.getPacketDispatch().sendString(line.getMessage(), getInterface().getId(), line.getChild());
			}
		}
		player.getPacketDispatch().sendInterfaceConfig(getInterface().getId(), 51, index < 1 ? true : false);
		boolean lastPage = index == sets.length - 1;
		player.getPacketDispatch().sendInterfaceConfig(getInterface().getId(), 53, lastPage ? true : false);
		if (lastPage) {
			finish();
		}
		player.unlock();
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new ShieldofArravBook(player);
	}

	@Override
	public int[] getIds() {
		return new int[] { ID };
	}
}
