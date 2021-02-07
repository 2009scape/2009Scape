package plugin.quest.members.familycrest

import core.game.node.entity.Entity
import core.game.node.entity.combat.CombatStyle
import core.game.node.entity.npc.AbstractNPC
import core.game.node.entity.player.Player
import core.game.node.entity.player.link.quest.Quest
import core.game.world.map.Location
import core.game.world.map.RegionManager


class ChronozonNPC(id: Int, location: Location?) : AbstractNPC(667, Location(3086, 9936, 0)){

    var targetPlayer: Player? = null

    override fun construct(id: Int, location: Location?, vararg objects: Any?): AbstractNPC {
        return ChronozonNPC(id, location)
    }

    override fun getIds(): IntArray {
        return intArrayOf(667)
    }

    override fun handleTickActions() {
        super.handleTickActions()
        if(RegionManager.getLocalPlayers(this).contains(targetPlayer)){

        }
    }

    fun setPlayer(player: Player){
        targetPlayer = player;
    }

}

//3086, 9936, 0