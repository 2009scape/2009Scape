package plugin.quest.members.fishingcontest;

import core.game.interaction.MovementPulse;
import core.game.node.Node;
import core.game.node.entity.player.Player;
import core.game.system.task.Pulse;
import core.game.world.GameWorld;
import core.game.world.map.Location;
import core.game.world.update.flag.context.Animation;
import core.plugin.InitializablePlugin;
import core.plugin.Plugin;
import plugin.quest.PluginInteraction;
import plugin.quest.PluginInteractionManager;

@InitializablePlugin
public class FenceInteraction extends PluginInteraction {
    @Override
    public Plugin<Object> newInstance(Object arg) throws Throwable {
        setIds(new int[]{51});
        PluginInteractionManager.register(this, PluginInteractionManager.InteractionType.OBJECT);
        return this;
    }

    @Override
    public boolean handle(Player player, Node node) {
        player.getPulseManager().run(new MovementPulse(player, node.asObject().getLocation().transform(player.getLocation().getX() == node.getLocation().getX() ? 0 : -1, 0, 0)) {
            @Override
            public boolean pulse() {
                GameWorld.submit(new SqueezePulse(player));
                return true;
            }
        }, "movement");
        return true;
    }

    @Override
    public Object fireEvent(String identifier, Object... args) {
        return null;
    }

    public class SqueezePulse extends Pulse {
        Player player;
        Location start,end;
        Animation walk;
        public SqueezePulse(Player p){
            this.player = p;
            if(player.getLocation().equals(Location.create(2661, 3500, 0))){
                start = Location.create(2661, 3500, 0);
                end = Location.create(2662, 3500, 0);
            } else {
                start = Location.create(2662, 3500, 0);
                end = Location.create(2661, 3500, 0);
            }
            walk = new Animation(2240);
        }
        int counter = 0;

        @Override
        public boolean pulse() {
            switch(counter++){
                case 0:
                    player.getAnimator().reset();
                    player.lock();
                    player.faceLocation(end);
                    break;
                case 1:
                    player.getAnimator().animate(new Animation(2594));
                    break;
                case 2:
                    player.getProperties().setTeleportLocation(end);
                    player.getAnimator().animate(new Animation(2595));
                    break;
                case 4:
                    player.unlock();
                    return true;
            }
            return false;
        }
    }
}
