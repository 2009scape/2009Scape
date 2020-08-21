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
public class JoshuaDialogue extends DialoguePlugin {

    public JoshuaDialogue() {
        //empty
    }

    public JoshuaDialogue(Player player){super(player);}

    @Override
    public DialoguePlugin newInstance(Player player) {
        return new JoshuaDialogue(player);
    }

    @Override
    public boolean open(Object... args) {
        npc = (NPC) args[0];
        npc("Yeah? What do you want?");
        return true;
    }

    @Override
    public boolean handle(int interfaceId, int buttonId) {
        switch (stage) {

            case 0:
                interpreter.sendOptions("Choose an option:", "Um... nothing really...", "Can I fish here instead of you?", "Do you have any tips for me?");
                stage++;
                break;

            case 1:
                switch (buttonId) {
                    case 1:
                        player("Um... nothing really...");
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
                npc("Quit bugging me then, dude!", "I got me some fish to catch!");
                stage = 9;
                break;

            case 20:
                npc("nuh uh dude. Less talk, more fishing!");
                stage = 9;
                break;

            case 30:
                npc("Dude! Why should I help you?", "You like, might beat me!", "I'm not giving away my secrets like that", "dude Grandpa Jack does!");
                stage++;
                break;
            case 31:
                player("Who's Grandpa Jack?");
                stage++;
                break;
            case 32:
                npc("Who's Grandpa Jack you say!", "He won this competition four years in a row!", "He lives in the house just outside the gate.");
                stage = 9;
                break;

        }
        return true;
    }

    @Override
    public int[] getIds() {
        return new int[] {229};
    }
}
