package plugin.quest.free.shieldofarrav;

import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.quest.Quest;
import core.game.node.entity.player.link.quest.QuestReward;
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
	 * The name of the quest.
	 */
	public static final String NAME = "Shield of Arrav";

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
			NAME,
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
				"I can start this quest by speaking to <red>Reldo <blue>in <red>Varrock's",
				"<red>Palace Library<blue>, or by speaking to <red>Charlie the Tramp <blue>near",
				"the <red>Blue Moon Inn <blue>in <red>Varrock.",
				"I will need a friend to help me and some combat experience",
				"may be an advantage.");
			break;
		case 10:
			writeJournal(player,
				"<red>Reldo <blue>says there is a <red>quest <blue>hidden in one of the books in",
				"his <red>library <blue>somewhere. I should look for it and see.");
			break;
		case 20:
			line = writeJournal(player, true,
				"I read about a valuable shield stolen long ago by a gang of",
				"thieves with an outstanding reward upon it.");
			writeJournal(player, line,
				"I should ask <red>Reldo <blue>if he knows anything more about this.");
			break;
		case 30:
			line = writeJournal(player, true,
				"I read about a valuable shield stolen long ago by a gang of",
				"thieves with an outstanding reward upon it.");
			writeJournal(player, line,
				"Reldo told me that the fur trader in <red>Varrock<blue>, named",
				"<red>Baraek<blue>, knows about the <red>Phoenix Gang<blue>. I should speak to",
				"him next.");
			break;
		case 40:
			line = writeJournal(player, true,
				"I read about a valuable shield stolen long ago by a gang of",
				"thieves with an outstanding reward upon it.");
			writeJournal(player, line,
				"Baraek told me that the <red>'Phoenix Gang' <blue>have a hideout in",
				"the <red>south-eastern part of Varrock<blue>, disguising themselves",
				"as the <red>VTAM Corporation<blue>. I should find them and join.");
			break;
		case 50:
			line = writeJournal(player, true,
				"I read about a valuable shield stolen long ago by a gang of",
				"thieves with an outstanding reward upon it.");
			line = writeJournal(player, line,
				"Baraek told me that the <red>'Phoenix Gang' <blue>have a hideout in",
				"the <red>south-eastern part of Varrock<blue>, disguising themselves",
				"as the <red>VTAM Corporation<blue>. I should find them and join.");
			line = writeJournal(player, line, true,
				"I also spoke to Charlie the tramp in Varrock.");
			writeJournal(player, line,
				"According to him there is a criminal organisation known as",
				"the <red>'Black Arm Gang' <blue>down an alley near to him. I should",
				"speak to their <red>leader, Katrine<blue>, about joining.");
			break;
		case 60:
			line = writeJournal(player, true,
				"I read about a valuable shield stolen long ago by a gang of",
				"thieves with an outstanding reward upon it. Baraek told me",
				"the location of the Phoenix Gang hideout.",
				"I also spoke to Charlie the tramp in Varrock.",
				"According to him there is a criminal organisation known as",
				"the <red>'Black Arm Gang' <blue>down the alley near to him.");
			if (isPhoenixMission(player) && isBlackArmMission(player)) {
				line = writeJournal(player, line - 3, true,
					"To start this quest, I spoke to Charlie the tramp in Varrock.",
					"He gave me the location of the Black Arm gang HQ.",
					"According to him there is a criminal organisation known as",
					"the <red>'Black Arm Gang'<blue>down the alley near to him.");
				writeJournal(player, line,
					"If I want to join the <red>Phoenix Gang <blue>I need to kill <red>Jonny the",
					"<red>Beard <blue>in the <red>Blue Moon Inn <blue>and retrieve his <red>report.",
					"<red>Katrine <blue>said if I wanted to join the <red>Black Arm Gang, <blue>I'd",
					"have to steal <red>two Phoenix crossbows <blue>from the rival gang.",
					"Maybe <red>Charlie the tramp <blue>can give me some ideas about",
					"how to do this.");
			} else if (isPhoenixMission(player)) {
				writeJournal(player, line,
					"If I want to join the <red>Phoenix Gang <blue>I need to kill <red>Jonny the",
					"<red>Beard <blue>in the <red>Blue Moon Inn <blue>and retrieve his <red>report.",
					"Alternatively, if I want to join the <red>Blackarm gang <blue>I should",
					"speak to their <red>leader, Katrine, <blue>about joining.");
			} else if (isBlackArmMission(player)) {
				writeJournal(player, line,
					"<red>Katrine <blue>said if I wanted to join the <red>Black Arm Gang<blue>, I'd",
					"have to steal <red>two Phoenix crossbows <blue>from the rival gang.",
					"Maybe <red>Charlie the tramp <blue>can give me some ideas about",
					"how to do this.");
			}
			break;
		case 70:
			if (isPhoenix(player)) {
				line = writeJournal(player, true,
					"I read about a valuable shield stolen long ago by a gang of",
					"thieves with an outstanding reward upon it. Baraek told me",
					"the location of the Phoenix Gang hideout.",
					"I killed Jonny the Beard and was welcomed into the Phoenix",
					"Gang. Straven gave me a key to the weapons room.",
					""
				);
				if (!player.getInventory().containsItem(PHOENIX_SHIELD) && !player.getBank().containsItem(PHOENIX_SHIELD)) {
					line = writeJournal(player, line,
						"I need to search the <red>Phoenix Gang's hideout <blue>to find half",
						"of the <red>Shield of Arrav."
					);
				} else {
					writeJournal(player, line,
						"I found half of the <red>Shield of Arrav <blue>in the <red>Phoenix Gang's",
						"<red>hideout."
					);
				}
				writeJournal(player, line,
					"The second half of the <red>shield <blue>belongs to a rival gang",
					"known as the <red>Black Arm Gang. <blue>I will need <red>a friend's help <blue>to",
					"retrieve it before claiming the <red>reward <blue>for it.");
			} else {
				line = writeJournal(player, true,
					"I read about a valuable shield stolen long ago by a gang of",
					"thieves with an outstanding reward upon it. Baraek told me",
					"the location of the Phoenix Gang hideout.",
					"",
					"With the help of a friend, I managed to obtain two Phoenix",
					"Crossbows from the Phoenix Gang's weapons store, and",
					"Katrine welcomes me as a Black Arm Gang member.",
					"");
				writeJournal(player, line,
					"With <red>my friend's help<blue>, I can get both pieces of the shield",
					"and return it to <red>King Roald <blue>for my <red>reward."
				);
			}
			break;
		case 100:
			line = writeJournal(player, true,
				"I read about a valuable shield stolen long ago by a gang of",
				"thieves with an outstanding reward upon it. Baraek told me",
				"the location of the Phoenix Gang hideout."
			);
			if (!isPhoenix(player)) {
				line = writeJournal(player, line, true,
					"",
					"With the help of a friend, I managed to obtain two Phoenix",
					"Crossbows from the Phoenix Gang's weapons store, and",
					"Katrine welcomes me as a Black Arm Gang member."
				);
			} else {
				line = writeJournal(player, line, true,
					"I killed Jonny the Beard and was welcomed into the Phoenix",
					"Gang. Straven gave me a key to the weapons room."
				);
			}
			line = writeJournal(player, line, true,
				"",
				"With the help of my friend in the rival gang, I was able to",
				"retrieve both parts of the fabled Shield of Arrav and",
				"return it to the Museum of Varrock. In recognition of my",
				"efforts, King Roald paid me the reward set by his",
				"ancestor.");
			writeJournal(player, ++line,
				"<col=FF00000>QUEST COMPLETE!",
				"<red>I received a reward of 600 coins and got 1 quest point.");
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
	public Item getRewardComponentItem() {
		return new Item(767);
	}

	@Override
	public QuestReward[] getQuestRewards() {
		return new QuestReward[]{
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
