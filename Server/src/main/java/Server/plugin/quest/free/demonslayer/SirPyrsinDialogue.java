package plugin.quest.free.demonslayer;

import plugin.dialogue.DialoguePlugin;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.quest.Quest;
import core.game.system.task.Pulse;
import core.game.world.GameWorld;
import core.game.world.map.Location;
import core.game.world.update.flag.context.Animation;

/**
 * Represents the dialogue which handles the Sir Prysin NPC.
 * @author Vexia
 * @date 3/1/13
 */
public class SirPyrsinDialogue extends DialoguePlugin {

	/**
	 * Represents the quest instance.
	 */
	private Quest quest;

	/**
	 * Represents the current npc id.
	 */
	private int id;

	/**
	 * Constructs a new {@code SirPyrsinDialogue} {@code Object}.
	 */
	public SirPyrsinDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code SirPyrsinDialogue} {@code Object}.
	 * @param player the player.
	 */
	public SirPyrsinDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new SirPyrsinDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		if (args[0] instanceof NPC) {
			npc = (NPC) args[0];
			id = npc.getId();
		} else if (args[0] instanceof Integer) {
			id = ((int) args[0]);
		}
		quest = player.getQuestRepository().getQuest("Demon Slayer");
		switch (quest.getStage(player)) {
		case 30:
			npc(id, "Have you sorted that demon out yet?");
			stage = 0;
			break;
		case 20:
			if (args.length > 1) {
				player("That must be the key Sir Prysin dropped.");
				stage = 800;
				return true;
			}
			npc(id, "So how are you doing with getting the keys?");
			stage = 0;
			break;
		default:
			npc(id, "Hello, who are you?");
			stage = 0;
			break;
		}
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (quest.getStage(player)) {
		case 30:
			switch (stage) {
			case 0:
				if (!player.getInventory().containsItem(DemonSlayer.SILVERLIGHT) && !player.getBank().containsItem(DemonSlayer.SILVERLIGHT) && !player.getEquipment().containsItem(DemonSlayer.SILVERLIGHT)) {
					player("Not yet. And I, um, lost Silverlight.");
					stage = 1;
				} else {
					player("No, not yet.");
					stage = 3;
				}
				break;
			case 1:
				if (player.getInventory().add(DemonSlayer.SILVERLIGHT)) {
					npc(id, "Yes, I know, someone returned it to me. Take better", "care of it this time.");
					stage = 2;
				}
				break;
			case 2:
				end();
				break;
			case 3:
				npc(id, "Well get on with it. He'll be pretty powerful when he", "gets to full strength.");
				stage = 4;
				break;
			case 4:
				end();
				break;
			case 302:
				end();
				break;
			}
			break;
		case 20:
			switch (stage) {
			case 0:
				if (player.getInventory().containsItem(DemonSlayer.FIRST_KEY) && player.getInventory().containsItem(DemonSlayer.SECOND_KEY) && player.getInventory().containsItem(DemonSlayer.THIRD_KEY)) {
					player("I've got all three keys!");
					stage = 300;
				} else {
					player("I haven't found them all yet.");
					stage = 1;
				}
				break;
			case 1:
				options("Can you remind me where all the keys were again?", "I'm still looking.");
				stage = 2;
				break;
			case 2:
				switch (buttonId) {
				case 1:
					player("Can you remind me where all the keys were again?");
					stage = 62;
					break;
				case 2:
					player("I'm still looking.");
					stage = 20;
					break;
				}
				break;
			case 20:
				npc(id, "Ok, tell me when you've got them all.");
				stage = 21;
				break;
			case 21:
				end();
				break;
			case 62:
				npc(id, "I kept one of the keys. I gave the other two to other", "people for safe keeping.");
				stage = 63;
				break;
			case 63:
				npc(id, "One I gave to Rovin, the captain of the palace guard.");
				stage = 64;
				break;
			case 64:
				npc(id, "I gave the other to the wizard Traiborn.");
				stage = 65;
				break;
			case 65:
				end();
				break;
			case 300:
				npc(id, "Excellent! Now I can give you Silverlight.");
				stage = 301;
				break;
			case 301:
				if (player.getInventory().freeSlots() == 0) {
					npc(id, "You don't have any inventory space for Silverlight.");
					stage = 302;
					return true;
				}
				close();
				player.lock();
				GameWorld.Pulser.submit(new Pulse(1, player) {
					int counter = 0;

					@Override
					public boolean pulse() {
						switch (counter++) {
						case 1:
							player.getProperties().setTeleportLocation(Location.create(3204, 3470, 0));
							if (!npc.getLocation().equals(Location.create(3204, 3469, 0))) {
								npc.getProperties().setTeleportLocation(Location.create(3204, 3469, 0));
							}
							npc.lock();
							npc.getWalkingQueue().reset();
							npc.getPulseManager().clear();
							npc.faceLocation(Location.create(3204, 3468, 0));
							player.face(npc);
							break;
						case 2:
							npc.animate(new Animation(4597));
							break;
						case 9:
							player.getConfigManager().set(222, 5653570, true);
							player.setAttribute("/save:demon-slayer:received", true);
							npc.animate(new Animation(4607));
							break;
						case 10:
							npc.transform(4657);
							break;
						case 11:
							npc.faceLocation(player.getLocation());
							break;
						case 12:
							npc.transform(883);
							if (player.getInventory().remove(DemonSlayer.FIRST_KEY, DemonSlayer.SECOND_KEY, DemonSlayer.THIRD_KEY)) {
								if (player.getInventory().add(DemonSlayer.SILVERLIGHT)) {
									npc.animate(new Animation(4608));
									player.animate(new Animation(4604));
									quest.setStage(player, 30);
								}
							}
							break;
						case 13:
							npc.getWalkingQueue().reset();
							npc.unlock();
							interpreter.sendItemMessage(DemonSlayer.SILVERLIGHT.getId(), "Sir Prysin hands you a very shiny sword.");
							stage = 302;
							player.unlock();
							return true;
						}
						return false;
					}
				});
				break;
			case 302:
				end();
				break;
			case 800:
				player("I don't seem to be able to reach it. I wonder if I can", "dislodge it somehow. That way it may go down into the", "sewers.");
				stage = 801;
				break;
			case 801:
				end();
				break;
			}
			break;
		case 10:
			switch (stage) {
			case 0:
				options("I am a mighty adventurer. Who are you?", "I'm not sure, I was hoping you could tell me.", "Gypsy Aris said I should come and talk to you.");
				stage = 1;
				break;
			case 1:
				switch (buttonId) {
				case 1:
				case 2:
					handleDefault(buttonId);
					break;
				case 3:
					player("Gypsy Aris said I should come and talk to you.");
					stage = 2;
					break;
				}
				break;
			case 2:
				npc(id, "Gypsy Aris? Is she still alive? I remember her from", "when I was pretty young. Well what do you need to", "talk to me about?");
				stage = 3;
				break;
			case 3:
				options("I need to find the Silverlight.", "Yes, she is still alive.");
				stage = 4;
				break;
			case 4:
				switch (buttonId) {
				case 1:
					player("I need to find the Silverlight.");
					stage = 40;
					break;
				case 2:
					player("Yes, she is still alive. She lives right outside the castle!");
					stage = 50;
					break;
				}
				break;
			case 40:
				npc(id, "What do you need to find that for?");
				stage = 41;
				break;
			case 41:
				player("I need it to fight Delrith.");
				stage = 42;
				break;
			case 42:
				npc(id, "Delrith? I thought the world was rid of him, thanks to", "my great-grandfather.");
				stage = 43;
				break;
			case 43:
				options("Well, the gypsy's crystal ball seems to think otherwise.", "He's back and unfortunately I've got to deal with him.");
				stage = 44;
				break;
			case 44:
				switch (buttonId) {
				case 1:
					player("Well the gypsy's crystall ball seems to think otherwise.");
					stage = 45;
					break;
				case 2:
					player("He's back and unfortunately I've got to deal with him.");
					stage = 48;
					break;
				}
				break;
			case 45:
				npc(id, "Well if the ball says so, I'd better help you.");
				stage = 46;
				break;
			case 46:
				npc(id, "The problem is getting Silverlight.");
				stage = 47;
				break;
			case 47:
				player("You mean you don't have it?");
				stage = 60;
				break;
			case 48:
				npc(id, "You don't look up to much. I suppose Silverlight may be", "good enough to carry you though though.");
				stage = 46;
				break;
			case 60:
				npc(id, "Oh I do have it, but it is so powerful that the king", "made me put it in a special box which needs three", "different keys to open it. That way it won't fall into the", "wrong hands.");
				stage = 61;
				break;
			case 61:
				player("And why is this a problem?");
				stage = 62;
				break;
			case 62:
				npc(id, "I kept one of the keys. I gave the other two to other", "people for safe keeping.");
				stage = 63;
				break;
			case 63:
				npc(id, "One I gave to Rovin, the captain of the palace guard.");
				stage = 64;
				break;
			case 64:
				npc(id, "I gave the other to the wizard Traiborn.");
				stage = 65;
				break;
			case 65:
				player("Can you give me your key?");
				stage = 66;
				break;
			case 66:
				npc(id, "Um.... ah....");
				stage = 67;
				break;
			case 67:
				npc(id, "Well there's a problem there as well.");
				stage = 68;
				break;
			case 68:
				npc(id, "I managed to drop the key in the drain just outside the", "palace kitchen. It is just inside and I can't reach it.");
				stage = 69;
				break;
			case 69:
				player("So what does the drain lead to?");
				stage = 70;
				break;
			case 70:
				npc(id, "It is the drain for the drainpipe running from the sink", "in the kitchen down to the palace sewers.");
				stage = 71;
				break;
			case 71:
				options("Where can I find Captain Rovin?", "Where does the wizard live?", "Well I'd better go key hunting.");
				stage = 72;
				break;
			case 72:
				switch (buttonId) {
				case 1:
					player("Where can I find Captain Rovin?");
					stage = 110;
					break;
				case 2:
					player("Where does the wizard live?");
					stage = 120;
					break;
				case 3:
					player("Well I'd better go key hunting.");
					stage = 130;
					break;
				}
				break;
			case 130:
				npc(id, "Ok, goodbye.");
				stage = 131;
				break;
			case 131:
				quest.setStage(player, 20);
				end();
				break;
			case 120:
				npc(id, "Wizard Traiborn?");
				stage = 121;
				break;
			case 121:
				npc(id, "He is one of the wizards who lives in the tower on the", "little island just south coast. I believe his", "quartes are on the first floor of the tower.");
				stage = 122;
				break;
			case 122:
				options("Where can I find Captain Rovin?", "Well I'd better go key hunting.");
				stage = 123;
				break;
			case 123:
				switch (buttonId) {
				case 1:
					player("Where can I find Captain Rovin?");
					stage = 110;
					break;
				case 2:
					player("Well I'd better go key hunting.");
					stage = 130;
					break;
				}
				break;
			case 110:
				npc(id, "Captain Rovin lives at the top of the guards' quarters in", "the north-west wing of this palace.");
				stage = 111;
				break;
			case 111:
				options("Can you give me your key?", "Where does the wizard live?", "Well I'd better go key hunting.");
				stage = 112;
				break;
			case 112:
				switch (buttonId) {
				case 1:
					player("Can you give me your key?");
					stage = 66;
					break;
				case 2:
					player("Where does the wizard live?");
					stage = 120;
					break;
				case 3:
					player("Well I'd better go key hunting.");
					stage = 130;
					break;
				}
				break;
			case 50:
				npc(id, "Oh , is that the same gypsy? I would have thought she", "would have died by now. She was pretty old when I", "was a lad.");
				stage = 51;
				break;
			case 51:
				npc(id, "Anyway, what can I do for you?");
				stage = 52;
				break;
			case 52:
				player("I need to find the Silverlight.");
				stage = 40;
				break;
			default:
				handleDefault(buttonId);
				break;
			}
			break;
		default:
			handleDefault(buttonId);
			break;
		}
		return true;
	}

	/**
	 * Method used to handle default chat.
	 * @param buttonId the button id.
	 */
	private final void handleDefault(int buttonId) {
		switch (stage) {
		case 0:
			options("I am a mighty adventurer. Who are you?", "I'm not sure, I was hoping you could tell me.");
			stage = 1;
			break;
		case 1:
			switch (buttonId) {
			case 1:
				player("I am a mighty adventurer, Who are you?");
				stage = 10;
				break;
			case 2:
				player("I'm not sure, I was hoping you could tell me.");
				stage = 20;
				break;
			}
			break;
		case 10:
			npc(id, "I am Sir Prysin. A bold and famous knight of the", "realm.");
			stage = 11;
			break;
		case 11:
			end();
			break;
		case 20:
			npc(id, "Well I've never met you before.");
			stage = 21;
			break;
		case 21:
			end();
			break;
		}
	}

	@Override
	public int[] getIds() {
		return new int[] { 883, 4657 };
	}
}