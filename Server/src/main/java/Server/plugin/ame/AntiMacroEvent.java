package plugin.ame;

import plugin.activity.ActivityPlugin;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.info.login.SavingModule;
import core.game.node.item.Item;
import core.game.world.map.Location;
import core.game.world.map.zone.ZoneRestriction;
import core.tools.RandomFunction;

/**
 * Handles an anti-macro event.
 * @author Emperor
 */
public abstract class AntiMacroEvent extends ActivityPlugin implements SavingModule {

	/**
	 * The random locations to teleport to if unresponsive.
	 */
	public static final Location[] LOCATIONS = new Location[] { Location.create(3218, 9616, 0),// Lumby
			// basement
			Location.create(3200, 3228, 0),// behind lumby
			Location.create(2961, 3381, 0),// Fally square
	};

	/**
	 * The skill ids.
	 */
	private final int[] skillIds;

	/**
	 * The player.
	 */
	protected Player player;

	/**
	 * If the anti-macro event is terminated.
	 */
	protected boolean terminated;

	/**
	 * If saving is required.
	 */
	private boolean saveRequired;

	/**
	 * Constructs a new {@code AntiMacroEvent} {@code Object}.
	 * @param name The random event name.
	 * @param instanced If the event is instanced.
	 * @param saveRequired .
	 * @param skillIds The skill ids of the skills that fire this event (nothing
	 * for default).
	 */
	public AntiMacroEvent(String name, boolean instanced, boolean saveRequired, int... skillIds) {
		super(name, instanced, false, false, ZoneRestriction.RANDOM_EVENTS, ZoneRestriction.FIRES, ZoneRestriction.FOLLOWERS);
		this.saveRequired = saveRequired;
		this.skillIds = skillIds;
	}

	@Override
	public AntiMacroEvent newInstance(Player player) {
		AntiMacroHandler.register(this);
		return this;
	}

	@Override
	public abstract boolean start(Player player, boolean login, Object... args);

	/**
	 * Creates a new anti macro event instance.
	 * @param player The player.
	 * @return The anti macro event instance.
	 */
	public abstract AntiMacroEvent create(Player player);

	/**
	 * Called to end the macro event.
	 */
	public void terminate() {
		if (terminated) {
			return;
		}
		if (player != null) {
			player.getAntiMacroHandler().setEvent(null);
		}
		terminated = true;
	}

	/**
	 * Initializes the random event for the player.
	 * @param player The player.
	 */
	public void init(Player player) {
		player.addExtension(AntiMacroEvent.class, this);
	}

	/**
	 * Notes all the items in an inventory.
	 */
	public void noteItems() {
		if (player == null) {
			return;
		}
		for (Item i : player.getInventory().toArray()) {
			if (i == null || !i.isActive()) {
				continue;
			}
			if (i.getDefinition().isUnnoted()) {
				int noteId = i.getDefinition().getNoteId();
				if (noteId < 0) {
					continue;
				}
				player.getInventory().remove(i);
				player.getInventory().add(new Item(noteId, i.getAmount()));
			}
		}
		player.getInventory().shift();
	}

	/**
	 * Checks if the macro event can be fired for the given skill id.
	 * @param skillId The skill id.
	 * @return {@code True} if so.
	 */
	public boolean canFire(int skillId) {
		if (skillIds.length == 0) {
			return true;
		}
		for (int id : skillIds) {
			if (id == skillId) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Gets a random location.
	 * @return a random location.
	 */
	public static Location getRandomLocation() {
		return LOCATIONS[RandomFunction.random(LOCATIONS.length)];
	}

	/**
	 * Wrapper to get the gender prefix.
	 * @return the prefix.
	 */
	public String getGenderPrefix() {
		return getGenderPrefix(player.getAppearance().isMale());
	}

	/**
	 * Gets the gender prefix.
	 * @param male if male.
	 * @return the prefix.
	 */
	public String getGenderPrefix(boolean male) {
		return male ? "Sir" : "Mam";
	}

	/**
	 * Gets the terminated.
	 * @return The terminated.
	 */
	public boolean isTerminated() {
		return terminated;
	}

	/**
	 * Sets the terminated.
	 * @param terminated The terminated to set.
	 */
	public void setTerminated(boolean terminated) {
		this.terminated = terminated;
	}

	/**
	 * Gets the saveRequired.
	 * @return The saveRequired.
	 */
	public boolean isSaveRequired() {
		return saveRequired;
	}

	/**
	 * Sets the saveRequired.
	 * @param saveRequired The saveRequired to set.
	 */
	public void setSaveRequired(boolean saveRequired) {
		this.saveRequired = saveRequired;
	}
}