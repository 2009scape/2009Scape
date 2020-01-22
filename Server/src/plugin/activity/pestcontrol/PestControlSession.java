package plugin.activity.pestcontrol;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.crandor.game.component.Component;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.entity.player.link.ConfigurationManager.Configuration;
import org.crandor.game.node.object.GameObject;
import org.crandor.game.world.map.Location;
import org.crandor.game.world.map.Point;
import org.crandor.game.world.map.RegionPlane;
import org.crandor.game.world.map.build.DynamicRegion;
import org.crandor.tools.RandomFunction;

import static plugin.activity.pestcontrol.PestControlActivityPlugin.MAX_TEAM_SIZE;

/**
 * Represents a pest control session.
 * @author Emperor
 */
public final class PestControlSession {

	/**
	 * The barricade object offsets.
	 */
	private static final Point[] OBJECT_OFFSETS = { new Point(46, 33), new Point(46, 32), new Point(32, 25), new Point(33, 25), new Point(19, 32), new Point(19, 33), new Point(49, 33), new Point(49, 32), new Point(49, 31), new Point(49, 30), new Point(52, 27), new Point(52, 26), new Point(52, 25), new Point(52, 24), new Point(52, 14), new Point(52, 13), new Point(52, 12), new Point(52, 11), new Point(42, 18), new Point(43, 18), new Point(44, 18), new Point(45, 18), new Point(32, 15), new Point(33, 15), new Point(34, 15), new Point(35, 15), new Point(23, 18), new Point(24, 18), new Point(25, 18), new Point(26, 18), new Point(13, 11), new Point(13, 12), new Point(13, 13), new Point(13, 14), new Point(12, 28), new Point(12, 29), new Point(12, 30), new Point(12, 31), };

	/**
	 * The object ids of non-attackable barricades/gates.
	 */
	public static final int[] INVALID_OBJECT_IDS = {14230, 14231, 14232, // Barricades
			14245, 14246, 14247, 14248, // Gates
	};

	/**
	 * The dynamic region.
	 */
	private final DynamicRegion region;

	/**
	 * The activity.
	 */
	private final PestControlActivityPlugin activity;

	/**
	 * The barricade & gate objects.
	 */
	private final List<GameObject> barricades = new ArrayList<>();

	/**
	 * The amount of ticks.
	 */
	private int ticks;

	/**
	 * The squire.
	 */
	private NPC squire;

	/**
	 * The portals.
	 */
	private final NPC[] portals = new NPC[4];

	/**
	 * The ids.
	 */
	private Integer[] ids = new Integer[4];

	/**
	 * If the session is active.
	 */
	private boolean active = true;

	/**
	 * Constructs a new {@code PestControlSession} {@code Object}.
	 * @param region The dynamic region.
	 * @param activity The pest control activity.
	 */
	public PestControlSession(DynamicRegion region, PestControlActivityPlugin activity) {
		this.region = region;
		this.activity = activity;
	}

	/**
	 * Updates the session.
	 * @return {@code True} if the session has finished.
	 */
	public boolean update() {
		if (!region.isActive()) {
			return true;
		}
		if (squire != null && !squire.isActive()) {
			activity.end(this, false);
			return true;
		}
		if (ticks % 100 == 0) {
			sendString(20 - (ticks / 100) + " min", 0);
		}
		switch (++ticks) {
		case 50: // Drop first portal shield
		case 100: // Drop second portal shield
		case 150: // Drop third portal shield
		case 200: // Drop fourth portal shield
			removePortalShield(ids[(ticks / 50) - 1]);
			return false;
		case 20_00: // End game.
			activity.end(this, true);
			return true;
		}
		return false;
	}

	/**
	 * Sends a message to all the players in the region.
	 * @param message The message to send.
	 */
	public void sendMessage(String message) {
		for (Player p : region.getPlanes()[0].getPlayers()) {
			if (p.isActive()) {
				p.getPacketDispatch().sendMessage(message);
			}
		}
	}

	/**
	 * Sends a string on the interface to all players in the region.
	 * @param message The message to send.
	 * @param child The child id.
	 */
	public void sendString(String message, int child) {
		for (Player p : region.getPlanes()[0].getPlayers()) {
			if (p.isActive()) {
				p.getPacketDispatch().sendString(message, 408, child);
			}
		}
	}

	/**
	 * Adds zeal to the player's total amount.
	 * @param player The player.
	 * @param zeal The amount of zeal to add.
	 */
	public void addZealGained(Player player, int zeal) {
		int total = zeal + player.getAttribute("pc_zeal", 0);
		player.setAttribute("pc_zeal", total);
		String col = total > 50 ? "<col=00FF00>" : "";
		if (total > 350) {
			player.getPacketDispatch().sendString(col + "LOTS!", 408, 11);
		} else {
			player.getPacketDispatch().sendString(col + Integer.toString(total), 408, 11);
		}
	}

	/**
	 * Sends a string on the interface to all players in the region.
	 * @param value The message value to send.
	 */
	public void sendConfig(int value) {
		for (Player p : region.getPlanes()[0].getPlayers()) {
			if (p.isActive()) {
				p.getConfigManager().set(Configuration.PC_PORTALS, value);
			}
		}
	}

	/**
	 * Removes a portal shield.
	 * @param index The index.
	 */
	public void removePortalShield(int index) {
		String message = null;
		switch (index) {
		case 0: // Western purple portal
			message = "The purple, western portal shield has dropped!";
			break;
		case 1: // Eastern blue portal
			message = "The blue, eastern portal shield has dropped!";
			break;
		case 2: // South-eastern yellow portal
			message = "The yellow, south-eastern portal shield has dropped!";
			break;
		case 3: // South-western red portal
			message = "The red, south-western portal shield has dropped!";
			break;
		}
		for (Player p : region.getPlanes()[0].getPlayers()) {
			if (p.isActive()) {
				p.getPacketDispatch().sendInterfaceConfig(408, 18 + (index << 1), true);
				p.getPacketDispatch().sendMessage(message);
			}
		}
		squire.sendChat(message);
		portals[index].reTransform();
	}

	/**
	 * Starts a game.
	 * @param waitingPlayers The list of waiting players.
	 */
	public void startGame(List<Player> waitingPlayers) {
		region.flagActive();
		initBarricadesList();
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < 4; i++) {
			list.add(i);
		}
		Collections.shuffle(list, RandomFunction.RANDOM);
		region.toggleMulticombat();
		region.setMusicId(588);
		ids = list.toArray(new Integer[4]);
		int count = 0;
		String portalHealth = "<col=00FF00>" + (activity.getType().ordinal() == 0 ? 200 : 250);
		for (Iterator<Player> it = waitingPlayers.iterator(); it.hasNext();) {
			Player p = it.next();
			if (p.getSession().isActive()) {
				if (++count > MAX_TEAM_SIZE) {
					int priority = p.getAttribute("pc_prior", 0) + 1;
					p.getPacketDispatch().sendMessage("You have been given priority level " + priority + " over other players in joining the next");
					p.getPacketDispatch().sendMessage("game.");
					p.setAttribute("pc_prior", priority);
					continue;
				}
				addPlayer(p, portalHealth);
			}
			it.remove();
		}
		spawnNPCs();
	}

	/**
	 * Adds a player to the game session.
	 * @param p The player.
	 * @param portalHealth The portal health string.
	 */
	private void addPlayer(Player p, String portalHealth) {
		p.lock(1);
		p.setAttribute("pc_zeal", 0);
		p.removeAttribute("pc_prior");
		p.addExtension(PestControlSession.class, this);
		p.getInterfaceManager().openOverlay(new Component(408));
		p.getPacketDispatch().sendString("200", 408, 1);
		for (int i = 0; i < 4; i++) {
			p.getPacketDispatch().sendString(portalHealth, 408, 13 + i);
		}
		Random r = RandomFunction.RANDOM;
		Location l = region.getBaseLocation();
		p.getProperties().setTeleportLocation(l.transform(32 + r.nextInt(4), 49 + r.nextInt(6), 0));
		p.getConfigManager().set(Configuration.PC_PORTALS, 0);
		p.getDialogueInterpreter().sendDialogues(3781, FacialExpression.FURIOUS, "You must defend the Void Knight while the portals are", "unsummoned. The ritual takes twenty minutes though,", "so you can help out by destroying them yourselves!", "Now GO GO GO!");
	}

	/**
	 * Initializes the barricades list.
	 */
	private void initBarricadesList() {
		RegionPlane p = region.getPlanes()[0];
		for (Point point : OBJECT_OFFSETS) {
			barricades.add(p.getObjects()[point.getX()][point.getY()]);
		}
	}

	/**
	 * Spawns the portals & squire.
	 */
	private void spawnNPCs() {
		Location l = region.getBaseLocation();
		int baseId = 6142;
		if (activity.getType().ordinal() == 1) {
			baseId = 6150;
		} else if (activity.getType().ordinal() == 2) {
			baseId = 7551;
		}
		portals[0] = NPC.create(baseId, l.transform(4, 31, 0));
		portals[1] = NPC.create(baseId + 1, l.transform(56, 28, 0));
		portals[2] = NPC.create(baseId + 2, l.transform(45, 10, 0));
		portals[3] = NPC.create(baseId + 3, l.transform(21, 9, 0));
		addNPC(squire = NPC.create(3782 + (RandomFunction.RANDOM.nextBoolean() ? 3 : 0), l.transform(32, 32, 0)));
		for (NPC npc : portals) {
			addNPC(npc);
			npc.transform(npc.getId() + 4);
		}
		addNPC(NPC.create(3781, l.transform(30, 47, 0)));
	}

	/**
	 * Adds an NPC.
	 * @param npc The NPC.
	 */
	public NPC addNPC(NPC npc) {
		npc.addExtension(PestControlSession.class, this);
		npc.init();
		return npc;
	}

	/**
	 * Gets the ticks elapsed.
	 * @return The ticks.
	 */
	public int getTicks() {
		return ticks;
	}

	/**
	 * Gets the activity.
	 * @return The activity.
	 */
	public PestControlActivityPlugin getActivity() {
		return activity;
	}

	/**
	 * Gets the dynamic region.
	 * @return The region.
	 */
	public DynamicRegion getRegion() {
		return region;
	}

	/**
	 * Gets the squire.
	 * @return The squire.
	 */
	public NPC getSquire() {
		return squire;
	}

	/**
	 * Gets the portals.
	 * @return The portals.
	 */
	public NPC[] getPortals() {
		return portals;
	}

	/**
	 * Gets the active.
	 * @return The active.
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * Sets the active.
	 * @param active The active to set.
	 */
	public void setActive(boolean active) {
		this.active = active;
	}

	/**
	 * Gets the barricades.
	 * @return The barricades.
	 */
	public List<GameObject> getBarricades() {
		return barricades;
	}

}