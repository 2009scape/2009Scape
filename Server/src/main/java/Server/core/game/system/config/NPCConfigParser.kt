package core.game.system.config

import core.ServerConstants
import core.cache.def.impl.NPCDefinition
import core.game.node.entity.combat.CombatStyle
import core.game.node.entity.impl.Animator
import core.game.system.SystemLogger
import core.game.world.update.flag.context.Animation
import org.json.simple.JSONArray
import org.json.simple.JSONObject
import org.json.simple.parser.JSONParser
import plugin.ttrail.ClueLevel
import java.io.FileReader

class NPCConfigParser {
    companion object{
        const val WEAKNESS = "weakness"

        /**
         * The lifepoints attribute.
         */
        const val LIFEPOINTS = "lifepoints"

        /**
         * The attack level attribute.
         */
        const val ATTACK_LEVEL = "attack_level"

        /**
         * The strength level attribute.
         */
        const val STRENGTH_LEVEL = "strength_level"

        /**
         * The defence level attribute.
         */
        const val DEFENCE_LEVEL = "defence_level"

        /**
         * The range level attribute.
         */
        const val RANGE_LEVEL = "range_level"

        /**
         * The range level attribute.
         */
        const val MAGIC_LEVEL = "magic_level"

        /**
         * The examine attribute.
         */
        const val EXAMINE = "examine"

        /**
         * The slayer task attribute.
         */
        const val SLAYER_TASK = "slayer_task"

        /**
         * The poisonous attribute.
         */
        const val POISONOUS = "poisonous"

        /**
         * The aggressive attribute.
         */
        const val AGGRESSIVE = "aggressive"

        /**
         * The respawn delay attribute.
         */
        const val RESPAWN_DELAY = "respawn_delay"

        /**
         * The attack speed attribute.
         */
        const val ATTACK_SPEED = "attack_speed"

        /**
         * The poison immune attribute.
         */
        const val POISON_IMMUNE = "poison_immune"

        /**
         * The bonuses attribute.
         */
        const val BONUSES = "bonuses"

        /**
         * The start graphic attribute.
         */
        const val START_GRAPHIC = "start_gfx"

        /**
         * The projectile attribute.
         */
        const val PROJECTILE = "projectile"

        /**
         * The end graphic attribute.
         */
        const val END_GRAPHIC = "end_gfx"

        /**
         * The combat style attribute.
         */
        const val COMBAT_STYLE = "combat_style"

        /**
         * The aggressive radius attribute.
         */
        const val AGGRESSIVE_RADIUS = "agg_radius"

        /**
         * The slayer experience attribute.
         */
        const val SLAYER_EXP = "slayer_exp"

        /**
         * The amount to poison.
         */
        const val POISON_AMOUNT = "poison_amount"

        /**
         * The movement radius.
         */
        const val MOVEMENT_RADIUS = "movement_radius"

        /**
         * The spawn animation.
         */
        const val SPAWN_ANIMATION = "spawn_animation"

        /**
         * The start height.
         */
        const val START_HEIGHT = "start_height"

        /**
         * The projectile height.
         */
        const val PROJECTILE_HEIGHT = "prj_height"

        /**
         * The end height.
         */
        const val END_HEIGHT = "end_height"

        /**
         * The clue level.
         */
        const val CLUE_LEVEL = "clue_level"

        /**
         * The spell id.
         */
        const val SPELL_ID = "spell_id"

        /**
         * The combat audio.
         */
        const val COMBAT_AUDIO = "combat_audio"

        /**
         * The melee-attack anim.
         */
        const val MELEE_ANIMATION = "melee_animation"

        /**
         * The defence anim.
         */
        const val DEFENCE_ANIMATION = "defence_animation"

        /**
         * The death anim.
         */
        const val DEATH_ANIMATION = "death_animation"

        /**
         * The range anim.
         */
        const val RANGE_ANIMATION = "range_animation"

        /**
         * The magic anim.
         */
        const val MAGIC_ANIMATION = "magic_animation"

        /**
         * The combat style protected from.
         */
        const val PROTECT_STYLE = "protect_style"

    }
    val parser = JSONParser()
    var reader: FileReader? = null
    fun load(){
        var count = 0
        reader = FileReader(ServerConstants.CONFIG_PATH + "npc_configs.json")
        val configlist = parser.parse(reader) as JSONArray
        for(config in configlist){
            val e = config as JSONObject
            val def = NPCDefinition.forId(e["id"].toString().toInt())
            val configs = def.configurations
            e.map {
                if(it.value.toString().isNotEmpty() && it.value.toString() != "null") {
                    when (it.key.toString()) {
                        //animations
                        "melee_animation",
                        "range_animation",
                        "magic_animation",
                        "death_animation",
                        "defence_animation" -> configs.put(it.key.toString(), Animation(it.value.toString().toInt(), Animator.Priority.HIGH))

                        //combat/protect style
                        "combat_style",
                        "protect_style" -> configs.put(it.key.toString(), CombatStyle.values()[it.value.toString().toInt()])


                        "clue_level" -> configs.put(it.key.toString(), ClueLevel.values()[it.value.toString().toInt()])
                        "name", "examine" -> configs.put(it.key.toString(), it.value.toString())
                        "combat_audio", "bonuses" -> configs.put(it.key.toString(), it.value.toString().split(",").map { v -> v.toInt() }.toIntArray())
                        "force_talk" -> configs.put(it.key.toString(),it.value.toString())

                        //ints
                        "spawn_animation",
                        "id",
                        "lifepoints",
                        "attack_level",
                        "strength_level",
                        "defence_level",
                        "range_level",
                        "movement_radius",
                        "agg_radius",
                        "attack_speed",
                        "poison_amount",
                        "respawn_delay",
                        "start_gfx",
                        "projectile",
                        "end_gfx",
                        "weakness",
                        "slayer_task",
                        "start_height",
                        "prj_height",
                        "end_height",
                        "spell_id",
                        "magic_level" -> configs.put(it.key.toString(), if (it.value.toString().isEmpty()) Unit else it.value.toString().toInt())

                        //doubles
                        "slayer_exp" -> configs.put(it.key.toString(), it.value.toString().toDouble())

                        //booleans
                        "safespot",
                        "aggressive",
                        "poisonous",
                        "poison_immune" -> configs.put(it.key.toString(), it.value.toString().toBoolean())
                        else -> SystemLogger.log("!!!! unhandled key for npc config: ${it.key.toString()} !!!!")
                    }
                }
            }
            count++
        }
        SystemLogger.log("[Config Parser]: Parsed $count NPC configurations")
    }
}