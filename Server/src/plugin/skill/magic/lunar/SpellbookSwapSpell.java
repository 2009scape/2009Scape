package plugin.skill.magic.lunar;

import org.crandor.game.component.Component;
import org.crandor.game.content.skill.free.magic.MagicSpell;
import org.crandor.game.content.skill.free.magic.Runes;
import org.crandor.game.node.Node;
import org.crandor.game.node.entity.Entity;
import org.crandor.game.node.entity.combat.equipment.SpellType;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.entity.player.link.SpellBookManager.SpellBook;
import org.crandor.game.node.item.Item;
import org.crandor.game.system.task.Pulse;
import org.crandor.game.world.GameWorld;
import org.crandor.game.world.update.flag.context.Animation;
import org.crandor.game.world.update.flag.context.Graphics;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.plugin.Plugin;
import org.crandor.tools.RandomFunction;

/**
 * The spellbook swap spell.
 * @author 'Vexia
 */
@InitializablePlugin
public class SpellbookSwapSpell extends MagicSpell {

	/**
	 * Represents the animation of this spell.
	 */
	private final Animation ANIMATION = new Animation(6299);

	/**
	 * Represents the graphics of this spell.
	 */
	private final Graphics GRAPHIC = new Graphics(1062);

	/**
	 * Constructs a new {@code SpellbookSwapSpell} {@code Object}.
	 */
	public SpellbookSwapSpell() {
		super(SpellBook.LUNAR, 96, 130, null, null, null, new Item[] { new Item(Runes.LAW_RUNE.getId(), 1), new Item(Runes.COSMIC_RUNE.getId(), 2), new Item(Runes.ASTRAL_RUNE.getId(), 3) });
	}

	@Override
	public Plugin<SpellType> newInstance(SpellType arg) throws Throwable {
		SpellBook.LUNAR.register(12, this);
		return this;
	}

	@Override
	public boolean cast(Entity entity, Node target) {
		final Player player = (Player) entity;
		if (!super.meetsRequirements(player, true, true)) {
			return false;
		}
		player.lock(9);
		player.animate(ANIMATION);
		player.graphics(GRAPHIC);
		player.getDialogueInterpreter().open(3264731);
		final int id = RandomFunction.random(1, 500000);
		player.setAttribute("spell:swap", id);
		GameWorld.Pulser.submit(new Pulse(20, player) {
			@Override
			public boolean pulse() {
				if (player.getAttribute("spell:swap", 0) == id) {
					removeTemporarySpell(player);
				}
				return true;
			}

		});
		return true;
	}

	/**
	 * Method used to remove the temp spell swap.
	 * @param player the player.
	 */
	public static void removeTemporarySpell(final Player player) {
		player.removeAttribute("spell:swap");
		player.getSpellBookManager().setSpellBook(SpellBook.LUNAR);
		player.getInterfaceManager().openTab(new Component(SpellBook.LUNAR.getInterfaceId()));
	}
}
