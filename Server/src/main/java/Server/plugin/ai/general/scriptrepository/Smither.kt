package plugin.ai.general.scriptrepository

import core.game.interaction.DestinationFlag
import core.game.interaction.MovementPulse
import core.game.node.item.Item
import core.tools.ItemNames
import core.tools.RandomFunction
import plugin.skill.Skills
import plugin.skill.smithing.Bars
import plugin.skill.smithing.SmithingPulse

class Smither : Script() {
    var state = State.SMITHING
    var amountToSmith = 0
    override fun tick() {
        when (state) {
            State.SMITHING -> {
                for (i in inventory) {
                    bot.inventory.add(i)
                }
                val anvil = scriptAPI.getNode("anvil", `object` = true, nearest = false)
                if (anvil != null) {
                    bot.pulseManager.run(object : MovementPulse(bot, anvil, DestinationFlag.OBJECT) {
                        override fun pulse(): Boolean {
                            bot.faceLocation(anvil.location)
                            bot.pulseManager.run(SmithingPulse(bot, Item(2353), Bars.STEEL_ARROW_TIPS, amountToSmith))
                            state = State.BANKING
                            return true
                        }
                    })
                }
            }

            State.BANKING -> {
                val bank = scriptAPI.getNode("Bank booth", nearest = false)
                if (bank != null)
                    bot.pulseManager.run(object : MovementPulse(bot, bank, DestinationFlag.OBJECT) {
                        override fun pulse(): Boolean {
                            bot.faceLocation(bank.location)
                            bot.inventory.clear()
                            state = State.SMITHING
                            return true
                        }
                    })
            }
        }
    }

    override fun newInstance(): Script {
        val script = Smither()
        return script
    }

    init {
        amountToSmith = 27 / RandomFunction.random(1, 5)
        skills[Skills.SMITHING] = RandomFunction.random(33, 99)
        inventory.add(Item(ItemNames.HAMMER))
        inventory.add(Item(ItemNames.STEEL_BAR, 27))
    }

    enum class State {
        SMITHING,
        BANKING
    }
}