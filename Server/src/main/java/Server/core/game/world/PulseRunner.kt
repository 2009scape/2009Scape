package core.game.world

import core.game.system.task.Pulse
import java.util.*
import java.util.concurrent.Executors
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit
import kotlin.math.floor

/** new way of running pulses that multithreads based on core count automatically, should improve performance drastically.
 * @author ceik
 * @author angle
 */

class PulseRunner {
    val TASKS = ArrayList<Pulse>()
    fun submit(pulse: Pulse){
        TASKS.add(pulse)
    }
}
/*
class PulseeRunner {
    val MAXIMUM_NUM_THREADS = 4
    val TARGET_PULSES_PER_THREAD = 100
    val ThreadPool = Executors.newFixedThreadPool(MAXIMUM_NUM_THREADS - 1) as ThreadPoolExecutor
    val TASKS: MutableList<Pulse> = ArrayList()
    var cores = Runtime.getRuntime().availableProcessors()
    var EXECUTOR = Executors.newSingleThreadScheduledExecutor()
    var lastPulse: Pulse? = null
    fun init(tickTimeMS: Int) {
        EXECUTOR.scheduleAtFixedRate(Runner(), 1200, tickTimeMS.toLong(), TimeUnit.MILLISECONDS)
    }

    fun submit(pulse: Pulse) {
        TASKS.add(pulse)
    }

    inner class Runner : Runnable {
        override fun run() {
            val currTime = System.nanoTime()
            var pulses: MutableList<Pulse>? = null
            pulses = ArrayList(TASKS)
            val pulseArray: Array<Any?> = pulses.toTypedArray()
            */
/*var numThreads = 1 + pulseArray.size / TARGET_PULSES_PER_THREAD
            if (numThreads > MAXIMUM_NUM_THREADS) numThreads = MAXIMUM_NUM_THREADS
            val nowTime = System.nanoTime()
            // Execute all the tasks not run on the first core
            for (i in 1 until numThreads) {
                val pulsesLengthStart = floor(pulseArray.size / numThreads * i.toDouble()).toInt()
                var pulsesLengthEnd = floor(pulseArray.size / numThreads * (i + 1).toDouble()).toInt()
                if (i + 1 == numThreads) pulsesLengthEnd = pulseArray.size
                ThreadPool.execute(PulseThread(pulsesLengthStart, pulsesLengthEnd, pulseArray))
            }


            // Execute the first core tasks all together just as before
            val pulsesLengthStart = floor(pulseArray.size / numThreads.toDouble()).toInt()*//*


            for (element in pulseArray) {
                val pulse = element as Pulse? ?: continue
                try {
                    if (TASKS.contains(pulse)) {
                        lastPulse = pulse
                        if (pulse.update()) {
                            TASKS.remove(pulse)
                        }
                    }
                } catch (t: Throwable) {
                    t.printStackTrace()
                    pulse.stop()
                    TASKS.remove(pulse)
                }
            }
            pulses.clear()
        }
    }

    inner class PulseThread(var threadStart: Int, var threadFinish: Int, var pulseArray: Array<Any?>) : Runnable {
        override fun run() {
            for (i in threadStart until threadFinish) {
                val pulse = pulseArray[i] as Pulse? ?: continue
                try {
                    if (TASKS.contains(pulse)) {
                        lastPulse = pulse
                        if (pulse.update()) {
                            TASKS.remove(pulse)
                        }
                    }
                } catch (t: Throwable) {
                    t.printStackTrace()
                    pulse.stop()
                    TASKS.remove(pulse)
                }
            }
        }

    }
}*/
