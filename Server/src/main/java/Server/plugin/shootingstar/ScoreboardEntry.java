package plugin.shootingstar;

public class ScoreboardEntry{
    public String playerName;
    public int time;
    public ScoreboardEntry(String player, int time){
        this.playerName = player;
        this.time = time;
    }
}
