package plugin.blackjack;

import core.game.node.entity.player.Player;
import core.plugin.InitializablePlugin;
import core.plugin.Plugin;
import core.plugin.PluginManifest;
import plugin.CorePluginTypes.ManagerPlugin;
import plugin.CorePluginTypes.Managers;

import java.util.ArrayList;
import java.util.List;

@InitializablePlugin
@PluginManifest(name="Blackjack")
public class BlackJackManager extends ManagerPlugin {
    public static List<BlackJackSession> ACTIVE_SESSIONS = new ArrayList<>();

    public static void register(BlackJackSession session){
        ACTIVE_SESSIONS.add(session);
    }

    @Override
    public void tick() {
        ACTIVE_SESSIONS.removeIf(s -> !s.tick());
    }

    @Override
    public Plugin<Object> newInstance(Object arg) throws Throwable {
        Managers.register(this);
        return this;
    }

    public static BlackJackSession get(Player p){
        for(BlackJackSession s : ACTIVE_SESSIONS){
            if(s instanceof BlackJackSingleSession){
                if(((BlackJackSingleSession) s).getPlayer() == p){
                    return s;
                }
            }else if(((BlackJackMultiSession)s).INVITED_PLAYERS.contains(p)){
                return s;
            }
        }
        return null;
    }
}
