package plugin.quest.members.thefremenniktrials

import core.game.node.entity.player.Player
import core.game.node.entity.player.info.PlayerDetails
import core.game.node.item.Item
import core.plugin.InitializablePlugin
import plugin.dialogue.DialoguePlugin

@InitializablePlugin
class CouncilWorkerDialogue(player: Player? = Player(PlayerDetails("",""))) : DialoguePlugin(player){
    override fun open(vararg args: Any?): Boolean {
        if(args.size > 1){
            npc("Ta very much like. That'll hit the spot nicely.. Here,","You can have this. I picked it up as a souvenir on me","last holz")
            player?.inventory?.add(Item(3713)); player?.inventory?.remove(Item(1917)); stage = 10
            stage = 100;
        } else {
            npc("'Ello there.")
            stage = 0;
        }
        return true
    }

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        when(stage){
            0 -> if(player?.questRepository?.getStage("Fremennik Trials")!! > 0){
                    player("I know this is an odd question, but are you","a member of the elder council?")
                    stage++
                 } else {
                    end()
                 }
            1 -> {npc("'fraid not, ${if(player?.appearance?.isMale!!) "sir" else "miss"}"); stage++}
            2 -> {npc("Say, would you do me a favor? I'm quite parched.","If you bring me a beer, I'll make it worthwhile.");stage++}
            3 -> if(player?.inventory?.containsItem(Item(1917))!!){
                    player("Oh, I have one here! Take it.")
                    stage++
                 } else {
                    end()
                 }
            4 -> {npc("Oh, thank you much ${if(player?.appearance?.isMale!!) "sir" else "miss"}");stage++}
            5 -> {npc("Ta very much like. That'll hit the spot nicely.. Here,","You can have this. I picked it up as a souvenir on me","last holz");player?.inventory?.add(Item(3713)); player?.inventory?.remove(Item(1917)); stage = 100}

            100 -> end()

        }
        return true
    }

    override fun newInstance(player: Player?): DialoguePlugin {
        return CouncilWorkerDialogue(player)
    }

    override fun getIds(): IntArray {
        return intArrayOf(1287)
    }

}