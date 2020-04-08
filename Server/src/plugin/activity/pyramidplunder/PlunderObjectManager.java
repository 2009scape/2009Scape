package plugin.activity.pyramidplunder;

import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.object.GameObject;
import org.crandor.game.node.object.ObjectBuilder;
import org.crandor.game.world.map.Location;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;

/**
 * Manages what objects a specific player has interacted with in pyramid plunder.
 * @author ceik
 */

public class PlunderObjectManager{
    public static HashMap<Location,Boolean> openedMap = new HashMap<>();
    public static HashMap<Location, Boolean> charmedMap = new HashMap<>();

    int originalIndex;
    public boolean resetObjectsFor(Player player){
        //Completely clear the mapping and reset objects
        openedMap.clear();
        charmedMap.clear();
        return true;
    }

    public void registerOpened(PlunderObject object){
        openedMap.putIfAbsent(object.getLocation(),true);
    }
    public void registerCharmed(PlunderObject object) { charmedMap.putIfAbsent(object.getLocation(),true);}
}
