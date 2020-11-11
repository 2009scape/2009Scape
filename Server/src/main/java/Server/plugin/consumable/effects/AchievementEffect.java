package plugin.consumable.effects;

import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.diary.DiaryType;
import plugin.consumable.ConsumableEffect;

public class AchievementEffect extends ConsumableEffect {
    private final DiaryType diary;
    private final int level;
    private final int task;
    public AchievementEffect(DiaryType diary, int level, int task) {
        this.diary = diary;
        this.level = level;
        this.task = task;
    }

    @Override
    public void activate(Player p) {
        p.getAchievementDiaryManager().finishTask(p, diary, level, task);
    }
}
