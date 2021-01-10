package core.net.packet.`in`

import core.game.interaction.DestinationFlag
import core.game.interaction.MovementPulse
import core.game.node.entity.player.Player
import core.game.node.item.GroundItemManager
import core.game.world.map.Location
import core.net.packet.IncomingPacket
import core.net.packet.IoBuffer
import core.tools.Items
import plugin.skill.firemaking.FireMakingPulse

/**
 * Handles the Item -> Ground Item packet
 * Decided not to include it with the rest of ItemActionPacket because it functions a bit differently from the rest of that
 * Will also require its own handlers instead of the normal UseWithHandler. Kind of annoying especially considering there's very few
 * Interactions that actually make use of this packet.
 * @author Ceikry
 */
class ItemOnGroundItemPacket : IncomingPacket {
    override fun decode(player: Player?, opcode: Int, buffer: IoBuffer?) {
        buffer ?: return
        player ?: return
        val x = buffer.leShortA
        val slot = buffer.leShort
        val usedItemId = buffer.leShort
        val usedWithItemId = buffer.leShort
        val y = buffer.leShortA
        buffer.int //uncertain what the use for this int is. My suspicion was region ID but no variety of the int data type matches up with the region id

        val GILocation = Location.create(x,y,player.location.z)

        var usedWith = GroundItemManager.get(usedWithItemId, GILocation, null) ?: GroundItemManager.get(usedWithItemId, GILocation, player)
        usedWith ?: return

        var used = player.inventory.get(slot)
        if(used.id != usedItemId) return


        /**
         * I'm just gonna put the handler for tinderbox -> logs here. Yes I know it's lazy, etc, etc but frankly the number of interactions
         * that are actually item -> ground item are so few it just doesn't make sense to make a whole system for it.
         */
        player.pulseManager.run(object : MovementPulse(player,GILocation, DestinationFlag.ITEM){
            override fun pulse(): Boolean {
                if(used.id == Items.TINDERBOX_590 || used.id == Items.GOLDEN_TINDERBOX_2946){
                    when(usedWithItemId){
                        Items.LOGS_1511,Items.ACHEY_TREE_LOGS_2862,Items.ARCTIC_PINE_LOGS_10810,Items.MAGIC_LOGS_1513,Items.MAHOGANY_LOGS_6332,Items.MAPLE_LOGS_1517,Items.YEW_LOGS_1515,Items.TEAK_LOGS_6333,Items.OAK_LOGS_1521,Items.WILLOW_LOGS_1519
                        -> {
                            player.pulseManager.run(FireMakingPulse(player,usedWith.asItem(),usedWith))
                        }
                        else -> player.sendMessage("Nothing interesting happens.")
                    }
                } else {
                    player.sendMessage("Nothing interesting happens.")
                }
                return true
            }
        })
    }

}