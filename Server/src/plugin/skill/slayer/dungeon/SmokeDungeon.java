package plugin.skill.slayer.dungeon;

import org.crandor.cache.def.impl.ObjectDefinition;
import org.crandor.game.component.Component;
import org.crandor.game.content.skill.member.slayer.Equipment;
import org.crandor.game.interaction.OptionHandler;
import org.crandor.game.node.Node;
import org.crandor.game.node.entity.Entity;
import org.crandor.game.node.entity.combat.ImpactHandler.HitsplatType;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.system.task.Pulse;
import org.crandor.game.world.GameWorld;
import org.crandor.game.world.map.Location;
import org.crandor.game.world.map.zone.MapZone;
import org.crandor.game.world.map.zone.ZoneBorders;
import org.crandor.game.world.map.zone.ZoneBuilder;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.plugin.Plugin;
import org.crandor.plugin.PluginManager;
import org.crandor.tools.RandomFunction;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles the smoke dungeon.
 * @author Vexia
 */
@InitializablePlugin
public final class SmokeDungeon extends MapZone implements Plugin<Object> {

	/**
	 * The chats to send.
	 */
	private static final String[] CHATS = new String[] { "*choke*", "*cough*" };

	/**
	 * The players list.
	 */
	private static final List<Player> PLAYERS = new ArrayList<>();

	/**
	 * The tick delay until the next effect.
	 */
	private static final int DELAY = 20;

	/**
	 * The smoke effect pulse.
	 */
	private static Pulse pulse = new Pulse(3) {
		@Override
		public boolean pulse() {
			for (Player player : PLAYERS) {
				if (player.getInterfaceManager().isOpened() || player.getInterfaceManager().hasChatbox() || player.getLocks().isMovementLocked()) {
					continue;
				}
				if (SmokeDungeon.getDelay(player) < GameWorld.getTicks() && !isProtected(player)) {
					effect(player);
				}
			}
			return PLAYERS.isEmpty();
		}
	};

	/**
	 * Constructs a new {@code SmokeDungeon} {@code Object}
	 */
	public SmokeDungeon() {
		super("zmoke dungeon", true);
	}

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		PluginManager.definePlugin(new OptionHandler() {

			@Override
			public Plugin<Object> newInstance(Object arg) throws Throwable {
				ObjectDefinition.forId(36002).getConfigurations().put("option:climb-down", this);
				ObjectDefinition.forId(6439).getConfigurations().put("option:climb-up", this);
				return this;
			}

			@Override
			public boolean handle(Player player, Node node, String option) {
				switch (node.getId()) {
				case 36002:
					player.getProperties().setTeleportLocation(Location.create(3206, 9379, 0));
					player.getPacketDispatch().sendMessage("You climb down the well.");
					break;
				case 6439:
					player.getProperties().setTeleportLocation(Location.create(3310, 2963, 0));
					player.getPacketDispatch().sendMessage("You climb up the rope.");
					break;
				}
				return true;
			}

		});
		ZoneBuilder.configure(this);
		return this;
	}

	@Override
	public boolean enter(Entity e) {
		if (e instanceof Player) {
			Player p = (Player) e;
			setDelay(p);
			PLAYERS.add(p);
			if (!pulse.isRunning()) {
				pulse.restart();
				pulse.start();
				GameWorld.Pulser.submit(pulse);
			}
			p.getInterfaceManager().openOverlay(new Component(118));
		}
		return true;
	}

	@Override
	public boolean leave(Entity e, boolean logout) {
		if (e instanceof Player) {
			Player p = (Player) e;
			p.getInterfaceManager().closeOverlay();
			PLAYERS.remove(e);
			e.removeAttribute("smoke-delay");
		}
		return super.leave(e, logout);
	}

	/**
	 * Effects the player with smoke.
	 * @param player the player.
	 */
	private static void effect(Player player) {
		int hit = 2;
		setDelay(player);
		if (RandomFunction.random(2) == 1) {
			player.sendChat(CHATS[RandomFunction.random(CHATS.length)]);
			player.getPacketDispatch().sendMessage("The stagnant, smoky air chokes you.");
		}
		player.getImpactHandler().manualHit(player, hit, HitsplatType.NORMAL);
	}

	/**
	 * Sets the delay on the player.
	 * @param player the player.
	 */
	private static void setDelay(Player player) {
		player.setAttribute("smoke-delay", GameWorld.getTicks() + DELAY);
	}

	/**
	 * Gets the smoke delay.
	 * @param player the player.
	 * @return the delay.
	 */
	private static int getDelay(Player player) {
		return player.getAttribute("smoke-delay", 0);
	}

	/**
	 * Checks if the player is protected.
	 * @param player the player.
	 * @return {@code True} if so.
	 */
	private static boolean isProtected(Player player) {
		return Equipment.FACEMASK.hasEquipment(player);
	}

	@Override
	public Object fireEvent(String identifier, Object... args) {
		return this;
	}

	@Override
	public void configure() {
		register(new ZoneBorders(3196, 9337, 3344, 9407));
		pulse.stop();
	}

}
