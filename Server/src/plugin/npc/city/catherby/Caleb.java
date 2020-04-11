package plugin.npc.city.catherby;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.item.Item;
import org.crandor.plugin.InitializablePlugin;

/**
 * @author Austin
 * NPC: Caleb ID: 666
 * Quest: Family Crest
 * TODO: Rewrite when Family Crest is functional
 */

@InitializablePlugin
public class Caleb extends DialoguePlugin {
    public Caleb() {
        /**
         * Empty
         */
    }
    public Caleb(Player player){ super(player);}
    @Override
    public DialoguePlugin newInstance(Player player){return new plugin.npc.city.catherby.Caleb(player);}

    @Override
    public boolean open(Object... args){
        player("Can you transform these into cooking gauntlets for me?");
        return true;
    }

    /**
     * User can have unlimited Cooking Gauntlets
     *
     * @param componentId
     * @param buttonId
     * @return
     */

    @Override
    public boolean handle(int componentId, int buttonId) {
        switch (stage) {
            case 0:
                npc("Sure! That will be 25,000 gp and a pair of Family Gauntlets.");
                stage++;
                break;

            case 1:
                interpreter.sendOptions(" ","Sounds good to me!","That is much too expensive.");
                stage++;
                break;

            case 2:
                switch(buttonId){
                    case 1:
                        player("Sounds good to me!");
                        stage = 3;
                        break;
                    case 2:
                        player("That is much too expensive.");
                        stage = 10;
                        break;
                }
                break;

            case 3:
                if(player.getInventory().contains(995,25000) && player.getInventory().contains(778, 1)) {
                    player.getInventory().remove(new Item(995,25000));
                    player.getInventory().remove(new Item(778,1));
                    if(player.getInventory().freeSlots() > 0) {
                        player.getInventory().add(new Item(775));
                    } else {
                        npc("You need more space in your inventory.");
                        stage = 10;
                        break;
                    }
                    end();
                } else {
                    player("Err, I don't seem to have everything with me.");
                    stage++;
                }
                break;

            case 4:
                npc("Don't forget you need gp and my Family Gauntlets.");
                stage = 10;
                break;

            case 10:
                end();
                break;
        }
        return true;
    }

    @Override
    public int[] getIds() {return new int[] {666};}
}