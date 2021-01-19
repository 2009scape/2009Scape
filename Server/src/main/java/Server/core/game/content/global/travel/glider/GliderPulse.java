package core.game.content.global.travel.glider;

import core.game.component.Component;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.diary.DiaryType;
import core.game.system.task.Pulse;
import core.net.packet.PacketRepository;
import core.net.packet.context.CameraContext;
import core.net.packet.context.CameraContext.CameraType;
import core.net.packet.context.MinimapStateContext;
import core.net.packet.out.CameraViewPacket;
import core.net.packet.out.MinimapState;

/**
 * Represents the pulse used for a glider.
 *
 * @author 'Vexia
 */
public final class GliderPulse extends Pulse {

    /**
     * Represents the player.
     */
    private final Player player;

    /**
     * Represents the glider.
     */
    private final Gliders glider;

    /**
     * Represents the count.
     */
    private int count;

    /**
     * Constructs a new {@code GliderPulse.java} {@Code Object}
     *
     * @param delay
     */
    public GliderPulse(int delay, Player player, Gliders glider) {
        super(delay, player);
        this.player = player;
        this.glider = glider;
        player.lock();
    }

    @Override
    public boolean pulse() {
        final boolean crash = glider == Gliders.LEMANTO_ADRA;
        if (count == 1) {
            player.varpManager.get(153).setVarbit(0,glider.getConfig()).send(player);
            PacketRepository.send(MinimapState.class, new MinimapStateContext(player, 2));
        } else if (count == 2 && crash) {
            PacketRepository.send(CameraViewPacket.class, new CameraContext(player, CameraType.SHAKE, 4, 4, 1200, 4, 4));
            player.getPacketDispatch().sendMessage("The glider almost gets blown from its path as it withstands heavy winds.");
        }
        if (count == 3) {
            player.getInterfaceManager().openOverlay(new Component(115));
        } else if (count == 4) {
            player.unlock();
            player.getProperties().setTeleportLocation(glider.getLocation());
        } else if (count == 5) {
            if (crash) {
                player.getPacketDispatch().sendMessage("The glider becomes uncontrolable and crashes down...");
                PacketRepository.send(CameraViewPacket.class, new CameraContext(player, CameraType.RESET, 0, 0, 0, 0, 0));
            }
            player.getInterfaceManager().closeOverlay();
            player.getInterfaceManager().close();
            PacketRepository.send(MinimapState.class, new MinimapStateContext(player, 0));
            player.varpManager.get(153).setVarbit(0,0).send(player);
            // Use the gnome glider to travel to Karamja
            if (!crash && glider == Gliders.GANDIUS) {
                player.getAchievementDiaryManager().finishTask(player, DiaryType.KARAMJA, 1, 11);
            }
            return true;
        }
        count++;
        return false;
    }
}
