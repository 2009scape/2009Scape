package plugin.ai.general;

import core.game.node.Node;
import core.game.node.entity.player.Player;
import core.game.node.object.GameObject;
import core.game.world.map.Location;
import core.game.world.map.RegionManager;
import core.game.world.map.path.Pathfinder;

public class ScriptAPI {

    private Player bot;

    public ScriptAPI(Player bot) {
        this.bot = bot;
    }

    public double distance(Node n1, Node n2)
    {
        return Math.sqrt(Math.pow(n1.getLocation().getX() - n2.getLocation().getX(), 2) + Math.pow(n2.getLocation().getY() - n1.getLocation().getY(), 2));
    }

    public Node getNearestNode(String entityName)
    {
        Node entity = null;
        double minDistance = Double.MAX_VALUE;
        for (Node node : RegionManager.forId(bot.getLocation().getRegionId()).getPlanes()[bot.getLocation().getZ()].getEntities())
        {
            if (node != null && node.getName().equals(entityName) && distance(bot, node) < minDistance && !Pathfinder.find(bot, node).isMoveNear())
            {
                entity = node;
                minDistance = distance(bot, node);
            }
        }

        return entity;
    }

    public Node getNearestNode(int id)
    {
        Node entity = null;
        double minDistance = Double.MAX_VALUE;
        for (Node e : RegionManager.forId(bot.getLocation().getRegionId()).getPlanes()[bot.getLocation().getZ()].getEntities())
        {
            if (e != null && e.getId() == id && distance(bot, e) < minDistance && !Pathfinder.find(bot, e).isMoveNear())
            {
                entity = e;
                minDistance = distance(bot, e);
            }
        }

        return entity;
    }

    public GameObject getNearestGameObject(Location loc, int objectId) {
        GameObject nearestObject = null;
        double minDistance = Double.MAX_VALUE;
        for (GameObject[] o : RegionManager.forId(loc.getRegionId()).getPlanes()[0].getObjects()) {
            for (GameObject obj : o) {
                if (obj != null) {
                    if (distance(loc, obj) < minDistance) {
                        nearestObject = obj;
                    }
                }
            }
        }

        return nearestObject;
    }
}
