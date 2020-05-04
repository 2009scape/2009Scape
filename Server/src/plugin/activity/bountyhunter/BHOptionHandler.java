package plugin.activity.bountyhunter;

import org.crandor.cache.def.impl.ObjectDefinition;
import org.crandor.game.content.activity.ActivityManager;
import org.crandor.game.interaction.OptionHandler;
import org.crandor.game.node.Node;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.object.GameObject;
import org.crandor.game.system.task.Pulse;
import org.crandor.game.world.GameWorld;
import org.crandor.game.world.update.flag.context.Animation;
import org.crandor.plugin.Plugin;

/**
 * Handles the bounty hunter options.
 * @author Emperor
 */
public final class BHOptionHandler extends OptionHandler {

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ObjectDefinition.forId(28110).getConfigurations().put("option:exit", this);
		ObjectDefinition.forId(28119).getConfigurations().put("option:enter", this);
		ObjectDefinition.forId(28120).getConfigurations().put("option:enter", this);
		ObjectDefinition.forId(28121).getConfigurations().put("option:enter", this);
		ObjectDefinition.forId(28122).getConfigurations().put("option:exit", this);
		ObjectDefinition.forId(28115).getConfigurations().put("option:view", this);
		ObjectDefinition.forId(28116).getConfigurations().put("option:view", this);
		return this;
	}

	@Override
	public boolean handle(final Player player, Node node, String option) {
		GameObject object = (GameObject) node;
		final BountyHunterActivity activity = player.getExtension(BountyHunterActivity.class);
		switch (object.getId()) {
		case 28119:
			ActivityManager.start(player, "BH low_level", false);
			return true;
		case 28120:
			ActivityManager.start(player, "BH mid_level", false);
			return true;
		case 28121:
			ActivityManager.start(player, "BH high_level", false);
			return true;
		case 28115:
			BHScoreBoard.getRogues().open(player);
			return true;
		case 28116:
			BHScoreBoard.getHunters().open(player);
			return true;
		case 28122:
			if (activity == null) {
				return false;
			}
			if (player.getAttribute("exit_penalty", 0) > GameWorld.getTicks()) {
				player.getPacketDispatch().sendMessage("You can't leave the crater until the exit penalty is over.");
				return true;
			}
			player.lock(2);
			GameWorld.Pulser.submit(new Pulse(1) {
				@Override
				public boolean pulse() {
					player.getProperties().setTeleportLocation(activity.getType().getExitLocation());
					return true;
				}
			});
			player.animate(Animation.create(7376));
			return true;
		case 28110:
			if (activity == null) {
				return false;
			}
			activity.leaveWaitingRoom(player, false);
			return true;
		}
		return false;
	}

}