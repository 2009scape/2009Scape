package plugin.skill.farming.patch;

import plugin.skill.farming.FarmingNode;
import plugin.skill.farming.wrapper.PatchCycle;
import core.game.node.item.Item;

/**
 * Represents the bush nodes.
 * @author 'Vexia
 * @version 1.0
 */
public enum Bushes {
	REDBERRY(new BushNode(new Item(5101), new Item(1951), 5, 5, 20, 10, new double[] { 11.5, 4.5, 64 }, new Item(5478, 4))),
	CADAVABERRY(new BushNode(new Item(5102), new Item(753), 15, 6, 20, 22, new double[] { 18, 7, 102.5 }, new Item(5968, 3))),
	DWELLBERRY(new BushNode(new Item(5103), new Item(2126), 26, 7, 20, 36, new double[] { 31.5, 12, 177.5 }, new Item(5406, 3))),
	JANGERBERRY(new BushNode(new Item(5104), new Item(247), 38, 8, 20, 48, new double[] { 50.5, 19, 284.5 }, new Item(5982, 6))),
	WHITEBERRY(new BushNode(new Item(5105), new Item(239), 51, 8, 20, 59, new double[] { 78, 29, 437.5 }, new Item(6004, 8))),
	POISON_IVY(new BushNode(new Item(5106), new Item(6018), 197, 8, 20, 70, new double[] { 120, 45, 675 }) {
		@Override
		public boolean canDisease(final PatchCycle cycle) {
			return false;
		}
	});

	/**
	 * Represents the farming node.
	 */
	private final FarmingNode node;

	/**
	 * Constructs a new {@code FarmingNodes} {@code Object}.
	 * @param node the node.
	 */
	Bushes(final FarmingNode allotment) {
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
	public static Bushes forNode(final FarmingNode node) {
		for (Bushes b : values()) {
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
		FarmingNode[] nodes = new BushNode[values().length];
		for (int i = 0; i < values().length; i++) {
			nodes[i] = values()[i].getFarmingNode();
		}
		return nodes;
	}

}
