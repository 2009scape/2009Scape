package core.net.packet.in;

import core.game.component.Component;
import core.game.component.ComponentPlugin;
import core.game.container.Container;
import plugin.dialogue.DialogueAction;
import core.game.interaction.Option;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.info.login.LoginConfiguration;
import core.game.node.entity.player.link.request.assist.AssistSession;
import core.game.node.item.Item;
import core.game.system.task.Pulse;
import core.game.world.GameWorld;
import core.net.packet.IncomingPacket;
import core.net.packet.IoBuffer;

import java.util.List;

/**
 * The incoming reward button packet.
 * @author Emperor
 */
public class ActionButtonPacket implements IncomingPacket {

	@Override
	public void decode(final Player player, int opcode, IoBuffer buffer) {
		if (player == null) {
			return;
		}
		int[] args = getArguments(player, buffer);
		if (args == null || (buffer.opcode() != 76 && args[0] != 106 && args[0] != 108 && args[0] != 110 && args[0] != 646 && player.getLocks().isComponentLocked() && player.getExtension(AssistSession.class) == null)) {
			player.debug("Check this code in reward button packet, where args arent null && opcode !=");
			return;
		}
		int componentId = args[0];
		int buttonId = args[1];
		int slot = args[2];
		int itemId = args[3];
		player.debug("Component=" + componentId + ", button=" + buttonId + ", slot=" + slot + ", item=" + itemId + ", opcode=" + buffer.opcode());
		if (player.getDialogueInterpreter().getDialogue() != null && buffer.opcode() != 132 && componentId != 64) {
			player.getDialogueInterpreter().close();
		}
		if (player.getLocks().isComponentLocked()) {
			return;
		}
		if (itemId > -1 && slot > -1) {
			Container container = getContainer(player, componentId);
			if (container != null) {
				handleItemInteraction(player, buffer.opcode(), itemId, slot, container);
				return;
			}
		}
		if (player.getZoneMonitor().clickButton(componentId, buttonId, slot, itemId, opcode)) {
			return;
		}
		Component c = player.getInterfaceManager().getComponent(componentId);
		if (c == null || c.isHidden()) {
			player.debug("Component " + c + " wasn't opened in interface manager.");
			return;
		}
		ComponentPlugin plugin = c.getPlugin();
		if (plugin != null) {
			player.debug("Component plugin = " + plugin.getClass().getSimpleName());
			plugin.handle(player, c, buffer.opcode(), buttonId, slot, itemId);
		}
	}

	/**
	 * Gets the container for the component id.
	 * @param player The player.
	 * @param componentId The component id.
	 * @return The container.
	 */
	private Container getContainer(Player player, int componentId) {
		switch (componentId) {
		case 149:
			return player.getInventory();
		}
		return null;
	}

	/**
	 * Gets the arguments for the reward button.
	 * @param player The player.
	 * @param buffer The buffer.
	 * @return The arguments [component, button, slot, item]
	 */
	private static int[] getArguments(final Player player, IoBuffer buffer) {
		int data = -1;
		int componentId = -1;
		int buttonId = -1;
		int itemId = -1;
		int slot = -1;
		switch (buffer.opcode()) {
		case 81:
			//System.out.println("Using action handler81");
			slot = buffer.getShortA();
			itemId = buffer.getShort();
			buttonId = buffer.getShort();
			componentId = buffer.getShort();
			break;
		case 156:
			//System.out.println("Using action handler156");
			slot = buffer.getLEShortA();
			itemId = buffer.getShortA();
			data = buffer.getLEInt();
			componentId = data >> 16;
			buttonId = data & 0xffff;
			break;
		case 55:
			//System.out.println("Using action handler55");
			itemId = buffer.getLEShort();
			slot = buffer.getShortA();
			data = buffer.getIntA();
			componentId = data >> 16;
			buttonId = data & 0xffff;
			break;
		case 153:
			//System.out.println("Using action handler153");
			data = buffer.getLEInt();
			slot = buffer.getLEShort();
			itemId = buffer.getLEShort();
			componentId = (data >> 16) & 0xFFFF;
			buttonId = data & 0xFFFF;
			break;
		case 161:
			//System.out.println("Using action handler161");
			data = buffer.getLEInt();
			itemId = buffer.getLEShortA();
			slot = buffer.getLEShortA();
			componentId = (data >> 16) & 0xFFFF;
			buttonId = data & 0xFFFF;
			break;
		case 135:
			//System.out.println("Using action handler135");
			itemId = buffer.getShortA();
			slot = buffer.getShortA();
			data = buffer.getIntB();
			componentId = (data >> 16) & 0xFFFF;
			buttonId = data & 0xFFFF;
			break;
		case 155: //Interface options
		case 196:
		case 124:
		case 199:
		case 234:
		case 168:
		case 166:
		case 64:
		case 53:
		case 9:
			//System.out.println("Using action handler155-9");
			data = buffer.getInt();
			slot = buffer.getShort();
			componentId = (data >> 16) & 0xFFFF;
			buttonId = data & 0xFFFF;
			break;
		case 132: //Dialogue options
			//System.out.println("Using action handler132");
			data = buffer.getIntA();
			slot = buffer.getLEShort();
			componentId = data >> 16;
			buttonId = data & 0xffff;
			if (player.getDialogueInterpreter().getDialogue() == null && player.getDialogueInterpreter().getDialogueStage() == null) {
				player.getInterfaceManager().closeChatbox();
				List<DialogueAction> actions = player.getDialogueInterpreter().getActions();
				if (actions.size() > 0) {
					DialogueAction action = actions.get(0);
					action.handle(player, buttonId);
					actions.remove(action);
					actions.clear();
				}
				break;
			}
			player.getDialogueInterpreter().handle(componentId, buttonId);
			break;
		case 133:
			//System.out.println("Using action handler133");
			data = buffer.getLEInt();
			componentId = (data >> 16) & 0xFFFF;
			buttonId = data & 0xFFFF;
			slot = buffer.getShort();
			itemId = buffer.getLEShortA();
			break;
		case 206:
			//System.out.println("Using action handler206");
		    itemId = buffer.getShortA();
		    slot = buffer.getLEShort();
		    data = buffer.getLEInt();
		    componentId = (data >> 16) & 0xFFFF;
		    buttonId = data & 0xFFFF;
		    //player.sendMessage("itemId=" + itemId + ", data=" + data  + ", buttonId=" + buttonId + ", compId= " + componentId);
		    break;
		case 230: // Short
		case 180:
		case 10:
			//System.out.println("Using action handler230-10");
			data = buffer.getInt();
			componentId = data >> 16;
			buttonId = data & 0xffff;
			if (buffer.opcode() == 230) {
				slot = buffer.getShort();
			}
			if (componentId == 49) {
				if (player.getDialogueInterpreter().getDialogue() == null && player.getDialogueInterpreter().getDialogueStage() == null) {
					player.setAttribute("chatbox-buttonid",buttonId);
					player.getInterfaceManager().closeChatbox();
					return null;
				}
				player.setAttribute("chatbox-buttonid",buttonId);
				player.getDialogueInterpreter().getDialogue().handle(componentId, buttonId);
			}
			break;
		case 157:
			//System.out.println("Using action handler157");
			itemId = buffer.getLEShort();
			slot = buffer.getLEShort();
			data = buffer.getLEInt();
			componentId = data >> 16;
			buttonId = data & 0xffff;
			break;
		case 39:
			//System.out.println("Using action handler39");
			data = buffer.getInt();
			slot = buffer.getShort();
			componentId = (data >> 16) & 0xFFFF;
			buttonId = data & 0xFFFF;
			break;
		case 128:
			//System.out.println("Using action handler128");
			data = buffer.getInt();
			slot = buffer.getShort();
			componentId = (data >> 16) & 0xFFFF;
			buttonId = data & 0xffff;
			break;
		case 235:
			//System.out.println("Using action handler235");
			data = buffer.getInt();
			slot = buffer.getShort();
			componentId = (data >> 16) & 0xFFFF;
			buttonId = data & 0xffff;
			break;
		case 243:
			//System.out.println("Using action handler243");
			data = buffer.getInt();
			itemId = buffer.getShortA();
			slot = buffer.getLEShortA();
			componentId = data >> 16;
			buttonId = data & 0xffff;
			break;
		case 170:
			//System.out.println("Using action handler170");
			slot = buffer.getLEShort();
			itemId = buffer.getShortA();
			data = buffer.getLEInt();
			componentId = (data >> 16) & 0xFFFF;
			buttonId = data & 0xFFFF;
			break;
		case 145:
			//System.out.println("Using action handler145");
			data = buffer.getIntB();
			itemId = buffer.getShortA();
			slot = buffer.getLEShort();
			componentId = (data >> 16) & 0xFFFF;
			buttonId = data & 0xffff;
			// TODO dialogue with store X.
			break;
		case 127:// 3rd option on send item.
		case 203:// 5th option on send item.
		case 205:// 2 option
		case 211:// 4rth option on send item.
		case 187: // 6th
			//System.out.println("Using action handler127-187");
			data = buffer.getInt();
			slot = buffer.getShort();
			componentId = data >> 16;
			buttonId = data & 0xffff;
			itemId = -1;
			break;
		case 184:
		case 95:
			//System.out.println("Using action handler184-95");
			if (player.getAttribute("logging_in") != null) {
				player.getInterfaceManager().close();
				GameWorld.getPulser().submit(new Pulse(1, player) {
					@Override
					public boolean pulse() {
						player.removeAttribute("logging_in");
						LoginConfiguration.configureGameWorld(player);
						return true;
					}
				});
			}
			player.getInterfaceManager().close();
			if (player.getAttribute("worldMap:viewing") != null) {
				player.removeAttribute("worldMap:viewing");
				player.getPacketDispatch().sendWindowsPane(player.getInterfaceManager().isResizable() ? 746 : 548, 2);
				player.unlock();
			}
			break;
		}
		return new int[] { componentId, buttonId, slot, itemId };
	}

	/**
	 * Handles an item interaction.
	 * @param player The player.
	 * @param opcode The opcode.
	 * @param itemId The item.
	 */
	private static void handleItemInteraction(Player player, int opcode, int itemId, int slot, Container container) {
		if (slot < 0 || slot >= container.capacity()) {
			return;
		}
		Item item = container.get(slot);
		if (item == null || item.getId() != itemId) {
			return;
		}
		int index = 0;
		switch (opcode) {
		case 156: // First option
			index = 0;
			break;
		case 55: // Second option (wield/wear)
			index = 1;
			break;
		case 153: // Third option
			index = 2;
			break;
		case 161: // Fourth option (summon)
			index = 3;
			break;
		case 135: // Fifth option (drop/destroy)
			index = 4;
			break;
		}
		final Option option = item.getInteraction().get(index);
		if (option == null || player.getLocks().isInteractionLocked()) {
			return;
		}
		item.getInteraction().handleItemOption(player, option, container);
	}
}