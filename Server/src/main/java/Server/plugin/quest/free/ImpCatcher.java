package plugin.quest.free;

import core.game.node.entity.player.link.quest.QuestReward;
import plugin.skill.Skills;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.quest.Quest;
import core.game.node.item.Item;
import core.game.node.object.GameObject;
import core.game.node.object.ObjectBuilder;
import core.game.world.map.Location;
import core.plugin.InitializablePlugin;
import core.game.world.map.RegionManager;

/**
 * Represents the imp catcher quest.
 * @author Vexia
 * 
 */
@InitializablePlugin
public class ImpCatcher extends Quest {

	/**
	 * Represents the black bead item.
	 */
	private static final Item BLACK_BEAD = new Item(1474);

	/**
	 * Represents the red bead item.
	 */
	private static final Item RED_BEAD = new Item(1470);

	/**
	 * Represents the white bead item.
	 */
	private static final Item WHITE_BEAD = new Item(1476);

	/**
	 * Represents the yellow bead item.
	 */
	private static final Item YELLOW_BEAD = new Item(1472);

	/**
	 * Represents the amulet item.
	 */
	private static final Item AMULET = new Item(1478);
	
	/**
	 * Constructs a new {@Code ImpCatcher} {@Code Object}
	 */
	public ImpCatcher() {
		super(
			"Imp Catcher",
			21,
			20,
			1,
			160, 0, 1, 2
		);
	}
	
	@Override
	public Quest newInstance(Object object) {
		return this;
	}

	@Override
	public void drawJournal(Player player, int stage) {
		super.drawJournal(player, stage);
		int line;
		if (getStage(player) == 0) {
			writeJournal(player,
				"I can start this quest by speaking to <red>Wizard Mizgog <blue>who is",
				"in the <red>Wizard's Tower",
				"",
				"There are no requirements for this quest.");
		} else if (getStage(player) == 10) {
			line = writeJournal(player, true,
				"I have spoken to Wizard Mizgog.",
				""
			);
			if (player.getInventory().containItems(BLACK_BEAD.getId(), RED_BEAD.getId(), YELLOW_BEAD.getId(), WHITE_BEAD.getId())) {
				writeJournal(player, line,
					"I have collected all the missing beads and need to return",
					"them to <red>Wizard Mizgog.");
			} else {
				writeJournal(player, line,
					"I need to collect some items by killing <red>Imps.",
					(player.getInventory().containsItem(BLACK_BEAD) ? "<str>" : "<red>") + "1 Black Bead",
					(player.getInventory().containsItem(RED_BEAD) ? "<str>" : "<red>") + "1 Red Bead",
					(player.getInventory().containsItem(WHITE_BEAD) ? "<str>" : "<red>") + "1 White Bead",
					(player.getInventory().containsItem(YELLOW_BEAD) ? "<str>" : "<red>") + "1 Yellow Bead"
				);
			}
		} else {
			line = writeJournal(player, true,
				"I have spoken to Wizard Mizgog.",
				"",
				"I have collected all the beads.",
				"",
				"Wizard Mizgog thanked me for finding his beads and gave",
				"me an Amulet of Accuracy.");
			writeJournal(player, ++line,
				"<col=FF0000>QUEST COMPLETE!");
		}
	}

	@Override
	public void finish(Player player) {
		super.finish(player);
		player.unlock();
		player.getPacketDispatch().sendMessage("The Wizard hands you an amulet.");
		// 16170
		GameObject table = RegionManager.getObject(Location.create(3102, 3163, 2));
		if (table.getId() != 16170) {
			ObjectBuilder.replace(table, table.transform(16170), 80);
		}
	}

	@Override
	public Item getRewardComponentItem() {
		return AMULET;
	}

	@Override
	public QuestReward[] getQuestRewards() {
		return new QuestReward[]{
			new QuestReward(Skills.MAGIC, 875),
			new QuestReward(AMULET, false),
		};
	}

}
