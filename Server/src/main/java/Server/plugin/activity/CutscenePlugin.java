package plugin.activity;

import core.game.component.Component;
import core.game.node.entity.Entity;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.game.system.task.Pulse;
import core.game.world.GameWorld;
import core.game.world.map.Location;
import core.game.world.map.build.DynamicRegion;
import core.net.packet.PacketRepository;
import core.net.packet.context.MinimapStateContext;
import core.net.packet.out.MinimapState;
import core.plugin.PluginManifest;
import core.plugin.PluginType;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the plugin used to handle a cutscene.
 * @author Vexia
 * @date 28/12/2013
 */
@PluginManifest(type = PluginType.ACTIVITY)
public abstract class CutscenePlugin extends ActivityPlugin {

	/**
	 * The list of tabs to remove.
	 */
	private static final int[] TABS = new int[] { 0, 1, 2, 3, 4, 5, 6, 11, 12 };

	/**
	 * The npcs in our cutscene.
	 */
	protected final List<NPC> npcs = new ArrayList<>();

	/**
	 * The start pulse used for effect.
	 */
	private final StartPulse startPulse = new StartPulse();

	/**
	 * The ending pulse used for effect.
	 */
	private final EndPulse endPulse = new EndPulse();

	/**
	 * If we should use a fade in or not.
	 */
	private final boolean fade;

	/**
	 * Constructs a new {@code CutscenePlugin} {@code Object}.
	 * @param name the name of the cutscene/mapzone.
	 * @param fading in or not.
	 */
	public CutscenePlugin(String name, final boolean fade) {
		super(name, true, false, true);
		this.fade = fade;
	}

	/**
	 * Constructs a new {@code CutscenePlugin} {@code Object}.
	 * @param name the name.
	 */
	public CutscenePlugin(final String name) {
		this(name, true);
	}

	@Override
	public boolean start(final Player player, boolean login, Object... args) {
		player.setAttribute("cutscene:original-loc", player.getLocation());
		player.removeAttribute("real-end");
		player.setAttribute("real-end", player.getLocation());
		if (isFade()) {
			GameWorld.Pulser.submit(getStartPulse());
		} else {
			PacketRepository.send(MinimapState.class, new MinimapStateContext(player, getMapState()));
			player.getInterfaceManager().hideTabs(getRemovedTabs());
			player.getProperties().setTeleportLocation(getStartLocation());
			player.unlock();
			player.getWalkingQueue().reset();
			player.getLocks().lockMovement(1000000);
			player.getInterfaceManager().close();
			open();
		}
		player.lock();
		return super.start(player, login, args);
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean leave(final Entity e, boolean logout) {
		if (player != null) {
			if (logout) {
				player.setLocation(player.getAttribute("cutscene:original-loc", player.getLocation()));
				end();
			} else {
				unpause();
			}
			player.unlock();
			player.getWalkingQueue().reset();
			player.getLocks().unlockMovement();
		}
		return super.leave(e, logout);
	}

	@Override
	public Object fireEvent(String identifier, Object... args) {
		return null;
	}

	/**
	 * Method used to stop the cutscene.
	 * @param if we should use a fade cutout.
	 */
	public void stop(boolean fade) {
		if (fade) {
			GameWorld.Pulser.submit(endPulse);
		} else {
			end();
		}
	}

	/**
	 * Method used to end the cutscene.
	 */
	public void end() {
		if (region != null) {
			for (int i = 0; i < region.getPlanes().length; i++) {
				for (NPC n : region.getPlanes()[i].getNpcs()) {
					if (n == null) {
						continue;
					}
					n.clear();
				}
			}
		}
		PacketRepository.send(MinimapState.class, new MinimapStateContext(player, 0));
		player.getInterfaceManager().restoreTabs();
		player.unlock();// incase he was locked.
		player.getWalkingQueue().reset();
	}

	/**
	 * Represents the pulse used when starting a cutscene. This is used to give
	 * dramatic effect for entering a cutscene. In the future allow for this to
	 * be toggled.
	 * @author 'Vexia
	 * @date 30/12/2013
	 */
	public class StartPulse extends Pulse {

		/**
		 * Represents the counter.
		 */
		private int counter = 0;

		/**
		 * Constructs a new {@code StartPulse} {@code Object}.
		 */
		public StartPulse() {
			super(1, player);
		}

		@Override
		public boolean pulse() {
			switch (counter++) {
			case 1:
				player.lock();
				player.getInterfaceManager().openOverlay(new Component(115));
				break;
			case 3:
				PacketRepository.send(MinimapState.class, new MinimapStateContext(player, getMapState()));
				player.getInterfaceManager().hideTabs(getRemovedTabs());
				break;
			case 4:
				player.getProperties().setTeleportLocation(getStartLocation());
				break;
			case 5:
				player.getInterfaceManager().closeOverlay();
				player.getInterfaceManager().close();
				player.unlock();
				player.getWalkingQueue().reset();
				player.getLocks().lockMovement(1000000);
				open();
				return true;
			}
			return false;
		}

	}

	/**
	 * Represents the pulse used when ending a cutscene. This is used to give
	 * dramatic effect for entering a cutscene.
	 * @author 'Vexia
	 * @date 30/12/2013
	 */
	public class EndPulse extends Pulse {

		/**
		 * Represents the counter.
		 */
		private int counter = 0;

		/**
		 * Constructs a new {@code EndPulse} {@code Object}.
		 */
		public EndPulse() {
			super(1, player);
		}

		@Override
		public boolean pulse() {
			switch (counter++) {
			case 1:
				player.lock();
				player.getInterfaceManager().openOverlay(new Component(115));
				break;
			case 3:
				PacketRepository.send(MinimapState.class, new MinimapStateContext(player, getMapState()));
				player.getInterfaceManager().hideTabs(getRemovedTabs());
				break;
			case 4:
				Location loc = (Location) (player.getAttribute("real-end", player.getAttribute("cutscene:original-loc", player.getLocation())));
				player.getProperties().setTeleportLocation(loc);
				break;
			case 5:
				end();
				stop();
				fade();// specfic for fadeout.
				if (player.getSession().isActive()) {
					PacketRepository.send(MinimapState.class, new MinimapStateContext(player, 0));
				}
				player.getInterfaceManager().closeOverlay();
				if (player.getSession().isActive()) {
					player.getInterfaceManager().close();
				}
				return true;
			}
			return false;
		}

	}

	/**
	 * Method called when the dim interface is closed. And you can see the
	 * cutscene.
	 */
	public void open() {

	}

	/**
	 * Method called on the end of the cutscene.
	 */
	public void fade() {

	}

	/**
	 * Gets the mapstate to use. (override if needed).
	 * @return the state.
	 */
	public int getMapState() {
		return 2;
	}

	/**
	 * Gets the removed tabs. (override if needed).
	 * @return the tabs.
	 */
	public int[] getRemovedTabs() {
		return TABS;
	}

	/**
	 * Gets the starting location (region based, override if needed).
	 * @return the location.
	 */
	public Location getStartLocation() {
		return getBase();
	}

	/**
	 * Method used to unpause this cutscene.
	 */
	public final void unpause() {
		stop(true);
	}

	/**
	 * Gets the player.
	 * @return The player.
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * Gets the region.
	 * @return The region.
	 */
	public DynamicRegion getRegion() {
		return region;
	}

	/**
	 * Gets the nPCS.
	 * @return The nPCS.
	 */
	public List<NPC> getNPCS() {
		return npcs;
	}

	/**
	 * Gets the fade.
	 * @return The fade.
	 */
	public boolean isFade() {
		return fade;
	}

	/**
	 * Gets the start pulse.
	 * @return the pulse.
	 */
	public Pulse getStartPulse() {
		return startPulse;
	}

	public Pulse getEndPulse(){return new EndPulse();}
}
