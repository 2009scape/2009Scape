package org.crandor.net.packet.in;

import org.crandor.game.node.entity.player.Player;
import org.crandor.game.system.monitor.PlayerMonitor;
import org.crandor.game.system.task.Pulse;
import org.crandor.game.world.GameWorld;
import org.crandor.game.world.PulseRunner;
import org.crandor.game.world.update.flag.context.ChatMessage;
import org.crandor.game.world.update.flag.player.ChatFlag;
import org.crandor.net.amsc.MSPacketRepository;
import org.crandor.net.amsc.WorldCommunicator;
import org.crandor.net.packet.IncomingPacket;
import org.crandor.net.packet.IoBuffer;
import org.crandor.tools.StringUtils;

/**
 * Represents the incoming chat packet.
 * @author Emperor
 */
public class ChatPacket implements IncomingPacket {

	@Override
	public void decode(final Player player, int opcode, IoBuffer buffer) {
		try {
			final int effects = buffer.getShort();
			final int numChars = buffer.getSmart();
			final String message = StringUtils.decryptPlayerChat(buffer, numChars);
			if (player.getDetails().isMuted()) {
				player.getPacketDispatch().sendMessage("You have been " + (player.getDetails().isPermMute() ? "permanently" : "temporarily") + " muted due to breaking a rule.");
				return;
			}
			if (message.startsWith("/") && player.getCommunication().getClan() != null) {
				StringBuilder sb = new StringBuilder(message);
				sb.append(" => ").append(player.getName()).append(" (owned by ").append(player.getCommunication().getClan().getOwner()).append(")");
				String m = sb.toString();
				player.getMonitor().log(m.replace(m.charAt(0), ' ').trim(), PlayerMonitor.CLAN_CHAT_LOG);
				if (WorldCommunicator.isEnabled()) {
					MSPacketRepository.sendClanMessage(player, message.substring(1));
				} else {
					player.getCommunication().getClan().message(player, message.substring(1));
				}
				return;
			}
			player.getMonitor().log(message, PlayerMonitor.PUBLIC_CHAT_LOG);
			GameWorld.Pulser.submit(new Pulse(1, player) {
				@Override
				public boolean pulse() {
					player.getUpdateMasks().register(new ChatFlag(new ChatMessage(player, message, effects, numChars)));
					return true;
				}
			});
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

}