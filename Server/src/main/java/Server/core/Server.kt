package core

import core.game.system.SystemLogger
import core.game.system.SystemShutdownHook
import core.game.system.config.ServerConfigParser
import core.game.system.mysql.SQLManager
import core.game.world.GameSettings.Companion.parse
import core.game.world.GameWorld
import core.gui.ConsoleFrame
import core.net.NioReactor
import core.net.amsc.WorldCommunicator
import core.tools.TimeStamp
import core.tools.backup.AutoBackup
import plugin.ge.BotGrandExchange
import plugin.ge.GEAutoStock
import java.io.File
import java.net.BindException
import java.util.*

/**
 * The main class, for those that are unable to read the class' name.
 * @author Emperor
 * @author Vexia
 */
object Server {
    /**
     * The time stamp of when the server started running.
     */
	@JvmField
	var startTime: Long = 0

    /**
     * The NIO reactor.
     */
    var reactor: NioReactor? = null
    private val backup: AutoBackup? = null

    /**
     * The main method, in this method we load background utilities such as
     * cache and our world, then end with starting networking.
     * @param args The arguments cast on runtime.
     * @throws Throwable When an exception occurs.
     */
    @Throws(Throwable::class)
    @JvmStatic
    fun main(args: Array<String>) {
        if (args.isNotEmpty()) {
            ServerConfigParser(args[0])
        } else {
            println("No config file supplied! Attempting to use default...")
            ServerConfigParser("Server/worldprops/default.json")
        }
        if (GameWorld.getSettings()!!.isGui) {
            try {
                ConsoleFrame.getInstance().init()
            } catch (e: Exception) {
                println("X11 server missing - launching server with no GUI!")
            }
        }
        startTime = System.currentTimeMillis()
        val t = TimeStamp()
        //		backup = new AutoBackup();
        GameWorld.prompt(true)
        SQLManager.init()
        Runtime.getRuntime().addShutdownHook(Thread(SystemShutdownHook()))
        SystemLogger.log("Starting NIO reactor...")
        try {
            NioReactor.configure(43594 + GameWorld.getSettings()!!.worldId).start()
        } catch (e: BindException) {
            println("Port " + (43594 + GameWorld.getSettings()!!.worldId) + " is already in use!")
            throw e
        }
        val timer = java.util.Timer()
        val task = object : TimerTask() {
            override fun run() {
                autoReconnect()
            }
        }
        timer.schedule(task, 0, 1000 * 60 * 5)
        SystemLogger.log(GameWorld.getName() + " flags " + GameWorld.getSettings().toString())
        SystemLogger.log(GameWorld.getName() + " started in " + t.duration(false, "") + " milliseconds.")
        GEAutoStock.parse(ServerConstants.GRAND_EXCHANGE_DATA_PATH + "itemstostock.xml")
        BotGrandExchange.loadOffersFromDB()
        // TODO Run the eco kick starter 1 time for the live server then comment it out
//		ResourceManager.kickStartEconomy();
    }

    fun autoReconnect() {
        SystemLogger.log("Attempting autoreconnect of server")
        WorldCommunicator.connect()
    }
    /**
     * Gets the startTime.
     * @return the startTime
     */
    fun getStartTime(): Long {
        return startTime
    }

    /**
     * Sets the bastartTime.ZZ
     * @param startTime the startTime to set.
     */
    fun setStartTime(startTime: Long) {
        Server.startTime = startTime
    }
}