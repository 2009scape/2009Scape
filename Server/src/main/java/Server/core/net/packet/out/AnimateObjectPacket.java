package core.net.packet.out;

import core.game.node.entity.player.Player;
import core.game.node.object.GameObject;
import core.game.world.map.Location;
import core.game.world.update.flag.context.Animation;
import core.net.packet.IoBuffer;
import core.net.packet.OutgoingPacket;
import core.net.packet.context.AnimateObjectContext;

/**
 * Represents the packet used to animate an object.
 * @author Vexia
 * @date 10/11/2013
 */
public class AnimateObjectPacket implements OutgoingPacket<AnimateObjectContext> {

	/**
	 * Writes the packet.
	 * @param buffer The buffer.
	 */
	public static IoBuffer write(IoBuffer buffer, Animation animation) {
		GameObject object = animation.getObject();
		Location l = object.getLocation();
		buffer.put(20);
		buffer.putS((l.getChunkOffsetX() << 4) | (l.getChunkOffsetY() & 0x7));
		buffer.putS((object.getType() << 2) + (object.getRotation() & 0x3));
		buffer.putLEShort(animation.getId());
		return buffer;
	}

	@Override
	public void send(AnimateObjectContext context) {
		Player player = context.getPlayer();
		GameObject object = context.getAnimation().getObject();
		IoBuffer buffer = write(UpdateAreaPosition.getBuffer(player, object.getLocation().getChunkBase()), context.getAnimation());
		player.getSession().write(buffer);
	}
}
