package org.crandor.game.node.entity.player.link;

import org.crandor.game.node.entity.Entity;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.entity.state.EntityState;
import org.crandor.game.world.update.flag.player.AppearanceFlag;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a managing class of the active player skulls.
 * @author Vexia
 * @author Emperor
 */
public final class SkullManager {

	/**
	 * The player instance.
	 */
	private final Player player;

	/**
	 * Represents if the player is in the wilderness.
	 */
	private boolean wilderness = false;

	/**
	 * If the wilderness zone is currently disabled.
	 */
	private boolean wildernessDisabled = false;

	/**
	 * Represents the current wilderness level.
	 */
	private int level;

	/**
	 * The players this player has skulled on.
	 */
	private final List<Player> skullCauses = new ArrayList<Player>();

	/**
	 * If the player is skulled.
	 */
	private boolean skulled;

	/**
	 * If the skull check is disabled.
	 */
	private boolean skullCheckDisabled;

	/**
	 * Constructs a new {@code SkullManager} {@code Object}.
	 * @param player the player.
	 */
	public SkullManager(Player player) {
		this.player = player;
	}

	/**
	 * Checks if we should skull on this entity.
	 * @param other The entity to check.
	 */
	public void checkSkull(Entity other) {
		if (!(other instanceof Player) || !wilderness || skullCheckDisabled) {
			return;
		}
		Player o = (Player) other;
		for (Player p : o.getSkullManager().skullCauses) {
			if (p == player) {
				return;
			}
		}
		if (skullCauses.contains(o)) {
			return;
		}
		skullCauses.add(o);
		player.getStateManager().set(EntityState.SKULLED);
	}

	/**
	 * Sets the skull icon.
	 * @param skullIcon The skull icon.
	 */
	public void setSkullIcon(int skullIcon) {
		player.getAppearance().setSkullIcon(skullIcon);
		player.getUpdateMasks().register(new AppearanceFlag(player));
	}

	/**
	 * Resets the skull causes cache.
	 */
	public void reset() {
		skullCauses.clear();
		setSkullIcon(-1);
		setSkulled(false);
	}

	/**
	 * Gets the player.
	 * @return the player.
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * Gets the level.
	 * @return the level.
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * Sets the level.
	 * @param level the level to set.
	 */
	public void setLevel(int level) {
		this.level = level;
	}

	/**
	 * Gets the value.
	 * @return the wilderness.
	 */
	public boolean isWilderness() {
		return wilderness;
	}

	/**
	 * Sets the value of this boolean.
	 * @param wilderness the wilderness to set.
	 */
	public void setWilderness(boolean wilderness) {
		this.wilderness = wilderness;
	}

	/**
	 * Gets the skullCheckDisabled.
	 * @return The skullCheckDisabled.
	 */
	public boolean isSkullCheckDisabled() {
		return skullCheckDisabled;
	}

	/**
	 * Sets the skullCheckDisabled.
	 * @param skullCheckDisabled The skullCheckDisabled to set.
	 */
	public void setSkullCheckDisabled(boolean skullCheckDisabled) {
		this.skullCheckDisabled = skullCheckDisabled;
	}

	/**
	 * Gets the wildernessDisabled.
	 * @return The wildernessDisabled.
	 */
	public boolean isWildernessDisabled() {
		return wildernessDisabled;
	}

	/**
	 * Sets the wildernessDisabled.
	 * @param wildernessDisabled The wildernessDisabled to set.
	 */
	public void setWildernessDisabled(boolean wildernessDisabled) {
		this.wildernessDisabled = wildernessDisabled;
	}

	/**
	 * Gets the skulled.
	 * @return The skulled.
	 */
	public boolean isSkulled() {
		return skulled;
	}

	/**
	 * Sets the skulled.
	 * @param skulled The skulled to set.
	 */
	public void setSkulled(boolean skulled) {
		this.skulled = skulled;
	}
}
