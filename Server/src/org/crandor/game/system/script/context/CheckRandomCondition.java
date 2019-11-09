package org.crandor.game.system.script.context;

import org.crandor.game.node.entity.Entity;
import org.crandor.game.system.script.ScriptContext;

/**
 * Used to check current random value (set by setrandom(value) instruction).
 * @author Emperor
 */
public final class CheckRandomCondition extends ScriptContext {

	/**
	 * Sets the value.
	 */
	private final int value;

	/**
	 * Constructs a new {@code CheckRandomCondition} {@code Object}.
	 */
	public CheckRandomCondition() {
		this(0);
	}

	/**
	 * Constructs a new {@code CheckRandomCondition} {@code Object}.
	 * @param value The value.
	 */
	public CheckRandomCondition(int value) {
		super("israndom");
		this.value = value;
	}

	@Override
	public ScriptContext parse(Object... params) {
		int value = 0;
		if (params[0] instanceof Integer) {
			value = (Integer) params[0];
		}
		CheckRandomCondition context = new CheckRandomCondition(value);
		context.parameters = params;
		return context;
	}

	@Override
	public boolean execute(Object... args) {
		System.out.println("CheckRandomCondition:" +  (String) ((Entity) args[0]).getAttribute("asc_random") );
		return ((Entity) args[0]).getAttribute("asc_random", 0) == value;
	}

}