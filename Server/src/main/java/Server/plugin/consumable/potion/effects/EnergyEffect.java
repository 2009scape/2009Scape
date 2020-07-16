package plugin.consumable.potion.effects;

import core.game.node.entity.player.Player;
import plugin.consumable.potion.PotionEffect;

public class EnergyEffect extends PotionEffect {
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
