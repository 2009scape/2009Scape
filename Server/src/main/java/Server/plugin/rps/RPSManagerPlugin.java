package plugin.rps;

import core.game.node.entity.player.Player;
import core.game.node.item.Item;
import core.game.system.SystemLogger;
import core.game.world.repository.Repository;
import core.plugin.InitializablePlugin;
import core.plugin.Plugin;
import core.plugin.PluginManifest;
import plugin.CorePluginTypes.ManagerPlugin;
import plugin.CorePluginTypes.Managers;

import java.util.ArrayList;
import java.util.List;

@InitializablePlugin
@PluginManifest(name="RPS")
public class RPSManagerPlugin extends ManagerPlugin {
    private static List<RPSChallenge> currentChallenges = new ArrayList<>();

    @Override
    public Plugin<Object> newInstance(Object arg) throws Throwable {
        Managers.register(this);
        return this;
    }

    public void registerChallenge(RPSChallenge challenge){
        currentChallenges.add(challenge);
        Player p = Repository.PLAYER_NAMES.get(challenge.getChallengedPlayer());
        int i =0;
        SystemLogger.log(p.getUsername());
        p.sendMessage(challenge.getInitiatingPlayer() + " has challenged you to a game of rock, paper, scissors!");
        if(challenge.gamble){
            p.sendMessage("This player wishes to gamble " + challenge.betAmount + " coins.");
        }
        p.sendMessage("type ::rpsaccept to accept.");
    }

    public void acceptChallengeFor(String player_name){
        for(RPSChallenge c : currentChallenges){
            if(c.getChallengedPlayer().equals(player_name.toLowerCase())){
                if(c.gamble){
                    if(!Repository.getPlayerByName(player_name).getInventory().containsItem(new Item(995,c.betAmount))){
                        Repository.getPlayerByName(player_name).sendMessage("You don't have enough coins to do this.");
                        return;
                    }
                }
                c.acceptChallenge();
            }
        }
    }

    @Override
    public void tick() {
        currentChallenges.forEach(RPSChallenge::tick);
        currentChallenges.removeIf(c -> c.finished);
    }
}
