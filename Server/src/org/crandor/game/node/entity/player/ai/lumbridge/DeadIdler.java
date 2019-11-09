package org.crandor.game.node.entity.player.ai.lumbridge;

import org.crandor.game.node.entity.player.ai.AIPlayer;
import org.crandor.game.world.map.Location;
import org.crandor.game.world.map.zone.ZoneBorders;
import org.crandor.tools.RandomFunction;

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
