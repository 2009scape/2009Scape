package plugin.zone.phasmatys;

import core.cache.def.impl.NPCDefinition;
import core.game.interaction.OptionHandler;
import core.game.node.Node;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.game.node.item.Item;
import core.plugin.InitializablePlugin;
import core.plugin.Plugin;

@InitializablePlugin
public class TokenCollectOption extends OptionHandler {

    @Override
    public boolean handle(Player player, Node node, String option) {
        if(option.equals("collect")){
            player.getDialogueInterpreter().open(1686,new NPC(1686),true);
        }
        return true;
    }

    @Override
    public Plugin<Object> newInstance(Object arg) throws Throwable {
        NPCDefinition.forId(1686).getConfigurations().put("option:collect",this);
        return this;
    }
}
