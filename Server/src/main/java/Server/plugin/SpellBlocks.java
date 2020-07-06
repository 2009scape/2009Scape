package plugin;

import core.game.node.Node;
import core.game.node.entity.npc.NPC;
import core.game.node.item.GroundItem;
import core.game.node.item.Item;
import core.game.node.object.GameObject;
import core.game.system.SystemLogger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class SpellBlocks {
    private static HashMap<Integer, List<Node>> blocks = new HashMap<>();

    public static void register(int spellId, Node toBlock){
        if(blocks.get(spellId) != null){
            blocks.get(spellId).add(toBlock);
        } else {
            List<Node> blockslist = new ArrayList<>();
            blockslist.add(toBlock);
            blocks.put(spellId,blockslist);
        }
    }

    public static boolean isBlocked(int spellId,Node node){
        AtomicBoolean blocked = new AtomicBoolean(false);
        if(blocks.get(spellId) == null){
            SystemLogger.log("Not in hashmap");
            return false;
        }
        SystemLogger.log("Checking...");
        blocks.get(spellId).forEach(n -> {
            if(node.getName().equals(n.getName())){
                blocked.set(true);
            }
        });
        return blocked.get();
    }
}
