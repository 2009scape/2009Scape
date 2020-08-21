package plugin.quest.members.thefremenniktrials

import core.game.node.entity.Entity
import core.game.node.entity.npc.NPC
import core.game.node.entity.player.Player
import core.game.node.item.Item

class Draugen(val player: Player?) : NPC(1279,player?.location?.transform(1,0,0)){

    override fun init() {
        super.init()
        isRespawn = false
    }

    override fun handleTickActions() {
        super.handleTickActions()
        if(!properties.combatPulse.isAttacking){
            properties.combatPulse.attack(player)
        }
    }

    override fun finalizeDeath(killer: Entity?) {
        super.finalizeDeath(killer)
        player?.setAttribute("/save:fremtrials:draugen-killed",true)
        player?.removeAttribute("fremtrials:draugen-loc")
        player?.inventory?.remove(Item(3696))
        player?.inventory?.add(Item(3697))
    }
}