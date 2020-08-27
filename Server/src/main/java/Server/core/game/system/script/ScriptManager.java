package core.game.system.script;

import core.ServerConstants;
import core.game.system.SystemLogger;
import core.game.world.GameWorld;

import java.io.File;

/**
 * A class used to manage the loading of scripts.
 * @author 'Vexia
 */
public final class ScriptManager {

	/**
	 * The amount of scripts loaded.
	 */
	private static int amount;

	/**
	 * Method used to load the script manager.
	 * @param args the arguments.
	 */
	public static void main(String... args) {
		load();
	}

	/**
	 * Runs the script and returns the current script context after executing.
	 * @param context The script to run.
	 * @param args The arguments.
	 * @return The last script context executed.
	 */
	public static ScriptContext run(ScriptContext context, Object... args) {
		ScriptContext ctx = context;
		do {
			ctx = ctx.run(args);
		} while (ctx != null && ctx.isInstant());
		return ctx;
	}

	/**
	 * Runs the script and returns the current script context after executing.
	 * @param context The script to run.
	 * @param name The method name.
	 * @return The method script context.
	 */
	public static ScriptContext getMethod(ScriptContext context, String name) {
		ScriptContext ctx = context;
		while (ctx != null) {
			if (ctx.getCondition() != null) {
				context = getMethod(ctx.getCondition(), name);
				if (context != null) {
					return context;
				}
			}
			if (ctx.getName().equals(name)) {
				// System.out.println(ctx + ", " +
				// Arrays.toString(ctx.getParameters()));
				return ctx;
			}
			ctx = ctx.getInstruction();
		}
		return null;
	}

	/**
	 * Initiates the chain reaction of script loading.
	 */
	public static void load() {
		amount = 0;
		load(new File(ServerConstants.SCRIPTS_PATH));
		SystemLogger.log("Parsed " + amount + " " + GameWorld.getName() + " script" + (amount == 1 ? "" : "s") + "...");
	}

	/**
	 * Method used to load a script by its path.
	 * @param path the path.
	 */
	public static void load(final String path) {
		load(new File(path));
	}

	/**
	 * Loads scripts from a directory.
	 * @param directory the directory.
	 * @throws Throwable the throwable.
	 */
	public static void load(final File directory) {
		try {
			for (File file : directory.listFiles()) {
				if (file.getName().equals(".DS_Store")) {
					continue;
				}
				//Files to EXCLUDE from loading
				if (file.getName().equals("simon_templeton.asc") || file.getName().equals("ardougne_baker.asc") || file.getName().equals("anna_isaakson.asc")){
					continue;
				}
				if (file.isDirectory()) {
					load(file);
					continue;
				}
//				ScriptContext context = ScriptCompiler.parseRaw(file);
//				if (ScriptCompiler.getBuilder() != null) {
//					ScriptCompiler.getBuilder().configureScript(context);
//				}
				amount++;
			}
		} catch (Throwable e) {
			e.printStackTrace();
			SystemLogger.error("Error loading at directory - " + directory + "!");
		}
	}

}
