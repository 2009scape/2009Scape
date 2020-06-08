/*
package plugin.skill.magic.lunar;

import plugin.consumable.Consumables;
import plugin.consumable.Drink;

import plugin.skill.magic.MagicSpell;
import plugin.skill.magic.Runes;
import core.game.node.Node;
import core.game.node.entity.Entity;
import core.game.node.entity.combat.equipment.SpellType;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.ai.AIPlayer;
import core.game.node.entity.player.link.SpellBookManager.SpellBook;
import core.game.node.item.Item;
import core.game.world.map.RegionManager;
import core.game.world.update.flag.context.Animation;
import core.game.world.update.flag.context.Graphics;
import core.plugin.InitializablePlugin;
import core.plugin.Plugin;

import java.util.List;

*/
/**
 * The stat boost spell.
 * @author 'Vexia
 * @note emp do this.
 *//*

@InitializablePlugin
public final class StatBoostSpell extends MagicSpell {

	*/
/**
	 * Represents the animation of this spell.
	 *//*

	private static final Animation ANIMATION = new Animation(4413);

	*/
/**
	 * Represents the graphics.
	 *//*

	private static final Graphics GRAPHICS = new Graphics(733, 130);

	*/
/**
	 * The vial item id.
	 *//*

	public static final int VIAL = 229;

	*/
/**
	 * Constructs a new {@code StatRestoreSpell} {@code Object}.
	 *//*

	public StatBoostSpell() {
		super(SpellBook.LUNAR, 84, 88, null, null, null, new Item[] { new Item(Runes.ASTRAL_RUNE.getId(), 3), new Item(Runes.EARTH_RUNE.getId(), 12), new Item(Runes.WATER_RUNE.getId(), 10) });
	}

	@Override
	public Plugin<SpellType> newInstance(SpellType arg) throws Throwable {
		SpellBook.LUNAR.register(26, this);
		return this;
	}

	@Override
	public boolean cast(Entity entity, Node target) {
		final Player player = ((Player) entity);
		Item item = ((Item) target);
		final Drink drink = Consumables.getDrinkByItemID(item.getId());
		player.getInterfaceManager().setViewedTab(6);
		if (drink == null || !(drink instanceof Potion)) {
			player.getPacketDispatch().sendMessage("For use of this spell use only one a potion.");
			return false;
		}
		if (!item.getDefinition().isTradeable() || item.getName().toLowerCase().contains("restore") || item.getName().toLowerCase().contains("zamorak") || item.getName().toLowerCase().contains("saradomin") || item.getName().toLowerCase().contains("combat")) {
			player.getPacketDispatch().sendMessage("You can't cast this spell on that item.");
			return false;
		}
		final Potion potion = (Potion) drink;
		List<Player> pl = RegionManager.getLocalPlayers(player, 1);
		int plSize = pl.size() - 1;
		int doses = potion.getDose(item);
		if (plSize > doses) {
			player.getPacketDispatch().sendMessage("You don't have enough doses.");
			return false;
		}
		if (doses > plSize) {
			doses = plSize;
		}
		if (pl.size() == 0) {
			return false;
		}
		if (!super.meetsRequirements(player, true, false)) {
			return false;
		}
		int size = 1;
		for (Player players : pl) {
			Player o = (Player) players;
			if (!o.isActive() || o.getLocks().isInteractionLocked() || o == player) {
				continue;
			}
			if (!o.getSettings().isAcceptAid() && !(o instanceof AIPlayer)) {
				continue;
			}
			o.graphics(GRAPHICS);
			potion.effect(o, item);
			size++;
		}
		if (size == 1) {
			player.getPacketDispatch().sendMessage("There is nobody around that has accept aid on to share the potion with you.");
			return false;
		}
		super.meetsRequirements(player, true, true);
		potion.effect(player, item);
		potion.message(player, item, player.getSkills().getLifepoints());
		player.animate(ANIMATION);
		player.graphics(GRAPHICS);
		potion.remove(player, item, size - 1, true);
		return true;
	}
}
*/
