package plugin.quest.members.rovingelves;

import core.game.node.entity.player.link.quest.QuestRequirement;
import core.game.node.entity.player.link.quest.QuestReward;
import plugin.quest.members.waterfallquest.WaterFall;
import plugin.skill.Skills;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.quest.Quest;
import core.game.node.item.Item;
import core.plugin.InitializablePlugin;
import core.plugin.PluginManager;


/**
 * The Roving Elves quest.
 * @author Splinter
 */
@InitializablePlugin
public class RovingElves extends Quest {

	/**
	 * Crystal Bow (full)
	 */
	public static final Item CRYSTAL_BOW_FULL = new Item(4214);

	/**
	 * Crystal Shield (full)
	 */
	public static final Item CRYSTAL_SHIELD_FULL = new Item(4225);

	/**
	 * The little seed.
	 */
	public static final Item CONSECRATION_SEED = new Item(4205);

	/**
	 * The little seed. (charged)
	 */
	public static final Item CONSECRATION_SEED_CHARGED = new Item(4206);

	/**
	 * Crystal seed, ready to be made into equipment
	 */
	public static final Item CRYSTAL_SEED = new Item(4207);

	/**
	 * Constructs a new {@Code RovingElves} {@Code Object}
	 */
	public RovingElves() {
		super(
			"Roving Elves",
			105,
			104,
			1,
			402, 0, 1, 6
		);
	}

	@Override
	public void drawJournal(Player player, int stage) {
		super.drawJournal(player, stage);
		int line;
		switch (stage) {
		case 0:
			line = writeJournal(player,
				"I can start this quest by talking to <red>Islwyn <blue>found in <red>Isafdar.",
				"",
				"Minimum requirements:"
			);
			writeJournal(player, line, getQuestRequirementsJournal(player));
			break;
		case 10:
			writeJournal(player,
				"It appears that when I recovered <red>Glarial's ashes <blue>from",
				"her <red>tomb <blue>near the waterfall, I disturbed her peace.",
				"",
				"I talked to <red>Islwyn<blue>, an elf that I found in the woods.",
				"When I offered to help he said I should talk with <red>Eluned,",
				"she will tell me how <red>elven consecration <blue>is done."
			);
			break;
		case 15:
			if (player.getInventory().contains(CONSECRATION_SEED.getId(), 1)) {
				line = writeJournal(player, true,
					"It appears that when I recovered Glarial's ashes from",
					"her tomb near the waterfall, I disturbed her peace.",
					"I talked to <red>Islwyn<blue>, an elf that I found in the woods.",
					"When I offered to help he said I should talk with Eluned,",
					"she will tell me how elven consecration is done.",
					"",
					"Eluned told me that I must acquire the <red>old consecration",
					"<red>seed <blue>from the <red>guardian spirit <blue>in <red>Glarial's old tomb.",
					"",
					"Once I have the old seed I will need to return to Eluned, who",
					"will re-enchant it for me.",
					"");
				writeJournal(player, line,
					"I have the <red>consecration seed. <blue>I should return to <red>Eluned",
					"and have her enchant it for me."
				);
			} else {
				line = writeJournal(player, true,
					"It appears that when I recovered Glarial's ashes from",
					"her tomb near the waterfall, I disturbed her peace.",
					"I talked to <red>Islwyn<blue>, an elf that I found in the woods.",
					"When I offered to help he said I should talk with Eluned,",
					"she will tell me how elven consecration is done.",
					"");
				writeJournal(player, line,
					"Eluned told me that I must acquire the <red>old consecration",
					"<red>seed <blue>from the <red>guardian spirit <blue>in <red>Glarial's old tomb.",
					"",
					"Once I have the old seed I will need to return to Eluned, who",
					"will re-enchant it for me."
				);
			}
			if (player.getInventory().contains(CONSECRATION_SEED_CHARGED.getId(), 1)) {
				line = writeJournal(player, line, true,
					"It appears that when I recovered Glarial's ashes from",
					"her tomb near the waterfall, I disturbed her peace.",
					"I talked to <red>Islwyn<blue>, an elf that I found in the woods.",
					"When I offered to help he said I should talk with Eluned,",
					"she will tell me how elven consecration is done.",
					"",
					"Eluned told me that I must acquire the <red>old consecration",
					"<red>seed <blue>from the <red>guardian spirit <blue>in <red>Glarial's old tomb.",
					"",
					"Once I have the old seed I will need to return to Eluned, who",
					"will re-enchant it for me.",
					"");
				writeJournal(player, line,
					"I have the <red>charged consecration seed<blue>.",
					"I need to head to the treasure room under the <red>waterfall <blue>and",
					"plant the <red>consecration seed <blue>near the <red>chalice <blue>in order to",
					"free Glarial's spirit."
				);
			}
			break;
		case 20:
			line = writeJournal(player,
				"It appears that when I recovered Glarial's ashes from",
				"her tomb near the waterfall, I disturbed her peace.",
				"I talked to <red>Islwyn<blue>, an elf that I found in the woods.",
				"When I offered to help he said I should talk with Eluned,",
				"she will tell me how elven consecration is done.",
				"",
				"Eluned told me that I must acquire the <red>old consecration",
				"<red>seed <blue>from the <red>guardian spirit <blue>in <red>Glarial's old tomb.",
				"",
				"Once I have the old seed I will need to return to Eluned, who",
				"will re-enchant it for me.",
				"",
				"I have the <red>charged consecration seed<blue>.",
				"I need to head to the treasure room under the <red>waterfall <blue>and",
				"plant the <red>consecration seed <blue>near the <red>chalice <blue>in order to",
				"free Glarial's spirit.",
				"");
			writeJournal(player, line,
				"I have freed <red>Glarial's spirit <blue>by planting the consecration seed near",
				"the chalice under the waterfall. The seed I planted",
				"vanished. I should go speak to <red>Eluned <blue>again.");
			break;
		case 100:
			line = writeJournal(player, true,
				"It appears that when I recovered Glarial's ashes from",
				"her tomb near the waterfall, I disturbed her peace.",
				"I talked to <red>Islwyn<blue>, an elf that I found in the woods.",
				"When I offered to help he said I should talk with Eluned,",
				"she will tell me how elven consecration is done.",
				"",
				"Eluned told me that I must acquire the <red>old consecration",
				"<red>seed <blue>from the <red>guardian spirit <blue>in <red>Glarial's old tomb.",
				"",
				"Once I have the old seed I will need to return to Eluned, who",
				"will re-enchant it for me.",
				"",
				"I have the <red>charged consecration seed<blue>.",
				"I need to head to the treasure room under the <red>waterfall <blue>and",
				"plant the <red>consecration seed <blue>near the <red>chalice <blue>in order to",
				"free Glarial's spirit.",
				"",
				"I have freed <red>Glarial's spirit <blue>by planting the consecration seed near",
				"the chalice under the waterfall. The seed I planted",
				"vanished. I should go speak to <red>Eluned <blue>again.");
			writeJournal(player, ++line,
				"<col=FF0000>QUEST COMPLETE!</col>");
			break;
		}
	}

	@Override
	public QuestRequirement[] getQuestRequirements(Player player) {
		return new QuestRequirement[]{
			new QuestRequirement(player.getQuestRepository().getQuest(WaterFall.NAME), "<red>I must have completed the Waterfall quest"),
			new QuestRequirement("<red>I must be able to kill a level 84 moss giant unarmed"),
		};
	}

	@Override
	public String getRewardComponentTitle() {
		return "You have completed Roving Elves!";
	}

	@Override
	public Item getRewardComponentItem() {
		return CRYSTAL_BOW_FULL;
	}

	@Override
	public QuestReward[] getQuestRewards() {
		return new QuestReward[]{
			new QuestReward("Used elf equipment"),
			new QuestReward(Skills.STRENGTH, 10000),
		};
	}

	@Override
	public void start(Player player) {
		super.start(player);
	}

	@Override
	public Quest newInstance(Object object) {
		PluginManager.definePlugin(new RovingElvesPlugin());
		PluginManager.definePlugin(new RovingElvesObstacles());
		PluginManager.definePlugin(new ElunedDialogue());
		PluginManager.definePlugin(new IslwynDialogue());
		PluginManager.definePlugin(new MossGiantGuardianNPC());
		return this;
	}

}
