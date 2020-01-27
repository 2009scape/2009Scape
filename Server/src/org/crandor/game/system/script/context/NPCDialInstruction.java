package org.crandor.game.system.script.context;

import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.system.script.ParamCall;
import org.crandor.game.system.script.ScriptCompiler;
import org.crandor.game.system.script.ScriptContext;

import java.util.Arrays;

/**
 * The NPC dialogue instruction.
 * @author Emperor
 */
public final class NPCDialInstruction extends ScriptContext {

	/**
	 * The messages.
	 */
	private final String[] messages;

	/**
	 * The facial expression.
	 */
	private final int expression;

	/**
	 * If the continue option should be hidden.
	 */
	private boolean hideContinue;

	/**
	 * The NPC id to show for this dialogue.
	 */
	private int forceNPCId;

	/**
	 * Constructs a new {@code PDialInstruction} {@code Object}.
	 */
	public NPCDialInstruction() {
		this(null, -1);
	}

	/**
	 * Constructs a new {@code PDialInstruction} {@code Object}.
	 * @param messages The messages.
	 * @param expression The facial expression animation id.
	 */
	public NPCDialInstruction(String[] messages, int expression) {
		super("npc");
		super.setInstant(false);
		this.messages = messages;
		this.expression = expression;
	}

	@Override
	// Arguments should be [player, npc]!
	public boolean execute(Object... args) {
		Player player = (Player) args[0]; // 1 = NPC
		int npcId = -1;
		if (args[1] instanceof NPC) {
			npcId = ((NPC) args[1]).getId();
		} else {
			npcId = (Integer) args[1];
		}
		String[] messages = new String[this.messages.length];
		for (int i = 0; i < messages.length; i++) {
			String message = this.messages[i];
			if (message.contains(">playername<")) {
				message = message.replace(">playername<", player.getUsername());
			}
			messages[i] = message;
		}
		player.getDialogueInterpreter().sendDialogues(forceNPCId == -1 ? npcId : forceNPCId, expression, hideContinue, messages);
		player.getDialogueInterpreter().setDialogueStage(this);
		return true;
	}

	@Override
	public ScriptContext parse(Object... params) {
		String[] messages = new String[6];
		int messageIndex = 0;
		int expression = FacialExpression.HALF_GUILTY.getAnimationId();
		int npcId = -1;
		boolean hide = false;
		for (int i = 0; i < params.length; i++) {
			Object o = params[i];
			if (o instanceof ParamCall) {
				String param = ((ParamCall) o).getParameter();
				if (param.startsWith("anim:")) {
					String expr = ScriptCompiler.formatArgument(param.substring("anim:".length())).toString();
					FacialExpression exp = null;
					for (FacialExpression e : FacialExpression.values()) {
						if (e.name().equals(expr)) {
							exp = e;
							break;
						}
					}
					if (exp != null) {
						expression = exp.getAnimationId();
					} else {
						expression = Integer.parseInt(expr);
					}
				} else if (param.startsWith("hidecont:")) {
					hide = Boolean.parseBoolean(ScriptCompiler.formatArgument(param.substring("hidecont:".length())).toString());
				} else if (param.startsWith("npc:")) {
					npcId = Integer.parseInt(ScriptCompiler.formatArgument(param.substring("npc:".length())).toString());
				}
			} else if (o instanceof String) {
				messages[messageIndex++] = (String) o;
			}
		}
		if (messageIndex != messages.length) {
			messages = Arrays.copyOf(messages, messageIndex);
		}
		NPCDialInstruction context = new NPCDialInstruction(messages, expression);
		context.forceNPCId = npcId;
		context.hideContinue = hide;
		context.parameters = params;
		return context;
	}

}