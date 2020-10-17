package plugin.skill.thieving

import core.game.node.entity.npc.NPC
import core.game.node.entity.npc.drop.DropFrequency
import core.game.node.entity.player.Player
import core.game.node.item.ChanceItem
import core.game.node.item.Item
import core.tools.RandomFunction
import plugin.ttrail.ClueLevel
import plugin.ttrail.ClueScrollPlugin
import java.util.*
import java.util.stream.IntStream

/**
 * Represents a pickpocket npc.
 * @author Ceikry
 */
enum class Pickpocket(val npc: IntArray, val level: Int, val experience: Double, val stunDamage: Int, val loot: Array<ChanceItem>) {
    MAN(intArrayOf(1, 2, 3, 4, 5, 6, 16, 24, 170, 3915), 1, 8.0, 1, arrayOf<ChanceItem>(
            ChanceItem(995, 3, DropFrequency.ALWAYS)
    )),
    FARMER(intArrayOf(7, 1757, 1758), 10, 14.5, 1, arrayOf<ChanceItem>(
            ChanceItem(995, 9, DropFrequency.COMMON),
            ChanceItem(5318, 1, DropFrequency.UNCOMMON)
    )),
    MALE_HAM_MEMBER(intArrayOf(1714), 20, 18.5, 1, arrayOf<ChanceItem>(
            ChanceItem(995, 20, DropFrequency.COMMON),
            ChanceItem(995, 3, DropFrequency.COMMON),
            ChanceItem(590, 1, DropFrequency.COMMON),
            ChanceItem(697, 1, DropFrequency.COMMON),
            ChanceItem(1511, 1, DropFrequency.COMMON),
            ChanceItem(201, 1, DropFrequency.COMMON),
            ChanceItem(203, 1, DropFrequency.COMMON),
            ChanceItem(205, 1, DropFrequency.COMMON),
            ChanceItem(688, 1, DropFrequency.COMMON),
            ChanceItem(1269, 1, DropFrequency.COMMON),
            ChanceItem(1267, 1, DropFrequency.COMMON),
            ChanceItem(1353, 1, DropFrequency.COMMON),
            ChanceItem(199, 1, DropFrequency.COMMON),
            ChanceItem(321, 1, DropFrequency.COMMON),
            ChanceItem(2138, 1, DropFrequency.COMMON),
            ChanceItem(1621, 1, DropFrequency.UNCOMMON),
            ChanceItem(1623, 1, DropFrequency.UNCOMMON),
            ChanceItem(995, 50, DropFrequency.UNCOMMON),
            ChanceItem(453, 1, DropFrequency.UNCOMMON),
            ChanceItem(4298, 1, DropFrequency.RARE),
            ChanceItem(4300, 1, DropFrequency.RARE),
            ChanceItem(4302, 1, DropFrequency.RARE),
            ChanceItem(4304, 1, DropFrequency.RARE),
            ChanceItem(4306, 1, DropFrequency.RARE),
            ChanceItem(4308, 1, DropFrequency.RARE),
            ChanceItem(995, 100, DropFrequency.RARE),
            ChanceItem(4310, 1, DropFrequency.RARE),
            ChanceItem(1625, 1, DropFrequency.RARE)
    )),
    FEMALE_HAM_MEMBER(intArrayOf(1715), 15, 18.5, 1, arrayOf<ChanceItem>(
            ChanceItem(995, 20, DropFrequency.COMMON),
            ChanceItem(995, 3, DropFrequency.COMMON),
            ChanceItem(590, 1, DropFrequency.COMMON),
            ChanceItem(697, 1, DropFrequency.COMMON),
            ChanceItem(1511, 1, DropFrequency.COMMON),
            ChanceItem(201, 1, DropFrequency.COMMON),
            ChanceItem(203, 1, DropFrequency.COMMON),
            ChanceItem(205, 1, DropFrequency.COMMON),
            ChanceItem(688, 1, DropFrequency.COMMON),
            ChanceItem(1269, 1, DropFrequency.COMMON),
            ChanceItem(1267, 1, DropFrequency.COMMON),
            ChanceItem(1353, 1, DropFrequency.COMMON),
            ChanceItem(199, 1, DropFrequency.COMMON),
            ChanceItem(321, 1, DropFrequency.COMMON),
            ChanceItem(2138, 1, DropFrequency.COMMON),
            ChanceItem(1621, 1, DropFrequency.UNCOMMON),
            ChanceItem(1623, 1, DropFrequency.UNCOMMON),
            ChanceItem(995, 50, DropFrequency.UNCOMMON),
            ChanceItem(453, 1, DropFrequency.UNCOMMON),
            ChanceItem(4298, 1, DropFrequency.RARE),
            ChanceItem(4300, 1, DropFrequency.RARE),
            ChanceItem(4302, 1, DropFrequency.RARE),
            ChanceItem(4304, 1, DropFrequency.RARE),
            ChanceItem(4306, 1, DropFrequency.RARE),
            ChanceItem(4308, 1, DropFrequency.RARE),
            ChanceItem(995, 100, DropFrequency.RARE),
            ChanceItem(4310, 1, DropFrequency.RARE),
            ChanceItem(1625, 1, DropFrequency.RARE)
    )),
    WARRIOR(intArrayOf(15, 18), 25, 26.0, 2, arrayOf<ChanceItem>(
            ChanceItem(995, 18, DropFrequency.ALWAYS)
    )),
    ROGUE(intArrayOf(187, 2267, 2268, 2269, 8122), 32, 35.5, 2, arrayOf<ChanceItem>(
            ChanceItem(995, 25, DropFrequency.COMMON),
            ChanceItem(995, 40, DropFrequency.COMMON),
            ChanceItem(1993, 1, DropFrequency.COMMON),
            ChanceItem(556, 1, DropFrequency.COMMON),
            ChanceItem(1219, 1, DropFrequency.COMMON),
            ChanceItem(1523, 1, DropFrequency.COMMON),
            ChanceItem(1944, 1, DropFrequency.COMMON)
    )),
    CAVE_GOBLIN(IntStream.rangeClosed(5752, 5768).toArray(), 36, 40.0, 2, arrayOf<ChanceItem>(
            ChanceItem(995, 1, 30, DropFrequency.COMMON),
            ChanceItem(4522, 1, DropFrequency.UNCOMMON),
            ChanceItem(4544, 1, DropFrequency.UNCOMMON),
            ChanceItem(596, 1, DropFrequency.COMMON),
            ChanceItem(1939, 1, DropFrequency.UNCOMMON),
            ChanceItem(442, 1, 4, DropFrequency.UNCOMMON),
            ChanceItem(441, 1, DropFrequency.COMMON),
            ChanceItem(10981, 1, DropFrequency.RARE)
    )),
    MASTER_FARMER(intArrayOf(2234, 2235), 38, 43.0, 3, arrayOf<ChanceItem>(
            ChanceItem(5096, 1, DropFrequency.COMMON),
            ChanceItem(5097, 1, DropFrequency.COMMON),
            ChanceItem(5098, 1, DropFrequency.COMMON),
            ChanceItem(5099, 1, DropFrequency.COMMON),
            ChanceItem(5100, 1, DropFrequency.COMMON),
            ChanceItem(5101, 1, DropFrequency.COMMON),
            ChanceItem(5102, 1, DropFrequency.COMMON),
            ChanceItem(5103, 1, DropFrequency.COMMON),
            ChanceItem(5104, 1, DropFrequency.COMMON),
            ChanceItem(5105, 1, DropFrequency.COMMON),
            ChanceItem(5106, 1, DropFrequency.COMMON),
            ChanceItem(5291, 1, DropFrequency.UNCOMMON),
            ChanceItem(5292, 1, DropFrequency.UNCOMMON),
            ChanceItem(5293, 1, DropFrequency.UNCOMMON),
            ChanceItem(5294, 1, DropFrequency.UNCOMMON),
            ChanceItem(5295, 1, DropFrequency.UNCOMMON),
            ChanceItem(5296, 1, DropFrequency.UNCOMMON),
            ChanceItem(5297, 1, DropFrequency.UNCOMMON),
            ChanceItem(5298, 1, DropFrequency.UNCOMMON),
            ChanceItem(5299, 1, DropFrequency.UNCOMMON),
            ChanceItem(5300, 1, DropFrequency.UNCOMMON),
            ChanceItem(5301, 1, DropFrequency.UNCOMMON),
            ChanceItem(5302, 1, DropFrequency.UNCOMMON),
            ChanceItem(5303, 1, DropFrequency.UNCOMMON),
            ChanceItem(5304, 1, DropFrequency.UNCOMMON)
    )),
    GUARD(intArrayOf(9, 32, 206, 296, 297, 298, 299, 344, 345, 346, 368, 678, 812, 9, 32, 296, 297, 298, 299, 2699, 2700, 2701, 2702, 2703, 3228, 3229, 3230, 3231, 3232, 3233, 3241, 3407, 3408, 4307, 4308, 4309, 4310, 4311, 5919, 5920), 40, 46.5, 2, arrayOf<ChanceItem>(
            ChanceItem(995, 30, DropFrequency.ALWAYS)
    )),
    FREMENIK_CITIZEN(intArrayOf(2462), 45, 65.0, 2, arrayOf<ChanceItem>(
            ChanceItem(995, 40, DropFrequency.ALWAYS)
    )),
    BEARDED_BANDIT(intArrayOf(1880, 1881, 6174), 45, 65.0, 5, arrayOf<ChanceItem>(
            ChanceItem(995, 40, DropFrequency.ALWAYS)
    )),
    DESERT_BANDIT(intArrayOf(1926, 1921), 53, 79.5, 3, arrayOf<ChanceItem>(
            ChanceItem(995, 30, DropFrequency.ALWAYS)
    )),
    KNIGHT_OF_ADROUGNE(intArrayOf(23, 26), 55, 84.3, 3, arrayOf<ChanceItem>(
            ChanceItem(995, 50, DropFrequency.ALWAYS)
    )),
    YANILLE_WATCHMAN(intArrayOf(34), 65, 137.5, 5, arrayOf<ChanceItem>(
            ChanceItem(995, 60, DropFrequency.ALWAYS)
    )),
    MENAPHITE_THUG(intArrayOf(1905), 65, 137.5, 5, arrayOf<ChanceItem>(
            ChanceItem(995, 60, DropFrequency.ALWAYS)
    )),
    PALADIN(intArrayOf(20, 2256), 70, 151.75, 3, arrayOf<ChanceItem>(
            ChanceItem(995, 80, DropFrequency.ALWAYS),
            ChanceItem(562, 1, 2, DropFrequency.ALWAYS)
    )),
    MONKEY_KNIFE_FIGHTER(intArrayOf(13195, 13212, 13213), 70, 150.0, 1, arrayOf<ChanceItem>(
            ChanceItem(995, 1, DropFrequency.COMMON),
            ChanceItem(995, 50, DropFrequency.UNCOMMON),
            ChanceItem(869, 1, 4, DropFrequency.COMMON),
            ChanceItem(874, 1, 2, DropFrequency.COMMON),
            ChanceItem(379, 1, DropFrequency.COMMON),
            ChanceItem(1331, 1, DropFrequency.COMMON),
            ChanceItem(1333, 1, DropFrequency.COMMON),
            ChanceItem(4587, 1, DropFrequency.COMMON)
    )),
    GNOME(intArrayOf(66, 67, 68, 168, 169, 2249, 2250, 2251, 2371, 2649, 2650, 6002, 6004), 75, 198.5, 1, arrayOf<ChanceItem>(
            ChanceItem(995, 1, 300, DropFrequency.COMMON),
            ChanceItem(557, 1, DropFrequency.COMMON),
            ChanceItem(444, 1, DropFrequency.COMMON),
            ChanceItem(569, 1, DropFrequency.COMMON),
            ChanceItem(2150, 1, DropFrequency.COMMON),
            ChanceItem(2162, 1, DropFrequency.COMMON)
    )),
    HERO(intArrayOf(21), 80, 273.3, 5, arrayOf<ChanceItem>(
            ChanceItem(995, 1, 200, DropFrequency.COMMON),
            ChanceItem(995, 1, 300, DropFrequency.UNCOMMON),
            ChanceItem(560, 1, 2, DropFrequency.COMMON),
            ChanceItem(565, 1, DropFrequency.COMMON),
            ChanceItem(569, 1, DropFrequency.COMMON),
            ChanceItem(1601, 1, DropFrequency.COMMON),
            ChanceItem(444, 1, DropFrequency.COMMON),
            ChanceItem(1993, 1, DropFrequency.COMMON)
    )),
    ELF(intArrayOf(), 85, 353.0, 5, arrayOf<ChanceItem>(
            ChanceItem(995, 250, DropFrequency.COMMON),
            ChanceItem(995, 350, DropFrequency.COMMON),
            ChanceItem(995, 300, DropFrequency.COMMON)
    )),
    DWARF_TRADER(intArrayOf(2109, 2110, 2111, 2112, 2113, 2114, 2115, 2116, 2117, 2118, 2119, 2120, 2121, 2122, 2123, 2124, 2125, 2126), 90, 556.5, 1, arrayOf<ChanceItem>(
            ChanceItem(995, 1, 100, DropFrequency.COMMON),
            ChanceItem(995, 1, 400, DropFrequency.COMMON),
            ChanceItem(2350, 1, DropFrequency.COMMON),
            ChanceItem(2352, 1, DropFrequency.COMMON),
            ChanceItem(2354, 1, DropFrequency.COMMON),
            ChanceItem(2360, 1, DropFrequency.COMMON),
            ChanceItem(2362, 1, DropFrequency.COMMON),
            ChanceItem(2364, 1, DropFrequency.COMMON),
            ChanceItem(437, 1, DropFrequency.COMMON),
            ChanceItem(439, 1, DropFrequency.COMMON),
            ChanceItem(441, 1, DropFrequency.COMMON),
            ChanceItem(448, 1, DropFrequency.COMMON),
            ChanceItem(450, 1, DropFrequency.COMMON),
            ChanceItem(452, 1, DropFrequency.COMMON),
            ChanceItem(454, 1, DropFrequency.COMMON)
    )),
    MARTIN_THE_MASTER_GARDENER(intArrayOf(3299), 38, 43.0, 3, arrayOf<ChanceItem>(
            ChanceItem(5318, 1, 4, DropFrequency.COMMON),
            ChanceItem(5319, 1, 3, DropFrequency.COMMON),
            ChanceItem(5324, 1, 3, DropFrequency.COMMON),
            ChanceItem(5322, 1, 2, DropFrequency.COMMON),
            ChanceItem(5320, 1, 1, DropFrequency.UNCOMMON),
            ChanceItem(5323, 1, 1, DropFrequency.UNCOMMON),
            ChanceItem(5321, 1, 1, DropFrequency.UNCOMMON),
            ChanceItem(5096, 1, 1, DropFrequency.COMMON),
            ChanceItem(5097, 1, 1, DropFrequency.COMMON),
            ChanceItem(5098, 1, 1, DropFrequency.COMMON),
            ChanceItem(5099, 1, 2, DropFrequency.COMMON),
            ChanceItem(5100, 1, 1, DropFrequency.COMMON),
            ChanceItem(5305, 1, 4, DropFrequency.COMMON),
            ChanceItem(5307, 1, 3, DropFrequency.COMMON),
            ChanceItem(5308, 1, 2, DropFrequency.COMMON),
            ChanceItem(5306, 1, 3, DropFrequency.COMMON),
            ChanceItem(5319, 1, 3, DropFrequency.COMMON),
            ChanceItem(5309, 1, 2, DropFrequency.COMMON),
            ChanceItem(5310, 1, 1, DropFrequency.COMMON),
            ChanceItem(5311, 1, 1, DropFrequency.COMMON),
            ChanceItem(5101, 1, 1, DropFrequency.COMMON),
            ChanceItem(5102, 1, 1, DropFrequency.COMMON),
            ChanceItem(5103, 1, 1, DropFrequency.COMMON),
            ChanceItem(5104, 1, 2, DropFrequency.COMMON),
            ChanceItem(5105, 1, 1, DropFrequency.COMMON),
            ChanceItem(5106, 1, 1, DropFrequency.COMMON),
            ChanceItem(5291, 1, 1, DropFrequency.COMMON),
            ChanceItem(5292, 1, 1, DropFrequency.COMMON),
            ChanceItem(5293, 1, 1, DropFrequency.COMMON),
            ChanceItem(5294, 1, 1, DropFrequency.UNCOMMON),
            ChanceItem(5295, 1, 1, DropFrequency.RARE),
            ChanceItem(5296, 1, 1, DropFrequency.UNCOMMON),
            ChanceItem(5297, 1, 1, DropFrequency.UNCOMMON),
            ChanceItem(5298, 1, 1, DropFrequency.UNCOMMON),
            ChanceItem(5299, 1, 1, DropFrequency.RARE),
            ChanceItem(5300, 1, 1, DropFrequency.VERY_RARE),
            ChanceItem(5301, 1, 1, DropFrequency.UNCOMMON),
            ChanceItem(5302, 1, 1, DropFrequency.UNCOMMON),
            ChanceItem(5303, 1, 1, DropFrequency.RARE),
            ChanceItem(5304, 1, 1, DropFrequency.VERY_RARE),
            ChanceItem(5282, 1, 1, DropFrequency.UNCOMMON),
            ChanceItem(5280, 1, 1, DropFrequency.UNCOMMON),
            ChanceItem(5281, 1, 1, DropFrequency.UNCOMMON)
    ));

    companion object {
        private val idMap = HashMap<Int, Pickpocket>()
        @JvmStatic
		fun forNPC(npc: NPC): Pickpocket {
            var pp = idMap[npc.id]
            if (pp == null) {
                println("Unhandled pickpocket option for: " + npc.name + "(" + npc.id + ")")
                pp = MAN
            }
            return pp
        }

        init {
            Arrays.stream(values()).forEach{entry -> Arrays.stream(entry.npc).forEach{id -> idMap.putIfAbsent(id,entry)}}
        }
    }

    private val messages: Array<String> = arrayOf(
        "You attempt to pick the " + "@name" + "'s pocket...",
        "You pick the " + "@name" + "'s pocket.",
        "You fail to pick the " + "@name" + "'s pocket.",
        "What do you think you're doing?"
    )

    val startMessage: String
        get() = messages[0]

    val rewardMessage: String
        get() = messages[1]

    val failMessage: String
        get() = messages[2]

    val forceMessage: String
        get() = messages[3]

    fun getRandomLoot(player: Player): List<Item> {
        // 5/250 chance for easy clue scroll
        if ((this == MALE_HAM_MEMBER || this == FEMALE_HAM_MEMBER) && RandomFunction.random(250) <= 5 && !player.treasureTrailManager.hasClue()) {
            return ArrayList(listOf((ClueScrollPlugin.getClue(ClueLevel.EASY))))
        }
        val loot = RandomFunction.rollChanceTable(true, *loot)

        // Calculate any bonus multipliers
        var bonusMultiplier = 1
        var rogueItemsWorn = 0
        val rogueItemIds = intArrayOf(5553, 5554, 5555, 5556, 5557)

        // Figure out how many rogue equipment pieces are worn
        rogueItemIds.forEach {
            if(player.equipment.contains(it, 1)){
                rogueItemsWorn += 1
            }
        }

        // Calculate if the player will get double the loot for this pickpocket
        if(RandomFunction.random(0.0, 1.0) <= 0.2 * rogueItemsWorn){
            bonusMultiplier += 1
        }

        // Multiply the lot amounts by the bonus multiplier
        loot.stream().forEach { item: Item -> item.amount = item.amount * bonusMultiplier }
        return loot
    }

}