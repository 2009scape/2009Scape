package plugin.ai.general.scriptrepository.runecrafting

import core.game.node.entity.skill.runecrafting.Altar
import core.game.world.map.zone.ZoneBorders

enum class RCData(val BANK_ZONE: ZoneBorders, val ALTAR_ZONES: List<ZoneBorders>, val INSIDE_ZONE: ZoneBorders,
                   val ALTAR: Altar, val TASK_TITLE: String, val SIMPLE_NAME: String,
                   val REG_ESSENCE_ALLOWED: Boolean) {

    Air(ZoneBorders(3010,3355,3015,3357),
        listOf<ZoneBorders>(ZoneBorders(3005, 3322, 3008, 3326),
            ZoneBorders(2995, 3308, 2995, 3310), ZoneBorders(2985, 3294, 2986, 3294)),
        ZoneBorders(2834, 4828, 2850, 4841),
        Altar.AIR, "Air Runes", "air", true),
    WATER(ZoneBorders(3092,3242,3094,3245),
        listOf<ZoneBorders>(ZoneBorders(3183, 3164, 3183, 3166)),
        ZoneBorders(3475, 4817, 3503, 4846),
        Altar.WATER, "Water Runes", "water", true),
    Earth(ZoneBorders(3252,3420,3254,3422),
        listOf<ZoneBorders>(ZoneBorders(3283, 3431, 3286, 3433),
            ZoneBorders(3304, 3473, 3304, 3474)),
        ZoneBorders(2635, 4816, 2680, 4854),
        Altar.EARTH, "Earth Runes", "earth",  true),
    Fire(ZoneBorders(3269,3166,3271,3169),
        listOf<ZoneBorders>(ZoneBorders(3312, 3253, 3313, 3253)),
        ZoneBorders(2568, 4824, 2603, 4850),
        Altar.FIRE, "Fire Runes", "fire",  true),
    MIND(ZoneBorders(2945,3368,2946,3370),
        listOf<ZoneBorders>(ZoneBorders(2987, 3422, 2988, 3424),
            ZoneBorders(2973, 3440, 2974, 3443), ZoneBorders(2980, 3513, 2980, 3515)),
        ZoneBorders(2744, 4809, 2805, 4857),
        Altar.MIND, "Mind Runes", "mind", true),
    BODY(ZoneBorders(3093,3493,3097,3497),
        listOf<ZoneBorders>(ZoneBorders(3075, 3501, 3078, 3503),
            ZoneBorders(3071, 3443, 3072, 3446), ZoneBorders(3052, 3443, 3054, 3443)),
        ZoneBorders(2496, 4824, 2543, 4860),
        Altar.BODY, "Body Runes", "body",  true),

    CHAOS(ZoneBorders(3093,3493,3097,3497),
        listOf<ZoneBorders>(
            ZoneBorders(3082, 3520, 3084, 3520),
            ZoneBorders(SimpleRunecrafting.SPECIAL_ZONE, SimpleRunecrafting.SpecialZones.CROSS_WILDY.zoneID, 0, 0),
            ZoneBorders(3080, 3523, 3083, 3523),
            ZoneBorders(3058, 3590, 3058, 3592)),
        ZoneBorders(2253, 4824, 2295, 4853),
        Altar.CHAOS, "Chaos Runes", "chaos",  false),

    NATURE(ZoneBorders(2385, 4456, 2386, 4459),
        listOf<ZoneBorders>(ZoneBorders(2407, 4444, 2409, 4445),
            ZoneBorders(2411, 4433, 2413, 4435),
            ZoneBorders(SimpleRunecrafting.SPECIAL_ZONE, SimpleRunecrafting.SpecialZones.FAIRY_RING.zoneID, 18, SimpleRunecrafting.Rings.CKR.ordinal),
            ZoneBorders(2801, 3002, 2801, 3002),// obj 14130: 2801, 3003
            ZoneBorders(2868, 3021, 2869, 3021)),
        ZoneBorders(2387, 4829, 2412, 4853),
        Altar.NATURE, "Nature Runes", "nat",  false),

    COSMIC(ZoneBorders(2385, 4456, 2386, 4459),
        listOf<ZoneBorders>(ZoneBorders(2407, 4444, 2409, 4445),
            ZoneBorders(2419, 4412, 2420, 4418),
            ZoneBorders(2378, 4403, 2379, 4404),
            ZoneBorders(2407, 4379, 2409, 4379)),
        ZoneBorders(2114, 4804, 2174, 4876),
        Altar.COSMIC, "Cosmic Runes", "cos",  false),

    LAW(ZoneBorders(3092,3242,3094,3245),
        listOf<ZoneBorders>(ZoneBorders(3068, 3276, 3069, 3278),
            ZoneBorders(3045, 3234, 3050, 3236),
            ZoneBorders(SimpleRunecrafting.SPECIAL_ZONE, SimpleRunecrafting.SpecialZones.BOARD_SHIP.zoneID, 12, 0),
            ZoneBorders(2834, 3335, 2834, 3335),
            ZoneBorders(2859, 3350, 2860, 3353),
            ZoneBorders(2857, 3379, 2859, 3379)),
        ZoneBorders(2437, 4805, 2491, 4856),
        Altar.LAW, "Law Runes", "law",  false)

}