package plugin.activity.barrows;

import core.game.content.global.action.DigAction;
import core.game.content.global.action.DigSpadeHandler;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.game.node.object.GameObject;
import core.game.world.map.Direction;
import core.game.world.map.Location;
import core.game.world.map.RegionManager;

/**
 * Handles a barrows crypt.
 * @author Emperor
 */
public final class BarrowsCrypt {

	/**
	 * The ahrim barrows crypt index.
	 */
	public static final int AHRIM = 0;

	/**
	 * The dharok barrows crypt index.
	 */
	public static final int DHAROK = 1;

	/**
	 * The guthan barrows crypt index.
	 */
	public static final int GUTHAN = 2;

	/**
	 * The karil barrows crypt index.
	 */
	public static final int KARIL = 3;

	/**
	 * The torag barrows crypt index.
	 */
	public static final int TORAG = 4;

	/**
	 * The verac barrows crypt index.
	 */
	public static final int VERAC = 5;

	/**
	 * The barrows crypts.
	 */
	private static final BarrowsCrypt[] CRYPTS = { new BarrowsCrypt(AHRIM, 2025, Location.create(3557, 9703, 3), Location.create(3565, 3289, 0)), new BarrowsCrypt(DHAROK, 2026, Location.create(3556, 9718, 3), Location.create(3575, 3298, 0)), new BarrowsCrypt(GUTHAN, 2027, Location.create(3534, 9704, 3), Location.create(3577, 3283, 0)), new BarrowsCrypt(KARIL, 2028, Location.create(3546, 9684, 3), Location.create(3565, 3276, 0)), new BarrowsCrypt(TORAG, 2029, Location.create(3568, 9683, 3), Location.create(3553, 3283, 0)), new BarrowsCrypt(VERAC, 2030, Location.create(3578, 9706, 3), Location.create(3557, 3298, 0)) };

	/**
	 * The NPC id.
	 */
	private final int npcId;

	/**
	 * The location to teleport to when entering the crypt.
	 */
	private final Location location;

	/**
	 * The exit location.
	 */
	private final Location exitLocation;

	/**
	 * The crypt index.
	 */
	private final int index;

	/**
	 * Constructs a new {@code BarrowsCrypt} {@code Object}.
	 * @param location The location to teleport to when entering the crypt.
	 * @param exitLocation The location to teleport to when leaving the crypt.
	 */
	public BarrowsCrypt(int index, int npcId, Location location, Location exitLocation) {
		this.index = index;
		this.npcId = npcId;
		this.location = location;
		this.exitLocation = exitLocation;
	}

	/**
	 * Initializes the barrow crypts.
	 */
	public static void init() {
		for (final BarrowsCrypt crypt : CRYPTS) {
			DigAction action = new DigAction() {
				@Override
				public void run(Player player) {
					crypt.enter(player);
				}
			};
			Location base = crypt.getExitLocation();
			for (int x = -2; x <= 2; x++) {
				for (int y = -2; y <= 2; y++) {
					DigSpadeHandler.register(base.transform(x, y, 0), action);
				}
			}
		}
	}

	/**
	 * Opens the sarcophagus.
	 * @param player The player.
	 */
	public void openSarcophagus(Player player, GameObject object) {
		if (index == player.getSavedData().getActivityData().getBarrowTunnelIndex()) {
			player.getDialogueInterpreter().open("barrow_tunnel", index);
			return;
		}
		if (player.getSavedData().getActivityData().getBarrowBrothers()[index] || player.getAttribute("barrow:npc") != null) {
			player.getPacketDispatch().sendMessage("You don't find anything.");
			return;
		}
		player.getPacketDispatch().sendMessage("You don't find anything.");
		Location location = RegionManager.getTeleportLocation(object.getLocation().transform(Direction.SOUTH_WEST), object.getSizeX() + 1, object.getSizeY() + 1);
		spawnBrother(player, location);
	}

	/**
	 * Spawns the barrow brother.
	 * @param player The player.
	 * @param location The location.
	 * @return {@code True} if successful.
	 */
	public boolean spawnBrother(Player player, Location location) {
		if (player.getAttribute("brother:" + index, false)) {
			return false;
		}
		NPC npc = new BarrowBrother(player, npcId, location);
		npc.init();
		player.setAttribute("barrow:npc", npc);
		player.setAttribute("brother:" + index, true);
		return true;
	}

	/**
	 * Enters the crypt.
	 * @param player The player.
	 */
	protected void enter(Player player) {
		player.addExtension(BarrowsCrypt.class, this);
		player.getPacketDispatch().sendMessage("You've broken into a crypt!");
		player.getProperties().setTeleportLocation(getEnterLocation());
	}

	/**
	 * Gets the crypt for the given index.
	 * @param index The crypt index.
	 * @return The barrows crypt.
	 */
	public static BarrowsCrypt getCrypt(int index) {
		return CRYPTS[index];
	}

	/**
	 * Gets the location to teleport to upon entering.
	 * @return The location.
	 */
	public Location getEnterLocation() {
		return location;
	}

	/**
	 * Gets the exit location.
	 * @return The location.
	 */
	public Location getExitLocation() {
		return exitLocation;
	}

	/**
	 * Gets the npcId.
	 * @return The npcId.
	 */
	public int getNpcId() {
		return npcId;
	}

	/**
	 * Gets the index.
	 * @return The index.
	 */
	public int getIndex() {
		return index;
	}

}