package plugin.dialogue;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.content.global.Skillcape;
import org.crandor.game.content.skill.Skills;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.game.node.entity.player.Player;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.node.item.Item;

/**
 * Represents the dialogue plugin used for the capn izzy dialogue.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class CapnIzzyDialogue extends DialoguePlugin {

	/**
	 * Represents the parrot npc id.
	 */
	private static final int PARROT = 4535;

	/**
	 * Constructs a new {@code CapnIzzyDialogue.java} {@code Object}.
	 */
	public CapnIzzyDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code CapnIzzyDialogue.java} {@code Object}.
	 * @param player the player.
	 */
	public CapnIzzyDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new CapnIzzyDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		if (args.length > 1) {
			interpreter.sendDialogues(PARROT, null, "Clap him in irons!");
			stage = 200;
			return true;
		}
		interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Ahoy Cap'n!");
		stage = 0;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Ahoy there!");
			stage = 90;
			break;
		case 90:
			interpreter.sendDialogues(PARROT, null, "Avast ye scurvy swabs!");
			stage = 91;
			break;
		case 91:
			interpreter.sendDialogues(player, null, "Huh?");
			stage = 92;
			break;
		case 92:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Don't mind me parrot, he's Cracked Jenny's Tea Cup!");
			stage = 1;
			break;
		case 1:
			interpreter.sendOptions("Select an Option", "What is this place?", "What do I do in the arena?", "I'd like to use the Agility Arena, please.", "Can you tell me a bit about the Skillcape of Agility, please.", "See you later.");
			stage = 2;
			break;
		case 2:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "What is this place?");
				stage = 10;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "What do I do in the arena?");
				stage = 20;
				break;
			case 3:
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "I'd like to use the Agility Arena, please.");
				stage = 30;
				break;
			case 4:
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Can you tell me a bit about the Skillcape of Agility,", "please?");
				stage = 40;
				break;
			case 5:
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "See you later.");
				stage = 50;
				break;
			}
			break;
		case 10:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "This, me hearty, is the entrance to the Brimhaven", "Agility Arena!");
			stage = 11;
			break;
		case 11:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "I were diffin for buried treasure when I found it!", "Amazed I was! It was a sight to behold!");
			stage = 12;
			break;
		case 12:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "It were the biggest thing I'd ever seen! it must've been", "at least a league from side to side!");
			stage = 13;
			break;
		case 13:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "It made me list, I were that excited!");
			stage = 14;
			break;
		case 14:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "I'd found a huge cave with all these platforms. I reckon", "it be an ancient civilisation that made it. I had to be", "mighty careful as there was these traps everywehre!", "Dangerous it was!");
			stage = 15;
			break;
		case 15:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Entrance is only 200 coins!");
			stage = 1;
			break;
		case 20:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Well, me hearty, it's simple. Ye can cross between two", "platforms by using the traps or obstacles strung across", "'em. Try and make your way to the pillar that is", "indicated by the flashing arrow.");
			stage = 21;
			break;
		case 21:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Ye receive tickets for tagging more than one pillar in a", "row. So ye won't get a ticket from the first pillar but", "ye will for every platform ye tag in a row after that.");
			stage = 22;
			break;
		case 22:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "If ye miss a platform ye will miss out on the next ticket", "so try and get every platform you can! When ye be", "done, take the tickets to Jackie over there and she'll", "exchange them for more stuff!");
			stage = 23;
			break;
		case 23:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Thanks me hearty!");
			stage = 24;
			break;
		case 24:
			interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Thanks!");
			stage = 1;
			break;
		case 30:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Aye, entrance be 200 coins.");
			stage = 31;
			break;
		case 31:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "A word of warning me hearty! There are dangerous", "traps down there!");
			stage = 32;
			break;
		case 32:
			interpreter.sendOptions("Select an Option", "Ok, here's 200 coins.", "Never mind.");
			stage = 33;
			break;
		case 33:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Ok, here's 200 coins.");
				stage = 35;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Never mind.");
				stage = 34;
				break;
			}
			break;
		case 34:
			end();
			break;
		case 35:
			if (!player.getAttribute("capn_izzy", false)) {
				if (player.getInventory().contains(995, 200) && player.getInventory().remove(new Item(995, 200))) {
					end();
					player.getDialogueInterpreter().sendItemMessage(new Item(995, 200), "You give Cap'n Izzy the 200 coin entrance fee.");
					player.getPacketDispatch().sendMessage("You give Cap'n Izzy the 200 coin entrance fee.");
					player.setAttribute("/save:capn_izzy", true);
					return true;
				} else {
					player.getPacketDispatch().sendMessage("You don't have the 200 coin entrance fee.");
					end();
				}
			} else {
				player.getDialogueInterpreter().sendDialogues(npc, null, "Avast there, ye've already paid!");
				stage = 36;
			}
			break;
		case 36:
			end();
			break;
		case 40:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Aye, to be sure! The Skillcape of Agility be the symbol", "of the master of dexterity! One who wears it can climb", "like a cat, run like the wind and jump like...err, well", "jump like a jumping thing! Now, be there anything else");
			stage = 41;
			break;
		case 41:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "ye'd like to know?");
			if (!Skillcape.isMaster(player, Skills.AGILITY)) {
				stage = 1;
			} else {
				stage = 99;
			}
			break;
		case 99:
			interpreter.sendDialogues(player, null, "I'd like to buy an Agility Skillcape.");
			stage = 100;
			break;
		case 100:
			interpreter.sendDialogues(npc, null, "That will cost you 99,000 gold coins.");
			stage = 101;
			break;
		case 101:
			interpreter.sendOptions("Select an Option", "Okay, I'll pay it.", "Nevermind.");
			stage = 102;
			break;
		case 102:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, null, "Okay, I'll pay it.");
				stage = 104;
				break;
			case 2:
				interpreter.sendDialogues(player, null, "Nevermind.");
				stage = 103;
				break;
			}
			break;
		case 104:
			if (Skillcape.purchase(player, Skills.AGILITY)) {
				interpreter.sendDialogues(npc, null, "There you go hearty! You're truly a master of Agility.");
			}
			stage = 105;
			break;
		case 105:
			end();
			break;
		case 103:
			end();
			break;
		case 50:
			end();
			break;
		case 200:
			interpreter.sendDialogues(npc, null, "Ahoy there! Pay up first!");
			stage = 201;
			break;
		case 201:
			end();
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 437 };
	}
}
