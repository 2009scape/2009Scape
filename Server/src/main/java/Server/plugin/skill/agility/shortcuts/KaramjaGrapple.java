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
import core.game.node.object.ObjectBuilder;
import core.game.system.task.Pulse;
import core.game.world.GameWorld;
import core.game.world.map.Direction;
import core.game.world.map.Location;
import core.game.world.map.RegionManager;
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
public class KaramjaGrapple extends OptionHandler {
    private static final HashMap<Integer, Integer> REQUIREMENTS = new HashMap<>();
    private static final String requirementsString;

    static {
        REQUIREMENTS.putIfAbsent(Skills.AGILITY, 53);
        REQUIREMENTS.putIfAbsent(Skills.RANGE, 42);
        REQUIREMENTS.putIfAbsent(Skills.STRENGTH, 21);

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
        ObjectDefinition.forId(17074).getHandlers().put("option:grapple", this);
        // island tree 17074 +1 rope loop, +2 grappled one way, +3 grappled other way
        // north tree 17056 +1 rope loop, +2 grappled
        // south tree 17059 +1 rope loop, +2 grappled
        return this;
    }

    @Override
    public boolean handle(Player player, Node node, String option) {
        Location destination;
        Location current = player.getLocation();
        GameObject startTree, endTree;
        Direction direction;
        if (current.getY() > 3134) { // starting at north side
            startTree = RegionManager.getObject(Location.create(2874, 3144, 0));
            endTree = RegionManager.getObject(Location.create(2873, 3125, 0));
            destination = Location.create(2874, 3127, 0);
            direction = Direction.SOUTH;
        } else {
            startTree = RegionManager.getObject(Location.create(2873, 3125, 0));
            endTree = RegionManager.getObject(Location.create(2874, 3144, 0));
            destination = Location.create(2874, 3142, 0);
            direction = Direction.NORTH;
        }
        GameObject islandTree = RegionManager.getObject(Location.create(2873, 3134, 0));


        switch (option) {
            case "grapple":

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
                            // TODO animations not implemented.
                            // See ~11min in https://www.youtube.com/watch?v=qpB53rzYqrA
                            // don't know how to get ropes to show up.  The tree objects have grapples and stuff but don't look like the video and aren't the right directions
                            // splash gfx are 68 and 69, not sure why there are two
                            // not sure what swimming animation is, could be 4464 thru 4468
                            case 1:
                                player.faceLocation(player.getLocation().transform(direction));
                                player.animate(new Animation(4230));
                                break;
                            case 3:
                                //player.getPacketDispatch().sendPositionedGraphic(67, 10, 0, player.getLocation().transform(direction, 5)); //
                                break;
                            case 4:
                                //ObjectBuilder.replace(startTree, startTree.transform(startTree.getId() + 1), 10);
                                //ObjectBuilder.replace(islandTree, islandTree.transform(islandTree.getId() + 1), 10);
                                break;
                            case 5:
                                break;
                            case 13:
                                player.getProperties().setTeleportLocation(destination);
                                break;
                            case 14:
                                player.unlock();
                                player.getAchievementDiaryManager().finishTask(player, DiaryType.KARAMJA, 2, 6);
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
        // Run between tree and water before firing grapple
        if (moving.getLocation().getY() > 3134) { // starting at north side
            return Location.create(2874, 3142, 0);
        } else {
            return Location.create(2874, 3127, 0);
        }
    }
}
