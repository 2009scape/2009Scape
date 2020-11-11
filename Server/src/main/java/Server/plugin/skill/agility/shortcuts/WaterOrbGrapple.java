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
public class WaterOrbGrapple extends OptionHandler {
    private static final HashMap<Integer, Integer> REQUIREMENTS = new HashMap<>();
    private static final String requirementsString;

    static {
        REQUIREMENTS.putIfAbsent(Skills.AGILITY, 36);
        REQUIREMENTS.putIfAbsent(Skills.RANGE, 39);
        REQUIREMENTS.putIfAbsent(Skills.STRENGTH, 22);

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
        ObjectDefinition.forId(17062).getHandlers().put("option:grapple", this);
        return this;
    }

    @Override
    public boolean handle(Player player, Node node, String option) {
        Location destination;
        Location current = player.getLocation();
        GameObject rock = RegionManager.getObject(Location.create(2841, 3426, 0));
        GameObject tree = RegionManager.getObject(Location.create(2841, 3434, 0));
        System.out.println(rock);
        System.out.println(tree);

        switch (option) {
            case "grapple":
                destination = Location.create(2841, 3433, 0);

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
                            // TODO this animation sequence is wrong. sendPositionedGraphic doesn't work correctly, and rest of water crossing not well implemented
                            // See 4:24 in https://www.youtube.com/watch?v=O90y-N_vwTc
                            // rope gfx is 67
                            // splash gfx are 68 and 69, not sure why there are two
                            // not sure what swimming animation is, could be 4464 thru 4468
                            case 1:
                                player.faceLocation(destination);
                                player.animate(new Animation(4230));
                                break;
                            case 3:
                                player.getPacketDispatch().sendPositionedGraphic(67, 10, 0, Location.create(2840,3427,0)); //
                                break;
                            case 4:
                                ObjectBuilder.replace(rock, rock.transform(rock.getId() + 1), 10);
                                ObjectBuilder.replace(tree, tree.transform(tree.getId() + 1), 10);
                                break;
                            case 5:
                                break;
                            case 13:
                                player.getProperties().setTeleportLocation(destination);
                                break;
                            case 14:
                                player.unlock();
                                player.getAchievementDiaryManager().finishTask(player, DiaryType.SEERS_VILLAGE, 2, 10);
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
        // Run between rock and stream before firing grapple
        return Location.create(2841, 3427, 0);
    }
}
