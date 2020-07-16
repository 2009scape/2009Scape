package plugin.ai.lumbridge;

import plugin.ai.AIPlayer;
import core.game.world.map.Location;
import core.game.world.map.zone.ZoneBorders;
import core.tools.RandomFunction;

public class DeadIdler extends AIPlayer {
    //Recreation of players I saw in w417 who seemed to have quit their computer after dying.

    private int tick = RandomFunction.random(500);

    public DeadIdler()
    {
        super(getRandomRespawnLoc());
        this.setCustomState("Lumbridge Bot");
    }

    @Override
    public void tick()
    {
        super.tick();
        if (this.tick > 0)
        {
            tick --;
        } else {
            AIPlayer.deregister(this.getUid());
        }
    }

    private static Location getRandomRespawnLoc() {
        return new ZoneBorders(3219, 3218, 3223, 3219).getRandomLoc();
    }
}
