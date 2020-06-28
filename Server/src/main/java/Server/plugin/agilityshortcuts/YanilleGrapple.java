package plugin.agilityshortcuts;

import core.cache.def.impl.ObjectDefinition;
import core.game.component.Component;
import core.game.interaction.OptionHandler;
import core.game.node.Node;
import core.game.node.entity.player.Player;
import core.game.node.item.Item;
import core.game.system.task.Pulse;
import core.game.world.GameWorld;
import core.game.world.map.Location;
import core.net.packet.PacketRepository;
import core.net.packet.context.MinimapStateContext;
import core.net.packet.out.MinimapState;
import core.plugin.InitializablePlugin;
import core.plugin.Plugin;
import plugin.skill.Skills;

import java.util.HashMap;
import java.util.Map;

@InitializablePlugin
public class YanilleGrapple extends OptionHandler {
    private static final HashMap<Integer,Integer> REQUIREMENTS = new HashMap<>();

    static {
        REQUIREMENTS.putIfAbsent(Skills.AGILITY,39);
        REQUIREMENTS.putIfAbsent(Skills.RANGE,21);
        REQUIREMENTS.putIfAbsent(Skills.STRENGTH,38);
    }

    private static final Item MITH_GRAPPLE = new Item(9419);
    private static final Item MITH_CBOW = new Item(9181);

    @Override
    public boolean handle(Player player, Node node, String option) {
        Location destination;
        Location current = player.getLocation();
        if(current.getY() < 3073){
            destination = Location.create(2556, 3075, 0);
        } else {
            destination = Location.create(2556, 3072, 0);
        }

        for(Map.Entry<Integer,Integer> e : REQUIREMENTS.entrySet()){
            if(player.getSkills().getLevel(e.getKey()) < e.getValue()){
                player.sendMessage("You need at least 39 Agility, 21 Ranged, and 38 Strength to use this shortcut.");
                return true;
            }
        }

        if (!player.getEquipment().containsItem(MITH_CBOW) || !player.getEquipment().containsItem(MITH_GRAPPLE)) {
            player.getDialogueInterpreter().sendDialogue("You need a Mithril crossbow and a Mithril grapple in order to do this.");
            return true;
        }

        player.lock();
        GameWorld.Pulser.submit(new Pulse(1, player) {
            int counter = 1;

            @Override
            public boolean pulse() {
                switch (counter++) {
                    case 2:
                        player.faceLocation(destination);
                        break;
                    case 3:
                        player.getInterfaceManager().openOverlay(new Component(115));
                        break;
                    case 4:
                        PacketRepository.send(MinimapState.class, new MinimapStateContext(player, 2));
                        player.getInterfaceManager().hideTabs(0, 1, 2, 3, 4, 5, 6, 11, 12);
                        break;
                    case 7:
                        player.getProperties().setTeleportLocation(destination);
                        break;
                    case 8:
                        player.getInterfaceManager().restoreTabs();
                        PacketRepository.send(MinimapState.class, new MinimapStateContext(player, 0));
                        player.getInterfaceManager().closeOverlay();
                        player.getInterfaceManager().close();
                        player.unlock();
                        return true;
                }
                return false;
            }
        });

        return true;
    }

    @Override
    public Plugin<Object> newInstance(Object arg) throws Throwable {
        ObjectDefinition.forId(17047).getConfigurations().put("option:grapple",this);
        return this;
    }
}
