package plugin.skill.prayer;

import core.cache.def.impl.ObjectDefinition;
import core.game.component.Component;
import core.game.node.entity.player.link.prayer.PrayerType;
import plugin.skill.SkillRestoration;
import plugin.skill.Skills;
import core.game.interaction.OptionHandler;
import core.game.node.Node;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.SpellBookManager.SpellBook;
import core.game.node.entity.player.link.diary.DiaryType;
import core.game.node.entity.player.link.quest.Quest;
import core.game.world.map.Location;
import core.game.world.update.flag.context.Animation;
import core.plugin.InitializablePlugin;
import core.plugin.Plugin;

/**
 * Handles the praying at an alter.
 * @author Vexia
 */
@InitializablePlugin
public class PrayerAltarPlugin extends OptionHandler {

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ObjectDefinition.setOptionHandler("pray-at", this);
		ObjectDefinition.setOptionHandler("pray", this);
		ObjectDefinition.forId(61).getHandlers().put("option:check", this);
		return this;
	}

	@Override
	public boolean handle(Player player, Node node, String option) {
		if (option.equalsIgnoreCase("check")) {
			final Quest quest = player.getQuestRepository().getQuest("Merlin's Crystal");
			if (quest.getStage(player) == 70) {
				player.getDialogueInterpreter().sendDialogue("You find a small inscription at the bottom of the altar. It reads:", "'Snarthon Candtrick Termanto'.");
				quest.setStage(player, 80);
				return true;
			}
			player.getPacketDispatch().sendMessage("An altar of the evil god Zamorak.");
			return true;
		}
		Altar altar = Altar.forId(node.getId());
		if (altar != null) {
			altar.pray(player);
			visualize(player);
			return true;
		}
		if (player.getSkills().getPrayerPoints() == player.getSkills().getStaticLevel(Skills.PRAYER)) {
			player.getPacketDispatch().sendMessage("You already have full prayer points.");
			return true;
		}
		visualize(player);
		player.getSkills().rechargePrayerPoints();
		player.getPacketDispatch().sendMessage("You recharge your Prayer points.");
		if (node.getId() == 2640) {
			player.getSkills().setLevel(Skills.PRAYER, player.getSkills().getStaticLevel(Skills.PRAYER) + 2);
			player.getSkills().getRestoration()[Skills.PRAYER] = new SkillRestoration(Skills.PRAYER);
			player.getSkills().getRestoration()[Skills.PRAYER].restartHpSummPray(false);
		}
		if (node.getId() == 409
				&& player.getLocation().withinDistance(new Location(3209, 3495, 1))
				&& player.getPrayer().getActive().contains(PrayerType.SMITE)) {
			player.getAchievementDiaryManager().finishTask(player,DiaryType.VARROCK,2, 4);
		}
		if (node.getId() == 39842
				&& player.getLocation().withinDistance(new Location(2995, 3177, 0))) {
			player.getAchievementDiaryManager().finishTask(player,DiaryType.FALADOR,0, 13);
		}
		// Seers task can be completed with either of camelot altar or seers church altar
		if (node.getId() == 19145
				&& player.getLocation().withinDistance(new Location(2749, 3496, 1))) {
			player.getAchievementDiaryManager().finishTask(player,DiaryType.SEERS_VILLAGE,0, 10);
		}
		if (node.getId() == 409
				&& player.getLocation().withinDistance(new Location(2694, 3462, 0))) {
			player.getAchievementDiaryManager().finishTask(player,DiaryType.SEERS_VILLAGE,0, 10);
		}

		if (node.getLocation().equals(new Location(2571, 9499, 0))) {
			player.teleport(new Location(2583, 9576, 0));
			player.sendMessage("It's a trap!");
			return true;
		}
		return true;
	}

	/**
	 * Visualizes the prayer.
	 * @param player the player.
	 */
	public void visualize(Player player) {
		player.lock(3);
		player.getAudioManager().send(2674);
		player.animate(Animation.create(645));
	}

	/**
	 * An altar.
	 * @author Vexia
	 */
	public enum Altar {
		ANCIENT(6552, SpellBook.ANCIENT.getInterfaceId(), "You feel a strange wisdom fill your mind...", "You feel a strange drain upon your memory...") {
			@Override
			public void pray(Player player) {
				if (player.getSkills().getStaticLevel(Skills.MAGIC) < 50) {
					player.sendMessage("You need a Magic level of at least 50 in order to do this.");
					return;
				}
				drain(player);
				if (!isPrayerType(player)) {
					switchToBook(player);
					player.sendMessage(getMessages()[0]);
				} else {
					revert(player);
					player.sendMessage(getMessages()[1]);
				}
			}
		},
		LUNAR(17010, SpellBook.LUNAR.getInterfaceId(), "Lunar spells activated!", "Modern spells activated!") {
			@Override
			public void pray(Player player) {
				if (player.getSkills().getStaticLevel(Skills.MAGIC) < 65) {
					player.sendMessage("You need a Magic level of at least 65 in order to do this.");
					return;
				}
				if (!isPrayerType(player)) {
					switchToBook(player);
					player.sendMessage(getMessages()[0]);
				} else {
					revert(player);
					player.sendMessage(getMessages()[1]);
				}
			}
		};

		/**
		 * The id.
		 */
		private int id;

		/**
		 * The book.
		 */
		private int book;

		/**
		 * The messages.
		 */
		private String[] messages;

		/**
		 * Constructs a new {@Code Altar} {@Code Object}
		 * @param id the id.
		 * @param book the book.
		 * @param messages the messages.
		 */
		private Altar(int id, int book, String... messages) {
			this.id = id;
			this.book = book;
			this.messages = messages;
		}

		/**
		 * Prays at the altar.
		 * @param player the player.
		 */
		public void pray(Player player) {

		}

		/**
		 * Reverts the book.
		 * @param player the player.
		 */
		public void revert(Player player) {
			player.getSpellBookManager().setSpellBook(SpellBook.MODERN);
			player.getInterfaceManager().openTab(new Component(SpellBook.values()[SpellBook.MODERN.ordinal()].getInterfaceId()));
		}

		/**
		 * Drains the player.
		 * @param player the player.
		 */
		public void drain(Player player) {
			player.getSkills().decrementPrayerPoints(player.getSkills().getPrayerPoints());
		}

		/**
		 * Switches to the book.
		 * @param player the player.
		 */
		public void switchToBook(Player player) {
			player.getSpellBookManager().setSpellBook(SpellBook.forInterface(book));
			player.getInterfaceManager().openTab(new Component(book));
		}

		/**
		 * Checks if it is the prayer type.
		 * @param player the player.
		 * @return true if so.
		 */
		public boolean isPrayerType(Player player) {
			return player.getSpellBookManager().getSpellBook() == book;
		}

		/**
		 * Gets an altar.
		 * @param id the id.
		 * @return the altar.
		 */
		public static Altar forId(int id) {
			for (Altar altar : values()) {
				if (id == altar.getId()) {
					return altar;
				}
			}
			return null;
		}

		/**
		 * Gets the id.
		 * @return the id
		 */
		public int getId() {
			return id;
		}

		/**
		 * Sets the baid.
		 * @param id the id to set.
		 */
		public void setId(int id) {
			this.id = id;
		}

		/**
		 * Gets the book.
		 * @return the book
		 */
		public int getBook() {
			return book;
		}

		/**
		 * Sets the babook.
		 * @param book the book to set.
		 */
		public void setBook(int book) {
			this.book = book;
		}

		/**
		 * Gets the messages.
		 * @return the messages
		 */
		public String[] getMessages() {
			return messages;
		}

		/**
		 * Sets the bamessages.
		 * @param messages the messages to set.
		 */
		public void setMessages(String[] messages) {
			this.messages = messages;
		}

	}

}
