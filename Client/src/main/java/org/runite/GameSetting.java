package org.runite;

/**
 * Represents the game settings to use in the client.
 * @author Vexia
 * 
 */
public final class GameSetting {

	/**
	 * The name of the game.
	 */
	private final String name;
	
	/**
	 * The ip to the game to connect to.
	 */
	private String ip;
	
	/**
	 * Represents the world if to load.
	 */
	private int world;
	
	/**
	 * Represents the environment of the game.
	 */
	private final String environment;
	
	/**
	 * If the client should use a lower amount of memory.
	 */
	private final boolean lowMemory;

	/**
	 * Constructs a new {@code GameSetting} {@code Object}
	 * @param name the name.
	 * @param ip the ip.
	 * @param world the world.
	 * @param environment the enviornment.
	 * @param lowMemory if low memory.
	 */
	public GameSetting(String name, String ip, int world, String environment, boolean lowMemory) {
		this.name = name; 
		this.ip = ip;
		this.world = world;
		this.environment = environment;
		this.lowMemory = lowMemory;
	}

	/** 
	 * Gets the name.
	 * @return the name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the world
	 */
	public int getWorld() {
		return world;
	}

	/**
	 * @return the ip
	 */
	public String getIp() {
		return ip;
	}

	/**
	 * Sets the ip.
	 * @param ip the ip to set
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}

	/**
	 * Sets the World
	 * @param world world to set
	 */
	public void setWorld(int world) {
		this.world = world;
	}

}
