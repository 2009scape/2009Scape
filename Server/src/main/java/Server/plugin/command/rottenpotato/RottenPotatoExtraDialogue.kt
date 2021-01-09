package plugin.command.rottenpotato

import core.game.node.entity.npc.NPC
import core.game.node.entity.player.Player
import core.game.node.entity.player.link.RunScript
import core.game.world.map.RegionManager
import core.game.world.repository.Repository
import core.plugin.InitializablePlugin
import plugin.dialogue.DialoguePlugin
import plugin.stringtools.colorize

@InitializablePlugin
/**
 * Rotten Potato Right click -> Extra dialogue menu
 * @author Ceikry
 */
class RottenPotatoExtraDialogue(player: Player? = null) : DialoguePlugin(player) {
    val ID = 38575794
    val AMEs = arrayOf("chicken","Sandwich Lady","tree spirit","rick turpentine","Genie")
    val BossIDs = arrayOf(50,8350,8133,2745)
    val BossNames = arrayOf("KBD","Tormented Demon","Corporeal Beast","Jad")

    override fun newInstance(player: Player?): DialoguePlugin {
        return RottenPotatoExtraDialogue(player)
    }

    override fun open(vararg args: Any?): Boolean {
        options("Send Player Notification","Targeted AME","Spawn Boss","Force Area NPC Chat","Kill All Nearby NPCs")
        stage = 0
        return true
    }

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        when(stage) {

            0 -> when (buttonId) {
               //Send Player Notification
               1 -> {
                   end()
                   player.setAttribute("runscript", object : RunScript() {
                       override fun handle(): Boolean {
                           val message = getValue().toString()
                           for (p in Repository.players) {
                               p ?: continue
                               p.packetDispatch.sendString(colorize("%Y${message.replace("_", " ").capitalize()}"), 754, 5)
                           }
                           return true
                       }
                   })
                   player.dialogueInterpreter.sendLongInput("Enter the notification message:")
               }
               //Targeted AME
               2 -> {
                   options(*AMEs)
                   stage = 100
               }

               //Boss Spawn menu
               3 -> {
                    options(*BossNames)
                    stage = 200
               }

               //Force Area NPC Chat
               4 -> {
                   end()
                   player.setAttribute("runscript", object : RunScript() {
                       override fun handle(): Boolean {
                           val msg = getValue().toString()
                           RegionManager.getLocalNpcs(player).forEach {
                               it.sendChat(msg)
                           }
                           return true
                       }
                   })
                   player.dialogueInterpreter.sendLongInput("Enter the chat message:")
               }

               //Kill all nearby NPCs
               5 -> {
                   end()
                   RegionManager.getLocalNpcs(player).forEach {
                       it.finalizeDeath(player)
                   }
               }

           }
                //AME Spawning
            100 -> {
                end()
                player.setAttribute("runscript", object : RunScript() {
                    override fun handle(): Boolean {
                        val other = Repository.getPlayerByName(getValue().toString().toLowerCase().replace(" ", "_"))
                        if (other == null) {
                            player.sendMessage(colorize("%RInvalid player name."))
                            return true
                        }

                        other?.antiMacroHandler?.fireEvent(AMEs[buttonId - 1])

                        return true
                    }
                })
                player.dialogueInterpreter.sendInput(true, "Enter player name:")
            }

           //Spawn boss
           200 -> {
               end()
               val id = BossIDs[buttonId - 1]
               val boss = NPC(id,player.location)
               boss.isRespawn = false
               boss.isAggressive = true
               boss.init()
           }
        }
        return true
    }

    override fun getIds(): IntArray {
        return intArrayOf(ID)
    }

}