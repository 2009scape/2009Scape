package ms.net.event;

import java.nio.ByteBuffer;

import ms.ServerConstants;
import ms.net.IoReadEvent;
import ms.net.IoSession;
import ms.world.WorldDatabase;
import ms.system.util.ByteBufferUtils;
import ms.world.info.CountryFlag;
import ms.world.info.WorldInfo;

/**
 * Handles world registry read events.
 * @author Emperor
 *
 */
public final class RegistryReadEvent extends IoReadEvent {
	
	/**
	 * The string check.
	 */
	private static final String CHECK = "12x4578f5g45hrdjiofed59898";
	//kratos = 666x14x88x28shhhwpwwb&h
	//12x4578f5g45hrdjiofed59898

	/**
	 * Constructs a new {@code RegistryReadEvent} {@code Object}.
	 * @param session The session.
	 * @param buffer The buffer to read.
	 */
	public RegistryReadEvent(IoSession session, ByteBuffer buffer) {
		super(session, buffer);
	}

	@Override
	public void read(IoSession session, ByteBuffer buffer) {
		int worldId = buffer.get() & 0xFF;
		if (buffer.remaining() < 2) {
			queueBuffer(worldId);
			return;
		}
		int revision = buffer.getInt();
		int country = buffer.get() & 0xFF;
		boolean members = buffer.get() == 1;
		boolean pvp = buffer.get() == 1;
		boolean quickChat = buffer.get() == 1;
		boolean lootshare = buffer.get() == 1;
		String activity = ByteBufferUtils.getString(buffer);
		System.out.println("["+ revision + "], country = " + country + ", members = " + members + ", pvp = " + pvp + ", quickChat = " + quickChat + ", lootShare = " + lootshare + ", activity = " + activity);
		for (int i = 0; i < CHECK.length(); i++) {
			if ((char) buffer.get() != CHECK.charAt(i)) {
				session.write(3);
				return;
			}
		}
		if (worldId >= ServerConstants.WORLD_LIMIT) {
			session.write(0);
			return;
		}
		if (WorldDatabase.isActive(worldId)) {
			session.write(2);
			return;
		}
		try {
			WorldInfo info = new WorldInfo(worldId, session.getAddress(), revision, CountryFlag.values()[country], activity, members, pvp, quickChat, lootshare);
			WorldDatabase.register(info).configure(session);
			session.write(1);
		} catch (Throwable t) {
			t.printStackTrace();
			session.write(3);
		}
	}

}