package plugin.dialogue;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.entity.player.link.quest.Quest;
import org.crandor.game.node.item.GroundItemManager;
import org.crandor.game.node.item.Item;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.world.GameWorld;

/**
 * Represents the dialogue plugin used for the doric npc.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class DoricDialogue extends DialoguePlugin {

	/**
	 * Represents the pickaxe item.
	 */
	private static final Item PICKAXE = new Item(1265, 1);

	/**
	 * Represents the requirement items.
	 */
	private static final Item[] REQUIREMENTS = new Item[] { new Item(436, 4), new Item(434, 6), new Item(440, 2) };

	/**
	 * Constructs a new {@code DoricDialogue} {@code Object}.
	 */
	public DoricDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code DoricDialogue} {@code Object}.
	 * @param player the player.
	 */
	public DoricDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new DoricDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		final Quest quest = player.getQuestRepository().getQuest("Doric's Quest");
		if (!quest.isStarted(player)) {
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Hello traveller, what brings you to my humble smithy?");
			stage = 0;
		}
		if (quest.isStarted(player)) {
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Have you got my materials yet, traveller?");
			stage = 100;
		}
		if (quest.isCompleted(player)) {
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Hello traveller, how is your metalworking coming along?");
			stage = 500;
		}
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			interpreter.sendOptions("Select an Option", "I wanted to use your anvils.", "I want to use your whetstone.", "Mind your own business, shortstuff!", "I was just checking out the landscape.", "What do you make here?");
			stage = 1;
			break;
		case 1:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "I wanted to use your anvils.");
				stage = 10;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "I want to use your whetsone.");
				stage = 20;
				break;
			case 3:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Mind your own business, shortstuff!");
				stage = 30;
				break;
			case 4:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "I was just checking out the landscape.");
				stage = 40;
				break;
			case 5:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "What do you make here?");
				stage = 50;
				break;

			}
			break;
		case 10:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "My anvils get enough work with my own use. I make", "pickaxes, and it takes a lot of hard work. If you could", "get me some more materials, then I could let you use", "them.");
			stage = 11;
			break;
		case 11:
			interpreter.sendOptions("Select an Option", "Yes, I will get you the materials.", "No, hitting rocks if for the boring people, sorry.");
			stage = 12;
			break;
		case 12:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Yes I will get you the materials.");
				stage = 15;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "No, hitting rocks is for the boring people, sorry.");
				stage = 13;
				break;
			}
			break;
		case 13:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "That is your choice. Nice to meet you anyway.");
			stage = 14;
			break;
		case 14:
			end();
			break;
		case 15:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Clay is what I use more than anything, to make casts.", "Could you get me 6 clay, 4 copper ore, and 2 iron ore,", "please? I could pay a little, and let you use my anvils.", "Take this pickaxe with you just in case you need it.");
			stage = 16;
			break;
		case 16:
			player.getQuestRepository().getQuest("Doric's Quest").setStage(player, 1);
			player.getQuestRepository().syncronizeTab(player);
			if (!player.getInventory().add(PICKAXE)) {
				GroundItemManager.create(PICKAXE, player.getLocation());
			}
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Certainly, I'll be right back!");
			stage = 17;
			break;
		case 17:
			end();
			break;
		case 20:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "The whetstone is for more advanced smithing, but I", "could let you use it as well as my anvils if you could", "get me some more materials.");
			stage = 11;
			break;
		case 30:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "How nice to meet someone with such pleasant manners.", "Do come again when you need to shout at someone", "smaller than you!");
			stage = 31;
			break;
		case 31:
			end();
			break;
		case 40:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Hope you like it. I do enjoy the solitude of my little", "home. If you get time, please say hi to my friends in", "the Dwarven Mine.");
			stage = 41;
			break;
		case 41:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Will do!");
			stage = 42;
			break;
		case 42:
			end();
			break;
		case 50:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "I make pickaxes. I am the best maker of pickaxes in the", "whole of " + GameWorld.getName() + ".");
			stage = 51;
			break;
		case 51:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Do you have any to sell?");
			stage = 52;
			break;
		case 52:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Sorry, but I've got a running order with Nurmof.");
			stage = 53;
			break;
		case 53:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Ah, fair enough.");
			stage = 54;
			break;
		case 54:
			end();
			break;
		case 100:
			if (player.getInventory().contains(434, 6) && player.getInventory().contains(440, 2) && player.getInventory().contains(436, 4)) {
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "I have everything you need.");
				stage = 200;
			} else {
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Sorry, I don't have them all yet.");
				stage = 101;
			}
			break;
		case 101:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Not to worry, stick at it. Remember, I need 6 clay, 4", "copper ore, and 2 iron ore.");
			stage = 102;
			break;
		case 102:
			interpreter.sendOptions("Select an Option", "Where can I find those?", "Certainly, I'll be right back.");
			stage = 103;
			break;
		case 103:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Where can I find those?");
				stage = 110;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Certainly, I'll be right back.");
				stage = 113;
				break;

			}
			break;
		case 110:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "You'll be able to find all those ores in the rocks just", "inside the Dwarven Mine. Head east from here and", "you'll find the entrance in the side of Ice Mountain.");
			stage = 111;
			break;
		case 111:
			end();
			break;
		case 113:
			end();
			break;
		case 200:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Many thanks. Pass them here, please. I can spare you", "some coins for your trouble, and please use my anvils", "anytime you want.");
			stage = 201;
			break;
		case 201:
			interpreter.sendItemMessage(436, "You hand the clay, copper, and iron to Doric.");
			stage = 202;
			break;
		case 202:
			if (player.getInventory().remove(REQUIREMENTS)) {
				end();
				player.getQuestRepository().getQuest("Doric's Quest").finish(player);
			}
			break;
		case 500:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Not too bad, Doric.");
			stage = 501;
			break;
		case 501:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Good, the love of metal is a thing close to my heart.");
			stage = 502;
			break;
		case 502:
			end();
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 284 };
	}
}
