package plugin.skill.hunter.bnet;

import core.game.node.entity.npc.NPC;
import core.game.node.entity.npc.drop.DropFrequency;
import core.game.node.entity.player.Player;
import core.game.node.item.ChanceItem;
import core.game.node.item.Item;
import core.game.world.update.flag.context.Graphics;

import java.util.ArrayList;
import java.util.List;

/**
 * A butterfly net type catch.
 * @author Vexia
 */
public enum BNetTypes {
	// Butterflies 
	RUBY_HARVEST(new BNetNode(new int[] { 5085 }, new int[] { 15, 80, 75 }, new double[] { 24, 300, 50 }, new Graphics[] { new Graphics(913), new Graphics(917) }, new ChanceItem(10020))), 
	SAPPHIRE_GLACIALIS(new BNetNode(new int[] { 5084, 7499 }, new int[] { 25, 85, 80 }, new double[] { 34, 400, 70 }, new Graphics[] { new Graphics(912), new Graphics(916) }, new ChanceItem(10018))), 
	SNOWRY_KNIGHT(new BNetNode(new int[] { 5083, 7498 }, new int[] { 35, 90, 85 }, new double[] { 44, 500, 100 }, new Graphics[] { new Graphics(911), new Graphics(915) }, new ChanceItem(10016))), 
	BLACK_WARLOCK(new BNetNode(new int[] { 5082 }, new int[] { 45, 95, 90 }, new double[] { 54, 650, 125 }, new Graphics[] { new Graphics(910), new Graphics(914) }, new ChanceItem(10014))), 
	// Implings ( Clearly these morons typed way too fast - wasn't sure if these names were anywhere else in the code) 
	BABY_IMPLING(new ImplingNode(new int[] { 1028, 6055 }, 17, 20, 18, new Item(11238), new ChanceItem(1755, 1, 1, DropFrequency.COMMON), new ChanceItem(1734, 1, 1, DropFrequency.COMMON), new ChanceItem(1733, 1, 1, DropFrequency.COMMON), new ChanceItem(946, 1, 1, DropFrequency.COMMON), new ChanceItem(1985, 1, 1, DropFrequency.COMMON), new ChanceItem(2347, 1, 1, DropFrequency.COMMON), new ChanceItem(1759, 1, 1, DropFrequency.COMMON), new ChanceItem(1927, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(319, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(2007, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(2007, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(1779, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(7170, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(401, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(1438, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(2355, 1, 1, DropFrequency.RARE), new ChanceItem(1607, 1, 1, DropFrequency.RARE), new ChanceItem(1743, 1, 1, DropFrequency.RARE), new ChanceItem(379, 1, 1, DropFrequency.RARE), new ChanceItem(1761, 1, 1, DropFrequency.RARE))), 
	YOUNG_IMPLING(new ImplingNode(new int[] { 1029, 6056 }, 22, 22, 25, new Item(11240), new ChanceItem(1539, 5, 5, DropFrequency.COMMON), new ChanceItem(1901, 1, 1, DropFrequency.COMMON), new ChanceItem(7936, 1, 1, DropFrequency.COMMON), new ChanceItem(361, 1, 1, DropFrequency.COMMON), new ChanceItem(1523, 1, 1, DropFrequency.COMMON), new ChanceItem(453, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(1777, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(2293, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(1353, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(2359, 1, 1, DropFrequency.RARE))), 
	GOURMET_IMPLING(new ImplingNode(new int[] { 1030, 6057 }, 28, 24, 22, new Item(11242), new ChanceItem(365, 1, 1, DropFrequency.COMMON), new ChanceItem(361, 1, 1, DropFrequency.COMMON), new ChanceItem(2011, 1, 1, DropFrequency.COMMON), new ChanceItem(1897, 1, 1, DropFrequency.COMMON), new ChanceItem(2327, 1, 1, DropFrequency.COMMON), new ChanceItem(5004, 1, 1, DropFrequency.COMMON), new ChanceItem(2007, 1, 1, DropFrequency.COMMON), new ChanceItem(5970, 1, 1, DropFrequency.COMMON), new ChanceItem(365, 4, 4, DropFrequency.UNCOMMON), new ChanceItem(3145, 2, 2, DropFrequency.RARE), new ChanceItem(7178, 1, 5, DropFrequency.UNCOMMON), new ChanceItem(5755, 1, 1, DropFrequency.RARE), new ChanceItem(386, 3, 3, DropFrequency.UNCOMMON), new ChanceItem(5406, 1, 1, DropFrequency.RARE), new ChanceItem(10136, 1, 1, DropFrequency.RARE), new ChanceItem(1883, 1, 1, DropFrequency.UNCOMMON))), 
	EARTH_IMPLING(new ImplingNode(new int[] { 1031, 6058 }, 36, 27, 25, new Item(11244), new ChanceItem(6033, 6, 6, DropFrequency.COMMON), new ChanceItem(1440, 1, 1, DropFrequency.COMMON), new ChanceItem(5535, 1, 1, DropFrequency.COMMON), new ChanceItem(557, 32, 32, DropFrequency.COMMON), new ChanceItem(1442, 1, 1, DropFrequency.COMMON), new ChanceItem(1784, 4, 4, DropFrequency.UNCOMMON), new ChanceItem(447, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(447, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(1606, 2, 2, DropFrequency.RARE))), 
	ESSENCE_IMPLING(new ImplingNode(new int[] { 1032, 6059 }, 42, 29, 27, new Item(11246), new ChanceItem(7937, 20, 20, DropFrequency.COMMON), new ChanceItem(555, 30, 30, DropFrequency.COMMON), new ChanceItem(556, 30, 30, DropFrequency.COMMON), new ChanceItem(558, 25, 25, DropFrequency.COMMON), new ChanceItem(559, 28, 28, DropFrequency.COMMON), new ChanceItem(562, 4, 4, DropFrequency.COMMON), new ChanceItem(1448, 1, 1, DropFrequency.COMMON), new ChanceItem(564, 4, 4, DropFrequency.UNCOMMON), new ChanceItem(565, 7, 7, DropFrequency.RARE), new ChanceItem(563, 13, 13, DropFrequency.RARE), new ChanceItem(566, 11, 11, DropFrequency.RARE))), 
	ECLECTIC_IMPLING(new ImplingNode(new int[] { 1033, 6060 }, 50, 32, 30, new Item(11248), new ChanceItem(1273, 1, 1, DropFrequency.COMMON), new ChanceItem(1199, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(2493, 1, 1, DropFrequency.RARE), new ChanceItem(10083, 1, 1, DropFrequency.RARE), new ChanceItem(1213, 1, 1, DropFrequency.RARE), new ChanceItem(1391, 1, 1, DropFrequency.VERY_RARE), new ChanceItem(5970, 1, 1, DropFrequency.COMMON), new ChanceItem(231, 1, 1, DropFrequency.COMMON), new ChanceItem(556, 30, 57, DropFrequency.COMMON), new ChanceItem(8779, 4, 4, DropFrequency.COMMON), new ChanceItem(4527, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(444, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(2358, 5, 5, DropFrequency.UNCOMMON), new ChanceItem(7937, 20, 35, DropFrequency.UNCOMMON), new ChanceItem(237, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(450, 10, 10, DropFrequency.RARE), new ChanceItem(5760, 2, 2, DropFrequency.RARE), new ChanceItem(7208, 1, 1, DropFrequency.RARE), new ChanceItem(5321, 3, 3, DropFrequency.RARE), new ChanceItem(1601, 1, 1, DropFrequency.VERY_RARE))), 
	NATURE_IMPLING(new ImplingNode(new int[] { 1034, 6061 }, 58, 36, 34, new Item(11250), new ChanceItem(5303, 1, 1, DropFrequency.VERY_RARE), new ChanceItem(270, 2, 2, DropFrequency.VERY_RARE), new ChanceItem(5295, 1, 1, DropFrequency.RARE), new ChanceItem(5304, 1, 1, DropFrequency.RARE), new ChanceItem(5298, 5, 5, DropFrequency.UNCOMMON), new ChanceItem(5299, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(5297, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(5974, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(3000, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(5285, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(5286, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(5100, 1, 1, DropFrequency.COMMON), new ChanceItem(5104, 1, 1, DropFrequency.COMMON), new ChanceItem(5281, 1, 1, DropFrequency.COMMON), new ChanceItem(5294, 1, 1, DropFrequency.COMMON), new ChanceItem(6016, 1, 1, DropFrequency.COMMON), new ChanceItem(1513, 1, 1, DropFrequency.COMMON), new ChanceItem(254, 4, 4, DropFrequency.COMMON), new ChanceItem(5313, 1, 1, DropFrequency.UNCOMMON))), 
	MAGPIE_IMPLING(new ImplingNode(new int[] { 1035, 6062 }, 65, 46, 44, new Item(11252), 500, new ChanceItem(1682, 3, 3, DropFrequency.COMMON), new ChanceItem(1732, 3, 3, DropFrequency.COMMON), new ChanceItem(2569, 3, 3, DropFrequency.COMMON), new ChanceItem(3391, 1, 1, DropFrequency.COMMON), new ChanceItem(1347, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(2571, 4, 4, DropFrequency.UNCOMMON), new ChanceItem(4097, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(4095, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(1215, 1, 1, DropFrequency.RARE), new ChanceItem(1185, 1, 1, DropFrequency.RARE), new ChanceItem(5541, 1, 1, DropFrequency.COMMON), new ChanceItem(1748, 6, 6, DropFrequency.COMMON), new ChanceItem(2364, 2, 2, DropFrequency.UNCOMMON), new ChanceItem(1602, 4, 4, DropFrequency.RARE), new ChanceItem(5287, 1, 1, DropFrequency.RARE), new ChanceItem(985, 1, 1, DropFrequency.RARE), new ChanceItem(987, 1, 1, DropFrequency.RARE), new ChanceItem(993, 1, 1, DropFrequency.VERY_RARE), new ChanceItem(5300, 1, 1, DropFrequency.VERY_RARE))), 
	NINJA_IMPLING(new ImplingNode(new int[] { 6053, 6063 }, 74, 52, 50, new Item(11254), 2000, new ChanceItem(4097, 1, 1, DropFrequency.COMMON), new ChanceItem(1113, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(3385, 1, 1, DropFrequency.COMMON), new ChanceItem(1215, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(811, 70, 70, DropFrequency.UNCOMMON), new ChanceItem(1333, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(1347, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(9342, 2, 2, DropFrequency.UNCOMMON), new ChanceItem(9194, 4, 4, DropFrequency.RARE), new ChanceItem(140, 4, 4, DropFrequency.COMMON), new ChanceItem(6155, 3, 3, DropFrequency.UNCOMMON), new ChanceItem(1748, 10, 16, DropFrequency.COMMON))), 
	// Pirate should be here
	DRAGON_IMPLING(new ImplingNode(new int[] { 6054, 6064 }, 83, 300, 65, new Item(11256), 3000, new ChanceItem(1705, 2, 3, DropFrequency.RARE), new ChanceItem(4093, 1, 1, DropFrequency.VERY_RARE), new ChanceItem(1684, 2, 3, DropFrequency.VERY_RARE), new ChanceItem(11212, 100, 500, DropFrequency.COMMON), new ChanceItem(9341, 3, 40, DropFrequency.COMMON), new ChanceItem(1305, 1, 1, DropFrequency.COMMON), new ChanceItem(5699, 3, 3, DropFrequency.UNCOMMON), new ChanceItem(11230, 105, 350, DropFrequency.UNCOMMON), new ChanceItem(11232, 105, 350, DropFrequency.COMMON), new ChanceItem(11237, 100, 500, DropFrequency.COMMON), new ChanceItem(9193, 10, 49, DropFrequency.COMMON), new ChanceItem(535, 111, 297, DropFrequency.COMMON), new ChanceItem(5316, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(537, 52, 99, DropFrequency.UNCOMMON), new ChanceItem(1616, 3, 6, DropFrequency.UNCOMMON), new ChanceItem(5300, 6, 6, DropFrequency.RARE), new ChanceItem(7219, 5, 15, DropFrequency.RARE)));
	
	
	/**
	 * The implings.
	 */
	private static final List<ImplingNode> IMPLINGS = new ArrayList<>();

	/**
	 * The node.
	 */
	private final BNetNode node;

	/**
	 * Constructs a new {@code BNetTypes} {@code Object}.
	 * @param node the node.
	 */
	private BNetTypes(BNetNode node) {
		this.node = node;
	}

	/**
	 * Catches a net npc.
	 * @param player the player.
	 * @param npc the npc.
	 */
	public void handle(Player player, NPC npc) {
		player.getPulseManager().run(new BNetPulse(player, npc, node));
	}

	/**
	 * Gets an impling.
	 * @param player the player.
	 * @return the imp.
	 */
	public static ImplingNode getImpling(Player player) {
		for (ImplingNode imp : IMPLINGS) {
			if (player.getInventory().containsItem(imp.getReward())) {
				return imp;
			}
		}
		return null;
	}

	/**
	 * Gets a net type for the npc.
	 * @param npc the npc.
	 * @return {@code BNetTypes} type.
	 */
	public static BNetTypes forNpc(NPC npc) {
		for (BNetTypes type : BNetTypes.values()) {
			for (int id : type.getNode().getNpcs()) {
				if (id == npc.getId()) {
					return type;
				}
			}
		}
		return null;
	}

	/**
	 * Gets the BNetNode by the item.
	 * @param item the item.
	 * @return the instance.
	 */
	public static BNetNode forItem(Item item) {
		for (BNetTypes type : values()) {
			if (type.getNode().getReward().getId() == item.getId()) {
				return type.getNode();
			}
		}
		return null;
	}

	/**
	 * Gets the node.
	 * @return The node.
	 */
	public BNetNode getNode() {
		return node;
	}

	/**
	 * Gets the implings.
	 * @return The implings.
	 */
	public static List<ImplingNode> getImplings() {
		return IMPLINGS;
	}

	/**
	 * static-modifier.
	 */
	static {
		for (BNetTypes type : values()) {
			BNetNode node = type.getNode();
			if (node instanceof ImplingNode) {
				IMPLINGS.add((ImplingNode) node);
			}
		}
	}

}
