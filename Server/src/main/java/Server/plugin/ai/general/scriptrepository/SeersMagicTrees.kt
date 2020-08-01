package plugin.ai.general.scriptrepository

import core.game.content.ItemNames
import core.game.interaction.DestinationFlag
import core.game.interaction.MovementPulse
import core.game.node.item.Item
import core.game.world.map.Location
import core.game.world.map.path.Pathfinder
import core.tools.RandomFunction
import plugin.ai.skillingbot.SkillingBotAssembler
import plugin.skill.Skills

class SeersMagicTrees : Script(){
    var state = State.CHOPPING
    var stage = 0

    override fun tick() {
        when(state){
            State.CHOPPING -> {
                val tree = scriptAPI.getNode(1306,true)
                tree?.interaction?.handle(bot,tree.interaction[0])
                if(bot.inventory.getAmount(1513) > 25){
                    state = State.FIND_BANK
                }
            }

            State.FIND_BANK -> {
                if(stage == 0)
                    Pathfinder.find(bot, Location.create(2719, 3431, 0)).walk(bot).also { stage++ }
                when(bot.location){
                    Location.create(2719, 3431, 0) -> Pathfinder.find(bot,Location.create(2720, 3460, 0)).walk(bot)
                    Location.create(2720, 3460, 0) -> Pathfinder.find(bot,Location.create(2726, 3491, 0)).walk(bot)
                    Location.create(2726, 3491, 0) -> state = State.BANKING.also { stage = 0 }
                }
            }

            State.BANKING -> {
                val bank = scriptAPI.getNode(25808,true)
                if(bank != null)
                    bot.pulseManager.run(object: MovementPulse(bot,bank, DestinationFlag.OBJECT){
                        override fun pulse(): Boolean {
                            bot.faceLocation(bank.location)
                            scriptAPI.bankItem(ItemNames.MAGIC_LOGS_1513)
                            if(bot.bank.getAmount(ItemNames.MAGIC_LOGS_1513) > 50){
                                state = State.TELE_GE
                                return true
                            }
                            state = State.RETURN_TO_TREES
                            return true
                        }
                    })
            }

            State.RETURN_TO_TREES -> {
                if(bot.location == Location.create(2756, 3478, 0))
                    Pathfinder.find(bot,Location.create(2725, 3485, 0)).walk(bot).also { stage++ }
                if(stage == 0)
                    Pathfinder.find(bot, Location.create(2725, 3485, 0)).walk(bot).also { stage++ }
                when(bot.location){
                    Location.create(2725, 3485, 0) -> Pathfinder.find(bot,Location.create(2726, 3477, 0)).walk(bot)
                    Location.create(2726, 3477, 0) -> Pathfinder.find(bot,Location.create(2719, 3431, 0)).walk(bot)
                    Location.create(2719, 3431, 0) -> Pathfinder.find(bot,Location.create(2701, 3396, 0)).walk(bot)
                    Location.create(2701, 3396, 0) -> state = State.CHOPPING.also { stage = 0 }
                }
            }

            State.TELE_GE -> {
                state = State.SELL_GE
                scriptAPI.teleportToGE()
            }

            State.SELL_GE -> {
                state = State.TELE_SEERS
                scriptAPI.sellOnGE(ItemNames.MAGIC_LOGS_1513,750)
            }

            State.TELE_SEERS -> {
                state = State.RETURN_TO_TREES
                scriptAPI.teleport(Location.create(2756, 3478, 0))
            }
        }
    }

    init {
        inventory.add(Item(ItemNames.RUNE_AXE))
        skills[Skills.WOODCUTTING] = RandomFunction.random(75,99)
    }

    enum class State {
        CHOPPING,
        FIND_BANK,
        BANKING,
        RETURN_TO_TREES,
        TELE_GE,
        SELL_GE,
        TELE_SEERS
    }

    override fun newInstance(): Script {
        val script = SeersMagicTrees()
        script.bot = SkillingBotAssembler().produce(SkillingBotAssembler.Wealth.AVERAGE,bot.startLocation)
        return script
    }
}