package org.crandor.game.world.repository;

import org.crandor.ServerConstants;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.entity.player.info.PlayerDetails;
import org.crandor.game.node.entity.player.info.login.PlayerParser;
import org.crandor.game.world.map.Location;
import org.crandor.game.world.map.RegionManager;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * The repository holding all node lists, etc in the game world.
 * @author Emperor
 * 
 */
public final class Repository {

	/**
	 * Represents the repository of active players.
	 */
	private static final NodeList<Player> PLAYERS = new NodeList<>(ServerConstants.MAX_PLAYERS);

	/**
	 * Represents the repository of active npcs.
	 */
	private static final NodeList<NPC> NPCS = new NodeList<>(ServerConstants.MAX_NPCS);

	/**
	 * The renderable NPCs.
	 */
	private static final List<NPC> RENDERABLE_NPCS = new CopyOnWriteArrayList<>();

	/**
	 * A mapping holding the players sorted by their names.
	 */
	private static final Map<String, Player> PLAYER_NAMES = new HashMap<String, Player>();

	/**
	 * Represents the list of players in lobby.
	 */
	private static final List<Player> LOBBY_PLAYERS = new ArrayList<Player>();

	/**
	 * The disconnection queue.
	 */
	private static final DisconnectionQueue DISCONNECTION_QUEUE = new DisconnectionQueue();

	/**
	 * Constructs a new {@code Repository} {@code Object}.
	 */
	public Repository() {
		/*
		 * empty.
		 */
	}

	/**
	 * Sends a news message to all players.
	 * @param string the string.
	 * @param icon The icon id.
	 * @param color The color of the text.
	 */
	public static void sendNews(String string, int icon, String color) {
		Object[] players = PLAYER_NAMES.values().toArray();
		int size = players.length;
		for (int i = 0; i < size; i++) {
			Player player = (Player) players[i];
			if (player == null) {
				continue;
			}
			player.sendMessage("<img=" + icon + ">" + color + "News: " + string);
		}
	}
	
	/**
	 * Send a news message to all players.
	 * @param string The string.
	 * @param icon The icon.
	 */
	public static void sendNews(String string, int icon) {
		sendNews(string, icon, "<col=CC6600>");
	}
	
	/**
	 * Sends a news message to all players.
	 * @param string The string.
	 */
	public static void sendNews(String string) {
		sendNews(string, 12, "<col=CC6600>");
	}
	
	/**
	 * Sends a news message to all players.
	 * @param string The string.
	 * @param color The color.
	 */
	public static void sendNews(String string, String color) {
		sendNews(string, 12, color);
	}

	/**
	 * Adds a renderable NPC.
	 * @param npc The NPC.
	 */
	public static void addRenderableNPC(NPC npc) {
		if (!RENDERABLE_NPCS.contains(npc)) {
			RENDERABLE_NPCS.add(npc);
			npc.setRenderable(true);
		}
	}

	/**
	 * Removes an NPC from rendering.
	 * @param npc The NPC.
	 */
	public static void removeRenderableNPC(NPC npc) {
		RENDERABLE_NPCS.remove(npc);
		npc.setRenderable(false);
	}

	/**
	 * Finds the NPC on the given location.
	 * @param l The location.
	 * @return The NPC, or {@code null} if the NPC wasn't found.
	 */
	public static NPC findNPC(Location l) {
		for (NPC n : RegionManager.getRegionPlane(l).getNpcs()) {
			if (n.getLocation().equals(l)) {
				return n;
			}
		}
		return null;
	}

	/**
	 * Find a non-player character.
	 * @param npcId The non-player character's id.
	 * @return The non-player character's node.
	 */
	public static NPC findNPC(int npcId) {
		for (NPC npc : NPCS) {
			if (npc == null) {
				continue;
			}
			if (npc.getId() == npcId) {
				return npc;
			}
		}
		return null;
	}
	
	/**
	 * Gets the player by the name,
	 * @param name the name.
	 * @return {@code True} if online.
	 */
	public static Player getPlayer(String name) {
		return getPlayer(name, false);
	}

	/**
	 * Get a player by their name.
	 * @param name The players name.
	 * @param load If the file should be loaded.
	 * @return The player.
	 */
	public static Player getPlayer(String name, boolean load) {
		if (name == null) {
			return null;
		}
		Player player = PLAYER_NAMES.get(name.toLowerCase().replaceAll(" ", "_"));
		if (player == null && load) {
			PlayerDetails details = PlayerDetails.getDetails(name);
			if (details == null) {
				return null;
			}
			player = new Player(details);
			PlayerParser.parse(player);
		}
		return player;
	}
	
	/*Verinia Player Grabbing*/
	
	/**
	 * Get a player by its username.
	 * @param name The player's <b>username</b>.
	 * @return The player.
	 */
	public static Player getPlayerByName(String name) {
		if (name == null) {
			return null;
		}
		return PLAYER_NAMES.get(name.toLowerCase().replaceAll(" ", "_"));
	}
	
	/**
	 * Gets the player display.
	 * @param display the display.
	 * @return the player.
	 */
	public static Player getPlayerByDisplay(String display) {
		if (display == null) {
			System.out.println("Null display!");
			return null;
		}
		Player pp = getPlayerByName(display);
		if (pp != null) {
			return pp;
		}
		display = display.toLowerCase().replaceAll(" ", "_");
		for (Player p : PLAYERS) {
			if (p.getName(true).equals(display)) {
				return p;
			}
		}
		return null;
	}
	
	/*End of Verinia Player Grabbing*/

	/**
	 * Get the players repository.
	 * @return The players repository.
	 */
	public static NodeList<Player> getPlayers() {
		return PLAYERS;
	}

	/**
	 * Get the NPCs repository.
	 * @return The NPCs repository.
	 */
	public static NodeList<NPC> getNpcs() {
		return NPCS;
	}

	/**
	 * Gets the Lobby Players repository.
	 * @return the lobby players.
	 */
	public static List<Player> getLobbyPlayers() {
		return LOBBY_PLAYERS;
	}

	/**
	 * Get the map holding the players sorted by their names.
	 * @return The map holding the players sorted by their names.
	 */
	public static Map<String, Player> getPlayerNames() {
		return PLAYER_NAMES;
	}

	/**
	 * Gets the disconnectionQueue.
	 * @return The disconnectionQueue.
	 */
	public static DisconnectionQueue getDisconnectionQueue() {
		return DISCONNECTION_QUEUE;
	}

	/**
	 * Gets the renderableNpcs.
	 * @return The renderableNpcs.
	 */
	public static List<NPC> getRenderableNpcs() {
		return RENDERABLE_NPCS;
	}

}