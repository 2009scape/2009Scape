package plugin.skill.summoning.familiar;

import core.game.node.entity.combat.equipment.WeaponInterface;
import core.game.node.entity.player.Player;
import core.game.node.item.Item;
import core.game.world.GameWorld;
import core.tools.RandomFunction;

/**
 * Represents a forager familiar.
 * @author Vexia
 */
public abstract class Forager extends BurdenBeast {

	/**
	 * The items to product if passive with no restrictions.
	 */
	private final Item[] items;

	/**
	 * The delay until the next random passive product.
	 */
	private int passiveDelay;

	/**
	 * Constructs a new {@code Forager} {@code Object}.
	 * @param owner the owner.
	 * @param id the id.
	 * @param ticks the ticks.
	 * @param pouchId the pouch id.
	 * @param specialCost the special cost.
	 */
	public Forager(Player owner, int id, int ticks, int pouchId, int specialCost, int attackStyle, final Item... items) {
		super(owner, id, ticks, pouchId, specialCost, 30, attackStyle);
		this.items = items;
		setRandomPassive();
	}

	/**
	 * Constructs a new {@code Forager} {@code Object}.
	 * @param owner the owner.
	 * @param id the id.
	 * @param ticks the ticks.
	 * @param pouchId the pouch id.
	 * @param specialCost the special cost.
	 */
	public Forager(Player owner, int id, int ticks, int pouchId, int specialCost, final Item... items) {
		this(owner, id, ticks, pouchId, specialCost, WeaponInterface.STYLE_DEFENSIVE, items);
	}

	@Override
	public void handleFamiliarTick() {
		super.handleFamiliarTick();
		if (items != null && items.length > 0 && passiveDelay < GameWorld.getTicks()) {
			if (RandomFunction.random(getRandom()) < 4) {
				produceItem(items[RandomFunction.random(items.length)]);
			}
			setRandomPassive();
		}
	}

	/**
	 * Adds an item to the container.
	 * @param item the item.
	 * @return {@code True} if so.
	 */
	public boolean produceItem(final Item item) {
		if (!container.hasSpaceFor(item)) {
			owner.getPacketDispatch().sendMessage("Your familar is too full to collect items.");
			return false;
		}
		owner.getPacketDispatch().sendMessage(item.getAmount() == 1 ? "Your familar has produced an item." : "Your familiar has produced items.");
		return container.add(item);
	}

	/**
	 * Wrapper method for {@link #produceItem()}.
	 * @return {@code True} if produced.
	 */
	public boolean produceItem() {
		if (items == null || items.length == 0) {
			return false;
		}
		return produceItem(items[RandomFunction.random(items.length)]);
	}

	/**
	 * Handles the passive reward of a forager.
	 */
	public void handlePassiveAction() {

	}

	/**
	 * Gets the random mod.
	 * @return the random mod.
	 */
	public int getRandom() {
		return 11;
	}

	/**
	 * Sets a random passive delay.
	 */
	public void setRandomPassive() {
		passiveDelay = GameWorld.getTicks() + RandomFunction.random(100, 440);
	}
}
