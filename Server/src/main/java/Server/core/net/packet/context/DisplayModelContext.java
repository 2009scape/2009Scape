package core.net.packet.context;

import core.game.node.entity.player.Player;
import core.net.packet.Context;

/**
 * Represents the player on interface context.
 * @author Emperor
 */
public class DisplayModelContext implements Context {

	/**
	 * Represents the model types.
	 * @author Emperor
	 */
	public static enum ModelType {
		PLAYER, NPC, ITEM, MODEL;
	}

	/**
	 * The player reference.
	 */
	private final Player player;

	/**
	 * The model type.
	 */
	private final ModelType type;

	/**
	 * The node id.
	 */
	private final int nodeId;

	/**
	 * The amount (for item display).
	 */
	private int amount;

	/**
	 * The zoom.
	 */
	private int zoom;

	/**
	 * The interface id.
	 */
	private final int interfaceId;

	/**
	 * The child id.
	 */
	private final int childId;

	/**
	 * Construct a new {@code DisplayModelContext} {@code Object} used for
	 * displaying the player.
	 * @param player The player reference.
	 * @param interfaceId The interface id.
	 * @param childId The child id.
	 */
	public DisplayModelContext(Player player, int interfaceId, int childId) {
		this(player, ModelType.PLAYER, -1, 0, interfaceId, childId);
	}

	/**
	 * Construct a new {@code DisplayModelContext} {@code Object} used for
	 * displaying an NPC.
	 * @param player The player reference.
	 * @param nodeId The node id to display.
	 * @param interfaceId The interface id.
	 * @param childId The child id.
	 */
	public DisplayModelContext(Player player, int nodeId, int interfaceId, int childId) {
		this(player, ModelType.NPC, nodeId, 0, interfaceId, childId);
	}

	/**
	 * Construct a new {@code DisplayModelContext} {@code Object} used by the
	 * other constructors or for displaying an item.
	 * @param player The player reference.
	 * @param type The model type.
	 * @param nodeId The node id to display.
	 * @param amount The amount.
	 * @param interfaceId The interface id.
	 * @param childId The child id.
	 */
	public DisplayModelContext(Player player, ModelType type, int nodeId, int amount, int interfaceId, int childId) {
		this.player = player;
		this.type = type;
		this.nodeId = nodeId;
		this.amount = amount;
		this.interfaceId = interfaceId;
		this.childId = childId;
	}

	/**
	 * Construct a new {@code DisplayModelContext} {@code Object} used by the
	 * other constructors or for displaying an item.
	 * @param player The player reference.
	 * @param type The model type.
	 * @param nodeId The node id to display.
	 * @param interfaceId The interface id.
	 * @param childId The child id.
	 */
	public DisplayModelContext(Player player, ModelType type, int nodeId, int zoom, int interfaceId, int childId, Object... object) {
		this.player = player;
		this.type = type;
		this.nodeId = nodeId;
		this.setZoom(zoom);
		this.interfaceId = interfaceId;
		this.childId = childId;
	}

	@Override
	public Player getPlayer() {
		return player;
	}

	/**
	 * Gets the type.
	 * @return The type.
	 */
	public ModelType getType() {
		return type;
	}

	/**
	 * Gets the nodeId.
	 * @return The nodeId.
	 */
	public int getNodeId() {
		return nodeId;
	}

	/**
	 * Gets the amount.
	 * @return The amount.
	 */
	public int getAmount() {
		return amount;
	}

	/**
	 * Get the interface id.
	 * @return The interface id.
	 */
	public int getInterfaceId() {
		return interfaceId;
	}

	/**
	 * Get the child id.
	 * @return The child id.
	 */
	public int getChildId() {
		return childId;
	}

	/**
	 * @return the zoom.
	 */
	public int getZoom() {
		return zoom;
	}

	/**
	 * @param zoom the zoom to set.
	 */
	public void setZoom(int zoom) {
		this.zoom = zoom;
	}

}