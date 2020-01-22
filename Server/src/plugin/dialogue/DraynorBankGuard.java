package plugin.dialogue;

import org.crandor.game.content.activity.ActivityManager;
import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.game.node.entity.player.Player;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.node.item.Item;

/**
 * Represents the dialogue plugin used for the draynor bank guard npc.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class DraynorBankGuard extends DialoguePlugin {

	/**
	 * Represents the coins item needed to re-watch the recording.
	 */
	private static final Item COINS = new Item(995, 50);

	/**
	 * Constructs a new {@code DraynorBankGuard} {@code Object}.
	 */
	public DraynorBankGuard() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code DraynorBankGuard} {@code Object}.
	 * @param player the player.
	 */
	public DraynorBankGuard(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new DraynorBankGuard(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Yes?");
		stage = 0;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			if (!player.getSavedData().getGlobalData().isDraynorRecording()) {
				interpreter.sendOptions("Select an option", "Can I deposit my stuff here?", "That wall doesn't look very good.", "Sorry, I don't want anything.");
				stage = 1;
			} else {
				interpreter.sendOptions("Select an Option", "Can I see that recording again, please?", "Sorry, I don't want anything.");
				stage = 70;
			}
			break;
		case 70:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, null, "Can I see that recording again, please?");
				stage = 71;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Sorry, I don't want anything.");
				stage = 30;
				break;
			}
			break;
		case 71:
			npc("I'd like you to pay me 50 gp first.");
			stage = 72;
			break;
		case 72:
			if (!player.getInventory().containsItem(COINS)) {
				player("I'm not carrying that much.");
				stage = 73;
				return true;
			} else {
				options("Ok, here's 50 gp.", "Thanks, maybe another day.");
				stage = 80;
			}
			break;
		case 73:
			if (player.getBank().containsItem(COINS)) {
				npc("As a bank employee, I suppose I could take the money", "directly from your bank account.");
				stage = 74;
			} else {
				npc("Come back when you do.");
				stage = 75;
			}
			break;
		case 74:
			options("Ok, you can take 50 gp from my bank account.", "Thanks, maybe another day.");
			stage = 76;
			break;
		case 75:
			end();
			break;
		case 76:
			switch (buttonId) {
			case 1:
				player("Ok, you can take 50 gp from my bank account.");
				stage = 79;
				break;
			case 2:
				player("Thanks, maybe another day.");
				stage = 77;
				break;
			}
			break;
		case 77:
			npc("Ok.");
			stage = 78;
			break;
		case 78:
			end();
			break;
		case 79:
			end();
			if (player.getBank().containsItem(COINS) && player.getBank().remove(COINS)) {
				startRecording(player);
			}
			break;
		case 80:
			switch (buttonId) {
			case 1:
				player("Ok, here's 50 gp.");
				stage = 81;
				break;
			case 2:
				player("Thanks, maybe another day.");
				stage = 77;
				break;
			}
			break;
		case 81:
			end();
			if (!player.getInventory().containsItem(COINS)) {
				end();
				return true;
			}
			if (player.getInventory().remove(COINS)) {
				startRecording(player);
			}
			break;
		case 1:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Hello. Can I deposit my stuff here?");
				stage = 10;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "That wall doesn't look very good.");
				stage = 20;
				break;
			case 3:
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Sorry, I don't want anything.");
				stage = 30;
				break;
			}
			break;
		case 30:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Ok.");
			stage = 31;
			break;
		case 31:
			end();
			break;
		case 20:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "No, it doesn't.");
			stage = 21;
			break;
		case 21:
			options("Are you going to tell me what happened?", "Alright, I'll stop bothering you now.");
			stage = 22;
			break;
		case 22:
			switch (buttonId) {
			case 1:
				player("Are you going to tell me what happended?");
				stage = 25;
				break;
			case 2:
				player("Alright, I'll stop bothering you now.");
				stage = 23;
				break;
			}
			break;
		case 23:
			npc("Good day, sir.");
			stage = 24;
			break;
		case 24:
			end();
			break;
		case 25:
			npc("I could do.");
			stage = 26;
			break;
		case 26:
			player("Ok, go on!");
			stage = 27;
			break;
		case 27:
			npc("Someone smashed the wall when", "they were robbing the bank.");
			stage = 28;
			break;
		case 28:
			player("Someone's robbed the bank?");
			stage = 29;
			break;
		case 29:
			npc("Yes.");
			stage = 50;
			break;
		case 50:
			player("But... was anyone hurt?", "Did they get anything valuable?");
			stage = 51;
			break;
		case 51:
			npc("Yes, but we were able to get more staff and mend the", "wall easily enough.");
			stage = 52;
			break;
		case 52:
			npc("The Bank has already replaces all the stolen items that", "belonged to customers.");
			stage = 53;
			break;
		case 53:
			player("Oh, good... but the bank staff got hurt?");
			stage = 54;
			break;
		case 54:
			npc("Yes but the new ones are just as good.");
			stage = 55;
			break;
		case 55:
			player("You're not very nice, are you?");
			stage = 56;
			break;
		case 56:
			npc("No-one's expecting me to be nice.");
			stage = 57;
			break;
		case 57:
			player("Anyway... So, someone's robbed the bank?");
			stage = 58;
			break;
		case 58:
			npc("Yes.");
			stage = 59;
			break;
		case 59:
			player("Do you know who did it?");
			stage = 60;
			break;
		case 60:
			npc("We are fairly sure we know who the robber was. The", "security recording was damaged in the attack, but it still", "shows his face clearly enough.");
			stage = 61;
			break;
		case 61:
			player("You've got a security recording?");
			stage = 62;
			break;
		case 62:
			npc("Yes. Our insurers insisted that we", "install a magical scrying orb.");
			stage = 63;
			break;
		case 63:
			player("Can I see the recording?");
			stage = 64;
			break;
		case 64:
			npc("I suppose so. But it's quite long.");
			stage = 65;
			break;
		case 65:
			options("That's ok, show me the recording.", "Thanks, maybe another day.");
			stage = 66;
			break;
		case 66:
			switch (buttonId) {
			case 1:
				player("That's ok, show me the recording.");
				stage = 68;
				break;
			case 2:
				player("Thanks, maybe another day.");
				stage = 67;
				break;
			}
			break;
		case 67:
			end();
			break;
		case 68:
			npc("Alright... The bank's magical playback device will feed the", "recorded images into your mind. Just shut your eyes.");
			stage = 69;
			break;
		case 69:
			startRecording(player);
			break;
		case 10:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "No. I'm a security guard, not a bank clerk.");
			stage = 11;
			break;
		case 11:
			end();
			break;
		}
		return true;
	}

	/**
	 * Method used to start the recording.
	 * @param player the player.
	 */
	private void startRecording(Player player) {
		end();
		ActivityManager.start(player, "dbr cutscene", false);
	}

	@Override
	public int[] getIds() {
		return new int[] { 2574 };
	}
}
