package plugin.interaction.item.withobject;

import core.game.interaction.NodeUsageEvent;
import core.game.interaction.UseWithHandler;
import core.game.node.entity.player.Player;
import core.game.node.item.Item;
import core.game.system.task.Pulse;
import core.game.world.update.flag.context.Animation;
import core.plugin.InitializablePlugin;
import core.plugin.Plugin;

@InitializablePlugin
public final class WaterSkinHandler extends UseWithHandler {

    /**
     * Represents objects with water in them.
     */
    private static final int[] OBJECTS = new int[]{16302, 6827, 11661, 24160, 34577, 15936, 15937, 15938, 23920, 35469, 24265, 153, 879, 880, 2864, 6232, 10436, 10437, 10827, 11007, 11759, 21764, 22973, 24161, 24214, 24265, 28662, 30223, 30820, 34579, 36781, 873, 874, 4063, 6151, 8699, 9143, 9684, 10175, 12279, 12974, 13563, 13564, 14868, 14917, 15678, 16704, 16705, 20358, 22715, 24112, 24314, 25729, 25929, 26966, 29105, 33458, 34082, 34411, 34496, 34547, 34566, 35762, 36971, 37154, 37155, 878, 884, 3264, 3305, 3359, 4004, 4005, 6097, 6249, 6549, 8747, 8927, 11793, 12201, 12897, 24166, 26945, 31359, 32023, 32024, 34576, 35671, 13561, 13563, 13559};

    /**
     * Represents the animation to use.
     */
    private static final Animation ANIMATION = new Animation(832);

    // Unfilled waterskin objects
    private static final int[] WATERSKINS = new int[]{1831, 1829, 1827, 1825};

    private static final int FILLED_WATERSKIN = 1823;

    // Waterskin fill diag
    private static final String WATERSKIN_TEXT = "You fill the waterskin.";

    /**
     * Constructs a new {@code FillBucketPlugin} {@code Object}.
     */
    public WaterSkinHandler() {
        super(WATERSKINS);
    }

    @Override
    public Plugin<Object> newInstance(Object arg) throws Throwable {
        for (int i : OBJECTS) {
            addHandler(i, OBJECT_TYPE, this);
        }
        return this;
    }

    @Override
    public boolean handle(NodeUsageEvent event) {
        Player player = event.getPlayer();
        player.getPulseManager().run(new Pulse(2, player) {
            @Override
            public boolean pulse() {
                int curSkin = -1;
                for (int i = 0; i < WATERSKINS.length; i++) {
                    if (player.getInventory().contains(WATERSKINS[i], 1)) {
                        curSkin = WATERSKINS[i];
                        break;
                    }
                }
                if (curSkin == -1) {
                    return true;
                }
                if (player.getInventory().remove(new Item(curSkin))) {
                    player.animate(ANIMATION);
                    player.getPacketDispatch().sendMessage(WATERSKIN_TEXT);
                    player.getInventory().add(new Item(FILLED_WATERSKIN));
                }
                return false;
            }

            @Override
            public void stop() {
                super.stop();
            }
        });
        return true;
    }
}