package plugin.quest.members.fishingcontest;

import core.game.node.entity.npc.NPC;
import core.plugin.InitializablePlugin;
import core.game.node.entity.player.Player;
import plugin.dialogue.DialoguePlugin;
import plugin.dialogue.FacialExpression;

@InitializablePlugin
public final class MorrisDialogue extends DialoguePlugin {

    /**
     * Constructs a new {@code MorrisDialogue} {@code Object}.
     */
    public MorrisDialogue() {
        /**
         * empty.
         */
    }

    /**
     * Constructs a new {@code MorrisDialogue} {@code Object}.
     * @param player the player.
     */
    public MorrisDialogue(Player player) {
        super(player);
    }

    @Override
    public DialoguePlugin newInstance(Player player) {
        return new MorrisDialogue(player);
    }

    @Override
    public boolean open(Object... args) {
        npc = (NPC) args[0];
        interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "What are you sitting around here for?");
        stage = 0;
        return true;
    }

    @Override
    public boolean handle(int interfaceId, int buttonId) {
        switch (stage) {
            case 0:
                interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "I'm making sure only those with a competition pass enter", "the fishing contest.");
                stage++;
                break;
            case 1:
                if(player.getInventory().containsItem(FishingContest.FISHING_PASS)){
                    player("I have one here...");
                    stage++;
                    break;
                } else {
                    end();
                    break;
                }
            case 2:
                player.getDialogueInterpreter().sendDialogue("You show Morris your pass.");
                stage ++;
                break;
            case 3:
                npc("Move on through. Talk to Bonzo","to enter the competition.");
                player.setAttribute("/save:fishing_contest:pass-shown",true);
                player.getInventory().remove(FishingContest.FISHING_PASS);
                stage = 100;
                break;
            case 100:
                end();
                break;
        }
        return true;
    }

    @Override
    public int[] getIds() {
        return new int[] { 227 };
    }
}
