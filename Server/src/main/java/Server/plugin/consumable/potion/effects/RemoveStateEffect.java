package plugin.consumable.potion.effects;

import core.game.node.entity.player.Player;
import core.game.node.entity.state.EntityState;
import plugin.consumable.potion.PotionEffect;

public class RemoveStateEffect extends PotionEffect {
    int state;
    public RemoveStateEffect(int state){
        this.state = state;
    }
    @Override
    public void activate(Player p) {
        p.getStateManager().remove(EntityState.values()[state]);
    }
}
