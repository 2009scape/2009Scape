package plugin.dialogue;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.content.skill.Skills;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.game.node.entity.player.Player;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.world.update.flag.context.Animation;

/**
 * Represents the jaraah dialogue plugin.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class JaraahDialogue extends DialoguePlugin {

	/**
	 * Represents the animation to use.
	 */
	private static final Animation ANIMATION = new Animation(881);

	/**
	 * Constructs a new {@code JaraahDialogue} {@code Object}.
	 */
	public JaraahDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code JaraahDialogue} {@code Object}.
	 * @param player the player.
	 */
	public JaraahDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new JaraahDialogue(player);
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
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "What? Can't you see I'm busy?!");
			stage = 1;
			break;
		case 1:
			interpreter.sendOptions("Select an Option", "Can you heal me?", "You must see some gruesome things?", "Why do they call you 'The Butcher'?");
			stage = 2;
			break;
		case 2:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Can you heal me?");
				stage = 10;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "You must see some gruesome things?");
				stage = 20;
				break;
			case 3:
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Why do they call you 'The Butcher'?");
				stage = 30;
				break;
			}
			break;
		case 10:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "OK. This will hurt you more than it will me.");
			stage = 11;
			break;
		case 11:
			npc.animate(ANIMATION);
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
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "It's a gruesome business and with the tools they give", "me it gets more gruesome before it gets better!");
			stage = 21;
			break;
		case 21:
			end();
			break;
		case 30:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "I often have to amputate peoples limbs!");
			stage = 31;
			break;
		case 31:
			end();
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 962 };
	}
}
