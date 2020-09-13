package core.game.node.entity.player.link.quest;

import core.game.node.item.Item;
import plugin.skill.Skills;

public class QuestReward {
    /**
     * Reward type
     */
    public enum QuestRewardType {
        ITEM,
        SKILL,
        TEXT,
    }

    /**
     * Reward type
     */
    public final QuestRewardType type;

    /**
     * Item to reward
     */
    Item item;

    /**
     * Whether to show the amount before the item name
     */
    boolean showAmount;

    /** {@link plugin.skill.Skills} to add experience to
     */
    int skill;

    /**
     * Amount of experience to reward
     */
    int experience;

    /**
     * Reward text. Overrides default QuestReward text
     */
    String rewardText;

    /**
     * Quest reward item
     *
     * @param item The {@link Item}
     */
    public QuestReward(Item item) {
        this.type = QuestRewardType.ITEM;
        this.item = item;
        this.showAmount = true;
    }

    /**
     * Quest reward item
     *
     * @param item The {@link Item}
     * @param rewardText The reward text
     */
    public QuestReward(Item item, String rewardText) {
        this.type = QuestRewardType.ITEM;
        this.item = item;
        this.showAmount = true;
        this.rewardText = rewardText;
    }

    /**
     * Quest reward item
     *
     * @param item The {@link Item}
     * @param showAmount Whether to show the amount before the item name
     */
    public QuestReward(Item item, boolean showAmount) {
        this.type = QuestRewardType.ITEM;
        this.item = item;
        this.showAmount = showAmount;
    }

    /**
     * Quest reward skill
     *
     * @param skill {@link plugin.skill.Skills} to add experience to
     * @param experience Amount of experience to grant
     */
    public QuestReward(int skill, int experience) {
        this.type = QuestRewardType.SKILL;
        this.skill = skill;
        this.experience = experience;
    }

    /**
     * Quest reward skill
     *
     * @param skill {@link plugin.skill.Skills} to add experience to
     * @param experience Amount of experience to grant
     * @param rewardText The reward text
     */
    public QuestReward(int skill, int experience, String rewardText) {
        this.type = QuestRewardType.SKILL;
        this.skill = skill;
        this.experience = experience;
        this.rewardText = rewardText;
    }

    /**
     * Just text
     *
     * @param rewardText The reward text
     */
    public QuestReward(String rewardText) {
        this.type = QuestRewardType.TEXT;
        this.rewardText = rewardText;
    }

    /**
     * Commas seem to only be used once the number hits 5 digits
     *
     * @param number Number
     * @return Formatted string
     */
    public String formatNumber(int number) {
        if (number >= 10000) {
            return String.format("%,d", number);
        }
        return Integer.toString(number);
    }

    /**
     * Converts the quest reward into text that can be written to the quest component
     *
     * @return String
     */
    public String toString() {
        if (rewardText != null) {
            return rewardText;
        }
        switch (type) {
            case ITEM:
                if (showAmount) {
                    return formatNumber(item.getAmount()) + " " + item.getName();
                } else {
                    return item.getName();
                }
            case SKILL:
                return formatNumber(experience) + " " + Skills.SKILL_NAME[skill] + " XP";
            default:
                return "";
        }
    }
}
