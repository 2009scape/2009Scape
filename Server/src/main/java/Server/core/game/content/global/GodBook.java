package core.game.content.global;

import core.game.node.entity.player.Player;
import core.game.node.item.Item;

/**
 * A god book.
 * @author Vexia
 */
public enum GodBook {
	HOLY_BOOK("Holy Book of Saradomin", new Item(3840), new Item(3839), new Item[] { new Item(1718) }, new Item(3827), new Item(3828), new Item(3829), new Item(3830)), BOOK_OF_BALANCE("Guthix's Book of Balance", new Item(3844), new Item(3843), new Item[] { new Item(1718), new Item(1724) }, new Item(3835), new Item(3836), new Item(3837), new Item(3838)), UNHOLY_BOOK("Unholy Book of Zamorak", new Item(3842), new Item(3841), new Item[] { new Item(1724) }, new Item(3831), new Item(3832), new Item(3833), new Item(3834));

	/**
	 * The books name.
	 */
	private final String name;

	/**
	 * The god book.
	 */
	private final Item book;

	/**
	 * The damaged book.
	 */
	private final Item damagedBook;

	/**
	 * The items the book can bless.
	 */
	private final Item[] blessItems;

	/**
	 * The pages of items.
	 */
	private final Item[] pages;

	/**
	 * Constructs a new {@code GodBook} {@code Object}
	 * @param book the book.
	 * @param damagedBook the damged book.
	 * @param name the name of the book.
	 * @param pages the pages.
	 */
	private GodBook(String name, Item book, Item damagedBook, Item[] blessedItems, Item... pages) {
		this.book = book;
		this.damagedBook = damagedBook;
		this.name = name;
		this.blessItems = blessedItems;
		this.pages = pages;
	}

	/**
	 * Gets the god book for the item.
	 * @param item the item.
	 * @param damaged Flagged for the damaged book.
	 * @return {@code GodBook} the god book.
	 */
	public static GodBook forItem(Item item, boolean damaged) {
		for (GodBook book : values()) {
			if ((!damaged ? book.getBook().getId() : book.getDamagedBook().getId()) == item.getId()) {
				return book;
			}
		}
		return null;
	}

	/**
	 * Checks if the player has this god book.
	 * @param player the player.
	 * @param both if we are checking for the damaged/good.
	 * @return {@code True} if so.
	 */
	public boolean hasGodBook(Player player, boolean both) {
		return player.getInventory().containsItems(both ? new Item[] { book, damagedBook } : new Item[] { book });
	}

	/**
	 * Gets a good book based on the page id.
	 * @param page the page.
	 * @return the respective god book.
	 */
	public static GodBook forPage(Item page) {
		for (GodBook book : values()) {
			for (Item i : book.getPages()) {
				if (i.getId() == page.getId()) {
					return book;
				}
			}
		}
		return null;
	}

	/**
	 * Inserts a page into the book.
	 * @param player the player.
	 * @param book the book.
	 * @param page the page.
	 */
	public void insertPage(Player player, Item book, Item page) {
		if (hasPage(player, book, page)) {
			player.sendMessage("The book already has that page.");
			return;
		}
		if (player.getInventory().remove(new Item(page.getId(), 1))) {
			setPageHash(player, book, getPageIndex(page));
			player.sendMessage("You add the page to the book...");
			if (isComplete(player, book)) {
				player.getSavedData().getGlobalData().setGodPages(new boolean[4]);
				player.getSavedData().getGlobalData().setGodBook(-1);
				player.getInventory().replace(this.book, book.getSlot());
				player.getSavedData().getGlobalData().setGodBook(this);
				player.sendMessage("The book is now complete!");
				String message = this == UNHOLY_BOOK ? "unholy symbols" : this == HOLY_BOOK ? "holy symbols" : "unblessed holy symbols";
				player.sendMessage("You can now use it to bless " + (message) + "!");
			}
		}
	}

	/**
	 * Checks if this item is a page of the book.
	 * @param asItem the item.
	 * @return {@code True} if so.
	 */
	public boolean isPage(Item asItem) {
		for (Item item : pages) {
			if (item.getId() == asItem.getId()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Checks if a book is complete.
	 * @param book the book.
	 * @return {@code True} if so.
	 */
	public boolean isComplete(Player player, Item book) {
		for (int i = 0; i < 4; i++) {
			if (!hasPage(player, book, i + 1)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Checks if there is a page in a book.
	 * @param book the book.
	 * @param page the page.
	 * @return {@code True} if so.
	 */
	public boolean hasPage(Player player, Item book, Item page) {
		return hasPage(player, book, getPageIndex(page));
	}

	/**
	 * Sets a page hash.
	 * @param book the book.
	 * @param pageId the page Id.
	 */
	public void setPageHash(Player player, Item book, int pageId) {
		// int hash = getHash(book);
		// hash |= hash | (1 << pageId);
		// book.setCharge(1000 + hash);
		player.getSavedData().getGlobalData().getGodPages()[pageId - 1] = true;
	}

	/**
	 * Checks if the book has a page.
	 * @param book the book.
	 * @param pageId the id of the page.
	 * @return {@code True} if so.
	 */
	public boolean hasPage(Player player, Item book, int pageId) {
		// return (getHash(book) & (1 << pageId)) != 0;
		return player.getSavedData().getGlobalData().getGodPages()[pageId - 1];
	}

	/**
	 * Gets the hash.
	 * @param book the book.
	 * @return the hash.
	 */
	public int getHash(Item book) {
		return book.getCharge() - 1000;
	}

	/**
	 * Gets the page index.
	 * @param page the page.
	 * @return the index.
	 */
	public int getPageIndex(Item page) {
		for (int i = 0; i < pages.length; i++) {
			if (pages[i].getId() == page.getId()) {
				return i + 1;
			}
		}
		return -1;
	}

	/**
	 * Gets the name of the god book.
	 * @return the name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the book.
	 * @return the book
	 */
	public Item getBook() {
		return book;
	}

	/**
	 * Gets the damagedBook.
	 * @return the damagedBook
	 */
	public Item getDamagedBook() {
		return damagedBook;
	}

	/**
	 * Gets the pages.
	 * @return the pages
	 */
	public Item[] getPages() {
		return pages;
	}

	/**
	 * Gets the blessItem.
	 * @return the blessItem
	 */
	public Item[] getBlessItem() {
		return blessItems;
	}
}
