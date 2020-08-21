package plugin.quest.members.fishingcontest;

import core.game.interaction.MovementPulse;
import core.game.interaction.Option;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.game.world.map.Location;
import core.plugin.InitializablePlugin;
import core.plugin.Plugin;
import plugin.dialogue.FacialExpression;
import plugin.quest.PluginInteraction;
import plugin.quest.PluginInteractionManager;

@InitializablePlugin
public class FishingSpotInteraction extends PluginInteraction {
    @Override
    public boolean handle(Player player, NPC npc, Option option) {
        Location npc_loc = npc.getLocation();
        if(!player.getAttribute("fishing_contest:fee-paid",false)) {
            if(npc_loc.equals(new Location(2637, 3444, 0))) {
                player.getPulseManager().run(new MovementPulse(player, npc.getLocation().transform(1, 0, 0)) {
                    @Override
                    public boolean pulse() {
                        player.getDialogueInterpreter().sendDialogues(3677, FacialExpression.NEUTRAL, "I think you will find that is","my spot.");
                        return true;
                    }
                }, "movement");
                return true;
            } else if (npc_loc.equals(2630, 3435, 0)){
                player.getPulseManager().run(new MovementPulse(player, npc.getLocation().transform(1, 0, 0)) {
                    @Override
                    public boolean pulse() {
                        player.getDialogueInterpreter().sendDialogues(225, FacialExpression.NEUTRAL, "Hey, you need to pay to enter the", "competition first! Only 5gp entrance fee!");
                        return true;
                    }
                }, "movement");
                return true;
            } else if (npc_loc.equals(Location.create(2632, 3427, 0))){
                player.getPulseManager().run(new MovementPulse(player, npc.getLocation().transform(1, 0, 0)) {
                    @Override
                    public boolean pulse() {
                        player.getDialogueInterpreter().sendDialogues(228, FacialExpression.NEUTRAL, "I think you will find that is my spot.");
                        return true;
                    }
                }, "movement");
                return true;
            } else if(npc_loc.equals(Location.create(2627,3415,0))) {
                player.getPulseManager().run(new MovementPulse(player, npc.getLocation().transform(1, 0, 0)) {
                    @Override
                    public boolean pulse() {
                        player.getDialogueInterpreter().sendDialogues(228, FacialExpression.NEUTRAL, "I think you will find that is my spot.");
                        return true;
                    }
                }, "movement");
                return true;
            }
        }
        return false;
    }

    @Override
    public Plugin<Object> newInstance(Object arg) throws Throwable {
        setIds(new int[]{309});
        PluginInteractionManager.register(this, PluginInteractionManager.InteractionType.NPC);
        return this;
    }

    @Override
    public Object fireEvent(String identifier, Object... args) {
        return null;
    }
}
