package plugin.skill.agility.shortcuts;

import org.crandor.game.content.skill.member.agility.AgilityShortcut;
import org.crandor.game.node.Node;
import org.crandor.game.node.entity.impl.ForceMovement;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.entity.player.link.diary.DiaryType;
import org.crandor.game.node.object.GameObject;
import org.crandor.game.system.task.Pulse;
import org.crandor.game.world.GameWorld;
import org.crandor.game.world.PulseRunner;
import org.crandor.game.world.map.Location;
import org.crandor.game.world.update.flag.context.Animation;
import org.crandor.plugin.InitializablePlugin;

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
		PulseRunner.submit(new Pulse(2, player) {
			@Override
			public boolean pulse() {
				player.animate(JUMP_ANIM);
				if (!player.getAchievementDiaryManager().getDiary(DiaryType.VARROCK).isComplete(0, 5)) {
					player.getAchievementDiaryManager().getDiary(DiaryType.VARROCK).updateTask(player, 0, 5, true);
				}
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
