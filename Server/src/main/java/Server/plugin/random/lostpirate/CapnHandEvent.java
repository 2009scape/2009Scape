package plugin.random.lostpirate;

import java.nio.ByteBuffer;

import core.game.content.ItemNames;
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

/**
 * Handles the capn hand anti macro event.
 * @author Vexia
 */
@InitializablePlugin
public final class CapnHandEvent extends AntiMacroEvent {

	/**
	 * The name of the event.
	 */
	public static final String NAME = "capn hand";

	/**
	 * Constructs a new {@code CapnHandEvent} {@code Object}.
	 */
	public CapnHandEvent() {
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
		final CapnHandNPC capn = new CapnHandNPC(2539, player.getLocation(), this, player);
		capn.init();
		super.init(player);
		return true;
	}

	@Override
	public AntiMacroEvent create(Player player) {
		CapnHandEvent event = new CapnHandEvent();
		event.player = player;
		return event;
	}

	@Override
	public void register() {
		PluginManager.definePlugin(new CapnHandDialogue());
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
		return male ? "mister" : "miss";
	}

	/**
	 * Handles the rick turpnetine dialogue.
	 * @author Vexia
	 */
	public static final class CapnHandDialogue extends AntiMacroDialogue {

		/**
		 * The random items recieved.
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
		 * Constructs a new {@code CapnHandDialogue} {@code Object}.
		 */
		public CapnHandDialogue() {
			/**
			 * empty.
			 */
		}

		/**
		 * Constructs a new {@code CapnHandDialogue} {@code Object}.
		 * @param player the player.
		 */
		public CapnHandDialogue(Player player) {
			super(player);
		}

		@Override
		public DialoguePlugin newInstance(Player player) {
			return new CapnHandDialogue(player);
		}

		@Override
		public boolean open(Object... args) {
			if (super.open(args)) {
				npc("My mistake " + event.getGenderPrefix() + ", I was thinking yer be some other", "fella!", "Take this swag, and we'll be saying no more about it!", "Yarrr!");
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
			return new int[] { 2539 };
		}

	}
}
