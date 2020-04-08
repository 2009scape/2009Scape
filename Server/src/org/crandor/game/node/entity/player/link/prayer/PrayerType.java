package org.crandor.game.node.entity.player.link.prayer;

import org.crandor.game.content.skill.SkillBonus;
import org.crandor.game.content.skill.Skills;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.entity.player.link.audio.Audio;
import org.crandor.tools.StringUtils;

import java.util.List;

/**
 * Represents a prayer type.
 * @author Vexia
 * @author Emperor
 * @author jamix77
 */
public enum PrayerType {
	THICK_SKIN(1, 12, 83, 5, PrayerCategory.BABY_BLUE, 10000, new SkillBonus(Skills.DEFENCE, 0.05)),
	BURST_OF_STRENGTH(4, 12, 84, 7, PrayerCategory.GREEN, 2689, new SkillBonus(Skills.STRENGTH, 0.05)), 
	CLARITY_OF_THOUGHT(7, 12, 85, 9, PrayerCategory.PINK, 2664, new SkillBonus(Skills.ATTACK, 0.05)), 
	SHARP_EYE(8, 12, 862, 11, PrayerCategory.LIME_GREEN, 2685, new SkillBonus(Skills.RANGE, 0.05)), 
	MYSTIC_WILL(9, 12, 863, 13, PrayerCategory.LIME_GREEN, 2670, new SkillBonus(Skills.MAGIC, 0.05)), 
	ROCK_SKIN(10, 6, 86, 15, PrayerCategory.BABY_BLUE, 2684, new SkillBonus(Skills.DEFENCE, 0.1)), 
	SUPERHUMAN_STRENGTH(13, 6, 87, 17, PrayerCategory.GREEN, 2689, new SkillBonus(Skills.STRENGTH, 0.1)), 
	IMPROVED_REFLEXES(16, 6, 88, 19, PrayerCategory.PINK, 2662, new SkillBonus(Skills.ATTACK, 0.1)),
	RAPID_RESTORE(19, 26, 89, 21, PrayerCategory.PURPLE, 2679), 
	RAPID_HEAL(22, 18, 90, 23, PrayerCategory.PURPLE, 2678), 
	PROTECT_ITEMS(25, 18, 91, 25, PrayerCategory.DARK_GREEN, 1982), 
	HAWK_EYE(26, 6, 864, 27, PrayerCategory.LIME_GREEN, 2666, new SkillBonus(Skills.RANGE, 0.1)), 
	MYSTIC_LORE(27, 6, 865, 29, PrayerCategory.LIME_GREEN, 2668, new SkillBonus(Skills.MAGIC, 0.1)), 
	STEEL_SKIN(28, 3, 92, 31, PrayerCategory.BABY_BLUE, 2687, new SkillBonus(Skills.DEFENCE, 0.15)), 
	ULTIMATE_STRENGTH(31, 3, 93, 33, PrayerCategory.GREEN, 2691, new SkillBonus(Skills.STRENGTH, 0.15)), 
	INCREDIBLE_REFLEXES(34, 3, 94, 35, PrayerCategory.PINK, 2667, new SkillBonus(Skills.ATTACK, 0.15)), 
	PROTECT_FROM_SUMMONING(35, 2, 1168, 53, PrayerCategory.DARK_BROWN, PrayerCategory.MAGENTA, new Audio(-1)),
	PROTECT_FROM_MAGIC(37, 3, 95, 37, PrayerCategory.LIGHT_BROWN, 2675), 
	PROTECT_FROM_MISSILES(40, 3, 96, 39, PrayerCategory.LIGHT_BROWN, 2677), 
	PROTECT_FROM_MELEE(43, 4, 97, 41, PrayerCategory.LIGHT_BROWN, 2676), 
	EAGLE_EYE(44, 3, 866, 43, PrayerCategory.LIME_GREEN, 2666, new SkillBonus(Skills.RANGE, 0.15)), 
	MYSTIC_MIGHT(45, 3, 867, 45, PrayerCategory.LIME_GREEN, 2669, new SkillBonus(Skills.MAGIC, 0.15)), 
	RETRIBUTION(46, 12, 98, 47, PrayerCategory.LIGHT_BROWN, PrayerCategory.MAGENTA, new Audio(10000)), 
	REDEMPTION(49, 6, 99, 49, PrayerCategory.LIGHT_BROWN, PrayerCategory.MAGENTA, new Audio(2678)), 
	SMITE(52, 2, 100, 51, PrayerCategory.LIGHT_BROWN, PrayerCategory.MAGENTA, new Audio(2685)), 
	CHIVALRY(60, 2, 1052, 55, PrayerCategory.PINK, 1000,65, new SkillBonus(Skills.DEFENCE, 0.2), new SkillBonus(Skills.STRENGTH, 0.18), new SkillBonus(Skills.ATTACK, 0.15)), 
	PIETY(70, 2, 1053, 57, PrayerCategory.PINK, 1000, 70 , new SkillBonus(Skills.DEFENCE, 0.25), new SkillBonus(Skills.STRENGTH, 0.23), new SkillBonus(Skills.ATTACK, 0.2));

	/**
	 * Represents the a cache of objects related to prayers in order to decide
	 * what head icon to display.
	 */
	private final static Object[][] ICON_CACHE = new Object[][] { { REDEMPTION, 5 }, { RETRIBUTION, 3 }, { SMITE, 4 }, { PROTECT_FROM_MAGIC, 2, 10 }, { PROTECT_FROM_MELEE, 0, 8 }, { PROTECT_FROM_MISSILES, 1, 9 }, { PROTECT_FROM_SUMMONING, 7, PROTECT_FROM_MELEE, 8, PROTECT_FROM_MISSILES, 9, PROTECT_FROM_MAGIC, 10 } };

	/**
	 * The level required.
	 */
	private final int level;

	/**
	 * The drain rate.
	 */
	private final int drain;

	/**
	 * The configuration id of the prayer.
	 */
	private final int config;

	/**
	 * The button id.
	 */
	private final int button;

	/**
	 * The restriction.
	 */
	private final PrayerCategory restriction;

	/**
	 * Represents the second restriction.
	 */
	private final PrayerCategory secondRestriction;

	/**
	 * The sound.
	 */
	private Audio sound;
	
	/**
	 * The required level of defence to use this prayer.
	 */
	private int defenceReq;

	/**
	 * The skill bonuses for this type.
	 */
	private final SkillBonus[] bonuses;

	/**
	 * Constructs a new {@code PrayerType} {@code Object}.
	 * @param level the level.
	 * @param drain the drain, represents the seconds until a drain.
	 * @param config the config value to represent on and off.
	 * @param button the button value to turn the prayer off and on.
	 * @param bonuses the skill bonuses for this type.
	 */
	PrayerType(int level, int drain, int config, int button, PrayerCategory restriction, int soundId, SkillBonus... bonuses) {
		this(level, drain, config, button, restriction, null, new Audio(soundId), bonuses);
	}
	
	/**
	 * 
	 * Constructs a new @{Code PrayerType} object.
	 * @param level
	 * @param drain
	 * @param config
	 * @param button
	 * @param restriction
	 * @param soundId
	 * @param defenceReq
	 * @param bonuses
	 */
	PrayerType(int level, int drain, int config, int button, PrayerCategory restriction, int soundId, int defenceReq, SkillBonus... bonuses) {
		this(level, drain, config, button, restriction, null, new Audio(soundId), defenceReq,bonuses);
	}

	/**
	 * Constructs a new {@code PrayerType} {@code Object}.
	 * @param level the level.
	 * @param drain the drain, represents the seconds until a drain.
	 * @param config the config value to represent on and off.
	 * @param button the button value to turn the prayer off and on.
	 * @param bonuses the skill bonuses for this type.
	 */
	PrayerType(int level, int drain, int config, int button, PrayerCategory restriction, PrayerCategory secondRestriction, Audio sound, SkillBonus... bonuses) {
		this(level,drain,config,button,restriction,secondRestriction,sound,1,bonuses);
	}
	
	/**
	 * 
	 * Constructs a new @{Code PrayerType} object.
	 * @param level
	 * @param drain
	 * @param config
	 * @param button
	 * @param restriction
	 * @param secondRestriction
	 * @param sound
	 * @param defenceReq
	 * @param bonuses
	 */
	PrayerType(int level, int drain, int config, int button, PrayerCategory restriction, PrayerCategory secondRestriction, Audio sound, int defenceReq, SkillBonus... bonuses) {
		this.level = level;
		this.drain = drain;
		this.config = config;
		this.button = button;
		this.restriction = restriction;
		this.secondRestriction = secondRestriction;
		this.sound = sound;
		this.bonuses = bonuses;
		this.defenceReq = defenceReq;
	}

	/**
	 * Gets the level.
	 * @return The level.
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * Gets the drain.
	 * @return The drain.
	 */
	public int getDrain() {
		return drain;
	}

	/**
	 * Gets the config.
	 * @return The config.
	 */
	public int getConfig() {
		return config;
	}

	/**
	 * Gets the button.
	 * @return The button.
	 */
	public int getButton() {
		return button;
	}

	/**
	 * Gets the restriction.
	 * @return The restriction.
	 */
	public PrayerCategory getRestriction() {
		return restriction;
	}

	/**
	 * Gets the bonuses.
	 * @return The bonuses.
	 */
	public SkillBonus[] getBonuses() {
		return bonuses;
	}

	/**
	 * Method used to check if the player has the required level to toggle this
	 * type.
	 * @param player the player.
	 * @return <code>True</code> if it is permitted.
	 */
	public boolean permitted(final Player player) {
		if (player.getSkills().getStaticLevel(Skills.PRAYER) < getLevel() || player.getSkills().getStaticLevel(Skills.DEFENCE) < defenceReq) {
			player.getAudioManager().send(2673);
			player.getDialogueInterpreter().sendDialogue("You need a <col=08088A>Prayer level of " + getLevel() + (player.getSkills().getStaticLevel(Skills.DEFENCE) < defenceReq ? (" and a Defence level of " + defenceReq) : "") + " to use " + StringUtils.formatDisplayName(name().toLowerCase().replace("_", " ")) + ".");
			return false;
		}
		return true;
	}

	/**
	 * Method used to check if we need to toggle a prayer on or off.
	 * @param player the player.
	 * @return <code>True</code> if toggled.
	 */
	public boolean toggle(final Player player, final boolean on) {
		player.getConfigManager().set(getConfig(), on ? 1 : 0);
		if (on) {
			flag(player, this);
			player.getPrayer().getActive().add(this);
			player.getPrayer().getTask().init(player);
			iconify(player, getIcon(player, this));
			player.getAudioManager().send(getSound());
		} else {
			player.getPrayer().getActive().remove(this);
			findNextIcon(player);
			if (player.getPrayer().getActive().isEmpty()) {
				player.getPrayer().getTask().stop(player);
				return true;
			}
		}
		return true;
	}

	/**
	 * Method used to flag others prayers that cannot be toggled together.
	 * @param player the player.
	 */
	public void flag(final Player player, final PrayerType type) {
		final List<PrayerType> active = player.getPrayer().getActive();
		final PrayerType[] remove = new PrayerType[active.size() + 10];
		int index = 0;
		for (int i = 0; i < active.size(); i++) {
			if (active.get(i).getRestriction() == type.getRestriction() || active.get(i).getSecondRestriction() != null && type.getSecondRestriction() != null && active.get(i).getSecondRestriction() == type.getSecondRestriction()) {
				remove[index++] = active.get(i);
				continue;
			}
			for (SkillBonus b : active.get(i).getBonuses()) {
				for (SkillBonus bb : type.getBonuses()) {
					if ((bb.getSkillId() == b.getSkillId()) || (b.getSkillId() == Skills.STRENGTH || b.getSkillId() == Skills.ATTACK) && (bb.getSkillId() == Skills.MAGIC || bb.getSkillId() == Skills.RANGE) || (b.getSkillId() == Skills.RANGE || b.getSkillId() == Skills.MAGIC) && (bb.getSkillId() == Skills.ATTACK || bb.getSkillId() == Skills.STRENGTH) || (b.getSkillId() == Skills.DEFENCE && bb.getSkillId() == Skills.DEFENCE)) {
						remove[index++] = active.get(i);
					}
				}
			}
		}
		for (int i = 0; i < index; i++) {
			remove[i].toggle(player, false);
		}
	}

	/**
	 * Method used to iconify the player.
	 * @param active the active list.
	 */
	public boolean iconify(final Player player, final int icon) {
		if (icon == -1) {
			return false;
		}
		player.getAppearance().setHeadIcon(icon);
		player.getAppearance().sync();
		return false;
	}

	/**
	 * Method used to find the next icon in place.
	 * @param player the player.
	 */
	public void findNextIcon(final Player player) {
		if (!hasIcon(player)) {
			player.getAppearance().setHeadIcon(-1);
			player.getAppearance().sync();
		}
		if ((this == PROTECT_FROM_MELEE || this == PROTECT_FROM_MISSILES || this == PROTECT_FROM_MAGIC) && player.getPrayer().get(PROTECT_FROM_SUMMONING)) {
			iconify(player, 7);
		} else if (this == PROTECT_FROM_SUMMONING) {
			for (PrayerType t : player.getPrayer().getActive()) {
				iconify(player, getIcon(player, t));
			}
			if (player.getAppearance().getHeadIcon() == 7) {
				player.getAppearance().setHeadIcon(-1);
				player.getAppearance().sync();
			}
		}
	}

	/**
	 * Method used to get the icon value.
	 * @param active the list prayers active.
	 * @return the icon.
	 */
	public int getIcon(final Player player, final PrayerType type) {
		List<PrayerType> active = player.getPrayer().getActive();
		for (int i = 0; i < ICON_CACHE.length; i++) {
			if (ICON_CACHE[i].length == 2 && type == ((PrayerType) ICON_CACHE[i][0])) {
				return (int) ICON_CACHE[i][1];
			} else if (ICON_CACHE[i].length == 3 && type == ((PrayerType) ICON_CACHE[i][0])) {
				if (active.contains(PROTECT_FROM_SUMMONING)) {
					return (int) ICON_CACHE[i][2];
				}
				return (int) ICON_CACHE[i][1];
			} else if (ICON_CACHE[i].length == 8 && type == ((PrayerType) ICON_CACHE[i][0])) {
				for (int k = 2; k < ICON_CACHE[i].length; k++) {
					if (active.contains(ICON_CACHE[i][k])) {
						return (int) ICON_CACHE[i][k + 1];
					}
				}
				return (int) ICON_CACHE[i][1];
			}
		}
		return -1;
	}

	/**
	 * Method used to check if theres an icon present.
	 * @param player the player.
	 * @return <code>True</code> if theres an icon present.
	 */
	public boolean hasIcon(final Player player) {
		int count = 0;
		for (PrayerType type : player.getPrayer().getActive()) {
			if (getIcon(player, type) != -1) {
				count++;
			}
		}
		return count != 0;
	}

	/**
	 * Method used to return the type by the button.
	 * @param button the button.
	 * @return the type.
	 */
	public static PrayerType get(int button) {
		for (PrayerType type : PrayerType.values()) {
			if (type.getButton() == button) {
				return type;
			}
		}
		return null;
	}

	/**
	 * Method used to get the melee types.
	 * @return the types.
	 */
	public static PrayerType[] getMeleeTypes() {
		return getByBonus(Skills.ATTACK, Skills.STRENGTH);
	}

	/**
	 * Method used to get the rage types.
	 * @return the types.
	 */
	public static PrayerType[] getRangeTypes() {
		return getByBonus(Skills.RANGE);
	}

	/**
	 * Method used to get the magic types.
	 * @return the types.
	 */
	public static PrayerType[] getMagicTypes() {
		return getByBonus(Skills.MAGIC);
	}

	/**
	 * Method used to get the prayer types by the bonuses.
	 * @param ids the ids.
	 * @return the type.
	 */
	public static PrayerType[] getByBonus(int... ids) {
		PrayerType[] types = new PrayerType[values().length];
		int count = 0;
		for (PrayerType type : values()) {
			for (SkillBonus b : type.getBonuses()) {
				for (int i : ids) {
					if (i == b.getSkillId()) {
						types[count] = type;
						count++;
					}
				}
			}
		}
		return types;
	}

	/**
	 * Gets the secondRestriction.
	 * @return The secondRestriction.
	 */
	public PrayerCategory getSecondRestriction() {
		return secondRestriction;
	}

	/**
	 * Gets the sound.
	 * @return The sound.
	 */
	public Audio getSound() {
		return sound;
	}

	public int getDefenceReq() {
		return defenceReq;
	}

	public void setDefenceReq(int defenceReq) {
		this.defenceReq = defenceReq;
	}

}
