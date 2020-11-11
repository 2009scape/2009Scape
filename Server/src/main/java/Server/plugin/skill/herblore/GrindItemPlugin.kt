package plugin.skill.herblore

import core.game.interaction.NodeUsageEvent
import core.game.interaction.UseWithHandler
import core.game.node.item.Item
import core.game.system.SystemLogger
import core.game.world.update.flag.context.Animation
import core.net.packet.PacketRepository
import core.net.packet.context.ChildPositionContext
import core.net.packet.out.RepositionChild
import core.plugin.InitializablePlugin
import core.plugin.Plugin
import plugin.dialogue.SkillDialogueHandler
import plugin.skill.SkillPulse
import plugin.skill.herblore.GrindingItem

/**
 * plugin used to handle the grinding of an item.
 * @author 'Ceikry
 */
@InitializablePlugin
class GrindItemPlugin : UseWithHandler(233) {
    @Throws(Throwable::class)
    override fun newInstance(arg: Any?): Plugin<Any>? {
        for (grind in GrindingItem.values()) {
            for (i in grind.items) {
                addHandler(i.id, ITEM_TYPE, this)
            }
        }
        return this
    }

    override fun handle(event: NodeUsageEvent): Boolean {
        val grind = GrindingItem.forItem(if (event.usedItem.id == 233) event.baseItem else event.usedItem)
        val handler = object : SkillDialogueHandler(event.player,SkillDialogue.ONE_OPTION,grind.product){
            override fun create(amount: Int, index: Int) {
                player.pulseManager.run(object : SkillPulse<Item>(player,event.usedItem){
                    var amt = 0
                    init {
                        amt = amount
                        if(amt > player.inventory.getAmount(node)){
                            amt = player.inventory.getAmount(node)
                        }
                        super.setDelay(2)
                    }
                    override fun checkRequirements(): Boolean {
                        return true
                    }

                    override fun animate() {
                        player.animator.animate(ANIMATION)
                    }

                    override fun reward(): Boolean {
                        if(player.inventory.remove(node)){
                            player.inventory.add(GrindingItem.forItem(node).product)
                        }
                        amt--
                        return amt <= 0
                    }
                })
            }
        }
        handler.open()
        PacketRepository.send(RepositionChild::class.java, ChildPositionContext(event.player, 309, 2, 210, 15))
        return true
    }

    companion object {
        /**
         * Represents the animation to use.
         */
        private val ANIMATION = Animation(364)
    }
}