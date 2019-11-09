package org.crandor.game.node.entity.player.info.login;

import org.crandor.game.component.CloseEvent;
import org.crandor.game.component.Component;
import org.crandor.game.content.global.tutorial.TutorialSession;

import org.crandor.game.content.global.tutorial.TutorialStage;
import org.crandor.game.events.GlobalEventManager;

import org.crandor.game.interaction.Option;
import org.crandor.game.interaction.OptionHandler;
import org.crandor.game.node.Node;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.entity.player.info.Rights;
import org.crandor.game.system.SystemManager;
import org.crandor.game.world.GameWorld;
import org.crandor.game.world.map.RegionManager;
import org.crandor.game.world.repository.Repository;
import org.crandor.game.world.update.UpdateSequence;
import org.crandor.game.world.update.flag.player.AppearanceFlag;
import org.crandor.net.amsc.MSPacketRepository;
import org.crandor.net.amsc.WorldCommunicator;
import org.crandor.net.packet.PacketRepository;
import org.crandor.net.packet.context.InterfaceContext;
import org.crandor.net.packet.out.Interface;
import org.crandor.plugin.Plugin;
import org.crandor.tools.RandomFunction;
import plugin.interaction.inter.PlayerExamineInterfacePlugin;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Sends the login configuration packets.
 *
 * @author Emperor
 */
public final class LoginConfiguration {

    /**
     * The login plugins.
     */
    private static final List<Plugin<Object>> LOGIN_PLUGINS = new ArrayList<>();

    /**
     * The lobby pane component.
     */
    private static final Component LOBBY_PANE = new Component(549);

    /**
     * The lobby interface close event.
     */
    private static final Component LOBBY_INTERFACE = new Component(378).setCloseEvent(new CloseEvent() {
        @Override
        public boolean close(Player player, Component c) {
            return player.getLocks().isLocked("login");
        }
    });

    /**
     * Constructs a new {@Code LoginConfiguration} {@Code Object}
     */
    public LoginConfiguration() {
        /*
         * empty.
         */
    }

    /**
     * Configures the lobby login.
     *
     * @param player The player.
     */
    public static void configureLobby(Player player) {
        player.updateSceneGraph(true);
        if (!player.isArtificial() && TutorialSession.getExtension(player).getStage() >= TutorialSession.MAX_STAGE && player.getAttribute("login_type", LoginType.NORMAL_LOGIN) != LoginType.RECONNECT_TYPE) {
            sendLobbyScreen(player);
        } else {
            configureGameWorld(player);
        }
    }

    /**
     * Sends the lobby interface-related packets.
     *
     * @param player The player.
     */
    public static void sendLobbyScreen(Player player) {
        int random = RandomFunction.getRandom(50);

        Repository.getLobbyPlayers().add(player);
        player.getPacketDispatch().sendString(getLastLogin(player), 378, 116);
        player.getPacketDispatch().sendString("Welcome to " + GameWorld.getName(), 378, 115);
        player.getPacketDispatch().sendString("" + player.getDetails().getShop().getCredits(), 378, 96);
        player.getPacketDispatch().sendString(player.getDetails().getShop().getCredits() + " credits", 378, 94);
        player.getPacketDispatch().sendString(SystemManager.getSystemConfig().getConfig("weeklyMessage", "Welcome to RuneScape!"), SystemManager.getSystemConfig().getConfig("messageInterface", 18), getMessageChild(SystemManager.getSystemConfig().getConfig("messageInterface", 18)));
        player.getPacketDispatch().sendString("You can gain more credits by voting, reporting bugs and various other methods of contribution.", 378, 93);
        player.getInterfaceManager().openWindowsPane(LOBBY_PANE);
        player.getInterfaceManager().setOpened(LOBBY_INTERFACE);
        PacketRepository.send(Interface.class, new InterfaceContext(player, 549, 2, 378, true));
        PacketRepository.send(Interface.class, new InterfaceContext(player, 549, 3, SystemManager.getSystemConfig().getConfig("messageInterface", 18), true));//UPDATE `configs` SET `value`=FLOOR(RAND()*(25-10)+10) WHERE key_="messageInterface"
    }

    /**
     * Configures the game world.
     *
     * @param player The player.
     */
    public static void configureGameWorld(final Player player) {
        player.getConfigManager().reset();
        sendGameConfiguration(player);
        Repository.getLobbyPlayers().remove(player);
        player.setPlaying(true);
        UpdateSequence.getRenderablePlayers().add(player);
        RegionManager.move(player);
        player.getMusicPlayer().init();
        player.getUpdateMasks().register(new AppearanceFlag(player));
        player.getPlayerFlags().setUpdateSceneGraph(true);
        player.getStateManager().init();
        player.getPacketDispatch().sendInterfaceConfig(226, 1, true);
		/*if (GameWorld.getSettings().isPvp()) {
			player.getPacketDispatch().sendString("", 226, 1);
		}*/
        TutorialSession.extend(player);
        if (TutorialSession.getExtension(player).getStage() != 73) {
            TutorialStage.load(player, TutorialSession.getExtension(player).getStage(), true);
        }
    }

    /**
     * Sends the game configuration packets.
     *
     * @param player The player to send to.
     */
    public static void sendGameConfiguration(final Player player) {
        player.getInterfaceManager().openWindowsPane(new Component(player.getInterfaceManager().isResizable() ? 746 : 548));
        player.getInterfaceManager().openChatbox(137);
        player.getInterfaceManager().openDefaultTabs();
        welcome(player);
        config(player);
        for (Plugin<Object> plugin : LOGIN_PLUGINS) {
            try {
                plugin.newInstance(player);
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
        player.getCommunication().sync(player);
        if (WorldCommunicator.isEnabled()) {
            MSPacketRepository.sendInfoUpdate(player);
        }
    }

    /**
     * Method used to welcome the player.
     *
     * @param player the player. Fullscreen mode Object id:
     */
    public static final void welcome(final Player player) {
        if (GameWorld.getSettings().isPvp()) {
            player.getPacketDispatch().sendString("", 226, 0);
        }
        if (player.isArtificial()) {

            return;
        }
        player.getPacketDispatch().sendMessage("Welcome to " + GameWorld.getName() + ".");
        //player.getPacketDispatch().sendMessage("You are currently playing in beta version 1.2");
        if (player.getDetails().isMuted()) {
            player.getPacketDispatch().sendMessage("You are muted.");
            player.getPacketDispatch().sendMessage("To prevent further mutes please read the rules.");
        }
        GlobalEventManager.get().alert(player);
        if (player.getSkills().getTotalLevel() < 300) {
            Repository.sendNews("<col=BA55D3>As a new player, you are receiving boosted combat skill experience.</col>", "<col=BA55D3>In addition, you may speak to the Guide for game information.");
        }
        player.setAttribute("startTime", System.currentTimeMillis());
			return;
		}
//		ResourceAIPManager.get().load(player);
//		ResourceAIPManager.get().save(player);
    }

    /**
     * Method used to configure all possible settings for the player.
     *
     * @param player the player.
     */
    public static final void config(final Player player) {
        player.getInventory().refresh();
        player.getEquipment().refresh();
        player.getSkills().refresh();
        player.getSkills().configure();
        player.getSettings().update();
        player.getInteraction().setDefault();
        player.getPacketDispatch().sendRunEnergy();
        player.getFamiliarManager().login();
        player.getInterfaceManager().openDefaultTabs();
        player.getGrandExchange().init();
        player.getPacketDispatch().sendString("Friends List - World " + GameWorld.getSettings().getWorldId(), 550, 3);
        player.getConfigManager().init();
        player.getAntiMacroHandler().init();
        player.getQuestRepository().syncronizeTab(player);
        player.getGraveManager().update();
        player.getInterfaceManager().close();
        player.getEmoteManager().refresh();
        player.getInterfaceManager().openInfoBars();
        player.getInteraction().set(new Option("Examine", 7).setHandler(new OptionHandler() {
            @Override
            public Plugin<Object> newInstance(Object arg) throws Throwable {
                return null;
            }

            @Override
            public boolean handle(Player p, Node node, String option) {
                PlayerExamineInterfacePlugin comp = new PlayerExamineInterfacePlugin();
                comp.prepareInterface(p, (Player) node);
                return true;
            }

            @Override
            public boolean isWalk() {
                return false;
            }
        }));
    }

    /**
     * Gets the message child for the inter id.
     *
     * @param interfaceId The interface id.
     * @return The child id.
     */
    public static int getMessageChild(int interfaceId) {
        //15 = keys & lock
        //16 = fly swat
        //17 = person with question marks
        //18 = wise old man
        //19 = man & woman with mouth closed
        //20 = man & lock & key
        //21 = closed chests
        //22 = same as 15
        //23 = snowmen
        if (interfaceId == 16) {
            return 6;
        } else if (interfaceId == 17 || interfaceId == 15 || interfaceId == 18 || interfaceId == 19 || interfaceId == 21 || interfaceId == 22) {
            return 4;
        } else if (interfaceId == 20) {
            return 5;
        } else if (interfaceId == 23) {
            return 3;
        }
        return 0;
    }

    /**
     * Gets the last login string for the lobby.
     *
     * @param player the player.
     * @return the last login.
     */
    public static String getLastLogin(Player player) {
        String lastIp = (String) player.getDetails().getSqlManager().getTable().getColumn("lastGameIp").getValue();
        if (lastIp == null || lastIp == "") {
            lastIp = player.getDetails().getIpAddress();
        }
        String string = "You last logged in @timeAgo from: " + lastIp;
        long time = player.getDetails().getLastLogin();
        Date lastLogin = new Date(time);
        Date current = new Date();
        long diff = current.getTime() - lastLogin.getTime();
        int days = (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
        if (days < 1) {
            string = string.replace("@timeAgo", "earlier today");
        } else if (days == 1) {
            string = string.replace("@timeAgo", "yesterday");
        } else {
            string = string.replace("@timeAgo", days + " days ago");
        }
        return string;
    }


    /**
     * Gets the loginPlugins.
     *
     * @return The loginPlugins.
     */
    public static List<Plugin<Object>> getLoginPlugins() {
        return LOGIN_PLUGINS;
    }

}