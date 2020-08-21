package plugin.quest.free.blackknightsfortress;

import plugin.dialogue.DialoguePlugin;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.quest.Quest;
import core.game.node.item.GroundItemManager;
import core.game.node.item.Item;

/**
 * Represents the sir amik varze dialogue.
 * @author Vexia
 * 
 */
public class SirAmikVarzeDialogue extends DialoguePlugin {

	/**
	 * Represents the instanced quest.
	 */
	private Quest quest;

	/**
	 * Constructs a new {@code SirAmikVarzeDialogue} {@code Object}.
	 */
	public SirAmikVarzeDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code SirAmikVarzeDialogue} {@code Object}.
	 * @param player the player.
	 */
	public SirAmikVarzeDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new SirAmikVarzeDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		quest = player.getQuestRepository().getQuest("Black Knights' Fortress");
		switch (quest.getStage(player)) {
		case 30:
			interpreter.sendDialogues(player, null, "I have ruined the Black Knights' invincibility potion.");
			stage = 0;
			break;
		case 10:
		case 20:
			interpreter.sendDialogues(npc, null, "How's the mission going?");
			stage = 0;
			break;
		case 100:
			interpreter.sendDialogues(player, null, "Hello Sir Amik.");
			stage = 0;
			break;
		default:
			interpreter.sendDialogues(npc, null, "I am the leader of the White Knights of Falador. Why", "do you seek my audience?");
			stage = 0;
			break;
		}
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (quest.getStage(player)) {
		case 100:
			switch (stage) {
			case 0:
				interpreter.sendDialogues(npc, null, "Hello, friend!");
				stage = 1;
				break;
			case 1:
				interpreter.sendDialogues(player, null, "Do you have any other quests for me to do?");
				stage = 2;
				break;
			case 2:
				interpreter.sendDialogues(npc, null, "Quests, eh?", "Well, I don't have anything on the go at the moment,", "but there is an organisation that is always looking for", "capable adventurers to assist them.");
				stage = 3;
				break;
			case 3:
				interpreter.sendDialogues(npc, null, "Your excellent work sorting out those Black Knights", "means I will happily write you a letter of", "recommendation.");
				stage = 4;
				break;
			case 4:
				interpreter.sendDialogues(npc, null, "Would you like me to put your name forwards to", "them?");
				stage = 5;
				break;
			case 5:
				interpreter.sendDialogues(player, null, "No thanks.");
				stage = 6;
				break;
			case 6:
				end();
				break;
			}
			break;
		case 30:
			switch (stage) {
			case 0:
				interpreter.sendDialogues(npc, null, "Yes, we have just received a message from the Black", "Knights saying they withdraw their demands, which", "would seem to confirm your story.");
				stage = 1;
				break;
			case 1:
				interpreter.sendDialogues(player, null, "No I believe there was some talk of a cash reward...");
				stage = 2;
				break;
			case 2:
				interpreter.sendDialogues(npc, null, "Absolutely right. Please accept this reward.");
				stage = 3;
				break;
			case 3:
				interpreter.sendDialogue("Sir Amik hands you 2500 coins.");
				stage = 4;
				break;
			case 4:
				if (!player.getInventory().add(new Item(995, 2500))) {
					GroundItemManager.create(new Item(995, 2500), player);
				}
				quest.finish(player);
				player.getQuestRepository().syncronizeTab(player);
				end();
				break;
			}
			break;
		case 20:
			switch (stage) {
			case 0:
				interpreter.sendDialogues(player, null, "I have managed to find what the secret weapon is", "I am now in the process of destroying it.");
				stage = 1;
				break;
			case 1:
				end();
				break;
			}
			break;
		case 10:
			switch (stage) {
			case 0:
				interpreter.sendDialogues(player, null, "I haven't managed to find what the secret weapon is", "yet...");
				stage = 1;
				break;
			case 1:
				interpreter.sendDialogues(npc, null, "Well keep at it! Falador's future is at stake!");
				stage = 2;
				break;
			case 2:
				if (!player.getInventory().containsItem(BlackKnightsFortress.DOSSIER) && !player.getBank().containsItem(BlackKnightsFortress.DOSSIER)) {
					interpreter.sendDialogues(npc, null, "Here's the dossier on the case.");
					stage = 3;
				} else {
					interpreter.sendDialogues(npc, null, "Don't forget to read that dossier of information I gave", "you.");
					stage = 4;
				}
				break;
			case 3:
				player.getInventory().add(BlackKnightsFortress.DOSSIER);
				end();
				break;
			case 4:
				end();
				break;
			}
			break;
		case 0:
			switch (stage) {
			case 0:
				if (player.getQuestRepository().getPoints() < 12) {
					interpreter.sendDialogues(player, null, "I don't I'm just looking around.");
					stage = 2;
				} else {
					interpreter.sendOptions("Select an Option", "I seek a quest!", "I don't, I'm just looking around.");
					stage = 1;
				}
				break;
			case 1:
				switch (buttonId) {
				case 1:
					interpreter.sendDialogues(player, null, "I seek a quest.");
					stage = 5;
					break;
				case 2:
					interpreter.sendDialogues(player, null, "I don't I'm just looking around.");
					stage = 2;
					break;
				}
				break;
			case 2:
				end();
				break;
			case 3:
				interpreter.sendDialogues(npc, null, "Ok. Please don't break anything.");
				stage = 4;
				break;
			case 4:
				end();
				break;
			case 5:
				interpreter.sendDialogues(npc, null, "Well, I need some spy work doing but it's quite", "dangerous. It will involve going into the Black Knights'", "fortress.");
				stage = 6;
				break;
			case 6:
				interpreter.sendOptions("Select an Option", "I laugh in the fance of danger!", "I go and cower in the corner at the first sign of danger!");
				stage = 7;
				break;
			case 7:
				switch (buttonId) {
				case 1:
					interpreter.sendDialogues(player, null, "I laugh in the face of danger!");
					stage = 15;
					break;
				case 2:
					interpreter.sendDialogues(player, null, "I go and cower in a corner at the first sign of danger!");
					stage = 8;
					break;
				}
				break;
			case 8:
				interpreter.sendDialogues(npc, null, "Err....");
				stage = 9;
				break;
			case 9:
				interpreter.sendDialogues(npc, null, "Well.");
				stage = 10;
				break;
			case 10:
				interpreter.sendDialogues(npc, null, "I... suppose spy work DOES involve a little hiding in", "corners.");
				stage = 11;
				break;
			case 11:
				interpreter.sendOptions("Select an Option", "Oh. I suppose I'll give it a go then.", "No, I'm not ready to do that.");
				stage = 12;
				break;
			case 12:
				switch (buttonId) {
				case 1:
					interpreter.sendDialogues(player, null, "Oh. I suppose I'll give it a go then.");
					stage = 17;
					break;
				case 2:
					interpreter.sendDialogues(player, null, "No, I'm not ready to do that.");
					stage = 13;
					break;
				}
				break;
			case 13:
				interpreter.sendDialogues(npc, null, "Come see me again if you change your mind.");
				stage = 14;
				break;
			case 14:
				end();
				break;
			case 15:
				interpreter.sendDialogues(npc, null, "Well that's good. Don't get too overconfident though.");
				stage = 16;
				break;
			case 16:
				interpreter.sendDialogues(npc, null, "You've come along at just the right time actually. All of", "my knights are already known to the Black Knights.");
				stage = 17;
				break;
			case 17:
				interpreter.sendDialogues(npc, null, "Subtley isn't exactly our strong point.");
				stage = 18;
				break;
			case 18:
				interpreter.sendDialogues(player, null, "Can't you just take your White Knights' armour off?", "They wouldn't recognise you then!");
				stage = 19;
				break;
			case 19:
				interpreter.sendDialogues(npc, null, "I am afraid our charter prevents us using espionage in", "any form, that is the domain of the Temple Knights.");
				stage = 20;
				break;
			case 20:
				interpreter.sendDialogues(player, null, "Temple Knights? Who are they?");
				stage = 21;
				break;
			case 21:
				interpreter.sendDialogues(npc, null, "The information is classified. I am forbidden to share it", "with outsiders.");
				stage = 22;
				break;
			case 22:
				interpreter.sendDialogues(player, null, "So... What do you need doing?");
				stage = 23;
				break;
			case 23:
				interpreter.sendDialogues(npc, null, "Well, the Black Knights have started making strange", "threats to us; demanding large amounts of money and", "land, and threating to invade Falador if we don't pay", "them.");
				stage = 24;
				break;
			case 24:
				interpreter.sendDialogues(npc, null, "Now, NORMALLY this wouldn't be a problem...");
				stage = 25;
				break;
			case 25:
				interpreter.sendDialogues(npc, null, "But they claim to have a powerful new secret weapon.");
				stage = 26;
				break;
			case 26:
				interpreter.sendDialogues(npc, null, "Your mission, should you decide to accept it, is to", "infiltrate their fortress, find out what their secret", "weapon is, and then sabotage it.");
				stage = 27;
				break;
			case 27:
				interpreter.sendOptions("Select an Option", "Ok, I'll do my best.", "No, I'm not ready to do that.");
				stage = 28;
				break;
			case 28:
				switch (buttonId) {
				case 1:
					interpreter.sendDialogues(player, null, "Ok, I'll do my best.");
					stage = 31;
					break;
				case 2:
					interpreter.sendDialogues(player, null, "No, I'm not ready to do that.");
					stage = 29;
					break;
				}
				break;
			case 29:
				interpreter.sendDialogues(npc, null, "Come see me again if you change your mind.");
				stage = 30;
				break;
			case 30:
				end();
				break;
			case 31:
				interpreter.sendDialogues(npc, null, "Good luck! Let me know how you get on. Here's the", "dossier for the case, I've already given you the details.");
				stage = 32;
				break;
			case 32:
				if (player.getInventory().add(BlackKnightsFortress.DOSSIER)) {
					quest.start(player);
				} else {
					player.getPacketDispatch().sendMessage("You don't have enough inventory space.");
				}
				end();
				break;
			}
			break;
		default:
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 608 };
	}
}