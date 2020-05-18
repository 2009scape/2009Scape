package org.crandor.game.node.entity.player.info;

import org.crandor.game.node.entity.player.Player;

/**
 * Represent the rights of a player.
 * @author 'Vexia
 */
public enum Rights {
	REGULAR_PLAYER, PLAYER_MODERATOR, ADMINISTRATOR() {
		@Override
		public boolean isVisible(Player player) {
			return player.getAttribute("visible_rank", ADMINISTRATOR) == ADMINISTRATOR;
		}
	};

	/**
	 * Gets the chat icon.
	 * @param player The player to get the chat icon for.
	 * @return The chat icon.
	 */
	public static int getChatIcon(Player player) {
		Rights c = player.getAttribute("visible_rank", player.getDetails().getRights());
		if (c != Rights.REGULAR_PLAYER && c != null) {
			return c.toInteger();
		}
		if (player.getIronmanManager().isIronman()) {
			return player.getIronmanManager().getMode().getIcon();
		}
		return 0;
	}

	/**
	 * Checks if the player has a hidden rank.
	 * @param player the player.
	 * @return {@code True} if hidden.
	 */
	public static boolean isHidden(final Player player) {
		return player.getAttribute("visible_rank", player.getDetails().getRights()) != player.getDetails().getRights();
	}

	/**
	 * Gets the ordinal of the rights.
	 * @return the integer format of a rank.
	 */
	public final int toInteger() {
		return ordinal();
	}

	/**
	 * Method used to get the credentials based off the id.
	 * @param id the id.
	 * @return the credential.
	 */
	public static Rights forId(int id) {
		if (id < 0) {
			id = 0;
		}
		return values()[id];
	}

	/**
	 * Checks if the player's rank is visible.
	 */
	public boolean isVisible(Player username) {
		return true;
	}
}
