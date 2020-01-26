package plugin.activity.tzhaar;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.crandor.game.component.Component;
import org.crandor.game.container.impl.EquipmentContainer;
import org.crandor.game.content.activity.ActivityPlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.content.skill.Skills;
import org.crandor.game.interaction.Option;
import org.crandor.game.node.Node;
import org.crandor.game.node.entity.Entity;
import org.crandor.game.node.entity.combat.CombatStyle;
import org.crandor.game.node.entity.combat.DeathTask;
import org.crandor.game.node.entity.combat.ImpactHandler.HitsplatType;
import org.crandor.game.node.entity.impl.ForceMovement;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.entity.player.info.portal.Perks;
import org.crandor.game.node.entity.player.link.diary.DiaryType;
import org.crandor.game.node.item.Item;
import org.crandor.game.node.object.GameObject;
import org.crandor.game.system.task.Pulse;
import org.crandor.game.world.GameWorld;
import org.crandor.game.world.map.Location;
import org.crandor.game.world.map.RegionManager;
import org.crandor.game.world.map.zone.ZoneBorders;
import org.crandor.game.world.map.zone.ZoneRestriction;
import org.crandor.game.world.update.flag.player.AppearanceFlag;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.tools.RandomFunction;

/**
 * Handles the Tzhaar Fight pits.
 * @author Emperor
 */
@InitializablePlugin
public final class TzhaarFightPitsPlugin extends ActivityPlugin {

	/**
	 * The zone where players battle.
	 */
	private static final ZoneBorders WAR_ZONE = new ZoneBorders(2368, 5120, 2420, 5168);

	/**
	 * The lobby list.
	 */
	private static final List<Player> LOBBY_PLAYERS = new ArrayList<>();

	/**
	 * The players in battle list.
	 */
	private static final List<Player> WAR_PLAYERS = new ArrayList<>();

	/**
	 * The interface id.
	 */
	private static final int INTERFACE_ID = 373;

	/**
	 * The skull id.
	 */
	private static final int SKULL_ID = 1;

	/**
	 * The minutes elapsed in the minigame (resets depending on the game
	 * schedule).
	 */
	private static int minutes = 0;

	/**
	 * The amount of tokkul to receive.
	 */
	private static int tokkulAmount;

	/**
	 * The last player who's won the pits.
	 */
	private static Player lastVictor;

	/**
	 * The pulse used.
	 */
	private static final Pulse PULSE = new Pulse(100) {
		@Override
		public boolean pulse() {
			if (++minutes == 3) {
				startGameSession();
			} else if (minutes == 4) {
				sendDialogue("FIGHT!");
			} else if ((minutes - 0) > 4) {
				spawnWave();
			}
			return false;
		}
	};

	/**
	 * Sends a dialogue to all players in battle.
	 * @param string The string.
	 */
	private static void sendDialogue(String string) {
		for (Player p : WAR_PLAYERS) {
			p.getDialogueInterpreter().sendDialogues(2618, FacialExpression.NO_EXPRESSION, string);
		}
	}

	/**
	 * Constructs a new {@code TzhaarFightPitsPlugin} {@code Object}.
	 */
	public TzhaarFightPitsPlugin() {
		super("fight pits", false, true, true, ZoneRestriction.CANNON);
	}

	@Override
	public boolean continueAttack(Entity e, Node target, CombatStyle style, boolean message) {
		if (target instanceof Player && !WAR_PLAYERS.contains(target)) {
			return false; // Safety check
		}
		if (minutes < 4 && e instanceof Player) {
			((Player) e).getPacketDispatch().sendMessage("The fight hasn't started yet.");
		}
		return minutes > 3;
	}

	/**
	 * Removes an entity from the war zone.
	 * @param e The entity.
	 */
	public void removeFromBattle(Entity e) {
		if (WAR_PLAYERS.remove(e)) {
			if (WAR_PLAYERS.size() < 2) {
				if (!WAR_PLAYERS.isEmpty()) {
					resetLastVictor();
					lastVictor = WAR_PLAYERS.get(0);
					if (lastVictor != null && !lastVictor.getAchievementDiaryManager().getDiary(DiaryType.KARAMJA).isComplete(2, 0)) {
						lastVictor.getAchievementDiaryManager().getDiary(DiaryType.KARAMJA).updateTask(lastVictor, 2, 0, true);
					}
					addTokkul(lastVictor);
					lastVictor.getAppearance().setSkullIcon(SKULL_ID);
					lastVictor.getUpdateMasks().register(new AppearanceFlag(lastVictor));
					lastVictor.getPacketDispatch().sendString("Current Champion: " + getChampionName(), INTERFACE_ID, 0);
					resetDamagePulse(lastVictor);
					RegionManager.forId(9552).getPlanes()[0].getNpcs().get(0).setAttribute("fp_champn", getChampionName());
				}
				minutes = 0;
			}
			sendPlayersRemaining(WAR_PLAYERS.size() - 1);
		}
		if (e instanceof Player && !LOBBY_PLAYERS.contains(e)) {
			LOBBY_PLAYERS.add(player = (Player) e);
			player.getSkullManager().setSkullCheckDisabled(false);
			player.getSkullManager().setWilderness(false);
			player.getInterfaceManager().closeOverlay();
			player.getInteraction().remove(Option._P_ATTACK);
			if (player.getAppearance().getSkullIcon() == SKULL_ID) {
				player.getAppearance().setSkullIcon(-1);
				player.getUpdateMasks().register(new AppearanceFlag(player));
			}
		}
	}

	/**
	 * Resets the damage pulse.
	 * @param e The entity.
	 */
	public static void resetDamagePulse(Entity e) {
		Pulse pl = e.getAttribute("fp_pulse", null);
		if (pl != null) {
			pl.stop();
			e.removeAttribute("fp_pulse");
		}
	}

	/**
	 * Resets the last victor.
	 */
	private void resetLastVictor() {
		List<NPC> npcs = new ArrayList<>(RegionManager.forId(9552).getPlanes()[0].getNpcs());
		for (NPC n : npcs) {
			if (n.getId() == 2734 || n.getId() == 2739) {
				n.clear();
			}
		}
		if (lastVictor == null || !lastVictor.isActive()) {
			return;
		}
		if (lastVictor.getAppearance().getSkullIcon() == SKULL_ID) {
			player.getAppearance().setSkullIcon(-1);
			player.getUpdateMasks().register(new AppearanceFlag(player));
		}
	}

	@Override
	public boolean death(Entity e, Entity killer) {
		if (e instanceof Player) {
			removeFromBattle(e);
		}
		return false;
	}

	@Override
	public boolean start(Player player, boolean login, Object... args) {
		if (!player.getAchievementDiaryManager().hasCompletedTask(DiaryType.KARAMJA, 0, 8)) {
			player.getAchievementDiaryManager().updateTask(player, DiaryType.KARAMJA, 0, 8, true);
		}
		if (!login) {
			player.setAttribute("fight_pits", true);
			ForceMovement.run(player, Location.create(2399, 5177, 0), Location.create(2399, 5175, 0));
			return true;
		}
		if (WAR_ZONE.insideBorder(player.getLocation().getX(), player.getLocation().getY())) {
			player.getProperties().setTeleportLocation(Location.create(2399, 5177, 0));
		}
		return false;
	}

	@Override
	public boolean enter(Entity e) {
		if (e instanceof Player) {
			if (!e.getAttribute("fight_pits", false)) {
				return false;
			}
			LOBBY_PLAYERS.add((Player) e);
		}
		return super.enter(e);
	}

	@Override
	public boolean leave(Entity e, boolean logout) {
		if (WAR_PLAYERS.contains(e)) {
			removeFromBattle(e);
		}
		LOBBY_PLAYERS.remove(e);
		if (logout) {
			e.setLocation(Location.create(2399, 5177, 0));
		}
		if (e instanceof Player && (player = (Player) e).getAppearance().getSkullIcon() == SKULL_ID) {
			player.getAppearance().setSkullIcon(-1);
			player.getUpdateMasks().register(new AppearanceFlag(player));
		}
		return super.leave(e, logout);
	}

	@Override
	public boolean interact(Entity e, Node target, Option option) {
		if (target instanceof GameObject) {
			GameObject o = (GameObject) target;
			if (o.getId() == 9369) {
				ForceMovement.run(e, Location.create(2399, 5175, 0), Location.create(2399, 5177, 0));
				return true;
			}
			if (o.getId() == 9368) {
				if (WAR_PLAYERS.contains(e)) {
					removeFromBattle(e);
					ForceMovement.run(e, Location.create(2399, 5167, 0), Location.create(2399, 5169, 0));
					return true;
				}
				((Player) e).getPacketDispatch().sendMessage("This vent is too hot for you to pass!");
				return true;
			}
		}
		return false;
	}

	@Override
	public Location getSpawnLocation() {
		Random r = RandomFunction.RANDOM;
		int x = 2395 + r.nextInt(8);
		int y = 5170 + r.nextInt(5);
		if (x == 2399 && y == 5172) {
			y--;
		}
		return Location.create(x, y, 0);
	}

	@Override
	public void configure() {
		register(new ZoneBorders(2368, 5120, 2420, 5176));
		PULSE.start();
		GameWorld.submit(PULSE);
	}

	@Override
	public ActivityPlugin newInstance(Player p) throws Throwable {
		return this;
	}

	/**
	 * Spawns a wave in the fight pits minigame.
	 */
	private static void spawnWave() {
		int stage = (minutes - 4) / 5;
		switch (stage) {
		case 1:
			for (Player p : WAR_PLAYERS) {
				for (int i = 0; i < 2 + ((minutes - 4) >> 2); i++) {
					NPC n = NPC.create(2734, getZoneDestination());
					n.init();
					n.setAggressive(true);
					n.setDefaultBehavior();
					n.getProperties().getCombatPulse().attack(p);
				}
			}
			break;
		case 2:
			for (Player p : WAR_PLAYERS) {
				for (int i = 0; i < 2 + ((minutes - 4) >> 2); i++) {
					NPC n = NPC.create(2739, getZoneDestination());
					n.init();
					n.getProperties().getCombatPulse().attack(p);
				}
			}
			break;
		case 3:
			for (final Player p : WAR_PLAYERS) {
				Pulse pl = new Pulse(1, p) {
					int count = 0;

					@Override
					public boolean pulse() {
						if (DeathTask.isDead(p)) {
							return true;
						}
						p.getImpactHandler().manualHit(p, 1, HitsplatType.NORMAL);
						p.getImpactHandler().manualHit(p, 1, HitsplatType.NORMAL);
						return ++count == 100;
					}
				};
				p.setAttribute("fp_pulse", pl);
				GameWorld.submit(pl);
			}
			break;
		}
	}

	/**
	 * Starts a new game session.
	 */
	private static void startGameSession() {
		if (LOBBY_PLAYERS.size() + WAR_PLAYERS.size() < 2) {
			minutes = 2;
			if (LOBBY_PLAYERS.size() == 1) {
				LOBBY_PLAYERS.get(0).getPacketDispatch().sendMessage("There's not enough players to start the game.");
			}
			return;
		}
		tokkulAmount = 0;
		String victor = null;
		if (lastVictor != null) {
			victor = "Current Champion: " + getChampionName();// JalYt-Mej-" +
			// lastVictor.getUsername();
		}
		int size = (LOBBY_PLAYERS.size() + WAR_PLAYERS.size()) - 1;
		if (!WAR_PLAYERS.isEmpty()) {
			WAR_PLAYERS.get(0).getConfigManager().set(560, size);
		}
		for (Iterator<Player> it = LOBBY_PLAYERS.iterator(); it.hasNext();) {
			Player p = it.next();
			if (p != null && p.isActive()) {
				WAR_PLAYERS.add(p);
				p.getInterfaceManager().openOverlay(new Component(INTERFACE_ID));
				if (victor != null) {
					p.getPacketDispatch().sendString(victor, INTERFACE_ID, 0);
				}
				p.getSkullManager().setSkullCheckDisabled(true);
				p.getSkullManager().setWilderness(true);
				p.getConfigManager().set(560, size);
				p.getProperties().setTeleportLocation(getZoneDestination());
				p.getInteraction().set(Option._P_ATTACK);
				tokkulAmount += p.getProperties().getCurrentCombatLevel();
			}
			it.remove();
		}
		sendDialogue("Wait for my signal before fighting.");
	}

	/**
	 * Gets the Tzhaar name of the current champion.
	 * @return The champion name.
	 */
	private static String getChampionName() {
		int strength = lastVictor.getSkills().getStaticLevel(Skills.STRENGTH);
		int defence = lastVictor.getSkills().getStaticLevel(Skills.DEFENCE);
		int count = 0;
		for (int i = 5; i < 23; i++) {
			int skill;
			if ((skill = lastVictor.getSkills().getStaticLevel(i)) > strength && skill > defence) {
				if (++count == 5) {
					return "JalYt-Hur-" + lastVictor.getUsername();
				}
			}
		}
		int skill = lastVictor.getSkills().getHighestCombatSkill();
		switch (skill) {
		case Skills.ATTACK:
		case Skills.STRENGTH:
			return "JalYt-Ket-" + lastVictor.getUsername();
		case Skills.RANGE:
			return "JalYt-Xil-" + lastVictor.getUsername();
		case Skills.MAGIC:
			return "JalYt-Mej-" + lastVictor.getUsername();
		}
		return "JalYtHur-" + lastVictor.getUsername();
	}

	/**
	 * Adds the tokkul.
	 * @param p The player to receive the tokkul.
	 */
	public static void addTokkul(Player p) {
		int amount = tokkulAmount;
		if (p.getEquipment().getNew(EquipmentContainer.SLOT_CAPE).getId() == 6570) {
			amount *= 2;
		}
		if (p.hasPerk(Perks.POWERPOINT)) {
			amount *= 2;
			p.sendMessage("<col=FF0000>You receive double the tokkul!");
		}
		if (amount > 0) {
			if (!p.getInventory().add(new Item(6529, amount))) {
				p.getPacketDispatch().sendMessage("Your Tokkul reward was added to your bank.");
				p.getBank().add(new Item(6529, amount));
			}
		}
	}

	/**
	 * Gets the teleport destination in the war zone.
	 * @return The location.
	 */
	private static Location getZoneDestination() {
		switch (RandomFunction.randomize(5)) {
		case 0:
			return Location.create(2384 + RandomFunction.random(29), 5133 + RandomFunction.random(4), 0);
		case 1:
			return Location.create(2410 + RandomFunction.random(4), 5140 + RandomFunction.random(18), 0);
		case 2:
			return Location.create(2392 + RandomFunction.random(11), 5141 + RandomFunction.random(26), 0);
		case 3:
			return Location.create(2383 + RandomFunction.random(3), 5141 + RandomFunction.random(15), 0);
		case 4:
			return Location.create(2392 + RandomFunction.random(12), 5145 + RandomFunction.random(20), 0);
		}
		return null;
	}

	/**
	 * Sends the foes remaining count to all players.
	 * @param value The value.
	 */
	public static void sendPlayersRemaining(int value) {
		for (Player p : WAR_PLAYERS) {
			p.getConfigManager().set(560, value);
		}
	}

}
