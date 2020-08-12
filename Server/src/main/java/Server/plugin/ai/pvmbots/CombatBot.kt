package plugin.ai.pvmbots

import core.game.node.entity.player.link.appearance.Gender
import core.game.node.entity.player.link.prayer.PrayerType
import core.game.node.item.Item
import core.game.world.map.Direction
import core.game.world.map.Location
import core.tools.RandomFunction
import plugin.ai.AIPlayer
import plugin.consumable.Consumable
import plugin.consumable.Consumables
import plugin.consumable.Food
import plugin.consumable.effects.HealingEffect
import plugin.skill.Skills
import java.util.*

class CombatBot(location: Location) : AIPlayer(location) {
    var tick = 0

    override fun updateRandomValues() {
        appearance.gender = if (RandomFunction.random(5) == 1) Gender.FEMALE else Gender.MALE
        setDirection(Direction.values()[Random().nextInt(Direction.values().size)]) //Random facing dir
        skills.updateCombatLevel()
        appearance.sync()
    }
    override fun tick() {
        super.tick()
        this.tick++

        //Despawn
        if (skills.lifepoints == 0) {
            //CombatBotAssembler.produce(CombatBotAssembler.Type.RANGE, CombatBotAssembler.Tier.LOW,this.location)
            deregister(uid)
        }
    }

    fun CheckPrayer(type: Array<PrayerType?>) {
        for (i in type.indices) prayer.toggle(type[i])
    }

    fun eat(foodId: Int) {
        val foodItem = Item(foodId)
        if (skills.getStaticLevel(Skills.HITPOINTS) >= skills.lifepoints * 3 && inventory.containsItem(foodItem)) {
            this.lock(3)
            //this.animate(new Animation(829));
            val food = inventory.getItem(foodItem)
            var consumable: Consumable? = Consumables.getConsumableById(food.id)
            if (consumable == null) {
                consumable = Food(IntArray(food.id), HealingEffect(1))
            }
            consumable.consume(food, this)
            properties.combatPulse.delayNextAttack(3)
        }
        if (!checkVictimIsPlayer()) if (!inventory.contains(foodId, 1)) inventory.add(Item(foodId, 5)) //Add Food to Inventory
    }
}