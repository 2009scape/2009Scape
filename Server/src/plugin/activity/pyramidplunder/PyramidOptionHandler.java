package plugin.activity.pyramidplunder;

import org.crandor.cache.def.impl.NPCDefinition;
import org.crandor.cache.def.impl.ObjectDefinition;
import org.crandor.game.content.global.action.ClimbActionHandler;
import org.crandor.game.interaction.OptionHandler;
import org.crandor.game.node.Node;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.object.GameObject;
import org.crandor.game.system.task.Pulse;
import org.crandor.game.world.GameWorld;
import org.crandor.game.world.map.Location;
import org.crandor.game.world.update.flag.context.Animation;
import org.crandor.plugin.Plugin;
import org.crandor.tools.RandomFunction;

/**
 * Handles the pyramid plunder options.
 * @author Emperor
 */
public final class PyramidOptionHandler extends OptionHandler {

	/**
	 * The guardian room location.
	 */
	private static final Location GUARDIAN_ROOM = Location.create(1968, 4420, 2);

	/**
	 * The empty room location.
	 */
	private static final Location EMPTY_ROOM = Location.create(1934, 4450, 2);

	/**
	 * The current entrance.
	 */
	private static int currentEntrance;

	/**
	 * The last entrance switch made.
	 */
	private static long lastEntranceSwitch;

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ObjectDefinition.forId(16484).getConfigurations().put("option:search", this);
		ObjectDefinition.forId(16485).getConfigurations().put("option:search", this);
		ObjectDefinition.forId(16487).getConfigurations().put("option:search", this);
		ObjectDefinition.forId(16488).getConfigurations().put("option:search", this);
		ObjectDefinition.forId(16490).getConfigurations().put("option:search", this);
		ObjectDefinition.forId(16491).getConfigurations().put("option:search", this);
		ObjectDefinition.forId(16493).getConfigurations().put("option:search", this);
		ObjectDefinition.forId(16494).getConfigurations().put("option:enter", this);
		ObjectDefinition.forId(16458).getConfigurations().put("option:leave tomb", this);
		ObjectDefinition.forId(16459).getConfigurations().put("option:leave tomb", this);
		NPCDefinition.forId(4476).getConfigurations().put("option:start-minigame", this);
		NPCDefinition.forId(4476).getConfigurations().put("option:talk-to",this);
		return null;
	}

	@Override
	public boolean handle(Player player, Node node, String option) {
		if (node instanceof GameObject) {
			Location destination = EMPTY_ROOM;
			GameObject object = (GameObject) node;
			boolean willBePushed = (RandomFunction.random(10) > 3);
			if (object.getId() == 16458 || object.getId() == 16459) {
				ClimbActionHandler.climb(player, ClimbActionHandler.CLIMB_UP, Location.create(3288, 2801, 0));
				player.getPlunderObjectManager().resetObjectsFor(player);
				return true;
			}
			if (System.currentTimeMillis() - lastEntranceSwitch > 15 * 60 * 1000) {
				currentEntrance = RandomFunction.random(4);
				lastEntranceSwitch = System.currentTimeMillis();
			}
			int entrance = (object.getId() - 16483) / 3;
			int value = 0;
			if (entrance == currentEntrance) {
				destination = GUARDIAN_ROOM;
				value = 4;
			}
			player.getConfigManager().set(704, value);
			player.getPacketDispatch().sendMessage("You use your thieving skills to search the stone panel.");
			if(entrance == currentEntrance && willBePushed){
				player.lock();
				player.animate(new Animation(7299));
				player.getImpactHandler().setDisabledTicks(4);
				GameWorld.Pulser.submit(new Pulse(4, player) {
					@Override
					public boolean pulse() {
						player.unlock();
						player.getAnimator().reset();
						return true;
					}
				});
			} else {
				ClimbActionHandler.climb(player, ClimbActionHandler.CLIMB_UP, destination, "You find a door! You open it.");
			}
			//ClimbActionHandler.climb(player, ClimbActionHandler.CLIMB_UP, destination, "You find a door! You open it.");
		} else if (node instanceof NPC) {
			if (option.equals("talk-to")) {
				player.getDialogueInterpreter().open(node.getId(), node, 0);
			} else {
				player.getDialogueInterpreter().open(node.getId(), node, 1);
			}
		}
		return true;
	}

}