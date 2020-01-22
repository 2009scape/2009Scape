package plugin.dialogue;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.content.skill.Skills;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.node.entity.player.Player;

/**
 * Represents the ghommal dialogue plugin.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class GhommalDialogue extends DialoguePlugin {

	/**
	 * Constructs a new {@code GhommalDialogue} {@code Object}.
	 */
	public GhommalDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code GhommalDialogue} {@code Object}.
	 * @param player the player.
	 */
	public GhommalDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new GhommalDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		if (player.getSkills().getLevel(Skills.ATTACK) + player.getSkills().getLevel(Skills.STRENGTH) >= 130 || player.getSkills().getLevel(Skills.ATTACK) == 99 || player.getSkills().getLevel(Skills.STRENGTH) == 99) {
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Ghommal welcome you to Warrior Guild!");
			stage = 10;
		} else {
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "You not pass. You too weedy.");
			stage = 0;
		}
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "What? But I'm a warrior!");
			stage = 1;
			break;
		case 1:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Heehee... he say he warrior... I not heard that one", "for... at leas' 5 minutes!");
			stage = 2;
			break;
		case 2:
			interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Go on, let me in, you know you want to. I could...", "make it worth your while...");
			stage = 3;
			break;
		case 3:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "No! You is not a strong warrior, you not enter till you", "bigger, Ghommal does not takes bribes.");
			stage = 4;
			break;
		case 4:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Ghommal stick to Warrior's Code of Honour. When", "you a bigger, stronger warriror, you come back.");
			stage = 5;
			break;
		case 5:
			end();
			break;
		case 10:
			interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Umm...thank you, I think.");
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
		return new int[] { 4285 };
	}
}
