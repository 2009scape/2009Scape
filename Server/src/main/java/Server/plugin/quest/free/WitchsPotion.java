package plugin.quest.free;

import plugin.skill.Skills;
import core.game.node.entity.player.Player;
import core.plugin.InitializablePlugin;
import core.game.node.entity.player.link.quest.Quest;

/**
 * Represents the witch's potion quest.
 * @author 'Vexia
 */
@InitializablePlugin
public class WitchsPotion extends Quest {

	/**
	 * Constructs a new {@code WitchsPotion} {@code Object}.
	 * @param player The player to construct the class for.
	 */
	public WitchsPotion() {
		super("Witch's Potion", 31, 30, 1, 67, 0, 1, 3);
	}

	@Override
	public void drawJournal(Player player, int stage) {
		super.drawJournal(player, stage);
		switch (getStage(player)) {
		case 0:
			line(player,  BLUE + "I can start this quest by speaking to " + RED + "Hetty " + BLUE + "in her house in", 4+ 7);
			line(player,  RED + "Rimmington" + BLUE + ", West of " + RED + "Port Sarim", 5+ 7);
			break;
		case 20:
			line(player, "<str>I spoke to Hetty in her house at Rimmington. hetty told me", 4+ 7);
			line(player, "<str>she could increase my magic power if I can bring her", 5+ 7);
			line(player, "<str>certain ingredients for a potion.", 6+ 7);
			line(player, BLUE + "Hetty needs me to bring her the following:", 8+ 7);
			if (player.getInventory().contains(1957, 1)) {
				line(player, "<str>I have an onion with me", 9+ 7);
			} else {
				line(player,  RED + "An onion", 9+ 7);
			}
			if (player.getInventory().contains(1957, 1)) {
				line(player, "<str>I have an onion with me", 9+ 7);
			} else {
				line(player, RED + "An onion", 9+ 7);
			}
			if (player.getInventory().contains(300, 1)) {
				line(player, "<str>I have a rat's tail with me", 10+ 7);
			} else {
				line(player, RED + "A rat's tail", 10+ 7);
			}
			if (player.getInventory().contains(2146, 1)) {
				line(player, "<str>I have a piece of burnt meat with me", 11+ 7);
			} else {
				line(player, RED + "A piece of burnt meat", 11+ 7);
			}
			if (player.getInventory().contains(221, 1)) {
				line(player, "<str>I have an eye of newt with me", 12+ 7);
			} else {
				line(player, RED + "An eye of newt", 12+ 7);
			}
			break;
		case 40:
			line(player, "<str>I brought her an onion, a rat's tail, a piece of burnt meat", 4+ 7);
			line(player, "<str>and eye of newt which she used to make a potion.", 5+ 7);
			line(player,  BLUE + "I should drink from the " + RED + "cauldron" + BLUE + " and improve my magic!", 7+ 7);
			break;
		case 100:
			line(player, "<str>I brought her an onion, a rat's tail, a piece of burnt meat", 4+ 7);
			line(player, "<str>and an eye of newt which she used to make a potion.", 5+ 7);
			line(player, "<str>I drank from the cauldron and my magic power increased!", 7+ 7);
			line(player, "<col=FF0000>QUEST COMPLETE!", 9+ 7);
			break;
		}
	}

	@Override
	public void finish(Player player) {
		super.finish(player);
		player.getConfigManager().set(101, player.getQuestRepository().getPoints());
		player.getPacketDispatch().sendString("1 Quest Point", 277, 8 + 2);
		player.getPacketDispatch().sendString("325 Magic XP", 277, 9 + 2);
		player.getSkills().addExperience(Skills.MAGIC, 325);
		player.getInterfaceManager().closeChatbox();
		player.getPacketDispatch().sendItemZoomOnInterface(221, 240, 277, 3 + 2);
	}

	@Override
	public Quest newInstance(Object object) {
		// TODO Auto-generated method stub
		return this;
	}
}
