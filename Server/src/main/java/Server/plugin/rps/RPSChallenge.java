package plugin.rps;

import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.emote.Emotes;
import core.game.node.item.Item;
import core.game.system.SystemLogger;
import core.game.world.repository.Repository;
import core.game.world.update.flag.context.Animation;
import core.tools.ItemNames;
import plugin.dialogue.SkillDialogueHandler;

public class RPSChallenge {
    private String initiatingPlayer;
    private String challengedPlayer;
    private int timeOut;
    private boolean accepted,initiated;
    public boolean finished = false;
    private int ticks = 0;
    private Player p1,p2;
    int player1choice = -1;
    int player2choice = -1;
    int betAmount;
    public boolean gamble = false;
    Player winner;


    public RPSChallenge(String initiatingPlayer, String challengedPlayer){
        this.initiatingPlayer = initiatingPlayer;
        this.challengedPlayer = challengedPlayer;
        this.timeOut = 100;
    }

    public RPSChallenge(String initiatingPlayer, String challengedPlayer, int amount){
        this(initiatingPlayer,challengedPlayer);
        gamble = true;
        betAmount = amount;
    }

    public String getChallengedPlayer(){
        return challengedPlayer;
    }

    public String getInitiatingPlayer(){
        return initiatingPlayer;
    }

    public void acceptChallenge(){
        accepted = true;
        Item[] items = new Item[]{new Item(ItemNames.ROCK_4043), new Item(ItemNames.NEWSPAPER_11169), new Item(ItemNames.SHEARS_1735)};
        p1 = Repository.PLAYER_NAMES.get(initiatingPlayer.toLowerCase());
        p2 = Repository.PLAYER_NAMES.get(challengedPlayer.toLowerCase());
        SkillDialogueHandler handler1 = new SkillDialogueHandler(p1, SkillDialogueHandler.SkillDialogue.THREE_OPTION,items){
            @Override
            public void create(int amount, int index) {
                player1choice = index;
                SystemLogger.log("Got player 1 choice: " + index);
            }
        };
        SkillDialogueHandler handler2 = new SkillDialogueHandler(p2, SkillDialogueHandler.SkillDialogue.THREE_OPTION,items){
            @Override
            public void create(int amount, int index) {
                player2choice = index;
                SystemLogger.log("Got player 2 choice: " + index);
            }
        };
        handler1.open();
        handler2.open();
    }

    public boolean tick(){
        if(!accepted){
            timeOut -= 1;
        } else if(!initiated && (player1choice != -1 && player2choice != -1)){
            initiated = true;
        }
        if(initiated){
            doTickAction(p1,p2);
        }
        if(timeOut <= 0){
            finished = true;
        }
        return true;
    }

    private void doTickAction(Player p1, Player p2){
        ticks++;
        switch(ticks){
            case 1:
                p1.faceLocation(p2.getLocation());
                p2.faceLocation(p1.getLocation());
                p1.sendChat("3");
                p2.sendChat("3");
                break;
            case 2:
                p1.sendChat("2");
                p2.sendChat("2");
                break;
            case 3:
                p1.sendChat("1");
                p2.sendChat("1");
                break;
            case 4:
                p1.sendChat(getType(player1choice));
                p2.sendChat(getType(player2choice));
                break;
            case 5:
                if(player1choice < player2choice){
                    if(player1choice == 0 && player2choice == 2){
                        p1.sendChat("I win!");
                        p1.lock(1);
                        p2.lock(1);
                        p1.getAnimator().animate(new Animation(1333));
                        p2.getAnimator().animate(Emotes.CRY.getAnimation());
                        winner = p1;
                    } else {
                        p2.sendChat("I win!");
                        p2.lock(1);
                        p2.getAnimator().animate(Emotes.CHEER.getAnimation());
                        p1.lock(1);
                        p1.getAnimator().animate(Emotes.CRY.getAnimation());
                        winner = p2;
                    }
                } else if(player2choice < player1choice){
                    if(player2choice == 0 && player1choice == 2){
                        p2.sendChat("I win!");
                        p2.lock(1);
                        p1.lock(1);
                        p2.getAnimator().animate(new Animation(1333));
                        p1.getAnimator().animate(Emotes.CRY.getAnimation());
                        winner = p2;
                    } else {
                        p1.sendChat("I win!");
                        p1.lock(1);
                        p1.getAnimator().animate(Emotes.CHEER.getAnimation());
                        p2.lock(1);
                        p2.getAnimator().animate(Emotes.CRY.getAnimation());
                        winner = p1;
                    }
                } else if(player2choice == player1choice){
                    p1.sendChat("Draw!");
                    p2.sendChat("Draw!");
                    p1.lock(1);
                    p2.lock(1);
                    p1.getAnimator().animate(Emotes.BECKON.getAnimation());
                    p2.getAnimator().animate(Emotes.BECKON.getAnimation());
                    winner = null;
                }
                break;
            case 6:
                if(gamble){
                    if(winner == p1){
                        p2.getInventory().remove(new Item(995,betAmount));
                    }
                    if(winner == p2){
                        p1.getInventory().remove(new Item(995,betAmount));
                    }
                    if(winner != null) {
                        winner.getInventory().add(new Item(995, betAmount));
                    }
                }
                finished = true;
                break;
        }
    }

    public String getType(int index){
        switch(index){
            case 0:
                return "Rock";
            case 1:
                return "Paper";
            case 2:
                return "Scissors";
        }
        return null;
    }
}
