package plugin.ai.resource.task;

import plugin.skill.Skills;
import core.game.node.entity.player.Player;
import core.game.node.item.Item;
import core.tools.RandomFunction;

/**
 * Package -> org.keldagrim.game.node.entity.player.ai.resource.task
 * Created on -> 9/13/2016 @12:43 PM for 530
 *
 * @author Ethan Kyle Millard
 */
public enum ResourceTasks {

    WOODCUTTING(new ResourceTask("Willow Logs", 6000) {

        @Override
        public void reward(Player player, String eventName) {
            if (player.getSkills().getLevel(Skills.WOODCUTTING) < 30) {
                player.sendMessage(reqirementMessage());
                return;
            }
            if (getTime() == 0) {
                player.getBank().add(new Item(1519, RandomFunction.random(700, 1000)));
                player.getSkills().addExperience(Skills.WOODCUTTING, RandomFunction.random(100000, 150000));
            }
        }
    });

    private ResourceTask resourceTask;


    ResourceTasks(ResourceTask resourceTask) {
        this.resourceTask = resourceTask;
    }

    public ResourceTask getResourceTask() {
        return resourceTask;
    }

    public void setResourceTask(ResourceTask resourceTask) {
        this.resourceTask = resourceTask;
    }

    private static String reqirementMessage() {
        return "You do not meet the requirements for this task.";
    }
}
