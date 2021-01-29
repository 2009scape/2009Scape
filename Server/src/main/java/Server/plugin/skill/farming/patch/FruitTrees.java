package plugin.skill.farming.patch;

import plugin.skill.farming.FarmingNode;
import core.game.node.item.Item;

/**
 * Represents the tree nodes.
 * @author 'Vexia
 * @version 1.0
 */
public enum FruitTrees {
	APPLE(new FruitTreeNode(new Item(5496), new Item(1955), 8, 6, 160, 27, new double[] { 22, 8.5, 1199.5 }, new Item(5986, 9)) {
		@Override
		public int getDiseaseBase() {
			return 12;
		}

		@Override
		public int getDeathBase() {
			return 20;
		}
	}),
	BANANA(new FruitTreeNode(new Item(5497), new Item(1963), 35, 6, 160, 33, new double[] { 28, 10.5, 1750.5 }, new Item(5386, 4))),
	ORANGE(new FruitTreeNode(new Item(5498), new Item(2108), 72, 6, 160, 39, new double[] { 35.5, 13.5, 2470.2 }, new Item(5406, 3))),
	CURRY(new FruitTreeNode(new Item(5499), new Item(5970), 99, 6, 160, 42, new double[] { 40, 15, 2906.9 }, new Item(5416, 5))),
	PINEAPPLE(new FruitTreeNode(new Item(5500), new Item(2114), 136, 6, 160, 51, new double[] { 57, 21.5, 4605.7 }, new Item(5982, 10))),
	PAPAYA(new FruitTreeNode(new Item(5501), new Item(5972), 163, 6, 160, 57, new double[] { 72, 27, 6146.4 }, new Item(2114, 10))),
	PALM(new FruitTreeNode(new Item(5502), new Item(5974), 200, 6, 160, 68, new double[] { 110.5, 41.5, 10150.1 }, new Item(5972, 15)));

	/**
	 * Represents the farming node.
	 */
	private final FarmingNode node;

	/**
	 * Constructs a new {@code FarmingNodes} {@code Object}.
	 */
	FruitTrees(final FarmingNode allotment) {
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
	 * Gets the bush for the node.
	 * @param node the node.
	 * @return the bush.
	 */
	public static FruitTrees forNode(final FarmingNode node) {
		for (FruitTrees b : values()) {
			if (b.getFarmingNode() == node) {
				return b;
			}
		}
		return null;
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
