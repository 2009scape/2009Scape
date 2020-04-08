package org.crandor.game.content.skill.member.fletching.items.arrow;

import org.crandor.game.content.skill.SkillPulse;
import org.crandor.game.content.skill.Skills;
import org.crandor.game.content.skill.member.fletching.Fletching;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.item.Item;

/**
 * Represents the arrow head pulse to complete the headless arrow.
 * @author 'Vexia
 */
public class ArrowHeadPulse extends SkillPulse<Item> {

	/**
	 * Represents the headless arrow item.
	 */
	private static final Item HEADLESS_ARROW = new Item(53);

	/**
	 * Represents the arrow head.
	 */
	private final Fletching.ArrowHeads arrow;

	/**
	 * Represents the sets to do.
	 */
	private int sets;

	/**
	 * Constructs a new {@code ArrowHeadPulse.java} {@code Object}.
	 * @param player the player.
	 * @param node the node.
	 * @param arrow the arrow.
	 * @param sets the sets.
	 */
	public ArrowHeadPulse(final Player player, final Item node, final Fletching.ArrowHeads arrow, final int sets) {
		super(player, node);
		this.arrow = arrow;
		this.sets = sets;
	}

	@Override
	public boolean checkRequirements() {
		if (arrow.unfinished == 4160) {
			if (!player.getSlayer().getLearned()[0]) {
				player.getDialogueInterpreter().sendDialogue("You need to unlock the ability to create broad arrows.");
				return false;
			}
		}
		if (player.getSkills().getLevel(Skills.FLETCHING) < arrow.level) {
			player.getDialogueInterpreter().sendDialogue("You need a fletching level of " + arrow.level + " to do this.");
			return false;
		}
		return true;
	}

	@Override
	public void animate() {
	}

	@Override
	public boolean reward() {
		if (getDelay() == 1) {
			super.setDelay(3);
		}
		Item tip = arrow.getUnfinished();
		int tipAmount = player.getInventory().getAmount(arrow.unfinished);
		int shaftAmount = player.getInventory().getAmount(HEADLESS_ARROW);
		if (tipAmount >= 15 && shaftAmount >= 15) {
			HEADLESS_ARROW.setAmount(15);
			tip.setAmount(15);
			player.getPacketDispatch().sendMessage("You attach arrow heads to 15 arrow shafts.");
		} else {
			int amount = tipAmount > shaftAmount ? shaftAmount : tipAmount;
			HEADLESS_ARROW.setAmount(amount);
			tip.setAmount(amount);
			player.getPacketDispatch().sendMessage(amount == 1 ? "You attach an arrow head to an arrow shaft." : "You attach arrow heads to " + amount + " arrow shafts.");
		}
		if (player.getInventory().remove(HEADLESS_ARROW, tip)) {
			player.getSkills().addExperience(Skills.FLETCHING, arrow.experience * tip.getAmount(), true);
			Item product = arrow.getFinished();
			product.setAmount(tip.getAmount());
			player.getInventory().add(product);
		}
		HEADLESS_ARROW.setAmount(1);
		tip.setAmount(1);
		if (!player.getInventory().containsItem(HEADLESS_ARROW)) {
			return true;
		}
		if (!player.getInventory().containsItem(tip)) {
			return true;
		}
		sets--;
		return sets == 0;
	}

	@Override
	public void message(int type) {

	}

}
