package plugin.quest.free;

import plugin.skill.Skills;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.quest.Quest;
import core.game.node.item.GroundItemManager;
import core.game.node.item.Item;
import core.game.node.object.GameObject;
import core.game.node.object.ObjectBuilder;
import core.game.world.map.Location;
import core.plugin.InitializablePlugin;
import core.game.world.map.RegionManager;

/**
 * Represents the imp catcher quest.
 * @author Vexia
 * 
 */
@InitializablePlugin
public class ImpCatcher extends Quest {

	/**
	 * Represents the black bead item.
	 */
	private static final Item BLACK_BEAD = new Item(1474);

	/**
	 * Represents the red bead item.
	 */
	private static final Item RED_BEAD = new Item(1470);

	/**
	 * Represents the white bead item.
	 */
	private static final Item WHITE_BEAD = new Item(1476);

	/**
	 * Represents the yellow bead item.
	 */
	private static final Item YELLOW_BEAD = new Item(1472);

	/**
	 * Represents the amulet item.
	 */
	private static final Item AMULET = new Item(1478);
	
	/**
	 * Constructs a new {@Code ImpCatcher} {@Code Object}
	 */
	public ImpCatcher() {
		super("Imp Catcher", 21, 20, 1, 160, 0, 1, 2);
	}
	
	@Override
	public Quest newInstance(Object object) {
		return this;
	}

	@Override
	public void drawJournal(Player player, int stage) {
		super.drawJournal(player, stage);
		if (getStage(player) == 0) {
			player.getPacketDispatch().sendString(BLUE + "I can start this quest by speaking to " + RED + "Wizard Mizgog " + BLUE + "who is", 275, 4+ 7);
			player.getPacketDispatch().sendString(BLUE + "in the " + RED + "Wizard's Tower", 275, 5+ 7);
			player.getPacketDispatch().sendString(BLUE + "There are no requirements for this quest.", 275, 7+ 7);
		} else if (getStage(player) == 10) {
			line(player, "<str>I have spoken to Wizard Mizgog.", 4+ 7);
			line(player,  BLUE + "I need to collect some items by killing " + RED + " Imps.", 6+ 7);
			if (player.getInventory().containItems(BLACK_BEAD.getId(), RED_BEAD.getId(), YELLOW_BEAD.getId(), WHITE_BEAD.getId())) {
				line(player, BLUE + "I have collected all the missing beads and need to return", 6+ 7);
				line(player, BLUE + "them to " + RED + "Wizard Mizgog.", 7+ 7);
				return;
			}
			if (player.getInventory().containsItem(BLACK_BEAD)) {
				line(player, "<str>1 Black Bead", 7+ 7);
			} else {
				line(player, RED + "1 Black Bead", 7+ 7);
			}
			if (player.getInventory().containsItem(RED_BEAD)) {
				line(player, "<str>1 Red Bead", 8+ 7);
			} else {
				line(player, RED + "1 Red Bead", 8+ 7);
			}
			if (player.getInventory().containsItem(WHITE_BEAD)) {
				line(player, "<str>1 White Bead", 9+ 7);
			} else {
				line(player, RED + "1 White Bead", 9+ 7);
			}
			if (player.getInventory().containsItem(YELLOW_BEAD)) {
				line(player, "<str>1 Yellow Bead", 10+ 7);
			} else {
				line(player, RED + "1 Yellow Bead", 10+ 7);
			}
		} else {
			line(player, "<str>I have spoken to Wizard Mizgog.", 4+ 7);
			line(player, "<str>I have collected all the beads.", 6+ 7);
			line(player, "<str>Wizard Mizgog thanked me for finding his beads and gave", 8+ 7);
			line(player, "<str>me and Amulet of Accuracy.", 9+ 7);
			line(player, "<col=FF0000>QUEST COMPLETE!", 10+ 7);
		}
	}

	@Override
	public void finish(Player player) {
		super.finish(player);
		player.unlock();
		player.getPacketDispatch().sendMessage("The Wizard hands you an amulet.");
		player.getPacketDispatch().sendString("1 Quest Point", 277, 8 + 2);
		player.getPacketDispatch().sendString("875 Magic XP", 277, 9 + 2);
		player.getPacketDispatch().sendString("Amulet of Accuracy", 277, 10 + 2);
		player.getPacketDispatch().sendString("You have completed the Imp Catcher Quest!", 277, 2 + 2);
		player.getPacketDispatch().sendItemZoomOnInterface(AMULET.getId(), 230, 277, 3 + 2);
		player.getSkills().addExperience(Skills.MAGIC, 875);
		// 16170
		GameObject table = RegionManager.getObject(Location.create(3102, 3163, 2));
		if (table.getId() != 16170) {
			ObjectBuilder.replace(table, table.transform(16170), 80);
		}
		if (!player.getInventory().add(AMULET)) {
			GroundItemManager.create(AMULET, player.getLocation(), player);
		}
	}
	
}
