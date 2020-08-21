package plugin.zone;

import java.util.ArrayList;
import java.util.List;

import core.cache.def.impl.ItemDefinition;
import plugin.quest.tutorials.tutorialisland.TutorialSession;
import core.game.node.entity.Entity;
import core.game.node.entity.combat.ImpactHandler.HitsplatType;
import core.game.node.entity.player.Player;
import core.game.node.item.Item;
import core.game.system.task.Pulse;
import core.game.world.GameWorld;
import core.game.world.map.zone.MapZone;
import core.game.world.map.zone.ZoneBorders;
import core.game.world.map.zone.ZoneBuilder;
import core.game.world.update.flag.context.Animation;
import core.plugin.Plugin;
import core.plugin.InitializablePlugin;
import core.tools.RandomFunction;

/**
 * The desert zone map.
 * @author Emperor
 * @author 'Vexia
 * @version 2.0
 */
@InitializablePlugin
public final class DesertZone extends MapZone implements Plugin<Object> {

	/**
	 * Represents the hydrateable waterskin items.
	 */
	private static final Item[] WATER_SKINS = new Item[] { new Item(1823), new Item(1825), new Item(1827), new Item(1829) };

	/**
	 * Represents data of water vessils to dry up.
	 */
	private static final int[][] VESSILS = new int[][] { { 1937, 1935 }, { 1929, 1925 }, { 1921, 1923 }, { 227, 229 } };

	/**
	 * Represents the animation of drinking water.
	 */
	private static final Animation ANIMATION = new Animation(829);

	/**
	 * The players list.
	 */
	private static final List<Player> PLAYERS = new ArrayList<>();

	/**
	 * The water draining pulse.
	 */
	private static Pulse pulse = new Pulse(3) {
		@Override
		public boolean pulse() {
			for (Player player : PLAYERS) {
				if (player.getInterfaceManager().isOpened() || player.getInterfaceManager().hasChatbox() || player.getLocks().isMovementLocked()) {
					continue;
				}
				if (player.getAttribute("desert-delay", -1) < GameWorld.getTicks()) {
					effect(player);
				}
			}
			return PLAYERS.isEmpty();
		}
	};

	/**
	 * Method used to handle the desert effect on the <code>Player</code>.
	 * @param p the <code>Player</code>.
	 */
	private static void effect(Player p) {
		p.setAttribute("desert-delay", GameWorld.getTicks() + getDelay(p));
		evaporate(p);
		if (drink(p)) {
			return;
		}
		p.getImpactHandler().manualHit(p, RandomFunction.random(1, p.getLocation().getY() < 2990 ? 12 : 8), HitsplatType.NORMAL);
		p.getPacketDispatch().sendMessage("You start dying of thirst while you're in the desert.");
	}

	/**
	 * Method used to evaporate the water vessils in the inventory.
	 * @param player the <code>Player</code>
	 */
	public static void evaporate(Player p) {
		for (int i = 0; i < VESSILS.length; i++) {
			if (p.getInventory().contains(VESSILS[i][0], 1)) {
				if (p.getInventory().remove(new Item(VESSILS[i][0]))) {
					p.getInventory().add(new Item(VESSILS[i][1]));
					p.getPacketDispatch().sendMessage("The water in your " + ItemDefinition.forId(VESSILS[i][0]).getName().toLowerCase().replace("of water", "").trim() + " evaporates in the desert heat.");
				}
			}
		}
	}

	/**
	 * Method used to drink a waterskin in the inventory.
	 * @param p the player.
	 */
	public static boolean drink(Player p) {
		for (Item i : WATER_SKINS) {
			if (p.getInventory().containsItem(i) && p.getInventory().remove(i)) {
				p.getInventory().add(new Item(i.getId() + 2));
				p.animate(ANIMATION);
				p.getPacketDispatch().sendMessage("You take a drink of water.");
				return true;
			}
		}
		if (p.getInventory().contains(1831, 1)) {
			p.getPacketDispatch().sendMessage("Perhaps you should fill up one of your empty waterskins.");
		} else {
			p.getPacketDispatch().sendMessage("You should get a waterskin for any travelling in the desert.");
		}
		return false;
	}

	/**
	 * Method used to calculate the delay to the next desert effect.
	 * @param player the player.
	 * @return <code>Long</code> the amount of the delay.
	 */
	private static int getDelay(Player player) {
		int delay = 116;
		if (player.getEquipment().contains(1833, 1)) {
			delay += 17;
		}
		if (player.getEquipment().contains(1835, 1)) {
			delay += 17;
		}
		if (player.getEquipment().contains(1837, 1)) {
			delay += 17;
		}
		return delay;
	}

	/**
	 * Constructs a new {@code DesertZone} {@code Object}.
	 */
	public DesertZone() {
		super("Desert Zone", true);
	}

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ZoneBuilder.configure(this);
		return this;
	}

	@Override
	public Object fireEvent(String identifier, Object... args) {
		return null;
	}

	@Override
	public void configure() {
		ZoneBorders borders = new ZoneBorders(3063, 2725, 3544, 3115);
		borders.addException(new ZoneBorders(3152, 2961, 3191, 2999));// Bandit
		// camp
		borders.addException(new ZoneBorders(3147, 3019, 3185, 3059));// Bedabin
		borders.addException(new ZoneBorders(3217, 2881, 3248, 2914));// pyramid
		borders.addException(new ZoneBorders(3264, 2752, 3323, 2810));// Sophanem
		borders.addException(new ZoneBorders(3326,2942,3371,3006)); //Pollnivneach
		borders.addException(new ZoneBorders(3007, 4672, 3071, 4735));// agility-pyramid
		// top(region12105)
		borders.addException(new ZoneBorders(3274, 3014, 3305, 3041));// Mining
		// camp
		borders.addException(new ZoneBorders(3260, 9408, 3331, 9472));// Mining
		// camp
		register(borders);
		pulse.stop();
	}

	@Override
	public boolean enter(Entity e) {
		if (e instanceof Player) {
			Player p = (Player) e;
			if (TutorialSession.getExtension(p).getStage() < TutorialSession.MAX_STAGE) {
				return true;
			}
			p.setAttribute("desert-delay", GameWorld.getTicks() + getDelay(p));
			PLAYERS.add(p);
			if (!pulse.isRunning()) {
				pulse.restart();
				pulse.start();
				GameWorld.Pulser.submit(pulse);
			}
		}
		return true;
	}

	@Override
	public boolean leave(Entity e, boolean logout) {
		if (e instanceof Player) {
			PLAYERS.remove(e);
			e.removeAttribute("desert-delay");
		}
		return super.leave(e, logout);
	}

}
