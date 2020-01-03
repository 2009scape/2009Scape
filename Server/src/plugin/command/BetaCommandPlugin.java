package plugin.command;

import org.crandor.ServerConstants;
import org.crandor.cache.Cache;
import org.crandor.cache.def.impl.ItemDefinition;
import org.crandor.cache.def.impl.NPCDefinition;
import org.crandor.game.content.eco.ge.GrandExchangeDatabase;
import org.crandor.game.content.eco.ge.GrandExchangeEntry;
import org.crandor.game.content.skill.Skills;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.entity.player.ai.resource.ResourceAIPManager;
import org.crandor.game.node.entity.player.info.Rights;
import org.crandor.game.node.entity.player.link.TeleportManager.TeleportType;
import org.crandor.game.node.entity.player.link.quest.Quest;
import org.crandor.game.node.entity.player.link.quest.QuestRepository;
import org.crandor.game.node.item.Item;
import org.crandor.game.system.SystemLogger;
import org.crandor.game.system.command.CommandPlugin;
import org.crandor.game.system.command.CommandSet;
import org.crandor.game.world.map.Location;
import org.crandor.game.world.map.Region;
import org.crandor.game.world.map.RegionManager;
import org.crandor.game.world.map.build.DynamicRegion;
import org.crandor.game.world.map.zone.impl.DonatorZone;
import org.crandor.game.world.repository.Repository;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.plugin.Plugin;
import org.crandor.tools.StringUtils;
import org.crandor.tools.npc.TestStats;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

/**
 * The commands available during beta stage.
 *
 * @author Emperor
 */
@InitializablePlugin
public final class BetaCommandPlugin extends CommandPlugin {

    @Override
    public boolean parse(Player player, String name, String[] args) {
        int id, amount;
        Player p;
        switch (name) {
        case "rights":
        	player.getDetails().setRights(Rights.forId(Integer.parseInt(args[1])));
        	player.sendMessage("Set rights to " + Rights.forId(Integer.parseInt(args[1])).name());
        	break;
            case "lo":
                int index = 0;
                for (int i = 8349; i < 8367; i++) {
                    NPC n = NPC.create(i, player.getLocation().transform(0, -index, 0));
                    n.init();
                    index += 2;
                }
                player.sendMessage("Lol");
                break;
            case "resetquest":
            case "reset_quest":
                if (args.length < 2) {
                    player.debug("syntax error: name");
                    return true;
                }
                name = "";
                for (int i = 1; i < args.length; i++) {
                    name += (i == 1 ? "" : " ") + args[i];
                }
                name = StringUtils.formatDisplayName(name);
                if (player.getQuestRepository().getQuest(name) == null) {
                    player.debug("err or: invalid quest - " + name);
                    return true;
                }
                player.getQuestRepository().getQuest(name).setStage(player, 0);
                player.getQuestRepository().syncronizeTab(player);
                return true;

            case "allquest":
                for (Quest quest : QuestRepository.getQuests().values()) {
                    quest.finish(player);
                }
                return true;

            case "pos":
            case "position":
            case "coords":
            case "loc":
                final Location l = player.getLocation();
                final Region r = player.getViewport().getRegion();
                player.getPacketDispatch().sendMessage("Absolute: " + l + ", regional: [" + l.getLocalX() + ", " + l.getLocalY() + "], chunk: [" + l.getChunkOffsetX() + ", " + l.getChunkOffsetY() + "], flag: [" + RegionManager.isTeleportPermitted(l) + ", " + RegionManager.getClippingFlag(l) + ", " + RegionManager.isLandscape(l) + "].");
                player.getPacketDispatch().sendMessage("Region: [id=" + l.getRegionId() + ", active=" + r.isActive() + ", instanced=" + ((r instanceof DynamicRegion)) + "], obj=" + RegionManager.getObject(l) + ".");
                player.getPacketDispatch().sendMessage("Object: " + RegionManager.getObject(l) + ".");
                SystemLogger.log("Viewport: " + l.getSceneX(player.getPlayerFlags().getLastSceneGraph()) + "," + l.getSceneY(player.getPlayerFlags().getLastSceneGraph()));
                String loc = "Location.create(" + l.getX() + ", " + l.getY() + ", " + l.getZ() + ")";
                SystemLogger.log(loc + "; "+ player.getPlayerFlags().getLastSceneGraph() + ", " + l.getLocalX() + ", " + l.getLocalY());
                StringSelection stringSelection = new StringSelection(loc);
                Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
                clpbrd.setContents(stringSelection, null);
                return true;

            case "dz":
                DonatorZone.getInstance().invite(player, null);
                return true;

            case "setquest":
            case "setoquest":
                if (args.length < 2) {
                    player.debug("syntax error: name");
                    return true;
                }
                Player m = name.equals("setoquest") ? getTarget(args[args.length - 1]) : player;
                if (m == null || !m.isActive()) {
                    player.sendMessage("Error! " + args[3] + " in invalid.");
                    return true;
                }
                name = "";
                for (int i = 1; i < args.length - 1; i++) {
                    name += (i == 1 ? "" : " ") + args[i];
                }
                Quest quest = null;
                for (Quest q : QuestRepository.getQuests().values()) {
                    if (q.getName().toLowerCase().equals(name.toLowerCase())) {
                        quest = q;
                        break;
                    }
                }
                if (quest == null) {
                    player.debug("error: invalid quest - " + name);
                    return true;
                }
                int stage = toInteger(args[args.length - 1]);
                quest.setStage(player, stage);
                m.getPacketDispatch().sendMessage("quest=" + name + ", new stage=" + stage);
                m.getQuestRepository().syncronizeTab(player);
                break;

            case "empty":
                player.getInventory().clear();
                return true;

            case "setvalue":
                int itemId = toInteger(args[1]);
                int value = toInteger(args[2]);
                Item item = new Item(itemId);
                GrandExchangeEntry entry = GrandExchangeDatabase.getDatabase().get(itemId);
                if (entry == null) {
                    player.getPacketDispatch().sendMessage("Could not find G.E entry for item [id=" + itemId + ", name=" + item.getName() + "]!");
                    break;
                }
                entry.setValue(value);
                player.getPacketDispatch().sendMessage("Set Grand Exchange value for item [id=" + itemId + ", name=" + item.getName() + "] to " + value + "gp!");
                break;

            case "npc":
                if (args.length < 2) {
                    player.debug("syntax error: id (optional) direction");
                    return true;
                }
                NPC npc = NPC.create(toInteger(args[1]), player.getLocation());
                npc.setAttribute("spawned:npc", true);
                npc.setRespawn(false);
                npc.setDirection(player.getDirection());
                npc.init();
                npc.setWalks(args.length > 2 ? true : false);
                String npcString = "{" + npc.getLocation().getX() + "," + npc.getLocation().getY() + "," + npc.getLocation().getZ() + "," + (npc.isWalks() ? "1" : "0") + "," + npc.getDirection().ordinal() + "}";
                clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
                clpbrd.setContents(new StringSelection(npcString), null);
                System.out.println(npcString);
                return true;

            case "npcn":
                if (args.length < 2) {
                    player.debug("syntax error: npc-name (optional) amount");
                    return true;
                }
                String parameters = "";
                for (int i = 1; i < args.length; i++) {
                    parameters += i == args.length - 1 ? args[i] : args[i] + " ";
                }

                for (int i = 0; i < NPCDefinition.getDefinitions().size(); i++) {
                    NPCDefinition npcDefinition = NPCDefinition.forId(i);
                    if (npcDefinition != null && npcDefinition.getName().equalsIgnoreCase(parameters.toLowerCase())) {
                        NPC n = NPC.create(npcDefinition.getId(), player.getLocation());
                        n.setAttribute("spawned:npcDefinition", true);
                        n.setRespawn(false);
                        n.setDirection(player.getDirection());
                        n.init();
                        n.setWalks(args.length > 2 ? true : false);
                        String npcs = "{" + n.getLocation().getX() + "," + n.getLocation().getY() + "," + n.getLocation().getZ() + "," + (n.isWalks() ? "1" : "0") + "," + n.getDirection().ordinal() + "}";
                        System.out.println(npcs);
                        break;
                    }
                }
                return true;

            // Get item by id
            case "item":
            	if (args.length < 2) {
            		player.sendMessage("You must specify an item ID");
            		return false;
            	}
                id = toInteger(args[1]);
                amount = args.length > 2 ? toInteger(args[2]) : 1;
                if (id > Cache.getItemDefinitionsSize()) {
            		player.sendMessage("Item ID '" + id + "' out of range.");
                    return true;
                }
                item = new Item(id, amount);
                int max = player.getInventory().getMaximumAdd(item);
                if (amount > max) {
                    amount = max;
                }
                item.setAmount(amount);
                player.getInventory().add(item);
                return true;
                
            // Get item by name
            case "itemn":
                if (args.length < 2) {
                    player.sendMessage("You must specify an item name");
                    return true;
                }
                String itemName = "";
                for (int i = 1; i < args.length; i++) {
                	itemName += i == args.length - 1 ? args[i] : args[i] + " ";
                }
                Boolean foundItem = false;
                for (int i = 0; i < ItemDefinition.getDefinitions().size(); i++) {
                    ItemDefinition def1 = ItemDefinition.forId(i);
                    if (def1 != null && def1.getName().equalsIgnoreCase(itemName.toLowerCase())) {
                        player.getInventory().add(new Item(i, 1));
                        player.sendMessage("Added " + def1.getName() + "[" + def1.getId() + "] to inventory");
                        foundItem = true;
                        break;
                    }
                }
                if (!foundItem) {
                	player.sendMessage("@red@Unable to find item: " + itemName + "");
                }
                return true;
            case "task":
                ResourceAIPManager.get().runTask(player, "Willow Logs");
                break;
            case "master":
            case "max":
                if (player.getDetails().getRights() != Rights.ADMINISTRATOR) {
                    if (player.inCombat() || player.getLocks().isInteractionLocked() || player.getSkullManager().isWilderness()) {
                        player.getPacketDispatch().sendMessage("You can't do that right now.");
                        return true;
                    }
                }
                for (int i = 0; i < Skills.SKILL_NAME.length; i++) {
                    player.getSkills().setLevel(i, 99);
                    player.getSkills().setStaticLevel(i, 99);
                }
                player.getSkills().updateCombatLevel();
                player.getAppearance().sync();
                return true;
            case "runes":
                for (int i = 554; i < 567; i++) {
                    player.getInventory().add(new Item(i, 50000));
                }
                player.getInventory().add(new Item(9075, 50000));
                return true;
            case "skill":
            case "oskill":
                if (player.getDetails().getRights() != Rights.ADMINISTRATOR) {
                    if (player.inCombat() || player.getLocks().isInteractionLocked() || player.getSkullManager().isWilderness()) {
                        player.getPacketDispatch().sendMessage("You can't do that right now.");
                        return true;
                    }
                }
                if (args.length < 3) {
                    player.getPacketDispatch().sendMessage("Use as ::skill skillname/id level.");
                    return true;
                }
                int skillId = -1;
                if (Character.isDigit(args[1].charAt(0))) {
                    skillId = toInteger(args[1]);
                } else {
                    for (id = 0; id < Skills.SKILL_NAME.length; id++) {
                        String skill = Skills.SKILL_NAME[id];
                        if (args[1].equals(skill.toLowerCase())) {
                            skillId = id;
                            break;
                        }
                    }
                }
                if (skillId < 0) {
                    player.getPacketDispatch().sendMessage("Use as ::skill skillname/id level.");
                    return true;
                }
                int level = Math.abs(toInteger(args[2]));
                if (level > 99) {
                    level = 99;
                }
                p = name.equals("oskill") && args.length > 3 ? Repository.getPlayer(args[3]) : player;
                if (p == null) {
                    player.getPacketDispatch().sendMessage("Unable to set level for " + args[3] + ".");
                    return true;
                }
                p.getSkills().setLevel(skillId, level);
                p.getSkills().setStaticLevel(skillId, level);
                p.getSkills().updateCombatLevel();
                p.getAppearance().sync();
                player.getPacketDispatch().sendMessage("Set " + p.getName() + "'s " + Skills.SKILL_NAME[skillId] + " level to " + args[2] + ".");
                return true;
            case "copy":
                Player target = Repository.getPlayer(args[1]);
                if (target != null) {
                    player.getInventory().copy(target.getInventory());
                    player.getInventory().refresh();
                    player.getSkills().copy(target.getSkills());
                    player.getSkills().configure();
                    player.getEquipment().copy(target.getEquipment());
                    player.getEquipment().refresh();
                    player.getAppearance().sync();
                }
                return true;
            case "to":
                if (player.getDetails().getRights() != Rights.ADMINISTRATOR) {
                    if (player.inCombat() || player.getLocks().isTeleportLocked()) {
                        player.getPacketDispatch().sendMessage("You can't teleport right now.");
                        return true;
                    }
                }
                Location destination = null;
                String place = getArgumentLine(args);
                for (Object[] destinations : ServerConstants.TELEPORT_DESTINATIONS) {
                    for (int i = 1; i < destinations.length; i++) {
                        if (place.equals(destinations[i])) {
                            destination = (Location) destinations[0];
                            break;
                        }
                    }
                }
                if (destination != null) {
                    player.getTeleporter().send(destination, TeleportType.NORMAL);
                } else {
                    player.getPacketDispatch().sendMessage("Could not locate teleport destination [name=" + place + "]!");
                }
                return true;
            case "teleports":
            case "destinations":
                player.getInterfaceManager().close();
                player.getPacketDispatch().sendString("<u>Teleport destinations</u>", 239, 1);
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < ServerConstants.TELEPORT_DESTINATIONS.length; i++) {
                    sb.append(ServerConstants.TELEPORT_DESTINATIONS[i][1]);
                    if (i != ServerConstants.TELEPORT_DESTINATIONS.length - 1) {
                        sb.append(", ");
                    }
                }
                player.getPacketDispatch().sendString("<br>" + sb.toString(), 239, 2);
                player.getPacketDispatch().sendString("", 239, 3);
                player.getPacketDispatch().sendString("", 239, 4);
                player.getPacketDispatch().sendString("", 239, 5);
                player.getInterfaceManager().openComponent(239);
                return true;
            case "maxmag":
                TestStats.setMaxedMagicAcc(player);
                return true;
            case "maxstr":
                TestStats.setMaxedMeleeStr(player);
                return true;
        }
        return false;
    }

    @Override
    public Plugin<Object> newInstance(Object arg) throws Throwable {
        link(CommandSet.BETA, CommandSet.ADMINISTRATOR);
        return this;
    }

}
