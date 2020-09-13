package plugin.quest.members;

import core.game.component.CloseEvent;
import core.game.component.Component;
import core.game.node.entity.player.link.quest.QuestReward;
import core.game.node.entity.player.link.quest.QuestRewardComponentItem;
import plugin.skill.Skills;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.plugin.InitializablePlugin;
import core.game.node.entity.player.link.quest.Quest;

/**
 * The main type for the druidic ritual quest.
 * @author Vexia
 * 
 */
@InitializablePlugin
public class DruidicRitual extends Quest {

	/**
	 * Constructs a new {@code DruidicRitual} {@code Object}.
	 */
	public DruidicRitual() {
		super(
			"Druidic Ritual",
			48,
			47,
			4,
			80, 0, 3, 4
		);
	}

	@Override
	public void drawJournal(Player player, int stage) {
		super.drawJournal(player, stage);
		switch(stage) {
			case 0:
				writeJournal(player,
					"<blue>I can start this quest by speaking to the <red>Kaqemeex <blue>who is at",
					"<blue>the <red>Druids Circle <blue>just <red>North <blue>of <red>Taverley.");
				break;
			case 10:
				writeJournal(player,
					"<str>I told Kaqemeex I would help them prepare their ceremony.",
					"<blue>I should speak to <red>Sanfew <blue>in the village to the <red>South.");
				break;
			case 20:
				writeJournal(player,
					"<str>I told Kaqemeex I would help them prepare their ceremony.",
					"",
					"<red>Sanfew <blue>told me for the ritual they would need me to place",
					"<red>raw bear meat, raw chicken, raw rat meat, <blue>and <red>raw beef <blue>in",
					"<blue>the <red>Cauldron of Thunder in the <blue>dungeon South of <red>Taverley.");
				break;
			case 99:
				writeJournal(player,
					"<str>I told Kaqemeex I would help them prepare their ceremony.",
					"<str>The ceremony required various meats being placed in the",
					"<str>Cauldron of Thunder. I did this and gave them to Sanfew.",
					"<blue>I should speak to <red>Kaqemeex <blue>again and claim my <red>reward.");
				break;
			case 100:
				writeJournal(player,
					"<str>I told Kaqemeex I would help them prepare their ceremony.",
					"<str>The ceremony required various meats being placed in the",
					"<str>Cauldron of Thunder. I did this and gave them to Sanfew.",
					"<str>Kaqemeex then taught me the basics of the skill Herblore.",
					"",
					"<col=FF0000>QUEST COMPLETE!");
				break;
		}
	}

	@Override
	public void finish(Player player) {
		super.finish(player);
		player.getInterfaceManager().open(new Component(277).setCloseEvent(new CloseEvent() {
			@Override
			public boolean close(Player player, Component c) {
				if (player != null) {
					player.getDialogueInterpreter().open(455, NPC.create(455, player.getLocation()), true);
				}
				return true;
			}
		}));
		player.getInterfaceManager().closeChatbox();
	}

	@Override
	public QuestRewardComponentItem getRewardComponentItem() {
		return new QuestRewardComponentItem(249, 240);
	}

	@Override
	public QuestReward[] getQuestRewards(Player player) {
		return new QuestReward[]{
			new QuestReward(Skills.HERBLORE, 250),
			new QuestReward("Access to Herblore skill"),
		};
	}

	@Override
	public Quest newInstance(Object object) {
		// TODO Auto-generated method stub
		return this;
	}
}
