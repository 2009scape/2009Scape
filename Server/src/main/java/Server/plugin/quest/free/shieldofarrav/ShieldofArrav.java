package plugin.quest.free.shieldofarrav;

import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.quest.Quest;
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
		super("Shield of Arrav", 29, 28, 1, 145, 0, 1, 7); 
	}

	@Override
	public Quest newInstance(Object object) {
		PluginManager.definePlugins(new CertificatePlugin(), new CuratorHaigHalenDialogue(), new JohnnyBeardNPC(), new JonnytheBeardPlugin(), new KatrineDialogue(), new KingRoaldDialogue(), new ReldoDialogue(), new ShieldArravPlugin(), new ShieldofArravBook(), new StravenDialogue(), new WeaponsMasterDialogue());
		return this;
	}
	
	@Override
	public void drawJournal(Player player, int stage) {
		super.drawJournal(player, stage);
		switch (stage) {
		case 0:
			player.getPacketDispatch().sendString(BLUE + "I can start this quest by speaking to " + RED + "Reldo " + BLUE + "in " + RED + "Varrock's", 275, 4+ 7);
			player.getPacketDispatch().sendString(RED + "Palace Library" + BLUE + ", or by speaking to " + RED + "Charlie the Tramp" + BLUE + " near", 275, 5+ 7);
			player.getPacketDispatch().sendString(BLUE + "the " + RED + "Blue Moon Inn " + BLUE + "in " + RED + "Varrock.", 275, 6+ 7);
			player.getPacketDispatch().sendString(BLUE + "I will need a friend to help me and some combat experience", 275, 7+ 7);
			player.getPacketDispatch().sendString(BLUE + "may be an advantage.", 275, 8+ 7);
			break;
		case 10:
			line(player, RED + "Reldo " + BLUE + "says there is a " + RED + "quest " + BLUE + "hidden in one of the books in", 4+ 7);
			line(player, BLUE + "his " + RED + "library" + BLUE + " somewhere. I should look for it and see.", 5+ 7);
			break;
		case 20:
			line(player, "<str>I read about a valuable shield stolen long ago by a gang of", 4+ 7);
			line(player, "<str>thieves with an outstanding reward upon it.", 5+ 7);
			line(player, BLUE + "I should ask " + RED + "Reldo " + BLUE + "if he knows anything more about this.", 6+ 7);
			break;
		case 30:
			line(player, "<str>I read about a valuable shield stolen long ago by a gang of", 4+ 7);
			line(player, "<str>thieves with an outstanding reward upon it.", 5+ 7);
			line(player, BLUE + "Reldo told me that the fur trader in " + RED + "Varrock" + BLUE + ", named", 6+ 7);
			line(player, RED + "Baraeck" + BLUE + ", knows about the " + RED + "Phoenix Gang." + BLUE + " I should speak to", 7+ 7);
			line(player, BLUE + "him next.", 8+ 7);
			break;
		case 40:
			line(player, "<str>I read about a valuable shield stolen long ago by a gang of", 4+ 7);
			line(player, "<str>thieves with an outstanding reward upon it.", 5+ 7);
			line(player, BLUE + "Baraeck told me that the " + RED + "'Phoenix Gang' " + BLUE + "have a hideout in", 6+ 7);
			line(player, BLUE + "the " + RED + "south-eastern part of Varrock" + BLUE + ", disgusing themselves", 7+ 7);
			line(player, BLUE + "as the " + RED + "VTAM Corporation" + BLUE + ". I should find them and join.", 8+ 7);
			break;
		case 50:
			line(player, "<str>I read about a valuable shield stolen long ago by a gang of", 4+ 7);
			line(player, "<str>thieves with an outstanding reward upon it.", 5+ 7);
			line(player, BLUE + "Baraeck told me that the " + RED + "'Phoenix Gang' " + BLUE + "have a hideout in", 6+ 7);
			line(player, BLUE + "the " + RED + "south-eastern part of Varrock" + BLUE + ", disgusing themselves", 7+ 7);
			line(player, BLUE + "as the " + RED + "VTAM Corporation" + BLUE + ". I should find them and join.", 8+ 7);
			line(player, "<str>I also spoke to Charlie the tramp in Varrock.", 9+ 7);
			line(player, BLUE + "According to him there is a criminal organisation known as", 10+ 7);
			line(player, BLUE + "the " + RED + "'Black Army Gang' " + BLUE + "down an alley near to him. I should", 11+ 7);
			line(player, BLUE + "speak to their " + RED + "leader, Katrine" + BLUE + ", about joining.", 12+ 7);
			break;
		case 60:
			line(player, "<str>I read about a valuable shield stolen long ago by a gang of", 4+ 7);
			line(player, "<str>thieves with an outstanding reward upon it. Baraeck told me", 5+ 7);
			line(player, "<str>the location of the Phoenix Gang hideout.", 6+ 7);
			line(player, "<str>I also spoke to Charlie the tramp in Varrock.", 7+ 7);
			line(player, "<str>According to him there is a criminal organisation known as", 8+ 7);
			line(player, "<str>the " + RED + "'Black Arm Gang'" + BLUE + "down the alley near to him.", 9+ 7);
			if (isPhoenixMission(player) && isBlackArmMission(player)) {
				line(player, "<str>I read about a valuable shield stolen long ago by a gang of", 4+ 7);
				line(player, "<str>thieves with an outstanding reward upon it. Baraeck told me", 5+ 7);
				line(player, "<str>the location of the Phoenix Gang hideout.", 6+ 7);
				line(player, "<str>To start this quest, I spoke to Charlie the tramp in Varrock.", 7+ 7);
				line(player, "<str>He gave me the location of the Black Arm gang HQ.", 8+ 7);
				line(player, "<str>According to him there is a criminal organisation known as", 9+ 7);
				line(player, "<str>the " + RED + "'Black Arm Gang'" + BLUE + "down the alley near to him.", 10+ 7);
				line(player, BLUE + "If I want to join the " + RED + "Phoenix Gang " + BLUE + "I need to kill" + RED + " Jonny The", 11+ 7);
				line(player, RED + "Beard " + BLUE + "in the " + RED + "Blue Moon Inn " + BLUE + "and retreive his " + RED + "report.", 12+ 7);
				line(player, RED + "Katrine " + BLUE + "said if I wanted to join the " + RED + "Black Arm Gang," + BLUE + " I'd", 13+ 7);
				line(player, BLUE + "have to steal " + RED + "two Phoenix crossbows " + BLUE + "from the rival gang.", 14+ 7);
				line(player, BLUE + "Maybe " + RED + "Charlie the tramp " + BLUE + "can give me some ideas about", 15+ 7);
				line(player, BLUE + "how to do this.", 16+ 7);
			} else if (isPhoenixMission(player)) {
				line(player, BLUE + "If I want to join the " + RED + "Phoenix Gang " + BLUE + "I need to kill" + RED + " Jonny The", 10+ 7);
				line(player, RED + "Beard " + BLUE + "in the " + RED + "Blue Moon Inn " + BLUE + "and retreive his " + RED + "report.", 11+ 7);
				line(player, BLUE + "Alternatively, if I want to join the " + RED + "Blackarm gang " + BLUE + "I should", 12+ 7);
				line(player, BLUE + "speak to their " + RED + "leader, Katrine, " + BLUE + "about joining.", 13+ 7);
			} else if (isBlackArmMission(player)) {
				line(player, RED + "Katrine " + BLUE + "said if I wanted to join the " + RED + "Black Arm Gang" + BLUE + ", I'd", 10+ 7);
				line(player, BLUE + "have to steal " + RED + "two Phoenix crossbows " + BLUE + "from the rival gang.", 11+ 7);
				line(player, BLUE + "Maybe " + RED + "Charlie the tramp " + BLUE + "can give me some ideas about", 12+ 7);
				line(player, BLUE + "how to do this.", 13+ 7);
			}
			break;
		case 70:
			if (isPhoenix(player)) {
				line(player, "<str>I read about a valuable shield stolen long ago by a gang of", 4+ 7);
				line(player, "<str>thieves with an outstanding reward upon it. Baraeck told me", 5+ 7);
				line(player, "<str>the location of the Phoenix Gang hideout.", 6+ 7);
				line(player, "<str>I killed Jonny the Beard and was welcomed into the Phoenix", 7+ 7);
				line(player, "<str>Gang. Straven gave me a key to the weapons room.", 8+ 7);
				if (!player.getInventory().containsItem(PHOENIX_SHIELD) && !player.getBank().containsItem(PHOENIX_SHIELD)) {
					line(player, BLUE + "I need to search the " + RED + "Phoenix Gang's hideout " + BLUE + "to find half", 10+ 7);
					line(player, BLUE + "of the " + RED + "Shield of Arrav.", 11+ 7);
				} else {
					line(player, BLUE + "I found half of the " + RED + "Shield of Arrav " + BLUE + "in the " + RED + "Phoenix Gang's", 10+ 7);
					line(player, RED + "hideout.", 11+ 7);
				}
				line(player, BLUE + "The second half of the " + RED + "shield" + BLUE + " belongs to a rival gang", 12+ 7);
				line(player, BLUE + "known as the " + RED + "Black Arm Gang. " + BLUE + "I will need " + RED + "a friend's help " + BLUE + "to", 13+ 7);
				line(player, BLUE + "retreive it before claiming the " + RED + "reward " + BLUE + "for it.", 14+ 7);
			} else {
				line(player, "<str>I read about a valuable shield stolen long ago by a gang of", 4+ 7);
				line(player, "<str>thieves with an outstanding reward upon it. Baraeck told me", 5+ 7);
				line(player, "<str>the location of the Phoenix Gang hideout.", 6+ 7);
				line(player, "<str>With the help of a friend, I managed to obtain two Phoenix", 8+ 7);
				line(player, "<str>Crossbows from the Phoenix Gang's weapons store, and", 9+ 7);
				line(player, "<str>Katrine welcomes me as a Black Arm Gang member.", 10+ 7);
				line(player, BLUE + "With " + RED + "my friend's help" + BLUE + ", I can get both pieces of the shield", 12+ 7);
				line(player, BLUE + "and return it to " + RED + "King Roald " + BLUE + "for my " + RED + "reward.", 13+ 7);
			}
			break;
		case 100:
			line(player, "<str>I read about a valuable shield stolen long ago by a gang of", 4+ 7);
			line(player, "<str>thieves with an outstanding reward upon it. Baraeck told me", 5+ 7);
			line(player, "<str>the location of the Phoenix Gang hideout.", 6+ 7);
			if (!isPhoenix(player)) {
				line(player, "<str>With the help of a friend, I managed to obtain two Phoenix", 8+ 7);
				line(player, "<str>Crossbows from the Phoenix Gang's weapons store, and", 9+ 7);
				line(player, "<str>Katrine welcomes me as a Black Arm Gang member.", 10+ 7);
				line(player, "<str>With the help of my friend in the rival gang, I was able to", 12+ 7);
				line(player, "<str>retreive both parts of the fabled Shield of Arrav and", 13+ 7);
				line(player, "<str>return it to the Museum of Varrock. In Recognition of my", 14+ 7);
				line(player, "<str>efforts, King Roald paid me the reward set by his", 15+ 7);
				line(player, "<str>ancestor.", 16+ 7);
				line(player, "<col=FF00000>QUEST COMPLETE!", 18+ 7);
				line(player, RED + "I received a reward of 600 coins and got 1 quest point.", 19+ 7);
			} else {
				line(player, "<str>I killed Jonny the Beard and was welcomed into the Phoenix", 7+ 7);
				line(player, "<str>Gang. Straven gave me a key to the weapons room.", 8+ 7);
				line(player, "<str>With the help of my friend in the rival gang, I was able to", 10+ 7);
				line(player, "<str>retreive both parts of the fabled Shield of Arrav and", 11+ 7);
				line(player, "<str>return it to the Museum of Varrock. In Recognition of my", 12+ 7);
				line(player, "<str>efforts, King Roald paid me the reward set by his", 13+ 7);
				line(player, "<str>ancestor.", 14+ 7);
				line(player, "<col=FF00000>QUEST COMPLETE!", 16+ 7);
				line(player, RED + "I received a reward of 600 coins and got 1 quest point.", 17+ 7);
			}
			break;
		}
	}

	@Override
	public void finish(Player player) {
		super.finish(player);
		player.removeAttribute("blackarm-mission");
		player.removeAttribute("phoenix-mission");
		player.getPacketDispatch().sendString("1 Quests Points", 277, 8 + 2);
		player.getPacketDispatch().sendString("600 Coins", 277, 9 + 2);
		player.getPacketDispatch().sendItemZoomOnInterface(767, 1, 230, 277, 3 + 2);
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
	 * Method used to set that the player is trying to do the phoennix mission.
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
	 * @return
	 */
	public static final Item getShield(final Player player) {
		return isBlackArm(player) ? BLACKARM_SHIELD : PHOENIX_SHIELD;
	}

}
