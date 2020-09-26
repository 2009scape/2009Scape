package core.game.node.entity.player.link.quest

import core.game.node.item.Item
import core.tools.StringUtils
import plugin.skill.Skills

class QuestReward {
    /**
     * Reward type
     */
    enum class QuestRewardType {
        ITEM, SKILL, TEXT
    }

    /**
     * Reward type
     */
    val type: QuestRewardType

    /**
     * Item to reward
     */
    var item: Item? = null

    /**
     * Whether to show the amount before the item name
     */
    var showAmount: Boolean = true

    /**
     * [plugin.skill.Skills] to add experience to
     */
    var skill: Int = 0

    /**
     * Amount of experience to reward
     */
    var experience: Int = 0

    /**
     * Reward text. Overrides default QuestReward text
     */
    var rewardText: String? = null

    /**
     * Quest reward item
     *
     * @param item The [Item]
     */
    constructor(item: Item) {
        this.type = QuestRewardType.ITEM
        this.item = item
        this.showAmount = true
    }

    /**
     * Quest reward item
     *
     * @param item The [Item]
     * @param rewardText The reward text
     */
    constructor(item: Item, rewardText: String?) {
        this.type = QuestRewardType.ITEM
        this.item = item
        this.showAmount = true
        this.rewardText = rewardText
    }

    /**
     * Quest reward item
     *
     * @param item The [Item]
     * @param showAmount Whether to show the amount before the item name
     */
    constructor(item: Item?, showAmount: Boolean) {
        this.type = QuestRewardType.ITEM
        this.item = item
        this.showAmount = showAmount
    }

    /**
     * Quest reward skill
     *
     * @param skill [Skills] to add experience to
     * @param experience Amount of experience to grant
     */
    constructor(skill: Int, experience: Int) {
        this.type = QuestRewardType.SKILL
        this.skill = skill
        this.experience = experience
    }

    /**
     * Quest reward skill
     *
     * @param skill [Skills] to add experience to
     * @param experience Amount of experience to grant
     * @param rewardText The reward text
     */
    constructor(skill: Int, experience: Int, rewardText: String?) {
        this.type = QuestRewardType.SKILL
        this.skill = skill
        this.experience = experience
        this.rewardText = rewardText
    }

    /**
     * Just text
     *
     * @param rewardText The reward text
     */
    constructor(rewardText: String?) {
        this.type = QuestRewardType.TEXT
        this.rewardText = rewardText
    }

    /**
     * Commas seem to only be used once the number hits 5 digits
     *
     * @param number Number
     * @return Formatted string
     */
    fun formatNumber(number: Int): String {
        return if (number >= 10000) {
            String.format("%,d", number)
        } else number.toString()
    }

    /**
     * Converts the quest reward into text that can be written to the quest component
     *
     * @return String
     */
    override fun toString(): String {
        return rewardText
            ?: when (type) {
                QuestRewardType.ITEM -> if (showAmount) {
                    formatNumber(item!!.amount) + " " + StringUtils.plusS(item!!.name)
                } else {
                    item!!.name
                }
                QuestRewardType.SKILL -> formatNumber(experience) + " " + Skills.SKILL_NAME[skill] + " XP"
                else -> ""
            }
    }
}