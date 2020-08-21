package plugin.quest.tutorials.tutorialisland;

import core.game.node.entity.player.Player;
import core.plugin.InitializablePlugin;
import core.plugin.Plugin;
import core.plugin.PluginManifest;
import core.plugin.PluginType;

/**
 * Basic starter until tutorial is completed.
 *
 * @author Michael Sasse (https://github.com/mikeysasse/)
 */
@InitializablePlugin
@PluginManifest(type = PluginType.LOGIN)
public class BasicStarter implements Plugin<Player> {

    private static final int[][] STARTER_ITEMS = { { 1351, 1 }, { 590, 1 },
            { 303, 1 }, { 315, 1 }, { 1925, 1 }, { 1931, 1 }, { 2309, 1 },
            { 1265, 1 }, { 1205, 1 }, { 1277, 1 }, { 1171, 1 }, { 841, 1 },
            { 882, 25 }, { 556, 25 }, { 558, 15 }, { 555, 6 }, { 557, 4 },
            { 559, 2 } };

    public BasicStarter() { }

    @Override
    public Plugin<Player> newInstance(Player player) throws Throwable {
        if (player.getDetails().getLastLogin() == 0) {
            //Redundant if the Tutorial Completion Dialogue is going to clear the bank, inventory and equipment after teleporting to Lumbridge.
            //Causes a full inventory on tutorial island when the instructors are giving the player more items.
            //The Starter pack array exists in the TutorialCompletionDialogue file... So could this file be deleted?

            /*for (int[] item : STARTER_ITEMS) {
                player.getInventory().add(new Item(item[0], item[1]));
            }*/
        }
        return this;
    }

    @Override
    public Object fireEvent(String identifier, Object... args) {
        return null;
    }
}
