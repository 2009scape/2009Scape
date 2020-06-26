package core.game.world;

import core.ServerConstants;
import core.cache.Cache;
import core.cache.ServerStore;
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
        parseObjects();
        CallbackHub.call();
        if (run) {
            SystemManager.flag(GameWorld.getSettings().isDevMode() ? SystemState.PRIVATE : SystemState.ACTIVE);
        }
        System.gc();
        FastPulser.init(20);
        Pulser.init(600);
    }

    //39956
    private static void parseObjects() {
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
        LandscapeParser.addGameObject(new GameObject(1293, new Location(2461, 3446, 0), 10,0)); // Gnome Stronghold Spirit Tree
        LandscapeParser.addGameObject(new GameObject(2646, new Location(2445, 3413, 0), 10,0)); // Gnome Stronghold flax
        LandscapeParser.addGameObject(new GameObject(2646, new Location(2447, 3414, 0), 10,2)); // Gnome Stronghold flax
        LandscapeParser.addGameObject(new GameObject(2646, new Location(2447, 3421, 0), 10,0));// Gnome Stronhold flax

        LandscapeParser.removeGameObject(new GameObject(37670, new Location(3292,3299,0),10,0));
        LandscapeParser.addGameObject(new GameObject(37304, new Location(3292,3299,0),10,0));

        LandscapeParser.addGameObject(new GameObject(20987,new Location(2821,2996,0),10,0));

        NPC[] npcs = new NPC[]{new NPC(494, new Location(2327, 3687, 0))};
        Arrays.stream(npcs).forEach(npc -> npc.setDirection(Direction.EAST));
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
            return (settings = GameSettings.parse("worldprops/server.properties"));
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

