package core.game.node.entity.npc.city.alkharid;

import core.game.content.dialogue.DialoguePlugin;
import core.game.content.dialogue.FacialExpression;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.plugin.Initializable;
import core.tools.RandomFunction;

/**
 * Represents the dialogue plugin used for al the camel.
 * @author 'Vexia
 * @version 1.0
 */
@Initializable
public final class AlTheCamelDialogue extends DialoguePlugin {

	/**
	 * Constructs a new {@code AlTheCamelDialogue} {@code Object}.
	 */
	public AlTheCamelDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code AlTheCamelDialogue} {@code Object}.
	 * @param player the player.
	 */
	public AlTheCamelDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new AlTheCamelDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		int rand = RandomFunction.random(1, 3);
		switch (rand) {
		case 1:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Mmm... Looks like that camel would make a nice kebab.");
			stage = 0;
			break;
		case 2:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "If I go near that camel, it'll probably bite my hand off.");
			stage = 0;
			break;
		case 3:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Mmm... Looks like that camel would make a nice kebab.");
			stage = 0;
			break;
		}
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			player.getPacketDispatch().sendMessage("The camel tries to stomp on your foot, but you pull it back quickly.");
			end();
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 2809 };
	}
}
