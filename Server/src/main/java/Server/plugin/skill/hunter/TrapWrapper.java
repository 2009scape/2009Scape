package plugin.skill.hunter;

import plugin.skill.hunter.NetTrapSetting.NetTrap;
import core.game.node.entity.player.Player;
import core.game.node.item.Item;
import core.game.node.object.GameObject;
import core.game.node.object.ObjectBuilder;
import core.game.world.GameWorld;
import core.game.world.update.flag.context.Animation;
import core.game.world.update.flag.context.Graphics;

import java.util.ArrayList;
import java.util.List;

/**
 * A wrapper class for a trap object.
 * @author Vexia
 */
public final class TrapWrapper {

	/**
	 * The items to be recieved on trap clearing.
	 */
	private final List<Item> items = new ArrayList<>();

	/**
	 * The player instance.
	 */
	private final Player player;

	/**
	 * The type of trap.
	 */
	private final Traps type;

	/**
	 * The net type if net trapping.
	 */
	private NetTrap netType;

	/**
	 * The original object id.
	 */
	private final int originalId;

	/**
	 * The object of the trap.
	 */
	private GameObject object;

	/**
	 * The secondary game object.
	 */
	private GameObject secondary;

	/**
	 * The trap hook.
	 */
	private TrapHook hook;

	/**
	 * The reward of the trap.
	 */
	private TrapNode reward;

	/**
	 * If the trap has been smoked.
	 */
	private boolean smoked;

	/**
	 * If the trap has been baited.
	 */
	private boolean baited;

	/**
	 * If the trap has failed.
	 */
	private boolean failed;

	/**
	 * If the trap is currently in an reward.
	 */
	private int busyTicks;

	/**
	 * The tick when the life is up.
	 */
	private int ticks;

	/**
	 * Constructs a new {@code TrapWrapper} {@code Object}.
	 * @param player the player.
	 * @param type the type.
	 * @param object the object.
	 */
	public TrapWrapper(final Player player, Traps type, GameObject object) {
		this.player = player;
		this.type = type;
		this.object = object;
		this.originalId = object.getId();
		this.ticks = GameWorld.getTicks() + (100);
		this.object.getAttributes().setAttribute("trap-uid", player.getHunterManager().getUid());
	}

	/**
	 * Cycles this trap wrapper.
	 * @return {@code True} to deregister the trap.
	 */
	public boolean cycle() {
		if (isTimeUp() && type.getSettings().clear(this, 0)) {
			if (!isCaught()) {
				player.sendMessage(type.getSettings().getTimeUpMessage());
			}
			return true;
		}
		return false;
	}

	/**
	 * Sets the new object of the wrapper.
	 * @param id the id.
	 */
	public void setObject(final int id) {
		GameObject newObject = object.transform(id);
		ObjectBuilder.remove(object);
		this.object = ObjectBuilder.add(newObject);
		this.object.getAttributes().setAttribute("trap-uid", player.getHunterManager().getUid());
	}

	/**
	 * Smokes the trap.
	 */
	public void smoke() {
		if (smoked) {
			player.sendMessage("This trap has already been smoked.");
			return;
		}
		if (player.getHunterManager().getStaticLevel() < 39) {
			player.sendMessage("You need a Hunter level of at least 39 to be able to smoke traps.");
			return;
		}
		smoked = true;
		player.lock(4);
		player.visualize(new Animation(5208), new Graphics(931));
		player.sendMessage("You use the smoke from the torch to remove your scent from the trap.");
	}

	/**
	 * Baits the trap.
     */
	public void bait(Item bait) {
		if (baited) {
			player.sendMessage("This trap has already been baited.");
			return;
		}
		if (!type.getSettings().hasBait(bait)) {
			player.sendMessage("You can't use that on this trap.");
			return;
		}
		baited = true;
		bait = new Item(bait.getId(), 1);
		player.getInventory().remove(new Item(bait.getId(), 1));
	}

	/**
	 * Gets the chance ratio of luring.
	 * @return the chance rate.
	 */
	public double getChanceRate() {
		double chance = 0.0;
		if (baited) {
			chance += 1.0;
		}
		if (smoked) {
			chance += 1.0;
		}
		chance += HunterGear.getChanceRate(player);
		return chance;
	}

	/**
	 * Adds items to the recieved list.
	 * @param items the items.
	 */
	public void addItem(Item... items) {
		for (Item item : items) {
			addItem(item);
		}
	}

	/**
	 * Adds an item to the recieved list.
	 * @param item the item.
	 */
	public void addItem(Item item) {
		items.add(item);
	}

	/**
	 * Checks if the time is up.
	 * @return {@code True} if o.
	 */
	private boolean isTimeUp() {
		return ticks < GameWorld.getTicks();
	}

	/**
	 * Gets the type.
	 * @return The type.
	 */
	public Traps getType() {
		return type;
	}

	/**
	 * Gets the object.
	 * @return The object.
	 */
	public GameObject getObject() {
		return object;
	}

	/**
	 * The original id.
	 * @return the id.
	 */
	public int getOriginalId() {
		return originalId;
	}

	/**
	 * Gets the player.
	 * @return The player.
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * Gets the ticks.
	 * @return The ticks.
	 */
	public int getTicks() {
		return ticks;
	}

	/**
	 * Sets the ticks.
	 * @param ticks The ticks to set.
	 */
	public void setTicks(int ticks) {
		this.ticks = ticks;
	}

	/**
	 * Gets the smoked.
	 * @return The smoked.
	 */
	public boolean isSmoked() {
		return smoked;
	}

	/**
	 * Sets the smoked.
	 * @param smoked The smoked to set.
	 */
	public void setSmoked(boolean smoked) {
		this.smoked = smoked;
	}

	/**
	 * Gets the hook.
	 * @return The hook.
	 */
	public TrapHook getHook() {
		return hook;
	}

	/**
	 * Sets the hook.
	 * @param hook The hook to set.
	 */
	public void setHook(TrapHook hook) {
		this.hook = hook;
	}

	/**
	 * Gets the baited.
	 * @return The baited.
	 */
	public boolean isBaited() {
		return baited;
	}

	/**
	 * Sets the baited.
	 * @param baited The baited to set.
	 */
	public void setBaited(boolean baited) {
		this.baited = baited;
	}

	/**
	 * Checks if the trap has been caught.
	 * @return {@code True} if so.
	 */
	public boolean isCaught() {
		return getReward() != null;
	}

	/**
	 * Gets the reward.
	 * @return The reward.
	 */
	public TrapNode getReward() {
		return reward;
	}

	/**
	 * Sets the reward.
	 * @param reward The reward to set.
	 */
	public void setReward(TrapNode reward) {
		this.reward = reward;
		this.addItem(reward.getRewards());
	}

	/**
	 * Checks if the trap is busy.
	 * @return {@code True} if so.
	 */
	public boolean isBusy() {
		return getBusyTicks() > GameWorld.getTicks();
	}

	/**
	 * Gets the busyTicks.
	 * @return The busyTicks.
	 */
	public int getBusyTicks() {
		return busyTicks;
	}

	/**
	 * Sets the busyTicks.
	 * @param busyTicks The busyTicks to set.
	 */
	public void setBusyTicks(int busyTicks) {
		this.busyTicks = GameWorld.getTicks() + busyTicks;
	}

	/**
	 * Gets the items.
	 * @return The items.
	 */
	public List<Item> getItems() {
		return items;
	}

	/**
	 * Gets the secondary.
	 * @return The secondary.
	 */
	public GameObject getSecondary() {
		return secondary;
	}

	/**
	 * Sets the secondary.
	 * @param secondary The secondary to set.
	 */
	public void setSecondary(GameObject secondary) {
		this.secondary = secondary;
		this.secondary.getAttributes().setAttribute("trap-uid", player.getName().hashCode());
	}

	/**
	 * Gets the netType.
	 * @return The netType.
	 */
	public NetTrap getNetType() {
		return netType;
	}

	/**
	 * Sets the netType.
	 * @param netType The netType to set.
	 */
	public void setNetType(NetTrap netType) {
		this.netType = netType;
	}

	/**
	 * Sets the object.
	 * @param object The object to set.
	 */
	public void setObject(GameObject object) {
		this.object = object;
	}

	/**
	 * Gets the failed.
	 * @return The failed.
	 */
	public boolean isFailed() {
		return failed;
	}

	/**
	 * Sets the failed.
	 * @param failed The failed to set.
	 */
	public void setFailed(boolean failed) {
		this.failed = failed;
	}

}
