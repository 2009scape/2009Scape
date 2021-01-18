package plugin.skill.thieving;

import core.cache.def.impl.ObjectDefinition;
import core.game.content.global.action.DoorActionHandler;
import plugin.skill.Skills;
import core.game.interaction.OptionHandler;
import core.game.node.Node;
import core.game.node.entity.Entity;
import core.game.node.entity.combat.ImpactHandler;
import core.game.node.entity.player.Player;
import core.game.node.item.Item;
import core.game.node.object.GameObject;
import core.game.world.map.Direction;
import core.game.world.map.Location;
import core.plugin.InitializablePlugin;
import core.plugin.Plugin;
import core.tools.RandomFunction;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a pickable door.
 * @author Vexia
 */
@InitializablePlugin
public class PickableDoorHandler extends OptionHandler {
    /**
     * The lock pick item.
     */
    private static final Item LOCK_PICK = new Item(1523);

    private static final List<PickableDoor> pickableDoors = new ArrayList<>();

    private static final int[] DOORS = new int[]{42028, 2550, 2551, 2554, 2555, 2556, 2557, 2558, 2559, 5501, 7246, 9565, 13314, 13317, 13320, 13323, 13326, 13344, 13345, 13346, 13347, 13348, 13349, 15759, 34005, 34805, 34806, 34812};

    PickableDoor door;

    @Override
    public Plugin<Object> newInstance(Object arg) throws Throwable {
        for (int i : DOORS) {
            ObjectDefinition.forId(i).getHandlers().put("option:pick-lock", this);
            ObjectDefinition.forId(i).getHandlers().put("option:open", this);
        }
        pickableDoors.add(new PickableDoor(new Location[]{Location.create(2672, 3308, 0)}, 1, 3.8));
        pickableDoors.add(new PickableDoor(new Location[]{Location.create(2672, 3301, 0)}, 14, 15));
        pickableDoors.add(new PickableDoor(new Location[]{Location.create(2610, 3316, 0)}, 15, 15));
        pickableDoors.add(new PickableDoor(new Location[]{Location.create(3190, 3957, 0)}, 32, 25, true));
        pickableDoors.add(new PickableDoor(new Location[]{Location.create(2565, 3356, 0)}, 46, 37.5));
        pickableDoors.add(new PickableDoor(new Location[]{Location.create(2579, 3286, 1)}, 61, 50));
        pickableDoors.add(new PickableDoor(new Location[]{Location.create(3018, 3187, 0)}, 1, 0.0));
        pickableDoors.add(new PickableDoor(new Location[]{Location.create(2601, 9482, 0)}, 82, 0.0, true));
        return this;
    }

    @Override
    public boolean handle(Player player, Node node, String option) {
        door = forDoor(node.getLocation());
        if (option.equals("open")) {
            if (door == null) {
                player.getPacketDispatch().sendMessage("The door is locked.");
                return true;
            }
            door.open(player, (GameObject) node);
            return true;
        }
        if (option.equals("pick-lock")) {
            door.pickLock(player, (GameObject) node);
            return true;
        }
        System.out.println("Unhandled door: " + node.getId());
        return false;
    }

    @Override
    public Location getDestination(Node node, Node n) {
        if (n instanceof GameObject) {
            GameObject object = (GameObject) n;
            if (object.getDefinition().hasAction("pick-lock")) {
                return DoorActionHandler.getDestination((Entity) node, object);
            }
        }
        return null;
    }

    private PickableDoor forDoor(Location location) {
        for (PickableDoor door : pickableDoors) {
            for (Location l : door.getLocations()) {
                if (l.equals(location)) {
                    return door;
                }
            }
        }
        return null;
    }

    public class PickableDoor{
        /**
         * The locations of the door.
         */
        private final Location[] locations;

        /**
         * The level.
         */
        private final int level;

        /**
         * The experience required.
         */
        private final double experience;

        /**
         * If it requires a lockpick.
         */
        private final boolean lockpick;

        /**
         * Constructs a new {@code PickableDoor} {@code Object}.
         * @param locations the locations.
         * @param level the level.
         * @param experience the experience.
         * @param lockpick the lock pick.
         */
        public PickableDoor(final Location[] locations, int level, double experience, boolean lockpick) {
            this.locations = locations;
            this.level = level;
            this.experience = experience;
            this.lockpick = lockpick;
        }

        /**
         * Constructs a new {@code PickableDoor} {@code Object}.
         * @param level the level.
         * @param experience the experience.
         */
        public PickableDoor(Location[] locations, int level, double experience) {
            this(locations, level, experience, false);
        }

        /**
         * Gets the location.
         * @return The location.
         */
        public Location[] getLocations() {
            return locations;
        }

        /**
         * Opens a pickable door.
         * @param player the player.
         * @param object the object.
         */
        public void open(Player player, GameObject object) {
            if (isInside(player, object)) {
                DoorActionHandler.handleAutowalkDoor(player, object);
                player.getPacketDispatch().sendMessage("You go through the door.");
            } else {
                player.getPacketDispatch().sendMessage("The door is locked.");
            }
        }

        /**
         * Picks a lock on a door.
         * @param player the player.
         * @param object the object.
         */
        public void pickLock(Player player, GameObject object) {
            boolean success = RandomFunction.random(12) >= 4;
            if (isInside(player, object)) {
                player.getPacketDispatch().sendMessage("The door is already unlocked.");
                return;
            }
            if (player.getSkills().getLevel(Skills.THIEVING) < level) {
                player.sendMessage("You attempt to pick the lock.");
                boolean hit = RandomFunction.random(10) < 5;
                player.getImpactHandler().manualHit(player, RandomFunction.random(1, 3), ImpactHandler.HitsplatType.NORMAL);
                player.sendMessage(hit ? "You have activated a trap on the lock." : "You fail to pick the lock.");
                return;
            }
            if (lockpick && !player.getInventory().containsItem(LOCK_PICK)) {
                player.sendMessage("You need a lockpick in order to pick this lock.");
                return;
            }
            if (success) {
                player.getSkills().addExperience(Skills.THIEVING, experience, true);
                DoorActionHandler.handleAutowalkDoor(player, object);
            }
            player.getPacketDispatch().sendMessage("You attempt to pick the lock.");
            player.getPacketDispatch().sendMessage("You " + (success ? "manage" : "fail") + " to pick the lock.");
        }

        /**
         * Checks if we're behind the door/inside the building.
         * @param player the player.
         * @param object the object.
         * @return {@code True} if so.
         */
        private boolean isInside(Player player, GameObject object) {
            boolean inside = false;
            Direction dir = Direction.getLogicalDirection(player.getLocation(), object.getLocation());
            Direction direction = object.getDirection();
            if (direction == Direction.SOUTH && dir == Direction.WEST) {
                inside = true;
            } else if (direction == Direction.EAST && dir == Direction.SOUTH) {
                inside = true;
            } else if (direction == Direction.NORTH && dir == Direction.EAST) {
                inside = true;
            }
            return inside;
        }

        /**
         * Gets the level.
         * @return The level.
         */
        public int getLevel() {
            return level;
        }

        /**
         * Gets the experience.
         * @return The experience.
         */
        public double getExperience() {
            return experience;
        }

        /**
         * Gets the lockpick.
         * @return The lockpick.
         */
        public boolean isLockpick() {
            return lockpick;
        }
    }

}

