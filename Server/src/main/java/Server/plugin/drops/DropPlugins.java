package plugin.drops;

import core.game.node.item.Item;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class DropPlugins {
    public static HashMap<Integer,List<DropPlugin>> plugins = new HashMap<>();
    public static List<DropPlugin> globalPlugins = new ArrayList<>();
    public static List<Item> getDrops(int npc_id){
        List<Item> drops = new ArrayList<>();
        List<DropPlugin> toHandle = plugins.get(npc_id);
        if(toHandle != null) {
            for (DropPlugin plugin : toHandle) {
                drops.addAll(plugin.handle());
            }
        }
        if(!globalPlugins.isEmpty()) {
            for (DropPlugin plugin : globalPlugins) {
                drops.addAll(plugin.handle());
            }
        }
        return drops;
    }

    public static void register(DropPlugin plugin){
        if(plugin.accepted_npcs.length > 0) {
            Arrays.stream(plugin.accepted_npcs).forEach(id -> {
                if (plugins.get(id) == null) {
                    List<DropPlugin> newlist = new ArrayList<>();
                    plugins.putIfAbsent(id, newlist);
                }
                plugins.get(id).add(plugin);
            });
        } else {
            globalPlugins.add(plugin);
        }
    }
}
