package plugin.interaction.item;

import java.util.concurrent.TimeUnit;

import core.Util;
import core.cache.def.impl.ItemDefinition;
import core.game.node.entity.player.link.emote.Emotes;
import core.game.world.update.flag.context.Animation;
import core.game.world.update.flag.context.Graphics;
import plugin.skill.Skills;
import core.game.interaction.OptionHandler;
import core.game.node.Node;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.TeleportManager.TeleportType;
import core.game.node.item.Item;
import core.game.world.map.Location;
import core.plugin.InitializablePlugin;
import core.plugin.Plugin;

/**
 * Handles the explorers ring.
 *
 * @author Vexia
 */
@InitializablePlugin
public class ExplorersRingPlugin extends OptionHandler {

    /**
     * The cabbage port location.
     */
    private static final Location CABBAGE_PORT = Location.create(3051, 3291, 0);

    @Override
    public Plugin<Object> newInstance(Object arg) throws Throwable {
        ItemDefinition.forId(13560).getHandlers().put("option:run-replenish", this);
        ItemDefinition.forId(13561).getHandlers().put("option:run-replenish", this);
        ItemDefinition.forId(13562).getHandlers().put("option:run-replenish", this);

        ItemDefinition.forId(13561).getHandlers().put("option:low-alchemy", this);
        ItemDefinition.forId(13562).getHandlers().put("option:low-alchemy", this);

        ItemDefinition.forId(13562).getHandlers().put("option:cabbage-port", this);
        return this;
    }

    @Override
    public boolean handle(Player player, Node node, String option) {
        final Item item = node.asItem();
        int level = getLevel(item.getId());

        switch (option) {
            case "run-replenish":
                if (player.getSavedData().getGlobalData().getRunReplenishDelay() < System.currentTimeMillis()) {
                    player.getSavedData().getGlobalData().setRunReplenishCharges(0);
                    player.getSavedData().getGlobalData().setRunReplenishDelay(Util.nextMidnight(System.currentTimeMillis()));
                }
                int charges = player.getSavedData().getGlobalData().getRunReplenishCharges();
                if (charges >= level) {
                    player.sendMessage("You have used all the charges you can for one day.");
                    return true;
                }
                player.getSettings().updateRunEnergy(-50);
                player.getSavedData().getGlobalData().setRunReplenishCharges(charges + 1);
                player.sendMessage("You feel refreshed as the ring revitalises you and a charge is used up.");
                player.visualize(new Animation(9988), new Graphics(1733));
                break;
            case "low-alchemy":
                if (player.getSkills().getStaticLevel(Skills.MAGIC) < 21) {
                    player.sendMessage("You need a Magic level of 21 in order to do that.");
                    break;
                }
                if (player.getSavedData().getGlobalData().getLowAlchemyDelay() < System.currentTimeMillis()) {
                    player.getSavedData().getGlobalData().setLowAlchemyCharges(0);
                    player.getSavedData().getGlobalData().setLowAlchemyDelay(Util.nextMidnight(System.currentTimeMillis()));
                }
                if (player.getSavedData().getGlobalData().getLowAlchemyCharges() <= 0
						&& player.getSavedData().getGlobalData().getLowAlchemyDelay() > System.currentTimeMillis()) {
                    player.sendMessage("You have used all the charges you can for one day.");
                    return true;
                }
                player.sendMessage("You grant yourself with 30 free low alchemy charges."); // todo this implementation is not correct, see https://www.youtube.com/watch?v=UbUIF2Kw_Dw
                player.getSavedData().getGlobalData().setLowAlchemyCharges(30);
                break;
            case "cabbage-port":
                player.getTeleporter().send(CABBAGE_PORT, TeleportType.CABBAGE);
                break;
        }
        return true;
    }

    /**
     * Gets the level of the ring.
     *
     * @param itemId The item id.
     * @return The level.
     */
    private int getLevel(int itemId) {
        switch (itemId) {
            case 13560:
                return 1;
            case 13561:
                return 2;
            case 13562:
                return 3;
        }
        return -1;
    }

}
