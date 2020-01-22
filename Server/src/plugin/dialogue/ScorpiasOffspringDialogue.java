package plugin.dialogue;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.node.entity.player.Player;

/**
 * Dialogue for the boss pet, Scorpia JR
 * @author Splinter
 * @version 1.0
 */
@InitializablePlugin
public final class ScorpiasOffspringDialogue extends DialoguePlugin {

	/**
	 * Constructs a new {@code ChaosElementalJRDialogue} {@code Object}.
	 */
	public ScorpiasOffspringDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code ChaosElementalJRDialogue} {@code Object}.
	 * @param player the player.
	 */
	public ScorpiasOffspringDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new ScorpiasOffspringDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "At night time, if I were to hold ultraviolet", "light over you, would you glow?");
		stage = 0;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Two things wrong there, human.");
			stage = 1;
			break;
		case 1:
			interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Oh?");
			stage = 2;
			break;
		case 2:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "One, when has it ever been night time here?");
			stage = 3;
			break;
		case 3:
			npc("Two, when have you ever seen ultraviolet light", "around here?");
			stage = 4;
			break;
		case 4:
			npc("In answer to your question though.","Yes I, like every scorpion, would glow.");
			stage = 5;
			break;
		case 5:
			end();
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 8598 };
	}
}
