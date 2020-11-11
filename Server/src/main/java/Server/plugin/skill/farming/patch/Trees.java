package plugin.skill.farming.patch;

import plugin.skill.farming.FarmingNode;
import core.game.node.item.Item;

/**
 * Represents the tree nodes.
 *
 * @author 'Vexia
 */
public enum Trees {
    OAK(new TreeNode(new Item(5370), new Item(1521), 8, 4, 40, 15, new double[]{14, 467.3}, new Item(5968))),
    WILLOW(new TreeNode(new Item(5371), new Item(1519), 15, 6, 40, 30, new double[]{25, 1456.5}, new Item(5386))),
    MAPLE(new TreeNode(new Item(5372), new Item(1517), 24, 8, 40, 45, new double[]{45, 3403.4}, new Item(5396))),
    YEW(new TreeNode(new Item(5373), new Item(1515), 35, 10, 40, 60, new double[]{81, 7069.9}, new Item(6016, 10))),
    MAGIC(new TreeNode(new Item(5374), new Item(1513), 48, 12, 40, 75, new double[]{145.5, 13768.3}, new Item(5974, 25)));

    /**
     * Represents the farming node.
     */
    private final FarmingNode node;

    /**
     * Constructs a new {@code FarmingNodes} {@code Object}.
     *
     * @param node the node.
     */
    Trees(final FarmingNode allotment) {
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
     * Gets the bush for the node.
     *
     * @param node the node.
     * @return the bush.
     */
    public static Trees forNode(final FarmingNode node) {
        for (Trees b : values()) {
            if (b.getFarmingNode() == node) {
                return b;
            }
        }
        return null;
    }

    /**
     * Gets all the farming nodes.
     *
     * @return the nodes.
     */
    public static FarmingNode[] getNodes() {
        FarmingNode[] nodes = new TreeNode[values().length];
        for (int i = 0; i < values().length; i++) {
            nodes[i] = values()[i].getFarmingNode();
        }
        return nodes;
    }

}
