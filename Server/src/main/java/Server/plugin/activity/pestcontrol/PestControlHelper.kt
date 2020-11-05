package plugin.activity.pestcontrol;

import core.game.node.entity.player.Player;
import core.game.world.map.Location;
import core.game.world.map.zone.ZoneBorders;

import java.util.Arrays;
import java.util.List;

public class PestControlHelper {

    public enum BoatInfo
    {
        NOVICE(new ZoneBorders(2660, 2638, 2664, 2644), new ZoneBorders(2657, 2637, 2657, 2643), 14315),
        INTERMEDIATE(new ZoneBorders(2638, 2642, 2641, 2647), new ZoneBorders(2644, 2642, 2644, 2646), 25631),
        VERTERAN(new ZoneBorders(2632, 2649, 2635, 2654), new ZoneBorders(2639, 2652, 2638, 2655), 25632);

        BoatInfo(ZoneBorders boatBorder, ZoneBorders outsideBoatBorder, int ladderId)
        {
            this.boatBorder = boatBorder;
            this.outsideBoatBorder = outsideBoatBorder;
            this.ladderId = ladderId;
        }

        public ZoneBorders boatBorder;
        public ZoneBorders outsideBoatBorder;
        public int ladderId;
    }

    public static List<Integer> GATE_ENTRIES = Arrays.asList(14233, 14235);
    public static List<Integer> PORTAL_ENTRIES = Arrays.asList(PCPortalNPC.portalIds);

    public static final Location PestControlIslandLocation = Location.create(2659, 2649, 0);
    public static final Location PestControlIslandLocation2 = Location.create(2648, 2648, 0);

    public static boolean isInPestControlInstance(Player p)
    {
        return p.getAttribute("pc_zeal") != null;
    }
    public static boolean landerContainsLoc(Location l)
    {
        for (BoatInfo i : BoatInfo.values())
            if (i.boatBorder.insideBorder(l))
                return true;
        return false;
    }
    public static boolean outsideGangplankContainsLoc(Location l)
    {
        for (BoatInfo i : BoatInfo.values())
            if (i.outsideBoatBorder.insideBorder(l))
                return true;
        return false;
    }
    public static boolean landerContainsLoc2(Location l)
    {
        for (BoatInfo n : BoatInfo.values())
            if (n.boatBorder.insideBorder(l))
                return true;
        return false;
    }
    public static boolean outsideGangplankContainsLoc2(Location l)
    {
        for (BoatInfo n : BoatInfo.values())
            if (n.outsideBoatBorder.insideBorder(l))
                return true;
        return false;
    }

    public static PestControlSession getMyPestControlSession(Player p)
    {
        return p.getExtension(PestControlSession.class);
    }


}
