package plugin.interaction.city;

import core.cache.def.impl.ObjectDefinition;
import core.game.content.global.action.ClimbActionHandler;
import plugin.skill.Skills;
import plugin.skill.gather.SkillingTool;
import plugin.skill.agility.AgilityHandler;
import core.game.interaction.OptionHandler;
import core.game.node.Node;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.diary.DiaryType;
import core.game.node.object.GameObject;
import core.game.node.object.ObjectBuilder;
import core.game.system.task.LocationLogoutTask;
import core.game.system.task.LogoutTask;
import core.game.system.task.Pulse;
import core.game.world.GameWorld;
import core.game.world.map.Direction;
import core.game.world.map.Location;
import core.game.world.update.flag.context.Animation;
import core.plugin.InitializablePlugin;
import core.plugin.Plugin;

/**
 * Handles the Brimhaven dungeon.
 * @author Emperor
 */
@InitializablePlugin
public final class BrimhavenDungeonPlugin extends OptionHandler {

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ObjectDefinition.forId(5084).getHandlers().put("option:leave", this);
		ObjectDefinition.forId(5088).getHandlers().put("option:walk-across", this);
		ObjectDefinition.forId(5090).getHandlers().put("option:walk-across", this);
		ObjectDefinition.forId(5094).getHandlers().put("option:walk-up", this);
		ObjectDefinition.forId(5096).getHandlers().put("option:walk-down", this);
		ObjectDefinition.forId(5097).getHandlers().put("option:walk-up", this);
		ObjectDefinition.forId(5098).getHandlers().put("option:walk-down", this);
		ObjectDefinition.forId(5103).getHandlers().put("option:chop-down", this);
		ObjectDefinition.forId(5104).getHandlers().put("option:chop-down", this);
		ObjectDefinition.forId(5105).getHandlers().put("option:chop-down", this);
		ObjectDefinition.forId(5106).getHandlers().put("option:chop-down", this);
		ObjectDefinition.forId(5107).getHandlers().put("option:chop-down", this);
		ObjectDefinition.forId(5110).getHandlers().put("option:jump-from", this);
		ObjectDefinition.forId(5111).getHandlers().put("option:jump-from", this);
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
						// Chop the vines to gain deeper access to Brimhaven Dungeon
						player.getAchievementDiaryManager().finishTask(player, DiaryType.KARAMJA, 1, 14);
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
			GameWorld.getPulser().submit(new Pulse(3, player) {
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
						player.getAchievementDiaryManager().finishTask(player, DiaryType.KARAMJA, 1, 15);
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
			// Climb the stairs within Brimhaven Dungeon
			player.getAchievementDiaryManager().finishTask(player, DiaryType.KARAMJA, 1, 16);
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
