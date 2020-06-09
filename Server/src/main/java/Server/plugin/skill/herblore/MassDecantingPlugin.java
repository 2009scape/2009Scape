
package plugin.skill.herblore;

import core.cache.def.impl.NPCDefinition;
import core.game.interaction.OptionHandler;
import core.game.node.Node;
import core.game.node.entity.player.Player;
import core.game.node.item.Item;

import core.plugin.InitializablePlugin;
import core.plugin.Plugin;
import plugin.consumable.potion.Potions;

import java.util.HashMap;
import java.util.List;

@InitializablePlugin
public class MassDecantingPlugin extends OptionHandler {

    @Override
    public Plugin<Object> newInstance(Object arg) throws Throwable {
        NPCDefinition.setOptionHandler("decant",this);
        return this;
    }

    @Override
    public boolean handle(Player player, Node node, String option) {
        decant(player);
        return true;
    }

    public void decant(Player p){
        HashMap<Potions,Integer> potcounts = new HashMap<>();
        final List<Item> results;
        for(int i = 0; i < 28; i++){
            Potions pot = Potions.forId(p.getInventory().getId(i));
            if(pot != null){
                int dosage = Integer.parseInt(p.getInventory().get(i).getName().replaceAll("[^\\d.]",""));
                if(potcounts.get(pot) != null){
                    potcounts.replace(pot,potcounts.get(pot) + dosage);
                } else {
                    potcounts.putIfAbsent(pot,dosage);
                }
                p.getInventory().remove(new Item(p.getInventory().getId(i)));
            }
        }
        potcounts.keySet().forEach(pot -> {
            int full_count = (potcounts.get(pot) / pot.ids.length);
            int partial_dose_amt = (potcounts.get(pot) % pot.ids.length);
            p.getInventory().add(new Item(pot.ids[0],full_count));
            if(partial_dose_amt > 0) p.getInventory().add(new Item(pot.ids[pot.ids.length - partial_dose_amt]));
        });
    }
}

