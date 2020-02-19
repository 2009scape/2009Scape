package org.crandor.net.packet.out;

import org.crandor.net.packet.IoBuffer;
import org.crandor.net.packet.OutgoingPacket;
import org.crandor.net.packet.context.CSConfigContext;

/**
 * The outgoing packet for client script configs.
 * 
 * @author Torchic
 */
public class CSConfigPacket implements OutgoingPacket<CSConfigContext> {//world map

	@Override
	public void send(CSConfigContext context) {

		/*
		 * TranslateBuffer buffer = new TranslateBuffer(header);
		buffer.putLEShort(context.getPlayer().getInterfaceManager().getPacketCount(1));
		buffer.putByteC((byte) context.getValue());
		buffer.putLEShortA(context.getId());
		Repository.send(context.getPlayer(), header, buffer);
		 */

		IoBuffer buffer = new IoBuffer(65);
		buffer.putLEShort(context.getPlayer().getInterfaceManager().getPacketCount(1));
		buffer.putC((byte) context.getValue());
		buffer.putLEShortA(context.getId());
		context.getPlayer().getDetails().getSession().write(buffer);
	}
}