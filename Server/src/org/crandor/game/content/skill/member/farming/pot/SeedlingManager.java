package org.crandor.game.content.skill.member.farming.pot;

import org.crandor.game.container.Container;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.entity.player.info.login.SavingModule;
import org.crandor.game.node.item.Item;
import org.crandor.game.system.task.Pulse;
import org.crandor.game.world.GameWorld;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * A managing class for the growth of seedlings.
 * @author 'Vexia
 */
public final class SeedlingManager implements SavingModule {

	/**
	 * Represents the list of active seedlings.
	 */
	private final List<Item> seedlings = new ArrayList<>();

	/**
	 * Represents the seedling pulse.
	 */
	private SeedlingPulse pulse = new SeedlingPulse();

	/**
	 * Represents the player instance.
	 */
	private final Player player;

	/**
	 * Constructs a new {@code SeedlingManager} {@code Object}.
	 * @param player the player.
	 */
	public SeedlingManager(final Player player) {
		this.player = player;
	}

	@Override
	public void save(ByteBuffer buffer) {
	}

	@Override
	public void parse(ByteBuffer buffer) {
		getSeedling(player.getInventory());
		getSeedling(player.getBank());
		if (seedlings.size() > 0) {
			pulse.setActive(true);
			GameWorld.Pulser.submit(pulse);
		}
	}

	/**
	 * Method used to add a seedling to the manager.
	 * @param seedling the seedling.
	 */
	public void addSeedling(final Item seedling) {
		seedling.setCharge(1001);
		seedlings.add(seedling);
		if (!pulse.isActive()) {
			pulse.stop();
			pulse.start();
			pulse.restart();
			GameWorld.Pulser.submit(pulse);
		}
	}

	/**
	 * Gets the active seedlings from a container.
	 * @param container the container.
	 */
	private void getSeedling(Container container) {
		Saplings sap = null;
		for (Item item : container.toArray()) {
			if (item == null) {
				continue;
			}
			if (item.getCharge() < 1001) {
				continue;
			}
			sap = Saplings.forSeedling(item);
			if (sap != null) {
				seedlings.add(item);
			}
		}
	}

	/**
	 * Gets the seedlings.
	 * @return The seedlings.
	 */
	public List<Item> getSeedlings() {
		return seedlings;
	}

	/**
	 * Represents the seedling growth pulse.
	 * @author 'Vexia
	 */
	public final class SeedlingPulse extends Pulse {

		/**
		 * If active.
		 */
		private boolean active;

		/**
		 * Constructs a new {@code SeedlingPulse} {@code Object}.
		 */
		public SeedlingPulse() {
			super(100, player);
		}

		@Override
		public boolean pulse() {
			final boolean invy = updateSeedling(player.getInventory());
			final boolean bank = updateSeedling(player.getBank());
			if (!invy && !bank) {
				setActive(false);
				return true;
			}
			return false;
		}

		/**
		 * Method used to update the seedlings.
		 * @param container the container.
		 */
		private boolean updateSeedling(Container container) {
			Saplings sapling = null;
			boolean updated = false;
			for (Item seedling : container.toArray()) {
				if (seedling == null) {
					continue;
				}
				if (seedling.getCharge() < 1001) {
					continue;
				}
				sapling = Saplings.forSeedling(seedling);
				if (sapling == null) {
					continue;
				}
				updated = true;
				int minute = 1005 - seedling.getCharge();
				if (minute == 0) {
					seedlings.remove(seedling);
					container.remove(new Item(seedling.getId(), 1));
					container.add(sapling.getSapling());
				} else {
					seedling.setCharge(seedling.getCharge() + 1);
				}
			}
			return updated;
		}

		/**
		 * Gets the active.
		 * @return The active.
		 */
		public boolean isActive() {
			return active;
		}

		/**
		 * Sets the active.
		 * @param active The active to set.
		 */
		public void setActive(boolean active) {
			this.active = active;
		}
	}

}
