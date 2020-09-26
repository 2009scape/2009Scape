package plugin.consumable.effects

import core.game.node.entity.player.Player
import plugin.consumable.ConsumableEffect

class StackedSkillEffect(private val skill_slot: Int, private val base: Int) : ConsumableEffect() {
    override fun activate(p: Player) {
        p.skills.setLevel(skill_slot, p.skills.getLevel(skill_slot) + base)
    }
}
