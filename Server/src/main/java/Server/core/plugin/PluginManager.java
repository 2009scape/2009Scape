package core.plugin;

import plugin.activity.ActivityManager;
import plugin.activity.ActivityPlugin;
import plugin.dialogue.DialoguePlugin;
import core.game.node.entity.player.info.login.LoginConfiguration;
import core.game.node.entity.player.link.quest.Quest;
import core.game.node.entity.player.link.quest.QuestRepository;
import core.game.system.SystemLogger;
import org.reflections.Reflections;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * Represents a class used to handle the loading of all plugins.
 * @author Emperor
 */
public final class PluginManager {

	public static HashMap<String,Boolean> disabledPlugins = new HashMap<>();

	/**
	 * The amount of plugins loaded.
	 */
	private static int pluginCount;

	/**
	 * The currently loaded plugin names.
	 */
	private static List<String> loadedPlugins = new ArrayList<>();
	
	/**
	 * The last loaded plugin.
	 */
	private static String lastLoaded;

	/**
	 * Initializes the plugin manager.
	 */
	public static void init() {
		try {
			load("plugin");
			loadedPlugins.clear();
			loadedPlugins = null;
			SystemLogger.log("Initialized " + pluginCount + " plugins...");
		} catch (Throwable t) {
			SystemLogger.log("Error initializing Plugins -> " + t.getLocalizedMessage() + " for file -> " + lastLoaded);
			t.printStackTrace();
		}
	}

	public static void load(String root) throws Throwable {
		if (root == null || root.isEmpty()) {
			root = "plugin";
		}
		Reflections reflections = new Reflections(root);
		Set<Class<?>> reflectionsSet = reflections.getTypesAnnotatedWith(InitializablePlugin.class);
		reflectionsSet.stream().filter(c -> !c.isMemberClass() && !c.isAnonymousClass()).forEach(c -> {
			try {
				definePlugin((Plugin)c.newInstance());
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
		});
	}

	/**
	 * Defines a list of plugins.
	 * @param plugins the plugins.
	 */
	public static void definePlugin(Plugin<?>... plugins) {
		int pluginsLength = plugins.length;
		for (int i = 0; i < pluginsLength; i++) {
			Plugin<?> p = plugins[i];
			definePlugin(p);
		}
	}

	/**
	 * Defines the plugin.
	 * @param plugin The plugin.
	 */
	@SuppressWarnings("unchecked")
	public static void definePlugin(Plugin<?> plugin) {
		try {
			PluginManifest manifest = plugin.getClass().getAnnotation(PluginManifest.class);
			if (manifest == null) {
				manifest = plugin.getClass().getSuperclass().getAnnotation(PluginManifest.class);
			} else {
				if(disabledPlugins.get(manifest.name()) != null){
					return;
				}
			}
			if (manifest == null || manifest.type() == PluginType.ACTION) {
				plugin.newInstance(null);
			} else {
				switch (manifest.type()) {
					case DIALOGUE:
						((DialoguePlugin) plugin).init();
						break;
					case ACTIVITY:
						ActivityManager.register((ActivityPlugin) plugin);
						break;
					case LOGIN:
						LoginConfiguration.getLoginPlugins().add((Plugin<Object>) plugin);
						break;
					case QUEST:
						plugin.newInstance(null);
						QuestRepository.register((Quest) plugin);
						break;
					default:
						System.out.println("Manifest: " + manifest.type());
						break;
				}
			}
			pluginCount++;
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	/**
	 * Gets the amount of plugins currently loaded.
	 * @return The plugin count.
	 */
	public static int getAmountLoaded() {
		return pluginCount;
	}

	/**
	 * Checks if the plugin shouldn't be repacked.
	 * @param name The plugin name.
	 * @return {@code True} if so.
	 */
	public static boolean isAutoPacked(String name) {
		return name.equals("VicTheTraderPlugin.jar");
	}

	/**
	 * Gets the pluginCount.
	 * @return the pluginCount.
	 */
	public static int getPluginCount() {
		return pluginCount;
	}

	/**
	 * Sets the pluginCount.
	 * @param pluginCount the pluginCount to set
	 */
	public static void setPluginCount(int pluginCount) {
		PluginManager.pluginCount = pluginCount;
	}

	/**
	 * Gets the loadedPlugins.
	 * @return the loadedPlugins.
	 */
	public static List<String> getLoadedPlugins() {
		return loadedPlugins;
	}

	/**
	 * Sets the loadedPlugins.
	 * @param loadedPlugins the loadedPlugins to set
	 */
	public static void setLoadedPlugins(List<String> loadedPlugins) {
		PluginManager.loadedPlugins = loadedPlugins;
	}
}