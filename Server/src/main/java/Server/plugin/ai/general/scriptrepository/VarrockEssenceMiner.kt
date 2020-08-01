package plugin.ai.general.scriptrepository

import core.game.interaction.DestinationFlag
import core.game.interaction.MovementPulse
import core.game.node.item.Item
import core.game.system.SystemLogger
import core.game.world.map.Location
import core.game.world.map.zone.ZoneBorders
import core.tools.ItemNames
import plugin.ai.skillingbot.SkillingBotAssembler
import plugin.skill.Skills

class VarrockEssenceMiner : Script() {

    var state = State.TO_ESSENCE
    val auburyZone = ZoneBorders(3252, 3398, 3254, 3402)
    val bankZone = ZoneBorders(3251, 3420, 3254, 3422)
    override fun tick() {
        if (!bot.questRepository.isComplete("Rune Mysteries")) {
            bot.questRepository.getQuest("Rune Mysteries").finish(bot)
        }

        when (state) {
            State.TO_ESSENCE -> {
                if (!auburyZone.insideBorder(bot))
                    scriptAPI.walkTo(auburyZone.randomLoc)
                else {
                    val aubury = scriptAPI.getNode("Aubury")
                    aubury?.interaction?.handle(bot, aubury.interaction[3])
                    state = State.MINING
                }
                /*val bank = scriptAPI.getNearestNode("Bank booth",true)
                if(bank != null && bank.location?.withinDistance(bot.location,2) == true){
                    Pathfinder.find(bot, Location.create(3259, 3405, 0)).walk(bot)
                } else {
                    when(bot.location){
                        Location.create(3165, 3487, 0) -> scriptAPI.teleport(Location.create(3254, 3421, 0))
                        Location.create(3259, 3405, 0) -> Pathfinder.find(bot,Location.create(3253, 3400, 0)).walk(bot)
                        Location.create(3253, 3400, 0) -> {
                            val aubury = scriptAPI.getNearestNode("Aubury")
                            aubury?.interaction?.handle(bot,aubury.interaction[3])
                        }
                        Location.create(2922, 4820, 0) -> {
                            state = State.MINING
                        }
                    }
                }*/
            }

            State.MINING -> {
                val essence = scriptAPI.getNode(2491, `object`=true)
                essence?.interaction?.handle(bot, essence.interaction[0])
                if (bot.inventory.getAmount(ItemNames.PURE_ESSENCE_7936) > 25)
                    state = State.TO_BANK
            }

            State.TO_BANK -> {
                val portal = scriptAPI.getNode("Portal", `object` = true)
                if (portal != null && portal.location.withinDistance(bot.location, 20))
                    portal.interaction.handle(bot, portal.interaction[0])
                else {
                    if (!bankZone.insideBorder(bot)) {
                        scriptAPI.walkTo(bankZone.randomLoc)
                    } else {
                        state = State.BANKING
                    }
                }
            }

            State.BANKING -> {
                val bank = scriptAPI.getNode("bank booth", `object` = true)
                if (bank != null) {
                    bot.pulseManager.run(object : MovementPulse(bot, bank, DestinationFlag.OBJECT) {
                        override fun pulse(): Boolean {
                            bot.faceLocation(bank.location)
                            scriptAPI.bankItem(ItemNames.PURE_ESSENCE_7936)
                            if (bot.bank.getAmount(ItemNames.PURE_ESSENCE_7936) > 500) {
                                SystemLogger.log("Should tele")
                                state = State.TELE_GE
                                return true
                            }
                            state = State.TO_ESSENCE
                            return true
                        }
                    })
                }
            }

            State.TELE_GE -> {
                if (bot.location != Location.create(3165, 3482, 0))
                    scriptAPI.walkTo(Location.create(3165, 3482, 0))
                else
                    state = State.SELL_GE
            }

            State.SELL_GE -> {
                scriptAPI.sellOnGE(ItemNames.PURE_ESSENCE_7936, 50)
                state = State.TO_ESSENCE
            }

        }

    }

    enum class State {
        TO_ESSENCE,
        TO_BANK,
        MINING,
        BANKING,
        TELE_GE,
        SELL_GE
    }

    init {
        inventory.add(Item(ItemNames.MITHRIL_PICKAXE))
        skills[Skills.MINING] = 45
    }

    override fun newInstance(): Script {
        val script = VarrockEssenceMiner()
        script.bot = SkillingBotAssembler().produce(SkillingBotAssembler.Wealth.POOR, bot.startLocation)
        return script
    }
}