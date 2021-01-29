package plugin.skill.farming.patch;

import plugin.skill.farming.FarmingNode;
import core.game.node.item.Item;

/**
 * Represents the herb nodes.
 * @author 'Vexia
 * @version 1.0
 */
public enum Herbs {
	GUAM(new HerbNode(new Item(5291), new Item(199), 11, 4, 20, 9, new double[] { 11, 12.5 })),
	MARRENTILL(new HerbNode(new Item(5292), new Item(201), 11, 4, 20, 14, new double[] { 13.5, 15 })),
	TARROMIN(new HerbNode(new Item(5293), new Item(203), 11, 4, 20, 19, new double[] { 16, 18 })),
	HARRALANDER(new HerbNode(new Item(5294), new Item(205), 11, 4, 20, 26, new double[] { 21.5, 24 })),
	RANAR(new HerbNode(new Item(5295), new Item(207), 11, 4, 20, 32, new double[] { 27, 30.5 })),
	SPIRIT_WEED(new HerbNode(new Item(12176), new Item(12174), 11, 4, 20, 36, new double[] { 32, 36 })),
	TOAD_FLAX(new HerbNode(new Item(5296), new Item(3049), 11, 4, 20, 38, new double[] { 34, 38.5 })),
	IRIT(new HerbNode(new Item(5297), new Item(209), 11, 4, 20, 44, new double[] { 43, 48.5 })),
	AVANTOE(new HerbNode(new Item(5298), new Item(211), 11, 4, 20, 50, new double[] { 54.5, 61.5 })),
	KWUARM(new HerbNode(new Item(5299), new Item(213), 11, 4, 20, 56, new double[] { 69, 78 })),
	SNAPDRAGON(new HerbNode(new Item(5300), new Item(3051), 11, 4, 20, 62, new double[] { 87.5, 98.5 })),
	CADANTINE(new HerbNode(new Item(5301), new Item(215), 11, 4, 20, 67, new double[] { 106.5, 120 })),
	LANTADYME(new HerbNode(new Item(5302), new Item(2485), 11, 4, 20, 73, new double[] { 134.5, 151.5 })),
	DWARF(new HerbNode(new Item(5303), new Item(217), 11, 4, 20, 79, new double[] { 170.5, 192 })),
	TORSTOL(new HerbNode(new Item(5304), new Item(219), 11, 4, 20, 85, new double[] { 199.5, 224.5 }));

	/**
	 * Represents the farming node.
	 */
	private final FarmingNode node;

	/**
	 * Constructs a new {@code FarmingNodes} {@code Object}.
	 */
	Herbs(final FarmingNode allotment) {
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
		FarmingNode[] nodes = new HerbNode[values().length];
		for (int i = 0; i < values().length; i++) {
			nodes[i] = values()[i].getFarmingNode();
		}
		return nodes;
	}

}
