package plugin.quest.members.sheepherder;

import core.game.interaction.DestinationFlag;
import core.game.interaction.MovementPulse;
import core.game.node.Node;
import core.game.node.entity.player.Player;
import core.game.system.SystemLogger;
import core.plugin.InitializablePlugin;
import core.plugin.Plugin;
import plugin.dialogue.FacialExpression;
import plugin.quest.PluginInteraction;
import plugin.quest.PluginInteractionManager;


@InitializablePlugin
public class GateInteractionHandler extends PluginInteraction {

    @Override
    public Plugin<Object> newInstance(Object arg) throws Throwable {
        setIds(new int[]{167,166});
        PluginInteractionManager.register(this, PluginInteractionManager.InteractionType.OBJECT);
        return this;
    }

    @Override
    public boolean handle(Player player, Node node) {
        SystemLogger.log("Trying to handle... " + node.getId());
            switch(node.getId()){
                case 167:
                case 166:
                    return handleGate(player,node);
            }
            return false;
    }

    public boolean handleGate(Player player, Node obj){
        if(!player.getEquipment().containsAll(SheepHerder.PLAGUE_BOTTOM.getId(),SheepHerder.PLAGUE_TOP.getId())) {
            player.getPulseManager().run(new MovementPulse(player, DestinationFlag.OBJECT.getDestination(player, obj)) {
                @Override
                public boolean pulse() {
                    player.getDialogueInterpreter().sendDialogues(SheepHerder.FARMER_BRUMTY, FacialExpression.SUSPICIOUS, "You can't enter without your protective gear!", "Can't have you spreading the plague!");
                    return true;
                }
            }, "movement");
            return true;
        }
        return false;
    }

    @Override
    public Object fireEvent(String identifier, Object... args) {
        return null;
    }
}
