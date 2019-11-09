package org.crandor.game.node.entity.player.ai.lumbridge;

import org.crandor.tools.RandomFunction;

import java.util.concurrent.Executors;

/**
 * Creates a few random bots around Lumbridge area.
 * Code by Red Bracket
 */
public class LumbridgeBotHandler {
    public static void immersiveLumbridge()
    {
        //Generate a few random bots here and there

        generateDeadIdlers();
    }

    private static void generateDeadIdlers() {
        Executors.newSingleThreadExecutor().execute(() -> {
            while (true) //Would probably be better if this could be "while game is running"
            {
                new DeadIdler();
                try {
                    Thread.sleep(RandomFunction.random(300_000));
                } catch (InterruptedException e) {
                    System.out.println("LumbridgeBotHandler can't sleep!!?");
                }
            }
        });
    }
}
