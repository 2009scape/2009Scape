package plugin.cutscene;

import org.crandor.game.content.activity.ActivityPlugin;
import org.crandor.game.content.activity.CutscenePlugin;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.object.GameObject;
import org.crandor.game.node.object.ObjectBuilder;
import org.crandor.game.system.task.Pulse;
import org.crandor.game.world.GameWorld;
import org.crandor.game.world.map.Location;
import org.crandor.game.world.map.RegionManager;
import org.crandor.game.world.map.build.DynamicRegion;
import org.crandor.game.world.update.flag.context.Animation;
import org.crandor.net.packet.PacketRepository;
import org.crandor.net.packet.context.CameraContext;
import org.crandor.net.packet.context.CameraContext.CameraType;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.net.packet.out.CameraViewPacket;

/**
 * Represents the organ cutscene.
 * @author 'Vexia
 */
@InitializablePlugin
public final class OrganCutScene extends CutscenePlugin {

	/**
	 * Constructs a new {@code OrganCutScene} {@code Object}.
	 */
	public OrganCutScene() {
		super("organ cutscene");
	}

	/**
	 * Constructs a new {@code OrganCutScene} {@code Object}.
	 * @param player the player.
	 */
	public OrganCutScene(final Player player) {
		super("organ cutscene");
		this.player = player;
	}

	@Override
	public void open() {
		final GameObject orgin = RegionManager.getObject(base.transform(43, 14, 0));
		final GameObject newOrgin = new GameObject(36979, base.transform(42, 14, 0));
		ObjectBuilder.remove(orgin);
		ObjectBuilder.add(newOrgin);
		PacketRepository.send(CameraViewPacket.class, new CameraContext(player, CameraType.POSITION, player.getLocation().getX() + 2, player.getLocation().getY() - 7, 405, 1, 100));
		PacketRepository.send(CameraViewPacket.class, new CameraContext(player, CameraType.ROTATION, player.getLocation().getX() + 1, player.getLocation().getY(), 405, 1, 100));
		player.lock();
		GameWorld.Pulser.submit(new Pulse(3) {
			@Override
			public boolean pulse() {
				player.getPacketDispatch().sendObjectAnimation(RegionManager.getObject(base.transform(42, 14, 0)), new Animation(9841));
				PacketRepository.send(CameraViewPacket.class, new CameraContext(player, CameraType.POSITION, player.getLocation().getX() + 2, player.getLocation().getY() - 3, 400, 1, 1));
				PacketRepository.send(CameraViewPacket.class, new CameraContext(player, CameraType.ROTATION, player.getLocation().getX() + 1, player.getLocation().getY(), 400, 1, 1));
				return true;
			}
		});
		GameWorld.Pulser.submit(new Pulse(30) {
			@Override
			public boolean pulse() {
				unpause();
				return true;
			}
		});
	}

	@Override
	public ActivityPlugin newInstance(Player p) throws Throwable {
		return new OrganCutScene(p);
	}

	@Override
	public Location getSpawnLocation() {
		return null;
	}

	@Override
	public Location getStartLocation() {
		return base.transform(42, 14, 0);
	}

	@Override
	public void configure() {
		region = DynamicRegion.create(12850);
		setRegionBase();
		registerRegion(region.getId());
	}

}
