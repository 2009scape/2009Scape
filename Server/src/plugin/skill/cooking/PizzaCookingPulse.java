package plugin.skill.cooking;

import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.item.Item;
import org.crandor.game.node.object.GameObject;

public class PizzaCookingPulse extends StandardCookingPulse {
    GameObject object;
    Player player;

    PizzaCookingPulse(Player player, GameObject object,int initial, int product, int amount){
        super(player,object,initial,product,amount);
        this.object = object;
        this.player = player;
    }

    @Override
    public boolean checkRequirements() {
        if(!object.getName().toLowerCase().contains("range")){
            player.getPacketDispatch().sendMessage("This can only be cooked on a range.");
            return false;
        }
        return super.checkRequirements();
    }

    @Override
    public String getMessage(Item food, Item product, boolean burned) {
        if(burned){
            return "You accidentally burn the pizza.";
        } else {
            return "You cook a delicious looking pizza.";
        }
    }
}
