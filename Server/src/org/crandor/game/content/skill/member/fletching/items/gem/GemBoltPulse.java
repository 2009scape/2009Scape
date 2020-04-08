package org.crandor.game.content.skill.member.fletching.items.gem;

import org.crandor.game.content.skill.SkillPulse;
import org.crandor.game.content.skill.Skills;
import org.crandor.game.content.skill.member.fletching.Fletching;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.item.Item;

/**
 * Represents the attaching of a gem bolt to a premade bolt.
 * @author Ceikry
 */
public final class GemBoltPulse extends SkillPulse<Item> {

	/**
	 * Represents the gem bolt being made.
	 */
	private Fletching.GemBolts bolt;

	/**
	 * Represents the sets to make.
	 */
	private int sets = 0;

	/**
	 * Represents the ticks passed.
	 */
	private int ticks;

	/**
	 * Constructs a new {@code GemBoltPulse} {@code Object}.
	 * @param player the player.
	 * @param node the node.
	 * @param sets the sets.
	 */
	public GemBoltPulse(Player player, Item node, Fletching.GemBolts bolt, int sets) {
		super(player, node);
		this.bolt = bolt;
		this.sets = sets;
	}

	@Override
	public boolean checkRequirements() {
		if (player.getSkills().getLevel(Skills.FLETCHING) < bolt.level) {
			player.getDialogueInterpreter().sendDialogue("You need a Fletching level of " + bolt.level + " or above to do that.");
			return false;
		}
		if (!player.getInventory().containsItem(new Item(bolt.base)) || !player.getInventory().containsItem(new Item(bolt.tip))) {
			return false;
		}
		return true;
	}

	@Override
	public void animate() {
	}

	@Override
	public boolean reward() {
		if (++ticks % 3 != 0) {
			return false;
		}
		int baseAmount = player.getInventory().getAmount(bolt.base);
		int tipAmount = player.getInventory().getAmount(bolt.tip);
		Item base = new Item(bolt.base);
		Item tip = new Item(bolt.tip);
		Item product = new Item(bolt.product);
		if(baseAmount >= 10 && tipAmount >= 10){
			base.setAmount(10);
			tip.setAmount(10);
			product.setAmount(10);
		} else {
			int amount = baseAmount > tipAmount ? tipAmount : baseAmount;
			base.setAmount(amount);
			tip.setAmount(amount);
			product.setAmount(amount);
		}
		if (player.getInventory().remove(base,tip)) {
			player.getInventory().add(product);
			player.getSkills().addExperience(Skills.FLETCHING, bolt.experience * product.getAmount(), true);
			player.getPacketDispatch().sendMessage(product.getAmount() == 1 ? "You attach the tip to the bolt." : "You fletch " + product.getAmount() + " bolts.");
		}
		sets--;
		return sets <= 0;
	}

}
