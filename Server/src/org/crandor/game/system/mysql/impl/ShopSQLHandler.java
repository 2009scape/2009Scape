package org.crandor.game.system.mysql.impl;

import org.crandor.ServerConstants;
import org.crandor.game.content.global.shop.Shop;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.item.Item;
import org.crandor.game.system.mysql.SQLEntryHandler;
import org.crandor.game.system.mysql.SQLManager;
import org.crandor.game.system.task.Pulse;
import org.crandor.game.world.GameWorld;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Handles the parsing of shops.
 * @author Vexia
 *
 */
public class ShopSQLHandler extends SQLEntryHandler<Object> {

	/**
	 * The mapped shops.
	 */
	private static final Map<Integer, Shop> SHOPS = new HashMap<>();

	/**
	 * The uid shops.
	 */
	private static final Map<Integer, Shop> UID_SHOPS = new HashMap<>();

	/**
	 * The list of all shops.
	 */
	private static final List<Shop> ALL = new ArrayList<>();

	/**
	 * The shop being parsed.
	 */
	private Shop shop;

	/**
	 * Constructs a new {@Code ShopSQLHandler} {@Code Object}
	 */
	public ShopSQLHandler() {
		super(null, "", "", "");
	}

	@Override
	public void parse() throws SQLException {
		connection = getConnection();
		if (connection == null) {
			SQLManager.close(connection);
			return;
		}
		PreparedStatement statement = connection.prepareStatement("SELECT * FROM "+ (SQLManager.LOCAL ? "server" : ServerConstants.DATABASE_NAMES[0]) + ".shops");
		ResultSet set = statement.executeQuery();
		while (set.next()) {
			parseShop(set);
		}
		SQLManager.close(statement.getConnection());
		GameWorld.Pulser.submit(new Pulse(100) {

			@Override
			public boolean pulse() {
				for (Shop shop : UID_SHOPS.values()) {
					if (shop.isRestock()) {
						shop.restock();
					}
				}
				return false;
			}

		});
	}

	@Override
	public void create() throws SQLException {
		PreparedStatement statement = connection.prepareStatement("");
		statement.executeUpdate();
		SQLManager.close(statement.getConnection());
	}

	@Override
	public void save() throws SQLException {
		PreparedStatement statement = connection.prepareStatement("");
		statement.executeUpdate();
		SQLManager.close(statement.getConnection());
	}

	/**
	 * Parses a shop from a result set.
	 * @param set the result set.
	 * @throws SQLException  the SQL exception if thrown.
	 */
	public void parseShop(ResultSet set) throws SQLException {
		int uid = set.getInt(1);
		String title = set.getString(2);
		boolean general = set.getBoolean(3);
		String stock = set.getString(4);
		String npcData = set.getString(5);
		int currency = set.getInt(6);
		boolean highAlch = set.getBoolean(7);
		if (general && stock.length() == 0) {
			shop = new Shop(title, general, currency, highAlch);
		} else {
			shop = new Shop(title, parseStock(stock), general, currency, highAlch);
		}
		shop.setNpcs(new int[] {});
		int[] npcs = parseIds(npcData);
		if (npcs != null && npcs.length > 0) {
			shop.setNpcs(npcs);
			for (int npc : npcs) {
				SHOPS.put(npc, shop);
			}
		} else {
			UID_SHOPS.put(uid, shop);
		}
		ALL.add(shop);
	}

	/**
	 * Opens a uid shop.
	 * @param player the player.
	 * @param uid the uid.
	 * @return {@code True} if so.
	 */
	public static boolean openUid(Player player, int uid) {
		Shop shop = UID_SHOPS.get(uid);
		if (shop == null) {
			return false;
		}
		shop.open(player);
		return true;
	}

	/**
	 * Parses the stock.
	 * @param stock the stock.
	 * @return the stock array.
	 */
	private Item[] parseStock(String stock) {
		if (stock.length() == 0) {
			return new Item[] {};
		}
		Item[] items = null;
		String[] datas = stock.split("-");
		String[] tokens = null;
		items = new Item[datas.length];
		for (int i = 0; i < datas.length; i++) {
			tokens = datas[i].replace("{", "").replace("}", "").split(",");
			items[i] = new Item(Integer.valueOf(tokens[0].trim()), Integer.valueOf(tokens[1].trim()));
		}
		return items;
	}

	/**
	 * Parses the npc ids.
	 * @param npcs the npcs.
	 * @return the array of ids.
	 */
	private int[] parseIds(String npcs) {
		if (npcs.length() == 0) {
			return new int[] {};
		}
		String[] tokens = npcs.split(",");
		int[] ids = new int[tokens.length];
		for (int i = 0; i < tokens.length; i++) {
			ids[i] = Integer.parseInt(tokens[i]);
		}
		return ids;
	}

	/**
	 * Gets the shops.
	 * @return the shops
	 */
	public static Map<Integer, Shop> getShops() {
		return SHOPS;
	}

	/**
	 * Gets the uidShops.
	 * @return the uidShops
	 */
	public static Map<Integer, Shop> getUidShops() {
		return UID_SHOPS;
	}

	@Override
	public Connection getConnection() {
		return SQLManager.getConnection();
	}

}
