package org.crandor.game.content.global.action;

import org.crandor.cache.def.impl.ItemDefinition;
import org.crandor.game.container.impl.EquipmentContainer;
import org.crandor.game.content.global.tutorial.TutorialSession;
import org.crandor.game.content.global.tutorial.TutorialStage;
import org.crandor.game.interaction.OptionHandler;
import org.crandor.game.node.Node;
import org.crandor.game.node.entity.lock.Lock;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.entity.player.link.audio.Audio;
import org.crandor.game.node.item.Item;
import org.crandor.game.world.GameWorld;
import org.crandor.plugin.Plugin;

/**
 * Represents the equipment equipping handler plugin.
 * @author Emperor
 * @author Vexia
 * 
 */
public class EquipHandler extends OptionHandler {

	/**
	 * Represents the singleton.
	 */
	public static final EquipHandler SINGLETON = new EquipHandler();

	/**
	 * The sound to send.
	 */
	private static final Audio SOUND = new Audio(2242, 1, 0);

	/**
	 * Constructs a new {@code EquipHandler} {@code Object}.
	 */
	public EquipHandler() {
		super();
	}

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		return this;
	}

	@Override
	public boolean handle(final Player player, Node node, String option) {
		final Item item = ((Item) node);
		if (item == null || player.getInventory().get(item.getSlot()) != item) {
			return true;
		}
		if (TutorialSession.getExtension(player).getStage() < 46) {
			player.getPacketDispatch().sendMessage("You'll be told how to equip items later.");
			return true;
		}
		if (TutorialSession.getExtension(player).getStage() == 46 && item.getId() == 1205) {
			TutorialStage.load(player, 47, false);
		}
		Plugin<Object> plugin = item.getDefinition().getConfiguration("equipment", null);
		if (plugin != null) {
			Boolean bool = (Boolean) plugin.fireEvent("equip", player, item);
			if (bool != null && !bool) {
				return true;
			}
		}
		Lock lock = player.getLocks().getEquipmentLock();
		if (lock != null && lock.isLocked()) {
			if (lock.getMessage() != null) {
				player.getPacketDispatch().sendMessage(lock.getMessage());
			}
			return true;
		}
		player.setAttribute("equipLock:" + item.getId(), GameWorld.getTicks() + 2);
		if (player.getEquipment().add(item, item.getSlot(), true, true)) {
			//check if a brawling glove is being equipped and register it
			if(item.getId() >= 13845 && item.getId() <= 13857) {
				player.debug("Registering gloves... ID: " + item.getId());
				player.getBrawlingGlovesManager().registerGlove(item.getId());
			}
			player.getDialogueInterpreter().close();
			player.getAudioManager().send(SOUND);
		}
		ItemDefinition.statsUpdate(player);
		if (TutorialSession.getExtension(player).getStage() == 48) {
			if (player.getEquipment().containItems(1171, 1277)) {
				TutorialStage.load(player, 49, false);
			}
		}
		return true;
	}

	/**
	 * Unequips an item.
	 * @param player the player.
	 * @param slot the slot.
	 * @param itemId the item id.
	 */
	public static void unequip(Player player, int slot, int itemId) {
		if (slot < 0 || slot > 13) {
			return;
		}
		Item item = player.getEquipment().get(slot);
		if (item == null) {
			return;
		}
		Lock lock = player.getLocks().getEquipmentLock();
		if (lock != null && lock.isLocked()) {
			if (lock.getMessage() != null) {
				player.getPacketDispatch().sendMessage(lock.getMessage());
			}
			return;
		}
		if (slot == EquipmentContainer.SLOT_WEAPON) {
			player.getPacketDispatch().sendString("", 92, 0);
		}
		int maximumAdd = player.getInventory().getMaximumAdd(item);
		if (maximumAdd < item.getAmount()) {
			player.getPacketDispatch().sendMessage("Not enough free space in your inventory.");
			return;
		}
		Plugin<Object> plugin = item.getDefinition().getConfiguration("equipment", null);
		if (plugin != null) {
			if (!(boolean) plugin.fireEvent("unequip", player, item)) {
				return;
			}
		}
		if (player.getEquipment().remove(item)) {
			player.getAudioManager().send(new Audio(2238, 10, 1));
			player.getDialogueInterpreter().close();
			player.getInventory().add(item);
		}
	}
}