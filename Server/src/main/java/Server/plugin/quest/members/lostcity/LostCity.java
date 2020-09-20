package plugin.quest.members.lostcity;

import core.game.node.entity.player.link.quest.QuestRequirement;
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
	 * The name of the quest.
	 */
	public static final String NAME = "Lost City";

	/**
	 * The dramen staff item.
	 */
	private static final Item DRAMEN_STAFF = new Item(772);

	/**
	 * Constructs a new {@code LostCity} {@code Object}.
	 */
	public LostCity() {
		super(
			NAME,
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
				"",
				BLUE + "To complete this quest I will need:");
			line = writeJournal(player, line, getQuestRequirementsJournal(player));
			writeJournal(player, line,
				BLUE + "and be able to defeat a " + RED + "Level 101 Spirit without weapons"
			);
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
			if (player.hasItem(DRAMEN_STAFF)) {
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

	@Override
	public QuestRequirement[] getQuestRequirements(Player player) {
		return new QuestRequirement[]{
			new QuestRequirement(Skills.CRAFTING, 31),
			new QuestRequirement(Skills.WOODCUTTING, 36),
		};
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
