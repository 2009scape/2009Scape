package plugin.quest.members.whatliesbelow;

import core.game.node.entity.combat.BattleState;
import core.game.node.entity.npc.AbstractNPC;
import core.game.world.map.Location;

/**
 * Handles the kign roald npc.
 * @author Vexia
 */
public class KingRoaldNPC extends AbstractNPC {

	/**
	 * The cutscene.
	 */
	private WLBelowCutscene cutscene;

	/**
	 * Constructs a new {@Code KingRoaldNPC} {@Code Object}
	 * @param id the id.
	 * @param location the location.
	 */
	public KingRoaldNPC(int id, Location location) {
		super(id, location);
	}

	/**
	 * Constructs a new {@Code KingRoaldNPC} {@Code Object}
	 */
	public KingRoaldNPC() {
		super(-1, null);
	}

	@Override
	public AbstractNPC construct(int id, Location location, Object... objects) {
		return new KingRoaldNPC(id, location);
	}

	@Override
	public void checkImpact(BattleState state) {
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
		if (lp <= 1 && !getAttribute("message", false)) {
			setAttribute("message", true);
			cutscene.getPlayer().sendMessage("Now would be a good time to summon Zaff!");
			;
		}
	}

	/**
	 * Sets the cutscene.
	 * @param cutscene the cutscene.
	 */
	public void setCutscene(WLBelowCutscene cutscene) {
		this.cutscene = cutscene;
	}

	@Override
	public int[] getIds() {
		return new int[] { 5838 };
	}

}
