package plugin.skill.farming.tool;

import core.game.content.ItemNames;
import core.game.node.item.Item;

/**
 * Represents the a patch tool that can be used with a patch.
 * @author 'Vexia
 */
public enum PatchTool {
	RAKE(new Item[] { new Item(5341) }, new RakePulse()), 
	SPADE(new Item[] { new Item(952) }, new SpadePulse()), 
	WATERING_CAN(new Item[] { new Item(5340), new Item(5339), new Item(5338), new Item(5337), new Item(5336), new Item(5335), new Item(5334), new Item(5333), new Item(5331) }, new WateringPulse()), 
	PATCH_CURE(new Item[] { new Item(6036) }, new PlantCurePulse()), 
	PLANT_POT(new Item[] { new Item(5350) }, new PlantPotPulse()), 
	SECATEUR(new Item[] { new Item(5329), new Item(ItemNames.MAGIC_SECATEURS_7409) }, new SecateurPulse());

	/**
	 * Represents the tools.
	 */
	private final Item[] tools;

	/**
	 * Represents the tool interacting pulse.
	 */
	private final ToolAction action;

	/**
	 * Constructs a new {@code PatchTool} {@code Object}.
	 * @param tools the tools.
	 * @param action the pulse.
	 */
	PatchTool(Item[] tools, ToolAction action) {
		this.tools = tools;
		this.action = action;
	}

	/**
	 * Gets the patch tool by the item.
	 * @param item the item.
	 * @return {@code True} if so.
	 */
	public static PatchTool forItem(final Item item) {
		for (PatchTool tool : PatchTool.values()) {
			for (Item i : tool.getTools()) {
				if (i.getId() == item.getId()) {
					return tool;
				}
			}
		}
		return null;
	}

	/**
	 * Gets the tools.
	 * @return The tools.
	 */
	public Item[] getTools() {
		return tools;
	}

	/**
	 * Gets the pulse.
	 * @return The pulse.
	 */
	public ToolAction getAction() {
		return action;
	}

}
