package plugin.dialogue;

import core.game.content.ItemNames;
import core.game.interaction.NodeUsageEvent;
import core.game.interaction.UseWithHandler;
import core.game.node.entity.npc.NPC;
import core.game.node.item.Item;
import core.plugin.InitializablePlugin;
import core.game.node.entity.player.Player;
import core.plugin.Plugin;
import core.plugin.PluginManager;

/**
 * Represents the jiminua dialogue plugin.
 *
 * @author 'afaroutdude
 */
@InitializablePlugin
public final class JiminuaDialogue extends DialoguePlugin {

    /**
     * Constructs a new {@code JiminuaDialogue} {@code Object}.
     */
    public JiminuaDialogue() {
        /**
         * empty.
         */
    }

    /**
     * Constructs a new {@code JiminuaDialogue} {@code Object}.
     *
     * @param player the player.
     */
    public JiminuaDialogue(Player player) {
        super(player);
    }

    @Override
    public DialoguePlugin newInstance(Player player) {
        PluginManager.definePlugin(new JiminuaUnnoteHandler());
        return new JiminuaDialogue(player);
    }

    @Override
    public boolean open(Object... args) {
        npc = (NPC) args[0];
        npc("Welcome to Jiminua's Jungle Store, Can I help you", "at all?");
        stage = 0;
        return true;
    }

    @Override
    public boolean handle(int interfaceId, int buttonId) {
        switch (stage) {
            case 999:
                end();
                break;
            case 0:
                options("Yes please. What are you selling?", "How should I use your shop?", "Can you un-note any of my items?", "No thanks.");
                stage++;
                break;
            case 1:
                switch (buttonId) {
                    case 1:
                        player("Yes please. What are you selling?");
                        stage = 10;
                        break;
                    case 2:
                        player("How should I use your shop?");
                        stage = 20;
                        break;
                    case 3:
                        player("Can you un-note any of my items?");
                        stage = 30;
                        break;
                    case 4:
                        player("No thanks.");
                        stage = 999;
                        break;
                }
                break;
            case 10:
                npc("Take a good look.");
                stage++;
                break;
            case 11:
                end();
                npc.openShop(player);
                break;
            case 20:
                npc("I'm glad you ask! You can buy as many of the items", "stocked as you wish. You can also sell most items", "to the shop.");
                stage = 999;
                break;
            case 30:
                npc("I can un-note pure essence, but nothing else. Just", "give me the notes you wish to exchange.");
                stage = 999;
                break;
        }
        return true;
    }

    @Override
    public int[] getIds() {
        return new int[]{560};
    }

    private static final class JiminuaUnnoteHandler extends UseWithHandler {
        public JiminuaUnnoteHandler() {
            super(ItemNames.PURE_ESSENCE_NOTED);
        }

        @Override
        public Plugin<Object> newInstance(Object arg) throws Throwable {
            addHandler(560, NPC_TYPE, this);
            return this;
        }

        @Override
        public boolean handle(NodeUsageEvent event) {
            Player player = event.getPlayer();
            assert (event.getUsedItem().getId() == ItemNames.PURE_ESSENCE_NOTED);
            int ess = player.getInventory().getAmount(ItemNames.PURE_ESSENCE_NOTED);
            int coins = player.getInventory().getAmount(ItemNames.COINS);
            int freeSpace = player.getInventory().freeSlots();

            if (ess == 0) {
                player.getDialogueInterpreter().sendDialogues(560, FacialExpression.HALF_GUILTY, "You don't have any essence for me to un-note.");
                return true;
            } else if (freeSpace == 0) {
                player.getDialogueInterpreter().sendDialogues(560, FacialExpression.HALF_GUILTY, "You don't have any free space.");
                return true;
            } else if (coins <= 1) {
                player.getDialogueInterpreter().sendDialogues(560, FacialExpression.HALF_GUILTY, "I charge 2 gp to un-note each pure essence.");
                return true;
            }

            int unnote = Math.min(Math.min(freeSpace, ess), coins / 2);
            player.getInventory().remove(new Item(ItemNames.PURE_ESSENCE_NOTED, unnote));
            player.getInventory().remove(new Item(ItemNames.COINS, 2 * unnote));
            player.getInventory().add(new Item(ItemNames.PURE_ESSENCE_7936, unnote));
            player.getPacketDispatch().sendMessage("You hand Jiminua some notes and coins, and she hands you back pure essence.");
            return true;
        }
    }
}
