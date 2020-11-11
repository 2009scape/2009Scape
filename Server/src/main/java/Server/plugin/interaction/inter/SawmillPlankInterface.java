package plugin.interaction.inter;

import core.game.component.Component;
import core.game.component.ComponentDefinition;
import core.game.component.ComponentPlugin;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.RunScript;
import core.game.node.entity.player.link.diary.DiaryType;
import core.game.node.item.Item;
import core.plugin.InitializablePlugin;
import core.plugin.Plugin;

/**
 * Represents the component handler for the interface 403.
 * @author 'Vexia
 * @date Oct 8, 2013
 */
@InitializablePlugin
public class SawmillPlankInterface extends ComponentPlugin {

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ComponentDefinition.put(403, this);
		return this;
	}

	@Override
	public boolean handle(final Player player, Component component, int opcode, int button, int slot, int itemId) {
		Plank plank = null;
		if (button > 101 && button < 108) {
			plank = Plank.WOOD;
		} else if (button > 108 && button < 114) {
			plank = Plank.OAK;
		} else if (button > 114 && button < 120) {
			plank = Plank.TEAK;
		} else if (button > 120 && button < 126) {
			plank = Plank.MAHOGANY;
		}
		int amount = -1;
		int fullIndex = plank == Plank.WOOD ? 107 : plank == Plank.OAK ? 113 : plank == Plank.TEAK ? 119 : 125;
		// 107, 105, 104, 103, 102
		int difference = -1;
		if (plank != Plank.WOOD) {
			difference = fullIndex - button;
		} else {
			difference = fullIndex - button - (button != 107 ? 1 : 0);
		}
		switch (difference) {
		case 0:
			amount = 1;
			break;
		case 1:
			amount = 5;
			break;
		case 2:
			amount = 10;
			break;
		case 3:
			amount = 69;
			break;
		case 4:
			amount = player.getInventory().getAmount(plank.getLog());
			break;
		}
		if (amount == 69) {
			final Plank plankk = plank;
			player.setAttribute("runscript", new RunScript() {
				@Override
				public boolean handle() {
					create(player, plankk, (int) super.getValue());
					return true;
				}
			});
			player.getDialogueInterpreter().sendInput(false, "Enter the amount:");
			return true;
		}
		if (plank != null) {
			create(player, plank, amount);
		}
		return true;
	}

	/**
	 * Method used to create a plank from a log.
	 * @param player the player.
	 * @param plank the plank.
	 * @param amount the amount.
	 */
	public final void create(final Player player, final Plank plank, int amount) {
		player.getInterfaceManager().close();
		if (amount > player.getInventory().getAmount(plank.getLog())) {
			amount = player.getInventory().getAmount(plank.getLog());
		}
		if (!player.getInventory().containsItem(new Item(plank.getLog().getId()))) {
			player.getDialogueInterpreter().sendDialogues(4250, null, "You'll need to bring me some more logs.");
			return;
		}
		if (!player.getInventory().contains(995, plank.getPrice() * amount)) {
			player.getDialogueInterpreter().sendDialogues(player, null, "Sorry, I don't have enough coins to pay for that.");
			return;
		}
		if (player.getInventory().remove(new Item(995, plank.getPrice() * amount))) {
			// Make a plank at the sawmill
			if (plank == Plank.WOOD) {
				player.getAchievementDiaryManager().finishTask(player, DiaryType.VARROCK, 0, 3);
			}
			// Buy twenty mahogany planks from the Sawmill Operator in one<br><br>transaction
			if (plank == Plank.MAHOGANY && amount >= 20) {
				player.getAchievementDiaryManager().finishTask(player, DiaryType.VARROCK, 1, 15);
			}
			Item remove = plank.getLog();
			remove.setAmount(amount);
			if (player.getInventory().remove(remove)) {
				Item planks = plank.getPlank();
				planks.setAmount(amount);
				player.getInventory().add(planks);
			}
		}
	}

	/**
	 * Represents an enum of planks to be boughten.
	 * @author 'Vexia
	 * @date Oct 8, 2013
	 */
	public enum Plank {
		WOOD(new Item(1511), new Item(960), 100), OAK(new Item(1521), new Item(8778), 250), TEAK(new Item(6333), new Item(8780), 500), MAHOGANY(new Item(6332), new Item(8782), 1500);

		/**
		 * Constructs a new {@code SawmillPlankInterface} {@code Object}.
		 * @param log the log.
		 * @param plank the plank.
		 * @param price the price.
		 */
		Plank(Item log, Item plank, int price) {
			this.log = log;
			this.plank = plank;
			this.price = price;
		}

		/**
		 * Represents the item needed.
		 */
		private final Item log;

		/**
		 * Represents the item given.
		 */
		private final Item plank;

		/**
		 * Represents the price of the plank.
		 */
		private final int price;

		/**
		 * @return the log.
		 */
		public Item getLog() {
			return log;
		}

		/**
		 * @return the plank.
		 */
		public Item getPlank() {
			return plank;
		}

		/**
		 * @return the price.
		 */
		public int getPrice() {
			return price;
		}
	}
}
