package plugin.quest.free.dragonslayer;

import core.game.component.Component;
import core.game.node.entity.player.link.quest.QuestReward;
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
	 * The name of the quest.
	 */
	public static final String NAME = "Dragon Slayer";

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
		super(
			NAME,
			18,
			17,
			2,
			176, 0, 1, 10
		);
	}
	
	@Override
	public Quest newInstance(Object object) {
		PluginManager.definePlugins(
			new CrandorMapPlugin(),
			new DragonSlayerPlugin(),
			new DSMagicDoorPlugin(),
			new DragonSlayerCutscene(),
			new MazeDemonNPC(),
			new MazeGhostNPC(),
			new MazeSkeletonNPC(),
			new MazeZombieNPC(),
			new MeldarMadNPC(),
			new WormbrainNPC(),
			new ZombieRatNPC(),
			new DSChestDialogue(),
			new GuildmasterDialogue(),
			new ElvargNPC(),
			new WormbrainDialogue(),
			new OziachDialogue(),
			new NedDialogue(),
			new DukeHoracioDialogue()
		);
		return this;
	}
	
	@Override
	public void drawJournal(Player player, int stage) {
		super.drawJournal(player, stage);
		int line = JOURNAL_TEXT_START;
		switch (getStage(player)) {
		case 0:
			line = writeJournal(player,
				"I can start this quest by speaking to the <red>Guildmaster <blue>in",
				"the <red>Champions' Guild<blue>, south-west of Varrock.",
				"I will need to be able to defeat a <red>level 83 dragon.");
			writeJournal(player, line, player.getQuestRepository().getPoints() < 32,
				"To enter the Champions' Guild I need <red>32 Quest Points.");
			break;
		case 10:
			line = writeJournal(player, true,
				"The Guildmaster of the Champions' Guild said I could earn",
				"the right to wear rune armour if I went on a quest for",
				"Oziach, who makes the armour.");
			writeJournal(player, line,
				"I should speak to <red>Oziach<blue>, who lives by the cliffs to the",
				"west of <red>Edgeville.");
			break;
		case 15:
			line = writeJournal(player, true,
				"The Guildmaster of the Champions' Guild said I could earn",
				"the right to wear rune armour if I went on a quest for",
				"Oziach, who makes the armour.",
				"I spoke to Oziach in Edgeville. He told me to slay the",
				"dragon of Crandor island.");
			writeJournal(player, line,
				"I should return to the <red>Champions' Guild Guildmaster <blue>for",
				"more detailed instructions.");
			break;
		case 20:
			line = writeJournal(player, true,
				"The Guildmaster of the Champions' Guild said I could earn",
				"the right to wear rune armour if I went on a quest for",
				"Oziach, who makes the armour.",
				"I spoke to Oziach in Edgeville. He told me to slay the",
				"dragon of Crandor island.",
				"The Champions' Guild Guildmaster gave me more detailed",
				"instructions.");
			writeJournal(player, line,
				"To defeat the dragon I will need to find a <red>map <blue>to Crandor, a",
				"<red>ship<blue>, a <red>captain <blue>to take me there and some kind of",
				"<red>protection <blue>against the dragon's breath."
			);
			if (player.getInventory().containsItem(MAZE_PIECE) || player.getBank().containsItem(MAZE_PIECE)) {
				line = writeJournal(player, line, true,
					"I found the piece of the map that was hidden in Melzar's",
					"Maze.");
			} else {
				line = writeJournal(player, line,
					"One-third of the map is in <red>Melzar's Maze<blue>, near",
					"<red>Rimmington");
			}

			if (player.getInventory().containsItem(MAGIC_PIECE) || player.getBank().containsItem(MAGIC_PIECE)) {
				line = writeJournal(player, line, true,
					"I found the piece of the map that was hidden beneath Ice",
					"Mountain.");
			} else {
				line = writeJournal(player, line,
					"One-third of the map is hidden, and only the <red>Oracle <blue>on <red>Ice",
					"<red>Mountain <blue>will know where it is.");
			}

			if (player.getInventory().containsItem(WORMBRAIN_PIECE) || player.getBank().containsItem(WORMBRAIN_PIECE)) {
				line = writeJournal(player, line, true,
					"I found the piece of the map that the goblin, Wormbrain,",
					"stole.");
			} else {
				line = writeJournal(player, line,
					"One-third of the map was stolen by a <red>goblin <blue>from the",
					"<red>Goblin Village.");
			}

			if (player.getInventory().containsItem(SHIELD) || player.getBank().containsItem(SHIELD)) {
				line = writeJournal(player, line, true,
					"The Duke of Lumbridge gave me an anti-dragonbreath",
					"shield.");
			} else {
				line = writeJournal(player, line,
					"I should ask the <red>Duke of Lumbridge <blue>for an <red>anti-",
					"<red>dragonbreath shield.");
			}

			if (player.getSavedData().getQuestData().getDragonSlayerAttribute("ship")) {
				line = writeJournal(player, line, true,
					"I bought a ship in Port Sarim called the Lady Lumbridge.");
				if (!player.getSavedData().getQuestData().getDragonSlayerAttribute("repaired")) {
					writeJournal(player, line, true,
						"I need to repair the hole in bottom of the ship.");
				} else {
					writeJournal(player, line, true,
						"I have repaired my ship using wooden planks and steel",
						"nails.");
				}
			} else {
				writeJournal(player, line,
					"I should see if there is a <red>ship <blue>for sale in <red>Port Sarim."
				);
			}

			break;
		case 30:
			line = writeJournal(player, true,
				"The Guildmaster of the Champions' Guild said I could earn",
				"the right to wear rune armour if I went on a quest for",
				"Oziach, who makes the armour.",
				"I spoke to Oziach in Edgeville. He told me to slay the",
				"dragon of Crandor island.",
				"The Champions' Guild Guildmaster told me I had to find",
				"three pieces of a map to Crandor, a ship, a captain to take",
				"me there and a shield to protect me from the dragon's",
				"breath.",
				"I found the piece of the map that was hidden in Melzar's",
				"Maze.",
				"I found the piece of the map that was hidden beneath Ice",
				"Mountain.",
				"I found the piece of the map that the goblin, Wormbrain,",
				"stole.",
				"The Duke of Lumbridge gave me an anti-dragonbreath",
				"shield.",
				"I bought a ship in Port Sarim called the Lady Lumbridge",
				"I have repaired my ship using wooden planks and steel",
				"nails.",
				"Captain Ned from Draynor Village has agreed to sail the",
				"ship to Crandor for me.");
			writeJournal(player, line,
				"Now I should go to my ship in <red>Port Sarim <blue>and set sail for",
				"<red>Crandor<blue>!");
			break;
		case 40:
			line = writeJournal(player, line, true,
				"The Guildmaster of the Champions' Guild said I could earn",
				"the right to wear rune armour if I went on a quest for",
				"Oziach, who makes the armour.",
				"I spoke to Oziach in Edgeville. He told me to slay the",
				"dragon of Crandor island.",
				"The Champions' Guild Guildmaster told me I had to find",
				"three pieces of a map to Crandor, a ship, a captain to take",
				"me there and a shield to protect me from the dragon's",
				"breath.",
				"I found the piece of the map that was hidden in Melzar's",
				"Maze.",
				"I found the piece of the map that was hidden beneath Ice",
				"Mountain.",
				"I found the piece of the map that the goblin, Wormbrain,",
				"stole.",
				"The Duke of Lumbridge gave me an anti-dragonbreath",
				"shield."
			);
			if (player.getAttribute("demon-slayer:memorized", false)) {
				line = writeJournal(player, line, true,
					"I have found a secret passage leading from Karamja to",
					"Crandor, so I no longer need to worry about finding a",
					"seaworthy ship and captain to take me there."
				);
			}
			if (player.getInventory().containsItem(ELVARG_HEAD) || player.getBank().containsItem(ELVARG_HEAD)) {
				writeJournal(player, line,
					"I have slain the dragon! Now I just need to tell <red>Oziach."
				);
			} else {
				writeJournal(player, line,
					"Now all I need to do is kill the <red>dragon<blue>!"
				);
			}
			break;
		case 100:
			line = writeJournal(player, true,
				"The Guildmaster of the Champions' Guild said I could earn",
				"the right to wear rune armour if I went on a quest for",
				"Oziach, who makes the armour.",
				"I spoke to Oziach in Edgeville. He told me to slay the",
				"dragon of Crandor island.",
				"The Champions' Guild Guildmaster told me I had to find",
				"three pieces of a map to Crandor, a ship, a captain to take",
				"me there and a shield to protect me from the dragon's",
				"breath.",
				"I found the piece of the map that was hidden in Melzar's",
				"Maze.",
				"I found the piece of the map that was hidden beneath Ice",
				"Mountain.",
				"I found the piece of the map that the goblin, Wormbrain,",
				"stole.",
				"The Duke of Lumbridge gave me an anti-dragonbreath",
				"shield.",
				"I have found a secret passage leading from Karamja to",
				"Crandor, so I no longer need to worry about finding a",
				"seaworthy ship and captain to take me there.",
				"I sailed to Crandor and killed the dragon. I am a true",
				"champion and have proved myself worthy to wear rune",
				"platemail!");
			writeJournal(player, ++line,
				"<col=FF0000>QUEST COMPLETE!</col>",
				"I gained <red>2 Quest Points<blue>, <red>18,650 Strength XP<blue>, <red>18,650",
				"<red>Defence XP <blue>and the right to wear <red>rune platebodies.");
			break;
		}
	}

	@Override
	public Item getRewardComponentItem() {
		return ELVARG_HEAD;
	}

	@Override
	public QuestReward[] getQuestRewards() {
		return new QuestReward[]{
			new QuestReward("Ability to wear Rune platebody"),
			new QuestReward(Skills.STRENGTH, 18650),
			new QuestReward(Skills.DEFENCE, 18650),
		};
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
		GameWorld.getPulser().submit(new Pulse(1, player) {
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
