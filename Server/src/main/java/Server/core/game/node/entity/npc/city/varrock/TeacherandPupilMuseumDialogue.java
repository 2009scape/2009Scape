package core.game.node.entity.npc.city.varrock;

import core.game.content.dialogue.DialoguePlugin;
import core.game.content.dialogue.FacialExpression;
import core.game.node.entity.npc.NPC;
import core.plugin.Initializable;
import core.game.node.entity.player.Player;

/**
 * Handles the TeacherandPupilMuseumDialogue dialogue.
 *
 * @author 'Vexia
 */
@Initializable
public class TeacherandPupilMuseumDialogue extends DialoguePlugin {

    public TeacherandPupilMuseumDialogue() {

    }

    public TeacherandPupilMuseumDialogue(Player player) {
        super(player);
    }

    @Override
    public int[] getIds() {
        return new int[]{5947};
    }

    @Override
    public boolean handle(int interfaceId, int buttonId) {
        switch (stage) {
            case 0:
                interpreter.sendDialogues(5951, FacialExpression.CHILD_FRIENDLY, "Aww, but miss, it's sooo exciting.");
                stage = 999;
                break;
			case 1:
				interpreter.sendDialogues(5950, FacialExpression.HALF_GUILTY, "That's because he's an art critic, dear. They have", "some very funny ideas.");
				stage = 999;
				break;
            case 999:
                end();
                break;
        }

        return true;
    }

    @Override
    public DialoguePlugin newInstance(Player player) {

        return new TeacherandPupilMuseumDialogue(player);
    }

    @Override
    public boolean open(Object... args) {
        npc = (NPC) args[0];
        if (npc.getLocation().getZ() == 0) {
            interpreter.sendDialogues(5950, FacialExpression.ANGRY, "Stop pulling, we've plenty of time to see everything.");
            stage = 0;
        } else {
            interpreter.sendDialogues(5951, FacialExpression.HALF_GUILTY, "That man over there talks funny, miss.");
            stage = 1;
        }
        return true;
    }
}
