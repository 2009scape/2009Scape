package core.game.node.entity.skill.slayer;

import core.cache.def.impl.ObjectDefinition;
import core.game.content.global.action.ClimbActionHandler;
import core.game.content.global.action.DigAction;
import core.game.content.global.action.DigSpadeHandler;
import core.game.interaction.OptionHandler;
import core.game.node.Node;
import core.game.node.entity.player.Player;
import core.game.world.map.Location;
import core.plugin.Initializable;
import core.plugin.Plugin;

/**
 * Handles related slayer nodes.
 * @author Vexia
 */
@Initializable
public class SlayerPlugin extends OptionHandler {

	/**
	 * The bryne dig locations.
	 */
	private static final Location[] BRYNE_DIGS = new Location[] { new Location(2749, 3733, 0), new Location(2748, 3733, 0), new Location(2747, 3733, 0), new Location(2747, 3734, 0), new Location(2747, 3735, 0), new Location(2747, 3736, 0), new Location(2748, 3736, 0), new Location(2749, 3736, 0) };

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ObjectDefinition.forId(8783).getHandlers().put("option:open", this);
		ObjectDefinition.forId(8785).getHandlers().put("option:climb-up", this);
		ObjectDefinition.forId(23158).getHandlers().put("option:exit", this);
		ObjectDefinition.forId(23157).getHandlers().put("option:exit", this);
		ObjectDefinition.forId(15767).getHandlers().put("option:enter", this);
		ObjectDefinition.forId(15811).getHandlers().put("option:exit", this);
		ObjectDefinition.forId(15812).getHandlers().put("option:exit", this);
		ObjectDefinition.forId(96).getHandlers().put("option:climb-up", this);
		ObjectDefinition.forId(35121).getHandlers().put("option:climb-down", this);
		for (Location loc : BRYNE_DIGS) {
			DigSpadeHandler.register(loc, new DigAction() {
				@Override
				public void run(Player player) {
					player.teleport(new Location(2697, 10119, 0));
					player.sendMessages("You dig a hole...", "...And fall into a dark and slimy pit!");
				}

			});
		}
		return this;
	}

	@Override
	public boolean handle(Player player, Node node, String option) {
		switch (node.getId()) {
		case 8783:
			player.teleport(new Location(2044, 4649, 0));
			break;
		case 8785:
			player.teleport(new Location(2543, 3327, 0));
			break;
		case 23158:
		case 23157:
			player.teleport(new Location(2729, 3733, 0));
			break;
		case 15767:
			player.teleport(new Location(3748, 9373, 0));
			break;
		case 15811:
		case 15812:
			player.teleport(new Location(3749, 2973, 0));
			break;
		case 96:
			ClimbActionHandler.climb(player, null, new Location(2649, 9804, 0));
			break;
		case 35121:
			ClimbActionHandler.climb(player, null, new Location(2641, 9763, 0));
			break;
		}
		return true;
	}

	@Override
	public Location getDestination(Node node, Node n) {
		if (n.getId() == 23158 || n.getId() == 23157) {
			return new Location(2690, 10124, 0);
		}
		if (n.getId() == 96) {
			return new Location(2641, 9763, 0);
		}
		return null;
	}

}
