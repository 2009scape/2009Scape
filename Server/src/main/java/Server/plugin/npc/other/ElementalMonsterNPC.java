package plugin.npc.other;

import core.game.node.entity.Entity;
import core.game.node.entity.npc.AbstractNPC;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.diary.DiaryType;
import core.game.world.map.Location;
import core.plugin.InitializablePlugin;
import core.tools.RandomFunction;

/**
 * Represents the Earth, Fire, Air, and Water Elemental NPCs.
 * @author afaroutdude
 */
@InitializablePlugin
public final class ElementalMonsterNPC extends AbstractNPC {

    /**
     * Constructs a new {@code DuckNPC} {@code Object}.
     */
    public ElementalMonsterNPC() {
        super(0, null);
    }

    /**
     * Constructs a new {@code DuckNPC} {@code Object}.
     * @param id the id.
     * @param location the location.
     */
    public ElementalMonsterNPC(int id, Location location) {
        super(id, location, true);
    }


    @Override
    public AbstractNPC construct(int id, Location location, Object... objects) {
        return new ElementalMonsterNPC(id, location);
    }

    @Override
    public void finalizeDeath(final Entity killer) {
        super.finalizeDeath(killer);
        if (killer instanceof Player) {
            final Player player = killer.asPlayer();
            if (this.getLocation().withinDistance(new Location(2719, 9889, 0), 100)) {
                switch (this.getId()) {
                    case 1019:
                        player.setAttribute("/save:diary:seers:elemental:fire", true);
                        break;
                    case 1020:
                        player.setAttribute("/save:diary:seers:elemental:earth", true);
                        break;
                    case 1021:
                        player.setAttribute("/save:diary:seers:elemental:air", true);
                        break;
                    case 1022:
                        player.setAttribute("/save:diary:seers:elemental:water", true);
                        break;
                }
                if (player.getAttribute("diary:seers:elemental:fire", false)
                        && player.getAttribute("diary:seers:elemental:earth", false)
                        && player.getAttribute("diary:seers:elemental:air", false)
                        && player.getAttribute("diary:seers:elemental:water", false)) {
                    player.getAchievementDiaryManager().finishTask(player, DiaryType.SEERS_VILLAGE, 1, 4);
                }
            }
        }
    }

    @Override
    public int[] getIds() {
        return new int[] { 1019, 1020, 1021, 1022 };
    }

}
