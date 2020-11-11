package plugin.quest.members.merlinscrystal;

import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import plugin.dialogue.DialogueInterpreter;
import plugin.dialogue.DialoguePlugin;

public class RenegadeKnightDialogue extends DialoguePlugin {
    public RenegadeKnightDialogue() {}
    public RenegadeKnightDialogue(Player player) {super(player);}

    @Override
    public DialoguePlugin newInstance(Player player) {
        return new RenegadeKnightDialogue(player);
    }

    @Override
    public boolean open(Object... args) {
        npc = new NPC(237);

        sendDialogue("You knock at the door. You hear a voice from", "inside...");
        stage = 0;

        return true;
    }

    @Override
    public boolean handle(int interfaceId, int buttonId) {
        switch(stage) {
            case 999:
                end();
                break;
            case 0:
                player("Um...");
                stage++;
                break;
            case 1:
                options("Pizza Delivery!", "Have you ever thought about letting Saradomin into your life?", "Can I interest you in some double glazing?", "Would you like to buy some lucky heather?");
                stage++;
                break;
            case 2:
                switch(buttonId) {
                    case 1:
                        player("Pizza delivery!");
                        stage = 3;
                        break;
                    case 2:
                        player("Have you ever thought about letting Saradomin into", "your life? I have some pamphlets you may be", "interested in reading and discussing with me.");
                        stage = 4;
                        break;
                    case 3:
                        player("Can I interest you in some double glazing? An old", "castle like this must get very draughty in the", "winter...");
                        stage = 5;
                        break;
                    case 4:
                        player("Would you like to buy some lucky heather?");
                        stage = 4;
                        break;
                }
                break;
            case 3:
                npc("We didn't order any Pizza. Get lost!");
                stage = 10;
                break;
            case 4:
                npc("No. Go away.");
                stage = 10;
                break;
            case 5:
                npc("No. Get out of here before I run you through.");
                stage = 10;
                break;
            case 10:
                sendDialogue("It looks like you'll have to find another way in...");
                stage = 999;
                break;

        }
        return true;
    }

    @Override
    public int[] getIds() {
        return new int[]{ DialogueInterpreter.getDialogueKey("renegade-knight") };
    }
}
