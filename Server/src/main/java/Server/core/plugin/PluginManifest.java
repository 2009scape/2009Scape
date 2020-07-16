package core.plugin;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Represents a plugin manifest.
 * @author Emperor
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface PluginManifest {

	/**
	 * Gets the plugin type.
	 * @return The plugin type.
	 */
	public PluginType type() default PluginType.ACTION;
	public String name() default "";
}