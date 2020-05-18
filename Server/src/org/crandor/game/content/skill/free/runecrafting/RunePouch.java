package org.crandor.game.content.skill.free.runecrafting;

import org.crandor.game.content.global.action.DropItemHandler;
import org.crandor.game.content.skill.Skills;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.item.Item;

/**
 * Represents a rune pouch.
 * @author Vexia
 */
public enum RunePouch {
	SMALL(new Item(5509), 1, 3, 3, 0), MEDIUM(new Item(5510), 25, 6, 9, 45), LARGE(new Item(5512), 50, 9, 18, 29), GIANT(new Item(5514), 75, 12, 30, 10);

	/**
	 * The pure essence item.
	 */
	private static final Item PURE_ESSENCE = new Item(7936);

	/**
	 * The rune essence item.
	 */
	private static final Item NORMAL_ESSENCE = new Item(1436);

	/**
	 * The pure essence base.
	 */
	private static final int PURE_BASE = 6000;

	/**
	 * The normal essence base.
	 */
	private static final int NORMAL_BASE = 2000;

	/**
	 * The pouch item.
	 */
	private final Item pouch;

	/**
	 * The decayed pouch.
	 */
	private final Item decayedPouch;

	/**
	 * The level required to use it.
	 */
	private final int level;

	/**
	 * The capacity.
	 */
	private final int capacity;

	/**
	 * The cumulative capacity.
	 */
	private final int cumulativeCapacity;

	/**
	 * The uses until decay.
	 */
	private final int uses;

	/**
	 * Constructs a new {@code Pouch} {@code Object}.
	 * @param pouch the pouch.
	 * @param level the level.
	 * @param capacity the capacity.
	 * @param cumulativeCapacity the cumulative capacity.
	 * @param uses the uses.
	 */
	RunePouch(Item pouch, int level, int capacity, int cumulativeCapacity, int uses) {
		this.pouch = pouch;
		this.decayedPouch = new Item(pouch.getId() + 1);
		this.level = level;
		this.capacity = capacity;
		this.cumulativeCapacity = cumulativeCapacity;
		this.uses = uses;
	}

	/**
	 * Method used to handle the reward.
	 * @param player the player.
	 * @param pouch the item.
	 * @param option the option.
	 */
	public void action(final Player player, final Item pouch, final String option) {
		if (pouch.getCharge() == 1000 && getDecay(player) > 0) {
			resetDecay(player);
		}
		switch (option) {
		case "fill":
			fill(player, pouch);
			break;
		case "empty":
			empty(player, pouch);
			break;
		case "check":
			check(player, pouch);
			break;
		case "drop":
			drop(player, pouch);
			break;
		}
	}

	/**
	 * Method used to fill a pouch.
	 * @param player the player.
	 * @param pouch the item.
	 */
	public final void fill(final Player player, Item pouch) {
		if (isFull(pouch, player)) {
			return;
		}
		if (!hasEssence(player)) {
			player.sendMessage("You do not have any essence to fill your pouch with.");
			return;
		}
		if (player.getSkills().getStaticLevel(Skills.RUNECRAFTING) < level) {
			player.sendMessage("You need level " + level + " Runecrafting to fill a " + name().toLowerCase() + " pouch.");
			return;
		}
		final Item essence = getEssence(player);
		if (!isValidEssence(pouch, essence, player)) {
			player.sendMessage("You can only put " + getPouchEssenceName(pouch) + " in this pouch.");
			return;
		}
		int amount = getAddAmount(pouch, essence, player);
		addEssence(player, pouch, essence, amount);
		if (isFull(pouch, player)) {
			return;
		}
	}

	/**
	 * Emptys a rune pouch.
	 * @param player the player.
	 * @param pouch the item.
	 */
	public final void empty(final Player player, Item pouch) {
		if (isEmpty(pouch)) {
			player.sendMessage("There are no essences in your pouch.");
			return;
		}
		int essenceAmount = getEssence(pouch);
		int addAmount = essenceAmount;
		if (player.getInventory().freeSlots() < essenceAmount) {
			addAmount = essenceAmount - (essenceAmount - player.getInventory().freeSlots());
		}
		Item add = new Item(getEssenceType(pouch).getId(), addAmount);
		if (!player.getInventory().hasSpaceFor(add)) {
			player.sendMessage("You do not have any more free space in your inventory.");
			return;
		}
		if (player.getInventory().add(add)) {
			incrementCharge(pouch, addAmount);
			if (essenceAmount != addAmount) {// means all didnt get added
				player.sendMessage("You do not have any more free space in your inventory.");
			}
		}
	}

	/**
	 * Check for doubles.
	 * @param player the player.
	 */
	public boolean checkDoubles(Player player) {
		boolean hit = false;
		for (RunePouch pouch : values()) {
			if (player.getInventory().getAmount(pouch.getPouch()) > 1 || player.getBank().getAmount(pouch.getPouch()) > 1) {
				hit = true;
				player.getInventory().remove(new Item(pouch.getPouch().getId(), player.getInventory().getAmount(pouch.getPouch()) - 1));
				player.getBank().remove(new Item(pouch.getPouch().getId(), player.getBank().getAmount(pouch.getPouch())));
			}
		}
		return hit;
	}

	/**
	 * Checks the essence pouch.
	 * @param player the player.
	 * @param item the item.
	 */
	public final void check(final Player player, Item item) {
		int amount = getEssence(item);
		player.sendMessage(amount == 0 ? "There are no essences in this pouch." : "There " + (amount == 1 ? "is" : "are") + " " + amount + " " + getPouchEssenceName(item, amount) + " in this pouch.");
	}

	/**
	 * Drops an item.
	 * @param player the player.
	 * @param item the item.
	 */
	private void drop(Player player, Item item) {
		onDrop(player, item);
		DropItemHandler.drop(player, item);
	}

	/**
	 * Handles the on dropping of a pouch.
	 * @param player the player.
	 * @param item the item.
	 */
	public void onDrop(Player player, Item item) {
		if (!isEmpty(item)) {
			resetCharge(item);
			player.sendMessage("The contents of the pouch fell out as you dropped it!");
		}
	}

	/**
	 * Adds an essence amount to the pouch.
	 * @param player the player.
	 * @param pouch the pouch.
	 * @param essence the essence.
	 * @param amount the amount.
	 */
	public void addEssence(Player player, Item pouch, Item essence, int amount) {
		final Item remove = new Item(essence.getId(), amount);
		if (!player.getInventory().containsItem(remove)) {
			return;
		}
		if (player.getInventory().remove(remove)) {
			int charge = getPouchCharge(pouch);
			if (charge == 1000) {
				charge = (isPureEssence(essence) ? PURE_BASE : NORMAL_BASE);
				setCharge(pouch, charge);
			}
			if (isPureEssence(essence) && charge == NORMAL_BASE) {
				charge = PURE_BASE;
			} else if (isNormalEssence(essence) && charge == PURE_BASE) {
				charge = NORMAL_BASE;
			}
			charge -= amount;
			setHash(pouch, charge);
			decay(player, pouch);
		}
	}

	/**
	 * Decays a pouch.
	 * @param player the player.
	 * @param pouch the pouch.
	 */
	public void decay(Player player, Item pouch) {
		incrementDecay(player);
		if (getDecay(player) >= uses) {
			String message = "";
			if (!isDecayed(pouch)) {
				int decrementAmount = 0;
				if (getDecayAmount() > getEssence(pouch)) {
					decrementAmount = getDecayAmount() - (getDecayAmount() - getEssence(pouch));
				} else {
					decrementAmount = getEssence(pouch) - (getEssence(pouch) - getDecayAmount());
				}
				incrementCharge(pouch, decrementAmount);
				message = "Your pouch has decayed through use.";
				player.getInventory().replace(new Item(getDecayedPouch().getId(), pouch.getAmount(), pouch.getCharge()), pouch.getSlot());
			} else {
				message = "Your pouch has decayed beyond any further use.";
				player.getInventory().remove(pouch);
			}
			resetDecay(player);
			player.sendMessage(message);
		}
	}

	/**
	 * Repairs a pouch.
	 * @param pouch the pouch.
	 */
	public void repair(Player player, Item pouch) {
		if (isDecayed(pouch)) {
			player.getInventory().replace(new Item(getPouch().getId(), pouch.getAmount(), pouch.getCharge()), pouch.getSlot());
		}
		resetDecay(player);
	}

	/**
	 * Increments the decay.
	 * @param player the player.
	 */
	public void incrementDecay(Player player) {
		player.getSavedData().getGlobalData().getRcDecays()[ordinal() - 1]++;
	}

	/**
	 * Increments the charge on a pouch.
	 * @param pouch the pouch.
	 * @param chargeIncrement the charge increment.
	 */
	public void incrementCharge(Item pouch, int chargeIncrement) {
		setHash(pouch, getPouchCharge(pouch) + chargeIncrement);
	}

	/**
	 * Decrements the charge on a pouch.
	 * @param pouch the pouch.
	 * @param chargeIncrement the charge increment.
	 */
	public void decrementCharge(Item pouch, int chargeIncrement) {
		setHash(pouch, getPouchCharge(pouch) - chargeIncrement);
	}

	/**
	 * Sets the charge.
	 * @param pouch the pouch.
	 * @param charge the charge.
	 */
	public void setCharge(Item pouch, int charge) {
		setHash(pouch, charge);
	}

	/**
	 * Resets the decay on a pouch.
	 */
	public void resetDecay(Player player) {
		player.getSavedData().getGlobalData().getRcDecays()[ordinal() - 1] = 0;
	}

	/**
	 * Resets the charge on the pouch.
	 * @param pouch the pouch.
	 */
	public void resetCharge(Item pouch) {
		setHash(pouch, 1000);
	}

	/**
	 * Sets the hash of the pouch.
	 * @param pouch the pouch.
	 * @param charge the charge.
	 */
	public void setHash(Item pouch, int charge) {
		pouch.setCharge(charge);
	}

	/**
	 * Gets the decay.
	 * @param player the player.
	 * @return the decay.
	 */
	public int getDecay(Player player) {
		return player.getSavedData().getGlobalData().getRcDecay(ordinal() - 1);
	}

	/**
	 * Gets the charge of the pouch.
	 * @param pouch the pouch.
	 * @return the charge.
	 */
	public int getPouchCharge(Item pouch) {
		return pouch.getCharge();
	}

	/**
	 * Checks if the pouch is full.
	 * @param item the item.
	 * @return {@code True} if so.
	 */
	public boolean isFull(Item item) {
		return getEssence(item) >= getCapacity(pouch);
	}

	/**
	 * Gets the add amount.
	 * @param pouch the pouch.
	 * @param essence the essence.
	 * @return the amount.
	 */
	public int getAddAmount(Item pouch, Item essence, Player player) {
		int essyAmount = player.getInventory().getAmount(essence);
		int pouchAmount = getEssence(pouch);
		int maxAdd = getCapacity(pouch) - pouchAmount;// 3 - 0 == 3, thus max =
		// 3.
		if (essyAmount > maxAdd) {
			return maxAdd;
		} else if (essyAmount <= maxAdd) {
			return essyAmount;
		}
		return 0;

	}

	/**
	 * Checks if essence can go inside a pouch.
	 * @param pouch the pouch.
	 * @param essence the essence.
	 * @param player the player.
	 * @return {@code True} if so.
	 */
	public boolean isValidEssence(Item pouch, Item essence, Player player) {
		if (isEmpty(pouch)) {
			return true;
		}
		return getPouchEssenceName(pouch).equals(getEssenceName(essence));
	}

	/**
	 * Checks if the pouch is full.
	 * @param item the item.
	 * @param player the player.
	 * @return
	 */
	public boolean isFull(Item item, Player player) {
		if (isFull(item)) {
			player.sendMessage("Your pouch is full.");
			return true;
		}
		return false;
	}

	/**
	 * Gets the essence name.
	 * @param item the item.
	 * @param amount the amount.
	 * @return the name.
	 */
	public String getPouchEssenceName(Item item, int amount) {
		return getPouchEssenceName(item) + (amount > 1 ? "s" : "");
	}

	/**
	 * Gets the essence name.
	 * @param item the item.
	 * @return the name.
	 */
	public String getPouchEssenceName(Item item) {
		int charge = getPouchCharge(item);
		return charge > NORMAL_BASE ? "pure essence" : "normal essence";
	}

	/**
	 * Gets the essence item in the pouch.
	 * @param pouch the pouch.
	 * @return {@code True} item.
	 */
	public Item getEssenceInPouch(Item pouch) {
		return getPouchEssenceName(pouch).equals("pure essence") ? PURE_ESSENCE : NORMAL_ESSENCE;
	}

	/**
	 * Gets the essence name.
	 * @param essence the essence.
	 * @return the name.
	 */
	public String getEssenceName(Item essence) {
		return essence.getId() == PURE_ESSENCE.getId() ? "pure essence" : "normal essence";
	}

	/**
	 * Gets the essence base.
	 * @param item the item.
	 * @return the base.
	 */
	public int getEssenceBase(Item item) {
		return getPouchEssenceName(item).equals("pure essence") ? PURE_BASE : NORMAL_BASE;
	}

	/**
	 * Checks if it's a pure essence pouch.
	 * @param pouch the item.
	 * @return {@code True} if so.
	 */
	public boolean isPureEssencePouch(Item pouch) {
		return getPouchEssenceName(pouch).equals("pure essence");
	}

	/**
	 * Checks if its a pure essence.
	 * @param essence the essence.
	 * @return {@code True} if so.
	 */
	public boolean isPureEssence(Item essence) {
		return essence.getId() == PURE_ESSENCE.getId();
	}

	/**
	 * Checks if its normal essence.
	 * @param essence the essence.
	 * @return {@code True} if so.
	 */
	public boolean isNormalEssence(Item essence) {
		return essence.getId() == NORMAL_ESSENCE.getId();
	}

	/**
	 * Gets the essence type.
	 * @param pouch the pouch.
	 * @return the item.
	 */
	public Item getEssenceType(Item pouch) {
		return getPouchEssenceName(pouch).equals("pure essence") ? PURE_ESSENCE : NORMAL_ESSENCE;
	}

	/**
	 * Gets the essence type.
	 * @param player the player.
	 * @return {@code True} if so.
	 */
	public Item getEssence(Player player) {
		if (player.getInventory().containsItem(PURE_ESSENCE)) {
			return PURE_ESSENCE;
		} else if (player.getInventory().containsItem(NORMAL_ESSENCE)) {
			return NORMAL_ESSENCE;
		}
		return null;
	}

	/**
	 * Checks if the player has essence.
	 * @param player the player.
	 * @return {@code True} if so.
	 */
	public boolean hasEssence(Player player) {
		return player.getInventory().containsItem(PURE_ESSENCE) || player.getInventory().containsItem(NORMAL_ESSENCE);
	}

	/**
	 * Gets a rune pouch for the item.
	 * @param pouch the pouch.
	 * @return the {@code RunePouch}.
	 */
	public static RunePouch forItem(final Item pouch) {
		for (RunePouch p : RunePouch.values()) {
			if (p.getPouch().getId() == pouch.getId() || (p != SMALL && p.getDecayedPouch().getId() == pouch.getId())) {
				return p;
			}
		}
		return null;
	}

	/**
	 * Checks if the pouch is empty.
	 * @param item the item.
	 * @return {@code True} if so.
	 */
	public boolean isEmpty(Item item) {
		return getEssence(item) <= 0;
	}

	/**
	 * Gets the essence amount.
	 * @param item the item.
	 * @return the amount.
	 */
	public int getEssence(Item item) {
		if (getPouchCharge(item) == 1000 || getPouchCharge(item) == 2000) {
			return 0;
		}
		return getEssenceBase(item) - getPouchCharge(item);
	}

	/**
	 * Gets the pouch.
	 * @return The pouch.
	 */
	public Item getPouch() {
		return pouch;
	}

	/**
	 * Gets the level.
	 * @return The level.
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * Gets the capacity.
	 * @param pouch the pouch.
	 * @return The capacity.
	 */
	public int getCapacity(Item pouch) {
		return capacity - (isDecayed(pouch) ? getDecayAmount() : 0);
	}

	/**
	 * Gets the decay amount.
	 * @return the amount.
	 */
	public int getDecayAmount() {
		return this == GIANT ? 3 : this == LARGE ? 2 : 1;
	}

	/**
	 * Gets the cumulativeCapacity.
	 * @return The cumulativeCapacity.
	 */
	public int getCumulativeCapacity() {
		return cumulativeCapacity;
	}

	/**
	 * Gets the uses.
	 * @return The uses.
	 */
	public int getUses() {
		return uses;
	}

	/**
	 * Checks if the pouch is decayed.
	 * @param pouch the pouch.
	 * @returne {@code True} if decayed.
	 */
	public boolean isDecayed(Item pouch) {
		return pouch.getId() == decayedPouch.getId();
	}

	/**
	 * Checks if the pouch is decayable.
	 * @return {@code True} if so.
	 */
	public boolean isDecayable() {
		return this != SMALL;
	}

	/**
	 * Gets the decayedPouch.
	 * @return The decayedPouch.
	 */
	public Item getDecayedPouch() {
		if (this == SMALL) {
			return pouch;
		}
		return decayedPouch;
	}

	/**
	 * Checks if this pouch has decay.
	 * @param player the player.
	 * @param pouch the pouch.
	 * @return {@code True} if so.
	 */
	public boolean hasDecay(Player player, Item pouch) {
		return getDecay(player) > 0 || isDecayed(pouch);
	}

}
