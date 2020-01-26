package plugin.dialogue;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.node.entity.player.Player;

/**
 * Dialogue for the boss pet, Vet'ion JR.
 * @author Splinter
 * @version 1.0
 */
@InitializablePlugin
public final class VetionJRDialogue extends DialoguePlugin {

	/**
	 * Constructs a new {@code ChaosElementalJRDialogue} {@code Object}.
	 */
	public VetionJRDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code ChaosElementalJRDialogue} {@code Object}.
	 * @param player the player.
	 */
	public VetionJRDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new VetionJRDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Who is the true lord and king of the lands?");
		stage = 0;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			interpreter.sendDialogues(npc, FacialExpression.OSRS_NORMAL, "The mighty heir and lord of the Wilderness.");
			stage = 1;
			break;
		case 1:
			interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Where is he? Why hasn't he lifted your burden?");
			stage = 2;
			break;
		case 2:
			interpreter.sendDialogues(npc, FacialExpression.OSRS_NORMAL, "I have not fulfilled my purpose.");
			stage = 3;
			break;
		case 3:
			player("What is your purpose?");
			stage = 4;
			break;
		case 4:
			npc(FacialExpression.OSRS_NORMAL, "Not what is, what was.", "A great war tore this land apart and, for my","failings in protecting this land, I carry the burden","of its waste.");
			stage = 5;
			break;
		case 5:
			end();
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 8600, 8654 };
	}
}
