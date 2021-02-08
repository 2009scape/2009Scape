package plugin.quest.members.familycrest


import core.game.interaction.NodeUsageEvent
import core.game.interaction.UseWithHandler
import core.game.node.item.Item
import core.game.system.task.Pulse
import core.game.world.GameWorld.Pulser
import core.plugin.InitializablePlugin
import core.plugin.Plugin
import core.tools.Items
import plugin.skill.Skills


@InitializablePlugin
class PerfectGoldSmeltingHandler : UseWithHandler(Items.PERFECT_GOLD_ORE_446){

    val furnaceIDs = listOf(2349, 2351, 2353, 2359, 2361, 2363, 2366, 2368, 9467, 11286, 1540, 11710, 11712, 11714, 11666, 11686, 11688, 11692)

    override fun newInstance(arg: Any?): Plugin<Any> {
        for(furnaces in furnaceIDs){
            addHandler(furnaces, OBJECT_TYPE, this)
        }
        return this
    }

    override fun handle(event: NodeUsageEvent?): Boolean {

        event ?: return false

            Pulser.submit(object : Pulse(2, event.player) {
                override fun pulse(): Boolean {
                    event.player.inventory.remove(Item(446))
                    event.player.inventory.add(Item(2365))
                    event.player.skills.addExperience(Skills.SMITHING,22.5)
                    return true
                }
            })
            return true

    }
}