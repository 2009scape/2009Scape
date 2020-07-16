package plugin.boredom;

import core.game.node.entity.player.Player;

public class BoredPlayer {
    Player p;
    int ticksLeft;
    boolean done = false;
    public BoredPlayer(Player p, int ticks){
        this.p = p;
        this.ticksLeft = ticks;
    }

    public void tick(){
        ticksLeft--;
        if(ticksLeft <= 0){
            p.getAppearance().transformNPC(-1);
            done = true;
        }
    }
}
