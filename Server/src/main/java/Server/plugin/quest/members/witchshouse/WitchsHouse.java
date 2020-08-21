package plugin.quest.members.witchshouse;

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
        super("Witch's House", 124, 123, 4, 226, 0, 1, 7);
    }

    @Override
    public void drawJournal(Player player, int stage) {
        super.drawJournal(player, stage);
        switch (getStage(player)) {
            case 0:
                line(player,  "<blue>I can start this quest by speaking to the <red>little boy", 4+ 7);
                line(player,  "<blue>standing by the long garden just <red>north of taverly", 5+ 7);
                line(player,  "<blue>I must be able to defeat a <red>level 53 enemy.", 6+ 7);
                break;
            case 10:
                line(player,  "<str>A small boy has kicked his ball over the fence into the", 4+ 7);
                line(player,  "<str>nearby garden, and I have agreed to retrieve it for him.", 5+ 7);
                line(player,  "<blue>I should find a way into the <red>garden<blue> where the <red>ball<blue> is.", 6+ 7);
                break;
            case 100:
                line(player,  "<str>A small boy has kicked his ball over the fence into the", 4+ 7);
                line(player,  "<str>nearby garden, and I have agreed to retrieve it for him.", 5+ 7);
                line(player,  "<str>After puzzling through the strangely elaborate security", 6+ 7);
                line(player,  "<str>system, and defeating a very strange monster, I returned",  7+ 7);
                line(player,  "<str>the child's ball to him, and he thanked me for my help.",  8+ 7);
                line(player,  "<col=FF0000>QUEST COMPLETE!",  10+ 7);
                break;
        }
    }

    @Override
    public void finish(Player player) {
        super.finish(player);
        player.getConfigManager().set(101, player.getQuestRepository().getPoints());
        player.getPacketDispatch().sendString("4 Quest Points", 277, 8 + 2);
        player.getPacketDispatch().sendString("6325 Hitpoints XP", 277, 9 + 2);
        player.getSkills().addExperience(Skills.HITPOINTS, 6325);
        player.getInterfaceManager().closeChatbox();
        player.getPacketDispatch().sendItemZoomOnInterface(2407, 240, 277, 3 + 2);
    }

    @Override
    public Quest newInstance(Object object) {
        return this;
    }
}
