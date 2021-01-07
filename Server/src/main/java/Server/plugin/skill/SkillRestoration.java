package plugin.skill;

import core.game.node.entity.Entity;
import core.game.node.entity.player.link.prayer.PrayerType;
import core.game.world.GameWorld;

/**
 * Handles the skill restoration data.
 * @author Emperor
 */
public final class SkillRestoration {

	/**
	 * The skill index.
	 */
	private final int skillId;

	/**
	 * The current tick.
	 */
	private int statTick;

	private int hpSummPrayTick;

	/**
	 * Constructs a new {@code SkillRestoration} {@code Object}.
	 * @param skillId The skill id.
	 */
	public SkillRestoration(int skillId) {
		this.skillId = skillId;
		restartHpSummPray(false);
		restartStat(false);
	}

	/**
	 * Restores the skill.
	 * @param entity The entity.
	 */
	public void restore(Entity entity) {
		Skills skills = entity.getSkills();
		int max = skills.getStaticLevel(skillId);
		if(hpSummPrayTick < GameWorld.getTicks()){
			if(skillId == Skills.HITPOINTS || skillId == Skills.SUMMONING || skillId == Skills.PRAYER){
				if(skillId == Skills.HITPOINTS){
					skills.heal(skills.getLifepoints() < max ? 1 : -1);
				} else {
					int current = skills.getLevel(skillId);
					skills.updateLevel(skillId,current < max ? 1 : -1,max);
				}
				restartHpSummPray(entity.asPlayer().getPrayer().getActive().contains(PrayerType.RAPID_HEAL));
			}
		}
		if(statTick < GameWorld.getTicks()) {
			if (skillId != Skills.HITPOINTS && skillId != Skills.SUMMONING && skillId == Skills.PRAYER) {
				int current = skills.getLevel(skillId);
				skills.updateLevel(skillId,current < max ? 1 : -1,max);
				restartStat(entity.asPlayer().getPrayer().getActive().contains(PrayerType.RAPID_RESTORE));
			}
		}
	}

	/**
	 * Gets the tick.
	 * @return The tick.
	 */
	public int getTick() {
		return hpSummPrayTick;
	}

	/**
	 * Sets the tick.
	 * @param tick The tick to set.
	 */
	public void setTick(int tick) {
		this.hpSummPrayTick = tick;
		this.statTick = tick;
	}

	/**
	 * Gets the skillId.
	 * @return The skillId.
	 */
	public int getSkillId() {
		return skillId;
	}

	/**
	 * Restarts the restoration.
	 */
	public void restartHpSummPray(boolean half) {
		int ticks = 100;
		if(half){
			ticks /= 2;
		}
		this.hpSummPrayTick = GameWorld.getTicks() + ticks;
	}

	public void restartStat(boolean half) {
		int ticks = 100;
		if(half){
			ticks /= 2;
		}
		this.statTick = GameWorld.getTicks() + ticks;
	}
}