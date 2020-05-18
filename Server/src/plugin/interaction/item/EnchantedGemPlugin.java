package plugin.interaction.item;

import org.crandor.cache.def.impl.ItemDefinition;
import org.crandor.cache.def.impl.NPCDefinition;
import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.global.EnchantedJewellery;
import org.crandor.game.interaction.OptionHandler;
import org.crandor.game.node.Node;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.item.Item;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.plugin.Plugin;

/**
 * Represents the plugin used to handle enchanted jewellery transportation.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class EnchantedGemPlugin extends OptionHandler {

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ItemDefinition.forId(4155).getConfigurations().put("option:check", this);
		return this;
	}

	@Override
	public boolean handle(Player player, Node node, String option) {
		player.sendMessage("You're assigned to kill " + NPCDefinition.forId((player.getSlayer().getTask().getNpcs()[0])).getName().toLowerCase() + "s; only " + player.getSlayer().getAmount() + " more to go.");
		return true;
	}

	@Override
	public boolean isWalk() {
		return false;
	}

	/**
	 * Represents the jewellery dialogue plugin.
	 * @author 'Vexia
	 * @version 1.0
	 */
	public final class JewelleryDialogue extends DialoguePlugin {

		/**
		 * Represents the id to use.
		 */
		public static final int ID = 329128389;

		/**
		 * Represents the enchanted jewellery.
		 */
		private EnchantedJewellery jewellery;

		/**
		 * If the operate option is used.
		 */
		private boolean operate;

		/**
		 * Represents the item instance.
		 */
		private Item item;

		/**
		 * Constructs a new {@code EnchantedJewelleryPlugin} {@code Object}.
		 */
		public JewelleryDialogue() {
			/**
			 * empty.
			 */
		}

		/**
		 * Constructs a new {@code EnchantedJewelleryPlugin} {@code Object}.
		 * @param player the player.
		 */
		public JewelleryDialogue(final Player player) {
			super(player);
		}

		@Override
		public DialoguePlugin newInstance(Player player) {
			return new JewelleryDialogue(player);
		}

		@Override
		public boolean open(Object... args) {
			item = (Item) args[0];
			jewellery = (EnchantedJewellery) args[1];
			operate = args.length > 2 && (Boolean) args[2];
			if (jewellery == EnchantedJewellery.DIGSITE_PENDANT) {
				jewellery.use(player, item, 0, operate);
				return true;
			}
			interpreter.sendOptions("Where would you like to go?", jewellery.getOptions());
			return true;
		}

		@Override
		public boolean handle(int interfaceId, int buttonId) {
			if (player.getInterfaceManager().isOpened()) {
				end();
				return true;
			}
			end();
			jewellery.use(player, item, (buttonId - 1), operate);
			return true;
		}

		@Override
		public int[] getIds() {
			return new int[] { ID };
		}

	}
}
