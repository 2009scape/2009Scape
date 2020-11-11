package plugin.jobs

import GatheringJobs
import SlayingJob
import core.cache.def.impl.NPCDefinition
import core.game.interaction.OptionHandler
import core.game.node.Node
import core.game.node.entity.npc.NPC
import core.game.node.entity.player.Player
import core.game.node.entity.player.link.diary.DiaryType
import core.game.node.item.Item
import core.plugin.InitializablePlugin
import core.plugin.Plugin
import core.plugin.PluginManager
import core.tools.ItemNames
import plugin.dialogue.DialoguePlugin

/**
 * Handles the work-for actions for the NPCs
 * @author Ceikry
 */
@InitializablePlugin
class WorkForOptionHandler : OptionHandler() {
    val possibleWeaponLooks = arrayListOf(
            ItemNames.BRONZE_SCIMITAR,
            ItemNames.STEEL_SCIMITAR,
            ItemNames.RUNE_SCIMITAR,
            ItemNames.BRONZE_MACE,
            ItemNames.MITHRIL_WARHAMMER
    )
    val gatheringMap = hashMapOf<Int,List<GatheringJobs>>(
            0 to listOf(GatheringJobs.AIR_RUNE,GatheringJobs.COWHIDES,GatheringJobs.WATER_RUNE),
            922 to listOf(GatheringJobs.ASHES,GatheringJobs.AIR_RUNE,GatheringJobs.WATER_RUNE),
            3807 to listOf(GatheringJobs.COWHIDES),
            4899 to listOf(GatheringJobs.CAKE,GatheringJobs.ANCHOVY_PIZZA,GatheringJobs.COOKED_SALMON,GatheringJobs.COOKED_TROUT,GatheringJobs.MEAT_PIE,GatheringJobs.MEAT_PIZZA,GatheringJobs.PLAIN_PIZZA),
            4901 to listOf(GatheringJobs.RAW_SALMON,GatheringJobs.RAW_SHRIMP,GatheringJobs.RAW_TROUT),
            4902 to listOf(GatheringJobs.COPPER_ORE,GatheringJobs.TIN_ORE,GatheringJobs.COAL,GatheringJobs.IRON_ORE,GatheringJobs.SILVER_ORE,GatheringJobs.GOLD_ORE),
            4903 to listOf(GatheringJobs.SILVER_ORE),
            4904 to listOf(GatheringJobs.BRONZE_BAR,GatheringJobs.IRON_BAR,GatheringJobs.STEEL_BAR),
            4906 to listOf(GatheringJobs.LOG,GatheringJobs.OAK,GatheringJobs.WILLOW)
    )
    val typeMap = hashMapOf(
            0 to 0,
            705 to 1,
            922 to 0,
            1861 to 1,
            3807 to 0,
            4899 to 0,
            4901 to 0,
            4902 to 0,
            4903 to 0,
            4904 to 0,
            4906 to 0,
            4707 to 1
    )
    @Throws(Throwable::class)
    override fun newInstance(arg: Any?): Plugin<Any>? {
        NPCDefinition.forId(4906).handlers["option:work-for"] = this
        NPCDefinition.forId(4707).handlers["option:work-for"] = this
        NPCDefinition.forId(4904).handlers["option:work-for"] = this
        NPCDefinition.forId(4903).handlers["option:work-for"] = this
        NPCDefinition.forId(4902).handlers["option:work-for"] = this
        NPCDefinition.forId(4901).handlers["option:work-for"] = this
        NPCDefinition.forId(4899).handlers["option:work-for"] = this
        NPCDefinition.forId(3807).handlers["option:work-for"] = this
        NPCDefinition.forId(1861).handlers["option:work-for"] = this
        NPCDefinition.forId(922).handlers["option:work-for"] = this
        NPCDefinition.forId(705).handlers["option:work-for"] = this
        NPCDefinition.forId(0).handlers["option:work-for"] = this
        PluginManager.definePlugin(CancelJobDialogue())
        return this
    }

    override fun handle(player: Player, node: Node, option: String): Boolean {
        var amount = 0
        var jobId = 0

        if(player.getAttribute("jobs:id",-1) != -1){
            JobManager.rewardPlayer(player)
            return true
        }

        val type = typeMap[node.id]
        jobId = if(type == 0) {
            var job = gatheringMap[node.id]?.random()
            while(!checkRequirement(player,job)){
                job = gatheringMap[node.id]?.random()
            }
            amount = job?.getAmount() ?: 0
            job?.ordinal ?: 0
        } else {
            SlayingJob.values().random().ordinal.also { amount = SlayingJob.values()[it].getAmount() }
        }

        when(type){
            0 -> {
                val job = GatheringJobs.values()[jobId]
                player.dialogueInterpreter.sendItemMessage(job.itemId,"You are assigned to gather $amount ${Item(job.itemId).name.toLowerCase()}")

                // Have the Fishing Tutor send you on an errand
                if (node.id == 4901) player.achievementDiaryManager.finishTask(player, DiaryType.LUMBRIDGE, 0, 14);
            }
            1 -> {
                val job = SlayingJob.values()[jobId]
                player.dialogueInterpreter.sendItemMessage(possibleWeaponLooks.random(),"You are assigned to kill $amount ${NPC(job.ids[0]).name.toLowerCase()}")
            }
        }

        player.setAttribute("/save:jobs:id",jobId)
        player.setAttribute("/save:jobs:amount",amount)
        player.setAttribute("/save:jobs:original_amount",amount)
        player.setAttribute("/save:jobs:type",type)
        return true
    }

    fun checkRequirement(player: Player, jobs: GatheringJobs?): Boolean{
        jobs ?: return true
        val requirement: Pair<Int,Int> = Pair(jobs.lvlReq,jobs.skill)
        if(player.skills.getLevel(requirement.second) < requirement.first){
            return false
        }
        return true
    }

    class CancelJobDialogue(player: Player? = null) : DialoguePlugin(player){
        var amount = 0
        override fun newInstance(player: Player?): DialoguePlugin {
            return CancelJobDialogue(player)
        }

        override fun open(vararg args: Any?): Boolean {
            amount = args[1] as Int
            player ?: return false
            npc("Would you like to cancel your job?","It will cost 500 coins.")
            stage = 0
            return true
        }

        override fun handle(interfaceId: Int, buttonId: Int): Boolean {
            when(stage){
                0 -> options("Yes, please.","No, thanks.").also { stage++ }
                1 -> when(buttonId){
                    1 -> player("Yes, please.").also{ stage = 10 }
                    2 -> player("No, thanks.").also { stage = 20 }
                }

                10 -> npc("Alright then, hand over the money.").also { stage++ }
                11 -> if(player.inventory.contains(995,500)){
                    player("Here you go.")
                    player.inventory.remove(Item(995,500))
                    player.removeAttribute("jobs:id")
                    player.removeAttribute("jobs:amount")
                    player.removeAttribute("jobs:original_amount")
                    player.removeAttribute("jobs:type")
                    stage = 1000
                } else {
                    player("Oh, I don't seem to have the money...")
                    stage++
                }

                12 -> npc("Ah, that sucks! Get to work.","You still need to gather $amount more.").also { stage = 1000 }

                20 -> npc("Alright then, get to work!","You still need to gather $amount more.").also { stage = 1000 }

                1000 -> end()
            }
            return true
        }

        override fun getIds(): IntArray {
            return intArrayOf(9987215) //Random number to avoid colliding with other dialogues
        }

    }
}