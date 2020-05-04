package org.crandor.game.node.entity.player.link;

import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.entity.player.info.login.SavingModule;
import org.crandor.game.system.task.Pulse;
import org.crandor.game.world.GameWorld;
import org.crandor.net.packet.PacketRepository;
import org.crandor.net.packet.context.ConfigContext;
import org.crandor.net.packet.out.Config;

import java.nio.ByteBuffer;

/**
 * Manages a player's configurations.
 * @author Emperor
 */
public final class ConfigurationManager implements SavingModule {

	/**
	 * The amount of configurations.
	 */
	public static final int SIZE = 2000;

	/**
	 * The player.
	 */
	private final Player player;

	/**
	 * The configurations.
	 */
	private final int[] configurations = new int[SIZE];

	/**
	 * The configurations.
	 */
	private final int[] savedConfigurations = new int[SIZE];

	/**
	 * Constructs a new {@code ConfigurationManager} {@code Object}.
	 * @param player The player.
	 */
	public ConfigurationManager(Player player) {
		this.player = player;
	}

	@Override
	public void save(ByteBuffer buffer) {
		for (int index = 0; index < savedConfigurations.length; index++) {
			int value = savedConfigurations[index];
			if (value != 0) {
				buffer.putShort((short) index);
				buffer.putInt(value);
			}
		}
		buffer.putShort((short) -1);
	}

	@Override
	public void parse(ByteBuffer buffer) {
		int index = 0;
		while ((index = buffer.getShort()) != -1) {
			savedConfigurations[index] = buffer.getInt();
		}
	}

	/**
	 * Initializes the configurations.
	 */
	public void init() {
		for (int i = 0; i < savedConfigurations.length; i++) {
			int value = savedConfigurations[i];
			if (value != 0) {
				set(i, value, false);
			}
		}
	}

	/**
	 * Resets the configurations.
	 */
	public void reset() {
		for (int i = 0; i < configurations.length; i++) {
			configurations[i] = 0;
		}
	}

	/**
	 * Sets a configuration.
	 * @param config The configuration.
	 * @param value The value.
	 */
	public void set(Configuration config, boolean value) {
		set(config.id, value);
	}

	/**
	 * Sets a configuration.
	 * @param config The configuration.
	 * @param value The value.
	 */
	public void set(int id, boolean value) {
		set(id, value ? 1 : 0);
	}

	/**
	 * Sets a configuration.
	 * @param config The configuration.
	 * @param value The value.
	 */
	public void set(Configuration config, int value) {
		set(config, value, false);
	}

	/**
	 * Sets the configuration.
	 * @param config The configuration id.
	 * @param value The value.
	 * @param saved If the configuration should be saved.
	 */
	public void set(Configuration config, int value, boolean saved) {
		set(config.id, value, saved);
	}

	/**
	 * Sets a configuration.
	 * @param id The configuration id.
	 * @param value The value.
	 */
	public void set(int id, int value) {
		set(id, value, false);
	}

	/**
	 * Sets a configuration for a set amount of time.
	 * @param id the id.
	 * @param value the value.
	 * @param delay the delay.
	 */
	public void set(final int id, final int value, int delay) {
		set(id, value);
		GameWorld.Pulser.submit(new Pulse(delay, player) {
			@Override
			public boolean pulse() {
				set(id, 0);
				return true;
			}
		});
	}

	/**
	 * Sets a configuration.
	 * @param id The configuration id.
	 * @param value The value.
	 */
	public void set(int id, int value, boolean saved) {
		if (configurations[id] != value) {
			PacketRepository.send(Config.class, new ConfigContext(player, id, configurations[id] = value));
		}
		if (saved) {
			savedConfigurations[id] = value;
		}
	}

	/**
	 * Sends the configuration without caching.
	 */
	@Deprecated
	public void send(int id, int value) {
		PacketRepository.send(Config.class, new ConfigContext(player, id, value));
	}

	/**
	 * Gets the configuration value.
	 * @param id The config id.
	 * @return The value.
	 */
	public int get(int id) {
		return configurations[id];
	}

	/**
	 * Holds the configurations.
	 * @author Emperor
	 */
	public enum Configuration {

		BRIGHTNESS(166), MUSIC_VOLUME(168), EFFECT_VOLUME(169), MOUSE_BUTTON(170), CHAT_EFFECT(171), RETALIATE(172), RUNNING(173), SPLIT_PRIVATE(287), ACCEPT_AID(427), PC_PORTALS(719), SURROUNDING_VOLUME(872), CLAN_WAR_DATA(1147), ;

		/**
		 * The config id.
		 */
		private final int id;

		/**
		 * Constructs a new {@code Configuration} {@code Object}.
		 * @param id The config id.
		 */
		private Configuration(int id) {
			this.id = id;
		}

		/**
		 * Gets the id.
		 * @return The id.
		 */
		public int getId() {
			return id;
		}
	}
}