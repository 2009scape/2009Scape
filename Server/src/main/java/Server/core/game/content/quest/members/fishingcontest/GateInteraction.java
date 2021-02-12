package core.game.content.quest.members.fishingcontest;

import core.game.interaction.MovementPulse;
import core.game.node.Node;
import core.game.node.entity.player.Player;
import core.game.world.map.Location;
import core.plugin.Initializable;
import core.plugin.Plugin;
import core.game.content.quest.PluginInteraction;
import core.game.content.quest.PluginInteractionManager;

@Initializable
public class GateInteraction extends PluginInteraction {

    @Override
    public boolean handle(Player player, Node node) {
        switch(node.getId()){
            case 47:
            case 48:
                return handleGate(player,node);
            case 52:
            case 53:
                return handleGruborGate(player,node);
        }
        return false;
    }

    public boolean handleGate(Player player, Node node){
        if(!player.getAttribute("fishing_contest:pass-shown",false) || player.getQuestRepository().getStage("Fishing Contest") < 10) {
            player.getPulseManager().run(new MovementPulse(player, node.asObject().getLocation().transform(1, 0, 0)) {
                @Override
                public boolean pulse() {
                    if(player.getQuestRepository().getStage("Fishing Contest") >= 10){
                        player.sendMessage("You should give your pass to Morris.");
                    } else {
                        player.sendMessage("You need a fishing pass to fish here.");
                    }
                    return true;
                }
            }, "movement");
            return true;
        } else {
            if(!player.getInventory().containsItem(FishingContest.FISHING_ROD)){
                player.getDialogueInterpreter().sendDialogue("I should probably get a rod from","Grandpa Jack before starting.");
            }
        }
        return false;
    }

    public boolean handleGruborGate(Player player, Node node){
        if(node.getLocation().withinDistance(Location.create(2650, 3469, 0),4)) {
            player.getPulseManager().run(new MovementPulse(player, node.asObject().getLocation().transform(0,-1 , 0)) {
                @Override
                public boolean pulse() {
                    player.getDialogueInterpreter().sendDialogue("This gate is locked.");
                    return true;
                }
            }, "movement");
            return true;
        }
        return false;
    }

    @Override
    public Plugin<Object> newInstance(Object arg) throws Throwable {
        setIds(new int[]{47,48,52,53});
        PluginInteractionManager.register(this, PluginInteractionManager.InteractionType.OBJECT);
        return this;
    }

    @Override
    public Object fireEvent(String identifier, Object... args) {
        return null;
    }
}
