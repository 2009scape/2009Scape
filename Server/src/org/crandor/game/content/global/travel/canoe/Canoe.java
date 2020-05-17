package org.crandor.game.content.global.travel.canoe;

/**
 * Represents a canoe to craft.
 * @author 'Vexia
 * @date 09/11/2013
 */
public enum Canoe {
    LOG(12, 30, 30),
    DUGOUT(27, 60, 31),
    STABLE_DUGOUT(42, 90, 32),
    WAKA(57, 150, 33);

    /**
     * Constructs a new {@code Canoe.java} {@code Object}.
     * @param level the woodcutting level requirement.
     * @param experience the experience.
     * @param child the child.
     */
    Canoe(final int level, final double experience, final int child) {
        this.level = level;
        this.experience = experience;
        this.child = child;
    }

    /**
     * Represents the woodcutting level requirement to craft the canoe.
     */
    private final int level;

    /**
     * Represents the experience received for crafting the canoe.
     */
    private final double experience;

    /**
     * Represents the child's id on the interface.
     */
    private final int child;

    /**
     * Gets the woodcutting level requirement.
     * @return The required level.
     */
    public int getRequiredLevel() {
        return level;
    }

    /**
     * Gets the experience received.
     * @return The experience amount.
     */
    public double getExperience() {
        return experience;
    }

    /**
     * Gets the child.
     * @return The child.
     */
    public int getChild() {
        return child;
    }

    /**
     * Method used to get the canoe from the child.
     * @param child the child.
     * @return <code>True</code> if so
     */
    public static Canoe getCanoeFromChild(final int child) {
        for (Canoe canoe : values()) {
            if(canoe.getChild() == child)
            {
                return canoe;
            }
        }
        return null;
    }
}