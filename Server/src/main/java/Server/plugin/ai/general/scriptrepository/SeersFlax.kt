package plugin.ai.general.scriptrepository

import core.game.interaction.DestinationFlag
import core.game.interaction.MovementPulse
import core.game.node.item.Item
import core.game.system.SystemLogger
import core.game.world.map.Location
import core.game.world.map.path.Pathfinder
import core.tools.ItemNames
import plugin.ai.skillingbot.SkillingBotAssembler
import plugin.skill.Skills
import plugin.skill.crafting.spinning.SpinningItem
import plugin.skill.crafting.spinning.SpinningPulse

class SeersFlax : Script(){
    var state = State.PICKING
    var stage = 0
    var doorOpen = false
    override fun tick() {

        when(state){

            State.PICKING -> {
                val flax = scriptAPI.getNode(2646,true)
                flax?.interaction?.handle(bot,flax.interaction[1])
                if(bot.inventory.getAmount(ItemNames.FLAX_1779) > 25){
                    state = State.TO_SPINNER
                }
            }

            State.TO_SPINNER -> {
                if(stage == 0)
                    Pathfinder.find(bot, Location.create(2736, 3442, 0)).walk(bot).also { stage++ }
                when(bot.location){
                    Location.create(2736, 3442, 0) -> Pathfinder.find(bot,Location.create(2722, 3456, 0)).walk(bot)
                    Location.create(2722, 3456, 0) -> Pathfinder.find(bot,Location.create(2716, 3472, 0)).walk(bot)
                    Location.create(2716, 3472, 0) -> {
                        val door = scriptAPI.getNode(25819,true)
                        if(door != null && door.location?.withinDistance(bot.location,2)!!){
                            door.interaction?.handle(bot, door.interaction[0])
                            doorOpen = true
                        } else {
                            val ladder = scriptAPI.getNode(25938,true)
                            ladder?.interaction?.handle(bot,ladder.interaction[0])                        }
                    }
                    Location.create(2714, 3470, 1) -> {
                        val spinner = scriptAPI.getNode(25824,true)
                        bot.faceLocation(spinner?.location)
                        bot.pulseManager.run(object: MovementPulse(bot,spinner, DestinationFlag.OBJECT){
                            override fun pulse(): Boolean {
                                bot.faceLocation(spinner?.location)
                                state = State.SPINNING.also { stage = 0; doorOpen = false }
                                return true
                            }
                        })
                    }
                }
            }

            State.SPINNING -> {
                bot.pulseManager.run(SpinningPulse(bot, Item(ItemNames.FLAX_1779),bot.inventory.getAmount(ItemNames.FLAX_1779),SpinningItem.FLAX))
                state = State.FIND_BANK
            }

            State.FIND_BANK -> {
                when(bot.location){
                    Location.create(2711, 3471, 1) -> {
                        val ladder = scriptAPI.getNode(25939,true)
                        ladder?.interaction?.handle(bot,ladder?.interaction[0])
                    }
                    Location.create(2714, 3470, 0) -> Pathfinder.find(bot,Location.create(2715, 3472, 0)).walk(bot)
                    Location.create(2715, 3472, 0) -> {
                        val door = scriptAPI.getNode(25819,true)
                        if(door != null && door.location?.withinDistance(bot.location,2)!!){
                            door.interaction?.handle(bot, door.interaction[0])
                            doorOpen = true
                        } else {
                            Pathfinder.find(bot,Location.create(2726, 3481, 0)).walk(bot)
                        }
                    }
                    Location.create(2726, 3481, 0) -> Pathfinder.find(bot,Location.create(2724, 3491, 0)).walk(bot)
                    Location.create(2724, 3491, 0) -> state = State.BANKING
                }
            }

            State.BANKING -> {
                val bank = scriptAPI.getNode(25808,true)
                if(bank != null)
                    bot.pulseManager.run(object: MovementPulse(bot,bank, DestinationFlag.OBJECT){
                        override fun pulse(): Boolean {
                            bot.faceLocation(bank.location)
                            scriptAPI.bankItem(ItemNames.BOW_STRING_1777)
                            if(bot.bank.getAmount(ItemNames.BOW_STRING_1777) > 500){
                                SystemLogger.log("Should tele")
                                state = State.TELE_GE
                                return true
                            }
                            state = State.RETURN_TO_FLAX
                            return true
                        }
                    })
            }

            State.RETURN_TO_FLAX -> {
                if(bot.location == Location.create(2756, 3478, 0))
                    Pathfinder.find(bot,Location.create(2726, 3486, 0)).walk(bot)
                if(stage == 0)
                    Pathfinder.find(bot,Location.create(2726, 3486, 0)).walk(bot).also { stage++ }
                when(bot.location){
                    Location.create(2726, 3486, 0) -> Pathfinder.find(bot,Location.create(2729, 3469, 0)).walk(bot)
                    Location.create(2729, 3469, 0) -> Pathfinder.find(bot,Location.create(2734, 3447, 0)).walk(bot)
                    Location.create(2734, 3447, 0) -> state = State.PICKING.also { stage = 0 }
                }
            }

            State.TELE_GE -> {
                scriptAPI.teleportToGE()
                state = State.SELL_GE
            }

            State.SELL_GE -> {
                scriptAPI.sellOnGE(ItemNames.BOW_STRING_1777,250)
                state = State.TELE_CAMELOT
            }

            State.TELE_CAMELOT -> {
                scriptAPI.teleport(Location.create(2756, 3478, 0))
                stage = 0
                state = State.RETURN_TO_FLAX
            }

        }
    }

    enum class State {
        PICKING,
        SPINNING,
        TO_SPINNER,
        FIND_BANK,
        BANKING,
        TELE_GE,
        SELL_GE,
        RETURN_TO_FLAX,
        TELE_CAMELOT
    }

    init {
        skills[Skills.CRAFTING] = 10
    }

    override fun newInstance(): Script {
        val script = SeersFlax()
        script.bot = SkillingBotAssembler().produce(SkillingBotAssembler.Wealth.POOR,bot.startLocation)
        return script
    }
}