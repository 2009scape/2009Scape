package plugin.quest.members.witchshouse;

import core.game.node.entity.player.link.quest.QuestReward;
import core.game.node.entity.player.link.quest.QuestRewardComponentItem;
import plugin.skill.Skills;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.quest.Quest;
import core.plugin.InitializablePlugin;

/**
 * Created for 2009Scape
 * User: Ethan Kyle Millard
 * Date: March 15, 2020
 * Time: 9:21 AM
 */
@InitializablePlugin
public class WitchsHouse extends Quest {

    /**
     * Constructs a new {@code WitchsHouse} {@code Object}.
     */
    public WitchsHouse() {
        super(
            "Witch's House",
            124,
            123,
            4,
            226, 0, 1, 7
        );
    }

    @Override
    public void drawJournal(Player player, int stage) {
        super.drawJournal(player, stage);
        switch (getStage(player)) {
            case 0:
                writeJournal(player,
                    "<blue>I can start this quest by speaking to the <red>little boy",
                    "<blue>standing by the long garden just <red>north of Taverley",
                    "<blue>I must be able to defeat a <red>level 53 enemy.");
                break;
            case 10:
                writeJournal(player,
                    "<str>A small boy has kicked his ball over the fence into the",
                    "<str>nearby garden, and I have agreed to retrieve it for him.",
                    "<blue>I should find a way into the <red>garden<blue> where the <red>ball<blue> is.");
                break;
            case 100:
                writeJournal(player,
                    "<str>A small boy has kicked his ball over the fence into the",
                    "<str>nearby garden, and I have agreed to retrieve it for him.",
                    "<str>After puzzling through the strangely elaborate security",
                    "<str>system, and defeating a very strange monster, I returned",
                    "<str>the child's ball to him, and he thanked me for my help.",
                    "",
                    "<col=FF0000>QUEST COMPLETE!");
                break;
        }
    }

    @Override
    public void finish(Player player) {
        super.finish(player);
        player.getInterfaceManager().closeChatbox();
    }

    @Override
    public QuestRewardComponentItem getRewardComponentItem() {
        return new QuestRewardComponentItem(2407, 240);
    }

    @Override
    public QuestReward[] getQuestRewards(Player player) {
        return new QuestReward[]{
            new QuestReward(Skills.HITPOINTS, 6325),
        };
    }

    @Override
    public Quest newInstance(Object object) {
        return this;
    }
}
