package core.plugin

import core.game.node.entity.player.info.login.LoginConfiguration
import core.game.node.entity.player.link.quest.Quest
import core.game.node.entity.player.link.quest.QuestRepository
import core.game.system.SystemLogger
import io.github.classgraph.ClassGraph
import io.github.classgraph.ClassInfo
import plugin.activity.ActivityManager
import plugin.activity.ActivityPlugin
import plugin.dialogue.DialoguePlugin
import java.util.*
import java.util.function.Consumer
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType

/**
 * Represents a class used to handle the loading of all plugins.
 * @author Ceikry
 */
object PluginManager {
    var disabledPlugins = HashMap<String, Boolean>()
    /**
     * The amount of plugins loaded.
     */
    var amountLoaded = 0
        private set

    /**
     * The currently loaded plugin names.
     */
    private var loadedPlugins: MutableList<String>? = ArrayList()

    /**
     * The last loaded plugin.
     */
    private val lastLoaded: String? = null

    /**
     * Initializes the plugin manager.
     */
	@JvmStatic
	fun init() {
        try {
            load()
            loadedPlugins!!.clear()
            loadedPlugins = null
            SystemLogger.log("Initialized $amountLoaded plugins...")
        } catch (t: Throwable) {
            SystemLogger.log("Error initializing Plugins -> " + t.localizedMessage + " for file -> " + lastLoaded)
            t.printStackTrace()
        }
    }

    fun load() {
        val result = ClassGraph().enableClassInfo().enableAnnotationInfo().scan()
        result.getClassesWithAnnotation("core.plugin.InitializablePlugin").forEach(Consumer { p: ClassInfo ->
            try {
                definePlugin(p.loadClass().newInstance() as Plugin<JvmType.Object>)
            } catch (e: InstantiationException) {
                e.printStackTrace()
            } catch (e: IllegalAccessException) {
                e.printStackTrace()
            }
        })
    }

    /**
     * Defines a list of plugins.
     * @param plugins the plugins.
     */
    @JvmStatic
    fun definePlugins(vararg plugins: Plugin<*>) {
        val pluginsLength = plugins.size
        for (i in 0 until pluginsLength) {
            val p = plugins[i]
            definePlugin(p)
        }
    }

    /**
     * Defines the plugin.
     * @param plugin The plugin.
     */
	@JvmStatic
	fun definePlugin(plugin: Plugin<*>) {
        try {
            var manifest = plugin.javaClass.getAnnotation(PluginManifest::class.java)
            if (manifest == null) {
                manifest = plugin.javaClass.superclass.getAnnotation(PluginManifest::class.java)
            } else {
                if (disabledPlugins[manifest.name] != null) {
                    return
                }
            }
            if (manifest == null || manifest.type == PluginType.ACTION) {
                plugin.newInstance(null)
            } else {
                when (manifest.type) {
                    PluginType.DIALOGUE -> (plugin as DialoguePlugin).init()
                    PluginType.ACTIVITY -> ActivityManager.register(plugin as ActivityPlugin)
                    PluginType.LOGIN -> LoginConfiguration.getLoginPlugins().add(plugin as Plugin<Any?>)
                    PluginType.QUEST -> {
                        plugin.newInstance(null)
                        QuestRepository.register(plugin as Quest)
                    }
                    else -> println("Manifest: " + manifest.type)
                }
            }
            amountLoaded++
        } catch (e: Throwable) {
            e.printStackTrace()
        }
    }
}
