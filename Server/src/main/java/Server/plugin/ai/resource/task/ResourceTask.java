package plugin.ai.resource.task;

import core.game.node.entity.player.Player;

/**
 * Package -> org.keldagrim.game.node.entity.player.ai.resource.task
 * Created on -> 9/13/2016 @12:44 PM for 530
 *
 * @author Ethan Kyle Millard
 */
public abstract class ResourceTask {


    private String taskName;
    private long time;

    public ResourceTask(String taskName, long time) {
        this.taskName = taskName;
        this.time = time;
    }

    public abstract void reward(Player player, String eventName);

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }
}
