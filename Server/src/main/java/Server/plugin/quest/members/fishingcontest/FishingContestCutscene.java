package plugin.quest.members.fishingcontest;

import core.game.interaction.MovementPulse;
import core.game.node.entity.Entity;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.game.system.task.Pulse;
import core.game.world.GameWorld;
import core.game.world.map.Direction;
import core.game.world.map.Location;
import core.game.world.map.build.DynamicRegion;
import core.game.world.map.zone.ZoneBorders;
import core.game.world.update.flag.context.Animation;
import core.plugin.InitializablePlugin;
import plugin.activity.ActivityPlugin;
import plugin.activity.CutscenePlugin;

@InitializablePlugin
public class FishingContestCutscene extends CutscenePlugin {

    public final int S_STRANGER = 3678;
    public final int BIG_DAVE = 228;

    public FishingContestCutscene(){super("Fishing Contest Cutscene");}

    public FishingContestCutscene(Player player){
        super("Fishing Contest Cutscene");
        this.player = player;
    }

    @Override
    public ActivityPlugin newInstance(Player p) throws Throwable {
        return new FishingContestCutscene(p);
    }

    @Override
    public boolean start(Player player, boolean login, Object... args) {
        NPC Bonzo = NPC.create(225,base.transform(17,45,0));//base.transform(145,45,0));
        NPC Stranger = NPC.create(S_STRANGER,base.transform(14,51,0));
        NPC Dave = NPC.create(BIG_DAVE,base.transform(9,34,0));
        Bonzo.init();
        Stranger.init();
        Dave.init();
        Bonzo.faceLocation(base.transform(16,45,0));
        Stranger.faceLocation(base.transform(13,51,0));
        Dave.faceLocation(base.transform(8,34,0));
        this.npcs.add(Bonzo);
        this.npcs.add(Dave);
        this.npcs.add(Stranger);
        player.faceLocation(base.transform(6,43,0));
        return super.start(player, login, args);
    }

    @Override
    public void register() {
        register(new ZoneBorders(2623, 3426,2644, 3448));
        super.register();
    }

    @Override
    public void open() {
        player.setAttribute("cutscene:original-loc", Location.create(2639, 3437, 0));
        player.setAttribute("real-end", Location.create(2639, 3437, 0));
        //PacketRepository.send(CameraViewPacket.class, new CameraContext(player, CameraContext.CameraType.POSITION, player.getLocation().getX(), player.getLocation().getY(), 450, 1, 100));
        //PacketRepository.send(CameraViewPacket.class, new CameraContext(player, CameraContext.CameraType.ROTATION, player.getLocation().getX() - 15, player.getLocation().getY() + -55, 450, 1, 100));
        if(player.getAttribute("fishing_contest:garlic",false)){
            NPC stranger = npcs.get(2);
            stranger.sendChat("What is this smell??");
            GameWorld.submit(new SwitchPulse());
        } else {
            GameWorld.submit(new FishingPulse());
        }
        player.lock();
    }

    private class FishingPulse extends Pulse{
        int counter = 0;
        @Override
        public boolean pulse() {
            switch(counter++){
                case 6:
                    for(NPC n : npcs){
                        n.faceLocation(n.getLocation().transform(Direction.WEST,1));
                        n.getAnimator().animate(new Animation(622));
                    }
                    player.faceLocation(player.getLocation().transform(Direction.WEST,1));
                    player.getAnimator().animate(new Animation(622));
                    break;
                case 12:
                    if(player.getAttribute("fishing_contest:garlic",false) && player.getInventory().containsItem(FishingContest.RED_VINE_WORM)){
                        player.getInventory().remove(FishingContest.RED_VINE_WORM);
                        player.getInventory().add(FishingContest.RAW_GIANT_CARP);
                        player.setAttribute("/save:fishing_contest:won",true);
                        player.getWalkingQueue().setRunDisabled(false);
                    }
                    GameWorld.submit(getEndPulse());
                    break;
                case 15:
                    GameWorld.submit(new Pulse(){
                        int counter = 0;
                        @Override
                        public boolean pulse() {
                            switch(counter++){
                                case 2:
                                    player.faceLocation(Location.create(2642, 3437, 0));
                                    break;
                                case 4:
                                    player.getDialogueInterpreter().open(225,true,true);
                                    return true;
                            }
                            return false;
                        }
                    });
                    return true;
            }
            return false;
        }
    }

    @Override
    public boolean leave(Entity e, boolean logout) {
        return true;//super.leave(e, logout);
    }

    @Override
    public void stop(boolean fade) {
        super.stop(fade);
    }

    public class SwitchPulse extends Pulse{
        NPC stranger = npcs.get(2);
        int counter = 0;
        @Override
        public boolean pulse() {
            switch (counter++) {
                case 1:
                    stranger.getPulseManager().run(new MovementPulse(stranger, base.getLocation().transform(8, 42, 0)) {
                        @Override
                        public boolean pulse() {
                            player.getWalkingQueue().setRunDisabled(true);
                            return true;
                        }
                    }, "movement");
                    break;
                case 12:
                    stranger.sendChat("You're switching with me. Go.");
                    break;
                case 16:
                    player.getPulseManager().run(new MovementPulse(player, base.getLocation().transform(14, 51, 0)) {
                        @Override
                        public boolean pulse() {
                            player.getDialogueInterpreter().sendDialogue("Your spot is now by the pipes.");
                            return true;
                        }
                    }, "movement");
                    break;
                case 22:
                    stranger.getPulseManager().run(new MovementPulse(stranger, base.getLocation().transform(7, 43, 0)) {
                        @Override
                        public boolean pulse() {
                            return true;
                        }
                    }, "movement");
                    GameWorld.submit(new FishingPulse());
                    return true;
            }
            return false;
        }
    }



    @Override
    public Location getStartLocation() {
        return base.transform(7,43,0);
    }

    @Override
    public Location getSpawnLocation() {
        return null;
    }

    @Override
    public void configure() {
        region = DynamicRegion.create(10549);
        setRegionBase();
        registerRegion(region.getId());
    }
}
