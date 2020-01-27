package plugin.dialogue;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.game.node.entity.player.Player;

/**
 * Handles the SurokMagis dialogue.
 * @author 'Vexia
 */
public class SurokMagis extends DialoguePlugin {

	public SurokMagis() {

	}

	public SurokMagis(Player player) {
		super(player);
	}

	@Override
	public int[] getIds() {
		return new int[] { 5834, 5835, 7002, 7136 };
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "What do you want? ...Oh, wait. I know! You're", "porbably just like all the others, aren't you? After some", "fancy spell or potion from me, I bet!");
			stage = 1;
			break;
		case 1:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "No! atleast, I don't think so. What sort of spells", "do you have?");
			stage = 2;
			break;
		case 2:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Hah! I knew it! I expect you want my Aphro-Dizzy-", "Yak spell! Want someone to fall madly in love with you,", "eh?");
			stage = 3;
			break;
		case 3:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "That spell sounds very interesting, but I didn't mean to", "disturb you!");
			stage = 4;
			break;
		case 4:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Well, I see that you do have some manners. I'm glad", "to see that you use them.");
			stage = 5;
			break;
		case 5:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Now, if it's all the same, I am very bust at the", "moment. Come back another time", "please and thank you.");
			stage = 6;
			break;
		case 6:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Yes, ofcourse!");
			stage = 7;
			break;
		case 7:
			end();
			break;
		}
		return true;
	}

	@Override
	public DialoguePlugin newInstance(Player player) {

		return new SurokMagis(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Excuse me?");
		stage = 0;
		return true;
	}
}