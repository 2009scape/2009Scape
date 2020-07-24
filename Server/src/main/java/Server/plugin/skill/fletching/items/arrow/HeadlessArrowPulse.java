package plugin.skill.fletching.items.arrow;

import core.game.content.ItemNames;
import plugin.skill.SkillPulse;
import plugin.skill.Skills;
import core.game.node.entity.player.Player;
import core.game.node.item.Item;

/**
 * Represents the arrow pulse for creating unfinished arrows.
 * @author 'Vexia
 */
public final class HeadlessArrowPulse extends SkillPulse<Item> {

	/**
	 * Represents the headless arrow item.
	 */
	private final Item HEADLESS_ARROW = new Item(ItemNames.HEADLESS_ARROW_53);

	/**
	 * Represents the arrow shaft item.
	 */
	private final Item ARROW_SHAFT = new Item(ItemNames.ARROW_SHAFT_52);

	/**
	 * Represents the feather items.
	 */
	private static final Item[] FEATHER = new Item[] {
			new Item(ItemNames.FEATHER),
			new Item(ItemNames.STRIPY_FEATHER_10087),
			new Item(ItemNames.RED_FEATHER_10088),
			new Item(ItemNames.BLUE_FEATHER_10089),
			new Item(ItemNames.YELLOW_FEATHER_10090),
			new Item(ItemNames.ORANGE_FEATHER_10091)
	};

	/**
	 * The feather being used.
	 */
	private Item feather;

	/**
	 * Represents the amount to make.
	 */
	private int sets;

	/**
	 * Represents if we should use sets, meaning we have 15 & 15 arrow shafts and feathers.
	 */
	private boolean useSets = false;

	/**
	 * Constructs a new {@code ArrowPulse.java} {@code Object}.
	 * @param player the player.
	 * @param node the node.
	 */
	public HeadlessArrowPulse(Player player, Item node, Item feather, int sets) {
		super(player, node);
		this.sets = sets;
		this.feather = feather;
	}

	@Override
	public boolean checkRequirements() {
		if (!player.getInventory().containsItem(ARROW_SHAFT)) {
			player.getDialogueInterpreter().sendDialogue("You don't have any arrow shafts.");
			return false;
		}
		if (feather == null || !player.getInventory().containsItem(feather)) {
			player.getDialogueInterpreter().sendDialogue("You don't have any feathers.");
			return false;
		}
		if (player.getInventory().contains(ARROW_SHAFT.getId(), 15) && player.getInventory().contains(feather.getId(), 15)) {
			useSets = true;
		} else {
			useSets = false;
		}
		return true;
	}

	@Override
	public void animate() {
	}

	@Override
	public boolean reward() {
		int featherAmount = player.getInventory().getAmount(feather);
		int shaftAmount = player.getInventory().getAmount(ARROW_SHAFT);
		if (getDelay() == 1) {
			super.setDelay(3);
		}
		if (featherAmount >= 15 && shaftAmount >= 15) {
			feather.setAmount(15);
			ARROW_SHAFT.setAmount(15);
			player.getPacketDispatch().sendMessage("You attach feathers to 15 arrow shafts.");
		} else {
			int amount = featherAmount > shaftAmount ? shaftAmount : featherAmount;
			feather.setAmount(amount);
			ARROW_SHAFT.setAmount(amount);
			player.getPacketDispatch().sendMessage(amount == 1
					? "You attach a feathers to a shaft." : "You attach feathers to " + amount + " arrow shafts.");
		}
		if (player.getInventory().remove(feather, ARROW_SHAFT)) {
			HEADLESS_ARROW.setAmount(feather.getAmount());
			player.getSkills().addExperience(Skills.FLETCHING, HEADLESS_ARROW.getAmount(), true);
			player.getInventory().add(HEADLESS_ARROW);
		}
		HEADLESS_ARROW.setAmount(1);
		feather.setAmount(1);
		ARROW_SHAFT.setAmount(1);
		if (!player.getInventory().containsItem(ARROW_SHAFT)) {
			return true;
		}
		if (!player.getInventory().containsItem(feather)) {
			return true;
		}
		sets--;
		return sets <= 0;
	}

	@Override
	public void message(int type) {
	}

	/**
	 * Gets the feather item.
	 * @return the item.
	 */
	private Item getFeather() {
		int length = FEATHER.length;
		for (int i = 0; i < length; i++) {
			Item f = FEATHER[i];
			if (player.getInventory().containsItem(f)) {
				return f;
			}
		}
		return null;
	}
}
