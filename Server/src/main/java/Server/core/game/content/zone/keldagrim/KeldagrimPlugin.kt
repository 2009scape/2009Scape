package core.game.content.zone.keldagrim

import core.cache.def.impl.ObjectDefinition
import core.game.interaction.OptionHandler
import core.game.node.Node
import core.game.node.entity.player.Player
import core.game.world.map.Location
import core.plugin.Initializable
import core.plugin.Plugin
import core.game.content.dialogue.DialoguePlugin

/**
 * File that contains several plugins relating to Keldagrim,
 * most notably the KeldagrimOptionHandlers and GETrapdoorDialogue
 * Anything that wasn't significant enough for its own individual file, tbh.
 * @author Ceikry
 */

const val GETrapdoorDialogueID = 22381232

/**
 * Handles various options around keldagrim that weren't significant enough for a separate file
 */
@Initializable
class KeldagrimOptionHandlers : OptionHandler() {
    override fun handle(player: Player?, node: Node?, option: String?): Boolean {
        player ?: return false
        node ?: return false
        option ?: return false
        when(option){
            "go-through" -> {
                when (node.id) {
                    5973 -> player.properties.teleportLocation = Location.create(2838, 10125)
                    5998 -> player.properties.teleportLocation = Location.create(2780, 10161)
                }
            }
            "climb-up" -> {
                when(node.id){
                    9138 -> player.properties.teleportLocation = Location.create(2931, 10196, 0)
                }
            }
            "climb-down" -> {
                when(node.id){
                    9084 -> player.properties.teleportLocation = Location.create(1940, 4958, 0)
                }
            }
            "open" -> {
                when(node.id){
                    28094 -> player.dialogueInterpreter.open(GETrapdoorDialogueID)
                }
            }
            "enter" -> {
                when(node.id){
                    5014 -> player.properties.teleportLocation = Location.create(2730, 3713, 0)
                }
            }
        }
        return true
    }

    override fun newInstance(arg: Any?): Plugin<Any> {
        ObjectDefinition.forId(5973).handlers["option:go-through"] = this
        ObjectDefinition.forId(5998).handlers["option:go-through"] = this
        ObjectDefinition.forId(9084).handlers["option:climb-down"] = this
        ObjectDefinition.forId(9138).handlers["option:climb-up"] = this
        ObjectDefinition.forId(28094).handlers["option:open"] = this
        ObjectDefinition.forId(5014).handlers["option:enter"] = this
        return this
    }
}

/**
 * Dialogue used for the trapdoor in the grand exchange.
 */
@Initializable
class GETrapdoorDialogue(player: Player? = null) : DialoguePlugin(player){
    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        when(stage){
            0 -> end()
            10 -> when(buttonId){
                1 -> KeldagrimCartMethods.goToKeldagrim(player).also { end() }
                2 -> end()
            }
        }
        return true
    }

    override fun open(vararg args: Any?): Boolean {
        val keldagrimVisited = player.getAttribute("keldagrim-visited",false)
        if(keldagrimVisited){
           options("Travel to Keldagrim","Nevermind.")
            stage = 10
        } else {
            player.dialogueInterpreter.sendDialogue("Perhaps I should visit Keldagrim first.")
            stage = 0
        }
        return true
    }

    override fun newInstance(player: Player?): DialoguePlugin {
        return GETrapdoorDialogue(player)
    }

    override fun getIds(): IntArray {
        return intArrayOf(GETrapdoorDialogueID)
    }
}
