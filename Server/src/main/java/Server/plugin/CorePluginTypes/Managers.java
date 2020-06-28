package plugin.CorePluginTypes;

import core.game.system.SystemLogger;

import java.util.ArrayList;
import java.util.List;

public class Managers {

    private static List<ManagerPlugin> plugins = new ArrayList<>();
    public static void register(ManagerPlugin plugin){
        if(plugin != null){
            SystemLogger.log("Registered manager " + plugin.toString());
            plugins.add(plugin);
        }
    }

    public static void tick(){
        for(ManagerPlugin p : plugins){
            p.tick();
        }
    }
}
