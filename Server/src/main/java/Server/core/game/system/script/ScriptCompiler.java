package core.game.system.script;

import core.ServerConstants;
import plugin.ai.AIPBuilder;
import plugin.ai.AIPlayer;
import core.game.system.script.exc.InvalidGOTOException;
import core.game.system.script.exc.InvalidInstructionException;
import core.game.system.script.exc.InvalidInterpreterException;
import core.game.system.script.exc.ScriptException;
import core.game.world.GameWorld;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Used for compiling scripts.
 * @author Emperor
 */
public final class ScriptCompiler {

	/**
	 * The current line index.
	 */
	private static int lineId;

	/**
	 * The mapping of methods.
	 */
	private static Map<String, MethodCall> methodScripts;

	/**
	 * The mapping of method calls.
	 */
	private static Map<ScriptContext, MethodCall> methodCalls;

	/**
	 * The mapping of fields.
	 */
	private static Map<String, Object> fields;

	/**
	 * The mapping of field calls.
	 */
	@SuppressWarnings("unused")
	private static Map<ScriptContext, String> fieldCalls;

	/**
	 * The current builder.
	 */
	private static ScriptBuilder builder;

	/**
	 * The instructions.
	 */
	private static Map<String, ScriptContext> instructions;

	/**
	 * The main method.
	 * @param args The arguments cast on runtime.
	 */
	public static void main(String... args) {
		try {
			GameWorld.prompt(false);
			ScriptContext context = parseRaw(new File(ServerConstants.DIALOGUE_SCRIPTS_PATH + "test.asc"));
			AIPlayer player = AIPBuilder.create(null);
			context.execute(player);
			ScriptManager.run(context, player);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		System.exit(0);
	}

	/**
	 * Loads the instructions.
	 * @throws Throwable When an exception occurs.
	 */
	public static void loadInstructions() throws Throwable {
		if (instructions != null) {
			return;
		}
		instructions = new HashMap<>();
		for (File file : new File("core/game/system/script/context/").listFiles()) {
			String name = file.getName();
			if (name.contains("$")) {
				continue;
			}
			Class<?> context = Class.forName("org.crandor.game.system.script.context." + name.replace(".class", ""));
			Object object = context.newInstance();
			if (object instanceof ScriptContext) {
				ScriptContext script = (ScriptContext) object;
				instructions.put(script.getName(), script);
			}
		}
	}

	/**
	 * Parses a raw script
	 * @param file The script file.
	 * @throws Throwable When an exception occurs.
	 */
	public static ScriptContext parseRaw(File file) throws Throwable {
		loadInstructions();
		BufferedReader br = new BufferedReader(new FileReader(file));
		lineId = -1;
		String line;
		builder = null;
		methodScripts = new HashMap<>();
		methodCalls = new HashMap<>();
		fields = new HashMap<>();
		try {
			Queue<String> rawScript = new LinkedList<>();
			boolean first = true;
			while ((line = br.readLine()) != null) {
				lineId++;
				StringBuilder sb = formatArgument(new StringBuilder(line));
				int commentIndex = sb.toString().indexOf("//");
				if (commentIndex > -1) {
					sb.setLength(commentIndex);
				}
				if (first && sb.length() > 0) {
					first = false;
					if (line.charAt(0) == '@') {
						builder = createBuilder(sb);
						continue;
					}
				}
				rawScript.add(sb.toString());

			}
			lineId = 0;
			if (builder != null) {
				lineId++;
			}
			line = rawScript.poll();
			if (line == null) {
				br.close();
				throw new ScriptException("ScriptAPI " + file.getName() + " is empty!", -1);
			}
			br.close();
			ScriptContext context = parseScript(line, rawScript, new ScriptEntry(null, null, null, false), null);
			if (context != null) {
				for (ScriptContext caller : methodCalls.keySet()) {
					MethodCall call = methodCalls.get(caller);
					MethodCall method = methodScripts.get(call.getMethodName());
					if (method == null) {
						throw new InvalidGOTOException(call.getMethodName() + "", -1);
					}
					ScriptContext script = method.getScript();
					if (call.getArguments() != null && call.getArguments().length() > 0) {
						String[] parameterNames = getArguments(method.getArguments());
						Object[] values = getParameters(call.getArguments(), 0);
						if (values.length != parameterNames.length) {
							throw new IllegalArgumentException("Not enough parameters name=" + call.getMethodName() + "!");
						}
						for (int i = 0; i < parameterNames.length; i++) {
							fields.put(parameterNames[i], values[i]);
						}
						script = checkScriptParameters(script, caller, null);
					}
					script.setName(call.getMethodName());
					caller.setInstruction(script);
				}
			}
			return context;
		} catch (ScriptException e) {
			e.printStackTrace();
		} catch (Throwable t) {
			br.close();
			throw new ScriptException("Error parsing line " + lineId + "!", t);
		}
		br.close();
		return null;
	}

	/**
	 * Checks for parameters.
	 * @param script The script to check.
	 * @param original The original script, to prevent infinite looping.
	 */
	private static ScriptContext checkScriptParameters(ScriptContext script, ScriptContext last, ScriptContext original) {
		Object[] copy = null;
		for (int i = 0; i < script.parameters.length; i++) {
			Object object = script.parameters[i];
			if (object instanceof ParamCall) {
				if (copy == null) {
					copy = Arrays.copyOf(script.parameters, script.parameters.length);
				}
				ParamCall call = (ParamCall) object;
				copy[i] = fields.get(call.getParameter());
				if (copy[i] == null) {
					System.out.println("Could not find field " + call.getParameter());
				}
			}
		}
		if (copy == null) {
			copy = script.parameters;
		}
		ScriptContext context = script.parse(copy);
		if (script == last.getCondition()) {
			last.setCondition(context);
		} else {
			last.setInstruction(context);
		}
		context.setCondition(script.getCondition());
		context.setInstruction(script.getInstruction());
		if (original == null) {
			original = context;
		}
		script = context;
		if (script.getCondition() != null && script.getCondition() != original) {
			checkScriptParameters(script.getCondition(), script, original);
		}
		if (script.getInstruction() != null && script.getInstruction() != original) {
			checkScriptParameters(script.getInstruction(), script, original);
		}
		return script;
	}

	/**
	 * Parses the script.
	 * @param line The first line the parse.
	 * @param rawScript The script data queue.
	 * @param script The main script context.
	 * @param method If the script is being parsed for a method.
	 * @throws IOException When an I/O exception occurs.
	 */
	private static ScriptContext parseScript(String line, Queue<String> rawScript, ScriptEntry script, ScriptContext previous) throws Throwable {
		ScriptContext condition = null;
		ScriptContext current = null;
		InstructionType type = null;
		boolean main = script.getName() == null;
		stop: while (line != null) {
			if (main) {
				lineId++;
			}
			if (line.length() < 1) {
				line = rawScript.poll();
				continue;
			}
			char c = line.charAt(0);
			Object[] ctx = parse(InstructionType.forIndicator(c), line);
			type = (InstructionType) ctx[0];
			String name = (String) ctx[1];
			String arguments = (String) ctx[2];
			boolean bracket = (Boolean) ctx[3];
			switch (type) {
			case LOCAL_METHOD:
			case METHOD:
				Queue<String> queue = new LinkedList<>();
				queue.add(Boolean.toString(bracket));
				String data;
				int bracketCount = bracket ? 1 : 0;
				while ((data = rawScript.poll()) != null) {
					if (main) {
						lineId++;
					}
					c = data.length() > 0 ? data.charAt(0) : 'f';
					queue.add(data);
					if (data.indexOf('{') > -1) {
						bracketCount++;
					}
					if (parse(InstructionType.forIndicator(c), data)[0] == InstructionType.END_BRACKET) {
						bracketCount--;
					}
					if (bracketCount < 1) {
						break;
					}
				}
				queue.poll();
				ScriptContext context = parseScript(queue.poll(), queue, new ScriptEntry(null, null, name, bracket), current);
				if (type == InstructionType.METHOD) {
					methodScripts.put(name, new MethodCall(name, arguments, context));
				} else {
					handleLocalMethod(name, context, current);
				}
				break;
			case CONDITION:
				context = getInstruction(name, arguments);
				if (context == null) {
					throw new InvalidInstructionException(name, lineId);
				}
				if (current != null) {
					current.setCondition(context);
				} else {
					condition = context;
				}
				script = new ScriptEntry(context, script, null, bracket);
				current = context;
				break;
			case INSTRUCTION:
			case GOTO:
				if (type == InstructionType.GOTO) {
					if (name == null) {
						name = arguments;
						arguments = null;
					}
					methodCalls.put(current, new MethodCall(name, arguments));
					if (!script.isBracket() && script.getPreviousEntry() != null) {
						script = script.getPreviousEntry();
						current = script.getCurrent();
						while (current.getInstruction() != null) {
							current = current.getInstruction();
						}
					}
					break;
				} else {
					context = getInstruction(name, arguments);
					if (context == null) {
						throw new InvalidInstructionException(name + " - " + arguments, lineId);
					}
				}
				if (script.getCurrent() == null) {
					current = context;
					script.setCurrent(context);
					context.setCondition(condition);
					condition = null;
					break;
				}
				current.setInstruction(context);
				current = context;
				if (!script.isBracket() && script.getPreviousEntry() != null) {
					script = script.getPreviousEntry();
					current = script.getCurrent();
					while (current.getInstruction() != null) {
						current = current.getInstruction();
					}
				}
				break;
			case END_BRACKET:
				if (script.isBracket() && script.getPreviousEntry() != null) {
					script = script.getPreviousEntry();
					current = script.getCurrent();
					while (current.getInstruction() != null) {
						current = current.getInstruction();
					}
					break;
				}
				break stop;
			}
			line = rawScript.poll();
		}
		if (script.getPreviousEntry() != null) {
			System.err.println("Error parsing " + type + " at line " + lineId + ": " + script.getName() + "!");
		}
		return script.getCurrent();
	}

	/**
	 * Handles a local method.
	 * @param name The name.
	 * @param context The method script.
	 * @param current The current script.
	 * @throws ScriptException When an exception occurs.
	 */
	private static void handleLocalMethod(String name, ScriptContext context, ScriptContext current) throws ScriptException {
		if (builder != null) {
			builder.handleLocalMethod(name, context, current);
		}
	}

	/**
	 * Gets the instruction for the given name.
	 * @param name The name.
	 * @param arguments The arguments.
	 * @return The script context.
	 */
	public static ScriptContext getInstruction(String name, String arguments) {
		ScriptContext context = instructions.get(name);
		if (context != null) {
			context = context.parse(getParameters(arguments, 0));
		}
		// ScriptContext context = builder != null ?
		// builder.newInstruction(name, arguments) : null;
		// if (context == null) {
		// String[] args = getArguments(arguments);
		// switch (name) {
		// case "startactivity":
		// name = args[0];
		// context = new StartActivityInstruction(name, getParameters(arguments,
		// 1));
		// break;
		// case "message":
		// for (int i = 0; i < args.length; i++) {
		// args[i] = getString(args[i]);
		// }
		// context = new PMessageInstruction(args);
		// break;
		// case "hasitem":
		// Item[] items = new Item[args.length / 2];
		// for (int i = 0; i < items.length; i++) {
		// items[i] = new Item(Integer.parseInt(args[i]),
		// Integer.parseInt(args[i + 1]));
		// }
		// context = new InvItemCondition(items);
		// break;
		// }
		// }
		return context;
	}

	/**
	 * Gets the parameters.
	 * @param arguments The argument line.
	 * @param offset The offset.
	 * @return The parameters.
	 */
	public static Object[] getParameters(String arguments, int offset) {
		String[] args = getArguments(arguments);
		Object[] params = new Object[args.length - offset];
		for (int i = offset; i < params.length; i++) {
			Object object = null;
			char c = args[i].charAt(0);
			if (c == '"') {
				object = getString(args[i]);
			} else if (Character.isDigit(c)) {
				object = Integer.parseInt(args[i]);
			} else if (args[i].equals("true")) {
				object = true;
			} else if (args[i].equals("false")) {
				object = false;
			} else {
				object = new ParamCall(args[i]);
			}
			params[i - offset] = object;
		}
		return params;
	}

	/**
	 * Gets the string without the surrounding "".
	 * @param string The string.
	 * @return The string without surrounding ""s.
	 */
	public static String getString(String string) {
		return string.substring(1, string.length() - 1);
	}

	/**
	 * Parses the script context.
	 * @param type The instruction type.
	 * @param line The line.
	 * @return The script context.
	 */
	private static Object[] parse(InstructionType type, String line) {
		int index = 1;
		if (type == null) {
			index = 0;
			type = InstructionType.INSTRUCTION;
		}
		String instructionName = null;
		String arguments = null;
		boolean bracketUsed = false;
		StringBuilder sb = new StringBuilder();
		boolean openingParenthese = false;
		boolean string = false;
		for (; index < line.length(); index++) {
			char ch = line.charAt(index);
			if (!string) {
				if (ch == ':' && !openingParenthese && type == InstructionType.INSTRUCTION) {
					type = InstructionType.METHOD;
					instructionName = formatArgument(sb).toString();
					sb = new StringBuilder();
					continue;
				}
				if (ch == '(') {
					openingParenthese = true;
					if (type != InstructionType.METHOD) {
						instructionName = formatArgument(sb).toString();
					}
					sb = new StringBuilder();
					continue;
				}
				if (ch == ')') {
					arguments = formatArgument(sb).toString();
					sb = new StringBuilder();
					continue;
				}
				if (ch == '{') {
					if (type == InstructionType.LOCAL_METHOD) {
						instructionName = formatArgument(sb).toString();
						sb = new StringBuilder();
					}
					bracketUsed = true;
					break;
				}
			}
			if (ch == '"') {
				string = !string;
			}
			sb.append(ch);
		}

		if (arguments == null) {
			arguments = formatArgument(sb).toString();
		}
		return new Object[] { type, instructionName, arguments, bracketUsed };
	}

	/**
	 * Creates a builder from the arguments.
	 * @param sb The arguments string builder.
	 * @return The script builder.
	 * @throws InvalidInterpreterException When the interpreter could not be
	 * found.
	 */
	private static ScriptBuilder createBuilder(StringBuilder sb) throws InvalidInterpreterException {
		String[] args = sb.substring(1).toString().replaceFirst(" ", ";").split(";");
		ScriptType i = ScriptType.forTag(args[0]);
		if (i == null) {
			throw new InvalidInterpreterException();
		}
		StringBuilder s = new StringBuilder();
		for (int j = 1; j < args.length; j++) {
			if (j != 1) {
				s.append(";");
			}
			s.append(args[j]);
		}
		Object[] builderArguments = i.getBuilder().parseArguments(formatArgument(s).toString());
		return i.getBuilder().create(builderArguments);
	}

	/**
	 * Gets the arguments.
	 * @param argumentLine The argument line.
	 * @return The arguments.
	 */
	public static String[] getArguments(String argumentLine) {
		String[] args = new String[20];
		int size = 0;
		StringBuilder sb = new StringBuilder();
		boolean string = false;
		for (int i = 0; i < argumentLine.length(); i++) {
			char c = argumentLine.charAt(i);
			if (c == '"') {
				string = !string;
			}
			if (!string) {
				if (c == ',') {
					args[size++] = sb.toString();
					sb = new StringBuilder();
					if (size == args.length) {
						args = Arrays.copyOf(args, args.length * 2);
					}
					continue;
				}
				if (c == ' ') {
					continue;
				}
			}
			sb.append(c);
		}
		if (sb.length() > 0) {
			args[size++] = sb.toString();
		}
		if (size == 0) {
			return null;
		}
		return Arrays.copyOf(args, size);
	}

	/**
	 * Formats an argument.
	 * @param sb The argument string.
	 * @return The formatted argument.
	 */
	public static StringBuilder formatArgument(String string) {
		return formatArgument(new StringBuilder(string));
	}

	/**
	 * Formats an argument.
	 * @param sb The argument string builder.
	 * @return The formatted argument.
	 */
	public static StringBuilder formatArgument(StringBuilder sb) {
		while (sb.length() > 0 && (sb.charAt(0) == ' ' || sb.charAt(0) == '	')) {
			sb.deleteCharAt(0);
		}
		char c;
		while (sb.length() > 0 && ((c = sb.charAt(sb.length() - 1)) == ' ' || c == '	')) {
			sb.deleteCharAt(sb.length() - 1);
		}
		return sb;
	}

	/**
	 * Gets the script builder used.
	 * @return The script builder.
	 */
	public static ScriptBuilder getBuilder() {
		return builder;
	}

}