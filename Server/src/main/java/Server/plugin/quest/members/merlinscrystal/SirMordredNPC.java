package plugin.quest.members.merlinscrystal;

import core.game.node.entity.Entity;
import core.game.node.entity.npc.AbstractNPC;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.quest.Quest;
import core.game.system.task.Pulse;
import core.game.world.GameWorld;
import core.game.world.map.Location;
import core.game.world.update.flag.context.Graphics;

/**
 * Handles Sir Mordred
 * @author Vexia
 * @author Splinter
 */
public class SirMordredNPC extends AbstractNPC {

	/**
	 * The NPC ids of NPCs using this plugin.
	 */
	private static final int[] ID = { 247 };

	/**
	 * Constructs a new {@code AlKharidWarriorPlugin} {@code Object}.
	 */
	public SirMordredNPC() {
		super(0, null);
	}

	/**
	 * Constructs a new {@code SirMordredNPC} {@code Object}
	 * @param id the id.
	 * @param location the location.
	 */
	private SirMordredNPC(int id, Location location) {
		super(id, location);
	}

	@Override
	public AbstractNPC construct(int id, Location location, Object... objects) {
		return new SirMordredNPC(id, location);
	}

	@Override
	public void finalizeDeath(final Entity killer) {
		super.finalizeDeath(killer);
		super.getProperties().getCombatPulse().stop();
		super.getSkills().setLifepoints(50);
		if (killer != null && killer.isPlayer()) {
			final Player p = ((Player) killer);
			Quest quest = p.getQuestRepository().getQuest("Merlin's Crystal");
			if (quest.getStage(p) == 40) {
				quest.setStage(p, 50);
				p.getQuestRepository().syncronizeTab(p);
				p.getDialogueInterpreter().open("merlin_dialogue", 34, 248);
				final NPC npc = NPC.create(248, p.getLocation());
				p.setAttribute("morgan", npc);
				npc.init();
				npc.graphics(Graphics.create(86));
				npc.moveStep();
				npc.face(p);
				GameWorld.Pulser.submit(new Pulse(100, p, npc) {

					@Override
					public boolean pulse() {
						return true;
					}

					@Override
					public void stop() {
						super.stop();
						if (npc != null && npc.isActive()) {
							npc.clear();
						}
					}

				});
			}
		}
	}

	@Override
	public int[] getIds() {
		return ID;
	}

}