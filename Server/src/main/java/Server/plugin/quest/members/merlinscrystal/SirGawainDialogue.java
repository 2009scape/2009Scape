package plugin.quest.members.merlinscrystal;

import core.game.node.entity.player.link.quest.Quest;
import plugin.dialogue.DialoguePlugin;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;

/**
 * Represents the dialogue plugin used for Sir Gawain.
 *
 * @author afaroutdude
 */
public final class SirGawainDialogue extends DialoguePlugin {

    /**
     * Constructs a new {@code KingArthurDialogue} {@code Object}.
     */
    public SirGawainDialogue() {
        /**
         * empty.
         */
    }

    /**
     * Constructs a new {@code KingArthurDialogue} {@code Object}.
     *
     * @param player the player.
     */
    public SirGawainDialogue(Player player) {
        super(player);
    }

    private Quest quest;

    @Override
    public DialoguePlugin newInstance(Player player) {
        return new SirGawainDialogue(player);
    }

    @Override
    public boolean open(Object... args) {
        npc = (NPC) args[0];
        quest = player.getQuestRepository().getQuest("Merlin's Crystal");

        npc("Good day to you " + (player.isMale() ? "sir" : "madam") + "!");
        stage = 0;

        return true;
    }

    @Override
    public boolean handle(int interfaceId, int buttonId) {

        switch (stage) {
            case 999:
                end();
                break;
            case 0:
                switch(quest.getStage(player)) {
                    case 0:
                        options("Good day.", "Know you of any quests sir knight?");
                        stage = 1;
                        break;
                    case 10:
                        options("Good day.", "Any ideas on how to get Merlin out of that crystal?", "Do you know how Merlin got trapped?");
                        stage = 2;
                        break;
                    case 20:
                        options("Any idea how to get into Morgan Le Faye's stronghold?", "Hello again.");
                        stage = 3;
                        break;
                    case 50:
                        player("Any ideas on finding Excalibur?");
                        stage = 4;
                        break;
                    default:
                        end();
                        break;
                }
            case 1:
                switch(buttonId) {
                    case 1:
                        player("Good day.");
                        stage = 999;
                        break;
                    case 2:
                        player("Know you of any quests sir knight?");
                        stage = 5;
                        break;
                }
                break;
            case 2:
                switch(buttonId) {
                    case 1:
                        player("Good day.");
                        stage = 999;
                        break;
                    case 2:
                        player("Any ideas on how to get Merlin out of that crystal?");
                        stage = 6;
                        break;
                    case 3:
                        player("Do you know how Merlin got trapped?");
                        stage = 7;
                        break;
                }
                break;
            case 3:
                switch(buttonId) {
                    case 1:
                        player("Any idea how to get into Morgan Le Faye's stronghold?");
                        stage = 12;
                        break;
                    case 2:
                        player("Hello again.");
                        stage = 999;
                        break;
                }
                break;
            case 4:
                npc("Unfortunately not, adventurer.");
                stage = 999;
                break;
            case 5:
                npc("The king is the man to talk to if you want a quest.");
                stage = 999;
                break;
            case 6:
                npc("I'm a little stumped myself. We've tried opening it", "with anything and everything!");
                stage = 999;
                break;
            case 7:
                npc("I would guess this is the work of the evil", "Morgan Le Faye!");
                stage++;
                break;
            case 8:
                player("And where could I find her?");
                stage++;
                break;
            case 9:
                npc("She lives in her stronghold to the south of here,", "guarded by some renegade knights led by Sir Mordred.");
                quest.setStage(player, 20);
                player.getQuestRepository().syncronizeTab(player);
                stage++;
                break;
            case 10:
                options("Any idea how to get into Moran Le Faye's stronghold?", "Thank you for the information.");
                stage++;
                break;
            case 11:
                switch(buttonId) {
                    case 1:
                        player("Any idea how to get into Moran Le Faye's stronghold?");
                        stage = 12;
                        break;
                    case 2:
                        player("Thank you for the information.");
                        stage = 13;
                        break;
                }
                break;
            case 12:
                npc("No, you've got me stumped there...");
                stage = 999;
                break;
            case 13:
                npc("It is the least I can do.");
                stage = 999;
                break;

        }
        return true;
    }

    @Override
    public int[] getIds() {
        return new int[]{240};
    }
}