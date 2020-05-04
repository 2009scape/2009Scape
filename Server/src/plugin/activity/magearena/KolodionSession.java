package plugin.activity.magearena;

import org.crandor.game.node.entity.npc.NPC;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.system.task.Pulse;
import org.crandor.game.world.GameWorld;
import org.crandor.game.world.map.Location;

/**
 * Represents a session with kolodion.
 * @author Vexia
 */
public final class KolodionSession {

	/**
	 * The player.
	 */
	private final Player player;

	/**
	 * The kolodion npc.
	 */
	private final KolodionNPC kolodion;

	/**
	 * Constructs a new {@code ExperimentSession} {@code Object}.
	 * @param player the player.
	 */
	public KolodionSession(final Player player) {
		this.player = player;
		this.kolodion = new KolodionNPC(KolodionNPC.KolodionType.values()[player.getSavedData().getActivityData().getKolodionBoss()].getNpcId(), Location.create(3106, 3934, 0), this);
		if (player.getExtension(KolodionSession.class) != null) {
			player.removeExtension(KolodionSession.class);
		}
		player.addExtension(KolodionSession.class, this);
	}

	/**
	 * Creates the kolodion session.
	 * @param player the player.
	 * @return the session.
	 */
	public static KolodionSession create(Player player) {
		return new KolodionSession(player);
	}

	/**
	 * Starts the session.
	 */
	public void start() {
		if (kolodion.getType().ordinal() > 0) {
			kolodion.init();
			kolodion.sendChat("Let us continue with our battle.");
			kolodion.getProperties().getCombatPulse().attack(player);
			player.unlock();
			player.getAnimator().reset();
			return;
		}
		GameWorld.Pulser.submit(new Pulse(1, player) {
			int count;

			@Override
			public boolean pulse() {
				switch (++count) {
				case 3:
					player.getAnimator().reset();
					break;
				case 5:
					player.getPacketDispatch().sendPositionedGraphic(86, 1, 0, Location.create(3106, 3934, 0));
					break;
				case 6:
					kolodion.init();
					kolodion.faceTemporary(player, 1);
					break;
				case 7:
					kolodion.sendChat("You must prove yourself... now!");
					break;
				case 9:
					player.unlock();
					kolodion.setCommenced(true);
					return true;
				}
				return false;
			}
		});
	}

	/**
	 * Closes the session.
	 */
	public void close() {
		kolodion.clear();
		player.removeExtension(KolodionSession.class);
	}

	/**
	 * Gets the kolodion session.
	 * @param player the player.
	 * @return the session.
	 */
	public static KolodionSession getSession(Player player) {
		return player.getExtension(KolodionSession.class);
	}

	/**
	 * Gets the player.
	 * @return The player.
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * Gets the kolodion.
	 * @return The kolodion.
	 */
	public NPC getKolodion() {
		return kolodion;
	}
}
