package plugin.ai.general.scriptrepository

import core.game.node.item.Item
import core.tools.ItemNames
import plugin.ai.skillingbot.SkillingBotAssembler
import plugin.interaction.inter.GlassInterface
import plugin.skill.Skills
import plugin.skill.crafting.GlassProduct

class GlassBlowingBankstander : Script(){
    var state = State.BLOWING
    override fun tick() {
        val bank = scriptAPI.getNode("Bank booth")
        bot.faceLocation(bank?.location)
        state = when(state){
            State.BLOWING -> {
                bot.inventory.add(Item(ItemNames.GLASSBLOWING_PIPE_1785))
                bot.inventory.add(Item(ItemNames.MOLTEN_GLASS_1775,27))
                GlassInterface.make(bot,GlassProduct.ORB,27)
                State.BANKING
            }

            State.BANKING -> {
                bot.inventory.clear()
                State.BLOWING
            }
        }
    }

    override fun newInstance(): Script {
        val script = GlassBlowingBankstander()
        script.bot = SkillingBotAssembler().produce(SkillingBotAssembler.Wealth.AVERAGE,bot.startLocation)
        return script
    }

    init {
        skills[Skills.CRAFTING] = 99
    }

    enum class State {
        BLOWING,
        BANKING
    }
}