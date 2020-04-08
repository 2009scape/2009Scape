package plugin.interaction.city.falador;

import org.crandor.game.interaction.Option;
import org.crandor.game.interaction.SpecialGroundInteraction;
import org.crandor.game.interaction.SpecialGroundItems;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.world.map.RegionManager;

import java.util.List;

public class WineOfZamorakInteraction extends SpecialGroundInteraction {
    @Override
    public void handle(Player player, Option option){
        player.faceLocation(SpecialGroundItems.WINE_OF_ZAMORAK.getLocation());
        final List<NPC> npcs = RegionManager.getLocalNpcs(player);
        for (NPC n : npcs) {
            if (n.getId() == 188) {
                n.sendChat("Hands off zamorak's wine!");
                n.getProperties().getCombatPulse().attack(player);
            }
        }
    }
}
