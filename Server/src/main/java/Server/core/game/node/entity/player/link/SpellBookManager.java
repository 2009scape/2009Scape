package core.game.node.entity.player.link;

import core.game.component.Component;
import plugin.skill.magic.MagicSpell;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.info.login.SavingModule;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents a managing class of a players spell book.
 * @author Vexia
 */
public final class SpellBookManager implements SavingModule {

	/**
	 * Represents the current interface if of the spellbook.
	 */
	private int spellBook = SpellBook.MODERN.getInterfaceId();

	/**
	 * Constructs a new {@code SpellBookManager} {@code Object}.
	 */
	public SpellBookManager() {
		/**
		 * empty.
		 */
	}

	@Override
	public void save(ByteBuffer buffer) {
		buffer.putInt(spellBook);
	}

	@Override
	public void parse(ByteBuffer buffer) {
		if (buffer.hasRemaining()) {
			setSpellBook(SpellBook.forInterface(buffer.getInt()));
		}
	}

	/**
	 * Sets the spell book.
	 * @param book
	 */
	public void setSpellBook(SpellBook book) {
		this.spellBook = book.getInterfaceId();
	}

	/**
	 * Updates the
	 * @param player
	 */
	public void update(Player player) {
		player.getInterfaceManager().openTab(new Component(SpellBook.forInterface(this.spellBook).getInterfaceId()));
	}

	/**
	 * Gets the spell book.
	 * @return the spellBook
	 */
	public int getSpellBook() {
		return spellBook;
	}

	/**
	 * Represents a characters spellbook.
	 * @author 'Vexia
	 * @author Emperor
	 */
	public enum SpellBook {

		/**
		 * The modern magic spell book.
		 */
		MODERN(192),

		/**
		 * The ancient magicks spell book.
		 */
		ANCIENT(193),

		/**
		 * The lunar magic spell book.
		 */
		LUNAR(430);

		/**
		 * The interface id of this spell book.
		 */
		private final int interfaceId;

		/**
		 * The spells mapping.
		 */
		private final Map<Integer, MagicSpell> spells = new HashMap<>();

		/**
		 * Creates the spell book.
		 * @param interfaceId The spellbook interface id.
		 */
		SpellBook(int interfaceId) {
			this.interfaceId = interfaceId;
		}

		/**
		 * @return The interfaceId.
		 */
		public int getInterfaceId() {
			return interfaceId;
		}

		/**
		 * Method used to get the book.
		 * @param id the id.
		 */
		public static SpellBook forInterface(final int id) {
			for (SpellBook book : SpellBook.values()) {
				if (book.interfaceId == id) {
					return book;
				}
			}
			return null;
		}

		/**
		 * Registers a new spell.
		 * @param buttonId The button id.
		 * @param spell The spell.
		 */
		public void register(int buttonId, MagicSpell spell) {
			spell.setSpellId(buttonId);
			spells.put(buttonId, spell);
		}

		/**
		 * Gets a spell from the spellbook.
		 * @param buttonId The button id.
		 * @return The spell.
		 */
		public MagicSpell getSpell(int buttonId) {
			return spells.get(buttonId);
		}
	}

}