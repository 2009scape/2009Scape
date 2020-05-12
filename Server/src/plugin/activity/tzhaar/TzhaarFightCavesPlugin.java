package plugin.activity.tzhaar;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.crandor.game.content.activity.ActivityPlugin;
import org.crandor.game.content.global.BossKillCounter;
import org.crandor.game.content.skill.Skills;
import org.crandor.game.content.skill.member.slayer.Tasks;
import org.crandor.game.interaction.Option;
import org.crandor.game.node.Node;
import org.crandor.game.node.entity.Entity;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.entity.player.info.portal.Perks;
import org.crandor.game.node.entity.player.link.diary.DiaryType;
import org.crandor.game.node.item.GroundItemManager;
import org.crandor.game.node.item.Item;
import org.crandor.game.node.object.GameObject;
import org.crandor.game.system.task.Pulse;
import org.crandor.game.world.GameWorld;
import org.crandor.game.world.map.Location;
import org.crandor.game.world.map.build.DynamicRegion;
import org.crandor.game.world.map.zone.ZoneRestriction;
import org.crandor.game.world.repository.Repository;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.tools.RandomFunction;

/**
 * Handles the Tzhaar Fight caves plugin.
 * @author Emperor
 */
@InitializablePlugin
public final class TzhaarFightCavesPlugin extends ActivityPlugin {

	/**
	 * The waves data.
	 */
	private static final int[][] WAVES = { { 2734, 0 }, { 2736, 2 }, { 2739, 6 }, { 2741, 14 }, { 2743, 30 }, { 2745, 62 } };

	/**
	 * The spawn location offsets.
	 */
	private static final Location[] SPAWN_LOCATIONS = { Location.create(8, 44, 0), Location.create(38, 47, 0), Location.create(5, 9, 0), Location.create(46, 21, 0), Location.create(28, 28, 0), };

	/**
	 * The currently active NPCs.
	 */
	public List<NPC> activeNPCs = new ArrayList<>();

	/**
	 * Constructs a new {@code TzhaarFightCavesPlugin} {@code Object}.
	 */
	public TzhaarFightCavesPlugin() {
		this(null);
	}

	/**
	 * Constructs a new {@code TzhaarFightCavesPlugin} {@code Object}.
	 * @param player The player.
	 */
	public TzhaarFightCavesPlugin(Player player) {
		super("fight caves", true, true, true, ZoneRestriction.CANNON);
		super.player = player;
	}

	@Override
	public void register() {
		try {
			new TzhaarFightCaveNPC().newInstance(null);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean canLogout(Player p) {
		if (!p.getAttribute("fc_safe_logout", false)) {
			p.setAttribute("fc_safe_logout", true);
			p.getPacketDispatch().sendMessage("<col=4C0E00>Your logout request has been received. The minigame will be paused at the end");
			p.getPacketDispatch().sendMessage("<col=4C0E00>of this wave.");
			p.getPacketDispatch().sendMessage("<col=4C0E00>If you try to logout before that, you will have to repeat this wave.");
			return false;
		}
		return super.canLogout(p);
	}

	@Override
	public boolean start(final Player player, boolean login, Object... args) {
		if (!player.getAchievementDiaryManager().hasCompletedTask(DiaryType.KARAMJA, 0, 8)) {
			player.getAchievementDiaryManager().updateTask(player, DiaryType.KARAMJA, 0, 8, true);
		}
		this.player = player;
		player.lock(3);
		int offsetX = player.getAttribute("fc_offset", 45 << 8) >> 8;
		int offsetY = player.getAttribute("fc_offset", 61) & 0xff;
		player.removeAttribute("fc_offset");
		player.getProperties().setTeleportLocation(getBase().transform(offsetX, offsetY, 0));
		Pulse pulse;
		if (!login) {
			final int wave = player.hasPerk(Perks.FIGHT_CAVE_FANATIC) ? 25 : 0;
			player.setAttribute("fc_wave", wave);
			player.getWalkingQueue().reset();
			pulse = new Pulse(1, player) {
				int count = 0;

				@Override
				public boolean pulse() {
					if (count++ == 0) {
						player.getWalkingQueue().reset();
						player.getWalkingQueue().addPath(getBase().getX() + 44, getBase().getY() + 49, true);
						player.getWalkingQueue().addPath(getBase().getX() + 32, getBase().getY() + 32, true);
						player.getDialogueInterpreter().sendDialogues(2617, null, "You're on your own now, JalYt.", "Prepare to fight for your life!");
					} else if (count == 16) {
						spawnWave(wave);
						return true;
					}
					return false;
				}
			};
		} else {
			if (player.getAttribute("fc_wave", 0) == 62) {
				player.getDialogueInterpreter().sendDialogues(2617, null, "Look out, here comes TzTok-Jad!");
			}
			pulse = new Pulse(16, player) {
				@Override
				public boolean pulse() {
					spawnWave(player.getAttribute("fc_wave", 0));
					return true;
				}
			};
		}
		player.setAttribute("fc:pulse", pulse);
		GameWorld.Pulser.submit(pulse);
		return true;
	}

	/**
	 * Leaves the fight caves activity.
	 * @param player The player.
	 * @param wave The current wave.
	 */
	public void leave(Player player, int wave) {
		activeNPCs.clear();
		player.getProperties().setTeleportLocation(getSpawnLocation());
		if (wave == 63) {
			if (!player.getInventory().add(new Item(6570))) {
				GroundItemManager.create(new Item(6570), getSpawnLocation(), player);
			}
			player.getPacketDispatch().sendMessage("You were victorious!");
			BossKillCounter.addtoKillcount(player, 2745);
			if (player.getSlayer().getTask() == Tasks.JAD) {
				player.getSkills().addExperience(Skills.SLAYER, 25000);
				player.getSlayer().clear();
				player.sendMessage("You receive 25,000 slayer experience for defeating TzTok-Jad.");
			}
			player.getDialogueInterpreter().sendDialogues(2617, null, "You even defeated TzTok-Jad, I am most impressed!", "Please accept this gift as a reward.");
			Repository.sendNews(player.getUsername() + " has been victorious in defeating TzTok-Jad for a firecape!");
		} else if (wave <= 1) {
			player.getDialogueInterpreter().sendDialogues(2617, null, "Well I suppose you tried... better luck next time.");
		} else {
			player.getDialogueInterpreter().sendDialogues(2617, null, "Well done in the cave, here, take TokKul as reward.");
		}
		int amount = wave << 7;
		if (player.hasPerk(Perks.POWERPOINT)) {
			amount *= 2;
			player.sendMessage("<col=FF0000>You receive double the tokkul!");
		}
		if (amount > 0 && !player.getInventory().add(new Item(6529, amount))) {
			GroundItemManager.create(new Item(6529, amount), getSpawnLocation(), player);
		}
	}

	@Override
	public boolean enter(final Entity e) {
		e.setAttribute("fight_caves", true);
		return super.enter(e);
	}

	@Override
	public boolean leave(final Entity e, boolean logout) {
		unregisterRegion(region.getId());
		if (logout) {
			Location l = player.getLocation();
			player.setAttribute("/save:fc_offset", (l.getX() - getBase().getX()) << 8 | (l.getY() - getBase().getY()));
		} else {
			player.removeAttribute("fc_offset");
			player.removeAttribute("fc_wave");
			player.removeAttribute("fc_safe_logout");
			Pulse pulse = player.getAttribute("fc:pulse");
			if (pulse != null) {
				pulse.stop();
				player.removeAttribute("fc:pulse");
			}
		}
		player.removeAttribute("fight_caves");
		return super.leave(e, logout);
	}

	@Override
	public boolean death(Entity e, Entity killer) {
		if (e instanceof NPC) {
			NPC n = (NPC) e;
			if (n.getId() == 2736 || n.getId() == 2737) {
				new TzhaarFightCaveNPC(2738, n.getLocation(), this).init();
				new TzhaarFightCaveNPC(2738, n.getLocation().transform(1, 0, 0), this).init();
			}
			if (activeNPCs.contains(n)) {
				activeNPCs.remove(n);
				if (n.getId() == 2745) {
					player.setAttribute("/save:fc_wave", 63);
					leave((Player) killer, 63);
				} else if (activeNPCs.isEmpty()) {
					final int wave = player.getAttribute("fc_wave", 0) + 1;
					player.setAttribute("/save:fc_wave", wave);
					if (player.getAttribute("fc_safe_logout", false)) {
						player.getPacketDispatch().sendLogout();
					} else {
						if (wave == 62) {
							player.getDialogueInterpreter().sendDialogues(2617, null, "Look out, here comes TzTok-Jad!");
						}
						Pulse pulse = new Pulse(9, player) {
							@Override
							public boolean pulse() {
								spawnWave(wave);
								return true;
							}
						};
						player.setAttribute("fc:pulse", pulse);
						GameWorld.Pulser.submit(pulse);
					}
				}
			}
			e.clear();
		} else {
			leave((Player) e, e.getAttribute("fc_wave", 0));
		}
		return true;
	}

	/**
	 * Spawns the wave for the player.
	 * @param wave The wave to spawn.
	 */
	public void spawnWave(int wave) {
		player.setAttribute("/save:fc_wave", wave);
		int count = wave;
		for (int i = WAVES.length - 1; i >= 0; i--) {
			if (count >= WAVES[i][1]) {
				if (count > WAVES[i][1] * 2) {
					spawn(WAVES[i][0] + 1);
					count -= (WAVES[i][1] + 1);
				}
				spawn(WAVES[i][0]);
				count -= WAVES[i][1] + 1;
			}
		}
		player.getPacketDispatch().sendMessage("Current wave: " + (wave + 1) + ".");
	}

	/**
	 * Spawns a tzhaar NPC.
	 * @param npcId The NPC.
	 * @return The NPC.
	 */
	public TzhaarFightCaveNPC spawn(int npcId) {
		Random r = RandomFunction.RANDOM;
		Location l = SPAWN_LOCATIONS[r.nextInt(SPAWN_LOCATIONS.length)];
		TzhaarFightCaveNPC npc = new TzhaarFightCaveNPC(npcId, getBase().transform(l).transform(r.nextInt(8), r.nextInt(8), 0), this);
		npc.init();
		return npc;
	}

	@Override
	public boolean interact(Entity e, Node target, Option option) {
		if (target instanceof GameObject && ((GameObject) target).getId() == 9357) {
			leave((Player) e, e.getAttribute("fc_wave", 0));
			player.removeAttribute("fc_wave");
			player.removeAttribute("fc_offset");
			return true;
		}
		return false;
	}

	@Override
	public ActivityPlugin newInstance(Player p) throws Throwable {
		return new TzhaarFightCavesPlugin(p);
	}

	@Override
	public Location getSpawnLocation() {
		return Location.create(2438, 5169, 0);
	}

	@Override
	public void configure() {
		region = DynamicRegion.create(9551);
		setRegionBase();
		registerRegion(region.getId());
		region.setMusicId(473);
	}

}
