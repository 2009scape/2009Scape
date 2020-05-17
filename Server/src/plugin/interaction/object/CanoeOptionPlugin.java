package plugin.interaction.object;

import org.crandor.cache.def.impl.ObjectDefinition;
import org.crandor.game.content.global.travel.canoe.CanoeExtension;
import org.crandor.game.content.global.travel.canoe.CanoeStation;
import org.crandor.game.interaction.OptionHandler;
import org.crandor.game.node.Node;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.object.GameObject;
import org.crandor.game.world.map.Location;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.plugin.Plugin;

/**
 * Represents the option handler plugin that will handle all node interactions
 * related to canoes.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class CanoeOptionPlugin extends OptionHandler {

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		final String[] options = new String[] { "chop-down", "shape-canoe", "float canoe", "paddle canoe", "float log", "float waka", "paddle log" };
		for (CanoeStation station : CanoeStation.values()) {
			if (station == CanoeStation.WILDERNESS) {
				continue;
			}
			int[] ids = new int[] { 12140, 12141, 12142, 12143, 12145, 12146, 12147, 12148, 12151, 12152, 12153, 12154, 12155, 12156, 12157, 12158, 12144, 12146, 12149, 12150, 12157 };
			for (String option : options) {
				for (int i : ids) {
					ObjectDefinition.forId(i).getConfigurations().put("option:" + option, this);
				}
			}
		}
		return this;
	}

	@Override
	public boolean handle(Player player, Node node, String option) {
		final GameObject object = ((GameObject) node);
		final CanoeExtension extension = CanoeExtension.extension(player);
		if (extension.getStage() != 0 && extension.getCurrentStation() != null && extension.getCurrentStation() != CanoeStation.getStationByObject(object)) {
			extension.setStage(0);
		}
		if (extension.getStage() != 0) {
			player.faceLocation(player.getLocation().transform(object.getId() == 12163 ? -4 : object.getId() == 12164 ? 0 : object.getId() == 12165 ? -1 : -1, object.getId() == 12163 ? 0 : object.getId() == 12164 ? -1 : object.getId() == 12165 ? 0 : 0, 0));
		}
		if (extension.getStage() == 0) {
			player.faceLocation(player.getLocation().transform(-1, 0, 0));
		}
		switch (extension.getStage()) {
		case 0:
			extension.chopTree(object);
			break;
		case 1:
			extension.shapeCanoe();
			break;
		case 2:
			extension.setAfloat(object);
			break;
		case 3:
			extension.selectDestination();
			break;
		}
		return true;
	}

	@Override
	public Location getDestination(Node node, Node n) {
		final Player p = ((Player) node);
		final GameObject object = ((GameObject) n);
		final CanoeExtension extension = CanoeExtension.extension(p);
		if (object.getLocation().equals(new Location(3110, 3409, 0))) {
			if (extension.getStage() == 0) {
				return Location.create(3112, 3409, 0);
			} else {
				return Location.create(3112, 3411, 0);
			}
		} else if (object.getLocation().equals(new Location(3130, 3508, 0))) {
			if (extension.getStage() == 0) {
				return Location.create(3132, 3508, 0);
			} else {
				return Location.create(3132, 3510, 0);
			}
		} else if (object.getLocation().equals(new Location(3200, 3341, 0))) {// champions
			// guild
			if (extension.getStage() == 0) {
				return Location.create(3204, 3343, 0);
			} else {
				return Location.create(3202, 3343, 0);
			}
		} else {
			if (extension.getStage() == 0) {
				return Location.create(3243, 3235, 0);
			} else {
				return Location.create(3243, 3237, 0);
			}
		}
	}
}
