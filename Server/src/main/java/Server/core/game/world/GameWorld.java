package core.game.world;

import core.ServerConstants;
import core.cache.Cache;
import core.cache.ServerStore;
import core.plugin.Plugin;
import plugin.CorePluginTypes.StartupPlugin;
import plugin.ge.GrandExchangeDatabase;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.npc.drop.RareDropTable;
import core.game.node.entity.player.Player;
import core.game.node.object.GameObject;
import core.game.system.SystemLogger;
import core.game.system.SystemManager;
import core.game.system.SystemState;
import core.game.system.mysql.SQLManager;
import core.game.system.script.ScriptManager;
import core.game.system.task.Pulse;
import core.game.system.task.TaskExecutor;
import core.game.world.callback.CallbackHub;
import core.game.world.map.Direction;
import core.game.world.map.Location;
import core.game.world.map.RegionManager;
import core.game.world.map.build.LandscapeParser;
import core.game.world.repository.DisconnectionQueue;
import core.game.world.repository.NodeList;
import core.game.world.repository.Repository;
import core.plugin.PluginManager;
import core.tools.RandomFunction;
import core.tools.mysql.DatabaseManager;
import core.worker.MajorUpdateWorker;

import java.util.*;
import java.util.concurrent.*;
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
    public static final Lock LOCK = new ReentrantLock();

    /**
     * The pulse tasks list.
     */
    public static final List<Pulse> TASKS = new ArrayList<>();

    public static final ScheduledThreadPoolExecutor executer = (ScheduledThreadPoolExecutor)Executors.newScheduledThreadPool(100);

    public static final ThreadPoolExecutor ThreadPool = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);

    public static int cores = Runtime.getRuntime().availableProcessors();

    public static final List<StartupPlugin> STARTUP_PLUGINS = new ArrayList<>();

    /**
     * The game settings to use.
     */
    private static GameSettings settings;

    /**
     * The current amount of (600ms) cycles elapsed.
     */
    public static int ticks;

    private static DatabaseManager dbm = new DatabaseManager(ServerConstants.DATABASES);

    private static int eventTicks;

    private static int cfTicks;

    // The multithreaded pulsemanagers
    public static PulseRunner FastPulser = new PulseRunner();

    public static PulseRunner Pulser = new PulseRunner();


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
    @Deprecated
    public static void submit(Pulse pulse) {
        GameWorld.Pulser.submit(pulse);
    }

    public static void pulse(){
        GameWorld.ticks++;
        if (GameWorld.ticks % 50 == 0) {
            TaskExecutor.execute(() -> {
                NodeList<Player> player = Repository.getPlayers();
                try {
                    player.stream().filter(Objects::nonNull).filter(p -> !p.isArtificial() && p.isPlaying()).forEach(p -> DisconnectionQueue.save(p,false));
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
        RareDropTable.init();
        SystemLogger.log("Initialized Rare Drop Table from " + RareDropTable.RDT_LOCATION);
        //ResourceAIPManager.get().init(); Commented out as we do not use Skilling Tasks, which is what this is for
        //ImmerseWorld.init(); disabled until bots are rewritten to work with the new pulse system
        SQLManager.postPlugin();
        CallbackHub.call();
        STARTUP_PLUGINS.forEach(plugin -> {
            if(plugin != null){
                plugin.run();
            }
        });
        if (run) {
            SystemManager.flag(GameWorld.getSettings().isDevMode() ? SystemState.PRIVATE : SystemState.ACTIVE);
        }
        System.gc();
        FastPulser.init(20);
        Pulser.init(600);
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
            return (settings = GameSettings.parse("worldprops/server1.xml"));
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

