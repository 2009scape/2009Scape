package plugin.skill.farming;

import plugin.skill.farming.patch.*;
import core.game.node.item.Item;

import java.util.HashMap;

/**
 * Represents a farming patch.
 * @author 'Vexia
 * @version 1.0
 */
public enum FarmingPatch {
	ALLOTMENT("allotment", new int[] { 8550, 8551, 8552, 8553, 8554, 8555, 8556, 8557 }, 3, 3 | 56 << 16, Allotments.getNodes()),
	HOP("hop", new int[] { 8173, 8174, 8175, 8176 }, 4, 3 | 29 << 16, Hops.getNodes()),
	TREE("tree", new int[] { 8391, 8390, 8389, 8388, 8387, 8386, 8385, 8384, 19147 }, 1, 1 | 1 << 16, Trees.getNodes()),
	FRUIT_TREE("fruit tree", new int[] { 7964, 7965, 7962, 7963 }, 1, 1 | 1 << 16, FruitTrees.getNodes()),
	BUSHES("bush", new int[] { 7579, 7578, 7577, 7580 }, 1, 4 | 4 << 16, Bushes.getNodes()),
	FLOWER("flower", new int[] { 7847, 7850, 7848, 7849 }, 1, 1 | 1 << 16, Flowers.getNodes()),
	HERB("herb", new int[] { 8152, 8153, 8150, 8151, 18816 }, 1, 3 | 18 << 16, Herbs.getNodes()),
	EVIL_TURNIP("evil turnip", new int[] { 23760 }, 1, 1 | 1 << 16, SpecialNode.getNodes()[0]),
	CACTUS("cactus", new int[] { 7771 }, 1, 1 | 3 << 16, SpecialNode.getNodes()[1]),
	BELLADONNA("belladonna", new int[] { 7572 }, 1, 1 | 1 << 16, SpecialNode.getNodes()[2]),
	CALQUAT("calquat", new int[] { 7807 }, 1, 1 | 1 << 16, SpecialNode.getNodes()[3]);



	public static HashMap<Integer, FarmingPatch> objectMap = new HashMap<>();
	static{
		FarmingPatch[] enumArray = values();
		int enumSize = enumArray.length;
		for(int i = 0; i < enumSize; i++){
			int[] objIds = enumArray[i].objectIds;
			int objectsSize = objIds.length;
			for(int j = 0; j < objectsSize; j++){
				objectMap.putIfAbsent(objIds[j], enumArray[i]);
			}
		}
	}
	/**
	 * Represents the patch name.
	 */
	private final String name;

	/**
	 * Represents the wrapper ids.
	 */
	private final int[] objectIds;

	/**
	 * Represents the seed requirement.
	 */
	private final int seedRequirement;

	/**
	 * Represents the yield hash.
	 */
	private final int yieldHash;

	/**
	 * Represents the farming nodes of a patch.
	 */
	private final FarmingNode[] nodes;

	/**
	 * Constructs a new {@code FarmingPatch} {@code Object}.
	 * @param nodes the nodes.
	 */
	FarmingPatch(final String name, final int[] objectIds, final int seedRequirement, final int yieldHash, final FarmingNode... nodes) {
		this.name = name;
		this.objectIds = objectIds;
		this.seedRequirement = seedRequirement;
		this.yieldHash = yieldHash;
		this.nodes = nodes;
	}


	/**
	 * Gets the farming node by the seed.
	 * @param item the item.
	 * @return the node,
	 */
	public FarmingNode forSeed(final Item item) {
		for (FarmingNode node : nodes) {
			if (node.getSeed().getId() == item.getId()) {
				return node;
			}
		}
		return null;
	}

	/**
	 * Gets the nodes.
	 * @return The nodes.
	 */
	public FarmingNode[] getNodes() {
		return nodes;
	}

	/**
	 * Gets the name.
	 * @return The name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the objectIds.
	 * @return The objectIds.
	 */
	public int[] getWrapperIds() {
		return objectIds;
	}

	/**
	 * Gets the seedRequirement.
	 * @return The seedRequirement.
	 */
	public int getSeedRequirement() {
		return seedRequirement;
	}

	/**
	 * Gets the minimum yield.
	 * @return the yield.
	 */
	public int getMinimumYield() {
		return yieldHash & 0xFFFF;
	}

	/**
	 * Gets the maximum yield.
	 * @return the maximum yield.
	 */
	public int getMaximumYield() {
		return (yieldHash >> 16) & 0xFFFF;
	}

	/**
	 * Gets the node at the position.
	 * @param node the node.
	 * @return the node.
	 */
	public int getNodePosition(final FarmingNode node) {
		int nodesSize = nodes.length;
		for (int i = 0; i < nodesSize; i++) {
			if (nodes[i] == node) {
				return i;
			}
		}
		return 0;
	}

	/**
	 * Gets the farming patch by the object.
	 * @return {@code FarmingPatch} {@code Object}.
	 */
	public static FarmingPatch forObject(final int id) {
		return objectMap.get(id);
	}

}
