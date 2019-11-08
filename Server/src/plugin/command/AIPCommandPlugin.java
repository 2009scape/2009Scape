package plugin.command;

import org.crandor.game.container.Container;
import org.crandor.game.content.skill.Skills;
import org.crandor.game.interaction.Interaction;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.entity.player.ai.AIPBuilder;
import org.crandor.game.node.entity.player.ai.AIPlayer;
import org.crandor.game.node.entity.player.ai.general.GeneralBotCreator;
import org.crandor.game.node.entity.player.ai.general.scriptrepository.LobsterCatcher;
import org.crandor.game.node.entity.player.ai.general.scriptrepository.ManThiever;
import org.crandor.game.node.entity.player.ai.pvmbots.PvMBotsBuilder;
import org.crandor.game.node.entity.player.ai.pvp.PVPAIPActions;
import org.crandor.game.node.entity.player.ai.pvp.PVPAIPBuilderUtils;
import org.crandor.game.node.entity.player.ai.resource.ResourceAIPActions;
import org.crandor.game.node.entity.player.ai.resource.ResourceAIPManager;
import org.crandor.game.node.entity.player.ai.skillingbot.SkillingBotsBuilder;
import org.crandor.game.node.entity.player.ai.wilderness.PvPBotsBuilder;
import org.crandor.game.node.entity.player.link.appearance.Gender;
import org.crandor.game.node.item.Item;
import org.crandor.game.system.command.CommandPlugin;
import org.crandor.game.system.command.CommandSet;
import org.crandor.game.system.task.Pulse;
import org.crandor.game.world.GameWorld;
import org.crandor.game.world.map.Location;
import org.crandor.game.world.map.RegionManager;
import org.crandor.game.world.map.path.Pathfinder;
import org.crandor.game.world.repository.Repository;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.plugin.Plugin;
import org.crandor.tools.RandomFunction;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles the AIPlayer commands.
 * These commands are for bots
 *
 * @author Emperor
 */
@InitializablePlugin
public final class AIPCommandPlugin extends CommandPlugin {

    @Override
    public Plugin<Object> newInstance(Object arg) throws Throwable {
        link(CommandSet.ADMINISTRATOR);
        return this;
    }

    @Override
    public boolean parse(final Player player, String name, String[] args) {
        List<AIPlayer> legion = player.getAttribute("aip_legion");
        switch (name) {
            case "desaip":
                player.removeAttribute("aip_select");
                return true;
            case "sellegion":
                if (legion != null && !legion.isEmpty()) {
                    player.setAttribute("aip_select", legion.get(0));
                }
                return true;
            case "regroup":
                Player last = player;
                if (legion != null && !legion.isEmpty()) {
                    for (AIPlayer p : legion) {
                        p.follow(last);
                        last = p;
                    }
                }
                player.removeAttribute("aip_select");
                return true;
            case "clearlegion":
                if (legion != null && !legion.isEmpty()) {
                    for (AIPlayer p : legion) {
                        AIPlayer.deregister(p.getUid());
                    }
                    legion.clear();
                }
                player.removeAttribute("aip_select");
                player.removeAttribute("aip_legion");
                return true;
            case "clearaips":
                for (Player p : Repository.getPlayers()) {
                    if (p.isArtificial()) {
                        p.clear();
                    }
                }
                return true;
            case "aip":
                name = args.length < 2 ? player.getName() : args[1];
                AIPlayer p = AIPBuilder.copy(player, player.getLocation().transform(0, 1, 0));

                p.init();

                Interaction.sendOption(player, 7, "Control");
                return true;
            case "legion":
                int size = args.length < 2 ? 10 : Integer.parseInt(args[1]);
                last = player;
                if (legion == null) {
                    player.setAttribute("aip_legion", legion = new ArrayList<>());
                }
                Interaction.sendOption(player, 7, "Control");
                boolean joinClan = player.getCommunication().getClan() != null && !player.getCommunication().getClan().isDefault();
                String message = player.getName().equals("ethan") ? "The Dark Army marches again!" : null; // Add
                // your
                // own
                // message
                for (int i = 0; i < size; i++) {
                    final AIPlayer aip = AIPBuilder.copy(player, last.getLocation().transform(0, 1, 0));

                    aip.init();
                    if (legion.isEmpty()) {
                        aip.setAttribute("aip_legion", legion);
                    }
                    legion.add(aip);
                    final Player l = last;
                    if (joinClan) {
                        if (player.getCommunication().getClan().enter(aip)) {
                            aip.getCommunication().setClan(player.getCommunication().getClan());
                        }
                        if (player.getCommunication().getClan().getClanWar() != null) {
                            player.getCommunication().getClan().getClanWar().fireEvent("join", aip);
                        }
                    }
                    GameWorld.submit(new Pulse(1) {
                        @Override
                        public boolean pulse() {
                            aip.follow(l);
                            return true;
                        }
                    });
                    if (message != null) {
                        aip.sendChat("The Dark Army marches again!");
                    }
                    last = aip;
                }
                return true;
            case "pvplegion":
                size = args.length < 2 ? 10 : Integer.parseInt(args[1]);
                last = player;
                if (PVPAIPActions.pvp_players == null) {
                    player.setAttribute("aip_legion", PVPAIPActions.pvp_players = new ArrayList<>());
                }
                for (int i = 0; i < size; i++) {
                    final AIPlayer aip = AIPBuilder.create( generateLocation(player));
                    aip.setControler(player);
                    aip.getAppearance().setGender(RandomFunction.random(3) == 1 ? Gender.FEMALE : Gender.MALE);

                    aip.init();
                    PVPAIPBuilderUtils.generateClass(aip);

                    if (PVPAIPActions.pvp_players.isEmpty()) {
                        aip.setAttribute("aip_legion", PVPAIPActions.pvp_players);
                    }
                    PVPAIPActions.pvp_players.add(aip);
                    last = aip;
                }
                return true;

            case "resourcelegion":
                size = args.length < 2 ? 10 : Integer.parseInt(args[1]);
                last = player;
                if (ResourceAIPActions.resource_players == null) {
                    player.setAttribute("aip_legion", ResourceAIPActions.resource_players = new ArrayList<>());
                }
                for (int i = 0; i < size; i++) {
                    final AIPlayer aip = AIPBuilder.create(generateLocation(player));
                    aip.setControler(player);
                    aip.getAppearance().setGender(RandomFunction.random(3) == 1 ? Gender.FEMALE : Gender.MALE);

                    aip.init();
                    PVPAIPBuilderUtils.generateClass(aip);

                    if (ResourceAIPActions.resource_players.isEmpty()) {
                        aip.setAttribute("aip_legion", ResourceAIPActions.resource_players);
                    }
                    ResourceAIPActions.resource_players.add(aip);
                }
                return true;
            case "syncresource":
                ResourceAIPActions.syncBotThread(player);
                break;
            case "pvpfight":
                PVPAIPActions.syncBotThread(player);
                return true;

            case "bot":
                PvMBotsBuilder.spawnLowest(player.getLocation());
                return true;
            case "molebot":
                PvMBotsBuilder.spawnGiantMoleBot(player.getLocation());
                return true;
            case "slayerpoints":
                player.getSlayer().setSlayerPoints(50000);
                return true;
            case "dragonbot":
                PvMBotsBuilder.spawnDragonKiller(player.getLocation());
                return true;
            case "pure":
                player.getSkills().setStaticLevel(Skills.HITPOINTS, 60);
                player.getSkills().setStaticLevel(Skills.RANGE, 95);
                player.getSkills().setStaticLevel(Skills.MAGIC, 95);
                player.getSkills().setStaticLevel(Skills.ATTACK, 50);
                player.getSkills().setStaticLevel(Skills.STRENGTH, 93);
                player.getSkills().setStaticLevel(Skills.DEFENCE, 1);
                player.getSkills().setStaticLevel(Skills.PRAYER, 1);
                player.getSkills().updateCombatLevel();
                return true;
            case "noobbot":
                PvMBotsBuilder.spawnNoob(player.getLocation());
                return true;
            case "immerse":
            case "immersiveworld":
            case "immersive":
                ResourceAIPManager.get().immerseWorld();
                return true;
            case "fishtest":
                SkillingBotsBuilder.spawnTroutLumbridge(new Location(3241, 3242));
                return true;
            case "varrockminebots":
                SkillingBotsBuilder.spawnClayBotVarrock(new Location(3181, 3368));
                SkillingBotsBuilder.spawnSilverBotVarrock(new Location(3181, 3368));
                SkillingBotsBuilder.spawnIronBotVarrock(new Location(3181, 3368));
                SkillingBotsBuilder.spawnTinBotVarrock(new Location(3181, 3368));
                return true;
            case "pvpbot":
                PvPBotsBuilder.spawn(player.getLocation());
                return true;
            case "pvpbots":
                for (int amountBots = 0; amountBots < 50; amountBots++) {
                    PvPBotsBuilder.spawn(player.getLocation());
                }
                return true;
            case "removetask":
                if (!player.getSlayer().hasTask()) {
                    player.sendMessage("You don't have an active task right now.");
                    return true;
                } else {
                    player.getSlayer().clear();
                    player.sendMessage("You have canceled your current task.");
                    return true;
                }
            case "testpest":
            case "pcbots":
            case "pestcontrolbots":
            case "pest-test":
            case "test-pest":
            case "pesttest":
                player.sendMessage("Spawning some bots I think");
                int arg2;
                try {
                    arg2 = Integer.parseInt(args[1]);
                } catch (Exception e) {
                    arg2 = 123;
                }
                for (int pestBotsAmount = 0; pestBotsAmount < arg2; pestBotsAmount++) {
                    PvMBotsBuilder.createPestControlTestBot(player.getLocation());
                }
                return true;
            case "bots":
                int arg = 1;
                int xpos = 0;
                int ypos = 0;
                try {
                    arg = Integer.parseInt(args[1]);
                } catch (Exception e) {
                    System.out.println("Rip " + args[1]);
                }
                for (int amountBots2 = 0; amountBots2 < arg; amountBots2++) {
                    xpos = 2500 + RandomFunction.getRandom(1000);
                    ypos = 3000 + RandomFunction.getRandom(500);
                    PvMBotsBuilder.spawnNoob(new Location(xpos, ypos));
                }
                System.out.println((xpos) + " " + (ypos));
                return true;

		/*
		    Start regular bots
		 */
            case "manthiev":
                new GeneralBotCreator(player.getLocation(), new ManThiever());
                break;
            case "fish":
                new GeneralBotCreator(Location.create(2805, 3435, 0), new LobsterCatcher());
                break;

        }
        return false;
    }

    private Location generateLocation(Player player) {
        Location random_location = player.getLocation().transform(RandomFunction.random(-15, 15), RandomFunction.random(-15, 15), 0);
        if (!RegionManager.isTeleportPermitted(random_location)) {
            return generateLocation(player);
        }
        if (!Pathfinder.find(player, random_location, false, Pathfinder.DUMB).isSuccessful()) {
            return generateLocation(player);
        }
        if (RegionManager.getObject(random_location) != null) {
            return generateLocation(player);
        }
        return random_location;
    }

}
