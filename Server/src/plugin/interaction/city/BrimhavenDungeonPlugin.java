package plugin.interaction.city;

import org.crandor.cache.def.impl.ObjectDefinition;
import org.crandor.game.content.global.action.ClimbActionHandler;
import org.crandor.game.content.skill.Skills;
import org.crandor.game.content.skill.free.gather.SkillingTool;
import org.crandor.game.content.skill.member.agility.AgilityHandler;
import org.crandor.game.interaction.OptionHandler;
import org.crandor.game.node.Node;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.entity.player.link.diary.DiaryType;
import org.crandor.game.node.object.GameObject;
import org.crandor.game.node.object.ObjectBuilder;
import org.crandor.game.system.task.LocationLogoutTask;
import org.crandor.game.system.task.LogoutTask;
import org.crandor.game.system.task.Pulse;
import org.crandor.game.world.GameWorld;
import org.crandor.game.world.PulseRunner;
import org.crandor.game.world.map.Direction;
import org.crandor.game.world.map.Location;
import org.crandor.game.world.update.flag.context.Animation;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.plugin.Plugin;

/**
 * Handles the Brimhaven dungeon.
 * @author Emperor
 */
@InitializablePlugin
public final class BrimhavenDungeonPlugin extends OptionHandler {

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ObjectDefinition.forId(5084).getConfigurations().put("option:leave", this);
		ObjectDefinition.forId(5088).getConfigurations().put("option:walk-across", this);
		ObjectDefinition.forId(5090).getConfigurations().put("option:walk-across", this);
		ObjectDefinition.forId(5094).getConfigurations().put("option:walk-up", this);
		ObjectDefinition.forId(5096).getConfigurations().put("option:walk-down", this);
		ObjectDefinition.forId(5097).getConfigurations().put("option:walk-up", this);
		ObjectDefinition.forId(5098).getConfigurations().put("option:walk-down", this);
		ObjectDefinition.forId(5099).getConfigurations().put("option:squeeze-through", this);
		ObjectDefinition.forId(5100).getConfigurations().put("option:squeeze-through", this);
		ObjectDefinition.forId(5103).getConfigurations().put("option:chop-down", this);
		ObjectDefinition.forId(5104).getConfigurations().put("option:chop-down", this);
		ObjectDefinition.forId(5105).getConfigurations().put("option:chop-down", this);
		ObjectDefinition.forId(5106).getConfigurations().put("option:chop-down", this);
		ObjectDefinition.forId(5107).getConfigurations().put("option:chop-down", this);
		ObjectDefinition.forId(5110).getConfigurations().put("option:jump-from", this);
		ObjectDefinition.forId(5111).getConfigurations().put("option:jump-from", this);
		return this;
	}

	@Override
	public boolean handle(final Player player, Node node, String option) {
		final GameObject object = (GameObject) node;
		switch (object.getId()) {
		case 5084:
			player.getProperties().setTeleportLocation(Location.create(2745, 3152, 0));
			return true;
		case 5103:
		case 5104:
		case 5105:
		case 5106:
		case 5107:
			int level = 10 + ((object.getId() - 5103) * 6);
			if (player.getSkills().getLevel(Skills.WOODCUTTING) < level) {
				player.getPacketDispatch().sendMessage("You need a woodcutting level of " + level + " to chop down this vine.");
				return true;
			}
			SkillingTool tool = SkillingTool.getHatchet(player);
			if (tool == null) {
				player.getPacketDispatch().sendMessage("You don't have an axe to cut these vines.");
				return true;
			}
			player.animate(tool.getAnimation());
			player.getPulseManager().run(new Pulse(3, player) {
				@Override
				public boolean pulse() {
					if (ObjectBuilder.replace(object, object.transform(0), 2)) {
						Location destination = getVineDestination(player, object);
						player.lock(3);
						player.getWalkingQueue().reset();
						if (!player.getAchievementDiaryManager().getDiary(DiaryType.KARAMJA).isComplete(1, 8)) {
							player.getAchievementDiaryManager().getDiary(DiaryType.KARAMJA).updateTask(player, 1, 8, true);
						}
						player.getWalkingQueue().addPath(destination.getX(), destination.getY(), true);
					}
					return true;
				}
			});
			return true;
		case 5110:
		case 5111:
			if (player.getSkills().getLevel(Skills.AGILITY) < 12) {
				player.getPacketDispatch().sendMessage("You need an agility level of 12 to cross this.");
				return true;
			}
			player.lock(12);
			final Direction dir = AgilityHandler.forceWalk(player, -1, player.getLocation(), object.getLocation(), Animation.create(769), 10, 0, null).getDirection();
			PulseRunner.submit(new Pulse(3, player) {
				int stage = dir == Direction.NORTH ? -1 : 0;
				Direction direction = dir;

				@Override
				public boolean pulse() {
					Location l = player.getLocation();
					switch (stage++) {
					case 1:
						direction = Direction.get(direction.toInteger() + 1 & 3);
						break;
					case 3:
						direction = Direction.get(direction.toInteger() - 1 & 3);
						break;
					case 5:
						if (direction == Direction.NORTH) {
							return true;
						}
					}
					if (stage == 6) {
						if (!player.getAchievementDiaryManager().getDiary(DiaryType.KARAMJA).isComplete(1, 9)) {
							player.getAchievementDiaryManager().getDiary(DiaryType.KARAMJA).updateTask(player, 1, 9, true);
						}
					}
					AgilityHandler.forceWalk(player, -1, l, l.transform(direction), Animation.create(769), 10, 0, null);
					return stage == 6;
				}
			});
			player.addExtension(LogoutTask.class, new LocationLogoutTask(13, player.getLocation()));
			return true;
		case 5094:
			ClimbActionHandler.climb(player, null, Location.create(2643, 9594, 2));
			return true;
		case 5096:
			ClimbActionHandler.climb(player, null, Location.create(2649, 9591, 0));
			return true;
		case 5097:
			if (!player.getAchievementDiaryManager().getDiary(DiaryType.KARAMJA).isComplete(1, 10)) {
				player.getAchievementDiaryManager().getDiary(DiaryType.KARAMJA).updateTask(player, 1, 10, true);
			}
			ClimbActionHandler.climb(player, null, Location.create(2636, 9510, 2));
			return true;
		case 5098:
			ClimbActionHandler.climb(player, null, Location.create(2636, 9517, 0));
			return true;
		case 5088:
			if (player.getSkills().getLevel(Skills.AGILITY) < 30) {
				player.getPacketDispatch().sendMessage("You need an agility level of 30 to cross this.");
				return true;
			}
			AgilityHandler.walk(player, -1, player.getLocation(), Location.create(2687, 9506, 0), Animation.create(155), 0, null);
			return true;
		case 5090:
			if (player.getSkills().getLevel(Skills.AGILITY) < 30) {
				player.getPacketDispatch().sendMessage("You need an agility level of 30 to cross this.");
				return true;
			}
			AgilityHandler.walk(player, -1, player.getLocation(), Location.create(2682, 9506, 0), Animation.create(155), 0, null);
			return true;
		case 5099:
		case 5100:
			level = object.getId() == 5099 ? 34 : 22;
			if (player.getSkills().getLevel(Skills.AGILITY) < level) {
				player.getPacketDispatch().sendMessage("You need an agility level of " + level + " to squeeze through the pipe.");
				return true;
			}
			final Direction direction = Direction.get(object.getRotation() + 1 & 3);
			player.lock(12);
			Location l = player.getLocation();
			AgilityHandler.forceWalk(player, -1, l, l = l.transform(direction, 3), Animation.create(749), 10, 0, null);
			AgilityHandler.forceWalk(player, -1, l, l = l.transform(direction, 2), Animation.create(844), 10, 0, null, 5);
			AgilityHandler.forceWalk(player, -1, l, l = l.transform(direction, 2), Animation.create(748), 20, 0, null, 8);
			player.addExtension(LogoutTask.class, new LocationLogoutTask(12, player.getLocation()));
			return true;
		}
		return false;
	}

	/**
	 * Gets the destination for chopping vines.
	 * @param player The player.
	 * @param object The object.
	 * @return The destination location.
	 */
	private static Location getVineDestination(Player player, GameObject object) {
		if (object.getRotation() % 2 != 0) {
			if (player.getLocation().getX() > object.getLocation().getX()) {
				return object.getLocation().transform(-1, 0, 0);
			}
			return object.getLocation().transform(1, 0, 0);
		}
		if (player.getLocation().getY() > object.getLocation().getY()) {
			return object.getLocation().transform(0, -1, 0);
		}
		return object.getLocation().transform(0, 1, 0);
	}

}
