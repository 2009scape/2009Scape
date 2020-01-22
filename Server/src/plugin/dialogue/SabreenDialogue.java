package plugin.dialogue;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.content.skill.Skills;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.game.node.entity.player.Player;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.world.update.flag.context.Animation;

/**
 * @author 'Vexia
 */
@InitializablePlugin
public class SabreenDialogue extends DialoguePlugin {

	public SabreenDialogue() {

	}

	public SabreenDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {

		return new SabreenDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Hi!");
		stage = 0;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Hi. How can I help?");
			stage = 1;
			break;
		case 1:
			interpreter.sendOptions("Select an Option", "Can you heal me?", "Do you see a lot of injured fighters?", "Do you come here often?");
			stage = 2;
			break;
		case 2:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Can you heal me?");
				stage = 10;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Do you see a lot of injured fighters?");
				stage = 30;
				break;
			case 3:
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Do you come here often?");
				stage = 20;
				break;

			}
			break;
		case 10:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Of course!");
			stage = 11;
			break;
		case 11:
			npc.animate(new Animation(881));
			if (player.getSkills().getLifepoints() == player.getSkills().getStaticLevel(Skills.HITPOINTS)) {
				interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "You look healthy to me!");
			} else {
				player.getSkills().heal(player.getSkills().getStaticLevel(Skills.HITPOINTS));
				interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "There you go!");
			}
			stage = 12;
			break;
		case 12:
			end();
			break;
		case 20:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "I work here, so yes!");
			stage = 21;
			break;
		case 21:
			end();
			break;
		case 30:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Yes I do. Thankfully we can cope with almost aything.", "Jaraah really is a wonderful surgeon, this methods are a", "little unorthodox but he gets the job done.");
			stage = 31;
			break;
		case 31:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "I shouldn't tell you this but his nickname is 'The", "Butcher'.");
			stage = 32;
			break;
		case 32:
			interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "That's reassuring.");
			stage = 33;
			break;
		case 33:
			end();
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 960 };
	}
}
