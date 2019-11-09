package org.crandor.game.content.skill.member.thieving;

import org.crandor.game.content.global.ttrail.ClueLevel;
import org.crandor.game.content.global.ttrail.ClueScrollPlugin;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.game.node.entity.npc.drop.DropFrequency;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.item.ChanceItem;
import org.crandor.game.node.item.Item;
import org.crandor.tools.RandomFunction;

/**
 * Represents a pickpocket npc.
 * @author 'Vexia
 * @date 22/10/2013
 */
public enum Pickpocket {
	MAN(new int[] { 1, 2, 3, 4, 5, 6, 16, 24, 170, 3915 }, 1, new int[][] { { 995, 3 } }, 8, 1),
	FARMER(new int[] { 7, 1757, 1758 }, 10, new int[][] { { 995, 9 }, { 5318, 1 } }, 14.5, 1),
	FEMALE_HAM_MEMBER(new int[] { 1715 }, 15, new int[][] { { 995, 50 }, { 995, 100 }, { 995, 20 }, { 995, 3 }, { 590, 1 }, { 1511, 1 }, { 1621, 1 }, { 1623, 1 }, { 1625, 1 }, { 1269, 1 }, { 321, 1 }, { 2138, 1 }, { 4298, 1 }, { 4300, 1 }, { 4302, 1 }, { 4304, 1 }, { 4306, 1 }, { 4308, 1 }, { 697, 1 }, { 4310, 1 }, { 1267, 1 }, { 1353, 1 }, { 199, 1 }, { 453, 1 }, { 201, 1 }, { 203, 1 }, { 205, 1 }, { 688, 1 }, { 688, 1 }, { 688, 1 }, { 686, 1 }, { 688, 1 } }, 18.5, 1, "You attempt to pick the woman's pocket...", "You pick the woman's pocket.", "You fail to pick the woman's pocket.", "What do you think you're doing?"),
	MALE_HAM_MEMBER(new int[] { 1714 }, 20, new int[][] { { 590, 1 }, { 1621, 1 }, { 1623, 1 }, { 1625, 1 }, { 1269, 1 }, { 321, 1 }, { 2138, 1 }, { 4298, 1 }, { 4300, 1 }, { 4302, 1 }, { 4304, 1 }, { 4306, 1 }, { 4308, 1 }, { 4310, 1 }, { 1267, 1 }, { 1353, 1 }, { 199, 1 }, { 201, 1 }, { 203, 1 }, { 205, 1 }, { 686, 1 }, { 697, 1 }, { 453, 1 }, { 688, 1 }, { 688, 1 }, { 688, 1 }, { 688, 1 }, { 688, 1 }, { 314, 1 }, { 8866, 1 }, { 8867, 1 }, { 8868, 1 }, { 8869, 1 } }, 22.5, 3, "You attempt to pick the man's pocket...", "You pick the man's pocket.", "You fail to pick the man's pocket.", "What do you think you're doing?"),
	WARRIOR(new int[] { 15, 18 }, 25, new int[][] { { 995, 18 } }, 26, 2),
	ROGUE(new int[] { 187, 2267, 2268, 2269, 8122 }, 32, new int[][] { { 995, 25 }, { 995, 40 }, { 1993, 1 }, { 556, 2 }, { 1219, 1 }, { 1523, 1 }, { 1944 } }, 35.5, 2),
	CAVE_GOBLIN(new int[] { 5752, 5753, 5754, 5755, 5756, 5757, 5758, 5759, 5760, 5761, 5762, 5763, 5764, 5765, 5766, 5767, 5768 }, 36, new int[][] { { 995, 30, 1 }, { 590, 1 }, { 4522, 1 }, { 4544, 1 }, { 596, 1 }, { 1939, 1 }, { 441, 4, 1 }, { 441, 1 }, { 10981, 1 } }, 40, 2),
	MASTER_FARMER(new int[] { 2234, 2235 }, 38, new int[][] { { 5096, 1 }, { 5097, 1 }, { 5098, 1 }, { 5099, 1 }, { 5100, 1 }, { 5101, 1 }, { 5102, 1 }, { 5103, 1 }, { 5104, 1 }, { 5105, 1 }, { 5106, 1 }, { 5291, 1 }, { 5292, 1 }, { 5293, 1 }, { 5294, 1 }, { 5295, 1 }, { 5296, 1 }, { 5297, 1 }, { 5298, 1 }, { 5299, 1 }, { 5300, 1 }, { 5301, 1 }, { 5302, 1 }, { 5304, 1 }, { 5305, 1 }, { 5306, 1 }, { 5307, 1 }, { 5308, 1 }, { 5309, 1 }, { 5310, 1 }, { 5311, 1 }, { 5312, 1 }, { 5318, 1 }, { 5319, 1 }, { 5320, 1 }, { 5321, 1 }, { 5322, 1 }, { 5323, 1 }, { 5324, 1 }, { 5296, 1 }, { 5297, 1 }, { 5298, 1 }, { 5299, 1 }, { 5300, 1 }, { 5301, 1 }, { 5302, 1 }, { 5303, 1 }, { 5304, 1 } }, 43, 3, "You attempt to pick the " + "@name" + "'s pocket...", "You pick the " + "@name" + "'s pocket.", "You fail to pick the " + "@name" + "'s pocket.", "Cor blimey mate, what are ye doing in me pockets?"),
	GUARD(new int[] { 9, 32, 206, 296, 297, 298, 299, 344, 345, 346, 368, 678, 812, 9, 32, 296, 297, 298, 299, 2699, 2700, 2701, 2702, 2703, 3228, 3229, 3230, 3231, 3232, 3233, 3241, 3407, 3408, 4307, 4308, 4309, 4310, 4311, 5919, 5920, }, 40, new int[][] { { 995, 30 }, { 995, 25 } }, 46.5, 2),
	FREMENIK_CITIZEN(new int[] { 2462 }, 45, new int[][] { { 995, 40 } }, 65, 2),
	BEARDED_BANDIT(new int[] { 1880, 1881, 6174 }, 45, new int[][] { { 995, 40 } }, 65, 5),
	DESERT_BANDIT(new int[] { 1926, 1921 }, 53, new int[][] { { 995, 30 }, { 995, 30 } }, 79.5, 3),
	KNIGHT_OF_ADROUGNE(new int[] { 23, 26 }, 55, new int[][] { { 995, 50 } }, 84.3, 3),
	YANILLE_WATCHMAN(new int[] { 34 }, 65, new int[][] { { 995, 60 } }, 137.5, 5),
	MENAPHITE_THUG(new int[] { 1905 }, 65, new int[][] { { 995, 60 } }, 137.5, 5),
	PALADIN(new int[] { 20, 2256 }, 70, new int[][] { { 995, 80 }, { 562, 2 } }, 151.75, 3),
	MONKEY_KNIFE_FIGHTER(new int[] { 13195, 13212, 13213 }, 70, new int[][] { { 995, 1, 1 }, { 995, 50, 1 }, { 869, 4, 1 }, { 874, 2, 1 }, { 379, 1 }, { 1331, 1 }, { 1333, 1 }, { 4587, 1 } }, 150, 1),
	GNOME(new int[] { 66, 67, 68, 168, 169, 2249, 2250, 2251, 2371, 2649, 2650, 6002, 6004 }, 75, new int[][] { { 995, 300, 1 }, { 557, 1 }, { 444, 1 }, { 569, 1 }, { 2150, 1 }, { 2162, 1 } }, 198.5, 1),
	HERO(new int[] { 21 }, 80, new int[][] { { 995, 200, 1 }, { 995, 300, 1 }, { 560, 2, 1 }, { 565, 1 }, { 569, 1 }, { 1601, 1 }, { 444, 1 }, { 1993, 1 } }, 273.3, 5),
	ELF(new int[] {}, 85, new int[][] { { 995, 250 }, { 995, 350 }, { 995, 300 } }, 353, 5),
	DWARF_TRADER(new int[] { 2109, 2110, 2111, 2112, 2113, 2114, 2115, 2116, 2117, 2118, 2119, 2120, 2121, 2122, 2123, 2124, 2125, 2126 }, 90, new int[][] { { 995, 100, 1 }, { 995, 400, 1 }, { 2350, 1 }, { 2352, 1 }, { 2354, 1 }, { 2360, 1 }, { 2362, 1 }, { 2364, 1 }, { 437, 1 }, { 439, 1 }, { 441, 1 }, { 448, 1 }, { 450, 1 }, { 452, 1 }, { 454, 1 } }, 556.5, 1),
	MARTIN_THE_MASTER_GARDENER(new int[] { 3299 }, 38, new int[][] { new int[] { 5318, 1, 2, 3, 4 }, new int[] { 5319, 1, 2, 3 }, new int[] { 5324, 1, 2, 3 }, new int[] { 5322, 1, 2 }, new int[] { 5320, 1 }, new int[] { 5323, 1 }, new int[] { 5321, 1 }, new int[] { 5096, 1 }, new int[] { 5097, 1 }, new int[] { 5099, 1, 2 }, new int[] { 5100, 1 }, new int[] { 5308, 1, 2 }, new int[] { 5306, 1, 2, 3 }, new int[] { 5101, 1 }, new int[] { 5102, 1 }, new int[] { 5103, 1 }, new int[] { 5104, 1, 2 }, new int[] { 5105, 1 }, new int[] { 5106, 1 }, new int[] { 5291, 1 }, new int[] { 5292, 1 }, new int[] { 5293, 1 }, new int[] { 5294, 1 }, new int[] { 5295, 1 } }, 43, 3, "You attempt to pick Martin's pocket.", "You pick Martin's pocket.", "You fail to pick Martin's pocket.", "Cor blimey mate, what are ye doing in me pockets?");

	/**
	 * The martin rewards.
	 */
	private static final ChanceItem[] MARTIN_REWARDS = new ChanceItem[] { new ChanceItem(5318, 1, 4, DropFrequency.COMMON), new ChanceItem(5319, 1, 3, DropFrequency.COMMON), new ChanceItem(5324, 1, 3, DropFrequency.COMMON), new ChanceItem(5322, 1, 2, DropFrequency.COMMON), new ChanceItem(5320, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(5323, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(5321, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(5096, 1, 1, DropFrequency.COMMON), new ChanceItem(5097, 1, 1, DropFrequency.COMMON), new ChanceItem(5098, 1, 1, DropFrequency.COMMON), new ChanceItem(5099, 1, 2, DropFrequency.COMMON), new ChanceItem(5100, 1, 1, DropFrequency.COMMON), new ChanceItem(5305, 1, 4, DropFrequency.COMMON), new ChanceItem(5307, 1, 3, DropFrequency.COMMON), new ChanceItem(5308, 1, 2, DropFrequency.COMMON), new ChanceItem(5306, 1, 3, DropFrequency.COMMON), new ChanceItem(5319, 1, 3, DropFrequency.COMMON), new ChanceItem(5309, 1, 2, DropFrequency.COMMON), new ChanceItem(5310, 1, 1, DropFrequency.COMMON), new ChanceItem(5311, 1, 1, DropFrequency.COMMON), new ChanceItem(5101, 1, 1, DropFrequency.COMMON), new ChanceItem(5102, 1, 1, DropFrequency.COMMON), new ChanceItem(5103, 1, 1, DropFrequency.COMMON), new ChanceItem(5104, 1, 2, DropFrequency.COMMON), new ChanceItem(5105, 1, 1, DropFrequency.COMMON), new ChanceItem(5106, 1, 1, DropFrequency.COMMON), new ChanceItem(5291, 1, 1, DropFrequency.COMMON), new ChanceItem(5292, 1, 1, DropFrequency.COMMON), new ChanceItem(5293, 1, 1, DropFrequency.COMMON), new ChanceItem(5294, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(5295, 1, 1, DropFrequency.RARE), new ChanceItem(5296, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(5297, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(5298, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(5299, 1, 1, DropFrequency.RARE), new ChanceItem(5300, 1, 1, DropFrequency.VERY_RARE), new ChanceItem(5301, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(5302, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(5303, 1, 1, DropFrequency.RARE), new ChanceItem(5304, 1, 1, DropFrequency.VERY_RARE), new ChanceItem(5282, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(5280, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(5281, 1, 1, DropFrequency.UNCOMMON) };

	/**
	 * The npcs related to this pick-pocket constant.
	 */
	private final int[] npc;

	/**
	 * The required level to pick-pocket this npc.
	 */
	private final int level;

	/**
	 * The loot you can recieve.
	 */
	private final int[][] loot;

	/**
	 * The amount of experienced rewarded on a succesfull thieve.
	 */
	private final double experience;

	/**
	 * Represents the stun damage.
	 */
	private final int stunDamage;

	/**
	 * Represents the messagee when pickpocketing.
	 */
	private final String[] messages;

	/**
	 * Constructs a new {@code Pickpocket} {@code Object}.
	 * @param npc the npc.
	 * @param level the level.
	 * @param loot the loot.
	 * @param experience the experience.
	 */
	Pickpocket(final int[] npc, final int level, final int[][] loot, final double experience, final int damage, final String... messages) {
		this.npc = npc;
		this.level = level;
		this.loot = loot;
		this.experience = experience;
		this.messages = messages == null || messages.length == 0 ? new String[] { "You attempt to pick the " + "@name" + "'s pocket...", "You pick the " + "@name" + "'s pocket.", "You fail to pick the " + "@name" + "'s pocket.", "What do you think you're doing?" } : messages;
		this.stunDamage = damage;
	}

	/**
	 * Gets the npc.
	 * @return The npc.
	 */
	public int[] getNpc() {
		return npc;
	}

	/**
	 * Gets the level.
	 * @return The level.
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * Gets the loot.
	 * @return The loot.
	 */
	public int[][] getLoot() {
		return loot;
	}

	/**
	 * Gets the experience.
	 * @return The experience.
	 */
	public double getExperience() {
		return experience;
	}

	/**
	 * Gets the start message.
	 * @return the message.
	 */
	public String getStartMessage() {
		return messages[0];
	}

	/**
	 * Gets the reward message.
	 * @return the message.
	 */
	public String getRewardMessage() {
		return messages[1];
	}

	/**
	 * Gets the fail message.
	 * @return the message.
	 */
	public String getFailMessage() {
		return messages[2];
	}

	/**
	 * Gets the force message.
	 * @return the message.
	 */
	public String getForceMessage() {
		return messages[3];
	}

	/**
	 * Gets a random loot.
	 * @return {@code Item} loot.
	 */
	public Item getRandomLoot(Player player) {
		if (this == MARTIN_THE_MASTER_GARDENER) {
			return RandomFunction.getChanceItem(MARTIN_REWARDS).getRandomItem();
		}
		// 5/250 chance for easy clue scroll?
		if ((this == FEMALE_HAM_MEMBER || this == MALE_HAM_MEMBER) && RandomFunction.random(250) <= 5 && !player.getTreasureTrailManager().hasClue()) {
			return ClueScrollPlugin.getClue(ClueLevel.EASY);
		}
		int[] loot = getLoot()[RandomFunction.random(getLoot().length)];
		if (loot.length == 1) {
			loot = new int[] { loot[0], 1 };
		}
		

		// Calculate any bonus multipliers
		int bonusMultiplier = 1;
		return new Item(loot[0], loot[1] * bonusMultiplier);
	}

	/**
	 * Gets the pickpocket type for the npc.
	 * @param npc the npc.
	 * @return the <code>Picketpocket</code>
	 */
	public static Pickpocket forNPC(final NPC npc) {
		for (Pickpocket pocket : Pickpocket.values()) {
			for (int i : pocket.getNpc()) {
				if (npc.getId() == i) {
					return pocket;
				}
			}
		}
		return MAN;
	}

	/**
	 * Gets the stunDamage.
	 * @return The stunDamage.
	 */
	public int getStunDamage() {
		return stunDamage;
	}
}