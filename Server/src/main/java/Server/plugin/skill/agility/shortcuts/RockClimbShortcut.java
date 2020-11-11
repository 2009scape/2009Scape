package plugin.skill.agility.shortcuts;

import plugin.skill.agility.AgilityShortcut;
import core.game.node.entity.impl.ForceMovement;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.diary.DiaryType;
import core.game.node.object.GameObject;
import core.game.world.map.Direction;
import core.game.world.map.Location;
import core.game.world.update.flag.context.Animation;
import core.plugin.InitializablePlugin;

/**
 * Handles the rock climbing shortcut.
 * @author Vexia
 */
@InitializablePlugin
public class RockClimbShortcut extends AgilityShortcut {

	/**
	 * Represents the scaling down anmation.
	 */
	private static final Animation ANIMATION = new Animation(1148);

	/**
	 * Represents the scaling animation.
	 */
	private static final Animation SCALE = new Animation(740);

	/**
	 * Constructs a new {@Code RockClimbShortcut} {@Code Object}
	 */
	public RockClimbShortcut() {
		super(new int[] { 9335, 9336, 2231 }, 1, 0.0, "climb");
	}

	@Override
	public void run(Player player, GameObject object, String option, boolean failed) {
		switch (object.getId()) {
		case 2231:
			if (player.getLocation().getX() <= 2791) {
				ForceMovement.run(player, object.getLocation(), object.getLocation().transform(3, 0, 0), SCALE, SCALE, Direction.WEST, 13).setEndAnimation(Animation.RESET);
			} else {
				ForceMovement.run(player, object.getLocation(), object.getLocation().transform(-3, 0, 0), ANIMATION, ANIMATION, Direction.WEST, 13).setEndAnimation(Animation.RESET);
			}
			break;
		case 9335:
			ForceMovement.run(player, player.getLocation(), Location.create(3427, 3478, 0), SCALE, SCALE, Direction.WEST, 13).setEndAnimation(Animation.RESET);
			break;
		case 9336:
			ForceMovement.run(player, player.getLocation(), Location.create(3424, 3476, 0), ANIMATION, ANIMATION, Direction.WEST, 13).setEndAnimation(Animation.RESET);
			break;
		}
	}

}
