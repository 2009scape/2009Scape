package core.game.node.entity.npc.city.varrock;

import core.game.content.dialogue.DialoguePlugin;
import core.game.content.dialogue.FacialExpression;
import core.game.node.entity.npc.NPC;
import core.plugin.Initializable;
import core.game.node.entity.player.Player;
import core.tools.RandomFunction;

/**
 * Handles the SchoolchildMuseumDialogue dialogue.
 *
 * @author afaroutdude
 */
@Initializable
public class SchoolchildMuseumDialogue extends DialoguePlugin {

    private static final String[][] chats = {
            {"Can you find my teacher? I need the toilet!"},
            {"I wonder what they're doing behind that", "rope."},
            {"Teacher! Can we go to the Natural History Exhibit", "now?"},
            {"*sniff* They won't let me take an arrowhead as a", "souvenir."},
            {"Yaaay! A day off school."},
            {"I wanna be an archaeologist when I grow up!"},
            {"Sada... Sram... Sa-ra-do-min is bestest!"},
            {"*cough* It's so dusty in here."},
            {"Maz... Zar... Za-mor-ak is bestest!"}
    };

    public SchoolchildMuseumDialogue() {

    }

    public SchoolchildMuseumDialogue(Player player) {
        super(player);
    }

    @Override
    public int[] getIds() {
        return new int[]{5984, 5945, 5946, 10};
    }

    @Override
    public boolean handle(int interfaceId, int buttonId) {
        end();
        return true;
    }

    @Override
    public DialoguePlugin newInstance(Player player) {
        return new SchoolchildMuseumDialogue(player);
    }

    @Override
    public boolean open(Object... args) {
        npc = (NPC) args[0];
        interpreter.sendDialogues(npc, FacialExpression.CHILD_FRIENDLY, chats[RandomFunction.random(0, chats.length)]);
        return true;
    }
}
