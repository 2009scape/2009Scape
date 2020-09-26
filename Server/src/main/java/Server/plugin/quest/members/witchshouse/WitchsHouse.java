package plugin.quest.members.witchshouse;

import core.game.node.entity.player.link.quest.QuestReward;
import core.game.node.item.Item;
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
        int line;
        switch (getStage(player)) {
            case 0:
                writeJournal(player,
                    "I can start this quest by speaking to the <red>little boy",
                    "standing by the long garden just <red>north of Taverley",
                    "I must be able to defeat a <red>level 53 enemy.");
                break;
            case 10:
                line = writeJournal(player, true,
                    "A small boy has kicked his ball over the fence into the",
                    "nearby garden, and I have agreed to retrieve it for him.");
                writeJournal(player, line,
                    "I should find a way into the <red>garden <blue>where the <red>ball <blue>is.");
                break;
            case 100:
                line = writeJournal(player, true,
                    "A small boy has kicked his ball over the fence into the",
                    "nearby garden, and I have agreed to retrieve it for him.",
                    "After puzzling through the strangely elaborate security",
                    "system, and defeating a very strange monster, I returned",
                    "the child's ball to him, and he thanked me for my help.");
                writeJournal(player, ++line,
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
    public Item getRewardComponentItem() {
        return new Item(2407);
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
