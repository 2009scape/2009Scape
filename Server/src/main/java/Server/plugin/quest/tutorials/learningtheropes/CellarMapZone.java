package plugin.quest.tutorials.learningtheropes;

import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.game.world.map.Direction;
import core.game.world.map.Location;
import core.game.world.map.build.DynamicRegion;
import core.game.world.map.zone.MapZone;
import core.game.world.update.flag.context.Animation;

/**
 * @author Ethan Kyle Millard <skype:pumpklins>
 * @since Wed, October 10, 2018 @ 1:03 PM
 */
public class CellarMapZone extends MapZone {

    private static final NPC[] NPCS = new NPC[]{
            /** sir vant */
            NPC.create(7938, Location.create(2524, 5005, 0), Direction.NORTH),
            /** goblin*/
            NPC.create(7965, Location.create(2524, 4997, 0), Direction.NORTH),
    };

    public static final CellarMapZone INSTANCE = new CellarMapZone();

    /**
     * The region of the zone.
     */
    private DynamicRegion region;

    /**
     * The base location.
     */
    private Location base;


    /**
     * Constructs a new {@code MapZone} {@code Object}.
     */
    public CellarMapZone() {
        super("Cellar Map Zone", true);
    }

    @Override
    public void configure() {
        region = DynamicRegion.create(10062);
        setRegionBase();
        registerRegion(region.getId());
        setNpcs();
    }

    public void create(Player player) {
        configure();
        player.teleport(getBase().transform(28, 12, 0));
    }

    private void setNpcs() {
        for (NPC n : NPCS) {
            n = NPC.create(n.getId(), n.getLocation(), n.getDirection());
            n.setLocation(base.transform(n.getLocation().getLocalX(), n.getLocation().getLocalY(), 0));
            n.setRespawn(false);
            n.init();
            n.setWalks(false);
            n.animate(new Animation(-1));
        }
    }

    /**
     * Sets the region base.
     */
    private void setRegionBase() {
        if (region != null) {
            setBase(Location.create(region.getBorders().getSouthWestX(), region.getBorders().getSouthWestY(), 0));
        }
    }

    /**
     * Gets the base.
     *
     * @return the base
     */
    public Location getBase() {
        return base;
    }

    /**
     * Sets the base.
     *
     * @param base the base to set.
     */
    public void setBase(Location base) {
        this.base = base;
    }

    public static CellarMapZone get() {
        return INSTANCE;
    }
}
