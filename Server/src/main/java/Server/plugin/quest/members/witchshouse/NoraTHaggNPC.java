package plugin.quest.members.witchshouse;

import core.game.node.entity.Entity;
import core.game.node.entity.combat.CombatSwingHandler;
import core.game.node.entity.combat.DeathTask;
import core.game.node.entity.npc.AbstractNPC;
import core.game.node.entity.player.Player;
import core.game.system.task.Pulse;
import core.game.world.GameWorld;
import core.game.world.map.Direction;
import core.game.world.map.Location;
import core.game.world.update.flag.context.Animation;
import core.game.world.update.flag.context.Graphics;
import core.net.packet.PacketRepository;
import core.net.packet.context.MinimapStateContext;
import core.net.packet.out.MinimapState;
import core.plugin.InitializablePlugin;
import core.plugin.Plugin;

import java.util.List;

/**
 * Nora T Hagg NPC
 * @author Ethan, cleaned up by ceik
 * @date y'know
 */

@InitializablePlugin
public class NoraTHaggNPC extends AbstractNPC {

    boolean walkdir;

    public NoraTHaggNPC() {
        super(896, Location.create(2904, 3463, 0));
    }
    private Location getRespawnLocation() {
        return Location.create(2900, 3473, 0);
    }

    private NoraTHaggNPC(int id, Location location) {
        super(id, location);
    }

    @Override
    public Plugin<Object> newInstance(Object arg) throws Throwable {
        this.configure();
        init();
        return super.newInstance(arg);
    }

    private boolean canTeleport(Entity t) {
        int playerX = t.getLocation().getX();
        int npcX = getLocation().getX();
        int[] sectors = { 2904, 2907, 2910, 2914, 2918, 2922, 2926, 2928 };
        for (int i = 0; i < sectors.length -1; i++) {
            if (npcX >= sectors[i] && npcX <= sectors[i+1] && playerX >= sectors[i] && playerX <= sectors[i+1]) {
                if (playerX < npcX && getDirection() == Direction.WEST || playerX > npcX && getDirection() == Direction.EAST) {
                    return true;
                }
                if (playerX == npcX) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public AbstractNPC construct(int id, Location location, Object... objects) {
        return new NoraTHaggNPC(id, location);
    }

    @Override
    public int[] getIds() {
        return new int[] { 896 };
    }


    @Override
    public int getWalkRadius() {
        return 50;
    }


    public void configure() {
        super.configure();
        setWalks(false);
        setPathBoundMovement(true);
    }

    private void sendTeleport(final Player player) {
        player.lock();
        GameWorld.Pulser.submit(new Pulse(1) {
            int delay = 0;

            @Override
            public boolean pulse() {
                if (delay == 0) {
                    face(player);
                    player.getInventory().remove(WitchsHousePlugin.BALL);
                    player.getInventory().remove(WitchsHousePlugin.KEY);
                    player.getInventory().remove(WitchsHousePlugin.DOOR_KEY);
                    sendChat("Stop! Thief!");
                    player.getPacketDispatch().sendMessage("You've been spotted by the witch.");
                    player.graphics(new Graphics(110, 100));
                    PacketRepository.send(MinimapState.class, new MinimapStateContext(player, 2));
                } else if (delay == 2) {
                    sendChat("Klarata... Seppteno... Valkan!");
                    face(null);
                } else if (delay == 4) {
                    player.getProperties().setTeleportLocation(Location.create(getRespawnLocation()));
                    PacketRepository.send(MinimapState.class, new MinimapStateContext(player, 0));
                    face(null);
                    player.unlock();
                    return true;
                }
                delay++;
                return false;
            }
        });
    }

    @Override
    public void tick() {
        super.tick();
        List<Player> players = getViewport().getCurrentPlane().getPlayers();
        if(getLocation().getX() == 2930){
            walkdir = false;
        } else if(getLocation().getX() == 2904){
            walkdir = true;
        }
        for (Player player : players) {
            if (player == null || !player.isActive() || player.getLocks().isInteractionLocked() || DeathTask.isDead(player) || !canTeleport(player) || !CombatSwingHandler.isProjectileClipped(this, player, false)) {
                continue;
            }
            animate(new Animation(5803));
            sendTeleport(player);
            player.getPacketDispatch().sendMessage("" + getLocation() + " matches? " + (getLocation().getX() == 2904));
        }
        if (location.getX() != 2930 && walkdir) {
            this.getWalkingQueue().reset();
            this.getWalkingQueue().addPath(location.getX() + 1,3463,true);
        } else if (location.getX() != 2904 && !walkdir) {
            this.getWalkingQueue().reset();
            this.getWalkingQueue().addPath(location.getX() - 1,3463,true);
        }
    }

}
