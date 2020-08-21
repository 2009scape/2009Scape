package plugin.quest.free;

import plugin.skill.Skills;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.quest.Quest;
import core.game.node.item.GroundItemManager;
import core.game.node.item.Item;
import core.plugin.InitializablePlugin;

/**
 * Represents the Doric's Quest
 * @author Vexia
 * 
 */
@InitializablePlugin
public class DoricsQuest extends Quest {

	/**
	 * Constructs a new {@Code DoricsQuest} {@Code Object}
	 */
	public DoricsQuest() {
		super("Doric's Quest", 17, 16, 1, 31, 0, 1, 100);
	}
	
	@Override
	public Quest newInstance(Object object) {
		return this;
	}

	@Override
	public void drawJournal(Player player, int stage) {
		super.drawJournal(player, stage);
		switch (stage) {
		case 0:
			player.getPacketDispatch().sendString("<col=08088A>I can start this quest by speaking to <col=8A0808>Doric</col> <col=08088A>who is <col=8A0808>North of", 275, 4+ 7);
			player.getPacketDispatch().sendString("<col=8A0808>Falador", 275, 5+ 7);
			player.getPacketDispatch().sendString("<col=08088A>There aren't any requirements but <col=8A0808>Level 15 Mining <col=08088A>will help", 275, 7+ 7);
			break;
		case 1:
			player.getPacketDispatch().sendString("<str>I have spoken to <col=8A0808>Doric</str>", 275, 4+ 7);
			player.getPacketDispatch().sendString("<col=08088A>I need to collect some items and bring them to <col=8A0808>Doric</col>", 275, 6+ 7);
			player.getPacketDispatch().sendString(player.getInventory().contains(434, 6) ? "<str>6 Clay</str>" : "<col=8A0808>6 Clay", 275, 7+ 7);
			player.getPacketDispatch().sendString(player.getInventory().contains(436, 4) ? "<str>4 Copper Ore</str>" : "<col=8A0808>4 Copper Ore", 275, 8+ 7);
			player.getPacketDispatch().sendString(player.getInventory().contains(440, 2) ? "<str>2 Iron Ore</str>" : "<col=8A0808>2 Iron Ore", 275, 9+ 7);
			break;
		case 100:
			player.getPacketDispatch().sendString("<str>I have spoken to <col=8A0808>Doric</str>", 275, 4+ 7);
			player.getPacketDispatch().sendString("<col=FF0000>QUEST COMPLETE!", 275, 10+ 7);
			player.getPacketDispatch().sendString("<str> I have collected some Clay, Copper Ore, and Iron Ore", 275, 6+ 7);
			player.getPacketDispatch().sendString("<str>Doric rewarded me for all my hard work", 275, 8+ 7);
			player.getPacketDispatch().sendString("<str>I can now use Doric's Anvils whenever I want", 275, 9+ 7);
			break;
		}
	}

	@Override
	public void finish(Player player) {
		super.finish(player);
		player.getPacketDispatch().sendString("1 Quest Point", 277, 8 + 2);
		player.getPacketDispatch().sendString("1300 Mining XP", 277, 9 + 2);
		player.getPacketDispatch().sendString("180 coins", 277, 10 + 2);
		player.getPacketDispatch().sendString("Use of Doric's Anvils", 277, 11 + 2);
		if (!player.getInventory().add(new Item(995, 180))) {
			GroundItemManager.create(new Item(995, 180), player.getLocation());
		}
		player.getSkills().addExperience(Skills.MINING, 1300);
		player.getPacketDispatch().sendItemZoomOnInterface(1269, 240, 277, 5);
		player.getInterfaceManager().closeChatbox();
		player.getPacketDispatch().sendString("You have completed Doric's Quest!", 277, 2 + 2);
	}

}
