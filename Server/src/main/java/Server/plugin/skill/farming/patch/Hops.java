package plugin.skill.farming.patch;

import plugin.skill.farming.FarmingNode;
import core.game.node.item.Item;

/**
 * Represents the hop nodes.
 *
 * @author 'Vexia
 * @version 1.0
 */
public enum Hops {
    BARLEY(new FarmingNode(new Item(5305), new Item(6006), 49, 4, 10, 3, new double[]{8.5, 9.5}, new Item(6032, 3))),
	HAMMERSTONE(new FarmingNode(new Item(5307), new Item(5994), 4, 4, 10, 4, new double[]{9, 10}, new Item(6010))),
	ASGARNIAN(new FarmingNode(new Item(5308), new Item(5996), 11, 5, 10, 8, new double[]{10.5, 12}, new Item(5458))),
	JUTE(new FarmingNode(new Item(5306), new Item(5931), 56, 5, 10, 13, new double[]{13, 14.5}, new Item(6008, 6))),
	YANILLIAN(new FarmingNode(new Item(5309), new Item(5998), 19, 6, 10, 16, new double[]{16, 37}, new Item(5968))),
	KRANDORIAN(new FarmingNode(new Item(5310), new Item(6000), 28, 7, 10, 21, new double[]{17.6, 19.5}, new Item(5478, 3))),
	WILDBLOOD(new FarmingNode(new Item(5311), new Item(6002), 38, 8, 10, 28, new double[]{23, 26}, new Item(6012)));

    /**
     * Represents the farming node.
     */
    private final FarmingNode node;

    /**
     * Constructs a new {@code FarmingNodes} {@code Object}.
     *
     */
    Hops(final FarmingNode allotment) {
        this.node = allotment;
    }

    /**
     * Gets the node.
     *
     * @return The node.
     */
    public FarmingNode getFarmingNode() {
        return node;
    }

    /**
     * Gets all the farming nodes.
     *
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
