package core.game.world;

import plugin.ai.lumbridge.LumbridgeBotHandler;
import plugin.ai.pvmbots.PvMBotsBuilder;

public class ImmerseWorld {
    public static void init(){
        PvMBotsBuilder.immersiveSpawns();
        LumbridgeBotHandler.immersiveLumbridge();
    }
}
