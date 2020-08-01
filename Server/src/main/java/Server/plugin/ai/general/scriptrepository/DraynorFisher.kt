package plugin.ai.general.scriptrepository

import core.game.interaction.DestinationFlag
import core.game.interaction.MovementPulse
import core.game.node.item.Item
import core.game.world.map.Location
import core.game.world.map.zone.ZoneBorders
import core.tools.ItemNames
import plugin.ai.skillingbot.SkillingBotAssembler

class DraynorFisher  : Script() {
    val fishingZone = ZoneBorders(3085, 3223,3089, 3233)
    val bankZone = ZoneBorders(3092, 3240,3094, 3246)


    var state = State.FISHING
    override fun tick() {
        when(state){
            State.FISHING -> {
                if(!fishingZone.insideBorder(bot))
                    scriptAPI.walkTo(fishingZone.randomLoc)
                else {
                    val spot = scriptAPI.getNode(316,false)
                    spot?.interaction?.handle(bot,spot.interaction[0]) ?: scriptAPI.walkTo(fishingZone.randomLoc)
                    if(bot.inventory.getMaximumAdd(Item(4151)) < 5)
                        state = State.BANKING
                }
            }

            State.BANKING -> {
                if(!bankZone.insideBorder(bot))
                    scriptAPI.walkTo(bankZone.randomLoc)
                else {
                    val bank = scriptAPI.getNode("Bank booth")
                    if (bank != null){
                        bot.pulseManager.run(object : MovementPulse(bot,bank, DestinationFlag.OBJECT){
                            override fun pulse(): Boolean {
                                bot.inventory.clear()
                                state = State.FISHING
                                bot.inventory.add(Item(ItemNames.SMALL_FISHING_NET_303))
                                return true
                            }
                        })
                    }
                }
            }


        }
    }

    override fun newInstance(): Script {
        val script = DraynorFisher()
        script.bot = SkillingBotAssembler().produce(SkillingBotAssembler.Wealth.POOR, Location.create(3094, 3242, 0))
        return script
    }

    init {
        inventory.add(Item(ItemNames.SMALL_FISHING_NET_303))
    }

    enum class State {
        FISHING,
        BANKING
    }
}