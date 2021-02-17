package core.game.node.entity.npc.city.varrock;

import core.game.content.dialogue.DialoguePlugin;
import core.game.content.dialogue.FacialExpression;
import core.plugin.Initializable;
import core.game.node.entity.player.Player;

/**
 * Handles the TeacherandPupilMuseumMaleDialogue dialogue.
 *
 * @author 'qmqz
 */
@Initializable
public class TeacherandPupilMuseumMaleDialogue extends DialoguePlugin {

    public TeacherandPupilMuseumMaleDialogue() {

    }

    public TeacherandPupilMuseumMaleDialogue(Player player) {
        super(player);
    }

    @Override
    public int[] getIds() {
        return new int[]{5944};
    }

    @Override
    public boolean handle(int interfaceId, int buttonId) {
        switch (stage) {
            case 0:
                interpreter.sendDialogues(5948, FacialExpression.HALF_GUILTY, "I told you to go before we got here.");
                stage = 1;
                break;

            case 1:
                interpreter.sendDialogues(5949, FacialExpression.CHILD_GUILTY, "But sir, I didn't need to go then!");
                stage = 2;
                break;

            case 2:
                interpreter.sendDialogues(5948, FacialExpression.HALF_GUILTY, "Alright, come on then.");
                stage = 99;
                break;

            case 99:
                end();
                break;
        }

        return true;
    }

    @Override
    public DialoguePlugin newInstance(Player player) {
        return new TeacherandPupilMuseumMaleDialogue(player);
    }

    @Override
    public boolean open(Object... args) {
        interpreter.sendDialogues(5949, FacialExpression.CHILD_GUILTY, "Teacher! Sir! I need the toilet!");
        stage = 0;
        return true;
    }
}
