package plugin.activity.pyramidplunder

import core.game.component.Component
import core.game.node.entity.player.Player
import core.game.system.SystemLogger
import core.game.system.task.Pulse
import core.game.world.GameWorld
import core.game.world.map.Location
import core.tools.RandomFunction

class PlunderSession(val player: Player) {
    var door1Open: Boolean = false
    var door2Open: Boolean = false
    var door3Open: Boolean = false
    var door4Open: Boolean = false
    var chestHidden: Boolean = false
    var chestOpen: Boolean = false
    var coffinHidden: Boolean = false
    var coffinOpen: Boolean = false
    var timeCounter: Int = 1
    var correctDoorIndex: Int = RandomFunction.random(1000) % 4


    fun init(){
        player.setAttribute("plunder-session",this)
        GameWorld.Pulser.submit(PlunderPulse(player))
        player.interfaceManager.openOverlay(Component(428))
    }

    fun updateOverlay(){
        player.configManager.set(821,getConfig())
    }

    fun resetVars(){
        chestOpen = false
        chestHidden = false
        coffinHidden = false
        coffinOpen = false
        door1Open = false
        door2Open = false
        door3Open = false
        door4Open = false
        correctDoorIndex = RandomFunction.random(1000) % 4
    }

    fun getConfig(): Int{
        var config = 0
        if(coffinOpen) config = 1
        if(coffinHidden) config = (config or (1 shl 1))
        if(chestOpen) config = (config or (1 shl 2))
        if(chestHidden) config = (config or (1 shl 3))
        if(door1Open) config = (config or (1 shl 9))
        if(door2Open) config = (config or (1 shl 10))
        if(door3Open) config = (config or (1 shl 11))
        if(door4Open) config = (config or (1 shl 12))
        return (config or (timeCounter shl 25))
    }

    fun setDoorOpen(index: Int){
        when(index){
            0 -> door1Open = true
            1 -> door2Open = true
            2 -> door3Open = true
            3 -> door4Open = true
        }
    }

    class PlunderPulse(val player: Player) : Pulse(){
        var ticks = 0
        var overlayConfig = 0
        val session: PlunderSession? = player.getAttribute("plunder-session",null)
        override fun pulse(): Boolean {
            session ?: return false
            if(ticks++ % 8 == 0){
                session.timeCounter++
                session.updateOverlay()
            }
            if(session.timeCounter > 63){
                player.properties.teleportLocation = Location.create(3286, 2803, 0)
                player.dialogueInterpreter.sendDialogue("You ran out of time and the mummy kicked you","out.")
                player.configManager.set(822,0)
                player.configManager.set(821,0)
            }
            return session.timeCounter > 63
        }
    }
}