package plugin.blackjack;
import core.game.node.entity.player.Player;

public class BlackJackPlayer{
    Player p;
    boolean stayed = false;
    boolean updated = false;
    boolean accepted = false;
    int score = 0;
    int initialScore = 0;
    public BlackJackPlayer(Player player){
        this.p = player;
    }
}