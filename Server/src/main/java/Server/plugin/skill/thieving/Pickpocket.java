package plugin.skill.thieving;

import plugin.ttrail.ClueLevel;
import plugin.ttrail.ClueScrollPlugin;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.npc.drop.DropFrequency;
import core.game.node.entity.player.Player;
import core.game.node.item.ChanceItem;
import core.game.node.item.Item;
import core.tools.RandomFunction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Represents a pickpocket npc.
 * @author Ceikry
 */
public enum Pickpocket {
	MAN(new int[] { 1, 2, 3, 4, 5, 6, 16, 24, 170, 3915 }, 1, 8, 1,new ChanceItem[] {
					new ChanceItem(995, 3,  DropFrequency.ALWAYS)
	}),
	FARMER(new int[] { 7, 1757, 1758 }, 10, 14.5, 1, new ChanceItem[] {
					new ChanceItem(995,  9, DropFrequency.COMMON),
					new ChanceItem(5318, 1, DropFrequency.UNCOMMON)
	}),
	MALE_HAM_MEMBER(new int[] { 1714 }, 20, 18.5, 1, new ChanceItem[] {
			new ChanceItem(995, 20, DropFrequency.COMMON),
			new ChanceItem(995, 3,  DropFrequency.COMMON),
			new ChanceItem(590, 1,  DropFrequency.COMMON),
			new ChanceItem(697, 1,  DropFrequency.COMMON),
			new ChanceItem(1511,1,  DropFrequency.COMMON),
			new ChanceItem(201, 1,  DropFrequency.COMMON),
			new ChanceItem(203, 1,  DropFrequency.COMMON),
			new ChanceItem(205, 1,  DropFrequency.COMMON),
			new ChanceItem(688, 1,  DropFrequency.COMMON),
			new ChanceItem(1269,1,  DropFrequency.COMMON),
			new ChanceItem(1267,1,  DropFrequency.COMMON),
			new ChanceItem(1353,1,  DropFrequency.COMMON),
			new ChanceItem(199, 1,  DropFrequency.COMMON),
			new ChanceItem(321, 1,  DropFrequency.COMMON),
			new ChanceItem(2138,1,  DropFrequency.COMMON),
			new ChanceItem(1621,1,  DropFrequency.UNCOMMON),
			new ChanceItem(1623,1,  DropFrequency.UNCOMMON),
			new ChanceItem(995, 50, DropFrequency.UNCOMMON),
			new ChanceItem(453, 1,  DropFrequency.UNCOMMON),
			new ChanceItem(4298,1,  DropFrequency.RARE),
			new ChanceItem(4300,1,  DropFrequency.RARE),
			new ChanceItem(4302,1,  DropFrequency.RARE),
			new ChanceItem(4304,1,  DropFrequency.RARE),
			new ChanceItem(4306,1,  DropFrequency.RARE),
			new ChanceItem(4308,1,  DropFrequency.RARE),
			new ChanceItem(995, 100,DropFrequency.RARE),
			new ChanceItem(4310,1,  DropFrequency.RARE),
			new ChanceItem(1625,1,  DropFrequency.RARE)
	}),
	FEMALE_HAM_MEMBER(new int[] { 1715 }, 15, 18.5, 1, new ChanceItem[] {
					new ChanceItem(995, 20, DropFrequency.COMMON),
					new ChanceItem(995, 3,  DropFrequency.COMMON),
					new ChanceItem(590, 1,  DropFrequency.COMMON),
					new ChanceItem(697, 1,  DropFrequency.COMMON),
					new ChanceItem(1511,1,  DropFrequency.COMMON),
					new ChanceItem(201, 1,  DropFrequency.COMMON),
					new ChanceItem(203, 1,  DropFrequency.COMMON),
					new ChanceItem(205, 1,  DropFrequency.COMMON),
					new ChanceItem(688, 1,  DropFrequency.COMMON),
					new ChanceItem(1269,1,  DropFrequency.COMMON),
					new ChanceItem(1267,1,  DropFrequency.COMMON),
					new ChanceItem(1353,1,  DropFrequency.COMMON),
					new ChanceItem(199, 1,  DropFrequency.COMMON),
					new ChanceItem(321, 1,  DropFrequency.COMMON),
					new ChanceItem(2138,1,  DropFrequency.COMMON),
					new ChanceItem(1621,1,  DropFrequency.UNCOMMON),
					new ChanceItem(1623,1,  DropFrequency.UNCOMMON),
					new ChanceItem(995, 50, DropFrequency.UNCOMMON),
					new ChanceItem(453, 1,  DropFrequency.UNCOMMON),
					new ChanceItem(4298,1,  DropFrequency.RARE),
					new ChanceItem(4300,1,  DropFrequency.RARE),
					new ChanceItem(4302,1,  DropFrequency.RARE),
					new ChanceItem(4304,1,  DropFrequency.RARE),
					new ChanceItem(4306,1,  DropFrequency.RARE),
					new ChanceItem(4308,1,  DropFrequency.RARE),
					new ChanceItem(995, 100,DropFrequency.RARE),
					new ChanceItem(4310,1,  DropFrequency.RARE),
					new ChanceItem(1625,1,  DropFrequency.RARE)
	}),
	WARRIOR(new int[] {15, 18}, 25, 26, 2, new ChanceItem[] {
		 			new ChanceItem(995, 18, DropFrequency.ALWAYS)
	}),
	ROGUE(new int[] { 187, 2267, 2268, 2269, 8122 }, 32, 35.5, 2, new ChanceItem[]{
					new ChanceItem(995, 25, DropFrequency.COMMON),
					new ChanceItem(995, 40, DropFrequency.COMMON),
					new ChanceItem(1993, 1, DropFrequency.COMMON),
					new ChanceItem(556, 1, DropFrequency.COMMON),
					new ChanceItem(1219, 1, DropFrequency.COMMON),
					new ChanceItem(1523, 1, DropFrequency.COMMON),
					new ChanceItem(1944, 1, DropFrequency.COMMON)
	}),
	CAVE_GOBLIN(IntStream.rangeClosed(5752,5768).toArray(), 36, 40, 2, new ChanceItem[]{
					new ChanceItem(995,1,30,DropFrequency.COMMON),
					new ChanceItem(4522, 1, DropFrequency.UNCOMMON),
					new ChanceItem(4544, 1, DropFrequency.UNCOMMON),
					new ChanceItem(596, 1, DropFrequency.COMMON),
					new ChanceItem(1939,1,DropFrequency.UNCOMMON),
					new ChanceItem(442,1,4, DropFrequency.UNCOMMON),
					new ChanceItem(441,1,DropFrequency.COMMON),
					new ChanceItem(10981, 1, DropFrequency.RARE)
	}),
	MASTER_FARMER(new int[] { 2234, 2235 }, 38, 43, 3, new ChanceItem[]{
					new ChanceItem(5096,1,DropFrequency.COMMON),
					new ChanceItem(5097,1,DropFrequency.COMMON),
					new ChanceItem(5098,1,DropFrequency.COMMON),
					new ChanceItem(5099,1,DropFrequency.COMMON),
					new ChanceItem(5100,1,DropFrequency.COMMON),
					new ChanceItem(5101,1,DropFrequency.COMMON),
					new ChanceItem(5102,1,DropFrequency.COMMON),
					new ChanceItem(5103,1,DropFrequency.COMMON),
					new ChanceItem(5104,1,DropFrequency.COMMON),
					new ChanceItem(5105,1,DropFrequency.COMMON),
					new ChanceItem(5106,1,DropFrequency.COMMON),
					new ChanceItem(5291,1,DropFrequency.UNCOMMON),
					new ChanceItem(5292,1,DropFrequency.UNCOMMON),
					new ChanceItem(5293,1,DropFrequency.UNCOMMON),
					new ChanceItem(5294,1,DropFrequency.UNCOMMON),
					new ChanceItem(5295,1,DropFrequency.UNCOMMON),
					new ChanceItem(5296,1,DropFrequency.UNCOMMON),
					new ChanceItem(5297,1,DropFrequency.UNCOMMON),
					new ChanceItem(5298,1,DropFrequency.UNCOMMON),
					new ChanceItem(5299,1,DropFrequency.UNCOMMON),
					new ChanceItem(5300,1,DropFrequency.UNCOMMON),
					new ChanceItem(5301,1,DropFrequency.UNCOMMON),
					new ChanceItem(5302,1,DropFrequency.UNCOMMON),
					new ChanceItem(5303,1,DropFrequency.UNCOMMON),
					new ChanceItem(5304,1,DropFrequency.UNCOMMON)
	}),
	GUARD(new int[] { 9, 32, 206, 296, 297, 298, 299, 344, 345, 346, 368, 678, 812, 9, 32, 296, 297, 298, 299, 2699, 2700, 2701, 2702, 2703, 3228, 3229, 3230, 3231, 3232, 3233, 3241, 3407, 3408, 4307, 4308, 4309, 4310, 4311, 5919, 5920, }, 40, 46.5,2, new ChanceItem[]{
					new ChanceItem(995,30,DropFrequency.ALWAYS)
	}),
	FREMENIK_CITIZEN(new int[] { 2462 }, 45, 65, 2, new ChanceItem[] {
					new ChanceItem(995, 40,DropFrequency.ALWAYS)
	}),
	BEARDED_BANDIT(new int[] { 1880, 1881, 6174 }, 45, 65, 5, new ChanceItem[] {
					new ChanceItem(995, 40,DropFrequency.ALWAYS)
	}),
	DESERT_BANDIT(new int[] { 1926, 1921 }, 53, 79.5, 3, new ChanceItem[] {
					new ChanceItem(995, 30, DropFrequency.ALWAYS)
	}),
	KNIGHT_OF_ADROUGNE(new int[] { 23, 26 }, 55, 84.3, 3, new ChanceItem[] {
					new ChanceItem(995, 50, DropFrequency.ALWAYS)
	}),
	YANILLE_WATCHMAN(new int[] { 34 }, 65, 137.5, 5, new ChanceItem[] {
					new ChanceItem(995, 60,DropFrequency.ALWAYS )
	}),
	MENAPHITE_THUG(new int[] { 1905 }, 65, 137.5, 5, new ChanceItem[] {
					new ChanceItem(995, 60, DropFrequency.ALWAYS)
	}),
	PALADIN(new int[] { 20, 2256 }, 70, 151.75, 3, new ChanceItem[]{
					new ChanceItem(995, 80, DropFrequency.ALWAYS),
					new ChanceItem(562, 1, 2, DropFrequency.ALWAYS)
	}),
	MONKEY_KNIFE_FIGHTER(new int[] { 13195, 13212, 13213 }, 70, 150, 1, new ChanceItem[] {
					new ChanceItem(995, 1, DropFrequency.COMMON),
					new ChanceItem(995, 50, DropFrequency.UNCOMMON),
					new ChanceItem(869,1, 4, DropFrequency.COMMON),
					new ChanceItem(874, 1, 2, DropFrequency.COMMON),
					new ChanceItem(379, 1, DropFrequency.COMMON),
					new ChanceItem(1331, 1, DropFrequency.COMMON),
					new ChanceItem(1333, 1,DropFrequency.COMMON),
					new ChanceItem(4587, 1, DropFrequency.COMMON)
	}),
	GNOME(new int[] { 66, 67, 68, 168, 169, 2249, 2250, 2251, 2371, 2649, 2650, 6002, 6004 }, 75, 198.5, 1, new ChanceItem[] {
					new ChanceItem(995, 1, 300, DropFrequency.COMMON),
					new ChanceItem(557, 1, DropFrequency.COMMON),
					new ChanceItem(444, 1, DropFrequency.COMMON),
					new ChanceItem(569, 1, DropFrequency.COMMON),
					new ChanceItem(2150, 1, DropFrequency.COMMON),
					new ChanceItem(2162, 1,DropFrequency.COMMON)
	}),
	HERO(new int[] { 21 }, 80, 273.3, 5, new ChanceItem[] {
					new ChanceItem(995, 1, 200, DropFrequency.COMMON),
					new ChanceItem(995,1,  300, DropFrequency.UNCOMMON),
					new ChanceItem(560, 1, 2, DropFrequency.COMMON),
					new ChanceItem(565, 1, DropFrequency.COMMON),
					new ChanceItem(569, 1, DropFrequency.COMMON),
					new ChanceItem(1601, 1, DropFrequency.COMMON),
					new ChanceItem(444, 1, DropFrequency.COMMON),
					new ChanceItem(1993, 1, DropFrequency.COMMON)
	}),
	ELF(new int[] {}, 85, 353, 5, new ChanceItem[] {
					new ChanceItem(995, 250, DropFrequency.COMMON),
					new ChanceItem(995, 350, DropFrequency.COMMON),
					new ChanceItem(995, 300, DropFrequency.COMMON)
	}),
	DWARF_TRADER(new int[] { 2109, 2110, 2111, 2112, 2113, 2114, 2115, 2116, 2117, 2118, 2119, 2120, 2121, 2122, 2123, 2124, 2125, 2126 }, 90, 556.5, 1, new ChanceItem[] {
					new ChanceItem(995,1, 100,DropFrequency.COMMON),
					new ChanceItem(995,1, 400, DropFrequency.COMMON),
					new ChanceItem(2350, 1,DropFrequency.COMMON),
					new ChanceItem(2352, 1,DropFrequency.COMMON),
					new ChanceItem(2354, 1,DropFrequency.COMMON),
					new ChanceItem(2360, 1,DropFrequency.COMMON),
					new ChanceItem(2362, 1,DropFrequency.COMMON),
					new ChanceItem(2364, 1,DropFrequency.COMMON),
					new ChanceItem(437, 1,DropFrequency.COMMON),
					new ChanceItem(439, 1,DropFrequency.COMMON),
					new ChanceItem(441, 1,DropFrequency.COMMON),
					new ChanceItem(448, 1,DropFrequency.COMMON),
					new ChanceItem(450, 1,DropFrequency.COMMON),
					new ChanceItem(452, 1,DropFrequency.COMMON),
					new ChanceItem(454, 1,DropFrequency.COMMON)
	}),
	MARTIN_THE_MASTER_GARDENER(new int[] { 3299 }, 38, 43, 3, 	new ChanceItem[] {
					new ChanceItem(5318, 1, 4, DropFrequency.COMMON),
					new ChanceItem(5319, 1, 3, DropFrequency.COMMON),
					new ChanceItem(5324, 1, 3, DropFrequency.COMMON),
					new ChanceItem(5322, 1, 2, DropFrequency.COMMON),
					new ChanceItem(5320, 1, 1, DropFrequency.UNCOMMON),
					new ChanceItem(5323, 1, 1, DropFrequency.UNCOMMON),
					new ChanceItem(5321, 1, 1, DropFrequency.UNCOMMON),
					new ChanceItem(5096, 1, 1, DropFrequency.COMMON),
					new ChanceItem(5097, 1, 1, DropFrequency.COMMON),
					new ChanceItem(5098, 1, 1, DropFrequency.COMMON),
					new ChanceItem(5099, 1, 2, DropFrequency.COMMON),
					new ChanceItem(5100, 1, 1, DropFrequency.COMMON),
					new ChanceItem(5305, 1, 4, DropFrequency.COMMON),
					new ChanceItem(5307, 1, 3, DropFrequency.COMMON),
					new ChanceItem(5308, 1, 2, DropFrequency.COMMON),
					new ChanceItem(5306, 1, 3, DropFrequency.COMMON),
					new ChanceItem(5319, 1, 3, DropFrequency.COMMON),
					new ChanceItem(5309, 1, 2, DropFrequency.COMMON),
					new ChanceItem(5310, 1, 1, DropFrequency.COMMON),
					new ChanceItem(5311, 1, 1, DropFrequency.COMMON),
					new ChanceItem(5101, 1, 1, DropFrequency.COMMON),
					new ChanceItem(5102, 1, 1, DropFrequency.COMMON),
					new ChanceItem(5103, 1, 1, DropFrequency.COMMON),
					new ChanceItem(5104, 1, 2, DropFrequency.COMMON),
					new ChanceItem(5105, 1, 1, DropFrequency.COMMON),
					new ChanceItem(5106, 1, 1, DropFrequency.COMMON),
					new ChanceItem(5291, 1, 1, DropFrequency.COMMON),
					new ChanceItem(5292, 1, 1, DropFrequency.COMMON),
					new ChanceItem(5293, 1, 1, DropFrequency.COMMON),
					new ChanceItem(5294, 1, 1, DropFrequency.UNCOMMON),
					new ChanceItem(5295, 1, 1, DropFrequency.RARE),
					new ChanceItem(5296, 1, 1, DropFrequency.UNCOMMON),
					new ChanceItem(5297, 1, 1, DropFrequency.UNCOMMON),
					new ChanceItem(5298, 1, 1, DropFrequency.UNCOMMON),
					new ChanceItem(5299, 1, 1, DropFrequency.RARE),
					new ChanceItem(5300, 1, 1, DropFrequency.VERY_RARE),
					new ChanceItem(5301, 1, 1, DropFrequency.UNCOMMON),
					new ChanceItem(5302, 1, 1, DropFrequency.UNCOMMON),
					new ChanceItem(5303, 1, 1, DropFrequency.RARE),
					new ChanceItem(5304, 1, 1, DropFrequency.VERY_RARE),
					new ChanceItem(5282, 1, 1, DropFrequency.UNCOMMON),
					new ChanceItem(5280, 1, 1, DropFrequency.UNCOMMON),
					new ChanceItem(5281, 1, 1, DropFrequency.UNCOMMON)
	});

	private static HashMap<Integer, Pickpocket> idMap = new HashMap<>();
	static{
		Arrays.stream(Pickpocket.values()).forEach(entry -> Arrays.stream(entry.npc).forEach(id -> idMap.putIfAbsent(id,entry)));
	}


	private final int[] npc;
	private final int level;
	private final ChanceItem[] loot;
	private final double experience;
	private final int stunDamage;
	private final String[] messages;


	Pickpocket(final int[] npc, final int level, final double experience, final int damage, final ChanceItem[] loot){
		this.npc = npc;
		this.level = level;
		this.experience = experience;
		this.stunDamage = damage;
		this.loot = loot;
		this.messages = new String[] { "You attempt to pick the " + "@name" + "'s pocket...", "You pick the " + "@name" + "'s pocket.", "You fail to pick the " + "@name" + "'s pocket.", "What do you think you're doing?" };
	}

	public int[] getNpc() {
		return npc;
	}

	public int getLevel() {
		return level;
	}

	public ChanceItem[] getLoot() {
		return loot;
	}

	public double getExperience() {
		return experience;
	}

	public String getStartMessage() {
		return messages[0];
	}

	public String getRewardMessage() {
		return messages[1];
	}

	public String getFailMessage() {
		return messages[2];
	}

	public String getForceMessage() {
		return messages[3];
	}

	public List<Item> getRandomLoot(Player player) {
		// 5/250 chance for easy clue scroll
		if ((this == MALE_HAM_MEMBER || this == FEMALE_HAM_MEMBER) && RandomFunction.random(250) <= 5 && !player.getTreasureTrailManager().hasClue()) {
			return new ArrayList<Item>(Arrays.asList(new Item[]{ClueScrollPlugin.getClue(ClueLevel.EASY)}));
		}
		List<Item> loot = RandomFunction.rollChanceTable(true,getLoot());

		// Calculate any bonus multipliers
		int bonusMultiplier = 1;
		/**
		 * TODO: ADD IN STUFF HERE FOR ROGUE'S SET
		 */
		loot.stream().forEach(item -> item.setAmount(item.getAmount() * bonusMultiplier));
		return loot;
	}

	public static Pickpocket forNPC(final NPC npc) {
		Pickpocket pp = idMap.get(npc.getId());
		if(pp == null) {
			System.out.println("Unhandled pickpocket option for: " + npc.getName() + "(" + npc.getId() + ")");
			pp = MAN;
		}
		return pp;
	}

	public int getStunDamage() {
		return stunDamage;
	}
}
