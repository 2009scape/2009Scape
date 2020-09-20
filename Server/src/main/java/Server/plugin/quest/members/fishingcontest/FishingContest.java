package plugin.quest.members.fishingcontest;

import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.quest.Quest;
import core.game.node.entity.player.link.quest.QuestRequirement;
import core.game.node.entity.player.link.quest.QuestReward;
import core.game.node.entity.player.link.quest.QuestRewardComponentItem;
import core.game.node.item.Item;
import core.plugin.InitializablePlugin;
import plugin.skill.Skills;

@InitializablePlugin
public class FishingContest extends Quest {
    public FishingContest(){
        super(
            "Fishing Contest",
            62,
            61,
            1,
            11, 0, 1, 5
        );
    }

    public static final Item FISHING_ROD = new Item(307);
    public static final Item FISHING_PASS = new Item(27);
    public static final Item RED_VINE_WORM = new Item(25);
    public static final Item RAW_GIANT_CARP = new Item(338);
    public static final Item GIANT_CARP = new Item(337);
    public static final Item FISHING_TROPHY = new Item(26);
    public static final Item GARLIC = new Item(1550);
    public static final Item SPADE = new Item(952);


    @Override
    public void drawJournal(Player player, int stage) {
        int line;
        super.drawJournal(player, stage);
        if (stage < 10) {
            line = writeJournal(player,
                "I can start this quest by trying to take the <red>shortcut",
                "near <red>White Wolf Mountain"
            );
            writeJournal(player, line, getQuestRequirementsJournal(player));
        } else {
            line = writeJournal(player, stage >= 20,
                "The <red>mountain Dwarves' home <blue>would be an ideal way to get across ",
                "White Wolf Mountain safely. However, the Dwarves aren't too",
                "fond of strangers. They will let you through if you can <red>bring ",
                "<red>them a trophy. <blue>The trophy is the prize for the annual Hemenster",
                "<red>fishing competition.<blue>",
                ""
            );
            if (stage == 20) {
                writeJournal(player, line,
                    "I should return to <red>Austri<blue> or <red>Vestri<blue>."
                );
            } else if (stage == 100) {
                writeJournal(player, line,
                    "<col=FF0000>QUEST COMPLETE!"
                );
            }
        }
    }

    @Override
    public void finish(Player player) {
        super.finish(player);
        player.removeAttribute("fishing_contest:garlic");
        player.removeAttribute("fishing_contest:won");
        player.removeAttribute("fishing_contest:pass-shown");
    }

    @Override
    public QuestRequirement[] getQuestRequirements(Player player) {
        return new QuestRequirement[]{
            new QuestRequirement(Skills.FISHING, 10, "I need level <red>10 Fishing<blue> to start this quest.")
        };
    }

    @Override
    public QuestRewardComponentItem getRewardComponentItem() {
        return new QuestRewardComponentItem(FISHING_TROPHY.getId(), 230);
    }

    @Override
    public QuestReward[] getQuestRewards(Player player) {
        return new QuestReward[]{
            new QuestReward(Skills.FISHING, 2437),
            new QuestReward("Access to the White Wolf Mountain shortcut."),
        };
    }

    @Override
    public Quest newInstance(Object object) {
        return this;
    }
}
