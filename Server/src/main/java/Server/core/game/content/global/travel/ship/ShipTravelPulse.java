package core.game.content.global.travel.ship;

import core.game.component.Component;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.diary.DiaryType;
import core.game.system.task.Pulse;
import core.net.packet.PacketRepository;
import core.net.packet.context.MinimapStateContext;
import core.net.packet.out.MinimapState;

/**
 * Represents a pulse used to travel a player to a location.
 * @author Vexia
 */
public final class ShipTravelPulse extends Pulse {

	/**
	 * Represents the player instance.
	 */
	private final Player player;

	/**
	 * Represents the ship we're using.
	 */
	private final Ships ship;

	/**
	 * Represents the current counter.
	 */
	private int counter = 0;

	/**
	 * Constructs a new {@code ShipTravelPulse.java} {@code Object}.
	 * @param player the <b>Player</b>.
	 */
	public ShipTravelPulse(Player player, Ships ship) {
		super(1);
		this.player = player;
		this.ship = ship;
	}

	@Override
	public boolean pulse() {
		switch (counter++) {
		case 0:
			prepare();
			break;
		case 1:
			if (ship != Ships.PORT_SARIM_TO_CRANDOR) {
				player.getProperties().setTeleportLocation(ship.getLocation());
			}
			break;
		default:
			if (counter == ship.getDelay()) {
				arrive();
				return true;
			}
			break;
		}
		return false;
	}

	/**
	 * Method used to arrive at a location.
	 */
	private void arrive() {
		player.unlock();
		player.getConfigManager().set(75, -1);
		player.getInterfaceManager().close();
		PacketRepository.send(MinimapState.class, new MinimapStateContext(player, 0));
		if (!ship.getName().equals("Crandor")) {
			player.getDialogueInterpreter().sendDialogue("The ship arrives at " + ship.getName() + ".");
			player.getInterfaceManager().close();
		} else {
			player.getInterfaceManager().open(new Component(317));
			PacketRepository.send(MinimapState.class, new MinimapStateContext(player, 2));
			player.getInterfaceManager().openOverlay(new Component(544));
			player.getInterfaceManager().open(new Component(317));
		}

		if (ship == Ships.KARAMJAMA_TO_PORT_SARIM) {
			player.getAchievementDiaryManager().finishTask(player, DiaryType.KARAMJA, 0, 3);
		}
		if (ship == Ships.BRIMHAVEN_TO_ARDOUGNE) {
			player.getAchievementDiaryManager().finishTask(player, DiaryType.KARAMJA, 0, 4);
		}
		if (ship == Ships.CAIRN_ISLAND_TO_PORT_KHAZARD) {
			player.getAchievementDiaryManager().finishTask(player, DiaryType.KARAMJA, 1, 6);
		}
	}

	/**
	 * Method used to prepare the player.
	 */
	private void prepare() {
		player.lock(ship.getDelay() + 1);
		player.getInterfaceManager().open(new Component(299));
		PacketRepository.send(MinimapState.class, new MinimapStateContext(player, 2));
		player.getConfigManager().set(75, ship.getConfig());
	}
}
