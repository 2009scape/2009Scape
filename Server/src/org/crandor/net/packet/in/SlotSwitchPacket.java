package org.crandor.net.packet.in;

import org.crandor.game.container.Container;
import org.crandor.game.container.impl.BankContainer;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.item.Item;
import org.crandor.net.packet.IncomingPacket;
import org.crandor.net.packet.IoBuffer;

/**
 * Represents the packet to handle an item slot switch.
 * @author 'Vexia
 */
public class SlotSwitchPacket implements IncomingPacket {

	@Override
	public void decode(Player player, int opcode, IoBuffer buffer) {
		if (buffer.opcode() == 79) {
			int interfaceHash = buffer.getIntB();
			int secondSlot = buffer.getLEShort();
			int interfaceHash2 = buffer.getInt();
			int slot = buffer.getLEShort();
			int interfaceId = interfaceHash >> 16;
			int childId = interfaceHash & 0xFF;
			int withInterfaceId = interfaceHash2 >> 16;
			int withChildId = interfaceHash2 & 0xFF;
			Container container = player.getInventory();
			switch (interfaceId) {
			case 762:
				if (withInterfaceId == 762) {
					if (withChildId == 73) {
						container = player.getBank();
						switchItem(slot, secondSlot, container, player.getBank().isInsertItems(), player);
						player.debug("Switching item [" + slot + ", " + interfaceId + ", " + childId + "] with [" + secondSlot + ", " + withInterfaceId + ", " + withChildId + "]!");
					}
					else {
						int tabIndex = BankContainer.getArrayIndex(withChildId);
		                if (tabIndex > -1) {
		                    secondSlot = tabIndex == 10 ? player.getBank().freeSlot() : player.getBank().getTabStartSlot()[tabIndex] + player.getBank().getItemsInTab(tabIndex);
		                    childId = player.getBank().getTabByItemSlot(slot);
		                    if (secondSlot > slot) {
		                        player.getBank().insert(slot, secondSlot - 1);
		                    } else if (slot > secondSlot) {
		                        player.getBank().insert(slot, secondSlot);
		                    }
		                    player.getBank().increaseTabStartSlots(tabIndex);
		                    player.getBank().decreaseTabStartSlots(childId);
		                    player.getBank().setTabConfigurations();
		                    return;
		                }
					}
					break;
				}
				break;
			default:
				player.debug("Switching item slot [from=" + slot + ", to=" + secondSlot + ", child=" + childId + ", to child=" + withChildId + "].");
				switchItem(slot, secondSlot, container, false, player);
				break;
			}
			return;
		}
		int slot = buffer.getShort();
		int interfaceHash = buffer.getLEInt();
		int secondSlot = buffer.getShortA();
		boolean insert = buffer.get() == 1;
		int interfaceId = interfaceHash >> 16;
		Container container = interfaceId == 762 ? player.getBank() : (interfaceId == 15 || interfaceId == 149 || interfaceId == 763) ? player.getInventory() : null;
		switchItem(slot, secondSlot, container, insert, player);
	}
	
	/**
	 * Switches an item.
	 * @param slot The slot.
	 * @param secondSlot The slot to switch slots with.
	 * @param container The container.
	 * @param insert If inserting should happen.
	 */
	public void switchItem(int slot, int secondSlot, Container container, boolean insert, Player player) {
		if (container == null || slot < 0 || slot >= container.toArray().length || secondSlot < 0 || secondSlot >= container.toArray().length) {
			return;
		}
		
		final Item item = container.get(slot);
		final Item second = container.get(secondSlot);
		if (player.getInterfaceManager().hasChatbox()) {
			player.getInterfaceManager().closeChatbox();
			switchItem(secondSlot,slot,container,insert,player);
			container.refresh();
			return;
		}
		if (item == null) {
			return;
		}
		if (!insert) {
			container.replace(second, slot, false);
			container.replace(item, secondSlot, false);
			if (item != null) {
				item.setIndex(secondSlot);
			}
			if (second != null) {
				second.setIndex(slot);
			}
		} else {
			container.insert(slot, secondSlot, false);
		}
		container.refresh();
	}

}
