package plugin.interaction.object;

import core.cache.def.impl.ObjectDefinition;
import core.game.node.entity.player.link.diary.DiaryType;
import plugin.skill.Skills;
import core.game.interaction.OptionHandler;
import core.game.node.Node;
import core.game.node.entity.impl.ForceMovement;
import core.game.node.entity.player.Player;
import core.game.node.object.GameObject;
import core.game.system.task.Pulse;
import core.game.world.GameWorld;
import core.game.world.map.Location;
import core.game.world.update.flag.context.Animation;
import core.plugin.InitializablePlugin;
import core.plugin.Plugin;

/**
 * Handles the grand exchange shortcut.
 * @author Emperor
 * @version 1.0
 */
@InitializablePlugin
public final class GrandExchangeShortcut extends OptionHandler {

	/**
	 * The climbing down animation.
	 */
	private static final Animation CLIMB_DOWN = Animation.create(2589);

	/**
	 * The crawling through animation.
	 */
	private static final Animation CRAWL_THROUGH = Animation.create(2590);

	/**
	 * The climbing up animation.
	 */
	private static final Animation CLIMB_UP = Animation.create(2591);

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ObjectDefinition.forId(9311).getHandlers().put("option:climb-into", this);
		ObjectDefinition.forId(9312).getHandlers().put("option:climb-into", this);
		return null;
	}

	@Override
	public boolean handle(final Player player, Node node, String option) {
		if (player.getSkills().getLevel(Skills.AGILITY) < 21) {
			player.getDialogueInterpreter().sendDialogue("You need an Agility level of at least 21 to do this.");
			return true;
		}
		player.lock(4);
		final GameObject o = (GameObject) node;
		if (o.getId() == 9311) {
			ForceMovement.run(player, Location.create(3138, 3516, 0), o.getLocation(), CLIMB_DOWN);
			GameWorld.getPulser().submit(new Pulse(1, player) {
				int count;

				@Override
				public boolean pulse() {
					switch (++count) {
					case 2:
						player.animate(CRAWL_THROUGH);
						player.getProperties().setTeleportLocation(Location.create(3143, 3514, 0));
						break;
					case 3:
						ForceMovement.run(player, Location.create(3143, 3514, 0), Location.create(3144, 3514, 0), CLIMB_UP);
						// Use the shortcut under the wall, north-west of the Grand<br><br>Exchange
						player.getAchievementDiaryManager().finishTask(player, DiaryType.VARROCK, 1, 8);
						return true;
					}
					return false;
				}
			});
		} else {
			ForceMovement.run(player, Location.create(3144, 3514, 0), o.getLocation(), CLIMB_DOWN);
			GameWorld.getPulser().submit(new Pulse(1, player) {
				int count;

				@Override
				public boolean pulse() {
					switch (++count) {
					case 2:
						player.animate(CRAWL_THROUGH);
						player.getProperties().setTeleportLocation(Location.create(3139, 3516, 0));
						break;
					case 3:
						ForceMovement.run(player, Location.create(3139, 3516, 0), Location.create(3138, 3516, 0), CLIMB_UP);
						// Use the shortcut under the wall, north-west of the Grand<br><br>Exchange
						player.getAchievementDiaryManager().finishTask(player, DiaryType.VARROCK, 1, 8);
						return true;
					}
					return false;
				}
			});
		}
		return true;
	}

	@Override
	public Location getDestination(Node n, Node node) {
		if (((GameObject) node).getId() == 9311) {
			return Location.create(3138, 3516, 0);
		}
		return Location.create(3144, 3514, 0);
	}

}
