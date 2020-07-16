package org.runite;

import org.runite.jagex.GameShell;

import static org.runite.Client.PUBLIC_IP_ADDRESS;

/**
 * Handles the launching of our Game Client.
 * @author Keldagrim Development Team
 *
 */
public class GameLaunch {

	/**
	 * The game settings.
	 */
	/*

	NOTICE: IF YOU'RE A DEVELOPER, THE EASIEST WAY TO CONNECT TO THE LIVESERVER IS WITH CLIENT.JAVA. THIS CLASS IS FOR LOCAL SERVER CONNECTING!

	 */
	public static GameSetting SETTINGS = new GameSetting("2009Scape", Configurations.LOCAL_SERVER ? "127.0.0.1" : "99.146.18.98"/*"34.68.75.237"*/, 1, "live", false);
	
	/**
	 * The main method.
	 * @param args the arguments casted on runtime.
	 */
	public static void main(String[]args) {
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
