package org.crandor.game.content.skill.member.fletching.items.bow;

import org.crandor.game.content.skill.SkillPulse;
import org.crandor.game.content.skill.Skills;
import org.crandor.game.content.skill.member.fletching.Fletching;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.item.Item;

/**
 * Represents the skill pulse of stringing.
 * @author Ceikry
 */
public class StringPulse extends SkillPulse<Item> {

	/**
	 * Represents the string bow.
	 */
	private final Fletching.String bow;

	/**
	 * The amount.
	 */
	private int amount;

	/**
	 * Constructs a new {@code StringbowPlugin.java} {@code Object}.
	 * @param player the player.
	 * @param node the node.
	 */
	public StringPulse(Player player, Item node, final Fletching.String bow, int amount) {
		super(player, node);
		setDelay(bow.string == 1777 ? 9 : 7);
		this.bow = bow;
		this.amount = amount;
	}

	@Override
	public boolean checkRequirements() {
		if(getDelay() == 1){
			setDelay(bow.string == 1777 ? 9 : 7);
		}
		if (player.getSkills().getLevel(Skills.FLETCHING) < bow.level) {
			player.getDialogueInterpreter().sendDialogue("You need a fletching level of " + bow.level + " to string this bow.");
			return false;
		}
		if (!player.getInventory().containsItem(new Item(bow.string))) {
			player.getDialogueInterpreter().sendDialogue("You seem to have run out of bow strings.");
			return false;
		}
		animate();
		return true;
	}

	@Override
	public void animate() {
		player.animate(bow.animation);
	}

	@Override
	public boolean reward() {
		if (player.getInventory().remove(new Item(bow.unfinished), new Item(bow.string))) {
			player.getInventory().add(new Item(bow.product));
			player.getSkills().addExperience(Skills.FLETCHING, bow.experience, true);
			player.getPacketDispatch().sendMessage("You add a string to the bow.");
		}
		if (!player.getInventory().containsItem(new Item(bow.string)) || !player.getInventory().containsItem(new Item(bow.unfinished))) {
			return true;
		}
		amount--;
		return amount == 0;
	}

	@Override
	public void message(int type) {
	}

}