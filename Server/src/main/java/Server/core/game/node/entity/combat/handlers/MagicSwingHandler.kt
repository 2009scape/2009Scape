package core.game.node.entity.combat.handlers

import core.game.container.Container
import core.game.container.impl.EquipmentContainer
import core.game.node.entity.Entity
import core.game.node.entity.combat.BattleState
import core.game.node.entity.combat.CombatStyle
import core.game.node.entity.combat.CombatSwingHandler
import core.game.node.entity.combat.InteractionType
import core.game.node.entity.combat.equipment.ArmourSet
import core.game.node.entity.combat.equipment.SpellType
import core.game.node.entity.combat.equipment.WeaponInterface
import core.game.node.entity.npc.NPC
import core.game.node.entity.player.Player
import core.tools.RandomFunction
import plugin.skill.Skills
import plugin.quest.tutorials.tutorialisland.TutorialSession
import plugin.quest.tutorials.tutorialisland.TutorialStage
import kotlin.math.floor

/**
 * Handles the magic combat swings.
 * @author Emperor
 * @author Ceikry, Kotlin conversion + cleanup
 */
open class MagicSwingHandler
    : CombatSwingHandler(CombatStyle.MAGIC) {

    override fun canSwing(entity: Entity, victim: Entity): InteractionType? {
        if (!isProjectileClipped(entity, victim, false)) {
            return InteractionType.NO_INTERACT
        }
        var distance = 10
        var type = InteractionType.STILL_INTERACT
        var goodRange = victim.centerLocation.withinDistance(entity.centerLocation, getCombatDistance(entity, victim, distance))
        if (victim.walkingQueue.isMoving && !goodRange) {
            goodRange = victim.centerLocation.withinDistance(entity.centerLocation, getCombatDistance(entity, victim, ++distance))
            type = InteractionType.MOVE_INTERACT
        }
        if (goodRange && isAttackable(entity, victim) != InteractionType.NO_INTERACT) {
            if (type == InteractionType.STILL_INTERACT) {
                entity.walkingQueue.reset()
            }
            return type
        }
        return InteractionType.NO_INTERACT
    }

    override fun swing(entity: Entity?, victim: Entity?, state: BattleState?): Int {
        var spell = entity!!.properties.spell
        if (spell == null) {
            if (entity.properties.autocastSpell.also { spell = it } == null) {
                return -1
            }
        }
        state!!.style = CombatStyle.MAGIC
        if (!spell!!.meetsRequirements(entity, true, true)) {
            entity.properties.spell = null
            val inter = entity.getExtension<WeaponInterface>(WeaponInterface::class.java)
            if (inter != null) {
                inter.selectAutoSpell(-1, true)
                entity.properties.combatPulse.updateStyle()
            }
            return -1
        }
        val max = calculateHit(entity, victim, spell!!.getMaximumImpact(entity, victim, state).toDouble())
        state.targets = spell!!.getTargets(entity, victim)
        state.spell = spell
        for (s in state.targets) {
            var hit = -1
            s.spell = spell
            if (isAccurateImpact(entity, s.victim, CombatStyle.MAGIC, 1.3, 1.0)) {
                s.maximumHit = max
                hit = RandomFunction.random(max)
            }
            s.style = CombatStyle.MAGIC
            s.estimatedHit = hit
        }
        if (spell === entity.properties.spell) {
            entity.properties.spell = null
            if (entity.properties.autocastSpell == null) {
                entity.properties.combatPulse.stop()
            }
        }
        var ticks = 2 + floor(entity.location.getDistance(victim!!.location) * 0.5).toInt()
        if (spell!!.type === SpellType.BLITZ) {
            ticks++
        }
        return ticks
    }

    override fun adjustBattleState(entity: Entity, victim: Entity, state: BattleState) {
        if (state.targets == null) {
            super.adjustBattleState(entity, victim, state)
            if (state.spell != null) {
                state.spell.fireEffect(entity, victim, state)
            }
            return
        }
        for (s in state.targets) {
            if (s != null) {
                super.adjustBattleState(entity, s.victim, s)
                if (s.spell != null) {
                    s.spell.fireEffect(entity, s.victim, s)
                }
            }
        }
    }

    override fun visualize(entity: Entity, victim: Entity?, state: BattleState?) {
        state!!.spell.visualize(entity, victim)
    }

    override fun impact(entity: Entity?, victim: Entity?, state: BattleState?) {
        if (state!!.spell != null && entity is Player) {
            entity.getSkills().addExperience(Skills.MAGIC, state.spell.experience)
        }
        if (state.targets == null) {
            if (state.estimatedHit > -1) {
                victim!!.impactHandler.handleImpact(entity, state.estimatedHit, CombatStyle.MAGIC, state)
            }
            return
        }
        for (s in state.targets) {
            if (s == null || s.estimatedHit < 0) {
                continue
            }
            val hit = s.estimatedHit
            s.victim.impactHandler.handleImpact(entity, hit, CombatStyle.MAGIC, s)
        }
    }

    override fun visualizeImpact(entity: Entity?, victim: Entity?, state: BattleState?) {
        if (entity is Player) {
            if (TutorialSession.getExtension(entity as Player?).stage == 51) {
                TutorialStage.load(entity as Player?, 52, false)
            }
            if (TutorialSession.getExtension(entity as Player?).stage == 52) {
                TutorialStage.load(entity as Player?, 53, false)
            }
            if (TutorialSession.getExtension(entity as Player?).stage == 54) {
                TutorialStage.load(entity as Player?, 55, false)
            }
        }
        if (state!!.targets == null) {
            if (state.spell != null) {
                state.spell.visualizeImpact(entity, victim, state)
            }
            return
        }
        for (s in state.targets) {
            if (s != null) {
                state.spell.visualizeImpact(entity, s.victim, s)
            }
        }
    }

    override fun calculateAccuracy(entity: Entity?): Int {
        val baseLevel = entity!!.skills.getStaticLevel(Skills.MAGIC)
        var spellRequirement = baseLevel
        if (entity is Player) {
            if (entity.getProperties().spell != null) {
                spellRequirement = entity.getProperties().spell.level
            } else if (entity.getProperties().autocastSpell != null) {
                spellRequirement = entity.getProperties().autocastSpell.level
            }
        }
        var spellBonus = 0.0
        if (baseLevel > spellRequirement) {
            spellBonus = (baseLevel - spellRequirement) * .3
        }
        val level = entity.skills.getLevel(Skills.MAGIC, true)
        var prayer = 1.0
        if (entity is Player) {
            prayer += entity.prayer.getSkillBonus(Skills.MAGIC)
        }
        val additional = 1.0 // Slayer helmet/salve/...
        val effective = floor(level * prayer * additional + spellBonus)
        val bonus = entity.properties.bonuses[WeaponInterface.BONUS_MAGIC]
        return floor((effective + 8) * (bonus + 64) / 10).toInt()
    }

    override fun calculateHit(entity: Entity?, victim: Entity?, modifier: Double): Int {
        var baseDamage = modifier
        if (baseDamage < 2.0 || entity is NPC) {
            if (baseDamage > 2.0) {
                baseDamage = 1.0
            }
            val level = entity!!.skills.getLevel(Skills.MAGIC, true)
            val bonus = entity.properties.bonuses[if (entity is Player) 14 else 13]
            val cumulativeStr = level.toDouble()
            return 1 + ((14 + cumulativeStr + bonus / 8 + cumulativeStr * bonus * 0.016865) * baseDamage).toInt() / 10
        }
        var levelMod = 1.0
        val entityMod = entity!!.getLevelMod(entity, victim)
        if (entityMod != 0.0) {
            levelMod += entityMod
        }
        return (baseDamage * levelMod).toInt() + 1
    }

    override fun calculateDefence(entity: Entity?, attacker: Entity?): Int {
        val level = entity!!.skills.getLevel(Skills.DEFENCE, true)
        var prayer = 1.0
        if (entity is Player) {
            prayer += entity.prayer.getSkillBonus(Skills.MAGIC)
        }
        val effective = floor(level * prayer * 0.3) + entity.skills.getLevel(Skills.MAGIC, true) * 0.7
        val equipment = entity.properties.bonuses[WeaponInterface.BONUS_MAGIC + 5]
        return floor((effective + 8) * (equipment + 64) / 10).toInt()
    }

    override fun getSetMultiplier(e: Entity?, skillId: Int): Double {
        if (e is Player) {
            val c: Container = e.equipment
            if (containsVoidSet(c) && c.getNew(EquipmentContainer.SLOT_HAT).id == 11663) {
                return 1.3
            }
        }
        return 1.0
    }

    override fun addExperience(entity: Entity?, victim: Entity?, state: BattleState?) {
        if (entity is Player) {
            if (victim is Player && entity.asPlayer().ironmanManager.isIronman) {
                return
            }
            var hit = 0
            if (state!!.targets != null) {
                for (s in state.targets) {
                    if (s != null && s.estimatedHit > 0) {
                        hit += s.estimatedHit
                    }
                }
            } else if (state.estimatedHit > 0) {
                hit += state.estimatedHit
            }
            if (state.spell != null) {
                state.spell.addExperience(entity, hit)
            } else {
                val famExp = entity.getAttribute("fam-exp", false) && entity.familiarManager.hasFamiliar()
                if (!famExp) {
                    entity.getSkills().addExperience(Skills.HITPOINTS, hit * 1.33, true)
                } else {
                    val fam = entity.familiarManager.familiar
                    var skill = Skills.MAGIC
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
                    }
                    entity.getSkills().addExperience(skill, hit * EXPERIENCE_MOD, true)
                    return
                }
                entity.getSkills().addExperience(Skills.MAGIC, hit * EXPERIENCE_MOD, true)
            }
        }
    }

    override fun getArmourSet(e: Entity?): ArmourSet? {
        return if (ArmourSet.AHRIM.isUsing(e)) {
            ArmourSet.AHRIM
        } else super.getArmourSet(e)
    }
}