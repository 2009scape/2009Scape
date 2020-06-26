package plugin.drops;

import core.game.node.item.Item;
import core.plugin.Plugin;

import java.util.List;

public abstract class DropPlugin implements Plugin<Object> {

    //the list of NPCs this plugin can effect, if left blank it effects all NPCs.
    public int[] accepted_npcs;

    //allows a super constructor to define the npcs
    public DropPlugin(int... accepted_npcs){
        this.accepted_npcs = accepted_npcs;
    }

    //necessary for the plugin framework, doesnt need to be changed ever.
    @Override
    public Object fireEvent(String identifier, Object... args) {
        return null;
    }

    //defines an abstract method all drop plugins must implement
    public abstract List<Item> handle();
}
