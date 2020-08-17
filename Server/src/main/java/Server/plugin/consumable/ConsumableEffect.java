package plugin.consumable;


import core.game.node.entity.player.Player;

public abstract class ConsumableEffect {
	public abstract void activate(Player p);

	public int getHealthEffectValue(Player player) {
		return 0;
	}
}
