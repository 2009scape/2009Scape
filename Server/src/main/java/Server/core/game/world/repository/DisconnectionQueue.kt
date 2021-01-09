package core.game.world.repository

import core.game.node.entity.player.Player
import core.game.node.entity.player.info.login.PlayerParser
import core.game.system.SystemLogger.log
import core.game.system.mysql.SQLEntryHandler
import core.game.system.mysql.impl.HighscoreSQLHandler
import core.game.system.mysql.impl.PlayerLogSQLHandler
import core.game.system.task.TaskExecutor
import core.game.world.GameWorld
import java.util.concurrent.ConcurrentHashMap

/**
 * Handles disconnecting players queuing.
 * @author Emperor
 */
class DisconnectionQueue {
    /**A
     * The pending disconnections queue.
     */
    private val queue: MutableMap<String, DisconnectionEntry?>

    /**
     * Updates all entries.
     */
    fun update() {
        if (queue.isEmpty() || GameWorld.ticks % 3 != 0 && GameWorld.settings?.isDevMode != true) {
            return
        }
        //make a copy of current entries as to avoid concurrency exceptions
        val entries = ArrayList(queue.entries)

        //loop through entries and disconnect each
        entries.forEach {
            if(finish(it.value,false)) queue.remove(it.key)
        }
    }

    /**
     * Finishes a disconnection.
     * @param entry The entry.
     * @param force If finalization should be forced.
     */
    private fun finish(entry: DisconnectionEntry?, force: Boolean): Boolean {
        val player = entry!!.player
        if (!force && player.allowRemoval()) {
            return false
        }
        if (entry.isClear) {
            player.clear()
        }
        Repository.playerNames.remove(player.name)
        Repository.lobbyPlayers.remove(player)
        Repository.players.remove(player)
        Repository.LOGGED_IN_PLAYERS.remove(player.details.username)
        log("Player cleared. Removed ${player.details.username}")
        log("Remaining Queue:")
        for(un in Repository.LOGGED_IN_PLAYERS)
            log(un)
        try {
            player.communication.clan.leave(player, false)
        } catch (e: Exception) {
        }
        if (player.isArtificial) {
            return true
        }
        if (!force) {
            TaskExecutor.executeSQL { save(player, true) }
            return true
        }
        save(player, false)
        return true
    }

    /**
     * Gets a queued player.
     * @param name The player name.
     * @return The player instance.
     */
    operator fun get(name: String?): Player? {
        val entry = queue[name]
        return entry?.player
    }

    /**
     * Clears the queue.
     */
    fun clear() {
        for (entry in queue.values) {
            finish(entry, true)
        }
        queue.clear()
    }

    fun safeClear(){
        for(entry in queue.values){
            finish(entry,false)
        }
        queue.clear()
    }
    /**
     * Adds a player to the disconnection queue.
     * @param player The player.
     * @param clear If the player should be cleared.
     */
    /**
     * Adds a player to the disconnection queue.
     * @param player The player.
     */
    @JvmOverloads
    fun add(player: Player, clear: Boolean = false) {
        queue[player.name] = DisconnectionEntry(player, clear)
    }

    /**
     * Checks if the queue contains the player name.
     * @param name The name.
     * @return `True` if so.
     */
    operator fun contains(name: String?): Boolean {
        return queue.containsKey(name)
    }

    /**
     * Removes a queued player.
     * @param name The name.
     */
    fun remove(name: String?) {
        queue.remove(name)
    }

    /**
     * Represents an entry in the disconnection queue, holding the disconnected
     * player and time stamp of disconnection.
     * @author Emperor
     */
    internal inner class DisconnectionEntry(
            /**
             * The player.
             */
            val player: Player,
            /**
             * If the `Player#clear()` method should be called.
             */
            var isClear: Boolean) {
        /**
         * Gets the timeStamp.
         * @return The timeStamp.
         */
        /**
         * Sets the timeStamp.
         * @param timeStamp The timeStamp to set.
         */
        /**
         * The time of disconnection.
         */
        var timeStamp: Int

        /**
         * Gets the player.
         * @return The player.
         */

        /**
         * Gets the clear.
         * @return The clear.
         */
        /**
         * Sets the clear.
         * @param isClear The clear to set.
         */

        /**
         * Constructs a new `DisconnectionQueue` `Object`.
         * @param player The disconnecting player.
         * @param clear If the player should be cleared.
         */
        init {
            timeStamp = GameWorld.ticks
        }
    }

    /**
     * Saves the player.
     * @param player The player to be saved.
     * @param sql If the sql database should be updated.
     */
    fun save(player: Player, sql: Boolean): Boolean {
        try {
            PlayerParser.save(player)
            if (sql) {
                player.details.sqlManager.update(player)
                player.details.save()
                SQLEntryHandler.write(HighscoreSQLHandler(player))
                SQLEntryHandler.write(PlayerLogSQLHandler(player.monitor, player.name))
            }
            return true
        } catch (t: Throwable) {
            t.printStackTrace()
        }
        return false
    }
    /**
     * Constructs a new `DisconnectionQueue` `Object`.
     */
    init {
        queue = ConcurrentHashMap()
    }
}