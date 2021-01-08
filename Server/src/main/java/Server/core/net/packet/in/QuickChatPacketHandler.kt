package core.net.packet.`in`

import core.game.interaction.QCRepository
import core.game.node.entity.player.Player
import core.game.system.SystemLogger
import core.game.world.GameWorld
import core.net.packet.IncomingPacket
import core.net.packet.IoBuffer

/**
 * Decodes the quick chat packet
 * @author Ceikry
 */
/**
 * There's different varieties of the quick chat packet.
 * Standard, which is only 3 bytes and has no string replacement.
 * Single-replacement, which is 5 bytes and has one string replacement. The last 3 bytes can and should be converted to a Short.
 * Double-replacement, which is 7 bytes and has two string replacements.
 * It ALWAYS sends a 0 as the first byte in a packet, so it can be discarded.
 * The second byte will either be 0,1,2,3. This is the number 256 is to be multiplied by.
 * The third byte is the index. This can be negative. This number is added to the multiple of 256 determined by the previous byte to get the actual id of the cache file holding the quick chat message.
 * In a single replacement, you then have a short (which is 3 bytes in length) that will tell you either the item ID or the index of the selection on the menu.
 * In a double replacement, you would have something like 0 1 0 3 instead as the proceeding 4 bytes. The 0s are spacers and the 1 and 3 are the selection indexes for the menus.
 */
class QuickChatPacketHandler : IncomingPacket {
    override fun decode(player: Player?, opcode: Int, buffer: IoBuffer?) {
        buffer ?: return
        val x = buffer.toByteBuffer()

        val packetType = when(x.array().size){
                3,4 -> QCPacketType.STANDARD
                5 -> QCPacketType.SINGLE
                7 -> QCPacketType.DOUBLE
                else -> QCPacketType.UNHANDLED.also { SystemLogger.log("UNHANDLED QC PACKET TYPE~~~~~~~~~~~~~~~~~~~~~~~~~~") }
        }

        buffer.get() //always 0, discarded
        val multiplier: Int = buffer.get()
        val offset: Int = buffer.get()
        var selection_a_index = -1
        var selection_b_index = -1

        when(packetType){
            QCPacketType.SINGLE -> {
                selection_a_index = buffer.short
            }
            QCPacketType.DOUBLE -> {
                buffer.get() //discard
                selection_a_index = buffer.get()
                buffer.get() //discard
                selection_b_index = buffer.get()
            }
            QCPacketType.UNHANDLED -> SystemLogger.log("Unhandled packet type, skipping remaining buffer contents.")
        }


        //Prints the values of each byte in the buffer to server log
        //If the world is in dev mode
        if(GameWorld.settings?.isDevMode == true) {
            SystemLogger.log("Begin QuickChat Packet Buffer Dump---------")
            SystemLogger.log("Packet Type: ${packetType.name}")
            x?.array()?.forEach {
                SystemLogger.log("$it")
            }
            SystemLogger.log("End QuickChat Packet Buffer Dump-----------")
        }

        QCRepository.sendQC(player,multiplier,offset,packetType,selection_a_index,selection_b_index)
    }
}

enum class QCPacketType{
    STANDARD,
    SINGLE,
    DOUBLE,
    UNHANDLED
}