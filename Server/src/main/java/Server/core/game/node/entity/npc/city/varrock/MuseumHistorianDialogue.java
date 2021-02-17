package core.game.node.entity.npc.city.varrock;

import core.game.content.dialogue.DialoguePlugin;
import core.game.content.dialogue.FacialExpression;
import core.game.node.entity.npc.NPC;
import core.plugin.Initializable;
import core.game.node.entity.player.Player;

/**
 * Represents the Museum Natural Historian dialogue plugin.
 * @author 'qmqz
 * @version 1.0
 */
@Initializable
public final class MuseumHistorianDialogue extends DialoguePlugin {

    /**
     * Constructs a new {@code MuseumHistorianDialogue {@code Object}.
     */
    public MuseumHistorianDialogue() {
        /**
         * empty.
         */
    }

    /**
     * Constructs a new {@code MuseumHistorianDialogue} {@code Object}.
     * @param player the player.
     */
    public MuseumHistorianDialogue(Player player) {
        super(player);
    }

    @Override
    public DialoguePlugin newInstance(Player player) {
        return new MuseumHistorianDialogue(player);
    }

    @Override
    public boolean open(Object... args) {
        genderCheck();
        npc = (NPC) args[0];
        interpreter.sendDialogues(npc, FacialExpression.ASKING, "Hello again, " + gender + ", how can I help you on this fine day?");
        stage = 0;
        return true;
    }

    public String gender;
    public void genderCheck() {
        if (player.isMale()) {
            gender = "sir";
        } else {
            gender = "madam";
        }
    }

    @Override
    public boolean handle(int interfaceId, int buttonId) {
        switch (stage) {
            case 0:
                interpreter.sendDialogues(player, FacialExpression.ASKING, "I was hoping you could tell me about something.");
                stage = 1;
                break;

            case 1:
                interpreter.sendOptions("Select an Option", "Tell me about snails.", "Tell me about monkeys.", "Tell me about sea slugs.", "Tell me about snakes.", "That's enough education for one day.");
                stage = 2;
                break;

            case 2:
                switch (buttonId) {
                    case 1:
                        interpreter.sendDialogues(player, FacialExpression.HALF_ASKING, "Tell me about snails.");
                        stage = 10;
                        break;
                    case 2:
                        interpreter.sendDialogues(player, FacialExpression.HALF_ASKING, "Tell me about monkeys.");
                        stage = 20;
                        break;
                    case 3:
                        interpreter.sendDialogues(player, FacialExpression.HALF_ASKING, "Tell me about sea slugs.");
                        stage = 30;
                        break;
                    case 4:
                        interpreter.sendDialogues(player, FacialExpression.HALF_ASKING, "Tell me about snakes.");
                        stage = 40;
                        break;
                    case 5:
                        interpreter.sendDialogues(player, FacialExpression.HALF_ASKING, "That's enough education for one day.");
                        stage = 99;
                        break;

                }
                break;
            case 10:
                interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Ahh snails, the gelatinous gastropods.", "If you just follow me to the display case,"," I shall explain all about them.");
                //transitions into a cutscene
                stage = 99;
                break;
            case 20:
                interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Ahh monkeys, the simian collective.", "If you just follow me to the display case,", " I shall explain all about them.");
                //transitions into a cutscene
                stage = 99;
                break;

            case 30:
                interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Ahh sea slugs, the cute crustaceans.", "If you just follow me to the display case,", "I shall explain all about them.");
                //transitions into a cutscene
                stage = 99;
                break;

            case 40:
                interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Ahh snakes, the slithering squamata.", "If you just follow me to the display case,", "I shall explain all about them.");
                //transitions into a cutscene
                stage = 99;
                break;

            case 99:
                end();
                break;
        }
        return true;
    }

    @Override
    public int[] getIds() {
        return new int[] { 5966, 5967 };
    }
}
