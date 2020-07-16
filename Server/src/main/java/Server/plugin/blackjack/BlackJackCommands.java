package plugin.blackjack;

import core.game.node.entity.player.Player;
import core.game.node.item.Item;
import core.game.system.SystemLogger;
import core.game.system.command.CommandPlugin;
import core.game.system.command.CommandSet;
import core.game.world.repository.Repository;
import core.plugin.InitializablePlugin;
import core.plugin.Plugin;
import core.plugin.PluginManifest;

import java.util.ArrayList;
import java.util.List;

@InitializablePlugin
@PluginManifest(name="Blackjack")
public class BlackJackCommands extends CommandPlugin {

    @Override
    public boolean parse(Player player, String name, String[] args) {
        switch(name){
            case "blackjack":
            case "bj":
                BlackJackSession session = BlackJackManager.get(player);
                if(args.length < 2){
                    player.sendMessage("Usage:");
                    player.sendMessage("New Game: ::bj bet amount");
                    player.sendMessage("New Multi Game: ::bj multi amount player1 player2 player3 etc");
                    player.sendMessage("Hit current: ::bj hit");
                    player.sendMessage("Stay current: ::bj stay");
                    return true;
                }
                String action = args[1];
                switch(action){
                    case "bet":
                        if(session == null) {
                            if (args.length < 3) {
                                player.sendMessage("Usage: ::bj bet amount");
                                return true;
                            }
                            try {
                                int betAmount = Integer.parseInt(args[2]);
                                if (!player.getInventory().containsItem(new Item(995, betAmount))) {
                                    player.sendMessage("You don't have enough gold.");
                                    return true;
                                }
                                new BlackJackSingleSession(player, betAmount).init();
                                player.getInventory().remove(new Item(995,betAmount));
                            } catch (Exception e) {
                                player.sendMessage("Usage: ::bj new amount");
                                return true;
                            }
                        } else {
                            player.sendMessage("You already have a game going!");
                        }
                        break;
                    case "hit":
                        if(session instanceof BlackJackSingleSession){
                            ((BlackJackSingleSession) session).setUpdated(true);
                        } else if(session != null){
                            ((BlackJackMultiSession)session).getPlayer(player).updated = true;
                        }
                        break;
                    case "stay":
                        if(session instanceof BlackJackSingleSession){
                            ((BlackJackSingleSession)session).playerStayed = true;
                            ((BlackJackSingleSession)session).setUpdated(true);
                        } else if(session != null){
                            ((BlackJackMultiSession)session).getPlayer(player).stayed = true;
                        }
                        break;
                    case "multi":
                        if(session == null) {
                            if (args.length < 5) {
                                player.sendMessage("Usage: ::bj multi amount player1 player2 etc");
                                return true;
                            }
                            try {
                                int betAmount = Integer.parseInt(args[2]);
                                if (!player.getInventory().containsItem(new Item(995, betAmount))) {
                                    player.sendMessage("You don't have enough gold.");
                                    return true;
                                }
                                List<Player> INVITED = new ArrayList<>();
                                for(int i = 3; i < args.length; i++){
                                    Player p = Repository.PLAYER_NAMES.get(args[i].toLowerCase());
                                    if(p == null){
                                        player.sendMessage("Invalid player name: " + args[i]);
                                        return true;
                                    }
                                    INVITED.add(p);
                                }
                                new BlackJackMultiSession(betAmount,INVITED.toArray(new Player[0])).init();
                                player.getInventory().remove(new Item(995,betAmount));
                            } catch (Exception e) {
                                player.sendMessage("Usage: ::bj multi amount player1 player2 etc");
                                return true;
                            }
                        } else {
                            player.sendMessage("You already have a game going!");
                        }
                        break;
                    case "accept":
                        BlackJackSession sess = BlackJackManager.get(player);
                        if(sess == null){
                            System.out.println("Can't find session.");
                            return true;
                        }
                            try {
                                if(!player.getInventory().containsItem(new Item(995,((BlackJackMultiSession)sess).getBetAmt()))){
                                    player.sendMessage("You don't have enough gold to join this. (" + ((BlackJackMultiSession)sess).getBetAmt() + ")");
                                    return true;
                                }
                                ((BlackJackMultiSession) sess).getPlayer(player).accepted = true;
                                SystemLogger.log("Accepting invite...");
                                return true;
                            } catch (Exception e){
                                e.printStackTrace();
                                return false;
                            }
                }
        }
        return false;
    }

    @Override
    public Plugin<Object> newInstance(Object arg) throws Throwable {
        link(CommandSet.PLAYER);
        return this;
    }
}
