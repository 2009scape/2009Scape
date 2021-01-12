package plugin.activity.fishingtrawler

import core.cache.def.impl.ItemDefinition
import core.cache.def.impl.ObjectDefinition
import core.game.interaction.OptionHandler
import core.game.node.Node
import core.game.node.entity.player.Player
import core.game.node.entity.player.info.stats.FISHING_TRAWLER_LEAKS_PATCHED
import core.game.node.entity.player.info.stats.STATS_BASE
import core.game.node.item.GroundItemManager
import core.game.node.item.Item
import core.game.system.task.Pulse
import core.game.world.map.Location
import core.game.world.update.flag.context.Animation
import core.plugin.InitializablePlugin
import core.plugin.Plugin
import core.tools.Items
import plugin.activity.ActivityManager
import plugin.dialogue.DialoguePlugin
import plugin.skill.Skills
import plugin.stringtools.colorize
import kotlin.math.ceil

/**
 * Option handler for fishing trawler
 * @author Ceikry
 */
@InitializablePlugin
class FishingTrawlerOptionHandler : OptionHandler() {
    override fun newInstance(arg: Any?): Plugin<Any> {
        ObjectDefinition.forId(2178).handlers["option:cross"] = this
        ObjectDefinition.forId(2167).handlers["option:fill"] = this
        ObjectDefinition.forId(2164).handlers["option:inspect"] = this
        ObjectDefinition.forId(2165).handlers["option:inspect"] = this
        ObjectDefinition.forId(2166).handlers["option:inspect"] = this
        ObjectDefinition.forId(2160).handlers["option:climb-on"] = this
        ObjectDefinition.forId(2159).handlers["option:climb-on"] = this
        ObjectDefinition.forId(2179).handlers["option:cross"] = this
        ItemDefinition.forId(583).handlers["option:bail-with"] = this
        ItemDefinition.forId(585).handlers["option:empty"] = this
        return this
    }

    override fun handle(player: Player?, node: Node?, option: String?): Boolean {
        player ?: return false
        when(node?.id){
            2178 -> { //Cross plank onto boat
                if(player.skills.getLevel(Skills.FISHING) < 15){
                    player.dialogueInterpreter.sendDialogue("You need to be at least level 15 fishing to play.")
                    return true
                }
                player.properties.teleportLocation = Location.create(2672, 3170, 1)
                (ActivityManager.getActivity("fishing trawler") as FishingTrawlerActivity).addPlayer(player)
            }
            2167 -> { //Fill hole
                val session: FishingTrawlerSession? = player.getAttribute("ft-session",null)
                session ?: return false
                player.lock()
                player.pulseManager.run(object : Pulse(){
                    var counter = 0
                    override fun pulse(): Boolean {
                        when(counter++){
                            0 -> player.animator.animate(Animation(827)).also { player.lock() }
                            1 -> session.repairHole(player,node.asObject()).also { player.incrementAttribute("/save:$STATS_BASE:$FISHING_TRAWLER_LEAKS_PATCHED"); player.unlock() }
                            2 -> return true
                        }
                        return false
                    }
                })
            }
            2164,2165 -> { //inspect net
                player.dialogueInterpreter.open(18237583)
            }
            2166 -> { //inspect reward net
                val session: FishingTrawlerSession? = player.getAttribute("ft-session",null)
                session ?: return false
                if(session.boatSank){
                    return false
                }
                player.dialogueInterpreter.open(18237582)
            }
            2179 -> { //plank from boat to dock
                player.properties.teleportLocation = Location.create(2676, 3170, 0)
                (ActivityManager.getActivity("fishing trawler") as FishingTrawlerActivity).removePlayer(player)
                val session: FishingTrawlerSession? = player.getAttribute("ft-session",null)
                session?.players?.remove(player)
                player.logoutPlugins.clear()
            }
            2159,2160 -> {  //barrel
                player.properties.teleportLocation = Location.create(2666, 3162, 0)
                player.logoutPlugins.clear()
                player.appearance.setDefaultAnimations()
                player.appearance.sync()
            }
            583 -> { //bail-with bucket
                val session: FishingTrawlerSession? = player.getAttribute("ft-session",null)
                session ?: return false
                if(!session.isActive){
                    return false
                }
                if(player.location.y > 0){
                    player.sendMessage("You cant scoop water out up here.")
                    return true
                }
                player.lock()
                player.pulseManager.run(
                        object : Pulse(){
                            var counter = 0
                            override fun pulse(): Boolean {
                                when(counter++){
                                    0 -> player.animator.animate(Animation(4471))
                                    1 -> if(player.inventory.remove(node.asItem())) {
                                            if (session.waterAmount > 0) {
                                                session.waterAmount -= 20
                                                if (session.waterAmount < 0) session.waterAmount = 0
                                                player.inventory.add(Item(Items.BAILING_BUCKET_585))
                                            } else {
                                                player.sendMessage("There's no water to remove.")
                                                player.inventory.add(node.asItem())
                                            }
                                    }
                                    2 -> player.unlock().also { return true }
                                }
                                return false
                            }
                        }
                )
            }
            585 -> { //Empty bailing bucket
                player.lock()
                player.pulseManager.run(
                        object : Pulse(){
                            var counter = 0
                            override fun pulse(): Boolean {
                                when(counter++){
                                    0 -> player.animator.animate(Animation(2450))
                                    1 -> {
                                        if(player.inventory.remove(node.asItem()))
                                            player.inventory.add(Item(Items.BAILING_BUCKET_583))
                                        player.unlock()
                                        return true
                                    }
                                }
                                return false
                            }
                        }
                )
            }
        }
        return true
    }

}

@InitializablePlugin
class NetLootDialogue(player: Player? = null): DialoguePlugin(player){
    var session: FishingTrawlerSession? = null
    var rolls = 0
    override fun newInstance(player: Player?): DialoguePlugin {
        return NetLootDialogue(player)
    }

    override fun open(vararg args: Any?): Boolean {
        session = player.getAttribute("ft-session",null)
        if(session == null) return false
        rolls = ceil(session!!.fishAmount / session!!.players.size.toDouble()).toInt()
        player.dialogueInterpreter.sendOptions("Skip Junk Items?","Yes","No")
        stage = 0
        return true
    }

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        when(buttonId){
            1 -> TrawlerLoot.getLoot(rolls,true).forEach {
                if(!player.bank.add(it)) GroundItemManager.create(it,player)
            }
            2 -> TrawlerLoot.getLoot(rolls,false).forEach {
                if(!player.bank.add(it)) GroundItemManager.create(it,player)
            }
        }
        player.sendMessage(colorize("%RYour reward has been sent to your bank."))
        player.skills.addExperience(Skills.FISHING,(((0.015 * player.skills.getLevel(Skills.FISHING))) * player.skills.getLevel(Skills.FISHING)) * rolls)
        player.removeAttribute("ft-session")
        end()
        return true
    }

    override fun getIds(): IntArray {
        return intArrayOf(18237582)
    }

}

@InitializablePlugin
class NetRepairDialogue(player: Player? = null) : DialoguePlugin(player){
    var session: FishingTrawlerSession? = null
    override fun newInstance(player: Player?): DialoguePlugin {
        return NetRepairDialogue(player)
    }

    override fun open(vararg args: Any?): Boolean {
        session  = player.getAttribute("ft-session",null)
        if(session!!.netRipped){
            player.dialogueInterpreter.sendDialogue("The net is ripped and needs repair.")
            stage = 10
        } else {
            player.dialogueInterpreter.sendDialogue("The net is in perfect condition")
            stage = 0
        }
        return true
    }

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        session ?: return false
        when(stage++){
            0 -> end()
            10 -> player.dialogueInterpreter.sendOptions("Repair the net?","Yes","No")
            11 -> when(buttonId){
                1 -> {
                    end()
                    session!!.repairNet(player)
                }
                else -> {}
            }
            12 -> end()
        }

        return true
    }

    override fun getIds(): IntArray {
        return intArrayOf(18237583)
    }

}