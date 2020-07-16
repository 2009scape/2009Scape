package plugin.blackjack;

import core.game.node.entity.player.Player;
import core.game.node.item.Item;

public class BlackJackSingleSession extends BlackJackSession{
    private Player player;
    private int playerScore,aiScore,betAmt;
    private boolean ongoing = true;
    public boolean playerStayed,AIStayed = false;
    public boolean updated = false;


    public BlackJackSingleSession(Player player, int betAmount){
        this.player = player;
        this.betAmt = betAmount;
    }

    public int getNewHit(){
        int roundScore = BlackJackCardFactory.getNewCard().ordinal() + 1;
        if(roundScore > 10){
            roundScore = 10;
        }
        return roundScore;
    }

    public void doRoundActions(){
        if(!playerStayed){
            playerScore += getNewHit();
        }
        if(playerScore > 21)
            ongoing = false;
        if(playerScore == 21)
            ongoing = false;

        updated = false;
        if(ongoing && !playerStayed) {
            player.sendMessage("You: " + getPlayerScore());
            player.sendMessage("AI: " + getAiScore());
            player.sendMessage("Would you like to hit or stay?");
        } else {
            ongoing = false;
            while(!AIStayed){
                if(aiScore <= 16){
                    aiScore += getNewHit();
                } else {
                    AIStayed = true;
                }
            }
            String victor = getVictor();
            player.sendMessage("You: " + getPlayerScore());
            player.sendMessage("AI: " + getAiScore());
            player.sendMessage("You " + (victor.equals("player") ? "win" : (victor.equals("tie") ? "tied" : "lose")) + "!");
        }
    }

    public String getVictor(){
        if(playerScore == aiScore){
            player.getInventory().add(new Item(995,betAmt));
            return "tie";
        }
        if(aiScore == 21){
            return "ai";
        }
        if(playerScore == 21){
            player.getInventory().add(new Item(995,betAmt * 2));
            return "player";
        }
        if(playerScore > 21){
            return "ai";
        } else if (playerScore > aiScore) {
            player.getInventory().add(new Item(995,betAmt * 2));
            return "player";
        } else if (aiScore > 21) {
            player.getInventory().add(new Item(995,betAmt * 2));
            return "player";
        } else {
            return "ai";
        }
    }

    public boolean tick(){
        if(updated){
            doRoundActions();
        }
        return ongoing;
    }

    public int getPlayerScore() {
        return playerScore;
    }

    public int getAiScore() {
        return aiScore;
    }

    public boolean isOngoing() {
        return ongoing;
    }

    public void setUpdated(boolean update){
        updated = update;
    }

    public Player getPlayer() {
        return player;
    }

    public void init(){
        BlackJackManager.register(this);
        player.sendMessage("BlackJack: Round started, initial scores:");
        aiScore += getNewHit();
        doRoundActions();
    }
}
