package plugin.dialogue;

import core.game.content.ItemNames;
import core.game.content.global.travel.ship.Ships;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.plugin.InitializablePlugin;
import core.game.node.item.Item;
import core.tools.RandomFunction;

/**
 * Represents the captain barnaby dialogue plugin.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class CaptainShanksDialogue extends DialoguePlugin {

    private Item coins;
    private static final Item TICKET = new Item(ItemNames.SHIP_TICKET_621);

    /**
     * Constructs a new {@code CaptainBarnabyDialogue} {@code Object}.
     */
    public CaptainShanksDialogue() {
        /**
         * empty.
         */
    }

    /**
     * Constructs a new {@code CaptainBarnabyDialogue} {@code Object}.
     * @param player the player.
     */
    public CaptainShanksDialogue(Player player) {
        super(player);
    }

    @Override
    public DialoguePlugin newInstance(Player player) {
        return new CaptainShanksDialogue(player);
    }

    @Override
    public boolean open(Object... args) {
        npc = (NPC) args[0];
        npc("Hello there shipmate! I sail to Khazard Port and", "to Port Sarim. Where are you bound?");
        if (!player.getInventory().containsOneItem(TICKET)) {
            stage = -1;
        } else {
            stage = 0;
        }
        return true;
    }

    @Override
    public boolean handle(int interfaceId, int buttonId) {
        switch (stage) {
            case 999:
                end();
                break;
            case -1:
                coins = new Item(995, RandomFunction.random(20, 50));
                npc("I see you don't have a ticket for the ship, my", "colleague normally only sells them in Shilo village.", "But I could sell you one for a small additional", "charge. Shall we say " + coins.getAmount() + " gold pieces?");
                stage = 3;
                break;
            case 0:
                options("Khazard Port please.", "Port Sarim please.", "Nowhere just at the moment thanks.");
                stage++;
                break;
            case 1:
                switch(buttonId) {
                    case 1:
                        player("Khazard Port please.");
                        if (!player.getInventory().containsItem(TICKET)) {
                            stage = -1;
                        } else {
                            stage = 10;
                        }
                        break;
                    case 2:
                        player("Port Sarim please.");
                        if (!player.getInventory().containsItem(TICKET)) {
                            stage = -1;
                        } else {
                            stage = 20;
                        }
                        break;
                    case 3:
                        player("Nowhere just at the moment thanks.");
                        stage++;
                        break;
                }
                break;
            case 2:
                npc("Very well then me old shipmate, Just let me know", "if you change your mind.");
                stage = 999;
                break;
            case 3:
                interpreter.sendOptions("Buy a ticket for " + coins.getAmount() + " gold pieces.", "Yes, I'll buy a ticket for the ship.", "No thanks, not just at the moment.");
                stage++;
                break;
            case 4:
                switch(buttonId) {
                    case 1:
                        player("Yes, I'll buy a ticket for the ship.");
                        stage = 6;
                        break;
                    case 2:
                        player("No thanks, not just at the moment.");
                        stage++;
                        break;
                }
                break;
            case 5:
                npc("Very well me old shipmate, come back if you change", "your mind now.");
                stage = 999;
                break;
            case 6:
                if (!player.getInventory().containsItem(coins)) {
                    npc("Sorry me old ship mate, but you seem to be", "financially challenged at the moment. Come back", "when your coffers are full!");
                    stage = 999;
                } else if (!player.getInventory().hasSpaceFor(new Item(ItemNames.SHIP_TICKET_621))) {
                    npc("Sorry me old ship mate, it looks like you haven't", "got enough space for a ticket. Come back when", "you've got rid of some of that junk.");
                    stage = 999;
                } else {
                    npc("It's a good deal and no mistake. Here you go me old", "shipmate, here's your ticket.");
                    player.getInventory().remove(coins);
                    player.getInventory().add(new Item(ItemNames.SHIP_TICKET_621));
                    stage++;
                }
                break;
            case 7:
                npc("Ok, now you have your ticket, do you want to sail", "anywhere?");
                stage++;
                break;
            case 8:
                interpreter.sendOptions("Captain Shanks asks, 'Do you want to sail anywhere?'", "Khazard Port please.", "Port Sarim please.", "Nowhere just at the moment thanks.");
                stage = 1;
                break;
            case 10:
                npc("Very well then me old shipmate, I'll just take your ticket and then we'll set sail.");
                stage = 11;
                break;
            case 11:
                end();
                if (player.getInventory().remove(TICKET)) {
                    Ships.sail(player, Ships.CAIRN_ISLAND_TO_PORT_KHAZARD);
                }
                break;
            case 20:
                npc("Very well then me old shipmate, I'll just take your ticket and then we'll set sail.");
                stage = 21;
                break;
            case 21:
                end();
                if (player.getInventory().remove(TICKET)) {
                    Ships.sail(player, Ships.CAIRN_ISLAND_TO_PORT_SARIM);
                }
                break;
        }
        return true;
    }

    @Override
    public int[] getIds() {
        return new int[] { 518 };
    }
}
