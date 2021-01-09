package plugin.command.rottenpotato

import core.game.node.entity.combat.ImpactHandler
import core.game.node.entity.npc.NPC
import core.game.node.entity.player.Player
import core.plugin.InitializablePlugin
import plugin.dialogue.DialoguePlugin
import plugin.stringtools.colorize

/**
 * Rotten potato -> npc interaction menu
 * @author Ceikry
 */
@InitializablePlugin
class RPUseWithNPCDialogue(player: Player? = null) : DialoguePlugin(player) {
    var npc: NPC? = null
    val ID = 38575795
    override fun newInstance(player: Player?): DialoguePlugin {
        return RPUseWithNPCDialogue(player)
    }

    override fun open(vararg args: Any?): Boolean {
        npc = args[0] as NPC
        options("Remove NPC","Enable Respawning","Disable Respawning","Kill","Copy Appearance")
        stage = 0
        return true
    }

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        npc ?: return false
        when(buttonId){
            //remove NPC
            1 -> {
                end()
                npc.isRespawn = false
                npc.clear()
                player.sendMessage(colorize("%RNPC Cleared."))
            }
            //Enable Respawn
            2 -> {
                end()
                npc.isRespawn = true
                player.sendMessage(colorize("%RNPC Respawn Enabled"))
            }
            //Disable Respawn
            3 -> {
                end()
                npc.isRespawn = false
                player.sendMessage(colorize("%RNPC Respawn Disabled"))
            }
            //Kill
            4 -> {
                end()
                npc.impactHandler.manualHit(player,npc.skills.lifepoints,ImpactHandler.HitsplatType.NORMAL)
            }
            //Copy Appearance
            5 -> {
                end()
                player.appearance.transformNPC(npc.id)
            }
        }
        return true
    }

    override fun getIds(): IntArray {
        return intArrayOf(ID)
    }

}