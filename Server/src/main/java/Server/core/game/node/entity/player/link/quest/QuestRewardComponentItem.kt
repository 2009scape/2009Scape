package core.game.node.entity.player.link.quest

class QuestRewardComponentItem {

    /**
     * [core.game.node.item.Item] id
     */
    @JvmField
    var itemId: Int

    /**
     * Item amount property
     */
    @JvmField
    var itemAmount: Int

    /**
     * Requirement type
     */
    @JvmField
    var zoom: Int

    /**
     *
     * @param itemId The [core.game.node.item.Item] id
     * @param zoom The amount to zoom. Doesn't seem to affect anything
     */
    constructor(itemId: Int, zoom: Int) {
        this.itemId = itemId
        this.itemAmount = 1
        this.zoom = zoom
    }

    /**
     *
     * @param itemId The [core.game.node.item.Item] id
     * @param itemAmount The item's amount
     * @param zoom The amount to zoom. Doesn't seem to affect anything
     */
    constructor(itemId: Int, itemAmount: Int, zoom: Int) {
        this.itemId = itemId
        this.itemAmount = itemAmount
        this.zoom = zoom
    }
}
