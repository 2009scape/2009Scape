package plugin.quest.members.asoulsbane;

import core.cache.def.impl.ObjectDefinition;
import core.game.content.ItemNames;
import core.game.interaction.MovementPulse;
import core.game.interaction.NodeUsageEvent;
import core.game.interaction.OptionHandler;
import core.game.interaction.UseWithHandler;
import core.game.node.Node;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.diary.DiaryType;
import core.game.node.entity.player.link.quest.Quest;
import core.game.node.item.Item;
import core.game.node.object.GameObject;
import core.game.system.task.Pulse;
import core.game.world.GameWorld;
import core.game.world.map.Location;
import core.game.world.update.flag.context.Animation;
import core.plugin.Plugin;
import core.plugin.PluginManager;

/**
 * Author: afaroutdude
 */

public class ASoulsBanePlugin extends OptionHandler {
    private static final int[] RIFT_IDS = {13967,13968,13969,13970,13971,13972,13973,13974,13975,13976,13977,13978,13979,13980,13981,13982,13983,13984,13985,13986,13987,13988,13989,13990,13991,13992,13993};
    private static final int[] OBJ_EXIT = {13878, 13901, 13904, 13932};
    private static final int WEAPONS_RACK = 13993;
    private static final int FINAL_EXIT = 13933;
    private static final int OBJ_CONFIG = 710;
    //private static final Animation CLIMB_DOWN = new Animation();
    private static final Location ROPE_STAND_LOC = Location.create(3309, 3452, 0);
    private static final Location RIFT_RAGE = Location.create(3015, 5244, 0);

    @Override
    public Plugin<Object> newInstance(Object arg) throws Throwable {
        PluginManager.definePlugins(new SoulsBaneRiftRopeHandler());
        for (int obj : RIFT_IDS) {
            ObjectDefinition.forId(obj).getHandlers().put("option:enter", this);
        }

        for (int obj : OBJ_EXIT) {
            ObjectDefinition.forId(obj).getHandlers().put("option:use", this);
        }
        return this;
    }

    @Override
    public boolean handle(Player player, Node node, String option) {
        final int id = node instanceof Item ? ((Item) node).getId() : ((GameObject) node).getId();
        GameObject object = node instanceof GameObject ? ((GameObject) node) : null;
        Location dest = null;
        Quest quest = player.getQuestRepository().getQuest(ASoulsBane.NAME);

        boolean isRift = false;
        for (int obj : RIFT_IDS) {
            if (id == obj) {
                isRift = true;
                break;
            }
        }

        if (isRift) {
            switch (quest.getStage(player)) {
                case 0:
                case 5:
                    player.getPacketDispatch().sendMessage("You can't just jump down there.");
                    break;
                case 10:
                    player.getPulseManager().run(new MovementPulse(player, ROPE_STAND_LOC) {
                        @Override
                        public boolean pulse() {
                            return false;
                        }

                        // todo this is janky, what's the better way to do this?  Make sure you run to rope before descending
                        @Override
                        public void stop() {
                            player.lock();
                            GameWorld.getPulser().submit(new Pulse(1, player) {
                                int counter = 1;

                                @Override
                                public boolean pulse() {
                                    switch (counter++) {
                                        case 1:
                                            player.animate(new Animation(3838));
                                            break;
                                        case 3:
                                            // todo there should be a fade to black here
                                            break;
                                        case 6:
                                            player.teleport(RIFT_RAGE);
                                            player.getAnimator().reset();
                                            player.getAchievementDiaryManager().finishTask(player, DiaryType.VARROCK, 1, 9);
                                            player.unlock();
                                            return true;
                                    }
                                    return false;
                                }
                            });
                        }
                    }, "movement");
            }
            return true;
        }

        boolean isEarlyExit = false;
        for (int obj : OBJ_EXIT) {
            if (id == obj) {
                isEarlyExit = true;
                break;
            }
        }

        if (isEarlyExit) {
            player.teleport(ROPE_STAND_LOC);
        }

        return false;
    }

    public class SoulsBaneRiftRopeHandler extends UseWithHandler {
        public SoulsBaneRiftRopeHandler() {
            super(ItemNames.ROPE_954);
        }

        @Override
        public Location getDestination(Player player, Node node) {
            return ROPE_STAND_LOC;
        }

        @Override
        public boolean handle(NodeUsageEvent event) {
            final Player player = event.getPlayer();
            Item usedItem = event.getUsedItem();
            final Quest quest = player.getQuestRepository().getQuest(ASoulsBane.NAME);
            final GameObject object = event.getUsedWith().asObject();

            if (quest.getStage(player) == 5 && usedItem.getId() == ItemNames.ROPE_954) {
                player.getConfigManager().set(OBJ_CONFIG, player.getConfigManager().get(OBJ_CONFIG) | 1<<11, true);
                player.getInventory().remove(usedItem);
                quest.setStage(player, 10);
                return true;
            }

            return false;
        }

        @Override
        public Plugin<Object> newInstance(Object arg) throws Throwable {
            for (int id : RIFT_IDS) {
                addHandler(id, OBJECT_TYPE, this);
            }
            return this;
        }
    }
}
