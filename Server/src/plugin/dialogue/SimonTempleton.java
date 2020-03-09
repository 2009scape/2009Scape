package plugin.dialogue;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.game.node.entity.player.Player;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.node.item.Item;

/**
 * Handles the dialog for Simon Templeton
 * @author ceik
 */
@InitializablePlugin
public final class SimonTempleton extends DialoguePlugin{
    public final Item[][] ARTIFACTS = { {new Item(9032),new Item(9036), new Item(9026)}, {new Item(9042), new Item(9030), new Item(9038)}, {new Item(9040), new Item(9028), new Item(9034)} };
    public SimonTempleton(){
        /**
         * Empty on purpose
         */
    }

    public SimonTempleton(Player player) {
        super(player);
    }

    @Override
    public DialoguePlugin newInstance(Player player) {
        return new SimonTempleton(player);
    }

    @Override
    public boolean open(Object... args) {
        npc = (NPC) args[0];
        //Handles what happens when using a pharoah scepter on him (required for accuracy)
        if(args.length == 4){
            if(((int) args[3] == 9044 || (int) args[3] == 9046 || (int) args[3] == 9048 || (int) args[3] == 9050)) {
                npc("You sellin' me this gold colored", "stick thing. Looks fake to me.", "I'll give you 100 gold for it.");
                stage = 30;
                return true;
            }
        }
        player.getPacketDispatch().sendMessage("" + args.length);
        npc("G'day, mate!. Got any new", "pyramid artefacts for me?");
        return true;
    }

    @Override
    public boolean handle(int interfaceId, int buttonId){
        boolean hasArtefacts = false;
        switch(stage){
            case 0:
                for(int i = 0; i < ARTIFACTS.length; i++){
                    for(int j = 0; j < 3; j++){
                        if(player.getInventory().containsItem(ARTIFACTS[i][j])){
                            hasArtefacts = true;
                            break;
                        }
                    }
                }
                if(hasArtefacts){
                    player("Why, yes I do!");
                    stage = 1;
                    break;
                }
                player("No, I haven't.");
                stage = 3;
                break;
            case 1:
                npc("Excellent! I would like to buy them.");
                stage = 2;
                break;
            case 2:
                interpreter.sendOptions("Sell artefacts?","Yes, I need money!!","No, I'll hang onto them.");
                stage = 10;
                break;
            case 3:
                npc("Well, keep my offer in mind", "if you do find one.");
                stage = 4;
                break;
            case 4:
                player("I will. Goodbye, Simon.");
                stage = 5;
                break;
            case 5:
                npc("Bye, mate.");
                stage = 999;
                break;
            case 10:
                switch(buttonId){
                    case 1:
                        interpreter.sendOptions("Which ones do you want to sell?", "Clay and Ivory", "Stone","Gold","Sell them all!");
                        stage = 20;
                        break;
                    case 2:
                        npc("Aww, alright. Well, keep my", "offer in mind, will ya?");
                        player("Sure thing, Simon.");
                        npc("Thanks, mate.");
                        stage = 999;
                        break;
                }
                break;
            case 20:
                switch(buttonId){
                    case 1:
                        for(int j = 0; j < 28; j++){
                            switch(player.getInventory().getId(j)){
                                case 9032:
                                    player.getInventory().remove(new Item(9032,j));
                                    player.getInventory().add(new Item(995, 75));
                                    break;
                                case 9036:
                                    player.getInventory().remove(new Item(9036,j));
                                    player.getInventory().add(new Item(995, 100));
                                    break;
                                case 9026:
                                    player.getInventory().remove(new Item(9026,j));
                                    player.getInventory().add(new Item(995, 50));
                                    break;
                            }
                        }
                        stage = 999;
                        break;
                    case 2:
                        for(int j = 0; j < 28; j++){
                            switch(player.getInventory().getId(j)){
                                case 9042:
                                    player.getInventory().remove(new Item(9042,j));
                                    player.getInventory().add(new Item(995, 150));
                                    break;
                                case 9030:
                                    player.getInventory().remove(new Item(9030,j));
                                    player.getInventory().add(new Item(995, 175));
                                    break;
                                case 9038:
                                    player.getInventory().remove(new Item(9038,j));
                                    player.getInventory().add(new Item(995, 200));
                                    break;
                            }
                        }
                        stage = 999;
                        break;
                    case 3:
                        for(int j = 0; j < 28; j++){
                            switch(player.getInventory().getId(j)){
                                case 9040:
                                    player.getInventory().remove(new Item(9040,j));
                                    player.getInventory().add(new Item(995, 750));
                                    break;
                                case 9028:
                                    player.getInventory().remove(new Item(9028,j));
                                    player.getInventory().add(new Item(995, 1000));
                                    break;
                                case 9034:
                                    player.getInventory().remove(new Item(9034,j));
                                    player.getInventory().add(new Item(995, 1250));
                                    break;
                            }
                        }
                        stage = 999;
                        break;
                    case 4:
                        for(int j = 0; j < 28; j++){
                            switch(player.getInventory().getId(j)){
                                case 9040:
                                    player.getInventory().remove(new Item(9040,j));
                                    player.getInventory().add(new Item(995, 750));
                                    break;
                                case 9028:
                                    player.getInventory().remove(new Item(9028,j));
                                    player.getInventory().add(new Item(995, 1000));
                                    break;
                                case 9034:
                                    player.getInventory().remove(new Item(9034,j));
                                    player.getInventory().add(new Item(995, 1250));
                                    break;
                                case 9042:
                                    player.getInventory().remove(new Item(9042,j));
                                    player.getInventory().add(new Item(995, 150));
                                    break;
                                case 9030:
                                    player.getInventory().remove(new Item(9030,j));
                                    player.getInventory().add(new Item(995, 175));
                                    break;
                                case 9038:
                                    player.getInventory().remove(new Item(9038,j));
                                    player.getInventory().add(new Item(995, 200));
                                    break;
                                case 9032:
                                    player.getInventory().remove(new Item(9032,j));
                                    player.getInventory().add(new Item(995, 75));
                                    break;
                                case 9036:
                                    player.getInventory().remove(new Item(9036,j));
                                    player.getInventory().add(new Item(995, 100));
                                    break;
                                case 9026:
                                    player.getInventory().remove(new Item(9026,j));
                                    player.getInventory().add(new Item(995, 50));
                                    break;
                            }
                        }
                        stage = 999;
                        break;
                }
            case 30:
                player("What! This is a genuine pharaoh's scepter - made out of"," solid gold and finely jewelled with precious gems"," by the finest craftsmen in the area.");
                stage = 31;
                break;
            case 31:
                npc("Strewth! I can tell a pile of croc when I hear it!","You've got the patter mate, but I'm no mug.", "That's a fake.");
                stage = 32;
                break;
            case 32:
                player("It has magical powers!");
                stage = 33;
                break;
            case 33:
                npc("Oh, magical powers... yeah yeah yeah. Heard it all before"," mate. I'll give you 100 gold, or some 'magic beans'.", "Take it or leave it.");
                stage = 34;
                break;
            case 34:
                player("I don't think so! I'll find someone who'll give me", "what it's worth.");
                stage = 35;
                break;
            case 35:
                npc("Suit yerself...");
                stage = 999;
                break;
            case 999:
                end();
                break;
        }
        return true;
    }
    @Override
    public int[] getIds() {
        return new int[] { 3123 };
    }
}
