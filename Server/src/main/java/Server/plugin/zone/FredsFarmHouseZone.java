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
public class FredsFarmHouseZone extends MapZone implements Plugin<Object> {

    public FredsFarmHouseZone() {
        super("freds-farm-house", true);
    }

    @Override
    public void configure() {
        register(new ZoneBorders(3188, 3275, 3192, 3270));
    }

    @Override
    public boolean enter(Entity entity) {
        // Visit Fred the Farmer's chicken and sheep farm
        if (entity.isPlayer()) {
            Player player = entity.asPlayer();
            player.getAchievementDiaryManager().finishTask(player, DiaryType.LUMBRIDGE, 0, 19);
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
