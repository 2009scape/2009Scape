package plugin.quest.members;

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
	 * @param player The player to construct the class for.
	 */
	public WolfWhistle() {
		super("Wolf Whistle", 146, 145, 1);
	}

	@Override
	public void drawJournal(Player player, int stage) {
		super.drawJournal(player, stage);
		switch (stage) {
		case 0:
			line(player, BLUE + "I can begin this quest by talking to " + RED + "Pikkupstix" + BLUE + ", who lives in", 4+ 7);
			line(player, RED + "Taverley.", 5+ 7);
			break;
		case 10:
			line(player, "<blue>Having spoken to <red>Pikkupstix<blue>, it seems that all I have to do<br><br><blue>is get rid of the <red>little rabbit upstairs in his house.", 4+ 7);
			break;
		case 20:
			line(player, "<str>Having spoken to Pikkupstix, it seems that all I have to do<br><br><str>is get rid of the little rabbit upstairs in his house.<br><br><br><br><blue>It appears that I have underestimated the rabbit in this<br><br><blue>case; it is some <red>huge rabbit-wolf-monster-bird-thing<blue>. I<br><br><blue>think I should speak to <red>Pikkupstix<blue> to find out what is going<br><br><blue>on.", 4+ 7);
			break;
		case 30:
			line(player, "<str>Having spoken to Pikkupstix, it seems that all I have to do<br><br><str>is get rid of the little rabbit upstairs in his house.<br><br><str>It appears that I have underestimated the rabbit in this<br><br><str>case; it is some huge rabbit-wolf-monster-bird-thing. I<br><br><str>think I should speak to Pikkupstix to find out what is going<br><br><str>on.<br><br><br><br><blue>I have spoken to <red>Pikkupstix<blue>, who has promised to teach me<br><br><blue>the secrets of <red>Summoning<blue> if I can help dismiss the <red>giant<br><br><red>wolpertinger<blue>. To do this, I need to bring him <red>2 lots of wolf<br><br><red>bones<blue>.<br><br>" + (player.getInventory().containsItem(WOLF_BONES) ? "<str>" : "<blue>") + "I need to get 2 lots of wolf bones.", 4+ 7);
			break;
		case 40:
			line(player, "<str>Having spoken to Pikkupstix, it seems that all I have to do<br><br><str>is get rid of the little rabbit upstairs in his house.<br><br><str>It appears that I have underestimated the rabbit in this<br><br><str>case; it is some huge rabbit-wolf-monster-bird-thing. I<br><br><str>think I should speak to Pikkupstix to find out what is going<br><br><str>on.<br><br><br><br><str>I have spoken to Pikkupstix, who has promised to teach me<br><br><str>the secrets of Summoning if I can help dismiss the giant<br><br><str>wolpertinger. To do this, I need to bring him 2 lots of wolf<br><br><str>bones.<br><br><str>I have given Pikkupstix all of the items he requested.<br><br><br><br><blue>Pikkupstix has given me some<red> gold charms<blue>, <red>spirit shards<br><br><blue>and <red>pouches<blue>, with which to make a <red>spirit wolf pouch <blue>and<br><br><blue>some <red>Howl scrolls<blue>. I will then be able to use them to dismiss<br><br><blue>the <red>giant wolpertinger<blue>.<br><br><blue>I need to open the <red>trapdoor<blue> with the <red>trapdoor key<blue> that I<br><br><blue>have been given. ", 4+ 7);
			break;
		case 50:
			line(player, "<str>Having spoken to Pikkupstix, it seems that all I have to do<br><br><str>is get rid of the little rabbit upstairs in his house.<br><br><str>It appears that I have underestimated the rabbit in this<br><br><str>case; it is some huge rabbit-wolf-monster-bird-thing. I<br><br><str>think I should speak to Pikkupstix to find out what is going<br><br><str>on.<br><br><br><br><str>I have spoken to Pikkupstix, who has promised to teach me<br><br><str>the secrets of Summoning if I can help dismiss the giant<br><br><str>wolpertinger. To do this, I need to bring him 2 lots of wolf<br><br><str>bones.<br><br><br><br><blue>Pikkupstix has given me some<red> gold charms<blue>, <red>spirit shards<br><br><blue>and <red>pouches<blue>, with which to make a <red>spirit wolf pouch <blue>and<br><br><blue>some <red>Howl scrolls<blue>. I will then be able to use them to dismiss<br><br><blue>the <red>giant wolpertinger<blue>.<br><br><blue>I have infused the 2 spirit wolf pouches, but I need to<br><br><blue>transform one of them into scrolls at the obelisk.", 4+ 7);
			break;
		case 60:
			line(player, "<str>Having spoken to Pikkupstix, it seems that all I have to do<br><br><str>is get rid of the little rabbit upstairs in his house.<br><br><str>It appears that I have underestimated the rabbit in this<br><br><str>case; it is some huge rabbit-wolf-monster-bird-thing. I<br><br><str>think I should speak to Pikkupstix to find out what is going<br><br><str>on.<br><br><br><br><str>I have spoken to Pikkupstix, who has promised to teach me<br><br><str>the secrets of Summoning if I can help dismiss the giant<br><br><str>wolpertinger. To do this, I need to bring him 2 lots of wolf<br><br><str>bones.<br><br><br><br><blue>Pikkupstix has given me some<red> gold charms<blue>, <red>spirit shards<br><br><blue>and <red>pouches<blue>, with which to make a <red>spirit wolf pouch <blue>and<br><br><blue>some <red>Howl scrolls<blue>. I will then be able to use them to dismiss<br><br><blue>the <red>giant wolpertinger<blue>.<br><br><str>I have infused the 2 spirit wolf pouches, but I need to<br><br><str>transform one of them into scrolls at the obelisk.<br><br><blue>I have dismissed the <red>giant wolpertinger<blue>.", 4+ 7);
			break;
		case 100:
			line(player, "<str>Having spoken to Pikkupstix, it seems that all I have to do<br><br><str>is get rid of the little rabbit upstairs in his house.<br><br><str>It appears that I have underestimated the rabbit in this<br><br><str>case; it is some huge rabbit-wolf-monster-bird-thing. I<br><br><str>think I should speak to Pikkupstix to find out what is going<br><br><str>on.<br><br><br><br><str>I have spoken to Pikkupstix, who has promised to teach me<br><br><str>the secrets of Summoning if I can help dismiss the giant<br><br><str>wolpertinger. To do this, I need to bring him 2 lots of wolf<br><br><str>bones.<br><br><br><br><blue>Pikkupstix has given me some<red> gold charms<blue>, <red>spirit shards<br><br><blue>and <red>pouches<blue>, with which to make a <red>spirit wolf pouch <blue>and<br><br><blue>some <red>Howl scrolls<blue>. I will then be able to use them to dismiss<br><br><blue>the <red>giant wolpertinger<blue>.<br><br><str>I have infused the 2 spirit wolf pouches, but I need to<br><br><str>transform one of them into scrolls at the obelisk.<br><br><blue>I have dismissed the <red>giant wolpertinger<blue>.<br><br><br><br><col=FF0000>QUEST COMPLETE!", 4+ 7);
			break;
		}
	}

	@Override
	public void finish(Player player) {
		super.finish(player);
		player.getPacketDispatch().sendString("1 Quest Points", 277, 8+ 2);
		player.getPacketDispatch().sendString("276 Summoning XP", 277, 9+ 2);
		player.getPacketDispatch().sendString("Access to the Summoning", 277, 10+ 2);
		player.getPacketDispatch().sendString("skill.", 277, 11+ 2);
		player.getPacketDispatch().sendString("275 gold charms.", 277, 12+ 2);
		player.getPacketDispatch().sendItemZoomOnInterface(12047, 230, 277, 3+ 2);
		player.getSkills().addExperience(Skills.SUMMONING, 276);
		player.getInventory().add(new Item(12158, 275), player);
		player.removeAttribute("searched-body");
		player.getQuestRepository().syncronizeTab(player);
		player.getInterfaceManager().openInfoBars();
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
	public Quest newInstance(Object object) {
		return this;
	}

}
