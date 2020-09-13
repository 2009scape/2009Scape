package plugin.quest.free.shieldofarrav;

import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.quest.Quest;
import core.game.node.entity.player.link.quest.QuestReward;
import core.game.node.entity.player.link.quest.QuestRewardComponentItem;
import core.game.node.item.Item;
import core.plugin.InitializablePlugin;
import core.plugin.PluginManager;

/**
 * Represents the shield of arrav quest.
 * @author Vexia
 * 
 */
@InitializablePlugin
public class ShieldofArrav extends Quest {

	/**
	 * Represents the shield of arrav book item.
	 */
	public static final Item BOOK = new Item(757);

	/**
	 * Represents the intel report item.
	 */
	public static final Item INTEL_REPORT = new Item(761);

	/**
	 * Represents the weapon store item key.
	 */
	public static final Item KEY = new Item(759);

	/**
	 * Represents the phoenix shield item.
	 */
	public static final Item PHOENIX_SHIELD = new Item(763);

	/**
	 * Represents the black arm shield item.
	 */
	public static final Item BLACKARM_SHIELD = new Item(765);

	/**
	 * Represents the blackarm certificate item.
	 */
	public static final Item BLACKARM_CERTIFICATE = new Item(11174, 2);

	/**
	 * Represents the phoenix certificate item.
	 */
	public static final Item PHOENIX_CERTIFICATE = new Item(11173, 2);

	/**
	 * Constructs a new {@Code ShieldofArrav} {@Code Object}
	 */
	public ShieldofArrav() {
		super(
			"Shield of Arrav",
			29,
			28,
			1,
			145, 0, 1, 7
		);
	}

	@Override
	public Quest newInstance(Object object) {
		PluginManager.definePlugins(
			new CertificatePlugin(),
			new CuratorHaigHalenDialogue(),
			new JohnnyBeardNPC(),
			new JonnytheBeardPlugin(),
			new KatrineDialogue(),
			new KingRoaldDialogue(),
			new ReldoDialogue(),
			new ShieldArravPlugin(),
			new ShieldofArravBook(),
			new StravenDialogue(),
			new WeaponsMasterDialogue()
		);
		return this;
	}
	
	@Override
	public void drawJournal(Player player, int stage) {
		super.drawJournal(player, stage);
		int line;
		switch (stage) {
		case 0:
			writeJournal(player,
				BLUE + "I can start this quest by speaking to " + RED + "Reldo " + BLUE + "in " + RED + "Varrock's",
				RED + "Palace Library" + BLUE + ", or by speaking to " + RED + "Charlie the Tramp" + BLUE + " near",
				BLUE + "the " + RED + "Blue Moon Inn " + BLUE + "in " + RED + "Varrock.",
				BLUE + "I will need a friend to help me and some combat experience",
				BLUE + "may be an advantage.");
			break;
		case 10:
			writeJournal(player,
				RED + "Reldo " + BLUE + "says there is a " + RED + "quest " + BLUE + "hidden in one of the books in",
				BLUE + "his " + RED + "library" + BLUE + " somewhere. I should look for it and see.");
			break;
		case 20:
			writeJournal(player,
				"<str>I read about a valuable shield stolen long ago by a gang of",
				"<str>thieves with an outstanding reward upon it.",
				BLUE + "I should ask " + RED + "Reldo " + BLUE + "if he knows anything more about this.");
			break;
		case 30:
			writeJournal(player,
				"<str>I read about a valuable shield stolen long ago by a gang of",
				"<str>thieves with an outstanding reward upon it.",
				BLUE + "Reldo told me that the fur trader in " + RED + "Varrock" + BLUE + ", named",
				RED + "Baraek" + BLUE + ", knows about the " + RED + "Phoenix Gang." + BLUE + " I should speak to",
				BLUE + "him next.");
			break;
		case 40:
			writeJournal(player,
				"<str>I read about a valuable shield stolen long ago by a gang of",
				"<str>thieves with an outstanding reward upon it.",
				BLUE + "Baraek told me that the " + RED + "'Phoenix Gang' " + BLUE + "have a hideout in",
				BLUE + "the " + RED + "south-eastern part of Varrock" + BLUE + ", disguising themselves",
				BLUE + "as the " + RED + "VTAM Corporation" + BLUE + ". I should find them and join.");
			break;
		case 50:
			writeJournal(player,
				"<str>I read about a valuable shield stolen long ago by a gang of",
				"<str>thieves with an outstanding reward upon it.",
				BLUE + "Baraek told me that the " + RED + "'Phoenix Gang' " + BLUE + "have a hideout in",
				BLUE + "the " + RED + "south-eastern part of Varrock" + BLUE + ", disguising themselves",
				BLUE + "as the " + RED + "VTAM Corporation" + BLUE + ". I should find them and join.",
				"<str>I also spoke to Charlie the tramp in Varrock.",
				BLUE + "According to him there is a criminal organisation known as",
				BLUE + "the " + RED + "'Black Arm Gang' " + BLUE + "down an alley near to him. I should",
				BLUE + "speak to their " + RED + "leader, Katrine" + BLUE + ", about joining.");
			break;
		case 60:
			line = writeJournal(player,
				"<str>I read about a valuable shield stolen long ago by a gang of",
				"<str>thieves with an outstanding reward upon it. Baraek told me",
				"<str>the location of the Phoenix Gang hideout.",
				"<str>I also spoke to Charlie the tramp in Varrock.",
				"<str>According to him there is a criminal organisation known as",
				"<str>the " + RED + "'Black Arm Gang' " + BLUE + "down the alley near to him.");
			if (isPhoenixMission(player) && isBlackArmMission(player)) {
				writeJournal(player, line - 3,
					"<str>To start this quest, I spoke to Charlie the tramp in Varrock.",
					"<str>He gave me the location of the Black Arm gang HQ.",
					"<str>According to him there is a criminal organisation known as",
					"<str>the " + RED + "'Black Arm Gang'" + BLUE + "down the alley near to him.",
					BLUE + "If I want to join the " + RED + "Phoenix Gang " + BLUE + "I need to kill" + RED + " Jonny the",
					RED + "Beard " + BLUE + "in the " + RED + "Blue Moon Inn " + BLUE + "and retrieve his " + RED + "report.",
					RED + "Katrine " + BLUE + "said if I wanted to join the " + RED + "Black Arm Gang," + BLUE + " I'd",
					BLUE + "have to steal " + RED + "two Phoenix crossbows " + BLUE + "from the rival gang.",
					BLUE + "Maybe " + RED + "Charlie the tramp " + BLUE + "can give me some ideas about",
					BLUE + "how to do this.");
			} else if (isPhoenixMission(player)) {
				writeJournal(player, line,
					BLUE + "If I want to join the " + RED + "Phoenix Gang " + BLUE + "I need to kill " + RED + "Jonny the",
					RED + "Beard " + BLUE + "in the " + RED + "Blue Moon Inn " + BLUE + "and retrieve his " + RED + "report.",
					BLUE + "Alternatively, if I want to join the " + RED + "Blackarm gang " + BLUE + "I should",
					BLUE + "speak to their " + RED + "leader, Katrine, " + BLUE + "about joining.");
			} else if (isBlackArmMission(player)) {
				writeJournal(player, line,
					RED + "Katrine " + BLUE + "said if I wanted to join the " + RED + "Black Arm Gang" + BLUE + ", I'd",
					BLUE + "have to steal " + RED + "two Phoenix crossbows " + BLUE + "from the rival gang.",
					BLUE + "Maybe " + RED + "Charlie the tramp " + BLUE + "can give me some ideas about",
					BLUE + "how to do this.");
			}
			break;
		case 70:
			if (isPhoenix(player)) {
				line = writeJournal(player,
					"<str>I read about a valuable shield stolen long ago by a gang of",
					"<str>thieves with an outstanding reward upon it. Baraek told me",
					"<str>the location of the Phoenix Gang hideout.",
					"<str>I killed Jonny the Beard and was welcomed into the Phoenix",
					"<str>Gang. Straven gave me a key to the weapons room.",
					""
				);
				if (!player.getInventory().containsItem(PHOENIX_SHIELD) && !player.getBank().containsItem(PHOENIX_SHIELD)) {
					line = writeJournal(player, line,
						BLUE + "I need to search the " + RED + "Phoenix Gang's hideout " + BLUE + "to find half",
						BLUE + "of the " + RED + "Shield of Arrav.");
				} else {
					writeJournal(player, line,
						BLUE + "I found half of the " + RED + "Shield of Arrav " + BLUE + "in the " + RED + "Phoenix Gang's",
						RED + "hideout."
					);
				}
				writeJournal(player, line,
					BLUE + "The second half of the " + RED + "shield" + BLUE + " belongs to a rival gang",
					BLUE + "known as the " + RED + "Black Arm Gang. " + BLUE + "I will need " + RED + "a friend's help " + BLUE + "to",
					BLUE + "retrieve it before claiming the " + RED + "reward " + BLUE + "for it.");
			} else {
				writeJournal(player,
					"<str>I read about a valuable shield stolen long ago by a gang of",
					"<str>thieves with an outstanding reward upon it. Baraek told me",
					"<str>the location of the Phoenix Gang hideout.",
					"",
					"<str>With the help of a friend, I managed to obtain two Phoenix",
					"<str>Crossbows from the Phoenix Gang's weapons store, and",
					"<str>Katrine welcomes me as a Black Arm Gang member.",
					"",
					BLUE + "With " + RED + "my friend's help" + BLUE + ", I can get both pieces of the shield",
					BLUE + "and return it to " + RED + "King Roald " + BLUE + "for my " + RED + "reward.");
			}
			break;
		case 100:
			line = writeJournal(player,
				"<str>I read about a valuable shield stolen long ago by a gang of",
				"<str>thieves with an outstanding reward upon it. Baraek told me",
				"<str>the location of the Phoenix Gang hideout."
			);
			if (!isPhoenix(player)) {
				writeJournal(player, line,
					"",
					"<str>With the help of a friend, I managed to obtain two Phoenix",
					"<str>Crossbows from the Phoenix Gang's weapons store, and",
					"<str>Katrine welcomes me as a Black Arm Gang member.",
					"",
					"<str>With the help of my friend in the rival gang, I was able to",
					"<str>retrieve both parts of the fabled Shield of Arrav and",
					"<str>return it to the Museum of Varrock. In Recognition of my",
					"<str>efforts, King Roald paid me the reward set by his",
					"<str>ancestor.",
					"",
					"<col=FF00000>QUEST COMPLETE!",
					RED + "I received a reward of 600 coins and got 1 quest point.");
			} else {
				writeJournal(player, line,
					"<str>I killed Jonny the Beard and was welcomed into the Phoenix",
					"<str>Gang. Straven gave me a key to the weapons room.",
					"",
					"<str>With the help of my friend in the rival gang, I was able to",
					"<str>retrieve both parts of the fabled Shield of Arrav and",
					"<str>return it to the Museum of Varrock. In Recognition of my",
					"<str>efforts, King Roald paid me the reward set by his",
					"<str>ancestor.",
					"",
					"<col=FF00000>QUEST COMPLETE!",
					RED + "I received a reward of 600 coins and got 1 quest point.");
			}
			break;
		}
	}

	@Override
	public void finish(Player player) {
		super.finish(player);
		player.removeAttribute("blackarm-mission");
		player.removeAttribute("phoenix-mission");
	}

	@Override
	public QuestRewardComponentItem getRewardComponentItem() {
		return new QuestRewardComponentItem(767, 1, 230);
	}

	@Override
	public QuestReward[] getQuestRewards(Player player) {
		return new QuestReward[] {
			new QuestReward(new Item(995, 600)),
		};
	}

	/**
	 * Sets the phoenix gang.
	 * @param player the player.
	 */
	public static void setPhoenix(final Player player) {
		player.setAttribute("/save:phoenix-gang", true);
	}

	/**
	 * Sets the black arm gang.
	 * @param player the player.
	 */
	public static void setBlackArm(final Player player) {
		player.setAttribute("/save:black-arm-gang", true);
	}

	/**
	 * Method used to check if the player is part of the phoenix gang.
	 * @param player the player.
	 * @return <code>True</code> if so.
	 */
	public static boolean isPhoenix(final Player player) {
		return player.getAttribute("phoenix-gang", false);
	}

	/**
	 * Method used to check if the player is part of the black arm gang.
	 * @param player the player.
	 * @return <code>True</code> if so.
	 */
	public static boolean isBlackArm(final Player player) {
		return player.getAttribute("black-arm-gang", false);
	}

	/**
	 * Method used to set that the player is trying to do the phoenix mission.
	 * @param player the player.
	 */
	public static void setPhoenixMission(final Player player) {
		player.setAttribute("/save:phoenix-mission", true);
	}

	/**
	 * Method used to set that the player is trying to do the black arm gang
	 * mission.
	 * @param player the player.
	 */
	public static void setBlackArmMission(final Player player) {
		player.setAttribute("/save:blackarm-mission", true);
	}

	/**
	 * Method used to check if they're doing the black arm gang mission.
	 * @param player the player.
	 * @return <code>True</code> if so.
	 */
	public static boolean isBlackArmMission(final Player player) {
		return player.getAttribute("blackarm-mission", false);
	}

	/**
	 * Method used to check if they're doing the phoenix gang mission.
	 * @param player the player.
	 * @return <code>True</code> if so.
	 */
	public static boolean isPhoenixMission(final Player player) {
		return player.getAttribute("phoenix-mission", false);
	}

	/**
	 * Gets the shield item.
	 * @param player the player.
	 * @return the shield
	 */
	public static Item getShield(final Player player) {
		return isBlackArm(player) ? BLACKARM_SHIELD : PHOENIX_SHIELD;
	}

}
