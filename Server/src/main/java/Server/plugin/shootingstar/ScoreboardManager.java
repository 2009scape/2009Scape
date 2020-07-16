package plugin.shootingstar;

import core.game.node.entity.player.Player;
import core.game.system.SystemLogger;
import core.game.world.GameWorld;

import java.util.ArrayList;
import java.util.List;

public class ScoreboardManager {
    public static List<ScoreboardEntry> entries = new ArrayList<>();

    public static void submit(Player player){
        if(entries.size() == 5){
            entries.remove(0);
        }
        entries.add(new ScoreboardEntry(player.getUsername(), GameWorld.getTicks()));
        SystemLogger.log("Added scoreboard entry for " + player.getUsername() + " at time " + GameWorld.getTicks());
    }

    public static List<ScoreboardEntry> getEntries(){
        return entries;
    }
}
