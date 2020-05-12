package plugin.skill.slayer;

import org.crandor.game.content.skill.member.slayer.Equipment;
import org.crandor.game.content.skill.member.slayer.Tasks;
import org.crandor.game.interaction.NodeUsageEvent;
import org.crandor.game.interaction.UseWithHandler;
import org.crandor.game.node.entity.combat.BattleState;
import org.crandor.game.node.entity.combat.ImpactHandler.HitsplatType;
import org.crandor.game.node.entity.npc.AbstractNPC;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.world.map.Location;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.plugin.Plugin;
import org.crandor.plugin.PluginManager;

/**
 * Handles the gargoyle npc.
 * @author Vexia
 */
@InitializablePlugin
public final class GargoyleNPC extends AbstractNPC {

	/**
	 * Constructs a new {@code GargoyleNPC} {@code Object}.
	 * @param id the id.
	 * @param location the location.
	 */
	public GargoyleNPC(int id, Location location) {
		super(id, location);
	}

	/**
	 * Constructs a new {@code GargoyleNPC} {@code Object}.
	 */
	public GargoyleNPC() {
		super(0, null);
	}

	@Override
	public void checkImpact(BattleState state) {
		super.checkImpact(state);
		int lp = getSkills().getLifepoints();
		if (state.getEstimatedHit() > -1) {
			if (lp - state.getEstimatedHit() < 1) {
				state.setEstimatedHit(0);
				if (lp > 1) {
					state.setEstimatedHit(lp - 1);
				}
			}
		}
		if (state.getSecondaryHit() > -1) {
			if (lp - state.getSecondaryHit() < 1) {
				state.setSecondaryHit(0);
				if (lp > 1) {
					state.setSecondaryHit(lp - 1);
				}
			}
		}
		int totalHit = state.getEstimatedHit() + state.getSecondaryHit();
		if (lp - totalHit < 1) {
			state.setEstimatedHit(0);
			state.setSecondaryHit(0);
		}
	}

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		PluginManager.definePlugin(new RockHammerHandler());
		return super.newInstance(arg);
	}

	@Override
	public AbstractNPC construct(int id, Location location, Object... objects) {
		return new GargoyleNPC(id, location);
	}

	@Override
	public int[] getIds() {
		return Tasks.GARGOYLES.getNpcs();
	}

	/**
	 * The rock hammer handler plugin.
	 * @author Vexia
	 */
	public final class RockHammerHandler extends UseWithHandler {

		/**
		 * Constructs a new {@code RockHammerHandler} {@code Object}.
		 */
		public RockHammerHandler() {
			super(Equipment.ROCK_HAMMER.getItem().getId());
		}

		@Override
		public Plugin<Object> newInstance(Object arg) throws Throwable {
			for (int id : getIds()) {
				addHandler(id, NPC_TYPE, this);
			}
			return this;
		}

		@Override
		public boolean handle(NodeUsageEvent event) {
			final Player player = event.getPlayer();
			final NPC npc = (NPC) event.getUsedWith();
			if (npc.getSkills().getLifepoints() > 10) {
				player.getPacketDispatch().sendMessage("The gargoyle isn't weak enough to be harmed by the hammer.");
			} else {
				player.getPacketDispatch().sendMessage("The gargoyle cracks apart.");
				npc.getImpactHandler().manualHit(npc, npc.getSkills().getLifepoints(), HitsplatType.NORMAL);
			}
			return true;
		}

	}

}
