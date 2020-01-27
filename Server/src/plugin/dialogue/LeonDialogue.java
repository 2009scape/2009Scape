package plugin.dialogue;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.node.entity.player.Player;

/**
 * Represents the dialogue plugin used for the leon npc.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class LeonDialogue extends DialoguePlugin {

	/**
	 * Constructs a new {@code LeonDialogue} {@code Object}.
	 */
	public LeonDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code LeonDialogue} {@code Object}.
	 * @param player the player.
	 */
	public LeonDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new LeonDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendOptions("Select an Option", "What is this place?", "Can I have a go with your crossbow?", "What are you holding there?");
		stage = 1;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 1:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "What is this place?");
				stage = 10;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Can I have a go with your crossbow?");
				stage = 20;
				break;
			case 3:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "What are you holding there?");
				stage = 30;
				break;

			}
			break;
		case 10:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "This is Aleck's Hunter Emporium. Basically, it's just a", "shop with fancy name; you can buy various weapons", "and traps here.");
			stage = 11;
			break;
		case 11:
			end();
			break;
		case 20:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "I'm afraid with it being a prototype, I've only got a few", "for my own testing purposes.");
			stage = 21;
			break;
		case 21:
			end();
			break;
		case 30:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "This? This is a prototype for a new type of crossbow", "I've been designing.");
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
		return new int[] { 5111 };
	}
}
