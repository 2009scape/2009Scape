package plugin.skill.agility.shortcuts;

import core.cache.def.impl.ObjectDefinition;
import core.game.component.Component;
import core.game.content.ItemNames;
import core.game.interaction.OptionHandler;
import core.game.node.Node;
import core.game.node.entity.impl.ForceMovement;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.diary.DiaryType;
import core.game.node.item.Item;
import core.game.node.object.GameObject;
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

/**
 * Represents the plugin used to handle the grappling of the falador wall.
 *
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class FaladorGrapplePlugin extends OptionHandler {
    private static final HashMap<Integer, Integer> REQUIREMENTS = new HashMap<>();
    private static String requirementsString;

    static {
        REQUIREMENTS.putIfAbsent(Skills.AGILITY, 11);
        REQUIREMENTS.putIfAbsent(Skills.RANGE, 19);
        REQUIREMENTS.putIfAbsent(Skills.STRENGTH, 37);

        requirementsString = "You need at least "
                + REQUIREMENTS.get(Skills.AGILITY) + " " + Skills.SKILL_NAME[Skills.AGILITY] + ", "
                + REQUIREMENTS.get(Skills.RANGE) + " " + Skills.SKILL_NAME[Skills.RANGE] + ", and "
                + REQUIREMENTS.get(Skills.STRENGTH) + " " + Skills.SKILL_NAME[Skills.STRENGTH]
                + " to use this shortcut.";
    }

    private static final int[] CBOWS = new int[]{
            ItemNames.MITH_CROSSBOW_9181,
            ItemNames.ADAMANT_CROSSBOW_9183,
            ItemNames.RUNE_CROSSBOW_9185,
            ItemNames.DORGESHUUN_CROSSBOW_8880
    };
    private static final Item MITH_GRAPPLE = new Item(9419);

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ObjectDefinition.forId(17049).getHandlers().put("option:grapple", this);
		ObjectDefinition.forId(17050).getHandlers().put("option:grapple", this);
		ObjectDefinition.forId(17051).getHandlers().put("option:jump", this);
		ObjectDefinition.forId(17052).getHandlers().put("option:jump", this);
		return this;
	}

    @Override
    public boolean handle(final Player player, final Node node, String option) {
        Location destination;
        Location current = player.getLocation();

        switch (option) {
            case "jump":
                ForceMovement.run(player,
                        current,
                        node.asObject().getId() == 17051
                                ? Location.create(3033, 3390, 0)
                                : Location.create(3032, 3388, 0),
                        new Animation(7268),
                        10);
                break;
            case "grapple":
                destination = node.asObject().getId() == 17049
                        ? Location.create(3033, 3389, 1)
                        : Location.create(3032, 3391, 1);

                for (Map.Entry<Integer, Integer> e : REQUIREMENTS.entrySet()) {
                    if (player.getSkills().getLevel(e.getKey()) < e.getValue()) {
                        player.getDialogueInterpreter().sendDialogue(requirementsString);
                        return true;
                    }
                }

                if (!player.getEquipment().containsOneItem(CBOWS) || !player.getEquipment().containsItem(MITH_GRAPPLE)) {
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
                                player.getAchievementDiaryManager().finishTask(player, DiaryType.FALADOR, 1, 2);
                                return true;
                        }
                        return false;
                    }
                });
                break;
        }
        return true;
    }

    @Override
    public Location getDestination(final Node moving, final Node destination) {
        return destination.asObject().getId() == 17050 ? Location.create(3032, 3388, 0) : null;
    }

}
