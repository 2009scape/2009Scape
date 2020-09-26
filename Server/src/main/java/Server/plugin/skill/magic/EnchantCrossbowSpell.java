package plugin.skill.magic;

import core.game.component.Component;
import core.game.node.Node;
import core.game.node.entity.Entity;
import core.game.node.entity.combat.equipment.SpellType;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.SpellBookManager.SpellBook;
import core.plugin.InitializablePlugin;
import core.plugin.Plugin;

/**
 * Represents the enchant crossbow spell.
 * @author 'Vexia.
 * @version 1.0
 */
@InitializablePlugin
public final class EnchantCrossbowSpell extends MagicSpell {

	/**
	 * Constructs a new {@code EnchantCrossbowSpell} {@code Object}.
	 */
	public EnchantCrossbowSpell() {
		super(SpellBook.MODERN, 4, 0, null, null, null, null);
	}

	@Override
	public Plugin<SpellType> newInstance(SpellType arg) throws Throwable {
		SpellBook.MODERN.register(3, this);
		return null;
	}

	@Override
	public boolean cast(Entity entity, Node target) {
		final Player player = ((Player) entity);
		player.getInterfaceManager().open(new Component(432));
		int[][] data = new int[][] { { 17, 879 }, { 21, 9335 }, { 25, 880 }, { 28, 9336 }, { 31, 9337 }, { 34, 9338 }, { 37, 9339 }, { 40, 9340 }, { 43, 9341 }, { 46, 9342 } };
		for (int[] datum : data) {
			player.getPacketDispatch().sendItemZoomOnInterface(datum[1], 10, 432, datum[0]);
		}
		return true;
	}
}
