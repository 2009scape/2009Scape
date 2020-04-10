package org.crandor.game.world.update;

import org.crandor.game.node.entity.Entity;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.item.GroundItemManager;
import org.crandor.game.world.map.RegionManager;
import org.crandor.game.world.repository.InitializingNodeList;
import org.crandor.game.world.repository.Repository;
import org.crandor.net.packet.PacketRepository;
import org.crandor.net.packet.context.PlayerContext;
import org.crandor.net.packet.out.ClearMinimapFlag;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * The entity update sequence.
 * @author Emperor
 */
public final class UpdateSequence {

	Object[] lobbyArray, playersArray, npcArray;
	int lobbySize, playerSize, npcSize;

	/**
	 * The list of active players.
	 */
	private static final InitializingNodeList<Player> RENDERABLE_PLAYERS = new InitializingNodeList<>();

	/**
	 * The executor used.
	 */
	private static final ExecutorService EXECUTOR = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

	/**
	 * Constructs a new {@code ParallelUpdatingSequence} {@code Object}.
	 */
	public UpdateSequence() {
		/*
		 * empty.
		 */
	}

	/**
	 * Starts the update sequence.
	 * @return {@code True} if we should continue.
	 */
	public void start() {
		lobbyArray = Repository.getLobbyPlayers().toArray();
		playersArray = getRenderablePlayers().toArray();
		npcArray = Repository.getRenderableNpcs().toArray();
		lobbySize = lobbyArray.length;
		playerSize = playersArray.length;
		npcSize = npcArray.length;
		for (int i = 0; i < lobbySize; i++) {
			Player p = (Player) lobbyArray[i];
			PacketRepository.send(ClearMinimapFlag.class, new PlayerContext(p));
		}
		for (int i = 0; i < playerSize; i++) {
			Player p = (Player) playersArray[i];
			try {
				p.tick();
			} catch (Throwable t) {
				t.printStackTrace();
			}
		}
		for (int i = 0; i < npcSize; i++) {
			NPC n = (NPC) npcArray[i];
			try {
				n.tick();
			} catch (Throwable t) {
				t.printStackTrace();
			}
		}
	}

	/**
	 * Runs the updating part of the sequence.
	 */
	public void run() {
		final CountDownLatch latch = new CountDownLatch(getRenderablePlayers().size());
		for (int i = 0; i < playerSize; i++) {
			Player p = (Player) playersArray[i];
			EXECUTOR.execute(new Runnable() {
				@Override
				public void run() {
					try {
						p.update();
					} catch (Throwable t) {
						t.printStackTrace();
					}
					latch.countDown();
				}
			});
		}
		try {
			latch.await(1000l, TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Ends the sequence, calls the {@link Entity#reset()} method..
	 */
	public void end() {
		for (int i = 0; i < playerSize; i++) {
			Player p = (Player) playersArray[i];
			p.reset();
		}
		for (int i = 0; i < npcSize; i++) {
			NPC npc = (NPC) npcArray[i];
			npc.reset();
		}
		getRenderablePlayers().sync();
		RegionManager.pulse();
		GroundItemManager.pulse();
	}

	/**
	 * Terminates the update sequence.
	 */
	public final void terminate() {
		EXECUTOR.shutdown();
	}

	/**
	 * Gets the renderablePlayers.
	 * @return The renderablePlayers.
	 */
	public static InitializingNodeList<Player> getRenderablePlayers() {
		return RENDERABLE_PLAYERS;
	}
}