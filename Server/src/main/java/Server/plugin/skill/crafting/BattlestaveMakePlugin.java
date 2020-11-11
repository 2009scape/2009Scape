package plugin.skill.crafting;

import core.game.node.entity.player.link.diary.DiaryType;
import plugin.skill.Skills;
import core.game.interaction.NodeUsageEvent;
import core.game.interaction.UseWithHandler;
import core.game.node.entity.player.Player;
import core.game.node.item.Item;
import core.plugin.InitializablePlugin;
import core.plugin.Plugin;

/**
 * Represents the battle stave making plugin used for zaff.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class BattlestaveMakePlugin extends UseWithHandler {

	/**
	 * Represents the original staff item.
	 */
	private static final Item STAFF = new Item(1391, 1);

	/**
	 * Constructs a new {@code BattlestaveMakePlugin} {@code Object}.
	 */
	public BattlestaveMakePlugin() {
		super(STAFF.getId(), 573, 571, 575, 569);
	}

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		addHandler(573, ITEM_TYPE, this);
		addHandler(571, ITEM_TYPE, this);
		addHandler(575, ITEM_TYPE, this);
		addHandler(569, ITEM_TYPE, this);
		return this;
	}

	@Override
	public boolean handle(NodeUsageEvent event) {
		final Player player = event.getPlayer();
		if (event.getUsedItem().getId() != STAFF.getId()) {
			return false;
		}
		int id = event.getUsedItem().getId() == STAFF.getId() ? event.getUsedWith().getId() : event.getUsedItem().getId();
		final BattleStaves staff = BattleStaves.forId(id);
		if (staff == null)
			return true;
		if (player.getSkills().getLevel(Skills.CRAFTING) < staff.getLevel()) {
			player.getPacketDispatch().sendMessage("You need a crafting level of " + staff.getLevel() + " to make this.");
			return true;
		}
		player.getInventory().remove(STAFF);
		player.getInventory().remove(new Item(staff.getObelisk(), 1));
		player.getInventory().add(new Item(staff.getProduct(), 1));
		player.getSkills().addExperience(Skills.CRAFTING, staff.getExp(), true);
		// Craft an air battlestaff
		if (staff == BattleStaves.AIR) {
			player.getAchievementDiaryManager().finishTask(player, DiaryType.VARROCK, 2, 6);
		}
		return true;
	}

}
