package org.crandor.game.node.entity.player.link.request.trade;

import org.crandor.cache.def.impl.ItemDefinition;
import org.crandor.game.container.*;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.item.Item;
import org.crandor.game.system.task.Pulse;
import org.crandor.game.world.GameWorld;
import org.crandor.net.packet.PacketRepository;
import org.crandor.net.packet.context.ContainerContext;
import org.crandor.net.packet.out.ContainerPacket;

/**
 * Represents the container during a trade session.
 * @author 'Vexia
 */
public final class TradeContainer extends Container {

	/**
	 * Represents the player of this container.
	 */
	private final Player player;

	/**
	 * Constructs a new {@code TradeContainer} {@code Object}.
	 * @param player the player.
	 */
	public TradeContainer(final Player player) {
		super(28, ContainerType.DEFAULT, SortType.ID);
		this.player = player;
		this.getListeners().add(new TradeListener());
	}

	/**
	 * Method used to offer an item to the container.
	 * @param slot the slot of the item.
	 * @param amount the amount of the item.
	 */
	public void offer(final int slot, int amount) {
		final Item item = getItem(slot, player.getInventory());
		if (!canUse()) {
			return;
		}
		if (!validatedItem(item, slot, amount, player.getInventory())) {
			return;
		}
		if (!tradeable(item) && !GameWorld.getSettings().isDevMode()) {
			player.getPacketDispatch().sendMessage("You can't trade this item.");
			return;
		}
		Item remove = new Item(item.getId(), amount);
		remove.setAmount(stabalizeAmount(remove, amount, player.getInventory()));
		if (!checkCapacity(remove, this)) {
			player.getPacketDispatch().sendMessage("You must accept the current items before adding any more.");
			return;
		}
		if (player.getInventory().remove(remove, slot, true) && super.add(remove)) {
			if (TradeModule.getExtension(player).isAccepted() || TradeModule.getExtension(TradeModule.getExtension(player).getTarget()).isAccepted()) {
				TradeModule.getExtension(player).setAccepted(false, true);
				TradeModule.getExtension(TradeModule.getExtension(player).getTarget()).setAccepted(false, true);
			}
			TradeModule.getExtension(player).update();
			checkAlert();
		}
	}

	/**
	 * Method used to withdraw an item from the container.
	 * @param slot the slot of the item.
	 * @param amount the amount of the item.
	 */
	public void withdraw(final int slot, int amount) {
		Item item = getItem(slot, this);
		if (!canUse()) {
			return;
		}
		if (!validatedItem(item, slot, amount, this)) {
			return;
		}
		Item remove = new Item(item.getId(), amount);
		remove.setAmount(stabalizeAmount(remove, amount, this));
		if (!checkCapacity(remove, player.getInventory())) {
			player.getPacketDispatch().sendMessage("You don't have enough space in your inventory.");
			return;
		}
		if (super.remove(remove, slot, true) && player.getInventory().add(remove)) {
			shift();
			TradeModule.getExtension(player).setModified(true);
			if (TradeModule.getExtension(player).isAccepted() || TradeModule.getExtension(TradeModule.getExtension(player).getTarget()).isAccepted()) {
				TradeModule.getExtension(player).setAccepted(false, true);
				TradeModule.getExtension(TradeModule.getExtension(player).getTarget()).setAccepted(false, true);
				TradeModule.getExtension(player).update();
			} else {
				TradeModule.getExtension(player).update();
			}
		}
		if (TradeModule.getExtension(player).isModified()) {
			alert(slot, true);
		}
	}

	@Override
	public void shift() {
		super.shift();
	}

	/**
	 * Method used to get the item from the slot based on container.
	 * @param slot the slot to get the item from.
	 * @param container the container to use.
	 * @return the item from the container at the slot.
	 */
	private Item getItem(int slot, final Container container) {
		return container.get(slot);
	}

	/**
	 * Method used to check if an item is validated.
	 * @param item the item.
	 * @param slot the slot.
	 * @param amount the amount.
	 * @param container the container.
	 * @return {@code True} if validated.
	 */
	private boolean validatedItem(final Item item, final int slot, final int amount, final Container container) {
		if (item == null) {
			return false;
		}
		return !(slot < 0 || slot > player.getInventory().capacity() || amount < 1);
	}

	/**
	 * Method used to stabalize the amount attempting to be withdrawn/offered.
	 * @param item the item.
	 * @param amount the amount.
	 * @param container the container.
	 * @return the stabalized amount if over.
	 */
	private int stabalizeAmount(final Item item, int amount, final Container container) {
		int maximum = container.getAmount(item);
		return (amount > maximum ? maximum : amount);
	}

	/**
	 * Method used to check if an item is tradeable.
	 * @param item the item.
	 * @return {@code True} if tradeable.
	 */
	private boolean tradeable(final Item item) {
		final ItemDefinition definition = ItemDefinition.forId(item.getId());
		if (item.getId() == 11174 || item.getId() == 11173 || item.getId() == 759) {
			return true;
		}
		if (player.getIronmanManager().isIronman() || TradeModule.getExtension(player).getTarget() != null && TradeModule.getExtension(player).getTarget().getIronmanManager().isIronman()) {
			return false;
		}
		if (item.getName().equals("Coins") && item.getId() != 995) {
			return false;
		}
		return definition.isTradeable();
	}

	/**
	 * Method used to check if the player can use the container.
	 * @return {@code True} if so.
	 */
	private boolean canUse() {
		if (!player.getInterfaceManager().isOpened() || TradeModule.getExtension(player) == null || TradeModule.getExtension(TradeModule.getExtension(player).getTarget()) == null || TradeModule.getExtension(player).getStage() != 0) {
			return false;
		}
		return true;
	}

	/**
	 * Method used to check if the container has capacity for more items.
	 * @param item the item to remove or add.
	 * @param container the container to check capacity for.
	 * @return {@code True} if there is enough room.
	 */
	private boolean checkCapacity(final Item item, final Container container) {
		if (item.getAmount() > container.getMaximumAdd(item)) {
			item.setAmount(container.getMaximumAdd(item));
			if (item.getAmount() < 1) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Method used to send an alert icon.
	 * @param slot the slot.
	 * @param save if we should cache the icon.
	 */
	private void alert(final int slot, final boolean save) {
		GameWorld.Pulser.submit(new Pulse(1, player) {
			@Override
			public boolean pulse() {
				if (TradeModule.getExtension(player) != null) {
					TradeModule.getExtension(player).alert(slot);
				}
				return true;
			}
		});
		if (save) {
			player.setAttribute("alert", GameWorld.getTicks() + 8);
			player.setAttribute("alertSlot", slot);
		}
	}

	/**
	 * Method used to check the alert.
	 */
	private void checkAlert() {
		if (player.getAttribute("alert", 0) > GameWorld.getTicks()) {
			alert(player.getAttribute("alertSlot", 0), false);
		}
	}

	/**
	 * Represents the container listener for a players trade session.
	 * @author 'Vexia
	 */
	public final class TradeListener implements ContainerListener {

		@Override
		public void update(Container c, ContainerEvent event) {//type 541 - loaning
			PacketRepository.send(ContainerPacket.class, new ContainerContext(player, -1, 64212, 90, c.toArray(), c.capacity(), false));	
			if (TradeModule.getExtension(player) != null && TradeModule.getExtension(TradeModule.getExtension(player).getTarget()) != null) {
				PacketRepository.send(ContainerPacket.class, new ContainerContext(player, 60981, -2, 91, TradeModule.getExtension(TradeModule.getExtension(player).getTarget()).getContainer().toArray(), 27, false));
			}
			checkAlert();
		}

		@Override
		public void refresh(Container c) {
			PacketRepository.send(ContainerPacket.class, new ContainerContext(player, -1, 64209, 93, player.getInventory(), false));
		}

	}
}