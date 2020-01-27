package plugin.dialogue;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.content.global.Skillcape;
import org.crandor.game.content.skill.Skills;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.node.entity.player.Player;

/**
 * Represents the hickton dialogue plugin.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class HicktonDialogue extends DialoguePlugin {

	/**
	 * Constructs a new {@code HicktonDialogue} {@code Object}.
	 */
	public HicktonDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code HicktonDialogue} {@code Object}.
	 * @param player the player.
	 */
	public HicktonDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new HicktonDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Welcome to Hickton's Archery Emporium. Do you", "want to see my wares?");
		stage = 0;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			if (Skillcape.isMaster(player, Skills.FLETCHING)) {
				interpreter.sendOptions("Select an Option", "Can I buy a Skillcape of Fletching?", "Yes, please.", "No, I prefer to bash things close up.");
				stage = 100;
			} else {
				interpreter.sendOptions("Select an Option", "Yes, please.", "No, I prefer to bash things close up.");
				stage = 1;
			}
			break;
		case 1:
			switch (buttonId) {
			case 1:
				end();
				npc.openShop(player);
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "No, I prefer to bash things close up.");
				stage = 20;
				break;
			}
			break;
		case 20:
			end();
			break;
		case 100:
			switch (buttonId) {
			case 1:
				player("Can I buy a Skillcape of Fletching?");
				stage = -99;
				break;
			case 2:
				end();
				npc.openShop(player);
				break;
			case 3:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "No, I prefer to bash things close up.");
				stage = 20;
				break;
			}
			break;
		case -99:
			npc("You will have to pay a fee of 99,000 gp.");
			stage = 101;
			break;
		case 101:
			options("Yes, here you go.", "No, thanks.");
			stage = 102;
			break;
		case 102:
			switch (buttonId) {
			case 1:
				player("Yes, here you go.");
				stage = 103;
				break;
			case 2:
				end();
				break;
			}
			break;
		case 103:
			if (Skillcape.purchase(player, Skills.FLETCHING)) {
				npc("There you go! Enjoy.");
			}
			stage = 104;
			break;
		case 104:
			end();
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 575 };
	}
}
