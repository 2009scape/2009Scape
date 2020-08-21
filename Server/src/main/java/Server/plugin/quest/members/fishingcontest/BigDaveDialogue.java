package plugin.quest.members.fishingcontest;

import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.plugin.InitializablePlugin;
import plugin.dialogue.DialoguePlugin;


/**
 * Handles the SinisterStrangerDialogue dialogue.
 * @author Woah
 */
@InitializablePlugin
public class BigDaveDialogue extends DialoguePlugin {

    public BigDaveDialogue(){
        //empty
    }

    public BigDaveDialogue(Player player){super(player);}

    @Override
    public DialoguePlugin newInstance(Player player) {
        return new BigDaveDialogue(player);
    }

    @Override
    public boolean open(Object... args) {
        npc = (NPC) args[0];
        npc("Hey lad! Always nice to see a fresh face!");
        return true;
    }

    @Override
    public boolean handle(int interfaceId, int buttonId) {
        switch (stage) {

            case 0:
                interpreter.sendOptions("Choose an option:", "So you're the champ?", "Can I fish here instead of you?", "Do you have any tips for me?");
                stage++;
                break;

            case 1:
                switch (buttonId) {
                    case 1:
                        player("So you're the champ?");
                        stage = 10;
                        break;
                    case 2:
                        player("Can I fish here instead of you?");
                        stage = 20;
                        break;
                    case 3:
                        player("Do you have any tips for me?");
                        stage = 30;
                        break;
                }
                break;


            case 9:
                end();
                break;

            case 10:
                npc("That's right, lad!", "Ain't nobody better at fishing round here", "than me! That's for sure!");
                stage = 9;
                break;

            case 20:
                npc("Sorry lad! This is my lucky spot!");
                stage = 9;
                break;

            case 30:
                npc("Why would I help you? I wanna stay the best!", "I'm not givin' away my secrets like", "old Grandpa Jack does!");
                stage++;
                break;
            case 31:
                player("Who's Grandpa Jack?");
                stage++;
                break;
            case 32:
                npc("You really have no clue do you!", "He won this competition four years in a row!", "He lives in the house just outside the gate.");
                stage = 9;
                break;

        }
        return true;
    }

    @Override
    public int[] getIds() {
        return new int[]{228};
    }
}
