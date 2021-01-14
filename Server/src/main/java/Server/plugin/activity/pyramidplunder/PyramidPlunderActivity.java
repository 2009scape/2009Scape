package plugin.activity.pyramidplunder;

import core.game.component.Component;
import plugin.activity.ActivityPlugin;
import core.game.node.entity.Entity;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.game.world.map.Location;
import core.plugin.InitializablePlugin;
import core.plugin.PluginManager;

/**
 * Handles the Pyramid plunder activity.
 * @author Emperor
 */
@InitializablePlugin
public final class PyramidPlunderActivity extends ActivityPlugin {

	/**
	 * The room locations.
	 */
	public static Location[] ROOM_LOCATIONS = { Location.create(1927, 4477, 0) };

	/**
	 * The mummy NPC.
	 */
	private static NPC mummy;

	/**
	 * Constructs a new {@code PyramidPlunderActivity} {@code Object}.
	 */
	public PyramidPlunderActivity() {
		super("Pyramid plunder", false, true, false);
	}

	@Override
	public boolean start(Player player, boolean login, Object... args) {
		enterRoom(player, 0);
		return true;
	}

	@Override
	public boolean enter(Entity e) {
		return super.enter(e);
	}

	@Override
	public boolean leave(Entity e, boolean logout) {
		if (e instanceof Player) {
			((Player) e).getInterfaceManager().closeOverlay();
		}
		return super.leave(e, logout);
	}

	@Override
	public ActivityPlugin newInstance(Player p) throws Throwable {
		return this;
	}

	@Override
	public void register() {
		PluginManager.definePlugin(new GuardMummyDialogue());
		PluginManager.definePlugin(new PyramidOptionHandler());
		mummy = NPC.create(4476, Location.create(1968, 4427, 2));
		mummy.init();
		registerRegion(7749);
	}

	@Override
	public Location getSpawnLocation() {
		return null;
	}

	@Override
	public void configure() {
	}

	/**
	 * Enters a room.
	 * @param player The player.
	 * @param index The index.
	 */
	public void enterRoom(Player player, int index) {
		player.lock(1);
		player.getProperties().setTeleportLocation(ROOM_LOCATIONS[index]);
		new PlunderSession(player).init();
	}

}
