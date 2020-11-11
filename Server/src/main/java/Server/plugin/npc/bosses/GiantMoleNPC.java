package plugin.npc.bosses;

import core.cache.def.impl.ObjectDefinition;
import core.game.component.Component;
import core.game.component.ComponentDefinition;
import core.game.component.ComponentPlugin;
import core.game.content.global.BossKillCounter;
import core.game.content.global.LightSource;
import core.game.content.global.action.DigAction;
import core.game.content.global.action.DigSpadeHandler;
import core.game.interaction.OptionHandler;
import core.game.node.Node;
import core.game.node.entity.Entity;
import core.game.node.entity.combat.BattleState;
import core.game.node.entity.combat.CombatStyle;
import core.game.node.entity.impl.Projectile;
import core.game.node.entity.impl.Animator.Priority;
import core.game.node.entity.npc.AbstractNPC;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.diary.DiaryType;
import core.game.node.item.Item;
import core.game.system.task.Pulse;
import core.game.world.GameWorld;
import core.game.world.map.Direction;
import core.game.world.map.Location;
import core.game.world.map.RegionManager;
import core.game.world.map.zone.impl.DarkZone;
import core.game.world.update.flag.context.Animation;
import core.game.world.update.flag.context.Graphics;
import core.net.packet.PacketRepository;
import core.net.packet.context.InterfaceContext;
import core.net.packet.out.Interface;
import core.plugin.Plugin;
import core.plugin.InitializablePlugin;
import core.tools.RandomFunction;

/**
 * Handles the Giant Mole NPC.
 * @author Emperor
 */
@InitializablePlugin
public final class GiantMoleNPC extends AbstractNPC {

	/**
	 * The dig locations.
	 */
	private static final Location[] DIG_LOCATIONS = new Location[] { Location.create(1760, 5183, 0),// center
			Location.create(1736, 5223, 0),// top left
			Location.create(1777, 5235, 0),// top right
			Location.create(1739, 5150, 0), Location.create(1769, 5148, 0), Location.create(1750, 5195, 0), Location.create(1778, 5207, 0), Location.create(1772, 5199, 0), Location.create(1774, 5173, 0), Location.create(1760, 5162, 0), Location.create(1753, 5151, 0), Location.create(1739, 5152, 0) };

	/**
	 * The digging animation.
	 */
	private static final Animation DIG_ANIMATION = new Animation(3314, Priority.VERY_HIGH);

	/**
	 * The digging graphic.
	 */
	private static final Graphics DIG_GRAPHIC = new Graphics(572);

	/**
	 * The dig up animation.
	 */
	private static final Animation DIG_UP_ANIMATION = new Animation(3315, Priority.VERY_HIGH);

	/**
	 * The digging graphic.
	 */
	private static final Graphics DIG_UP_GRAPHIC = new Graphics(573);

	/**
	 * The dust graphics.
	 */
	private static final Graphics DUST_GRAPHIC = new Graphics(571);

	/**
	 * If the NPC is digging.
	 */
	private boolean digging;

	/**
	 * Constructs a new {@code GiantMoleNPC} {@code Object}.
	 */
	public GiantMoleNPC() {
		super(3340, null);
	}

	/**
	 * Constructs a new {@code GiantMoleNPC} {@code Object}.
	 * @param id The NPC id.
	 * @param location The location.
	 */
	public GiantMoleNPC(int id, Location location) {
		super(id, location);
	}

	@Override
	public void init() {
		super.init();
		super.setWalks(true);
		super.walkRadius = 128;
	}

	/**
	 * Starts digging.
	 */
	private void dig() {
		digging = true;
		lock(5);
		getProperties().getCombatPulse().stop();
		getWalkingQueue().reset();
		getLocks().lockMovement(5);
		final Direction dir = Direction.get(RandomFunction.randomize(4));
		faceLocation(getCenterLocation().transform(dir.getStepX() << 2, dir.getStepY() << 2, 0));
		setDirection(dir);
		int index = RandomFunction.randomize(DIG_LOCATIONS.length);
		Location dest = DIG_LOCATIONS[index];
		if (dest.withinDistance(getLocation())) {
			dest = DIG_LOCATIONS[(index + 1) % DIG_LOCATIONS.length];
		}
		final Location destination = dest;
		GameWorld.getPulser().submit(new Pulse(1, this) {
			int count = 0;
			Location hole;

			@Override
			public boolean pulse() {
				if (count == 0) {
					hole = visualizeDig(destination, true);
				} else if (count == 1) {
					if (RandomFunction.RANDOM.nextBoolean()) {
						splatterMud(hole);
					}
				} else if (count == 3) {
					getProperties().setTeleportLocation(destination);
				} else if (count == 4) {
					visualizeDig(destination, false);
					digging = false;
					return true;
				}
				count++;
				return false;
			}
		});
	}

	/**
	 * Handles the mud splattering.
	 * @param hole The hole location.
	 */
	private void splatterMud(Location hole) {
		for (Player p : RegionManager.getLocalPlayers(getCenterLocation(), (size() >> 1) + 2)) {
			PacketRepository.send(Interface.class, new InterfaceContext(p, 548, 77, 226, true));
			LightSource s = LightSource.getActiveLightSource(p);
			if (s == null || s.isOpen()) {
				if (s != null) {
					p.getPacketDispatch().sendMessage("Your " + s.getName() + " seems to have been extinguished by the mud.");
					int slot = p.getInventory().getSlot(s.getProduct());
					if (slot > -1) {
						p.getInventory().replace(new Item(s.getRaw().getId()), slot);
					}
				}
				DarkZone.checkDarkArea(p);
			}
		}
		for (int i = 0; i < 1 + RandomFunction.randomize(3); i++) {
			Projectile.create(hole, hole.transform(-4 + RandomFunction.randomize(9), -4 + RandomFunction.randomize(9), 0), 570, 0, 5, 45, 70, 5, 11).send();
		}
	}

	/**
	 * Visualizes the digging.
	 * @param destination The destination of the mole.
	 * @param underground If the mole is going underground.
	 * @return The hole location.
	 */
	private Location visualizeDig(Location destination, boolean underground) {
		Location offset = getCenterLocation();
		if (underground) {
			switch (getDirection()) {
			case NORTH:
				offset = getLocation().transform(1, size() - 1, 0);
				break;
			case EAST:
				offset = getLocation().transform(size() - 1, 1, 0);
				break;
			case WEST:
				offset = getLocation().transform(0, 1, 0);
				break;
			default:
				offset = getLocation().transform(1, 0, 0);
				break;
			}
		}
		for (int x = -1; x < 2; x++) {
			for (int y = -1; y < 2; y++) {
				Graphics.send(DUST_GRAPHIC, offset.transform(x, y, 0));
			}
		}
		if (underground) {
			animate(DIG_ANIMATION);
			Graphics.send(DIG_GRAPHIC, offset);
		} else {
			animate(DIG_UP_ANIMATION);
			Graphics.send(DIG_UP_GRAPHIC, offset);
		}
		return offset;
	}

	@Override
	public void onImpact(final Entity entity, BattleState state) {
		if (!getLocks().isInteractionLocked()) {
			int chance = 15;
			if (getSkills().getLifepoints() < getSkills().getMaximumLifepoints() >> 1) {
				chance *= 3;
			}
			if (RandomFunction.randomize(100) < chance && inCombat()) {
				dig();
				return;
			}
		}
		super.onImpact(entity, state);
	}

	@Override
	public boolean isAttackable(Entity entity, CombatStyle style) {
		if (digging) {
			return false;
		}
		return super.isAttackable(entity, style);
	}

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ComponentDefinition.put(568, new ComponentPlugin() {
			@Override
			public Plugin<Object> newInstance(Object arg) throws Throwable {
				return this;
			}

			@Override
			public boolean handle(Player player, Component component, int opcode, int button, int slot, int itemId) {
				if (button == 17) {
					player.getProperties().setTeleportLocation(Location.create(1752, 5237, 0));
					player.getPacketDispatch().sendMessage("You seem to have dropped down into a network of mole tunnels.");

					if (!player.getAchievementDiaryManager().getDiary(DiaryType.FALADOR).isComplete(0, 5)) {
						player.getAchievementDiaryManager().getDiary(DiaryType.FALADOR).updateTask(player,0,5,true);
					}
				}
				player.getInterfaceManager().close();
				return false;
			}
		});

		DigAction action = new DigAction() {
			@Override
			public void run(Player player) {
				if (!LightSource.hasActiveLightSource(player)) {
					player.getPacketDispatch().sendMessage("It's going to be dark down there, I should bring a light source.");
					return;
				}
				player.getInterfaceManager().open(new Component(568));
			}
		};
		DigSpadeHandler.register(Location.create(3005, 3376, 0), action);
		DigSpadeHandler.register(Location.create(2999, 3375, 0), action);
		DigSpadeHandler.register(Location.create(2996, 3377, 0), action);
		DigSpadeHandler.register(Location.create(2989, 3378, 0), action);
		DigSpadeHandler.register(Location.create(2984, 3387, 0), action);
		DigSpadeHandler.register(Location.create(2987, 3387, 0), action);
		ObjectDefinition.forId(12230).getHandlers().put("option:climb", new OptionHandler() {
			@Override
			public Plugin<Object> newInstance(Object arg) throws Throwable {
				return this;
			}

			@Override
			public boolean handle(final Player player, Node node, String option) {
				player.animate(Animation.create(828));
				player.lock(2);
				GameWorld.getPulser().submit(new Pulse(1, player) {
					@Override
					public boolean pulse() {
						player.getProperties().setTeleportLocation(Location.create(2985, 3316, 0));
						return true;
					}
				});
				return true;
			}
		});
		return super.newInstance(arg);
	}

	@Override
	public void finalizeDeath(Entity killer) {
		super.finalizeDeath(killer);
		if (killer instanceof Player) {
			Player player = killer.asPlayer();
			BossKillCounter.addtoKillcount(player, this.getId());
		}
	}

	@Override
	public AbstractNPC construct(int id, Location location, Object... objects) {
		return new GiantMoleNPC(id, location);
	}

	@Override
	public int[] getIds() {
		return new int[] { 3340 };
	}

}
