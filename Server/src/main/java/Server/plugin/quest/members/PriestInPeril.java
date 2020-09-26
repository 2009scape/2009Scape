package plugin.quest.members;

import core.game.node.entity.player.link.quest.QuestReward;
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
		int line;
		switch (stage) {
		case 0:
			writeJournal(player,
				"I can start this quest by speaking to <red>King Roald <blue>in <red>Varrock",
				"<red>Palace.",
				"",
				"I must be able to defeat a <red>level 30 enemy.");
			break;
		case 10:
			line = writeJournal(player, true,
				"I spoke to King Roald who asked me to investigate why his",
				"friend Priest Drezel has stopped communicating with him.",
				"");
			writeJournal(player, line,
				"<red>Drezel <blue>lives in a <red>temple <blue>to the east of Varrock Palace. I",
				"should head there and <red>investigate <blue>what's happened to him");
			break;
		case 11:
			line = writeJournal(player, true,
				"I spoke to King Roald who asked me to investigate why his",
				"friend Priest Drezel has stopped communicating with him.",
				"I headed to the temple where Drezel lives, but it was all",
				"locked shut. I spoke through the locked door to Drezel.");
			writeJournal(player, line,
				"He told me that there was an annoying <red>dog <blue>below the",
				"temple, and has asked me to <red>kill it <blue>for him.");
			break;
		case 12:
			line = writeJournal(player, true,
				"I spoke to King Roald who asked me to investigate why his",
				"friend Priest Drezel has stopped communicating with him.",
				"I headed to the temple where Drezel lives, but it was all",
				"locked shut. I spoke through the locked door to Drezel.",
				"He told me that there was an annoying dog below the",
				"temple, and asked me to kill it, which I did easily.",
				"");
			writeJournal(player, line,
				"I should tell <red>King Roald <blue>everything's fine with <red>Drezel <blue>now I",
				"have killed that <red>dog <blue>for him, and claim my <red>reward.");
			break;
		case 13:
			line = writeJournal(player, true,
				"I spoke to King Roald who asked me to investigate why his",
				"friend Priest Drezel has stopped communicating with him.",
				"I headed to the temple where Drezel lives, but it was all",
				"locked shut. I spoke through the locked door to Drezel.",
				"He told me that there was an annoying dog below the",
				"temple, and asked me to kill it, which I did easily.",
				"When I told Roald what I had done, he was furious. The",
				"person who told me to kill the dog wasn't Drezel at all!",
				"");
			writeJournal(player, line,
				"I must return to the <red>temple <blue>and find out what happened to",
				"the real <red>Drezel<blue>, or the King will have me executed!");
			break;
		case 14:
			line = writeJournal(player, true,
				"I spoke to King Roald who asked me to investigate why his",
				"friend Priest Drezel has stopped communicating with him.",
				"I headed to the temple where Drezel lives, but it was all",
				"locked shut. I spoke through the locked door to Drezel.",
				"He told me that there was an annoying dog below the",
				"temple, and asked me to kill it, which I did easily.",
				"When I told Roald what I had done, he was furious. The",
				"person who told me to kill the dog wasn't Drezel at all!",
				"I returned to the temple and found the real Drezel locked",
				"in a makeshift cell upstairs, guarded by a vampire.",
				"");
			writeJournal(player, line,
				"I need to find the <red>key <blue>to his cell and free him!");
			break;
		case 15:
			line = writeJournal(player, true,
				"I spoke to King Roald who asked me to investigate why his",
				"friend Priest Drezel has stopped communicating with him.",
				"I headed to the temple where Drezel lives, but it was all",
				"locked shut. I spoke through the locked door to Drezel.",
				"He told me that there was an annoying dog below the",
				"temple, and asked me to kill it, which I did easily.",
				"When I told Roald what I had done, he was furious. The",
				"person who told me to kill the dog wasn't Drezel at all!",
				"I returned to the temple and found the real Drezel locked",
				"in a makeshift cell upstairs, guarded by a vampire.",
				"I used a key from the monument to open the cell door");
			writeJournal(player, line,
				"but I still have to do something about that <red>vampire");
			break;
		case 16:
			line = writeJournal(player, true,
				"I spoke to King Roald who asked me to investigate why his",
				"friend Priest Drezel has stopped communicating with him.",
				"I headed to the temple where Drezel lives, but it was all",
				"locked shut. I spoke through the locked door to Drezel.",
				"He told me that there was an annoying dog below the",
				"temple, and asked me to kill it, which I did easily.",
				"When I told Roald what I had done, he was furious. The",
				"person who told me to kill the dog wasn't Drezel at all!",
				"I returned to the temple and found the real Drezel locked",
				"in a makeshift cell upstairs, guarded by a vampire.",
				"I used a key from the monument to open the cell door and",
				"used Holy Water to trap the vampire in his coffin.");
			writeJournal(player, line,
				"I should speak to <red>Drezel <blue>again.");
			break;
		case 17:
			line = writeJournal(player, true,
				"I spoke to King Roald who asked me to investigate why his",
				"friend Priest Drezel has stopped communicating with him.",
				"I headed to the temple where Drezel lives, but it was all",
				"locked shut. I spoke through the locked door to Drezel.",
				"He told me that there was an annoying dog below the",
				"temple, and asked me to kill it, which I did easily.",
				"When I told Roald what I had done, he was furious. The",
				"person who told me to kill the dog wasn't Drezel at all!",
				"I returned to the temple and found the real Drezel locked",
				"in a makeshift cell upstairs, guarded by a vampire.",
				"I used a key from the monument to open the cell door and",
				"used Holy Water to trap the vampire in his coffin.");
			writeJournal(player, line,
				"I should head downstairs to the <red>monument <blue>like <red>Drezel",
				"asked me to, and asses what <blue>damage <blue>has been done.");
			break;
		case 18:
			int amt = player.getGameAttributes().getAttribute("priest-in-peril:rune", 50+ 7);
			line = writeJournal(player, true,
				"I spoke to King Roald who asked me to investigate why his",
				"friend Priest Drezel has stopped communicating with him.",
				"I headed to the temple where Drezel lives, but it was all",
				"locked shut. I spoke through the locked door to Drezel.",
				"He told me that there was an annoying dog below the",
				"temple, and asked me to kill it, which I did easily.",
				"When I told Roald what I had done, he was furious. The",
				"person who told me to kill the dog wasn't Drezel at all!",
				"I returned to the temple and found the real Drezel locked",
				"in a makeshift cell upstairs, guarded by a vampire.",
				"I used a key from the monument to open the cell door and",
				"used Holy Water to trap the vampire in his coffin.",
				"I followed Drezel downstairs only to find that the Salve",
				"had been contaminated and now needed purifying",
				"");
			writeJournal(player, line,
				"I need to bring <red>" + amt + " <blue>rune essence to undo the damage",
				"done by the Zamorakians and <red>purify the salve");
			break;
		case 100:
			line = writeJournal(player, true,
				"I spoke to King Roald who asked me to investigate why his",
				"friend Priest Drezel has stopped communicating with him.",
				"I headed to the temple where Drezel lives, but it was all",
				"locked shut. I spoke through the locked door to Drezel.",
				"He told me that there was an annoying dog below the",
				"temple, and asked me to kill it, which I did easily.",
				"When I told Roald what I had done, he was furious. The",
				"person who told me to kill the dog wasn't Drezel at all!",
				"I returned to the temple and found the real Drezel locked",
				"in a makeshift cell upstairs, guarded by a vampire.",
				"I used a key from the monument to open the cell door and",
				"used Holy Water to trap the vampire in his coffin.",
				"I followed Drezel downstairs only to find that the Salve",
				"had been contaminated and now needed purifying",
				"I brought Drezel fifty rune essences and the",
				"contaminants were dissolved from the Salve, and Drezel",
				"rewarded me for all of my help with an ancient holy weapon",
				"to fight with.");
			writeJournal(player, ++line,
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
	public Item getRewardComponentItem() {
		return new Item(2952);
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
