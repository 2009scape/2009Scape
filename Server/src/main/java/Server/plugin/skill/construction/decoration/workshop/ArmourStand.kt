package plugin.skill.construction.decoration.workshop

import core.game.content.global.RepairItem
import core.game.interaction.NodeUsageEvent
import core.game.interaction.UseWithHandler
import core.game.node.entity.player.Player
import core.game.node.item.Item
import core.plugin.InitializablePlugin
import core.plugin.Plugin
import plugin.dialogue.DialoguePlugin
import plugin.interaction.npc.bob.BobDialogue
import plugin.interaction.npc.bob.BobDialogue.BarrowsEquipment
import plugin.interaction.npc.bob.BobDialogue.BarrowsEquipment.BarrowsFullEquipment
import plugin.skill.Skills
import kotlin.math.ceil

@InitializablePlugin
class ArmourStand : UseWithHandler(494, 468, 496, 470, 498, 472, 500, 502, 474, 504, 476, 506, 478, 6741, 4856, 4857, 4858, 4859, 4860, 4862, 4863, 4864, 4865, 4866, 4868, 4869, 4870, 4871, 4872, 4874, 4875, 4876, 4877, 4878, 4880, 4881, 4882, 4883, 4884, 4886, 4887, 4888, 4889, 4890, 4892, 4893, 4894, 4895, 4896, 4898, 4899, 4900, 4901, 4902, 4904, 4905, 4906, 4907, 4908, 4910, 4911, 4912, 4913, 4914, 4916, 4917, 4918, 4919, 4920, 4922, 4923, 4924, 4925, 4926, 4928, 4929, 4930, 4931, 4932, 4934, 4935, 4936, 4937, 4938, 4940, 4941, 4942, 4943, 4944, 4946, 4947, 4948, 4949, 4950, 4952, 4953, 4954, 4955, 4956, 4958, 4959, 4960, 4961, 4962, 4964, 4965, 4966, 4967, 4968, 4970, 4971, 4972, 4973, 4974, 4976, 4977, 4978, 4979, 4980, 4982, 4983, 4984, 4985, 4986, 4988, 4989, 4990, 4991, 4992, 4994, 4995, 4996, 4997, 4998){
    override fun newInstance(arg: Any?): Plugin<Any> {
        addHandler(13715, OBJECT_TYPE, this)
        return this
    }

    override fun handle(event: NodeUsageEvent?): Boolean {
        event ?: return false
        val player = event.player
        val repairItem = RepairItem.forId(event.used.id)

        var baseCost = 0.0
        var product: Item? = null

        if(repairItem != null){
            baseCost = repairItem.cost * 1.0
            product = repairItem.product
        } else if(BarrowsEquipment.isBarrowsItem(event.used.id)){
            //BEGIN RETARDED SHIT THANKS TO FUCKING SHIT-FOR-BRAINS FUCKING MORON VEXIA
            val type = BarrowsEquipment.formatedName(event.used.id)
            val single = BarrowsEquipment.getSingleName(type)
            val equipment = BarrowsEquipment.getEquipmentType(type)
            val newString = type.toLowerCase().replace(single, "").trim { it <= ' ' }.replace("'s", "")
            val newewString = StringBuilder()
            newewString.append(newString).append(" $equipment")
            val fullequip = BarrowsFullEquipment.forName(newewString.toString())
            baseCost = BarrowsEquipment.getFormatedCost(equipment,event.used.asItem()) * 1.0
            product = fullequip.full
            //END RETARDED SHIT THANKS TO FUCKING SHIT-FOR-BRAINS FUCKING MORON VEXIA
        }

        if((repairItem == null && baseCost == 0.0)){
            player.sendMessage("That item can't be repaired.")
            return true
        }

        val cost: Int = ceil(((100.0 - (player.skills.getLevel(Skills.SMITHING) / 2.0) ) / 100.0) * baseCost).toInt()

        player.dialogueInterpreter.open(58824213,event.used,cost,product)

        return true
    }
    @InitializablePlugin
    class RepairDialogue(player: Player? = null) : DialoguePlugin(player){
        override fun newInstance(player: Player?): DialoguePlugin {
            return RepairDialogue(player)
        }
        var item: Item? = null
        var cost: Int = 0
        var product: Item? = null

        override fun open(vararg args: Any?): Boolean {
            item = args[0] as Item
            cost = args[1] as Int
            product = args[2] as Item
            player.dialogueInterpreter.sendDialogue("Would you like to repair your ${(item as Item).name.toLowerCase()}","for $cost gp?")
            stage = 0
            return true
        }

        override fun handle(interfaceId: Int, buttonId: Int): Boolean {
            item ?: return false
            product ?: return false
            when(stage){
                0 -> options("Yes, please","No, thanks").also{stage++}
                1 -> when(buttonId){
                    1 -> exchangeItems(item as Item,cost,product as Item).also { end() }
                    2 -> end()
                }
            }
            return true
        }

        fun exchangeItems(item: Item, cost: Int, product: Item) {
            val coins = Item(995, cost)
            if (player.inventory.containsItem(coins) && player.inventory.containsItem(item)) {
                player.inventory.remove(item, coins)
                player.inventory.add(product)
                player.sendMessage("You repair your ${product.name.toLowerCase()} for $cost.")
            } else {
                player.sendMessage("You can't afford that.")
            }
        }

        override fun getIds(): IntArray {
            return intArrayOf(58824213)
        }
    }
}