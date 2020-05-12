package plugin.skill.slayer;

import org.crandor.game.content.skill.member.slayer.Tasks;
import org.crandor.game.node.entity.Entity;
import org.crandor.game.node.entity.combat.BattleState;
import org.crandor.game.node.entity.combat.CombatStyle;
import org.crandor.game.node.entity.npc.AbstractNPC;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.world.GameWorld;
import org.crandor.game.world.map.Location;
import org.crandor.game.world.update.flag.context.Animation;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.tools.RandomFunction;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles the nechryael npc.
 * @author Vexia
 */
@InitializablePlugin
public final class NechryaelNPC extends AbstractNPC {

	/**
	 * The death spawn id.
	 */
	private static final int DEATH_SPAWN = 1614;

	/**
	 * The death spawn npcs.
	 */
	private List<DeathSpawnNPC> spawns = new ArrayList<>();

	/**
	 * The next spawn time.
	 */
	private int nextSpawn;

	/**
	 * Constructs a new {@code NechryaelNPC} {@code Object}.
	 * @param id the id.
	 * @param location the location.
	 */
	public NechryaelNPC(int id, Location location) {
		super(id, location);
	}

	/**
	 * Constructs a new {@code NechryaelNPC} {@code Object}.
	 */
	public NechryaelNPC() {
		super(0, null);
	}

	@Override
	public void onImpact(Entity entity, final BattleState state) {
		super.onImpact(entity, state);
		if (entity instanceof Player && RandomFunction.random(5) == 1) {
			spawn((Player) entity);
		}
	}

	/**
	 * Spawns a death spawn.
	 * @param player the player.
	 */
	public void spawn(Player player) {
		if (!canSpawn()) {
			return;
		}
		final DeathSpawnNPC spawn = new DeathSpawnNPC(DEATH_SPAWN, getLocation().transform(getDirection(), 1), player, this);
		spawn.init();
		setSpawnTime();
		spawns.add(spawn);
		animate(Animation.create(9491));
		spawn.getProperties().getCombatPulse().attack(player);
	}

	/**
	 * Sets the next spawn time.
	 */
	private void setSpawnTime() {
		nextSpawn = GameWorld.getTicks() + 50;
	}

	/**
	 * Checks if a death spawn can spawn.
	 * @return {@code True} if so.
	 */
	private boolean canSpawn() {
		if (spawns.size() >= 2) {
			setSpawnTime();
			return false;
		}
		return nextSpawn < GameWorld.getTicks();
	}

	@Override
	public AbstractNPC construct(int id, Location location, Object... objects) {
		return new NechryaelNPC(id, location);
	}

	@Override
	public int[] getIds() {
		return Tasks.NECHRYAELS.getNpcs();
	}

	/**
	 * Handles the death spawn npc.
	 * @author Vexia
	 */
	public static final class DeathSpawnNPC extends AbstractNPC {

		/**
		 * The player binded with the death spawn.
		 */
		private final Player player;

		/**
		 * The parent npc.
		 */
		private final NechryaelNPC parent;

		/**
		 * The time until its cleared.
		 */
		private int time;

		/**
		 * Constructs a new {@code DeathSpawnNPC} {@code Object}.
		 * @param id the id.
		 * @param location the location.
		 */
		public DeathSpawnNPC(int id, Location location, final Player player, NechryaelNPC parent) {
			super(id, location);
			this.player = player;
			this.parent = parent;
			this.setRespawn(false);
			this.time = GameWorld.getTicks() + 120;
		}

		@Override
		public void handleTickActions() {
			super.handleTickActions();
			if (!inCombat() || !player.inCombat()) {
				getProperties().getCombatPulse().attack(player);
			}
			if (time < GameWorld.getTicks() || !player.isActive() || player.getLocation().getDistance(getLocation()) > 15) {
				clear();
			}
		}

		@Override
		public void clear() {
			super.clear();
			parent.spawns.remove(this);
		}

		@Override
		public AbstractNPC construct(int id, Location location, Object... objects) {
			return new DeathSpawnNPC(id, location, null, null);
		}

		@Override
		public boolean isAttackable(Entity entity, CombatStyle style) {
			if (entity instanceof Player) {
				final Player t = (Player) entity;
				if (t != player) {
					t.getPacketDispatch().sendMessage("This isn't spawned for you.");
					return false;
				}
			}
			return super.isAttackable(entity, style);
		}

		@Override
		public boolean isPoisonImmune() {
			return true;
		}

		@Override
		public int[] getIds() {
			return new int[] { DEATH_SPAWN };
		}

	}
}
