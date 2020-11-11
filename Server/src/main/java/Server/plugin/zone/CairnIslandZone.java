package plugin.zone;

import core.game.node.entity.Entity;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.diary.DiaryType;
import core.game.world.map.zone.MapZone;
import core.game.world.map.zone.ZoneBorders;
import core.game.world.map.zone.ZoneBuilder;
import core.plugin.InitializablePlugin;
import core.plugin.Plugin;

@InitializablePlugin
public class CairnIslandZone extends MapZone implements Plugin<Object> {

    public CairnIslandZone() {
        super("cairn-island", true);
    }

    @Override
    public void configure() {
        register(new ZoneBorders(2752, 2963, 2774, 2992));
    }

    @Override
    public boolean enter(Entity entity) {
        // Explore Cairn Island to the west of Karamja
        if (entity.isPlayer()) {
            Player player = entity.asPlayer();
            player.getAchievementDiaryManager().finishTask(player, DiaryType.KARAMJA, 0, 5);
        }
        return super.enter(entity);
    }

    @Override
    public Plugin<Object> newInstance(Object arg) throws Throwable {
        ZoneBuilder.configure(this);
        return this;
    }

    @Override
    public Object fireEvent(String identifier, Object... args) {
        return null;
    }
}
