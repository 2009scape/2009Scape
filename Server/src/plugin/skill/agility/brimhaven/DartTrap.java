package plugin.skill.agility.brimhaven;

import org.crandor.game.content.skill.Skills;
import org.crandor.game.content.skill.member.agility.AgilityHandler;
import org.crandor.game.node.entity.Entity;
import org.crandor.game.node.entity.impl.Projectile;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.system.task.LocationLogoutTask;
import org.crandor.game.system.task.LogoutTask;
import org.crandor.game.system.task.MovementHook;
import org.crandor.game.system.task.Pulse;
import org.crandor.game.world.GameWorld;
import org.crandor.game.world.map.Direction;
import org.crandor.game.world.map.Location;
import org.crandor.game.world.update.flag.context.Animation;
import org.crandor.net.packet.PacketRepository;
import org.crandor.net.packet.context.CameraContext;
import org.crandor.net.packet.context.CameraContext.CameraType;
import org.crandor.net.packet.out.CameraViewPacket;
import org.crandor.tools.RandomFunction;

/**
 * Handles the darts trap.
 * @author Emperor
 */
public final class DartTrap implements MovementHook {

	@Override
	public boolean handle(Entity e, final Location l) {
		final Direction dir = e.getDirection();
		final Player player = (Player) e;
		final Location start = l.transform(-dir.getStepX(), -dir.getStepY(), 0);
		e.lock(6);
		e.addExtension(LogoutTask.class, new LocationLogoutTask(13, start));
		final Location startProj = l.transform(dir.getStepX() * 5, dir.getStepY() * 5, 0);
		GameWorld.Pulser.submit(new Pulse(2, e) {
			boolean failed;
			int count;

			@Override
			public boolean pulse() {
				if (++count == 1) {
					if (failed = AgilityHandler.hasFailed(player, 40, 0.15)) {
						if (player.getSkills().getLevel(Skills.AGILITY) < 40) {
							player.getPacketDispatch().sendMessage("You need an agility of at least 40 to get past this trap!");
						}
						Projectile.create(startProj, l, 270, 0, 10, 46, 85, 5, 11).send();
						setDelay(3);
					} else {
						PacketRepository.send(CameraViewPacket.class, new CameraContext(player, CameraType.POSITION, startProj.getX() + (dir.getStepX() * 4), startProj.getY() + (dir.getStepY() * 4), 350, 1, 100));
						PacketRepository.send(CameraViewPacket.class, new CameraContext(player, CameraType.ROTATION, l.getX(), l.getY(), 350, 1, 100));
						Projectile.create(startProj, l.transform(-dir.getStepX() * 4, -dir.getStepY() * 4, 0), 270, 0, 0, 46, 200, 5, 11).send();
					}
				} else if (count == 2) {
					if (failed) {
						int hit = player.getSkills().getLifepoints() / 12;
						if (hit < 2) {
							hit = 2;
						}
						setDelay(1);
						AgilityHandler.failWalk(player, 1, l, start, start, Animation.create(1114), 10, hit, null).setDirection(dir);
					} else {
						if (dir.toInteger() % 2 != 0) {
							int mod = dir == Direction.WEST ? -1 : 1;
							PacketRepository.send(CameraViewPacket.class, new CameraContext(player, CameraType.POSITION, l.getX() - (5 * mod), l.getY() - (5 * mod), 400, 8, 6));
							PacketRepository.send(CameraViewPacket.class, new CameraContext(player, CameraType.ROTATION, l.getX() + (2 * mod), l.getY(), 350, 8, 1));
						} else {
							int mod = dir == Direction.SOUTH ? -1 : 1;
							PacketRepository.send(CameraViewPacket.class, new CameraContext(player, CameraType.POSITION, l.getX() + (5 * mod), l.getY() - (5 * mod), 400, 8, 6));
							PacketRepository.send(CameraViewPacket.class, new CameraContext(player, CameraType.ROTATION, l.getX(), l.getY() + (2 * mod), 350, 8, 1));
						}
						player.lock(7);
						player.animate(new Animation(1110, 20));
						setDelay(5);
					}
				} else if (count == 3) {
					if (failed) {
						player.getPacketDispatch().sendMessage("You were hit by some darts, something on them makes you feel dizzy!");
						player.getSkills().updateLevel(Skills.AGILITY, -(2 + RandomFunction.randomize(2)), 0);
						return true;
					}
					setDelay(2);
					AgilityHandler.walk(player, -1, l, l.transform(dir.getStepX() << 1, dir.getStepY() << 1, 0), null, 30.0, null);
				} else if (count == 4) {
					PacketRepository.send(CameraViewPacket.class, new CameraContext(player, CameraType.RESET, 0, 0, 0, 0, 0));
					return true;
				}
				return false;
			}
		});
		return false;
	}

}