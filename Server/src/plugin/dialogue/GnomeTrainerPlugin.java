package plugin.dialogue;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.game.node.entity.player.Player;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.tools.RandomFunction;

/**
 * Represents the dialogue plugin used for the gnome trainer.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class GnomeTrainerPlugin extends DialoguePlugin {

	/**
	 * Constructs a new {@code GnomeTrainerPlugin} {@code Object}.
	 */
	public GnomeTrainerPlugin() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code GnomeTrainerPlugin} {@code Object}.
	 * @param player the player.
	 */
	public GnomeTrainerPlugin(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new GnomeTrainerPlugin(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		int rand = RandomFunction.random(0, 3);
		switch (rand) {
		case 0:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Hello there.");
			stage = 0;
			break;
		case 1:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Hello, what is this place?");
			stage = 3;
			break;
		case 2:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Hello how are you?");
			stage = 7;
			break;
		case 3:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "This is fun!");
			stage = 10;
			break;
		}
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "This isn't a grannies' tea party, let's see some sweat", "human. Go! Go! Go!");
			stage = 1;
			break;
		case 1:
			end();
			break;
		case 3:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "This, my friend, is where we train. Here we improve", "out agility. It's an essential skill.");
			stage = 4;
			break;
		case 4:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "It looks easy enough.");
			stage = 5;
			break;
		case 5:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "If you complete the course in order from the slippery", "log to the end, your agility will increase much faster", "than by repeating just one obstacle.");
			stage = 6;
			break;
		case 6:
			end();
			break;
		case 7:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "I'm amazed by how much humans chat. The sign over", "there says training area, not pointless conversation area.");
			stage = 8;
			break;
		case 8:
			end();
			break;
		case 10:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "This is training soldier. If you want fun go make some", "cocktails.");
			stage = 11;
			break;
		case 11:
			end();
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 162 };
	}
}
