package plugin.skill.runecrafting;

import core.cache.def.impl.VarbitDefinition;
import core.cache.def.impl.ItemDefinition;
import core.cache.def.impl.ObjectDefinition;
import core.game.node.entity.player.Player;
import core.game.node.item.Item;
import core.plugin.Plugin;

/**
 * Handles a tiara equipment.
 * @author Vexia
 */
public final class TiaraPlugin implements Plugin<Object> {

	/**
	 * The config id.
	 */
	private static final int CONFIG = 491;

	/**
	 * The tiara ids.
	 */
	private static final int[] IDS = new int[] { 9765, 9766, 14831, 14833, 14835, 14839, 14840, 5527, 5529, 5531, 5533, 5535, 5537, 5539, 5541, 5543, 5545, 5547, 5551, 5549 };

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		for (int i : IDS) {
			ItemDefinition.forId(i).getHandlers().put("equipment", this);
		}
		return this;
	}

	@Override
	public Object fireEvent(String identifier, Object... args) {
		final Player player = (Player) args[0];
		final Item item = (Item) args[1];
		switch (identifier) {
		case "equip":
			if (item.getName().contains("cape")) {
				player.getConfigManager().set(CONFIG, 6143);
				break;
			}
			MysteriousRuin ruin = MysteriousRuin.forTalisman(Tiara.forItem(item).getTalisman());
			player.getConfigManager().set(CONFIG, 1 << VarbitDefinition.forObjectID(ObjectDefinition.forId(ruin.getObject()[0]).getVarbitID()).getBitShift(), true);
			break;
		case "unequip":
			final Item other = args.length == 2 ? null : (Item) args[2];
			if (other != null) {
				if (other.getName().toLowerCase().contains("cape")) {
					player.getConfigManager().set(CONFIG, 6143);
					break;
				}
				Tiara tiara = Tiara.forItem(other);
				if (tiara != null) {
					MysteriousRuin r = MysteriousRuin.forTalisman(tiara.getTalisman());
					player.getConfigManager().set(CONFIG, 1 << VarbitDefinition.forObjectID(ObjectDefinition.forId(r.getObject()[0]).getVarbitID()).getBitShift(), true);
					break;
				}
			}
			player.getConfigManager().set(CONFIG, 0);
			break;
		}
		return true;
	}

}
