package plugin.activity.guild;

import java.util.List;

import core.cache.def.impl.ObjectDefinition;
import core.game.component.Component;
import core.game.component.ComponentDefinition;
import core.game.component.ComponentPlugin;
import core.game.container.impl.EquipmentContainer;
import core.game.content.ItemNames;
import core.game.node.entity.Entity;
import core.game.node.entity.combat.handlers.RangeSwingHandler;
import core.game.node.entity.npc.AbstractNPC;
import core.game.node.entity.player.link.diary.DiaryType;
import core.plugin.PluginManager;
import core.tools.RandomFunction;
import plugin.dialogue.DialoguePlugin;
import plugin.dialogue.FacialExpression;
import core.game.content.global.Skillcape;
import core.game.content.global.action.ClimbActionHandler;
import core.game.content.global.action.DoorActionHandler;
import plugin.npc.other.BorkNPC;
import plugin.skill.Skills;
import plugin.skill.crafting.TanningProduct;
import core.game.interaction.OptionHandler;
import core.game.node.Node;
import core.game.node.entity.combat.equipment.Ammunition;
import core.game.node.entity.combat.equipment.WeaponInterface;
import core.game.node.entity.impl.Projectile;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.game.node.item.Item;
import core.game.node.object.GameObject;
import core.game.system.task.Pulse;
import core.game.world.map.Location;
import core.game.world.map.RegionManager;
import core.game.world.update.flag.context.Animation;
import core.plugin.InitializablePlugin;
import core.plugin.Plugin;

/**
 * Represents the plugin used for the ranging guild.
 *
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class RangingGuildPlugin extends OptionHandler {

    @Override
    public Plugin<Object> newInstance(Object arg) throws Throwable {
        ObjectDefinition.forId(2514).getHandlers().put("option:open", this);
        ObjectDefinition.forId(2511).getHandlers().put("option:climb-up", this);
        ObjectDefinition.forId(2512).getHandlers().put("option:climb-down", this);
        ObjectDefinition.forId(2513).getHandlers().put("option:fire-at", this);
        new RangingGuildDoorman().init();
        new GuardDialogue().init();
        new LeatherWorkerDialogue().init();
        new ArmourSalesman().init();
        new TribalWeaponSalesman().init();
        new BowArrowSalesman().init();
        new WarningInterface().newInstance(arg);
        new CompetitionJudge().init();
        PluginManager.definePlugin(new TowerArcher());
        return this;
    }

    @Override
    public boolean handle(Player player, Node node, String option) {
        final int id = node instanceof GameObject ? ((GameObject) node).getId() : 0;
        switch (option) {
            case "fire-at":
                if (player.getArcheryTargets() <= 0) {
                    player.getDialogueInterpreter().sendDialogues(693, null, "Sorry, you may only use the targets for the", "competition, not for practicing.");
                    return true;
                }
                if (!player.getEquipment().containsItem(new Item(882))
                        || player.getEquipment().get(EquipmentContainer.SLOT_WEAPON) == null
                        || (!player.getEquipment().get(EquipmentContainer.SLOT_WEAPON).getDefinition().getName().toLowerCase().contains("shortbow")
                        && !player.getEquipment().get(EquipmentContainer.SLOT_WEAPON).getDefinition().getName().toLowerCase().contains("longbow"))) {
                    player.sendMessage("You must have bronze arrows and a bow equipped.");
                    return true;
                }
                player.getPulseManager().run(new ArcheryCompetitionPulse(player, (GameObject) node));
                break;
            case "open":
                switch (id) {
                    case 2514:
                        if (player.getLocation().getY() >= 3438) {
                            if (player.getSkills().getStaticLevel(Skills.RANGE) < 40) {
                                player.getDialogueInterpreter().sendDialogue("You need a Ranging level of 40 to enter here.");
                                return true;
                            }
                        }
                        DoorActionHandler.handleAutowalkDoor(player, (GameObject) node, player.getLocation().getY() >= 3438 ? Location.create(2659, 3437, 0) : Location.create(2657, 3439, 0));
                        break;
                }
                break;
            case "climb-up":
                switch (id) {
                    case 2511:
                        player.setAttribute("ladder", node);
                        player.getInterfaceManager().open(new Component(564));
                        break;
                }
                break;
            case "climb-down":
                switch (id) {
                    case 2512:
                        ClimbActionHandler.climb(player, null, Location.create(2668, 3427, 0));
                        break;
                }
                break;
        }
        return true;
    }

    @Override
    public Location getDestination(Node node, Node n) {
        if (n instanceof GameObject) {
            if (((GameObject) n).getDefinition().hasAction("open")) {
                if (((GameObject) n).getId() == 2514) {
                    if (node.getLocation().getY() >= 3438) {
                        return Location.create(2657, 3439, 0);
                    } else {
                        return Location.create(2659, 3437, 0);
                    }
                }
                return DoorActionHandler.getDestination((Player) node, (GameObject) n);
            }
            if (((GameObject) n).getId() == 2513)
                return Location.create(2673, 3420, 0);
        }
        return null;
    }

    /**
     * Represents the dialogue used for the ranging guild door man.
     *
     * @author 'Vexia
     * @version 1.0
     */
    public class RangingGuildDoorman extends DialoguePlugin {

        /**
         * Constructs a new {@code RangingGuildDoorman} {@code Object}.
         */
        public RangingGuildDoorman() {
            /**
             * empty.
             */
        }

        /**
         * Constructs a new {@code RangingGuildDoorman} {@code Object}.
         *
         * @param player the player.
         */
        public RangingGuildDoorman(Player player) {
            super(player);
        }

        @Override
        public DialoguePlugin newInstance(Player player) {
            return new RangingGuildDoorman(player);
        }

        @Override
        public boolean open(Object... args) {
            npc = (NPC) args[0];
            interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Hello there.");
            stage = 0;
            return true;
        }

        @Override
        public boolean handle(int interfaceId, int buttonId) {
            switch (stage) {
                case 0:
                    interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Greetings. If you are an experienced archer, you may", "want to visit the guild here...");
                    stage = 1;
                    break;
                case 1:
                    end();
                    break;
            }
            return true;
        }

        @Override
        public int[] getIds() {
            return new int[]{679};
        }
    }

    /**
     * Represents the tribal weapon salseman dialogue.
     *
     * @author 'Vexia
     * @version 1.0
     */
    public final class TribalWeaponSalesman extends DialoguePlugin {

        /**
         * Constructs a new {@code TribalWeaponSalesman} {@code Object}.
         */
        public TribalWeaponSalesman() {
            /**
             * empty.
             */
        }

        /**
         * Constructs a new {@code TribalWeaponSalesman} {@code Object}.
         *
         * @param player the player.
         */
        public TribalWeaponSalesman(final Player player) {
            super(player);
        }

        @Override
        public DialoguePlugin newInstance(Player player) {
            return new TribalWeaponSalesman(player);
        }

        @Override
        public boolean open(Object... args) {
            npc = (NPC) args[0];
            player("Hello there.");
            stage = 0;
            return true;
        }

        @Override
        public boolean handle(int interfaceId, int buttonId) {
            switch (stage) {
                case 0:
                    npc("Greetings, traveller. Are you interested in any throwing", "weapons?");
                    stage = 1;
                    break;
                case 1:
                    options("Yes I am.", "Not really.");
                    stage = 2;
                    break;
                case 2:
                    switch (buttonId) {
                        case 1:
                            player("Yes I am.");
                            stage = 10;
                            break;
                        case 2:
                            player("Not really.");
                            stage = 20;
                            break;
                    }
                    break;
                case 10:
                    npc("That is a good thing.");
                    stage = 11;
                    break;
                case 11:
                    end();
                    npc.openShop(player);
                    break;
                case 20:
                    npc("No bother to me.");
                    stage = 21;
                    break;
                case 21:
                    end();
                    break;
            }
            return true;
        }

        @Override
        public int[] getIds() {
            return new int[]{692};
        }

    }

    /**
     * Represents the guard dialogue.
     *
     * @author 'Vexia
     * @version 1.0
     */
    public final class GuardDialogue extends DialoguePlugin {

        /**
         * Constructs a new {@code GuardDialogue} {@code Object}.
         */
        public GuardDialogue() {
            /**
             * empty.
             */
        }

        /**
         * Constructs a new {@code GuardDialogue} {@code Object}.
         *
         * @param player the player.
         */
        public GuardDialogue(final Player player) {
            super(player);
        }

        @Override
        public DialoguePlugin newInstance(Player player) {
            return new GuardDialogue(player);
        }

        @Override
        public boolean open(Object... args) {
            player("Hello there.");
            stage = 0;
            return true;
        }

        @Override
        public boolean handle(int interfaceId, int buttonId) {
            switch (stage) {
                case 0:
                    npc("Greetings, traveller. Enjoy the time at the Ranging", "Guild.");
                    stage = 1;
                    break;
                case 1:
                    end();
                    break;
            }
            return true;
        }

        @Override
        public int[] getIds() {
            return new int[]{678};
        }

    }

    /**
     * Represents the leather workers dialogue.
     *
     * @author 'Vexia
     * @version 1.0
     */
    public final class LeatherWorkerDialogue extends DialoguePlugin {

        /**
         * Constructs a new {@code LeatherWorkerDialogue} {@code Object}.
         */
        public LeatherWorkerDialogue() {
            /**
             * empty.
             */
        }

        /**
         * Constructs a new {@code LeatherWorkerDialogue} {@code Object}.
         *
         * @param player the player.
         */
        public LeatherWorkerDialogue(final Player player) {
            super(player);
        }

        @Override
        public DialoguePlugin newInstance(Player player) {
            return new LeatherWorkerDialogue(player);
        }

        @Override
        public boolean open(Object... args) {
            player("Hello.");
            stage = 0;
            return true;
        }

        @Override
        public boolean handle(int interfaceId, int buttonId) {
            switch (stage) {
                case 0:
                    npc("Can I help you?");
                    stage = 1;
                    break;
                case 1:
                    options("What do you do here?", "No thanks.");
                    stage = 2;
                    break;
                case 2:
                    switch (buttonId) {
                        case 1:
                            player("What do you do here?");
                            stage = 10;
                            break;
                        case 2:
                            player("No thanks.");
                            stage = 20;
                            break;
                    }
                    break;
                case 10:
                    npc("Well, I can cure plain cowhides into pieces of leather", "ready for crafting.");
                    stage = 11;
                    break;
                case 11:
                    npc("I work with ordinary, hard or dragonhide leather and", "also snakeskin.");
                    stage = 12;
                    break;
                case 12:
                    end();
                    TanningProduct.open(player, 680);
                    break;
                case 20:
                    npc("Suit yourself.");
                    stage = 21;
                    break;
                case 21:
                    end();
                    break;
            }
            return true;
        }

        @Override
        public int[] getIds() {
            return new int[]{680};
        }

    }

    /**
     * Represents the armour salesman dialogue.
     *
     * @author 'Vexia
     * @version 1.0
     */
    public final class ArmourSalesman extends DialoguePlugin {

        /**
         * Constructs a new {@code ArmourSalesman} {@code Object}.
         */
        public ArmourSalesman() {
            /**
             * empty.
             */
        }

        /**
         * Constructs a new {@code ArmourSalesman} {@code Object}.
         *
         * @param player the player.
         */
        public ArmourSalesman(final Player player) {
            super(player);
        }

        @Override
        public DialoguePlugin newInstance(Player player) {
            return new ArmourSalesman(player);
        }

        @Override
        public boolean open(Object... args) {
            npc = (NPC) args[0];
            player("Good day to you.");
            stage = 0;
            return true;
        }

        @Override
        public boolean handle(int interfaceId, int buttonId) {
            switch (stage) {
                case 0:
                    npc("And to you. Can I help you?");
                    stage = 1;
                    break;
                case 1:
                    if (Skillcape.isMaster(player, Skills.RANGE)) {
                        options("What do you do here?", "I'd like to see what you sell.", "Can I buy a Skillcape of Range?", "I've seen enough, thanks.");
                        stage = 100;
                    } else {
                        options("What do you do here?", "I'd like to see what you sell.", "I've seen enough, thanks.");
                        stage = 2;
                    }
                    break;
                case 2:
                    switch (buttonId) {
                        case 1:
                            player("What do you do here?");
                            stage = 10;
                            break;
                        case 2:
                            player("I'd like to see what you sell.");
                            stage = 20;
                            break;
                        case 3:
                            player("I've seen enough, thanks.");
                            stage = 30;
                            break;
                    }
                    break;
                case 10:
                    npc("I am a supplier of leather armours and accessories. Ask", "and I will tell you what I know.");
                    stage = 11;
                    break;
                case 11:
                    options("Tell me about your armours.", "Tell me about your accessories.", "I've seen enough, thanks.");
                    stage = 12;
                    break;
                case 12:
                    switch (buttonId) {
                        case 1:
                            player("Tell me about your armours.");
                            stage = 13;
                            break;
                        case 2:
                            player("Tell me about your accessories.");
                            stage = 15;
                            break;
                        case 3:
                            player("I've seen enough, thanks.");
                            stage = 30;
                            break;
                    }
                    break;
                case 13:
                    npc("I have normal, studded and hard types.");
                    stage = 14;
                    break;
                case 14:
                case 31:
                case 105:
                    end();
                    break;
                case 15:
                    npc("Ah yes we have a new range of accessories in stock.", "Essential items for an archer like you.");
                    stage = 14;
                    break;
                case 20:
                    npc("Indeed, cast your eyes on my wares, adventurer.");
                    stage = 21;
                    break;
                case 21:
                    end();
                    npc.openShop(player);
                    break;
                case 30:
                    npc("Very good, adventurer.");
                    stage = 31;
                    break;
                case 100:
                    switch (buttonId) {
                        case 1:
                            player("What do you do here?");
                            stage = 10;
                            break;
                        case 2:
                            player("I'd like to see what you sell.");
                            stage = 20;
                            break;
                        case 3:
                            player("Can I buy a Skillcape of Range?");
                            stage = 101;
                            break;
                        case 4:
                            player("I've seen enough, thanks.");
                            stage = 30;
                            break;
                    }
                    break;
                case 101:
                    npc("Certainly! Right when you give me 99000 coins.");
                    stage = 102;
                    break;
                case 102:
                    options("Okay, here you go.", "No, thanks.");
                    stage = 103;
                    break;
                case 103:
                    switch (buttonId) {
                        case 1:
                            player("Okay, here you go.");
                            stage = 104;
                            break;
                        case 2:
                            end();
                            break;
                    }
                    break;
                case 104:
                    if (Skillcape.purchase(player, Skills.RANGE)) {
                        npc("There you go! Enjoy.");
                    }
                    stage = 105;
                    break;
            }
            return true;
        }

        @Override
        public int[] getIds() {
            return new int[]{682};
        }

    }

    /**
     * Represents the dialogue plugin used for the bow and arrow salesman.
     *
     * @author 'Vexia
     * @version 1.0
     */
    public final class BowArrowSalesman extends DialoguePlugin {

        /**
         * Constructs a new {@code BowArrowSalesman} {@code Object}.
         */
        public BowArrowSalesman() {
            /**
             * empty.
             */
        }

        /**
         * Constructs a new {@code BowArrowSalesman} {@code Object}.
         *
         * @param player the player.
         */
        public BowArrowSalesman(final Player player) {
            super(player);
        }

        @Override
        public DialoguePlugin newInstance(Player player) {
            return new BowArrowSalesman(player);
        }

        @Override
        public boolean open(Object... args) {
            npc = (NPC) args[0];
            player("Hello.");
            stage = 0;
            return true;
        }

        @Override
        public boolean handle(int interfaceId, int buttonId) {
            switch (stage) {
                case 0:
                    npc("A fair day, traveller. Would you like to see my wares?");
                    stage = 1;
                    break;
                case 1:
                    options("Yes please.", "No thanks.");
                    stage = 2;
                    break;
                case 2:
                    switch (buttonId) {
                        case 1:
                            end();
                            npc.openShop(player);

                            break;
                        case 2:
                            end();
                            break;
                    }
                    break;
            }
            return true;
        }

        @Override
        public int[] getIds() {
            return new int[]{683};
        }

    }

    /**
     * Represents the waring interface used in the guild.
     *
     * @author 'Vexia
     * @version 1.0
     */
    public final class WarningInterface extends ComponentPlugin {

        @Override
        public Plugin<Object> newInstance(Object arg) throws Throwable {
            ComponentDefinition.forId(564).setPlugin(this);
            return this;
        }

        @Override
        public boolean handle(Player player, Component component, int opcode, int button, int slot, int itemId) {
            switch (button) {
                case 17:
                    ClimbActionHandler.climb(player, null, Location.create(2668, 3427, 2));
                    player.getInterfaceManager().close();
                    List<NPC> npcs = RegionManager.getLocalNpcs(Location.create(2668, 3427, 2));
                    String dir = "";
                    for (NPC n : npcs) {
                        if (n.getId() >= 684 && n.getId() <= 687) {
                            switch (n.getId()) {
                                case 684:
                                    dir = "north";
                                    break;
                                case 685:
                                    dir = "east";
                                    break;
                                case 686:
                                    dir = "south";
                                    break;
                                case 687:
                                    dir = "west";
                                    break;
                            }
                            n.sendChat("The " + dir + " tower is occupied, get them!");
                        }
                    }
                    break;
                case 18:
                    player.getInterfaceManager().close();
                    break;
            }
            return true;
        }

    }

    /**
     * Represents the competition judge.
     *
     * @author afaroutdude
     */
    public final class CompetitionJudge extends DialoguePlugin {

        /**
         * Constructs a new {@code CompetitionJudge} {@code Object}.
         *
         * @param player the player.
         */
        public CompetitionJudge(final Player player) {
            super(player);
        }

        /**
         * Constructs a new {@code CompetitionJudge} {@code Object}.
         */
        public CompetitionJudge() {
            /**
             * empty.
             */
        }

        @Override
        public DialoguePlugin newInstance(Player player) {
            return new CompetitionJudge(player);
        }

        @Override
        public boolean open(Object... args) {
            if (player.getInventory().getAmount(1464) >= 1000
                    && !player.getAchievementDiaryManager().hasCompletedTask(DiaryType.SEERS_VILLAGE, 1, 7)) {
                npc("Wow! I see that you've got yourself a whole load of ", "archery tickets. Well done!");
                player.getAchievementDiaryManager().finishTask(player, DiaryType.SEERS_VILLAGE, 1, 7);
                stage = -1;
            } else if (player.getArcheryTargets() > 0) {
                npc("Hello again, do you need reminding of the rules?");
                stage = 20;
            } else if (player.getArcheryTotal() == 0) {
                npc("Hello there, would you like to take part in the",
                        "archery competition? It only costs 200 coins to",
                        "enter.");
                stage = 0;
            } else {
                int reward = player.getArcheryTotal() / 10;
                npc("Well done. Your score is: " + player.getArcheryTotal() + ".",
                        "For that score you will receive " + reward + " Archery tickets.");
                player.setArcheryTargets(-1);
                player.setArcheryTotal(0);
                if (!player.getInventory().add(new Item(1464, reward))) {
                    player.getBank().add(new Item(1464, reward));
                    player.sendMessage("Your reward was sent to your bank.");
                }
                stage = 999;
            }
            return true;
        }

        @Override
        public boolean handle(int interfaceId, int buttonId) {
            switch (stage) {
                case 999:
                    end();
                    break;
                case -1:
                    if (player.getArcheryTargets() > 0) {
                        npc("Hello again, do you need reminding of the rules?");
                        stage = 20;
                    } else if (player.getArcheryTotal() == 0) {
                        npc("Hello there, would you like to take part in the",
                                "archery competition? It only costs 200 coins to",
                                "enter.");
                        stage = 0;
                    } else {
                        int reward = player.getArcheryTotal() / 10;
                        npc("Well done. Your score is: " + player.getArcheryTotal() + ".",
                                "For that score you will receive " + reward + " Archery tickets.");
                        if (!player.getInventory().add(new Item(1464, reward))) {
                            player.getBank().add(new Item(1464, reward));
                            player.sendMessage("Your reward was sent to your bank.");
                        }
                        stage = 999;
                    }
                    break;
                case 0:
                    options("Sure, I'll give it a go.", "What are the rules?", "No thanks.");
                    stage++;
                    break;
                case 1:
                    switch (buttonId) {
                        case 1:
                            player("Sure, I'll give it a go.");
                            stage = 2;
                            break;
                        case 2:
                            player("What are the rules?");
                            stage = 5;
                            break;
                        case 3:
                            player("No thanks.");
                            stage = 999;
                            break;
                    }
                    break;
                case 2:
                    npc("Great! That will be 200 coins then please.");
                    stage++;
                    break;
                case 3:
                    if (player.getInventory().getAmount(995) < 200) {
                        player("Oops, I don't have enough coins on me...");
                        stage++;
                    } else {
                        end();
                        player.getPacketDispatch().sendMessage("You pay the judge and he gives you 10 bronze arrows.");
                        player.getInventory().remove(new Item(995, 200));
                        player.getInventory().add(new Item(882, 10));
                        player.setArcheryTargets(10);
                        player.setArcheryTotal(0);
                    }
                    break;
                case 4:
                    npc("Never mind, come back when you've got enough.");
                    stage = 999;
                    break;
                case 5:
                case 22:
                    npc("The rules are very simple:");
                    stage++;
                    break;
                case 6:
                case 23:
                    npc("You're given 10 shots at the targets, for each hit", "you will receive points. At the end you'll be", "rewarded 1 ticket for every 10 points.");
                    stage++;
                    break;
                case 7:
                    npc("The tickets can be exchanged for goods from our stores.", "Do you want to give it a go? Only 200 coins.");
                    stage++;
                    break;
                case 8:
                    options("Sure, I'll give it a go.", "No thanks.");
                    stage++;
                    break;
                case 9:
                    switch (buttonId) {
                        case 1:
                            player("Sure, I'll give it a go.");
                            stage = 2;
                            break;
                        case 3:
                            player("No thanks.");
                            stage = 999;
                            break;
                    }
                    break;
                case 20:
                    int arrows = player.getInventory().getAmount(ItemNames.BRONZE_ARROW)
                            + player.getEquipment().getAmount(ItemNames.BRONZE_ARROW);
                    if (arrows < 1) {
                        player("Well, I actually don't have any more arrows. Could I", "get some more?");
                        stage = 25;
                    } else {
                        options("Yes please.", "No thanks, I've got it.", "How am I doing so far?");
                        stage++;
                    }
                    break;
                case 21:
                    switch (buttonId) {
                        case 1:
                            player("Yes please.");
                            stage++;
                            break;
                        case 2:
                            player("No thanks, I've got it.");
                            stage = 30;
                            break;
                        case 3:
                            player("How am I doing so far?");
                            stage = 40;
                            break;
                    }
                    break;
                case 24:
                    npc("The tickets can be exchanged for goods from our stores.", "Good Luck!");
                    stage = 999;
                    break;
                case 25:
                    npc("Ok, but it'll cost you 100 coins.");
                    stage++;
                    break;
                case 26:
                    options("Sure, I'll take some.", "No thanks.");
                    stage++;
                    break;
                case 27:
                    switch (buttonId) {
                        case 1:
                            player("Sure, I'll take some.");
                            stage++;
                            break;
                        case 2:
                            player("No thanks.");
                            stage = 999;
                            break;
                    }
                    break;
                case 28:
                    if (player.getInventory().getAmount(995) < 100) {
                        player("Oops, I don't have enough coins on me...");
                        stage++;
                    } else {
                        end();
                        player.getPacketDispatch().sendMessage("You pay the judge and he gives you 10 bronze arrows.");
                        player.getInventory().remove(new Item(995, 100));
                        player.getInventory().add(new Item(882, 10));
                    }
                    break;
                case 30:
                    npc("Glad to hear it, good luck!");
                    stage = 999;
                    break;
                case 40:
                    String msg;
                    if (player.getArcheryTotal() <= 0) {
                        msg = "You haven't started yet.";
                    } else if (player.getArcheryTotal() <= 80) {
                        msg = "Not bad, keep going.";
                    } else {
                        msg = "You're pretty good, keep it up.";
                    }
                    npc("So far your score is: " + player.getArcheryTotal(), msg);
                    stage = 999;
                    break;
            }
            return true;
        }

        @Override
        public int[] getIds() {
            return new int[]{693};
        }

    }

    /**
     * Represents the tower advisors.
     *
     * @author afaroutdude
     */
    public final class TowerAdvisor extends DialoguePlugin {

        /**
         * Constructs a new {@code TowerAdvisor} {@code Object}.
         *
         * @param player the player.
         */
        public TowerAdvisor(final Player player) {
            super(player);
        }

        /**
         * Constructs a new {@code TowerAdvisor} {@code Object}.
         */
        public TowerAdvisor() {
        }

        @Override
        public DialoguePlugin newInstance(Player player) {
            return new TowerAdvisor(player);
        }

        @Override
        public boolean open(Object... args) {
            player("Hello there, what do you do here?");
            stage = 0;
            return true;
        }

        @Override
        public boolean handle(int interfaceId, int buttonId) {
            switch (stage) {
                case 999:
                    end();
                case 0:
                    npc("Hi. We are in charge of this practice area.");
                    stage++;
                    break;
                case 1:
                    player("This is a practice area?");
                    break;
                case 2:
                    npc("Surrounding us are four towers. Each tower contains",
                            "trained archers of a different level. You'll notice",
                            "it's quite a distance, so you'll need a longbow.");
                    stage++;
                    break;
                case 3:
                    int rangeLevel = player.getSkills().getLevel(Skills.RANGE);
                    if (rangeLevel < 50) { // north
                        npc("As you're not very skilled, I advise you to practice",
                                "on the north tower. That'll provide the best",
                                "challenge for you.");
                    } else if (rangeLevel < 60) { // east
                        npc("You appear to be somewhat skilled with a bow, so I",
                                "advise you to practice on the south tower. That'll",
                                "provide the best challenge for you.");
                    } else if (rangeLevel < 70) { // south
                        npc("You appear to be fairly skilled with a bow, so I",
                                "advise you to practice on the south tower. That'll",
                                "provide the best challenge for you.");
                    } else { // west
                        npc("Looks like you're very skilled, so I advise you to",
                                "practice on the west tower. That'll provide the best",
                                "challenge for you.");
                    }
                    stage = 999;
                    break;
            }
            return true;
        }

        @Override
        public int[] getIds() {
            return new int[]{684, 685, 686, 687};
        }

    }


    /**
     * Represents all the tower archers
     *
     * @author afaroutdude
     */
    public final class TowerArcher extends AbstractNPC {

        public TowerArcher() {
            super(-1, null);
        }
        public TowerArcher(int id, Location location) {
            super(id, location);
        }

        @Override
        public void finalizeDeath(Entity killer) {
            super.finalizeDeath(killer);
            if (killer instanceof Player) {
                Player player = (Player) killer;
                if (!player.getAchievementDiaryManager().hasCompletedTask(DiaryType.SEERS_VILLAGE, 1, 6)) {
                    player.setAttribute("/save:diary:seers:tower-archers",
                            player.getAttribute("diary:seers:tower-archers", 0)
                            | 1 << getId() - 688);
                    if (player.getAttribute("diary:seers:tower-archers", 0) == 15) {
                        player.getAchievementDiaryManager().finishTask(player, DiaryType.SEERS_VILLAGE, 1, 6);
                    }
                }
            }
        }

        @Override
        public AbstractNPC construct(int id, Location location, Object... objects) {
            return new TowerArcher(id, location);
        }

        @Override
        public int[] getIds() {
            return new int[]{688, 689, 690, 691};
        }
    }

    /**
     * Represents the archery target pulse.
     *
     * @author Jamix77
     */
    public static final class ArcheryCompetitionPulse extends Pulse {

        /**
         * Represents the player instance.
         */
        private final Player player;

        /**
         * Represents the game object.
         */
        private final GameObject object;

        /**
         * Constructs a new {@code ArcheryCompetitionPulse} {@code Object}.
         *
         * @param player the player.
         * @param object the object.
         */
        public ArcheryCompetitionPulse(final Player player, final GameObject object) {
            super(1, player, object);
            this.player = player;
            this.object = object;
        }

        private void showInterface(int points, int arrowsLeft, int target, String msg) {
            player.getInterfaceManager().openComponent(325);
            player.getConfigManager().set(156, 11 - arrowsLeft);
            player.getConfigManager().set(157, points);
            player.getConfigManager().set(158, target);
            player.getPacketDispatch().sendString(msg, 325, 32);
        }

        @Override
        public boolean pulse() {
            if (player.getArcheryTargets() <= 0) {
                return true;
            }
            if (getDelay() == 1) {
                setDelay(player.getProperties().getAttackSpeed());
            }
            if (player.getEquipment().remove(new Item(ItemNames.BRONZE_ARROW, 1))) {
                Projectile p = Ammunition.get(ItemNames.BRONZE_ARROW).getProjectile().transform(player, object.getLocation());
                p.setEndLocation(object.getLocation());
                p.setEndHeight(25);
                p.send();
                player.animate(new Animation(426));
                player.lock(getDelay());

                int level = player.getSkills().getLevel(Skills.RANGE);
                int bonus = player.getProperties().getBonuses()[14];
                double prayer = 1.0;
                prayer += player.getPrayer().getSkillBonus(Skills.RANGE);
                double cumulativeStr = Math.floor(level * prayer);
                /*if (player.getProperties().getAttackStyle().getStyle() == WeaponInterface.STYLE_RANGE_ACCURATE) {
                    cumulativeStr += 3;
                } else if (player.getProperties().getAttackStyle().getStyle() == WeaponInterface.STYLE_LONG_RANGE) {
                    cumulativeStr += 1;
                }*/
                cumulativeStr *= 1.0;
                int hit = (int) ((14 + cumulativeStr + (bonus / 8) + ((cumulativeStr * bonus) * 0.016865)) * 1.0) / 10 + 1;
                hit = hit + RandomFunction.randomSign(RandomFunction.random(3));

                int target = Math.max(0, 13 - hit);
                int points = 0;
                String msg = "";
                switch (target) {
                    case 0:
                        points = 100;
                        msg = "Bulls-Eye!";
                        break;
                    case 1:
                        points = 50;
                        msg = "Hit Yellow!";
                        break;
                    case 2:
                    case 3:
                    case 4:
                        points = 30;
                        msg = "Hit Red!";
                        break;
                    case 5:
                    case 6:
                    case 7:
                    case 8:
                        points = 20;
                        msg = "Hit Blue!";
                        break;
                    case 9:
                    case 10:
                        points = 10;
                        msg = "Hit Black!";
                        break;
                    case 11:
                    case 12:
                    case 13:
                        points = 0;
                        msg = "Missed!";
                        break;
                }
                int xp = points / 2;

                player.getSkills().addExperience(Skills.RANGE, xp, true);
                player.setArcheryTotal(player.getArcheryTotal() + points);
                player.setArcheryTargets(player.getArcheryTargets() - 1);
                player.debug("Hit: " + hit);
                player.debug("You have " + player.getArcheryTargets() + " targets left.");
                player.debug("You have " + player.getArcheryTotal() + " score.");

                showInterface(player.getArcheryTotal(), player.getArcheryTargets(), target, msg);

                return true; //player.getArcheryTargets() <= 0;
            } else {
                return true;
            }
        }

    }

}
