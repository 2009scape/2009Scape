package plugin.interaction.item.toys;

import core.cache.def.impl.ItemDefinition;
import core.game.component.Component;
import core.game.component.ComponentDefinition;
import core.game.component.ComponentPlugin;
import core.game.interaction.OptionHandler;
import core.game.node.Node;
import core.game.node.entity.player.Player;
import core.game.node.item.Item;
import core.game.system.task.Pulse;
import core.game.world.update.flag.context.Animation;
import core.game.world.update.flag.context.Graphics;
import core.plugin.InitializablePlugin;
import core.plugin.Plugin;
import core.plugin.PluginManager;
import core.tools.ItemNames;

@InitializablePlugin
public class SnowGlobePlugin extends OptionHandler {

    private final Animation SHAKE = new Animation(7535);//Initial Shake
    private final Animation HOLDFACE = new Animation(7536);//Immediately after shake, player holds the snow globe to face
    private static final Component INTERFACE = new Component(659);//After HOLDFACE this interface is displayed, player either clicks 'continue' for inv of snowballs, or 'close' for no snowballs
    private final Animation DOWNFAST = new Animation(7537);//Used when player hit 'close' on the interface
    private final Animation DOWNSLOW = new Animation(7538);//Used when the player hit 'continue' on the interface
    private final Animation STOMP = new Animation(7528);//When player hits continue this animation plays
    private final Graphics SNOW = new Graphics(1284);//When Animation STOMP is playing this gfx also plays

    @Override
    public Plugin<Object> newInstance(Object arg) throws Throwable {
        PluginManager.definePlugin(new SnowGlobeInterface());
        ItemDefinition.forId(11949).getConfigurations().put("option:shake",this);
        return this;
    }

    @Override
    public boolean handle(Player player, Node node, String option) {
        player.getLocks().lockInteractions(2);
        player.getAnimator().animate(SHAKE);
        player.getPulseManager().run(new Pulse(3) {
            @Override
            public boolean pulse() {
                player.getAnimator().animate(HOLDFACE);
                player.getPulseManager().run(new Pulse(1){
                    @Override
                    public boolean pulse(){
                        player.getInterfaceManager().open(INTERFACE);
                        return true;
                    }
                });
                return true;
            }
        });
        return true;
    }

    public class SnowGlobeInterface extends ComponentPlugin{
        @Override
        public Plugin<Object> newInstance(Object arg) throws Throwable {
            ComponentDefinition.put(INTERFACE.getId(),this);
            return this;
        }

        @Override
        public boolean handle(Player player, Component component, int opcode, int button, int slot, int itemId) {
            if(button == 2){
                player.getInterfaceManager().close();
                player.getAnimator().animate(DOWNSLOW);
                player.getPulseManager().run(new Pulse(1) {
                    @Override
                    public boolean pulse() {
                        player.getAnimator().animate(STOMP,SNOW);
                        player.getInventory().add(new Item(11951,player.getInventory().freeSlots()));
                        return true;
                    }
                });
                return true;
            }
            player.getAnimator().animate(DOWNFAST);
            return true;
        }
    }
}
