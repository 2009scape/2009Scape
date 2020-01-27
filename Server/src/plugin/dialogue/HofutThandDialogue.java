package plugin.dialogue;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.content.eco.ge.GEGuidePrice;
import org.crandor.game.content.eco.ge.GEGuidePrice.GuideType;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.node.entity.player.Player;

/**
 * Represents the hofut thand dialogue plugin.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class HofutThandDialogue extends DialoguePlugin {

	/**
	 * Constructs a new {@code HofutThandDialogue} {@code Object}.
	 */
	public HofutThandDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code HofutThandDialogue} {@code Object}.
	 * @param player the player.
	 */
	public HofutThandDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new HofutThandDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Hello!");
		stage = 0;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "What? Oh, hello. I was deep in thought. Did", "you say something?");
			stage = 1;
			break;
		case 1:
			interpreter.sendOptions("Select an Option", "Do you know about the prices for armour and weapons?", "I didn't say anything at all.");
			stage = 2;
			break;
		case 2:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Do you know about the prices for armour and weapons?");
				stage = 10;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "I didn't say anything at all.");
				stage = 20;
				break;
			}
			break;
		case 20:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "I thought you at least said. 'Hello' I must be", "going mad. Do you think I'm going mad?");
			stage = 21;
			break;
		case 21:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Oh, most definitely. You should see a doctor before it's", "too late.");
			stage = 22;
			break;
		case 22:
			end();
			break;
		case 10:
			end();
			GEGuidePrice.open(player, GuideType.WEAPONS_AND_ARMOUR);
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 6527 };
	}
}
