package plugin.skill.agility.shortcuts;

import core.game.node.entity.player.link.diary.DiaryType;
import plugin.skill.agility.AgilityHandler;
import plugin.skill.agility.AgilityShortcut;
import core.game.node.Node;
import core.game.node.entity.player.Player;
import core.game.node.object.GameObject;
import core.game.world.map.Location;
import core.game.world.update.flag.context.Animation;
import core.plugin.InitializablePlugin;
import core.plugin.Plugin;

/**
 * Handles the log balance shortcut.
 * @author Vexia
 */
@InitializablePlugin
public class LogBalanceShortcut extends AgilityShortcut {

	/**
	 * The start and ending location.
	 */
	private Location start;

	/**
	 * The ending and start location.
	 */
	private Location end;

	/**
	 * Constructs a new {@Code LogBalanceShortcut} {@Code Object}
	 * @param ids the ids.
	 * @param level the level.
	 * @param experience the experience.
	 * @param options the options.
	 */
	public LogBalanceShortcut(int[] ids, int level, double experience, Location start, Location end, String... options) {
		super(ids, level, experience, options);
		this.start = start;
		this.end = end;
	}

	/**
	 * Constructs a new {@Code LogBalanceShortcut} {@Code Object}
	 */
	public LogBalanceShortcut() {
		super(new int[] {}, 0, 0.0);
	}

	@Override
	public Plugin<Object> newInstance(Object arg) {
		configure(new LogBalanceShortcut(new int[] { 2296 }, 20, 5, new Location(2598, 3477, 0), Location.create(2603, 3477, 0), "walk-across"));
		// Sinclair mansion to Rellekka.  9324 is on the south side of the stream going north.
		configure(new LogBalanceShortcut(new int[] { 9322, 9324 }, 48, 1, Location.create(2722, 3592, 0), Location.create(2722, 3596, 0), "walk-across"));
		configure(new LogBalanceShortcut(new int[] { 35997, 35999 }, 33, 1, Location.create(2602, 3336, 0), Location.create(2598, 3336, 0), "walk-across"));
		configure(new LogBalanceShortcut(new int[] { 2332 }, 1, 1, Location.create(2910, 3049, 0), Location.create(2906, 3049, 0), "cross"));
		return this;
	}

	@Override
	public void run(Player player, GameObject object, String option, boolean failed) {
		Location destination = start;
		if (player.getLocation().getDistance(start) < player.getLocation().getDistance(end)) {
			destination = end;
		}
		AgilityHandler.walk(player, -1, player.getLocation(), destination, Animation.create(155), getExperience(), null);

		// Seers Achievement Diary
		if (destination.equals(new Location(2722,3596,0))
				&& !player.getAchievementDiaryManager().getDiary(DiaryType.SEERS_VILLAGE).isComplete(1,0)) {
			player.getAchievementDiaryManager().getDiary(DiaryType.SEERS_VILLAGE).updateTask(player,1,0,true);
		}
	}

	@Override
	public Location getDestination(Node node, Node n) {
		if (node.getLocation().getDistance(start) < node.getLocation().getDistance(end)) {
			return start;
		}
		return end;
	}

}
