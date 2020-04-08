package plugin.interaction.city.portsarim;

import org.crandor.cache.def.impl.NPCDefinition;
import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.interaction.Interaction;
import org.crandor.game.interaction.Option;
import org.crandor.game.interaction.SpecialGroundInteraction;
import org.crandor.game.interaction.SpecialGroundItems;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.item.GroundItem;
import org.crandor.game.node.item.GroundItemManager;
import org.crandor.game.node.item.Item;
import org.crandor.game.system.mysql.impl.GroundSpawnSQLHandler;
import org.crandor.game.system.task.Pulse;
import org.crandor.game.world.map.Location;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.plugin.Plugin;

/**
 * Handles Ahab's beer in port sarim
 * @author ceik
 */

public class AhabBeerInteraction extends SpecialGroundInteraction {
    //The dialogue key
    public static final int DIALOGUE_KEY = 2692;

    @Override
    public void handle(final Player player, final Option option){
        configure();
        if(option.getName() == "take"){
            player.faceLocation(SpecialGroundItems.AHAB_BEER.getLocation());
            player.getDialogueInterpreter().open(DIALOGUE_KEY, new NPC(2692), false);
        } else {
            player.debug("Unhandled option: " + option.getName());
        }
    }
}
