package plugin.activity.allfiredup

import core.game.node.entity.player.Player
import core.game.node.item.Item
import core.game.system.SystemLogger
import core.game.system.task.Pulse
import core.game.world.GameWorld
import core.plugin.Plugin
import core.tools.Items
import plugin.stringtools.colorize

/**
 * Handles keeping track of lit beacons and their burn time remaining
 * @author Ceikry
 */
class AFUSession(val player: Player) {
    private val beaconTimers = Array(14){i -> BeaconTimer(0,AFUBeacon.values()[i])}
    private val logInventories = Array(14){Item(0,0)}
    private val beaconWatched = Array(14){false}
    private var isActive = false

    fun init() {
        SystemLogger.log("Initializing")
        isActive = true
        GameWorld.Pulser.submit(object: Pulse(){
            override fun pulse(): Boolean {
                player.setAttribute("afu-pulse",this)
                beaconTimers.forEach {timer ->
                    timer.ticks--
                    if(timer.ticks > 0) {
                        SystemLogger.log("Tick " + timer.ticks)
                    }
                    if(timer.ticks == 300) timer.beacon.diminish(player).also {
                        if(beaconWatched[timer.beacon.ordinal]){
                            beaconWatched[timer.beacon.ordinal] = false
                            timer.ticks += (getTicks(logInventories[timer.beacon.ordinal].id) * 5)
                            timer.beacon.light(player)
                            player.sendMessage(colorize("%RThe ${timer.beacon.name.toLowerCase().replace("_"," ")} watcher has used your backup logs."))
                        } else {
                            player.sendMessage(colorize("%RThe ${timer.beacon.name.toLowerCase().replace("_", " ")} beacon is dying!"))
                        }
                    }
                    if(timer.ticks == 0)   timer.beacon.extinguish(player).also { player.sendMessage(colorize("%RThe ${timer.beacon.name.toLowerCase().replace("_"," ")} beacon has gone out!")) }
                }
                return !isActive
            }
        })
        player.setAttribute("afu-session",this)
        player.logoutPlugins.add(AFULogoutPlugin())
    }

    fun getLitBeacons(): Int{
        return beaconTimers.count { it.ticks > 0 }
    }

    fun end(){
        isActive = false
    }

    fun setLogs(beaconIndex: Int, logs: Item){
        logInventories[beaconIndex] = logs
    }

    fun startTimer(beaconIndex: Int){
        val ticks = getTicks(logInventories[beaconIndex].id) * 20
        logInventories[beaconIndex] = Item(0,0)
        beaconTimers[beaconIndex].ticks = ticks
    }

    fun refreshTimer(beacon: AFUBeacon, logID: Int){
        val ticks = getTicks(logID) * 5
        beaconTimers.forEach {
            if(it.beacon.ordinal == beacon.ordinal) it.ticks += ticks
        }
    }

    fun setWatcher(index: Int, logs: Item){
        beaconWatched[index] = true
        logInventories[index] = logs
    }

    fun isWatched(index: Int): Boolean{
        return beaconWatched[index]
    }

    fun getTicks(logID: Int): Int{
        val ticks = when(logID){
            Items.LOGS_1511 -> 65
            Items.OAK_LOGS_1521 -> 68
            Items.WILLOW_LOGS_1519 -> 73
            Items.MAPLE_LOGS_1517 -> 79
            Items.YEW_LOGS_1515 -> 83
            Items.MAGIC_LOGS_1513 -> 90
            else -> 0
        }
        return ticks
    }

    fun getBonusExperience(): Double{
        return when(getLitBeacons()){
            1 -> 608.4
            2 -> 1622.4
            3 -> 1987.4
            4 -> 2149.6
            5 -> 2149.6
            6 -> 2514.6
            7 -> 2555.2
            8 -> 2758.0
            9 -> 2839.1
            10 -> 3041.9
            11 -> 3123.0
            12 -> 3244.7
            13 -> 3366.4
            14 -> 4867.4
            else -> 0.0
        }
    }

    internal class BeaconTimer(var ticks: Int, val beacon: AFUBeacon)

    class AFULogoutPlugin: Plugin<Player> {
        override fun newInstance(arg: Player?): Plugin<Player> {
            arg ?: return this
            AFUBeacon.resetAllBeacons(arg)
            val session: AFUSession? = arg.getAttribute("afu-session",null)
            session?.end()
            arg.removeAttribute("afu-session")
            return this
        }

        override fun fireEvent(identifier: String?, vararg args: Any?): Any {
            return Unit
        }
    }
}