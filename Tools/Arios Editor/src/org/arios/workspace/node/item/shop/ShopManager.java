package org.arios.workspace.node.item.shop;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import org.arios.cache.ServerStore;
import org.arios.cache.misc.ByteBufferUtils;
import org.arios.workspace.WorkSpace;
import org.arios.workspace.editor.EditorType;
import org.arios.workspace.node.item.ItemWrapper;

/**
 * Manages the shops.
 * @author Vexia
 *
 */
public class ShopManager {

	/**
	 * The list of shops.
	 */
	private static final List<Shop> SHOPS = new ArrayList<>();

	/**
	 * Constructs a new {@Code ShopManager} {@Code Object}
	 */
	public ShopManager() {
		/**
		 * empty.
		 */
	}

	/**
	 * Parses the shops.
	 */
	public static void parse() {
		ByteBuffer buf = ServerStore.getArchive("shop_data");
		@SuppressWarnings("unused")
		int uid;
		Shop shop;
		String title;
		boolean general;
		ItemWrapper[] stock = null;
		int size;
		int[] npcs;
		boolean highAlch;
		int currency;
		while ((uid = buf.getShort()) != 0) {
			title = ByteBufferUtils.getString(buf);
			general = buf.get() == 1;
			boolean items  = buf.get() == 1;
			if (items) {
				size = buf.get();
				stock = new ItemWrapper[size];
				for (int i = 0; i < size; i++) {
					stock[i] = new ItemWrapper(buf.getShort(), buf.getInt());
				}
			}
			size = buf.get();
			npcs = new int[size];
			for (int i = 0; i < size; i++) {
				npcs[i] = buf.getShort();
			}
			highAlch = buf.get() == 1;
			currency = buf.getShort();
			if (general && !items) {
				shop = new Shop(title, general, currency, npcs, highAlch);
			} else {
				shop = new Shop(title, stock, general, currency, npcs, highAlch);
			}
			SHOPS.add(shop);
			title = null;
			stock = null;
			npcs = null;
			EditorType.SHOP.getTab().getNodes().put(shop.getName().hashCode(), shop);
		}
	}

	/**
	 * Saves the buffer.
	 */
	public static void save() {
		ByteBuffer buffer = ByteBuffer.allocate(6666666);
		for (int i = 0; i < SHOPS.size(); i++) {
			Shop shop = SHOPS.get(i);
			String title = shop.getTitle();
			buffer.putShort((short) (i + 1));//uid
			ByteBufferUtils.putString(title, buffer);//title
			boolean general = shop.isGeneral();
			buffer.put((byte) (general ? 1 : 0));
			boolean items = !shop.getItems().equals(Shop.GENERAL_STORE);
			buffer.put((byte) (items ? 1 : 0));
			if (items) {
				buffer.put((byte) shop.getItems().length);//length of array
				for (ItemWrapper item : shop.getItems()) {
					buffer.putShort((short) item.getId());//itemId
					buffer.putInt(item.getAmount());//amount
				}
			}
			if (shop.getNpcs() == null || shop.getNpcs().length == 0) {
			//	System.err.println("No npcs for shop " + shop.getTitle());
			}
			if (shop.getNpcs() == null) {
				shop.setNpcs(new int [] {});
			}
			buffer.put((byte) shop.getNpcs().length);//shop length
			for (int npc : shop.getNpcs()) {
				buffer.putShort((short) npc);
			}
			buffer.put((byte) (shop.isHighAlch() ? 1 : 0));//if high alch.
			buffer.putShort((short) shop.getCurrency());
		}
		buffer.putShort((short) 0);
		buffer.flip();
		ServerStore.setArchive("shop_data", buffer, false);
		ServerStore.createStaticStore(WorkSpace.getWorkSpace().getSettings().getStorePath());
	}

	/**
	 * Gets the shops.
	 * @return the shops.
	 */
	public static List<Shop> getShops() {
		return SHOPS;
	}

}
