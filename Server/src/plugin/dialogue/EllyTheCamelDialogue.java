package plugin.dialogue;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.game.node.entity.player.Player;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.tools.RandomFunction;

/**
 * Represents the elly the camel dialogue plugin.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class EllyTheCamelDialogue extends DialoguePlugin {

	/**
	 * Constructs a new {@code EllyTheCamelDialogue} {@code Object}.
	 */
	public EllyTheCamelDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code EllyTheCamelDialogue} {@code Object}.
	 * @param player the player.
	 */
	public EllyTheCamelDialogue(Player player) {
		super(player);
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			end();
			player.getPacketDispatch().sendMessage("The camel tries to stomp on your foot, but you pull it back quickly.");
			break;
		}
		return true;
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new EllyTheCamelDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		int rand = RandomFunction.random(1, 3);
		switch (rand) {
		case 1:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "If I go near that camel, it'll probably", "bite my hand off.");
			stage = 0;
			break;
		case 2:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "I wonder if that camel has fleas...");
			stage = 0;
			break;
		case 3:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "I wonder if that camel has fleas...");
			stage = 0;
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 2810, 2812 };
	}
}
