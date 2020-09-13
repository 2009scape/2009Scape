package plugin.skill.agility.shortcuts;

import plugin.quest.members.PriestInPeril;
import plugin.skill.agility.AgilityHandler;
import plugin.skill.agility.AgilityShortcut;
import core.game.node.entity.player.Player;
import core.game.node.object.GameObject;
import core.game.world.map.Direction;
import core.game.world.map.Location;
import core.game.world.update.flag.context.Animation;
import core.plugin.InitializablePlugin;
import core.plugin.Plugin;

/**
 * Handles the bar squeezing shortcut.
 * @author Vexia
 */
@InitializablePlugin
public class BarSqueezeShortcut extends AgilityShortcut {

	/**
	 * Constructs a new {@Code BarSqueezeShortcut} {@Code Object}
	 */
	public BarSqueezeShortcut() {
		super(new int[] { 9334, 9337 }, 66, 1, "squeeze-through");
	}

	/**
	 * Constructs a new {@Code BarSqueezeShortcut} {@Code Object}
	 * @param ids the ids.
	 * @param level the level.
	 * @param exp the exp.
	 * @param option the option.
	 */
	public BarSqueezeShortcut(int[] ids, int level, double exp, String option) {
		super(ids, level, exp, option);
	}

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		configure(new BarSqueezeShortcut(new int[] { 2186 }, 1, 0.0, "squeeze-through"));
		return super.newInstance(arg);
	}

	@Override
	public void run(Player player, GameObject object, String option, boolean failed) {
		Direction dir = Direction.getLogicalDirection(player.getLocation(), object.getLocation());
		Location start = player.getLocation();
		if (object.getId() == 9334 && dir == Direction.NORTH) {
			dir = Direction.WEST;
			start = Location.create(3424, 3476, 0);
		} else if (object.getId() == 9337 && dir == Direction.NORTH) {
			dir = Direction.SOUTH;
			if (player.getLocation().getY() < object.getLocation().getY()) {
				dir = Direction.NORTH;
			}
		} else if (object.getId() == 2186 && player.getLocation().getY() >= 3161) {
			dir = Direction.SOUTH;
		}
		AgilityHandler.forceWalk(player, -1, start, player.getLocation().transform(dir, 1), Animation.create(2240), 10, 0.0, null);
	}

	@Override
	public boolean checkRequirements(Player player) {
		if (!player.getQuestRepository().isComplete(PriestInPeril.NAME) && !(player.getLocation().getY() >= 3159 && player.getLocation().getY() <= 3161)) {
			player.getDialogueInterpreter().sendDialogue("You need to have completed Priest in Peril in order to do this.");
			return false;
		}
		return super.checkRequirements(player);
	}
}
