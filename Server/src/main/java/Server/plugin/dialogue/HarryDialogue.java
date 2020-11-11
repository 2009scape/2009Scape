package plugin.dialogue;

import core.game.content.ItemNames;
import core.game.node.entity.npc.NPC;
import core.game.node.item.Item;
import core.plugin.InitializablePlugin;
import core.game.node.entity.player.Player;

/**
 * Represents the dialogue plugin used for the harry npc.
 *
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class HarryDialogue extends DialoguePlugin {
	private final static int FISHBOWL_EMPTY = ItemNames.EMPTY_FISHBOWL_6667;
	private final static int FISHBOWL_WATER = ItemNames.FISHBOWL;
	private final static int FISHBOWL_SEAWEED = ItemNames.FISHBOWL_6669;

	private final static int FISHBOWL_BLUE = ItemNames.FISHBOWL_6670;
	private final static int FISHBOWL_GREEN = ItemNames.FISHBOWL_6671;
	private final static int FISHBOWL_SPINE = ItemNames.FISHBOWL_6672;

	private final static int TINY_NET = ItemNames.TINY_NET_6674;

    /**
     * Constructs a new {@code HarryDialogue} {@code Object}.
     */
    public HarryDialogue() {
    }

    /**
     * Constructs a new {@code HarryDialogue} {@code Object}.
     *
     * @param player the player.
     */
    public HarryDialogue(Player player) {
        super(player);
    }

    @Override
    public DialoguePlugin newInstance(Player player) {
        return new HarryDialogue(player);
    }

    private boolean needsFish() {
        return player.getInventory().containsOneItem(FISHBOWL_SEAWEED);
    }

    private boolean needsSeaWeed() {
        return player.getInventory().containsOneItem(FISHBOWL_WATER);
    }

    private boolean needsFood() {
        return player.getInventory().containsOneItem(new int[] {FISHBOWL_SEAWEED, FISHBOWL_BLUE, FISHBOWL_GREEN, FISHBOWL_SPINE});
    }

    @Override
    public boolean open(Object... args) {
        npc = (NPC) args[0];
        interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Welcome! You can buy Fishing equipment at my store.", "We'll also give you a good price for any fish that you", "catch.");
        if (needsFish() || needsSeaWeed()) {
            stage = 10;
        } else if (needsFood()) {
            stage = 20;
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
            case 0:
                options("Let's see what you've got, then.", "Sorry, I'm not interested.");
                stage = 1;
                break;
            case 1:
                switch (buttonId) {
                    case 1:
                        player("Let's see what you've got, then.");
                        stage++;
                        break;
                    case 2:
                        player("Sorry, I'm not interested.");
                        stage = 999;
                        break;
                }
                break;
            case 2:
				end();
				npc.openShop(player);
				break;
            case 10:
                options("Let's see what you've got, then.", "Can I get a fish for this bowl?", "Do you have any fishfood?", "Sorry, I'm not interested.");
                stage++;
                break;
            case 11:
                switch (buttonId) {
                    case 1:
                        player("Let's see what you've got, then.");
                        stage = 2;
                        break;
                    case 2:
                        player("Can I get a fish for this bowl?");
                        stage = 30;
                        break;
                    case 3:
                        player("Do you have any fishfood?");
                        stage = 40;
                        break;
                    case 4:
                        player("Sorry, I'm not interested.");
                        stage = 999;
                        break;
                }
                break;
            case 20:
                options("Let's see what you've got, then.", "Do you have any fishfood?", "Sorry, I'm not interested.");
                stage++;
                break;
            case 21:
                switch (buttonId) {
                    case 1:
                        player("Let's see what you've got, then.");
                        stage = 2;
                        break;
                    case 2:
                        player("Do you have any fishfood?");
                        stage = 40;
                        break;
                    case 3:
                        player("Sorry, I'm not interested.");
                        stage = 999;
                        break;
                }
                break;
            case 30:
                if (!needsFish()) { // ergo, needsSeaWeed() == true
                    npc("Sorry, you need to put some seaweed into the bowl", "first.");
                    stage++;
                } else {
                    npc("Yes you can!");
                    stage = 33;
                }
                break;
            case 31:
                player("Seaweed?");
                stage++;
                break;
            case 32:
                npc("Yes, the fish seem to like it. Come and see me when", "you have put some in the bowl.");
                stage = 999;
                break;
            case 33:
                npc("I can see that you have a nicely filled fishbowl there to", "use, and you can catch a fish from my aquarium if", "you want.");
                stage++;
                break;
            case 34:
                npc("You will need a special net to do this though, and I sell", "them for 10 gold.");
                stage++;
                break;
            case 35:
                options("I'll take it!", "No thanks, later maybe");//[sic] no punctuation on last option
                stage++;
                break;
            case 36:
                switch (buttonId) {
                    case 1:
                        player("I'll take it!");
                        stage++;
                        break;
                    case 2:
                        player("No thanks, later maybe");
                        stage = 999;
                        break;
                }
                break;
            case 37:
                if (player.getInventory().getAmount(995) >= 10) {
                    if (!player.getInventory().hasSpaceFor(new Item(TINY_NET))) {
                        npc("Here you... oh.");
                        stage = 38;
                    } else {
                        npc("Here you go!");
                        if (player.getInventory().remove(new Item(995, 10))) {
                            player.getInventory().add(new Item(TINY_NET));
                            player.getPacketDispatch().sendMessage("Harry sells you a tiny net!");
                        }
                        stage = 999;
                    }
                } else {
                    npc("Well, I'll be happy to give you the net once you", "have the cash, but not before!");
                    stage = 999;
                }
                break;
            case 38:
                npc("Well, you don't seem to have any free space for", "this right now. Come back later when you do.");
                stage = 999;
                break;
            case 40:
                npc("Sorry, I'm all out. I used up the last of it feeding the", "fish in the aquarium.");
                stage++;
                break;
            case 41:
                npc("I have some empty boxes though, they have the", "ingredients written on the back. I'm sure if you pick up", "a pestle and mortar you will be able to make your own.");
                stage++;
                break;
            case 42:
                npc("Here. I can hardly charge you for an empty box.");
                player.getInventory().add(new Item(ItemNames.AN_EMPTY_BOX_6675));
                if (needsFood() && !needsSeaWeed()) {
                    stage++;
                } else {
                    stage = 999;
                }
                break;
            case 43:
                npc("Take good care of that fish!");
                stage = 999;
                break;
        }
        return true;
    }

    @Override
    public int[] getIds() {
        return new int[]{576};
    }
}
