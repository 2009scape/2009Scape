package plugin.skill.farming.patch;

import plugin.skill.farming.FarmingNode;
import core.game.node.item.Item;

/**
 * Represents the special nodes.
 * @author 'Vexia
 * @version 1.0
 */
public enum SpecialNode {
	EVIL_TURNIP(new FarmingNode(new Item(12148), new Item(12134), 4, 1, 4, 42, new double[] { 841, 46 })),
	CACTUS(new PickingNode(new Item(5280), new Item(6016), 8, 7, 80, 55, new double[] { 66.5, 25, 374 }, 3) {
		@Override
		public int getCheckHealthBase() {
			return 31;
		}

		@Override
		public int getDeathBase() {
			return 17;
		}

		@Override
		public int getDiseaseBase() {
			return 10;
		}
	}),
	BELLADONNA(new PickingNode(new Item(5281), new Item(2398), 4, 5, 80, 63, new double[] { 91, 512 }, 1) {
		@Override
		public int getDeathBase() {
			return 8;
		}

		@Override
		public int getDiseaseBase() {
			return 4;
		}
	}),
	CALQUAT(new PickingNode(new Item(5503), new Item(5980), 4, 8, 160, 72, new double[] { 129.5, 48.5, 12096 }, 6) {

		@Override
		public int getDiseaseBase() {
			return 14;
		}

		@Override
		public int getDeathBase() {
			return 22;
		}

		@Override
		public int getCheckHealthBase() {
			return 34;
		}
	});

	/**
	 * Represents the farming node.
	 */
	private final FarmingNode node;

	/**
	 * Constructs a new {@code FarmingNodes} {@code Object}.
     */
	SpecialNode(final FarmingNode allotment) {
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
