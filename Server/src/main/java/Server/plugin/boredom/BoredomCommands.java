package plugin.boredom;

import core.game.node.entity.player.Player;
import core.game.system.command.CommandPlugin;
import core.game.system.command.CommandSet;
import core.plugin.InitializablePlugin;
import core.plugin.Plugin;
import core.plugin.PluginManifest;
import core.tools.RandomFunction;
import plugin.skill.Skills;

@InitializablePlugin
@PluginManifest(name="Boredom")
public class BoredomCommands extends CommandPlugin {
    private final int[] POSSIBLE_TRANSFORMS = new int[] {81, 46, 925, 155, 5571, 5593, 5596 };

    @Override
    public boolean parse(Player player, String name, String[] args) {
        switch(name){
            case "bored":
                rollCommand(player);
                return true;
        }
        return false;
    }

    public void rollCommand(Player p){
        int roll = RandomFunction.random(100);
        if(roll <= 10){
            int npc = RandomFunction.random(POSSIBLE_TRANSFORMS.length);
            npc = POSSIBLE_TRANSFORMS[npc];
            BoredomManager.submit(p,npc);
        } else {
            p.sendChat("I should train " + Skills.SKILL_NAME[RandomFunction.random(24)] + "!");
        }
    }

    @Override
    public Plugin<Object> newInstance(Object arg) throws Throwable {
        link(CommandSet.PLAYER);
        return this;
    }
}
