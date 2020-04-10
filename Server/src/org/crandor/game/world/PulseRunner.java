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
 */
public class PulseRunner{
    public static final ThreadPoolExecutor ThreadPool = (ThreadPoolExecutor) Executors.newFixedThreadPool(4);
    public static final List<Pulse> TASKS = new ArrayList<>();
    public static int cores = Runtime.getRuntime().availableProcessors();
    public static ScheduledExecutorService EXECUTOR  = Executors.newSingleThreadScheduledExecutor();

    public static void init(){
        EXECUTOR.scheduleAtFixedRate(new Runner(),1200,600, TimeUnit.MILLISECONDS);
    }

    public static void submit(Pulse pulse){
        TASKS.add(pulse);
    }

    public static class Runner implements Runnable {
        @Override
        public void run() {
            List<Pulse> pulses = null;
            pulses = new ArrayList<>(TASKS);
            Object[] pulseArray = pulses.toArray();
            switch (pulseArray.length) {
                case 1:
                case 2:
                case 3:
                    int pulsesLength = pulseArray.length;
                    for (int i = 0; i < pulsesLength; i++) {
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
                    break;
                default:
                    pulseMultiplexer(pulseArray);
                    pulses.clear();
                    break;
            }
        }


        public static void pulseMultiplexer(Object[] pulseArray) {
            switch (cores) {
                case 1: {
                    ThreadPool.execute(new PulseThread(0,pulseArray.length,pulseArray));
                    break;
                }
                case 2: {
                    int pulsesLength1 = (int) Math.floor(pulseArray.length / 2.0);
                    int pulsesLength2 = pulseArray.length;
                    ThreadPool.execute(new PulseThread(0,pulsesLength1,pulseArray));
                    ThreadPool.execute(new PulseThread(pulsesLength1,pulsesLength2,pulseArray));
                    break;
                }
                default: {
                    int pulsesLength1 = (int) Math.floor(pulseArray.length / 4);
                    int pulsesLength2 = (int) Math.floor((pulseArray.length / 4) * 2);
                    int pulsesLength3 = (int) Math.floor((pulseArray.length / 4) * 3);
                    int pulsesLength4 = pulseArray.length;
                    ThreadPool.execute(new PulseThread(0,pulsesLength1,pulseArray));
                    ThreadPool.execute(new PulseThread(pulsesLength1,pulsesLength2,pulseArray));
                    ThreadPool.execute(new PulseThread(pulsesLength2,pulsesLength3,pulseArray));
                    ThreadPool.execute(new PulseThread(pulsesLength3,pulsesLength4,pulseArray));
                    break;
                }
            }
        }
    }

    public static class PulseThread implements Runnable{
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
