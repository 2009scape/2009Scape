package plugin.skill.fletching.items.gem;

import plugin.skill.SkillPulse;
import plugin.skill.Skills;
import plugin.skill.fletching.Fletching;
import core.game.node.entity.player.Player;
import core.game.node.item.Item;
import core.game.world.update.flag.context.Animation;

/**
 * Represents the gem cutting pulse(gem to bolt).
 * @author Ceikry
 */
public final class GemBoltCutPulse extends SkillPulse<Item> {

	/**
	 * Represents the cutting animation.
	 */
	private static final Animation ANIMATION = new Animation(6702);

	/**
	 * Represents the gem we're cutting.
	 */
	private final Fletching.GemBolts gem;

	/**
	 * Represents the amount to make.
	 */
	private int amount;

	/**
	 * Represents the ticks passed.
	 */
	private int ticks;

	/**
	 * Constructs a new {@code GemCutPulse.java} {@code Object}.
	 * @param player the player.
	 * @param node the node.
	 * @param amount the amount.
	 */
	public GemBoltCutPulse(Player player, Item node, final Fletching.GemBolts gem, final int amount) {
		super(player, node);
		this.gem = gem;
		this.amount = amount;
	}

	@Override
	public boolean checkRequirements() {
		if (player.getSkills().getLevel(Skills.FLETCHING) < gem.level) {
			player.getDialogueInterpreter().sendDialogue("You need a Fletching level of " + gem.level + " or above to do that.");
			return false;
		}
		if (!player.getInventory().containsItem(new Item(gem.gem))) {
			return false;
		}
		return true;
	}

	@Override
	public void animate() {
		if (ticks % 6 == 0) {
			player.animate(ANIMATION);
		}
	}

	@Override
	public boolean reward() {
		if (++ticks % 5 != 0) {
			return false;
		}
		Item reward = new Item(gem.tip,12);
		if (player.getInventory().remove(new Item(gem.gem))) {
			player.getInventory().add(reward);
			player.getSkills().addExperience(Skills.FLETCHING, gem.experience, true);
		}
		amount--;
		return amount <= 0;
	}

}
