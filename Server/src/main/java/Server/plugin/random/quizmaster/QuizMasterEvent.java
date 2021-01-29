package plugin.random.quizmaster;

import java.nio.ByteBuffer;

import core.ServerConstants;
import plugin.ame.AntiMacroEvent;
import core.game.node.entity.Entity;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.SavedData;
import core.game.world.map.Location;
import core.net.packet.PacketRepository;
import core.net.packet.context.CameraContext;
import core.net.packet.context.CameraContext.CameraType;
import core.net.packet.out.CameraViewPacket;
import core.plugin.InitializablePlugin;
import core.plugin.PluginManager;

/**
 * Handles the quiz master random event.
 * @author Vexia
 */
@InitializablePlugin
public final class QuizMasterEvent extends AntiMacroEvent {

	/**
	 * The quiz master random event.
	 */
	private NPC quizMaster;

	/**
	 * The score of the quiz.
	 */
	private int score;

	/**
	 * If the quiz was started.
	 */
	private boolean startedQuiz;

	/**
	 * Constructs a new {@code QuizMasterEvent} {@code Object}.
	 */
	public QuizMasterEvent() {
		super("quiz master", true, true);
	}

	@Override
	public void save(ByteBuffer buffer) {
		SavedData.save(buffer, score, 1);
		SavedData.save(buffer, startedQuiz, 2);
		buffer.put((byte) 3);
		Location l = player.getAttribute("ame:location", ServerConstants.HOME_LOCATION);
		l.save(buffer);
		buffer.put((byte) 0);
	}

	@Override
	public void parse(ByteBuffer buffer) {
		int opcode;
		while ((opcode = buffer.get()) != 0) {
			switch (opcode) {
			case 1:
				score = buffer.get();
				break;
			case 2:
				startedQuiz = SavedData.getBoolean(buffer.get());
				break;
			case 3:
				player.setAttribute("ame:location", Location.parse(buffer));
				break;
			}
		}
	}

	@Override
	public boolean enter(Entity entity) {
		/*
		 * if (player.getName().equals("vexia")) { return false; } if (entity
		 * instanceof Player) { ((Player)
		 * entity).getInterfaceManager().removeTabs(0, 1, 2, 3, 4, 5, 6, 7, 11,
		 * 12); } return super.enter(entity);
		 */
		return false;
	}

	@Override
	public boolean start(Player player, boolean login, Object... args) {
		return false;
		/*
		 * if (player.getName().equals("vexia")) { return false; }
		 */
		/*
		 * setRegion(); if (login) { quizMaster = NPC.create(2477,
		 * getLocation(32, 32)); quizMaster.init(); teleport(); if (startedQuiz)
		 * { startQuiz(); } return true; } player.getPulseManager().clear();
		 * player.getInterfaceManager().close(); setQuizMaster(false); if
		 * (quizMaster == null) { return false; }
		 * player.setAttribute("ame:location", player.getLocation());
		 * player.lock(5); quizMaster.lock(5); quizMaster.face(player);
		 * quizMaster.graphics(Graphics.create(86));
		 * quizMaster.sendChat("It's your lucky day!");
		 * player.graphics(Graphics.create(86), 3); GameWorld.getPulser().submit(new
		 * Pulse(4, player, quizMaster) {
		 * @Override public boolean pulse() { teleport(); setQuizMaster(true);
		 * return true; } }); super.init(player); return true;
		 */
	}

	@Override
	public boolean leave(Entity e, boolean logout) {
		if (e == player) {
			if (!logout) {
				terminate();
			} else {
				if (quizMaster != null) {
					quizMaster.clear();
				}
				region.flagInactive();
			}
		}
		return super.leave(e, logout);
	}

	@Override
	public void terminate() {
		super.terminate();
		quizMaster = null;
		if (player != null) {
			player.getAnimator().reset();
			player.getAppearance().setDefaultAnimations();
			player.getAppearance().sync();
			player.getInterfaceManager().restoreTabs();
			PacketRepository.send(CameraViewPacket.class, new CameraContext(player, CameraType.RESET, 0, 0, 0, 0, 0));
		}
	}

	@Override
	public AntiMacroEvent create(Player player) {
		final QuizMasterEvent event = new QuizMasterEvent();
		event.player = player;
		return event;
	}

	/**
	 * Resets the score.
	 */
	public void resetScore() {
		score = 0;
	}

	/**
	 * Increments the score.
	 */
	public void incrementScore() {
		score++;
	}

	/**
	 * Gets the score.
	 * @return the score.
	 */
	public int getScore() {
		return score;
	}

	/**
	 * Sets the region.
	 */
	/*
	 * private void setRegion() { region = DynamicRegion.create(7754);
	 * registerRegion(region.getId()); } /** Teleports the player to the quiz.
	 */
	/*
	 * private void teleport() {
	 * player.getAppearance().setAnimations(Animation.create(2378));
	 * player.getAppearance().sync(); player.faceLocation(getLocation(32, 32));
	 * player.getProperties().setTeleportLocation(getLocation(32, 28));
	 * startQuiz(); } /** Sets the quiz master.
	 */
	/*
	 * private void setQuizMaster(boolean teleport) { if (quizMaster == null) {
	 * quizMaster = new QuizMasterNPC(2477, player.getLocation(), this, player);
	 * quizMaster.init(); } if (teleport) {
	 * quizMaster.getProperties().setTeleportLocation(getLocation(32, 32)); } }
	 * /** Stars the quiz.
	 */
	/*
	 * private void startQuiz() { player.getDialogueInterpreter().open(2477,
	 * this); player.sendMessage(
	 * "Answer four questions correctly in a row to be teleported back where you came from."
	 * ); } /** Gets the startedQuiz.
	 * @return The startedQuiz.
	 */
	public boolean isStartedQuiz() {
		return startedQuiz;
	}

	/**
	 * Sets the startedQuiz.
	 * @param startedQuiz The startedQuiz to set.
	 */
	public void setStartedQuiz(boolean startedQuiz) {
		this.startedQuiz = startedQuiz;
	}

	/**
	 * Gets the transformed location.
	 * @return the location.
	 */
	/*
	 * private Location getLocation(int xOff, int yOff) { return
	 * region.getBaseLocation().transform(xOff, yOff, 1); }
	 */

	@Override
	public void register() {
		PluginManager.definePlugin(new QuizMasterDialogue());
	}

	@Override
	public Location getSpawnLocation() {
		return null;
	}

	@Override
	public void configure() {

	}

}
