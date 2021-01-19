package plugin.activity.allfiredup

import core.cache.def.impl.ObjectDefinition
import core.game.interaction.OptionHandler
import core.game.node.Node
import core.game.node.entity.npc.NPC
import core.game.node.entity.player.Player
import core.game.node.item.Item
import core.game.system.SystemLogger
import core.game.system.task.Pulse
import core.game.world.GameWorld
import core.game.world.update.flag.context.Animation
import core.plugin.InitializablePlugin
import core.plugin.Plugin
import core.tools.Items
import plugin.dialogue.FacialExpression
import plugin.skill.Skills

private val VALID_LOGS = arrayOf(Items.LOGS_1511,Items.OAK_LOGS_1521,Items.WILLOW_LOGS_1519,Items.MAPLE_LOGS_1517,Items.YEW_LOGS_1515,Items.MAGIC_LOGS_1513)
private val FILL_ANIM = Animation(9136)
private val LIGHT_ANIM = Animation(7307)

/**
 * Handles interactions for beacons
 * @author Ceikry
 */
@InitializablePlugin
class AFUBeaconHandler : OptionHandler(){
    override fun newInstance(arg: Any?): Plugin<Any> {
        for(i in 38448..38461)
        ObjectDefinition.forId(i).childrenIds.forEach {
            ObjectDefinition.forId(it).handlers["option:add-logs"] = this
            ObjectDefinition.forId(it).handlers["option:light"] = this
        }
        return this
    }

    override fun handle(player: Player?, node: Node?, option: String?): Boolean {
        player ?: return false
        node ?: return false
        val beacon = AFUBeacon.forLocation(node.location)
        val questComplete = player.questRepository.isComplete("All Fired Up")
        val questStage = player.questRepository.getStage("All Fired Up")

        if ((beacon != AFUBeacon.RIVER_SALVE && beacon != AFUBeacon.RAG_AND_BONE && !questComplete)
                || (beacon == AFUBeacon.RIVER_SALVE && questStage < 20 && !questComplete)
                || (beacon == AFUBeacon.RAG_AND_BONE && questStage < 50 && !questComplete)) {
            player.dialogueInterpreter.sendDialogues(player, FacialExpression.THINKING, "I probably shouldn't mess with this.")
            return true
        }
        player.debug(beacon.getState(player).name)

        when (beacon.getState(player)) {
            BeaconState.EMPTY -> fillBeacon(player, beacon, questComplete)

            BeaconState.DYING -> restoreBeacon(player, beacon, questComplete)

            BeaconState.FILLED -> lightBeacon(player, beacon, questComplete)

            BeaconState.LIT, BeaconState.WARNING -> {
                player.debug("INVALID BEACON STATE")
            }
        }
        return true
    }

    fun fillBeacon(player: Player, beacon: AFUBeacon, questComplete: Boolean){

        when(beacon){
            AFUBeacon.MONASTERY -> {
                if(player.skills.getLevel(Skills.PRAYER) < 31){
                    player.dialogueInterpreter.sendDialogues(NPC(beacon.keeper).getShownNPC(player),FacialExpression.ANGRY,"You must join the monastery to light this beacon!")
                    return
                }
            }

            AFUBeacon.GWD -> {
                if(!AFURepairClimbHandler.isRepaired(player,beacon)){
                    player.dialogueInterpreter.sendDialogue("You must repair the windbreak before you","can light this beacon.")
                    return
                }
            }

            AFUBeacon.GOBLIN_VILLAGE -> {
                if(!player.questRepository.isComplete("Goblin Diplomacy")){
                    player.dialogueInterpreter.sendDialogues(NPC(beacon.keeper).getShownNPC(player),FacialExpression.THINKING,"We no trust you outsider. You no light our beacon.","(Complete Goblin Diplomacy to use this beacon.)")
                    return
                }
            }

        }

        if(player.skills.getLevel(Skills.FIREMAKING) < beacon.fmLevel){
            player.dialogueInterpreter.sendDialogue("You need ${beacon.fmLevel} Firemaking to light this beacon.")
            return
        }

        val logs = getLogs(player,20)
        if (logs.id != 0 && player.inventory.remove(logs)) {
            player.lock()

            var session: AFUSession? = null
            if(questComplete){
                session = player.getAttribute("afu-session", null)
                if(session == null) {
                    session = AFUSession(player)
                    session.init()
                }
            }

            GameWorld.Pulser.submit(object : Pulse() {
                var counter = 0
                override fun pulse(): Boolean {
                    when (counter++) {
                        0 -> player.animator.animate(FILL_ANIM)
                        1 -> {
                            beacon.fillWithLogs(player)
                            if(questComplete){
                                session?.setLogs(beacon.ordinal,logs)
                            }
                            player.varpManager.flagSave(beacon.varpId);
                        }
                        2 -> player.unlock().also {player.animator.animate(Animation.RESET); return true }
                    }
                    return false
                }
            })
        } else {
            player.dialogueInterpreter.sendDialogue("You need some logs to do this.")
        }
    }

    fun lightBeacon(player: Player,beacon: AFUBeacon, questComplete: Boolean){
        var session: AFUSession? = null
        if(questComplete){
            session = player.getAttribute("afu-session",null)
            if(session == null) return
        }

        if(player.inventory.contains(Items.TINDERBOX_590,1)){
            player.lock()
            GameWorld.Pulser.submit(object: Pulse(){
                var counter = 0
                override fun pulse(): Boolean {
                    when(counter++){
                        0 -> player.animator.animate(LIGHT_ANIM)
                        1 -> {
                            beacon.light(player)
                            if(questComplete){
                                session?.startTimer(beacon.ordinal)
                                if(session?.getLitBeacons() == 6 && !player.hasFireRing()){
                                    player.sendMessage("Congratulations on lighting 6 beacons at once! King Roald has something for you.")
                                    player.setAttribute("/save:afu-mini:ring",true)
                                }
                                if(session?.getLitBeacons() == 10 && !player.hasFlameGloves()){
                                    player.sendMessage("Congratulations on lighting 10 beacons at once! King Roald has something for you.")
                                    player.setAttribute("/save:afu-mini:gloves",true)
                                }
                                if(session?.getLitBeacons() == 14 && !player.hasInfernoAdze()){
                                    player.sendMessage("Congratulations on lighting all 14 beacons! King Roald has something special for you.")
                                    player.setAttribute("/save:afu-mini:adze",true)
                                }
                                var experience = beacon.experience
                                experience += session?.getBonusExperience() ?: 0.0
                                player.skills.addExperience(Skills.FIREMAKING,experience)
                            } else {
                                player.questRepository.getQuest("All Fired Up").setStage(player, player.questRepository.getStage("All Fired Up") + 10)
                            }
                        }
                        2 -> player.unlock().also { return true }
                    }
                    return false
                }
            })
        } else {
            player.dialogueInterpreter.sendDialogue("You need a tinderbox to light this.")
        }
    }

    fun restoreBeacon(player: Player,beacon: AFUBeacon, questComplete: Boolean){
        var session: AFUSession? = null
        if(questComplete){
            session = player.getAttribute("afu-session",null)
            if(session == null) return
        }

        val logs = getLogs(player, 5)
        if (logs.id != 0 && player.inventory.remove(logs)) {
            player.lock()
            GameWorld.Pulser.submit(object: Pulse(){
                var counter = 0
                override fun pulse(): Boolean {
                    when(counter++){
                        0 -> player.animator.animate(FILL_ANIM)
                        1 -> beacon.light(player).also {
                            if(questComplete){
                                session?.refreshTimer(beacon,logs.id)
                            } else {
                                player.questRepository.getQuest("All Fired Up").setStage(player, 80)
                            }
                        }
                        2 -> player.unlock().also { return true }
                    }
                    return false
                }
            })
        } else {
            player.dialogueInterpreter.sendDialogue("You need some logs to do this.")
        }
    }

    fun getLogs(player: Player, amount: Int): Item{
        var logId = 0
        for (log in VALID_LOGS) if (player.inventory.getAmount(log) >= amount) {logId = log; break}
        return Item(logId,amount)
    }

    fun Player.hasFireRing(): Boolean{
        return inventory.containsItem(Item(Items.RING_OF_FIRE_13659)) || bank.containsItem(Item(Items.RING_OF_FIRE_13659)) || equipment.containsItem(Item(Items.RING_OF_FIRE_13659))
    }

    fun Player.hasFlameGloves(): Boolean{
        return inventory.containsItem(Item(Items.FLAME_GLOVES_13660)) || bank.containsItem(Item(Items.FLAME_GLOVES_13660)) || equipment.containsItem(Item(Items.FLAME_GLOVES_13660))
    }

    fun Player.hasInfernoAdze(): Boolean{
        return inventory.containsItem(Item(Items.INFERNO_ADZE_13661)) || bank.containsItem(Item(Items.INFERNO_ADZE_13661)) || equipment.containsItem(Item(Items.INFERNO_ADZE_13661))
    }

}