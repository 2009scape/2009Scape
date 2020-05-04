package plugin.npc.familiar;

import org.crandor.cache.def.impl.NPCDefinition;
import org.crandor.game.content.skill.Skills;
import org.crandor.game.content.skill.member.summoning.familiar.Familiar;
import org.crandor.game.content.skill.member.summoning.familiar.FamiliarSpecial;
import org.crandor.game.content.skill.member.summoning.familiar.Forager;
import org.crandor.game.interaction.OptionHandler;
import org.crandor.game.node.Node;
import org.crandor.game.node.entity.combat.BattleState;
import org.crandor.game.node.entity.combat.equipment.WeaponInterface;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.item.Item;
import org.crandor.game.system.task.Pulse;
import org.crandor.game.world.GameWorld;
import org.crandor.game.world.map.Location;
import org.crandor.game.world.update.flag.context.Animation;
import org.crandor.game.world.update.flag.context.Graphics;
import org.crandor.plugin.Plugin;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.plugin.PluginManager;

/**
 * The plugin used to load void familiar npcs.
 * @author Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class VoidFamiliarNPC implements Plugin<Object> {

	/**
	 * The foraging items.
	 */
	private static final Item[] ITEMS = new Item[] { new Item(434), new Item(440), new Item(453), new Item(444), new Item(447) };

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		PluginManager.definePlugin(new VoidRavagerNPC());
		PluginManager.definePlugin(new VoidShifterNPC());
		PluginManager.definePlugin(new VoidSpinnerNPC());
		PluginManager.definePlugin(new VoidTorcherNPC());
		return this;
	}

	@Override
	public Object fireEvent(String identifier, Object... args) {
		return null;
	}

	/**
	 * Handles the call to arms scroll.
	 * @param special the special.
	 * @return {@code True} if so.
	 */
	public boolean callToArms(Familiar familiar, FamiliarSpecial special) {
		final Player owner = familiar.getOwner();
		owner.lock();
		GameWorld.Pulser.submit(new Pulse(1, owner) {
			int counter;

			@Override
			public boolean pulse() {
				switch (++counter) {
				case 1:
					owner.visualize(Animation.create(8136), Graphics.create(1503));
					break;
				case 3:
					owner.unlock();
					owner.getProperties().setTeleportLocation(Location.create(2659, 2658, 0));
					owner.visualize(Animation.create(8137), Graphics.create(1502));
					return true;
				}
				return false;
			}
		});
		return true;
	}

	/**
	 * Represents the Void Ravager familiar.
	 * @author Vexia
	 * @author Aero
	 */
	public final class VoidRavagerNPC extends Forager {

		/**
		 * Constructs a new {@code VoidRavagerNPC} {@code Object}.
		 */
		public VoidRavagerNPC() {
			this(null, 7370);
		}

		/**
		 * Constructs a new {@code VoidRavagerNPC} {@code Object}.
		 * @param owner The owner.
		 * @param id The id.
		 */
		public VoidRavagerNPC(Player owner, int id) {
			super(owner, id, 2700, 12818, 3, WeaponInterface.STYLE_AGGRESSIVE, ITEMS);
		}

		@Override
		public Familiar construct(Player owner, int id) {
			return new VoidRavagerNPC(owner, id);
		}

		@Override
		protected boolean specialMove(FamiliarSpecial special) {
			return callToArms(this, special);
		}

		@Override
		public int[] getIds() {
			return new int[] { 7370, 7371 };
		}

	}

	/**
	 * Represents the Void Shifter familiar.
	 * @author Aero
	 */
	public class VoidShifterNPC extends Familiar {

		/**
		 * Constructs a new {@code VoidShifterNPC} {@code Object}.
		 */
		public VoidShifterNPC() {
			this(null, 7367);
		}

		/**
		 * Constructs a new {@code VoidShifterNPC} {@code Object}.
		 * @param owner The owner.
		 * @param id The id.
		 */
		public VoidShifterNPC(Player owner, int id) {
			super(owner, id, 9400, 12814, 3, WeaponInterface.STYLE_ACCURATE);
		}

		@Override
		public Familiar construct(Player owner, int id) {
			return new VoidShifterNPC(owner, id);
		}

		@Override
		public void adjustPlayerBattle(BattleState state) {
			super.adjustPlayerBattle(state);
			int percentage = (int) (owner.getSkills().getStaticLevel(Skills.HITPOINTS) * 0.10);
			if (owner.getSkills().getLifepoints() < percentage) {
				owner.getProperties().setTeleportLocation(Location.create(2659, 2658, 0));
			}
		}

		@Override
		protected boolean specialMove(FamiliarSpecial special) {
			return callToArms(this, special);
		}

		@Override
		public int[] getIds() {
			return new int[] { 7367, 7368 };
		}

	}

	/**
	 * Represents the Void Spinner familiar.
	 * @author Aero
	 * @author Vexia
	 */
	public class VoidSpinnerNPC extends Familiar {

		/**
		 * The delay till the next heal.
		 */
		private int healDelay;

		/**
		 * Constructs a new {@code VoidSpinnerNPC} {@code Object}.
		 */
		public VoidSpinnerNPC() {
			this(null, 7333);
		}

		/**
		 * Constructs a new {@code VoidSpinnerNPC} {@code Object}.
		 * @param owner The owner.
		 * @param id The id.
		 */
		public VoidSpinnerNPC(Player owner, int id) {
			super(owner, id, 2700, 12780, 3, WeaponInterface.STYLE_DEFENSIVE);
		}

		@Override
		public void handleFamiliarTick() {
			super.handleFamiliarTick();
			if (healDelay < GameWorld.getTicks()) {
				getSkills().heal(1);
				healDelay = GameWorld.getTicks() + 25;
			}
		}

		@Override
		public Familiar construct(Player owner, int id) {
			return new VoidSpinnerNPC(owner, id);
		}

		@Override
		protected boolean specialMove(FamiliarSpecial special) {
			return callToArms(this, special);
		}

		@Override
		public int[] getIds() {
			return new int[] { 7333, 7334 };
		}

	}

	/**
	 * Represents the Void Torcher familiar.
	 * @author Aero
	 * @author Vexia
	 * @note TODO: the "Strike" attack.
	 */
	public class VoidTorcherNPC extends Familiar {

		/**
		 * Constructs a new {@code VoidTorcherNPC} {@code Object}.
		 */
		public VoidTorcherNPC() {
			this(null, 7351);
		}

		/**
		 * Constructs a new {@code VoidTorcherNPC} {@code Object}.
		 * @param owner The owner.
		 * @param id The id.
		 */
		public VoidTorcherNPC(Player owner, int id) {
			super(owner, id, 9400, 12798, 3, WeaponInterface.STYLE_CAST);
		}

		@Override
		public Familiar construct(Player owner, int id) {
			return new VoidTorcherNPC(owner, id);
		}

		@Override
		public void configureFamiliar() {
			PluginManager.definePlugin(new OptionHandler() {

				@Override
				public Plugin<Object> newInstance(Object arg) throws Throwable {
					for (int i : getIds()) {
						NPCDefinition.forId(i).getConfigurations().put("option:strike", this);
					}
					return this;
				}

				@Override
				public boolean handle(Player player, Node node, String option) {
					final Familiar familiar = (Familiar) node;
					if (!player.getFamiliarManager().isOwner(familiar)) {
						return true;
					}
					// TODO:
					return true;
				}

			});
		}

		@Override
		protected boolean specialMove(FamiliarSpecial special) {
			return callToArms(this, special);
		}

		@Override
		public int[] getIds() {
			return new int[] { 7351, 7352 };
		}

	}
}
