package plugin.quest.members.familycrest

import core.tools.Items

import core.game.interaction.NodeUsageEvent
import core.game.interaction.UseWithHandler
import core.plugin.InitializablePlugin
import core.plugin.Plugin

@InitializablePlugin
class PerfectJewelryOnUseHandler : UseWithHandler(Items.PERFECT_GOLD_BAR_2365){
    val furnaceIDs = listOf(2349, 2351, 2353, 2359, 2361, 2363, 2366, 2368, 9467, 11286, 1540, 11710, 11712, 11714, 11666, 11686, 11688, 11692)
    override fun newInstance(arg: Any?): Plugin<Any> {
        for(furnaces in furnaceIDs){
            addHandler(furnaces, OBJECT_TYPE, this)
        }
        return this
    }

    override fun handle(event: NodeUsageEvent?): Boolean {
        event ?: return false
        event.player.dialogueInterpreter.open("perfect-jewelry");
        return true
    }

}

//event.getPlayer().getDialogueInterpreter().open("perfect-jewelry");