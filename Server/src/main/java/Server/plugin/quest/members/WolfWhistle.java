package plugin.quest.members;

import core.game.node.entity.player.link.quest.QuestReward;
import core.game.node.entity.player.link.quest.QuestRewardComponentItem;
import plugin.skill.Skills;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.quest.Quest;
import core.plugin.InitializablePlugin;
import core.game.node.item.Item;

/**
 * Represents the demon slayer quest.
 * @author 'Vexia
 */
@InitializablePlugin
public class WolfWhistle extends Quest {

	/**
	 * The wolf bones item.
	 */
	public static final Item WOLF_BONES = new Item(2859, 2);

	/**
	 * Constructs a new {@code WolfWhistle} {@code Object}.
	 */
	public WolfWhistle() {
		super(
			"Wolf Whistle",
			146,
			145,
			1
		);
	}

	@Override
	public void drawJournal(Player player, int stage) {
		super.drawJournal(player, stage);
		switch (stage) {
		case 0:
			writeJournal(player,
				BLUE + "I can begin this quest by talking to " + RED + "Pikkupstix" + BLUE + ", who lives in",
				RED + "Taverley.");
			break;
		case 10:
			writeJournal(player,
				"<blue>Having spoken to <red>Pikkupstix<blue>, it seems that all I have to do",
				"<blue>is get rid of the <red>little rabbit upstairs in his house.");
			break;
		case 20:
			writeJournal(player,
				"<str>Having spoken to Pikkupstix, it seems that all I have to do",
				"<str>is get rid of the little rabbit upstairs in his house.",
				"",
				"<blue>It appears that I have underestimated the rabbit in this",
				"<blue>case; it is some <red>huge rabbit-wolf-monster-bird-thing<blue>. I",
				"<blue>think I should speak to <red>Pikkupstix<blue> to find out what is going",
				"<blue>on.");
			break;
		case 30:
			writeJournal(player,
				"<str>Having spoken to Pikkupstix, it seems that all I have to do",
				"<str>is get rid of the little rabbit upstairs in his house.",
				"<str>It appears that I have underestimated the rabbit in this",
				"<str>case; it is some huge rabbit-wolf-monster-bird-thing. I",
				"<str>think I should speak to Pikkupstix to find out what is going",
				"<str>on.",
				"",
				"<blue>I have spoken to <red>Pikkupstix<blue>, who has promised to teach me",
				"<blue>the secrets of <red>Summoning<blue> if I can help dismiss the <red>giant",
				"<red>wolpertinger<blue>. To do this, I need to bring him <red>2 lots of wolf",
				"<red>bones<blue>.",
				(player.getInventory().containsItem(WOLF_BONES) ? "<str>" : "<blue>") + "I need to get 2 lots of wolf bones.");
			break;
		case 40:
			writeJournal(player,
				"<str>Having spoken to Pikkupstix, it seems that all I have to do",
				"<str>is get rid of the little rabbit upstairs in his house.",
				"<str>It appears that I have underestimated the rabbit in this",
				"<str>case; it is some huge rabbit-wolf-monster-bird-thing. I",
				"<str>think I should speak to Pikkupstix to find out what is going",
				"<str>on.",
				"",
				"<str>I have spoken to Pikkupstix, who has promised to teach me",
				"<str>the secrets of Summoning if I can help dismiss the giant",
				"<str>wolpertinger. To do this, I need to bring him 2 lots of wolf",
				"<str>bones.",
				"<str>I have given Pikkupstix all of the items he requested.",
				"",
				"<blue>Pikkupstix has given me some<red> gold charms<blue>, <red>spirit shards",
				"<blue>and <red>pouches<blue>, with which to make a <red>spirit wolf pouch <blue>and",
				"<blue>some <red>Howl scrolls<blue>. I will then be able to use them to dismiss",
				"<blue>the <red>giant wolpertinger<blue>.",
				"<blue>I need to open the <red>trapdoor<blue> with the <red>trapdoor key<blue> that I",
				"<blue>have been given.");
			break;
		case 50:
			writeJournal(player,
				"<str>Having spoken to Pikkupstix, it seems that all I have to do",
				"<str>is get rid of the little rabbit upstairs in his house.",
				"<str>It appears that I have underestimated the rabbit in this",
				"<str>case; it is some huge rabbit-wolf-monster-bird-thing. I",
				"<str>think I should speak to Pikkupstix to find out what is going",
				"<str>on.",
				"",
				"<str>I have spoken to Pikkupstix, who has promised to teach me",
				"<str>the secrets of Summoning if I can help dismiss the giant",
				"<str>wolpertinger. To do this, I need to bring him 2 lots of wolf",
				"<str>bones.",
				"",
				"<blue>Pikkupstix has given me some<red> gold charms<blue>, <red>spirit shards",
				"<blue>and <red>pouches<blue>, with which to make a <red>spirit wolf pouch <blue>and",
				"<blue>some <red>Howl scrolls<blue>. I will then be able to use them to dismiss",
				"<blue>the <red>giant wolpertinger<blue>.",
				"<blue>I have infused the 2 spirit wolf pouches, but I need to",
				"<blue>transform one of them into scrolls at the obelisk.");
			break;
		case 60:
			writeJournal(player,
				"<str>Having spoken to Pikkupstix, it seems that all I have to do",
				"<str>is get rid of the little rabbit upstairs in his house.",
				"<str>It appears that I have underestimated the rabbit in this",
				"<str>case; it is some huge rabbit-wolf-monster-bird-thing. I",
				"<str>think I should speak to Pikkupstix to find out what is going",
				"<str>on.",
				"",
				"<str>I have spoken to Pikkupstix, who has promised to teach me",
				"<str>the secrets of Summoning if I can help dismiss the giant",
				"<str>wolpertinger. To do this, I need to bring him 2 lots of wolf",
				"<str>bones.",
				"",
				"<blue>Pikkupstix has given me some<red> gold charms<blue>, <red>spirit shards",
				"<blue>and <red>pouches<blue>, with which to make a <red>spirit wolf pouch <blue>and",
				"<blue>some <red>Howl scrolls<blue>. I will then be able to use them to dismiss",
				"<blue>the <red>giant wolpertinger<blue>.",
				"<str>I have infused the 2 spirit wolf pouches, but I need to",
				"<str>transform one of them into scrolls at the obelisk.",
				"<blue>I have dismissed the <red>giant wolpertinger<blue>.");
			break;
		case 100:
			writeJournal(player,
				"<str>Having spoken to Pikkupstix, it seems that all I have to do",
				"<str>is get rid of the little rabbit upstairs in his house.",
				"<str>It appears that I have underestimated the rabbit in this",
				"<str>case; it is some huge rabbit-wolf-monster-bird-thing. I",
				"<str>think I should speak to Pikkupstix to find out what is going",
				"<str>on.",
				"",
				"<str>I have spoken to Pikkupstix, who has promised to teach me",
				"<str>the secrets of Summoning if I can help dismiss the giant",
				"<str>wolpertinger. To do this, I need to bring him 2 lots of wolf",
				"<str>bones.",
				"",
				"<blue>Pikkupstix has given me some<red> gold charms<blue>, <red>spirit shards",
				"<blue>and <red>pouches<blue>, with which to make a <red>spirit wolf pouch <blue>and",
				"<blue>some <red>Howl scrolls<blue>. I will then be able to use them to dismiss",
				"<blue>the <red>giant wolpertinger<blue>.",
				"<str>I have infused the 2 spirit wolf pouches, but I need to",
				"<str>transform one of them into scrolls at the obelisk.",
				"<blue>I have dismissed the <red>giant wolpertinger<blue>.",
				"",
				"<col=FF0000>QUEST COMPLETE!");
			break;
		}
	}

	@Override
	public void finish(Player player) {
		super.finish(player);
		player.removeAttribute("searched-body");
		player.getInterfaceManager().openInfoBars(); // WHATS THIS DO
	}

	@Override
	public int[] getConfig(Player player, int stage) {
		int val = player.getConfigManager().get(1178);
		boolean open = val >= 4096;
		boolean closed = val == 2048;
		if (stage == 100) {
			if (val == 5 || val == 0) {
				return new int[] { 1178, 32989 };
			} else if (val == 4101) {
				return new int[] { 1178, 28893 };
			}
			return new int[] { 1178, val };
		}
		return new int[] { 1178, stage > 0 ? 5 + (open ? 4096 : 0) : stage >= 100 ? !closed ? 28893 : 32989 : 0 };
	}

	@Override
	public QuestRewardComponentItem getRewardComponentItem() {
		return new QuestRewardComponentItem(12047, 230);
	}

	@Override
	public QuestReward[] getQuestRewards(Player player) {
		return new QuestReward[]{
			new QuestReward(Skills.SUMMONING, 276),
			new QuestReward("Access to the Summoning"),
			new QuestReward("skill."),
			new QuestReward(new Item(12158, 275)),
		};
	}

	@Override
	public Quest newInstance(Object object) {
		return this;
	}
}
