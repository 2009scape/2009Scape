package plugin.activity.bountyhunter;

import org.crandor.game.component.Component;
import org.crandor.game.component.ComponentDefinition;
import org.crandor.game.component.ComponentPlugin;
import org.crandor.game.container.Container;
import org.crandor.game.container.ContainerEvent;
import org.crandor.game.container.ContainerListener;
import org.crandor.game.content.activity.ActivityManager;
import org.crandor.game.content.activity.ActivityPlugin;
import org.crandor.game.interaction.Option;
import org.crandor.game.node.Node;
import org.crandor.game.node.entity.Entity;
import org.crandor.game.node.entity.combat.CombatStyle;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.entity.player.link.HintIconManager;
import org.crandor.game.node.entity.player.link.prayer.PrayerType;
import org.crandor.game.node.item.GroundItem;
import org.crandor.game.node.item.Item;
import org.crandor.game.system.task.Pulse;
import org.crandor.game.world.GameWorld;
import org.crandor.game.world.map.Location;
import org.crandor.game.world.map.Point;
import org.crandor.game.world.map.zone.ZoneBorders;
import org.crandor.game.world.map.zone.ZoneRestriction;
import org.crandor.game.world.map.zone.impl.MultiwayCombatZone;
import org.crandor.game.world.map.zone.impl.WildernessZone;
import org.crandor.game.world.update.flag.context.Animation;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.plugin.Plugin;
import org.crandor.plugin.PluginManager;
import org.crandor.tools.RandomFunction;

import java.util.*;

/**
 * Handles the Bounty hunter activity.
 * @author Emperor
 */
@InitializablePlugin
public final class BountyHunterActivity extends ActivityPlugin {

	/**
	 * The skull values.
	 */
	private static final int[] SKULL_VALUES = { 100_000, // Bronze skull
			500_000, // Iron skull
			1_100_000, // Adamant skull
			2_500_000, // Runite skull
			-1 // Dragon skull
	};

	/**
	 * The exit offsets.
	 */
	private static final Point[] EXIT_OFFSETS = { new Point(19, 58), new Point(24, 44), new Point(29, 34), new Point(36, 22), new Point(52, 17), new Point(14, 72), new Point(15, 85), new Point(17, 127), new Point(14, 133), new Point(19, 151), new Point(38, 163), new Point(49, 170), new Point(64, 20), new Point(84, 16), new Point(106, 18), new Point(69, 176), new Point(85, 176), new Point(127, 174), new Point(130, 21), new Point(138, 33), new Point(155, 48), new Point(163, 53), new Point(172, 60), new Point(174, 64), new Point(176, 106), new Point(178, 84), new Point(155, 169), new Point(162, 162), new Point(163, 153), new Point(173, 136) };

	/**
	 * The minimum amount of players to enter the crater.
	 */
	private static final int MINIMUM_PLAYERS = 2;

	/**
	 * The waiting room overlay.
	 */
	private static final Component WAITING_OVERLAY = new Component(656);

	/**
	 * The game overlay.
	 */
	private static final Component GAME_OVERLAY = new Component(653);

	/**
	 * The player in the current crater.
	 */
	final Map<Player, BountyEntry> players = new HashMap<>();

	/**
	 * The waiting room.
	 */
	private final List<Player> waitingRoom = new ArrayList<>();

	/**
	 * The crater type.
	 */
	private final CraterType type;

	/**
	 * The amount of time to wait in the waiting room.
	 */
	private int waitingTime = 166;

	/**
	 * The waiting room pulse.
	 */
	private final Pulse waitRoomPulse = new Pulse(1) {
		@Override
		public boolean pulse() {
			String time = Integer.toString((int) Math.round(waitingTime-- * 0.6)) + " Sec";
			for (Player player : waitingRoom) {
				player.getPacketDispatch().sendString(time, 656, 10);
			}
			if (waitingTime == -1) {
				for (Iterator<Player> it = waitingRoom.iterator(); it.hasNext();) {
					enterCrater(it.next());
					it.remove();
				}
				return true;
			}
			return false;
		}

		@Override
		public void stop() {
			super.stop();
			waitingTime = 166;
		}
	};

	/**
	 * The game pulse (doesn't update every tick on RuneScape as well).
	 */
	private final Pulse gamePulse = new Pulse(10) {
		@Override
		public boolean pulse() {
			for (Player player : players.keySet()) {
				BountyEntry entry = players.get(player);
				if (entry.getTarget() == null) {
					findTarget(player);
				}
				entry.updatePenalty(player, false);
			}
			return false;
		}
	};

	/**
	 * Constructs a new {@code BountyHunterActivity} {@code Object}.
	 */
	public BountyHunterActivity() {
		this(CraterType.LOW_LEVEL);
	}

	/**
	 * Constructs a new {@code BountyHunterActivity} {@code Object}.
	 */
	public BountyHunterActivity(CraterType type) {
		super("BH " + type.name().toLowerCase(), false, false, false, ZoneRestriction.FOLLOWERS);
		this.type = type;
	}

	@Override
	public void register() {
		waitRoomPulse.stop();
		gamePulse.stop();
		if (getType() == CraterType.LOW_LEVEL) {
			// Disable bounty hunter area as wilderness
			Location check = Location.create(3166, 3679, 0);
			for (ZoneBorders border : WildernessZone.getInstance().getBorders()) {
				if (border.insideBorder(check)) {
				/*	if (GameWorld.getSettings().isPvp()) {
						border.addException(new ZoneBorders(2924, 3306, 3078, 3404));
					}*/
					border.addException(new ZoneBorders(3140, 3653, 3149, 3670));
					border.addException(new ZoneBorders(3150, 3656, 3154, 3676));
					border.addException(new ZoneBorders(3155, 3661, 3164, 3686));
					border.addException(new ZoneBorders(3165, 3667, 3173, 3693));
					border.addException(new ZoneBorders(3174, 3673, 3192, 3705));
					border.addException(new ZoneBorders(3193, 3681, 3196, 3709));
					break;
				}
			}
			PluginManager.definePlugin(new ComponentPlugin() {
				@Override
				public Plugin<Object> newInstance(Object arg) throws Throwable {
					ComponentDefinition.put(657, this);
					return this;
				}

				@Override
				public boolean handle(Player player, Component component, int opcode, int button, int slot, int itemId) {
					if (button == 18) {
						player.getInterfaceManager().close();
						player.lock(1);
						BountyHunterActivity activity = player.getExtension(BountyHunterActivity.class);
						if (activity.players.isEmpty()) {
							activity.joinWaitingRoom(player);
							return true;
						}
						activity.enterCrater(player);
					}
					return true;
				}

			});
			BHScoreBoard.init();
			PluginManager.definePlugin(new BountyLocateSpell());
			PluginManager.definePlugin(new BHOptionHandler());
			ActivityManager.register(new BountyHunterActivity(CraterType.MID_LEVEL));
			ActivityManager.register(new BountyHunterActivity(CraterType.HIGH_LEVEL));
		}
	}

	@Override
	public boolean start(Player player, boolean login, Object... args) {
		if (player.getFamiliarManager().hasFamiliar()) {
			player.getPacketDispatch().sendMessage("You can't bring a follower into the crater.");
			return false;
		}
		if (!getType().canEnter(player)) {
			return false;
		}
		player.addExtension(BountyHunterActivity.class, this);
		if (!login) {
			player.getInterfaceManager().open(new Component(657));
		} else {
			enterCrater(player);
		}
		return true;
	}

	@Override
	public boolean enter(Entity e) {
		if (e instanceof Player && getType().getZone().insideBorder(e.getLocation()) || e instanceof Player) {
			if (e.getAttribute("viewing_orb") != null) {
				return super.enter(e);
			}
			Player player = (Player) e;
			for (int i = 0; i < type.ordinal(); i++) {
				if (!player.getMusicPlayer().hasUnlockedIndex(517 + i)) {
					player.getMusicPlayer().unlock(444 - i, false);
				}
			}
			player.addExtension(BountyHunterActivity.class, this);
			BountyEntry entry = new BountyEntry();
			players.put(player, entry);
			player.getInterfaceManager().openOverlay(GAME_OVERLAY);
			int penalty;
			if ((penalty = player.getAttribute("pickup_penalty", 0)) != 0) {
				player.setAttribute("/save:pickup_penalty", GameWorld.getTicks() + penalty);
			}
			if ((penalty = player.getAttribute("exit_penalty", 0)) != 0) {
				player.setAttribute("/save:exit_penalty", GameWorld.getTicks() + penalty);
				if (player.getPrayer().get(PrayerType.PROTECT_ITEMS)) {
					player.getPrayer().toggle(PrayerType.PROTECT_ITEMS);
				}
			}
			findTarget(player);
			entry.updatePenalty(player, true);
			player.getInteraction().set(Option._P_ATTACK);
			player.getInteraction().remove(Option._P_ASSIST);
			player.getSkullManager().setSkullCheckDisabled(true);
			player.getSkullManager().setWilderness(true);
			player.setAttribute("bh_joined", GameWorld.getTicks() + 10);
			updateSkull(player);
			if (!gamePulse.isRunning()) {
				gamePulse.start();
				GameWorld.Pulser.submit(gamePulse);
			}
		}
		return super.enter(e);
	}

	@Override
	public boolean leave(Entity e, boolean logout) {
		if (e instanceof Player) {
			Player player = (Player) e;
			player.removeExtension(BountyHunterActivity.class);
			if (waitingRoom.contains(player)) {
				leaveWaitingRoom(player, logout);
			}
			BountyEntry entry = players.get(player);
			if (entry != null) {
				leaveCrater(player, logout, entry);
			}
		}
		return super.leave(e, logout);
	}

	@Override
	public boolean interact(Entity e, Node target, Option option) {
		if (target instanceof GroundItem && option.getName().equals("take")) {
			return actionButton((Player) e, 192, 19, -1, -1, 55);
		}
		if (target instanceof Item && option.getName().equals("drop")) {
			if (((Item) target).getValue() > 1000) {
				((Player) e).getPacketDispatch().sendMessage("This item is too valuable to drop in the crater.");
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean ignoreMultiBoundaries(Entity attacker, Entity victim) {
		if (attacker instanceof Player) {
			BountyEntry entry = players.get(attacker);
			return entry != null && entry.getTarget() == victim;
		}
		return false;
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean actionButton(Player player, int interfaceId, int buttonId, int slot, int itemId, int opcode) {
		if (interfaceId == 192 && buttonId == 19) {
			BountyEntry entry = players.get(player);
			if (entry != null && player.getAttribute("pickup_penalty", 0) > GameWorld.getTicks()) {
				player.getPacketDispatch().sendMessage("You should not be picking up items. Now you must wait before you can leave.");
				player.removeAttribute("pickup_penalty");
				player.setAttribute("/save:exit_penalty", GameWorld.getTicks() + 300);
				entry.updatePenalty(player, true);
				if (player.getPrayer().get(PrayerType.PROTECT_ITEMS)) {
					player.getPrayer().toggle(PrayerType.PROTECT_ITEMS);
				}
			}
		} else if (interfaceId == 271 && buttonId == 25) {
			if (player.getAttribute("exit_penalty", 0) > GameWorld.getTicks()) {
				player.getPacketDispatch().sendMessage("You can't use the protect item prayer until your penalty has passed.");
				player.getConfigManager().send(PrayerType.PROTECT_ITEMS.getConfig(), 0);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean continueAttack(Entity e, Node target, CombatStyle style, boolean message) {
		if (e instanceof Player && target instanceof Player) {
			if (((Player) target).getAttribute("bh_joined", -1) > GameWorld.getTicks()) {
				((Player) e).getPacketDispatch().sendMessage("This player has only just entered and is temporarily invulnerable to attacks.");
				return false;
			}
			e.removeAttribute("bh_joined");
		}
		return true;
	}

	/**
	 * Updates the skull.
	 * @param player The player.
	 */
	private void updateSkull(final Player player) {
		if (player.getAttribute("value_listener") == null) {
			ContainerListener listener = new ContainerListener() {
				@Override
				public void update(Container c, ContainerEvent event) {
					refresh(c);
				}

				@Override
				public void refresh(Container c) {
					updateSkull(player);
				}
			};
			player.setAttribute("value_listener", listener);
			player.getInventory().getListeners().add(listener);
			player.getEquipment().getListeners().add(listener);
		}
		long value = 0;
		for (Item item : player.getInventory().toArray()) {
			if (item != null) {
				value += item.getValue();
			}
		}
		for (Item item : player.getEquipment().toArray()) {
			if (item != null) {
				value += item.getValue();
			}
		}
		int skull = 2;
		if (value >= 0) {
			for (int i = 0; i < SKULL_VALUES.length - 1; i++) {
				if (value < SKULL_VALUES[i]) {
					skull = 6 - i;
					break;
				}
			}
		}
		player.getSkullManager().setSkulled(true);
		player.getAppearance().setSkullIcon(skull);
		player.getAppearance().sync();
	}

	/**
	 * Enters the bounty hunter crater.
	 * @param player The player.
	 */
	public void enterCrater(Player player) {
		Point offset = RandomFunction.getRandomElement(EXIT_OFFSETS);
		Location destination = Location.create(getType().getZone().getSouthWestX() + offset.getX(), getType().getZone().getSouthWestY() + offset.getY(), 0);
		player.getProperties().setTeleportLocation(destination);
		player.animate(Animation.create(7377));
	}

	/**
	 * Leaves the bounty hunter crater.
	 * @param player The player.
	 * @param logout If the player has logged out.
	 * @param entry The player's bounty entry.
	 */
	public void leaveCrater(Player player, boolean logout, BountyEntry entry) {
		if (entry.getHunter() != null) {
			BountyEntry other = players.get(entry.getHunter());
			if (other != null) {
				entry.getHunter().getPacketDispatch().sendMessage("Your target has " + (logout ? "logged out" : "left") + ". You shall be found a new target.");
				other.setTarget(null);
				findTarget(entry.getHunter());
			}
		}
		if (entry.getTarget() != null) {
			BountyEntry other = players.get(entry.getTarget());
			if (other != null) {
				other.setHunter(null);
			}
		}
		player.getHintIconManager().clear();
		players.remove(player);
		ContainerListener listener = player.getAttribute("value_listener");
		if (listener != null) {
			player.getInventory().getListeners().remove(listener);
			player.getEquipment().getListeners().remove(listener);
		}
		player.getAppearance().setSkullIcon(-1);
		player.getAppearance().sync();
		player.getInteraction().remove(Option._P_ATTACK);
		player.getInteraction().set(Option._P_ASSIST);
		player.getSkullManager().setSkullCheckDisabled(false);
		player.getSkullManager().setWilderness(false);
		player.getInterfaceManager().closeOverlay();
		if (players.isEmpty()) {
			gamePulse.stop();
		}
		int penalty;
		if ((penalty = player.getAttribute("pickup_penalty", 0)) > GameWorld.getTicks()) {
			player.setAttribute("/save:pickup_penalty", penalty - GameWorld.getTicks());
		} else {
			player.removeAttribute("pickup_penalty");
		}
		if ((penalty = player.getAttribute("exit_penalty", 0)) > GameWorld.getTicks()) {
			player.setAttribute("/save:exit_penalty", penalty - GameWorld.getTicks());
		} else {
			player.removeAttribute("exit_penalty");
		}
		player.getSkullManager().setSkulled(false);
	}

	/**
	 * Joins the waiting room.
	 * @param player The player.
	 */
	private void joinWaitingRoom(Player player) {
		waitingRoom.add(player);
		player.getProperties().setTeleportLocation(getType().getRoomLocation());
		player.getInterfaceManager().openOverlay(WAITING_OVERLAY);
		player.getPacketDispatch().sendString("Players waiting (need " + MINIMUM_PLAYERS + "):", 656, 6);
		updateWaitingRoomSize();
		if (waitingRoom.size() == MINIMUM_PLAYERS) {
			String time = (int) Math.round(waitingTime * 0.6) + " Sec";
			for (Player p : waitingRoom) {
				player.getPacketDispatch().sendString(time, 656, 10);
				p.getPacketDispatch().sendInterfaceConfig(656, 9, false);
				p.getPacketDispatch().sendInterfaceConfig(656, 8, false);
			}
			if (!waitRoomPulse.isRunning()) {
				waitRoomPulse.start();
				GameWorld.Pulser.submit(waitRoomPulse);
			}
		} else if (waitingRoom.size() > MINIMUM_PLAYERS) {
			player.getPacketDispatch().sendString((int) Math.round(waitingTime * 0.6) + " Sec", 656, 10);
			player.getPacketDispatch().sendInterfaceConfig(656, 9, false);
			player.getPacketDispatch().sendInterfaceConfig(656, 8, false);
		}
	}

	/**
	 * Leaves the waiting room.
	 * @param player The player.
	 */
	@SuppressWarnings("deprecation")
	public void leaveWaitingRoom(Player player, boolean logout) {
		waitingRoom.remove(player);
		if (waitingRoom.size() < MINIMUM_PLAYERS && waitRoomPulse.isRunning()) {
			waitRoomPulse.stop();
			for (Player p : waitingRoom) {
				p.getPacketDispatch().sendInterfaceConfig(656, 9, true);
				p.getPacketDispatch().sendInterfaceConfig(656, 8, true);
			}
		}
		updateWaitingRoomSize();
		player.getProperties().setTeleportLocation(getType().getExitLocation());
		player.getInterfaceManager().closeOverlay();
		if (logout) {
			player.setLocation(getType().getExitLocation());
		}
	}

	/**
	 * Finds a target for the specified player.
	 * @param player The player.
	 */
	private void findTarget(Player player) {
		Player target = null;
		BountyEntry other = null;
		int difference = 999;
		for (Player p : players.keySet()) {
			if (p == player) {
				continue;
			}
			BountyEntry entry = players.get(p);
			if (entry.getHunter() == null) {
				int diff = Math.abs(player.getProperties().getCurrentCombatLevel() - p.getProperties().getCurrentCombatLevel());
				if (diff < difference) {
					difference = diff;
					target = p;
					other = entry;
				}
			}
		}
		if (other != null) {
			other.setHunter(player);
			HintIconManager.registerHintIcon(player, target);
		} else {
			player.getHintIconManager().clear();
		}
		BountyEntry entry = players.get(player);
		entry.setTarget(target);
		entry.update(player);
	}

	/**
	 * Updates the amount of players in the waiting room.
	 */
	private void updateWaitingRoomSize() {
		String size = Integer.toString(waitingRoom.size());
		for (Player player : waitingRoom) {
			player.getPacketDispatch().sendString(size, 656, 7);
		}
	}

	@Override
	public boolean canLogout(Player player) {
		if (player.getAttribute("exit_penalty", 0) > GameWorld.getTicks()) {
			player.getPacketDispatch().sendMessage("You can't logout until the exit penalty is over.");
			return false;
		}
		return true;
	}

	@Override
	public boolean death(Entity e, Entity killer) {
		if (e instanceof Player) {
			Player player = (Player) e;
			BountyEntry entry = players.get(player);
			if (entry != null) {
				if (entry.getHunter() != killer && killer instanceof Player) {
					handleRogueKill((Player) killer, player, entry);
				} else if (entry.getHunter() == killer) { // "They" is not a
					// typo, this was
					// the actual
					// message on RS.
					entry.getHunter().getPacketDispatch().sendMessage("You killed " + player.getUsername() + ". They were your target, so your Hunter PvP rating increases!");
					entry.getHunter().getSavedData().getActivityData().updateBountyHunterRate(1);
					BHScoreBoard.getHunters().check(entry.getHunter());
				} else if (entry.getHunter() != null) {
					entry.getHunter().getPacketDispatch().sendMessage("Your target has died. You shall be found a new target.");
				}
				if (entry.getHunter() != null) {
					BountyEntry other = players.get(entry.getHunter());
					if (other != null) {
						other.setTarget(null);
					}
					entry.setHunter(null);
				}
				player.getHintIconManager().clear();
				if (player.getAttribute("pickup_penalty", 0) != 0) {
					player.setAttribute("pickup_penalty", GameWorld.getTicks() - 5);
				}
				if (player.getAttribute("exit_penalty", 0) != 0) {
					player.setAttribute("exit_penalty", GameWorld.getTicks() - 5);
				}
				entry.updatePenalty((Player) e, true);
			}
		}
		return false;
	}

	/**
	 * Handles a rogue kill.
	 * @param player The player who killed the victim.
	 * @param victim The victim.
	 */
	private void handleRogueKill(Player player, Player victim, BountyEntry entry) {
		player.getPacketDispatch().sendMessage("You killed " + victim.getUsername() + ". They were not your target, so your Rogue PvP rating");
		player.getPacketDispatch().sendMessage("increases!");
		player.getPacketDispatch().sendMessage("This means you get the pick-up penalty: pick anything up and you can't leave!");
		player.getSavedData().getActivityData().updateBountyRogueRate(1);
		BHScoreBoard.getRogues().check(player);
		player.setAttribute("/save:pickup_penalty", GameWorld.getTicks() + 300);
		entry.updatePenalty(player, true);
	}

	@Override
	public boolean teleport(Entity e, int type, Node node) {
		if (e instanceof Player && type != -1) {
			((Player) e).getPacketDispatch().sendMessage("A magical force stops you from teleporting.");
			return false;
		}
		return true;
	}

	@Override
	public ActivityPlugin newInstance(Player p) throws Throwable {
		return this;
	}

	@Override
	public Location getSpawnLocation() {
		return Location.create(3166, 3679, 0);
	}

	@Override
	public void configure() {
		registerRegion(6234);
		register(getType().getZone());
		int x = getType().getZone().getSouthWestX();
		int y = getType().getZone().getSouthWestY();
		MultiwayCombatZone.getInstance().register(new ZoneBorders(x + 56, y + 40, x + 140, y + 140));
	}

	/**
	 * Gets the type.
	 * @return The type.
	 */
	public CraterType getType() {
		return type;
	}

}
