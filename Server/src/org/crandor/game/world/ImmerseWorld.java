package org.crandor.game.world;

import org.crandor.game.node.entity.player.ai.lumbridge.LumbridgeBotHandler;
import org.crandor.game.node.entity.player.ai.pvmbots.PvMBotsBuilder;

public class ImmerseWorld {
    public static void init(){
        PvMBotsBuilder.immersiveSpawns();
        LumbridgeBotHandler.immersiveLumbridge();
    }
}
