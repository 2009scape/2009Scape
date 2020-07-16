package core.game.world;

import core.game.system.SystemLogger;
import core.net.Constants;
import core.plugin.PluginManager;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Represents the game settings used for this game instance.
 * @author Vexia
 */
public final class GameSettings {

	/**
	 * The name of the namme.
	 */
	private final String name;

	/**
	 * If the game is in beta mode.
	 */
	private final boolean beta;

	/**
	 * If the game is in developer mode.
	 */
	private final boolean devMode;

	/**
	 * If the gui is enabled.
	 */
	private final boolean gui;
	
	/**
	 * The world id of the server.
	 */
	private final int worldId;

	/**
	 * The country index.
	 */
	private final int countryIndex;

	/**
	 * The activity.
	 */
	private final String activity;

	/**
	 * If the world is members only.
	 */
	private final boolean members;

	/**
	 * If the world is a pvp world.
	 */
	private final boolean pvp;

	/**
	 * If only quick chat can be used on the world.
	 */
	private final boolean quickChat;

	/**
	 * If lootshare option is enabled on this world.
	 */
	private final boolean lootshare;

	/**
	 * The address of the Management server.
	 */
	private final String msAddress;

	/**
	 * Constructs a new {@code GameSettings} {@code Object}.
	 * @param name the name.
	 * @param beta the beta.
	 * @param type the game type.
	 * @param gui if gui is enabled.
	 * @param worldId the world id.
	 * @param countryIndex The country index.
	 * @param members If the world is members only.
	 * @param msAddress The address of the Management server.
	 */
	GameSettings(String name, boolean beta, boolean devMode, boolean gui, int worldId, int countryIndex, String activity, boolean members, boolean pvp, boolean quickChat, boolean lootshare, String msAddress) {
		this.name = name;
		this.beta = beta;
		this.devMode = devMode;
		this.gui = gui;
		this.worldId = worldId;
		this.countryIndex = countryIndex;
		this.activity = activity;
		this.members = members;
		this.pvp = pvp;
		this.quickChat = quickChat;
		this.lootshare = lootshare;
		this.msAddress = msAddress;
	}

	/**
	 * Parses the game settings from the program arguments.
	 * @param args The program arguments.
	 * @return The game settings.
	 */
	public static GameSettings parse(String[] args) {
		return parse(args[0]);
	}

	/**
	 * Parses a game settings file.
	 * @param path the path.
	 * @return the settings.
	 */
	public static GameSettings parse(final String path) {
		File f = new File(path);
		if(!f.exists()){
			SystemLogger.log("Could not find server config at " + path);
			return null;
		}
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(path);

			Element settings = (Element) doc.getElementsByTagName("GameSettings").item(0);

			String name = settings.getAttribute("name");
			boolean beta = Boolean.parseBoolean(settings.getAttribute("beta"));
			boolean devMode = Boolean.parseBoolean(settings.getAttribute("dev"));
			boolean startGui = Boolean.parseBoolean(settings.getAttribute("startGui"));
			int worldId = Integer.parseInt(settings.getAttribute("worldID"));
			int countryId = Integer.parseInt(settings.getAttribute("countryID"));
			String activity = (settings.getAttribute("activity"));
			boolean pvp = Boolean.parseBoolean(settings.getAttribute("pvpWorld"));
			String ipAddress = settings.getAttribute("msip");

			NodeList pluginSettings = doc.getElementsByTagName("PluginSetting");
			for(int i = 0; i < pluginSettings.getLength(); i++){
				Node settingsNode = pluginSettings.item(i);

				if(settingsNode.getNodeType() == Node.ELEMENT_NODE){
					Element pluginSetting = (Element) settingsNode;

					String pName = pluginSetting.getAttribute("name");
					boolean enabled = Boolean.parseBoolean(pluginSetting.getAttribute("enabled"));

					if(!enabled){
						System.out.println("Setting " + pName + " as disabled.");
						PluginManager.disabledPlugins.putIfAbsent(pName,false);
					}
				}
			}
			return new GameSettings(name,beta,devMode,startGui,worldId,countryId,activity,true,pvp,false,false,ipAddress);
		} catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Gets the properties.
	 * @param path the path.
	 * @return the properties.
	 */
	private static Properties getProperties(String path) {
		FileInputStream file;
		Properties properties = new Properties();
		try {
			file = new FileInputStream(path);
			properties.load(file);
		} catch (IOException e) {
			System.out.println("Warning: Could not find file in " + System.getProperty("user.dir") + path);
			e.printStackTrace();
		}
		return properties;
	}

	/**
	 * Gets the devMode.
	 * @return The devMode.
	 */
	public boolean isDevMode() {
		return devMode;
	}

	/**
	 * Gets the name.
	 * @return The name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the beta.
	 * @return The beta.
	 */
	public boolean isBeta() {
		return beta;
	}

	/**
	 * Checks if the server is hosted publicly.
	 * @return {@code True} if so.
	 */
	public boolean isHosted() {
		return !devMode;
	}

	/**
	 * Gets the gui.
	 * @return The gui.
	 */
	public boolean isGui() {
		return gui;
	}

	/**
	 * Gets the worldId.
	 * @return The worldId.
	 */
	public int getWorldId() {
		return worldId;
	}

	/**
	 * Gets the countryIndex.
	 * @return the countryIndex
	 */
	public int getCountryIndex() {
		return countryIndex;
	}

	/**
	 * Gets the members.
	 * @return the members
	 */
	public boolean isMembers() {
		return members;
	}

	/**
	 * Gets the msAddress.
	 * @return the msAddress
	 */
	public String getMsAddress() {
		return msAddress;
	}

	/**
	 * Gets the activity value.
	 * @return The activity.
	 */
	public String getActivity() {
		return activity;
	}

	/**
	 * Gets the pvp value.
	 * @return The pvp.
	 */
	public boolean isPvp() {
		return pvp;
	}

	/**
	 * Gets the quickChat value.
	 * @return The quickChat.
	 */
	public boolean isQuickChat() {
		return quickChat;
	}

	/**
	 * Gets the lootshare value.
	 * @return The lootshare.
	 */
	public boolean isLootshare() {
		return lootshare;
	}
	
	@Override
	public String toString() {
		return "GameSettings [name=" + name + ", beta=" + beta + ", devMode=" + devMode + ", gui=" + gui + ", worldId=" + worldId + "]";
	}
}
