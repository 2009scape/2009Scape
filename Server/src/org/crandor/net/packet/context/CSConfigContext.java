package org.crandor.net.packet.context;

import org.crandor.game.node.entity.player.Player;
import org.crandor.net.packet.Context;

/**
 * The context for ClientScript configurations.
 * 
 * @author Torchic
 */
public class CSConfigContext implements Context {
	/**
	 * The player reference.
	 */
	private Player player;
	
	/**
	 * The value
	 */
	private int value;

	/**
	 * The id.
	 */
	private int id;
	//private final Object[] parameters;
	//private final String types;
	

	/**
	 * Constructs a new {@Code CSConfigContext} {@Code Object}
	 * @param player The player.
	 * @param id The id.
	 * @param value The config value.
	 * @param parameters
	 * @param types
	 */
	public CSConfigContext(Player player, int id, int value /*String types, Object[] parameters*/) {
		this.player = player;
		this.value = value;
		this.id = id;
		//this.parameters = parameters;
		//this.types = types;
	}

	@Override
	public Player getPlayer() {
		return player;
	}

	/**
	 * Sets the player.
	 * @param player The player.
	 * @return This context instance.
	 */
	public Context setPlayer(Player player) {
		this.player = player;
		return this;
	}

	/**
	 * Get the config value.
	 * @return The config value.
	 */
	public int getValue() {
		return value;
	}

	/**
	 * Get the config id.
	 * @return The config id.
	 */
	public int getId() {
		return id;
	}

	/*public String getTypes() {
		return types;
	}

	public Object[] getParameters() {
		return parameters;
	}*/
}