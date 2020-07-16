package plugin.trivia;

import core.game.node.entity.player.Player;
import core.game.system.command.CommandPlugin;
import core.game.system.command.CommandSet;
import core.plugin.InitializablePlugin;
import core.plugin.Plugin;
import core.plugin.PluginManifest;

@InitializablePlugin
@PluginManifest(name="Trivia")
public class TriviaCommandPlugin extends CommandPlugin {

    @Override
    public boolean parse(Player player, String name, String[] args) {
        switch(name){
            case "trivia":
                if(args.length < 2){
                    player.sendMessage("Format: ::trivia start|stop|answer");
                    return true;
                }
                switch(args[1].toLowerCase()){
                    case "start":
                        if(TriviaManager.continuous) {
                            player.sendMessage("A game is already in progress!");
                        } else {
                            TriviaManager.start();
                        }
                        return true;
                    case "stop":
                        TriviaManager.stop();
                        return true;
                    case "answer":
                        if(args.length < 3){
                            player.sendMessage("Format: ::trivia answer type out answer");
                        } else {
                            TriviaManager.submit(player,args[2]);
                        }
                        return true;
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
