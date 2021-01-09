package plugin.command.rottenpotato

import core.game.container.impl.BankContainer
import core.game.node.entity.player.Player
import core.game.node.entity.player.info.login.PlayerParser
import core.game.node.entity.player.link.RunScript
import core.game.world.ImmerseWorld
import core.game.world.repository.Repository
import core.plugin.InitializablePlugin
import plugin.ai.AIPlayer
import plugin.ai.AIRepository
import plugin.dialogue.DialoguePlugin
import plugin.stringtools.colorize

@InitializablePlugin
/**
 * Rotten Potato "RS HD" option menu
 * @author Ceikry
 */
class RottenPotatoRSHDDialogue(player: Player? = null) : DialoguePlugin(player) {
    val ID = 38575793
    override fun newInstance(player: Player?): DialoguePlugin {
        return RottenPotatoRSHDDialogue(player)
    }

    override fun open(vararg args: Any?): Boolean {
        options("Wipe Bots", "Spawn Bots", "Force Log Players", "View Bank", "Copy Inventory")
        stage = 0
        return true
    }

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        when(stage){
            0 -> {
                when (buttonId) {
                    //Wipe Bots
                    1 -> AIRepository.PulseRepository.toTypedArray().forEach { it.stop(); it.botScript.bot.clear(); AIPlayer.deregister(it.botScript.bot.uid) }.also { player.sendMessage(colorize("%RBots wiped.")); end() }
                    //Spawn Bots
                    2 -> ImmerseWorld.init().also { player.sendMessage(colorize("%RBots Respawning...")); end() }
                    //Force Log All Online Players
                    3 -> {
                        Repository.disconnectionQueue.clear().also { end() }
                        Repository.players.toArray().forEach {
                            val p = it.asPlayer()
                            if (p != null && !p.isArtificial()) { // Should never be null.
                                p.removeAttribute("combat-time")
                                p.clear()
                                PlayerParser.save(p)
                                p.getDetails().save()
                            }
                        }
                    }
                    //View Bank
                    4 -> {
                        end()
                        player.setAttribute("runscript", object : RunScript() {
                            override fun handle(): Boolean {
                                val other = Repository.getPlayerByName(getValue().toString().toLowerCase().replace(" ","_"))
                                if(other == null){
                                    player.sendMessage(colorize("%RInvalid player name."))
                                    return true
                                }

                                other.bank.open(player)
                                return true
                            }
                        })
                        player.dialogueInterpreter.sendInput(true, "Enter the player name:")
                    }
                    //View Inventory
                    5 -> {
                        end()
                        player.setAttribute("runscript", object : RunScript() {
                            override fun handle(): Boolean {
                                val other = Repository.getPlayerByName(getValue().toString().toLowerCase().replace(" ","_"))
                                if(other == null){
                                    player.sendMessage(colorize("%RInvalid player name."))
                                    return true
                                }

                                player.inventory.clear()
                                player.inventory.addAll(other.inventory)
                                return true
                            }
                        })
                        player.dialogueInterpreter.sendInput(true, "Enter the player name:")
                    }
                }
            }
        }
        return true
    }

    override fun getIds(): IntArray {
        return intArrayOf(ID)
    }
}