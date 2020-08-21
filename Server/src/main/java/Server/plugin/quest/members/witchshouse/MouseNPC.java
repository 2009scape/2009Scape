package plugin.quest.members.witchshouse;

import core.game.node.entity.npc.AbstractNPC;
import core.game.node.entity.player.Player;
import core.game.world.GameWorld;
import core.game.world.map.Location;

/**
 * Created for 2009Scape
 * User: Ethan Kyle Millard
 * Date: March 15, 2020
 * Time: 10:56 AM
 */
public class MouseNPC extends AbstractNPC {

    /**
     * The player.
     */
    private Player player;

    /**
     * The end time of the mouse being spawned.
     */
    private int endTime;

    /**
     * Represents the NPC ids of NPCs using this plugin.
     */
    private static final int[] ID = {901};

    /**
     * Constructs a new {@code DSNedNPC} {@code Object}.
     */
    public MouseNPC() {
        super(0, null);
    }

    /**
     * Constructs a new {@code DSNedNPC} {@code Object}.
     *
     * @param id       The NPC id.
     * @param location The location.
     */
    private MouseNPC(int id, Location location) {
        super(id, location);
        this.endTime = (int) (GameWorld.getTicks() + (4 / 0.6));
    }

    @Override
    public AbstractNPC construct(int id, Location location, Object... objects) {
        return new MouseNPC(id, location);
    }

    @Override
    public void handleTickActions() {
        super.handleTickActions();
        if (player.getAttribute("mouse_out") == null) {
            clear();
        }
        if (GameWorld.getTicks() > endTime) {
            clear();
        }
        if (!player.isActive() || player.getLocation().getDistance(getLocation()) > 8) {
            clear();
        }

    }

    @Override
    public void clear() {
        super.clear();
        player.removeAttribute("mouse_out");
    }

    @Override
    public int[] getIds() {
        return ID;
    }

    /**
     * Sets the player.
     * @param player the player.
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * Gets the player.
     * @return The player.
     */
    public Player getPlayer() {
        return player;
    }


}
