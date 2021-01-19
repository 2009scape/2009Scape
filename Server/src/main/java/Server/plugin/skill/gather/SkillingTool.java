package plugin.skill.gather;

import plugin.skill.Skills;
import core.game.node.entity.player.Player;
import core.game.world.update.flag.context.Animation;

/**
 * Represents a skilling tool (such as knife, axe, needle, ...)
 * @author Emperor
 */
public enum SkillingTool {

	/**
	 * Represents a bronze axe (woodcutting).
	 */
	BRONZE_AXE(1351, 1, 0.05D, new Animation(879)),

	/**
	 * Represents an iron axe (woodcutting).
	 */
	IRON_AXE(1349, 1, 0.1D, new Animation(877)),

	/**
	 * Represents a steel axe (woodcutting).
	 */
	STEEL_AXE(1353, 6, 0.2D, new Animation(875)),

	/**
	 * Represents a black axe (woodcutting).
	 */
	BLACK_AXE(1361, 6, 0.25D, new Animation(873)),

	/**
	 * Represents a mithril axe (woodcutting).
	 */
	MITHRIL_AXE(1355, 21, 0.30D, new Animation(871)),

	/**
	 * Represents an adamant axe (woodcutting).
	 */
	ADAMANT_AXE(1357, 31, 0.45D, new Animation(869)),

	/**
	 * Represents a rune axe (woodcutting).
	 */
	RUNE_AXE(1359, 41, 0.65D, new Animation(867)),

	/**
	 * Represents a dragon axe (woodcutting).
	 */
	DRAGON_AXE(6739, 61, 0.85D, new Animation(2846)),

	/**
	 * Represents a bronze pickaxe (mining).
	 */
	BRONZE_PICKAXE(1265, 1, 0.05D, new Animation(625)),

	/**
	 * Represents an iron pickaxe (mining).
	 */
	IRON_PICKAXE(1267, 1, 0.1D, new Animation(626)),

	/**
	 * Represents a steel pickaxe (mining).
	 */
	STEEL_PICKAXE(1269, 6, 0.2D, new Animation(627)),

	/**
	 * Represents a mithril pickaxe (mining).
	 */
	MITHRIL_PICKAXE(1273, 21, 0.30D, new Animation(629)),

	/**
	 * Represents an adamant pickaxe (mining).
	 */
	ADAMANT_PICKAXE(1271, 31, 0.45D, new Animation(628)),

	/**
	 * Represents a rune pickaxe (mining).
	 */
	RUNE_PICKAXE(1275, 41, 0.65D, new Animation(624)),
	
	/**
	 * Represents a dragon pickaxe (mining).
	 */
	DRAGON_PICKAXE(14669, 61, 1.0D, new Animation(11171)),
	
	/**
	 * Represents the Inferno Adze (woodcutting)
	 */
	INFERNO_ADZE(13661, 61, 0.85D, new Animation(10251)),
	
	/**
	 * Represents the Inferno Adze (mining)
	 */
	INFERNO_ADZE2(13661, 41, 0.85D, new Animation(10222));

	/**
	 * The tool id.
	 */
	private final int id;

	/**
	 * The level required.
	 */
	private final int level;

	/**
	 * The ratio.
	 */
	private final double ratio;

	/**
	 * The animation.
	 */
	private final Animation animation;

	/**
	 * Constructs a new {@code SkillingTool} {@code Object}.
	 * @param id The tool item id.
	 * @param level The level required to use this.
	 * @param ratio The ratio.
	 * @param animation The animation.
	 */
	private SkillingTool(int id, int level, double ratio, Animation animation) {
		this.id = id;
		this.level = level;
		this.ratio = ratio;
		this.animation = animation;
	}

	/**
	 * Gets the tool by the item id.
	 * @param itemId The item id.
	 * @return The skilling tool, or {@code null} if the tool wasn't found.
	 */
	public static SkillingTool forId(int itemId) {
		for (SkillingTool tool : SkillingTool.values()) {
			if (tool.id == itemId) {
				return tool;
			}
		}
		return null;
	}

	/**
	 * Gets the hatchet used by the player.
	 * @param player The player.
	 * @return The hatchet.
	 */
	public static SkillingTool getHatchet(Player player) {
		SkillingTool tool = null;
		if (checkTool(player, Skills.WOODCUTTING, SkillingTool.DRAGON_AXE)) {
			tool = SkillingTool.DRAGON_AXE;
		} else if (checkTool(player, Skills.WOODCUTTING, SkillingTool.RUNE_AXE)) {
			tool = SkillingTool.RUNE_AXE;
		} else if (checkTool(player, Skills.WOODCUTTING, SkillingTool.ADAMANT_AXE)) {
			tool = SkillingTool.ADAMANT_AXE;
		} else if (checkTool(player, Skills.WOODCUTTING, SkillingTool.MITHRIL_AXE)) {
			tool = SkillingTool.MITHRIL_AXE;
		} else if (checkTool(player, Skills.WOODCUTTING, SkillingTool.BLACK_AXE)) {
			tool = SkillingTool.BLACK_AXE;
		} else if (checkTool(player, Skills.WOODCUTTING, SkillingTool.STEEL_AXE)) {
			tool = SkillingTool.STEEL_AXE;
		} else if (checkTool(player, Skills.WOODCUTTING, SkillingTool.IRON_AXE)) {
			tool = SkillingTool.IRON_AXE;
		} else if (checkTool(player, Skills.WOODCUTTING, SkillingTool.BRONZE_AXE)) {
			tool = SkillingTool.BRONZE_AXE;
		} else if (checkTool(player, Skills.WOODCUTTING, SkillingTool.INFERNO_ADZE)) {
			if(player.getSkills().getLevel(Skills.FIREMAKING) >= 92) {
				tool = SkillingTool.INFERNO_ADZE;
			}
		}
		return tool;
	}

	/**
	 * Gets the pickaxe used by the player.
	 * @param player The player.
	 * @return The hatchet.
	 */
	public static SkillingTool getPickaxe(Player player) {
		SkillingTool tool = null;
		if (checkTool(player, Skills.MINING, SkillingTool.INFERNO_ADZE2)) {
			if(player.getSkills().getLevel(Skills.FIREMAKING) >= 92) {
				tool = SkillingTool.INFERNO_ADZE2;
			}
		} else if (checkTool(player, Skills.MINING, SkillingTool.DRAGON_PICKAXE)) {
			tool = SkillingTool.DRAGON_PICKAXE;
		} else if (checkTool(player, Skills.MINING, SkillingTool.RUNE_PICKAXE)) {
			tool = SkillingTool.RUNE_PICKAXE;
		} else if (checkTool(player, Skills.MINING, SkillingTool.ADAMANT_PICKAXE)) {
			tool = SkillingTool.ADAMANT_PICKAXE;
		} else if (checkTool(player, Skills.MINING, SkillingTool.MITHRIL_PICKAXE)) {
			tool = SkillingTool.MITHRIL_PICKAXE;
		} else if (checkTool(player, Skills.MINING, SkillingTool.STEEL_PICKAXE)) {
			tool = SkillingTool.STEEL_PICKAXE;
		} else if (checkTool(player, Skills.MINING, SkillingTool.IRON_PICKAXE)) {
			tool = SkillingTool.IRON_PICKAXE;
		} else if (checkTool(player, Skills.MINING, SkillingTool.BRONZE_PICKAXE)) {
			tool = SkillingTool.BRONZE_PICKAXE;
		} 
		return tool;
	}

	/**
	 * Checks if the player has a tool and if he can use it.
	 * @param tool The tool.
	 * @return {@code True} if the tool is usable.
	 */
	public static boolean checkTool(Player player, int skillId, SkillingTool tool) {
		if (player.getSkills().getStaticLevel(skillId) < tool.getLevel()) {
			return false;
		}
		if (player.getEquipment().getNew(3).getId() == tool.getId()) {
			return true;
		}
		return player.getInventory().contains(tool.getId(), 1);
	}

	/**
	 * Gets the id.
	 * @return The id.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Gets the level.
	 * @return The level.
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * Gets the ratio.
	 * @return The ratio.
	 */
	public double getRatio() {
		return ratio;
	}

	/**
	 * Gets the animation.
	 * @return The animation.
	 */
	public Animation getAnimation() {
		return animation;
	}
}