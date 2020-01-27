package plugin.activity.stronghold.playersafety;

import org.crandor.game.component.Component;
import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.item.Item;

/**
 * @author Tyler Telis
 */
public class ProfessorHenryDialogue extends DialoguePlugin {

	/**
	 * Constructs a new {@code ProfessorHenryDialogue} instance.
	 * @param player The {@code Player} instance.
	 */
	public ProfessorHenryDialogue(Player player) {
		super(player);
	}

	/**
	 * Constructs a new {@code ProfessorHenryDialogue} instance.
	 */
	public ProfessorHenryDialogue() {
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new ProfessorHenryDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		stage = 0;
		if (player.getSavedData().getGlobalData().getTestStage() == 2 && player.getInventory().contains(12626, 1)) {
			player("Hello, Professor.");
			stage = 900;
			return true;
		}
		if (player.getSavedData().getGlobalData().getTestStage() >= 3) {
			npc("Good job " + player.getUsername() + " you completed the test!");
			stage = 800;
			return true;
		}
		sendNormalDialogue(player, FacialExpression.HALF_GUILTY, "Hello.");
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 800:
			end();
			break;
		case 900:
			npc("Ah, " + player.getUsername() + ". How's the test going?");
			stage++;
			break;
		case 901:
			player("I think I've finished.");
			stage++;
			break;
		case 902:
			npc("Excellent! Let me just mark the paper for you then.");
			stage++;
			break;
		case 903:
			npc("Hmm. Uh-huh, yes I see. Good! Yes, that's right.");
			stage++;
			break;
		case 904:
			npc("Excellent! Allow me to reward you for your work. I", "have these two old lamps that you may find useful.");
			stage++;
			break;
		case 905:
			npc("Also, there is an old jail block connected to the cells", "below the training centre, which have been overrun with", "vicious creatures. If you search around the jail cells", "downstairs, you should find it easily enough.");
			stage++;
			break;
		case 906:
			npc("Now, your rewards.");
			stage++;
			break;
		case 907:
			if (player.getInventory().remove(new Item(12626, 1000))) {
				showReward(player);
				end();
			}
			break;
		case -1:
			end();
			break;
		case 0:
			sendNormalDialogue(npc, FacialExpression.HALF_GUILTY, "Hello what?");
			increment();
			break;
		case 1:
			sendNormalDialogue(player, FacialExpression.HALF_GUILTY, "Uh...hello there?");
			increment();
			break;
		case 2:
			sendNormalDialogue(npc, FacialExpression.HALF_GUILTY, "Hello, 'Professor'. Manners cost nothing, you know.", "When you're in my classroom, I ask that you use the", "proper address for my station.");
			increment();
			break;
		case 3:
			sendNormalDialogue(player, FacialExpression.HALF_GUILTY, "Your station?");
			increment();
			break;
		case 4:
			sendNormalDialogue(npc, FacialExpression.HALF_GUILTY, "Yes. It means 'position', so to speak.");
			increment();
			break;
		case 5:
			sendNormalDialogue(player, FacialExpression.HALF_GUILTY, "Oh, okay.");
			increment();
			break;
		case 6:
			sendNormalDialogue(npc, FacialExpression.HALF_GUILTY, "Now, what can I do for you, exactly?");
			increment();
			break;
		case 7:
			sendNormalDialogue(player, FacialExpression.HALF_GUILTY, "What is this place?");
			increment();
			break;
		case 8:
			sendNormalDialogue(npc, FacialExpression.HALF_GUILTY, "This is the Misthalin Training Centre of Ezcelience. It", "is where bold adventurers, such as yourself, can come", "to learn of the dangers of the wide world and gain", "some valuable experience at the same time.");
			increment();
			break;
		case 9:
			sendNormalDialogue(player, FacialExpression.HALF_GUILTY, "What can I do here?");
			increment();
			break;
		case 10:
			sendNormalDialogue(npc, FacialExpression.HALF_GUILTY, "Here you can take part in the Player Safety test: a set", "of valuable lessons to learn about staying safe in ", "RuneScape.");
			increment();
			break;
		case 11:
			sendNormalDialogue(npc, FacialExpression.HALF_GUILTY, "I can give you a test paper to take and, once", "completed, you can bring it back to me for marking.", "Would you like to take the test?", "It will not cost you anything");
			increment();
			break;
		case 12:
			interpreter.sendOptions("Select an Option", "Yes, please", "Not right now, thanks.");
			increment();
			break;
		case 13:
			if (buttonId == 1) {
				sendNormalDialogue(player, FacialExpression.HALF_GUILTY, "Yes, please.");
				increment();
			} else if (buttonId == 2) {
				sendNormalDialogue(player, FacialExpression.HALF_GUILTY, "Not right now, thanks.");
				stage = -1;
			}
			break;
		case 14:
			if (player.getInventory().freeSlots() == 0) {
				sendNormalDialogue(npc, FacialExpression.HALF_GUILTY, "It seems your inventory is full.");
				stage = -1;
				return false;
			} else if (player.getInventory().containItems(StrongHoldOfPlayerSafetyPlugin.TEST_PAPER_ITEM_ID)) {
				sendNormalDialogue(npc, FacialExpression.HALF_GUILTY, "You already have a test, please fill it out", "and return it to me.");
				stage = -1;
				return false;
			}
			player.getSavedData().getGlobalData().setTestStage(1);
			player.getInventory().add(new Item(StrongHoldOfPlayerSafetyPlugin.TEST_PAPER_ITEM_ID));
			sendNormalDialogue(npc, FacialExpression.HALF_GUILTY, "Right then. Here is the test paper. When you have", "completed all the questions, bring it back to me for", "marking.");
			increment();
			break;
		case 15:
			sendNormalDialogue(player, FacialExpression.HALF_GUILTY, "Okay, thanks.");
			stage = -1;
			break;
		}
		return false;
	}

	/**
	 * Shows the reward for the player.
	 * @param player the player.
	 */
	private void showReward(Player player) {
		player.getConfigManager().set(1203, 1 << 29, true);
		player.getSavedData().getGlobalData().setTestStage(3);
		player.getQuestRepository().syncronizeTab(player);
		player.getConfigManager().set(101, player.getQuestRepository().getPoints());
		player.getInterfaceManager().open(new Component(277));
		for (int i = 9; i < 18; i++) {
			player.getPacketDispatch().sendString("", 277, i);
		}
		player.getPacketDispatch().sendString("You have completed the Player Safety test!", 277, 4);
		player.getPacketDispatch().sendString("2 Experience lamps", 277, 9);
		player.getPacketDispatch().sendString("Access to the Stronghold of", 277, 10);
		player.getPacketDispatch().sendString("Player Safety Dungeon", 277, 11);
		player.getPacketDispatch().sendString("The Safety First' emote", 277, 12);
		player.getPacketDispatch().sendString(player.getQuestRepository().getPoints() + "", 277, 7);
		player.getPacketDispatch().sendItemZoomOnInterface(12626, 240, 277, 5);
		if (!player.hasItem(new Item(4447, 2))) {
			player.getInventory().add(new Item(4447, 2), player);
		}
	}

	@Override
	public int[] getIds() {
		return new int[] { 7143 };
	}

}
