package org.crandor.game.content.skill.member.summoning.familiar;

import org.crandor.game.node.entity.player.Player;
import org.crandor.game.system.task.Pulse;
import org.crandor.game.world.GameWorld;
import org.crandor.game.world.map.Direction;
import org.crandor.game.world.map.Location;
import org.crandor.game.world.update.flag.context.Animation;
import org.crandor.net.packet.PacketRepository;
import org.crandor.net.packet.context.CameraContext;
import org.crandor.net.packet.context.CameraContext.CameraType;
import org.crandor.net.packet.out.CameraViewPacket;

/**
 * Handles the remote viewing of an area.
 * @author Vexia
 */
public final class RemoteViewer {

	/**
	 * The dialogue name to use.
	 */
	public static final String DIALOGUE_NAME = "remote-view";

	/**
	 * The remote camera height.
	 */
	public static final int HEIGHT = 1000;

	/**
	 * The player.
	 */
	private final Player player;

	/**
	 * The familiar.
	 */
	private final Familiar familiar;

	/**
	 * The animation to start viewing.
	 */
	private final Animation animation;

	/**
	 * The view type.
	 */
	private final ViewType type;

	/**
	 * Constructs a new {@code RemoteViewer} {@code Object}.
	 * @param player the player.
	 * @param familiar the familiar.
	 * @param animation the animation.
	 * @param type the type.
	 */
	public RemoteViewer(Player player, Familiar familiar, Animation animation, ViewType type) {
		this.player = player;
		this.familiar = familiar;
		this.animation = animation;
		this.type = type;
	}

	/**
	 * Creates a remote view object.
	 * @param player the player.
	 * @param familiar the familiar.
	 * @param animation the animation.
	 * @param type
	 * @return
	 */
	public static RemoteViewer create(final Player player, Familiar familiar, Animation animation, ViewType type) {
		return new RemoteViewer(player, familiar, animation, type);
	}

	/**
	 * Starts viewing an area.
	 * @param player the player.
	 * @param type the type.
	 */
	public void startViewing() {
		player.lock();
		familiar.animate(animation);
		player.getPacketDispatch().sendMessage("You send the " + familiar.getName().toLowerCase() + " to fly " + (type == ViewType.STRAIGHT_UP ? "directly up" : "to the " + type.name().toLowerCase()) + "...");
		GameWorld.Pulser.submit(new Pulse(5) {
			@Override
			public boolean pulse() {
				view();
				return true;
			}
		});
	}

	/**
	 * Views the area from the view type.
	 * @param player the player.
	 * @param familiar the familiar.
	 * @param type the type.
	 */
	private void view() {
		if (!canView()) {
			return;
		}
		sendCamera(type.getXOffset(), type.getYOffset(), type.getXRot(), type.getYRot());
		GameWorld.Pulser.submit(new Pulse(13) {
			@Override
			public boolean pulse() {
				reset();
				return true;
			}
		});
	}

	/**
	 * Checks if a remote view can start.
	 * @return {@code True} if so.
	 */
	private boolean canView() {
		// player.getPacketDispatch().sendMessage("There seems to be an obstruction in the direction; the familiar cannot fly there");
		if (!familiar.isActive()) {
			return false;
		}
		return true;
	}

	/**
	 * Resets the camera packet.
	 */
	private void reset() {
		familiar.call();
		player.unlock();
		PacketRepository.send(CameraViewPacket.class, new CameraContext(player, CameraType.RESET, 0, 0, HEIGHT, 1, 100));
	}

	/**
	 * Sends a camera packet.
	 * @param xRot the xRot.
	 * @param yRot the yRot.
	 */
	private void sendCamera(int xOffset, int yOffset, final int xRot, final int yRot) {
		final Location location = type.getLocationTransform(player);
		final int x = location.getX() + xOffset;
		final int y = location.getY() + yOffset;
		PacketRepository.send(CameraViewPacket.class, new CameraContext(player, CameraType.POSITION, x, y, HEIGHT, 1, 100));
		PacketRepository.send(CameraViewPacket.class, new CameraContext(player, CameraType.ROTATION, x + xRot, y + yRot, HEIGHT, 1, 90));
	}

	/**
	 * Opens the remote viewing dialogue.
	 * @param player the player.
	 * @param familiar the familiar.
	 */
	public static void openDialogue(final Player player, final Familiar familiar) {
		player.getDialogueInterpreter().open(DIALOGUE_NAME, familiar);
	}

	/**
	 * Gets the player.
	 * @return The player.
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * Gets the familiar.
	 * @return The familiar.
	 */
	public Familiar getFamiliar() {
		return familiar;
	}

	/**
	 * Gets the animation.
	 * @return The animation.
	 */
	public Animation getAnimation() {
		return animation;
	}

	/**
	 * Gets the type.
	 * @return The type.
	 */
	public ViewType getType() {
		return type;
	}

	/**
	 * A view type.
	 * @author Vexia
	 */
	public enum ViewType {
		NORTH(Direction.NORTH, 0, 0, 0, 0), EAST(Direction.WEST, 0, 0, 0, 0), SOUTH(Direction.SOUTH, 0, 0, 0, 0), WEST(Direction.EAST, 0, 0, 0, 0), STRAIGHT_UP(null, 0, 0, 0, 0);

		/**
		 * The direction.
		 */
		private final Direction direction;

		/**
		 * The data for the camera.
		 */
		private final int[] data;

		/**
		 * Constructs a new {@code ViewType} {@code Object}.
		 * @param data the data.
		 */
		private ViewType(Direction direction, int... data) {
			this.direction = direction;
			this.data = data;
		}

		/**
		 * Gets the transformed location.
		 * @param player the player.
		 * @return the location.
		 */
		public Location getLocationTransform(final Player player) {
			if (this == STRAIGHT_UP) {
				return player.getLocation();
			}
			return player.getLocation().transform(direction, 10);
		}

		/**
		 * Gets the direction.
		 * @return The direction.
		 */
		public Direction getDirection() {
			return direction;
		}

		/**
		 * Gets the x offset.
		 * @return the offset.
		 */
		public int getXOffset() {
			return data[0];
		}

		/**
		 * Gets the y offset.
		 * @return the offset.
		 */
		public int getYOffset() {
			return data[1];
		}

		/**
		 * Gets the x rotation.
		 * @return the rotation.
		 */
		public int getXRot() {
			return data[2];
		}

		/**
		 * Gets the y rot.
		 * @return the rot.
		 */
		public int getYRot() {
			return data[3];
		}

		/**
		 * Gets the data.
		 * @return The data.
		 */
		public int[] getData() {
			return data;
		}

	}
}
