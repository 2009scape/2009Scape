package plugin.activity.pyramidplunder;

import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.object.GameObject;
import org.crandor.game.world.map.Location;
import org.crandor.game.world.map.zone.RegionZone;

import java.util.HashMap;

/**
 * Object wrapper for pyramid plunder nodes
 * @author ceik
 */

public class PlunderObject extends GameObject {
    private transient Player player;
    private static int thisId;
    public static int snakeId, openId;


    public PlunderObject(GameObject obj){
        super(obj.getId(),obj.getLocation(),obj.getRotation(),obj.getDirection());
        this.thisId = obj.getId();
        switch(thisId){
            case 16501:
                this.snakeId = 16509;
                this.openId = 16505;
                break;
            case 16502:
                this.snakeId = 16510;
                this.openId = 16506;
                break;
            case 16503:
                this.snakeId = 16511;
                this.openId = 16507;
                break;
            case 16495:
                this.openId = 16496;
                break;
            case 16473:
                this.openId = 16474;
                break;
        }
    }
}
