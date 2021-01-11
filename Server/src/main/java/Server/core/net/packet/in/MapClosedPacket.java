package core.net.packet.in;

import core.game.node.entity.player.Player;
import core.net.packet.IncomingPacket;
import core.net.packet.IoBuffer;

public class MapClosedPacket implements IncomingPacket {
    @Override
    public void decode(Player player, int opcode, IoBuffer buffer) {
        //This buffer contains an int that is actually a bunch of booleans bitshifted into specific positions. No clue what the use might be.
    }
}
