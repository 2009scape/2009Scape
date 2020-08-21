package plugin.quest.free.dragonslayer;

import plugin.dialogue.DialoguePlugin;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.quest.Quest;
import core.game.node.item.GroundItemManager;

/**
 * Represents the guild master dialogue at the champions guild related to dragon slayer.
 * @author Vexia
 * 
 */
public final class GuildmasterDialogue extends DialoguePlugin {

	/**
	 * Represents the quest instance.
	 */
	private Quest quest;

	/**
	 * Constructs a new {@code GuildmasterDialogue} {@code Object}.
	 */
	public GuildmasterDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code GuildmasterDialogue} {@code Object}.
	 * @param player the player.
	 */
	public GuildmasterDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new GuildmasterDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		if (player.getQuestRepository().getPoints() < 32) {
			return true;
		}
		quest = player.getQuestRepository().getQuest("Dragon Slayer");
		npc("Greetings!");
		if (quest.getStage(player) == 10) {
			stage = 0;
		} else {
			stage = 1;
		}
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (quest.getStage(player)) {
		case 30:
			end();
			break;
		case 40:
			end();
			break;
		case 100:
			switch (stage) {
			case 1:
				options("What is this place?", "Can I have another key to Melzar's Maze?");
				stage = 2;
				break;
			case 2:
				switch (buttonId) {
				case 1:
					handleDescription(buttonId);
					break;
				case 2:
					player("Can I have another key to Melzar's Maze?");
					stage = 20;
					break;
				}
				break;
			case 99:
				end();
				break;
			case 20:
				if (!player.getInventory().containsItem(DragonSlayer.MAZE_KEY) && !player.getBank().containsItem(DragonSlayer.MAZE_KEY)) {
					if (!player.getInventory().add(DragonSlayer.MAZE_KEY)) {
						GroundItemManager.create(DragonSlayer.MAZE_KEY, player);
					}
					interpreter.sendItemMessage(DragonSlayer.MAZE_KEY.getId(), "The Guildmaster hands you a key.");
					stage = 21;
					break;
				}
				npc("I think you've already got a key.");
				stage = 21;
				break;
			case 21:
				end();
				break;
			}
			break;
		case 20:
			switch (stage) {
			case 1:
				player("About my quest to kill the dragon...");
				stage = 2;
				break;
			case 2:
				npc("If you're serious about taking on Elvarg, first you'll", "need to get to Crandor. The island is surrounded by", "dangerous reefs, so you'll need a ship capable of getting", "through them and a map to show you the way.");
				stage = 3;
				break;
			case 3:
				npc("When you reach Crandor, you'll also need some kind of", "protection against the dragon's breath.");
				stage = 18;
				break;
			case 18:
				options("How can I find the route to Crandor?", "Where can I find the right ship?", "How can I protect myself from the dragon's breath?", "What's so special about this dragon?", "Okay, I'll get going.");
				stage = 19;
				break;
			case 19:
				switch (buttonId) {
				case 1:
					player("How can I find the route to Crandor?");
					stage = 100;
					break;
				case 2:
					player("Where can I find the right ship?");
					stage = 110;
					break;
				case 3:
					player("How can I protect myself from the dragon's breath?");
					stage = 120;
					break;
				case 4:
					player("What's so special about this dragon?");
					stage = 130;
					break;
				case 5:
					player("Okay, I'll get going.");
					stage = 140;
					break;
				}
				break;
			case 100:
				npc("Only one map exists that shows the route through the", "reefs of Crandor. That map was split into three pieces", "by Melzar, Thalzar and Lozar, the wizards who escaped", "from the dragon. Each of them took one piece.");
				stage = 101;
				break;
			case 101:
				options("Where is Melzar's map piece?", "Where is Thalzar's map piece?", "Where is Lozar's map piece?");
				stage = 102;
				break;
			case 102:
				switch (buttonId) {
				case 1:
					player("Where is Melzar's map piece?");
					stage = 1000;
					break;
				case 2:
					player("Where is Thalzar's map piece?");
					stage = 1020;
					break;
				case 3:
					player("Where is Lozar's map piece?");
					stage = 1030;
					break;
				}
				break;
			case 1000:
				npc("Melzar built a catle on the site of the Crandorian", "refugee camp, north of Rimmington. He's locked himself", "in there and no one's seen him for years.");
				stage = 1001;
				break;
			case 1001:
				npc("The inside of his castle is like a maze, and is populated", "by undead monsters. Maybe, if you could get all the", "way through the maze, you could find his piece of the", "map.");
				if (player.getInventory().containsItem(DragonSlayer.MAZE_KEY) || player.getBank().containsItem(DragonSlayer.MAZE_KEY)) {
					stage = 1004;
					return true;
				}
				stage = 1002;
				break;
			case 1002:
				npc("Adventurers sometimes go in there to prove themselves,", "so I can give you this key to Melzar's Maze.");
				stage = 1003;
				break;
			case 1003:
				if (!player.getInventory().add(DragonSlayer.MAZE_KEY)) {
					GroundItemManager.create(DragonSlayer.MAZE_KEY, player);
				}
				interpreter.sendItemMessage(DragonSlayer.MAZE_KEY.getId(), "The Guildmaster hands you a key.");
				stage = 1004;
				break;
			case 1004:
				end();
				break;
			case 1020:
				npc("Thalzar was the most paranoid of the three wizards. He", "hid his map piece and took the secret f its location to", "his grave.");
				stage = 1021;
				break;
			case 1021:
				npc("I don't think you'll be able to find out where it is by", "ordinary means. You'll need to talk to the Oracle on", "Ice Mountain.");
				stage = 1022;
				break;
			case 1022:
				end();
				break;
			case 1030:
				npc("A few weeks ago, I'd have told you to speak to Lozar", "herself, in her house across the river from Lumbridge.");
				stage = 1031;
				break;
			case 1031:
				npc("Unfortunately, goblin raiders killer her and stole", "everything. One of the goblins from the Goblin Village", "probably has the map piece now.");
				stage = 1032;
				break;
			case 1032:
				end();
				break;
			case 110:
				npc("Even if you find the right route, only a ship made to", "the old crandorian design would be able to navigate", "through the reefs to the island.");
				stage = 111;
				break;
			case 111:
				npc("If there's still one in existence, it's probably in Port", "Sarim.");
				stage = 112;
				break;
			case 112:
				npc("Then, of course, you'll need to find a captain willy to", "sail to Crandor, and I'm not sure where you'd find one", "of them!");
				stage = 113;
				break;
			case 113:
				end();
				break;
			case 120:
				npc("That part shouldn't be too different, actually. I believe", "the Duke of Lumbridge has a special shield in his", "armoury that is enchanted against dragon's breath.");
				stage = 121;
				break;
			case 121:
				end();
				break;
			case 130:
				npc("Thirty years ago, Candor was a thriving community", "with a great tradition of mages and adventurers. Many", "Crandorians even earned the right to be part of the", "Champions' Guild!");
				stage = 131;
				break;
			case 131:
				npc("One of their adventurers went too far, however. He", "descended into the volcano in the centre of Crandor", "and woke the dragon Elvarg.");
				stage = 132;
				break;
			case 132:
				npc("He must have fought valiantly against the dragon", "because they say that, to this day, she has a scar down", "her side,");
				stage = 133;
				break;
			case 133:
				npc("but the dragon still won the fight. She emerged and laid", "waste to the whole of Crandor with her fire breath!");
				stage = 134;
				break;
			case 134:
				npc("Some refugees managed to escape in fishing boats.", "They landed on the coast, north of Rimmington, and", "set up camp but the dragon followed them and burned", "the camp to the ground.");
				stage = 135;
				break;
			case 135:
				npc("Out of all the people of Crandor there were only three", "survivors: a trio of wizards who used magic to escape.", "Their names were Thalzar, Lozar and Melzar.");
				stage = 140;
				break;
			case 136:
				npc("If you're serious about taking on Elvarg, first you'll", "need to get to Crandor. The island is surrounded by", "dangerous reefs, so you'll need a ship capable of getting", "through them and a map to show you the way.");
				stage = 17;
				break;
			case 140:
				end();
				break;
			case 99:
				end();
				break;
			}
			break;
		case 15:
			switch (stage) {
			case 1:
				options("What is this place?", "I talked to Oziach...");
				stage = 2;
				break;
			case 2:
				switch (buttonId) {
				case 1:
					handleDescription(buttonId);
					break;
				case 2:
					player("I talked to Oziach and he have me a quest.");
					stage = 3;
					break;
				}
				break;
			case 3:
				npc("Oh? What did he tell you to do?");
				stage = 4;
				break;
			case 4:
				player("Defeat the dragon of Crandor.");
				stage = 5;
				break;
			case 5:
				npc("The dragon of Crandor?");
				stage = 7;
				break;
			case 7:
				player("Um, yes...");
				stage = 8;
				break;
			case 8:
				npc("Goodness, he hasn't given you an easy job, has he?");
				stage = 9;
				break;
			case 9:
				player("What's so special about this dragon?");
				stage = 10;
				break;
			case 10:
				npc("Thirty years ago, Crandor was a thriving community", "with a great tradition of mages and adventurers. Many", "Crandorians even earned the right to be part of the", "Champion's Guild!");
				stage = 11;
				break;
			case 11:
				npc("One of their adventurers went too far, however. He", "descended into the volcano in the centre of Crandor", "and woke the dragon Elvarg.");
				stage = 12;
				break;
			case 12:
				npc("He must have fought valiantly against the dragon", "because they say that, to this day, she has a scar down", "her side,");
				stage = 13;
				break;
			case 13:
				npc("but the dragon still won the fight. She emerged and laid", "waste to the whole of Crandor with her fire breath!");
				stage = 14;
				break;
			case 14:
				npc("Some refuegees managed to escape in fishing boats.", "They landed on the coast, north of Rimmington, and", "set up camp but the dragon followed them and burned", "the camp to the ground.");
				stage = 15;
				break;
			case 15:
				npc("Out of all the people of Crandor there were only three", "surviors: a trio of wizards who used the magic to escape.", "Their names were Thalzar, Lozar and Melzar.");
				stage = 16;
				break;
			case 16:
				npc("If you're serious about taking on Elvarg, first you'll", "need to get to Crandor. The island is surrounded by", "dangerous reefs, so you'll need a ship capable of getting", "through them and a map to show you the way.");
				stage = 17;
				break;
			case 17:
				npc("When you reach Crandor, you'll also need some kind of", "protection against the dragon's breath.");
				stage = 19;
				break;
			case 19:
				player("Okay, I'll get going.");
				stage = 20;
				break;
			case 20:
				quest.setStage(player, 20);
				end();
				break;
			case 99:
				end();
				break;
			}
			break;
		case 10:
			switch (stage) {
			case 18:
				end();
				break;
			case 0:
				npc("Have you gone to talk to Oziach yet?");
				stage = 1;
				break;
			case 1:
				player("No, not yet.");
				stage = 2;
				break;
			case 2:
				npc("You can only begin your journey once you've", "spoken to Oziach who is located near Edgeville.");
				stage = 3;
				break;
			case 3:
				player("Okay, thanks.");
				stage = 4;
				break;
			case 4:
				end();
				break;
			}
			break;
		case 0:
			switch (stage) {
			case 1:
				options("What is this place?", "Can I have a quest?");
				stage = 10;
				break;
			case 10:
				switch (buttonId) {
				case 1:
					handleDescription(buttonId);
					break;
				case 2:
					player("Can I have a quest?");
					stage = 13;
					break;
				}
				break;
			case 11:
				if (quest.getStage(player) == 10) {
					npc("You're already on a quest for me, if I recall correctly.", "Have you talked to Oziach yet?");
					stage = 20;
					return true;
				}
				npc("This is the Champions' Guild. Only adventurers who", "have proved themselves worthy by gaining influence", "from quests are allowed in here.");
				stage = 12;
				break;
			case 12:
				end();
				break;
			case 13:
				npc("Aha!");
				stage = 14;
				break;
			case 14:
				npc("Yes! A mighty and perilous quest fit only for the most", "powerful champion! And, at the end of it, you will earn", "the right to wear the legendary rune platebody!");
				stage = 15;
				break;
			case 15:
				player("So, what is this quest?");
				stage = 16;
				break;
			case 16:
				npc("You'll have to speak to Oziach, the maker of rune", "armour. He sets the quests that champions must", "complete in order to wear it.");
				stage = 17;
				break;
			case 17:
				npc("Oziach lives in a hut, by the cliffs to the west of", "Edgeville. he can be a little...odd...sometimes, though.");
				stage = 18;
				quest.start(player);
				break;
			case 18:
				end();
				break;
			case 20:
				player("No, not yet.");
				stage = 21;
				break;
			case 21:
				npc("Well, he's the only one who can grant you the right to", "wear rune platemail. He lives in a hut, by the cliffs west", "of Edgeville.");
				stage = 22;
				break;
			case 22:
				player("Okay, I'll go and talk to him.");
				stage = 23;
				break;
			case 23:
				end();
				break;
			case 99:
				end();
				break;
			}
			break;
		}
		return true;
	}

	/**
	 * Method used to handle the description.
	 * @param buttonId the id.
	 */
	public void handleDescription(int buttonId) {
		switch (buttonId) {
		case 1:
			if (quest.getStage(player) == 10) {
				npc("You're already on a quest for me, if I recall correctly.", "Have you talked to Oziach yet?");
				stage = 20;
				return;
			}
			npc("This is the Champions' Guild. Only adventurers who", "have proved themselves worthy by gaining influence", "from quests are allowed in here.");
			stage = 99;
			break;
		case 2:
			end();
			break;
		}
	}

	@Override
	public int[] getIds() {
		return new int[] { 198 };
	}
}