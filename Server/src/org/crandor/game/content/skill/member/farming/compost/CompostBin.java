package org.crandor.game.content.skill.member.farming.compost;

import org.crandor.cache.def.impl.ConfigFileDefinition;
import org.crandor.cache.def.impl.ObjectDefinition;
import org.crandor.game.container.Container;
import org.crandor.game.content.skill.Skills;
import org.crandor.game.content.skill.member.farming.FarmingConstant;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.item.Item;
import org.crandor.game.system.task.Pulse;
import org.crandor.game.world.GameWorld;
import org.crandor.game.world.update.flag.context.Animation;
import org.crandor.tools.RandomFunction;

import java.util.concurrent.TimeUnit;

/**
 * Represents a compost bin.
 * @author 'Vexia
 * @version 1.0
 */
public final class CompostBin {

	/**
	 * Represents the animation used to fill a compost bin or bucket.
	 */
	private static final Animation FILL_ANIM = new Animation(832);

	/**
	 * Represents the close animation.
	 */
	private static final Animation CLOSE_ANIM = new Animation(835);

	/**
	 * Represents the open animation.
	 */
	private static final Animation OPEN_ANIM = new Animation(834);

	/**
	 * Represents the config value of the compost bin.
	 */
	private static final int CONFIG = 511;

	/**
	 * Represents the container of this compost bin.
	 */
	private final Container container = new Container(15);

	/**
	 * Represents the objects wrapper id.
	 */
	private final int wrapperId;

	/**
	 * Represents the time stamp of when the compost is ready.
	 */
	private long timeStamp;

	/**
	 * Constructs a new {@code CompostBin} {@code Object}.
	 * @param wrapperId the wrapper id.
	 */
	public CompostBin(final int wrapperId) {
		this.wrapperId = wrapperId;
	}

	/**
	 * Method used to fill the compost bin container with an item.
	 * @param player the player.
	 * @param item the item.
	 * @param delay the delay.
	 */
	public void fillBin(final Player player, final Item item, int delay) {
		final String[] messages = isRotted(player) ? new String[] { "The compost bin must be empty of compost before you can put new", "items in it." } : isClosed(player) ? new String[] { "The compost bin is closed" } : !container.hasSpaceFor(item) ? new String[] { "The compost bin is too full to put anything else in it." } : null;
		if (messages != null) {
			player.getDialogueInterpreter().sendDialogue(messages);
			return;
		}
		player.getPulseManager().run(new FillBinPulse(player, item, delay));
	}

	/**
	 * Method used to fill a bucket.
	 * @param player the player.
	 * @param delay .
	 */
	public void fillBucket(final Player player, int delay) {
		if ((!isTomato() && !player.getInventory().containsItem(FarmingConstant.BUCKET)) || isClosed(player) || isRotting(player) || !isRotted(player)) {
			return;
		}
		if (isTomato() && !player.getInventory().hasSpaceFor(FarmingConstant.ROTTEN_TOMATO)) {
			player.getDialogueInterpreter().sendDialogue("You don't have enough inventory space.");
			return;
		}
		player.getPulseManager().run(new FillBucketPulse(player, delay));
	}

	/**
	 * Method used to take a tomato from the bin.
	 * @param player the player.
	 */
	public void takeTomato(Player player) {
		fillBucket(player, 1);
	}

	/**
	 * Method used to close the compost bin.
	 * @param player the player.
	 */
	public void close(final Player player) {
		long time = (long) (isSuperCompostable() ? TimeUnit.MINUTES.toMillis(90) : RandomFunction.random(TimeUnit.MINUTES.toMillis(35), TimeUnit.MINUTES.toMillis(50))) / 2;
		setTimeStamp((long) (System.currentTimeMillis() + time));
		addConfigValue(player, 31 << getBitShift());
		player.animate(CLOSE_ANIM);
		player.getPacketDispatch().sendMessage("You close the compost bin.");
		player.getPacketDispatch().sendMessage("The " + (isTomato() ? "tomatoes" : "contents") + " have begun to rot.");
	}

	/**
	 * Method used to open the bin.
	 * @param player the player.
	 */
	public void open(final Player player) {
		if (isRotting(player)) {
			player.getDialogueInterpreter().sendDialogue(isTomato() ? "The tomatoes haven't finished rotting yet." : "The vegetation hasn't finished rotting yet.");
			return;
		}
		player.animate(OPEN_ANIM);
		addConfigValue(player, getCompostMultiplier(player) << getBitShift());
		player.getPacketDispatch().sendMessage("You open the compost bin.");
	}

	/**
	 * Method used to reset the bin and remove it from manager.
	 * @param player the player.
	 */
	public void reset(final Player player) {
		addConfigValue(player, 0);
		player.getFarmingManager().getCompostManager().getBins().remove(this);
		timeStamp = 0L;
		container.clear();
	}

	/**
	 * Method used to add a config value.
	 * @param player the player.
	 * @param value the value.
	 */
	public void addConfigValue(final Player player, final int value) {
		player.getConfigManager().set(CONFIG, (player.getConfigManager().get(CONFIG) - getConfigValue(player)) + value, true);
	}

	/**
	 * Method used to get the config value of this compost bin.
	 * @param player the player.
	 * @return the value set.
	 */
	public int getConfigValue(final Player player) {
		return getState(player) << getBitShift();
	}

	/**
	 * Checks if the compost bin is empty and ready to be refilled.
	 * @param player the player.
	 * @return {@code True} if so.
	 */
	public boolean isEmpty(final Player player) {
		return getState(player) == 0;
	}

	/**
	 * Checks if the compost bin is closed.
	 * @param player the player.
	 * @return {@code True} if so.
	 */
	public boolean isClosed(final Player player) {
		return getState(player) == 31;
	}

	/**
	 * Checks if the bin is rotted.
	 * @param player the player.
	 * @return {@code True} if so.
	 */
	public boolean isRotted(final Player player) {
		int state = getState(player);
		return state == 47 || state == 62 || state == 7 || state == 30 || state == 158 || state == 144 || state == 16 || state == 48;
	}

	/**
	 * Checks if the bin is a super compost bin.
	 * @return {@code True} if so.
	 */
	public boolean isSuperCompostable() {
		for (Item item : container.toArray()) {
			if (item == null) {
				continue;
			}
			boolean isSuper = false;
			for (int i : FarmingConstant.SUPERCOMPOST_IDS) {
				if (item.getId() == i) {
					isSuper = true;
					break;
				}
			}
			if (!isSuper) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Checks if the compost is a super compostable.
	 * @param player the player.
	 * @return {@code True} if so.
	 */
	public boolean isSuperCompost(final Player player) {
		return getState(player) == 47 || getState(player) == 62 || getState(player) == 48;
	}

	/**
	 * Gets the compost multiplier.
	 * @param player the player.
	 * @return the multiplier.
	 */
	public int getCompostMultiplier(final Player player) {
		boolean full = container.itemCount() == 15;
		if (isTomato()) {
			return !full ? 144 : 158;
		}
		return isSuperCompostable() ? !full ? 47 : 62 : !full ? 7 : 30;
	}

	/**
	 * Checsk if it's a tomato compost bin.
	 * @return {@code True} if so.
	 */
	public boolean isTomato() {
		return container.getAmount(FarmingConstant.TOMATO) == container.itemCount();
	}

	/**
	 * Checks if the compost bin is rotting.
	 * @param player the player.
	 * @return {@code True} if so.
	 */
	public boolean isRotting(final Player player) {
		return isClosed(player) && timeStamp > System.currentTimeMillis();
	}

	/**
	 * Gets the wrapper config state.
	 * @param player the player.
	 * @return {@code True} if so.
	 */
	public int getState(final Player player) {
		ConfigFileDefinition def = ConfigFileDefinition.forId(ObjectDefinition.forId(wrapperId).getConfigFileId());
		if (def == null) {
			return 0;
		}
		return def.getValue(player);
	}

	/**
	 * Gets the wrapperId.
	 * @return The wrapperId.
	 */
	public int getWrapperId() {
		return wrapperId;
	}

	/**
	 * Gets the config id.
	 * @return the id.
	 */
	public int getConfigId() {
		return ConfigFileDefinition.forId(ObjectDefinition.forId(wrapperId).getConfigFileId()).getConfigId();
	}

	/**
	 * Gets the bitshift for the wrapper.
	 * @return the bitshift.
	 */
	public int getBitShift() {
		ConfigFileDefinition def = ConfigFileDefinition.forId(ObjectDefinition.forId(wrapperId).getConfigFileId());
		if (def == null) {
			return 0;
		}
		return def.getBitShift();
	}

	/**
	 * Gets the container.
	 * @return The container.
	 */
	public Container getContainer() {
		return container;
	}

	/**
	 * Gets the timeStamp.
	 * @return The timeStamp.
	 */
	public long getTimeStamp() {
		return timeStamp;
	}

	/**
	 * Sets the time stamp.
	 * @param timeStamp the stamp.
	 */
	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}

	/**
	 * Represents the filling of a bucket pulse.
	 * @author 'Vexia
	 * @version 1.0
	 */
	public final class FillBucketPulse extends Pulse {

		/**
		 * Represents the player.
		 */
		private final Player player;

		/**
		 * Constructs a new {@code FillBucketPulse} {@code Object}.
		 * @param player the player.
		 * @param delay the delay.
		 */
		public FillBucketPulse(final Player player, int delay) {
			super(delay, player);
			this.player = player;
		}

		@Override
		public boolean pulse() {
			final boolean superCompost = isSuperCompost(player);
			player.animate(FILL_ANIM);
			if (!isTomato()) {
				player.getInventory().replace(superCompost ? FarmingConstant.SUPERCOMPOST : FarmingConstant.COMPOST, player.getInventory().getSlot(FarmingConstant.BUCKET));
			} else {
				player.getInventory().add(FarmingConstant.ROTTEN_TOMATO);
			}
			player.getSkills().addExperience(Skills.FARMING, 4.5, true);
			int state = getState(player);
			if (state == 30 || state == 62) {
				addConfigValue(player, (state == 30 ? 16 : 48) << getBitShift());
			}
			container.replace(null, container.itemCount() - 1);
			if (isTomato() && container.itemCount() != 0) {
				return true;
			}
			if (container.itemCount() != 0) {
				fillBucket(player, 3);
			} else {
				reset(player);
			}
			return true;
		}

	}

	/**
	 * Represents the filling of the compost bin pulse.
	 * @author 'Vexia
	 * @version 1.0
	 */
	public final class FillBinPulse extends Pulse {

		/**
		 * Represents the player instance.
		 */
		private final Player player;

		/**
		 * Represents the item being used.
		 */
		private final Item item;

		/**
		 * Constructs a new {@code FillBinPulse} {@code Object}.
		 * @param player the player.
		 * @param item the item.
		 * @param delay the delay.
		 */
		public FillBinPulse(final Player player, final Item item, int delay) {
			super(delay, player);
			this.player = player;
			this.item = item;
		}

		@Override
		public boolean pulse() {
			final boolean isWeeds = item.getId() == FarmingConstant.WEEDS.getId() ? true : false;
			final Item item = isWeeds ? new Item(FarmingConstant.WEEDS.getId(), getWeedDifference()) : this.item;
			if (player.getInventory().remove(item)) {
				player.animate(FILL_ANIM);
				container.add(item);
				if (container.freeSlots() == 0) {
					addConfigValue(player, (isTomato() ? 143 : (!isSuperCompostable() ? 15 : 47)) << getBitShift());
					return true;
				}
				addConfigValue(player, (isTomato() ? 129 : (!isSuperCompostable() ? 8 : 33)) << getBitShift());
			}
			if (!isWeeds && player.getInventory().containsItem(item)) {
				fillBin(player, item, 3);
			}
			return true;
		}

		/**
		 * Gets the difference in amount of weed.
		 * @return the amount to use.
		 */
		private int getWeedDifference() {
			int amount = player.getInventory().getAmount(FarmingConstant.WEEDS);
			if (amount + container.itemCount() > 15) {
				int difference = 15 - container.itemCount();
				if (amount > difference) {
					amount = difference;
				}
			}
			return amount;
		}

	}
}