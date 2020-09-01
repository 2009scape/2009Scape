package org.runite.jagex;

import net.arikia.dev.drpc.DiscordEventHandlers;
import net.arikia.dev.drpc.DiscordRPC;
import net.arikia.dev.drpc.DiscordRichPresence;

import java.util.Timer;
import java.util.TimerTask;

public class Discord {

    public static DiscordEventHandlers discord;

    public static final String APPLICATION_ID = "750136793814401134";

    public static boolean startedDiscord = false;

    public static void InitalizeDiscord() {
        if (!startedDiscord){
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                System.out.println("Closing Discord hook.");
                DiscordRPC.discordShutdown();
            }));
            System.out.println("Starting discord rich presence.");
            discord = new DiscordEventHandlers.Builder().setReadyEventHandler((user) -> {
                System.out.println("Established discord rich presence.");
                DiscordRPC.discordRunCallbacks();
            }).build();
            DiscordRPC.discordInitialize(APPLICATION_ID, discord, false);
            DiscordRPC.discordRegister(APPLICATION_ID, "");
            Timer timer = new Timer();
            timer.schedule(new DiscordUpdate(), 0, 5000);
            startedDiscord = true;
        }
    }

    static class DiscordUpdate extends TimerTask {
        public void run() {
            DiscordRPC.discordRunCallbacks();
            DiscordRichPresence.Builder presence = new DiscordRichPresence.Builder("An open source MMO.");
            presence.setDetails("World " + ObjectDefinition.worldId);
            presence.setBigImage("jarfix-300x300", "Check out our github!");
            DiscordRPC.discordUpdatePresence(presence.build());
        }
    }
}
