package plugin.skill.magic;

import core.ServerConstants;
import core.game.node.Node;
import core.game.node.entity.Entity;
import core.game.node.entity.combat.equipment.SpellType;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.SpellBookManager.SpellBook;
import core.game.node.entity.player.link.TeleportManager.TeleportType;
import core.game.node.entity.player.link.diary.DiaryType;
import core.game.node.item.Item;
import core.game.world.GameWorld;
import core.game.world.map.Location;
import core.plugin.InitializablePlugin;
import core.plugin.Plugin;
import core.tools.RandomFunction;

/**
 * Represents the plugin to handle all teleport spells in the modern book.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class ModernTeleportPlugin extends MagicSpell {

	/**
	 * Represents the location to teleport to.
	 */
	private Location location;

	/**
	 * Constructs a new {@code ModernTeleportPlugin} {@code Object}.
	 */
	public ModernTeleportPlugin() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code ModernTeleportPlugin.java} {@code Object}.
	 * @param level the level.
	 * @param experience the experience.
	 * @param location the location.
	 * @param items the items.
	 */
	public ModernTeleportPlugin(final int level, final double experience, final Location location, final Item... items) {
		super(SpellBook.MODERN, level, experience, null, null, null, items);
		this.location = location;
	}

	@Override
	public boolean cast(Entity entity, Node target) {
		if (entity.getLocks().isTeleportLocked() || !super.meetsRequirements(entity, true, false)) {
			return false;
		}
		if (entity.getTeleporter().send(location.transform(0, RandomFunction.random(3), 0), getSpellId() == 0 ? TeleportType.HOME : TeleportType.NORMAL)) {
			if (!super.meetsRequirements(entity, true, true)) {
				entity.getTeleporter().getCurrentTeleport().stop();
				return false;
			}
			// Use the teleport to Varrock spell
			if (entity.isPlayer() && location.equals(Location.create(3213, 3424, 0))) {
				entity.asPlayer().getAchievementDiaryManager().finishTask(entity.asPlayer(),DiaryType.VARROCK,1, 13);
			}
			//
			if (entity.isPlayer() && location.getX() == 2758 && location.getY() == 3478) {
				entity.asPlayer().getAchievementDiaryManager().finishTask(entity.asPlayer(), DiaryType.SEERS_VILLAGE, 1, 5);
			}
			// Use the teleport Lumbridge spell
			if (entity.isPlayer() && location.equals(Location.create(3221, 3219, 0))) {
				entity.asPlayer().getAchievementDiaryManager().finishTask(entity.asPlayer(), DiaryType.LUMBRIDGE, 2, 2);
			}
			entity.setAttribute("teleport:items", super.runes);
			entity.setAttribute("magic-delay", GameWorld.getTicks() + 5);
			return true;
		}
		return false;
	}

	@Override
	public Plugin<SpellType> newInstance(SpellType arg) throws Throwable {
		// home
		SpellBook.MODERN.register(0, new ModernTeleportPlugin(0, 0, ServerConstants.HOME_LOCATION));
		// varrock
		SpellBook.MODERN.register(15, new ModernTeleportPlugin(25, 35, Location.create(3213, 3424, 0), new Item(Runes.FIRE_RUNE.getId()), new Item(Runes.AIR_RUNE.getId(), 3), new Item(Runes.LAW_RUNE.getId(), 1)));
		// lumby
		SpellBook.MODERN.register(18, new ModernTeleportPlugin(31, 41, Location.create(3221, 3219, 0), new Item(Runes.EARTH_RUNE.getId()), new Item(Runes.AIR_RUNE.getId(), 3), new Item(Runes.LAW_RUNE.getId(), 1)));
		// fally
		SpellBook.MODERN.register(21, new ModernTeleportPlugin(37, 47, Location.create(2965, 3378, 0), new Item(Runes.WATER_RUNE.getId()), new Item(Runes.AIR_RUNE.getId(), 3), new Item(Runes.LAW_RUNE.getId(), 1)));
		// camelot
		SpellBook.MODERN.register(26, new ModernTeleportPlugin(45, 55.5, Location.create(2758, 3478, 0), new Item(Runes.AIR_RUNE.getId(), 5), new Item(Runes.LAW_RUNE.getId(), 1)));
		// house
		SpellBook.MODERN.register(23, new HouseTeleportPlugin(40, 50, new Item(Runes.LAW_RUNE.getId()), new Item(Runes.AIR_RUNE.getId(), 1), new Item(Runes.EARTH_RUNE.getId(), 1)));
		// ardougne
		SpellBook.MODERN.register(32, new ModernTeleportPlugin(51, 61, Location.create(2662, 3307, 0), new Item(Runes.WATER_RUNE.getId(), 2), new Item(Runes.LAW_RUNE.getId(), 2)));
		// watchtower
		SpellBook.MODERN.register(37, new ModernTeleportPlugin(58, 68, Location.create(2549, 3112, 0), new Item(Runes.EARTH_RUNE.getId(), 2), new Item(Runes.LAW_RUNE.getId(), 2)));
		// trollheim
		SpellBook.MODERN.register(44, new ModernTeleportPlugin(61, 68, Location.create(2891, 3678, 0), new Item(Runes.FIRE_RUNE.getId(), 2), new Item(Runes.LAW_RUNE.getId(), 2)));
		// ape atol
		SpellBook.MODERN.register(47, new ModernTeleportPlugin(64, 74, Location.create(2754, 2784, 0), new Item(Runes.FIRE_RUNE.getId(), 2), new Item(Runes.WATER_RUNE.getId(), 2), new Item(Runes.LAW_RUNE.getId(), 2), new Item(1963, 1)));
		return this;
	}

}
