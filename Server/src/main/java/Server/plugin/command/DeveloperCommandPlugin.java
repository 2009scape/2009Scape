package plugin.command;

import core.ServerConstants;
import core.cache.Cache;
import core.cache.def.impl.ItemDefinition;
import core.cache.def.impl.NPCDefinition;
import core.game.container.Container;
import core.game.container.impl.EquipmentContainer;
import core.game.content.eco.EconomyManagement;
import core.game.content.global.shop.Shop;
import plugin.tutorial.TutorialSession;
import core.game.content.holiday.HolidayItem;
import core.game.content.holiday.ItemLimitation;
import plugin.skill.Skills;
import plugin.skill.smithing.smelting.Bar;
import plugin.skill.construction.HouseLocation;
import plugin.skill.summoning.pet.Pets;
import core.game.node.entity.combat.ImpactHandler.HitsplatType;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.npc.drop.DropTables;
import core.game.node.entity.npc.drop.RareDropTable;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.ai.resource.ResourceAIPManager;
import core.game.node.entity.player.info.PlayerDetails;
import core.game.node.entity.player.info.login.PlayerParser;
import core.game.node.entity.player.link.IronmanMode;
import core.game.node.entity.player.link.music.MusicEntry;
import core.game.node.entity.player.link.quest.Quest;
import core.game.node.entity.player.link.skillertasks.Difficulty;
import core.game.node.entity.state.EntityState;
import core.game.node.item.Item;
import core.game.node.object.GameObject;
import core.game.system.command.CommandPlugin;
import core.game.system.command.CommandSet;
import core.game.system.mysql.SQLManager;
import core.game.system.mysql.impl.ItemConfigSQLHandler;
import core.game.system.mysql.impl.NPCDropSQLHandler;
import core.game.system.script.ScriptManager;
import core.game.system.task.Pulse;
import core.game.world.GameWorld;
import core.game.world.map.Location;
import core.game.world.map.RegionManager;
import core.game.world.map.RegionPlane;
import core.game.world.map.path.Pathfinder;
import core.game.world.map.zone.RegionZone;
import core.game.world.repository.Repository;
import core.game.world.update.flag.context.Animation;
import core.game.world.update.flag.context.Graphics;
import core.plugin.InitializablePlugin;
import core.plugin.Plugin;
import core.plugin.PluginManager;
import core.tools.ItemNames;
import core.tools.RandomFunction;
import core.tools.StringUtils;
import plugin.skill.herblore.PotionDecantingPlugin;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.sql.SQLException;
import java.util.*;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.Executors;

/**
 * Handles the developer commands.
 *
 * @author Vexia
 */
@InitializablePlugin
public final class DeveloperCommandPlugin extends CommandPlugin {

    @Override
    public Plugin<Object> newInstance(Object arg) throws Throwable {
        link(CommandSet.ADMINISTRATOR);
        return this;
    }

    public static class SpawnSearch extends Shop {

        public SpawnSearch() {
            super("Spawn Search", false);
        }

        @Override
        public void open(Player player) {
            super.open(player);
            player.getPacketDispatch().sendInterfaceConfig(620, 29, true);
            player.getPacketDispatch().sendInterfaceConfig(620, 26, true);
        }

        @Override
        public boolean canSell(Player player, Item item, ItemDefinition def) {
            player.sendMessage("You cannot sell items to this store.");
            return false;
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean parse(final Player player, String name, String[] args) {
        switch (name) {
        	case "unlockmusic":
        		for (MusicEntry me : MusicEntry.getSongs().values()) {
        			player.getMusicPlayer().unlock(me.getId());
        		}
        		
        	break;
        	
        	case "setlevel":
        		player.getSkills().setLevel(Integer.parseInt(args[1]), Integer.parseInt(args[2]));
        		player.getSkills().setStaticLevel(Integer.parseInt(args[1]), Integer.parseInt(args[2]));
        		break;
        	
        	case "playsong":
        		player.getMusicPlayer().play(MusicEntry.getSongs().get(Integer.parseInt(args[1])));
        		player.sendMessage("Playing song: " + MusicEntry.getSongs().get(Integer.parseInt(args[1])).getName());
        		break;
            case "find":
                try {
                    player.getAttributes().put("spawning_items", true);
                    String itemName = "";
                    for (int i = 1; i < args.length; i++)
                        itemName += args[i] + ((i == args.length - 1) ? "" : " ");
                    int count = 0;
                    player.getAttributes().computeIfAbsent("found_items", k -> new HashMap<Integer, Integer>());
                    player.getAttributes().remove("found_items");
                    Container storeItems = new Container(1200);
                    for (int i = 0; i < ItemDefinition.getDefinitions().size(); i++) {
                        ItemDefinition definition = ItemDefinition.forId(i);
                        itemName = itemName.toLowerCase();
                        if (definition == null)
                            continue;
                        String output = definition.getName().toLowerCase();
                        int itemId = definition.getId();
                        if (output.contains(itemName)) {
                            storeItems.add(new Item(itemId, 1));
                            player.getAttributes().put("" + count, itemId);
                            count++;
                        }
                    }

                    SpawnSearch shop = new SpawnSearch();
                    shop.getContainer(0).copy(storeItems);
                    shop.open(player);

                } catch (NumberFormatException e) {
                    return true;
                }
                break;
            case "tut":
                System.out.println("TUT");
                if (player.getInventory().isEmpty()) {
                    player.getPacketDispatch().sendMessage("You have nothing to deposit.");
                } else {
                    dump(player, player.getInventory());
                }
                if (player.getEquipment().isEmpty()) {
                    player.getPacketDispatch().sendMessage("You have nothing to deposit.");
                } else {
                    dump(player, player.getEquipment());
                }
                TutorialSession.extend(player);
                GameWorld.Pulser.submit(new Pulse(1, player) {
                    @Override
                    public boolean pulse() {
                        TutorialSession.getExtension(player).init();
                        return true;
                    }
                });
                break;
            case "drops":
                try {
                    new NPCDropSQLHandler().parse();
                    player.sendMessage(EconomyManagement.getModificationRate() + "");
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                break;
            case "stask":
                player.getSkillTasks().getNewTask(player, Difficulty.NOVICE);
                player.sendMessage("Your current task is " + player.getSkillTasks().getCurrentTask().getAssignment() + ".");
                player.sendMessage(player.getSkillTasks().getCurrentTask().getDescription());
                break;
            case "taskamount":
                player.sendMessage("You have " + player.getSkillTasks().getTaskAmount() + " more to go!");
                break;

            case "poison":
                player.getStateManager().set(EntityState.POISONED, 200, player);
                player.getConfigManager().set(102, 1);
                player.sendMessage("Poisoned...");
                break;

            case "reloaddb":
                SQLManager.init();
                player.sendMessage("[MySQl] The database has been reloaded.");
                break;
            case "g":
                Location l = player.getLocation();
                int xz = l.getX();
                int yz = l.getY();
                int er = Integer.parseInt(args[1]);
                for (int w = 10; w > 0; w--) {
                    Graphics gfx = new Graphics(er + w);
                    Graphics.send(gfx, new Location(xz - (w > 5 ? -(w - 5) : 5 - w), yz));
                }
            case "v":
                Map<Integer, ArrayList<NPC>> zz = new HashMap<>();
                for (NPC n : Repository.getNpcs()) {
                    if (n == null) {
                        continue;
                    }
                    if (n.getAttribute("spawned:npc", false) && n.getName().startsWith("Kalphite") && n.getViewport().getRegion().getId() == 14939) {
                        if (!zz.containsKey(n.getId())) {
                            ArrayList<NPC> ooo = new ArrayList<NPC>();
                            ooo.add(n);
                            zz.put(n.getId(), ooo);
                        } else {
                            ArrayList<NPC> nn = zz.get(n.getId());
                            nn.add(n);
                            zz.put(n.getId(), nn);
                        }
                    }
                }
                for (Entry<Integer, ArrayList<NPC>> e : zz.entrySet()) {
                    System.out.println("--- npc -> " + e.getKey() + "   ----");
                    List<NPC> bn = e.getValue();
                    String ll = "";
                    for (NPC n : bn) {
                        Location hu = n.getProperties().getSpawnLocation();
                        ll += "{" + hu.getX() + "," + hu.getY() + "," + hu.getZ() + "," + (n.isWalks() ? "1" : "0") + "," + n.getDirection().toInteger() + "}-";
                    }
                    System.out.println(ll);
                }
                break;
            case "fillbank":
                for (int zy = 0; zy < 100; zy++) {
                    player.getBank().add(new Item(zy + 2000));
                }
                break;
            case "chins":
                player.getInventory().add(new Item(ItemNames.RED_CHINCHOMPA_10034,Integer.MAX_VALUE));
                break;
            case "shop":
                int npc = Integer.parseInt(args[1]);
                new NPC(npc).openShop(player);
                break;
            case "simdrops":
                Executors.newSingleThreadExecutor().execute(()->{
                    int npc_id = Integer.parseInt(args[1]);
                    int npc_amt = Integer.parseInt(args[2]);
                    player.getDropLog().clear();
                    for(int i = 0; i < npc_amt; i++){
                        DropTables.forId(npc_id).getDrops().forEach(drop -> {
                            if(drop.getId() == RareDropTable.SLOT_ITEM_ID){
                                drop = RareDropTable.retrieve();
                            }
                            if(drop.getId() != 0) {
                                player.getDropLog().add(drop);
                            }
                        });
                    }
                    player.getDropLog().open();
                });
                break;
            case "anim":
                player.animate(new Animation(Integer.parseInt(args[1])));
                break;
            case "em":
                final int[] y = new int[]{16044, 16045, 16256, 16259, 16260, 16334, 16381, 16556, 16564, 16638, 16642, 16673, 16710, 16713, 16715, 16722, 16796, 16797, 16805, 16831, 16886, 16890, 16926, 16938, 16942};
                GameWorld.Pulser.submit(new Pulse(3) {
                    int anim = 0;

                    @Override
                    public boolean pulse() {
                        if (anim == (y.length - 1)) {
                            player.sendChat("Done");
                            return true;
                        }
                        player.animate(new Animation(y[anim]));
                        player.sendChat("Animation: " + y[anim]);
                        anim++;
                        return false;
                    }

                });
                break;
               
            case "tempsound":
            	player.getPacketDispatch().sendTempMusic(Integer.parseInt(args[1]));
            	break;
            case "loopsounds":
                GameWorld.Pulser.submit(new Pulse(5) {
            		int i = Integer.parseInt(args[2]);
            		int j = Integer.parseInt(args[1]);
            		@Override
            		public boolean pulse() {
                		player.sendMessage("Sound: " + i);
                		player.getPacketDispatch().sendTempMusic(i);
            			i++;
            			if (i >= j) {
            				return true;
            			}
            			return false;
            		}
            	});
            	break;
                
            case "c":
                for (Bar bar : Bar.values()) {
                    System.out.println(bar.getProduct().getId() + ",");
                }
                break;
            /*	case "zulrahzone":
			DynamicRegion y = DynamicRegion.create(9007, 9008);
			if (y != null) {
				Location l = y.getBaseLocation();
				int xOffset = 2240 - l.getX();
				int yOffset = 3008 - l.getY();
				System.out.println(y.getBaseLocation());
				player.teleport(y.getBaseLocation());
				ZulrahPattern.startZulrahSpawn(ZulrahPattern.getPatterns().get(0), player, xOffset, yOffset);
			} else {
				System.out.println("No zone");
			}
			break;*/
            case "damage":
                player.getImpactHandler().manualHit(player, 5, HitsplatType.VENOM);
                break;
            case "region":
                System.out.println("You're in region: " + player.getLocation().getRegionId() + " " + player.getLocation().getLocalX() + " " + player.getLocation().getLocalY());
                player.getPacketDispatch().sendMessage("You're in region: " + player.getLocation().getRegionId() + " " + player.getLocation().getRegionX() + " " + player.getLocation().getRegionY());
                break;
            case "pots":
                for (int i = 0; i < ItemDefinition.getDefinitions().size(); i++) {
                    if (ItemDefinition.getDefinitions().get(i).getName().contains("(1)")) {
                        System.out.println(ItemDefinition.getDefinitions().get(i).getName());
                    }
                }
                break;
            case "t":

                RegionPlane plane = player.getViewport().getCurrentPlane();
                int replace = 2103;
                System.out.println("----------------------");
                for (GameObject[] o : plane.getObjects()) {
                    if (o == null) {
                        continue;
                    }
                    for (GameObject oo : o) {
                        if (oo == null) {
                            continue;
                        }
                        if (oo.getId() == 42036) {
                            System.out.println("{new GameObject(" + oo.getId() + "," + oo.getLocation().getX() + "," + oo.getLocation().getY() + ",0," + oo.getType() + "," + oo.getRotation() + "),new GameObject(" + replace + "," + oo.getLocation().getX() + "," + oo.getLocation().getY() + ",0," + oo.getType() + "," + oo.getRotation() + ")},");
                        }
                    }
                }
                System.out.println("----------------------");
                break;
            case "rp":
                PluginManager.init();
                player.sendMessage("Plugins reloaded");
                break;
            case "decant":
                PotionDecantingPlugin.decant(player);
                break;
            case "removeiron":
                Player t = Repository.getPlayer(args[1], true);
                if (t == null) {
                    return true;
                }
                t.getIronmanManager().setMode(IronmanMode.NONE);
                if (t.isActive()) {
                    t.sendMessage("Iron man mode removed.");
                }
                player.sendMessage("Removed ironman mode.");
                PlayerParser.dump(t);
                break;
            case "holidayitemloc":
                if (args.length < 1) {
                    player.sendMessage("Syntax error - please do ::holidayitemloc itemId");
                    return true;
                }
                int holidayItemId = Integer.parseInt(args[1]);
                Location itemLoc = HolidayItem.getHolidayItemLocation(holidayItemId);
                player.sendMessage("The location of the holiday item is - " + (itemLoc != null ? itemLoc : "null") + ".");
                return true;
            case "krakme":
                player.getFamiliarManager().summon(new Item(14651), true);
                break;
            case "addpets":
                player.getFamiliarManager().getInsuredPets().add(Pets.BABY_MOLE);
                player.getFamiliarManager().getInsuredPets().add(Pets.KREE_JR);
                player.getFamiliarManager().getInsuredPets().add(Pets.KQ_FORM_1);
                player.getFamiliarManager().getInsuredPets().add(Pets.TZREK_JAD);
                return true;
            case "clearpets":
                player.getFamiliarManager().getInsuredPets().clear();
                return true;
            case "holidayitemamount":
                if (args.length < 1) {
                    player.sendMessage("Syntax error - please do ::holidayitemamount itemId");
                    return true;
                }
                int itemAmount = Integer.parseInt(args[1]);
                player.sendMessage("There are " + ItemLimitation.getAmountLeft(itemAmount) + " left.");
                return true;
            case "iparse":
                try {
                    new ItemConfigSQLHandler().parse();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "l":
                player.sendMessage("" + player.getSavedData().getGlobalData().getLowAlchemyCharges());
                //player.getSavedData().getGlobalData().setLowAlchemyCharges(2);
                break;
            case "o":
                String[] stock = {"Bronze arrow", "Iron arrow", "Steel arrow", "Mithril arrow", "Adamant arrow", "Bronze bolts", "Iron bolts", "Steel bolts", "Shortbow", "Oak shortbow", "Willow shortbow", "Maple shortbow", "Yew shortbow", "Magic shortbow", "Crossbow", "Iron crossbow", "Steel crossbow", "Mith crossbow", "Adamant crossbow", "Rune crossbow", "Green dragonhide set", "Blue dragonhide set", "Red dragonhide set", "Black dragonhide set", "Leather vambraces", "Leather chaps", "Leather body", "Leather body", "Hardleather body", "Leather cowl", "Studded body", "Studded chaps", "Snakeskin boots", "Archer helm", "Amulet of accuracy", "Dorgeshuun c'bow", "Bone bolts", "Iron knife", "Rune knife"};
                String output = "";
                for (String string : stock) {
                    for (int i = 0; i < Cache.getItemDefinitionsSize(); i++) {
                        if (ItemDefinition.forId(i).getName().toLowerCase().equals(string.toLowerCase())) {
                            output += "{" + i + ",10}-";
                            break;
                        }
                        if (i == Cache.getItemDefinitionsSize() - 1) {
                            System.err.println("You failed on " + string);
                        }
                    }
                }
                System.out.println(output);
                break;
            case "rls":
                ScriptManager.load();
                break;
            case "k":
                String[] types = {"Adamant", "Rune", "Dragon"};
                String[] items = {"dagger", "spear", "pickaxe", "sword", "longsword", "2h sword", "scimitar", "warhammer", "axe", "battleaxe"};
                String out = "";
                for (String type : types) {
                    for (String item : items) {
                        for (int i = 0; i < Cache.getItemDefinitionsSize(); i++) {
                            if (ItemDefinition.forId(i).getName().equals(type + " " + item)) {
                                out += "{" + i + ",10}-";
                                break;
                            }
                            if (i == Cache.getItemDefinitionsSize()) {
                                System.err.println("Could not do " + type + ", & " + item);
                            }
                        }
                    }
                }
                System.out.println(out);
                break;
            case "i":
                for (int i = 0; i < Cache.getNPCDefinitionsSize(); i++) {
                    if (NPCDefinition.forId(i).getName().equals("Gnome traveller")) {
                        System.out.println(i + ", " + NPCDefinition.forId(i).getExamine());
                    }
                }
                break;
            case "setpin":
                if (args.length < 2) {
                    player.sendMessage("Syntax error - ::setpin username 1234");
                    return true;
                }
                Player p = super.getTarget(args[1], true);
                if (p == null) {
                    player.sendMessage("Could not find player " + args[1] + "!");
                    return true;
                }
                String pin = args.length > 2 ? args[2] : null;
                p.getBankPinManager().setPin(pin == "" ? null : pin);
                p.sendMessage("Your pin has been set by an administrator.");
                player.sendMessage("Set " + p.getUsername() + "'s pin to " + pin + ".");
                return true;
            case "special":
            case "spec":
                int amount = args.length > 1 ? Integer.parseInt(args[1]) : 100;
                player.getSettings().setSpecialEnergy(amount);
                return true;
            case "god":
                player.setAttribute("godMode", !player.getAttribute("godMode", false));
                player.sendMessage("God mode=<col=ff0000>" + player.getAttribute("godMode", false));
                break;
            case "1hit":
                int[] bonuses = new int[15];
                for (int z = 0; z < 15; z++) {
                    if (player.getProperties().getBonuses()[z] > 1000) {
                        EquipmentContainer.updateBonuses(player);
                        break;
                    }
                    bonuses[z] = 10000;
                }
                if (bonuses[1] > 1000) {
                    player.getProperties().setBonuses(bonuses);
                }
                return true;
            case "home":
                player.teleport(ServerConstants.HOME_LOCATION);
                return true;
            case "heal":
            case "hp":
            case "life":
                player.getSettings().setSpecialEnergy(100);
                player.getSettings().updateRunEnergy(-100);
                player.getSkills().setLifepointsIncrease(0);
                player.getSkills().rechargePrayerPoints();
                player.getSkills().heal(100);
                player.getSkills().setLevel(Skills.SUMMONING, player.getSkills().getStaticLevel(Skills.SUMMONING));
                player.getStateManager().remove(EntityState.TELEBLOCK);
                if (player.getFamiliarManager().hasFamiliar()) {
                    player.getFamiliarManager().getFamiliar().updateSpecialPoints(-200);
                }
                return true;
            case "slayerpoints":
                player.getSlayer().setSlayerPoints(10000);
                return true;
            case "bank":
                player.getBank().open();
                return true;
            case "peekbank":
            	try {
            		Player pl = new Player(PlayerDetails.getDetails(args[1]));
            		player.sendMessage("penis");
            		PlayerParser.parse(pl);
            		pl.getBank().open(player);
            		player.getPacketDispatch().sendString(pl.getUsername()+ "'s bank.",762,24);
            	} catch (Exception e) {player.sendMessage("damn"); e.printStackTrace();}
            	return true;
            case "iii":
            	for (int i = 0; i < Integer.parseInt(args[1]); ++i) {
            		player.getPacketDispatch().sendString("" + i, Integer.parseInt(args[2]), i);
            	}
            	return true;
            case "debugil":
                for (int itemIdv : ItemLimitation.getItems().keySet()) {
                    player.getPacketDispatch().sendMessage(itemIdv + ": " + ItemDefinition.forId(itemIdv).getName() + " is limited to " + ItemLimitation.getItems().get(itemIdv) + ".");
                    System.out.println(itemIdv + ": " + ItemDefinition.forId(itemIdv).getName() + " is limited to " + ItemLimitation.getItems().get(itemIdv) + ".");
                }
                return true;
            case "setil":
                if (args.length < 3) {
                    player.getPacketDispatch().sendMessage("Syntax is ::setil itemId limit.");
                    return true;
                }
                player.getPacketDispatch().sendMessage("Limited item " + args[1] + " to " + args[2] + ".");
                ItemLimitation.register(toInteger(args[1]), toInteger(args[2]));
                return true;
            case "unlock":
                player.unlock();
                return true;
            case "task":
                ResourceAIPManager.get().runTask(player, "Willow Logs");
                break;
            case "gettask":
                ResourceAIPManager.get().runTask(player, "Willow Logs");
                break;
            case "modroom":
                player.teleport(new Location(2843, 5214, 0));
                return true;
            case "poh":
                if (!player.getHouseManager().hasHouse()) {
                    player.getHouseManager().create(HouseLocation.RIMMINGTON);
                }
                player.getHouseManager().enter(player, true);
                return true;
            case "resethouse":
            case "rh":
                player.getHouseManager().clearRooms();
                player.getHouseManager().create(HouseLocation.RIMMINGTON);
                player.getHouseManager().enter(player, true);
                return true;
            case "debug":
                player.toggleDebug();
                return true;
            case "copycoords":
                String coords = player.getLocation().getX() + ", " + player.getLocation().getY() + ", " + player.getLocation().getZ();
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                clipboard.setContents(new StringSelection(coords), null);
                break;
            case "zones":
                for (RegionZone z : player.getZoneMonitor().getZones()) {
                    player.getPacketDispatch().sendMessage("Region zone " + z.getZone().getName() + " active...");
                }
                return true;
            case "additem":
            case "deleteitem":
            case "deleteitemb":
                if (name.equals("additem")) {
                    addItem(player, args);
                } else {
                    deleteItem(player, args);
                }
                break;
            case "roar":
                player.getPacketDispatch().sendInterfaceConfig(762, 20, false);
                break;
            case "setstage":
                if(args.length < 2) {
                    player.getPacketDispatch().sendMessage("Syntax: setstage stage# quest name");
                } else {
                    int stageId = 0;
                    stageId = toInteger(args[1]);
                    String questName = "";
                    for (int i = 2; i < args.length; i++) {
                        questName += (i == 2 ? "" : " ") + args[i];
                    }
                    questName = StringUtils.formatDisplayName(questName);
                    Quest quest = player.getQuestRepository().getQuest(questName);
                    if (quest == null) {
                        player.debug("err or: invalid quest - " + questName);
                        return true;
                    }
                    player.debug("Setting quest " + questName + " to stage " + stageId);
                    player.getQuestRepository().setStage(quest,stageId);
                    player.getQuestRepository().syncronizeTab(player);
                }
                break;
            case "finishtask":
                player.debug("Kill the npc that spawned to finish your task.");
                player.getSlayer().setAmount(1);
                NPC finisher = new NPC(player.getSlayer().getTask().getNpcs()[0],player.getLocation());
                finisher.setRespawn(false);
                finisher.init();
                break;
            case "setslayerpoints":
                player.getSlayer().setSlayerPoints(toInteger(args[1]));
                player.debug("Set slayer points to " + args[1]);
                break;
            }
        return false;
    }

    /**
     * Adds an item to a players item.
     *
     * @param player the player.
     * @param args   the args.
     */
    private void addItem(Player player, String[] args) {
        Player t = Repository.getPlayer(args[1]);
        if (t == null) {
            return;
        }
        int id = toInteger(args[2]);
        int amount = toInteger(args[3]);
        Item item = new Item(id, amount);
        t.getInventory().add(item);
        player.getPacketDispatch().sendMessage("You just gave " + t.getUsername() + " the item - " + item);
    }

    /**
     * Deletes an item from a players item.
     *
     * @param player the player.
     * @param args   the args.
     */
    private void deleteItem(Player player, String[] args) {
        Player t = Repository.getPlayer(args[1]);
        if (t == null) {
            return;
        }
        int id = toInteger(args[2]);
        int amount = toInteger(args[3]);
        Item item = new Item(id, amount);
        if (args[0].equals("deleteitemb")) {
            t.getBank().remove(item);
        } else {
            t.getInventory().remove(item);
        }
        player.getPacketDispatch().sendMessage("You just removed" + t.getUsername() + " the item - " + item);
    }


    private Location generateLocation(Player player) {
        Location random_location = new Location(3075 + RandomFunction.random(-15, 15), 3954 + RandomFunction.random(-15, 15), 0);
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

    private void dump(Player player, Container container) {
        for (Item i : container.toArray()) {
            if (i == null) {
                continue;
            }
            if (!player.getBank().canAdd(i)) {
                player.getPacketDispatch().sendMessage("A magical force prevents you from banking your equipment.");
                return;
            }
            if (!player.getBank().hasSpaceFor(i)) {
                player.getPacketDispatch().sendMessage("There is not enough space left in your bank.");
                return;
            }
            if (container.remove(i)) {
                player.getBank().add(i.getDefinition().isUnnoted() ? i : new Item(i.getNoteChange(), i.getAmount()));
            }
        }
    }

}
