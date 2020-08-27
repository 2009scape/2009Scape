package core.game.system.monitor;

import core.ServerConstants;

/**
 * Handles duplication-related logging.
 * @author Emperor
 */
public final class DuplicationLog extends MessageLog {

	/**
	 * The amount of milliseconds the player's actions should be logged. (days *
	 * hours * minutes * milliseconds)
	 */
	public static long LOGGING_DURATION = 5 * 24 * 60 * 60_000;

	/**
	 * The duplication flag for player talking about duping.
	 */
	public static final int DUPE_TALK = 0x1;

	/**
	 * The duplication flag for a large networth increase.
	 */
	public static final int NW_INCREASE = 0x8;

	/**
	 * The current flag.
	 */
	private int flag;

	/**
	 * The last time the player triggered the "large networth increase" flag.
	 */
	private long lastIncreaseFlag;

	/**
	 * Constructs a new {@code DuplicationLog} {@code Object}.
	 */
	public DuplicationLog() {
		super(-1, false);
	}

	@Override
	public void write(String playerName) {
		String priority = "low";
		switch (getProbability()) {
		case 3:
			priority = "high";
			break;
		case 2:
			priority = "mid";
		}
		super.write(ServerConstants.LOGS_PATH + "duplicationflags/" + priority + "prior/" + playerName + ".log");
	}

	/**
	 * Gets the probability of duping (scale from 0 - 3).
	 * @return The probability (where 3 is very likely and 0 is very unlikely).
	 */
	public int getProbability() {
		int probability = 0;
		if ((flag & NW_INCREASE) != 0) {
			probability += 2;
		}
		if ((flag & DUPE_TALK) != 0) {
			probability++;
		}
		return probability;
	}

	/**
	 * Checks if the player's actions should be logged (to find cause of
	 * duping).
	 * @return {@code True} if so.
	 */
	public boolean isLoggingFlagged() {
		return (flag & NW_INCREASE) != 0 && (System.currentTimeMillis() - lastIncreaseFlag) < LOGGING_DURATION;
	}

	/**
	 * Adds a flag.
	 * @param flag The flag to add.
	 */
	public void flag(int flag) {
		this.flag |= flag;
		if (flag == NW_INCREASE) {
			lastIncreaseFlag = System.currentTimeMillis();
		}
	}

	/**
	 * Gets the flag.
	 * @return The flag.
	 */
	public int getFlag() {
		return flag;
	}

	/**
	 * Gets the lastIncreaseFlag.
	 * @return The lastIncreaseFlag.
	 */
	public long getLastIncreaseFlag() {
		return lastIncreaseFlag;
	}

	/**
	 * Sets the lastIncreaseFlag.
	 * @param lastIncreaseFlag The lastIncreaseFlag to set.
	 */
	public void setLastIncreaseFlag(long lastIncreaseFlag) {
		this.lastIncreaseFlag = lastIncreaseFlag;
	}

}