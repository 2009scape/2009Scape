package core.game.node.entity.player.info.login;

import core.game.node.entity.player.Player;
import core.game.node.entity.player.info.PlayerDetails;
import core.game.system.SystemManager;
import core.game.system.monitor.PlayerMonitor;
import core.game.system.task.Pulse;
import core.game.world.GameWorld;
import core.game.world.repository.Repository;
import core.net.amsc.MSPacketRepository;
import core.net.amsc.ManagementServerState;
import core.net.amsc.WorldCommunicator;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Parses the login of a player.
 * @author Emperor
 * @author Vexia
 */
public final class LoginParser implements Runnable {

	/**
	 * The lock used to disable 2 of the same player being logged in.
	 */
	private static final Lock LOCK = new ReentrantLock();

	/**
	 * The player details file.
	 */
	private final PlayerDetails details;

	/**
	 * The login type.
	 */
	private final LoginType type;

	/**
	 * The player in the game, used for reconnect login type.
	 */
	private Player gamePlayer;

	/**
	 * The time stamp.
	 */
	private final int timeStamp;

	/**
	 * Constructs a new {@code LoginParser} {@code Object}.
	 * @param details the player details.
	 * @param type The login type.
	 */
	public LoginParser(PlayerDetails details, LoginType type) {
		this.details = details;
		this.type = type;
		this.timeStamp = GameWorld.getTicks();
	}

	@Override
	public void run() {
		try {
			LOCK.tryLock(1000L, TimeUnit.MILLISECONDS);
		} catch (Exception e){
			System.out.println(e);
			LOCK.unlock();
			return;
		}
		try {
			if (validateRequest()) {
				handleLogin();
			}
		} catch (Throwable t) {
			t.printStackTrace();
			try {
				flag(Response.ERROR_LOADING_PROFILE);
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}
		LOCK.unlock();
	}

	/**
	 * Handles the actual login.
	 */
	private void handleLogin() {
		Player p = getWorldInstance();
		if (!details.parse()) {
			flag(Response.INVALID_LOGIN_SERVER);
			return;
		}
		final Player player = p == null ? new Player(details) : p;
		player.setAttribute("login_type", type);
		if (p != null) { // Reconnecting
			p.updateDetails(details);
			reconnect(p, type);
			return;
		}
		initialize(player, false);
	}

	/**
	 * Initializes the player.
	 * @param player The player.
	 * @param reconnect If the player data should be parsed.
	 */
	public void initialize(final Player player, boolean reconnect) {
		if (reconnect) {
			reconnect(player, type);
			return;
		}
		PlayerParser.parse(player);
		//Repository.getPlayerNames().put(player.getName(), player);
		GameWorld.Pulser.submit(new Pulse(1) {
			@Override
			public boolean pulse() {
				try {
					if (details.getSession().isActive()) {
						if (!Repository.getPlayers().contains(player)) {
							Repository.getPlayers().add(player);
						}
						player.getDetails().getSession().setObject(player);
						flag(Response.SUCCESSFUL);
						player.init();
						player.getMonitor().log(player.getDetails().getIpAddress(), PlayerMonitor.ADDRESS_LOG);
						player.getMonitor().log(player.getDetails().getSerial(), PlayerMonitor.ADDRESS_LOG);
						player.getMonitor().log(player.getDetails().getMacAddress(), PlayerMonitor.ADDRESS_LOG);
					} else {
						Repository.getPlayerNames().remove(player.getName());
						MSPacketRepository.sendPlayerRemoval(player.getName());
					}
				} catch (Throwable t) {
					t.printStackTrace();
					Repository.getPlayerNames().remove(player.getName());
					MSPacketRepository.sendPlayerRemoval(player.getName());
				}
				return true;
			}
		});
	}

	/**
	 * Gets the player instance in the current world.
	 * @return The player instance, if found.
	 */
	private Player getWorldInstance() {
		Player player = Repository.getDisconnectionQueue().get(details.getUsername());
		if (player == null) {
			player = gamePlayer;
		}
		return player;
	}

	/**
	 * Initializes a reconnecting player.
	 * @param player The player.
	 * @param type The login type.
	 */
	private void reconnect(final Player player, LoginType type) {
		Repository.getDisconnectionQueue().remove(details.getUsername());
		player.initReconnect();
		player.setActive(true);
		flag(Response.SUCCESSFUL);
		player.updateSceneGraph(true);
		player.getConfigManager().init();
		LoginConfiguration.configureGameWorld(player);
		Repository.getPlayerNames().put(player.getName(), player);
		GameWorld.Pulser.submit(new Pulse(1) {
			@Override
			public boolean pulse() {
				if (!Repository.getPlayers().contains(player)) {
					Repository.getPlayers().add(player);
				}
				return true;
			}
		});
	}

	/**
	 * Checks if the login request is valid.
	 * @return {@code True} if the request is valid.
	 */
	private boolean validateRequest() {
		if (WorldCommunicator.getState() == ManagementServerState.CONNECTING) {
			return flag(Response.LOGIN_SERVER_OFFLINE);
		}
		if (!details.getSession().isActive()) {
			return false;
		}
		if (SystemManager.isUpdating()) {
			return flag(Response.UPDATING);
		}
		if ((gamePlayer = Repository.getPlayer(details.getUsername())) != null && gamePlayer.getSession().isActive()) {
			return flag(Response.ALREADY_ONLINE);
		}
		if (details.isBanned()) {
			return flag(Response.ACCOUNT_DISABLED);
		}
		return true;
	}

	/**
	 * Flags a response.
	 * @param response the {@link Response}.
	 * @return {@code True} if successfully logged in.
	 */
	public boolean flag(Response response) {
		details.getSession().write(response, true);
		return response == Response.SUCCESSFUL;
	}

	/**
	 * Gets the player details.
	 * @return The player details.
	 */
	public PlayerDetails getDetails() {
		return details;
	}

	/**
	 * Gets the timeStamp.
	 * @return the timeStamp
	 */
	public int getTimeStamp() {
		return timeStamp;
	}
	
}