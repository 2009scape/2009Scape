package plugin.skill.construction

import core.game.system.SystemLogger.log

/**
 * Represents a hotspot.
 * @author Emperor
 */
class Hotspot (val hotspot: BuildHotspot,val chunkX: Int,val chunkY: Int,var chunkX2: Int,var chunkY2: Int) {

    constructor(hotspot: BuildHotspot, chunkX: Int, chunkY: Int) : this(hotspot,chunkX,chunkY,-1,-1)

    /**
     * The current chunk x coordinate.
     */
    var currentX: Int
    /**
     * Gets the currentY value.
     * @return The currentY.
     */
    /**
     * Sets the currentY value.
     * @param currentY The currentY to set.
     */
    /**
     * The chunk y coordinate.
     */
    var currentY: Int
    /**
     * Gets the decorationIndex.
     * @return The decorationIndex.
     */
    /**
     * Sets the decorationIndex.
     * @param decorationIndex The decorationIndex to set.
     */
    /**
     * The current decoration index.
     */
    var decorationIndex = -1

    /**
     * Copies the hotspot.
     * @return The hotspot.
     */
    fun copy(): Hotspot {
        return Hotspot(hotspot, chunkX, chunkY, chunkX2, chunkY2)
    }

    /**
     * Constructs a new `Hotspot` `Object`.
     * @param hotspot The hotspot.
     * @param chunkX The chunk x-coordinate.
     * @param chunkY The chunk y-coordinate.
     */
    init {
        currentX = chunkX
        currentY = chunkY
    }
}