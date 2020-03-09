package plugin.interaction.item;

import org.crandor.cache.def.impl.ItemDefinition;
import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.interaction.OptionHandler;
import org.crandor.game.node.Node;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.entity.player.info.portal.Perks;
import org.crandor.game.node.item.Item;
import org.crandor.game.system.task.Pulse;
import org.crandor.game.world.GameWorld;
import org.crandor.game.world.map.Location;
import org.crandor.game.world.update.flag.context.Animation;
import org.crandor.game.world.update.flag.context.Graphics;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.plugin.Plugin;


/**
 * Adds functionality to the pharoah's scepter
 * @author ceik
 */
@InitializablePlugin
public final class PharoahSceptre extends OptionHandler {
    private static final Graphics GRAPHICS = new Graphics(308, 100, 50);
    private static final Animation ANIMATION = new Animation(714);

    @Override
    public Plugin<Object> newInstance(Object arg) throws Throwable {
        new SceptreDialog().init();
        ItemDefinition.forId(9044).getConfigurations().put("option:teleport",this);
        ItemDefinition.forId(9046).getConfigurations().put("option:teleport",this);
        ItemDefinition.forId(9048).getConfigurations().put("option:teleport",this);
        ItemDefinition.forId(9050).getConfigurations().put("option:teleport",this);
        return this;
    }
    @Override
    public boolean handle(Player player, Node node, String string){
        Item item = node.asItem();
        if(string.equals("teleport")){
            switch(item.getId()) {
                case 9044:
                case 9046:
                case 9048:
                    player.getDialogueInterpreter().open(325128389);
                    return true;
                case 9050:
                    player.getPacketDispatch().sendMessage("You have used up all the charges on this sceptre.");
                    return true;
            }
        }
        return true;
    }

    public final class SceptreDialog extends DialoguePlugin {
        public SceptreDialog() {
            /**
             * empty
             */
        }
        public SceptreDialog(Player player){super(player);}

        @Override
        public DialoguePlugin newInstance(Player player){ return new SceptreDialog(player);}

        @Override
        public boolean open(Object... args){
            //Item item = (Item) args[0];
            player.getPacketDispatch().sendMessage("" + args[0]);
            interpreter.sendOptions("Teleport to:","Jalsavrah","Jaleustrophos","Jaldraocht","Nowhere");
            return true;
        }
        public void teleport(Location location, Player player){
            player.lock();
            player.visualize(ANIMATION, GRAPHICS);
            player.getImpactHandler().setDisabledTicks(4);
            GameWorld.submit(new Pulse(4, player) {
                @Override
                public boolean pulse() {
                    player.unlock();
                    player.getProperties().setTeleportLocation(location);
                    player.getAnimator().reset();
                    return true;
                }
            });
        }
        @Override
        public boolean handle(int interfaceId, int buttonId){
            if (player.isTeleBlocked()) {
                player.sendMessage("A magical force has stopped you from teleporting.");
                return false;
            }
            switch(buttonId){
                case 1:
                    teleport(Location.create(3289,2786,0), player);
                    stage = 10;
                    break;
                case 2:
                    teleport(Location.create(3342,2827,0),player);
                    stage = 10;
                    break;
                case 3:
                    teleport(Location.create(3233,2902,0),player);
                    stage = 10;
                    break;
                case 4:
                    stage = 20;
                    break;
            }
          switch(stage){
              case 10:
                  if(player.getInventory().containsItem(new Item(9044))){
                      player.getInventory().remove(new Item(9044));
                      player.getInventory().add(new Item(9046));
                      player.getPacketDispatch().sendMessage("<col=7f03ff>Your Pharoah's Sceptre has 2 charges remaining.");
                      end();
                  } else if (player.getInventory().containsItem(new Item(9046))){
                      player.getInventory().remove(new Item(9046));
                      player.getInventory().add(new Item(9048));
                      player.getPacketDispatch().sendMessage("<col=7f03ff>Your Pharoah's Sceptre has 1 charge remaining.");
                      end();
                  } else if (player.getInventory().containsItem(new Item(9048))){
                      player.getInventory().remove(new Item(9048));
                      player.getInventory().add(new Item(9050));
                      player.sendMessage("<col=7f03ff>Your Pharoah's Sceptre has used its last charge.");
                      end();
                  }
                  stage = 20;
                  break;
              case 20:
                  end();
                  break;
          }
            return true;
        }
        public int[] getIds() { return new int[] {325128389};}
    }

}
