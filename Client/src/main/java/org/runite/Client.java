package org.runite;

import org.runite.jagex.GameShell;

/**
 * Handles the launching of our Game Client.
 * @author Keldagrim Development Team
 *
 */

/*

NOTICE: THIS IS THE LIVESERVER CLIENT. For development purposes, use GameLaunch.java instead!!!

 */
public class Client {

	public static final String CONF_FILE="client.conf";

	public static String PUBLIC_IP_ADDRESS;

	/**
	 * The game settings.
	 */
	public static GameSetting SETTINGS = new GameSetting("2009Scape", "play.2009scape.org", 3, "live", false);
	
	/**
	 * The main method.
	 r @param args the arguments casted on runtime.
     r_game

	 */
	public static void main(String[]args) {
		try {
			PUBLIC_IP_ADDRESS = "play.2009scape.org";
		} catch (Exception e){
			System.out.println("Can't find config file " + CONF_FILE + " defaulting to IP 127.0.0.1");
			PUBLIC_IP_ADDRESS = "play.2009scape.org";
		}
		System.out.println("Running liveserver client");
		Configurations.LOCAL_SERVER = false;
		Configurations.LOCAL_MS = false;
		Configurations.MS_IP = PUBLIC_IP_ADDRESS; //Needs to be done because of order it's otherwise set

		for (int i = 0; i < args.length; i++) {
			String[] cmd = args[i].split("=");
			switch (cmd[0]) {
			case "ip":
				SETTINGS.setIp(cmd[1]);
				break;
			case "world":
				SETTINGS.setWorld(Integer.parseInt(cmd[1]));
				break;
			}
		}
		/**
		 * Launches the client
		 */
		GameShell.launchDesktop();
	}
	
}
