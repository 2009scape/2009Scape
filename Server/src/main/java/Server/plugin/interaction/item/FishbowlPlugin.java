package plugin.interaction.item;

import core.Util;
import core.cache.def.impl.ItemDefinition;
import core.cache.def.impl.ObjectDefinition;
import core.game.content.ItemNames;
import core.game.interaction.NodeUsageEvent;
import core.game.interaction.OptionHandler;
import core.game.interaction.UseWithHandler;
import core.game.node.Node;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.diary.DiaryType;
import core.game.node.item.Item;
import core.game.node.item.WeightedChanceItem;
import core.game.world.update.flag.context.Animation;
import core.plugin.InitializablePlugin;
import core.plugin.Plugin;
import core.plugin.PluginManager;
import core.tools.RandomFunction;
import plugin.dialogue.DialogueInterpreter;
import plugin.dialogue.DialoguePlugin;
import plugin.skill.Skills;

// Sources:
// https://www.youtube.com/watch?v=xu7Err9YUgw
// https://www.youtube.com/watch?v=7XSVFKnUM9Y&t=683s
// https://2007rshelp.com/misc.php?id=3#7
// https://oldschoolrunescape.fandom.com/wiki/Fishbowl_(pet)

@InitializablePlugin
public class FishbowlPlugin extends OptionHandler {
    private final static int FISHBOWL_EMPTY = ItemNames.EMPTY_FISHBOWL_6667;
    private final static int FISHBOWL_WATER = ItemNames.FISHBOWL;
    private final static int FISHBOWL_SEAWEED = ItemNames.FISHBOWL_6669;

    private final static int FISHBOWL_BLUE = ItemNames.FISHBOWL_6670;
    private final static int FISHBOWL_GREEN = ItemNames.FISHBOWL_6671;
    private final static int FISHBOWL_SPINE = ItemNames.FISHBOWL_6672;

    private final static int TINY_NET = ItemNames.TINY_NET_6674;

    private final static Animation ANIM_TALK = new Animation(2782);
    private final static Animation ANIM_PLAY = new Animation(2780);
    private final static Animation ANIM_FEED = new Animation(2781);


    @Override
    public Plugin<Object> newInstance(Object arg) throws Throwable {
        ItemDefinition.forId(FISHBOWL_WATER).getHandlers().put("option:empty", this);
        ItemDefinition.forId(FISHBOWL_SEAWEED).getHandlers().put("option:empty", this);
        for (int id : new int[] {FISHBOWL_BLUE, FISHBOWL_GREEN, FISHBOWL_SPINE}) {
            ItemDefinition def = ItemDefinition.forId(id);
            def.getHandlers().put("option:talk-at", this);
            def.getHandlers().put("option:play-with", this);
            def.getHandlers().put("option:feed", this);
            def.getHandlers().put("option:drop", this);
        }
        PluginManager.definePlugin(new FishbowlDialogue());
        PluginManager.definePlugin(new FeedPetFishHandler());
        new AquariumPlugin().newInstance(arg);
        return this;
    }

    @Override
    public boolean handle(Player player, Node node, String option) {
        if (node instanceof Item) {
            Item item = node.asItem();
            switch (item.getId()) {
                case FISHBOWL_WATER:
                case FISHBOWL_SEAWEED:
                    if (player.getInventory().remove(item)) {
                        player.lock(2);
                        player.getInventory().add(new Item(FISHBOWL_EMPTY));
                        player.getPacketDispatch().sendMessage("You empty the contents of the fishbowl onto the ground.");
                    }
                    break;
                case FISHBOWL_BLUE:
                case FISHBOWL_GREEN:
                case FISHBOWL_SPINE:
                    switch (option) {
                        case "talk-at":
                            player.lock(ANIM_TALK.getDuration());
                            player.animate(ANIM_TALK);
                            return player.getDialogueInterpreter().open("fishbowl-options", option);
                        case "play-with":
                            player.lock(ANIM_PLAY.getDuration());
                            player.animate(ANIM_PLAY);
                            return player.getDialogueInterpreter().open("fishbowl-options", option);
                        case "feed":
                            return player.getDialogueInterpreter().open("fishbowl-options", option);
                        case "drop":
                            return player.getDialogueInterpreter().open("fishbowl-options", option, item);
                    }
                    break;
            }
        }

        return true;
    }

    private final class FeedPetFishHandler extends UseWithHandler {
        public FeedPetFishHandler() {
            super(ItemNames.FISH_FOOD_272);
        }

        @Override
        public Plugin<Object> newInstance(Object arg) throws Throwable {
            addHandler(FISHBOWL_BLUE, ITEM_TYPE, this);
            addHandler(FISHBOWL_GREEN, ITEM_TYPE, this);
            addHandler(FISHBOWL_SPINE, ITEM_TYPE, this);
            return this;
        }

        @Override
        public boolean handle(NodeUsageEvent event) {
            return event.getPlayer().getDialogueInterpreter().open("fishbowl-options", "feed");
        }
    }

    public final class FishbowlDialogue extends DialoguePlugin {
        private Item fishbowl;
        private String option;

        public FishbowlDialogue() {}

        public FishbowlDialogue(Player player) {
            super(player);
        }

        @Override
        public DialoguePlugin newInstance(Player player) {
            return new FishbowlDialogue(player);
        }

        @Override
        public boolean open(Object... args) {
            for (Object arg : args) {
                if (arg instanceof Item) {
                    this.fishbowl = (Item) arg;
                } else if (arg instanceof String) {
                    this.option = (String) arg;
                }
            }
            switch (option) {
                case "talk-at":
                    player("Good fish. Just keep swimming... swimming... swimming...");
                    stage = 1;
                    break;
                case "play-with":
                    player("Jump! 'Cmon " + (player.isMale() ? "girl": "boy") + ", jump!");
                    stage = 2;
                    break;
                case "feed":
                    if (player.getInventory().containsOneItem(ItemNames.FISH_FOOD_272) && player.getInventory().remove(new Item(ItemNames.FISH_FOOD_272))) {
                        player.getInventory().add(new Item(ItemNames.AN_EMPTY_BOX_6675));
                        player.lock(ANIM_FEED.getDuration());
                        player.animate(ANIM_FEED);
                        player.getPacketDispatch().sendMessage("You feed your fish.");
                    } else if (player.getInventory().containsOneItem(ItemNames.POISONED_FISH_FOOD_274)) {
                        player.getPacketDispatch().sendMessage("You can't poison your own pet!");
                    } else {
                        player.getPacketDispatch().sendMessage("You don't have any fish food.");
                    }
                    break;
                case "drop":
                    sendDialogue("If you drop your fishbowl it will break!");
                    stage = 4;
                    break;
            }
            return true;
        }

        @Override
        public boolean handle(int interfaceId, int buttonId) {
            switch (stage) {
                case 999:
                    end();
                    break;
                case 1:
                    sendDialogue("The fish swims. It is clearly an obedient fish.");
                    stage = 999;
                    break;
                case 2:
                    sendDialogue("The fish bumps into the side of the fishbowl. Then it swims some", "more.");
                    stage++;
                    break;
                case 3:
                    player("Good fish...");
                    stage = 999;
                    break;
                case 4:
                    options("Drop it regardless", "Keep hold");
                    stage++;
                    break;
                case 5:
                    switch(buttonId) {
                        case 1:
                            sendDialogue("The fishbowl shatters on the ground.");
                            player.getInventory().remove(fishbowl);
                            stage = 999;
                            break;
                        case 2:
                            sendDialogue("You keep a hold of it for now.");
                            stage = 999;
                            break;
                    }
                    break;
            }
            return true;
        }

        @Override
        public int[] getIds() {
            return new int[] { DialogueInterpreter.getDialogueKey("fishbowl-options") };
        }
    }


    public static final class AquariumPlugin extends OptionHandler {
        @Override
        public Plugin<Object> newInstance(Object arg) throws Throwable {
            ObjectDefinition.forId(10091).getHandlers().put("option:fish-in", this);
            PluginManager.definePlugin(new TinyNetHandler());
            return this;
        }

        @Override
        public boolean handle(Player player, Node node, String option) {
            return getFish(player);
        }

        public boolean getFish(final Player player) {
            if (!player.getInventory().containsOneItem(TINY_NET)) {
                player.getPacketDispatch().sendMessage("You see some tiny fish swimming around... but how to catch them?");
                return true;
            }
            else if (player.getInventory().remove(new Item(FISHBOWL_SEAWEED))) {
                player.getPacketDispatch().sendMessage("You wave the net around...");
                player.getSkills().addExperience(Skills.FISHING, 1., true);
                int level = player.getSkills().getLevel(Skills.FISHING);
                int blueChance = (int) Math.round(Util.clamp(-0.6667 * (double) level + 106., 40, 60));
                int greenChance = (int) Math.round(Util.clamp(0.2941 * (double) level + 19.7059, 20, 40));
                int spineChance = (int) Math.round(Util.clamp(0.6667 * (double) level - 46., 0, 20));

                WeightedChanceItem[] fishChance = new WeightedChanceItem[] {
                        new WeightedChanceItem(FISHBOWL_BLUE, 1, blueChance),
                        new WeightedChanceItem(FISHBOWL_GREEN, 1, greenChance),
                        new WeightedChanceItem(FISHBOWL_SPINE, 1, spineChance)
                };

                Item fish = RandomFunction.rollWeightedChanceTable(fishChance);
                player.getInventory().add(fish);
                String msg = "[ REPORT BUG ON DISCORD ]";
                switch(fish.getId()) {
                    case FISHBOWL_BLUE:
                        msg = "Bluefish";
                        break;
                    case FISHBOWL_GREEN:
                        msg = "Greenfish";
                        break;
                    case FISHBOWL_SPINE:
                        msg = "Spinefish";
                        break;
                }
                player.getPacketDispatch().sendMessage("...and you catch a Tiny " + msg + "!");
                player.getAchievementDiaryManager().finishTask(player, DiaryType.SEERS_VILLAGE, 1, 10);
                return true;
            }
            else {
                player.getPacketDispatch().sendMessage("You need something to put your catch in!");
                return true;
            }
        }

        private final class TinyNetHandler extends UseWithHandler {
            public TinyNetHandler() {
                super(TINY_NET);
            }

            @Override
            public Plugin<Object> newInstance(Object arg) throws Throwable {
                addHandler(10091, OBJECT_TYPE, this);
                return this;
            }

            @Override
            public boolean handle(NodeUsageEvent event) {
                return getFish(event.getPlayer());
            }
        }
    }
}
