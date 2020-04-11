package plugin.npc.city.varrock;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.item.Item;
import org.crandor.plugin.InitializablePlugin;

/**
 * @author Austin
 * NPC: Dimintheis ID: 8172
 * Quest: Family Crest
 * TODO: Rewrite when Family Crest is functional
 */

@InitializablePlugin
public class Dimintheis extends DialoguePlugin {
    public Dimintheis() {
        /**
         * Empty
         */
    }
    public Dimintheis(Player player){ super(player);}
    @Override
    public DialoguePlugin newInstance(Player player){return new Dimintheis(player);}

    @Override
    public boolean open(Object... args){
        player("I lost my gauntlets.");
        return true;
    }

    /**
     * User can have unlimited Family Gauntlets
     *
     * @param componentId
     * @param buttonId
     * @return
     */

    @Override
    public boolean handle(int componentId, int buttonId) {
        switch (stage) {
            case 0:
                if(player.getInventory().freeSlots() > 0) {
                    npc("Here you go!");
                    player.getInventory().add(new Item(778));
                    stage++;
                    break;
                } else {
                    npc("You need to clear room in your inventory!");
                    stage++;
                    break;
                }
            case 1:
                end();
                break;
        }
        return true;
    }

        @Override
        public int[] getIds() {return new int[] {8172};}
}
