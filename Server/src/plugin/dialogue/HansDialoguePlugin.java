package plugin.dialogue;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.node.entity.player.Player;

/**
 * Represents the dialogue plugin used for the hans npc.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class HansDialoguePlugin extends DialoguePlugin {

	/**
	 * Constructs a new {@code HansDialoguePlugin} {@code Object}.
	 */
	public HansDialoguePlugin() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code HansDialoguePlugin} {@code Object}.
	 * @param player the player.
	 */
	public HansDialoguePlugin(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new HansDialoguePlugin(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Hello. What are you doing here?");
		stage = 0;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {

		switch (stage) {
		case 0:
			interpreter.sendOptions("Select an Option", "I'm looking for whoever is in charge of this place.", "I have come to kill everyone in this castle!", "I don't know. I'm lost. Where am I?", "Have you been here as long as me?");
			stage = 1;
			break;
		case 1:

			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Who, the Duke? He's in his study, on the first floor.");
				stage = 10;
				break;
			case 2:
				end();
				npc.moveStep();
				npc.sendChat("Help Help!");
				break;
			case 3:
				interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "You are in Lumbridge Castle.");
				stage = 30;
				break;
			case 4:
				interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "I've been patrolling this castle for years!");
				stage = 40;
				break;
			}

			break;
		case 40:
			interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "You must be old then?");
			stage = 41;
			break;
		case 41:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Haha, you could say I'm quite the veteran of these lands.", "Yes, I've been here a fair while...");
			stage = 42;
			break;
		case 42:
			end();
			break;
		case 10:
			end();
			break;
		case 30:
			end();
			break;
		}

		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 0 };
	}
}
