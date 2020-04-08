package plugin.skill.cooking;

import org.crandor.cache.def.impl.ItemDefinition;
import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.entity.player.link.RunScript;
import org.crandor.game.node.object.GameObject;
import org.crandor.net.packet.PacketRepository;
import org.crandor.net.packet.context.ChildPositionContext;
import org.crandor.net.packet.out.RepositionChild;

public class FoodCookingDialogue extends DialoguePlugin {
    public static final int DialogueID = 1238503;
    private int initial,product;
    private boolean sinew = false;
    private GameObject object;

    public FoodCookingDialogue(){
        /**
         * Empty
         */
    }
    public FoodCookingDialogue(Player player){super(player);}

    @Override
    public DialoguePlugin newInstance(Player player) {
        return new FoodCookingDialogue(player);
    }

    @Override
    public boolean open(Object... args) {
        stage = 0;
        switch(args.length){
            case 2:
                initial = (int) args[0];
                if(CookableItems.intentionalBurn(initial)){ // checks intentional burning
                    product = CookableItems.getIntentionalBurn(initial).getId();
                } else {
                    product = CookableItems.forId(initial).cooked;

                }
                object = (GameObject) args[1];
                break;
            case 4:
                initial = (int) args[0];
                product = (int) args[1];
                sinew = (boolean) args[2];
                object = (GameObject) args[3];
                if(sinew){
                    player.getDialogueInterpreter().sendOptions("Select one","Dry the meat into sinew","Cook the meat");
                    stage = 100;
                    return true;
                }
                break;
        }
        display();
        return true;
    }

    @Override
    public boolean handle(int interfaceId, int buttonId) {
        switch(stage){
            case 0:
                end();
                int amount = getAmount(buttonId);
                switch(amount){
                    case -1:
                        player.setAttribute("runscript", new RunScript() {
                            @Override
                            public boolean handle() {
                                int amount = (int) value;
                                CookingRewrite.cook(player, object, initial, product, amount);
                                return false;
                            }
                        });
                        player.getDialogueInterpreter().sendInput(false,"Enter an amount:");
                        break;
                    default:
                        end();
                        CookingRewrite.cook(player,object,initial,product,amount);
                        break;
                }
                break;
            case 100:
                switch(buttonId){
                    case 1:
                        CookingRewrite.cook(player,object,initial,product,1);
                        break;
                    case 2:
                        product = CookableItems.forId(initial).cooked;
                        display();
                        break;
                }
        }
        return true;
    }

    private final int getAmount(final int buttonId) {
        switch (buttonId) {
            case 5:
                return 1;
            case 4:
                return 5;
            case 3:
                return -1;
            case 2:
                return player.getInventory().getAmount(initial);
        }
        return -1;
    }

    public void display() {
        player.getInterfaceManager().openChatbox(307);
        PacketRepository.send(RepositionChild.class, new ChildPositionContext(player, 307, 3, 60, 90));
        PacketRepository.send(RepositionChild.class, new ChildPositionContext(player,307,2,208,20));
        player.getPacketDispatch().sendItemZoomOnInterface(product, 160, 307, 2);
        player.getPacketDispatch().sendString(ItemDefinition.forId(product).getName(), 307, 3);
        stage = 0;
    }

    @Override
    public int[] getIds() {
        return new int[] {DialogueID};
    }
}
