package plugin.interaction.`object`

import core.cache.def.impl.ObjectDefinition
import core.game.component.Component
import core.game.interaction.OptionHandler
import core.game.node.Node
import core.game.node.entity.player.Player
import core.game.node.entity.player.link.TeleportManager.TeleportType
import core.game.world.map.Location
import core.plugin.InitializablePlugin
import core.plugin.Plugin

/**
 * Handles interactions with fairy rings
 * @author Ceikry
 */

private val RINGS = intArrayOf(12003, 12094, 12095, 14058, 14061, 14064, 14067, 14070, 14073, 14076, 14079, 14082, 14085, 14088, 14091, 14094, 14097, 14100, 14103, 14106, 14109, 14112, 14115, 14118, 14121, 14124, 14127, 14130, 14133, 14136, 14139, 14142, 14145, 14148, 14151, 14154, 14157, 14160, 16181, 16184, 23047, 27325, 37727)
private const val MAIN_RING = 12128


@InitializablePlugin
class FairyRingPlugin : OptionHandler() {
    override fun newInstance(arg: Any?): Plugin<Any> {
        for (i in RINGS) {
            ObjectDefinition.forId(i).handlers["option:use"] = this
        }
        ObjectDefinition.forId(MAIN_RING).handlers["option:use"] = this
        return this
    }

    override fun handle(player: Player, node: Node, option: String): Boolean {
        when (option) {
            "use" -> {
                if (!player.equipment.contains(772, 1) && !player.equipment.contains(9084, 1)) {
                    player.sendMessage("The fairy ring only works for those who wield fairy magic.")
                    return true
                }
                when (node.id) {
                    MAIN_RING -> openFairyRing(player)
                    else -> player.teleporter.send(Location.create(2412, 4434, 0), TeleportType.FAIRY_RING)
                }
            }
        }
        return true
    }

    private fun reset(player: Player) {
        player.removeAttribute("fairy-delay")
        player.removeAttribute("fairy_location_combo")
        for (i in 0..2) {
            player.configManager[816 + i] = 0
        }
    }

    private fun openFairyRing(player: Player) {
        reset(player)
        player.interfaceManager.openSingleTab(Component(735))
        player.interfaceManager.open(Component(734))
  }
}