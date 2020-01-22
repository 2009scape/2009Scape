package plugin.skill.hunter;

import org.crandor.cache.def.impl.ItemDefinition;
import org.crandor.game.component.Component;
import org.crandor.game.component.ComponentDefinition;
import org.crandor.game.component.ComponentPlugin;
import org.crandor.game.content.dialogue.DialogueInterpreter;
import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.interaction.OptionHandler;
import org.crandor.game.node.Node;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.item.Item;
import org.crandor.net.packet.PacketRepository;
import org.crandor.net.packet.context.ContainerContext;
import org.crandor.net.packet.out.ContainerPacket;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.plugin.Plugin;
import org.crandor.plugin.PluginManager;
import org.crandor.tools.RandomFunction;

/**
 * Handles the imp box.
 * @author Taylor
 */
@InitializablePlugin
public class ImpBoxPlugin extends OptionHandler {

	/**
	 * The item ids.
	 */
	private static final int[] IDS = new int[] { 10028, 10027 };

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		PluginManager.definePlugin(new ImpInterfaceHandler(null));
		for (int id : IDS) {
			ItemDefinition.forId(id).getConfigurations().put("option:bank", this);
			ItemDefinition.forId(id).getConfigurations().put("option:talk-to", this);
		}
		PluginManager.definePlugin(new ImpBoxDialogue());
		return this;
	}

	@Override
	public boolean handle(Player player, Node node, String option) {
		switch (option) {
		case "bank":
			Component component = new Component(478);
			component.setPlugin(new ImpInterfaceHandler((Item) node));
			player.getInterfaceManager().open(component);
			PacketRepository.send(ContainerPacket.class, new ContainerContext(player, 478, 61, 91, player.getInventory(), true));
			break;
		case "talk-to":
			player.getDialogueInterpreter().open("imp-box");
			break;
		}
		return true;
	}

	@Override
	public boolean isWalk() {
		return false;
	}

	/**
	 * Handles talk-to dialogue on the imp box.
	 * @author Taylor
	 */
	public static class ImpBoxDialogue extends DialoguePlugin {

		/**
		 * The messages to send.
		 */
		private static final String[] MESSAGES = { "Let me outa here!", "Errgghh..", "Well look who it is.", "What are you looking at?" };

		/**
		 * Constructs a new {@code ImpBoxDialogue} {@code Object}.
		 */
		public ImpBoxDialogue() {
			/**
			 * empty.
			 */
		}

		/**
		 * Constructs a new {@code ImpBoxDialogue} {@code Object}.
		 * @param player The player.
		 */
		public ImpBoxDialogue(Player player) {
			super(player);
		}

		@Override
		public DialoguePlugin newInstance(Player player) {
			return new ImpBoxDialogue(player);
		}

		@Override
		public boolean open(Object... args) {
			interpreter.sendDialogues(708, FacialExpression.FURIOUS, MESSAGES[RandomFunction.getRandom(MESSAGES.length - 1)]);
			return true;
		}

		@Override
		public boolean handle(int interfaceId, int buttonId) {
			end();
			return true;
		}

		@Override
		public int[] getIds() {
			return new int[] { DialogueInterpreter.getDialogueKey("imp-box") };
		}

	}

	/**
	 * Handles the imp interface.
	 * @author Taylor
	 */
	public class ImpInterfaceHandler extends ComponentPlugin {

		/**
		 * The message to show when the imp is gone.
		 */
		private static final String FINISHING_MESSAGE = "The imp teleports away, taking the item to your bank account.";

		/**
		 * The box the player is using.
		 */
		private Item box;

		/**
		 * Constructs a new {@code ImpInterfaceHandler} {@code Object}.
		 * @param box The box the player is using.
		 */
		public ImpInterfaceHandler(Item box) {
			this.box = box;
		}

		@Override
		public Plugin<Object> newInstance(Object arg) throws Throwable {
			ComponentDefinition.forId(478).setPlugin(this);
			return this;
		}

		@Override
		public boolean handle(Player player, Component component, int opcode, int button, int slot, int itemId) {
			Item item = player.getInventory().get(slot);
			if (item != null) {
				if (player.getBank().canAdd(item) && item.getId() != box.getId()) {
					player.getDialogueInterpreter().close();
					player.getInventory().remove(item);
					player.getBank().add(item);
					PacketRepository.send(ContainerPacket.class, new ContainerContext(player, 478, 61, 91, player.getInventory(), true));
					if (box.getId() == IDS[1]) {
						int boxSlot = player.getInventory().getSlot(box);
						player.getInventory().replace((box = new Item(IDS[0])), boxSlot);
					} else if (box.getId() == IDS[0]) {
						int boxSlot = player.getInventory().getSlot(box);
						player.getInventory().replace(new Item(10025), boxSlot);
						player.getInterfaceManager().close(component);
						player.sendMessage(FINISHING_MESSAGE);
					}
				}
			} else {
				player.sendMessage("You cannot add this item to your bank.");
				return false;
			}
			return true;
		}
	}
}
