package plugin.CorePluginTypes

import core.game.node.entity.player.Player
import core.game.system.SystemLogger
import core.plugin.Plugin
import plugin.skill.Skills

object XPGainPlugins {
    @JvmStatic
    private val plugins = ArrayList<XPGainPlugin>()
    @JvmStatic
    fun add(plugin: XPGainPlugin){
        plugins.add(plugin)
    }
    @JvmStatic
    fun run(player: Player, skill: Int, amount: Double){
        if(player.isArtificial) return
        plugins.forEach {
            it.run(player,skill,amount)
        }
    }
}

abstract class XPGainPlugin : Plugin<Any> {
    override fun newInstance(arg: Any?): Plugin<Any> {
        XPGainPlugins.add(this)
        return this
    }
    abstract fun run(player: Player, skill: Int, amount: Double)
}