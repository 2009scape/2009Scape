package org.crandor.game.world;

import org.crandor.ServerConstants;
import org.crandor.cache.Cache;
import org.crandor.cache.ServerStore;
import org.crandor.game.content.eco.ge.GrandExchangeDatabase;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.entity.player.ai.AIPBuilder;
import org.crandor.game.node.entity.player.ai.AIPlayer;
import org.crandor.game.node.entity.player.ai.pvp.PVPAIPActions;
import org.crandor.game.node.entity.player.ai.pvp.PVPAIPBuilderUtils;
import org.crandor.game.node.entity.player.ai.resource.ResourceAIPManager;
import org.crandor.game.node.entity.player.link.appearance.Gender;
import org.crandor.game.node.object.GameObject;
import org.crandor.game.system.SystemLogger;
import org.crandor.game.system.SystemManager;
import org.crandor.game.system.SystemState;
import org.crandor.game.system.mysql.SQLManager;
import org.crandor.game.system.script.ScriptManager;
import org.crandor.game.system.task.Pulse;
import org.crandor.game.system.task.TaskExecutor;
import org.crandor.game.world.callback.CallbackHub;
import org.crandor.game.world.map.Direction;
import org.crandor.game.world.map.Location;
import org.crandor.game.world.map.RegionManager;
import org.crandor.game.world.map.build.LandscapeParser;
import org.crandor.game.world.repository.DisconnectionQueue;
import org.crandor.game.world.repository.Repository;
import org.crandor.plugin.PluginManager;
import org.crandor.tools.RandomFunction;
import org.crandor.tools.mysql.DatabaseManager;
import org.crandor.worker.MajorUpdateWorker;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Represents the game world.
 *
 * @author Emperor
 */
public final class GameWorld {

    /**
     * The major update worker.
     */
    private static final MajorUpdateWorker MAJOR_UPDATE_WORKER = new MajorUpdateWorker();

    /**
     * The lock object.
     */
    private static final Lock LOCK = new ReentrantLock();

    /**
     * The pulse tasks list.
     */
    private static final List<Pulse> TASKS = new ArrayList<>();

    /**
     * The game settings to use.
     */
    private static GameSettings settings;

    /**
     * The current amount of (600ms) cycles elapsed.
     */
    private static int ticks;

    private static DatabaseManager dbm = new DatabaseManager(ServerConstants.DATABASES);

    private static int eventTicks;

    private static int cfTicks;

    /**
     * Constructs a new {@Code GameWorld} {@Code Object}
     */
    private GameWorld() {
        /**
         * empty.
         */
    }

    /**
     * Submits a pulse.
     *
     * @param pulse the pulse.
     */
    public static void submit(Pulse pulse) {
        LOCK.lock();
        try {
            TASKS.add(pulse);
        } finally {
            LOCK.unlock();
        }
    }

    /**
     * Pulses all current pulses.
     */
    public static void pulse() {
        LOCK.lock();
        List<Pulse> pulses = null;
        try {
            pulses = new ArrayList<>(TASKS);
        } finally {
            LOCK.unlock();
        }
        for (Pulse pulse : pulses) {
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
        ticks++;

        if (ticks % 50 == 0) {
            TaskExecutor.execute(() -> {
                try {
                    for (Iterator<Player> it = Repository.getPlayers().iterator(); it.hasNext(); ) {
                        Player p = it.next();
                        if (p != null && !p.isArtificial() && p.isPlaying()) {
                            DisconnectionQueue.save(p, false);
                        }
                    }
                } catch (Throwable t) {
                    t.printStackTrace();
                }
            });
        }
    }

    private static boolean checkDay() {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("America/Toronto"));
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        return (day == Calendar.SATURDAY && hour == 1) || (day == Calendar.SUNDAY && hour == 1) || (day == Calendar.TUESDAY && hour == 10);
    }


    /**
     * Prompts the {@link GameWorld} to begin it's initialization.
     *
     * @param directory the directory to the properties.
     * @throws Throwable when the exception occurs.
     */
    public static void prompt(String directory) throws Throwable {
        prompt(true, directory);
    }

    /**
     * Prompts the game world.
     *
     * @param running if running.
     * @throws Throwable the throwable.
     */
    public static void prompt(boolean running) throws Throwable {
        prompt(running, "server.properties");
    }

    /**
     * Prompts the {@link GameWorld} to begin its initialization.
     *
     * @param run       If the server should be running.
     * @param directory the path to the dir.
     * @throws Throwable When an exception occurs.
     */
    public static void prompt(boolean run, String directory) throws Throwable {
        SystemLogger.log("Prompting " + GameWorld.getName() + " Game World...");
        Cache.init(ServerConstants.CACHE_PATH);
        ServerStore.init(ServerConstants.STORE_PATH);
        dbm.connect();
        GrandExchangeDatabase.init();
        SQLManager.prePlugin();
        ScriptManager.load();
        PluginManager.init();
        ResourceAIPManager.get().init();
        ResourceAIPManager.get().immerseWorld();
        SQLManager.postPlugin();
        parseObjects();
        CallbackHub.call();

        if (run) {
            SystemManager.flag(GameWorld.getSettings().isDevMode() ? SystemState.PRIVATE : SystemState.ACTIVE);
        }
        System.gc();
    }

    //39956
    private static void parseObjects() {
        /*
		Removed Objects from port phastmatsysy
		LandscapeParser.removeGameObject(new GameObject(11484, 2338, 3689, 0));
		LandscapeParser.removeGameObject(new GameObject(14963, 2339, 3688, 0));
		LandscapeParser.removeGameObject(new GameObject(14962, 2340, 3688, 0));
		LandscapeParser.removeGameObject(new GameObject(14963, 2341, 3688, 0));
		LandscapeParser.removeGameObject(new GameObject(14953, 2342, 3689, 0));
		LandscapeParser.removeGameObject(new GameObject(14962, 2341, 3689, 0));
		LandscapeParser.removeGameObject(new GameObject(14962, 2340, 3689, 0));
		LandscapeParser.removeGameObject(new GameObject(14962, 2339, 3689, 0));
		LandscapeParser.removeGameObject(new GameObject(14963, 2338, 3690, 0));
		LandscapeParser.removeGameObject(new GameObject(14962, 2339, 3690, 0));
		LandscapeParser.removeGameObject(new GameObject(14962, 2340, 3690, 0));
		LandscapeParser.removeGameObject(new GameObject(14962, 2341, 3690, 0));
		LandscapeParser.removeGameObject(new GameObject(14963, 2342, 3690, 0));
		LandscapeParser.removeGameObject(new GameObject(14965, 2340, 3691, 0));
		LandscapeParser.removeGameObject(new GameObject(14951, 2341, 3692, 0));
		LandscapeParser.removeGameObject(new GameObject(14965, 2344, 3688, 0));
		LandscapeParser.removeGameObject(new GameObject(14951, 2336, 3691, 0));

		
		LandscapeParser.addGameObject(new GameObject(24716, new Location(2343, 3690, 0), 0));
		LandscapeParser.addGameObject(new GameObject(10179, new Location(2335, 3676, 0), 0));
		LandscapeParser.addGameObject(new GameObject(10179, new Location(2339, 3683, 0), 1));
		LandscapeParser.addGameObject(new GameObject(10179, new Location(2333, 3688, 0), 2));
		LandscapeParser.addGameObject(new GameObject(10179, new Location(2333, 3690, 0), 2));*/

        LandscapeParser.removeGameObject(new GameObject(1113, 2854, 2954, 0));//Table
        LandscapeParser.removeGameObject(new GameObject(620, 2854, 2953, 0));//Stool
        LandscapeParser.removeGameObject(new GameObject(1113, 2854, 2952, 0));//Stool
        LandscapeParser.removeGameObject(new GameObject(1160, 2857, 2954, 0));//Potted plant
        LandscapeParser.removeGameObject(new GameObject(396, 2859, 2952, 0));//Pots
        LandscapeParser.removeGameObject(new GameObject(1113, 2851, 2952, 0));//Stool
        LandscapeParser.removeGameObject(new GameObject(620, 2849, 2953, 0));//Table
        LandscapeParser.removeGameObject(new GameObject(1160, 2848, 2954, 0));//Potted plant
        LandscapeParser.removeGameObject(new GameObject(396, 2844, 2952, 0));//Pots
        LandscapeParser.removeGameObject(new GameObject(393, 2844, 2956, 0));//Bookcase
        LandscapeParser.removeGameObject(new GameObject(620, 2854, 2954, 1));//Table
        LandscapeParser.removeGameObject(new GameObject(620, 2850, 2954, 1));//Table
        LandscapeParser.removeGameObject(new GameObject(620, 2852, 2956, 1));//Table
        LandscapeParser.removeGameObject(new GameObject(1113, 2851, 2956, 1));//Stool
		
		/*Piscatoris Home*/
        LandscapeParser.removeGameObject(new GameObject(14965, 2327, 3686, 0));//Large barrel
        LandscapeParser.removeGameObject(new GameObject(14969, 2327, 3687, 0));//Barrow
        LandscapeParser.removeGameObject(new GameObject(14964, 2327, 3689, 0));//Barrel
        LandscapeParser.removeGameObject(new GameObject(14964, 2327, 3691, 0));//Barrel
        LandscapeParser.removeGameObject(new GameObject(14963, 2327, 3693, 0));//Crates
        LandscapeParser.removeGameObject(new GameObject(11484, 2328, 3693, 0));//Barrel
        LandscapeParser.removeGameObject(new GameObject(14964, 2329, 3686, 0));//Barrel
        LandscapeParser.removeGameObject(new GameObject(14963, 2329, 3693, 0));//Crates
        LandscapeParser.removeGameObject(new GameObject(14963, 2330, 3692, 0));//Crates
        LandscapeParser.removeGameObject(new GameObject(14962, 2330, 3693, 0));//Crates
        LandscapeParser.removeGameObject(new GameObject(14962, 2331, 3693, 0));//Crates
        LandscapeParser.removeGameObject(new GameObject(14962, 2331, 3692, 0));//Crates
        LandscapeParser.removeGameObject(new GameObject(14962, 2332, 3693, 0));//Crates
        LandscapeParser.removeGameObject(new GameObject(14962, 2332, 3692, 0));//Crates
        LandscapeParser.removeGameObject(new GameObject(14962, 2332, 3691, 0));//Crates
        LandscapeParser.removeGameObject(new GameObject(14966, 2332, 3687, 0));//Barrels
        LandscapeParser.removeGameObject(new GameObject(14966, 2332, 3686, 0));//Barrels
        LandscapeParser.removeGameObject(new GameObject(14951, 2336, 3691, 0));//Cogs
        LandscapeParser.removeGameObject(new GameObject(14951, 2341, 3692, 0));//Cogs
        LandscapeParser.removeGameObject(new GameObject(14963, 2338, 3690, 0));//Crates
        LandscapeParser.removeGameObject(new GameObject(11484, 2338, 3689, 0));//Barrel
        LandscapeParser.removeGameObject(new GameObject(14962, 2339, 3690, 0));//Crates
        LandscapeParser.removeGameObject(new GameObject(14962, 2339, 3689, 0));//Crates
        LandscapeParser.removeGameObject(new GameObject(14962, 2340, 3690, 0));//Crates
        LandscapeParser.removeGameObject(new GameObject(14962, 2340, 3689, 0));//Crates
        LandscapeParser.removeGameObject(new GameObject(14962, 2340, 3688, 0));//Crates
        LandscapeParser.removeGameObject(new GameObject(14962, 2341, 3690, 0));//Crates
        LandscapeParser.removeGameObject(new GameObject(14962, 2341, 3689, 0));//Crates
        LandscapeParser.removeGameObject(new GameObject(14962, 2333, 3693, 0));//Crates
        LandscapeParser.removeGameObject(new GameObject(14963, 2339, 3688, 0));//Crates
        LandscapeParser.removeGameObject(new GameObject(14963, 2341, 3688, 0));//Crates
        LandscapeParser.removeGameObject(new GameObject(14963, 2342, 3690, 0));//Crates
        LandscapeParser.removeGameObject(new GameObject(14965, 2340, 3691, 0));//Large Barrel
        LandscapeParser.removeGameObject(new GameObject(14965, 2344, 3688, 0));//Large Barrel
        LandscapeParser.removeGameObject(new GameObject(14953, 2342, 3689, 0));//Fishing Weights
        LandscapeParser.removeGameObject(new GameObject(14966, 2333, 3692, 0));//Rack of Barrels


        LandscapeParser.addGameObject(new GameObject(11758, new Location(2328, 3686, 0), 10, 3));//Bank Booth
        LandscapeParser.addGameObject(new GameObject(11758, new Location(2328, 3687, 0), 10, 3));//Bank Booth
        LandscapeParser.addGameObject(new GameObject(11758, new Location(2328, 3688, 0), 10, 3));//Bank Booth
        LandscapeParser.addGameObject(new GameObject(590, new Location(2328, 3689, 0), 10, 1));//Desk
        LandscapeParser.addGameObject(new GameObject(11758, new Location(2328, 3691, 0), 10, 1));//Bank Booth
        LandscapeParser.addGameObject(new GameObject(11758, new Location(2328, 3692, 0), 10, 1));//Bank Booth
        LandscapeParser.addGameObject(new GameObject(11758, new Location(2328, 3693, 0), 10, 1));//Bank Booth
        LandscapeParser.addGameObject(new GameObject(9398, new Location(2332, 3692, 0), 10, 1));//Deposit box

        LandscapeParser.addGameObject(new GameObject(724, new Location(2333, 3688, 0), 10, 1));//Torch
        LandscapeParser.addGameObject(new GameObject(724, new Location(2333, 3690, 0), 10, 1));//Torch

        //Home Fountain & Benches, Bushes & Torches
        //LandscapeParser.addGameObject(new GameObject(47150, 10, 0, 2342, 3689, 0));
        LandscapeParser.addGameObject(new GameObject(15088, new Location(2343, 3688, 0), 10, 0));
        LandscapeParser.addGameObject(new GameObject(15088, new Location(2346, 3690, 0), 10, 3));
        //LandscapeParser.addGameObject(new GameObject(15088, new Location(2341, 3690, 0), 10, 1));
        LandscapeParser.addGameObject(new GameObject(15088, new Location(2343, 3693, 0), 10, 2));
        LandscapeParser.addGameObject(new GameObject(2357, new Location(2345, 3693, 0), 10, 0));
        LandscapeParser.addGameObject(new GameObject(2357, new Location(2346, 3692, 0), 10, 0));
        LandscapeParser.addGameObject(new GameObject(2357, new Location(2346, 3689, 0), 10, 0));
        LandscapeParser.addGameObject(new GameObject(2357, new Location(2345, 3688, 0), 10, 0));
        LandscapeParser.addGameObject(new GameObject(2357, new Location(2342, 3693, 0), 10, 0));
        LandscapeParser.addGameObject(new GameObject(2357, new Location(2341, 3692, 0), 10, 0));
        LandscapeParser.addGameObject(new GameObject(2357, new Location(2341, 3689, 0), 10, 0));
        LandscapeParser.addGameObject(new GameObject(2357, new Location(2342, 3688, 0), 10, 0));
        LandscapeParser.addGameObject(new GameObject(724, new Location(2346, 3693, 0), 10, 0));
        LandscapeParser.addGameObject(new GameObject(724, new Location(2346, 3688, 0), 10, 0));
        LandscapeParser.addGameObject(new GameObject(724, new Location(2341, 3688, 0), 10, 0));
        LandscapeParser.addGameObject(new GameObject(724, new Location(2341, 3693, 0), 10, 0));
        //LandscapeParser.addGameObject(new GameObject(6097, new Location(2343, 3690, 0), 10, 0));
        LandscapeParser.addGameObject(new GameObject(1317, new Location(2343, 3690, 0), 10, 3));
        LandscapeParser.addGameObject(new GameObject(1814, new Location(3090, 3475, 0), 4, 0));//edgeville lever
        NPC[] npcs = new NPC[]{new NPC(494, new Location(2327, 3687, 0))};
        for (NPC npc : npcs) {
            npc.setDirection(Direction.EAST);
        }

    }


    /**
     * Called when the server shuts down.
     *
     * @throws Throwable When an exception occurs.
     */
    public static final void shutdown() throws Throwable {
        SystemManager.flag(SystemState.TERMINATED);
    }

    /**
     * Gets the major update worker.
     *
     * @return The major update worker.
     */
    public static MajorUpdateWorker getMajorUpdateWorker() {
        return MAJOR_UPDATE_WORKER;
    }

    /**
     * Gets the ticks.
     *
     * @return The ticks.
     */
    public static int getTicks() {
        return ticks;
    }

    /**
     * Gets the settings.
     *
     * @return The settings.
     */
    public static GameSettings getSettings() {
        if (settings == null) {
            return (settings = GameSettings.parse("server.properties"));
        }
        return settings;
    }

    /**
     * Sets the settings.
     *
     * @param settings The settings to set.
     */
    public static void setSettings(GameSettings settings) {
        GameWorld.settings = settings;
    }

    /**
     * Gets the name of the world.
     *
     * @return the name.
     */
    public static String getName() {
        return getSettings().getName();
    }

    /**
     * Checks if its the economy world.
     *
     * @return {@code True} if so.
     */
    public static boolean isEconomyWorld() {
        return false;
    }

    public static DatabaseManager getDatabaseManager() {
        return dbm;
    }

    private static Location generateLocation() {
        Location random_location = new Location(3075 + RandomFunction.random(-15, 15), 3954 + RandomFunction.random(-15, 15), 0);
        if (!RegionManager.isTeleportPermitted(random_location)) {
            return generateLocation();
        }
        if (RegionManager.getObject(random_location) != null) {
            return generateLocation();
        }
        return random_location;
    }
}

