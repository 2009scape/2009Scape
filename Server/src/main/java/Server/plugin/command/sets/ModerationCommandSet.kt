package plugin.command.sets

import core.game.system.task.Pulse
import core.game.world.GameWorld
import core.game.world.map.Location
import core.game.world.repository.Repository
import core.plugin.InitializablePlugin
import plugin.command.Command
import plugin.command.CommandSet
import java.util.concurrent.TimeUnit

@InitializablePlugin
/**
 * Moderation commands
 * @author Ceikry
 */
class ModerationCommandSet : CommandSet(Command.Privilege.MODERATOR){
    override fun defineCommands() {
        val MAX_JAIL_TIME = 1800 //Max jail time (in seconds)


        /**
         * Jail a player
         * =============================================================================================================
         */
        define("jail"){player,args ->
            if(args.size < 3) {
                reject(player,"Usage: ::jail <seconds> <player>")
                return@define
            }

            val timeSeconds = args[1].toIntOrNull()
            if(timeSeconds == null){
                reject(player, "<seconds> Must be a valid integer!")
                return@define
            }
            if(timeSeconds > MAX_JAIL_TIME){
                reject(player, "Maximum jail time is $MAX_JAIL_TIME seconds.")
                return@define
            }
            val name = args.slice(2 until args.size).joinToString("_")
            val otherPlayer = Repository.getPlayerByName(name)
            if(otherPlayer == null){
                reject(player, "Can not find $name in the player list!")
                return@define
            }
            player.sendMessage("Jailing ${otherPlayer.username} for $timeSeconds seconds.")
            GameWorld.Pulser.submit(object : Pulse(3){
                val originalLoc = otherPlayer.location
                val releaseTime = System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(timeSeconds.toLong())
                override fun pulse(): Boolean {
                    val done = System.currentTimeMillis() >= releaseTime
                    if(otherPlayer.location != Location.create(3228, 3407, 0)){
                        otherPlayer.properties.teleportLocation = Location.create(3228, 3407, 0)
                    }
                    if(done){
                        otherPlayer.properties.teleportLocation = originalLoc
                    }
                    return done
                }
            })
        }
        /**
         * =============================================================================================================
         */


    }
}