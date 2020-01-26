package org.arios.workspace.node.item.shop;

import java.nio.ByteBuffer;
import java.util.Arrays;

import org.arios.cache.def.Definition;
import org.arios.workspace.node.Configuration;
import org.arios.workspace.node.Node;
import org.arios.workspace.node.item.ItemWrapper;
import org.arios.workspace.node.item.shop.Shop.ShopDefinition;

/**
 * A shop.
 * @author Vexia
 *
 */
public class Shop extends Node<ShopDefinition> {
	
	/**
	 * Represents the general store items.
	 */
	public final static ItemWrapper[] GENERAL_STORE = {new ItemWrapper(1931, 30), new ItemWrapper(1935, 30), new ItemWrapper(1735, 10), new ItemWrapper(1925, 10), new ItemWrapper(1923, 10), new ItemWrapper(1887, 10), new ItemWrapper(590, 10), new ItemWrapper(1755, 10), new ItemWrapper(2347, 10), new ItemWrapper(550, 10), new ItemWrapper(9003, 10)};

	/**
	 * Represents the title of the shop.
	 */
	private String title;

	/**
	 * Represents the ItemWrappers in the store.
	 */
	private ItemWrapper[] items;

	/**
	 * Represents if it's a general store.
	 */
	private boolean general;

	/**
	 * Represents the currency the shop allows.
	 */
	private int currency;

	/**
	 * Represents the owners of the shop.
	 */
	private int[] npcs;

	/**
	 * If the shop buys for high alch.
	 */
	private boolean highAlch;

	/**
	 * Constructs a new {@Code Shop} {@Code Object}
	 * @param title the title.
	 * @param items the items.
	 * @param general the general.
	 * @param currency the currency.
	 * @param npcs the npcs.
	 * @param highAlch the high alch.
	 */
	public Shop(String title, ItemWrapper[] items, boolean general, int currency, int[] npcs, boolean highAlch) {
		super(title.hashCode());
		this.title = title;
		this.items = items;
		this.general = general;
		this.currency = currency;
		this.npcs = npcs;
		this.highAlch = highAlch;
		setDefaultConfigs();
		setConfigs();
	}

	/**
	 * Constructs a new {@Code Shop} {@Code Object}
	 * @param title the title.
	 * @param items the items.
	 * @param general the general.
	 * @param currency the currency.
	 * @param npcs the npcs.
	 * @param highAlch the high alch.
	 */
	public Shop(String title2, boolean general2, int currency2, int[] npcs, boolean highAlch2) {
		this(title2, GENERAL_STORE, general2, currency2, npcs, highAlch2);
	}
	
	/**
	 * Sets the configs.
	 */
	public void setConfigs() {
		setConfig("title", title);
		setConfig("items", items);
		setConfig("general", general);
		setConfig("currency", currency);
		setConfig("npcs", npcs);
		setConfig("highAlch", highAlch);
	}
	
	/**
	 * Sets from the configs.
	 */
	public void setFromConfigs() {
		title = (String) getConfigValue("title");
		items = (ItemWrapper[]) getConfigValue("items");
		general = (boolean) getConfigValue("general");
		currency = (int) getConfigValue("currency");
		highAlch = (boolean) getConfigValue("highAlch");
	}

	/**
	 * Gets the title.
	 * @return the title.
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Gets the items.
	 * @return the items.
	 */
	public ItemWrapper[] getItems() {
		return items;
	}

	/**
	 * Gets the general.
	 * @return the general.
	 */
	public boolean isGeneral() {
		return general;
	}

	/**
	 * Gets the currency.
	 * @return the currency.
	 */
	public int getCurrency() {
		return currency;
	}

	/**
	 * Gets the npcs.
	 * @return the npcs.
	 */
	public int[] getNpcs() {
		return npcs;
	}

	/**
	 * Gets the highAlch.
	 * @return the highAlch.
	 */
	public boolean isHighAlch() {
		return highAlch;
	}

	/**
	 * Sets the title.
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Sets the items.
	 * @param items the items to set
	 */
	public void setItems(ItemWrapper[] items) {
		this.items = items;
	}

	/**
	 * Sets the general.
	 * @param general the general to set
	 */
	public void setGeneral(boolean general) {
		this.general = general;
	}

	/**
	 * Sets the currency.
	 * @param currency the currency to set
	 */
	public void setCurrency(int currency) {
		this.currency = currency;
	}

	/**
	 * Sets the npcs.
	 * @param npcs the npcs to set
	 */
	public void setNpcs(int[] npcs) {
		this.npcs = npcs;
	}

	/**
	 * Sets the highAlch.
	 * @param highAlch the highAlch to set
	 */
	public void setHighAlch(boolean highAlch) {
		this.highAlch = highAlch;
	}
		
	@Override
	public String toString() {
		return getTitle() + ", " + Arrays.toString(npcs) + "";
	}
	
	@Override
	public String getName() {
		return getTitle();
	}
	
	@Override
	public void save(ByteBuffer buffer) {
		
	}
	
	@Override
	public void setDefaultConfigs() {
		getConfigurations().put("title", new Configuration<String>(1, "Shop"));
		getConfigurations().put("items", new Configuration<ItemWrapper[]>(2, new ItemWrapper[] {}));
		getConfigurations().put("general", new Configuration<Boolean>(3, false));
		getConfigurations().put("currency", new Configuration<Integer>(4, 995));
		getConfigurations().put("npcs", new Configuration<Integer[]>(6, new Integer[] {}));
		getConfigurations().put("highAlch", new Configuration<Boolean>(7, false));
	}

	@Override
	public Definition forId(int id) {
		return new ShopDefinition(this);
	}
	
	/**
	 * The shop definitions.
	 * @author Vexia
	 *
	 */
	public static class ShopDefinition extends Definition {

		private Shop shop;
		
		public ShopDefinition(Shop shop) {
			this.setShop(shop);
		}

		/**
		 * Gets the shop.
		 * @return the shop.
		 */
		public Shop getShop() {
			return shop;
		}

		/**
		 * Sets the shop.
		 * @param shop the shop to set
		 */
		public void setShop(Shop shop) {
			this.shop = shop;
		}

		
	}
}
