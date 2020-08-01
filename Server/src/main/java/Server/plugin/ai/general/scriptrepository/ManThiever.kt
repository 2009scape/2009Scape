package plugin.ai.general.scriptrepository

import core.game.node.item.Item
import plugin.ai.skillingbot.SkillingBotAssembler
import java.util.*

class ManThiever : Script() {
    override fun tick() {
        val man = scriptAPI.getNode("Man")
        man?.interaction?.handle(bot, man.interaction[2])
    }

    override fun newInstance(): Script? {
        val script = ManThiever()
        script.bot = SkillingBotAssembler().produce(SkillingBotAssembler.Wealth.POOR, bot.startLocation)
        return script
    }

    init {
        equipment.addAll(Arrays.asList(Item(1103), Item(1139), Item(1265)))
    }
}