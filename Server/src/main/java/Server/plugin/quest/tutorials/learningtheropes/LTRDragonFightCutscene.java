package plugin.quest.tutorials.learningtheropes;

import plugin.activity.ActivityPlugin;
import plugin.activity.CutscenePlugin;
import plugin.quest.tutorials.tutorialisland.TutorialStage;
import plugin.skill.Skills;
import core.game.node.entity.combat.ImpactHandler;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.game.node.entity.state.EntityState;
import core.game.system.task.Pulse;
import core.game.world.GameWorld;
import core.game.world.map.Direction;
import core.game.world.map.Location;
import core.game.world.map.build.DynamicRegion;
import core.game.world.map.path.Path;
import core.game.world.map.path.Pathfinder;
import core.game.world.map.zone.ZoneBuilder;
import core.game.world.update.flag.context.Animation;
import core.game.world.update.flag.context.Graphics;
import core.net.packet.PacketRepository;
import core.net.packet.context.CameraContext;
import core.net.packet.out.CameraViewPacket;
import core.plugin.InitializablePlugin;

/**
 * @author Ethan Kyle Millard <skype:pumpklins>
 * @since Mon, October 08, 2018 @ 5:00 PM
 */
@InitializablePlugin
public class LTRDragonFightCutscene extends CutscenePlugin {

    private static final NPC[] NPCS = new NPC[] {
            /** dragon */
            NPC.create(7943, Location.create(2524, 5015, 0), Direction.SOUTH),
            /** sir vant */
            NPC.create(7938, Location.create(2524, 5005, 0), Direction.NORTH),
            /** goblin*/
            NPC.create(7964, Location.create(2522, 5000, 0), Direction.NORTH),
    };

    private static final Animation SIR_VANT_BLOCK = Animation.create(9941);
    private static final Animation SIR_VANT_STAB = Animation.create(9938);
    private static final Animation SIR_VANT_SLASH = Animation.create(9939);
    private static final Animation SIR_VANT_BLOCK_FIRE = Animation.create(9942);
    private static final Animation SIR_VANT_BIG_SLASH = Animation.create(9943);
    private static final Animation DRAGON_ATTACK = Animation.create(9922);
    private static final Animation DRAGON_BLOCK = Animation.create(9923);
    private static final Animation DRAGON_FIRE_BREATH = Animation.create(9919);
    private static final Graphics DRAGON_FIRE_BREATH_GFX = new Graphics(1, 64);
    private static final Animation DRAGON_SLASHED = Animation.create(9921);
    private static final Animation DRAGON_STANDING_UNCUT = Animation.create(9914);
    private static final Animation DRAGON_STANDING_CUT = Animation.create(9915);
    private static final Animation DRAGON_STANDING_WOBBLING = Animation.create(9916);
    private static final Animation DRAGON_STANDING_CUTTING = Animation.create(9917);
    private static final Animation DRAGON_FLYING = Animation.create(9913);
    private static final Animation GOBLIN_FALLING = Animation.create(9965);
    private static final Animation GOBLIN_PLUNDERING = Animation.create(9963);
    private static final Animation GOBLIN_HIT_ON_HEAD= Animation.create(9964);



    private final FightPulse fightPulse = new FightPulse();


    public LTRDragonFightCutscene() {
        this(null);
    }

    public LTRDragonFightCutscene(final Player player) {
        super("ltr:dragon_fight_cs");
        this.player = player;
    }

    @Override
    public ActivityPlugin newInstance(Player p) throws Throwable {
        return new LTRDragonFightCutscene(p);
    }

    @Override
    public Location getSpawnLocation() {
        return getBase().transform(28, 12, 0);
    }

    @Override
    public Location getStartLocation() {
        return getBase().transform(28, 12, 0);
    }

    @Override
    public void register() {
        super.register();
    }

    @Override
    public void open() {
        ZoneBuilder.configure(CellarMapZone.get());
        setNpcs();
        GameWorld.Pulser.submit(fightPulse);
        player.lock();
        player.getLocks().lockMovement(1000000);
        camera(31, 12, -45, 0, 300, 95);
    }


    public final class FightPulse extends Pulse {

        /**
         * Represents the counter.
         */
        private int counter;

        /**
         * Constructs a new {@code FightPulse} {@code Object}.
         */
        public FightPulse() {
            super(1, player);
        }

        @Override
        public boolean pulse() {
            switch (counter++) {
                case 1:
                    System.out.println("Fight begin");
                    getSirVant().getSkills().setStaticLevel(Skills.HITPOINTS, 99);
                    getSirVant().getSkills().setLifepoints(99);
                    getDragon().getSkills().setStaticLevel(Skills.HITPOINTS, 99);
                    getDragon().getSkills().setLifepoints(99);
                    System.out.println("getGoblin().getLocation().getLocalX() = " + getGoblin().getLocation().getLocalX());
                    System.out.println("getGoblin().getLocation().getLocalY() = " + getGoblin().getLocation().getLocalY());
                    break;

                case 4:
                    camera(28, 21, 1, 4, 220, 100);
                    Path path = Pathfinder.find(getDragon(), getDragon().getLocation().transform(-1, -9, 0), true, Pathfinder.DUMB, (a, b, c) -> 0);
                    path.walk(getDragon());
                    break;

                case 9:
                    camera(31, 12, -5, 3, 300, 100);
                    break;

                case 13:
                    camera(32, 10, -4, 5, 450, 100);
                    break;

                case 15:
                    getDragon().animate(DRAGON_ATTACK);//attack
                    getSirVant().animate(SIR_VANT_BLOCK);//block
                    getSirVant().getImpactHandler().manualHit(getDragon(), 12, ImpactHandler.HitsplatType.NORMAL);
                    break;

                case 17:
                    getDragon().animate(DRAGON_BLOCK);
                    getSirVant().animate(SIR_VANT_STAB);
                    getDragon().getImpactHandler().manualHit(getSirVant(), 11, ImpactHandler.HitsplatType.NORMAL);
                    break;

                case 19:
                    getDragon().animate(DRAGON_BLOCK);
                    getSirVant().animate(SIR_VANT_SLASH);
                    getDragon().getImpactHandler().manualHit(getSirVant(), 8, ImpactHandler.HitsplatType.NORMAL);
                    break;

                case 21:
                    getDragon().animate(DRAGON_FIRE_BREATH);
                    getDragon().graphics(DRAGON_FIRE_BREATH_GFX);
                    getSirVant().animate(SIR_VANT_BLOCK_FIRE);
                    getSirVant().getImpactHandler().manualHit(getDragon(), 18, ImpactHandler.HitsplatType.NORMAL);
                    break;

                case 23:
                    getSirVant().sendChat("Look out behind you!");
                    break;

                case 25:
                    camera(32, 13, -5, -5, 300, 100);
                    getGoblin().animate(GOBLIN_FALLING);
                    break;
                case 26:
                    camera(31, 9, -3, -3, 250, 75);
                    break;
                case 27:
                    path = Pathfinder.find(getGoblin(), getGoblin().getLocation().transform(2, -3, 0), true, Pathfinder.DUMB, (a, b, c) -> 0);
                    path.walk(getGoblin());
                    break;

                case 30:
                    getGoblin().sendChat("What can I steal in here?");
                    getGoblin().animate(GOBLIN_PLUNDERING);
                    break;

                case 34:
                    camera(32, 10, -3, 5, 500, 100);
                    break;

                case 35:
                    getDragon().animate(DRAGON_FLYING);
                    break;

                case 39:
                    getDragon().animate(DRAGON_STANDING_UNCUT);
                    break;
                case 40:
                    getDragon().animate(DRAGON_STANDING_CUT);
                    getSirVant().animate(SIR_VANT_BIG_SLASH);
                    getDragon().getImpactHandler().manualHit(getSirVant(), 30, ImpactHandler.HitsplatType.NORMAL);
                    break;
                case 42:
                    getDragon().animate(DRAGON_STANDING_WOBBLING);
                    break;

                case 45:
                    PacketRepository.send(CameraViewPacket.class, new CameraContext(player, CameraContext.CameraType.SHAKE, 4, 4, 1200, 4, 4));
                    camera(31, 9, -3, -3, 250, 45);
                    break;
                case 46:
                    getGoblin().face(player);
                    break;
                case 47:
                    getGoblin().animate(GOBLIN_HIT_ON_HEAD);
                    break;
                case 50:
                    PacketRepository.send(CameraViewPacket.class, new CameraContext(player, CameraContext.CameraType.SHAKE, 3, 2, 2, 2, 2));
                    getGoblin().getStateManager().set(EntityState.STUNNED, 4);
                    player.face(getGoblin());
                    break;
                case 53:
                    PacketRepository.send(CameraViewPacket.class, new CameraContext(player, CameraContext.CameraType.RESET, 5, 2, 450, 100, 0));
                    break;
                case 54:
                    camera(28, 5, 2, 6, 450, 100);
                    break;
                case 55:
                    path = Pathfinder.find(getDragon(), getDragon().getLocation().transform(0, 9, 0), true, Pathfinder.DUMB, (a, b, c) -> 0);
                    path.walk(getDragon());
                    break;
                case 60:
                    System.out.println("Fight end");
                    LTRDragonFightCutscene.this.stop(true);
                    TutorialStage.load(player, 3, false);
                    CellarMapZone.get().create(player);
                    return true;
                case 61:
                    break;
            }
            return !player.isActive();
        }

        @Override
        public void stop() {
            super.stop();
            clearNpcs();
        }

        /**
         * Gets the counter.
         *
         * @return the counter.
         */
        public int getCounter() {
            return counter;
        }
    }

    /**
     * Method used to handle the camera.
     *
     * @param x
     *            the x offset.
     * @param y
     *            the y offset.
     * @param xRot
     *            the xRotation.
     * @param yRot
     *            the yRotation.
     * @Param height the height.
     */
    private void camera(int x, int y, int xRot, int yRot, int height, int speed) {
        Location loc = base.transform(x, y, 0);
        PacketRepository.send(CameraViewPacket.class, new CameraContext(player, CameraContext.CameraType.POSITION, loc.getX(), loc.getY(), height, 1, speed));
        PacketRepository.send(CameraViewPacket.class, new CameraContext(player, CameraContext.CameraType.ROTATION, loc.getX() + xRot, loc.getY() + yRot, height, 1, speed));
    }

    @Override
    public void configure() {
        region = DynamicRegion.create(10062);
        setRegionBase();
        registerRegion(region.getId());
    }

    private void setNpcs() {
        for (NPC n : NPCS) {
            n = NPC.create(n.getId(), n.getLocation(), n.getDirection());
            n.setLocation(base.transform(n.getLocation().getLocalX(), n.getLocation().getLocalY(), 0));
            n.setRespawn(false);
            n.init();
            n.setWalks(false);
        }
    }

    @Override
    public void stop(boolean fade) {
        super.stop(false);
        player.teleport(Location.create(2524, 5004, 0));
        PacketRepository.send(CameraViewPacket.class, new CameraContext(player, CameraContext.CameraType.RESET, 0, 0, 0, 0, 0));
    }

    /**
     * Method used to clear all the npcs.
     */
    private void clearNpcs() {
        GameWorld.Pulser.submit(new Pulse(5) {
            @Override
            public boolean pulse() {
                for (NPC n : region.getPlanes()[0].getNpcs()) {
                    n.clear();
                }
                return true;
            }

        });
    }
    /**
     * Gets the npc.
     *
     * @param id
     *            the id.
     * @return the npc.
     */
    private NPC getNpc(int id) {
        for (NPC n : region.getPlanes()[0].getNpcs()) {
            if (n.getId() == id) {
                return n;
            }
        }
        return null;
    }

    private void walk(NPC npc, final Location location) {
        Pathfinder.find(npc, location, true, Pathfinder.DUMB).walk(npc);
    }

    public NPC getDragon() {
        return getNpc(7943);
    }
    public NPC getSirVant() {
        return getNpc(7938);
    }
    public NPC getGoblin() {
        return getNpc(7964);
    }


}
