package plugin.blackjack;

import core.game.node.entity.player.Player;
import core.game.node.item.Item;
import core.game.system.SystemLogger;
import core.game.world.repository.Repository;

import java.util.ArrayList;
import java.util.List;

public class BlackJackMultiSession extends BlackJackSession{
    private int aiScore,betAmt;
    private int pot;
    private boolean ongoing = true;
    public boolean AIStayed = false;
    public boolean updated = false;
    public boolean started;

    List<Player> INVITED_PLAYERS = new ArrayList<>();
    List<BlackJackPlayer> PLAYERS = new ArrayList<>();
    List<BlackJackPlayer> topScorers = new ArrayList<>();


    public BlackJackMultiSession(int betAmount, Player... players){
        for(Player p : players){
            PLAYERS.add(new BlackJackPlayer(p));
        }
        this.betAmt = betAmount;
    }

    public int getNewHit(){
        int roundScore = BlackJackCardFactory.getNewCard().ordinal() + 1;
        if(roundScore > 10){
            roundScore = 10;
        }
        return roundScore;
    }

    public void doRoundActions(BlackJackPlayer p){
        Player player = p.p;
        if(!p.stayed){
            p.score += getNewHit();
        }
        if(p.score > 21)
            p.stayed = true;
        if(p.score == 21)
            p.stayed = true;

        p.updated = false;
        if(ongoing) {
            player.sendMessage("You: " + p.score);
            List<BlackJackPlayer> OTHERS = new ArrayList<>(PLAYERS);
            OTHERS.remove(p);
            for(BlackJackPlayer b : OTHERS){
                player.sendMessage(b.p.getUsername() + ": " + b.initialScore);
            }
            player.sendMessage("Dealer: " + aiScore);
            player.sendMessage("Would you like to hit or stay?");
        } else {
            while(!AIStayed){
                if(aiScore <= 16){
                    aiScore += getNewHit();
                } else {
                    AIStayed = true;
                }
            }
            String victor = getVictor();
            SystemLogger.log("POT " + pot);
            player.sendMessage("You: " + p.score);
            List<BlackJackPlayer> OTHERS = new ArrayList<>(PLAYERS);
            OTHERS.remove(p);
            for(BlackJackPlayer b : OTHERS){
                player.sendMessage(b.p.getUsername() + ": " + b.score);
            }
            player.sendMessage("Dealer: " + getAiScore());
            player.sendMessage("You " + (victor.equals(player.getUsername()) ? "win" : (topScorers.contains(p) && aiScore <= topScorers.get(0).score ? "tied" : "lose")) + "!");
            if(victor.equals(player.getUsername())){
                player.getInventory().add(new Item(995,pot));
                return;
            }else if(victor.equals("tie")){
                if(topScorers.contains(p)){
                    player.getInventory().add(new Item(995,pot / topScorers.size()));
                    return;
                }
            } else if(victor.equals("ai")){
                    return;
            } else if(topScorers.contains(p)) {
                player.getInventory().add(new Item(995, getBetAmt()));
                return;
            } else if (victor.equals("draw")) {
                player.getInventory().add(new Item(995,betAmt));
                return;
            } else if(victor.equals("UNHANDLED")) {
                SystemLogger.log("UNHANDLED SITUATION:");
                SystemLogger.log("DEALER: " + aiScore);
                for(BlackJackPlayer w : PLAYERS) {
                    SystemLogger.log(w.p.getUsername() + ": " + w.score);
                }
                return;
            } else {
                Repository.getPlayer(victor).getInventory().add(new Item(995,pot));
                return;
            }
        }
    }

    public String getVictor(){
        int highestScore = 0;
        topScorers = new ArrayList<>();
        for(BlackJackPlayer b : PLAYERS){
            if(b.score > highestScore && b.score < 22){
                highestScore = b.score;
                if(topScorers.size() > 0){
                    topScorers.clear();
                }
                topScorers.add(b);
            } else if (b.score == highestScore){
                topScorers.add(b);
            }
        }


        if(aiScore > highestScore && aiScore < 22){ //AI > others
            return "ai";
        }
        if(highestScore > aiScore && highestScore < 22){ //others > AI
            if(topScorers.size() > 1){                      //MULTIPLE > AI
                return "tie";
            } else if (topScorers.size() == 1){            //SINGLE > AI
                return topScorers.get(0).p.getUsername();
            }
        }
        if (aiScore == highestScore && aiScore < 22){  //OTHERS == AI
            if(aiScore == 21){                              //OTHERS & AI == 21
                return "draw";
            }
            return "ai";                               //OTHERS & AI < 21
        }
        if(aiScore > 21 && topScorers.size() == 0){   //EVERYONE > 21
            return "ai";
        }
        if(aiScore > 21){
            if(topScorers.size() > 1){
                return "tie";
            } else {
                return topScorers.get(0).p.getUsername();
            }
        }
        return "UNHANDLED";
    }

    public boolean tick(){
        if(started) {
            ongoing = false;
            for (BlackJackPlayer b : PLAYERS) {
                if (!b.stayed) {
                    ongoing = true;
                    break;
                }
            }
            for (BlackJackPlayer b : PLAYERS) {
                if ((b.updated && started) || !ongoing)
                    doRoundActions(b);
            }
        } else {
            started = true;
            for(BlackJackPlayer b : PLAYERS){
                if(!b.accepted)
                    started = false;
            }
            if(started){
                aiScore += getNewHit();
                for(BlackJackPlayer b : PLAYERS) {
                    pot += betAmt;
                    b.score += getNewHit();
                    b.initialScore = b.score;
                }
                for(BlackJackPlayer b : PLAYERS) {
                    List<BlackJackPlayer> OTHERS = new ArrayList<>(PLAYERS);
                    OTHERS.remove(b);
                    b.p.sendMessage("BlackJack started, initial scores: ");
                    b.p.sendMessage("You: " + b.score);
                    for (BlackJackPlayer j : OTHERS) {
                        b.p.sendMessage(j.p.getUsername() + ": " + j.initialScore);
                    }
                    b.p.sendMessage("Dealer: " + aiScore);
                    b.p.sendMessage("Would you like to hit or stay?");
                }
            }
        }
        return ongoing;
    }

    public int getAiScore() {
        return aiScore;
    }

    public void setUpdated(boolean update){
        updated = update;
    }

    public void init(){
        for(BlackJackPlayer b : PLAYERS){
            INVITED_PLAYERS.add(b.p);
            b.p.sendMessage("You have been invited to a multiplayer BlackJack game.");
            b.p.sendMessage("Type '::bj accept' to accept");
        }
        BlackJackManager.register(this);
    }

    public BlackJackPlayer getPlayer(Player p){
        for(BlackJackPlayer b : PLAYERS){
            if(b.p.equals(p)){
                return b;
            }
        }
        return null;
    }

    public int getBetAmt() {
        return betAmt;
    }
}
