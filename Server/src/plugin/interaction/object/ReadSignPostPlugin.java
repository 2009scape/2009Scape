package plugin.interaction.object;

import org.crandor.cache.def.impl.ObjectDefinition;
import org.crandor.game.component.CloseEvent;
import org.crandor.game.component.Component;
import org.crandor.game.interaction.OptionHandler;
import org.crandor.game.node.Node;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.object.GameObject;
import org.crandor.net.packet.PacketRepository;
import org.crandor.net.packet.context.MinimapStateContext;
import org.crandor.net.packet.out.MinimapState;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.plugin.Plugin;

/**
 * Handles the reading of a sign post.
 * @author 'Vexia
 */
@InitializablePlugin
public class ReadSignPostPlugin extends OptionHandler {

	/**
	 * Represents the areas on the sign.
	 * @author 'Vexia
	 */
	public enum Signs {
		NEAR_LUMBRIDGE(18493, "North to farms and<br> Varrock.", "The River Lum lies to<br> the south.", "West to<br>Lumbridge.", "East to Al<br>Kharid - toll<br>gate; bring some<br>money."), NEAR_VARROCK(24263, "Sheep lay this way.", "South through farms<br> to Al Kharid and<br> Lumbridge", "West to Champion's Guild<br> and Varrock south<br> gate.", "East to Al Kharid mine and<br> follow the path north to<br> Varrock east gate.");

		public static Signs forId(int id) {
			for (Signs sign : Signs.values()) {
				if (sign == null) {
					continue;
				}
				if (sign.object == id) {
					return sign;
				}
			}
			return null;
		}

		/**
		 * The object id.
		 */
		private int object;

		/**
		 * The directions.
		 */
		private String directions[];

		/**
		 * Constructs a new {@code ReadSignPostPlugin.java} {@code Object}.
		 * @param object the object.
		 * @param directions the directions.
		 */
		Signs(int object, String... directions) {
			this.object = object;
			this.directions = directions;
		}
	}

	@Override
	public boolean handle(Player player, Node node, String option) {
		PacketRepository.send(MinimapState.class, new MinimapStateContext(player, 2));
		player.getInterfaceManager().open(new Component(135)).setCloseEvent(new CloseEvent() {
			@Override
			public boolean close(Player player, Component c) {
				PacketRepository.send(MinimapState.class, new MinimapStateContext(player, 0));
				return true;
			}
		});
		player.getPacketDispatch();
		final GameObject object = (GameObject) node;
		Signs sign = Signs.forId(object.getId());
		if (sign == null) {
			return false;
		}
		String[] dirs = sign.directions;
		if (object.getLocation().getX() == 3107 && object.getLocation().getY() == 3296) {
			dirs[0] = "North to Draynor<br> Manor";
			dirs[1] = "South to Draynor<br> Village";
			dirs[2] = "West to Port<br> Sarim";
			dirs[3] = "East to<br> Lumbridge";
		}
		player.getPacketDispatch().sendString(dirs[0], 135, 3); // North
		player.getPacketDispatch().sendString(dirs[1], 135, 9); // South
		player.getPacketDispatch().sendString(dirs[2], 135, 12); // West
		player.getPacketDispatch().sendString(dirs[3], 135, 8); // East
		return true;
	}

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ObjectDefinition.forId(2366).getConfigurations().put("option:read", this);
		ObjectDefinition.forId(2367).getConfigurations().put("option:read", this);
		ObjectDefinition.forId(2368).getConfigurations().put("option:read", this);
		ObjectDefinition.forId(2369).getConfigurations().put("option:read", this);
		ObjectDefinition.forId(2370).getConfigurations().put("option:read", this);
		ObjectDefinition.forId(2371).getConfigurations().put("option:read", this);
		ObjectDefinition.forId(4132).getConfigurations().put("option:read", this);
		ObjectDefinition.forId(4133).getConfigurations().put("option:read", this);
		ObjectDefinition.forId(4134).getConfigurations().put("option:read", this);
		ObjectDefinition.forId(4135).getConfigurations().put("option:read", this);
		ObjectDefinition.forId(5164).getConfigurations().put("option:read", this);
		ObjectDefinition.forId(10090).getConfigurations().put("option:read", this);
		ObjectDefinition.forId(13873).getConfigurations().put("option:read", this);
		ObjectDefinition.forId(15522).getConfigurations().put("option:read", this);
		ObjectDefinition.forId(18493).getConfigurations().put("option:read", this);
		ObjectDefinition.forId(24263).getConfigurations().put("option:read", this);
		ObjectDefinition.forId(25397).getConfigurations().put("option:read", this);
		ObjectDefinition.forId(30039).getConfigurations().put("option:read", this);
		ObjectDefinition.forId(30040).getConfigurations().put("option:read", this);
		ObjectDefinition.forId(31296).getConfigurations().put("option:read", this);
		ObjectDefinition.forId(31298).getConfigurations().put("option:read", this);
		ObjectDefinition.forId(31299).getConfigurations().put("option:read", this);
		ObjectDefinition.forId(31300).getConfigurations().put("option:read", this);
//		ObjectDefinition.forId(31301).getConfigurations().put("option:read", this);//goblin village
		return this;
	}
}
