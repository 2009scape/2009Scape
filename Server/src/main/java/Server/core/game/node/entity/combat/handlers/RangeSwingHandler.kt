package core.game.node.entity.combat.handlers

import core.game.container.Container
import core.game.container.impl.EquipmentContainer
import core.game.content.global.SkillcapePerks
import core.game.node.entity.Entity
import core.game.node.entity.combat.BattleState
import core.game.node.entity.combat.CombatStyle
import core.game.node.entity.combat.CombatSwingHandler
import core.game.node.entity.combat.InteractionType
import core.game.node.entity.combat.equipment.*
import core.game.node.entity.combat.equipment.Weapon.WeaponType
import core.game.node.entity.impl.Projectile
import core.game.node.entity.npc.NPC
import core.game.node.entity.player.Player
import core.game.node.entity.state.EntityState
import core.game.node.item.GroundItem
import core.game.node.item.GroundItemManager
import core.game.node.item.Item
import core.game.system.task.Pulse
import core.game.world.GameWorld
import core.game.world.map.Location
import core.game.world.map.RegionManager
import core.game.world.update.flag.context.Graphics
import core.tools.RandomFunction
import plugin.skill.Skills
import plugin.quest.tutorials.tutorialisland.TutorialSession
import plugin.quest.tutorials.tutorialisland.TutorialStage
import java.util.*
import kotlin.math.ceil
import kotlin.math.floor

/**
 * Handles the range combat swings.
 * @author Emperor
 * @author Ceikry, conversion to Kotlin + cleanup
 */
open class RangeSwingHandler
/**
 * Constructs a new `RangeSwingHandler` {@Code Object}.
 */
    : CombatSwingHandler(CombatStyle.RANGE) {
    override fun canSwing(entity: Entity, victim: Entity): InteractionType? {
        if (!isProjectileClipped(entity, victim, false)) {
            return InteractionType.NO_INTERACT
        }
        if (entity.getAttribute("tut-island", false)) {
            if (TutorialSession.getExtension(entity as Player).stage == 70) {
                return InteractionType.NO_INTERACT
            }
        }
        var distance = 7
        if (entity is Player && (entity.getExtension<Any>(WeaponInterface::class.java) as WeaponInterface).weaponInterface.interfaceId == 91) {
            distance -= 2
        }
        if (entity.properties.attackStyle.style == WeaponInterface.STYLE_LONG_RANGE) {
            distance += 2
        }
        var goodRange = victim.centerLocation.withinDistance(entity.centerLocation, getCombatDistance(entity, victim, distance))
        var type = InteractionType.STILL_INTERACT
        if (victim.walkingQueue.isMoving && !goodRange) {
            goodRange = victim.centerLocation.withinDistance(entity.centerLocation, getCombatDistance(entity, victim, ++distance))
            type = InteractionType.MOVE_INTERACT
        }
        if (goodRange && super.canSwing(entity, victim) != InteractionType.NO_INTERACT) {
            if (type == InteractionType.STILL_INTERACT) {
                entity.walkingQueue.reset()
            }
            return type
        }
        return InteractionType.NO_INTERACT
    }

    override fun swing(entity: Entity?, victim: Entity?, state: BattleState?): Int {
        configureRangeData(entity, state)
        if (state!!.weapon == null || !hasAmmo(entity, state)) {
            entity!!.properties.combatPulse.stop()
            return -1
        }
        var hit = 0
        if (isAccurateImpact(entity, victim, CombatStyle.RANGE)) {
            val max = calculateHit(entity, victim, 1.0)
            state.maximumHit = max
            hit = RandomFunction.random(max)
        }
        state.estimatedHit = hit
        if (state.weapon.type == WeaponType.DOUBLE_SHOT) {
            if (isAccurateImpact(entity, victim, CombatStyle.RANGE)) {
                hit = RandomFunction.random(calculateHit(entity, victim, 1.0))
            }
            state.secondaryHit = hit
        }
        if (entity == null || victim!!.location == null) {
            return -1
        }
        useAmmo(entity, state, victim.location)
        return 1 + ceil(entity.location.getDistance(victim.location) * 0.3).toInt()
    }

    /**
     * Configures the range data.
     * @param entity The entity.
     * @param state The battle state.
     */
    fun configureRangeData(entity: Entity?, state: BattleState?) {
        state!!.style = CombatStyle.RANGE
        val w: Weapon?
        if (entity is Player) {
            val rw = RangeWeapon.get(entity.equipment.getNew(3).id)
            if (rw == null) {
                System.err.println("Unhandled range weapon used - [item id=" + entity.equipment.getNew(3).id + "].")
                return
            }
            w = Weapon(entity.equipment[3], rw.ammunitionSlot, entity.equipment.getNew(rw.ammunitionSlot))
            w.type = rw.weaponType
            state.rangeWeapon = rw
            state.ammunition = Ammunition.get(w.ammunition.id)
        } else {
            w = Weapon(null)
        }
        state.weapon = w
    }

    override fun adjustBattleState(entity: Entity, victim: Entity, state: BattleState) {
        if (state.ammunition != null && entity is Player) {
            val damage = state.ammunition.poisonDamage
            if (state.estimatedHit > 0 && damage > 8 && RandomFunction.random(10) < 4) {
                victim.stateManager.register(EntityState.POISONED, false, damage, entity)
            }
        }
        super.adjustBattleState(entity, victim, state)
    }

    override fun addExperience(entity: Entity?, victim: Entity?, state: BattleState?) {
        if (entity is Player) {
            if (victim is Player && entity.asPlayer().ironmanManager.isIronman) {
                return
            }
            var hit = state!!.estimatedHit
            if (hit < 0) {
                hit = 0
            }
            if (state.secondaryHit > 0) {
                hit += state.secondaryHit
            }
            val p = entity.asPlayer()
            val famExp = entity.getAttribute("fam-exp", false) && p.familiarManager.hasFamiliar()
            if (famExp) {
                val fam = p.familiarManager.familiar
                var skill = Skills.RANGE
                when (fam.attackStyle) {
                    WeaponInterface.STYLE_DEFENSIVE -> skill = Skills.DEFENCE
                    WeaponInterface.STYLE_ACCURATE -> skill = Skills.ATTACK
                    WeaponInterface.STYLE_AGGRESSIVE -> skill = Skills.STRENGTH
                    WeaponInterface.STYLE_RANGE_ACCURATE -> skill = Skills.RANGE
                    WeaponInterface.STYLE_CONTROLLED -> {
                        var experience = hit * EXPERIENCE_MOD
                        experience /= 3.0
                        entity.getSkills().addExperience(Skills.ATTACK, experience, true)
                        entity.getSkills().addExperience(Skills.STRENGTH, experience, true)
                        entity.getSkills().addExperience(Skills.DEFENCE, experience, true)
                        return
                    }
                    WeaponInterface.STYLE_CAST -> skill = Skills.MAGIC
                }
                entity.getSkills().addExperience(skill, hit * EXPERIENCE_MOD, true)
                return
            }
            entity.getSkills().addExperience(Skills.HITPOINTS, hit * 1.33, true)
            if (entity.getProperties().attackStyle.style == WeaponInterface.STYLE_LONG_RANGE) {
                entity.getSkills().addExperience(Skills.RANGE, hit * (EXPERIENCE_MOD / 2), true)
                entity.getSkills().addExperience(Skills.DEFENCE, hit * (EXPERIENCE_MOD / 2), true)
            } else {
                entity.getSkills().addExperience(Skills.RANGE, hit * EXPERIENCE_MOD, true)
            }
        }
    }

    override fun visualize(entity: Entity, victim: Entity?, state: BattleState?) {
        var start: Graphics? = null
        if (state!!.ammunition != null) {
            start = state.ammunition.startGraphics
            state.ammunition.projectile.copy(entity, victim, 5.0).send()
            if (state.weapon.type == WeaponType.DOUBLE_SHOT && state.ammunition.darkBowGraphics != null) {
                start = state.ammunition.darkBowGraphics
                val speed = (55 + entity.location.getDistance(victim!!.location) * 10).toInt()
                Projectile.create(entity, victim, state.ammunition.projectile.projectileId, 40, 36, 41, speed, 25).send()
            }
        } else if (entity is NPC) {
            if (entity.definition.combatGraphics[0] != null) {
                start = entity.definition.combatGraphics[0]
            }
            val g = entity.definition.combatGraphics[1]
            if (g != null) {
                Projectile.ranged(entity, victim, g.id, g.height, 36, 41, 5).send()
            }
        }
        val weapon: RangeWeapon? = RangeWeapon.get(state.weapon.id)
        val anim = entity.properties.attackAnimation.id
        weapon?.let {
            if ((anim == 422 || anim == 423)) {
                entity.visualize(it.animation, start)
                return
            }
        }
        entity.visualize(entity.properties.attackAnimation, start)
    }

    override fun impact(entity: Entity?, victim: Entity?, state: BattleState?) {
        if (state!!.ammunition != null && state.ammunition.effect != null && state.ammunition.effect.canFire(state)) {
            state.ammunition.effect.impact(state)
        }
        val hit = state.estimatedHit
        if (entity is Player) {
            if (TutorialSession.getExtension(entity as Player?).stage == 51) {
                TutorialStage.load(entity as Player?, 52, false)
            }
            if (TutorialSession.getExtension(entity as Player?).stage == 52) {
                TutorialStage.load(entity as Player?, 53, false)
            }
        }
        victim!!.impactHandler.handleImpact(entity, hit, CombatStyle.RANGE, state)
        if (state.secondaryHit > -1) {
            val hitt = state.secondaryHit
            GameWorld.Pulser.submit(object : Pulse(1, victim) {
                override fun pulse(): Boolean {
                    victim.impactHandler.handleImpact(entity, hitt, CombatStyle.RANGE, state)
                    return true
                }
            })
        }
    }

    override fun visualizeImpact(entity: Entity?, victim: Entity?, state: BattleState?) {
        victim!!.animate(victim.properties.defenceAnimation)
    }

    override fun calculateAccuracy(entity: Entity?): Int {
        val baseLevel = entity!!.skills.getStaticLevel(Skills.RANGE)
        var weaponRequirement = baseLevel
        if (entity is Player) {
            val weapon = entity.equipment[3]
            weaponRequirement = weapon?.definition?.getRequirement(Skills.RANGE) ?: 1
        }
        var weaponBonus = 0.0
        if (baseLevel > weaponRequirement) {
            weaponBonus = (baseLevel - weaponRequirement) * .3
        }
        val level = entity.skills.getLevel(Skills.RANGE)
        var prayer = 1.0
        if (entity is Player) {
            prayer += entity.prayer.getSkillBonus(Skills.RANGE)
        }
        val additional = 1.0 // Slayer helmet/salve/...
        var styleBonus = 0
        if (entity.properties.attackStyle.style == WeaponInterface.STYLE_RANGE_ACCURATE) {
            styleBonus = 3
        }
        val effective = floor(level * prayer * additional + styleBonus + weaponBonus)
        val bonus = entity.properties.bonuses[WeaponInterface.BONUS_RANGE]
        return floor((effective + 8) * (bonus + 64) / 10).toInt()
    }

    override fun calculateHit(entity: Entity?, victim: Entity?, modifier: Double): Int {
        val level = entity!!.skills.getLevel(Skills.RANGE)
        val bonus = entity.properties.bonuses[14]
        var prayer = 1.0
        if (entity is Player) {
            prayer += entity.prayer.getSkillBonus(Skills.RANGE)
        }
        var cumulativeStr = floor(level * prayer)
        if (entity.properties.attackStyle.style == WeaponInterface.STYLE_RANGE_ACCURATE) {
            cumulativeStr += 3.0
        } else if (entity.properties.attackStyle.style == WeaponInterface.STYLE_LONG_RANGE) {
            cumulativeStr += 1.0
        }
        cumulativeStr *= getSetMultiplier(entity, Skills.RANGE)
        return ((14 + cumulativeStr + bonus / 8 + cumulativeStr * bonus * 0.016865) * modifier).toInt() / 10 + 1
    }

    override fun calculateDefence(entity: Entity?, attacker: Entity?): Int {
        val style = entity!!.properties.attackStyle
        var styleBonus = 0
        if (style.style == WeaponInterface.STYLE_DEFENSIVE || style.style == WeaponInterface.STYLE_LONG_RANGE) {
            styleBonus = 3
        } else if (style.style == WeaponInterface.STYLE_CONTROLLED) {
            styleBonus = 1
        }
        val level = entity.skills.getLevel(Skills.DEFENCE)
        var prayer = 1.0
        if (entity is Player) {
            prayer += entity.prayer.getSkillBonus(Skills.DEFENCE)
        }
        val effective = floor(level * prayer + styleBonus)
        val equipment = entity.properties.bonuses[WeaponInterface.BONUS_RANGE + 5]
        return floor((effective + 8) * (equipment + 64) / 10).toInt()
    }

    override fun getSetMultiplier(e: Entity?, skillId: Int): Double {
        if (e is Player) {
            val c: Container = e.equipment
            if (containsVoidSet(c) && c.getNew(EquipmentContainer.SLOT_HAT).id == 11664) {
                return 1.1
            }
        }
        return 1.0
    }

    override fun getArmourSet(e: Entity?): ArmourSet? {
        return if (ArmourSet.KARIL.isUsing(e)) {
            ArmourSet.KARIL
        } else super.getArmourSet(e)
    }

    companion object {
        /**
         * Checks if the entity has the ammunition needed to proceed.
         * @param e The entity.
         * @param state The battle state.
         * @return `True` if so.
         */
        fun hasAmmo(e: Entity?, state: BattleState?): Boolean {
            if (e !is Player) {
                return true
            }
            val type = state!!.weapon.type
            val amount = if (type == WeaponType.DOUBLE_SHOT) 2 else 1
            if (type == WeaponType.DEGRADING) {
                return true
            }
            val item = e.equipment[state.weapon.ammunitionSlot]
            if (item != null && item.amount >= amount) {
                if (!state.rangeWeapon.ammunition.contains(item.id)) {
                    e.packetDispatch.sendMessage("You can't use this type of ammunition with this bow.")
                    return false
                }
                return true
            }
            if (type == WeaponType.DOUBLE_SHOT) {
                state.weapon.type = WeaponType.DEFAULT
                return hasAmmo(e, state)
            }
            e.packetDispatch.sendMessage("You do not have enough ammo left.")
            return false
        }

        /**
         * Uses the ammunition for the range weapon.
         * @param e The entity.
         * @param state The battle state.
         * @param location The drop location.
         */
        fun useAmmo(e: Entity, state: BattleState, location: Location?) {
            var dropLocation = location
            if (e !is Player) {
                return
            }
            val type = state.weapon.type
            val amount = if (type == WeaponType.DOUBLE_SHOT) 2 else 1
            if (type == WeaponType.DEGRADING) {
                degrade(e, state, amount)
                return
            }
            val ammo = state.weapon.ammunition
            if (state.weapon.ammunitionSlot == -1 || ammo == null) {
                return
            }
            val dropRate = getDropRate(e)
            if (dropRate == -1.0) {
                return
            }
            if (dropLocation == null) {
                return
            }
            val flag = RegionManager.getClippingFlag(dropLocation)
            if (flag and 0x200000 != 0) { //Water
                dropLocation = null
            }
            e.equipment.replace(Item(ammo.id, ammo.amount - amount, ammo.charge), state.weapon.ammunitionSlot)
            if (dropLocation != null && state.rangeWeapon.isDropAmmo) {
                val rate = 5 * (1.0 + e.skills.getLevel(Skills.RANGE) * 0.01) * dropRate
                if (RandomFunction.randomize(rate.toInt()) != 0) {
                    val drop = GroundItemManager.increase(Item(ammo.id, amount), dropLocation, e)
                    if (drop != null) {
                        if (e.getAttribute<Any?>("duel:ammo", null) != null) {
                            (e.getAttribute<Any>("duel:ammo") as ArrayList<GroundItem?>).add(drop)
                        }
                    }
                }
            }
            if (e.equipment[state.rangeWeapon.ammunitionSlot] == null) {
                e.packetDispatch.sendMessage("You have no ammo left in your quiver!")
            }
        }

        /**
         * Checks if ammunition should be saved.
         * @param e The entity.
         * @return `True` if so.
         */
        private fun getDropRate(e: Entity?): Double {
            if (e is Player) {
                val cape = e.equipment[EquipmentContainer.SLOT_CAPE]
                val weapon = e.equipment[EquipmentContainer.SLOT_WEAPON]
                if (cape != null && (cape.id == 10498 || cape.id == 10499 || SkillcapePerks.hasSkillcapePerk(e, SkillcapePerks.RANGING)) && weapon != null && weapon.id != 10034 && weapon.id != 10033) {
                    val rate = if (cape.id == 10498 || SkillcapePerks.hasSkillcapePerk(e, SkillcapePerks.RANGING)) 70 else 80
                    if (RandomFunction.random(100) < rate) {
                        val torso = e.equipment[EquipmentContainer.SLOT_CHEST]
                        val modelId = torso?.definition?.maleWornModelId1 ?: -1
                        if (modelId == 301 || modelId == 306 || modelId == 3379) {
                            e.packetDispatch.sendMessage("Your armour interferes with Ava's device.")
                            return 1.0
                        }
                        return (-1).toDouble()
                    }
                    return 0.33
                }
            }
            return 1.0
        }

        /**
         * Degrades the player's range weapon used.
         * @param p The player.
         * @param state The battle state.
         * @param amount The amount of shots to degrade.
         */
        private fun degrade(p: Player, state: BattleState, amount: Int) {
            if (state.weapon.item.id == 4212) { // New crystal bow.
                p.packetDispatch.sendMessage("Your crystal bow has degraded!")
                p.equipment.replace(Item(4214, 1, 996), 3)
                return
            }
            val charge = state.weapon.item.charge - amount * 4
            state.weapon.item.charge = charge
            if (charge < 1) {
                val id = state.weapon.id + 1
                if (id < 4224) {
                    p.packetDispatch.sendMessage("Your crystal bow has degraded!")
                    p.equipment.replace(Item(id, 1, 999), 3)
                } else {
                    var replace: Item? = null
                    if (!p.inventory.add(Item(4207))) {
                        replace = Item(4207)
                    }
                    p.equipment.replace(replace, 3)
                    p.packetDispatch.sendMessage("Your crystal bow has degraded to a small crystal seed.")
                }
            }
        }
    }
}