package org.crandor.game.content.dialogue;

/**
 * Represents the facial expressions (the animations the entity does when
 * talking).
 *
 * @author Emperor
 * @author Empathy
 */
public enum FacialExpression {

    /**
     * The normal talking expression.
     */

    //Names based off https://runescape.wiki/w/Chathead/Animations
    //Animation names recorded 2020-01-21

    //Chat heads from oldschool runescape?
    //Maybe for gnomes? Chat heads are frozen when used on Human NPCs
    OSRS_HAPPY(588),
    OSRS_NORMAL(594),
    OSRS_SNEAKY(595),
    OSRS_SAD(596),
    OSRS_LAUGH1(605),
    OSRS_LAUGH2(606),
    OSRS_LAUGH3(607),
    OSRS_LAUGH4(608), //TODO: More

    //Chatheads from 2009?
    NO_EXPRESSION(9760),
    SAD_TWO(9768), //This one did not have a name on the wiki
    AFRAID(9772),
    SCARED(9776),
    PANICKED(9780),
    ANNOYED(9784),
    FURIOUS(9792),
    ANGRY(9796),
    SILENT(9804),
    NEUTRAL(9808),
    THINKING(9812),
    DISGUSTED(9816),
    DISGUSTED_TWO(9820), //Same as DISGUSTED?
    ASKING(9827),
    ASKING_TWO(9828),//Same as ASKING?
    ROLLING_EYES(9831),
    ROLLING_EYES_TWO(9832),//Same as ROLLING_EYES?
    DRUNK(9835),
    SUSPICIOUS_TWO(9836),
    LAUGH(9840),
    FRIENDLY(9844),
    JOLLY(9851),

    //Child Chathead?
    CHILD_EVIL_LAUGH(7171),
    CHILD_FRIENDLY(7172),
    CHILD_NORMAL(7173),
    CHILD_NEUTRAL(7174),
    CHILD_LOUDLY_LAUGHING(7175),
    CHILD_THINKING(7176),
    CHILD_SAD(7177),
    CHILD_GUILTY(7178),
    CHILD_SUSPICIOUS(7179);



    /**
     * The animation id.
     */
    private final int animationId;

    /**
     * Constructs a new {@code FacialExpression} {@code Object}.
     *
     * @param animationId The animation id.
     */
    FacialExpression(int animationId) {
        this.animationId = animationId;
    }

    /**
     * Gets the animation id.
     *
     * @return The animation id.
     */
    public int getAnimationId() {
        return animationId;
    }
}