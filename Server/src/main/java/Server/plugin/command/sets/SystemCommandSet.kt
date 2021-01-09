package plugin.command.sets

import core.game.node.entity.player.info.login.Response
import core.game.node.entity.player.info.portal.PlayerSQLManager
import core.game.node.item.Item
import core.game.system.SystemManager
import core.game.system.SystemState
import core.plugin.InitializablePlugin
import core.tools.Items
import plugin.command.Command
import plugin.command.CommandSet

@InitializablePlugin
class SystemCommandSet : CommandSet(Command.Privilege.ADMIN){
    override fun defineCommands() {
        /**
         * Start an update countdown
         */
        define("update"){player,args ->
            if (args.size > 1) {
                SystemManager.getUpdater().setCountdown(args[1].toInt())
            }
            SystemManager.flag(SystemState.UPDATING)
        }

        /**
         * Cancel an update countdown
         */
        define("cancelupdate"){player,_ ->
            SystemManager.getUpdater().cancel()
        }


        /**
         * Allows a player to reset their password
         */
        define("resetpassword",Command.Privilege.STANDARD){ player, args ->
            if(args.size != 3){
                reject(player,"Usage: ::resetpassword current new")
                reject(player,"WARNING: THIS IS PERMANENT.")
                reject(player,"WARNING: PASSWORD CAN NOT CONTAIN SPACES.")
                return@define
            }
            val oldPass = args[1]
            var newPass = args[2]

            if(PlayerSQLManager.getCredentialResponse(player.details.username,oldPass) != Response.SUCCESSFUL){
                reject(player,"INVALID PASSWORD!")
                return@define
            }

            if(newPass.length < 5 || newPass.length > 20){
                reject(player,"NEW PASSWORD MUST BE BETWEEN 5 AND 20 CHARACTERS")
                return@define
            }

            if(newPass == player.username){
                reject(player,"PASSWORD CAN NOT BE SAME AS USERNAME.")
                return@define
            }

            if(newPass == oldPass){
                reject(player,"PASSWORDS CAN NOT BE THE SAME")
                return@define
            }

            newPass = SystemManager.getEncryption().hashPassword(newPass)
            PlayerSQLManager.updatePassword(player.username.toLowerCase().replace(" ","_"),newPass)
            reject(player,"Password updated successfully.")
        }

        define("potato"){player,_ ->
            player.inventory.add(Item(Items.ROTTEN_POTATO_5733))
        }

    }
}