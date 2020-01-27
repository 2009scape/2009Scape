package plugin.dialogue;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.entity.player.link.quest.Quest;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.node.item.Item;

/**
 * Represents the charlie the tramp dialogue.
 * @author 'Vexia
 * @dtae 3/1/14
 */
@InitializablePlugin
public class CharlieTheTrampDialogue extends DialoguePlugin {

	/**
	 * Represents the quest.
	 */
	private Quest quest;

	/**
	 * Constructs a new {@code CharlieTheTrampDialogue} {@code Object}.
	 */
	public CharlieTheTrampDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code CharlieTheTrampDialogue} {@code Object}.
	 * @param player the player.
	 */
	public CharlieTheTrampDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new CharlieTheTrampDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		quest = player.getQuestRepository().getQuest("Shield of Arrav");
		switch (quest.getStage(player)) {
		default:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Spare some change guv?");
			stage = 0;
			break;
		}
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (quest.getStage(player)) {
		default:
			regular(buttonId);
			break;
		}
		return true;
	}

	/**
	 * Method used to handle the regular dial.
	 * @param buttonId the buttonid.
	 */
	public void regular(int buttonId) {
		switch (stage) {
		case 0:
			interpreter.sendOptions("Select an Option", "Who are you?", "Sorry, I haven't got any.", "Go get a job!", "Ok. Here you go.", "Is there anything down this alleyway?");
			stage = 1;
			break;
		case 1:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Who are you?");
				stage = 10;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Sorry, I haven't got any.");
				stage = 20;
				break;
			case 3:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Go get a job!");
				stage = 30;
				break;
			case 4:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Ok. Here you go.");
				stage = 40;
				break;
			case 5:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Is there anything down this alleyway?");
				stage = 50;
				break;
			}
			break;
		case 10:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Charles. Charles E. Trampin' at your service. Now, about", "that change you were going to give me...");
			stage = 0;
			break;
		case 20:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Thanks anyways!");
			stage = 21;
			break;
		case 21:
			end();
			break;
		case 30:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "You startin? I hope your nose falls off!");
			stage = 21;
			break;
		case 40:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Hey, thanks alot!");
			stage = 41;
			break;
		case 41:
			interpreter.sendOptions("Select an Option", "No problem.", "Don't I get some sort of quest hint or something now?");
			stage = 42;
			break;
		case 42:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "No problem.");
				stage = 180;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "So...don't I get some sort of quest hint or something now?");
				stage = 190;
				break;
			}
			break;
		case 180:
			end();
			if (player.getInventory().contains(995, 1))
				player.getInventory().remove(new Item(995, 1));
			else
				player.getPacketDispatch().sendMessage("You need one coin to give away.");
			break;
		case 190:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Huh? What do you mean? That wasn't why", " I asked you for money.");
			stage = 191;
			break;
		case 191:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "I just need to eat...");
			stage = 192;
			break;
		case 192:
			end();
			break;
		case 800:
			npc("The ruthless and notorious criminal gang known as the", "Black Arm Gang have their headquarters down there.");
			stage = 801;
			break;
		case 801:
			options("Thank you for the warning!", "Do you think they would let me join?");
			stage = 802;
			break;
		case 802:
			switch (buttonId) {
			case 1:
				player("Thanks for the warning!");
				stage = 803;
				break;
			case 2:
				player("Do you think they would let me join?");
				stage = 804;
				break;
			}
			break;
		case 803:
			npc("Don't worry about it.");
			stage = 806;
			break;
		case 804:
			npc("You never know. You'll find a lady down there called", "Katrine. Speak to her.");
			stage = 807;
			break;
		case 807:
			npc("But don't upset her, she's pretty dangerous.");
			stage = 808;
			break;
		case 808:
			npc("I also heard that Reldo the librarian", "knows more about them, go talk to him.");
			stage = 809;
			break;
		case 809:
			end();
			break;
		case 806:
			player("Do you think they would let me join?");
			stage = 804;
			break;
		case 50:
			if (quest.getStage(player) == 0) {
				npc("Funny you should mention that...there is actually.");
				stage = 800;
				break;
			}
			if (quest.getStage(player) == 30 || quest.getStage(player) == 40 || quest.getStage(player) == 50) {
				npc("Funny you should mention that...there is actually.");
				stage = 51;
				break;
			}
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Nope, nothing at all.");
			stage = 192;
			break;
		case 51:
			npc("The ruthless and notorious criminal gang known as the", "Black Arm Gang have their headquarters down there.");
			stage = 52;
			break;
		case 52:
			options("Thank you for the warning!", "Do you think they would let me join?");
			stage = 53;
			break;
		case 53:
			switch (buttonId) {
			case 1:
				player("Thanks for the warning!");
				stage = 54;
				break;
			case 2:
				player("Do you think they would let me join?");
				stage = 55;
				break;
			}
			break;
		case 54:
			npc("Don't worry about it.");
			stage = 56;
			break;
		case 55:
			npc("You never know. You'll find a lady down there called", "Katrine. Speak to her.");
			stage = 57;
			break;
		case 57:
			npc("But don't upset her, she's pretty dangerous.");
			stage = 58;
			break;
		case 58:
			quest.setStage(player, 50);
			end();
			break;
		case 56:
			player("Do you think they would let me join?");
			stage = 55;
			break;
		}
	}

	@Override
	public int[] getIds() {
		return new int[] { 641 };
	}
}
