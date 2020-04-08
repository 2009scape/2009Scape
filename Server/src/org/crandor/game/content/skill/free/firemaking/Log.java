package org.crandor.game.content.skill.free.firemaking;

import java.util.HashMap;

/**
 * Represents an enumeration of burnable logs.
 * @author 'Vexia
 */
public enum Log {
	NORMAL(1511, 1, 180, 2732, 40),
	ACHEY(2862, 1, 180, 2732, 40),
	OAK(1521, 15, 200, 2732, 60), 
	WILLOW(1519, 30, 250, 2732, 90), 
	TEAK(6333, 35, 300, 2732, 105), 
	ARCTIC_PINE(10810, 42, 500, 2732, 125),
	MAPLE(1517, 45, 300, 2732, 135),
	MAHOGANY(6332, 50, 300, 2732, 157.5), 
	EUCALYPTUS(12581, 58, 300, 2732, 193.5), 
	YEW(1515, 60, 400, 2732, 202.5), 
	MAGIC(1513, 75, 450, 2732, 303.8), 
	CURSED_MAGIC(13567, 82, 650, 2732, 303.8), 
	PURPLE(10329, 1, 200, 20001, 50),
	WHITE(10328, 1, 200, 20000, 50),
	BLUE(7406, 1, 200, 11406, 50),
	GREEN(7405, 1, 200, 11405, 50), 
	RED(7404, 1, 200, 11404, 50), 
	JOGRE(3125, 1, 200, 3862, 50);

	public static HashMap<Integer,Log> logMap = new HashMap<>();
	static{
		Log[] logArray = Log.values();
		int logLength = logArray.length;
		for(int i = 0; i < logLength; i++){
			Log log = logArray[i];
			logMap.putIfAbsent(log.logId,log);
		}
	}
	
	/**
	 * The log id.
	 */
	private final int logId;

	/**
	 * The level.
	 */
	private final int level;

	/**
	 * The life.
	 */
	private final int life;

	/**
	 * The fire id.
	 */
	private final int fireId;

	/**
	 * The exp gained.
	 */
	private final double xp;

	/**
	 * Constructs a new {@code FireMakingDefinitions.java} {@code Object}.
	 * @param logId the log id.
	 * @param level the level.
	 * @param life the life.
	 * @param fireId the fire id.
	 * @param xp the experience.
	 * @param time the time.
	 */
	Log(int logId, int level, int life, int fireId, double xp) {
		this.logId = logId;
		this.level = level;
		this.life = life;
		this.fireId = fireId;
		this.xp = xp;
	}

	/**
	 * Gets the logId.
	 * @return The logId.
	 */
	public int getLogId() {
		return logId;
	}

	/**
	 * Gets the level.
	 * @return The level.
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * Gets the life.
	 * @return The life.
	 */
	public int getLife() {
		return life;
	}

	/**
	 * Gets the fireId.
	 * @return The fireId.
	 */
	public int getFireId() {
		return fireId;
	}

	/**
	 * Gets the xp.
	 * @return The xp.
	 */
	public double getXp() {
		return xp;
	}

	/**
	 * Gets the log by the id.
	 * @param id the id.
	 * @return the log.
	 */
	public static Log forId(int id) {
		return logMap.get(id);
	}
}