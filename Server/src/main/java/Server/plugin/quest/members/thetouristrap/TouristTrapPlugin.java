package plugin.quest.members.thetouristrap;

import core.cache.def.impl.AnimationDefinition;
import core.cache.def.impl.NPCDefinition;
import core.cache.def.impl.ObjectDefinition;
import core.game.component.Component;
import plugin.activity.ActivityManager;
import plugin.activity.ActivityPlugin;
import plugin.activity.CutscenePlugin;
import plugin.dialogue.DialogueInterpreter;
import plugin.dialogue.DialoguePlugin;
import core.game.content.global.action.ClimbActionHandler;
import core.game.content.global.action.DoorActionHandler;
import plugin.skill.SkillPulse;
import plugin.skill.smithing.smelting.Bar;
import plugin.skill.agility.AgilityHandler;
import core.game.interaction.NodeUsageEvent;
import core.game.interaction.OptionHandler;
import core.game.interaction.UseWithHandler;
import core.game.node.Node;
import core.game.node.entity.combat.ImpactHandler.HitsplatType;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.quest.Quest;
import core.game.node.item.Item;
import core.game.node.object.GameObject;
import core.game.node.object.ObjectBuilder;
import core.game.system.task.Pulse;
import core.game.world.GameWorld;
import core.game.world.map.Location;
import core.game.world.map.RegionManager;
import core.game.world.map.build.DynamicRegion;
import core.game.world.update.flag.context.Animation;
import core.plugin.Plugin;
import core.plugin.PluginManager;
import core.tools.RandomFunction;

import plugin.quest.members.thetouristrap.TouristTrapPlugin.AnnaCartHandler.AnnaCartCutscene;
import plugin.quest.members.thetouristrap.TouristTrapPlugin.BedabinAnvilHandler.AnnaWinchHandler;

/**
 * Represents the plugin used to handle interactions for tourist trap.
 *
 * @author 'Vexia
 * @version 1.0
 */
public final class TouristTrapPlugin extends OptionHandler {

    @Override
    public Plugin<Object> newInstance(Object arg) throws Throwable {
        ObjectDefinition.forId(2673).getConfigurations().put("option:open", this);
        ObjectDefinition.forId(2674).getConfigurations().put("option:open", this);
        ObjectDefinition.forId(2673).getConfigurations().put("option:search", this);
        ObjectDefinition.forId(2674).getConfigurations().put("option:search", this);
        ObjectDefinition.forId(2688).getConfigurations().put("option:open", this);
        ObjectDefinition.forId(2688).getConfigurations().put("option:search", this);
        ObjectDefinition.forId(2687).getConfigurations().put("option:open", this);
        ObjectDefinition.forId(2687).getConfigurations().put("option:search", this);
        ObjectDefinition.forId(2685).getConfigurations().put("option:open", this);
        ObjectDefinition.forId(2685).getConfigurations().put("option:open", this);
        ObjectDefinition.forId(2686).getConfigurations().put("option:search", this);
        ObjectDefinition.forId(2686).getConfigurations().put("option:search", this);
        ObjectDefinition.forId(18958).getConfigurations().put("option:search", this);
        ObjectDefinition.forId(18958).getConfigurations().put("option:look at", this);
        ObjectDefinition.forId(18959).getConfigurations().put("option:search", this);
        ObjectDefinition.forId(18959).getConfigurations().put("option:look at", this);
        ObjectDefinition.forId(18888).getConfigurations().put("option:look at", this);
        ObjectDefinition.forId(18888).getConfigurations().put("option:operate", this);
        ObjectDefinition.forId(36748).getConfigurations().put("option:talk-to", this);
        ObjectDefinition.forId(18869).getConfigurations().put("option:bend", this);
        ObjectDefinition.forId(18870).getConfigurations().put("option:escape", this);
        ObjectDefinition.forId(2689).getConfigurations().put("option:open", this);
        ObjectDefinition.forId(1528).getConfigurations().put("option:open", this);
        ObjectDefinition.forId(1529).getConfigurations().put("option:close", this);
        ObjectDefinition.forId(18899).getConfigurations().put("option:search", this);
        ObjectDefinition.forId(18899).getConfigurations().put("option:look at", this);
        ObjectDefinition.forId(18878).getConfigurations().put("option:search", this);
        ObjectDefinition.forId(18878).getConfigurations().put("option:look at", this);
        ObjectDefinition.forId(18879).getConfigurations().put("option:search", this);
        ObjectDefinition.forId(18879).getConfigurations().put("option:look at", this);
        ObjectDefinition.forId(18898).getConfigurations().put("option:search", this);
        ObjectDefinition.forId(18898).getConfigurations().put("option:look at", this);
        ObjectDefinition.forId(18902).getConfigurations().put("option:search", this);
        ObjectDefinition.forId(18871).getConfigurations().put("option:climb", this);
        ObjectDefinition.forId(18923).getConfigurations().put("option:climb-up", this);
        ObjectDefinition.forId(18924).getConfigurations().put("option:climb-down", this);
        ObjectDefinition.forId(2676).getConfigurations().put("option:open", this);
        ObjectDefinition.forId(2675).getConfigurations().put("option:open", this);
        ObjectDefinition.forId(2676).getConfigurations().put("option:watch", this);
        ObjectDefinition.forId(2675).getConfigurations().put("option:watch", this);
        ObjectDefinition.forId(2690).getConfigurations().put("option:open", this);
        ObjectDefinition.forId(2691).getConfigurations().put("option:open", this);
        ObjectDefinition.forId(2698).getConfigurations().put("option:walk through", this);
        ObjectDefinition.forId(2699).getConfigurations().put("option:walk through", this);
        ObjectDefinition.forId(2677).getConfigurations().put("option:open", this);
        ObjectDefinition.forId(2678).getConfigurations().put("option:search", this);
        ObjectDefinition.forId(2684).getConfigurations().put("option:search", this);
        ObjectDefinition.forId(2684).getConfigurations().put("option:look at", this);
        ObjectDefinition.forId(2680).getConfigurations().put("option:search", this);
        ObjectDefinition.forId(2680).getConfigurations().put("option:look-in", this);
        ObjectDefinition.forId(2681).getConfigurations().put("option:search", this);
        ObjectDefinition.forId(2681).getConfigurations().put("option:look in", this);
        ObjectDefinition.forId(18962).getConfigurations().put("option:search", this);
        ObjectDefinition.forId(18962).getConfigurations().put("option:look in", this);
        ObjectDefinition.forId(18963).getConfigurations().put("option:search", this);
        ObjectDefinition.forId(18963).getConfigurations().put("option:look in", this);
        ObjectDefinition.forId(18951).getConfigurations().put("option:look at", this);
        ObjectDefinition.forId(18951).getConfigurations().put("option:use", this);
        ObjectDefinition.forId(2684).getConfigurations().put("option:search", this);
        ObjectDefinition.forId(2684).getConfigurations().put("option:look at", this);
        NPCDefinition.forId(830).getConfigurations().put("option:watch", this);
        NPCDefinition.forId(4975).getConfigurations().put("option:talk-to", this);
        NPCDefinition.forId(4976).getConfigurations().put("option:talk-to", this);
        NPCDefinition.forId(4977).getConfigurations().put("option:talk-to", this);
        NPCDefinition.forId(4978).getConfigurations().put("option:talk-to", this);
        NPCDefinition.forId(5002).getConfigurations().put("option:talk-to", this);
        TouristTrap.TECHNICAL_PLANS.getDefinition().getConfigurations().put("option:read", this);
        TouristTrap.ANNA_BARREL.getDefinition().getConfigurations().put("option:look", this);
        TouristTrap.ANNA_BARREL.getDefinition().getConfigurations().put("option:drop", this);
        PluginManager.definePlugin(new BedabinKeyHandler());
        PluginManager.definePlugin(new BedabinAnvilHandler());
        PluginManager.definePlugin(new BarrelDialogue());
        PluginManager.definePlugin(new WinchDialogue());
        PluginManager.definePlugin(new MineCartDialogue());
        PluginManager.definePlugin(new AnnaCartHandler());
        PluginManager.definePlugin(new AnnaCartCutscene());
        PluginManager.definePlugin(new AnnaWinchHandler());
        PluginManager.definePlugin(new WinchCutscene());
        PluginManager.definePlugin(new CartDialogue());
        return this;
    }

    @Override
    public boolean handle(final Player player, Node node, String option) {
        final Quest quest = player.getQuestRepository().getQuest(TouristTrap.NAME);
        final int id = node.getId();
        switch (option) {
            case "read":
                player.getDialogueInterpreter().sendDialogue("The plans look very technical! But you can see that this item will", "require a bronze bar and at least 10 feathers.");
                break;
            case "watch":
                switch (id) {
                    case 2676:
                    case 2675:
                        player.getDialogueInterpreter().sendDialogue("You watch the doors for some time. You notice that only slaves seem", "to do down there. You might be able to sneak down if you pass as a", "slave.");
                        break;
                    default:
                        player.getDialogueInterpreter().sendDialogue("You watch the Mercenary Captain for some time. He has a large", "metal key attached to his belt. You notice that he usually gets his", "men to do his dirty work.");
                        break;
                }
                break;
            case "walk through":
                switch (id) {
                    case 2698:
                    case 2699:
                        if (quest.getStage(player) < 60) {
                            player.getDialogueInterpreter().sendDialogues(5001, null, "Hey you! You're not allowed in there.");
                            break;
                        }
                        if (!TouristTrap.hasSlaveClothes(player)) {
                            player.getDialogueInterpreter().sendDialogues(5001, null, "Hey you're not a slave!");
                            break;
                        }
                        player.getProperties().setTeleportLocation(player.getLocation().transform(player.getLocation().getX() >= 3284 ? -4 : 4, 0, 0));
                        player.getPacketDispatch().sendMessages("You walk into the darnkess of the cavern...", "... and emerge in a different part of this huge underground complex.");
                        break;
                }
                break;
            case "look":
                player.getDialogueInterpreter().sendDialogues(823, null, "Let me out of here, I feel sick!");
                break;
            case "drop":
                player.getDialogueInterpreter().sendDialogues(823, null, "Don't let me out!");
                break;
            case "open":
                switch (id) {
                    case 2688:
                    case 2687:
                        if (!player.getInventory().containsItem(TouristTrap.WROUGHT_IRON_KEY)) {
                            player.getPacketDispatch().sendMessage("This gate looks like it needs a key to open it.");
                            break;
                        }
                        DoorActionHandler.handleAutowalkDoor(player, (GameObject) node);
                        break;
                    case 2686:
                    case 2685:
                        player.getDialogueInterpreter().sendDialogues(4999, null, "Hey, move away from the gate. There's nothing", "interesting for you here.");
                        break;
                    case 2677:
                        if (!player.getInventory().containsItem(TouristTrap.BEDABIN_KEY)) {
                            player.getPacketDispatch().sendMessage("This chest needs a key to unlock it.");
                            break;
                        }
                        if (quest.getStage(player) <= 54 && quest.getStage(player) != 53) {
                            player.getPacketDispatch().sendMessage("The captain spots you before you manage to open the chest...");
                            player.lock(3);
                            GameWorld.Pulser.submit(new Pulse(2, player) {
                                @Override
                                public boolean pulse() {
                                    player.getDialogueInterpreter().open(831, RegionManager.getNpc(player, 831));
                                    return true;
                                }
                            });
                        } else if (quest.getStage(player) == 53) {
                            if (!player.hasItem(TouristTrap.TECHNICAL_PLANS)) {
                                player.getDialogueInterpreter().sendItemMessage(TouristTrap.TECHNICAL_PLANS, "While the Captain's distracted, you quickly unlock the", "chest with the Bedabins' copy of the key. You take out", "the plans.");
                                player.getInventory().add(TouristTrap.TECHNICAL_PLANS, player);
                            }
                        }
                        break;
                    case 2690:
                    case 2691:
                        player.setAttribute("ana-delay", GameWorld.getTicks() + 2);
                        player.getProperties().setTeleportLocation(Location.create(3301, 3035, 0));
                        break;
                    case 2676:
                    case 2675:
                        if (!TouristTrap.hasSlaveClothes(player)) {
                            player.getDialogueInterpreter().sendDialogues(4997, null, "Watch it! Only slaves can travel into the mine.");
                            break;
                        }
                        player.setAttribute("ana-delay", GameWorld.getTicks() + 2);
                        player.getProperties().setTeleportLocation(Location.create(3278, 9427, 0));
                        player.getDialogueInterpreter().sendDialogue("The huge doors open into a dark, dank and smelly tunnel. The", "associated smells of a hundred sweaty miners greets your nostrills.", "And your ears ring with the 'CLANG CLANG CLANG' as metal hits", "rock.");
                        break;
                    case 2674:
                    case 2673:
                        if (quest.getStage(player) > 60 && quest.getStage(player) < 98 && player.getInventory().containsItem(TouristTrap.ANNA_BARREL)) {
                            player.lock();
                            player.getDialogueInterpreter().sendDialogues(4999, null, true, "Would you like me to take that heavy barrel", "for you?");
                            GameWorld.Pulser.submit(new Pulse(4, player) {
                                int counter;

                                @Override
                                public boolean pulse() {
                                    switch (++counter) {
                                        case 1:
                                            player.getDialogueInterpreter().sendDialogues(player, null, "No, please don't.");
                                            break;
                                        case 2:
                                            player.getDialogueInterpreter().close();
                                            player.getPacketDispatch().sendMessage("The guards search you!");
                                            break;
                                        case 3:
                                            player.getPacketDispatch().sendMessage("You are roughed up by the guards and manhandled into a cell.");
                                            break;
                                        case 4:
                                            player.unlock();
                                            player.getInventory().remove(TouristTrap.ANNA_BARREL);
                                            TouristTrap.addConfig(player, (1 << 4));
                                            quest.setStage(player, 61);
                                            player.getProperties().setTeleportLocation(Location.create(3285, 3034, 0));
                                            return true;
                                    }
                                    return false;
                                }
                            });
                            return true;
                        }
                        if (node.getLocation().withinDistance(new Location(3273, 3028, 0)) && !((player.getLocation().getX() >= 3274))) {
                            if (!player.getInventory().containsItem(TouristTrap.METAL_KEY)) {
                                player.getPacketDispatch().sendMessage("The gate needs a key in order to be opened.");
                                return true;
                            }
                            switch (quest.getStage(player)) {
                                default:
                                    DoorActionHandler.handleAutowalkDoor(player, (GameObject) node);
                                    player.getPacketDispatch().sendMessage("The guards search you thoroughly as you go through the gates.");
                                    break;
                            }
                            return true;
                        }
                        DoorActionHandler.handleAutowalkDoor(player, (GameObject) node);
                        break;
                    case 2689:
                        if (!player.getInventory().containsItem(TouristTrap.CELL_DOOR_KEY)) {
                            player.getPacketDispatch().sendMessage("The door seems to be pretty locked.");
                            break;
                        }
                        DoorActionHandler.handleAutowalkDoor(player, (GameObject) node);
                        break;
                    case 1528:
                        ObjectBuilder.replace((GameObject) node, ((GameObject) node).transform(1529));
                        break;
                }
                break;
            case "close":
                switch (id) {
                    case 1529:
                        ObjectBuilder.replace((GameObject) node, ((GameObject) node).transform(1528));
                        break;
                }
                break;
            case "look-in":
            case "look in":
                switch (id) {
                    case 2681:
                    case 18962:
                    case 18963:
                        player.getDialogueInterpreter().sendDialogue("This looks like an empty mining barrel. Slaves use this to load up the", "rocks and stones that they're mining.");
                        break;
                    case 2680:
                        player.getDialogueInterpreter().sendDialogue("You seach the full barrel... It's full of rocks.");
                        break;
                }
                break;
            case "use":
                switch (id) {
                    case 18951:
                        player.getDialogueInterpreter().open("winch dialogue");
                        break;
                }
                break;
            case "search":
                switch (id) {
                    case 2688:
                    case 2687:
                        player.getDialogueInterpreter().sendDialogue("These wrought iron gates look like they're designed to keep people out.", "it looks like you'll need a key to get past these.");
                        break;
                    case 2686:
                    case 2685:
                        player.getDialogueInterpreter().sendDialogue("It looks as if this is where very difficult prsioners are sent as a", "punishment.");
                        break;
                    case 2681:
                    case 18962:
                    case 18963:
                        player.getDialogueInterpreter().open("barrel dialogue", node);
                        break;
                    case 2680:// barrel of rocks.
                        player.getDialogueInterpreter().sendDialogue("This looks like a full mining barrel. Slaves use this to load up the", "rocks and stones that they're mining. This barrel is full of rocks.");
                        break;
                    case 2684:// cart
                        player.getDialogueInterpreter().open("cart dialogue", node);
                        break;
                    case 2678:
                        if (quest.getStage(player) == 51) {
                            quest.setStage(player, 52);
                        }
                        player.getDialogueInterpreter().sendItemMessage(9904, "You notice several books on the subject of sailing.");
                        break;
                    case 2673:
                    case 2674:
                        player.getDialogueInterpreter().sendDialogue("You see what looks like a mining compound. There seems to be people", "mining rocks. They look as if they're chained to the rocks and they're", "being watched over by the guards. It's not a very happy place.");
                        break;
                    case 18958:
                    case 18959:
                        if (quest.getStage(player) == 90) {
                            player.getDialogueInterpreter().open("ana cart dialogue");
                            break;
                        }
                        player.getPacketDispatch().sendMessage("This looks like a mine cart which takes barrels out of the encampment to Al Kharid.");
                        break;
                    case 18899:
                    case 18898:
                    case 18878:
                    case 18879:
                        player.getDialogueInterpreter().sendDialogue("You searh the footsteps more closely. You can see that there are", "five sets of footprints. One set of footprints seem lighter than the", "others. The four other footsteps were made by heavier people", "with boots.");
                        break;
                    case 18902:
                        if (!hasItem(player, TouristTrap.CELL_DOOR_KEY)) {
                            player.getInventory().add(TouristTrap.CELL_DOOR_KEY, player);
                            player.getDialogueInterpreter().sendItemMessage(TouristTrap.CELL_DOOR_KEY, "You find a cell door key.");
                            break;
                        } else if (!hasItem(player, TouristTrap.WROUGHT_IRON_KEY) && player.getQuestRepository().isComplete(TouristTrap.NAME)) {
                            player.getInventory().add(TouristTrap.WROUGHT_IRON_KEY, player);
                            player.getDialogueInterpreter().sendItemMessage(TouristTrap.WROUGHT_IRON_KEY, "You find the key to the main gate.");
                            break;
                        }
                        player.getPacketDispatch().sendMessage("You search the captains desk while he's not looking...");
                        player.getPacketDispatch().sendMessage("...but you find nothing of interest.");
                        break;
                }
                break;
            case "operate":
                if (quest.getStage(player) != 71) {
                    player.getDialogueInterpreter().sendDialogue("There doesn't seem anything to lift.");
                    break;
                }
                ActivityManager.start(player, "winch cutscene", false);
                break;
            case "look at":
                switch (id) {
                    case 18888:
                        player.getDialogueInterpreter().sendDialogue("This looks like a winch, it probably brings rocks up from", "underground.");
                        break;
                    case 18951:
                        player.getDialogueInterpreter().sendDialogue("This looks like a lift of some sort. You see barrels of rocks being", "placed on the lift and they're hauled up to the surface.");
                        break;
                    case 2684:
                        if (node.getLocation().getX() == 3318) {
                            player.getDialogueInterpreter().sendDialogue("This mine cart is being loaded up with new rocks and stone.", "It gets sent to a different section of the mine for unloading.");
                            break;
                        }
                        player.getDialogueInterpreter().sendDialogue("This cart is being unloaded into this section of the mine. Before being", "sent back to another section for another load.");
                        break;
                    case 18958:
                    case 18959:
                        player.getDialogueInterpreter().sendDialogue("A sturdy looking cart for carrying barrels of rocks out of ", "the mining camp.");
                        break;
                    case 18900:
                    case 18899:
                    case 18898:
                    case 18887:
                    case 18886:
                    case 18885:
                    case 18884:
                    case 18883:
                    case 18882:
                    case 18881:
                    case 18880:
                    case 18879:
                    case 18878:
                    case 18877:
                        player.getDialogueInterpreter().sendDialogue("This looks like some disturbed sand. Footseps seem to be heading off", "towards the South.");
                        break;
                }
                break;
            case "talk-to":
                switch (id) {
                    case 5002:
                        ((NPC) node).sendChat("Move along please, don't want any trouble today!");
                        break;
                    case 36748:
                        player.getDialogueInterpreter().sendDialogues(player, null, "Mmm... looks like that camel would make a nice kebab.");
                        break;
                    case 4975:
                    case 4977:
                    case 4978:
                    case 4976:
                        ((NPC) node).sendChat("Hey leave me alone, can't you see that i'm busy?");
                        break;
                }
                break;
            case "bend":
                player.animate(Animation.create(5037));
                GameWorld.Pulser.submit(new Pulse(5, player) {
                    @Override
                    public boolean pulse() {
                        player.getPacketDispatch().sendMessage("You bend the bars back.");
                        player.getConfigManager().set(907, player.getConfigManager().get(907) + 1);
                        return true;
                    }
                });
                break;
            case "escape":
                player.getPacketDispatch().sendMessage("You prepare to squeeze through the bars.");
                AgilityHandler.forceWalk(player, 0, player.getLocation(), player.getLocation().transform(player.getLocation().getX() <= node.getLocation().getX() ? 1 : -1, 0, 0), Animation.create(5038), 4, 0.0, null);
                break;
            case "climb":
                player.getPacketDispatch().sendMessage("You scrape your hands and knees as you climb up.");
                AgilityHandler.forceWalk(player, 0, player.getLocation(), Location.create(3279, 3037, 0), Animation.create(5041), 10, 0, null);
                GameWorld.Pulser.submit(new Pulse(3, player) {
                    @Override
                    public boolean pulse() {
                        player.getAnimator().reset();
                        return true;
                    }
                });
                break;
            case "climb-up":
                switch (id) {
                    case 18923:
                        if (player.getLocation().getX() <= 3278) {
                            return true;
                        }
                        player.animate(Animation.create(5039));
                        GameWorld.Pulser.submit(new Pulse(6, player) {
                            @Override
                            public boolean pulse() {
                                player.getAnimator().reset();
                                player.getProperties().setTeleportLocation(Location.create(3278, 3037, 0));
                                return true;
                            }
                        });
                        break;
                }
                break;
            case "climb-down":
                switch (id) {
                    case 18924:
                        if (player.getLocation().getX() <= 3273) {
                            return true;
                        }
                        AgilityHandler.forceWalk(player, 0, player.getLocation(), Location.create(3270, 3039, 0), Animation.create(5040), 20, 0, null);
                        GameWorld.Pulser.submit(new Pulse(3, player) {
                            @Override
                            public boolean pulse() {
                                player.getAnimator().reset();
                                return true;
                            }
                        });
                        break;
                }
                break;
        }
        return true;
    }

    @Override
    public boolean isWalk(final Player player, Node node) {
        return !(node instanceof Item);
    }

    @Override
    public boolean isWalk() {
        return false;
    }

    @Override
    public Location getDestination(Node node, Node n) {
        if (n.getId() == 18923) {
            return new Location(3279, 3037, 0);
        }
        return null;
    }

    /**
     * Checks if the player contains an item.
     *
     * @param player the player.
     * @param item   the item.
     * @return the item.
     */
    private boolean hasItem(final Player player, final Item item) {
        return player.getInventory().containsItem(item) || player.getBank().containsItem(item);
    }

    /**
     * The winch dialogue plugin.
     *
     * @author 'Vexia
     * @version 1.0
     */
    public static final class WinchDialogue extends DialoguePlugin {

        /**
         * Constructs a new {@code WinchDialogue} {@code Object}.
         */
        public WinchDialogue() {
            /**
             * empty.
             */
        }

        /**
         * Constructs a new {@code WinchDialogue} {@code Object}.
         *
         * @param player the player.
         */
        public WinchDialogue(final Player player) {
            super(player);
        }

        @Override
        public DialoguePlugin newInstance(Player player) {
            return new WinchDialogue(player);
        }

        @Override
        public boolean open(Object... args) {
            if (args.length >= 1) {
                interpreter.sendDialogue("The guard notices the barrel (with Ana in it) that you're carrying.");
                stage = 500;
                return true;
            }
            interpreter.sendDialogues(4999, null, "Hey there, what do you want?");
            return true;
        }

        @Override
        public boolean handle(int interfaceId, int buttonId) {
            switch (stage) {
                case 500:// winch
                    interpreter.sendDialogues(5002, null, "Hey, that Barrel looks heavy, do you need a hand?");
                    stage++;
                    break;
                case 501:
                    player("Yes please.");
                    stage++;
                    break;
                case 502:
                    interpreter.sendDialogue("The guard comes over and helps you. He takes one end of the", "barrel.");
                    stage++;
                    break;
                case 503:
                    interpreter.sendDialogues(5002, null, "Blimey! This is heavy!");
                    stage++;
                    break;
                case 504:
                    interpreter.sendDialogues(823, null, "Why you cheeky....!");
                    stage++;
                    break;
                case 505:
                    interpreter.sendDialogues(5002, null, "<col=08088A>- The guard looks around surprised at Ana's outburst. -", "What was that?");
                    stage++;
                    break;
                case 506:
                    player("Oh, it was nothing.");
                    stage++;
                    break;
                case 507:
                    interpreter.sendDialogues(5002, null, "I could have sworn I heard something!");
                    stage++;
                    break;
                case 508:
                    interpreter.sendDialogues(823, null, "Yes you did you ignoramus.");
                    stage++;
                    break;
                case 509:
                    interpreter.sendDialogues(5002, null, "What was that you said?");
                    stage++;
                    break;
                case 510:
                    player("I said you were very gregarious!");
                    stage++;
                    break;
                case 511:
                    interpreter.sendDialogues(823, null, "You creep!");
                    stage++;
                    break;
                case 512:
                    interpreter.sendDialogues(5002, null, "Oh, right, how very nice of you to say so.", "<col=08088A>-- The guard seems flattered. --");
                    stage++;
                    break;
                case 513:
                    interpreter.sendDialogues(5002, null, "Anyway, let's get this barrel up to the surface, plenty", "more work for you to do!");
                    stage++;
                    break;
                case 514:
                    interpreter.sendDialogue("The guard places the barrel carefully on the lift platform.");
                    stage++;
                    break;
                case 515:
                    interpreter.sendDialogues(5002, null, "Oh, there's no one operating the lift up top, hope this", "barrel isn't urgent? You'd better get back to work!");
                    stage = 516;
                    break;
                case 516:
                    player.getInventory().remove(TouristTrap.ANNA_BARREL);
                    player.getBank().remove(TouristTrap.ANNA_BARREL);
                    player.getQuestRepository().getQuest(TouristTrap.NAME).setStage(player, 71);
                    end();
                    break;
                case 0:
                    options("What is this thing?", "Can I use this?");
                    stage++;
                    break;
                case 1:
                    switch (buttonId) {
                        case 1:
                            player("What is this thing?");
                            stage = 10;
                            break;
                        case 2:
                            player("Can I use this?");
                            stage = 20;
                            break;
                    }
                    break;
                case 10:
                    interpreter.sendDialogues(4999, null, "It is quite clearly a lift. Any fool can see that it's used to", "transport rock to the sruface.");
                    stage++;
                    break;
                case 11:
                    end();
                    break;
                case 20:
                    interpreter.sendDialogues(4999, null, "Of course not, you'd be doing me out of a job. Anyway", "you haven't got any barrels that need to go to", "the surface.");
                    stage++;
                    break;
                case 21:
                    end();
                    break;
            }
            return true;
        }

        @Override
        public int[] getIds() {
            return new int[]{DialogueInterpreter.getDialogueKey("winch dialogue")};
        }

    }

    /**
     * The use with handler for anna on the cart.
     *
     * @author 'Vexia
     * @version 1.0
     */
    public static final class AnnaCartHandler extends UseWithHandler {

        /**
         * Constructs a new {@code AnnaCartHandler} {@code Object}.
         */
        public AnnaCartHandler() {
            super(TouristTrap.ANNA_BARREL.getId());
        }

        @Override
        public Plugin<Object> newInstance(Object arg) throws Throwable {
            addHandler(2684, OBJECT_TYPE, this);
            addHandler(18958, OBJECT_TYPE, this);
            return this;
        }

        @Override
        public boolean handle(final NodeUsageEvent event) {
            final Player player = event.getPlayer();
            final Quest quest = player.getQuestRepository().getQuest(TouristTrap.NAME);
            if (event.getUsedWith().getId() == 18958) {// cart
                if (quest.getStage(player) == 72) {
                    player.lock(4);
                    player.animate(Animation.create(5050));
                    GameWorld.Pulser.submit(new Pulse(3, player) {
                        @Override
                        public boolean pulse() {
                            player.getInventory().remove(event.getUsedItem());
                            TouristTrap.addConfig(player, 4096 + (2048 + (1 << 4)));
                            quest.setStage(player, 80);
                            player.getDialogueInterpreter().sendDialogue("You place Ana (In the barrel) carefully on the cart. This  was the last", "barrel to go on the cart, but the cart driver doesn't seem to be", "any rush to get going. And the desert heat will soon get to Ana.");
                            ;
                            return true;
                        }
                    });
                }
                return true;
            }
            if (!event.getUsedWith().getLocation().equals(Location.create(3318, 9430, 0))) {
                return false;
            }
            if (quest.getStage(player) != 61) {
                return false;
            }
            player.getDialogueInterpreter().sendDialogue("You carefully place Ana in the barrel into the mine", "cart. Soon the cart moves out of sight and then it", "returns.");
            player.setAttribute("ana-delay", GameWorld.getTicks() + 100000000);
            ActivityManager.start(player, "ana cart", false);
            return true;
        }

        /**
         * The ana cart cutscene plugin.
         *
         * @author 'Vexia
         * @version 1.0
         */
        public static final class AnnaCartCutscene extends CutscenePlugin {

            /**
             * The paths.
             */
            private static final Location[][] PATHS = new Location[][]{{Location.create(3315, 9417, 0), Location.create(3317, 9417, 0), Location.create(3318, 9418, 0), Location.create(3318, 9428, 0)}, {Location.create(3318, 9430, 0), Location.create(3318, 9418, 0), Location.create(3317, 9417, 0), Location.create(3316, 9417, 0), Location.create(3314, 9417, 0), Location.create(3303, 9417, 0)}};

            /**
             * Constructs a new {@code AnnaCartCutscene} {@code Object}.
             */
            public AnnaCartCutscene() {
                super("ana cart");
            }

            /**
             * Constructs a new {@code AnnaCartCutscene} {@code Object}.
             *
             * @param player the player.
             */
            public AnnaCartCutscene(final Player player) {
                super("ana cart");
                this.player = player;
            }

            @Override
            public void open() {
                super.open();
                player.setAttribute("ana-delay", GameWorld.getTicks() + 100000000);
                player.faceLocation(base.transform(54, 22, 0));
                GameWorld.Pulser.submit(new Pulse(1, player) {
                    int counter;
                    NPC cart;

                    @Override
                    public boolean pulse() {
                        switch (++counter) {
                            case 1:
                                player.animate(Animation.create(5052));
                                break;
                            case 3:
                                ObjectBuilder.remove(RegionManager.getObject(base.transform(54, 22, 0)));
                                cart = NPC.create(4980, base.transform(54, 22, 0));
                                cart.init();
                                break;
                            case 4:
                                cart.getWalkingQueue().reset();
                                for (Location l : PATHS[1]) {
                                    Location loc = base.transform(l.getLocalX(), l.getLocalY(), 0);
                                    cart.getWalkingQueue().addPath(loc.getX(), loc.getY());
                                }
                                break;
                            case 18:
                                cart.clear();
                                break;
                            case 22:
                                cart = NPC.create(4981, base.transform(51, 9, 0));
                                cart.init();
                                cart.getWalkingQueue().reset();
                                for (Location l : PATHS[0]) {
                                    Location loc = base.transform(l.getLocalX(), l.getLocalY(), 0);
                                    cart.getWalkingQueue().addPath(loc.getX(), loc.getY());
                                }
                                break;
                            case 33:
                                player.getQuestRepository().getQuest(TouristTrap.NAME).setStage(player, 70);
                                player.getInventory().remove(TouristTrap.ANNA_BARREL);
                                player.removeAttribute("ana-delay");
                                AnnaCartCutscene.this.stop(true);
                                return true;
                        }
                        return false;
                    }
                });
            }

            @Override
            public ActivityPlugin newInstance(Player p) throws Throwable {
                return new AnnaCartCutscene(p);
            }

            @Override
            public Location getSpawnLocation() {
                return null;
            }

            @Override
            public Location getStartLocation() {
                return base.transform(54, 23, 0);
            }

            @Override
            public void configure() {
                region = DynamicRegion.create(13203);
                setRegionBase();
                registerRegion(region.getId());
            }
        }
    }

    /**
     * The cart dialogue plugin.
     *
     * @author 'Vexia
     * @version 1.0
     */
    public static final class CartDialogue extends DialoguePlugin {

        /**
         * Constructs a new {@code CartDialogue} {@code Object}.
         *
         * @param player the player.
         */
        public CartDialogue(final Player player) {
            super(player);
        }

        /**
         * Constructs a new {@code CartDialogue} {@code Object}.
         */
        public CartDialogue() {
            /**
             * empty.
             */
        }

        @Override
        public DialoguePlugin newInstance(Player player) {
            return new CartDialogue(player);
        }

        @Override
        public boolean open(Object... args) {
            interpreter.sendDialogue("There is space on the cart for you get on, would you like to try?");
            return true;
        }

        @Override
        public boolean handle(int interfaceId, int buttonId) {
            switch (stage) {
                case 0:
                    options("Yes, I'll get on.", "No, I've got other plans.");
                    stage++;
                    break;
                case 1:
                    switch (buttonId) {
                        case 1:
                            interpreter.sendDialogue("You decide to climb onto the cart.");
                            stage++;
                            break;
                        case 2:
                            end();
                            break;
                    }
                    break;
                case 2:
                    player.lock();
                    player.animate(ClimbActionHandler.CLIMB_UP);
                    GameWorld.Pulser.submit(new Pulse(1, player) {
                        int counter;

                        @Override
                        public boolean pulse() {
                            switch (counter++) {
                                case 1:
                                    player.getInterfaceManager().openOverlay(new Component(115));
                                    break;
                                case 4:
                                    player.getDialogueInterpreter().sendDialogue("As soon as you get on the cart, it starts to move.", "Before too long you are past the gates. You jump off", "the cart taking Ana with you.");
                                    break;
                                case 6:
                                    player.unlock();
                                    player.getInterfaceManager().closeOverlay();
                                    player.getQuestRepository().getQuest(TouristTrap.NAME).setStage(player, 95);
                                    player.getInterfaceManager().close();
                                    player.getProperties().setTeleportLocation(Location.create(3258, 3029, 0));
                                    player.getInventory().add(TouristTrap.ANNA_BARREL);
                                    return true;
                            }
                            return false;
                        }
                    });
                    end();
                    break;
            }
            return true;
        }

        @Override
        public int[] getIds() {
            return new int[]{DialogueInterpreter.getDialogueKey("ana cart dialogue")};
        }

    }

    /**
     * The minecart dialogue plugin.
     *
     * @author 'Vexia
     * @version 1.0
     */
    public static final class MineCartDialogue extends DialoguePlugin {

        /**
         * The failing animation of getting in the cart.
         */
        private static final Animation FAIL_ANIMATION = new Animation(5048);

        /**
         * The jumping animation.
         */
        private static final Animation JUMP_ANIMATION = new Animation(5049);

        /**
         * The cart.
         */
        private GameObject cart;

        /**
         * Constructs a new {@code MineCartDialogue} {@code Object}.
         */
        public MineCartDialogue() {
            /**
             * empty.
             */
        }

        /**
         * Constructs a new {@code MineCartDialogue} {@code Object}.
         *
         * @param player the player.
         */
        public MineCartDialogue(final Player player) {
            super(player);
        }

        @Override
        public void init() {
            super.init();
            PluginManager.definePlugin(new MiningCartCutscene());
        }

        @Override
        public DialoguePlugin newInstance(Player player) {
            return new MineCartDialogue(player);
        }

        @Override
        public boolean open(Object... args) {
            cart = (GameObject) args[0];
            if (player.getInventory().containsItem(TouristTrap.ANNA_BARREL)) {
                player("There's not enough room for both of us.");
                stage = -1;
                return true;
            }
            interpreter.sendDialogue("You search the mine cart...");
            return true;
        }

        @Override
        public boolean handle(int interfaceId, int buttonId) {
            switch (stage) {
                case -1:
                    end();
                    break;
                case 0:
                    interpreter.sendDialogue("There may be just enough space to squeeze yourself into the cart.", "Would you like to try?");
                    stage++;
                    break;
                case 1:
                    options("Yes, of course.", "No thanks, it looks pretty dangerous.");
                    stage++;
                    break;
                case 2:
                    switch (buttonId) {
                        case 1:
                            end();
                            enterCart(player);
                            break;
                        case 2:
                            end();
                            break;
                    }
                    break;
            }
            return true;
        }

        /**
         * Method used to enter the cart.
         *
         * @param player the player.
         */
        public void enterCart(final Player player) {
            if (RandomFunction.random(3) == 1) {
                player.lock(FAIL_ANIMATION.getDelay());
                player.animate(FAIL_ANIMATION);
                player.getImpactHandler().manualHit(player, 2, HitsplatType.NORMAL);
                player.getPacketDispatch().sendMessages("You fail to fit yourself into the cart in time before it starts its journey.", "You bang your head on the cart as you try to jump in.");
            } else {
                GameWorld.Pulser.submit(new Pulse(3, player) {
                    @Override
                    public boolean pulse() {
                        player.animate(JUMP_ANIMATION);
                        return true;
                    }
                });
                ActivityManager.start(player, "mining cart", false, cart.getLocation().getX() == 3303 ? 0 : 1);
            }
        }

        /**
         * The mining cart cutscene plugin.
         *
         * @author 'Vexia
         * @version 1.0
         */
        public static final class MiningCartCutscene extends CutscenePlugin {

            /**
             * The paths.
             */
            private static final Location[][] PATHS = new Location[][]{{Location.create(3303, 9417, 0), Location.create(3316, 9417, 0), Location.create(3317, 9417, 0), Location.create(3318, 9418, 0), Location.create(3318, 9430, 0)}, {Location.create(3318, 9430, 0), Location.create(3318, 9418, 0), Location.create(3317, 9417, 0), Location.create(3303, 9417, 0)}};

            /**
             * The path index.
             */
            private int index;

            /**
             * Constructs a new {@code MiningCartCutscene} {@code Object}.
             */
            public MiningCartCutscene() {
                super("mining cart");
            }

            /**
             * Constructs a new {@code MiningCartCutscene} {@code Object}.
             *
             * @param player the player.
             */
            public MiningCartCutscene(final Player player) {
                super("mining cart");
                this.player = player;
            }

            @Override
            public boolean start(final Player player, boolean login, Object... args) {
                index = (int) args[0];
                return super.start(player, login, args);
            }

            @Override
            public void open() {
                ObjectBuilder.remove(RegionManager.getObject(base.getLocation().transform(getPath()[0].getLocalX(), getPath()[0].getLocalY(), 0)));
                ObjectBuilder.remove(RegionManager.getObject(base.getLocation().transform(getPath()[getPath().length - 1].getLocalX(), getPath()[getPath().length - 1].getLocalY(), 0)));
                player.getAppearance().setAnimations(Animation.create(2148));
                player.getAppearance().setRidingMinecart(true);
                player.getAppearance().sync();
                player.getWalkingQueue().reset();
                for (Location l : getPath()) {
                    Location loc = base.transform(l.getLocalX(), l.getLocalY(), 0);
                    player.getWalkingQueue().addPath(loc.getX(), loc.getY(), true);
                }
                GameWorld.Pulser.submit(new Pulse(22, player) {
                    @Override
                    public boolean pulse() {
                        player.setAttribute("real-end", index == 0 ? Location.create(3319, 9431, 0) : Location.create(3303, 9416, 0));
                        player.setAttribute("cutscene:original-loc", index == 0 ? Location.create(3319, 9431, 0) : Location.create(3303, 9416, 0));
                        MiningCartCutscene.this.stop(true);
                        return true;
                    }
                });
            }

            @Override
            public void end() {
                super.end();
                player.getAppearance().setDefaultAnimations();
                player.getAppearance().setRidingMinecart(false);
                player.getAppearance().sync();
            }

            @Override
            public void fade() {
                if (index == 0) {
                    player.getDialogueInterpreter().sendDialogue("You appear in a large open room with what looks like lots of miners", "working away. This is a very rough looking area, the miners look like", "they're on their last legs.");
                } else {
                    player.getDialogueInterpreter().sendDialogue("You appear back in the barrel loading room. A nearby slave looks", "surprised to see you popping out of the cart.");
                }
            }

            @Override
            public ActivityPlugin newInstance(Player p) throws Throwable {
                return new MiningCartCutscene(p);
            }

            @Override
            public Location getSpawnLocation() {
                return null;
            }

            @Override
            public Location getStartLocation() {
                return base.transform(getPath()[0].getLocalX(), getPath()[0].getLocalY(), 0);
            }

            @Override
            public void configure() {
                region = DynamicRegion.create(13203);
                setRegionBase();
                registerRegion(region.getId());
            }

            /**
             * Gets the path index.
             *
             * @return the location.
             */
            public Location[] getPath() {
                return PATHS[index];
            }
        }

        @Override
        public int[] getIds() {
            return new int[]{DialogueInterpreter.getDialogueKey("cart dialogue")};
        }

    }

    /**
     * The winch cutscene plugin.
     *
     * @author 'Vexia
     * @version 1.0
     */
    public static final class WinchCutscene extends CutscenePlugin {

        /**
         * Constructs a new {@code WinchCutscene} {@code Object}.
         */
        public WinchCutscene() {
            super("winch cutscene");
        }

        /**
         * Constructs a new {@code WinchCutscene} {@code Object}.
         *
         * @param player the player.
         */
        public WinchCutscene(final Player player) {
            super("winch cutscene");
            this.player = player;
        }

        @Override
        public ActivityPlugin newInstance(Player p) throws Throwable {
            return new WinchCutscene(p);
        }

        @Override
        public void open() {
            super.open();
            player.getPacketDispatch().sendMessage("You try to operate the winch.");
            player.faceLocation(base.transform(15, 9, 0));
            player.animate(Animation.create(5054));
            GameWorld.Pulser.submit(new Pulse(AnimationDefinition.forId(5054).getDurationTicks(), player) {
                @Override
                public boolean pulse() {
                    TouristTrap.addConfig(player, 2048 + (1 << 4));
                    WinchCutscene.this.stop(true);
                    player.getDialogueInterpreter().open(822, true, true);
                    return true;
                }
            });
        }

        @Override
        public Location getSpawnLocation() {
            return null;
        }

        @Override
        public Location getStartLocation() {
            return base.transform(16, 10, 0);
        }

        @Override
        public void configure() {
            region = DynamicRegion.create(13103);
            setRegionBase();
            registerRegion(region.getId());
        }

    }

    /**
     * The use with handler on the chest.
     *
     * @author 'Vexia
     * @version 1.0
     */
    public static final class BedabinKeyHandler extends UseWithHandler {

        /**
         * Constructs a new {@code BedabinKeyHandler} {@code Object}.
         */
        public BedabinKeyHandler() {
            super(TouristTrap.BEDABIN_KEY.getId());
        }

        @Override
        public Plugin<Object> newInstance(Object arg) throws Throwable {
            addHandler(2677, OBJECT_TYPE, this);
            return this;
        }

        @Override
        public boolean handle(NodeUsageEvent event) {
            final Player player = event.getPlayer();
            player.getDialogueInterpreter().open(831, RegionManager.getNpc(player, 831));
            return true;
        }

    }

    /**
     * The barrel dialogue plugin.
     *
     * @author 'Vexia
     * @version 1.0
     */
    public static final class BarrelDialogue extends DialoguePlugin {

        /**
         * The game object barrel.
         */
        private GameObject barrel;

        /**
         * The quest.
         */
        private Quest quest;

        /**
         * Constructs a new {@code BarrelDialogue} {@code Object}.
         */
        public BarrelDialogue() {
            /**
             * empty.
             */
        }

        /**
         * Constructs a new {@code BarrelDialogue} {@code Object}.
         *
         * @param player the player.
         */
        public BarrelDialogue(final Player player) {
            super(player);
        }

        @Override
        public DialoguePlugin newInstance(Player player) {
            return new BarrelDialogue(player);
        }

        @Override
        public boolean open(Object... args) {
            barrel = (GameObject) args[0];
            quest = player.getQuestRepository().getQuest(TouristTrap.NAME);
            if ((quest.getStage(player) == 70 || quest.getStage(player) == 72) && !player.hasItem(TouristTrap.ANNA_BARREL)) {
                interpreter.sendDialogue("You search the barrels and find Ana.");
                stage = 400;
                return true;
            }
            if (player.getInventory().containsItem(TouristTrap.BARREL)) {
                player.getPacketDispatch().sendMessage("You can only manage to have one of these at a time.");
                end();
                return true;
            }
            interpreter.sendItemMessage(TouristTrap.BARREL, "This barrel is quite big. but you may be able to carry one. Would you like to take one?");
            return true;
        }

        @Override
        public boolean handle(int interfaceId, int buttonId) {
            switch (stage) {
                case 400:// finding anna.
                    interpreter.sendDialogues(823, null, "Let me out!");
                    stage++;
                    break;
                case 401:
                    if (!player.getInventory().hasSpaceFor(TouristTrap.ANNA_BARREL)) {
                        end();
                        return true;
                    }
                    player.getPacketDispatch().sendMessage("You pick up Ana in a Barrel.");
                    player.getInventory().add(TouristTrap.ANNA_BARREL);
                    end();
                    break;
                case 402:
                    end();
                    break;
                case 0:
                    options("Yeah, cool!", "No thanks.");
                    stage++;
                    break;
                case 1:
                    switch (buttonId) {
                        case 1:
                            interpreter.sendItemMessage(TouristTrap.BARREL, "You take the barrel, it's not heavy, just awkward.");
                            stage = 3;
                            break;
                        case 2:
                            end();
                            break;
                    }
                    break;
                case 3:
                    end();
                    player.getInventory().add(TouristTrap.BARREL, player);
                    ObjectBuilder.remove(barrel);
                    GameWorld.Pulser.submit(new Pulse(40) {
                        @Override
                        public boolean pulse() {
                            ObjectBuilder.add(barrel);
                            return true;
                        }
                    });
                    break;

            }
            return true;
        }

        @Override
        public int[] getIds() {
            return new int[]{DialogueInterpreter.getDialogueKey("barrel dialogue")};
        }

    }

    /**
     * The use with handler for the bedabin anvil.
     *
     * @author 'Vexia
     * @version 1.0
     */
    public static final class BedabinAnvilHandler extends UseWithHandler {

        /**
         * Constructs a new {@code BedabinAnvilHandler} {@code Object}.
         */
        public BedabinAnvilHandler() {
            super(2349);
        }

        @Override
        public Plugin<Object> newInstance(Object arg) throws Throwable {
            addHandler(2672, OBJECT_TYPE, this);
            PluginManager.definePlugin(new PrototypeDartHandler());
            PluginManager.definePlugin(new BedabinAnvilDialogue());
            return this;
        }

        @Override
        public boolean handle(NodeUsageEvent event) {
            final Player player = event.getPlayer();
            final Quest quest = player.getQuestRepository().getQuest(TouristTrap.NAME);
            if (quest.getStage(player) == 54 && player.getInventory().containsItem(TouristTrap.TECHNICAL_PLANS)) {
                player.getDialogueInterpreter().open("bedabin-anvil");
                return true;
            } else {
                player.getPacketDispatch().sendMessage("You need technical plans in order to do this.");
            }
            return true;
        }

        /**
         * The handler used to handle the anna barrel on the winch.
         *
         * @author 'Vexia
         * @version 1.0
         */
        public static final class AnnaWinchHandler extends UseWithHandler {

            /**
             * Constructs a new {@code AnnaWinchHandler} {@code Object}.
             */
            public AnnaWinchHandler() {
                super(TouristTrap.ANNA_BARREL.getId());
            }

            @Override
            public Plugin<Object> newInstance(Object arg) throws Throwable {
                addHandler(18951, OBJECT_TYPE, this);
                return this;
            }

            @Override
            public boolean handle(NodeUsageEvent event) {
                final Player player = event.getPlayer();
                final Quest quest = player.getQuestRepository().getQuest(TouristTrap.NAME);
                if (!event.getUsedWith().getLocation().equals(Location.create(3292, 9423, 0))) {
                    return false;
                }
                if (quest.getStage(player) == 70) {
                    player.getDialogueInterpreter().open("winch dialogue", true);
                    return true;
                }
                return false;
            }

        }

        /**
         * The prototype dart creation handler.
         *
         * @author 'Vexia
         * @version 1.0
         */
        public static final class PrototypeDartHandler extends UseWithHandler {

            /**
             * Constructs a new {@code PrototypeDartHandler} {@code Object}.
             */
            public PrototypeDartHandler() {
                super(314);
            }

            @Override
            public Plugin<Object> newInstance(Object arg) throws Throwable {
                addHandler(TouristTrap.PROTOTYPE_DART_TIP.getId(), ITEM_TYPE, this);
                PluginManager.definePlugin(new ProtoTypeDialogue());
                return this;
            }

            @Override
            public boolean handle(NodeUsageEvent event) {
                final Player player = event.getPlayer();
                player.getDialogueInterpreter().open("prototype dart");
                return true;
            }

            /**
             * The prototype dialogue plugin.
             *
             * @author 'Vexia
             * @version 1.0
             */
            public static final class ProtoTypeDialogue extends DialoguePlugin {

                /**
                 * The feathers item.
                 */
                private static final Item FEATHERS = new Item(314, 10);

                /**
                 * Constructs a new {@code ProtoTypeDialogue} {@code Object}.
                 *
                 * @param player the player.
                 */
                public ProtoTypeDialogue(final Player player) {
                    super(player);
                }

                /**
                 * Constructs a new {@code ProtoTypeDialogue} {@code Object}.
                 */
                public ProtoTypeDialogue() {
                    /**
                     * empty.
                     */
                }

                @Override
                public DialoguePlugin newInstance(Player player) {
                    return new ProtoTypeDialogue(player);
                }

                @Override
                public boolean open(Object... args) {
                    if (!player.getInventory().containsItem(FEATHERS)) {
                        interpreter.sendDialogue("You need 10 feathers in order to do this.");
                        stage = 10;
                        return false;
                    }
                    interpreter.sendItemMessage(314, "You try to attach feathers to the bronze dart tip.");
                    stage = 1;
                    return true;
                }

                @Override
                public boolean handle(int interfaceId, int buttonId) {
                    switch (stage) {
                        case 1:
                            interpreter.sendDialogue("Following the plans is tricky, but you persevere.");
                            stage++;
                            break;
                        case 2:
                            interpreter.sendItemMessage(TouristTrap.PROTOTYPE_DART, "You succesfully attach the feathers to the dart tip.");
                            stage++;
                            break;
                        case 3:
                            player.getInventory().remove(FEATHERS, TouristTrap.PROTOTYPE_DART_TIP);
                            player.getInventory().add(TouristTrap.PROTOTYPE_DART);
                            end();
                            break;
                        case 4:
                            end();
                            break;
                    }
                    return true;
                }

                @Override
                public int[] getIds() {
                    return new int[]{DialogueInterpreter.getDialogueKey("prototype dart")};
                }

            }
        }

        /**
         * The bedabin dialogue plugin handler.
         *
         * @author 'Vexia
         * @version 1.0
         */
        public static final class BedabinAnvilDialogue extends DialoguePlugin {

            /**
             * Constructs a new {@code BedabinAnvilDialogue} {@code Object}.
             */
            public BedabinAnvilDialogue() {
                /**
                 * empty.
                 */
            }

            /**
             * Constructs a new {@code BedabinAnvilDialogue} {@code Object}.
             *
             * @param player the player.
             */
            public BedabinAnvilDialogue(final Player player) {
                super(player);
            }

            @Override
            public DialoguePlugin newInstance(Player player) {
                return new BedabinAnvilDialogue(player);
            }

            @Override
            public boolean open(Object... args) {
                if (!player.getInventory().containsItem(TouristTrap.TECHNICAL_PLANS)) {
                    player.getPacketDispatch().sendMessage("You need the plans to do this.");
                    return false;
                }
                player.getDialogueInterpreter().sendItemMessage(TouristTrap.TECHNICAL_PLANS, "Do you want to follow the technical plans ?");
                return true;
            }

            @Override
            public boolean handle(int interfaceId, int buttonId) {
                switch (stage) {
                    case 0:
                        options("Yes. I'd like to try.", "No, not just yet.");
                        stage++;
                        break;
                    case 1:
                        switch (buttonId) {
                            case 1:
                                end();
                                player.getPulseManager().run(new ProtoTypePulse(player));
                                break;
                            case 2:
                                end();
                                break;
                        }
                        break;
                }
                return true;
            }

            /**
             * The skill pulse used to make a prototype dart.
             *
             * @author 'Vexia
             * @version 1.0
             */
            public static final class ProtoTypePulse extends SkillPulse<GameObject> {

                /**
                 * The hammer item.
                 */
                private static final Item HAMMER = new Item(2347);

                /**
                 * The ticks passed.
                 */
                private int ticks;

                /**
                 * Constructs a new {@code ProtoTypePulse} {@code Object}.
                 *
                 * @param player the player.
                 */
                public ProtoTypePulse(Player player) {
                    super(player, null);
                }

                @Override
                public boolean checkRequirements() {
                    if (!player.getInventory().hasSpaceFor(TouristTrap.PROTOTYPE_DART_TIP)) {
                        player.getPacketDispatch().sendMessage("Not enough inventory space.");
                        return false;
                    }
                    if (!player.getInventory().containsItem(HAMMER)) {
                        player.getPacketDispatch().sendMessage("You need a hammer in order to work metal with.");
                        return false;
                    }
                    if (!player.getInventory().containsItem(Bar.BRONZE.getProduct())) {
                        return false;
                    }
                    return true;
                }

                @Override
                public void animate() {
                    if (ticks % 4 == 0) {
                        player.animate(Animation.create(898));
                    }
                }

                @Override
                public boolean reward() {
                    if (++ticks % 4 != 0) {
                        return false;
                    }
                    if (ticks == 4) {
                        player.getDialogueInterpreter().sendPlainMessage(true, "You begin experimenting in forging the weapon...");
                    } else if (ticks == 8) {
                        player.getInventory().remove(Bar.BRONZE.getProduct());
                        player.getInventory().add(TouristTrap.PROTOTYPE_DART_TIP);
                        player.getDialogueInterpreter().sendItemMessage(TouristTrap.PROTOTYPE_DART_TIP, "You follow the plans carefully, and after some careful", "work, you finally manage to forge a sharp, pointed...", "dart tip.");
                        return true;
                    }
                    return false;
                }

                @Override
                public void stop() {
                    super.stop();
                }

            }

            @Override
            public int[] getIds() {
                return new int[]{DialogueInterpreter.getDialogueKey("bedabin-anvil")};
            }

        }
    }
}
