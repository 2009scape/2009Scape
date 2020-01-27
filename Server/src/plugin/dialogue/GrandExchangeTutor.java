package plugin.dialogue;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.game.node.entity.player.Player;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.world.GameWorld;

/**
 * Represents the dialogue plugin used for the grand exchange tutor.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class GrandExchangeTutor extends DialoguePlugin {

	/**
	 * Constructs a new {@code GrandExchangeTutor} {@code Object}.
	 */
	public GrandExchangeTutor() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code GrandExchangeTutor} {@code Object}.
	 * @param player the player.
	 */
	public GrandExchangeTutor(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new GrandExchangeTutor(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "How can I help?");
		stage = 0;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			interpreter.sendOptions("Select an Option", "Can you teach me about the Grand Exchange?", "Where can I found out more info?", "I'm okay thanks.");
			stage = 1;
			break;
		case 1:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Can you teach me about the Grand Exchange?");
				stage = 10;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Where can I find more info?");
				stage = 20;
				break;
			case 3:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "I'm okay thanks.");
				stage = 30;
				break;
			}
			break;
		case 10:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Of course.");
			stage = 11;
			break;
		case 11:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "This is the Grand Exchange. You can tell us", "what you want to buy or sell, and for how much", "and we'll search for another player willing to do the trade!");
			stage = 12;
			break;
		case 12:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Let me describe the process in four steps:");
			stage = 13;
			break;
		case 13:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, getRed() + "Step 1</col>: You come here with items to sell or money to", "spend.");
			stage = 14;
			break;
		case 14:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, getRed() + "Step 2</col>: Speak with one of the clerks at the desk.", "They will help you set up your bid.");
			stage = 15;
			break;
		case 15:
			interpreter.sendDialogues(npc, null, "When you're setting up a bid we'll show you a", "guide price for each item. This is just a suggestion", "though: you can offer any amount you like.");
			stage = 16;
			break;
		case 16:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, getRed() + "Step 3</col>: The clerks will take the items or money off you", "and look for someone to complete the trade. This may be", "very fast, or it could take several days.");
			stage = 17;
			break;
		case 17:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, getRed() + "Step 4</col>: When the trade is complete, we will send you a", "message. You can collect your stuff by talking to the", "clerks or by visiting any banker in " + GameWorld.getName() + ".");
			stage = 18;
			break;
		case 18:
			interpreter.sendDialogues(npc, null, "There's plenty more information about the Grand", "Exchange, all of which can be found out from Brugsen", "Bursen, the guy with the megaphone. I would suggest", "you speak with him to fully get to grips with the Grand");
			stage = 19;
			break;
		case 19:
			interpreter.sendDialogues(npc, null, "Exchange. Good luck!");
			stage = 99;
			break;
		case 99:
			end();
			break;
		case 20:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Go and speak to Brugsen who's standing over", "there, closer to the building. He'll help you out.");
			stage = 31;
			break;
		case 30:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Fair enough.");
			stage = 31;
			break;
		case 31:
			end();
			break;
		}
		return true;
	}

	/**
	 * Gets the red color id.
	 * @return the color.
	 */
	public String getRed() {
		return "<col=8A0808>";
	}

	@Override
	public int[] getIds() {
		return new int[] { 6521 };
	}
}
