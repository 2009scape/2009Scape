package org.crandor.game.node.entity.player.ai.general.scriptrepository;

import org.crandor.game.content.skill.Skills;
import org.crandor.game.node.Node;
import org.crandor.game.node.item.Item;
import org.crandor.game.world.map.Location;
import org.crandor.game.world.map.path.Pathfinder;
import org.crandor.tools.RandomFunction;

import java.util.Arrays;
import java.util.List;


public class LobsterCatcher extends Script {

    enum Sets {

        SET_1 (Arrays.asList(new Item(2643), new Item(9470), new Item(10756), new Item(10394), new Item(88), new Item(9793))),
        SET_2 (Arrays.asList(new Item(2643), new Item(6585), new Item(10750), new Item(10394), new Item(88), new Item(9793))),
        SET_3 (Arrays.asList(new Item(9472), new Item(9470), new Item(10750), new Item(10394), new Item(88), new Item(9786))),
        SET_4 (Arrays.asList(new Item(2639), new Item(6585), new Item(10752), new Item(10394), new Item(88), new Item(9786))),
        SET_5 (Arrays.asList(new Item(2639), new Item(9470), new Item(10750), new Item(10394), new Item(88), new Item(9784))),
        SET_6 (Arrays.asList(new Item(2639), new Item(6585), new Item(10750), new Item(10394), new Item(88), new Item(9784)));

        private List<Item> equipment;

        Sets(List<Item> equipment) {
            this.equipment = equipment;
        }

        public List<Item> getEquipment() {
            return equipment;
        }
    }

    private int tick = 0;

    public LobsterCatcher() {
        int setUp = RandomFunction.random(Sets.values().length);
        this.equipment.addAll(Sets.values()[setUp].getEquipment());
        this.inventory.add(new Item(301));
        this.skills.put(Skills.FISHING, 40);
    }

    @Override
    public void runLoop() {
        tick++;
        Node spot = scriptAPI.getNearestNode(333);
        Node bank = scriptAPI.getNearestNode(2213);


        Pathfinder.find(bot, Location.create(2837, 3435, 0)).walk(bot);

        if (spot != null) {
            System.out.println("LobsterCatcher: " + spot.getLocation().toString());
            spot.getInteraction().handle(bot, spot.getInteraction().get(0));
        }


        if (bot.getInventory().isFull()) {
            bank.getInteraction().handle(bot, bank.getInteraction().get(2));
        }
                 
        System.out.println("LobsterCatcher: " + tick);
    }
}
