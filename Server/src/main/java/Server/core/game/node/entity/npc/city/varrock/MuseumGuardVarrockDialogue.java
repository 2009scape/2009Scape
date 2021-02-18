package core.game.node.entity.npc.city.varrock;

import core.game.content.dialogue.DialoguePlugin;
import core.game.content.dialogue.FacialExpression;
import core.game.node.entity.npc.NPC;
import core.plugin.Initializable;
import core.game.node.entity.player.Player;

/**
 * Handles the MuseumGuardVarrockDialogue dialogue.
 * @author 'Vexia
 */
@Initializable
public class MuseumGuardVarrockDialogue extends DialoguePlugin {

	public MuseumGuardVarrockDialogue() {

	}

	public MuseumGuardVarrockDialogue(Player player) {
		super(player);
	}

	@Override
	public int[] getIds() {
		return new int[] { 5943 };
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {

		switch (stage) {
		case 0:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Yes, how do I get in?");
			stage = 2;
			break;
		case 2:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Well, the main entrance is 'round the front. Just head", "west then north slightly, you can't miss it!");
			stage = 3;
			break;
		case 3:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "What about these doors?");
			stage = 4;
			break;
		case 4:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "They're primarily for the workmen bringing finds from the", "Dig Site, but you can go through if you want.");
			stage = 5;
			break;
		case 5:
			end();
			break;
		}

		return true;
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new MuseumGuardVarrockDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Hello there. Come to see the new museum?");
		stage = 0;
		return true;
	}
}
