package plugin.dialogue;

import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.game.node.item.Item;
import core.game.content.ItemNames;
import core.plugin.InitializablePlugin;

@InitializablePlugin
public class SeravelDialogue extends DialoguePlugin {
    public SeravelDialogue() {
    }
    public SeravelDialogue(Player player) {
        super(player);
    }

    private static final Item COINS = new Item(995, 20);
    private static final Item TICKET = new Item(ItemNames.SHIP_TICKET_621);

    @Override
    public DialoguePlugin newInstance(Player player) {
        return new SeravelDialogue(player);
    }

    @Override
    public boolean open(Object... args) {
        npc = (NPC) args[0];
        player("Hello.");
        stage = -1;
        return true;
    }

    @Override
    public boolean handle(int interfaceId, int buttonId) {
        switch (stage) {
            case 999:
                end();
                break;
            case -1:
                npc("Hello Bwana. Are you interested in buying a ticket", "for the 'Lady of the Waves'?");
                stage++;
                break;
            case 0:
                options("Yes, that sounds great!", "No thanks.", "Tell me more about the ship.");
                stage++;
                break;
            case 1:
                switch (buttonId) {
                    case 1:
                        player("Yes, that sounds great!");
                        stage = 10;
                        break;
                    case 2:
                        player("No thanks.");
                        stage = 20;
                        break;
                    case 3:
                        player("Tell me more about the ship.");
                        stage = 30;
                        break;
                }
                break;
            case 10:
                if (player.getInventory().containsItem(COINS)) {
                    if (player.getInventory().hasSpaceFor(TICKET)) {
                        npc("Great, nice doing business with you.");
                        player.getInventory().remove(COINS);
                        player.getInventory().add(TICKET);
                    } else {
                        npc("Sorry Bwana, you don't have enough space. Come back", "when you do!");
                    }
                } else {
                    npc("Sorry Bwana, you don't have enough money. Come back", "when you have 25 Gold Pieces.");
                }
                stage = 999;
                break;
            case 20:
                npc("Fair enough Bwana, let me know if you change your", "mind.");
                stage = 999;
                break;
            case 30:
                npc("It's a ship that can take you to either Port Sarim", "or Port Khazard. The ship lies west of Shilo Village", "and south of Cairn Island.");
                stage++;
                break;
            case 31:
                npc("The tickets cost 25 Gold Pieces. Would you like to", "purchase a ticket Bwana?");
                stage++;
                break;
            case 32:
                options("Yes, that sounds great!", "No thanks.");
                stage = 1;
                break;
        }
        return true;
    }

    @Override
    public int[] getIds() {
        return new int[] { 514 };
    }
}
