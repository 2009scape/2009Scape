package plugin.ai.general;

import plugin.ai.AIPBuilder;
import plugin.ai.AIPlayer;
import plugin.ai.general.scriptrepository.Script;
import core.game.system.task.Pulse;
import core.game.world.GameWorld;
import core.game.world.map.Location;

public class GeneralBotCreator {

    //org/crandor/net/packet/in/InteractionPacket.java <<< This is a very useful class for learning to code bots
    public GeneralBotCreator(Location loc, Script botScript)
    {
        botScript.bot = AIPBuilder.create(loc);

        botScript.init();

        GameWorld.Pulser.submit(new Pulse(1, botScript.bot) {
            int ticks = 0;
            @Override
            public boolean pulse() {
                if (ticks ++ == 5000)
                {
                    AIPlayer.deregister(botScript.bot.getUid());
                    return true;
                }

                if (!botScript.bot.getPulseManager().hasPulseRunning())
                {
                    botScript.runLoop();
                }
                return false;
            }
        });
    }
}
