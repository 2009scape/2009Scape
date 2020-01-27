package plugin.dialogue;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.node.entity.player.Player;

/**
 * Represents the mining tutor dialogue plugin.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class MiningtutorDialogue extends DialoguePlugin {

	/**
	 * Constructs a new {@code MiningtutorDialogue} {@code Object}.
	 */
	public MiningtutorDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code MiningtutorDialogue} {@code Object}.
	 * @param player the player.
	 */
	public MiningtutorDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new MiningtutorDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendOptions("Select an Option", "Can you teach me the basics of mining please?", "Are there any mining related quests?", "Goodbye.");
		stage = 0;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Can you teach me the basics of mining please?");
				stage = 10;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Are there are mining related quests?");
				stage = 20;
				break;
			case 3:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Goodbye.");
				stage = 30;
				break;
			}
			break;
		case 10:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "If you want to know what ore's in a rock before you", "mine it, right-click the rock and select prospect from the", "menu, it will take a little time, but you'll find out what's", "in the rock before you mine.");
			stage = 11;
			break;
		case 11:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "You can also tell the ore you'll get from the colour of", "the rock.");
			stage = 12;
			break;
		case 12:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "To mine, simply click on the rock to mine it, but make", "sue you have your pick with you.");
			stage = 13;
			break;
		case 13:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "When you have a full inventory, take it to the bank,", "you can find it on the roof of the castle in Lumbridge.");
			stage = 14;
			break;
		case 14:
			interpreter.sendOptions("Select an Option", "Can you teach me the basics of mining please?", "Are there any mining related quests?", "Goodbye.");
			stage = 0;
			break;
		case 20:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Oh yes, if you haven't already, speak to Doric who can", "be found around the anvils north of Falador. I'm sure", "he can help you out.");
			stage = 21;
			break;
		case 21:
			interpreter.sendOptions("Select an Option", "Can you teach me the basics of mining please?", "Are there any mining related quests?", "Goodbye.");
			stage = 0;
			break;
		case 30:
			end();
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 4902 };
	}
}
