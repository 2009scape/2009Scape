package plugin.skill.farming.patch;

import plugin.skill.farming.FarmingNode;
import core.game.node.item.Item;

/**
 * Represents the allotments.
 * @author Vexia
 */
public enum Allotments {
	POTATO(new FarmingNode(new Item(5318), new Item(1942), 6, 4, 10, 1, new double[] { 8, 9 }, Flowers.MARIGOLD, new Item(6032))),
	ONION(new FarmingNode(new Item(5319), new Item(1957), 13, 4, 10, 5, new double[] { 9.5, 10.5 }, Flowers.MARIGOLD, new Item(5438))),
	CABBAGE(new FarmingNode(new Item(5324), new Item(1965), 20, 4, 10, 7, new double[] { 10, 11.5 }, Flowers.ROSEMARY, new Item(5458))),
	TOMATO(new FarmingNode(new Item(5322), new Item(1982), 27, 4, 10, 12, new double[] { 12.5, 14 }, new Item(5478, 2))),
	SWEETCORN(new FarmingNode(new Item(5320), new Item(5986), 34, 6, 10, 20, new double[] { 17, 19 }, new Item(5931, 10))),
	STRAWBERRY(new FarmingNode(new Item(5323), new Item(5504), 43, 6, 10, 31, new double[] { 26, 29 }, new Item(5386))),
	WATERMELON(new FarmingNode(new Item(5321), new Item(5982), 52, 8, 10, 47, new double[] { 48.5, 54.5 }, Flowers.NASTURTIUM, new Item(5970, 10)));

	/**
	 * Represents the allotment node.
	 */
	private final FarmingNode allotment;

	/**
	 * Constructs a new {@code FarmingNodes} {@code Object}.
	 * @param allotment the allotment.
	 */
	Allotments(final FarmingNode allotment) {
		this.allotment = allotment;
	}

	/**
	 * Gets the allotment.
	 * @return The allotment.
	 */
	public FarmingNode getFarmingNode() {
		return allotment;
	}

	/**
	 * Gets all the allotment nodes.
	 * @return the allotments.
	 */
	public static FarmingNode[] getNodes() {
		FarmingNode[] allotments = new FarmingNode[values().length];
		for (int i = 0; i < values().length; i++) {
			allotments[i] = values()[i].getFarmingNode();
		}
		return allotments;
	}

}
