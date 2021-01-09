package plugin.command.rottenpotato

import core.cache.def.impl.ItemDefinition
import core.game.interaction.OptionHandler
import core.game.node.Node
import core.game.node.entity.player.Player
import core.game.system.command.CommandSystem
import core.plugin.InitializablePlugin
import core.plugin.Plugin
import core.tools.Items
import plugin.stringtools.colorize

/**
 * Handles the Rotten Potato options
 * @author Ceikry
 */
private val ROTTEN_POTATO_OPTIONS = arrayOf("RS HD","Heal","Extra","Commands")
@InitializablePlugin
class RottenPotatoOptionHandler : OptionHandler() {
    override fun newInstance(arg: Any?): Plugin<Any> {
        for(option in ROTTEN_POTATO_OPTIONS) {
            ItemDefinition.forId(Items.ROTTEN_POTATO_5733).handlers["option:${option.toLowerCase()}"] = this
        }
        return this;
    }

    override fun handle(player: Player?, node: Node?, option: String?): Boolean {
        player ?: return false
        node ?: return false
        option ?: return false

        when(option){
            "rs hd" -> player.dialogueInterpreter.open(RottenPotatoRSHDDialogue().ID)
            "heal" -> player.fullRestore().also { player.sendMessage(colorize("%RAll healed!")) }
            "extra" -> player.dialogueInterpreter.open(RottenPotatoExtraDialogue().ID)
            "commands" -> {
                CommandSystem.commandSystem.parse(player,"commands")
            }
        }
        return true
    }

}