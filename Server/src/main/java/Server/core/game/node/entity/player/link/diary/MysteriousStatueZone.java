package core.game.node.entity.player.link.diary;

import core.game.node.entity.Entity;
import core.game.node.entity.player.Player;
import core.game.world.map.Location;
import core.game.world.map.zone.MapZone;
import core.game.world.map.zone.ZoneBorders;
import core.game.world.map.zone.ZoneBuilder;
import core.plugin.InitializablePlugin;
import core.plugin.Plugin;
import core.tools.Vector3d;

@InitializablePlugin
public class MysteriousStatueZone extends MapZone implements Plugin<Object> {
    private final static Vector3d origin = new Vector3d(2740.5, 3490.5, 0);
    private final static Vector3d n = new Vector3d(0, 0, 1);

    public MysteriousStatueZone() {
        super("mysterious-statue", true);
    }

    @Override
    public boolean enter(Entity e) {
        if (e instanceof Player) {
            //System.out.println("enter");
        }
        return true;
    }

    @Override
    public boolean leave(Entity e, boolean logout) {
        if (e instanceof Player) {
            //System.out.println("leave");
            e.asPlayer().removeAttribute("diary:seers:statue-walk-start");
        }
        return true;
    }

    @Override
    public void locationUpdate(Entity e, Location last) {
        if (e instanceof Player) {
            Player player = e.asPlayer();
            //System.out.println("update - " + player.getLocation().toString());
            if (!player.getWalkingQueue().isRunning() && !player.getSettings().isRunToggled()) {
                if (player.getAttribute("diary:seers:statue-walk-start") != null) {
                    Vector3d start = player.getAttribute("diary:seers:statue-walk-start");
                    Vector3d a = player.getAttribute("diary:seers:statue-walk-a");
                    Vector3d b = new Vector3d(player.getLocation()).sub(origin);

                    double angle_a_b = Vector3d.signedAngle(a, b, n) * 360. / 2 / 3.14159265355;

                    /* player.getWalkingQueue().isMoving() doesn't work right, or at least does not do what name implies it should
                    if (!player.getWalkingQueue().isMoving()) {
                        System.out.println("removing, not moving");
                        player.removeAttribute("diary:seers:statue-walk-start");
                    }*/
                    if (angle_a_b >= 0) {
                        //System.out.println("removing, not going clockwise");
                        player.removeAttribute("diary:seers:statue-walk-start");
                    }
                    if (b.epsilonEquals(start, .001)) {
                        player.getAchievementDiaryManager().finishTask(player, DiaryType.SEERS_VILLAGE, 0, 1);
                        //System.out.println("removing, finished task");
                        player.removeAttribute("diary:seers:statue-walk-start");
                    }

                    player.setAttribute("diary:seers:statue-walk-a", b);
                } else {
                    //System.out.println("started");
                    Vector3d start = new Vector3d(player.getLocation()).sub(origin);
                    player.setAttribute("diary:seers:statue-walk-start", start);
                    player.setAttribute("diary:seers:statue-walk-a", start);
                }
            } else {
                //System.out.println("removing, running or outside");
                player.removeAttribute("diary:seers:statue-walk-start");
            }
        }
    }

    @Override
    public void configure() {
        register(new ZoneBorders(2739, 3489, 2742, 3492, 0));
    }

    @Override
    public Plugin<Object> newInstance(Object arg) throws Throwable {
        ZoneBuilder.configure(this);
        return this;
    }

    @Override
    public Object fireEvent(String identifier, Object... args) {
        return null;
    }
}
