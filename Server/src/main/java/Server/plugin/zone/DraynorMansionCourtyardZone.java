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
public class DraynorMansionCourtyardZone extends MapZone implements Plugin<Object> {

    public DraynorMansionCourtyardZone() {
        super("draynor-mansion-courtyard", true);
    }

    @Override
    public void configure() {
        register(new ZoneBorders(3100, 3333, 3114, 3346));
    }

    @Override
    public boolean enter(Entity entity) {
        // Enter the courtyard of the spooky mansion in Draynor Village
        if (entity.isPlayer()) {
            Player player = entity.asPlayer();
            player.getAchievementDiaryManager().finishTask(player, DiaryType.LUMBRIDGE, 0, 8);
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
