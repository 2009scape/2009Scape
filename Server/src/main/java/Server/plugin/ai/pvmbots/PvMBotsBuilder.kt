package plugin.ai.pvmbots

import core.game.container.impl.EquipmentContainer
import core.game.node.entity.combat.CombatSpell
import core.game.node.entity.player.link.SpellBookManager
import core.game.node.entity.player.link.appearance.Gender
import core.game.node.item.Item
import core.game.world.GameWorld
import core.game.world.map.Location
import core.tools.RandomFunction
import plugin.ai.AIPlayer
import plugin.ai.minigamebots.pestcontrol.PestControlTestBot
import plugin.ai.minigamebots.pestcontrol.PestControlTestBot2
import plugin.skill.Skills
import java.util.*
import java.util.concurrent.Executors

// import sun.util.resources.CalendarData;
class PvMBotsBuilder {
    fun randomItem() {
        var test: Item? = null
        val tests = ArrayList<Item?>()
        for (x in 0..9998) test = Item(x)
        if (test!!.definition.equipId != 0) tests.add(test)
    }

    companion object {
        var botsSpawned = 0

        fun create(l: Location?): PvMBots {
            botsSpawned++
            return PvMBots(l)
        }

        fun createLowest(l: Location?): LowestBot {
            botsSpawned++
            return LowestBot(l)
        }

        fun createNoob(l: Location?): NoobBot {
            botsSpawned++
            return NoobBot(l)
        }

        fun createDragonKiller(l: Location?): DragonKiller {
            botsSpawned++
            return DragonKiller(l)
        }

        fun createGiantMoleBot(l: Location?): GiantMoleBot {
            botsSpawned++
            return GiantMoleBot(l)
        }

        fun generateGiantMoleBot(p: PvMBots) {
            p.skills.setLevel(Skills.SLAYER, 99)
            p.skills.setStaticLevel(Skills.SLAYER, 99)
            p.skills.setLevel(Skills.STRENGTH, 99)
            p.skills.setStaticLevel(Skills.STRENGTH, 99)
            p.skills.setLevel(Skills.ATTACK, 99)
            p.skills.setStaticLevel(Skills.ATTACK, 99)
            p.skills.setStaticLevel(Skills.HITPOINTS, 99)
            p.skills.setLevel(Skills.HITPOINTS, 99)
            p.skills.setStaticLevel(Skills.DEFENCE, 99)
            p.skills.setLevel(Skills.DEFENCE, 99)
            p.skills.setStaticLevel(Skills.PRAYER, 99)
            p.skills.setLevel(Skills.PRAYER, 99)
            p.skills.updateCombatLevel()
            p.appearance.sync()
            p.equipment.replace(Item(4720), EquipmentContainer.SLOT_CHEST)
            p.equipment.replace(Item(4722), EquipmentContainer.SLOT_LEGS)
            p.equipment.replace(Item(4716), EquipmentContainer.SLOT_HAT)
            p.equipment.replace(Item(4718), EquipmentContainer.SLOT_WEAPON)
            p.equipment.replace(Item(-1), EquipmentContainer.SLOT_SHIELD)
            p.inventory.add(Item(952))
            p.inventory.add(Item(33))
        }

        fun generateMinLevels(p: PvMBots) {
            //Slayer so they can attack all monsters
            p.skills.setLevel(Skills.SLAYER, 99)
            p.skills.setStaticLevel(Skills.SLAYER, 99)
            val combatType = RandomFunction.getRandom(2)
            p.skills.setLevel(Skills.HITPOINTS, 15)
            p.skills.setStaticLevel(Skills.HITPOINTS, 15)
            p.inventory.add(Item(329, 5))
            when (combatType) {
                0 -> {
                    buildArcherStats(p)
                    buildArcherEquipment(p)
                }
                1 -> {
                    buildMeleeStats(p)
                    buildMeleeEquipment(p)
                }
                2 -> {
                    buildMageStats(p)
                    buildMagicEquipment(p)
                    setupWizard(p)
                }
                else -> {
                    buildMeleeStats(p)
                    buildMeleeEquipment(p)
                }
            }
        }

        fun createDragonKiller(p: DragonKiller) {
            p.skills.setLevel(Skills.SLAYER, 99)
            p.skills.setStaticLevel(Skills.SLAYER, 99)
            val combatType = RandomFunction.getRandom(2)
            p.skills.setLevel(Skills.HITPOINTS, 55)
            p.skills.setStaticLevel(Skills.HITPOINTS, 55)
            when (combatType) {
                0 -> {
                    buildDragonArcherStats(p)
                    buildDragonArcherEquipment(p)
                }
                1 -> {
                    buildDragonMeleeStats(p)
                    buildDragonMeleeEquipment(p)
                }
                else -> {
                    buildDragonMeleeStats(p)
                    buildDragonMeleeEquipment(p)
                }
            }
        }

        private fun buildMaxMeleeStats(p: AIPlayer) {
            p.skills.setLevel(Skills.ATTACK, 99)
            p.skills.setStaticLevel(Skills.ATTACK, 99)
            p.skills.setLevel(Skills.STRENGTH, 99)
            p.skills.setStaticLevel(Skills.STRENGTH, 99)
            p.skills.setLevel(Skills.DEFENCE, 99)
            p.skills.setStaticLevel(Skills.DEFENCE, 99)
            p.skills.updateCombatLevel()
            p.appearance.sync()
        }

        private fun buildMaxMeleeEquipment(p: AIPlayer) {
            val helms = intArrayOf(
                    3751)
            val shield = intArrayOf(
                    14767)
            val platebody = intArrayOf(
                    11724)
            val platelegs = intArrayOf(
                    11726)
            val amulets = intArrayOf(
                    6585)
            val boots = intArrayOf(
                    3105)
            val cape = intArrayOf(
                    6570)
            val gloves = intArrayOf(
                    7462)
            val weapons2 = intArrayOf(
                    4151)
            val arrows = intArrayOf(
                    892)
            p.equipment.replace(Item(helms[RandomFunction.random(helms.size)]), EquipmentContainer.SLOT_HAT)
            p.equipment.replace(Item(shield[RandomFunction.random(shield.size)]), EquipmentContainer.SLOT_SHIELD)
            p.equipment.replace(Item(platebody[RandomFunction.random(platebody.size)]), EquipmentContainer.SLOT_CHEST)
            p.equipment.replace(Item(platelegs[RandomFunction.random(platelegs.size)]), EquipmentContainer.SLOT_LEGS)
            p.equipment.replace(Item(weapons2[RandomFunction.random(weapons2.size)]), EquipmentContainer.SLOT_WEAPON)
            p.equipment.replace(Item(weapons2[RandomFunction.random(arrows.size)], 99999), EquipmentContainer.SLOT_ARROWS)
            p.equipment.replace(Item(amulets[RandomFunction.random(amulets.size)]), EquipmentContainer.SLOT_AMULET)
            p.equipment.replace(Item(boots[RandomFunction.random(boots.size)]), EquipmentContainer.SLOT_FEET)
            p.equipment.replace(Item(cape[RandomFunction.random(cape.size)]), EquipmentContainer.SLOT_CAPE)
            p.equipment.replace(Item(gloves[RandomFunction.random(gloves.size)]), EquipmentContainer.SLOT_HANDS)
        }

        fun createNoob(p: NoobBot) {
            p.skills.setLevel(Skills.SLAYER, 99)
            p.skills.setStaticLevel(Skills.SLAYER, 99)
            val combatType = RandomFunction.getRandom(3)
            p.skills.setLevel(Skills.HITPOINTS, 10 + RandomFunction.getRandom(10))
            p.skills.setStaticLevel(Skills.HITPOINTS, 10 + RandomFunction.getRandom(10))
            p.skills.setLevel(Skills.DEFENCE, 1 + RandomFunction.getRandom(9))
            p.skills.setStaticLevel(Skills.DEFENCE, 1 + RandomFunction.getRandom(9))
            p.inventory.add(Item(329, 5))
            when (combatType) {
                0 -> {
                    buildNoobArcherStats(p)
                    buildNoobArcherEquipment(p)
                }
                1 -> {
                    buildNoobMeleeStats(p)
                    buildNoobMeleeEquipment(p)
                }
                2 -> {
                    run {
                        buildNoobMageStats(p)
                        buildNoobMagicEquipment(p)
                        setupWizard(p)
                    }
                    run {
                        buildNoobMeleeStats(p)
                        buildNoobMeleeEquipment(p)
                    }
                }
                else -> {
                    buildNoobMeleeStats(p)
                    buildNoobMeleeEquipment(p)
                }
            }
        }

        private fun buildArcherStats(p: PvMBots) {
            p.skills.setLevel(Skills.RANGE, 10)
            p.skills.setStaticLevel(Skills.RANGE, 10)
            p.skills.setLevel(Skills.DEFENCE, 10)
            p.skills.setStaticLevel(Skills.DEFENCE, 10)
            p.skills.updateCombatLevel()
            p.appearance.sync()
        }

        private fun buildDragonArcherStats(p: DragonKiller) {
            p.skills.setStaticLevel(Skills.RANGE, 55 + RandomFunction.getRandom(15))
            p.skills.setLevel(Skills.RANGE, p.skills.getLevel(Skills.RANGE))
            p.skills.updateCombatLevel()
            p.appearance.sync()
        }

        private fun buildNoobArcherStats(p: NoobBot) {
            p.skills.setLevel(Skills.RANGE, 1 + RandomFunction.getRandom(10))
            p.skills.setStaticLevel(Skills.RANGE, 1 + RandomFunction.getRandom(10))
            p.skills.updateCombatLevel()
            p.appearance.sync()
        }

        fun buildMeleeStats(p: PvMBots) {
            p.skills.setLevel(Skills.ATTACK, 10)
            p.skills.setStaticLevel(Skills.ATTACK, 10)
            p.skills.setLevel(Skills.STRENGTH, 10)
            p.skills.setStaticLevel(Skills.STRENGTH, 10)
            p.skills.setLevel(Skills.DEFENCE, 10)
            p.skills.setStaticLevel(Skills.DEFENCE, 10)
            p.skills.updateCombatLevel()
            p.appearance.sync()
        }

        fun buildDragonMeleeStats(p: DragonKiller) {
            p.skills.setStaticLevel(Skills.ATTACK, 55 + RandomFunction.getRandom(15))
            p.skills.setLevel(Skills.ATTACK, p.skills.getLevel(Skills.ATTACK))
            p.skills.setStaticLevel(Skills.STRENGTH, 55 + RandomFunction.getRandom(15))
            p.skills.setLevel(Skills.STRENGTH, p.skills.getLevel(Skills.STRENGTH))
            p.skills.setLevel(Skills.DEFENCE, 45 + RandomFunction.getRandom(15))
            p.skills.setLevel(Skills.DEFENCE, p.skills.getLevel(Skills.DEFENCE))
            p.skills.updateCombatLevel()
            p.appearance.sync()
        }

        private fun buildNoobMeleeStats(p: NoobBot) {
            p.skills.setLevel(Skills.ATTACK, 1 + RandomFunction.getRandom(10))
            p.skills.setStaticLevel(Skills.ATTACK, 1 + RandomFunction.getRandom(10))
            p.skills.setLevel(Skills.STRENGTH, 1 + RandomFunction.getRandom(10))
            p.skills.setStaticLevel(Skills.STRENGTH, 1 + RandomFunction.getRandom(10))
            p.skills.updateCombatLevel()
            p.appearance.sync()
        }

        private fun buildMageStats(p: PvMBots) {
            p.skills.setLevel(Skills.MAGIC, 10)
            p.skills.setStaticLevel(Skills.MAGIC, 10)
            p.skills.setLevel(Skills.DEFENCE, 10)
            p.skills.setStaticLevel(Skills.DEFENCE, 10)
            p.skills.updateCombatLevel()
            p.appearance.sync()
        }

        private fun buildNoobMageStats(p: NoobBot) {
            p.skills.setLevel(Skills.MAGIC, 1 + RandomFunction.getRandom(10))
            p.skills.setStaticLevel(Skills.MAGIC, 1 + RandomFunction.getRandom(10))
            p.skills.updateCombatLevel()
            p.appearance.sync()
        }

        private fun setupWizard(p: PvMBots) {
            //final int SPELL_IDS[] = new int[] {1, 4, 6, 8, 10, 14, 17, 20, 24, 27, 33, 38, 45, 48, 52, 55 };
            p.properties.autocastSpell = SpellBookManager.SpellBook.MODERN.getSpell(1) as CombatSpell
            p.inventory.add(Item(556, 1000)) //Air runes
            p.inventory.add(Item(558, 1000)) //Mind runes
        }

        private fun buildArcherEquipment(p: PvMBots) {
            p.equipment.replace(Item(1169), EquipmentContainer.SLOT_HAT)
            p.equipment.replace(Item(1129), EquipmentContainer.SLOT_CHEST)
            p.equipment.replace(Item(1095), EquipmentContainer.SLOT_LEGS)
            p.equipment.replace(Item(841), EquipmentContainer.SLOT_WEAPON)
            p.equipment.replace(Item(884, 5000), EquipmentContainer.SLOT_ARROWS)
            p.equipment.replace(Item(1007), EquipmentContainer.SLOT_CAPE)
            p.equipment.replace(Item(1478), EquipmentContainer.SLOT_AMULET)
        }

        private fun buildDragonArcherEquipment(p: DragonKiller) {
            p.equipment.replace(Item(1169), EquipmentContainer.SLOT_HAT)
            p.equipment.replace(Item(13483), EquipmentContainer.SLOT_CHEST)
            p.equipment.replace(Item(1099), EquipmentContainer.SLOT_LEGS)
            p.equipment.replace(Item(9185), EquipmentContainer.SLOT_WEAPON)
            p.equipment.replace(Item(1540), EquipmentContainer.SLOT_SHIELD)
            p.equipment.replace(Item(9140, 500), EquipmentContainer.SLOT_ARROWS)
            p.equipment.replace(Item(10498), EquipmentContainer.SLOT_CAPE)
            p.equipment.replace(Item(1478), EquipmentContainer.SLOT_AMULET)
        }

        private fun buildNoobArcherEquipment(p: PvMBots) {
            val hats = intArrayOf(1169, 1169, 1139, 1137, 1153, 579)
            val legs = intArrayOf(1095, 7366, 1075, 1087, 1095)
            val chest = intArrayOf(1129, 1103, 1101, 1129, 1117, 577)
            p.equipment.replace(Item(hats[RandomFunction.getRandom(hats.size - 1)]), EquipmentContainer.SLOT_HAT)
            p.equipment.replace(Item(chest[RandomFunction.getRandom(chest.size - 1)]), EquipmentContainer.SLOT_CHEST)
            p.equipment.replace(Item(legs[RandomFunction.getRandom(legs.size - 1)]), EquipmentContainer.SLOT_LEGS)
            p.equipment.replace(Item(841), EquipmentContainer.SLOT_WEAPON)
            p.equipment.replace(Item(884, 5000), EquipmentContainer.SLOT_ARROWS)
            p.equipment.replace(Item(1007), EquipmentContainer.SLOT_CAPE)
            p.equipment.replace(Item(1478), EquipmentContainer.SLOT_AMULET)
        }

        private fun buildMeleeEquipment(p: PvMBots) {
            p.equipment.replace(Item(1153), EquipmentContainer.SLOT_HAT)
            p.equipment.replace(Item(1115), EquipmentContainer.SLOT_CHEST)
            p.equipment.replace(Item(1067), EquipmentContainer.SLOT_LEGS)
            p.equipment.replace(Item(1309), EquipmentContainer.SLOT_WEAPON)
            p.equipment.replace(Item(884), EquipmentContainer.SLOT_ARROWS)
            p.equipment.replace(Item(1007), EquipmentContainer.SLOT_CAPE)
            p.equipment.replace(Item(1725), EquipmentContainer.SLOT_AMULET)
        }

        private fun buildDragonMeleeEquipment(p: DragonKiller) {
            p.equipment.replace(Item(1163), EquipmentContainer.SLOT_HAT)
            p.equipment.replace(Item(1127), EquipmentContainer.SLOT_CHEST)
            p.equipment.replace(Item(1079), EquipmentContainer.SLOT_LEGS)
            p.equipment.replace(Item(1333), EquipmentContainer.SLOT_WEAPON)
            p.equipment.replace(Item(1540), EquipmentContainer.SLOT_SHIELD)
            p.equipment.replace(Item(1007), EquipmentContainer.SLOT_CAPE)
            p.equipment.replace(Item(1725), EquipmentContainer.SLOT_AMULET)
        }

        private fun buildNoobMeleeEquipment(p: PvMBots) {
            val hats = intArrayOf(1169, 1169, 1139, 1137, 1153, 579)
            val legs = intArrayOf(1095, 7366, 1075, 1087, 1095)
            val chest = intArrayOf(1129, 1103, 1101, 1129, 1117, 577)
            val weapons = intArrayOf(1307, 1321, 1375, 1203, 1239, 1267, 1293, 1323, 1335)
            p.equipment.replace(Item(hats[RandomFunction.getRandom(hats.size - 1)]), EquipmentContainer.SLOT_HAT)
            p.equipment.replace(Item(chest[RandomFunction.getRandom(chest.size - 1)]), EquipmentContainer.SLOT_CHEST)
            p.equipment.replace(Item(legs[RandomFunction.getRandom(legs.size - 1)]), EquipmentContainer.SLOT_LEGS)
            p.equipment.replace(Item(weapons[RandomFunction.getRandom(weapons.size - 1)]), EquipmentContainer.SLOT_WEAPON)
            p.equipment.replace(Item(884), EquipmentContainer.SLOT_ARROWS)
            p.equipment.replace(Item(1007), EquipmentContainer.SLOT_CAPE)
            p.equipment.replace(Item(1725), EquipmentContainer.SLOT_AMULET)
        }

        private fun buildMagicEquipment(p: PvMBots) {
            p.equipment.replace(Item(579), EquipmentContainer.SLOT_HAT)
            p.equipment.replace(Item(577), EquipmentContainer.SLOT_CHEST)
            p.equipment.replace(Item(1011), EquipmentContainer.SLOT_LEGS)
            p.equipment.replace(Item(1389), EquipmentContainer.SLOT_WEAPON)
            p.equipment.replace(Item(884), EquipmentContainer.SLOT_ARROWS)
            p.equipment.replace(Item(1007), EquipmentContainer.SLOT_CAPE)
            p.equipment.replace(Item(1727), EquipmentContainer.SLOT_AMULET)
        }

        private fun buildNoobMagicEquipment(p: PvMBots) {
            val hats = intArrayOf(1169, 1169, 1139, 1137, 1153, 579)
            val legs = intArrayOf(1095, 7366, 1075, 1087, 1095)
            val chest = intArrayOf(1129, 1103, 1101, 1129, 1117, 577)
            val weapons = intArrayOf(1379, 1383, 1385, 1387, 1389)
            p.equipment.replace(Item(hats[RandomFunction.getRandom(hats.size - 1)]), EquipmentContainer.SLOT_HAT)
            p.equipment.replace(Item(chest[RandomFunction.getRandom(chest.size - 1)]), EquipmentContainer.SLOT_CHEST)
            p.equipment.replace(Item(legs[RandomFunction.getRandom(legs.size - 1)]), EquipmentContainer.SLOT_LEGS)
            p.equipment.replace(Item(weapons[RandomFunction.getRandom(weapons.size - 1)]), EquipmentContainer.SLOT_WEAPON)
            p.equipment.replace(Item(884), EquipmentContainer.SLOT_ARROWS)
            p.equipment.replace(Item(1007), EquipmentContainer.SLOT_CAPE)
            p.equipment.replace(Item(1727), EquipmentContainer.SLOT_AMULET)
        }

        fun spawn(loc: Location?) {
            val bot = create(loc)
            bot.appearance.gender = if (RandomFunction.random(3) == 1) Gender.FEMALE else Gender.MALE
        }

        fun spawnLowest(loc: Location?) {
            val bot = createLowest(loc)
            generateMinLevels(bot)
        }

        fun spawnNoob(loc: Location?) {
            val bot = createNoob(loc)
            createNoob(bot)
        }

        fun spawnDragonKiller(loc: Location?) {
            val bot = createDragonKiller(loc)
            bot.appearance.gender = if (RandomFunction.random(3) == 1) Gender.FEMALE else Gender.MALE
            createDragonKiller(bot)
        }

        fun spawnGiantMoleBot(loc: Location?) {
            val bot = createGiantMoleBot(Location(0, 0))
            bot.teleport(loc)
            bot.appearance.gender = if (RandomFunction.random(3) == 1) Gender.FEMALE else Gender.MALE
            generateGiantMoleBot(bot)
        }

        @JvmStatic
		fun immersiveSpawns() {
            Executors.newSingleThreadExecutor().execute {
                /*//Lumbridge
		//GOBLINS
		spawnLowest(new Location(3259, 3224));
		spawnLowest(new Location(3251, 3235));
		spawnLowest(new Location(3244, 3247));
		spawnNoob(new Location(3244, 3247));
		//COWS
		spawnLowest(new Location(3259, 3245));
		spawnLowest(new Location(3258, 3261));
		spawnLowest(new Location(3263, 3263));
		spawnLowest(new Location(3263, 3271));
		spawnLowest(new Location(3254, 3288));
		//CHICKENS
		spawnLowest(new Location(3234, 3294));
		spawnLowest(new Location(3229, 3297));
		//GOBLINS2
		spawnLowest(new Location(3182, 3255));
		spawnLowest(new Location(3169, 3254));
		spawnLowest(new Location(3167, 3233));
		spawnLowest(new Location(3152, 3228));*/
                //Lumby castle
                spawnLowest(Location(3221, 3219))
                /*		spawnNoob(new Location(3193, 3208));
		//Lumby swamp
		spawnNoob(new Location(3224, 3186));
		spawnNoob(new Location(3217, 3177));
		spawnNoob(new Location(3209, 3191));
		
		//Edge
		spawnLowest(new Location(3096, 3509));
		
		//Edge dungeon
		//Hill giants
		spawnLowest(new Location(3118, 9848));
		spawnLowest(new Location(3110, 9842));
		spawnLowest(new Location(3115, 9836));
		spawnLowest(new Location(3107, 9832));
		spawnLowest(new Location(3099, 9834));
		//brass key room
		spawnLowest(new Location(3127, 9863));
		
		//Dark wizzards
		spawnLowest(new Location(3226, 3367));
		spawnLowest(new Location(3226, 3368));
		spawnLowest(new Location(3230, 3373));
		spawnLowest(new Location(3209, 3378));
		
		//Varrock palace
		spawnLowest(new Location(3218, 3465));
		spawnLowest(new Location(3209, 3462));
		
		//Varrock Sewers
		spawnLowest(new Location(3235, 9868));
		spawnLowest(new Location(3242, 9915));
		spawnLowest(new Location(3176, 9883));
		
		//Dragons Wilderness
		//WEST
		spawnDragonKiller(new Location(2977, 3614));
		spawnDragonKiller(new Location(2987, 3619));
		
		//Burthrope Dungeon
		//Blue dragons
		spawnDragonKiller(new Location(2901, 9799));
		spawnDragonKiller(new Location(2910, 9804));
		//Al kharid
		//palace
		spawnLowest(new Location(3301, 3173));
		spawnNoob(new Location(3293, 3170));
		spawnNoob(new Location(3286, 3171));
		spawnNoob(new Location(3289, 3169));
		
		//Slayer Tower
		spawnLowest(new Location(3412, 3173));
		spawnNoob(new Location(3412, 3539));
		spawnNoob(new Location(3420, 3545));
		spawnNoob(new Location(3420, 3545));
		spawnNoob(new Location(3420, 3558));
		spawnNoob(new Location(3433, 3571));
		//Replace with something like rune bots
		spawnDragonKiller(new Location(3437, 3562, 1));
		spawnDragonKiller(new Location(3437, 3562, 1));
		spawnDragonKiller(new Location(3437, 3562, 1));
		//Bloodvelds
		spawnDragonKiller(new Location(3417, 3561, 1));
		spawnDragonKiller(new Location(3417, 3561, 1));
		spawnDragonKiller(new Location(3417, 3561, 1));
		//Brimhaven dragons
		spawnDragonKiller(new Location(2704, 9450));
		spawnDragonKiller(new Location(2704, 9450));*/println("[" + GameWorld.settings?.name + "]: PvMBotsBuilder: Spawned " + botsSpawned + " bots.")
            }
        }

        @JvmStatic
        fun createPestControlTestBot2(l: Location?): PestControlTestBot2 {
            botsSpawned++
            return PestControlTestBot2(l!!)
        }
        @JvmStatic
        fun createPestControlTestBot(l: Location?): PestControlTestBot {
            botsSpawned++
            return PestControlTestBot(l!!)
        }

    }
}