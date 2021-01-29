package plugin.skill.farming.patch;

import plugin.skill.farming.FarmingNode;
import core.game.node.item.Item;

/**
 * Represents the flower nodes.
 * @author 'Vexia
 * @version 1.0
 */
public enum Flowers {
	MARIGOLD(new FarmingNode(new Item(5096), new Item(6010), 8, 4, 5, 2, new double[] { 8.5, 47 })),
	ROSEMARY(new FarmingNode(new Item(5097), new Item(6014), 13, 4, 5, 11, new double[] { 12, 66.5 })),
	NASTURTIUM(new FarmingNode(new Item(5098), new Item(6012), 18, 4, 5, 24, new double[] { 19.5, 111 })),
	WOAD(new FarmingNode(new Item(5099), new Item(1793, 3), 23, 4, 5, 25, new double[] { 20.5, 115.5 })),
	LIMPWURT(new FarmingNode(new Item(5100), new Item(225, 3), 28, 4, 5, 26, new double[] { 18.5, 120 }));

	/**
	 * Represents the farming node.
	 */
	private final FarmingNode node;

	/**
	 * Constructs a new {@code FarmingNodes} {@code Object}.
	 */
	Flowers(final FarmingNode allotment) {
		this.node = allotment;
	}

	/**
	 * Gets the node.
	 * @return The node.
	 */
	public FarmingNode getFarmingNode() {
		return node;
	}

	/**
	 * Gets all the farming nodes.
	 * @return the nodes.
	 */
	public static FarmingNode[] getNodes() {
		FarmingNode[] nodes = new FarmingNode[values().length];
		for (int i = 0; i < values().length; i++) {
			nodes[i] = values()[i].getFarmingNode();
		}
		return nodes;
	}

}
