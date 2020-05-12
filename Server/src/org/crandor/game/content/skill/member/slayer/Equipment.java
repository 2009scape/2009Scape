package org.crandor.game.content.skill.member.slayer;

import org.crandor.game.content.skill.Skills;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.item.Item;

/**
 * Represents a slayer equipment.
 * @author Vexia
 */
public enum Equipment {
	ENCHANTED_GEM(new Item(4155, 1)),
	MIRROR_SHIELD(new Item(4156, 1)) {
		@Override
		public boolean hasRequirement(final Player player) {
			return player.getSkills().getStaticLevel(Skills.DEFENCE) >= 20;
		}
	},
	LEAF_BLADED_SPEAR(new Item(4158, 1)), 
	BROAD_ARROWS(new Item(4150, 1)), 
	BAG_OF_SALT(new Item(4161, 1)), 
	ROCK_HAMMER(new Item(4162, 1)),
	FACEMASK(new Item(4164, 1), true), 
	EARMUFFS(new Item(4166, 1), true) {
		@Override
		public boolean hasRequirement(final Player player) {
			return player.getSkills().getStaticLevel(Skills.DEFENCE) >= 15;
		}
	},
	NOSE_PEG(new Item(4168, 1), true),
	SLAYERS_STAFF(new Item(4170, 1)),
	SPINY_HELMET(new Item(4551, 1), true), 
	FISHING_EXPLOSIVE(new Item(6660, 1)), 
	ICE_COOLER(new Item(6696, 1)),
	SLAYER_GLOVES(new Item(6708, 1)), 
	UNLIT_BUG_LANTERN(new Item(7051, 1)), 
	INSULATED_BOOTS(new Item(7159, 1)),
	FUNGICIDE_SPRAY_10(new Item(7421, 1)),
	FUNGICIDE(new Item(7432, 1)), 
	LUMBER_PATCH(new Item(8932, 1)),
	SLAYER_BELL(new Item(10952, 1)), 
	WITCHWOOD_ICON(new Item(8923, 1)),
	LIT_BUG_LANTERN(new Item(7053, 1)),

	//null equipment for when none is needed
	NONE(new Item(0));

	/**
	 * The slayer helmet item.
	 */
	private static final Item SLAYER_HELM = new Item(13263);

	/**
	 * Represents the item.
	 */
	private final Item item;
	
	/**
	 * IF the slayer helm has this equipment.
	 */
	private final boolean slayerHelm;

	/**
	 * Constructs a new {@Code Equipment} {@Code Object}
	 * @param item The item.
	 * @param slayerHelm If slayer helm incorporated.
	 */
	Equipment(Item item, boolean slayerHelm) {
		this.item = item;
		this.slayerHelm = slayerHelm;
	}
	
	/**
	 * Constructs a new {@Code Equipment} {@Code Object}
	 * @param item The item.
	 */
	Equipment(Item item) {
		this(item, false);
	}

	/**
	 * Checks if the player has the requirement for the item.
	 * @param player the player.
	 * @return {@code True} if so.
	 */
	public boolean hasRequirement(final Player player) {
		return item.getDefinition().hasRequirement(player, false, false);
	}

	/**
	 * Checks if the player has the equipment equipped.
	 * @param player the player.
	 * @return {@code True} if so.
	 */
	public boolean hasEquipment(final Player player) {
		if (slayerHelm && player.getEquipment().containsItem(SLAYER_HELM)) {
			return true;
		}
		return player.getEquipment().containsItem(item);
	}

	/**
	 * Gets the item.
	 * @return The item.
	 */
	public Item getItem() {
		return item;
	}

	/**
	 * Gets the slayerHelm.
	 * @return the slayerHelm.
	 */
	public boolean isSlayerHelm() {
		return slayerHelm;
	}
}
