package core.game.node.entity.player.link.request;

import plugin.quest.tutorials.tutorialisland.TutorialSession;
import core.game.node.entity.player.Player;
import core.game.world.GameWorld;

/**
 * Represents a managing class for requests of a player.
 * @author Vexia
 */
public final class RequestManager {

	/**
	 * Represents the player instance.
	 */
	private final Player player;

	/**
	 * Represents the target being requested.
	 */
	private Player target;

	/**
	 * Constructs a new {@code RequestManager} {@code Object}.
	 * @param player the player.
	 */
	public RequestManager(final Player player) {
		this.player = player;
	}

	/**
	 * Method used to send a request type to a target.
	 * @param target the target.
	 * @param type the type of request.
	 * @return {@code True} if successful in requesting.
	 */
	public boolean request(final Player target, final RequestType type) {
		if (!canRequest(type, target)) {
			return false;
		}
		if (acceptExisting(target, type)) {
			return true;
		}
		player.getPacketDispatch().sendMessage(type.getMessage());
		target.getPacketDispatch().sendMessage(type.getRequestMessage(player));
		player.setAttribute("lastRequest", type);
		this.target = target;
		return true;
	}

	/**
	 * Method used to check if a player can continue with a request.
	 * @param type the type.
	 * @param target the target.
	 * @return {@code True} if they can request.
	 */
	private boolean canRequest(RequestType type, Player target) {
		if (target == player) {
			return false;
		}
		if (!TutorialSession.getExtension(player).finished() || !TutorialSession.getExtension(target).finished()) {
			return false;
		}
		if (!target.getLocation().withinDistance(player.getLocation(), 15)) {
			player.getPacketDispatch().sendMessage("Unable to find " + target.getUsername() + ".");
			return false;
		}
		if (!target.isActive() || target.getInterfaceManager().isOpened()) {
			player.getPacketDispatch().sendMessage("Other player is busy at the moment.");
			return false;
		}
		if (target.getAttribute("busy", 0) > GameWorld.getTicks() || player.getAttribute("busy", 0) > GameWorld.getTicks()) {
			player.getPacketDispatch().sendMessage("Other player is busy at the moment.");
			return false;
		}
		if (!player.getZoneMonitor().canRequest(type, target)) {
			return false;
		}
		return type.canRequest(player, target);
	}

	/**
	 * Method used to check if we can accept an existing request.
	 * @param target the target.
	 * @param type the type.
	 * @return {@code True} if so.
	 */
	public boolean acceptExisting(final Player target, final RequestType type) {
		final RequestType lastType = target.getAttribute("lastRequest", null);
		if (lastType == type && player == target.getRequestManager().getTarget()) {
			close(player);
			clear();
			target.getRequestManager().clear();
			player.setAttribute("busy", GameWorld.getTicks() + 2);
			target.setAttribute("busy", GameWorld.getTicks() + 2);
			type.getModule().open(player, target);
			return true;
		}
		close(player);
		return false;
	}

	/**
	 * Closes the components for the player.
	 * @param player the player.
	 */
	private void close(Player player) {
		player.getDialogueInterpreter().close();
		player.getInterfaceManager().close();
		player.getInterfaceManager().closeChatbox();
	}

	/**
	 * Method used to clear the cached target.
	 */
	public void clear() {
		this.target = null;
	}

	/**
	 * Gets the player.
	 * @return the player.
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * Gets the target.
	 * @return the target.
	 */
	public Player getTarget() {
		return target;
	}

}
