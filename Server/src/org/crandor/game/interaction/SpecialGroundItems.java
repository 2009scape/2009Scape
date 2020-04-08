package org.crandor.game.interaction;

import org.crandor.game.node.item.GroundItem;
import org.crandor.game.node.item.GroundItemManager;
import org.crandor.game.world.map.Location;
import plugin.interaction.city.falador.WineOfZamorakInteraction;
import plugin.interaction.city.portsarim.AhabBeerInteraction;

/**
 * Handles interactions for special ground items
 * @author ceik
 */
public enum SpecialGroundItems {
    //Ahab's beer
    AHAB_BEER(1917,new Location(3049,3257,0), new AhabBeerInteraction()),
    WINE_OF_ZAMORAK(245, Location.create(2931, 3515, 0), new WineOfZamorakInteraction());

    private int itemid;
    private Location location;
    private SpecialGroundInteraction inter;

    SpecialGroundItems(int itemId, Location location, SpecialGroundInteraction inter){
        this.itemid = itemId;
        this.location = location;
        this.inter = inter;
    }

    public SpecialGroundInteraction getInteraction() { return inter;}
    public int getItemid() {return itemid;}
    public Location getLocation() {return location;}
    public GroundItem asGroundItem(){return GroundItemManager.get(itemid,location,null);}
}
