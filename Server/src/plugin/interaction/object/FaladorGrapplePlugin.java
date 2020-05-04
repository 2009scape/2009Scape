package plugin.interaction.object;

import org.crandor.cache.def.impl.ObjectDefinition;
import org.crandor.game.component.Component;
import org.crandor.game.content.skill.Skills;
import org.crandor.game.interaction.OptionHandler;
import org.crandor.game.node.Node;
import org.crandor.game.node.entity.impl.ForceMovement;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.item.Item;
import org.crandor.game.node.object.GameObject;
import org.crandor.game.system.task.Pulse;
import org.crandor.game.world.GameWorld;
import org.crandor.game.world.map.Location;
import org.crandor.game.world.update.flag.context.Animation;
import org.crandor.net.packet.PacketRepository;
import org.crandor.net.packet.context.MinimapStateContext;
import org.crandor.net.packet.out.MinimapState;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.plugin.Plugin;

/**
 * Represents the plugin used to handle the grappling of the falador wall.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class FaladorGrapplePlugin extends OptionHandler {

	/**
	 * Represents the requirements array.
	 */
	private static final int[][] REQUIREMENTS = new int[][] { { Skills.AGILITY, 11 }, { Skills.RANGE, 19 }, { Skills.STRENGTH, 37 } };

	/**
	 * Represents the mithril c'bow item.
	 */
	private static final Item MITH_CBOW = new Item(9181);

	/**
	 * Represents the mithril grapple item.
	 */
	private static final Item MITH_GRAPPLE = new Item(9419);

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ObjectDefinition.forId(17049).getConfigurations().put("option:grapple", this);
		ObjectDefinition.forId(17050).getConfigurations().put("option:grapple", this);
		ObjectDefinition.forId(17051).getConfigurations().put("option:jump", this);
		ObjectDefinition.forId(17052).getConfigurations().put("option:jump", this);
		return this;
	}

	@Override
	public boolean handle(final Player player, final Node node, String option) {
		switch (option) {
		case "jump":
			ForceMovement.run(player, player.getLocation(), ((GameObject) node).getId() == 17051 ? Location.create(3033, 3390, 0) : Location.create(3032, 3388, 0), new Animation(7268), 10);
			break;
		case "grapple":
			for (int i = 0; i < REQUIREMENTS.length; i++) {
				if (player.getSkills().getLevel(REQUIREMENTS[i][0]) < REQUIREMENTS[i][1]) {
					player.getDialogueInterpreter().sendDialogue("You need at least 19 Rangd, 11 Agility and 37 Strength to do that.");
					return true;
				}
			}
			if (!player.getEquipment().containsItem(MITH_CBOW) || !player.getEquipment().containsItem(MITH_GRAPPLE)) {
				player.getDialogueInterpreter().sendDialogue("You need a Mithril crossbow and a Mithril grapple in order to do this.");
				return true;
			}
			player.lock();
			GameWorld.Pulser.submit(new Pulse(1, player) {
				int counter = 1;

				@Override
				public boolean pulse() {
					switch (counter++) {
					case 2:
						player.faceLocation(((GameObject) node).getId() == 17049 ? Location.create(3033, 3388, 0) : Location.create(3032, 3391, 0));
						break;
					case 3:
						player.getInterfaceManager().openOverlay(new Component(115));
						break;
					case 4:
						PacketRepository.send(MinimapState.class, new MinimapStateContext(player, 2));
						player.getInterfaceManager().hideTabs(0, 1, 2, 3, 4, 5, 6, 11, 12);
						break;
					case 7:
						player.getProperties().setTeleportLocation(((GameObject) node).getId() == 17050 ? Location.create(3033, 3389, 1) : Location.create(3033, 3389, 1));
						break;
					case 8:
						player.getInterfaceManager().restoreTabs();
						PacketRepository.send(MinimapState.class, new MinimapStateContext(player, 0));
						player.getInterfaceManager().closeOverlay();
						player.getInterfaceManager().close();
						player.unlock();
						return true;
					}
					return false;
				}
			});
			break;
		}
		return true;
	}

	@Override
	public Location getDestination(final Node node, final Node n) {
		return ((GameObject) n).getId() == 17050 ? Location.create(3032, 3388, 0) : null;
	}

}
