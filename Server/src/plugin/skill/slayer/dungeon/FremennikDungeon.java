package plugin.skill.slayer.dungeon;

import org.crandor.game.content.skill.Skills;
import org.crandor.game.content.skill.member.agility.AgilityHandler;
import org.crandor.game.interaction.Option;
import org.crandor.game.node.Node;
import org.crandor.game.node.entity.Entity;
import org.crandor.game.node.entity.combat.ImpactHandler.HitsplatType;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.object.GameObject;
import org.crandor.game.system.task.Pulse;
import org.crandor.game.world.GameWorld;
import org.crandor.game.world.map.Direction;
import org.crandor.game.world.map.Location;
import org.crandor.game.world.map.zone.MapZone;
import org.crandor.game.world.map.zone.ZoneBorders;
import org.crandor.game.world.map.zone.ZoneBuilder;
import org.crandor.game.world.update.flag.context.Animation;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.plugin.Plugin;
import org.crandor.tools.RandomFunction;

/**
 * Represents the femennik dungeon zone.
 * @author Vexia
 */
@InitializablePlugin
public final class FremennikDungeon extends MapZone implements Plugin<Object> {

	/**
	 * Constructs a new {@code FremennikDungeon} {@code Object}.
	 */
	public FremennikDungeon() {
		super("fremennik", true);
	}

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ZoneBuilder.configure(this);
		return this;
	}

	@Override
	public Object fireEvent(String identifier, Object... args) {
		return true;
	}

	@Override
	public boolean interact(Entity entity, Node target, Option option) {
		if (entity instanceof Player && target instanceof GameObject) {
			final Player player = (Player) entity;
			final GameObject object = (GameObject) target;
			final Direction dir = Direction.getLogicalDirection(player.getLocation(), object.getLocation());
			switch (target.getId()) {
			case 9326:// pyrefiend area.
				if (player.getSkills().getLevel(Skills.AGILITY) < 81) {
					player.getPacketDispatch().sendMessage("You need an Agility level of at least 81 to do this.");
					return true;
				}
				player.lock();
				GameWorld.Pulser.submit(new Pulse(1, player) {
					int count;

					@Override
					public boolean pulse() {
						switch (++count) {
						case 1:
							Location start = object.getLocation().transform(dir.getOpposite(), 2);
							player.getWalkingQueue().reset();
							player.getWalkingQueue().addPath(start.getX(), start.getY());
							break;
						case 2:
							player.faceLocation(object.getLocation());
							break;
						case 3:
							Location end = object.getLocation().transform(dir, 1);
							AgilityHandler.forceWalk(player, -1, player.getLocation(), end, Animation.create(1995), 20, 0.0, null);
							break;
						case 4:
							final boolean fail = AgilityHandler.hasFailed(player, 20, 0.1);
							if (fail) {
								player.getImpactHandler().manualHit(player, RandomFunction.random(6), HitsplatType.NORMAL);
								player.getPacketDispatch().sendMessage("You trigger the trap as you jump over it.");
							}
							player.animate(Animation.create(1603));
							player.unlock();
							return true;
						}
						return false;
					}
				});
				return true;
			case 9321:
				if (player.getSkills().getLevel(Skills.AGILITY) < 62) {
					player.getPacketDispatch().sendMessage("You need an Agility level of at least 62 to do this.");
					return true;
				}
				Location end = object.getLocation().transform(dir, 4);
				AgilityHandler.walk(player, -1, player.getLocation(), end, Animation.create(156), 10, "You climb your way through the narrow crevice.");
				return true;
			}
		}
		return super.interact(entity, target, option);
	}

	@Override
	public void configure() {
		register(new ZoneBorders(2671, 9950, 2813, 10054));
	}

}
