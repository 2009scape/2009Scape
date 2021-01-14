package core.worker

import core.game.system.task.Pulse
import core.game.world.GameWorld
import core.game.world.repository.Repository
import core.game.world.update.UpdateSequence
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import plugin.CorePluginTypes.Managers

/**
 * Handles the running of pulses and writing of masks, etc
 * @author Ceikry
 */
class MajorUpdateWorker {
    var started = false
    val sequence = UpdateSequence()
    fun start() = GlobalScope.launch {
        started = true
        while(true){
            delay(600L)
            val rmlist = ArrayList<Pulse>()
            val list = ArrayList(GameWorld.Pulser.TASKS)

            //run our pulses
            for(pulse in list) {
                if (pulse == null || pulse.update()) rmlist.add(pulse)
            }

            //remove all null or finished pulses from the list
            GameWorld.Pulser.TASKS.removeAll(rmlist)
            //perform our update sequence where we write masks, etc
            sequence.start()
            sequence.run()
            sequence.end()
            //increment global ticks variable
            GameWorld.pulse()
            //disconnect all players waiting to be disconnected
            Repository.disconnectionQueue.update()
            //tick all manager plugins
            Managers.tick()
        }
    }
}