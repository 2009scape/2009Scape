package plugin.activity.fishingtrawler

import core.game.node.entity.player.Player

private const val configIndex = 391
private const val interfaceID = 366
private const val netOkayChild = 27
private const val netRippedChild = 28
private const val fishChild = 29
private const val timeChild = 30


/**
 * Handles updating the fishing trawler overlay interface
 * @author Ceikry
 */
object FishingTrawlerOverlay {
    @JvmStatic
    fun sendUpdate(player: Player, waterPercent: Int, NetRipped: Boolean, fishCaught: Int, timeLeft: Int){
        player.configManager.set(configIndex,waterPercent)
        player.packetDispatch.sendInterfaceConfig(interfaceID,if(NetRipped) netRippedChild else netOkayChild, false)
        player.packetDispatch.sendInterfaceConfig(interfaceID,if(NetRipped) netOkayChild else netRippedChild, true)
        player.packetDispatch.sendString("${if(fishCaught > 0) fishCaught else "Nothing"}",interfaceID,fishChild)
        player.packetDispatch.sendString("$timeLeft Minutes", interfaceID, timeChild)
    }
}