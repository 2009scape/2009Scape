package plugin.consumable;


import core.game.node.entity.player.Player;

public abstract class ConsumableEffect {
	public abstract void activate(Player p);

	public int getHealthEffectValue(Player player) {
		return 0;
	}
}

/*
package plugin.consumable;

import plugin.skill.SkillBonus;
import plugin.skill.Skills;
import core.game.node.entity.player.Player;

import java.util.HashMap;
import java.util.Map;

*/
/**
 * Represents a potion effect enumeration.
 * @author Emperor
 *//*

public enum ConsumableEffect {


	*/
/**
	 * Constructs a new {@code ConsumableEffect} {@code Object}.
	 * @param name The potion name (as it appears in the message).
	 * @param itemIds The item ids of all the doses (starting from full potion
	 * to 1 dose).
	 * @param skillBonus The skill bonus.
	 *//*

	private
	ConsumableEffect(final Effect effect) {
		this.effect = effect;
	}

	*/
/**
	 * Represents the potion effect.
	 *//*

	private final Effect effect;

	*/
/**
	 * The potions mapping.
	 *//*

	private static Map<Integer, ConsumableEffect> potions = new HashMap<>();

	*/
/**
	 * Populate the potions mapping.
	 *//*

	static {
		for (ConsumableEffect p : ConsumableEffect.values())
			for (int id : p.effect.getItemIds()) {
				potions.put(id, p);
			}
	}

	*/
/**
	 * @return the name.
	 *//*

	public String getName() {
		return effect.getName();
	}

	*/
/**
	 * @return the itemIds.
	 *//*

	public int[] getItemIds() {
		return effect.getItemIds();
	}

	*/
/**
	 * Gets the skill bones.
	 * @param player the player.
	 * @return the bonus.
	 *//*

	public SkillBonus getSkillBonus(final Player player) {
		return effect.getSkillBonus(player);
	}

	*/
/**
	 * @return the skillBonus.
	 *//*

	public SkillBonus getSkillBonus() {
		return effect.getSkillBonus();
	}

	*/
/**
	 * Getsd the effect.
	 * @return the effect.
	 *//*

	public Effect getEffect() {
		return effect;
	}

	*/
/**
	 * Get the potion for the given item id.
	 * @param itemId The item id.
	 * @return The potion.
	 *//*

	public static ConsumableEffect forId(int itemId) {
		return potions.get(itemId);
	}

	*/
/**
	 * Represents a potion effect.
	 * @author Emperor
	 * @author 'Vexia
	 *//*

	public static class Effect {

		*/
/**
		 * The name of the potion.
		 *//*

		private final String name;

		*/
/**
		 * The item ids.
		 *//*

		private final int[] itemIds;

		*/
/**
		 * The skill bonus.
		 *//*

		private final SkillBonus skillBonus;

		*/
/**
		 * Constructs a new {@code ConsumableEffect} {@code Object}.
		 * @param name the name.
		 * @param itemIds the item ids.
		 * @param skillBonus the bonus.
		 *//*

		public Effect(String name, int[] itemIds, SkillBonus skillBonus) {
			this.name = name;
			this.itemIds = itemIds;
			this.skillBonus = skillBonus;
		}

		*/
/**
		 * @return the name.
		 *//*

		public String getName() {
			return name;
		}

		*/
/**
		 * @return the itemIds.
		 *//*

		public int[] getItemIds() {
			return itemIds;
		}

		*/
/**
		 * Gets the skill bones.
		 * @param player the player.
		 * @return the bonus.
		 *//*

		public SkillBonus getSkillBonus(final Player player) {
			return skillBonus;
		}

		*/
/**
		 * @return the skillBonus.
		 *//*

		public SkillBonus getSkillBonus() {
			return skillBonus;
		}
	}
}*/
