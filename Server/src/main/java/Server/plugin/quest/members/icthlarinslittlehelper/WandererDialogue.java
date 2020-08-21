package plugin.quest.members.icthlarinslittlehelper;

import plugin.dialogue.DialoguePlugin;
import core.game.node.entity.player.Player;

/**
 * @author Ethan Kyle Millard <skype:pumpklins>
 * @since Tue, October 09, 2018 @ 9:02 PM
 */
public class WandererDialogue extends DialoguePlugin {

    public WandererDialogue() {
    }

    public WandererDialogue(Player player) {
        super(player);
    }

    @Override
    public DialoguePlugin newInstance(Player player) {
        return new WandererDialogue(player);
    }

    @Override
    public boolean open(Object... args) {
        player ("Good day, wanderer.");
        return true;
    }

    @Override
    public boolean handle(int interfaceId, int buttonId) {
        switch (stage){
            case 0:
                npc ("Hello to you too adventurer.");
                next();
                break;
            case 1:
                break;
        }
        return true;
    }

    @Override
    public int[] getIds() {
        return new int[] {};


    }
}
