package core.game.system.config

import core.ServerConstants
import core.cache.def.impl.ItemDefinition
import core.game.node.entity.impl.Animator
import core.game.node.entity.player.link.audio.Audio
import core.game.system.SystemLogger
import core.game.world.update.flag.context.Animation
import org.json.simple.JSONArray
import org.json.simple.JSONObject
import org.json.simple.parser.JSONParser
import java.io.FileReader

class ItemConfigParser {
    companion object{
        /**
         * The tradeable item configuration key.
         */
        const val TRADEABLE = "tradeable"

        /**
         * The lendable item configuration key.
         */
        const val LENDABLE = "lendable"

        /**
         * If the item is spawnable.
         */
        const val SPAWNABLE = "spawnable"

        /**
         * The destroy item configuration key.
         */
        const val DESTROY = "destroy"

        /**
         * The two-handed item configuration key.
         */
        const val TWO_HANDED = "two_handed"

        /**
         * The high-alchemy price item configuration key.
         */
        const val HIGH_ALCHEMY = "high_alchemy"

        /**
         * The low-alchemy price item configuration key.
         */
        const val LOW_ALCHEMY = "low_alchemy"

        /**
         * The shop price item configuration key.
         */
        const val SHOP_PRICE = "shop_price"

        /**
         * The grand exchange price item configuration key.
         */
        const val GE_PRICE = "grand_exchange_price"

        /**
         * The examine item configuration key.
         */
        const val EXAMINE = "examine"

        /**
         * The weight item configuration key.
         */
        const val WEIGHT = "weight"

        /**
         * The bonuses item configuration key.
         */
        const val BONUS = "bonuses"

        /**
         * The absorb item configuration key.
         */
        const val ABSORB = "absorb"

        /**
         * The equipment slot item configuration key.
         */
        const val EQUIP_SLOT = "equipment_slot"

        /**
         * The attack speed item configuration key.
         */
        const val ATTACK_SPEED = "attack_speed"

        /**
         * The remove hair item configuration key.
         */
        const val REMOVE_HEAD = "remove_head"

        /**
         * The remove beard item configuration key.
         */
        const val REMOVE_BEARD = "remove_beard"

        /**
         * The remove sleeves item configuration key.
         */
        const val REMOVE_SLEEVES = "remove_sleeves"

        /**
         * The stand anim item configuration key.
         */
        const val STAND_ANIM = "stand_anim"

        /**
         * The stand-run anim item configuration key.
         */
        const val STAND_TURN_ANIM = "stand_turn_anim"

        /**
         * The walk anim item configuration key.
         */
        const val WALK_ANIM = "walk_anim"

        /**
         * The run anim item configuration key.
         */
        const val RUN_ANIM = "run_anim"

        /**
         * The turn 180 anim item configuration key.
         */
        const val TURN180_ANIM = "turn180_anim"

        /**
         * The turn 90cw anim item configuration key.
         */
        const val TURN90CW_ANIM = "turn90cw_anim"

        /**
         * The turn 90ccw anim item configuration key.
         */
        const val TURN90CCW_ANIM = "turn90ccw_anim"

        /**
         * The weapon interface.
         */
        const val WEAPON_INTERFACE = "weapon_interface"

        /**
         * If the item has a special attack bar.
         */
        const val HAS_SPECIAL = "has_special"

        /**
         * The item's attack animations.
         */
        const val ATTACK_ANIMS = "attack_anims"

        /**
         * The items destroy message.
         */
        const val DESTROY_MESSAGE = "destroy_message"

        /**
         * The requirements.
         */
        const val REQUIREMENTS = "requirements"

        /**
         * The grand exchange buying limit attribute key.
         */
        const val GE_LIMIT = "ge_buy_limit"

        /**
         * The defence animation key.
         */
        const val DEFENCE_ANIMATION = "defence_anim"

        /**
         * The attack sound key.
         */
        const val ATTACK_AUDIO = "attack_audios"

        /**
         * The point price.
         */
        const val POINT_PRICE = "point_price"

        /**
         * If the item is bankable.
         */
        const val BANKABLE = "bankable"

        /**
         * If the item is a rare item.
         */
        const val RARE_ITEM = "rare_item"

        /**
         * The tokkul price of an item.
         */
        const val TOKKUL_PRICE = "tokkul_price"

        /**
         * The render animation id of an item.
         */
        const val RENDER_ANIM_ID = "render_anim"

        /**
         * the archery ticket price of an item.
         */
        const val ARCHERY_TICKET_PRICE = "archery_ticket_price"

    }

    val parser = JSONParser()
    var reader: FileReader? = null
    fun load(){
        var count = 0
        reader = FileReader(ServerConstants.CONFIG_PATH + "item_configs.json")
        val configlist = parser.parse(reader) as JSONArray
        for(config in configlist){
            val e = config as JSONObject
            val def = ItemDefinition.forId(e["id"].toString().toInt())
            val configs = def.configurations
            val requirements = HashMap<Int, Int>()
            e.map {
                if (it.value.toString().isNotEmpty() && it.value.toString() != "null") {
                    when (it.key.toString()) {
                        //Special cases
                        "defence_anim" -> configs.put(it.key.toString(), Animation(it.value.toString().toInt(), Animator.Priority.HIGH))
                        "shop_price" -> {def.value = (it.value.toString().toInt()); configs.put(it.key.toString(),it.value.toString().toInt())}
                        "requirements" -> { configs.put(it.key.toString(),requirements)
                            it.value.toString()
                                    .split("-")
                                    .map {
                                        en -> val tokens = en.replace("{", "").replace("}", "").split(",")
                                        requirements.put(tokens[0].toInt(),tokens[1].toInt())
                                    }
                            }
                        "attack_audio" -> configs.put(it.key.toString(),it.value.toString().split(",").map { i -> Audio(i.toInt()) }.toTypedArray())
                        "attack_anims" -> configs.put(it.key.toString(),it.value.toString().split(",").map { i -> Animation(i.toInt(), Animator.Priority.HIGH) }.toTypedArray())

                        //int arrays
                        "absorb",
                        "bonuses" -> configs.put(it.key.toString(),it.value.toString().split(",").map { i -> i.toInt() }.toIntArray())

                        //booleans
                        "fun_weapon",
                        "rare_item",
                        "bankable",
                        "two_handed",
                        "has_special",
                        "remove_sleeves",
                        "remove_beard",
                        "remove_head",
                        "destroy",
                        "lendable",
                        "tradeable" -> configs.put(it.key.toString(),it.value.toString().toBoolean())

                        //doubles
                        "weight" -> configs.put(it.key.toString(),it.value.toString().toDouble())

                        //ints
                        "point_price",
                        "tokkul_price",
                        "archery_ticket_price",
                        "ge_buy_limit",
                        "weapon_interface",
                        "attack_speed",
                        "equipment_slot",
                        "render_anim",
                        "turn90ccw_anim",
                        "turn90cw_anim",
                        "turn180_anim",
                        "run_anim",
                        "walk_anim",
                        "stand_anim",
                        "low_alchemy",
                        "high_alchemy",
                        "grand_exchange_price",
                        "id" -> configs.put(it.key.toString(),it.value.toString().toInt())

                        //Strings
                        "examine",
                        "destroy_message",
                        "name" -> configs.put(it.key.toString(),it.value.toString())
                    }
                }
            }
            count++
        }
        SystemLogger.log("[Config Parser]: Parsed $count item configs.")
    }
}