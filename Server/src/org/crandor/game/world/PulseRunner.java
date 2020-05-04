package org.crandor.game.world;

import org.crandor.game.system.task.Pulse;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/** new way of running pulses that multithreads based on core count automatically, should improve performance drastically.
 *  @author ceik
 *  @author eli
 */
public class PulseRunner {
    public final int MAXIMUM_NUM_THREADS = 4;
    public final int TARGET_PULSES_PER_THREAD = 100;
    public final ThreadPoolExecutor ThreadPool = (ThreadPoolExecutor) Executors.newFixedThreadPool(MAXIMUM_NUM_THREADS - 1);
    public final List<Pulse> TASKS = new ArrayList<>();
    public int cores = Runtime.getRuntime().availableProcessors();
    public ScheduledExecutorService EXECUTOR  = Executors.newSingleThreadScheduledExecutor();

    public void init(int tickTimeMS){ EXECUTOR.scheduleAtFixedRate(new Runner(),1200,tickTimeMS, TimeUnit.MILLISECONDS); }

    public void submit(Pulse pulse){ TASKS.add(pulse); }

    public class Runner implements Runnable {
        @Override
        public void run() {
            long currTime = System.nanoTime();
            List<Pulse> pulses = null;
            pulses = new ArrayList<>(TASKS);
            Object[] pulseArray = pulses.toArray();
            int numThreads = 1 + (pulseArray.length / TARGET_PULSES_PER_THREAD);
            if (numThreads > MAXIMUM_NUM_THREADS)
                numThreads = MAXIMUM_NUM_THREADS;

            long nowTime = System.nanoTime();
            // Execute all the tasks not run on the first core
            for (int i = 1; i < numThreads; i++) {
                int pulsesLengthStart = (int) Math.floor((pulseArray.length / numThreads) * i);
                int pulsesLengthEnd = (int) Math.floor((pulseArray.length / numThreads) * (i + 1));
                if (i + 1 == numThreads)
                    pulsesLengthEnd = pulseArray.length;
                ThreadPool.execute(new PulseThread(pulsesLengthStart,pulsesLengthEnd,pulseArray));
            }

            // Execute the first core tasks all together just as before
            int pulsesLengthStart = (int) Math.floor(pulseArray.length / numThreads);
            for (int i = 0; i < pulsesLengthStart; i++) {
                Pulse pulse = (Pulse) pulseArray[i];
                if (pulse == null) {
                    continue;
                }
                try {
                    if (pulse.update()) {
                        TASKS.remove(pulse);
                    }
                } catch (Throwable t) {
                    t.printStackTrace();
                    pulse.stop();
                    TASKS.remove(pulse);
                }
            }
            pulses.clear();
        }
    }

    public class PulseThread implements Runnable{
        int threadStart, threadFinish;
        Object[] pulseArray;
        public PulseThread(int threadStart, int threadFinish, Object[] pulseArray){
            this.threadStart = threadStart;
            this.threadFinish = threadFinish;
            this.pulseArray = pulseArray;
        }

        @Override
        public void run() {
            for (int i = threadStart; i < threadFinish; i++) {
                Pulse pulse = (Pulse) pulseArray[i];
                if (pulse == null) {
                    continue;
                }
                try {
                    if (pulse.update()) {
                        TASKS.remove(pulse);
                    }
                } catch (Throwable t) {
                    t.printStackTrace();
                    pulse.stop();
                    TASKS.remove(pulse);
                }
            }
        }
    }

}
