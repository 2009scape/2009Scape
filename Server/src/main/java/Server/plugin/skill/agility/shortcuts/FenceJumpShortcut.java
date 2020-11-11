package plugin.skill.agility.shortcuts;

import plugin.skill.agility.AgilityShortcut;
import core.game.node.Node;
import core.game.node.entity.impl.ForceMovement;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.diary.DiaryType;
import core.game.node.object.GameObject;
import core.game.system.task.Pulse;
import core.game.world.GameWorld;
import core.game.world.map.Location;
import core.game.world.update.flag.context.Animation;
import core.plugin.InitializablePlugin;

/**
 * Handles the fence jump shortcut.
 * @author Vexia
 */
@InitializablePlugin
public class FenceJumpShortcut extends AgilityShortcut {

	/**
	 * Represents the running animation.
	 */
	private static final Animation RUNNING_ANIM = new Animation(1995);

	/**
	 * Represents the jumping animation.
	 */
	private static final Animation JUMP_ANIM = new Animation(1603);

	/**
	 * Represents the related locations used in this shortcut. format: (start,
	 * start, end, end
	 */
	private static final Location[] LOCATIONS = new Location[] { new Location(3240, 3331, 0), new Location(3240, 3338, 0), Location.create(3240, 3334, 0), Location.create(3240, 3335, 0) };

	/**
	 * Constructs a new {@Code FenceJumpShortcut} {@Code Object}
	 */
	public FenceJumpShortcut() {
		super(new int[] { 9300 }, 13, 0.0, "jump-over");
	}

	@Override
	public void run(final Player player, GameObject object, String option, boolean failed) {
		player.faceLocation(object.getLocation());
		GameWorld.getPulser().submit(new Pulse(2, player) {
			@Override
			public boolean pulse() {
				player.animate(JUMP_ANIM);
				player.getAchievementDiaryManager().finishTask(player, DiaryType.VARROCK, 0, 5);
				return true;
			}
		});
		ForceMovement.run(player, player.getLocation().getY() >= 3335 ? LOCATIONS[1] : LOCATIONS[0], player.getLocation().getY() >= 3335 ? LOCATIONS[2] : LOCATIONS[3], RUNNING_ANIM, 18);
	}

	@Override
	public Location getDestination(Node node, Node n) {
		Player player = node.asPlayer();
		return player.getLocation().getY() >= 3335 ? LOCATIONS[1] : LOCATIONS[0];
	}

}
