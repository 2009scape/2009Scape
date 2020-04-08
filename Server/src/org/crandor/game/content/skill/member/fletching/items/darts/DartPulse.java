package org.crandor.game.content.skill.member.fletching.items.darts;

import org.crandor.game.content.skill.SkillPulse;
import org.crandor.game.content.skill.Skills;
import org.crandor.game.content.skill.member.fletching.Fletching;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.item.Item;

/**
 * Represents the dart pulse.
 * @author ceikry
 */
public final class DartPulse extends SkillPulse<Item> {

	/**
	 * Represents the feather item.
	 */
	private static final Item FEATHER = new Item(314);

	/**
	 * Represents the dart.
	 */
	private final Fletching.Darts dart;

	/**
	 * Represents the sets to make.
	 */
	private int sets;

	/**
	 * Constructs a new {@code DartPulse.java} {@code Object}.
	 * @param player the player.
	 * @param node the node.
	 */
	public DartPulse(Player player, Item node, Fletching.Darts dart, int sets) {
		super(player, node);
		this.dart = dart;
		this.sets = sets;
	}

	@Override
	public boolean checkRequirements() {
		if (player.getSkills().getLevel(Skills.FLETCHING) < dart.level) {
			player.getDialogueInterpreter().sendDialogue("You need a fletching level of " + dart.level + " to do this.");
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
		final Item unfinished = dart.getUnfinished();
		final int dartAmount = player.getInventory().getAmount(unfinished);
		final int featherAmount = player.getInventory().getAmount(FEATHER);
		if (dartAmount >= 10 && featherAmount >= 10) {
			FEATHER.setAmount(10);
			unfinished.setAmount(10);
			player.getPacketDispatch().sendMessage("You attach feathers to 10 darts.");
		} else {
			int amount = featherAmount > dartAmount ? dartAmount : featherAmount;
			FEATHER.setAmount(amount);
			unfinished.setAmount(amount);
			player.getPacketDispatch().sendMessage(amount == 1 ? "You attach a feather to a dart." : "You attach feathers to " + amount + " darts.");
		}
		if (player.getInventory().remove(FEATHER, unfinished)) {
			Item product = dart.getFinished();
			product.setAmount(FEATHER.getAmount());
			player.getSkills().addExperience(Skills.FLETCHING, dart.experience * product.getAmount(), true);
			player.getInventory().add(product);
		}
		FEATHER.setAmount(1);
		if (!player.getInventory().containsItem(FEATHER)) {
			return true;
		}
		if (!player.getInventory().containsItem(dart.getUnfinished())) {
			return true;
		}
		sets--;
		return sets == 0;
	}

	@Override
	public void message(int type) {
	}

}
