package plugin.dialogue;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.content.global.Skillcape;
import org.crandor.game.content.skill.Skills;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.game.node.entity.player.Player;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.world.update.flag.context.Animation;

/**
 * Represents the dialogue plugin used for surgeon general tafani.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class SurgeonGeneralTafani extends DialoguePlugin {

	/**
	 * Constructs a new {@code SurgeonGeneralTafani} {@code Object}
	 */
	public SurgeonGeneralTafani() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code SurgeonGeneralTafani} {@code Object}.
	 * @param player the player.
	 */
	public SurgeonGeneralTafani(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new SurgeonGeneralTafani(player);
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
			if (Skillcape.isMaster(player, Skills.HITPOINTS)) {
				interpreter.sendOptions("Select an Option", "Can you heal me?", "Do you see a lot of injured fighters?", "Do you come here often?", "Can I buy a Skillcape of Hitpoints from you?");
				stage = 80;
			} else {
				interpreter.sendOptions("Select an Option", "Can you heal me?", "Do you see a lot of injured fighters?", "Do you come here often?", "Where can I get a cape like yours?");
				stage = 2;
			}
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
			case 4:
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Where can I get a cape like yours?");
				stage = 40;
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
		case 40:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Well, these capes are only available for people who have", "achieved a Hitpoint level of 99. You should go and train", "some more and come back to me when your Hitpoints", "are higher.");
			stage = 41;
			break;
		case 41:
			end();
			break;
		case 80:
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
			case 4:
				player("Can I buy a Skillcape of Hitpoints from you?");
				stage = 81;
				break;
			}
			break;
		case 81:
			npc("Why, certainly my friend. However, owning such an item", "makes you part of an elite group and that privilege will", "cost you 99000 coins.");
			stage = 82;
			break;
		case 82:
			options("Sorry, that's much too pricey.", "Sure, that's not too expensive for such a magnificent cape.");
			stage = 83;
			break;
		case 83:
			switch (buttonId) {
			case 1:
				player("Sorry, that's much too pricey.");
				stage = 84;
				break;
			case 2:
				player("Sure, that's not too expensive for such a magnificent", "cape.");
				stage = 86;
				break;
			}
			break;
		case 84:
			npc("I'm sorry you feel that way. Still if you change", "your mind...");
			stage = 85;
			break;
		case 85:
			end();
			break;
		case 86:
			if (Skillcape.purchase(player, Skills.HITPOINTS)) {
				npc("There you go! I hope you enjoy it.");
			}
			stage = 87;
			break;
		case 87:
			end();
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 961 };
	}
}
