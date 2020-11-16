package plugin.ai.general.scriptrepository

import core.game.interaction.DestinationFlag
import core.game.interaction.MovementPulse
import core.game.node.item.Item
import core.game.system.SystemLogger
import core.game.system.task.Pulse
import core.game.world.map.Location
import core.game.world.map.path.Pathfinder
import core.game.world.map.zone.Zone
import core.game.world.map.zone.ZoneBorders
import core.tools.ItemNames
import plugin.ai.AIPlayer
import plugin.ai.pvmbots.CombatBotAssembler
import java.net.Proxy

class CowKiller : Script() {
    var state = State.KILLING
    var spawnZone = ZoneBorders(3254,3255,3264,3281)
    var cowZone = ZoneBorders(3242, 3254,3265, 3296)
    val bankZone = ZoneBorders(3208, 3217,3210, 3220)
    init {
        cowZone.addException(ZoneBorders(3242,3254,3252,3277))
        cowZone.addException(ZoneBorders(3242,3277,3242,3296))
        cowZone.addException(ZoneBorders(3253, 3267,3253, 3267))
    }

    override fun tick() {
        when(state){
            State.KILLING -> {
                scriptAPI.attackNpcInRadius(bot,"Cow",10)
                state = State.LOOTING
            }

            State.LOOTING -> {
                bot.pulseManager.run(object: Pulse(4){
                    override fun pulse(): Boolean {
                        state = State.KILLING
                        scriptAPI.takeNearestGroundItem(ItemNames.COWHIDE)
                        state = if(bot.inventory.getAmount(ItemNames.COWHIDE) > 22){
                            State.TO_BANK
                        } else {
                            State.KILLING
                        }
                        return true
                    }
                })
            }

            State.TO_BANK -> {
                if(cowZone.insideBorder(bot))
                    scriptAPI.walkTo(Location.create(3253, 3267, 0))
                else{
                    val closedGate = scriptAPI.getNearestNode(15516,true)
                    if(closedGate != null && closedGate.location.withinDistance(bot.location,2)){
                        closedGate.interaction.handle(bot,closedGate.interaction[0])
                    } else {
                        when (bot.location) {
                            Location.create(3212, 3227, 0) -> {
                                val stairs = scriptAPI.getNearestGameObject(bot.location, 36776)
                                stairs?.interaction?.handle(bot, stairs.interaction[0])
                            }
                            Location.create(3206, 3229, 1) -> {
                                val stairs = scriptAPI.getNearestNode(36777, true)
                                stairs?.interaction?.handle(bot, stairs.interaction[1])
                            }
                            Location.create(3206, 3229, 2) -> {
                                scriptAPI.walkTo(bankZone.randomLoc)
                                state = State.BANKING
                            }
                            else -> scriptAPI.walkTo(Location.create(3212, 3227, 0))
                        }
                    }
                }
            }

            State.BANKING -> {
                if(bankZone.insideBorder(bot)) {
                    val bank = scriptAPI.getNearestNode(36786, true)
                    bot.pulseManager.run(object : MovementPulse(bot, bank, DestinationFlag.OBJECT) {
                        override fun pulse(): Boolean {
                            scriptAPI.bankItem(ItemNames.COWHIDE)
                            if (bot.bank.getAmount(ItemNames.COWHIDE) > 75) {
                                scriptAPI.teleportToGE()
                                state = State.TELE_GE
                            } else {
                                state = State.BACK_TO_COWS
                            }
                            return true
                        }
                    })
                }
            }


            State.BACK_TO_COWS -> {
                if (bankZone.insideBorder(bot))
                    scriptAPI.walkTo(Location.create(3206, 3229, 2))
                else {
                    when (bot.location) {
                        Location.create(3206, 3229, 2) -> {
                            val stairs = scriptAPI.getNearestNode(36778, true)
                            stairs?.interaction?.handle(bot, stairs.interaction[0])
                        }
                        Location.create(3206, 3229, 1) -> {
                            val stairs = scriptAPI.getNearestNode(36777, true)
                            stairs?.interaction?.handle(bot, stairs.interaction[2])
                        }
                        Location.create(3252, 3267, 0) -> {
                            val closedGate = scriptAPI.getNearestNode(15516, true)
                            if (closedGate != null && closedGate.location.withinDistance(bot.location, 2)) {
                                SystemLogger.log("Opening closed gate.")
                                closedGate.interaction.handle(bot, closedGate.interaction[0])
                            } else {
                                scriptAPI.walkTo(cowZone.randomLoc)
                                state = State.KILLING
                            }
                        }
                        else -> scriptAPI.walkTo(Location.create(3252, 3267, 0))
                    }
                }
            }


            State.TELE_GE -> {
                if(bot.location == Location.create(3165, 3482, 0))
                    state = State.SELL_GE
                else
                    scriptAPI.walkTo(Location.create(3165, 3482, 0))
            }

            State.SELL_GE -> {
                state = State.TELE_LUM
                scriptAPI.sellOnGE(ItemNames.COWHIDE,250)
            }

            State.TELE_LUM -> {
                state = State.BACK_TO_COWS
                scriptAPI.teleport(Location.create(3222, 3218, 0))
            }
        }
    }

    enum class State {
        KILLING,
        LOOTING,
        BANKING,
        TO_BANK,
        BACK_TO_COWS,
        SELL_GE,
        TELE_GE,
        TELE_LUM
    }

    override fun newInstance(): Script {
        val script = CowKiller()
        script.bot = CombatBotAssembler().produce(CombatBotAssembler.Type.values().random(),CombatBotAssembler.Tier.LOW,spawnZone.randomLoc)
        script.state = State.KILLING
        return script
    }
}