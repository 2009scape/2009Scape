package plugin.activity.duel;

import java.util.ArrayList;
import java.util.List;

import org.crandor.ServerConstants;
import org.crandor.cache.def.impl.ObjectDefinition;
import org.crandor.game.container.Container;
import org.crandor.game.container.impl.EquipmentContainer;
import org.crandor.game.content.dialogue.DialogueAction;
import org.crandor.game.content.skill.member.summoning.familiar.Familiar;
import org.crandor.game.interaction.Option;
import org.crandor.game.interaction.OptionHandler;
import org.crandor.game.node.Node;
import org.crandor.game.node.entity.Entity;
import org.crandor.game.node.entity.combat.CombatStyle;
import org.crandor.game.node.entity.combat.equipment.WeaponInterface;
import org.crandor.game.node.entity.impl.PulseManager;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.entity.player.link.HintIconManager;
import org.crandor.game.node.entity.player.link.prayer.PrayerType;
import org.crandor.game.node.item.GroundItem;
import org.crandor.game.node.item.GroundItemManager;
import org.crandor.game.node.item.Item;
import org.crandor.game.system.task.Pulse;
import org.crandor.game.world.GameWorld;
import org.crandor.game.world.map.Location;
import org.crandor.game.world.map.RegionManager;
import org.crandor.game.world.map.zone.MapZone;
import org.crandor.game.world.map.zone.ZoneBorders;
import org.crandor.game.world.map.zone.ZoneRestriction;
import org.crandor.plugin.Plugin;
import org.crandor.tools.RandomFunction;
import org.crandor.tools.StringUtils;

/**
 * Represents a dueling area.
 * @author Vexia
 *
 */
public class DuelArea extends MapZone {

	/**
	 * The respawn locations.
	 */
	public static final Location[] RESPAWN_LOCATIONS = new Location[] { Location.create(3371, 3275, 0), Location.create(3365, 3276, 0), Location.create(3366, 3274, 0), Location.create(3369, 3274, 0), Location.create(3372, 3275, 0), Location.create(3372, 3266, 0), Location.create(3371, 3269, 0), Location.create(3376, 3270, 0), Location.create(3376, 3273, 0), Location.create(3377, 3275, 0) };

	/**
	 * The zone borders set for the area.
	 */
	private final ZoneBorders border;

	/**
	 * If this area has obstacles.
	 */
	private final boolean obstacles;

	/**
	 * The center location to determine starting points.
	 */
	private final Location center;

	/**
	 * The fight option.
	 */
	private static final Option FIGHT_OPTION = new Option("Fight", 0).setHandler(new OptionHandler() {

		@Override
		public boolean handle(Player player, Node node, String option) {
			player.getPulseManager().clear("interaction:attack:" + node.hashCode());
			player.getProperties().getCombatPulse().attack(node);
			return true;
		}

		@Override
		public boolean isWalk() {
			return false;
		}

		@Override
		public Plugin<Object> newInstance(Object arg) throws Throwable {
			return this;
		}

		@Override
		public boolean isDelayed(Player player) {
			return false;
		}
	});

	/**
	 * Constructs a new {@Code DuelArea} {@Code Object}
	 */
	public DuelArea() {
		this(-1, null, false, null);
	}

	/**
	 * Constructs a new {@Code DuelArea} {@Code Object}
	 * @param index the index.
	 * @param border the border.
	 * @param obstacles if there is obstacles.
	 */
	public DuelArea(int index, ZoneBorders border, boolean obstacles, Location center) {
		super("Duel Area - " + index, true, ZoneRestriction.FIRES, ZoneRestriction.CANNON, ZoneRestriction.RANDOM_EVENTS, ZoneRestriction.FOLLOWERS);
		this.obstacles = obstacles;
		this.border = border;
		this.center = center;
	}

	/**
	 * Starts a duel between two players.
	 * @param session the session.
	 */
	public void duel(final DuelSession session) {
		Location[] locations = getStartLocations(session);
		session.getPlayer().teleport(locations[0]);
		session.getOther().teleport(locations[1]);
		session.getPlayer().getInterfaceManager().close();
		session.getPlayer().getInterfaceManager().restoreTabs();
		session.getOther().getInterfaceManager().close();
		session.getOther().getInterfaceManager().restoreTabs();
		session.getPlayer().setAttribute("duel:icon", HintIconManager.registerHintIcon(session.getPlayer(), session.getOther()));
		session.getOther().setAttribute("duel:icon", HintIconManager.registerHintIcon(session.getOther(), session.getPlayer()));
		session.getPlayer().setAttribute("duel:ammo", new ArrayList<GroundItem>());
		session.getOther().setAttribute("duel:ammo", new ArrayList<GroundItem>());
		session.getPlayer().setAttribute("vengeance", false);
		session.getOther().setAttribute("vengeance", false);
		GameWorld.Pulser.submit(new Pulse(4, session.getPlayer(), session.getOther()) {
			int count;

			@Override
			public boolean pulse() {
				String chat = count < 3 ? String.valueOf(3 - count) : "FIGHT!";
				session.getPlayer().sendChat(chat);
				session.getOther().sendChat(chat);
				return count++ >= 3;
			}

			@Override
			public void stop() {
				super.stop();
				if (session.getPlayer().isActive() && session.getOther().isActive() && session.getFightState() != 2) {
					session.setFightState(1);
					session.getPlayer().getSkullManager().setSkullCheckDisabled(true);
					session.getPlayer().getSkullManager().setWilderness(true);
					session.getOther().getSkullManager().setSkullCheckDisabled(true);
					session.getOther().getSkullManager().setWilderness(true);
					session.getPlayer().getProperties().setMultiZone(true);
					session.getOther().getProperties().setMultiZone(true);
				}
			}
		});
	}

	@Override
	public boolean interact(Entity e, Node target, Option option) {
		if (!e.isPlayer()) {
			return true;
		}
		Player p = e.asPlayer();
		final DuelSession session = getSession(p);
		if (session == null) {
			return true;
		}
		if (option.getName().equalsIgnoreCase("eat") && session.hasRule(DuelRule.NO_FOOD)) {
			p.sendMessage("You can't eat in this fight.");
			return true;
		}
		if (option.getName().equalsIgnoreCase("drink") && session.hasRule(DuelRule.NO_DRINKS)) {
			p.sendMessage("You can't drink in this fight.");
			return true;
		}
		if (option.getName().equalsIgnoreCase("wield") || option.getName().equalsIgnoreCase("wear") && target instanceof Item) {
			if (session.isRestrictedEquipment(target.asItem())) {
				p.sendMessage("You can't equip that during this duel.");
				return true;
			}
		}
		if (option.getName().equalsIgnoreCase("Summon") && !session.hasRule(DuelRule.ENABLE_SUMMONING)) {
			p.sendMessage("You cannot summon familiars whilst in a duel.");
			return true;
		}
		if (option.getName().equalsIgnoreCase("drop") && target instanceof Item) {
			p.sendMessage("You cannot drop items whilst in a duel.");
			return true;
		}
		switch (target.getId()) {
		case 3203:
			handleForfeit(p);
			return true;
		}
		return super.interact(e, target, option);
	}

	@Override
	public boolean move(Entity entity, Location from, Location to) {
		if (entity.isPlayer()) {
			Player p = entity.asPlayer();
			DuelSession session = getSession(p);
			if (session == null) {
				return false;
			}
			return !session.hasRule(DuelRule.NO_MOVEMENT);
		}
		return super.move(entity, from, to);
	}

	@Override
	public boolean actionButton(Player player, int interfaceId, int buttonId, int slot, int itemId, int opcode) {
		DuelSession session = getSession(player);
		if (session == null) {
			return true;
		}
		WeaponInterface inter = player.getExtension(WeaponInterface.class);
		if (inter != null && interfaceId == inter.getId()) {
			switch (buttonId) {
			case 8:
			case 10:
				if (session.hasRule(DuelRule.NO_SPECIAL_ATTACKS)) {
					player.sendMessage("You can't use special attacks during a duel.");
					return true;
				}
				break;
			}
		}
		switch (interfaceId) {
		case 182:
			player.sendMessage("You can't logout during a duel.");
			return true;
		case 271:
			if (session.hasRule(DuelRule.NO_PRAYER)) {
				player.getPrayer().toggle(PrayerType.get(buttonId));
				player.getPrayer().toggle(PrayerType.get(buttonId));
				player.sendMessage("Use of prayer has been turned off for this duel.");
				return true;
			}
			break;
		case 430:
			if (session.hasRule(DuelRule.NO_MAGIC)) {
				player.sendMessage("Use of prayer has been turned off for this duel.");
				return true;
			}
			break;
		}
		return super.actionButton(player, interfaceId, buttonId, slot, itemId, opcode);
	}

	@Override
	public boolean continueAttack(Entity e, Node target, CombatStyle style, boolean message) {
		if (e.isPlayer() && target instanceof Player && !checkAttack(e.asPlayer(), ((Player) target))) {
			return false;
		}
		if (e instanceof Familiar && target instanceof Player) {
			Familiar f = (Familiar) e;
			Player o = f.getOwner();
			if (o != null && target.asPlayer() != getSession(o).getOpposite(o)) {
				return false;
			}
		}
		if (e instanceof Familiar && target instanceof Familiar) {
			Familiar f = (Familiar) e;
			Familiar t = (Familiar) target;
			if (getSession(f.getOwner()).getOpposite(f.getOwner()) != t.getOwner()) {
				return false;
			}
		}
		if (e.isPlayer()) {
			Player p = e.asPlayer();
			DuelSession session = getSession(p);
			if (session == null) {
				return false;
			}
			boolean canAttack = true;
			switch (style) {
			case MAGIC:
				canAttack = !session.hasRule(DuelRule.NO_MAGIC);
				break;
			case MELEE:
				canAttack = !session.hasRule(DuelRule.NO_MELEE);
				break;
			case RANGE:
				canAttack = !session.hasRule(DuelRule.NO_RANGE);
				break;
			}
			if (!canAttack) {
				p.sendMessage(StringUtils.formatDisplayName(style.name().toLowerCase()) + " has been turned off for this duel.");
				return false;
			}
			if (session.hasRule(DuelRule.FUN_WEAPONS) && (p.getEquipment().get(EquipmentContainer.SLOT_WEAPON) == null || !p.getEquipment().get(EquipmentContainer.SLOT_WEAPON).getDefinition().getConfiguration("fun_weapon", false))) {
				p.sendMessages("This is a 'fun weapon' duel. You can only use flowers, basket of eggs, or a", "rubber chicken.");
				return false;
			}
			if (target instanceof Familiar) {
				Familiar f = (Familiar) target;
				if (f.getOwner() != null && getSession(p).getOpposite(p) != f.getOwner()) {
					p.sendMessage("You can't attack that familiar.");
					return false;
				}
			}
		}
		return true;
	}

	@Override
	public boolean teleport(Entity e, int type, Node node) {
		if (e.isPlayer()) {
			e.asPlayer().getDialogueInterpreter().sendDialogue("Coward! You can't teleport from a duel.");
		}
		return false;
	}

	@Override
	public boolean enter(Entity e) {
		if (e.isPlayer()) {
			getSession(e.asPlayer());
			e.getProperties().setSafeZone(true);
			e.getProperties().setSpawnLocation(RandomFunction.getRandomElement(RESPAWN_LOCATIONS));
			e.asPlayer().getInteraction().remove(DuelArenaActivity.CHALLENGE_OPTION);
			e.asPlayer().getInteraction().set(FIGHT_OPTION);
		} else if (e instanceof Familiar) {
			Familiar f = (Familiar) e;
			Player o = f.getOwner();
			if (o != null && !o.getFamiliarManager().hasPet()) {
				DuelSession s = getSession(f.getOwner());
				if (s != null && s.hasRule(DuelRule.ENABLE_SUMMONING)) {
					f.transform();
				}
			}
			f.getProperties().setMultiZone(true);
		}
		return super.enter(e);
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean leave(Entity entity, boolean logout) {
		entity.getProperties().setSafeZone(false);
		if (entity instanceof Player) {
			Player p = entity.asPlayer();
			if (logout) {
				p.setLocation(RandomFunction.getRandomElement(RESPAWN_LOCATIONS));
			}
			p.lock(1);
			leave(p);
			DuelSession session = getSession(p);
			if (session == null) {
				return true;
			}
			session.leave(p, logout ? 1 : p.getAttribute("duel:forfeit", false) ? 0 : 2);
			remove(session.getOpposite(p));
			leave(session.getOpposite(p));
		} else if (entity instanceof Familiar) {
			Familiar familiar = (Familiar) entity;
			if (familiar.isCombatFamiliar()) {
				familiar.reTransform();
			}
			familiar.getProperties().setMultiZone(false);
		}
		return super.leave(entity, logout);
	}

	@Override
	public void configure() {
		register(border);
	}

	/**
	 * Handles the leaving of a player.
	 * @param p the player.
	 */
	private void leave(Player p) {
		if (p.getAttribute("duel:ammo", null) != null) {
			List<GroundItem> ammo = p.getAttribute("duel:ammo");
			Container c = new Container(40);
			for (GroundItem item : ammo) {
				if (item == null) {
					continue;
				}
				if (item.isActive() && GroundItemManager.getItems().contains(item) && item.droppedBy(p)) {
					GroundItemManager.destroy(item);
					c.add(item);
				}
			}
			p.getInventory().addAll(c);
		}
		p.getInteraction().remove(FIGHT_OPTION);
		p.getSkullManager().setSkullCheckDisabled(false);
		p.getSkullManager().setWilderness(false);
		p.getProperties().setSpawnLocation(ServerConstants.HOME_LOCATION);
		p.getProperties().setMultiZone(false);
		p.getInteraction().set(DuelArenaActivity.CHALLENGE_OPTION);
	}

	/**
	 * Forcefully removes a player.
	 * @param player the player.
	 */
	private static void remove(Player player) {
		HintIconManager.removeHintIcon(player, player.getAttribute("duel:icon", -1));
		if (player.getExtension(DuelSession.class) != null) {
			player.teleport(RandomFunction.getRandomElement(RESPAWN_LOCATIONS));
		}
	}

	/**
	 * Handles the forfeit trapdoor.
	 * @param p the player.
	 */
	public static void handleForfeit(Player p) {
		DuelSession session = getSession(p);
		if (session == null) {
			return;
		}
		if (session.getFightState() != 1) {
			p.sendMessage("The duel has not started yet!");
			return;
		}
		if (session.hasRule(DuelRule.NO_FORFEIT)) {
			p.getDialogueInterpreter().sendDialogue("Forfeit has been turned off for this duel.");
			return;
		}
		p.getDialogueInterpreter().sendOptions("Do you wish to forfeit?", "Yes", "No");
		p.getDialogueInterpreter().addAction(new DialogueAction() {
			@Override
			public void handle(Player player, int buttonId) {
				if (buttonId == 2) {
					remove(player);
					player.setAttribute("duel:forfeit", true);
				}
			}
		});
	}

	/**
	 * Gets the starting locations for the session.
	 * @param session the session.
	 * @return the locations.
	 */
	public Location[] getStartLocations(DuelSession session) {
		Location start = null;
		Location[] locations = new Location[2];
		while (start == null) {
			start = center.transform(RandomFunction.random(10), RandomFunction.random(session.hasRule(DuelRule.NO_MOVEMENT) ? 6 : 10), 0);
			if (isValidLocation(start)) {
				locations[locations[0] == null ? 0 : 1] = start;
				if (session.hasRule(DuelRule.NO_MOVEMENT)) {
					Location l = null;
					if (!isValidLocation(l = start.transform(1, 0, 0))) {
						if (!isValidLocation(l = start.transform(-1, 0, 0))) {
							if (!isValidLocation(l = start.transform(1, 0, 0))) {
								if (!isValidLocation(l = start.transform(-1, 0, 0))) {
									locations[1] = start;
									break;
								}
							}
						}
					}
					start = l;
					locations[1] = l;
					break;
				}
				start = locations[1] == null ? null : start;
			} else {
				start = null;
			}
		}
		return locations;
	}

	@Override
	public boolean startDeath(Entity entity, Entity killer) {
		if (entity instanceof Player) {
			Player k = killer instanceof Player ? killer.asPlayer() : killer instanceof Familiar ? ((Familiar) killer).getOwner() : null;
			if (k != null) {
				k.getImpactHandler().setDisabledTicks(10);
				k.getSkills().heal(100);
				k.lock(5);
			}
		}
		return true;
	}

	@Override
	public boolean death(Entity e, Entity killer) {
		if (e.isPlayer() && (killer.isPlayer() || killer instanceof Familiar)) {
			Player k = killer instanceof Familiar ? ((Familiar) killer).getOwner() : killer.asPlayer();
			if (k != null) {
				k.getSkills().heal(100);
				PulseManager.cancelDeathTask(e);
			}
		}
		return super.death(e, killer);
	}

	/**
	 * Checks if the location is valid to start on.
	 * @param location the location.
	 * @return {@code True} if so.
	 */
	public boolean isValidLocation(Location location) {
		return RegionManager.isTeleportPermitted(location) && RegionManager.getObject(location) == null;
	}

	/**
	 * Checks the attack of a player.
	 * @param player the player.
	 * @param target the target.
	 * @return {@code True} if so.
	 */
	public boolean checkAttack(Player player, Player target) {
		DuelSession session = getSession(player);
		if (session == null) {
			return false;
		}
		if (session.getOpposite(player) != target) {
			player.sendMessage("You can only attack your opponent!");
			return false;
		}
		if (session.getFightState() != 1) {
			player.sendMessage("You can't attack yet!");
			return false;
		}
		return true;
	}

	/**
	 * Gets the duel session for the player.
	 * @param player the player.
	 * @return the duel session.
	 */
	public static DuelSession getSession(Player player) {
		DuelSession session = player.getExtension(DuelSession.class);
		if (session == null) {
			remove(player);
		}
		return session;
	}

	/**
	 * Gets the obstacles.
	 * @return the obstacles.
	 */
	public boolean isObstacles() {
		return obstacles;
	}

	/**
	 * Gets the border.
	 * @return the border.
	 */
	public ZoneBorders getBorder() {
		return border;
	}

	/**
	 * Gets the center.
	 * @return the center.
	 */
	public Location getCenter() {
		return center;
	}

	/**
	 * Handles the forfeit trapdoor plugin.
	 * @author Vexia
	 *
	 */
	public static class ForfeitTrapdoorPlugin extends OptionHandler {

		@Override
		public Plugin<Object> newInstance(Object arg) throws Throwable {
			ObjectDefinition.forId(3203).getConfigurations().put("option:forfeit", this);
			return this;
		}

		@Override
		public boolean handle(Player player, Node node, String option) {
			handleForfeit(player);
			return true;
		}

		@Override
		public Location getDestination(Node node, Node n) {
			Location loc = null;
			if (node instanceof Player) {
				DuelSession session = getSession(node.asPlayer());
				if (session != null && session.hasRule(DuelRule.NO_MOVEMENT)) {
					loc = node.getLocation();
				}
			}
			return loc;
		}
	}
}
