package core.net.packet.out;

import core.net.packet.IoBuffer;
import core.net.packet.OutgoingPacket;
import core.net.packet.context.DisplayModelContext;

/**
 * Represents the outgoing packet for the displaying of a node model on an
 * interface.
 * @author Emperor
 */
public final class DisplayModel implements OutgoingPacket<DisplayModelContext> {

	@Override
	public void send(DisplayModelContext context) {
		IoBuffer buffer;
		switch (context.getType()) {
		case PLAYER:
			buffer = new IoBuffer(66);
			buffer.putLEShortA(context.getPlayer().getInterfaceManager().getPacketCount(1));
			buffer.putIntA(context.getInterfaceId() << 16 | context.getChildId());
			break;
		case NPC:
			buffer = new IoBuffer(73);
			buffer.putShortA(context.getNodeId());
			buffer.putLEInt((context.getInterfaceId() << 16) | context.getChildId());
			buffer.putLEShort(context.getPlayer().getInterfaceManager().getPacketCount(1));
			break;
		case ITEM:
			buffer = new IoBuffer(50);
			buffer.putInt(context.getAmount());
			buffer.putIntB((context.getInterfaceId() << 16) | context.getChildId());
			buffer.putLEShortA(context.getNodeId());
			buffer.putLEShort(context.getPlayer().getInterfaceManager().getPacketCount(1));
			break;
		case MODEL:
			buffer = new IoBuffer(130);
			buffer.putLEInt(context.getInterfaceId() << 16 | context.getChildId());
			buffer.putLEShortA(context.getPlayer().getInterfaceManager().getPacketCount(1));
			buffer.putShortA(context.getNodeId());
			break;
		default:
			return;
		}
		context.getPlayer().getSession().write(buffer);
	}

}