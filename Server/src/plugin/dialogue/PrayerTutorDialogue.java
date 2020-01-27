package plugin.dialogue;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.node.entity.player.Player;

/**
 * Handles the PrayerTutorDialogue dialogue.
 * @author 'Vexia
 */
@InitializablePlugin
public class PrayerTutorDialogue extends DialoguePlugin {

	public PrayerTutorDialogue() {

	}

	public PrayerTutorDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {

		return new PrayerTutorDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendOptions("Select an Option", "I already know about the basic prayers, got any tips?", "Tell me about different bones.", "Goodbye.");
		stage = 0;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "I already know about the basic prayers, got any tips?");
				stage = 10;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Tell me about different bones.");
				stage = 20;
				break;
			case 3:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Goodbye.");
				stage = 30;
				break;
			}
			break;
		case 10:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "For you " + player.getUsername() + "? Always. There are many", "advantages to using the protection prayers when", "fighting the more dangerous foes. You can protect", "yourself from magic, melee or ranged attacks with these");
			stage = 11;
			break;
		case 11:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "useful prayers.");
			stage = 12;
			break;
		case 12:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "A good prayer to have when venturing into the", "wilderness is protect item. This will protect one of your", "items if you should die there.");
			stage = 13;
			break;
		case 13:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Remember though that venturing into the wilderness is", "a risky business, store your items in a bank before you", "go there so that you don't lose them.");
			stage = 14;
			break;
		case 14:
			interpreter.sendOptions("Select an Option", "I already know about the basic prayers, got any tips?", "Tell me about different bones.", "Goodbye.");
			stage = 0;
			break;
		case 20:
			interpreter.sendOptions("Select an Option", "Basic Bones", "Big Bones", "Babydragon Bones", "Goodbye.");
			stage = 21;
			break;
		case 21:
			switch (buttonId) {
			case 1:
				interpreter.sendItemMessage(526, "Basic bones are left by many creatures such as goblins, monkeys and that sort of thing. They won't get you much when you burry them, but if you do it every time you come across them, it all adds up!");
				stage = 20;
				break;
			case 2:
				interpreter.sendItemMessage(532, "Big bones you can get by killing things like ogres and giants, them being big things and all. They're quite a good boost for your prayer if you are up to fighting the big boys.");
				stage = 220;
				break;
			case 3:
				interpreter.sendItemMessage(536, "If you're feeling adventurous and up to tackling baby dragons, you can get these Baby Dragon bones which are even better than big bones.");
				stage = 20;
				break;
			case 4:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Goodbye.");
				stage = 40;
				break;
			}
			break;
		case 220:
			interpreter.sendItemMessage(532, "You can probably find them in caves and such dark dank places.");
			stage = 20;
			break;
		case 40:
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
		return new int[] { 4903 };
	}
}
