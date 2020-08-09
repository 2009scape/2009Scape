package plugin.consumable.effects;

import core.game.node.entity.player.Player;
import plugin.consumable.ConsumableEffect;

public class EnergyEffect extends ConsumableEffect {
    double amt;
    public EnergyEffect(int amt){
        this.amt = amt;
    }
    @Override
    public void activate(Player p) {
        if(amt == -1){
            p.getSettings().updateRunEnergy(-Math.floor(p.getSettings().getRunEnergy() * 0.2));
        } else {
            p.getSettings().updateRunEnergy(-amt);
        }
    }
}
