package core.game.node.entity.combat.handlers

import core.game.container.Container
import core.game.container.impl.EquipmentContainer
import core.game.node.entity.Entity
import core.game.node.entity.combat.BattleState
import core.game.node.entity.combat.CombatStyle
import core.game.node.entity.combat.CombatSwingHandler
import core.game.node.entity.combat.InteractionType
import core.game.node.entity.combat.equipment.ArmourSet
import core.game.node.entity.combat.equipment.Weapon
import core.game.node.entity.combat.equipment.WeaponInterface
import core.game.node.entity.npc.NPC
import core.game.node.entity.player.Player
import core.game.node.entity.state.EntityState
import core.game.world.map.path.Pathfinder
import core.tools.RandomFunction
import plugin.skill.Skills
import plugin.quest.tutorials.tutorialisland.TutorialSession
import plugin.quest.tutorials.tutorialisland.TutorialStage
import kotlin.math.floor

/**
 * Handles a melee combat swing.
 * @author Emperor
 * @author Ceikry, Kotlin conversion + cleanup
 */
open class MeleeSwingHandler
/**
 * Constructs a new `MeleeSwingHandler` {@Code Object}.
 */
    : CombatSwingHandler(CombatStyle.MELEE) {
    override fun canSwing(entity: Entity, victim: Entity): InteractionType? {
        //Credits wolfenzi, https://www.rune-server.ee/runescape-development/rs2-server/snippets/608720-arios-hybridding-improve.html
        var distance = if (usingHalberd(entity)) 2 else 1
        var type = InteractionType.STILL_INTERACT
        var goodRange = canMelee(entity, victim, distance)
        if (!goodRange && victim.properties.combatPulse.getVictim() !== entity && victim.walkingQueue.isMoving && entity.size() == 1) {
            type = InteractionType.MOVE_INTERACT
            distance += if (entity.walkingQueue.isRunningBoth) 2 else 1
            goodRange = canMelee(entity, victim, distance)
        }
        if (!isProjectileClipped(entity, victim, true)) {
            return InteractionType.NO_INTERACT
        }
        val isRunning = entity.walkingQueue.runDir != -1
        val enemyRunning = victim.walkingQueue.runDir != -1
        // THX 4 fix tom <333.
        if (super.canSwing(entity, victim) != InteractionType.NO_INTERACT) {
            val maxDistance = if (isRunning) if (enemyRunning) 3 else 4 else 2
            if (entity.walkingQueue.isMoving
                    && entity.location.getDistance(victim.location) <= maxDistance) {
                return type
            } else if (goodRange) {
                if (type == InteractionType.STILL_INTERACT) entity.walkingQueue.reset()
                return type
            }
        }
        return InteractionType.NO_INTERACT
    }

    override fun swing(entity: Entity?, victim: Entity?, state: BattleState?): Int {
        var hit = 0
        state!!.style = CombatStyle.MELEE
        if (entity is Player) {
            state.weapon = Weapon(entity.equipment[3])
        }
        if (entity!!.properties.armourSet === ArmourSet.VERAC && RandomFunction.random(100) < 21) {
            state.armourEffect = ArmourSet.VERAC
        }
        if (state.armourEffect === ArmourSet.VERAC || isAccurateImpact(entity, victim, CombatStyle.MELEE)) {
            val max = calculateHit(entity, victim, 1.0)
            state.maximumHit = max
            hit = RandomFunction.random(max)
        }
        state.estimatedHit = hit
        return 1
    }

    override fun visualize(entity: Entity, victim: Entity?, state: BattleState?) {
        entity.animate(entity.properties.attackAnimation)
    }

    override fun impact(entity: Entity?, victim: Entity?, state: BattleState?) {
        var targets = state!!.targets
        if (targets == null) {
            targets = arrayOf<BattleState?>(state)
        }
        for (s in targets) {
            var hit = s!!.estimatedHit
            if (hit > -1) {
                victim!!.impactHandler.handleImpact(entity, hit, CombatStyle.MELEE, s)
            }
            hit = s.secondaryHit
            if (hit > -1) {
                victim!!.impactHandler.handleImpact(entity, hit, CombatStyle.MELEE, s)
            }
        }
    }

    override fun visualizeImpact(entity: Entity?, victim: Entity?, state: BattleState?) {
        if (entity is Player) {
            if (TutorialSession.getExtension(entity as Player?).stage == 51) {
                TutorialStage.load(entity as Player?, 52, false)
            }
            if (TutorialSession.getExtension(entity as Player?).stage == 54) {
                TutorialStage.load(entity as Player?, 55, false)
            }
        }
        victim!!.animate(victim.properties.defenceAnimation)
    }

    override fun adjustBattleState(entity: Entity, victim: Entity, state: BattleState) {
        if (entity is Player) {
            if (state.estimatedHit > 0) {
                val name = entity.equipment.getNew(3).name
                var damage = -1
                if (name.contains("(p++") || name.contains("(s)") || name.contains("(kp)")) {
                    damage = 68
                } else if (name.contains("(p+)")) {
                    damage = 58
                } else if (name.contains("(p)")) {
                    damage = 48
                }
                if (damage > -1 && RandomFunction.random(10) < 4) {
                    victim.stateManager.register(EntityState.POISONED, false, damage, entity)
                }
            }
        }
        super.adjustBattleState(entity, victim, state)
    }

    override fun calculateAccuracy(entity: Entity?): Int {
        val baseLevel = entity!!.skills.getStaticLevel(Skills.ATTACK)
        var weaponRequirement = baseLevel
        if (entity is Player) {
            val weapon = entity.equipment[3]
            weaponRequirement = weapon?.definition?.getRequirement(Skills.ATTACK) ?: 1
        }
        var weaponBonus = 0.0
        if (baseLevel > weaponRequirement) {
            weaponBonus = (baseLevel - weaponRequirement) * .3
        }
        val style = entity.properties.attackStyle
        val level = entity.skills.getLevel(Skills.ATTACK)
        var prayer = 1.0
        if (entity is Player) {
            prayer += entity.prayer.getSkillBonus(Skills.ATTACK)
        }
        var additional = 1.0 // Black mask/slayer helmet/salve/...
        if (entity.properties.combatPulse.getVictim() != null) {
            if (!entity.properties.combatPulse.getVictim()!!.isPlayer && entity is Player) {
                if (checkUndead(entity.getProperties().combatPulse.getVictim()!!.name) && entity.asPlayer().equipment[EquipmentContainer.SLOT_AMULET] != null) {
                    if (entity.asPlayer().equipment[EquipmentContainer.SLOT_AMULET].id == 10588) {
                        additional += 0.20
                    } else if (entity.isPlayer() && entity.asPlayer().equipment[EquipmentContainer.SLOT_AMULET].id == 4081) {
                        additional += 0.15
                    }
                }
            }
        }
        var styleBonus = 0
        if (style.style == WeaponInterface.STYLE_ACCURATE) {
            styleBonus = 3
        } else if (style.style == WeaponInterface.STYLE_CONTROLLED) {
            styleBonus = 1
        }
        val effective = floor(level * prayer * additional + styleBonus + weaponBonus)
        val bonus = entity.properties.bonuses[style.bonusType]
        return floor((effective + 8) * (bonus + 64) / 10 * 1.10).toInt()
    }

    override fun calculateHit(entity: Entity?, victim: Entity?, modifier: Double): Int {
        val level = entity!!.skills.getLevel(Skills.STRENGTH)
        val bonus = entity.properties.bonuses[11]
        var prayer = 1.0
        if (entity is Player) {
            prayer += entity.prayer.getSkillBonus(Skills.STRENGTH)
        }
        var cumulativeStr = floor(level * prayer)
        if (entity.properties.attackStyle.style == WeaponInterface.STYLE_AGGRESSIVE) {
            cumulativeStr += 3.0
        } else if (entity.properties.attackStyle.style == WeaponInterface.STYLE_CONTROLLED) {
            cumulativeStr += 1.0
        }
        cumulativeStr *= getSetMultiplier(entity, Skills.STRENGTH)
        val hit = (16 + cumulativeStr + bonus / 8 + cumulativeStr * bonus * 0.016865) * modifier
        return (hit / 10).toInt() + 1
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
        val equipment = entity.properties.bonuses[attacker!!.properties.attackStyle.bonusType + 5]
        return floor((effective + 8) * (equipment + 64) / 10).toInt()
    }

    override fun getSetMultiplier(e: Entity?, skillId: Int): Double {
        if (e!!.properties.armourSet === ArmourSet.DHAROK && skillId == Skills.STRENGTH) {
//			System.out.println("Fiist number -> " + 1.0 + ((e.getSkills().getMaximumLifepoints() - e.getSkills().getLifepoints()) * 0.01));
            return 1.0 + (e!!.skills.maximumLifepoints - e.skills.lifepoints) * 0.01
        }
        if (e is Player) {
            val c: Container = e.equipment
            val itemId = c.getNew(EquipmentContainer.SLOT_HAT).id
            if ((skillId == Skills.ATTACK || skillId == Skills.STRENGTH) && (itemId in 8901..8921 || itemId == 13263)) {
                val victim = e.getProperties().combatPulse.getVictim()
                if (victim is NPC && victim.task == e.slayer.task) {
                    return 1.15
                }
            }
            if (containsVoidSet(c) && c.getNew(EquipmentContainer.SLOT_HAT).id == 11665) {
                return 1.1
            }
        }
        return 1.0
    }

    override fun addExperience(entity: Entity?, victim: Entity?, state: BattleState?) {
        if (entity is Player && state != null) {
            if (victim is Player && entity.asPlayer().ironmanManager.isIronman) {
                return
            }
            var hit = state.estimatedHit
            if (hit < 0) {
                hit = 0
            }
            if (state.secondaryHit > 0) {
                hit += state.secondaryHit
            }
            var experience = hit * EXPERIENCE_MOD
            val famExp = entity.getAttribute("fam-exp", false) && entity.familiarManager.hasFamiliar()
            if (!famExp) {
                entity.getSkills().addExperience(Skills.HITPOINTS, hit * 1.33, true)
            }
            when (if (famExp) entity.familiarManager.familiar.attackStyle else entity.properties.attackStyle.style) {
                WeaponInterface.STYLE_ACCURATE -> entity.skills.addExperience(Skills.ATTACK, experience, true)
                WeaponInterface.STYLE_AGGRESSIVE -> entity.skills.addExperience(Skills.STRENGTH, experience, true)
                WeaponInterface.STYLE_DEFENSIVE -> entity.skills.addExperience(Skills.DEFENCE, experience, true)
                WeaponInterface.STYLE_CONTROLLED -> {
                    experience /= 3.0
                    entity.skills.addExperience(Skills.ATTACK, experience, true)
                    entity.skills.addExperience(Skills.STRENGTH, experience, true)
                    entity.skills.addExperience(Skills.DEFENCE, experience, true)
                }
                WeaponInterface.STYLE_CAST -> entity.skills.addExperience(Skills.MAGIC, experience, true)
            }
        }
    }

    override fun getArmourSet(e: Entity?): ArmourSet? {
        if (ArmourSet.DHAROK.isUsing(e)) {
            return ArmourSet.DHAROK
        }
        if (ArmourSet.GUTHAN.isUsing(e)) {
            return ArmourSet.GUTHAN
        }
        if (ArmourSet.VERAC.isUsing(e)) {
            return ArmourSet.VERAC
        }
        return if (ArmourSet.TORAG.isUsing(e)) {
            ArmourSet.TORAG
        } else super.getArmourSet(e)
    }

    /**
     * Check to see whether an NPC is classified as undead.
     * @param name
     * @return true if so
     */
    private fun checkUndead(name: String): Boolean {
        return (name == "Zombie" || name.contains("rmoured") || name == "Ankou" || name == "Crawling Hand" || name == "Banshee" || name == "Ghost" || name == "Ghast" || name == "Mummy" || name.contains("Revenant")
                || name == "Skeleton" || name == "Zogre" || name == "Spiritual Mage")
    }

    companion object {
        /**
         * Checks if the entity is using a halberd.
         * @param entity The entity.
         * @return `True` if so.
         */
        private fun usingHalberd(entity: Entity): Boolean {
            if (entity is Player) {
                val weapon = entity.equipment[EquipmentContainer.SLOT_WEAPON]
                if (weapon != null) {
                    return weapon.id in 3190..3204 || weapon.id == 6599
                }
            } else if (entity is NPC) {
                return entity.id == 8612
            }
            return false
        }

        /**
         * Checks if the entity can execute a melee swing from its current location.
         * @param entity The attacking entity.
         * @param victim The victim.
         * @return `True` if so.
         */
        fun canMelee(entity: Entity, victim: Entity?, distance: Int): Boolean {
            val e = entity.location
            if (victim == null) {
                return false
            }
            if (entity.id == 7135 && entity.location.withinDistance(victim.location, 2)) {
                return true
            }
            val x = victim.location.x
            val y = victim.location.y
            val size = entity.size()
            if (distance == 1) {
                for (i in 0 until size) {
                    if (Pathfinder.isStandingIn(e.x - 1, e.y + i, 1, 1, x, y, victim.size(), victim.size())) {
                        return true
                    }
                    if (Pathfinder.isStandingIn(e.x + size, e.y + i, 1, 1, x, y, victim.size(), victim.size())) {
                        return true
                    }
                    if (Pathfinder.isStandingIn(e.x + i, e.y - 1, 1, 1, x, y, victim.size(), victim.size())) {
                        return true
                    }
                    if (Pathfinder.isStandingIn(e.x + i, e.y + size, 1, 1, x, y, victim.size(), victim.size())) {
                        return true
                    }
                }
                if (e == victim.location) {
                    return true
                }
                return victim.getSwingHandler(false).type == CombatStyle.MELEE && e.withinDistance(victim.location, 1) && victim.properties.combatPulse.getVictim() === entity && entity.index < victim.index
            }
            return entity.centerLocation.withinDistance(victim.centerLocation, distance + (size shr 1) + (victim.size() shr 1))
        }
    }
}