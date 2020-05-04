package plugin.activity.barrows;

import org.crandor.game.component.Component;
import org.crandor.game.content.activity.ActivityPlugin;
import org.crandor.game.content.global.action.ClimbActionHandler;
import org.crandor.game.content.global.action.DoorActionHandler;
import org.crandor.game.content.skill.member.summoning.familiar.Familiar;
import org.crandor.game.interaction.Option;
import org.crandor.game.node.Node;
import org.crandor.game.node.entity.Entity;
import org.crandor.game.node.entity.combat.CombatStyle;
import org.crandor.game.node.entity.combat.DeathTask;
import org.crandor.game.node.entity.combat.ImpactHandler.HitsplatType;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.game.node.entity.npc.agg.AggressiveBehavior;
import org.crandor.game.node.entity.npc.agg.AggressiveHandler;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.entity.player.info.portal.Perks;
import org.crandor.game.node.entity.player.link.ActivityData;
import org.crandor.game.node.object.GameObject;
import org.crandor.game.system.task.Pulse;
import org.crandor.game.world.GameWorld;
import org.crandor.game.world.map.Location;
import org.crandor.game.world.map.RegionManager;
import org.crandor.game.world.map.zone.ZoneBorders;
import org.crandor.game.world.update.flag.context.Graphics;
import org.crandor.net.packet.PacketRepository;
import org.crandor.net.packet.context.CameraContext;
import org.crandor.net.packet.context.MinimapStateContext;
import org.crandor.net.packet.context.CameraContext.CameraType;
import org.crandor.net.packet.out.CameraViewPacket;
import org.crandor.net.packet.out.MinimapState;
import org.crandor.plugin.PluginManager;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.tools.RandomFunction;

/**
 * Handles the barrows activity plugin.
 * @author Emperor
 */
@InitializablePlugin
public final class BarrowsActivityPlugin extends ActivityPlugin {

	/**
	 * The tunnel configuration values.
	 */
	private static final int[] TUNNEL_CONFIGS = { 55328769, 2867201, 44582944, 817160, 537688072, 40763408, 44320784, 23478274 };
	
	/**
	 * Represents the tunnels between 2 rooms in the barrows tunnels.
	 */
	public static final ZoneBorders[] MINI_TUNNELS = {
		new ZoneBorders(3532, 9665, 3570, 9671),
		new ZoneBorders(3575, 9676, 3570, 9671),
		new ZoneBorders(3575, 9676, 3581, 9714), 
		new ZoneBorders(3534, 9718, 3570, 9723), 
		new ZoneBorders(3523, 9675, 3528, 9712), 
		new ZoneBorders(3541, 9711, 3545, 9712), 
		new ZoneBorders(3558, 9711, 3562, 9712), 
		new ZoneBorders(3568, 9701, 3569, 9705), 
		new ZoneBorders(3551, 9701, 3552, 9705), 
		new ZoneBorders(3534, 9701, 3535, 9705), 
		new ZoneBorders(3541, 9694, 3545, 9695), 
		new ZoneBorders(3558, 9694, 3562, 9695), 
		new ZoneBorders(3568, 9684, 3569, 9688), 
		new ZoneBorders(3551, 9684, 3552, 9688), 
		new ZoneBorders(3534, 9684, 3535, 9688), 
		new ZoneBorders(3541, 9677, 3545, 9678), 
		new ZoneBorders(3558, 9677, 3562, 9678),
	};
	
	/**
	 * The overlay.
	 */
	private static final Component OVERLAY = new Component(24);

	/**
	 * The activity handling pulse.
	 */
	private static final Pulse PULSE = new Pulse(3) {
		@Override
		public boolean pulse() {
			boolean end = true;
			for (Player p : RegionManager.getRegionPlayers(14231)) {
				end = false;
				int index = p.getAttribute("barrow:drain-index", -1);
				if (index > -1) {
					p.removeAttribute("barrow:drain-index");
					p.getPacketDispatch().sendItemOnInterface(-1, 1, 24, index);
					continue;
				}
				if (p.getLocation().getZ() == 0 && p.getAttribute("barrow:looted", false)) {
					if (RandomFunction.random(15) == 0) {
						p.getImpactHandler().manualHit(p, RandomFunction.random(5), HitsplatType.NORMAL);
						Graphics.send(Graphics.create(405), p.getLocation());
					}
				}
				if (p.getLocks().isLocked("barrow:drain")) {
					continue;
				}
				int drain = 8;
				for (boolean killed : p.getSavedData().getActivityData().getBarrowBrothers()) {
					if (killed) {
						drain += 1;
					}
				}
				if (p.hasPerk(Perks.BARROWS_BEFRIENDER)) {
					drain /= 2;
				}
				p.getSkills().decrementPrayerPoints(drain);
				p.getLocks().lock("barrow:drain", (3 + RandomFunction.random(15)) * 3);
				index = 1 + RandomFunction.random(6);
				p.setAttribute("barrow:drain-index", index);
				p.getPacketDispatch().sendItemZoomOnInterface(4761 + RandomFunction.random(12), 100, 24, index);
				p.getPacketDispatch().sendAnimationInterface(9810, 24, index);
			}
			return end;
		}
	};

	/**
	 * Constructs a new {@code BarrowsActivityPlugin} {@code Object}.
	 */
	public BarrowsActivityPlugin() {
		super("Barrows", false, false, false);
	}

	@Override
	public void locationUpdate(Entity e, Location last) {
		if (e instanceof Player && e.getViewport().getRegion().getId() == 14231) {
			boolean tunnel = false;
			for (ZoneBorders border : MINI_TUNNELS) {
				if (border.insideBorder(e)) {
					tunnel = true;
					break;
				}
			}
			Player player = (Player) e;
			if ((player.getConfigManager().get(1270) == 1) != tunnel) {
				player.getConfigManager().set(1270, tunnel ? 3 : 0, true);//@emp: proper value seems to be 3, val of 1 makes corridors black
			}
		}
	}
	
	@Override
	public boolean enter(Entity e) {
		if (e instanceof Player) {
			Player player = (Player) e;
			PacketRepository.send(MinimapState.class, new MinimapStateContext(player, 2));
			player.getInterfaceManager().openOverlay(OVERLAY);
			player.getConfigManager().set(0, 1);
			if (player.getConfigManager().get(452) == 0) {
				shuffleCatacombs(player);
			}
			sendConfiguration(player);
			if (!PULSE.isRunning()) {
				PULSE.restart();
				PULSE.start();
				GameWorld.Pulser.submit(PULSE);
			}
		} else {
			((NPC) e).setAggressive(true);
			((NPC) e).setAggressiveHandler(new AggressiveHandler(e, new AggressiveBehavior() {
				@Override
				public boolean canSelectTarget(Entity entity, Entity target) {
					if (!target.isActive() || DeathTask.isDead(target)) {
						return false;
					}
					if (!target.getProperties().isMultiZone() && target.inCombat()) {
						return false;
					}
					return true;
				}
			}));
		}
		return super.enter(e);
	}

	@Override
	public boolean leave(Entity e, boolean logout) {
		if (e instanceof Player) {
			Player player = (Player) e;
			PacketRepository.send(MinimapState.class, new MinimapStateContext(player, 0));
			player.getInterfaceManager().closeOverlay();
			NPC npc = player.getAttribute("barrow:npc");
			if (npc != null && !DeathTask.isDead(npc)) {
				npc.clear();
			}
			player.removeAttribute("barrow:solvedpuzzle");
			player.removeAttribute("barrow:opened_chest");
			player.removeAttribute("crusade-delay");
			if (!logout && player.getAttribute("barrow:looted", false)) {
				for (int i = 0; i < 6; i++) {
					player.removeAttribute("brother:" + i);
					player.getSavedData().getActivityData().getBarrowBrothers()[i] = false;
				}
				player.removeAttribute("barrow:looted");
				shuffleCatacombs(player);
				player.getSavedData().getActivityData().setBarrowTunnelIndex(RandomFunction.random(6));
				player.getSavedData().getActivityData().setBarrowKills(0);
				PacketRepository.send(CameraViewPacket.class, new CameraContext(player, CameraType.RESET, 0, 0, 0, 0, 0));
			}
		}
		return super.leave(e, logout);
	}

	/**
	 * "Shuffles" the catacomb gates.
	 * @param player The player.
	 */
	public static void shuffleCatacombs(Player player) {
		int value = TUNNEL_CONFIGS[RandomFunction.random(TUNNEL_CONFIGS.length)];
		value |= 1 << (6 + RandomFunction.random(4));
		player.getConfigManager().set(452, value);
	}

	@Override
	public boolean death(Entity e, Entity killer) {
		Player player = null;
		if (killer instanceof Player) {
			player = (Player) killer;
		} else if (killer instanceof Familiar) {
			player = ((Familiar) killer).getOwner();
		}
		if (player != null && e instanceof NPC) {
			player.getSavedData().getActivityData().setBarrowKills(player.getSavedData().getActivityData().getBarrowKills() + 1);
			sendConfiguration(player);
		}
		return false;
	}

	@Override
	public boolean interact(Entity e, Node target, Option option) {
		if (target instanceof GameObject) {
			GameObject object = (GameObject) target;
			Player player = (Player) e;
			if (object.getId() >= 6702 && object.getId() <= 6707) {
				ClimbActionHandler.climb((Player) e, ClimbActionHandler.CLIMB_UP, BarrowsCrypt.getCrypt(object.getId() - 6702).getExitLocation());
				return true;
			}
			if (object.getId() >= 6708 && object.getId() <= 6712) {
				ClimbActionHandler.climb((Player) e, ClimbActionHandler.CLIMB_UP, BarrowsCrypt.getCrypt(player.getSavedData().getActivityData().getBarrowTunnelIndex()).getEnterLocation());
				return true;
			}
			switch (object.getWrapper().getId()) {
			case 6727:
			case 6724:
			case 6746:
			case 6743:
				if (player.getAttribute("barrow:solvedpuzzle", false)) {
					break;
				}
				player.setAttribute("barrow:puzzledoor", object);
				BarrowsPuzzle.open(player);
				return true;
			}
			switch (object.getId()) {
			case 6714:
			case 6733:
				int index = -1;
				for (int i = 0; i < player.getSavedData().getActivityData().getBarrowBrothers().length; i++) {
					if (!player.getSavedData().getActivityData().getBarrowBrothers()[i] && RandomFunction.random(15) == 0 && !player.getAttribute("brother:" + i, false)) {
						index = i;
						break;
					}
				}
				if (index > -1) {
					BarrowsCrypt.getCrypt(index).spawnBrother(player, RegionManager.getTeleportLocation(target.getLocation(), 1));
				}
				DoorActionHandler.handleAutowalkDoor(e, (GameObject) target);
				return true;
			case 6821:
				BarrowsCrypt.getCrypt(BarrowsCrypt.AHRIM).openSarcophagus((Player) e, object);
				return true;
			case 6771:
				BarrowsCrypt.getCrypt(BarrowsCrypt.DHAROK).openSarcophagus((Player) e, object);
				return true;
			case 6773:
				BarrowsCrypt.getCrypt(BarrowsCrypt.GUTHAN).openSarcophagus((Player) e, object);
				return true;
			case 6822:
				BarrowsCrypt.getCrypt(BarrowsCrypt.KARIL).openSarcophagus((Player) e, object);
				return true;
			case 6772:
				BarrowsCrypt.getCrypt(BarrowsCrypt.TORAG).openSarcophagus((Player) e, object);
				return true;
			case 6823:
				BarrowsCrypt.getCrypt(BarrowsCrypt.VERAC).openSarcophagus((Player) e, object);
				return true;
			case 6774:
				player.lock(1);
				for (int i = 0; i < player.getSavedData().getActivityData().getBarrowBrothers().length; i++) {
					if (!player.getSavedData().getActivityData().getBarrowBrothers()[i] && !player.getAttribute("brother:" + i, false)) {
						BarrowsCrypt.getCrypt(i).spawnBrother(player, RegionManager.getTeleportLocation(target.getCenterLocation(), 4));
					}
				}
				player.setAttribute("barrow:opened_chest", true);
				sendConfiguration(player);
				return true;
			case 6775:
				if (option.getName().equals("Close")) {
					player.removeAttribute("barrow:opened_chest");
					sendConfiguration(player);
					return true;
				}
				if (player.getAttribute("barrow:looted", false)) {
					player.getPacketDispatch().sendMessage("The chest is empty.");
					return true;
				}
				if ((!player.hasPerk(Perks.CRUSADER) || player.getAttribute("crusader", 0) > GameWorld.getTicks()) || (player.hasPerk(Perks.CRUSADER) && RandomFunction.random(100) > 25)) {
					player.setAttribute("/save:barrow:looted", true);
				} else {
					player.setAttribute("crusader", GameWorld.getTicks() + 500);
					player.sendMessage("You are given the chance for a second loot.");
				}
				RewardChest.reward(player);
				PacketRepository.send(CameraViewPacket.class, new CameraContext(player, CameraType.SHAKE, 3, 2, 2, 2, 2));
				return true;
			}
		}
		return false;
	}

	/**
	 * Sends the kill count configuration.
	 * @param player The player.
	 */
	public static void sendConfiguration(Player player) {
		ActivityData data = player.getSavedData().getActivityData();
		int config = data.getBarrowKills() << 17;
		for (int i = 0; i < data.getBarrowBrothers().length; i++) {
			if (data.getBarrowBrothers()[i]) { // This actually wasn't in 498
				// but we'll keep it anyways.
				config |= 1 << i;
			}
		}
		if (player.getAttribute("barrow:opened_chest", false)) {
			config |= 1 << 16;
		}
		player.getConfigManager().set(453, config);
	}

	@Override
	public boolean actionButton(Player player, int interfaceId, int buttonId, int slot, int itemId, int opcode) {
		return false;
	}

	@Override
	public boolean continueAttack(Entity e, Node target, CombatStyle style, boolean message) {
		if (target instanceof BarrowBrother) {
			Player p = null;
			if (e instanceof Player) {
				p = (Player) e;
			} else if (e instanceof Familiar) {
				p = ((Familiar) e).getOwner();
			}
			if (p != null && p != ((BarrowBrother) target).getPlayer()) {
				p.getPacketDispatch().sendMessage("He's not after you.");
				return false;
			}
		}
		return super.continueAttack(e, target, style, message);
	}

	@Override
	public ActivityPlugin newInstance(Player p) throws Throwable {
		return this;
	}

	@Override
	public Location getSpawnLocation() {
		return null;
	}

	@Override
	public void configure() {
		BarrowsEquipment.init();
		PluginManager.definePlugin(new TunnelEntranceDialogue());
		PluginManager.definePlugin(BarrowsPuzzle.SHAPES);
		registerRegion(14231);
		BarrowsCrypt.init();
		PULSE.stop();
	}

}
