package org.crandor.game.content.skill.member.fletching;

import org.crandor.cache.def.impl.ItemDefinition;
import org.crandor.game.content.skill.SkillPulse;
import org.crandor.game.content.skill.Skills;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.entity.player.info.portal.Perks;
import org.crandor.game.node.item.Item;
import org.crandor.game.world.update.flag.context.Animation;
import org.crandor.tools.StringUtils;

/**
 * fletching skill pulse
 * @author ceik
 */
public final class FletchingPulse extends SkillPulse<Item> {

	/**
	 * Represents the animation used in this generic pulse.
	 */
	private static final Animation ANIMATION = new Animation(1248);

	/**
	 * Represents the item we are fletching.
	 */
	private Fletching.FletchingItems fletch;

	/**
	 * Represents the amount to fletch.
	 */
	private int amount = 0;

	/**
	 * Constructs a new {@code FletchingPulse.java} {@code Object}.
	 * @param player
	 * @param node
	 */
	public FletchingPulse(final Player player, final Item node, final int amount, final Fletching.FletchingItems fletch) {
		super(player, node);
		this.amount = amount;
		this.fletch = fletch;
	}

	@Override
	public boolean checkRequirements() {
		if (player.getSkills().getLevel(Skills.FLETCHING) < fletch.level) {
			player.getDialogueInterpreter().sendDialogue("You need a Fletching skill of " + fletch.level + " or above to make " + (StringUtils.isPlusN(fletch.getItem().getName().replace("(u)", "").trim()) ? "an" : "a") + " " + fletch.getItem().getName().replace("(u)", "").trim());
			return false;
		}
		if (amount > player.getInventory().getAmount(node)) {
			amount = player.getInventory().getAmount(node);
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
			super.setDelay(4);
			return false;
		}
		if (player.getInventory().remove(node)) {
			final Item item = new Item(fletch.id,fletch.amount);
		    player.getInventory().add(item);
			player.getSkills().addExperience(Skills.FLETCHING, fletch.experience, true);
			String message = getMessage();
			player.getPacketDispatch().sendMessage(message);
		} else {
			return true;
		}
		amount--;
		return amount == 0;
	}

	/**
	 * Method used to get the message of the fletch.
	 * @return the message.
	 */
	public String getMessage() {
		switch (fletch) {
		case ARROW_SHAFT:
			return "You carefully cut the wood into 15 arrow shafts.";
		default:
			return "You carefully cut the wood into " + (StringUtils.isPlusN(fletch.getItem().getName()) ? "an" : "a") + " " + fletch.getItem().getName().replace("(u)", "").trim() + ".";
		}
	}
}
