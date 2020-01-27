package plugin.dialogue;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.game.node.entity.player.Player;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.node.item.Item;

/**
 * Represents the dialogue plugin used for the wise old man.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class WiseOldManDialogue extends DialoguePlugin {

    /**
     * Represents the items to use.
     */
    private static final Item[] ITEMS = new Item[] { new Item(9813), new Item(9814) };

    /**
     * Represents the coins item.
     */
    private static final Item COINS = new Item(995, 99000);

    /**
     * Constructs a new {@code WiseOldManDialogue} {@code Object}.
     */
    public WiseOldManDialogue() {
	/**
	 * empty.
	 */
    }

    /**
     * Constructs a new {@code WiseOldManDialogue} {@code Object}.
     * @param player the player.
     */
    public WiseOldManDialogue(Player player) {
	super(player);
    }

    @Override
    public DialoguePlugin newInstance(Player player) {
	return new WiseOldManDialogue(player);
    }

    @Override
    public boolean open(Object... args) {
	npc = (NPC) args[0];
	interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Greetings, " + player.getUsername() + ",");
	stage = 0;
	return true;
    }

    @Override
    public boolean handle(int interfaceId, int buttonId) {
	switch (stage) {
	case 0:
	    if (player.getQuestRepository().hasCompletedAll()) {
		options("Quest Point Cape.", "Something else.");
		stage = 500;
		return true;
	    }
	    interpreter.sendOptions("What would you like to say?", "Is there anything I can do for you?", "Could you check my things for junk, please?", "I've got something I'd like you to look at.");
	    stage = 1;
	    break;
	case 500:
	    switch (buttonId) {
	    case 1:
		player("I believe you are the person to talk to if I want to buy", "a Quest Point Cape?");
		stage = 501;
		break;
	    case 2:
		interpreter.sendOptions("What would you like to say?", "Is there anything I can do for you?", "Could you check my things for junk, please?", "I've got something I'd like you to look at.");
		stage = 1;
		break;
	    }
	    break;
	case 501:
	    npc("Indeed you believe rightly, " + player.getUsername() + ", and if you know that", "then you'll also know that they cost 99000 coins.");
	    stage = 502;
	    break;
	case 502:
	    options("No, I hadn't heard that!", "Yes, so I was lead to believe.");
	    stage = 503;
	    break;
	case 503:
	    switch (buttonId) {
	    case 1:
		player("No, I hadn't heard that!");
		stage = 504;
		break;
	    case 2:
		player("Yes, so I was lead to believe.");
		stage = 506;
		break;
	    }
	    break;
	case 504:
	    npc("Well that's the cost, and it's not changing.");
	    stage = 505;
	    break;
	case 505:
	    end();
	    break;
	case 506:
	    if (player.getInventory().freeSlots() < 2) {
		player("I don't seem to have enough inventory space.");
		stage = 507;
		return true;
	    }
	    if (!player.getInventory().containsItem(COINS)) {
		player("I don't seem to have enough coins with", "me at this time.");
		stage = 507;
		return true;
	    }
	    if (player.getInventory().remove(COINS) && player.getInventory().add(ITEMS)) {
		npc("Have fun with it.");
		stage = 507;
	    } else {
		player("I don't seem to have enough coins with", "me at this time.");
		stage = 507;
	    }
	    break;
	case 507:
	    end();
	    break;
	case 1:
	    switch (buttonId) {
	    case 1:
		interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Is there anything I can do for you?");
		stage = 10;
		break;
	    case 2:
		interpreter.sendOptions("Choose an option:", "Could you check my bank for junk, please?", "Could you check my inventory for junk, please?");
		stage = 20;
		break;
	    case 3:
		interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "I've got something I'd like you to look at.");
		stage = 30;
		break;
	    }

	    break;
	case 10:
	    interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Thanks, but I don't have anything I need.");
	    stage = 11;
	    break;
	case 11:
	    end();
	    break;
	case 20:
	    switch (buttonId) {
	    case 1:
		interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Certainly, but I should warn you that I don't know about", "all items.");
		stage = 100;
		break;
	    case 2:
		interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Certainly, but I should warn you that I don't know about", "all items.");
		stage = 102;
		break;
	    }

	    break;
	case 100:
	    interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "You seem to have no junk in your bank, sorry.");
	    stage = 101;
	    break;
	case 101:
	    end();
	    break;
	case 102:
	    interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "You seem to have no junk in your inventory, sorry.");
	    stage = 101;
	    break;
	case 30:
	    interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Jolly good. Give it to me, and I'll tell you anything I know", "about it.");
	    stage = 31;
	    break;
	case 31:
	    end();
	    break;
	}

	return true;
    }

    @Override
    public int[] getIds() {
	return new int[] {  };
    }
}
