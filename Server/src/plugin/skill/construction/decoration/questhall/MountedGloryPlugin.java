package plugin.skill.construction.decoration.questhall;


import org.crandor.cache.def.impl.ObjectDefinition;
import org.crandor.game.content.dialogue.DialogueAction;
import org.crandor.game.content.dialogue.DialogueInterpreter;
import org.crandor.game.interaction.OptionHandler;
import org.crandor.game.node.Node;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.system.task.Pulse;
import org.crandor.game.world.GameWorld;
import org.crandor.game.world.PulseRunner;
import org.crandor.game.world.map.Location;
import org.crandor.game.world.update.flag.context.Animation;
import org.crandor.game.world.update.flag.context.Graphics;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.plugin.Plugin;

/**
 * Handles the POH Mounted Glory.
 * @author Splinter
 */
@InitializablePlugin
public class MountedGloryPlugin extends OptionHandler {

	/**
	 * Represents the teleport animation.
	 */
	private static final Animation ANIMATION = new Animation(714);

	/**
	 * Represents the graphics to use.
	 */
	private static final Graphics GRAPHICS = new Graphics(308, 100, 50);

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ObjectDefinition.forId(13523).getConfigurations().put("option:rub", this);
		return this;
	}

	@Override
	public boolean handle(Player player, Node node, String option) {
		DialogueInterpreter interpreter = player.getDialogueInterpreter();
		interpreter.sendOptions("Select a location.", "Edgeville", "Karamja", "Draynor Village", "Al-Kharid", "Nowhere.");
		interpreter.addAction(new DialogueAction() {

			@Override
			public void handle(Player player, int buttonId) {
				switch (buttonId) {
				case 2:
					teleport(player, Location.create(3087, 3495, 0));
					break;
				case 3:
					teleport(player, Location.create(2919, 3175, 0));
					break;
				case 4:
					teleport(player, Location.create(3081, 3250, 0));
					break;
				case 5:
					teleport(player, Location.create(3304, 3124, 0));
					break;
				}
			}

		});
		return true;
	}

	/**
	 * Method used to teleport to a location.
	 * @param player the player.
	 * @param location the location.
	 */
	private boolean teleport(final Player player, final Location location) {
		if (player.isTeleBlocked()) {
			player.sendMessage("A magical force has stopped you from teleporting.");
			return false;
		}
		player.lock();
		player.visualize(ANIMATION, GRAPHICS);
		player.getImpactHandler().setDisabledTicks(4);
		GameWorld.Pulser.submit(new Pulse(4, player) {
			@Override
			public boolean pulse() {
				player.unlock();
				player.getProperties().setTeleportLocation(location);
				player.getAnimator().reset();
				return true;
			}
		});
		return true;
	}

}