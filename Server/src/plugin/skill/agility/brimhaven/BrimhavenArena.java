package plugin.skill.agility.brimhaven;

import org.crandor.game.component.Component;
import org.crandor.game.content.global.action.ClimbActionHandler;
import org.crandor.game.interaction.Option;
import org.crandor.game.node.Node;
import org.crandor.game.node.entity.Entity;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.entity.player.link.HintIconManager;
import org.crandor.game.node.entity.player.link.diary.DiaryType;
import org.crandor.game.node.item.GroundItem;
import org.crandor.game.node.item.GroundItemManager;
import org.crandor.game.node.item.Item;
import org.crandor.game.node.object.GameObject;
import org.crandor.game.node.object.ObjectBuilder;
import org.crandor.game.system.task.MovementHook;
import org.crandor.game.system.task.Pulse;
import org.crandor.game.world.GameWorld;
import org.crandor.game.world.map.*;
import org.crandor.game.world.map.zone.MapZone;
import org.crandor.game.world.map.zone.ZoneBuilder;
import org.crandor.game.world.update.flag.context.Animation;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.plugin.Plugin;
import org.crandor.tools.RandomFunction;

import java.util.HashMap;
import java.util.Map;

/**
 * Handles the Brimhaven agility arena zone.
 * @author Emperor
 */
@InitializablePlugin
public final class BrimhavenArena extends MapZone implements Plugin<Object> {

	/**
	 * The location based traps.
	 */
	private static final Map<Location, MovementHook> LOCATION_TRAPS = new HashMap<>();

	/**
	 * The overlay component.
	 */
	private static final Component OVERLAY = new Component(5);

	/**
	 * The ticket dispenser locations.
	 */
	private static final Location[] DISPENSERS = new Location[24];

	/**
	 * The agility ticket reward item.
	 */
	private static final Item TICKET = new Item(2996);

	/**
	 * If the saw blade is currently active.
	 */
	public static boolean sawBladeActive;

	/**
	 * Constructs a new {@code BrimhavenArena} {@code Object}.
	 */
	public BrimhavenArena() {
		super("Brimhaven agility arena", true);
	}

	/**
	 * Sets the current ticket dispenser target.
	 * @param player The player.
	 */
	private void setDispenser(Player player) {
		if (!player.getAttribute("brim-tagged", false)) {
			player.setAttribute("brim-tagcount", 0);
		}
		player.setAttribute("brim-tagged", false);
		player.getConfigManager().set(309, 0);
		int index = RandomFunction.randomize(DISPENSERS.length);
		if (index == player.getAttribute("brim-tag", -1)) {
			index = (index + 1) % DISPENSERS.length;
		}
		int iconSlot = player.getAttribute("brim-icon", -1);
		if (iconSlot > -1) {
			HintIconManager.removeHintIcon(player, iconSlot);
		}
		player.setAttribute("brim-tag", index);
		player.setAttribute("brim-icon", HintIconManager.registerHeightHintIcon(player, DISPENSERS[index], 50));
	}

	@Override
	public boolean enter(Entity entity) {
		if (entity instanceof Player) {
			Player player = (Player) entity;
			player.getInterfaceManager().openOverlay(OVERLAY);
			setDispenser(player);
		}
		return super.enter(entity);
	}

	@Override
	public boolean leave(Entity entity, boolean logout) {
		if (entity instanceof Player && !logout) {
			Player player = (Player) entity;
			player.getInterfaceManager().closeOverlay();
			player.removeAttribute("brim-tag");
			player.removeAttribute("brim-tagcount");
			int iconSlot = player.getAttribute("brim-icon", -1);
			if (iconSlot > -1) {
				HintIconManager.removeHintIcon(player, iconSlot);
				player.removeAttribute("brim-icon");
			}
		}
		return super.enter(entity);
	}

	@Override
	public boolean interact(Entity entity, Node node, Option option) {
		if (node instanceof GameObject) {
			GameObject object = (GameObject) node;
			if (object.getId() == 3610) {
				ClimbActionHandler.climb((Player) entity, Animation.create(828), object.getLocation().transform(0, 0, 3));
				return true;
			}
			if (object.getId() == 3608 || object.getId() == 3581) {
				Player player = (Player) entity;
				if (!object.getLocation().equals(DISPENSERS[player.getAttribute("brim-tag", 0)])) {
					player.getPacketDispatch().sendMessage("Tag the pillar indicated by the yellow arrow to get an Agility ticket.");
					return true;
				}
				if (player.getAttribute("brim-tagged", false)) {
					player.getPacketDispatch().sendMessage("You have already tagged this pillar.");
					return true;
				}
				player.setAttribute("brim-tagged", true);
				int tags = player.getAttribute("brim-tagcount", 0) + 1;
				player.setAttribute("brim-tagcount", tags);
				player.getConfigManager().set(309, 4);
				if (tags > 1) {
				    int amount =  1;
				    	if (!player.getInventory().add(new Item(TICKET.getId(), amount))) {
				    		GroundItemManager.create(new GroundItem(new Item(TICKET.getId(), amount), player.getLocation(), player));
				    	}
				    	if (!player.getAchievementDiaryManager().getDiary(DiaryType.KARAMJA).isComplete(1, 0)) {
				    		player.getAchievementDiaryManager().getDiary(DiaryType.KARAMJA).updateTask(player, 1, 0, true);
				    	}
				    player.getDialogueInterpreter().sendItemMessage(TICKET.getId(), "You have received an Agility Arena Ticket!");
				    player.getPacketDispatch().sendMessage("You have received an Agility Arena Ticket!");
				    return true;
				}
				player.getPacketDispatch().sendMessage("You get tickets by tagging more than one pillar in a row, tag the next pillar!");
				player.getDialogueInterpreter().sendDialogue("You get tickets by tagging more than one pillar in a row. <col=C04000>Tag the", "<col=C04000>next pillar for a ticket!</col>");
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean move(Entity e, Location loc, Location dest) {
		if (!e.getLocks().isMovementLocked() && e instanceof Player) {
			MovementHook hook = LOCATION_TRAPS.get(loc);
			if (hook != null) {
				e.setDirection(Direction.getLogicalDirection(loc, dest));
				return hook.handle(e, loc);
			}
		}
		return super.move(e, loc, dest);
	}

	@Override
	public void locationUpdate(Entity e, Location last) {
		if (!e.getLocks().isMovementLocked() && e instanceof Player) {
			MovementHook hook = LOCATION_TRAPS.get(e.getLocation());
			if (hook != null) {
				if (!hook.handle(e, e.getLocation())) {
					if (e.getPulseManager().isMovingPulse()) {
						e.getPulseManager().clear();
					}
					e.getWalkingQueue().reset();
				}
			}
		}
	}

	@Override
	public void configure() {
		registerRegion(11157);
		PressurePad pad = new PressurePad();
		LOCATION_TRAPS.put(Location.create(2800, 9579, 3), pad);
		LOCATION_TRAPS.put(Location.create(2799, 9579, 3), pad);
		LOCATION_TRAPS.put(Location.create(2800, 9557, 3), pad);
		LOCATION_TRAPS.put(Location.create(2799, 9557, 3), pad);
		LOCATION_TRAPS.put(Location.create(2772, 9585, 3), pad);
		LOCATION_TRAPS.put(Location.create(2772, 9584, 3), pad);
		FloorSpikes spikes = new FloorSpikes();
		LOCATION_TRAPS.put(Location.create(2800, 9568, 3), spikes);
		LOCATION_TRAPS.put(Location.create(2799, 9568, 3), spikes);
		LOCATION_TRAPS.put(Location.create(2772, 9552, 3), spikes);
		LOCATION_TRAPS.put(Location.create(2772, 9551, 3), spikes);
		LOCATION_TRAPS.put(Location.create(2761, 9573, 3), spikes);
		LOCATION_TRAPS.put(Location.create(2761, 9574, 3), spikes);
		DartTrap dart = new DartTrap();
		LOCATION_TRAPS.put(Location.create(2788, 9557, 3), dart);
		LOCATION_TRAPS.put(Location.create(2789, 9557, 3), dart);
		LOCATION_TRAPS.put(Location.create(2794, 9573, 3), dart);
		LOCATION_TRAPS.put(Location.create(2794, 9574, 3), dart);
		LOCATION_TRAPS.put(Location.create(2777, 9568, 3), dart);
		LOCATION_TRAPS.put(Location.create(2778, 9568, 3), dart);
		SpinningBlades blade = new SpinningBlades();
		LOCATION_TRAPS.put(Location.create(2778, 9579, 3), blade);
		LOCATION_TRAPS.put(Location.create(2783, 9574, 3), blade);
		LOCATION_TRAPS.put(Location.create(2778, 9557, 3), blade);
		BladeTrap trap = new BladeTrap();
		LOCATION_TRAPS.put(Location.create(2788, 9579, 3), trap);
		LOCATION_TRAPS.put(Location.create(2789, 9579, 3), trap);
		LOCATION_TRAPS.put(Location.create(2783, 9551, 3), trap);
		LOCATION_TRAPS.put(Location.create(2783, 9552, 3), trap);
		LOCATION_TRAPS.put(Location.create(2761, 9584, 3), trap);
		LOCATION_TRAPS.put(Location.create(2761, 9585, 3), trap);
		int index = 0;
		for (int x = 2761; x < 2815; x += 11) {
			for (int y = 9546; y < 9600; y += 11) {
				if (x == 2805 && y == 9590) {
					continue;
				}
				DISPENSERS[index++] = Location.create(x, y, 3);
			}
		}
		GameWorld.Pulser.submit(new Pulse(1) {
			@Override
			public boolean pulse() {
				Region r = RegionManager.forId(11157);
				if (!r.isActive()) {
					return false;
				}
				if (GameWorld.getTicks() % 100 == 0) {
					for (RegionPlane plane : r.getPlanes()) {
						for (Player player : plane.getPlayers()) {
							setDispenser(player);
						}
					}
					handlePlankSwitching();
				}
				int ticks = 3;
				if (GameWorld.getTicks() % ticks == 0) {
					sawBladeActive = !sawBladeActive;
					if (sawBladeActive) {
						GameObject object = RegionManager.getObject(3, 2788, 9579);
						ObjectBuilder.replace(object, object.transform(3567, object.getRotation(), 10), ticks);
						object = RegionManager.getObject(3, 2789, 9579);
						ObjectBuilder.replace(object, object.transform(0), ticks);

						object = RegionManager.getObject(3, 2783, 9551);
						ObjectBuilder.replace(object, object.transform(3567, object.getRotation(), 10), ticks);
						object = RegionManager.getObject(3, 2783, 9552);
						ObjectBuilder.replace(object, object.transform(0), ticks);

						object = RegionManager.getObject(3, 2761, 9584);
						ObjectBuilder.replace(object, object.transform(3567, object.getRotation(), 10), ticks);
						object = RegionManager.getObject(3, 2761, 9585);
						ObjectBuilder.replace(object, object.transform(0), ticks);
					}
				}
				return false;
			}
		});
	}

	/**
	 * Switches the plank states.
	 */
	private static void handlePlankSwitching() {
		boolean[] available = new boolean[3];
		for (int i = 0; i < 1 + RandomFunction.randomize(2); i++) {
			available[RandomFunction.randomize(3)] = true;
		}
		for (int i = 0; i < 3; i++) {
			boolean avail = available[i];
			for (PlankSet set : PlankSet.values()) {
				Location l = set.entrance[i];
				for (int x = 1; x < set.exit[i].getX() - l.getX(); x++) {
					GameObject object = RegionManager.getObject(l.transform(x, 0, 0));
					ObjectBuilder.replace(object, object.transform(avail ? 3573 : 3576));
				}
				RegionManager.getObject(set.entrance[i]).setCharge(avail ? 1000 : 500);
				RegionManager.getObject(set.exit[i]).setCharge(avail ? 1000 : 500);
			}
		}
	}

	/**
	 * The plank obstacles.
	 * @author Emperor
	 */
	private static enum PlankSet {
		FIRST(new Location[] { Location.create(2797, 9591, 3), Location.create(2797, 9590, 3), Location.create(2797, 9589, 3) }, new Location[] { Location.create(2802, 9591, 3), Location.create(2802, 9590, 3), Location.create(2802, 9589, 3) }), SECOND(new Location[] { Location.create(2764, 9558, 3), Location.create(2764, 9557, 3), Location.create(2764, 9556, 3) }, new Location[] { Location.create(2769, 9558, 3), Location.create(2769, 9557, 3), Location.create(2769, 9556, 3) });
		/**
		 * Constructs a new {@code PlankSet} {@code Object}.
		 * @param entrance The entrance locations for each plank.
		 * @param exit The exit locations for each plank.
		 */
		private PlankSet(Location[] entrance, Location[] exit) {
			this.entrance = entrance;
			this.exit = exit;
		}

		/**
		 * The entrance location of the plank.
		 */
		Location[] entrance;

		/**
		 * The exit location of the plank.
		 */
		Location[] exit;
	}

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ZoneBuilder.configure(this);
		return this;
	}

	@Override
	public Object fireEvent(String identifier, Object... args) {
		return null;
	}

}
