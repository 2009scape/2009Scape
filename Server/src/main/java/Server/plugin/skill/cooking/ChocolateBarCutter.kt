package plugin.skill.cooking

import core.game.interaction.NodeUsageEvent
import core.game.interaction.UseWithHandler
import core.game.node.item.Item
import core.game.system.task.Pulse
import core.game.world.update.flag.context.Animation
import core.plugin.InitializablePlugin
import core.plugin.Plugin
import core.tools.ItemNames

@InitializablePlugin
class ChocolateBarCutter : UseWithHandler(946){
    override fun newInstance(arg: Any?): Plugin<Any> {
        addHandler(1973, ITEM_TYPE, this)
        return this
    }

    override fun handle(event: NodeUsageEvent?): Boolean {
        event ?: return false
        val player = event.player
        player.pulseManager.run(object : Pulse(1){
            val cut_animation = Animation(1989)
            override fun pulse(): Boolean {
                super.setDelay(4)
                val amount = player.inventory.getAmount(ItemNames.CHOCOLATE_BAR_1973)
                if(amount > 0) player.inventory.remove(Item(1973)).also { player.animator.animate(cut_animation); player.inventory.add(Item(1975)) }
                return amount <= 0
            }
        })
        return true
    }

}