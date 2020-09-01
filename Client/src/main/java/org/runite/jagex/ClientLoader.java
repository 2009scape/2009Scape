package org.runite.jagex;
import javax.swing.*;

import net.arikia.dev.drpc.DiscordEventHandlers;
import net.arikia.dev.drpc.DiscordRPC;
import org.runite.GameLaunch;

import java.applet.Applet;
import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

/**
 * The client loader used to initialize the game.
 * @author Jagex
 * @author Keldagrim Development Team
 *
 */
public class ClientLoader extends Applet {
	
	/**
	 * The serial version UID.  
	 */
	private static final long serialVersionUID = -4744673981053459832L;
	
	/**
	 * The properties being used.
	 */
	public static Properties props = new Properties();
	
	/**
	 * The JFrame the clients hosted in.
	 */
	public JFrame frame;
	
	/**
	 * The jpanel of the client.
	 */
	public JPanel jp = new JPanel();
	
	/**
	 * The world name.
	 */
	public static String world;
	
	/**
	 * The look and feel being used.
	 */
	public static LookAndFeel lookAndFeel;

	/**
	 * The manegement server IP.
	 */
	public static final String ADDRESS = "127.0.0.l";
	
	/**
	 * The world list port.
	 */
	public static final int WLPORT = 5555; //43595
	
	/**
	 * The game client.
	 */
	public static Client game; 
	
	/**
	 * The client loader.
	 */
	public static ClientLoader loader;
	
	/**
	 * Client version id
	 */
	public static final double CLIENT_VERSION = 1.0;
		
	/**
	 * Creates the client loader instance.
	 * @return the client load.
	 */
	public static ClientLoader create() {
		System.getProperties().put("sun.java2d.noddraw", "true"); //Fixes fullscreen mode 
		loader = new ClientLoader();
		return loader;
	}

	/**
	 * Launches the client.
	 */
	public void launch() {
		try {
			ClientLoader.world = "" + GameLaunch.SETTINGS.getWorld();
			//System.out.println(GameLaunch.SETTINGS.getWorld());
			this.frame = new JFrame(GameLaunch.SETTINGS.getName());
			this.frame.setLayout(new BorderLayout());
			this.frame.setBackground(Color.BLACK);
			this.frame.setResizable(true);
			this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.jp.setLayout(new BorderLayout());
			this.jp.add(this);
			this.jp.setBackground(Color.black);
			this.jp.setPreferredSize(new Dimension(765, 503));
			//this.frame.setIconImage(Toolkit.getDefaultToolkit().getImage("./res/favicon.png"));
			this.frame.getContentPane().add(this.jp, "Center");
			this.frame.pack();
			this.frame.setVisible(true);
			this.frame.setLocationRelativeTo(null);
			props.put("worldid", "" + GameLaunch.SETTINGS.getWorld());
			props.put("members", "1");
			props.put("modewhat", "1");
			props.put("modewhere", "0");
			props.put("safemode", "0");
			props.put("game", "0");
			props.put("js", "1");
			props.put("lang", "0");
			props.put("affid", "0");
			props.put("advertsuppressed", "1");
			props.put("lowmem", "0");
			props.put("settings", "kKmok3kJqOeN6D3mDdihco3oPeYN2KFy6W5--vZUbNA");
			Signlink sn = new Signlink(this, 32, "runescape", 29);
			Client.providesignlink(sn);
			game = new Client();
			game.init();
		} catch (Exception localException) {
			localException.printStackTrace();
		}
	}
	
	/**
	 * Gets a property parameter.
	 * @return the parameter string.
	 */
	public String getParameter(String paramString) {
		return ((String)props.get(paramString));
	}

	/**
	 * Gets document base.
	 * @return the URL.
	 */
	public URL getDocumentBase() {
		return getCodeBase();
	}

	/**
	 * Gets the code base.
	 * @return The URL.
	 */
	public URL getCodeBase() {
		try {
			return new URL("http://" + GameLaunch.SETTINGS.getIp());
		} catch (MalformedURLException localException) {
			System.out.println("World List Loading might not be working or something else is wrong.");
			System.out.println("Stack Trace:");
			localException.printStackTrace();
			return null;
		}
	}

}