package plugin.ge

import core.cache.def.impl.ItemDefinition
import core.tools.Items

class BotPrices {

    companion object {
        @JvmStatic
        fun getPrice(id: Int): Int {
            return getPriceOverrides(id) ?: ItemDefinition.forId(id).value
        }

        @JvmStatic
        private fun getPriceOverrides(id: Int): Int? {
            return when (id) {
                Items.PURE_ESSENCE_7936 -> 50
                Items.BOW_STRING_1777 -> 250
                Items.MAGIC_LOGS_1513 -> 750
                Items.COWHIDE_1739 -> 250
                Items.DRAGON_BONES_536 -> 1250
                Items.GREEN_DRAGONHIDE_1753 -> 550
                Items.GRIMY_RANARR_207 -> 1214
                Items.GRIMY_AVANTOE_211 -> 453
                Items.GRIMY_CADANTINE_215 -> 232
                Items.GRIMY_DWARF_WEED_217 -> 86
                Items.GRIMY_GUAM_199 -> 50
                Items.GRIMY_HARRALANDER_205 -> 115
                Items.GRIMY_IRIT_209 -> 860
                Items.GRIMY_KWUARM_213 -> 334
                Items.GRIMY_LANTADYME_2485 -> 115
                Items.GRIMY_MARRENTILL_201 -> 250
                Items.LOBSTER_379 -> 268
                Items.RAW_LOBSTER_377 -> 265
                Items.LOOP_HALF_OF_A_KEY_987 -> 5250
                Items.TOOTH_HALF_OF_A_KEY_985 -> 4263
                Items.SWORDFISH_373 -> 400
                Items.RAW_SWORDFISH_371 -> 390
                Items.SHARK_385 -> 720
                Items.RAW_SHARK_383 -> 710
                else -> null
            }
        }
    }
}