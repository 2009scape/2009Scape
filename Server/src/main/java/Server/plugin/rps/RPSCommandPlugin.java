package plugin.rps;

import core.game.node.entity.player.Player;
import core.game.node.item.Item;
import core.game.system.SystemLogger;
import core.game.system.command.CommandPlugin;
import core.game.system.command.CommandSet;
import core.game.world.repository.Repository;
import core.plugin.InitializablePlugin;
import core.plugin.Plugin;
import core.plugin.PluginManifest;

@InitializablePlugin
@PluginManifest(name="RPS")
public class RPSCommandPlugin extends CommandPlugin {

    RPSManagerPlugin manager;

    @Override
    public Plugin<Object> newInstance(Object arg) throws Throwable {
        manager = new RPSManagerPlugin();
        link(CommandSet.PLAYER);
        return this;
    }

    @Override
    public boolean parse(Player player, String name, String[] args) {
        switch(name){
            case "rpschallenge": {
                if (args.length == 1) {
                    player.sendMessage("Format: ::rpschallenge player optional:amount");
                    return true;
                }
                String otherPlayer = args[1].toLowerCase();
                manager.registerChallenge(new RPSChallenge(player.getUsername(), otherPlayer));
                return true;
            }
            case "rpsbet": {
                if (args.length == 1) {
                    player.sendMessage("Format: ::rpsbet player amount");
                    return true;
                }
                String otherPlayer = args[1];
                if (args.length > 2) {
                    try {
                        int bet = Integer.parseInt(args[2]);
                        if(bet <= 0){
                            player.sendMessage("Format: ::rpsbet player amount");
                            return true;
                        }
                        if(!player.getInventory().containsItem(new Item(995,bet))){
                            player.sendMessage("You dont have enough coins for that.");
                            return true;
                        }
                        manager.registerChallenge(new RPSChallenge(player.getUsername(), otherPlayer, bet));
                        return true;
                    } catch (Exception e) {
                        player.sendMessage("Format: ::rpsbet player amount");
                    }
                }
                return true;
            }
            case "rpshelp":
                player.sendMessage("::rpschallenge player (challenge player to fun match)");
                player.sendMessage("::rpsbet player amount (challenge to a gamble match)");
                player.sendMessage("::rpsaccept (accept a challenge levied against you)");
                return true;
            case "rpsaccept":
                manager.acceptChallengeFor(player.getUsername());
                SystemLogger.log("Accepting challenge for " + player.getUsername());
                return true;
        }
        return false;
    }
}
