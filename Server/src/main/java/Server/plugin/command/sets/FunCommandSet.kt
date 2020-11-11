package plugin.command.sets

import core.game.node.entity.npc.NPC
import core.game.system.task.Pulse
import core.game.world.GameWorld
import core.game.world.map.RegionManager
import core.game.world.update.flag.context.Animation
import core.plugin.InitializablePlugin
import plugin.command.Command
import plugin.command.CommandSet
import plugin.quest.tutorials.tutorialisland.CharacterDesign
import java.util.*

@InitializablePlugin
class FunCommandSet : CommandSet(Command.Privilege.ADMIN) {

    var npcs: List<NPC> = ArrayList()


    override fun defineCommands() {

        /**
         * Force animation + messages on all NPCs in a radius of 10 from the player.
         */
        define("npcareaanim") { player, args ->
            if (args.size < 3) {
                reject(player, "Syntax error: ::npcareaanim <Animation ID> <String>")
                return@define
            }
            npcs = RegionManager.getLocalNpcs(player.location, 10)
            for (n in npcs) {
                n.sendChat(args.slice(2 until args.size).joinToString(" "))
                n.lock(6)
                n.faceTemporary(player, 6)
                n.animator.animate(Animation(args[1].toInt()))
                n.animate(Animation.create(-1), 6)
            }
        }


        /**
         * Transform a player's appearance into that of an NPC.
         */
        define("pnpc",Command.Privilege.MODERATOR){ player, args ->
            if(args.size < 2){
                reject(player, "Usage: ::pnpc <npcid>")
                return@define
            }

            val pnpc_id = args[1].toIntOrNull()
            if(pnpc_id == null){
                reject(player, "<npcid> must be a valid integer.")
                return@define
            }

            player.appearance.transformNPC(pnpc_id)
            player.sendMessage("Transformed into NPC $pnpc_id")
        }


        /**
         * Open bank
         */
        define("bank"){ player, _ ->
            player.getBank().open()
        }

        /**
         * Toggle invisibility
         */
        define("invis"){ player, _ ->
            player.isInvisible = !player.isInvisible
            player.sendMessage("You are now ${if (player.isInvisible) "invisible" else "visible"} to others.")
        }


        /**
         * Toggle 1-hit kills
         */
        define("1hit"){ player, _ ->
            player.setAttribute("1hko", !player.getAttribute("1hko", false))
            player.sendMessage("1-hit KO mode " + if (player.getAttribute("1hko", false)) "on." else "off.")
        }


        /**
         * Toggle god mode
         */
        define("god"){ player, _ ->
            player.setAttribute("godMode", !player.getAttribute("godMode", false))
            player.sendMessage("God mode ${if (player.getAttribute("godMode", false)) "enabled." else "disabled."}")
        }


        /**
         * Go on Mr Bones' Wild Ride
         */
        define("mrboneswildride"){ player, _ ->
            val boneMode = !player.getAttribute("boneMode",false)
            player.setAttribute("boneMode", boneMode)
            player.sendMessage("Bone Mode ${if (boneMode) "<col=00ff00>ENGAGED</col>." else "<col=ff0000>POWERING DOWN</col>."}")
            player.appearance.rideCart(boneMode)
            if (player.appearance.isRidingMinecart) {
                var i = 0
                GameWorld.Pulser.submit(object : Pulse(1, player) {
                    override fun pulse(): Boolean {
                        if (i++ % 12 == 0) player.sendChat("I want to get off Mr. Bones Wild Ride.")
                        player.moveStep()
                        return !player.appearance.isRidingMinecart
                    }
                })
            }
        }


        /**
         * Opens up the makeover interface
         */
        define("makeover",Command.Privilege.MODERATOR){ player, _ ->
            CharacterDesign.open(player)
        }

    }
}
