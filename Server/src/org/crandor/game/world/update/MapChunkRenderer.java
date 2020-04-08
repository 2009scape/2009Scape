package org.crandor.game.world.update;

import org.crandor.game.node.entity.player.Player;
import org.crandor.game.world.map.Region;
import org.crandor.game.world.map.RegionChunk;
import org.crandor.game.world.map.Viewport;
import org.crandor.net.packet.PacketRepository;
import org.crandor.net.packet.context.ClearChunkContext;
import org.crandor.net.packet.out.ClearRegionChunk;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Handles the rendering of the player's surrounding map chunks.
 * @author Emperor
 */
public final class MapChunkRenderer {

	/**
	 * Sends the map chunk rendering packet.
	 * @param player The player.
	 */
	public static void render(Player player) {
		Viewport v = player.getViewport();
		RegionChunk[][] last = player.getPlayerFlags().getLastViewport();
		List<RegionChunk> updated = new ArrayList<>();
		RegionChunk[][] current = v.getChunks();
		if(!Arrays.equals(current,last)) { //don't update if the viewport hasn't changed
			int sizeX = last.length;
			for (int x = 0;  x < sizeX; x++) {
				int sizeY = last[x].length;
				for (int y = 0; y < sizeY; y++) {
					RegionChunk previous = last[x][y];
					if (previous == null) {
						continue;
					}
					if (!containsChunk(current, previous)) {
						PacketRepository.send(ClearRegionChunk.class, new ClearChunkContext(player, previous));
					} else {
						updated.add(previous);
					}
				}
			}
			sizeX = current.length;
			for (int x = 0; x < sizeX; x++) {
				int sizeY = current[x].length;
				for (int y = 0; y < sizeY; y++) {
					RegionChunk chunk = current[x][y];
					if (!updated.contains(chunk)) {
						chunk.synchronize(player);
					} else {
						chunk.update(player);
					}
					last[x][y] = current[x][y];
				}
			}
		}
	}

	/**
	 * Checks if the chunks list contains the specified region chunk.
	 * @param list The list to search.
	 * @param c The region chunk.
	 * @return {@code True} if so.
	 */
	private static boolean containsChunk(RegionChunk[][] list, RegionChunk c) {
		int sizeList = list.length;
		for (int x = 0; x < sizeList; x++) {
			int chunkSize = list[x].length;
			for (int y = 0; y < chunkSize; y++) {
				if (list[x][y] == c) {
					return true;
				}
			}
		}
		return false;
	}

}