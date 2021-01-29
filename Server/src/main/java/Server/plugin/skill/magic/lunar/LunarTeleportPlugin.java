package plugin.skill.magic.lunar;

import core.game.component.Component;
import plugin.skill.magic.MagicSpell;
import plugin.skill.magic.Runes;
import core.game.node.Node;
import core.game.node.entity.Entity;
import core.game.node.entity.combat.equipment.SpellType;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.SpellBookManager.SpellBook;
import core.game.node.entity.player.link.TeleportManager.TeleportType;
import core.game.node.item.Item;
import core.game.world.map.Location;
import core.game.world.map.RegionManager;
import core.plugin.InitializablePlugin;
import core.plugin.Plugin;

/**
 * Represents the plugin to handle all teleport spells.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class LunarTeleportPlugin extends MagicSpell {

	/**
	 * Represents the component used for group spells.
	 */
	private static final Component COMPONENT = new Component(326);

	/**
	 * Represents the location to teleport to.
	 */
	private Location location;

	/**
	 * Represents if it's a group teleport.
	 */
	private boolean group;

	/**
	 * Constructs a new {@code LunarTeleportPlugin} {@code Object}.
	 */
	public LunarTeleportPlugin() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code LunarTeleportPlugin} {@code Object}.
	 * @param level the level.
	 * @param experience the experience.
	 * @param items the items.
	 */
	public LunarTeleportPlugin(final int level, final double experience, final Location location, boolean group, final Item... items) {
		super(SpellBook.LUNAR, level, experience, null, null, null, items);
		this.location = location;
		this.group = group;
	}

	@Override
	public boolean cast(Entity entity, Node target) {
		final Player player = (Player) entity;
		if (player.getLocks().isTeleportLocked() || !meetsRequirements(player, true, false)) {
			return false;
		}
		player.getTeleporter().send(location.transform(0, 0, 0), getSpellId() == 16 ? TeleportType.HOME : TeleportType.LUNAR);
		if (!meetsRequirements(player, true, true)) {
			return false;
		}
		entity.setAttribute("teleport:items", super.runes);
		if (group) {
			String destination = "Moonclan Island";
			switch (getSpellId()) {
			case 34:
				destination = "Waterbirth Island";
				break;
			case 35:
				destination = "Barbarian outpost";
				break;
			case 36:
				destination = "Port Khazard";
				break;
			case 37:
				destination = "Fishing guild";
				break;
			case 38:
				destination = "Catherby";
				break;
			case 39:
				destination = "Ice plateau";
				break;
			}
			for (Player p : RegionManager.getLocalPlayers(player, 1)) {
				if (p == player) {
					continue;
				}
				if (!p.isActive() || p.getLocks().isTeleportLocked()) {
					player.getPacketDispatch().sendMessage("The other player is currently busy.");
					continue;
				}
				if (!p.getSettings().isAcceptAid()) {
					player.getPacketDispatch().sendMessage("The player is not accepting any aid.");
					continue;
				}
				visualize(entity, p);
				p.setAttribute("t-o_location", location);
				p.getPacketDispatch().sendString(player.getUsername(), 326, 1);
				p.getPacketDispatch().sendString(destination, 326, 3);
				p.getInterfaceManager().open(COMPONENT);
			}
		}
		return true;
	}

	@Override
	public Plugin<SpellType> newInstance(SpellType arg) throws Throwable {
		// home
		SpellBook.LUNAR.register(16, new LunarTeleportPlugin(0, 0, Location.create(2100, 3914, 0), false));
		// moonclan teleport
		SpellBook.LUNAR.register(20, new LunarTeleportPlugin(69, 66, Location.create(2111, 3916, 0), false, new Item(Runes.LAW_RUNE.getId(), 1), new Item(Runes.ASTRAL_RUNE.getId(), 2), new Item(Runes.EARTH_RUNE.getId(), 2)));
		// moonclan group teleport
		SpellBook.LUNAR.register(33, new LunarTeleportPlugin(70, 67, Location.create(2111, 3916, 0), true, new Item(Runes.LAW_RUNE.getId(), 1), new Item(Runes.ASTRAL_RUNE.getId(), 2), new Item(Runes.EARTH_RUNE.getId(), 4)));
		// ourina teleport
		SpellBook.LUNAR.register(31, new LunarTeleportPlugin(71, 69, Location.create(2469, 3247, 0), false, new Item(Runes.LAW_RUNE.getId(), 1), new Item(Runes.ASTRAL_RUNE.getId(), 2), new Item(Runes.EARTH_RUNE.getId(), 6)));
		// waterbirth teleport
		SpellBook.LUNAR.register(24, new LunarTeleportPlugin(72, 71, Location.create(2527, 3739, 0), false, new Item(Runes.LAW_RUNE.getId(), 1), new Item(Runes.ASTRAL_RUNE.getId(), 2), new Item(Runes.WATER_RUNE.getId(), 1)));
		// waterbirth group teleport
		SpellBook.LUNAR.register(34, new LunarTeleportPlugin(73, 72, Location.create(2527, 3739, 0), true, new Item(Runes.LAW_RUNE.getId(), 1), new Item(Runes.ASTRAL_RUNE.getId(), 2), new Item(Runes.WATER_RUNE.getId(), 5)));
		// barbarian post teleport.
		SpellBook.LUNAR.register(0, new LunarTeleportPlugin(75, 76, Location.create(2544, 3572, 0), false, new Item(Runes.LAW_RUNE.getId(), 2), new Item(Runes.ASTRAL_RUNE.getId(), 2), new Item(Runes.FIRE_RUNE.getId(), 3)));
		// barbarian group teleport
		SpellBook.LUNAR.register(35, new LunarTeleportPlugin(77, 77, Location.create(2544, 3572, 0), true, new Item(Runes.LAW_RUNE.getId(), 2), new Item(Runes.ASTRAL_RUNE.getId(), 2), new Item(Runes.FIRE_RUNE.getId(), 6)));
		// khzard teleport
		SpellBook.LUNAR.register(18, new LunarTeleportPlugin(78, 80, Location.create(2656, 3157, 0), false, new Item(Runes.LAW_RUNE.getId(), 2), new Item(Runes.ASTRAL_RUNE.getId(), 2), new Item(Runes.WATER_RUNE.getId(), 4)));
		// khzard group teleport
		SpellBook.LUNAR.register(36, new LunarTeleportPlugin(79, 81, Location.create(2656, 3157, 0), true, new Item(Runes.LAW_RUNE.getId(), 2), new Item(Runes.ASTRAL_RUNE.getId(), 2), new Item(Runes.WATER_RUNE.getId(), 8)));
		// fishing guild teleport
		SpellBook.LUNAR.register(17, new LunarTeleportPlugin(85, 89, Location.create(2611, 3393, 0), false, new Item(Runes.LAW_RUNE.getId(), 3), new Item(Runes.ASTRAL_RUNE.getId(), 3), new Item(Runes.WATER_RUNE.getId(), 10)));
		// fishing guild group teleport
		SpellBook.LUNAR.register(37, new LunarTeleportPlugin(86, 90, Location.create(2611, 3393, 0), true, new Item(Runes.LAW_RUNE.getId(), 3), new Item(Runes.ASTRAL_RUNE.getId(), 3), new Item(Runes.WATER_RUNE.getId(), 14)));
		// catherby teleport
		SpellBook.LUNAR.register(21, new LunarTeleportPlugin(87, 92, Location.create(2804, 3433, 0), false, new Item(Runes.LAW_RUNE.getId(), 3), new Item(Runes.ASTRAL_RUNE.getId(), 3), new Item(Runes.WATER_RUNE.getId(), 10)));
		// catherby group teleport
		SpellBook.LUNAR.register(38, new LunarTeleportPlugin(88, 93, Location.create(2804, 3433, 0), true, new Item(Runes.LAW_RUNE.getId(), 3), new Item(Runes.ASTRAL_RUNE.getId(), 3), new Item(Runes.WATER_RUNE.getId(), 12)));
		// ice plateua teleport
		SpellBook.LUNAR.register(28, new LunarTeleportPlugin(89, 96, Location.create(2972, 3873, 0), false, new Item(Runes.LAW_RUNE.getId(), 3), new Item(Runes.ASTRAL_RUNE.getId(), 3), new Item(Runes.WATER_RUNE.getId(), 8)));
		// ice plateua group teleport
		SpellBook.LUNAR.register(39, new LunarTeleportPlugin(90, 99, Location.create(2972, 3873, 0), true, new Item(Runes.LAW_RUNE.getId(), 3), new Item(Runes.ASTRAL_RUNE.getId(), 3), new Item(Runes.WATER_RUNE.getId(), 16)));
		return this;
	}

}
