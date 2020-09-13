package plugin.quest.members.lostcity;

import core.game.node.entity.player.link.quest.QuestReward;
import core.game.node.entity.player.link.quest.QuestRewardComponentItem;
import plugin.skill.Skills;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.quest.Quest;
import core.game.node.item.Item;
import core.plugin.InitializablePlugin;
import core.plugin.PluginManager;

/**
 * The main type for the lost city Quest.
 * @author Vexia
 * @author Aero
 */
@InitializablePlugin
public class LostCity extends Quest {
	
	/**
	 * The requirement titles.
	 */
	private static final String[] TITLES = new String[] { "Level 31 Crafting", "Level 36 Woodcutting" };

	/**
	 * The dramen staff item.
	 */
	private static final Item DRAMEN_STAFF = new Item(772);

	/**
	 * The quest requirements.
	 */
	private boolean[] questRequirements = new boolean[2];

	/**
	 * Constructs a new {@code LostCity} {@code Object}.
	 */
	public LostCity() {
		super(
			"Lost City",
			83,
			82,
			3,
			147, 0, 1, 6
		);
	}

	@Override
	public void drawJournal(Player player, int stage) {
		super.drawJournal(player, stage);
		switch (stage) {
		case 0:
			int line = writeJournal(player,
				BLUE + "I can start this quest by speaking to the Adventurers in",
				BLUE + "the Swamp just south of Lumbridge.",
				""
			);
			drawQuestRequirements(player, line);
			break;
		case 10:
			writeJournal(player,
				"<str>According to one of the adventurers in Lumbridge Swamp",
				"<str>the entrance to Zanaris is somewhere around there.",
				"<blue>Apparently there is a <red>leprechaun<blue> hiding in a <red>tree<blue> nearby",
				"<blue>who can tell me how to enter the <red>Lost City of Zanaris");
			break;
		case 20:
			writeJournal(player,
				"<str>According to one of the adventurers in Lumbridge Swamp",
				"<str>the entrance to Zanaris is somewhere around there.",
				"<str>I found the Leprechaun hiding in a nearby tree.",
				"<blue>He told me that the entrance to <red>Zanaris<blue> is in the <red>shed<blue> in",
				"<red>Lumbridge swamp<blue> but only if I am carrying a <red>Dramen Staff",
				"<blue>I can find a <red>Dramen Tree <blue>in a cave on <red>Entrana <blue>somewhere");
			break;
		case 21:
			if (player.getBank().containsItem(DRAMEN_STAFF) || player.getInventory().containsItem(DRAMEN_STAFF) || player.getEquipment().containsItem(DRAMEN_STAFF)) {
				writeJournal(player,
					"<str>According to one of the adventurers in Lumbridge Swamp",
					"<str>the entrance to Zanaris is somewhere around there.",
					"<str>I found the Leprechaun hiding in a nearby tree.",
					"<str>He told me that the entrance to Zanaris is in the shed in",
					"<str>Lumbridge swamp but only if I am carrying a Dramen Staff.",
					"<str>The Dramen Tree was guarded by a powerful Tree Spirit.",
					"<str>I cut a branch from the tree and crafted a Dramen Staff.",
					"<blue>I should enter <red>Zanaris <blue>by going to the <red>shed <blue>in <red>Lumbridge",
					"<red>Swamp <blue>while keeping the <red>Dramen staff<blue> with me");
			} else {
				writeJournal(player,
					"<str>According to one of the adventurers in Lumbridge Swamp",
					"<str>the entrance to Zanaris is somewhere around there.",
					"<str>I found the Leprechaun hiding in a nearby tree.",
					"<str>He told me that the entrance to Zanaris is in the shed in",
					"<str>Lumbridge swamp but only if I am carrying a Dramen Staff.",
					"<str>The Dramen Tree was guarded by a powerful Tree Spirit.",
					"<blue>With the <red>Spirit <blue>defeated I can cut a <red>branch <blue>from the tree");
			}
			break;
		case 100:
			writeJournal(player,
				"<str>According to one of the adventurers in Lumbridge Swamp",
				"<str>the entrance to Zanaris is somewhere around there.",
				"<str>I found the Leprechaun hiding in a nearby tree.",
				"<str>He told me that the entrance to Zanaris is in the shed in",
				"<str>Lumbridge swamp but only if I am carrying a Dramen Staff.",
				"<str>The Dramen Tree was guarded by a powerful Tree Spirit.",
				"<str>I cut a branch from the tree and crafted a Dramen Staff.",
				"<str>With the mystical Dramen Staff in my possession I was",
				"<str>able to enter Zanaris through the shed in Lumbridge",
				"<str>swamp.",
				"<br><col=FF0000>QUEST COMPLETE!");
			break;
		}
	}

	@Override
	public QuestRewardComponentItem getRewardComponentItem() {
		return new QuestRewardComponentItem(772, 235);
	}

	@Override
	public QuestReward[] getQuestRewards(Player player) {
		return new QuestReward[]{
			new QuestReward("Access to Zanaris"),
		};
	}

	/**
	 * Draws the quest requirements onto the journal component.
	 * @param player The player to draw the requirements for.
	 */
	private final void drawQuestRequirements(final Player player, int line) {
		questRequirements[0] = player.getSkills().getStaticLevelByExperience(Skills.CRAFTING) > 30;
		questRequirements[1] = player.getSkills().getStaticLevelByExperience(Skills.WOODCUTTING) > 35;
		String[] requirements = new String[questRequirements.length];
		for (int i = 0; i < questRequirements.length; i++) {
			boolean bool = !questRequirements[i];
			String str = TITLES[i];
			requirements[i] = (!bool ? "<str>" : "") + (bool ? RED + str : str);
		}
		line = writeJournal(player, line, BLUE + "To complete this quest I will need:");
		line = writeJournal(player, line, requirements);
		writeJournal(player, line, BLUE + "and be able to defeat a " + RED + "Level 101 Spirit without weapons");
	}

	@Override
	public Quest newInstance(Object object) {
		PluginManager.definePlugin(new LostCityPlugin());
		PluginManager.definePlugin(new TreeSpiritNPC());
		PluginManager.definePlugin(new ShamusDialogue());
		PluginManager.definePlugin(new WarriorDialogue());
		PluginManager.definePlugin(new DramenStaffPlugin());
		return this;
	}

}
