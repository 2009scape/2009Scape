package plugin.activity.wguild.barrel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.crandor.cache.def.impl.ObjectDefinition;
import org.crandor.game.container.impl.EquipmentContainer;
import org.crandor.game.interaction.OptionHandler;
import org.crandor.game.node.Node;
import org.crandor.game.node.entity.Entity;
import org.crandor.game.node.entity.combat.ImpactHandler.HitsplatType;
import org.crandor.game.node.entity.lock.Lock;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.item.Item;
import org.crandor.game.node.object.GameObject;
import org.crandor.game.system.task.Pulse;
import org.crandor.game.world.GameWorld;
import org.crandor.game.world.map.zone.MapZone;
import org.crandor.game.world.map.zone.ZoneBorders;
import org.crandor.game.world.map.zone.ZoneBuilder;
import org.crandor.game.world.update.flag.context.Animation;
import org.crandor.game.world.update.flag.context.Graphics;
import org.crandor.plugin.Plugin;
import org.crandor.plugin.PluginManager;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.tools.RandomFunction;

/**
 * Handles the Barrel room.
 * @author Emperor
 */
@InitializablePlugin
public final class BarrelRoom extends MapZone implements Plugin<Object> {

	/**
	 * The players list.
	 */
	private static List<Player> players = new ArrayList<>();

	/**
	 * The pulse.
	 */
	private static Pulse pulse = new Pulse(5) {
		@Override
		public boolean pulse() {
			if (players.isEmpty()) {
				return true;
			}
			for (Iterator<Player> it = players.iterator(); it.hasNext();) {
				Player player = it.next();
				player.getSettings().updateRunEnergy(5);
				if (player.getLocks().isMovementLocked()) {
					continue;
				}
				int barrels = (player.getAttribute("barrel_count", 8860) - 8859);
				int chance = (int) (player.getSettings().getRunEnergy() - (5 * barrels));
				if (RandomFunction.randomize(100) > chance) {
					removeBarrels(player);
					player.sendChat("Ouch!");
					player.getPacketDispatch().sendMessage("Some of the barrels hit you on their way to the floor.");
					player.getImpactHandler().manualHit(player, 1, HitsplatType.NORMAL);
					player.visualize(Animation.create(4177), Graphics.create(689 - barrels));
					it.remove();
					continue;
				}
				player.getSavedData().getActivityData().updateWarriorTokens(barrels);
			}
			return false;
		}
	};

	/**
	 * Constructs a new {@code BarrelRoom} {@code Object}.
	 */
	public BarrelRoom() {
		super("wg barrel", true);
	}

	@Override
	public void configure() {
		super.register(new ZoneBorders(2861, 3536, 2876, 3543));
	}

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ZoneBuilder.configure(this);
		pulse.stop();
		PluginManager.definePlugin(new OptionHandler() {
			@Override
			public Plugin<Object> newInstance(Object arg) throws Throwable {
				ObjectDefinition.forId(15668).getConfigurations().put("option:pick-up", this);
				return this;
			}

			@Override
			public boolean handle(final Player player, final Node node, String option) {
				if (player.getSettings().getRunEnergy() < 5) {
					player.getDialogueInterpreter().sendDialogue("You're too exhausted to continue. Take a break.");
					return true;
				}
				int helmId = player.getEquipment().getNew(EquipmentContainer.SLOT_HAT).getId();
				int currentBarrel = player.getAttribute("barrel_count", 0);
				if (player.getEquipment().get(EquipmentContainer.SLOT_WEAPON) != null || player.getEquipment().get(EquipmentContainer.SLOT_SHIELD) != null || player.getEquipment().get(EquipmentContainer.SLOT_HANDS) != null || helmId != currentBarrel) {
					player.getDialogueInterpreter().sendDialogue("To balance kegs you will need your head and hands free!");
					return true;
				}
				int id = currentBarrel + 1;
				if (id < 8860) {
					id = 8860;
				} else if (id > 8864) {
					id = 8864;
				}
				final int barrelId = id;
				player.lock(5);
				player.animate(Animation.create(4180));
				Lock lock = new Lock("You're too busy balancing barrels to do that!");
				lock.lock();
				player.getLocks().setEquipmentLock(lock);
				player.getPacketDispatch().sendMessage("You pick up the keg and balance it on your head carefully.");
				GameWorld.Pulser.submit(new Pulse(3, player) {
					@Override
					public boolean pulse() {
						player.getEquipment().replace(new Item(barrelId), EquipmentContainer.SLOT_HAT);
						player.getAppearance().setAnimations(Animation.create(4178));
						player.getAppearance().setStandAnimation(4179);
						player.getAppearance().sync();
						player.setAttribute("barrel_count", barrelId);
						((GameObject) node).setChildIndex(player, 1);
						if (!players.contains(player)) {
							player.getWalkingQueue().setRunDisabled(true);
							players.add(player);
							if (!pulse.isRunning()) {
								pulse.restart();
								pulse.start();
								GameWorld.Pulser.submit(pulse);
							}
						}
						return true;
					}
				});
				return true;
			}
		});
		return this;
	}

	@Override
	public Object fireEvent(String identifier, Object... args) {
		return null;
	}

	@Override
	public boolean leave(Entity e, boolean logout) {
		if (e instanceof Player && e.getAttribute("barrel_count", 0) > 0) {
			players.remove(e);
			removeBarrels((Player) e);
		}
		return super.leave(e, logout);
	}

	/**
	 * Removes the barrels from the player's head.
	 * @param player The player.
	 * @param force If the barrels are being removed due to leaving the area.
	 */
	private static void removeBarrels(Player player) {
		if (player.getLocks().getEquipmentLock() != null) {
			player.getLocks().getEquipmentLock().unlock();
		}
		player.removeAttribute("barrel_count");
		player.getWalkingQueue().setRunDisabled(false);
		player.getEquipment().replace(null, EquipmentContainer.SLOT_HAT);
		player.getAppearance().setAnimations();
		player.getAppearance().sync();
		player.getConfigManager().set(793, 0);
	}

}
