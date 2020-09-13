package plugin.quest.free.therestlessghost;

import core.game.node.entity.player.link.quest.QuestReward;
import core.game.node.entity.player.link.quest.QuestRewardComponentItem;
import plugin.quest.members.animalmagnetism.OldCronDialogue;
import plugin.skill.Skills;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.quest.Quest;
import core.game.node.item.Item;
import core.plugin.InitializablePlugin;
import core.plugin.PluginManager;

/**
 * Represents the restless ghost quest.
 * @author Vexia
 * 
 */
@InitializablePlugin
public class RestlessGhost extends Quest {

	/**
	 * The name of the quest.
	 */
	public static final String NAME = "The Restless Ghost";

	/**
	 * The ghost speak amulet.
	 */
	public static final Item AMULET = new Item(552);
	
	/**
	 * Constructs a new {@Code RestlessGhost} {@Code Object}
	 */
	public RestlessGhost() {
		super(
			NAME,
			25,
			24,
			1,
			107, 0, 4, 5
		);
	}

	@Override
	public Quest newInstance(Object object) {
		PluginManager.definePlugin(new OldCronDialogue());
		return this;
	}

	@Override
	public void drawJournal(Player player, int stage) {
		super.drawJournal(player, stage);
		switch (stage) {
			case 0:
				writeJournal(player,
					"<blue>I can start this quest by speaking to <red>Father Aereck <blue>in the",
					"<red>church <blue>next to <red>Lumbridge Castle.<red>",
					"<blue>I must be unafraid of a <red>Level 13 Skeleton.");
				break;
			case 10:
				writeJournal(player,
					"<str>Father Aereck asked me to help him deal with the Ghost in",
					"<str>the graveyard next to the church.",
					"<blue>I should find <red>Father Urhney <blue>who is an expert on <red>ghosts.",
					"<blue>He lives in a <red>shack <blue>in <red>Lumbridge Swamp.");
				break;
			case 20:
				writeJournal(player,
					"<str>Father Aereck asked me to help him deal with the Ghost in",
					"<str>the graveyard next to the church.",
					"<str>I should find Father Urhney who is an expert on ghosts.",
					"<str>He lives in a shack in Lumbridge Swamp.",
					"<blue>I should talk to the <red>Ghost <blue>to find out why it is haunting the",
					"<red>graveyard crypt.");
				break;
			case 30:
				writeJournal(player,
					"<str>Father Aereck asked me to help him deal with the Ghost in",
					"<str>the graveyard next to the church.",
					"<str>I found Father Urhney in the swamp south of Lumbridge. He",
					"<str>gave me an Amulet of Ghostspeak to talk to the ghost.",
					"<str>I spoke to the Ghost and he told me he could not rest in",
					"<str>peace because an evil wizard had stolen his skull.",
					"<blue>I should go and search the <red>Wizard's Tower South West of",
					"<red>Lumbridge <blue>for the <red>Ghost's Skull.");
				break;
			case 40:
				writeJournal(player,
					"<str>Father Aereck asked me to help him deal with the Ghost in",
					"<str>the graveyard next to the church.",
					"<str>I found Father Urhney in the swamp south of Lumbridge. He",
					"<str>gave me an Amulet of Ghostspeak to talk to the ghost.",
					"<str>I spoke to the Ghost and he told me he could not rest in",
					"<str>peace because an evil wizard had stolen his skull.",
					"<str>I found the Ghost's Skull in the basement of the Wizards'",
					"<str>Tower. It was guarded by a skeleton, but I took it anyways.",
					"<blue>I should take the <red>Skull <blue>back to the <red>Ghost <blue>so it can rest.");
				break;
			case 100:
				writeJournal(player,
					"<str>Father Aereck asked me to help him deal with the Ghost in",
					"<str>the graveyard next to the church.",
					"<str>I found Father Urhney in the swamp south of Lumbridge. He",
					"<str>gave me an Amulet of Ghostspeak to talk to the ghost.",
					"<str>I spoke to the Ghost and he told me he could not rest in", 
					"<str>peace because an evil wizard had stolen his skull.",
					"<str>I found the Ghost's Skull in the basement of the Wizards'",
					"<str>Tower. It was guarded by a skeleton, but I took it anyways.", 
					"<str>I placed the Skull in the Ghost's coffin, and allowed it to",
					"<str>rest in peace once more, with gratitude for my help.",
					"",
					"<col=FF0000>QUEST COMPLETE!");
				break;
		}
	}

	@Override
	public void finish(Player player) {
		super.finish(player);
		player.getInterfaceManager().closeChatbox();
		player.getConfigManager().set(728, 31, true);
		player.getGameAttributes().removeAttribute("restless-ghost:urhney");
	}

	@Override
	public QuestRewardComponentItem getRewardComponentItem() {
		return new QuestRewardComponentItem(964, 240);
	}

	@Override
	public QuestReward[] getQuestRewards(Player player) {
		return new QuestReward[]{
			new QuestReward(Skills.PRAYER, 1125),
		};
	}
}
