package core.game.content.global.action;

import core.game.container.impl.EquipmentContainer;
import core.game.content.EquipSoundsKt;
import core.game.content.ItemNames;
import core.game.interaction.OptionHandler;
import core.game.node.Node;
import core.game.node.entity.lock.Lock;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.audio.Audio;
import core.game.node.entity.player.link.diary.DiaryType;
import core.game.node.item.Item;
import core.game.world.GameWorld;
import core.game.world.map.Location;
import core.game.world.map.zone.ZoneBorders;
import core.plugin.Plugin;

/**
 * Represents the equipment equipping handler plugin.
 * @author Ceikry
 * @author Woah
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
	private static final Audio DEFAULT = new Audio(2242, 1, 0);

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

			if (item.getId() == ItemNames.BLACK_CHAINBODY
					&& player.getAttribute("diary:falador:black-chain-bought", false)
					&& new ZoneBorders(2969, 3310, 2975, 3314, 0).insideBorder(player)) {
				player.getAchievementDiaryManager().finishTask(player,DiaryType.FALADOR, 0, 2);
			}


			player.getDialogueInterpreter().close();
			player.getAudioManager().send(EquipSoundsKt.gibAudio(item.getId()), 1);
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