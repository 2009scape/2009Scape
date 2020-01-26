package plugin.dialogue;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.node.entity.player.Player;

/**
 * Represents the ellamaria dialogue plugin.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class EllamariaDialogue extends DialoguePlugin {

	/**
	 * Constructs a new {@code EllamariaDialogue} {@code Object}.
	 */
	public EllamariaDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code EllamariaDialogue} {@code Object}.
	 * @param player the player.
	 */
	public EllamariaDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new EllamariaDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "What's going on here? I see a lot of farming patches", "with nothing growing in them.");
		stage = 0;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "No, one has just had them installed. One has had the", "most marvellous idea to bring renewed happiness to", "one's own deatest husband.");
			stage = 1;
			break;
		case 1:
			interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "One? I'm not sure I understand you-");
			stage = 2;
			break;
		case 2:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Oh dear - the common classes, oh how they fill one with", "intolerable levels of exasperation, I swear to the most", "true.");
			stage = 3;
			break;
		case 3:
			interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Ri-ight ... if you say so, my lady. I'll be off then, if you", "don't mind.");
			stage = 4;
			break;
		case 4:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Yes, be off with you, before I call the guards.");
			stage = 5;
			break;
		case 5:
			interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "No problem, sorry to have bothered you.");
			stage = 6;
			break;
		case 6:// TODO: ques.t
			end();
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 2581 };
	}
}
