package plugin.quest.members;

import plugin.skill.Skills;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.quest.Quest;
import core.game.node.item.GroundItemManager;
import core.game.node.item.Item;
import core.plugin.InitializablePlugin;
import core.tools.RandomFunction;

/**
 * Represents the gertrudes fortress quest.
 * @author 'Vexia
 */
@InitializablePlugin
public class GertrudesCat extends Quest {

	/**
	 * Constructs a new {@code GertrudesCat} {@code Object}.
	 * @param player The player to construct the class for.
	 */
	public GertrudesCat() {
		super("Gertrude's Cat", 67, 66, 1, 180, 0, 1, 100);
	}

	@Override
	public void drawJournal(Player player, int stage) {
		super.drawJournal(player, stage);
		switch (stage) {
		case 0:
			player.getPacketDispatch().sendString(BLUE+ "I can start this quest by speaking to " + RED + "Gertrude.", 275, 4+ 7);
			player.getPacketDispatch().sendString(BLUE+ "She can be found to the " + RED + "west of Varrock.", 275, 5+ 7);
			break;
		case 10:
			player.getPacketDispatch().sendString("<str>I accepted the challenge of finding Gertrude's lost cat.", 275, 4+ 7);
			player.getPacketDispatch().sendString(BLUE+ "I need " + RED + "to speak to Shilop and Wilough" + BLUE+ " at the " + RED + "marketplace.", 275, 6+ 7);
			break;
		case 20:
			player.getPacketDispatch().sendString("<str>I accepted the challenge of finding Gertrude's lost cat.", 275, 4+ 7);
			player.getPacketDispatch().sendString("<str>I spoke to Shilop, Gertrude's Son.", 275, 5+ 7);
			player.getPacketDispatch().sendString(BLUE+ "I need to " + RED + "go to their play area " + BLUE+ "and " + RED + "find the lost cat and", 275, 7+ 7);
			player.getPacketDispatch().sendString(RED + "return it to Gertrude.", 275, 8+ 7);
			break;
		case 30:
			player.getPacketDispatch().sendString("<str>I accepted the challenge of finding Gertrude's lost cat.", 275, 4+ 7);
			player.getPacketDispatch().sendString("<str>I spoke to Shilop, Gertrude's Son.", 275, 5+ 7);
			player.getPacketDispatch().sendString("<str>I found the lost cat but it won't come back.</str>", 275, 6+ 7);
			player.getPacketDispatch().sendString(RED + "I still need to " + RED + "get her to follow me home.", 275, 8+ 7);
			break;
		case 40:
		case 50:
			player.getPacketDispatch().sendString("<str>I accepted the challenge of finding Gertrude's lost cat.", 275, 4+ 7);
			player.getPacketDispatch().sendString("<str>I spoke to Shilop, Gertrude's Son.", 275, 5+ 7);
			player.getPacketDispatch().sendString("<str>I found the lost cat but it won't come back.</str>", 275, 6+ 7);
			player.getPacketDispatch().sendString("<str>I gave the cat milk and sardines.</str>", 275, 7+ 7);
			player.getPacketDispatch().sendString(BLUE+ "I still need to" + RED + "get her to follow me home.", 275, 9+ 7);
			break;
		case 60:
			player.getPacketDispatch().sendString("<str>I accepted the challenge of finding Gertrude's lost cat.", 275, 4+ 7);
			player.getPacketDispatch().sendString("<str>I spoke to Shilop, Gertrude's Son.", 275, 5+ 7);
			player.getPacketDispatch().sendString("<str>I found the lost cat but it won't come back.</str>", 275, 6+ 7);
			player.getPacketDispatch().sendString("<str>I gave the cat milk and sardines.</str>", 275, 7+ 7);
			player.getPacketDispatch().sendString(BLUE+ "She ran off home.", 275, 9+ 7);
			break;
		case 100:
			player.getPacketDispatch().sendString("<str>I helped Gertrude to find her lost cat,", 275, 4+ 7);
			player.getPacketDispatch().sendString("<str>I fed it and returned her missing kitten,", 275, 5+ 7);
			player.getPacketDispatch().sendString("<str>Gertrude gave me my very own pet for a reward.", 275, 6+ 7);
			player.getPacketDispatch().sendString("<col=FF0000>QUEST COMPLETE!", 275, 8+ 7);
			break;
		}
	}

	@Override
	public void finish(Player player) {
		super.finish(player);
		final Item kitten = getKitten();
		player.getConfigManager().set(101, player.getQuestRepository().getPoints());
		player.getPacketDispatch().sendString("1 Quest Point", 277, 8 + 2);
		player.getPacketDispatch().sendString("A kitten!", 277, 9 + 2);
		player.getPacketDispatch().sendString("1525 Cooking XP", 277, 10 + 2);
		player.getPacketDispatch().sendString("A chocolate cake", 277, 11 + 2);
		player.getPacketDispatch().sendString("A bowl of stew", 277, 12 + 2);
		player.getPacketDispatch().sendString("Raise cats.", 277, 13 + 2);
		player.getSkills().addExperience(Skills.COOKING, 1525);
		player.getPacketDispatch().sendItemZoomOnInterface(kitten.getId(), 240, 277, 3 + 2);
		setStage(player, 100);
		player.getInventory().add(kitten);
		player.getFamiliarManager().summon(kitten, true);
		final Item cake = new Item(1897);
		final Item stew = new Item(2003);
		if (!player.getInventory().add(cake)) {
			GroundItemManager.create(cake, player.getLocation(), player);
		}
		if (!player.getInventory().add(stew)) {
			GroundItemManager.create(stew, player.getLocation(), player);
		}
	}

	/**
	 * Method used to get a random kitten.
	 * @return the item.
	 */
	public Item getKitten() {
		return new Item(RandomFunction.random(1555, 1560));
	}

	@Override
	public Quest newInstance(Object object) {
		// TODO Auto-generated method stub
		return this;
	}
}
