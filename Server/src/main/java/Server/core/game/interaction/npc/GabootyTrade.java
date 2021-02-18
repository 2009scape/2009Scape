package core.game.interaction.npc;

import core.cache.def.impl.NPCDefinition;

import core.game.interaction.OptionHandler;
import core.game.node.Node;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.game.system.config.ShopParser;
import core.plugin.Initializable;
import core.plugin.Plugin;

/**
 * Represents the plugin used for trading with Gabooty
 *
 * @author 'qmqz
 * @version 1.0
 */
@Initializable
public final class GabootyTrade extends OptionHandler {

    @Override
    public Plugin<Object> newInstance(Object arg) throws Throwable {
        NPCDefinition.forId(2521).getHandlers().put("option:talk-to", this);
        NPCDefinition.forId(2521).getHandlers().put("option:trade-co-op", this);
        NPCDefinition.forId(2521).getHandlers().put("option:trade-drinks", this);
        return this;
    }

    @Override
    public boolean handle(Player player, Node node, String option) {
        final NPC npc = (NPC) node;
        switch (npc.getId()) {
            case 2519:
            case 2520:
            case 2521:
                switch (option) {
                    case "talk-to":
                        player.getDialogueInterpreter().open(2521, node.asNpc());
                        return true;

                    case "trade-co-op":
                        ShopParser.Companion.openUid(player, 226);
                        return true;

                    case "trade-drinks":
                        ShopParser.Companion.openUid(player, 227);
                        return true;
                }
                break;
        }
        return true;
    }
}
