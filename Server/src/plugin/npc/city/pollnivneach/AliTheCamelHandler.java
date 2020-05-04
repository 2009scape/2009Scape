package plugin.npc.city.pollnivneach;

import org.crandor.cache.def.impl.NPCDefinition;
import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.interaction.OptionHandler;
import org.crandor.game.node.Node;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.system.task.Pulse;
import org.crandor.game.world.GameWorld;
import org.crandor.game.world.update.flag.context.Animation;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.plugin.Plugin;
import org.crandor.tools.RandomFunction;

/**
 * Handles Ali the Camel interaction
 * @author ceik
 */
@InitializablePlugin
public class AliTheCamelHandler extends OptionHandler {
    public Plugin<Object> newInstance(Object arg) throws Throwable {
        new AliTheCamelDialogue().init();
        NPCDefinition.forId(1873).getConfigurations().put("option:pet",this);
        return this;
    }

    public boolean handle(Player player, Node node, String options){
        NPC npc = (NPC) node;
        if(npc.getId() == 1873){
            if (options.equals("pet")){
                player.getDialogueInterpreter().open(1873);
            }
        }
        return true;
    }

    public class AliTheCamelDialogue extends DialoguePlugin {
        public AliTheCamelDialogue(){
            /**
             * Empty
             */
        }
        public AliTheCamelDialogue(Player player){super(player);}

        @Override
        public DialoguePlugin newInstance(Player player){return new AliTheCamelDialogue(player);}

        @Override
        public boolean open(Object... args){
            int phrase = RandomFunction.random(1,4);
            switch(phrase){
                case 1:
                    player(FacialExpression.AFRAID,"That beast would probably bite my fingers off","if I tried to pet it");
                    break;
                case 2:
                    player(FacialExpression.DISGUSTED,"I'm not going to pet that! I might get fleas","or something else that nasty creature","might have.");
                    break;
                case 3:
                    player(FacialExpression.THINKING,"Mmmm... Won't you make the nicest kebab?");
                    break;
            }
            return true;
        }
        @Override
        public boolean handle(int interfaceId, int buttonId) {
            switch (stage) {
                case 0:
                    player.lock();
                    player.animate(new Animation(7299));
                    player.getImpactHandler().setDisabledTicks(3);
                    GameWorld.Pulser.submit(new Pulse(4, player) {
                        @Override
                        public boolean pulse() {
                            player.unlock();
                            player.getAnimator().reset();
                            return true;
                        }
                    });
                    player.getDialogueInterpreter().sendDialogue("The camel tries to kick you for insulting it.");
                    stage = 1;
                    break;
                case 1:
                    end();
                    break;
            }
            return true;
        }
        @Override
        public int[] getIds(){return new int[] {1873};}
    }
}
