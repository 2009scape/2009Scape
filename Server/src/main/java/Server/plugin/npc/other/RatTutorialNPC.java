package plugin.npc.other;

import plugin.quest.tutorials.tutorialisland.TutorialSession;
import plugin.quest.tutorials.tutorialisland.TutorialStage;
import plugin.skill.Skills;
import core.game.node.entity.Entity;
import core.game.node.entity.npc.AbstractNPC;
import core.game.node.entity.player.Player;
import core.game.node.item.GroundItemManager;
import core.game.node.item.Item;
import core.plugin.InitializablePlugin;
import core.game.world.map.Location;

/**
 * Handles the tutorial rat npc.
 * @author 'Vexia
 */
@InitializablePlugin
public class RatTutorialNPC extends AbstractNPC {

	/**
	 * The NPC ids of NPCs using this plugin.
	 */
	private static final int[] ID = { 86 };

	/**
	 * Constructs a new {@code AlKharidWarriorPlugin} {@code Object}.
	 */
	public RatTutorialNPC() {
		super(0, null);
		this.setAggressive(false);
	}

	/**
	 * Constructs a new {@code AlKharidWarriorPlugin} {@code Object}.
	 * @param id The NPC id.
	 * @param location The location.
	 */
	private RatTutorialNPC(int id, Location location) {
		super(id, location, true);
	}

	@Override
	public void init() {
		super.init();
		setAggressive(false);
		getSkills().setLevel(Skills.HITPOINTS, 5);
		getSkills().setStaticLevel(Skills.HITPOINTS, 5);
		getSkills().setLifepoints(5);
	}

	@Override
	public AbstractNPC construct(int id, Location location, Object... objects) {
		return new RatTutorialNPC(id, location);
	}

	@Override
	public void tick() {
		super.tick();
	}

	@Override
	public void finalizeDeath(final Entity killer) {
		super.finalizeDeath(killer);
		if (!(killer instanceof Player)) {
			return;
		}
		final Player p = ((Player) killer);
		if (TutorialSession.getExtension(p).getStage() == 52) {
			TutorialStage.load(p, 53, false);
		}
		if (TutorialSession.getExtension(p).getStage() == 54) {
			TutorialStage.load(p, 55, false);
		}
		if (killer instanceof Player) {
			if (p.getQuestRepository().getQuest("Witch's Potion").isStarted(p)) {
				GroundItemManager.create(new Item(300), getLocation(), p);
			}
		}
	}

	@Override
	public int[] getIds() {
		return ID;
	}

}
