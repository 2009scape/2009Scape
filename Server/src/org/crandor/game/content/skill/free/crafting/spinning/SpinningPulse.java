package org.crandor.game.content.skill.free.crafting.spinning;

import org.crandor.cache.def.impl.ItemDefinition;
import org.crandor.game.content.skill.SkillPulse;
import org.crandor.game.content.skill.Skills;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.item.Item;
import org.crandor.game.world.update.flag.context.Animation;

/**
 * Represents the pulse used to spin an item.
 * @author 'Vexia
 */
public final class SpinningPulse extends SkillPulse<Item> {

	/**
	 * Represents the animation to use.
	 */
	private static final Animation ANIMATION = new Animation(896);

	/**
	 * Represents the type of spinning item.
	 */
	private final SpinningItem type;

	/**
	 * Represents the amount to spin.
	 */
	private int ammount;

	/**
	 * Represents the ticks passed.
	 */
	private int ticks;

	/**
	 * Constructs a new {@code SpinningPulse.java} {@Code Object}
	 * @param player
	 * @param node
	 */
	public SpinningPulse(Player player, Item node, int ammount, SpinningItem def) {
		super(player, node);
		this.type = def;
		this.ammount = ammount;
	}

	@Override
	public boolean checkRequirements() {
		player.getInterfaceManager().close();
		if (player.getSkills().getLevel(Skills.CRAFTING) < type.getLevel()) {
			player.getPacketDispatch().sendMessage("You need a crafting level of " + type.getLevel() + " to make this.");
			return false;
		}
		if (!player.getInventory().contains(type.getNeed(), 1)) {
			player.getPacketDispatch().sendMessage("You need " + ItemDefinition.forId(type.getNeed()).getName() + " to do this.");
			return false;
		}
		return true;
	}

	@Override
	public void animate() {
		if (ticks % 5 == 0) {
			player.animate(ANIMATION);
		}
	}

    @Override
    public boolean reward() {
	if (++ticks % 3 != 0) {
	    return false;
	}
	if (player.getInventory().remove(new Item(type.getNeed(), 1))) {
		final Item item = new Item(type.getProduct(), 1);
	    player.getInventory().add(item);
	    player.getSkills().addExperience(Skills.CRAFTING, type.getExp(), true);
	}
		ammount--;
		return ammount < 1;
    }

	@Override
	public void message(int type) {
	}

}
