package plugin.quest.members.lostcity;

import plugin.skill.Skills;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.quest.Quest;
import core.game.node.item.Item;
import core.plugin.InitializablePlugin;
import core.plugin.PluginManager;

/**
 * The main type for the lost city Quest.
 * @author Vexia
 * @author Aero
 */
@InitializablePlugin
public class LostCity extends Quest {
	
	/**
	 * The requirement titles.
	 */
	private static final String[] TITLES = new String[] { "Level 31 Crafting", "Level 36 Woodcutting" };

	/**
	 * The dramen staff item.
	 */
	private static final Item DRAMEN_STAFF = new Item(772);

	/**
	 * The quest requirements.
	 */
	private boolean[] questRequirements = new boolean[2];

	/**
	 * Constructs a new {@code LostCity} {@code Object}.
	 * @param player The player to construct the class for.
	 */
	public LostCity() {
		super("Lost City", 83, 82, 3, 147, 0, 1, 6);
	}

	@Override
	public void drawJournal(Player player, int stage) {
		super.drawJournal(player, stage);
		switch (stage) {
		case 0:
			line(player, BLUE + "I can start this quest by speaking to the Adventurers in", 4+ 7);
			line(player, BLUE + "the Swamp just south of Lumbridge.", 5+ 7);
			drawQuestRequirements(player);
			break;
		case 10:
			line(player, "<str>According to one of the adventurers in Lumbridge Swamp<br><br><str>the entrance to Zanaris is somewhere around there.<br><br><blue>Apparently there is a <red>leprechaun<blue> hiding in a <red>tree<blue> nearby<br><br><blue>who can tell me how to enter the <red>Lost City of Zanaris", 4+ 7);
			break;
		case 20:
			line(player, "<str>According to one of the adventurers in Lumbridge Swamp<br><br><str>the entrance to Zanaris is somewhere around there.<br><br><str>I found the Leprechaun hiding in a nearby tree.<br><br><blue>He told me that the entrance to <red>Zanaris<blue> is in the <red>shed<blue> in<br><br><red>Lumbridge swamp<blue> but only if I am carrying a <red>Dramen Staff<br><br><blue>I can find a <red>Dramen Tree <blue>in a cave on <red>Entrana <blue>somewhere", 4+ 7);
			break;
		case 21:
			if (player.getBank().containsItem(DRAMEN_STAFF) || player.getInventory().containsItem(DRAMEN_STAFF) || player.getEquipment().containsItem(DRAMEN_STAFF)) {
				line(player, "<str>According to one of the adventurers in Lumbridge Swamp<br><br><str>the entrance to Zanaris is somewhere around there.<br><br><str>I found the Leprechaun hiding in a nearby tree.<br><br><str>He told me that the entrance to Zanaris is in the shed in<br><br><str>Lumbridge swamp but only if I am carrying a Dramen Staff.<br><br><str>The Damen Tree was guarded by a powerful Tree Spirit.<br><br><str>I cut a branch from the tree and crafted a Dramen Staff.<br><br><blue>I should enter <red>Zanaris <blue>by going to the <red>shed <blue>in <red>Lumbridge<br><br><red>Swamp <blue>while keeping the <red>Dramen staff<blue> with me", 4+ 7);
			} else {
				line(player, "<str>According to one of the adventurers in Lumbridge Swamp<br><br><str>the entrance to Zanaris is somewhere around there.<br><br><str>I found the Leprechaun hiding in a nearby tree.<br><br><str>He told me that the entrance to Zanaris is in the shed in<br><br><str>Lumbridge swamp but only if I am carrying a Dramen Staff.<br><br><str>The Damen Tree was guarded by a powerful Tree Spirit.<br><br><blue>With the <red>Spirit <blue>defeated I can cut a <red>branch <blue>from the tree", 4+ 7);
			}
			break;
		case 100:
			line(player, "<str>According to one of the adventurers in Lumbridge Swamp<br><br><str>the entrance to Zanaris is somewhere around there.<br><br><str>I found the Leprechaun hiding in a nearby tree.<br><br><str>He told me that the entrance to Zanaris is in the shed in<br><br><str>Lumbridge swamp but only if I am carrying a Dramen Staff.<br><br><str>The Damen Tree was guarded by a powerful Tree Spirit.<br><br><str>I cut a branch from the tree and crafted a Dramen Staff.<br><br><str>With the mystical Dramen Staff in my possession I was<br><br><str>able to enter Zanaris through the shed in Lumbridge<br><br><str>swamp.<br><br><br><col=FF0000>QUEST COMPLETE!", 4+ 7);
			break;
		}
	}

	@Override
	public void finish(Player player) {
		super.finish(player);
		player.getPacketDispatch().sendString("3 Quest Points", 277, 8+ 2);
		player.getPacketDispatch().sendString("Access to Zanaris", 277, 9+ 2);
		player.getPacketDispatch().sendItemZoomOnInterface(772, 235, 277, 3+ 2);
		player.getQuestRepository().syncronizeTab(player);
	}

	/**
	 * Draws the quest requirements onto the journal component.
	 * @param player The player to draw the requirements for.
	 */
	private final void drawQuestRequirements(final Player player) {
		int line = 7 + 7;
		questRequirements[0] = player.getSkills().getStaticLevelByExperience(Skills.CRAFTING) > 30;
		questRequirements[1] = player.getSkills().getStaticLevelByExperience(Skills.WOODCUTTING) > 35;
		line(player, BLUE + "To complete this quest I will need:", 6);
		for (int i = 0; i < questRequirements.length; i++) {
			boolean bool = !questRequirements[i];
			String str = TITLES[i];
			line(player, (bool ? RED + str : str), line++, !bool);
		}
		line(player, BLUE + "and be able to defeat a " + RED + "Level 101 Spirit without weapons", line++);
	}

	@Override
	public Quest newInstance(Object object) {
		PluginManager.definePlugin(new LostCityPlugin());
		PluginManager.definePlugin(new TreeSpiritNPC());
		PluginManager.definePlugin(new ShamusDialogue());
		PluginManager.definePlugin(new WarriorDialogue());
		PluginManager.definePlugin(new DramenStaffPlugin());
		return this;
	}

}
