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

    //Chat heads from oldschool runescape?
    //Maybe for gnomes or dwarves? Chat heads are frozen when used on Human NPCs
    OSRS_HAPPY(588),
    OSRS_NORMAL(594),
    OSRS_SNEAKY(595),
    OSRS_SAD(596),
    OSRS_LAUGH1(605),
    OSRS_LAUGH2(606),
    OSRS_LAUGH3(607),
    OSRS_LAUGH4(608), //TODO: More

    //Chatheads from 2009?
    NOD_YES(9741),
    WORRIED(9743),
    HALF_WORRIED(9745), //Not on the wiki, first half of worried
    AMAZED(9746),
    EXTREMELY_SHOCKED(9750),
    GUILTY(9758),
    HALF_GUILTY(9760), //Not on the wiki, first half of guilty
    SAD(9761),
    CRYING(9765),
    HALF_CRYING(9768), //Not on the wiki, but plays the first half of the crying animation
    AFRAID(9772),
    SCARED(9776),
    PANICKED(9780),
    ANNOYED(9784),
    ANGRY(9785),
    FURIOUS(9792),
    ANGRY_WITH_SMILE(9796), // Not on the wiki
    ANGRY_WITH_SMILE_AND_EVIL_EYE(9798), //Not on the Wiki
    SLEEPING(9802),
    SILENT(9804),
    NEUTRAL(9808),
    THINKING(9812),
    HALF_THINKING(9814),
    DISGUSTED(9816),
    DISGUSTED_HEAD_SHAKE(9823), //Not on the wiki
    ASKING(9827),
    HALF_ASKING(9830), //Not on wiki, first half of Asking
    ROLLING_EYES(9831), //9832, 9833 are the same
    HALF_ROLLING_EYES(9834), //Not on Wiki, first half of Rolling eyes
    DRUNK(9835),
    SUSPICIOUS(9836),
    LAUGH(9840),
    LOUDLY_LAUGHING(9841),
    EVIL_LAUGH(9842),
    FRIENDLY(9844),
    HAPPY(9847),
    JOLLY(9851),
    STRUGGLE(9865), //TODO: More?
    //9855-9857 are like disgusted? does it just repeat after this?

    //Child Chathead?
    CHILD_EVIL_LAUGH(7171),
    CHILD_FRIENDLY(7172),
    CHILD_NORMAL(7173),
    CHILD_NEUTRAL(7174),
    CHILD_LOUDLY_LAUGHING(7175),
    CHILD_THINKING(7176),
    CHILD_SAD(7177),
    CHILD_GUILTY(7178),
    CHILD_SUSPICIOUS(7179); //TODO: More?



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