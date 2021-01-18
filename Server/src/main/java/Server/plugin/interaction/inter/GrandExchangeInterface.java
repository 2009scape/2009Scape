package plugin.interaction.inter;

import core.cache.def.impl.CS2Mapping;
import core.cache.def.impl.ItemDefinition;
import core.game.component.Component;
import core.game.component.ComponentDefinition;
import core.game.component.ComponentPlugin;
import plugin.ge.*;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.RunScript;
import core.game.node.entity.player.link.audio.Audio;
import core.game.node.item.Item;
import core.game.system.task.Pulse;
import core.game.world.GameWorld;
import core.net.packet.PacketRepository;
import core.net.packet.context.ContainerContext;
import core.net.packet.out.ContainerPacket;
import core.plugin.InitializablePlugin;
import core.plugin.Plugin;
import plugin.interaction.npc.BogrogPouchSwapper;

/**
 * Handles the Grand Exchange interface options.
 * @author Emperor
 * @version 1.0
 */
@InitializablePlugin
public class GrandExchangeInterface extends ComponentPlugin {

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ComponentDefinition.put(105, this); // Main interface
		ComponentDefinition.put(107, this); // Selling tab
		ComponentDefinition.put(109, this); // Collection interface
		ComponentDefinition.put(389, this); // Search interface
		ComponentDefinition.put(644, this); // Item sets inventory interface
		ComponentDefinition.put(645, this); // Item sets interface
		ComponentDefinition.put(642, this); // Guide Prices interface.
		return this;
	}

	/**
	 * Increments the opened grand exchange offer amount.
	 * @param player The player.
	 * @param amount The amount to increment.
	 */
	@SuppressWarnings("deprecation")
	private void setOfferAmount(Player player, GrandExchangeOffer offer, int amount) {
		if (offer == null || amount < 0 || offer.getOfferState() != OfferState.PENDING) {
			return;
		}
		offer.setAmount(amount);
		player.getConfigManager().send(1110, offer.getAmount());
	}

	/**
	 * Increments the opened grand exchange offer amount.
	 * @param player The player.
	 */
	@SuppressWarnings("deprecation")
	private void setOfferValue(Player player, GrandExchangeOffer offer, int value) {
		if (offer == null || value < 1 || offer.getOfferState() != OfferState.PENDING) {
			return;
		}
		if (value == GrandExchangeDatabase.getDatabase().get(offer.getItemID()).getValue()) {
			player.getAudioManager().send(new Audio(4043, 1, 1));
		} else if (value > offer.getOfferedValue()) {
			player.getAudioManager().send(new Audio(4041, 1, 1));
		} else {
			player.getAudioManager().send(new Audio(4045, 1, 1));
		}
		offer.setOfferedValue(value);
		player.getConfigManager().send(1111, offer.getOfferedValue());
	}

	@Override
	public boolean handle(final Player player, final Component component, final int opcode, final int button, final int slot, final int itemId) {
		GameWorld.getPulser().submit(new Pulse(1, player) {
			@Override
			public boolean pulse() {
				switch (component.getId()) {
				case 644:
				case 645:
					handleItemSet(player, component, opcode, button, slot, itemId);
					return true;
				case 389:
					handleSearchInterface(player, opcode, button, slot, itemId);
					return true;
				case 107:
					handleSellingTab(player, opcode, button, slot, itemId);
					return true;
				case 109:
					handleCollectionBox(player, opcode, button, slot, itemId);
					return true;
				case 642:
					handleGuidePrice(player, opcode, button, slot, itemId);
					return true;
				}
				handleMainInterface(player, opcode, button, slot, itemId);
				return true;
			}
		});
		return true;
	}

	/**
	 * Handles the search interface options.
	 * @param player The player.
	 * @param opcode The packet opcode.
	 * @param button The button id.
	 * @param slot The slot.
	 * @param itemId The item id.
	 * @return {@code true} if the option got handled.
	 */
	public boolean handleSearchInterface(final Player player, int opcode, int button, int slot, int itemId) {
		switch (button) {
		case 10:
			player.getInterfaceManager().closeChatbox();
			return true;
		}
		return false;
	}

	/**
	 * Handles the selling tab interface options.
	 * @param player The player.
	 * @param opcode The packet opcode.
	 * @param button The button id.
	 * @param slot The slot.
	 * @param itemId The item id.
	 * @return {@code true} if the option got handled.
	 */
	public boolean handleSellingTab(final Player player, int opcode, int button, int slot, int itemId) {
		if (button != 18 || slot < 0 || slot > 27) {
			return false;
		}
		Item item = player.getInventory().get(slot);
		if (item == null) {
			return false;
		}
		switch (opcode) {
		case 196:
			player.getPacketDispatch().sendMessage(item.getDefinition().getExamine());
			return true;
		case 155:
			player.getPlayerGrandExchange().constructSale(item);
			return true;
		}
		return false;
	}

	/**
	 * Handles the selling tab interface options.
	 * @param player The player.
	 * @param opcode The packet opcode.
	 * @param button The button id.
	 * @param slot The slot.
	 * @param itemId The item id.
	 * @return {@code true} if the option got handled.
	 */
	public boolean handleCollectionBox(final Player player, int opcode, int button, int slot, int itemId) {
		int index = -1;
		switch (button) {
		case 18:
		case 23:
		case 28:
			index = (button - 18) >> 2;
			break;
		case 36:
		case 44:
		case 52:
			index = 3 + ((button - 36) >> 3);
			break;
		}
		GrandExchangeOffer offer;
		if (index > -1 && (offer = player.getPlayerGrandExchange().getOffers()[index]) != null) {
			player.getPlayerGrandExchange().withdraw(offer, slot >> 1);
		}
		return true;
	}

	/**
	 * Handles the item set.
	 * @param player The player.
	 * @param opcode The opcode.
	 * @param button The button.
	 * @param slot The slot.
	 * @param itemId The item id.
	 */
	private void handleItemSet(Player player, Component component, int opcode, int button, int slot, int itemId) {
		if (button != 16 && button != 0) {
			return;
		}
		boolean inventory = component.getId() == 644;
		if (slot < 0 || slot >= (inventory ? 28 : GEItemSet.values().length)) {
			return;
		}
		GEItemSet set = GEItemSet.values()[slot];
		Item item = inventory ? player.getInventory().get(slot) : new Item(set.getItemId());
		if (item == null) {
			return;
		}
		if (opcode != 127 && inventory && ((set = GEItemSet.forId(item.getId())) == null) && !BogrogPouchSwapper.handle(player,opcode,slot,itemId)) {
			player.getPacketDispatch().sendMessage("This isn't a set item.");
			return;
		}
		switch (opcode) {
		case 124:
			player.getPacketDispatch().sendMessage(item.getDefinition().getExamine());
			break;
		case 196:
			if (inventory) {
				if (player.getInventory().freeSlots() < set.getComponents().length - 1) {
					player.getPacketDispatch().sendMessage("You don't have enough inventory space for the component parts.");
					return;
				}
				if (!player.getInventory().remove(item, false)) {
					return;
				}
				for (int id : set.getComponents()) {
					player.getInventory().add(new Item(id, 1));
				}
				player.getInventory().refresh();
				player.getPacketDispatch().sendMessage("You successfully traded your set for its component items!");
			} else {
				if (!player.getInventory().containItems(set.getComponents())) {
					player.getPacketDispatch().sendMessage("You don't have the parts that make up this set.");
					break;
				}
				for (int id : set.getComponents()) {
					player.getInventory().remove(new Item(id, 1), false);
				}
				player.getInventory().add(item);
				player.getInventory().refresh();
				player.getPacketDispatch().sendMessage("You successfully traded your item components for a set!");
			}
			player.getAudioManager().send(new Audio(4044, 1, 1));
			PacketRepository.send(ContainerPacket.class, new ContainerContext(player, -1, -2, player.getAttribute("container-key", 93), player.getInventory(), false));
			break;
		case 155:
			player.getPacketDispatch().sendMessage((String) CS2Mapping.forId(1089).getMap().get(set.getItemId()));
			break;
		}
	}

	/**
	 * Handles the main interface options.
	 * @param player The player.
	 * @param opcode The packet opcode.
	 * @param button The button id.
	 * @param slot The slot.
	 * @param itemId The item id.
	 * @return {@code true} if the option got handled.
	 */
	public boolean handleMainInterface(final Player player, int opcode, int button, int slot, int itemId) {
		final GrandExchangeOffer offer = player.getPlayerGrandExchange().getTemporaryOffer();
		final GrandExchangeOffer opened = player.getPlayerGrandExchange().getOpenedOffer();
		int amount = offer == null ? 0 : offer.getAmount();
		switch (button) {
		case 209:
		case 211:
			if (opened == null) {
				return false;
			}
			player.getPlayerGrandExchange().withdraw(opened, (button - 209) >> 1);
			return true;
		case 190:
			player.getPlayerGrandExchange().confirmOffer();
			return true;
		case 194:
			player.getPlayerGrandExchange().openSearch();
			return true;
		case 203:
			if (opened == null) {
				return false;
			}
			player.getPlayerGrandExchange().abort(opened.getIndex());
			return true;
		case 18:
		case 34:
		case 50:
		case 69:
		case 88:
		case 107:
			if (opcode == 205) {
				player.getPlayerGrandExchange().abort((button - 18) >> 4);
				return true;
			}
			player.getPlayerGrandExchange().view((button - 18) >> 4);
			return true;
		case 30:
		case 46:
		case 62:
		case 81:
		case 100:
		case 119:
			player.getPlayerGrandExchange().openBuy((button - 30) >> 4);
			return true;
		case 31:
		case 47:
		case 63:
		case 82:
		case 101:
		case 120:
			player.getPlayerGrandExchange().openSell((button - 31) >> 4);
			return true;
		case 157: // -1
			setOfferAmount(player, offer, amount - 1);
			return true;
		case 159: // +1
			setOfferAmount(player, offer, amount + 1);
			return true;
		case 162: // 1
			setOfferAmount(player, offer, offer != null && offer.getSell() ? 1 : amount + 1);
			return true;
		case 164: // 10
			setOfferAmount(player, offer, offer != null && offer.getSell() ? 10 : amount + 10);
			return true;
		case 166: // 100
			setOfferAmount(player, offer, offer != null && offer.getSell() ? 100 : amount + 100);
			return true;
		case 168: // 1000 / sell all
			if (offer != null && offer.getSell()) {
				setOfferAmount(player, offer, GrandExchange.getInventoryAmount(player, offer.getItemID()));
				return true;
			}
			setOfferAmount(player, offer, amount + 1000);
			return true;
		case 170: // value x
			player.setAttribute("runscript", new RunScript() {
				@Override
				public boolean handle() {
					if (player.getInterfaceManager().getChatbox().getId() == 389) {
						player.getPlayerGrandExchange().openSearch();
					}
					setOfferAmount(player, offer, (int) value);
					return true;
				}
			});
			player.getDialogueInterpreter().sendInput(false, "Enter the amount.");
			return false;
		case 180:
			if (offer != null) {
				setOfferValue(player, offer, GrandExchangeDatabase.getDatabase().get(offer.getItemID()).getValue());
				return true;
			}
			return false;
		case 177: // mid - 5% value
		case 183: // mid + 5% value
			if (offer != null) {
				setOfferValue(player, offer, (int) (GrandExchangeDatabase.getDatabase().get(offer.getItemID()).getValue() * (button == 177 ? 0.95 : 1.05)));
				return true;
			}
			return false;
		case 171: // Decrease value by 1
		case 173: // Increase value with 1
			if (offer != null) {
				setOfferValue(player, offer, offer.getOfferedValue() + (button == 171 ? -1 : 1));
				return true;
			}
			return false;
		case 185: // Set value x
			if (offer == null) {
				player.getPacketDispatch().sendMessage("Please select an offer first.");
				return true;
			}
			player.setAttribute("runscript", new RunScript() {
				@Override
				public boolean handle() {
					if (player.getInterfaceManager().getChatbox().getId() == 389) {
						player.getPlayerGrandExchange().openSearch();
					}
					setOfferValue(player, offer, (int) value);
					return true;
				}
			});
			player.getDialogueInterpreter().sendInput(false, "Enter the amount.");
			return false;
		case 195:
			player.getInterfaceManager().close();
			return true;
		case 127:
			player.getPlayerGrandExchange().setTemporaryOffer(new GrandExchangeOffer());
			player.getInterfaceManager().closeSingleTab();
			player.getInterfaceManager().closeChatbox();
			return true;
		}
		return false;
	}

	/***
	 * Method used to handle the guide price opcode.
	 * @param player the player.
	 * @param opcode the opcode.
	 * @param buttonId the buttonId.
	 * @param slot the slot.
	 * @param itemId the itemId.
	 */
	private void handleGuidePrice(final Player player, final int opcode, final int buttonId, final int slot, final int itemId) {
		switch (opcode) {
		case 155:
			GEGuidePrice.GuideType type = player.getAttribute("guide-price", null);
			if (type == null) {
				return;
			}
			int subtract = 0;
			if (buttonId >= 15 && buttonId <= 23) {
				subtract = 15;
			}
			if (buttonId >= 43 && buttonId <= 57) {
				subtract = 43;
			}
			if (buttonId >= 89 && buttonId <= 103) {
				subtract = 89;
			}
			if (buttonId >= 135 && buttonId <= 144) {
				subtract = 135;
			}
			if (buttonId >= 167 && buttonId <= 182) {
				subtract = 167;
			}
			player.getPacketDispatch().sendMessage(ItemDefinition.forId(type.getItems()[buttonId - subtract].getItem()).getExamine());
			break;
		}
	}
}
