package plugin.skill.agility.shortcuts;

import core.cache.def.impl.ObjectDefinition;
import core.game.component.Component;
import core.game.interaction.OptionHandler;
import core.game.node.Node;
import core.game.node.entity.impl.ForceMovement;
import core.game.node.entity.player.Player;
import core.game.node.item.Item;
import core.game.system.task.Pulse;
import core.game.world.GameWorld;
import core.game.world.map.Location;
import core.game.world.update.flag.context.Animation;
import core.game.world.update.flag.context.Graphics;
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
    private static final HashMap<Integer, Integer> REQUIREMENTS = new HashMap<>();
    private static String requirementsString;

    static {
        REQUIREMENTS.putIfAbsent(Skills.AGILITY, 39);
        REQUIREMENTS.putIfAbsent(Skills.RANGE, 21);
        REQUIREMENTS.putIfAbsent(Skills.STRENGTH, 38);

        requirementsString = "You need at least "
                + REQUIREMENTS.get(Skills.AGILITY) + " " + Skills.SKILL_NAME[Skills.AGILITY] + ", "
                + REQUIREMENTS.get(Skills.RANGE) + " " + Skills.SKILL_NAME[Skills.RANGE] + ", and "
                + REQUIREMENTS.get(Skills.STRENGTH) + " " + Skills.SKILL_NAME[Skills.STRENGTH]
                + " to use this shortcut.";
    }

    private static final Item MITH_CBOW = new Item(9181);
    private static final Item MITH_GRAPPLE = new Item(9419);

    @Override
    public Plugin<Object> newInstance(Object arg) throws Throwable {
        ObjectDefinition.forId(17047).getHandlers().put("option:grapple", this);
        ObjectDefinition.forId(17048).getHandlers().put("option:jump", this);
        return this;
    }

    @Override
    public boolean handle(Player player, Node node, String option) {
        Location destination;
        Location current = player.getLocation();

        switch (option) {
            case "jump":
                ForceMovement.run(player,
                        current,
                        current.getY() < 3074 ? Location.create(2556,3072,0) : Location.create(2556,3075,0),
                        new Animation(7268),
                        10);
                break;
            case "grapple":
                destination = current.getY() < 3073
                        ? Location.create(2556, 3073, 1)
                        : Location.create(2556, 3074, 1);

                for (Map.Entry<Integer, Integer> e : REQUIREMENTS.entrySet()) {
                    if (player.getSkills().getLevel(e.getKey()) < e.getValue()) {
                        player.getDialogueInterpreter().sendDialogue(requirementsString);
                        return true;
                    }
                }

                if (!player.getEquipment().containsItem(MITH_CBOW) || !player.getEquipment().containsItem(MITH_GRAPPLE)) {
                    player.getDialogueInterpreter().sendDialogue("You need a Mithril crossbow and a Mithril grapple in order to do this.");
                    return true;
                }

                player.lock();
                GameWorld.getPulser().submit(new Pulse(1, player) {
                    int counter = 1;
                    Component tab;

                    @Override
                    public boolean pulse() {
                        switch (counter++) {
                            case 1:
                                player.faceLocation(destination);
                                player.visualize(new Animation(4455), new Graphics(760, 100));
                                break;
                            case 8:
                                tab = player.getInterfaceManager().getSingleTab();
                                player.getInterfaceManager().openOverlay(new Component(115));
                                PacketRepository.send(MinimapState.class, new MinimapStateContext(player, 2));
                                player.getInterfaceManager().hideTabs(0, 1, 2, 3, 4, 5, 6, 11, 12);
                                break;
                            case 13:
                                player.getProperties().setTeleportLocation(destination);
                                break;
                            case 14:
                                player.getInterfaceManager().restoreTabs();
                                if (tab != null) {
                                    player.getInterfaceManager().openTab(tab);
                                }
                                PacketRepository.send(MinimapState.class, new MinimapStateContext(player, 0));
                                player.getInterfaceManager().closeOverlay();
                                player.getInterfaceManager().close();
                                player.unlock();
                                return true;
                        }
                        return false;
                    }
                });
                break;
        }
        return true;
    }
}
