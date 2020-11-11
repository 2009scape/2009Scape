package plugin.npc.other;

import core.game.node.entity.Entity;
import core.game.node.entity.npc.AbstractNPC;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.diary.DiaryType;
import core.game.node.item.GroundItemManager;
import core.game.node.item.Item;
import core.plugin.InitializablePlugin;
import core.game.world.map.Location;

/**
 * Represents a rat npc.
 * @author afaroutdude
 */
@InitializablePlugin
public class LumbridgeSwampGiantRatNPC extends AbstractNPC {

    /**
     * The NPC ids of NPCs using this plugin.
     */
    private static final int[] ID = { 86 };

    /**
     * Constructs a new {@code RatNPC} {@code Object}.
     */
    public LumbridgeSwampGiantRatNPC() {
        super(0, null);
    }

    /**
     * Constructs a new {@code RatNPC} {@code Object}.
     * @param id the id.
     * @param location the location.
     */
    private LumbridgeSwampGiantRatNPC(int id, Location location) {
        super(id, location, true);
    }

    @Override
    public AbstractNPC construct(int id, Location location, Object... objects) {
        return new LumbridgeSwampGiantRatNPC(id, location);
    }

    @Override
    public void finalizeDeath(final Entity killer) {
        super.finalizeDeath(killer);

        // Kill a giant rat in Lumbridge Swamp
        if (killer instanceof Player && (killer.getViewport().getRegion().getId() == 12593 || killer.getViewport().getRegion().getId() == 12849)) {
            killer.asPlayer().getAchievementDiaryManager().finishTask(killer.asPlayer(), DiaryType.LUMBRIDGE, 1, 7);
        }
    }

    @Override
    public int[] getIds() {
        return ID;
    }

}
