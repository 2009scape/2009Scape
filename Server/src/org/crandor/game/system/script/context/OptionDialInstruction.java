package org.crandor.game.system.script.context;

import org.crandor.game.node.entity.player.Player;
import org.crandor.game.system.script.ParamCall;
import org.crandor.game.system.script.ScriptCompiler;
import org.crandor.game.system.script.ScriptContext;

import java.util.Arrays;

/**
 * Sends an option dialogue.
 * @author Emperor
 */
public final class OptionDialInstruction extends ScriptContext {

	/**
	 * The title.
	 */
	private final String title;

	/**
	 * The options.
	 */
	private final String[] options;

	/**
	 * The option handlers.
	 */
	private final ScriptContext[] optionHandlers;

	/**
	 * Constructs a new {@code OptionDialInstruction} {@code Object}.
	 */
	public OptionDialInstruction() {
		this(null);
	}

	/**
	 * Constructs a new {@code OptionDialInstruction} {@code Object}.
	 * @param title The title.
	 * @param options The options.
	 */
	public OptionDialInstruction(String title, String... options) {
		super("option");
		super.setInstant(false);
		this.title = title;
		this.options = options;
		this.optionHandlers = new ScriptContext[options.length];
	}

	@Override
	public boolean execute(Object... args) {
		Player player = (Player) args[0];
		player.getDialogueInterpreter().sendOptions(title, options);
		player.getDialogueInterpreter().setDialogueStage(this);
		return true;
	}

	@Override
	public ScriptContext parse(Object... params) {
		String[] messages = new String[params.length];
		int messageIndex = 0;
		String title = null;
		for (int i = 0; i < params.length; i++) {
			Object o = params[i];
			if (o instanceof String) {
				messages[messageIndex++] = (String) o;
			} else if (o instanceof ParamCall) {
				String param = ((ParamCall) o).getParameter();
				if (param.startsWith("title:")) {
					title = ScriptCompiler.formatArgument(param.substring("title:".length())).toString();
				} else {
					messages[messageIndex++] = param;
				}
			}
		}
		if (messageIndex != messages.length) {
			messages = Arrays.copyOf(messages, messageIndex);
		}
		OptionDialInstruction context = new OptionDialInstruction(title, messages);
		for (int i = 0; i < context.optionHandlers.length; i++) {
			if (i < optionHandlers.length) {
				context.optionHandlers[i] = optionHandlers[i];
			}
		}
		context.parameters = params;
		return context;
	}

	@Override
	public ScriptContext create(String args) {
		System.out.println("OptionDialInstruction: " + args);
		ScriptContext context = super.create(args);

		if (context != null) {
			OptionDialInstruction other = (OptionDialInstruction) context;
			for (int i = 0; i < other.optionHandlers.length; i++) {
				if (i >= optionHandlers.length) {
					break;
				}
				other.optionHandlers[i] = optionHandlers[i];
			}
		}
		return context;
	}

	@Override
	public ScriptContext run(Object... args) {
		if (args.length < 3) {
			return null;
		}
		int option = (Integer) args[2] - 1;
		if (option > optionHandlers.length -1) {
			System.err.println("Error! Option out of bounds for option handlers.");
			return null;
		}
		ScriptContext context = optionHandlers[option];
		if (context == null) {
			System.err.println("No option handler found!");
			((Player) args[0]).getDialogueInterpreter().close();
			return null;
		}
		context.execute(args);
		return context;
	}

	/**
	 * Gets the optionHandlers.
	 * @return The optionHandlers.
	 */
	public ScriptContext[] getOptionHandlers() {
		return optionHandlers;
	}

}