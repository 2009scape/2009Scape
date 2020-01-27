package plugin.dialogue;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.item.GroundItem;
import org.crandor.game.node.item.GroundItemManager;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.node.item.Item;

/**
 * Represents the dialogue plugin used for the fritz glass blower npc.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class FritzGlassBlowerDialogue extends DialoguePlugin {

	/**
	 * Represents the molten glass.
	 */
	private static final Item MOLTEN_GLASS = new Item(1775);

	/**
	 * Constructs a new {@code FritzGlassBlowerDialogue} {@code Object}.
	 */
	public FritzGlassBlowerDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code FritzGlassBlowerDialogue} {@code Object}.
	 * @param player the player.
	 */
	public FritzGlassBlowerDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {

		return new FritzGlassBlowerDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		if (!player.getSavedData().getGlobalData().isFritzGlass()) {
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Hello adventurer, welcome to the Entrana furnace.");
			stage = 0;
		} else {
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Ah " + player.getUsername() + ", have you come to sell me some molten", "glass?");
			stage = 100;
		}
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Would you like me to explain my craft to you?");
			stage = 1;
			break;
		case 1:
			interpreter.sendOptions("Select an Option", "Yes please. I'd be fascinated to hear what you do.", "No thanks, I doubt I'll ever turn my hand to glassblowing.");
			stage = 2;
			break;
		case 2:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Yes please. I'd be fascinated to hear what you do.");
				stage = 10;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "No thanks, I doubt I'll ever turn my hand to glassblowing.");
				stage = 22;
				break;
			}
			break;
		case 10:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "I'm extremely pleased to hear that! I've always wanted", "an apprentice. Let me talk to you through the secrets of", "glassblowing.");
			stage = 11;
			break;
		case 11:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Glass is made from soda ash and silica. We get out", "soda ash by collecting seaweed from the rocks - the", "prevailing currents make the north-west corner of the", "island the best place to find it, it can also be found in");
			stage = 12;
			break;
		case 12:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "your nets sometimes when you're fishing, on Karamja", "island or at the Piscatoris Fishing Colonly in the nets", "there. To turn seaweed into soda ash, all you need to", "do is burn it on a fire. Feel free to use the range in");
			stage = 13;
			break;
		case 13:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "my house for that; it's the one directly west of here.", "Next we collect sand from the sandpit that you'll also", "find just west of here, there are other located in", "Yanille and Shilo Village.");
			stage = 14;
			break;
		case 14:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "You'll need a bucket to cary it in. Tell you what, you", "can have this old one of mine.");
			stage = 15;
			break;
		case 15:
			if (!player.getInventory().add(new Item(1925))) {
				GroundItemManager.create(new GroundItem(new Item(1925), player.getLocation(), player));
			}
			player.getSavedData().getGlobalData().setFritzGlass(true);
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Bring the sand and the soda ash back here and melt", "them together in the furnace, and there you have it -", "molten glass!");
			stage = 16;
			break;
		case 16:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "There are many things you can use the molten glass", "for once you have made it. Depending on how talented", "you are, you could try turning it into something, like a", "fishbowl, for example. If you'd like to try your hand at");
			stage = 17;
			break;
		case 17:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "the fine art of glassblowing you can use my spare", "glassblowing pipe. I think I left it on the chest of", "drawers in my house this morning.");
			stage = 18;
			break;
		case 18:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Alternatively I am always happy to buy the molten glass", "from you, saves me running about making it for", "myself.");
			stage = 19;
			break;
		case 19:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "That sounds good. How much will you pay me?");
			stage = 20;
			break;
		case 20:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Tell you what, because you've been interested in my", "art, I'll pay you the premium price of 20 gold pieces", "for each piece of molten glass you bring me.");
			stage = 21;
			break;
		case 21:
			end();
			break;
		case 22:
			end();
			break;
		case 100:
			interpreter.sendOptions("Select an Option", "Yes.", "No.");
			stage = 101;
			break;
		case 101:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Yes.");
				stage = 110;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "No.");
				stage = 120;
				break;
			}
			break;
		case 110:
			if (!player.getInventory().containsItem(MOLTEN_GLASS)) {
				interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Umm, not much point me trying to pay you for glass", "you don't have, is there?");
				stage = 111;
			} else {
				int amt = player.getInventory().getAmount(MOLTEN_GLASS);
				Item remove = new Item(MOLTEN_GLASS.getId(), amt);
				if (!player.getInventory().containsItem(remove)) {
					end();
					return true;
				}
				if (player.getInventory().remove(remove)) {
					player.getInventory().add(new Item(995, amt * 20));
					interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Pleasure doing business with you " + player.getUsername() + ".");
					stage = 118;
				}
			}
			break;
		case 111:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Well, actually, if you don't mind...");
			stage = 112;
			break;
		case 112:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "I guess you've never heard of a rhetorical question", "then. I'll make it simple for you. You bring glass, me", "pay shiny gold coins.");
			stage = 113;
			break;
		case 113:
			end();
			break;
		case 118:
			end();
			break;
		case 120:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Oh.");
			stage = 121;
			break;
		case 121:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "...errr, well should you get any I'm quite happy to pay", "for it. Remember, I'll pay you 20 gold pieces for each", "piece of molten glass you get for me.");
			stage = 122;
			break;
		case 122:
			end();
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 4909 };
	}
}
