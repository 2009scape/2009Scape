package core.game.system.command.sets

import core.game.component.Component
import core.game.node.entity.player.Player
import core.game.node.entity.player.link.quest.QuestRepository
import core.plugin.Initializable
import core.game.system.command.Command

@Initializable
class QuestCommandSet : CommandSet(Command.Privilege.ADMIN){
    override fun defineCommands() {
        /**
         * Completes all quests
         */
        define("allquest"){player,_->
            for (quest in QuestRepository.getQuests().values) {
                quest.finish(player)
            }
        }

        /**
         * Displays the currently implemented quests with debug information
         */
        define("quest"){player,_ ->
            sendQuestsDebug(player)
        }

        /**
         * Sets stage of quest
         */
        define("setqueststage"){player,args ->
            if (args.size < 2) {
                reject(player,"You must specify the index# of a quest, and a stage number")
                return@define
            }
            val quest = args[1].toIntOrNull() ?: return@define
            val stage = args[2].toIntOrNull() ?: return@define
            player.questRepository.setStage(player.questRepository.forIndex(quest), stage)
            player.sendMessage("<col=209dff>Setting " + player.questRepository.forIndex(quest).name + " to stage $stage</col>")
        }

        /**
         * Displays the currently implemented quests
         */
        define("quests", Command.Privilege.STANDARD){ player, _ ->
            sendQuests(player)
        }
    }

    /**
     * Sends the list of quests.
     * @param player the player.
     */
    private fun sendQuests(player: Player?) {
        player!!.interfaceManager.open(Component(275))
        for (i in 0..310) {
            player.packetDispatch.sendString("", 275, i)
        }
        var lineId = 11
        player.packetDispatch.sendString("<col=ecf0f1>" + "Available Quests" + "</col>", 275, 2)
        for (q in QuestRepository.getQuests().toSortedMap().values) {
            // Add a space to beginning and end of string for the strikethrough
            player.packetDispatch.sendString("<col=ecf0f1>" + (if (q.isCompleted(player)) "<str> " else "") + q.name + " ", 275, lineId++)
        }
    }

    /**
     * Sends the list of quests with debug information
     * @param player the player.
     */
    private fun sendQuestsDebug(player: Player?) {
        player!!.interfaceManager.open(Component(275))
        for (i in 0..310) {
            player.packetDispatch.sendString("", 275, i)
        }
        var lineId = 11
        player.packetDispatch.sendString("<col=ecf0f1>" + "Quests Debug" + "</col>", 275, 2)
        for (q in QuestRepository.getQuests().values) {
            // Add a space to beginning and end of string for the strikethrough
            player.packetDispatch.sendString("<col=ecf0f1>${q.name}</col>", 275, lineId++)
            player.packetDispatch.sendString("<col=ecf0f1>Index: </col><col=ff1f1f>${q.index}</col> | <col=ecf0f1>Stage:</col> <col=ff1f1f>${player.questRepository.getStage(q)}</col>", 275, lineId++)
            player.packetDispatch.sendString("<str>          ", 275, lineId++)
        }
    }
}