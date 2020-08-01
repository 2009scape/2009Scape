package plugin.ai.general.scriptrepository

import core.game.world.map.zone.ZoneBorders
import plugin.ai.skillingbot.SkillingBotAssembler
import plugin.skill.Skills

class FarmerThiever : Script() {
    val pickpocketZone = ZoneBorders(3074,3245,3086,3255)


    override fun tick() {
        if(!pickpocketZone.insideBorder(bot))
            scriptAPI.walkTo(pickpocketZone.randomLoc)
        else{
            val farmer = scriptAPI.getNode(2234,false)
            farmer?.interaction?.handle(bot,farmer.interaction[2]) ?: scriptAPI.walkTo(pickpocketZone.randomLoc)

        }
    }

    override fun newInstance(): Script {
        val script = FarmerThiever()
        script.bot = SkillingBotAssembler().produce(SkillingBotAssembler.Wealth.POOR,bot.startLocation)
        return script
    }

    init {
        skills[Skills.THIEVING] = 80
    }
}