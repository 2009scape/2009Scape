package plugin.ai.general.scriptrepository;

import core.game.node.Node;
import core.game.node.item.Item;

import java.util.Arrays;

public class ManThiever extends Script {
    public ManThiever() {
        this.equipment.addAll(Arrays.asList(new Item(1103), new Item(1139), new Item(1265)));
    }

    @Override
    public void runLoop() {
        Node man = scriptAPI.getNearestNode("Man");

        if (man != null) {
            man.getInteraction().handle(bot, man.getInteraction().get(2));
        }
    }
}
