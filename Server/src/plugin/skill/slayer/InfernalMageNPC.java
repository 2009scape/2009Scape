package plugin.skill.slayer;

import org.crandor.game.content.skill.member.slayer.Tasks;
import org.crandor.game.node.entity.combat.CombatSpell;
import org.crandor.game.node.entity.npc.AbstractNPC;
import org.crandor.game.node.entity.player.link.SpellBookManager.SpellBook;
import org.crandor.game.world.map.Location;
import org.crandor.plugin.InitializablePlugin;

/**
 * Handles the infernal mage npc.
 * @author Vexia
 */
@InitializablePlugin
public final class InfernalMageNPC extends AbstractNPC {

	/**
	 * Constructs a new {@code InfernalMageNPC} {@code Object}.
	 * @param id the id.
	 * @param location the location.
	 */
	public InfernalMageNPC(int id, Location location) {
		super(id, location);
	}

	/**
	 * Constructs a new {@code InfernalMageNPC} {@code Object}.
	 */
	public InfernalMageNPC() {
		super(0, null);
	}

	@Override
	public void init() {
		super.init();
		getProperties().setAutocastSpell((CombatSpell) SpellBook.MODERN.getSpell(8));
	}

	@Override
	public AbstractNPC construct(int id, Location location, Object... objects) {
		return new InfernalMageNPC(id, location);
	}

	@Override
	public int[] getIds() {
		return Tasks.INFERNAL_MAGES.getNpcs();
	}

}
