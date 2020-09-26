package plugin.quest.members.thetouristrap;

import core.game.component.Component;
import core.game.container.impl.EquipmentContainer;
import core.game.node.entity.player.link.quest.QuestRequirement;
import core.game.node.entity.player.link.quest.QuestReward;
import core.game.node.entity.player.link.quest.QuestRewardComponentItem;
import plugin.skill.Skills;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.quest.Quest;
import core.game.node.item.Item;
import core.game.system.task.Pulse;
import core.game.world.GameWorld;
import core.game.world.map.Location;
import core.game.world.map.zone.ZoneBorders;
import core.plugin.InitializablePlugin;
import core.plugin.PluginManager;

/**
 * The main type for the tourist trap quest.
 * @author Aero
 * @author Vexia
 * 
 */
@InitializablePlugin
public final class TouristTrap extends Quest {

	/**
	 * The name of the quest.
	 */
	public static final String NAME = "The Tourist Trap";

	/**
	 * The metal key item.
	 */
	public static final Item METAL_KEY = new Item(1839);

	/**
	 * The desert gear items.
	 */
	public static final Item[] DESERT_CLOTHES = new Item[] { new Item(1833), new Item(1835), new Item(1837) };

	/**
	 * The slave clothes items.
	 */
	public static final Item[] SLAVE_CLOTHES = new Item[] { new Item(1846), new Item(1844), new Item(1845) };

	/**
	 * Teh tenti pineapple item.
	 */
	public static final Item TENTI_PINEAPPLE = new Item(1851);

	/**
	 * The cell door key item.
	 */
	public static final Item CELL_DOOR_KEY = new Item(1840);

	/**
	 * The wrought iron key item.
	 */
	public static final Item WROUGHT_IRON_KEY = new Item(1843);

	/**
	 * The bedabin key item.
	 */
	public static final Item BEDABIN_KEY = new Item(1852);

	/**
	 * The config id for this quest.
	 */
	public static final int CONFIG_ID = 907;

	/**
	 * The technical plans item.
	 */
	public static final Item TECHNICAL_PLANS = new Item(1850);

	/**
	 * The prototype dart tip item.
	 */
	public static final Item PROTOTYPE_DART_TIP = new Item(1853);

	/**
	 * The prototype dart item.
	 */
	public static final Item PROTOTYPE_DART = new Item(1849);

	/**
	 * The barrel item.
	 */
	public static final Item BARREL = new Item(1841);

	/**
	 * The anna barrel item.
	 */
	public static final Item ANNA_BARREL = new Item(1842);

	/**
	 * The jail location.
	 */
	public static final Location JAIL = Location.create(3285, 3034, 0);

	/**
	 * The zone borders.
	 */
	public static final ZoneBorders JAIL_BORDER = new ZoneBorders(3284, 3032, 3287, 3037);

	/**
	 * The slots to check for items on.
	 */
	public static final int[] SLOTS = new int[] { EquipmentContainer.SLOT_WEAPON, EquipmentContainer.SLOT_FEET, EquipmentContainer.SLOT_SHIELD, EquipmentContainer.SLOT_HAT, EquipmentContainer.SLOT_CHEST, EquipmentContainer.SLOT_LEGS };

	/**
	 * Constructs a new {@code TouristTrap} {@code Object}.
	 */
	public TouristTrap() {
		super(
			NAME,
			123,
			122,
			2,
			197, 0, 1, 30
		);
	}

	@Override
	public Quest newInstance(Object object) {
		PluginManager.definePlugins(
			new TouristTrapPlugin(),
			new AnaDialogue(),
			new CaptainSiadDialogue(),
			new DesertGuardDialogue(),
			new IrenaDialogue(),
			new MaleSlaveDialogue(),
			new MercenaryCaptainDialogue(),
			new MercenaryDialogue(),
			new MinecartDriverDialogue(),
			new MineSlaveNPC(),
			new MiningCampZone(),
			new RowdySlaveNPC(),
			new AlShabimDialogue(),
			new BedabinNomadDialogue()
		);
		return this;
	}

	static String[][] JOURNAL_ENTRIES = new String[][]{
		new String[]{
			"Irena was distraught that her daughter Ana had vanished",
			"somewhere in the desert, and I agreed to help find her.",
		}
	};

	@Override
	public void drawJournal(Player player, int stage) {
		super.drawJournal(player, stage);
		int line = JOURNAL_TEXT_START;

		if (stage > 0) {
			line = writeJournal(player, true, JOURNAL_ENTRIES[0]);
		}

		switch (getStage(player)) {
		case 0:
			line = writeJournal(player,
				"I can start this quest by speaking to <red>Irena <blue>after I have",
				"gone through the <red>Shantay Pass, South of Al-Kharid.",
				"To complete this quest I need:"
			);
			line = writeJournal(player, line, getQuestRequirementsJournal(player));
			if (hasRequirements(player)) {
				writeJournal(player, line,
					"I have all the <red>requirements <blue>to begin and complete this",
					"<red>quest."
				);
			}
			break;
		case 10:
		case 11:
		case 30:
		case 40:
			writeJournal(player, line,
				"I need to head into <red>the desert <blue>and search for <red>Ana");
			break;
		case 50:
			writeJournal(player, line,
				"I need to find the guard a <red>Tenti Pineapple <blue>for the guard.");
			break;
		case 51:
		case 52:
			writeJournal(player, line,
				"I have found a way to get <red>Tenti Pineapple <blue>I need to find",
				"the research plans that <red>Captain Siad <blue>has.");
			break;
		case 53:
			writeJournal(player, line,
				"I have found a way to get <red>Tenti Pineapple<blue>",
				"I found the technical plans <red>Al Shabim <blue>was looking for.");
			break;
		case 54:
			writeJournal(player, line,
				"I have found a way to get <red>Tenti Pineapple",
				"I need to manufacture the <red>Prototype <blue>weapon for <red>Al Shabim<blue>.");
			break;
		case 60:
			writeJournal(player, line,
				"I manufactured the <red>Prototype <blue>weapon and received",
				"a tasty <red>Tenti Pineapple<blue>.");
			break;
		case 61:
			writeJournal(player, line,
				"I finally found <red>Anna<blue>. I just need to find a way to smuggle",
				"her out of here.");
			break;
		case 71:
			writeJournal(player, line,
				"I need to operate the <red>Winch <blue>to lift <red>Ana <blue>back up here.");
			break;
		case 72:
			writeJournal(player, line,
				"I need to get <red>Ana <blue>from one of the barrels lifted.");
			break;
		case 80:
			writeJournal(player, line,
				"I loaded <red>Ana <blue>into the <red>Cart<blue>. I now need to get the <red>cart driver",
				"to transport it.");
			break;
		case 90:
			writeJournal(player, line,
				"I payed the <red>Mine cart driver <blue>and he agreed to smuggle me and <red>Ana",
				"out of the <red>Mining camp<blue>.");
			break;
		case 95:
		case 98:
			writeJournal(player, line,
				"I smuggled both me and <red>Ana <blue>from the <red>Mining camp<blue>. I should",
				"go tell <red>Irena <blue>straight away.");
			break;
		case 100:
			line = writeJournal(player, line, true,
				"I returned Ana back to her mother and was rewarded",
				"with a key and the knowledge in two skills.");
			writeJournal(player, ++line,
				"<col=FF0000>QUEST COMPLETE!");
			break;
		}
	}

	@Override
	public void finish(Player player) {
		super.finish(player);
		player.getInventory().remove(new Item(1842, player.getInventory().getAmount(ANNA_BARREL)));
		player.getBank().remove(new Item(1842, player.getBank().getAmount(ANNA_BARREL)));
	}

	/**
	 * Sends the player to jail.
	 * @param player the player.
	 */
	public static void jail(final Player player, String dialogue) {
		player.getDialogueInterpreter().sendDialogues(4999, null, dialogue);
		player.lock();
		GameWorld.getPulser().submit(new Pulse(1) {
			int counter;

			@Override
			public boolean pulse() {
				switch (counter++) {
				case 1:
					player.lock();
					player.getInterfaceManager().openOverlay(new Component(115));
					break;
				case 3:
					player.getProperties().setTeleportLocation(Location.create(3285, 3034, 0));
					player.getPacketDispatch().sendMessage("You are roughed up by the guards and manhandled into a cell.");
					player.getInterfaceManager().closeOverlay();
					player.getInterfaceManager().close();
					player.unlock();
					return true;
				}
				return false;
			}
		});
	}

	/**
	 * Jails a player.
	 * @param player the player.
	 */
	public static void jail(final Player player) {
		jail(player, "Hey you! You're not supposed to be in here!");
	}

	/**
	 * Checks if the player is jailable.
	 * @param player the player.
	 * @return {@code True} if so.
	 */
	public static boolean isJailable(final Player player) {
		if (inJail(player)) {
			return false;
		}
		if (player.getEquipment().itemCount() > 3 || (!hasDesertClothes(player) && !hasSlaveClothes(player))) {
			for (int i : SLOTS) {
				if (player.getEquipment().get(i) != null) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Adds a config value pertaining to this quest.
	 * @param player the player.
	 * @param value the value.
	 */
	public static void addConfig(final Player player, final int value) {
		player.getConfigManager().set(CONFIG_ID, value, true);
	}

	/**
	 * Checks if the player has desert gear.
	 * @param player the player.
	 * @return {@code True} if so.
	 */
	public static boolean hasDesertClothes(final Player player) {
		for (Item i : DESERT_CLOTHES) {
			if (!player.getEquipment().containsItem(i)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Checks if the player has slave clothes.
	 * @param player the player.
	 * @return {@code True} if so.
	 */
	public static boolean hasSlaveClothes(final Player player) {
		for (Item i : SLAVE_CLOTHES) {
			if (!player.getEquipment().containsItem(i)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Checks if the player has armour.
	 * @param player the player.
	 * @return true if the player is wearing anything other than the desert or slave clothes
	 */
	public static boolean hasArmour(final Player player) {
		return player.getEquipment().itemCount() > 0 && !hasDesertClothes(player) && !hasSlaveClothes(player);
	}

	/**
	 * Checks if the player is in jail.
	 * @param player the player.
	 * @return {@code True} if so.
	 */
	public static boolean inJail(final Player player) {
		return JAIL_BORDER.insideBorder(player);
	}

	@Override
	public QuestRequirement[] getQuestRequirements(Player player) {
		return new QuestRequirement[]{
			new QuestRequirement(Skills.FLETCHING, 10),
			new QuestRequirement(Skills.SMITHING, 20),
		};
	}

	@Override
	public QuestRewardComponentItem getRewardComponentItem() {
		return new QuestRewardComponentItem(806, 230);
	}

	@Override
	public QuestReward[] getQuestRewards(Player player) {
		return new QuestReward[]{
			// XP is given in IrenaDialogue
			new QuestReward("4650 XP in each of the two skills"),
			new QuestReward("Ability to make throwing darts"),
			new QuestReward("Access to desert mining camp"),
			new QuestReward("mithril and adamantite rocks."),
		};
	}

}
