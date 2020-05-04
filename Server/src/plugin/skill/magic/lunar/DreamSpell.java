package plugin.skill.magic.lunar;

import org.crandor.game.content.skill.Skills;
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

/**
 * Represents the dream magic spell.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class DreamSpell extends MagicSpell {

	/**
	 * Represents the starting animation.
	 */
	private static final Animation START = Animation.create(6295);

	/**
	 * Represents the dreaming animation.
	 */
	private static final Animation DREAMING = Animation.create(6296);

	/**
	 * Represents the end animation.
	 */
	private static final Animation END = Animation.create(6297);

	/**
	 * Represents the graphics of this spell.
	 */
	private static final Graphics GRAPHIC = new Graphics(1056);

	/**
	 * Constructs a new {@code CureOtherSpell} {@code Object}.
	 */
	public DreamSpell() {
		super(SpellBook.LUNAR, 79, 82, null, null, null, new Item[] { new Item(Runes.COSMIC_RUNE.getId(), 1), new Item(Runes.ASTRAL_RUNE.getId(), 2), new Item(Runes.BODY_RUNE.getId(), 5) });
	}

	@Override
	public Plugin<SpellType> newInstance(SpellType arg) throws Throwable {
		SpellBook.LUNAR.register(10, this);
		return this;
	}

	@Override
	public boolean cast(Entity entity, Node target) {
		final Player p = (Player) entity;
		if (p.getSkills().getLifepoints() == p.getSkills().getStaticLevel(Skills.HITPOINTS)) {
			p.getPacketDispatch().sendMessage("You already have full hitpoints.");
			return false;
		}
		if (!meetsRequirements(entity, true, true)) {
			return false;
		}
		p.animate(START);
		p.lock();
		GameWorld.Pulser.submit(new Pulse(4, p) {
			@Override
			public boolean pulse() {
				p.animate(DREAMING);
				p.graphics(GRAPHIC);
				p.unlock();
				return true;
			}

		});
		p.getPulseManager().run(new Pulse(18, p) {
			@Override
			public boolean pulse() {
				p.graphics(GRAPHIC);
				p.getSkills().heal(1);
				if (p.getSkills().getLifepoints() == p.getSkills().getStaticLevel(Skills.HITPOINTS)) {
					stop();
					return true;
				}
				return false;
			}

			@Override
			public void stop() {
				super.stop();
				p.graphics(new Graphics(-1));
				p.animate(END);
			}
		});
		return true;
	}

}
