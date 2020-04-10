package plugin.dialogue;

import org.crandor.game.component.Component;
import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.system.task.Pulse;
import org.crandor.game.world.GameWorld;
import org.crandor.game.world.PulseRunner;
import org.crandor.game.world.map.Location;
import org.crandor.net.packet.PacketRepository;
import org.crandor.net.packet.context.MinimapStateContext;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.net.packet.out.MinimapState;

/**
 * Handles the lokar searunner dialogue.
 * @author Vexia
 */
@InitializablePlugin
public class LokarSearunnerDialogue extends DialoguePlugin {

	/**
	 * The location of relekka.
	 */
	private static final Location RELEKKA = new Location(2621, 3687, 0);

	/**
	 * The pirate's minigame.
	 */
	private static final Location PIRATES_COVE = new Location(2213, 3794, 0);

	/**
	 * Constructs a new {@Code LokarSearunnerDialogue} {@Code
	 * Object}
	 */
	public LokarSearunnerDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@Code LokarSearunnerDialogue} {@Code
	 * Object}
	 * @param player the player.
	 */
	public LokarSearunnerDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new LokarSearunnerDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		switch (npc.getId()) {
		case 4536:
			npc("Hi, would you like to take a boat trip to relekka?");
			break;
		case 4537:
			npc("Hi, would you like to take a boat trip to pirate's cove?");
			break;
		}
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (npc.getId()) {
		case 4536:
			switch (stage) {
			case 0:
				options("Yes, please.", "No, thanks.");
				stage++;
				break;
			case 1:
				end();
				if (buttonId == 1) {
					travel(player, RELEKKA);
					return true;
				}
				break;
			}
			break;
		case 4537:
			switch (stage) {
			case 0:
				options("Yes, please.", "No, thanks.");
				stage++;
				break;
			case 1:
				end();
				if (buttonId == 1) {
					travel(player, PIRATES_COVE);
					return true;
				}
				break;
			}
			break;
		}
		return true;
	}

	/**
	 * Travels to a location.
	 * @param location the location.
	 */
	private void travel(final Player player, final Location location) {
		player.lock();
		PulseRunner.submit(new Pulse(1, player) {
			int counter;

			@Override
			public boolean pulse() {
				switch (counter++) {
				case 1:
					player.lock();
					player.getInterfaceManager().openOverlay(new Component(115));
					break;
				case 3:
					PacketRepository.send(MinimapState.class, new MinimapStateContext(player, 2));
					break;
				case 4:
					player.getProperties().setTeleportLocation(location);
					break;
				case 5:
					PacketRepository.send(MinimapState.class, new MinimapStateContext(player, 0));
					player.getInterfaceManager().close();
					player.getInterfaceManager().closeOverlay();
					player.unlock();
					return true;
				}
				return false;
			}

		});
	}

	@Override
	public int[] getIds() {
		return new int[] { 4536, 4537 };
	}

}
