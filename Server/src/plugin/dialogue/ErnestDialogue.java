package plugin.dialogue;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.node.entity.player.Player;

/**
 * Represents the dialogue which handles the interaction with ernest.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class ErnestDialogue extends DialoguePlugin {

	/**
	 * Constructs a new {@code ErnestDialogue} {@code Object}.
	 */
	public ErnestDialogue() {
		/**
		 * empty
		 */
	}

	/**
	 * Constructs a new {@code ErnestDialogue} {@code Object}.
	 * @param player the player.
	 */
	public ErnestDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new ErnestDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Thank you sir. It was dreadfully irritating being a", "chicken. How can I ever thank you?");
		stage = 1;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 1:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Well a cash reward is always nice...");
			stage = 2;
			break;
		case 2:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Of course, of course.");
			stage = 3;
			break;
		case 3:
			npc.clear();
			end();// this will trigger close event.
			break;
		}
		return true;
	}

	@Override
	public boolean close() {
		finish();
		return super.close();
	}

	/**
	 * Method used to finish the quest.
	 */
	public void finish() {
		if (player.getQuestRepository().isComplete("Ernest the Chicken")) {
			npc.clear();
			return;
		}
		npc.clear();
		player.getQuestRepository().getQuest("Ernest the Chicken").finish(player);
	}

	@Override
	public int[] getIds() {
		return new int[] { 287 };
	}
}
