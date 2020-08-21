package plugin.quest.members.rovingelves;

import core.cache.def.impl.ObjectDefinition;
import plugin.skill.agility.AgilityHandler;
import core.game.interaction.OptionHandler;
import core.game.node.Node;
import core.game.node.entity.player.Player;
import core.game.node.item.Item;
import core.game.world.map.Direction;
import core.game.world.map.Location;
import core.game.world.update.flag.context.Animation;
import core.plugin.Plugin;

/**
 * Handles all the agility obstacles for Roving Elves.
 * @author Splinter
 */
public final class RovingElvesObstacles extends OptionHandler {

	// TODO: TRIPWIRE

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ObjectDefinition.forId(3999).getConfigurations().put("option:enter", this);
		ObjectDefinition.forId(3939).getConfigurations().put("option:enter", this);
		ObjectDefinition.forId(3998).getConfigurations().put("option:enter", this);
		ObjectDefinition.forId(3938).getConfigurations().put("option:enter", this);
		ObjectDefinition.forId(3937).getConfigurations().put("option:enter", this);
		ObjectDefinition.forId(3924).getConfigurations().put("option:jump", this);
		ObjectDefinition.forId(3925).getConfigurations().put("option:jump", this);
		ObjectDefinition.forId(8742).getConfigurations().put("option:pass", this);
		return this;
	}

	final Animation OVER = new Animation(840);
	final Animation THROUGH = new Animation(749);
	final Animation JUMP = new Animation(3067);

	@Override
	public boolean handle(final Player player, Node node, String option) {
		Direction NORTH_SOUTH = player.getLocation().getY() < node.getLocation().getY() ? Direction.NORTH : Direction.SOUTH;
		Direction EAST_WEST = player.getLocation().getX() > node.getLocation().getX() ? Direction.WEST : Direction.EAST;
		switch (node.getId()) {
		case 8742:
			player.teleport(player.getLocation().transform(EAST_WEST, 2));
			break;
		case 3999:
			AgilityHandler.forceWalk(player, -1, player.getLocation(), player.getLocation().transform(NORTH_SOUTH, 3), OVER, 25, 0, null);
			break;
		case 3939:
		case 3998:
		case 3938:
			if (node.getLocation().equals(new Location(2235, 3148)) || node.getLocation().equals(new Location(2238, 3148)) || node.getLocation().equals(new Location(2236, 3218)) || node.getLocation().equals(new Location(2230, 3218)) || node.getLocation().equals(new Location(2227, 3218))) {
				AgilityHandler.forceWalk(player, -1, player.getLocation(), player.getLocation().transform(EAST_WEST, 3), THROUGH, 25, 0, null);
			} else {
				AgilityHandler.forceWalk(player, -1, player.getLocation(), player.getLocation().transform(NORTH_SOUTH, 3), THROUGH, 25, 0, null);
			}
			break;
		case 3937:
			AgilityHandler.forceWalk(player, -1, player.getLocation(), player.getLocation().transform(EAST_WEST, 3), OVER, 25, 0, null);
			break;
		case 3924:
		case 3925:
			AgilityHandler.forceWalk(player, -1, player.getLocation(), player.getLocation().transform(NORTH_SOUTH, 4), JUMP, 25, 0, null);
			break;
		}
		return true;
	}

	@Override
	public Location getDestination(Node node, Node n) {
		switch (node.getId()) {
		case 3999:
			return new Location(2188, 3162);
		case 3998:
			return new Location(2188, 3171);
		}
		return null;
	}

	@Override
	public boolean isWalk(Player player, Node node) {
		return !(node instanceof Item);
	}

	@Override
	public boolean isWalk() {
		return false;
	}

}
