package plugin.ai.resource;

import core.game.node.entity.player.Player;
import plugin.ai.resource.task.ResourceTask;
import plugin.ai.resource.task.ResourceTasks;
import core.game.system.task.Pulse;
import core.game.world.GameWorld;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


/**
 * Package -> org.keldagrim.game.node.entity.player.ai.resource
 * Created on -> 9/13/2016 @12:42 PM for 530
 *
 * @author Ethan Kyle Millard
 */
public class ResourceAIPManager {

    private long time = 0;

    private Player player;

    private static Map<ResourceTask, Long> TASKS = new HashMap<>();

    public ResourceAIPManager init() {
        getTasks().put(ResourceTasks.WOODCUTTING.getResourceTask(), 0L);
        return this;
    }

    public ResourceAIPManager runTask(Player player, String taskName) {
        for (Map.Entry<ResourceTask, Long> entry : getTasks().entrySet()) {
            if (entry.getKey().getTaskName().equalsIgnoreCase(taskName)) {
                if (entry.getValue() != 0) {
                    message(player, "You have extended the task " + taskName + ".");
                } else {
                    message(player, "You have activated the task " + taskName + ".");
                }
            }
            entry.getKey().setTaskName(taskName);
            entry.setValue(entry.getValue() + 70);
            this.player = player;
        }
        return this;
    }

    public void reActivate(String name, long time) {
        for (Map.Entry<ResourceTask, Long> entry : TASKS.entrySet()) {
            if (entry.getKey().getTaskName().equalsIgnoreCase(name)) {
                entry.setValue(time);
            }
        }
    }

    public ResourceAIPManager load(Player player) {
        try {
            Statement statement = GameWorld.getDatabaseManager().connections().get("global").createStatement();

            ResultSet result = statement.executeQuery("SELECT * FROM `members` WHERE username='" + player.getUsername() + "'");
           // Results result = new Results(GameWorld.getDatabaseManager().query("global", "SELECT * FROM `members` WHERE username='" + player.getUsername() + "'"));

            while (result.next()) {
                String eventName = result.getString("taskName");
                String eventTime = result.getString("taskTime");
                reActivate(eventName, Long.valueOf(eventTime));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    public ResourceAIPManager save(Player player) {
        /*if (GameWorld.getDatabaseManager().update("global", "DELETE FROM `members` WHERE worldid='" + GameWorld.getSettings().getWorldId() + "'") < 0)
            return this;*/
        System.out.println("Saving...");
        Iterator<Map.Entry<ResourceTask, Long>> iterator = getTasks().entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<ResourceTask, Long> entry = iterator.next();
            if (entry.getValue() <= 0)
                continue;
            StringBuilder query = new StringBuilder();
            query.append("UPDATE `members` SET `taskName`='" + entry.getKey().getTaskName() + "',`taskTime`='" + entry.getValue() + "' WHERE `username`='" + player.getUsername() + "'");
            System.out.println("ResourceAIPManager: " + query.toString());
            GameWorld.getDatabaseManager().update("global", query.toString());

        }
        return this;
    }

    public void pulse(Player player) {
        GameWorld.Pulser.submit(new Pulse(1) {
            @Override
            public boolean pulse() {

                time++;

                Iterator<Map.Entry<ResourceTask, Long>> iterator = getTasks().entrySet().iterator();

                while (iterator.hasNext()) {
                    Map.Entry<ResourceTask, Long> entry = iterator.next();
                    if (entry.getValue() > 0) {
                        entry.setValue(entry.getValue() - 1);
                        if (entry.getValue() == 50)
                            message(player, "You have 30 minutes before " + entry.getKey().getTaskName() + " task ends.");
                        if (entry.getValue() <= 0) {
                            entry.getKey().setTime(0);
                            entry.getKey().reward(player, entry.getKey().getTaskName());
                            message(player, "The task " + entry.getKey().getTaskName() + " has now ended.");
                            save(player);
                        }
                        if (time == 50) {
                            entry.getKey().setTime(0);
                            save(player);
                        }
                    }
                }
                return false;
            }
        });
    }

    public ResourceAIPManager message(Player player, String message) {
        return message(player, message, true, "<col=027fc7>");
    }

    public ResourceAIPManager message(Player player, String message, boolean tag) {
        return message(player, message, tag, "<col=027fc7>");
    }

    public ResourceAIPManager notify(Player player, String message) {
        return message(player, message, true, "<col=800000>");
    }

    public ResourceAIPManager notify(Player player, String message, boolean tag) {
        return message(player, message, tag, "<col=800000>");
    }


    public ResourceAIPManager message(Player player, String message, boolean tag, String color) {
        player.getPacketDispatch().sendMessage(color + (tag ? "[Resource Manager] - " : "") + message);
        return this;

    }

    public static Map<ResourceTask, Long> getTasks() {
        return TASKS;
    }

    private static ResourceAIPManager INSTANCE = new ResourceAIPManager();

    public static ResourceAIPManager get() {
        return INSTANCE;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
