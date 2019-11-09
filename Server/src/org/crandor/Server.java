package org.crandor;

import org.crandor.game.system.SystemLogger;
import org.crandor.game.system.SystemShutdownHook;
import org.crandor.game.system.mysql.SQLManager;
import org.crandor.game.world.GameSettings;
import org.crandor.game.world.GameWorld;
import org.crandor.gui.ConsoleFrame;
import org.crandor.net.NioReactor;
import org.crandor.net.amsc.WorldCommunicator;
import org.crandor.tools.TimeStamp;
import org.crandor.tools.backup.AutoBackup;

import java.awt.*;
import java.net.BindException;

/**
 * The main class, for those that are unable to read the class' name.
 * @author Emperor
 * @author Vexia
 * 
 */
public final class Server {

	/**
	 * The time stamp of when the server started running.
	 */
	public static long startTime;

	/**
	 * The NIO reactor.
	 */
	public static NioReactor reactor;

	private static AutoBackup backup;

	/**
	 * The main method, in this method we load background utilities such as
	 * cache and our world, then end with starting networking.
	 * @param args The arguments cast on runtime.
	 * @throws Throwable When an exception occurs.
	 */
	public static void main(String... args) throws Throwable {
		if (args.length > 0) {
			GameWorld.setSettings(GameSettings.parse(args));
		}
		if (GameWorld.getSettings().isGui()) {
			try {
				ConsoleFrame.getInstance().init();
			} catch (Exception e) {
				System.out.println("X11 server missing - launching server with no GUI!");
			}
		}
		startTime = System.currentTimeMillis();
		final TimeStamp t = new TimeStamp();
//		backup = new AutoBackup();
		GameWorld.prompt(true);
		SQLManager.init();
		Runtime.getRuntime().addShutdownHook(new Thread(new SystemShutdownHook()));
		SystemLogger.log("Starting NIO reactor...");

		try {
			reactor = NioReactor.configure(43594 + GameWorld.getSettings().getWorldId());
		} catch (BindException e) {
			System.out.println("Port " + 43594 + GameWorld.getSettings().getWorldId() + " is already in use!");
			throw e;
		}

		WorldCommunicator.connect();
		reactor.start();
		SystemLogger.log(GameWorld.getName() + " flags " + GameWorld.getSettings().toString());
		SystemLogger.log(GameWorld.getName() + " started in " + t.duration(false, "") + " milliseconds.");

	}

	/**
	 * Gets the startTime.
	 * @return the startTime
	 */
	public static long getStartTime() {
		return startTime;
	}

	/**
	 * Sets the bastartTime.ZZ
	 * @param startTime the startTime to set.
	 */
	public static void setStartTime(long startTime) {
		Server.startTime = startTime;
	}

}