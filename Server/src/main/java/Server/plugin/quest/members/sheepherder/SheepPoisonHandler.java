package plugin.quest.members.sheepherder;

import core.game.interaction.DestinationFlag;
import core.game.interaction.MovementPulse;
import core.game.interaction.NodeUsageEvent;
import core.game.node.Node;
import core.game.node.entity.player.Player;
import core.game.node.item.GroundItemManager;
import core.game.node.item.Item;
import core.game.system.task.Pulse;
import core.game.world.GameWorld;
import core.game.world.map.Location;
import core.game.world.update.flag.context.Animation;
import core.plugin.InitializablePlugin;
import core.plugin.Plugin;
import plugin.quest.PluginInteraction;
import plugin.quest.PluginInteractionManager;

@InitializablePlugin
public class SheepPoisonHandler extends PluginInteraction {
    @Override
    public Plugin<Object> newInstance(Object arg) throws Throwable {
        setIds(new int[]{SheepHerder.POISON.getId()});
        PluginInteractionManager.register(this, PluginInteractionManager.InteractionType.USEWITH);
        return null;
    }

    @Override
    public boolean handle(Player player, NodeUsageEvent event) {
        Node n = event.getUsedWith();
        Item i = event.getUsedItem();
        if(n instanceof HerderSheepNPC){
            if (withinBorders(n.getLocation(),Location.create(2595, 3364, 0),Location.create(2609, 3351, 0))){
                handlePoisoning(player,(HerderSheepNPC) n);
                return true;
            }
        }
        return false;
    }

    public void handlePoisoning(Player p, HerderSheepNPC n){
        p.lock(1);
        Pulse deathPulse = new Pulse() {
            int counter = 0;
            @Override
            public boolean pulse() {
                switch(counter){
                    case 0:
                        p.getDialogueInterpreter().sendDialogue("You feed some poisoned sheep food to the sheep.","It happily eats it.");
                        counter++;
                        break;
                    case 1:
                        n.getAnimator().animate(new Animation(5336));
                        counter++;
                        break;
                    case 2:
                        p.getDialogueInterpreter().sendDialogue("You watch as the sheep collapses dead onto the floor.");
                        counter++;
                        break;
                    case 3:
                        n.getProperties().setTeleportLocation(n.spawnLocation);
                        GroundItemManager.create(SheepHerder.boneMap.get(n.getId()),n.getLocation(),p);
                        return true;
                }
                return false;
            }
        };
        p.getPulseManager().run(new MovementPulse(p, DestinationFlag.ENTITY.getDestination(p,n)) {
            @Override
            public boolean pulse() {
                p.faceLocation(n.getLocation());
                GameWorld.Pulser.submit(deathPulse);
                return true;
            }
        });
    }


    private boolean withinBorders(Location toCheck, Location sW, Location nE){
        return (toCheck.getX() >= sW.getX() && toCheck.getX() <= nE.getX()) && (toCheck.getY() <= sW.getY() && toCheck.getY() >= nE.getY());
    }

    @Override
    public Object fireEvent(String identifier, Object... args) {
        return null;
    }
}
