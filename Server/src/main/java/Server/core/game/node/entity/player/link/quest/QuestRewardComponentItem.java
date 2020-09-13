package core.game.node.entity.player.link.quest;

public class QuestRewardComponentItem {
    int itemId;
    int itemAmount;
    int zoom;

    /**
     *
     * @param itemId The {@link core.game.node.item.Item} id
     * @param zoom The amount to zoom. Doesn't seem to affect anything
     */
    public QuestRewardComponentItem(int itemId, int zoom) {
        this.itemId = itemId;
        this.itemAmount = 1;
        this.zoom = zoom;
    }

    /**
     *
     * @param itemId The {@link core.game.node.item.Item} id
     * @param itemAmount The item's amount
     * @param zoom The amount to zoom. Doesn't seem to affect anything
     */
    public QuestRewardComponentItem(int itemId, int itemAmount, int zoom) {
        this.itemId = itemId;
        this.itemAmount = itemAmount;
        this.zoom = zoom;
    }
}
