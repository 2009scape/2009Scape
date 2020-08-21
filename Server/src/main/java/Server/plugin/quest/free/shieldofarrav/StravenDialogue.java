package plugin.quest.free.shieldofarrav;

import plugin.dialogue.DialoguePlugin;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.quest.Quest;
import core.game.node.item.GroundItemManager;

/**
 * Represents the dialogue which handles the straven NPC.
 * @author 'Vexia
 * @date 3/1/14
 */
public class StravenDialogue extends DialoguePlugin {

	/**
	 * Represents the quest instance.
	 */
	private Quest quest;

	/**
	 * Constructs a new {@code StravenDialogue} {@code Object}.
	 */
	public StravenDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code StravenDialogue} {@code Object}.
	 * @param player the player.
	 */
	public StravenDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new StravenDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		quest = player.getQuestRepository().getQuest("Shield of Arrav");
		switch (quest.getStage(player)) {
		case 100:
		case 70:
			if (ShieldofArrav.isPhoenix(player)) {
				npc("Greetings fellow gang member.");
				stage = 10;
			} else {
				player.getPacketDispatch().sendMessage("I wouldn't talk to him if I was you.");
				end();
			}
			break;
		case 60:
			if (ShieldofArrav.isPhoenixMission(player)) {
				npc("How's your little mission going?");
				stage = 200;
			} else {
				player("What's through the door?");
				stage = 100;
			}
			break;
		default:
			player("What's through the door?");
			break;
		}
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		if (quest.getStage(player) == 60 && stage >= 200) {
			switch (stage) {
			case 200:
				if (!player.getInventory().containsItem(ShieldofArrav.INTEL_REPORT)) {
					player("I haven't managed to find the report yet...");
					stage = 201;
				} else {
					player("I have the intelligence report!");
					stage = 204;
				}
				break;
			case 201:
				npc("You need to kill Johnny the Beard, who should be in the", "Blue Moon Inn.");
				stage = 202;
				break;
			case 202:
				npc("...I would guess. Not being a member of the Phoenix", "Gang and all.");
				stage = 203;
				break;
			case 203:
				end();
				break;
			case 204:
				npc("Let's see it then.");
				stage = 205;
				break;
			case 205:
				interpreter.sendDialogue("You hand over the report. The man reads the report.");
				stage = 206;
				break;
			case 206:
				npc("Yes. Yes, this is very good.");
				stage = 207;
				break;
			case 207:
				npc("Ok! You can join the Phoenix Gang! I am Straven, one", "of the gang leaders.");
				stage = 208;
				break;
			case 208:
				player("Nice to meet you.");
				stage = 209;
				break;
			case 209:
				npc("Take this key.");
				stage = 210;
				break;
			case 210:
				if (player.getInventory().remove(ShieldofArrav.INTEL_REPORT)) {
					if (!player.getInventory().add(ShieldofArrav.KEY)) {
						GroundItemManager.create(ShieldofArrav.KEY, player);
					}
					interpreter.sendItemMessage(ShieldofArrav.KEY.getId(), "Straven hands you a key.");
					quest.setStage(player, 70);
					ShieldofArrav.setPhoenix(player);
					stage = 211;
				}
				break;
			}
			return true;
		}
		switch (quest.getStage(player)) {
		case 100:
		case 70:
			switch (stage) {
			case 10:
				if (!player.getInventory().containsItem(ShieldofArrav.KEY) && !player.getBank().containsItem(ShieldofArrav.KEY)) {
					player("I'm afraid I've lost the key you gave me...");
					stage = 80;
				} else {
					options("I've heard you've got some cool treasure in this place.", "Any suggestions for where I can go thieving?", "Where's the Black Arm Gang hideout?");
					stage = 11;
				}
				break;
			case 80:
				npc("You really need to be more careful. We don't want", "that key falling into the wrong hands. Ah well... Have", "this spare. Don't lose THIS one.");
				stage = 81;
				break;
			case 81:
				if (!player.getInventory().add(ShieldofArrav.KEY)) {
					GroundItemManager.create(ShieldofArrav.KEY, player);
				}
				interpreter.sendItemMessage(ShieldofArrav.KEY.getId(), "Straven hands you a key.");
				stage = 82;
				break;
			case 82:
				end();
				break;
			case 11:
				switch (buttonId) {
				case 1:
					player("I've heard you've got some cool treasures in this place...");
					stage = 110;
					break;
				case 2:
					player("Any suggestions on where I can go thieving?");
					stage = 120;
					break;
				case 3:
					player("Where's the Black Arm Gang hideout? I wanna go", "sabotage 'em!");
					stage = 130;
					break;
				}
				break;
			case 130:
				npc("That would be a little tricky; their security is pretty", "good.");
				stage = 131;
				break;
			case 131:
				end();
				break;
			case 120:
				npc("You can always try the marketplace in Ardougne.", "LOTS of opportunity there!");
				stage = 121;
				break;
			case 121:
				end();
				break;
			case 110:
				npc("Oh yeah, we've all stolen some stuff in our time. Those", "candelsticks down here, for example, were quite a", "challenge to get out of the palace.");
				stage = 111;
				break;
			case 111:
				player("And the shield of Arrav? I heard you got that!");
				stage = 112;
				break;
			case 112:
				npc("Woah... thats a blast from the past! We stole that years", "and years ago! We don't even have all the shield", "anymore.");
				stage = 113;
				break;
			case 113:
				end();
				break;
			case 211:
				npc("This key will give you access to our weapons supply", "depot round the front of this building.");
				stage = 212;
				break;
			case 212:
				end();
				break;
			default:
				handleDefault(buttonId);
				break;
			}
			break;
		case 60:
		case 50:
		case 40:
			switch (stage) {
			case 0:
				npc("Hey! You can't go in there. Only authories personnel", "of the VTAM Corporation are allowed beyond this point.");
				stage = 1;
				break;
			case 1:
				options("I know who you are!", "How do I get a job with the VTAM corporation?", "Why not?");
				stage = 2;
				break;
			case 2:
				switch (buttonId) {
				case 1:
					player("I know who you are!");
					stage = 10;
					break;
				case 2:
					player("How do I get a job with the VTAM corporation?");
					stage = 20;
					break;
				case 3:
					player("Why not?");
					stage = 30;
					break;
				}
				break;
			case 10:
				npc("Really?");
				stage = 11;
				break;
			case 11:
				npc("Well?");
				stage = 12;
				break;
			case 12:
				npc("Who are we then?");
				stage = 13;
				break;
			case 13:
				player("This is the headquarters of the Phoenix Gang, the most", "powerful crime syndicate this city has ever seen!");
				stage = 14;
				break;
			case 14:
				npc("No, this is a legitimate business run by legitimate", "businessmen.");
				stage = 15;
				break;
			case 15:
				npc("Supposing we were this crime gang however, what would", "you want with us?");
				stage = 16;
				break;
			case 16:
				options("I'd like to offer you my services.", "I want nothing. I was just making sure you were them.");
				stage = 17;
				break;
			case 17:
				switch (buttonId) {
				case 1:
					player("I'd like to offer you my services.");
					stage = 41;
					break;
				case 2:
					player("I want nothing. I was just making sure you were them.");
					stage = 18;
					break;
				}
				break;
			case 18:
				npc("Well then get lost and stop wasting my time.");
				stage = 19;
				break;
			case 19:
				npc("...if you know what's good for you.");
				stage = 40;
				break;
			case 40:
				end();
				break;
			case 41:
				npc("You mean you'd like to join the Phoenix Gang?");
				stage = 42;
				break;
			case 42:
				npc("well obviously I can't speak for them, but the Phoenix", "Gang doesn't let people join just like that.");
				stage = 43;
				break;
			case 43:
				npc("You can't be too careful, you understand.");
				stage = 44;
				break;
			case 44:
				npc("Generally someone has to prove their loyalty before", "they can join.");
				stage = 45;
				break;
			case 45:
				player("How would I go about doing that?");
				stage = 46;
				break;
			case 46:
				npc("Obviously, I would have no idea about that.");
				stage = 47;
				break;
			case 47:
				npc("Although having said that, a rival gang of ours, er,", "theirs, called the Black Arm Gang is supposedly metting", "a contact from Port Sarim today in the Blue Moon", "Inn.");
				stage = 48;
				break;
			case 48:
				npc("The Blue Moon Inn is just by the south entrance to", "this city, and supposedly the name of the contact is", "Jonny the Beard.");
				stage = 49;
				break;
			case 49:
				npc("OBVIOUSLY I know NOTHING about the dealing", "of the Phoenix Gang, but I bet if SOMEBODY were", "to kill him and bring back his intelligence report, they", "would be considered loyal enough to join.");
				stage = 50;
				break;
			case 50:
				player("Ok, I'll get right on it.");
				stage = 51;
				break;
			case 51:
				quest.setStage(player, 60);
				ShieldofArrav.setPhoenixMission(player);
				end();
				break;
			case 20:
				npc("Get a copy of the Varrock Herald. If we have any", "positions right now, they'll be advertised in there.");
				stage = 21;
				break;
			case 21:
				end();
				break;
			case 30:
				npc("Sorry. That's classified information.");
				stage = 31;
				break;
			case 31:
				end();
				break;
			default:
				handleDefault(buttonId);
				break;
			}
			break;
		default:
			switch (stage) {
			case 0:
				npc("Hey! You can't go in there. Only authories personnel", "of the VTAM Corporation are allowed beyond this point.");
				stage = 1;
				break;
			case 1:
				player("Ok, sorry.");
				stage = 2;
				break;
			case 2:
				end();
				break;
			}
			break;
		}
		return true;
	}

	/**
	 * Method used to handle the default dial.
	 * @param buttonId the button id.
	 */
	public void handleDefault(int buttonId) {
		switch (stage) {
		case 0:
			npc("Hey! You can't go in there. Only authories personnel", "of the VTAM Corporation are allowed beyond this point.");
			stage = 1;
			break;
		case 1:
			player("Ok, sorry.");
			stage = 2;
			break;
		case 2:
			end();
			break;
		}
	}

	@Override
	public int[] getIds() {
		return new int[] { 644 };
	}
}