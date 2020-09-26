package plugin.skill.herblore;

import plugin.skill.SkillPulse;
import plugin.skill.Skills;
import core.game.node.entity.player.Player;
import core.game.node.item.Item;
import core.game.world.update.flag.context.Animation;

/**
 * Represents the pulse used to create herb tars.
 * @author 'Vexia
 */
public final class HerbTarPulse extends SkillPulse<Item> {

	/**
	 * Represents the animation to use.
	 */
	private static final Animation ANIMATION = new Animation(364);

	/**
	 * Represents the pestle and mortar item.
	 */
	private static final Item PESTLE_AND_MORTAR = new Item(233);

	/**
	 * Represents the swamp tar item.
	 */
	private static final Item SWAMP_TAR = new Item(1939, 15);

	/**
	 * Represents the tar to make.
	 */
	private final Tars tar;

	/**
	 * Represents the amount to make.
	 */
	private int amount;

	/**
	 * Constructs a new {@code HerbTarPulse} {@code Object}.
	 * @param player the player.
	 * @param node the node.
	 * @param tar the tar.
	 * @param amount the amount.
	 */
	public HerbTarPulse(Player player, Item node, Tars tar, int amount) {
		super(player, node);
		this.tar = tar;
		this.amount = amount;
	}

	@Override
	public boolean checkRequirements() {
		if (!player.getQuestRepository().isComplete("Drudic Ritual")) {
			player.getPacketDispatch().sendMessage("You must complete the Druidic Ritual quest before you can use Herblore.");
			return false;
		}
		if (player.getSkills().getLevel(Skills.HERBLORE) < tar.getLevel()) {
			player.getPacketDispatch().sendMessage("You need a Herblore level of at least " + tar.getLevel() + " in order to do this.");
			return false;
		}
		if (!player.getInventory().containsItem(PESTLE_AND_MORTAR)) {
			player.getPacketDispatch().sendMessage("You need Pestle and Mortar in order to crush the herb.");
			return false;
		}
		if (!player.getInventory().containsItem(SWAMP_TAR)) {
			player.getPacketDispatch().sendMessage("You need at least 15 swamp tar in order to do this.");
			return false;
		}
		return true;
	}

	@Override
	public void animate() {
		player.animate(ANIMATION);
	}

	@Override
	public boolean reward() {
		if (getDelay() == 1) {
			setDelay(4);
			return false;
		}
		if (player.getInventory().containsItem(SWAMP_TAR) && player.getInventory().containsItem(tar.getIngredient()) && player.getInventory().remove(SWAMP_TAR) && player.getInventory().remove(tar.getIngredient())) {
			final Item item = new Item(tar.getTar().getId(), 15);
		    player.getInventory().add(item);
			player.getSkills().addExperience(Skills.HERBLORE, tar.getExperience(), true);
			player.getPacketDispatch().sendMessage("You add the " + tar.getIngredient().getName().toLowerCase().replace("clean", "").trim() + " to the swamp tar.");
		} else {
			return true;
		}
		amount--;
		return amount == 0;
	}

	@Override
	public void message(int type) {
	}

	/**
	 * Gets the tar.
	 * @return The tar.
	 */
	public Tars getTar() {
		return tar;
	}

}
