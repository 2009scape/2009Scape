package core.game.content.global.report;

import core.game.node.entity.player.Player;

/**
 * Represents a rule.
 * @author 'Vexia
 */
public enum Rule {
	OFFENSIVE_LANGUAGE(0), ITEM_SCAMMING(1), PASSWORD_SCAMMING(2), BUG_ABUSE(3), KELDAGRIM_STAFF_IMPERSONATION(4), ACCCOUNT_SHARING(5), MACROING(6), MULTIPLE_LOGGING(7), ENCOURAGING_TO_BREAK_TULES(8), MISUSE_OF_CUSTOMER_SUPPORT(9), ADVERISTING(10), REAL_WORLD_ITEM_TRADING(11), ASKING_PERSONAL_DETAILS(12);

	/**
	 * Constructs a new {@code Rule.java} {@code Object}.
	 * @param rule the rule.
	 */
	Rule(int rule) {
		this.rule = rule;
	}

	/**
	 * The rule id opcode.
	 */
	private int rule;

	/**
	 * Represents if the rule is relative to the sequences that has happend.
	 * @return {@code True} if so.
	 */
	public boolean canRequest(Player target) {
		if (target == null) {
			return false;
		}
		if (target.getSavedData().getGlobalData().getChatPing() < System.currentTimeMillis()) {
			return false;
		}
		return true;
	}

	/**
	 * Gets the rule.
	 * @return the rule.
	 */
	public int getRule() {
		return rule;
	}

	/**
	 * Gets the rule.
	 * @param id the id.
	 * @return the rule.
	 */
	public static Rule forId(int id) {
		for (Rule rule : Rule.values()) {
			if (rule.getRule() == id) {
				return rule;
			}
		}
		return null;
	}
}
