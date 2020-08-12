package plugin.consumable.effects;

import core.game.node.entity.player.Player;
import core.game.system.task.Pulse;
import core.game.world.map.Location;
import plugin.consumable.ConsumableEffect;

public class TroubleBrewingRumEffect extends ConsumableEffect {

    private static final Location TROUBLE_BREWING_MINIGAME = new Location(3813, 3022);

    private final String forceChatMessage;

    public TroubleBrewingRumEffect(final String forceChatMessage) {
        this.forceChatMessage = forceChatMessage;
    }

    @Override
    public void activate(Player p) {
        final Pulse teleportation = new Pulse(6) {
            @Override
            public boolean pulse() {
                p.teleport(TROUBLE_BREWING_MINIGAME);
                return true;
            }
        };
        final Pulse mainPulse = new Pulse(4) {
            @Override
            public boolean pulse() {
                p.sendChat(forceChatMessage);
                p.getPulseManager().run(teleportation);
                return true;
            }
        };
        p.getPulseManager().run(mainPulse);
    }
}
