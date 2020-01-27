package plugin.dialogue;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.node.entity.player.Player;

/**
 * The dialogue used for shanomi.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class ShanomiDialogue extends DialoguePlugin {

	/**
	 * Constructs a new {@code Shanomi} {@code Object}.
	 */
	public ShanomiDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code Shanomi} {@code Object}.
	 * @param player the player.
	 */
	public ShanomiDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new ShanomiDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendDialogues(npc, null, "Greetings " + player.getUsername() + ". Welcome you are in the test of", "combat.");
		stage = 0;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			interpreter.sendOptions("Select an Option", "What do I do here?", "Where do the machines come from?", "May I claim my tokens please?", "Bye.");
			stage = 1;
			break;
		case 1:
			if (buttonId == 1) {
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "What do I do here?");
				stage = 2;
			} else if (buttonId == 2) {
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Where do the machines come from?");
				stage = 11;
			} else if (buttonId == 3) {
				end();
				player.getDialogueInterpreter().open("wg:claim-tokens", npc.getId());
			} else if (buttonId == 4) {
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Bye.");
				stage = 16;
			}
			break;
		case 2:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "A spare suit of plate armour need you will. Full helm", "plate, leggings and platebody. Placing it in the centre", "of the magical machines you will be doing. KA-POOF!", "The armour it attacks most furiously as if alive! Kill it");
			stage = -3;
			break;
		case -3:
			npc("you must, yes.");
			stage = 3;
			break;
		case 3:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "So I use a full set of plate armour on the centre plate of", "the machines and it will animate it? Then I have to kill my", "own armour... how bizarre!");
			stage = 4;
			break;
		case 4:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Yes. It is as you are saying. For this earn tokens you", "will. Also gain experience in combat you will. Trained", "long and hard here have I.");
			stage = 5;
			break;
		case 5:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "You're not from around here are you...?");
			stage = 6;
			break;
		case 6:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "It is as you say.");
			stage = 7;
			break;
		case 7:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "So will I lose my armour?");
			stage = 8;
			break;
		case 8:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Lose armour you will if damaged to much it becomes.", "Rare this is, but still possible. If kill you the armour does,", "also lose armour you will.");
			stage = 9;
			break;
		case 9:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "So, occasionally I might lose a bit because it's being", "bashed about and I'll obviously lose it if I die... that it?");
			stage = 10;
			break;
		case 10:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "It is as you say.");
			stage = 0;
			break;
		case 11:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Make them I did, with magics.");
			stage = 12;
			break;
		case 12:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Magic, in the Warrior's Guild?");
			stage = 13;
			break;
		case 13:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "A skilled warrior also am I. Harrallak mistakes does not", "make. Potential in my invention he sees and opportunity", "grasps.");
			stage = 14;
			break;
		case 14:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "I see, so you made the magical machines and Harrallak", "saw how they could be used in the guild to train warrior's", "combat... interesting. Harrallak certainly is an intelligent", "guy.");
			stage = 15;
			break;
		case 15:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "It is as you say.");
			stage = 0;
			break;
		case 16:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Health be with you travelling.");
			stage = 100;
			break;
		case 100:
			end();
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 4290 };
	}
}
