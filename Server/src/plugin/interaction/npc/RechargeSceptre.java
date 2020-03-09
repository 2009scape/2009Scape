package plugin.interaction.npc;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.interaction.NodeUsageEvent;
import org.crandor.game.interaction.UseWithHandler;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.item.Item;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.plugin.Plugin;

/**
 * Allows the sceptre to be recharged by using it on the Guardian Mummy
 * @author ceik
 */
@InitializablePlugin
public class RechargeSceptre extends UseWithHandler {
    Item usedSceptre = new Item(4950);
    int clayRecharge[] = new int[24];
    int stoneRecharge[] = new int[12];
    int goldRecharge[] = new int[6];
    public RechargeSceptre() {
        super(9046, 9048, 9050);
    }
    @Override
    public Plugin<Object> newInstance(Object arg) throws Throwable {
        new RechargeDialogue().init();
        addHandler(4476, NPC_TYPE, this);
        return this;
    }
    @Override
    public boolean handle(NodeUsageEvent event){
        final Player player = event.getPlayer();
        player.getDialogueInterpreter().open(999876,event.getUsedItem());
        return true;
    }
    public class RechargeDialogue extends DialoguePlugin {
        public RechargeDialogue() {
            /**
             * empty
             */
        }
        public RechargeDialogue(Player player){super(player);}

        @Override
        public DialoguePlugin newInstance(Player player){ return new RechargeDialogue(player);}

        @Override
        public boolean open(Object... args){
            usedSceptre = (Item) args[0];
            player.getPacketDispatch().sendMessage("" + args[0]);
            interpreter.sendDialogue("Mrrrh, how do you have this? You shouldn't.", "Nevertheless, I can recharge this for you.");
            return true;
        }
        @Override
        public boolean handle(int dialogId, int buttonId){
            switch(stage){
                case 0:
                    interpreter.sendOptions("Recharge sceptre?", "Yes, please.", "No, thanks.");
                    stage = 1;
                    break;
                case 1:
                    switch(buttonId){
                        case 1:
                            interpreter.sendOptions("Recharge with?","Clay/Ivory Artefacts(24)","Stone Artefacts(12)","Gold Artefacts(6)");
                            stage = 20;
                            break;
                        case 2:
                            stage = 100;
                            break;
                    }
                    break;
                case 20:
                    switch(buttonId){
                        case 1: {
                            int totalCounter = 0;
                            for (int i = 0; i < 28; i++) {
                                if (totalCounter == 24) {
                                    break;
                                }
                                int thisSlot = player.getInventory().getId(i);
                                if (thisSlot == 9026) {
                                    clayRecharge[totalCounter] = i;
                                    totalCounter += 1;
                                }
                            }
                            for (int i = 0; i < 28; i++) {
                                if (totalCounter == 24) {
                                    break;
                                }
                                int thisSlot = player.getInventory().getId(i);
                                if (thisSlot == 9032) {
                                    clayRecharge[totalCounter] = i;
                                    totalCounter += 1;
                                }
                            }
                            for (int i = 0; i < 28; i++) {
                                if (totalCounter == 24) {
                                    break;
                                }
                                int thisSlot = player.getInventory().getId(i);
                                if (thisSlot == 9036) {
                                    clayRecharge[totalCounter] = i;
                                    totalCounter += 1;
                                }
                            }
                            if (clayRecharge[23] == 0) {
                                player.getPacketDispatch().sendMessage("You do not have enough clay/ivory artifacts.");
                                end();
                                break;
                            }
                            for (int i = 0; i < 24; i++) {
                                player.getInventory().remove(new Item(player.getInventory().getId(clayRecharge[i])), clayRecharge[i], true);
                            }
                            player.getInventory().remove(usedSceptre);
                            player.getInventory().add(new Item(9044));
                            player.getPacketDispatch().sendMessage("<col=7f03ff>Your Pharoah's Sceptre has been fully recharged!");
                            clayRecharge[23] = 0;
                            end();
                            break;
                        }
                        case 2: {
                            int totalCounter = 0;
                            for (int i = 0; i < 28; i++) {
                                if (totalCounter == 12) {
                                    break;
                                }
                                int thisSlot = player.getInventory().getId(i);
                                if (thisSlot == 9042) {
                                    stoneRecharge[totalCounter] = i;
                                    totalCounter += 1;
                                }
                            }
                            for (int i = 0; i < 28; i++) {
                                if (totalCounter == 12) {
                                    break;
                                }
                                int thisSlot = player.getInventory().getId(i);
                                if (thisSlot == 9030) {
                                    stoneRecharge[totalCounter] = i;
                                    totalCounter += 1;
                                }
                            }
                            for (int i = 0; i < 28; i++) {
                                if (totalCounter == 12) {
                                    break;
                                }
                                int thisSlot = player.getInventory().getId(i);
                                if (thisSlot == 9038) {
                                    stoneRecharge[totalCounter] = i;
                                    totalCounter += 1;
                                }
                            }
                            if (stoneRecharge[11] == 0) {
                                player.getPacketDispatch().sendMessage("You do not have enough stone artifacts.");
                                end();
                                break;
                            }
                            for (int i = 0; i < 12; i++) {
                                player.getInventory().remove(new Item(player.getInventory().getId(stoneRecharge[i])), stoneRecharge[i], true);
                            }
                            player.getInventory().remove(usedSceptre);
                            player.getInventory().add(new Item(9044));
                            player.getPacketDispatch().sendMessage("<col=7f03ff>Your Pharoah's Sceptre has been fully recharged!");
                            stoneRecharge[11] = 0;
                            end();
                            break;
                        }
                        case 3:  {
                            int totalCounter = 0;
                            for(int i = 0; i < 28; i++){
                                if(totalCounter == 6) {break;}
                                int thisSlot = player.getInventory().getId(i);
                                if(thisSlot == 9040){
                                    goldRecharge[totalCounter] = i;
                                    totalCounter += 1;
                                }
                            }
                            for(int i = 0; i < 28; i++){
                                if(totalCounter == 6) {break;}
                                int thisSlot = player.getInventory().getId(i);
                                if(thisSlot == 9028){
                                    goldRecharge[totalCounter] = i;
                                    totalCounter += 1;
                                }
                            }
                            for(int i = 0; i < 28; i++){
                                if(totalCounter == 6) {break;}
                                int thisSlot = player.getInventory().getId(i);
                                if(thisSlot == 9034){
                                    goldRecharge[totalCounter] = i;
                                    totalCounter += 1;
                                }
                            }
                            if(goldRecharge[5] == 0){player.getPacketDispatch().sendMessage("You do not have enough gold artifacts."); end(); break;}
                            for(int i = 0; i < 6; i++){
                                player.getInventory().remove( new Item (player.getInventory().getId(goldRecharge[i])), goldRecharge[i], true);
                            }
                            player.getInventory().remove(usedSceptre);
                            player.getInventory().add(new Item(9044));
                            player.getPacketDispatch().sendMessage("<col=7f03ff>Your Pharoah's Sceptre has been fully recharged!");
                            goldRecharge[5] = 0;
                            end();
                            break;
                        }
                    }
                    break;
                case 100:
                    end();
                    break;
            }
            return true;
        }
        @Override
        public int[] getIds() {
            return new int[] { 999876 };
        }
    }
}

