package plugin.quest.free.shieldofarrav;

import plugin.dialogue.DialoguePlugin;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.quest.Quest;
import core.game.node.item.Item;

/**
 * Represents the katrine NPC dialogue.
 * @author 'Vexia
 * @version 1.0
 */
public final class KatrineDialogue extends DialoguePlugin {

	/**
	 * Represents the corssbow items.
	 */
	private static final Item CROSSBOWS = new Item(767, 2);

	/**
	 * Represents the quest instance.
	 */
	private Quest quest;

	/**
	 * Constructs a new {@code KatrineDialogue} {@code Object}.
	 */
	public KatrineDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code KatrineDialogue} {@code Object}.
	 * @param player the player.
	 */
	public KatrineDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new KatrineDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		quest = player.getQuestRepository().getQuest("Shield of Arrav");
		switch (quest.getStage(player)) {
		case 80:
		case 90:
		case 100:
		case 70:
			if (ShieldofArrav.isPhoenix(player)) {
				npc("You've got some guts coming here, Phoenix guy!");
				stage = 200;
			} else {
				player("Hey.");
				stage = 0;
			}
			break;
		case 60:
			if (ShieldofArrav.isBlackArmMission(player)) {
				npc("Have you got those crossbows for me yet?");
				stage = 200;
			} else {
				player("What is this place?");
				stage = 0;
			}
			break;
		default:
			player("What is this place?");
			stage = 0;
			break;
		}
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		if (quest.getStage(player) == 60 && stage >= 200) {
			switch (stage) {
			case 200:
				if (!player.getInventory().containsItem(CROSSBOWS)) {
					player("No, I haven't found them yet.");
					stage = 201;
				} else {
					player("Yes, I have.");
					stage = 204;
				}
				break;
			case 201:
				npc("I need two crossbows stolen from the Phoenix Gang", "weapons stash, which if you head east for a bit, is a", "building on the south side of the road.");
				stage = 202;
				break;
			case 202:
				npc("Come back when you got 'em.");
				stage = 203;
				break;
			case 203:
				end();
				break;
			case 204:
				interpreter.sendDialogue("You give the crossbows to Katrine.");
				stage = 205;
				break;
			case 205:
				if (!player.getInventory().containsItem(CROSSBOWS)) {
					end();
					return true;
				}
				if (player.getInventory().remove(CROSSBOWS)) {
					npc("Ok. You can join our gang now. Feel free to enter", "any of the rooms of the ganghouse.");
					stage = 206;
					quest.setStage(player, 70);
					ShieldofArrav.setBlackArm(player);
				}
				break;
			case 206:
				end();
				break;
			}
			return true;
		}
		switch (quest.getStage(player)) {
		case 80:
		case 90:
		case 100:
		case 70:
			switch (stage) {
			case 0:
				npc("Hey.");
				stage = 1;
				break;
			case 1:
				end();
				break;
			case 200:
				interpreter.sendDialogue("Katrine spits.");
				stage = 201;
				break;
			case 201:
				npc("Now get lost!");
				stage = 202;
				break;
			case 202:
				end();
				break;
			case 206:
				end();
				break;
			}
			break;
		case 60:
		case 50:
			switch (stage) {
			case 0:
				npc("It's a private business. Can I help you at all?");
				stage = 1;
				break;
			case 1:
				options("I've heard you're the Black Arm Gang.", "What sort of business?", "I'm looking for fame and riches.");
				stage = 2;
				break;
			case 2:
				switch (buttonId) {
				case 1:
					player("I've heard you're the Black Arm Gang.");
					stage = 100;
					break;
				case 2:
					player("What sort of business?");
					stage = 10;
					break;
				case 3:
					player("I'm looking for fame and riches.");
					stage = 20;
					break;
				}
				break;
			case 100:
				npc("Who told you that?");
				stage = 101;
				break;
			case 101:
				options("I'd rather not reveal my sources.", "It was Charlie, the tramp outside.", "Everyone knows - it's no great secret.");
				stage = 102;
				break;
			case 102:
				switch (buttonId) {
				case 1:
					player("I'd rather not reveal my sources.");
					stage = 110;
					break;
				case 2:
					player("It was Charlie, the tramp outside.");
					stage = 120;
					break;
				case 3:
					player("Everyone knows - it's not great secret.");
					stage = 130;
					break;
				}
				break;
			case 110:
				npc("Yes, I can understand that. So what do you want with", "us?");
				stage = 111;
				break;
			case 111:
				options("I want to become a member of your gang.", "I want some hints for becoming a thief.", "I'm looking for the door out of here.");
				stage = 112;
				break;
			case 112:
				switch (buttonId) {
				case 1:
					player("I want to become a member of your gang.");
					stage = 160;
					break;
				case 2:
					player("I want some hints for becoming a thief.");
					stage = 113;
					break;
				case 3:
					player("I'm looking for the door out of here.");
					stage = 115;
					break;
				}
				break;
			case 160:
				npc("How unusual.");
				stage = 161;
				break;
			case 161:
				npc("Normally we recruit for our gang by watching local", "thugs and thieves in reward. People don't normally waltz", "in here saying 'hello, can I play'.");
				stage = 162;
				break;
			case 162:
				npc("How can I be sure you can be trusted?");
				stage = 163;
				break;
			case 163:
				options("Well, you can give me a try can't you?", "Well, people tell me I have an honest face.");
				stage = 164;
				break;
			case 164:
				switch (buttonId) {
				case 1:
					player("Well, you can give me a try can't you?");
					stage = 167;
					break;
				case 2:
					player("Well, people tell me I have an honest face.");
					stage = 165;
					break;
				}
				break;
			case 165:
				npc("... How unusual. Someone honest wanting to join a", "gang of thieves. Excuse me if I remain unconvinced.");
				stage = 166;
				break;
			case 166:
				npc("Thinking about it... I may have a solution actually.");
				stage = 169;
				break;
			case 167:
				npc("I'm not so sure.");
				stage = 166;
				break;
			case 169:
				npc("Our rival gang - the Phoenix Gang - has a weapons", "stash a little east of here.");
				stage = 170;
				break;
			case 170:
				npc("We're fresh out of crossbows, so if you could steal a", "couple of crossbows for us it would be very much", "appreciated.");
				stage = 171;
				break;
			case 171:
				npc("Then I'll be happy to call you a Black Arm.");
				stage = 172;
				break;
			case 172:
				player("Sounds simple enough. Any particular reason you need", "two of them?");
				stage = 173;
				break;
			case 173:
				npc("I have an idea for framing a local merchant who is", "refusing to pay our, very reasonable, 'keep-your-life-", "pleasant' insurance rates. I need two phoenix crossbows;", "one to kill somebody important with and the other to");
				stage = 174;
				break;
			case 174:
				npc("hide in the merchant's house where the local law can", "find it! When they find it, they'll suspect him of", "murdering the target for the Phoenix gang and", "hopefully, arrest the whole gang! Leaving us as the only");
				stage = 175;
				break;
			case 175:
				npc("thieves gang in Varrock! Brilliant, eh?");
				stage = 176;
				break;
			case 176:
				player("Yeah, brilliant. So who are you planning to murder?");
				stage = 177;
				break;
			case 177:
				npc("I haven't decided yet, but it'll need to be somebody", "important. Say, why you being so nosey? You aren't", "with the law are you?");
				stage = 178;
				break;
			case 178:
				player("No, no! Just curious.");
				stage = 179;
				break;
			case 179:
				npc("You'd better just keep your mouth shut about this plan,", "or I'll make sure it stays shut for you. Now, are you", "going to get those crossbows or not?");
				stage = 180;
				break;
			case 180:
				options("Ok, no problem.", "Sounds a little tricky. Got anything easier?");
				stage = 181;
				break;
			case 181:
				switch (buttonId) {
				case 1:
					player("Ok, no problem.");
					stage = 184;
					break;
				case 2:
					player("Sounds a little tricky. Got anything easier?");
					stage = 182;
					break;
				}
				break;
			case 182:
				npc("If you're not up to a little bit of dager I don't think", "you've got anything to offer our gang.");
				stage = 183;
				break;
			case 183:
				end();
				break;
			case 184:
				quest.setStage(player, 60);
				ShieldofArrav.setBlackArmMission(player);
				npc("Great! You'll find the Phoenix gang's weapon stash just", "next to a temple, due east of here.");
				stage = 185;
				break;
			case 185:
				end();
				break;
			case 113:
				npc("Well, I'm sorry luv, I'm not giving away any of my", "secrets.");
				stage = 114;
				break;
			case 114:
				end();
				break;
			case 115:
				interpreter.sendDialogue("Katrine groans.");
				stage = 116;
				break;
			case 116:
				npc("Try... the one you just came in?");
				stage = 117;
				break;
			case 117:
				end();
				break;
			case 120:
				npc("Is that guy still out there? He's getting to be a", "nuisance. Remind me to send someone to kill him.");
				stage = 121;
				break;
			case 121:
				end();
				break;
			case 130:
				npc("I thought we were safe back here!");
				stage = 131;
				break;
			case 131:
				player("Oh no, not at all... It's so obvious! Even the town", "guard have caught on...");
				stage = 132;
				break;
			case 132:
				npc("Wow! We MUSE be obvious! I guess they'll be", "expecting bribes again soon in that case.");
				stage = 133;
				break;
			case 133:
				npc("Thanks for the information.");
				stage = 134;
				break;
			case 134:
				end();
				break;
			case 10:
				npc("A small, family business. We give financial advice to", "other companies.");
				stage = 11;
				break;
			case 11:
				end();
				break;
			case 20:
				npc("And you expect to find it up the back streets of", "Varrock?");
				stage = 21;
				break;
			case 21:
				end();
				break;
			}
			break;
		default:
			switch (stage) {
			case 0:
				npc("It's a private business. Can I help you at all?");
				stage = 1;
				break;
			case 1:
				options("What sort of business?", "I'm looking for fame and riches.");
				stage = 2;
				break;
			case 2:
				switch (buttonId) {
				case 1:
					player("What sort of business?");
					stage = 10;
					break;
				case 2:
					player("I'm looking for fame and riches.");
					stage = 20;
					break;
				}
				break;
			case 10:
				npc("A small, family business. We give financial advice to", "other companies.");
				stage = 11;
				break;
			case 11:
				end();
				break;
			case 20:
				npc("And you expect to find it up the back streets of", "Varrock?");
				stage = 21;
				break;
			case 21:
				end();
				break;
			}
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 642 };
	}
}