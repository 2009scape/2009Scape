package plugin.dialogue;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.content.skill.Skills;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.node.entity.player.Player;

/**
 * Reprepsents the dialogue plugin used to handle the brian orichard npc.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class BrianORichardDialogue extends DialoguePlugin {

	/**
	 * Constructs a new {@code BrianORichardDialogue} {@code Object}.
	 */
	public BrianORichardDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code BrianORichardDialogue}{@code Object}.
	 * @param player the player.
	 */
	public BrianORichardDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new BrianORichardDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Hi there, looking for a challenge are you?");
		stage = 1;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 1:
			interpreter.sendOptions("Select an Option", "Yes, actually, what've you got?", "What is this place?", "No thanks.");
			stage = 2;
			break;
		case 2:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Yes actually, what've you got?");
				stage = 10;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "What is this place?");
				stage = 20;
				break;
			case 3:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "No thanks.");
				stage = 30;
				break;
			}
			break;
		case 10:
			if (player.getSkills().getLevel(Skills.THIEVING) < 50) {
				interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Shame, I don't think I have anything for you. Train up", "your Thieving skill to at least 50 and I might be able to", "help you out.");
				stage = 11;
			} else {

			}
			break;
		case 11:
			end();
			break;
		case 20:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Ah welcome to my humble home, well actually it belongs", "to mummsie but she's getting on a bit so I look after", "the place for her.");
			stage = 21;
			break;
		case 21:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "So are you interested in a challenge?");
			stage = 22;
			break;
		case 22:
			interpreter.sendOptions("Select an Option", "Yes actually, what've you got?", "No thanks.");
			stage = 23;
			break;
		case 23:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Yes actually, what've you got?");
				stage = 24;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "No, thanks.");
				stage = 25;
				break;
			}
			break;
		case 24:
			if (player.getSkills().getLevel(Skills.THIEVING) < 50) {
				interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Shame, I don't think I have anything for you. Train up", "your Thieving skill to at least 50 and I might be able to", "help you out.");
				stage = 25;
			} else {

			}
			break;
		case 25:
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
		return new int[] { 2266 };
	}
}
