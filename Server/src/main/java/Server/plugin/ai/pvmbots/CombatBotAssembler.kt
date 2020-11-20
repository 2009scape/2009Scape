package plugin.ai.pvmbots

import core.game.node.item.Item
import core.game.system.SystemLogger
import core.game.world.map.Location
import core.tools.ItemNames
import core.tools.RandomFunction
import plugin.ai.AIPlayer
import plugin.skill.Skills
import java.util.*
import kotlin.collections.ArrayList

/**
 * Assembles combat-style bots, intended to replace PVMBotBuilder.
 * @author Ceikry
 */
class CombatBotAssembler {
    enum class Type{
        RANGE,
        MAGE,
        MELEE
    }
    enum class Tier{
        LOW,
        MED,
        HIGH,
        PURE
    }

    /**
     * Produces a bot with the specified parameters.
     * @param type the type of bot to produce. Melee, Ranged, or Mage.
     * @param tier specifies the range of skills the bot can have, and thereby, the gear. LOW, MED, or HIGH.
     * @param location the location that the bot spawns and respawns at.
     * @return an AIPlayer with the specified values.
     * @author Ceikry
     */
    fun produce(type: Type, tier: Tier, location: Location): AIPlayer? {
        return when (type) {
            Type.RANGE -> assembleRangedBot(tier, location)
            Type.MELEE -> assembleMeleeBot(tier,location)
            Type.MAGE -> assembleMeleeBot(tier,location)
        }
    }

    /**
     * Assembles a ranged bot with the specified values.
     * @param tier the tier to use which defines skill ranges and gear.
     * @param location the bot's spawn and respawn location
     * @param crossbow (optional) whether or not this bot should use a crossbow
     * @return a CombatBot with the specified values.
     * @author Ceikry
     */
    fun assembleRangedBot(tier: Tier, location: Location, crossbow: Boolean? = null): CombatBot {
        val bot = CombatBot(location)

        generateStats(bot, tier, Skills.RANGE, Skills.DEFENCE)
        gearRangedBot(bot, crossbow ?: Random().nextInt() % 2 == 0)
        return bot
    }

    /**
     * Assembles a melee bot with the specified values.
     * @param tier the tier of the bot. Specifies levels and gear.
     * @param location the spawn and respawn location of the bot.
     * @return a CombatBot with the specified values.
     * @author Ceikry
     */
    fun assembleMeleeBot(tier: Tier, location: Location): CombatBot {
        val bot = CombatBot(location)

        generateStats(bot, tier, Skills.ATTACK, Skills.STRENGTH, Skills.DEFENCE)
        gearMeleeBot(bot)
        return bot
    }

    /**
     * Assembles a melee bot with gear appropriate for killing dragons.
     * @param tier the tier of the bot, specifying levels and gear.
     * @param location the spawn and respawn location of the bot.
     * @return a CombatBot suited for killing dragons.
     * @author Kermit
     */
    fun MeleeAdventurer(tier: Tier, location: Location): CombatBot{
        val bot = CombatBot(location)
        var max = 0
        val level = RandomFunction.random(25, 65).also {max = 99 }
        generateStats(bot,tier,Skills.ATTACK, Skills.STRENGTH, Skills.DEFENCE)
        bot.skills.setStaticLevel(Skills.HITPOINTS, level)
        bot.skills.setStaticLevel(Skills.ATTACK, level + 5)
        bot.skills.setStaticLevel(Skills.STRENGTH, level + 5)
        bot.skills.setLevel(Skills.HITPOINTS, level)
        bot.skills.setLevel(Skills.ATTACK, level + 5)
        bot.skills.setLevel(Skills.STRENGTH, level + 5)
        bot.skills.updateCombatLevel()
        equipHighest(bot, MELEE_HELMS)
        equipHighest(bot, MELEE_TOP)
        equipHighest(bot, MELEE_LEG)
        equipHighest(bot, MELEE_WEP)
        equipHighest(bot, MELEE_SHIELD)
        equipHighest(bot, CAPE)
        equipHighest(bot, NNECK)
        equipHighest(bot, NGLOVES)
        equipHighest(bot, NBOOTS)
        bot.equipment.refresh()
        return bot
    }

    /**
     * Assembles a melee bot with gear appropriate for killing dragons.
     * @param tier the tier of the bot, specifying levels and gear.
     * @param location the spawn and respawn location of the bot.
     * @return a CombatBot suited for killing dragons.
     * @author Kermit
     */
    fun RangeAdventurer(tier: Tier, location: Location): CombatBot{
        val bot = CombatBot(location)
        var max = 0
        val level = RandomFunction.random(35, 65).also {max = 75 }
        generateStats(bot,tier,Skills.ATTACK, Skills.STRENGTH)
        bot.skills.setStaticLevel(Skills.HITPOINTS, level)
        bot.skills.setStaticLevel(Skills.DEFENCE, level)
        bot.skills.setStaticLevel(Skills.RANGE, level + 10)
        bot.skills.setLevel(Skills.HITPOINTS, level)
        bot.skills.setLevel(Skills.DEFENCE, level)
        bot.skills.setLevel(Skills.RANGE, level + 10)
        bot.skills.updateCombatLevel()
        equipHighest(bot,RANGE_HELMS,65)
        equipHighest(bot,RANGE_TOPS,65)
        equipHighest(bot,RANGE_LEGS,65)
        equipHighest(bot,CROSSBOWS,50)
        equipHighest(bot, CAPE)
        equipHighest(bot, NRANGENECK)
        equipHighest(bot, NRANGESHIELD)
        equipHighest(bot, PCRANGE_BACK)
        equipHighest(bot, NGLOVES)
        equipHighest(bot, NRBOOTS)
        bot.equipment.add(Item(ItemNames.BRONZE_BOLTS,100000),13,false,false)
        bot.equipment.refresh()
        return bot
    }

    /**
     * Assembles a melee bot with gear appropriate for killing dragons.
     * @param tier the tier of the bot, specifying levels and gear.
     * @param location the spawn and respawn location of the bot.
     * @return a CombatBot suited for killing dragons.
     * @author Ceikry
     */
    fun assembleMeleeDragonBot(tier: Tier, location: Location): CombatBot{
        val bot = CombatBot(location)
        generateStats(bot,tier,Skills.ATTACK, Skills.STRENGTH, Skills.DEFENCE)
        equipHighest(bot, MELEE_HELMS, 50)
        equipHighest(bot, MELEE_TOP, 40)
        equipHighest(bot, MELEE_LEG, 40)
        equipHighest(bot, MELEE_WEP, 60)
        equipHighest(bot, CAPE)
        equipHighest(bot, NGLOVES)
        equipHighest(bot, NBOOTS)
        bot.equipment.refresh()
        return bot
    }

    /**
     * Assembles a ranged bot with gear appropriate for killing dragons.
     * @param tier the tier of the bot, specifying levels and gear.
     * @param location the spawn and respawn location of the bot.
     * @return a CombatBot suited for killing dragons.
     * @author Ceikry
     */
    fun assembleRangeDragonBot(tier: Tier, location: Location): CombatBot{
        val bot = CombatBot(location)
        bot.fullRestore()
        generateStats(bot,tier,Skills.RANGE,Skills.DEFENCE)
        equipHighest(bot,RANGE_HELMS,50)
        equipHighest(bot,RANGE_TOPS,50)
        equipHighest(bot,RANGE_LEGS,50)
        equipHighest(bot,CROSSBOWS,50)
        equipHighest(bot, CAPE)
        equipHighest(bot, NGLOVES)
        equipHighest(bot, NRBOOTS)
        bot.equipment.add(Item(ItemNames.BRONZE_BOLTS,100000),13,false,false)
        bot.equipment.refresh()
        return bot
    }

    /**
     * Gears a ranged bot with the best gear for its stats.
     * @param bot the bot to gear
     * @param crossbow (optional) whether or not this bot should use a crossbow. Default is false.
     * @author Ceikry
     */
    fun gearRangedBot(bot: AIPlayer, crossbow: Boolean? = false) {
        equipHighest(bot, RANGE_HELMS)
        equipHighest(bot, RANGE_TOPS)
        equipHighest(bot, RANGE_LEGS)
        equipHighest(bot, CAPE)
        equipHighest(bot, NGLOVES)
        equipHighest(bot, NRBOOTS)
        if(crossbow == true) { equipHighest(bot,CROSSBOWS); equipHighest(bot,MELEE_SHIELD); bot.equipment.add(Item(ItemNames.BRONZE_BOLTS,Integer.MAX_VALUE),13,false,false) }
        else {equipHighest(bot, BOWS); bot.equipment.add(Item(ItemNames.BRONZE_ARROW,Integer.MAX_VALUE),13,false,false) }
        bot.equipment.refresh()
    }

    /**
     * Gears a melee bot with the best gear for its stats.
     * @param bot the bot to gear.
     * @author Ceikry
     */
    fun gearMeleeBot(bot: AIPlayer){
        equipHighest(bot, MELEE_HELMS)
        equipHighest(bot, MELEE_LEG)
        equipHighest(bot, MELEE_SHIELD)
        equipHighest(bot, MELEE_TOP)
        equipHighest(bot, CAPE)
        equipHighest(bot, NGLOVES)
        equipHighest(bot, NBOOTS)
        equipHighest(bot, MELEE_WEP)
        bot.equipment.refresh()
    }

    /**
     * Gears a Novice Pest control bot.
     * @author Sir Kermit & Ceikry
     */
    fun gearPCnRangedBot(bot: AIPlayer, crossbow: Boolean? = false, vararg skills: Int) {
        var max = 0
        val level = RandomFunction.random(30, 70).also {max = 75 }
        bot.fullRestore()

        bot.skills.setStaticLevel(Skills.RANGE, 50)
        bot.skills.setStaticLevel(Skills.DEFENCE, 50)
        bot.skills.setStaticLevel(Skills.ATTACK, level)
        bot.skills.setStaticLevel(Skills.STRENGTH, level)
        bot.skills.setStaticLevel(Skills.HITPOINTS, level)
        bot.skills.setLevel(Skills.RANGE, 50)
        bot.skills.setLevel(Skills.DEFENCE, 50)
        bot.skills.setLevel(Skills.ATTACK, level)
        bot.skills.setLevel(Skills.STRENGTH, level)
        bot.skills.setLevel(Skills.HITPOINTS, level)
        bot.skills.updateCombatLevel()
        equipHighest(bot, PCRANGE_HELMS)
        equipHighest(bot, PCRANGE_TOPS)
        equipHighest(bot, PCRANGE_BACK)
        equipHighest(bot, PCRANGE_LEGS)
        bot.equipment.refresh()
        equipHighest(bot, NECK)
        equipHighest(bot, GLOVES)
        equipHighest(bot, BOOTS)
        equipHighest(bot, RING_ARCH)
        bot.equipment.refresh()
        if(crossbow == true) { equipHighest(bot,PCCROSSBOWS); equipHighest(bot,MELEE_SHIELD); bot.equipment.add(Item(ItemNames.BRONZE_BOLTS,Integer.MAX_VALUE),13,false,false) }
        else {equipHighest(bot, PCBOWS); bot.equipment.add(Item(ItemNames.BRONZE_ARROW,Integer.MAX_VALUE),13,false,false) }
        bot.skills.setStaticLevel(Skills.RANGE, 99)
        bot.skills.setLevel(Skills.RANGE, 99)
        bot.equipment.refresh()
    }

    fun gearPCnMeleeBot(bot: AIPlayer, vararg skills: Int){
        var max = 0
        val initial = RandomFunction.random(30, 75).also {max = 75 }
        var level = initial
        bot.fullRestore()

        bot.skills.setStaticLevel(Skills.STRENGTH, level)
        bot.skills.setStaticLevel(Skills.DEFENCE, level)
        bot.skills.setStaticLevel(Skills.ATTACK, level)
        bot.skills.setStaticLevel(Skills.HITPOINTS, level)
        bot.skills.setStaticLevel(Skills.PRAYER, 70)
        bot.skills.setStaticLevel(Skills.RANGE, 10)
        bot.skills.setStaticLevel(Skills.MAGIC, 10)
        bot.skills.setLevel(Skills.STRENGTH, level)
        bot.skills.setLevel(Skills.DEFENCE, level)
        bot.skills.setLevel(Skills.ATTACK, level)
        bot.skills.setLevel(Skills.HITPOINTS, level)
        bot.skills.setLevel(Skills.PRAYER, 70)
        bot.skills.setLevel(Skills.RANGE, 10)
        bot.skills.setLevel(Skills.MAGIC, 10)
        bot.skills.updateCombatLevel()
        equipHighest(bot, PCMELEE_HELMS)
        equipHighest(bot, PCMELEE_LEG)
        equipHighest(bot, PCMELEE_SHIELD)
        equipHighest(bot, PCMELEE_TOP)
        equipHighest(bot, PCMELEE_WEP)
        bot.equipment.refresh()
        equipHighest(bot, CAPE)
        equipHighest(bot, NECK)
        equipHighest(bot, GLOVES)
        equipHighest(bot, BOOTS)
        equipHighest(bot, RING_BERS)
        bot.equipment.refresh()
        bot.skills.setStaticLevel(Skills.DEFENCE, 70)
        bot.skills.setStaticLevel(Skills.ATTACK, 99)
        bot.skills.setStaticLevel(Skills.STRENGTH, 99)
        bot.skills.setStaticLevel(Skills.HITPOINTS, 80)
        bot.skills.setLevel(Skills.DEFENCE, 70)
        bot.skills.setLevel(Skills.ATTACK, 99)
        bot.skills.setLevel(Skills.STRENGTH, 99)
        bot.skills.setLevel(Skills.HITPOINTS, 80)
        bot.fullRestore()
    }
    /**
     * Gears a Intermediate Pest control bot.
     * @author Sir Kermit & Ceikry
     */
    fun gearPCiRangedBot(bot: AIPlayer, crossbow: Boolean? = false, vararg skills: Int) {
        var max = 0
        val level = RandomFunction.random(50, 80).also {max=99 }
        bot.fullRestore()

        bot.skills.setStaticLevel(Skills.RANGE, level)
        bot.skills.setStaticLevel(Skills.DEFENCE, 80)
        bot.skills.setStaticLevel(Skills.ATTACK, level)
        bot.skills.setStaticLevel(Skills.STRENGTH, level)
        bot.skills.setStaticLevel(Skills.HITPOINTS, 70)
        bot.skills.setStaticLevel(Skills.SUMMONING, 99)
        bot.skills.setLevel(Skills.RANGE, level)
        bot.skills.setLevel(Skills.DEFENCE, 80)
        bot.skills.setLevel(Skills.ATTACK, level)
        bot.skills.setLevel(Skills.STRENGTH, level)
        bot.skills.setLevel(Skills.HITPOINTS, 70)
        bot.skills.setLevel(Skills.SUMMONING, 99)
        bot.skills.updateCombatLevel()
        equipHighest(bot, PCRANGE_HELMS)
        equipHighest(bot, PCRANGE_TOPS)
        equipHighest(bot, PCRANGE_BACK)
        equipHighest(bot, PCRANGE_LEGS)
        bot.equipment.refresh()
        equipHighest(bot, GLOVES)
        equipHighest(bot, NECK)
        equipHighest(bot, BOOTS)
        equipHighest(bot, RING_ARCH)
        bot.equipment.refresh()
        if(crossbow == true) { equipHighest(bot,PCCROSSBOWS); equipHighest(bot,MELEE_SHIELD); bot.equipment.add(Item(ItemNames.BRONZE_BOLTS,Integer.MAX_VALUE),13,false,false) }
        else {equipHighest(bot, PCBOWS); bot.equipment.add(Item(ItemNames.BRONZE_ARROW,Integer.MAX_VALUE),13,false,false) }
        bot.skills.setStaticLevel(Skills.RANGE, 99)
        bot.skills.setLevel(Skills.RANGE, 99)
        bot.equipment.refresh()
    }

    fun gearPCiMeleeBot(bot: AIPlayer, vararg skills: Int){
        var max = 0
        val initial = RandomFunction.random(55, 95).also {max=95 }
        var level = initial
        bot.fullRestore()

        bot.skills.setStaticLevel(Skills.STRENGTH, level)
        bot.skills.setStaticLevel(Skills.DEFENCE, level)
        bot.skills.setStaticLevel(Skills.ATTACK, level)
        bot.skills.setStaticLevel(Skills.HITPOINTS, level)
        bot.skills.setStaticLevel(Skills.PRAYER, 99)
        bot.skills.setStaticLevel(Skills.RANGE, level)
        bot.skills.setStaticLevel(Skills.MAGIC, level)
        bot.skills.setStaticLevel(Skills.SUMMONING, 99)
        bot.skills.setLevel(Skills.STRENGTH, level)
        bot.skills.setLevel(Skills.DEFENCE, level)
        bot.skills.setLevel(Skills.ATTACK, level)
        bot.skills.setLevel(Skills.HITPOINTS, level)
        bot.skills.setLevel(Skills.PRAYER, 99)
        bot.skills.setLevel(Skills.RANGE, level)
        bot.skills.setLevel(Skills.MAGIC, level)
        bot.skills.setLevel(Skills.SUMMONING, 99)
        bot.skills.updateCombatLevel()
        equipHighest(bot, PCMELEE_HELMS)
        equipHighest(bot, PCMELEE_LEG)
        equipHighest(bot, PCMELEE_SHIELD)
        equipHighest(bot, PCMELEE_TOP)
        equipHighest(bot, PCMELEE_WEP)
        bot.equipment.refresh()
        equipHighest(bot, CAPE)
        equipHighest(bot, NECK)
        equipHighest(bot, GLOVES)
        equipHighest(bot, BOOTS)
        equipHighest(bot, RING_BERS)
        bot.equipment.refresh()
        bot.skills.setStaticLevel(Skills.DEFENCE, 99)
        bot.skills.setStaticLevel(Skills.ATTACK, 99)
        bot.skills.setStaticLevel(Skills.STRENGTH, 99)
        bot.skills.setStaticLevel(Skills.HITPOINTS, 99)
        bot.skills.setLevel(Skills.DEFENCE, 99)
        bot.skills.setLevel(Skills.ATTACK, 99)
        bot.skills.setLevel(Skills.STRENGTH, 99)
        bot.skills.setLevel(Skills.HITPOINTS, 99)
        bot.fullRestore()
    }


    /**
     * Generates the stats for a bot with the given tier.
     * @param bot the bot to gear
     * @param tier the Tier of stats and gear. LOW, MED, HIGH, PURE.
     * @param skills the skills that we should generate.
     * @author Ceikry and Eli
     */
    fun generateStats(bot: AIPlayer, tier: CombatBotAssembler.Tier, vararg skills: Int) {
        var totalXPAdd = 0.0
        var skillAmt = 0.0
        val variance = 0.5
        var max = 0
        val initial = when (tier) {
            Tier.LOW -> RandomFunction.random(33).also { max = 33 }
            Tier.MED -> RandomFunction.random(33, 66).also { max = 66 }
            Tier.HIGH -> RandomFunction.random(66, 99).also { max = 99 }
            Tier.PURE -> RandomFunction.random(90, 99).also {max = 99 }
        }
        for (skill in skills.indices) {
            val perc = RandomFunction.random(-variance,variance)
            var level = initial +  (perc * 33).toInt()
            if(level < 1)
                level = 1
            if(level > max)
                level = max
            bot.skills.setLevel(skills[skill], level)
            bot.skills.setStaticLevel(skills[skill], level)
            totalXPAdd += bot.skills.getExperience(skills[skill])
            skillAmt++
        }
        when (tier){
            Tier.PURE -> bot.skills.setStaticLevel(Skills.DEFENCE,10)
            Tier.PURE -> bot.skills.setStaticLevel(Skills.STRENGTH,99)
            Tier.PURE -> bot.skills.setStaticLevel(Skills.ATTACK,90)
            Tier.PURE -> bot.skills.setStaticLevel(Skills.PRAYER,43)
            Tier.PURE -> bot.skills.setStaticLevel(Skills.RANGE,1)
            Tier.PURE -> bot.skills.setStaticLevel(Skills.MAGIC,1)
        }

        bot.skills.addExperience(Skills.HITPOINTS, (totalXPAdd / skillAmt) * 0.2)
        SystemLogger.log("hp: ${(totalXPAdd / skillAmt) * 0.2}")
        val new_hp = bot.skills.levelFromXP((totalXPAdd / skillAmt) * 0.2)
        bot.skills.setStaticLevel(Skills.HITPOINTS,10 + new_hp)
        bot.skills.updateCombatLevel()
        bot.fullRestore()
    }

    /**
     * Equips the highest piece of gear from a set for the stats the bot has.
     * @param bot the bot to equip gear to.
     * @param set the IntArray set to check
     * @param levelcap (optional) the max level of gear to equip.
     * @author Ceikry
     */
    private fun equipHighest(bot: AIPlayer, set: Array<Int>, levelcap: Int? = null) {
        val highestItems = ArrayList<Item>()
        var highest: Item? = null
        for (i in set.indices) {
            val item = Item(set[i])
            var canEquip = true
            (item.definition.handlers.getOrDefault("requirements",null) as HashMap<Int,Int>?)?.let { map ->
                levelcap?.let {levelcap ->
                    map.map {
                        if (bot.skills.getLevel(it.key) < it.value || it.value > levelcap)
                            canEquip = false
                    }
                } ?: map.map {
                    if (bot.skills.getLevel(it.key) < it.value)
                        canEquip = false
                }
            }
            if (canEquip) {
                if (highest == null) {
                    highest = item
                    highestItems.add(item)
                    continue
                }
                if (item.lvlAvg() > highest.lvlAvg()) {
                    highest = item
                    highestItems.clear()
                    highestItems.add(item)
                } else if(item.lvlAvg() == highest.lvlAvg()){
                    highestItems.add(item)
                }
            }
        }
        bot.equipment.add(highestItems.random(), highest!!.definition!!.handlers["equipment_slot"] as Int, false, false)
    }

    /**
     * Extension function for the Item class that gets the average of its level requirements.
     * @author Ceikry
     */
    fun Item.lvlAvg(): Int {
        var total = 1
        var count = 1
        (definition.handlers.getOrDefault("requirements",null) as HashMap<Int,Int>?)?.let { map ->
            map.map {
                total += it.value
                count++
            }
        }
        return total / count
    }


    val RANGE_HELMS = arrayOf(1167,4732,3749)
    val RANGE_TOPS = arrayOf(1129,1131,1135,2499,2501,2503)
    val RANGE_LEGS = arrayOf(1095,1097,1099,2493,2495,2497)
    val BOWS = arrayOf(841,843,847,853)
    val CROSSBOWS  = arrayOf(9185,9174,9177,9176,9179,9181,9183)

    val PCRANGE_HELMS = arrayOf(1167,4732,3749,11718)
    val PCRANGE_TOPS = arrayOf(1129,1131,1135,2503,11720)
    val PCRANGE_LEGS = arrayOf(1095,1097,1099,2497,11722)
    val PCRANGE_BACK = arrayOf(1019,10498,10499)
    val PCBOWS = arrayOf(841,843,847,853)
    val PCCROSSBOWS  = arrayOf(9185,9174,9177,9176,9179,9181,9183)

    val MELEE_HELMS = arrayOf(1137,1139, 1141, 6621, 1143,1145,1147,1149,1151,1153, 6623, 1159,1163,1165,3748, 3751, 3753, 4716,4724, 4745, 4753)
    val MELEE_TOP = arrayOf(1101,1103,1105,1107,1109,1111,1113,2513,1115,1117,1119,1121,1123,1125,1127,4720,4728,4749,4749)
    val MELEE_LEG = arrayOf(1081,1083,1085,1087,1089,1091,1093,4759,1067,1069,1071,1073,1075,1077,1079,4722,4751,4722,4751)
    val MELEE_SHIELD = arrayOf(1171,1173,1175,1177,1179,1181,1183,1185,1187,1189,1191,1193,1195,1197,1199,1201)
    val MELEE_WEP = arrayOf(1277,1279,1281,1283,1285,1287,1289,1291,1293,1295,1297,1299,1301,1303,1305,1321,1323,1325,1327,1329,1331,1333,4587,4151,1363,1365,1367,1369,1371,1373,1375,1377)
    val NGLOVES = arrayOf(1059,2922,2912,2902,2932,2942,3799)
    val NBOOTS = arrayOf(4121,4123,4125,4127,4129,4131,1061,1837,2579,9005)
    val NRBOOTS = arrayOf(9006,626,628,630,632,634)
    val NNECK = arrayOf(1704,1725,1729,1731)
    val NRANGENECK = arrayOf(1478,1704)
    val NRANGESHIELD = arrayOf(1191,1193,1195,1197,1199,1201)

    val PCMELEE_HELMS = arrayOf(1137,1139, 1141, 6621, 1143,1145,1147,1149,1151,1153, 6623, 1159,1163,1165,3748, 3751, 10828, 11335, 3753, 4716,4724, 4745, 4753, 3751)
    val PCMELEE_TOP = arrayOf(1101,1103,1105,1107,1109,1111,1113,2513,1115,1117,1119,1121,1123,1125,1127,4720,4728,4749,4749,11724,14479,2513)
    val PCMELEE_LEG = arrayOf(1081,1083,1085,1087,1089,1091,1093,4759,1067,1069,1071,1073,1075,1077,1079,4722,4751,4722,4751,11726,4087)
    val PCMELEE_SHIELD = arrayOf(1171,1173,1175,1177,1179,1181,1183,1185,1187,1189,1191,1193,1195,1197,1199,1201,6524,13742,13740,13738,13736,13734)
    val PCMELEE_WEP = arrayOf(1277,1279,1281,1283,1285,1287,1289,1291,1293,1295,1297,1299,1301,1303,1305,1321,1323,1325,1327,1329,1331,1333,4587,4151,1363,1365,1367,1369,1371,1373,1375,1377,1434,5698)

    val NECK = arrayOf(1704,6585)
    val CAPE = arrayOf(1019,1021,1023,6568,4315,4317,4319,4321,4323,4325,4327,4329,4331,4333,4335,4337,4339,4341,4343,4345,4347,4349,4351)
    val GLOVES = arrayOf(1059,7456,7457,7458,7459,7460,7461,7462)
    val BOOTS = arrayOf(1061,4131,11732,11728,4131)
    val RING_BERS = arrayOf(6737)
    val RING_ARCH = arrayOf(6733)

    val RICH_MELEE_HELMS = arrayOf(2587,2595,2605,2613,2619,2627,2657,2665,2673,3486,1149,10828,4716,4724,4753)
}