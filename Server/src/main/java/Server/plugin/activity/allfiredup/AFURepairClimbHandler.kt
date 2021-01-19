package plugin.activity.allfiredup

import core.cache.def.impl.ObjectDefinition
import core.game.interaction.OptionHandler
import core.game.node.Node
import core.game.node.entity.impl.ForceMovement
import core.game.node.entity.player.Player
import core.game.node.item.Item
import core.game.world.map.Direction
import core.game.world.map.Location
import core.game.world.update.flag.context.Animation
import core.plugin.InitializablePlugin
import core.plugin.Plugin
import core.tools.Items
import plugin.skill.Skills
import plugin.skill.construction.NailType
import java.util.*

/**
 * Handles repairing and climbing of the 3 beacon shortcuts needed to access them
 * @author Ceikry
 */
@InitializablePlugin
class AFURepairClimbHandler : OptionHandler() {
    override fun newInstance(arg: Any?): Plugin<Any> {
        ObjectDefinition.forId(38480).handlers["option:repair"] = this
        ObjectDefinition.forId(38470).handlers["option:repair"] = this
        ObjectDefinition.forId(38494).handlers["option:repair"] = this
        ObjectDefinition.forId(38469).handlers["option:climb"] = this
        ObjectDefinition.forId(38471).handlers["option:climb"] = this
        ObjectDefinition.forId(38486).handlers["option:climb"] = this
        ObjectDefinition.forId(38481).handlers["option:climb"] = this
        ObjectDefinition.forId(38469).handlers["option:climb"] = this
        return this
    }

    override fun handle(player: Player?, node: Node?, option: String?): Boolean {
        player ?: return false
        node ?: return false
        var rco: RepairClimbObject = RepairClimbObject.GWD
        for(ent in RepairClimbObject.values()) if(ent.destinationDown?.withinDistance(player.location,2) == true || ent.destinationUp?.withinDistance(player.location,2) == true) rco = ent

        if(option.equals("repair")) repair(player,rco) else climb(player,rco,node.location)
        return true
    }

    private fun repair(player: Player,rco: RepairClimbObject){
        val skill = rco.levelRequirement?.first ?: 0
        val level = rco.levelRequirement?.second ?: 0
        if(player.skills.getLevel(skill) < level){
            player.dialogueInterpreter.sendDialogue("You need level $level ${Skills.SKILL_NAME[skill]} for this.")
            return
        }

        var requiresNeedle = false

        val requiredItems = when(rco){
            RepairClimbObject.DEATH_PLATEAU -> {
                arrayOf(Item(Items.PLANK_960,2))
            }

            RepairClimbObject.BURTHORPE -> {
                arrayOf(Item(Items.IRON_BAR_2351,2))
            }

            RepairClimbObject.GWD -> {
                requiresNeedle = true
                arrayOf(Item(Items.JUTE_FIBRE_5931,3))
            }

            RepairClimbObject.TEMPLE -> {
                arrayOf(Item(Items.IRON_BAR_2351,2))
            }
        }

        if(requiresNeedle){
            if(player.inventory.containsItem(Item(Items.NEEDLE_1733)) && player.inventory.containItems(*requiredItems.map { it.id }.toIntArray())) {
                player.inventory.remove(*requiredItems)
                if (Random().nextBoolean()) player.inventory.remove(Item(Items.NEEDLE_1733))
            } else {
                player.dialogueInterpreter.sendDialogue("You need a needle and ${requiredItems.map { "${it.amount} ${it.name.toLowerCase()}s" }.toString().replace("[","").replace("]","")} for this.")
                return
            }
        } else {
            if(player.inventory.containsItem(Item(Items.HAMMER_2347)) && player.inventory.containItems(*requiredItems.map { it.id }.toIntArray())) {
                val nails = NailType.get(player,4)
                if(nails == null && rco == RepairClimbObject.DEATH_PLATEAU){
                    player.dialogueInterpreter.sendDialogue("You need 4 nails for this.")
                    return
                } else if (rco == RepairClimbObject.DEATH_PLATEAU){
                    player.inventory.remove(Item(nails.itemId,4))
                }
                player.inventory.remove(*requiredItems)
            } else {
                player.dialogueInterpreter.sendDialogue("You need a hammer and ${requiredItems.map { "${it.amount} ${it.name.toLowerCase()}s" }.toString().replace("[","").replace("]","")} for this.")
                return
            }
        }

        player.varpManager.get(rco.varp).setVarbit(rco.offset,1).send(player)
        player.varpManager.flagSave(rco.varp)
    }

    private fun climb(player: Player, rco: RepairClimbObject, location: Location){
        ForceMovement.run(player,location,rco.getOtherLocation(player),rco.getAnimation(player),rco.getAnimation(player),rco.getDirection(player),20).endAnimation = Animation(-1)
    }

    private enum class RepairClimbObject(val varp: Int, val offset: Int, val destinationUp: Location?, val destinationDown: Location?, val levelRequirement: Pair<Int,Int>?){
        DEATH_PLATEAU(1282,15,Location.create(2949, 3623, 0),Location.create(2954, 3623, 0), Pair(Skills.CONSTRUCTION,42)),
        BURTHORPE(1282,14,Location.create(2941, 3563, 0),Location.create(2934, 3563, 0),Pair(Skills.SMITHING,56)),
        GWD(1283,27,null,null,Pair(Skills.CRAFTING,60)),
        TEMPLE(1287,29,Location.create(2949, 3835, 0),Location.create(2956, 3835, 0),Pair(Skills.SMITHING,64));

        fun getOtherLocation(player: Player): Location?{
            if(player.location == destinationDown) return destinationUp
            else return destinationDown
        }

        fun getAnimation(player: Player): Animation {
            if(getOtherLocation(player) == destinationDown) return Animation(1148)
            else return Animation(740)
        }

        fun getDirection(player: Player): Direction {
            if(this == BURTHORPE){
                return Direction.EAST
            } else return Direction.WEST
        }

        fun isRepaired(player: Player): Boolean{
            return player.varpManager.get(varp).getVarbit(offset)?.value == 1
        }
    }

    companion object {
        fun isRepaired(player: Player, beacon: AFUBeacon): Boolean{
            if(beacon == AFUBeacon.DEATH_PLATEAU) return RepairClimbObject.DEATH_PLATEAU.isRepaired(player)
            if(beacon == AFUBeacon.BURTHORPE) return RepairClimbObject.BURTHORPE.isRepaired(player)
            if(beacon == AFUBeacon.GWD) return RepairClimbObject.GWD.isRepaired(player)
            if(beacon == AFUBeacon.TEMPLE) return RepairClimbObject.TEMPLE.isRepaired(player)
            else return true
        }
    }

}