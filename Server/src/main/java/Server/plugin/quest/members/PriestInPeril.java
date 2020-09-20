package plugin.quest.members;

import core.game.node.entity.player.link.quest.QuestReward;
import core.game.node.entity.player.link.quest.QuestRewardComponentItem;
import plugin.skill.Skills;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.quest.Quest;
import core.plugin.InitializablePlugin;
import core.game.node.item.Item;

/**
 * Represents the <b>Quest</b> priest in peril.
 * @author 'Vexia
 */
@InitializablePlugin
public class PriestInPeril extends Quest {

	/**
	 * The name of the quest.
	 */
	public static final String NAME = "Priest in Peril";

	/**
	 * Constructs a new {@code PriestInPeril} {@code Object}.
	 */
	public PriestInPeril() {
		super(
			NAME,
			99,
			98,
			1,
			302, 0, 1, 100
		);
	}

	@Override
	public void drawJournal(Player player, int stage) {
		super.drawJournal(player, stage);
		switch (stage) {
		case 0:
			writeJournal(player,
				"<blue>I can start this quest by speaking to <red>King Roald <blue>in <red>Varrock",
				"<red>Palace.",
				"",
				"<blue>I must be able to defeat a <red>level 30 enemy.");
			break;
		case 10:
			writeJournal(player,
				"<str>I spoke to King Roald who asked me to investigate why his",
				"<str>friend Priest Drezel has stopped communicating with him.",
				"",
				"<red>Drezel <blue>lives in a <red>temple <blue>to the east of Varrock Palace. I",
				"<blue>should head there and <red>investigate <blue>what's happened to him");
			break;
		case 11:
			writeJournal(player,
				"<str>I spoke to King Roald who asked me to investigate why his",
				"<str>friend Priest Drezel has stopped communicating with him.",
				"<str>I headed to the temple where Drezel lives, but it was all",
				"<str>locked shut. I spoke through the locked door to Drezel.",
				"<blue>He told me that there was an annoying <red>dog <blue>below the",
				"<blue>temple, and has asked me to <red>kill it <blue>for him.");
			break;
		case 12:
			writeJournal(player,
				"<str>I spoke to King Roald who asked me to investigate why his",
				"<str>friend Priest Drezel has stopped communicating with him.",
				"<str>I headed to the temple where Drezel lives, but it was all",
				"<str>locked shut. I spoke through the locked door to Drezel.",
				"<str>He told me that there was an annoying dog below the",
				"<str>temple, and asked me to kill it, which I did easily.",
				"",
				"<blue>I should tell <red>King Roald <blue>everything's fine with <red>Drezel <blue>now I",
				"<blue>have killed that <red>dog <blue>for him, and claim my <red>reward.");
			break;
		case 13:
			writeJournal(player,
				"<str>I spoke to King Roald who asked me to investigate why his",
				"<str>friend Priest Drezel has stopped communicating with him.",
				"<str>I headed to the temple where Drezel lives, but it was all",
				"<str>locked shut. I spoke through the locked door to Drezel.",
				"<str>He told me that there was an annoying dog below the",
				"<str>temple, and asked me to kill it, which I did easily.",
				"<str>When I told Roald what I had done, he was furious. The",
				"<str>person who told me to kill the dog wasn't Drezel at all!",
				"",
				"<blue>I must return to the <red>temple <blue>and find out what happened to",
				"<blue>the real <red>Drezel<blue>, or the King will have me executed!");
			break;
		case 14:
			writeJournal(player,
				"<str>I spoke to King Roald who asked me to investigate why his",
				"<str>friend Priest Drezel has stopped communicating with him.",
				"<str>I headed to the temple where Drezel lives, but it was all",
				"<str>locked shut. I spoke through the locked door to Drezel.",
				"<str>He told me that there was an annoying dog below the",
				"<str>temple, and asked me to kill it, which I did easily.",
				"<str>When I told Roald what I had done, he was furious. The",
				"<str>person who told me to kill the dog wasn't Drezel at all!",
				"<str>I returned to the temple and found the real Drezel locked",
				"<str>in a makeshift cell upstairs, guarded by a vampire.",
				"",
				"<blue>I need to find the <red>key <blue>to his cell and free him!");
			break;
		case 15:
			writeJournal(player,
				"<str>I spoke to King Roald who asked me to investigate why his",
				"<str>friend Priest Drezel has stopped communicating with him.",
				"<str>I headed to the temple where Drezel lives, but it was all",
				"<str>locked shut. I spoke through the locked door to Drezel.",
				"<str>He told me that there was an annoying dog below the",
				"<str>temple, and asked me to kill it, which I did easily.",
				"<str>When I told Roald what I had done, he was furious. The",
				"<str>person who told me to kill the dog wasn't Drezel at all!",
				"<str>I returned to the temple and found the real Drezel locked",
				"<str>in a makeshift cell upstairs, guarded by a vampire.",
				"<str>I used a key from the monument to open the cell door",
				"<blue>but I still have to do something about that <red>vampire");
			break;
		case 16:
			writeJournal(player,
				"<str>I spoke to King Roald who asked me to investigate why his",
				"<str>friend Priest Drezel has stopped communicating with him.",
				"<str>I headed to the temple where Drezel lives, but it was all",
				"<str>locked shut. I spoke through the locked door to Drezel.",
				"<str>He told me that there was an annoying dog below the",
				"<str>temple, and asked me to kill it, which I did easily.",
				"<str>When I told Roald what I had done, he was furious. The",
				"<str>person who told me to kill the dog wasn't Drezel at all!",
				"<str>I returned to the temple and found the real Drezel locked",
				"<str>in a makeshift cell upstairs, guarded by a vampire.",
				"<str>I used a key from the monument to open the cell door and",
				"<str>used Holy Water to trap the vampire in his coffin.",
				"<blue>I should speak to <red>Drezel <blue>again.");
			break;
		case 17:
			writeJournal(player,
				"<str>I spoke to King Roald who asked me to investigate why his",
				"<str>friend Priest Drezel has stopped communicating with him.",
				"<str>I headed to the temple where Drezel lives, but it was all",
				"<str>locked shut. I spoke through the locked door to Drezel.",
				"<str>He told me that there was an annoying dog below the",
				"<str>temple, and asked me to kill it, which I did easily.",
				"<str>When I told Roald what I had done, he was furious. The",
				"<str>person who told me to kill the dog wasn't Drezel at all!",
				"<str>I returned to the temple and found the real Drezel locked",
				"<str>in a makeshift cell upstairs, guarded by a vampire.",
				"<str>I used a key from the monument to open the cell door and",
				"<str>used Holy Water to trap the vampire in his coffin.",
				"<blue>I should head downstairs to the <red>monument <blue>like <red>Drezel",
				"<blue>asked me to, and asses what <blue>damage <blue>has been done.");
			break;
		case 18:
			int amt = player.getGameAttributes().getAttribute("priest-in-peril:rune", 50+ 7);
			writeJournal(player,
				"<str>I spoke to King Roald who asked me to investigate why his",
				"<str>friend Priest Drezel has stopped communicating with him.",
				"<str>I headed to the temple where Drezel lives, but it was all",
				"<str>locked shut. I spoke through the locked door to Drezel.",
				"<str>He told me that there was an annoying dog below the",
				"<str>temple, and asked me to kill it, which I did easily.",
				"<str>When I told Roald what I had done, he was furious. The",
				"<str>person who told me to kill the dog wasn't Drezel at all!",
				"<str>I returned to the temple and found the real Drezel locked",
				"<str>in a makeshift cell upstairs, guarded by a vampire.",
				"<str>I used a key from the monument to open the cell door and",
				"<str>used Holy Water to trap the vampire in his coffin.",
				"<str>I followed Drezel downstairs only to find that the Salve",
				"<str>had been contaminated and now needed purifying",
				"",
				"<blue>I need to bring <red>" + amt + " <blue>rune essence to undo the damage",
				"<blue>done by the Zamorakians and <red>purify the salve");
			break;
		case 100:
			writeJournal(player,
				"<str>I spoke to King Roald who asked me to investigate why his",
				"<str>friend Priest Drezel has stopped communicating with him.",
				"<str>I headed to the temple where Drezel lives, but it was all",
				"<str>locked shut. I spoke through the locked door to Drezel.",
				"<str>He told me that there was an annoying dog below the",
				"<str>temple, and asked me to kill it, which I did easily.",
				"<str>When I told Roald what I had done, he was furious. The",
				"<str>person who told me to kill the dog wasn't Drezel at all!",
				"<str>I returned to the temple and found the real Drezel locked",
				"<str>in a makeshift cell upstairs, guarded by a vampire.",
				"<str>I used a key from the monument to open the cell door and",
				"<str>used Holy Water to trap the vampire in his coffin.",
				"<str>I followed Drezel downstairs only to find that the Salve",
				"<str>had been contaminated and now needed purifying",
				"<str>I brought Drezel fifty rune essences and the",
				"<str>contaminants were dissolved from the Salve, and Drezel",
				"<str>Rewarded me for all of my help with an ancient holy weapon",
				"<str>to fight with.",
				"",
				"<col=FF0000>QUEST COMPLETE!");
			break;
		}
	}

	@Override
	public void finish(Player player) {
		super.finish(player);
		player.removeAttribute("priest_in_peril:key");
		player.removeAttribute("priest-in-peril:rune");
	}

	@Override
	public QuestRewardComponentItem getRewardComponentItem() {
		return new QuestRewardComponentItem(2952, 240);
	}

	@Override
	public QuestReward[] getQuestRewards(Player player) {
		return new QuestReward[]{
			new QuestReward(Skills.PRAYER, 1406),
			new QuestReward(new Item(2952), "Wolfbane dagger"),
			new QuestReward("Route to Canifis"),
		};
	}

	@Override
	public Quest newInstance(Object object) {
		return this;
	}
}
