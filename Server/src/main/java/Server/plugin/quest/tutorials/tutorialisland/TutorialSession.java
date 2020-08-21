package plugin.quest.tutorials.tutorialisland;

import core.game.component.Component;
import core.game.node.entity.player.Player;
import core.game.system.task.Pulse;
import core.game.world.GameWorld;
import core.net.packet.PacketRepository;
import core.net.packet.context.InterfaceConfigContext;
import core.net.packet.out.InterfaceConfig;

/**
 * Represents the session used during tutorial.
 * @author 'Vexia
 */
public final class TutorialSession {

	/**
	 * The maximum stage, the tutorial can go to.
	 */
	public final static int MAX_STAGE = 72;

	/**
	 * Represents the player instance.
	 */
	private Player player;

	/**
	 * Represents the delay pulse.
	 */
	private DelayPulse delayPulse;

	/**
	 * Constructs a new {@code TutorialSession} {@code Object}.
	 * @param player the player.
	 */
	public TutorialSession(Player player) {
		this.player = player;
	}

	/**
	 * Method used to extend this class.
	 * @param player the player.
	 */
	public static void extend(Player player) {
		player.addExtension(TutorialSession.class, new TutorialSession(player));
	}

	/**
	 * Method used to init this session.
	 */
	public void init() {
		if (getStage() >= MAX_STAGE) {
			player.removeAttribute("tut-island");
			setStage(MAX_STAGE + 1);
			return;
		}
		if (getStage() < 26) {
			player.getSettings().setRunToggled(true);
		}
		delayPulse = new DelayPulse();
		player.setAttribute("tut-island", true);
		player.getInterfaceManager().openOverlay(new Component(371));
		GameWorld.Pulser.submit(delayPulse);
		PacketRepository.send(InterfaceConfig.class, new InterfaceConfigContext(player, 371, 25, true));
		TutorialStage.load(player, getStage(), true);
	}

	/**
	 * If the player has finished tutorial island.
	 * @return {@code True} if so.
	 */
	public boolean finished() {
		return getStage() >= MAX_STAGE;
	}

	/**
	 * Method used to return the instance of the tutorial session.
	 * @param player the player.
	 * @return the session.
	 */
	public static TutorialSession getExtension(Player player) {
		if (player.getExtension(TutorialSession.class) == null) {
			extend(player);
		}
		return player.getExtension(TutorialSession.class);
	}

	/**
	 * Method used to return the stage of the tutorial, the stage is saved in
	 * the {@link Player#getGameAttributes()} since it's a single piece of data
	 * to save, the stage should never be reset after the tutorial is finished.
	 * @return the value of the stage.
	 */
	public int getStage() {
		if (player.isArtificial()) {
			return MAX_STAGE + 1;
		}
		return player.getSavedData().getGlobalData().getTutorialStage();
	}

	/**
	 * Gets the delay pulse.
	 * @return the delay pulse.
	 */
	public DelayPulse getDelayPulse() {
		return delayPulse == null ? new DelayPulse() : delayPulse;
	}

	/**
	 * Method used to set the stage of the tutorial.
	 */
	public void setStage(int stage) {
		getDelayPulse().reset();
		getDelayPulse().hide();
		player.getSavedData().getGlobalData().setTutorialStage(stage);
	}

	/**
	 * Gets the player.
	 * @return the player.
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * Sets the player.
	 * @param player the player to set.
	 */
	public void setPlayer(Player player) {
		this.player = player;
	}

	/**
	 * Gets the maximum stage.
	 * @return the max stage.
	 */
	public int getMaxStage() {
		return MAX_STAGE;
	}

	/**
	 * Represents the pulse used to inform a player to go to the next stage.
	 * @author 'Vexia
	 * @version 1.0
	 */
	public final class DelayPulse extends Pulse {

		/**
		 * Represents the delay.
		 */
		private int delay;

		/**
		 * Constructs a new {@code DelayPulse} {@code Object}.
		 */
		public DelayPulse() {
			super(1, player);
			reset();
		}

		@Override
		public boolean pulse() {
			if (delay < GameWorld.getTicks()) {
				show();
			}
			return false;
		}

		/**
		 * Method used to reset the delay.
		 */
		public void reset() {
			delay = GameWorld.getTicks() + 20000;
		}

		/**
		 * Method used to hide the arrow.
		 */
		public void hide() {
			PacketRepository.send(InterfaceConfig.class, new InterfaceConfigContext(player, 371, 24, true));
		}

		/**
		 * Method used to show the arrow.
		 */
		public void show() {
			reset();
			PacketRepository.send(InterfaceConfig.class, new InterfaceConfigContext(player, 371, 24, false));
		}
	}
}
