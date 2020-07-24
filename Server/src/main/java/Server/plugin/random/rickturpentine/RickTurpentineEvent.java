package plugin.random.rickturpentine;

import java.nio.ByteBuffer;

import plugin.ame.AntiMacroDialogue;
import plugin.ame.AntiMacroEvent;
import plugin.dialogue.DialoguePlugin;
import core.game.node.entity.npc.drop.DropFrequency;
import core.game.node.entity.player.Player;
import core.game.node.item.ChanceItem;
import core.game.node.item.Item;
import core.game.world.map.Location;
import core.plugin.PluginManager;
import core.plugin.InitializablePlugin;
import core.tools.RandomFunction;
import core.game.content.ItemNames;

/**
 * Handles the rick turpentine anti macro event.
 * @author Vexia
 */
@InitializablePlugin
public final class RickTurpentineEvent extends AntiMacroEvent {

	/**
	 * The name of the event.
	 */
	public static final String NAME = "rick turpentine";

	/**
	 * Constructs a new {@code RickTurpentineEvent} {@code Object}.
	 */
	public RickTurpentineEvent() {
		super(NAME, true, false);
	}

	@Override
	public void save(ByteBuffer buffer) {

	}

	@Override
	public void parse(ByteBuffer buffer) {

	}

	@Override
	public boolean start(Player player, boolean login, Object... args) {
		final RickTurpentineNPC npc = new RickTurpentineNPC(2476, player.getLocation(), this, player);
		npc.init();
		super.init(player);
		return true;
	}

	@Override
	public AntiMacroEvent create(Player player) {
		RickTurpentineEvent event = new RickTurpentineEvent();
		event.player = player;
		return event;
	}

	@Override
	public void register() {
		PluginManager.definePlugin(new RickTurpentineDialogue());
	}

	@Override
	public Location getSpawnLocation() {
		return null;
	}

	@Override
	public void configure() {

	}

	@Override
	public String getGenderPrefix(boolean male) {
		return male ? "milord" : "milady";
	}

	/**
	 * Handles the rick turpnetine dialogue.
	 * @author Vexia
	 */
	public static final class RickTurpentineDialogue extends AntiMacroDialogue {

		/**
		 * The random items recieved from rick turpnetine.
		 */
		private static final ChanceItem[] ITEMS = new ChanceItem[] {
				new ChanceItem(ItemNames.COINS, 1, 640, DropFrequency.COMMON),
				new ChanceItem(ItemNames.SPINACH_ROLL, 1, 1, DropFrequency.COMMON),
				new ChanceItem(ItemNames.KEBAB_1971, 1, 1, DropFrequency.COMMON),
				new ChanceItem(ItemNames.UNCUT_SAPPHIRE_1623, 1, 1, DropFrequency.UNCOMMON),
				new ChanceItem(ItemNames.UNCUT_EMERALD_1621, 1, 1, DropFrequency.UNCOMMON),
				new ChanceItem(ItemNames.UNCUT_RUBY_1619, 1, 1, DropFrequency.UNCOMMON),
				new ChanceItem(ItemNames.UNCUT_DIAMOND, 1, 1, DropFrequency.UNCOMMON),
				new ChanceItem(ItemNames.COSMIC_TALISMAN, 1, 1, DropFrequency.UNCOMMON),
				new ChanceItem(ItemNames.TOOTH_HALF_OF_KEY, 1, 1, DropFrequency.RARE)};

		/**
		 * Constructs a new {@code RickTurpentineDialogue} {@code Object}.
		 */
		public RickTurpentineDialogue() {
			/**
			 * empty.
			 */
		}

		/**
		 * Constructs a new {@code RickTurpentineDialogue} {@code Object}.
		 * @param player the player.
		 */
		public RickTurpentineDialogue(Player player) {
			super(player);
		}

		@Override
		public DialoguePlugin newInstance(Player player) {
			return new RickTurpentineDialogue(player);
		}

		@Override
		public boolean open(Object... args) {
			if (super.open(args)) {
				npc("Today is your lucky day, sirrah!", "I am donating to the victims of crime to atone for my", "past actions!");
			} else {
				return true;
			}
			return true;
		}

		@Override
		public boolean handle(int interfaceId, int buttonId) {
			Item item = RandomFunction.getChanceItem(ITEMS).getRandomItem();
			if (item.getId() == ItemNames.TOOTH_HALF_OF_KEY && RandomFunction.randomSign(1) <= 0) {
				item = new Item(ItemNames.LOOP_HALF_OF_KEY);
			}

			player.getInventory().add(item, player);
			wave(null);
			return true;
		}

		@Override
		public int[] getIds() {
			return new int[] { 2476 };
		}

	}
}
