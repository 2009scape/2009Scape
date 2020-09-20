package core.game.node.entity.player.link.quest

import core.game.node.entity.player.Player
import plugin.skill.Skills

class QuestRequirement {
    /**
     * Requirement type
     */
    enum class QuestRequirementType {
        SKILL, QUEST, QUEST_POINT, TEXT
    }

    /**
     * Requirement type
     */
    var type: QuestRequirementType

    /**
     * The [Skills] required
     */
    var skill: Int = 0

    /**
     * The [Skills] level required
     */
    var level: Int = 0

    /**
     * Quest required
     */
    var quest: Quest? = null

    /**
     * Quest points required
     */
    var questPoints: Int = 0

    /**
     * Text to prepend to [text] when the player does not meet the requirement
     */
    var textPrependIfFail: String? = null

    /**
     * Text to prepend to [text] when the player meets the requirement
     */
    var textPrependIfPass: String? = null

    /**
     * Requirement text. Overrides default QuestRequirement text.
     * May be prepended with `textIfFail` or `textIfPass`
     */
    var text: String? = null

    /**
     * Skill requirement for quest
     *
     * @param skill The [Skills]
     * @param level Level required
     */
    constructor(skill: Int, level: Int) {
        this.type = QuestRequirementType.SKILL
        this.skill = skill
        this.level = level
    }

    /**
     * Skill requirement for quest
     *
     * @param skill The [Skills]
     * @param level Level required
     */
    constructor(skill: Int, level: Int, text: String) {
        this.type = QuestRequirementType.SKILL
        this.skill = skill
        this.level = level
        this.text = text
    }

    /**
     * Quest completion requirement for quest
     *
     * @param quest The [Quest]
     */
    constructor(quest: Quest) {
        this.type = QuestRequirementType.QUEST
        this.quest = quest
    }

    /**
     * Quest completion requirement for quest
     *
     * @param quest The [Quest]
     * @param text The text
     */
    constructor(quest: Quest, text: String) {
        this.type = QuestRequirementType.QUEST
        this.quest = quest
        this.text = text
    }

    /**
     * Skill requirement for quest
     *
     * @param questPoints Quest Points required
     */
    constructor(questPoints: Int) {
        this.type = QuestRequirementType.QUEST_POINT
        this.questPoints = questPoints
    }

    /**
     * Skill requirement for quest
     *
     * @param questPoints Quest Points required
     * @param textPrependIfPass Text to prepend to [text] if the player meets the requirement
     * @param textPrependIfFail Text to prepend to [text] if the player does not meet the requirement
     * @param text text
     */
    constructor(questPoints: Int, textPrependIfPass: String, textPrependIfFail: String, text: String) {
        this.type = QuestRequirementType.QUEST_POINT
        this.questPoints = questPoints
        this.textPrependIfPass = textPrependIfPass
        this.textPrependIfFail = textPrependIfFail
        this.text = text
    }

    /**
     * Text
     *
     * @param text The text
     */
    constructor(text: String) {
        this.type = QuestRequirementType.TEXT
        this.text = text
    }

    /**
     * Checks if the player meets this requirement
     *
     * @param player The player
     */
    fun check(player: Player): Boolean {
        return when (type) {
            QuestRequirementType.SKILL -> player.skills.getStaticLevel(skill) >= level
            QuestRequirementType.QUEST -> player.questRepository.getStage(quest?.name) >= 100
            QuestRequirementType.QUEST_POINT -> player.questRepository.points >= questPoints
            QuestRequirementType.TEXT -> true
        }
    }

    /**
     * Converts the quest requirement into text that can be written to the quest component
     *
     * @return String
     */
    fun toJournalString(player: Player): String {
        val meetsRequirement = check(player)
        if (this.text != null) {
            return Quest.processString(
                (if (textPrependIfPass != null && textPrependIfFail != null) if (meetsRequirement) textPrependIfPass else textPrependIfFail else "") + text,
                if (type == QuestRequirementType.TEXT) false else meetsRequirement
            )
        }
        return when (this.type) {
            QuestRequirementType.SKILL -> Quest.processString(
                (if (meetsRequirement) "" else "<red>") + String.format("Level %d %s", level, Skills.SKILL_NAME[skill]),
                meetsRequirement
            )
            QuestRequirementType.QUEST_POINT -> "$questPoints Quest Points"
            QuestRequirementType.QUEST -> quest?.name.toString()
            QuestRequirementType.TEXT -> text.toString()
        }
    }
}
