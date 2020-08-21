package plugin.quest.free.dragonslayer;

import core.game.component.Component;
import plugin.skill.Skills;
import plugin.skill.agility.AgilityHandler;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.quest.Quest;
import core.game.node.item.Item;
import core.game.node.object.GameObject;
import core.game.system.task.Pulse;
import core.game.world.GameWorld;
import core.game.world.map.Location;
import core.game.world.map.RegionManager;
import core.game.world.update.flag.context.Animation;
import core.plugin.InitializablePlugin;
import core.plugin.PluginManager;

/**
 * Represents the dragon slayer quest.
 * @author Vexia
 * 
 */
@InitializablePlugin
public final class DragonSlayer extends Quest {

	/**
	 * Represents the maze key given by the guildmaster.
	 */
	public static final Item MAZE_KEY = new Item(1542);

	/**
	 * Represents the red key item.
	 */
	public static final Item RED_KEY = new Item(1543);

	/**
	 * Represents the orange key item.
	 */
	public static final Item ORANGE_KEY = new Item(1544);

	/**
	 * Represents the yellow key item.
	 */
	public static final Item YELLOW_KEY = new Item(1545);

	/**
	 * Represents the blue key item.
	 */
	public static final Item BLUE_KEY = new Item(1546);

	/**
	 * Represents the purple key item.
	 */
	public static final Item PURPLE_KEY = new Item(1547);

	/**
	 * Represents the green key item.
	 */
	public static final Item GREEN_KEY = new Item(1548);

	/**
	 * Represents the maze map piece.
	 */
	public static final Item MAZE_PIECE = new Item(1535);

	/**
	 * Represents the magic door map piece.
	 */
	public static final Item MAGIC_PIECE = new Item(1537);

	/**
	 * Represents the wormbrain piece.
	 */
	public static final Item WORMBRAIN_PIECE = new Item(1536);

	/**
	 * Represents the anti dragon fire shield.
	 */
	public static final Item SHIELD = new Item(1540);

	/**
	 * Represents the crandor map item.
	 */
	public static final Item CRANDOR_MAP = new Item(1538);

	/**
	 * Represents the map component interface.
	 */
	public static final Component MAP_COMPONENT = new Component(547);

	/**
	 * Represents the nails item.
	 */
	public static final Item NAILS = new Item(1539, 30);

	/**
	 * Represents the plank item.
	 */
	public static final Item PLANK = new Item(960);

	/**
	 * Represents the hammer item.
	 */
	public static final Item HAMMER = new Item(2347);

	/**
	 * Represents the elvarg head item.
	 */
	public static final Item ELVARG_HEAD = new Item(11279);

	/**
	 * Constructs a new {@Code DragonSlayer} {@Code Object}
	 */
	public DragonSlayer() {
		super("Dragon Slayer", 18, 17, 2, 176, 0, 1, 10);
	}
	
	@Override
	public Quest newInstance(Object object) {
		PluginManager.definePlugins(new CrandorMapPlugin(), new DragonSlayerPlugin(), new DSMagicDoorPlugin(), new DragonSlayerCutscene(), new MazeDemonNPC(), new MazeGhostNPC(), new MazeSkeletonNPC(), new MazeZombieNPC(), new MeldarMadNPC(), new WormbrainNPC(), new ZombieRatNPC(), new DSChestDialogue(), new GuildmasterDialogue(), new ElvargNPC(), new WormbrainDialogue(), new OziachDialogue(), new NedDialogue(), new DukeHoracioDialogue());
		return this;
	}
	
	@Override
	public void drawJournal(Player player, int stage) {
		super.drawJournal(player, stage);
		switch (getStage(player)) {
		case 0:
			player.getPacketDispatch().sendString(BLUE + "I can start this quest by speaking to the " + RED + "Guildmaster " + BLUE + "in", 275, 4+ 7);
			player.getPacketDispatch().sendString(BLUE + "the " + RED + "Champions' Guild" + BLUE + " ,south-west of Varrock.", 275, 5+ 7);
			player.getPacketDispatch().sendString(BLUE + "I will need to be able to defeat a " + RED + "level 83 dragon.", 275, 6+ 7);
			if (player.getQuestRepository().getPoints() < 32) {
				player.getPacketDispatch().sendString(BLUE + "To enter the Champions' Guild I need" + RED + " 32 Quest Points.", 275, 7+ 7);
			} else {
				player.getPacketDispatch().sendString("<str>To enter the Champions' Guild I need 32 Quest Points.", 275, 7+ 7);
			}
			break;
		case 10:
			line(player, "<str>The Guildmaster of the Champions' Guild said I could earn", 4+ 7);
			line(player, "<str>the right to wear rune armour if I went on a quest for", 5+ 7);
			line(player, "<str>Oziach, who makes the armour.", 6+ 7);
			line(player, BLUE + "I should speak to " + RED + "Oziach" + BLUE + ", who lives by the cliffs to the", 7+ 7);
			line(player, BLUE + "west of " + RED + "Edgeville.", 8+ 7);
			break;
		case 15:
			line(player, "<str>The Guildmaster of the Champions' Guild said I could earn", 4+ 7);
			line(player, "<str>the right to wear rune armour if I went on a quest for", 5+ 7);
			line(player, "<str>Oziach, who makes the armour.", 6+ 7);
			line(player, "<str>I spoke to Oziach in Edgeville. He told me to slay the", 7+ 7);
			line(player, "<str>dragon of Crandor island.", 8+ 7);
			line(player, BLUE + "I should return to the " + RED + "Champions' Guild Guildmaster " + BLUE + "for", 9+ 7);
			line(player, BLUE + "more detailed instructions.", 10+ 7);
			break;
		case 20:
			line(player, "<str>The Guildmaster of the Champions' Guild said I could earn", 4+ 7);
			line(player, "<str>the right to wear rune armour if I went on a quest for", 5+ 7);
			line(player, "<str>Oziach, who makes the armour.", 6+ 7);
			line(player, "<str>I spoke to Oziach in Edgeville. He told me to slay the", 7+ 7);
			line(player, "<str>dragon of Crandor island.", 8+ 7);
			line(player, "<str>The Champions' Guild Guildmaster gave me more detailed", 9+ 7);
			line(player, "<str>instructions.", 10+ 7);
			line(player, BLUE + "To defeat the dragon I will need to find a " + RED + "map " + BLUE + "to Crandor, a", 11+ 7);
			line(player, RED + "ship" + BLUE + ", a " + RED + "captain " + BLUE + "to take me there and some kind of", 12+ 7);
			line(player, RED + "protection " + BLUE + "against the dragon's breath.", 13+ 7);
			if (!player.getInventory().containsItem(MAZE_PIECE) && !player.getBank().containsItem(MAZE_PIECE)) {
				line(player, BLUE + "One-third of the map is in " + RED + "Melzar's Maze" + BLUE + ", near", 14+ 7);
				line(player, RED + "Rimmington" + ".", 15+ 7);
			} else {
				line(player, "<str>I found the piece of the map that was hidden in Melzar's", 14+ 7);
				line(player, "<str>Maze.", 15+ 7);
			}
			if (!player.getInventory().containsItem(MAGIC_PIECE) && !player.getBank().containsItem(MAGIC_PIECE)) {
				line(player, BLUE + "One-third of the map is hidden, and only the " + RED + "Oracle " + BLUE + "on " + RED + "Ice", 16+ 7);
				line(player, RED + "Mountain" + BLUE + " will know where it is.", 17+ 7);
			} else {
				line(player, "<str>I found the piece of the map that was hidden beneath Ice", 16+ 7);
				line(player, "<str>Mountain.", 18+ 7);
			}
			if (!player.getInventory().containsItem(WORMBRAIN_PIECE) && !player.getBank().containsItem(WORMBRAIN_PIECE)) {
				line(player, BLUE + "One-third of the map was stolen by a " + RED + "goblin " + BLUE + "from the", 18+ 7);
				line(player, RED + "Goblin Village.", 19+ 7);
			} else {
				line(player, "<str>I found the piece of the map that the goblin, Wormbrain,", 18+ 7);
				line(player, "<str>stole.", 19+ 7);
			}
			if (!player.getInventory().containsItem(SHIELD) && !player.getBank().containsItem(SHIELD)) {
				line(player, BLUE + "I should ask the " + RED + "Duke of Lumbridge " + BLUE + "for an " + RED + "anti-", 20+ 7);
				line(player, RED + "dragonbreath shield.", 21+ 7);
			} else {
				line(player, "<str>The Duke of Lumbridge gave me an anti-dragonbreath", 20+ 7);
				line(player, "<str>shield.", 21+ 7);

			}
			if (!player.getSavedData().getQuestData().getDragonSlayerAttribute("ship")) {
				line(player, BLUE + "I should see if there is a " + RED + "ship " + BLUE + "for sale in " + RED + "Port Sarim", 22+ 7);
			} else {
				line(player, "<str>I bought a ship in Port Sarim called the Lady Lumbridge.", 22+ 7);
				if (!player.getSavedData().getQuestData().getDragonSlayerAttribute("repaired")) {
					line(player, "<str>I need to repair the hole in bottom of the ship.", 23+ 7);
				} else {
					line(player, "<str>I have repared my ship using wooden planks and steel", 23+ 7);
					line(player, "<str>nails.", 24+ 7);
				}
			}
			break;
		case 30:
			line(player, "<str>The Guildmaster of the Champions' Guild said I could earn", 4+ 7);
			line(player, "<str>the right to wear rune armour if I went on a quest for", 5+ 7);
			line(player, "<str>Oziach, who makes the armour.", 6+ 7);
			line(player, "<str>I spoke to Oziach in Edgeville. He told me to slay the", 7+ 7);
			line(player, "<str>dragon of Crandor island.", 8+ 7);
			line(player, "<str>The Champions' Guilg Guildmaster told me I had to find", 9+ 7);
			line(player, "<str>three pieces of a map to Crandor, a ship, a captain to take", 10+ 7);
			line(player, "<str>me there and a shield to protect me from the dragon's", 11+ 7);
			line(player, "<str>breath.", 12+ 7);
			line(player, "<str>I found the piece of the map that was hidden in Melzar's", 13+ 7);
			line(player, "<str>Maze.", 14+ 7);
			line(player, "<str>I found the piece of the map that was hidden beneath Ice", 15+ 7);
			line(player, "<str>Mountain.", 16+ 7);
			line(player, "<str>I found the piece of the map that the goblin, Wormbrain,", 17+ 7);
			line(player, "<str>stole.", 18+ 7);
			line(player, "<str>The Duke of Lumbridge gave me an anti-dragonbreath", 19+ 7);
			line(player, "<str>shield.", 20+ 7);
			line(player, "<str>I bought a ship in Port Sarim called the Lady Lumbridge", 21+ 7);
			line(player, "<str>I have repaired my ship using wooden planks and steel", 22+ 7);
			line(player, "<str>nails.", 23+ 7);
			line(player, "<str>Captain Ned from Draynor Village has agreed to sail the", 24+ 7);
			line(player, "<str>ship to Crandor for me.", 25+ 7);
			line(player, BLUE + "Now I should go to my ship in " + RED + "Port Sarim " + BLUE + "and set sail for", 26+ 7);
			line(player, RED + "Crandor" + BLUE + "!", 27+ 7);
			break;
		case 40:
			line(player, "<str>The Guildmaster of the Champions' Guild said I could earn", 4+ 7);
			line(player, "<str>the right to wear rune armour if I went on a quest for", 5+ 7);
			line(player, "<str>Oziach, who makes the armour.", 6+ 7);
			line(player, "<str>I spoke to Oziach in Edgeville. He told me to slay the", 7+ 7);
			line(player, "<str>dragon of Crandor island.", 8+ 7);
			line(player, "<str>The Champions' Guilg Guildmaster told me I had to find", 9+ 7);
			line(player, "<str>three pieces of a map to Crandor, a ship, a captain to take", 10+ 7);
			line(player, "<str>me there and a shield to protect me from the dragon's", 11+ 7);
			line(player, "<str>breath.", 12+ 7);
			line(player, "<str>I found the piece of the map that was hidden in Melzar's", 13+ 7);
			line(player, "<str>Maze.", 14+ 7);
			line(player, "<str>I found the piece of the map that was hidden beneath Ice", 15+ 7);
			line(player, "<str>Mountain.", 16+ 7);
			line(player, "<str>I found the piece of the map that the goblin, Wormbrain,", 17+ 7);
			line(player, "<str>stole.", 18+ 7);
			line(player, "<str>The Duke of Lumbridge gave me an anti-dragonbreath", 19+ 7);
			line(player, "<str>shield.", 20+ 7);
			if (!player.getAttribute("demon-slayer:memorize", false)) {
				if (!player.getInventory().containsItem(ELVARG_HEAD)) {
					line(player, BLUE + "Now all I need to do is kill the " + RED + "dragon" + BLUE + "!", 21+ 7);
				} else {
					line(player, BLUE + "I have slain the dragon! Now I just need to tell " + RED + "Oziach.", 21+ 7);
				}
			} else {
				line(player, "<str>I have found a secret passage leading from Karamaja to", 21+ 7);
				line(player, "<str>Crandor, so I no longer need to worry about finding a", 22+ 7);
				line(player, "<str>seaworthy ship and captain to take me there.", 23+ 7);
				if (!player.getInventory().containsItem(ELVARG_HEAD)) {
					line(player, BLUE + "Now all I need to do is kill the " + RED + "dragon" + BLUE + "!", 24+ 7);
				} else {
					line(player, BLUE + "I have slain the dragon! Now I just need to tell " + RED + "Oziach.", 24+ 7);
				}
			}
			break;
		case 100:
			line(player, "<str>The Guildmaster of the Champions' Guild said I could earn", 4+ 7);
			line(player, "<str>the right to wear rune armour if I went on a quest for", 5+ 7);
			line(player, "<str>Oziach, who makes the armour.", 6+ 7);
			line(player, "<str>I spoke to Oziach in Edgeville. He told me to slay the", 7+ 7);
			line(player, "<str>dragon of Crandor island.", 8+ 7);
			line(player, "<str>The Champions' Guilg Guildmaster told me I had to find", 9+ 7);
			line(player, "<str>three pieces of a map to Crandor, a ship, a captain to take", 10+ 7);
			line(player, "<str>me there and a shield to protect me from the dragon's", 11+ 7);
			line(player, "<str>breath.", 12+ 7);
			line(player, "<str>I found the piece of the map that was hidden in Melzar's", 13+ 7);
			line(player, "<str>Maze.", 14+ 7);
			line(player, "<str>I found the piece of the map that was hidden beneath Ice", 15+ 7);
			line(player, "<str>Mountain.", 16+ 7);
			line(player, "<str>I found the piece of the map that the goblin, Wormbrain,", 17+ 7);
			line(player, "<str>stole.", 18+ 7);
			line(player, "<str>The Duke of Lumbridge gave me an anti-dragonbreath", 19+ 7);
			line(player, "<str>shield.", 20+ 7);
			line(player, "<str>I have found a secret passage leading from Karamaja to", 21+ 7);
			line(player, "<str>Crandor, so I no longer need to worry about finding a", 22+ 7);
			line(player, "<str>seaworthy ship and captain to take me there.", 23+ 7);
			line(player, "<str>I sailed to Crandor and killed the dragon. I am not a true", 24+ 7);
			line(player, "<str>champion and have proved myself worthy to wear rune", 25+ 7);
			line(player, "<str>platemail!", 26+ 7);
			line(player, "<col=FF0000>QUEST COMPLETE!</col>", 27+ 7);
			line(player, BLUE + "I gained " + RED + "2 Quest Points" + BLUE + ", " + RED + "18,650 Strength XP" + BLUE + "," + RED + " 18" + BLUE + ",650", 28+ 7);
			line(player, RED + "Defence XP " + BLUE + "and the right to wear " + RED + "rune platebodies.", 29+ 7);
			break;
		}
	}

	@Override
	public void finish(Player player) {
		super.finish(player);
		player.getPacketDispatch().sendString("2 Quests Points", 277, 8 + 2);
		player.getPacketDispatch().sendString("Ability to wear rune platebody", 277, 9 + 2);
		player.getPacketDispatch().sendString("18,650 Strength XP", 277, 10 + 2);
		player.getPacketDispatch().sendString("18,650 Defence XP", 277, 11 + 2);
		player.getPacketDispatch().sendString("You have completed the Dragon Slayer Quest!", 277, 2 + 2);
		player.getPacketDispatch().sendItemZoomOnInterface(ELVARG_HEAD.getId(), 230, 277, 3 + 2);
		player.getSkills().addExperience(Skills.STRENGTH, 18650);
		player.getSkills().addExperience(Skills.DEFENCE, 18650);
	}

	/**
	 * Method used to handle going through the magic door.
	 * @param player the player.
	 * @param interaction the interaction.
	 * @return <code>True</code> if so.
	 */
	public static boolean handleMagicDoor(final Player player, boolean interaction) {
		if (!player.getSavedData().getQuestData().getDragonSlayerItem("lobster") || !player.getSavedData().getQuestData().getDragonSlayerItem("bowl") || !player.getSavedData().getQuestData().getDragonSlayerItem("silk") || !player.getSavedData().getQuestData().getDragonSlayerItem("wizard")) {
			if (interaction) {
				player.getPacketDispatch().sendMessage("You can't see any way to open the door.");
			}
			return true;
		}
		player.getPacketDispatch().sendMessage("The door opens...");
		final GameObject object = RegionManager.getObject(new Location(3050, 9839, 0));
		player.faceLocation(object.getLocation());
		player.getPacketDispatch().sendObjectAnimation(object, new Animation(6636));
		GameWorld.Pulser.submit(new Pulse(1, player) {
			int counter = 0;

			@Override
			public boolean pulse() {
				switch (counter++) {
				case 4:
					AgilityHandler.walk(player, 0, player.getLocation(), player.getLocation().getX() == 3051 ? Location.create(3049, 9840, 0) : Location.create(3051, 9840, 0), null, 0, null);
					break;
				case 5:
					player.getPacketDispatch().sendObjectAnimation(object, new Animation(6637));
					break;
				case 6:
					player.getPacketDispatch().sendObjectAnimation(object, new Animation(6635));
					return true;
				}
				return false;
			}
		});
		return true;
	}
	
}
