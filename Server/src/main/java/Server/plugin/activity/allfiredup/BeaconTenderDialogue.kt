package plugin.activity.allfiredup

import core.game.node.entity.npc.NPC
import core.game.node.entity.player.Player
import core.game.node.item.Item
import core.game.system.SystemLogger
import core.plugin.InitializablePlugin
import core.tools.Items
import plugin.dialogue.DialoguePlugin
import plugin.skill.Skills

private val VALID_LOGS = arrayOf(Items.LOGS_1511, Items.OAK_LOGS_1521, Items.WILLOW_LOGS_1519, Items.MAPLE_LOGS_1517, Items.YEW_LOGS_1515, Items.MAGIC_LOGS_1513)

@InitializablePlugin
//TODO: Add requirements for beacon keepers to watch beacons, most of the requirements were not possible to implement at the time of writing this.
class BeaconTenderDialogue(player: Player? = null) : DialoguePlugin(player) {
    var index = 0

    override fun newInstance(player: Player?): DialoguePlugin {
        return BeaconTenderDialogue(player)
    }

    override fun open(vararg args: Any?): Boolean {
        index = getIndexOf((args[0] as NPC).originalId)
        if(index == AFUBeacon.GWD.ordinal && player.skills.getLevel(Skills.SUMMONING) < 81){
            npc("Awwf uurrrhur","(You need 81 Summoning to communicate with Nanuq.)")
            stage = 1000
            return true
        }
        if(index == AFUBeacon.MONASTERY.ordinal && player.skills.getLevel(Skills.PRAYER) < 53){
            npc("I will aid you when your devotion is","strong enough.","(You need 53 Prayer for him to watch the beacon.)")
            stage = 1000
            return true
        }
        npc = (args[0] as NPC).getShownNPC(player)
        npc("Hello, adventurer.")
        return true
    }

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        val beacon = AFUBeacon.values()[index]
        val logs = getLogs(player,5)
        val session: AFUSession? = player.getAttribute("afu-session",null)
        when(stage){
            0 -> player("Hello!").also { stage++ }
            1 -> if(beacon.getState(player) == BeaconState.LIT && session?.isWatched(index) == false){
                    options("Can you watch this beacon for me?","Nevermind.").also { stage = 10 }
                 } else {
                    npc("Carry on, adventurer.").also { stage = 1000 }
                 }
            10 -> when(buttonId){
                    1 -> player("Can you watch this beacon for me?").also { stage++ }
                    2 -> player("Nevermind.").also { stage = 1000 }
                  }
            11 -> npc("Certainly, adventurer. Do you have logs for me?").also { stage++ }
            12 -> if(logs.id != 0){
                    player("Yes, I do!").also { stage++ }
                  } else {
                    player("No, I don't.").also { stage = 1000 }
                  }
            13 -> npc("Great, hand them over.").also { stage++ }
            14 -> player("Here you go!").also {
                    player.inventory.remove(logs)
                    session?.setWatcher(index,logs)
                    stage = 1000
                  }

            1000 -> end()
        }
        return true
    }

    fun getIndexOf(id: Int): Int{
        if(id == 8065) return 0
        if(id == 8066) return 1
        for(index in ids.indices){
            if(ids[index] == id) return index + 2
        }
        return -1
    }

    fun getLogs(player: Player, amount: Int): Item {
        var logId = 0
        for (log in VALID_LOGS) if (player.inventory.getAmount(log) >= amount) {logId = log; break}
        return Item(logId,amount)
    }

    override fun getIds(): IntArray {
        return intArrayOf(8067,8068,8069,8070,8071,8072,8073,8074,8075,8076)
    }

}