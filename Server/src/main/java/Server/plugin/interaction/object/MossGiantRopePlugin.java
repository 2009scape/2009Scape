package plugin.interaction.object;

import core.cache.def.impl.ObjectDefinition;
import plugin.skill.Skills;
import plugin.skill.agility.AgilityHandler;
import core.game.interaction.OptionHandler;
import core.game.node.Node;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.diary.DiaryType;
import core.game.world.map.Location;
import core.game.world.update.flag.context.Animation;
import core.plugin.InitializablePlugin;
import core.plugin.Plugin;

/**
 * Handles the rope swing to the moss giants.
 * @author Vexia
 */
@InitializablePlugin
public class MossGiantRopePlugin extends OptionHandler {

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ObjectDefinition.forId(2322).getHandlers().put("option:swing-on", this);
		ObjectDefinition.forId(2323).getHandlers().put("option:swing-on", this);
		return this;
	}

	@Override
	public boolean handle(Player player, Node node, String option) {
		if (!player.getLocation().withinDistance(node.getLocation(), 4)) {
			player.sendMessage("I can't reach that.");
			return true;
		}
		if (player.getSkills().getStaticLevel(Skills.AGILITY) < 10) {
			player.getDialogueInterpreter().sendDialogue("You need an Agility level of at least 10 in order to do that.");
			return true;
		}
		Location end = node.getId() == 2322 ? Location.create(2704, 3209, 0) : Location.create(2709, 3205, 0);
		player.getPacketDispatch().sendObjectAnimation(node.asObject(), Animation.create(497), true);
		AgilityHandler.forceWalk(player, 0, player.getLocation(), end, Animation.create(751), 50, 22, "You skillfully swing across.", 1);
		player.getAchievementDiaryManager().finishTask(player, DiaryType.KARAMJA, 0, 1);
		return true;
	}

	@Override
	public Location getDestination(Node node, Node n) {
		return n.getId() == 2322 ? Location.create(2709, 3209, 0) : Location.create(2705, 3205, 0);
	}
}
