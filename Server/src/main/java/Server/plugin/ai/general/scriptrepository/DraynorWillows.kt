package plugin.ai.general.scriptrepository

import core.game.interaction.DestinationFlag
import core.game.interaction.MovementPulse
import core.game.node.item.Item
import core.game.world.map.zone.ZoneBorders
import core.tools.ItemNames
import plugin.ai.skillingbot.SkillingBotAssembler
import plugin.skill.Skills

class DraynorWillows : Script() {
    val willowZone = ZoneBorders(3084, 3225, 3091, 3239)


    val bankZone = ZoneBorders(3092, 3240, 3094, 3246)
    var state = State.CHOPPING


    override fun tick() {
        when (state) {
            State.CHOPPING -> {
                if (!willowZone.insideBorder(bot))
                    scriptAPI.walkTo(willowZone.randomLoc)
                else {
                    val willowtree = scriptAPI.getNode("willow", `object` = true)
                    if (willowtree != null)
                        willowtree.interaction.handle(bot, willowtree.interaction[0])
                    if (bot.inventory.getAmount(Item(ItemNames.WILLOW_LOGS_1519)) > 22)
                        state = State.BANKING
                }
            }

            State.BANKING -> {
                if (!bankZone.insideBorder(bot))
                    scriptAPI.walkTo(bankZone.randomLoc)
                else {
                    val bank = scriptAPI.getNode("Bank Booth", `object` = true)
                    if (bank != null) {
                        bot.pulseManager.run(object : MovementPulse(bot, bank, DestinationFlag.OBJECT) {
                            override fun pulse(): Boolean {
                                val logs = bot.inventory.getAmount(Item(ItemNames.WILLOW_LOGS_1519))
                                bot.inventory.remove(Item(ItemNames.WILLOW_LOGS_1519, logs))
                                state = State.CHOPPING
                                return true
                            }
                        })
                    }
                }
            }


        }
    }

    init {
        inventory.add(Item(ItemNames.ADAMANT_AXE))
        skills[Skills.WOODCUTTING] = 35
    }

    override fun newInstance(): Script {
        val script = DraynorWillows()
        script.bot = SkillingBotAssembler().produce(SkillingBotAssembler.Wealth.values().random(), bot.startLocation)
        return script
    }

    enum class State {
        CHOPPING,
        BANKING
    }
}