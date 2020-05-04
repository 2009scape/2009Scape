package plugin.activity.gnomecopter;

import org.crandor.game.container.impl.EquipmentContainer;
import org.crandor.game.content.activity.ActivityPlugin;
import org.crandor.game.interaction.Option;
import org.crandor.game.node.Node;
import org.crandor.game.node.entity.Entity;
import org.crandor.game.node.entity.impl.ForceMovement;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.item.Item;
import org.crandor.game.node.object.GameObject;
import org.crandor.game.node.object.ObjectBuilder;
import org.crandor.game.system.task.Pulse;
import org.crandor.game.world.GameWorld;
import org.crandor.game.world.map.Direction;
import org.crandor.game.world.map.Location;
import org.crandor.game.world.map.zone.ZoneBorders;
import org.crandor.game.world.update.flag.context.Animation;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.world.update.flag.context.Graphics;

/**
 * Handles the gnome copter activity.
 * @author Emperor
 */
@InitializablePlugin
public final class GnomeCopterActivity extends ActivityPlugin {

	/**
	 * The gnome copter item.
	 */
	private static final Item COPTER_ITEM = new Item(12842);

	/**
	 * The landing pads currently in use.
	 */
	private final boolean[] usedLandingPads = new boolean[4];

	/**
	 * Constructs a new {@code GnomeCopterActivity} {@code Object}.
	 */
	public GnomeCopterActivity() {
		super("Gnome copters", false, false, true);
	}

	@Override
	public ActivityPlugin newInstance(Player p) throws Throwable {
		return this;
	}

	@Override
	public boolean interact(Entity e, Node target, Option option) {
		if (target instanceof GameObject) {
			GameObject object = (GameObject) target;
			if (object.getId() == 30032) {
				flyGnomeCopter((Player) e, object);
				return true;
			}
			if (object.getId() == 30036) {
				GCInformationSign.ENTRANCE.read((Player) e);
				return true;
			}
		} else if (target instanceof Item && e.getAttribute("gc:flying", false)) {
			((Player) e).getPacketDispatch().sendMessage("You can't do this right now.");
			return true;
		}
		return false;
	}

	@Override
	public boolean leave(Entity e, boolean logout) {
		if (logout && e.getAttribute("gc:flying", false)) {
			e.setLocation(getSpawnLocation());
			((Player) e).getEquipment().remove(COPTER_ITEM);
		}
		return super.leave(e, logout);
	}

	/**
	 * Starts flying the gnome copter.
	 * @param player The player.
	 * @param object The object.
	 */
	private void flyGnomeCopter(final Player player, final GameObject object) {
		if (!player.getLocation().equals(object.getLocation().transform(0, 1, 0))) {
			return;
		}
		if (object.getCharge() == 88) {
			player.getPacketDispatch().sendMessage("Someone else is already using this gnomecopter.");
			return;
		}
		if (player.getEquipment().get(EquipmentContainer.SLOT_HAT) != null) {
			player.getPacketDispatch().sendMessage("You can't wear a hat on a Gnomecopter.");
			return;
		}
		if (player.getEquipment().get(EquipmentContainer.SLOT_CAPE) != null) {
			player.getPacketDispatch().sendMessage("You can't wear a cape on a Gnomecopter.");
			return;
		}
		if (player.getEquipment().get(3) != null || player.getEquipment().get(5) != null) {
			player.getPacketDispatch().sendMessage("You need to have your hands free to use this.");
			return;
		}
		player.setAttribute("gc:flying", true);
		player.lock();
		player.faceLocation(player.getLocation().transform(0, 3, 0));
		object.setCharge(88);
		GameWorld.Pulser.submit(new Pulse(1, player) {
			int stage = 0;

			@Override
			public boolean pulse() {
				if (++stage == 1) {
					player.getInterfaceManager().hideTabs(0, 1, 2, 3, 4, 5, 6, 7, 11);
					ForceMovement.run(player, player.getLocation(), object.getLocation(), ForceMovement.WALK_ANIMATION, new Animation(8955), Direction.NORTH, 8);
					player.lock();
				} else if (stage == 3) {
					player.getEquipment().replace(COPTER_ITEM, 3);
					player.visualize(Animation.create(8956), Graphics.create(1578));
					player.getAppearance().setStandAnimation(8964);
					player.getAppearance().setWalkAnimation(8961);
					player.getAppearance().setRunAnimation(8963);
					player.getAppearance().setTurn180(8963);
					player.getAppearance().setTurn90ccw(8963);
					player.getAppearance().setTurn90cw(8963);
					player.getAppearance().setStandTurnAnimation(8963);
				} else if (stage == 4) {
					object.setCharge(88);
					player.getPacketDispatch().sendObjectAnimation(object, new Animation(5));
				} else if (stage == 16) {
					player.getWalkingQueue().reset();
					player.getWalkingQueue().addPath(object.getLocation().getX(), object.getLocation().getY() + 16, true);
					Graphics.send(Graphics.create(1579), object.getLocation());
				} else if (stage == 20) {
					object.setCharge(1000);
					player.getPacketDispatch().sendObjectAnimation(object, new Animation(8941));
				} else if (stage == 33) {
					player.unlock();
					landGnomeCopter(player);
					return true;
				}
				return false;
			}
		});
	}

	/**
	 * Lands the gnomecopter.
	 * @param player The player.
	 * @param object The object.
	 */
	private void landGnomeCopter(final Player player) {
		int index = 0;
		for (index = 0; index < usedLandingPads.length; index++) {
			if (!usedLandingPads[index]) {
				break;
			}
		}
		usedLandingPads[index] = true;
		player.lock();
		final int pad = index;
		player.setDirection(Direction.SOUTH);
		player.getProperties().setTeleportLocation(Location.create(3162, 3352, 0));
		GameWorld.Pulser.submit(new Pulse(1, player) {
			int stage = 0;
			int tick = 0;

			@Override
			public boolean pulse() {
				if (++stage == 1) {
					player.getWalkingQueue().reset();
					player.getWalkingQueue().addPath(3162, 3348, true);
					player.getWalkingQueue().addPath(3161 - (pad << 1), 3336, true);
					tick = stage + player.getWalkingQueue().getQueue().size();
				} else if (stage == tick) {
					player.animate(Animation.create(8957));
				} else if (stage == tick + 14) {
					ObjectBuilder.add(new GameObject(30034, player.getLocation()), 6);
					player.getEquipment().replace(null, 3);
					ForceMovement.run(player, player.getLocation(), player.getLocation().transform(0, -1, 0), new Animation(8959), 8);
					player.lock(2);
				} else if (stage == tick + 15) {
					player.unlock();
					player.getInterfaceManager().restoreTabs();
					usedLandingPads[pad] = false;
					player.removeAttribute("gc:flying");
					return true;
				}
				return false;
			}
		});
	}

	@Override
	public Location getSpawnLocation() {
		return Location.create(3161, 3337, 0);
	}

	@Override
	public void configure() {
		register(new ZoneBorders(3154, 3330, 3171, 3353));
	}

}
