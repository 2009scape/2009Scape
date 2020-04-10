package org.arios.workspace.editor;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.MatteBorder;

import org.arios.workspace.node.Configuration;
import org.arios.workspace.node.Node;
import org.arios.workspace.node.item.ItemWrapper;
import org.arios.workspace.node.item.shop.Shop;

/**
 * Represents a table used to represent a nodes configs.
 * @author Vexia
 *
 */
public final class NodeTable extends JScrollPane {

	/**
	 * The serail UID.
	 */
	private static final long serialVersionUID = -6345104732675498099L;
	
	/**
	 * The column names.
	 */
	private static final String[] COLUMN_NAMES = new String[] {"Config", "Value"};
	
	/**
	 * The node we're editing.
	 */
	private final Node<?> node;
	
	/**
	 * The table for the nodes configs.
	 */
	private final JTable table;
	
	/**
	 * Constructs a new {@Code NodeTable} {@Code Object}
	 * @param node the node.
	 */
	public NodeTable(Node<?> node) {
		super();
		setSize(285, 353);
		this.table = createTable(node);
		this.node = node;
		setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		setBounds(16, 47, node instanceof Shop ? 800 : 285, 341);
		setViewportView(table);
		setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	}
	
	/**
	 * Saves the table.
	 */
	public void save() {
		for (int i = 0; i < table.getRowCount(); i++) {
			String key = (String) table.getValueAt(i, 0);
			Object obj = table.getValueAt(i, 1);
			if (!(obj instanceof String)) {
				node.setConfig(key, obj);
				continue;
			}
			String value = (String) table.getValueAt(i, 1);
			Configuration<?> config = node.getConfigurations().get(key);
			if (config.getType() == Byte.class) {
				node.setConfig(key, Byte.valueOf(value));
			} else if (config.getType() == Short.class) {
				node.setConfig(key, Short.valueOf(value));
			} else if (config.getType() == Integer.class) {
				node.setConfig(key, Integer.valueOf(value));
			} else if (config.getType() == Double.class) {
				node.setConfig(key, Double.valueOf(value));
			} else if (config.getType() == Boolean.class) {
				node.setConfig(key, Boolean.valueOf(value));
			} else if (config.getType() == String.class) {
				node.setConfig(key, value);
			} else if (config.getType() == ItemWrapper[].class) {
				String[] tokens = value.replace("[", "").replace("]", "").trim().split(",");
				boolean amt = false;
				int id = 0;
				int amount = 0;
				List<ItemWrapper> wraps = new ArrayList<>();
				for (String tok : tokens) {
					if (amt) {
						amount = Integer.parseInt(tok.replace(")", "").trim());
						wraps.add(new ItemWrapper(id, amount));
					} else {
						id = Integer.parseInt(tok.replace("(", "").trim());
					}
					amt = !amt;
				}
				node.setConfig(key, wraps.toArray(new ItemWrapper[] {}));
			} else if (config.getType() == Map.class || config.getType() == HashMap.class) {
				@SuppressWarnings("unchecked")
				Map<Integer, Integer> req = (Map<Integer, Integer>) config.getValue();
				req.clear();
				String[] tokens = value.replace("{", "").replace("}", "").trim().split(",");
				for (String token : tokens) {
					token = token.trim();
					req.put(Integer.parseInt(token.split("=")[0]), Integer.parseInt(token.split("=")[1]));
				}
				node.setConfig(key, req);
			} else if (config.getType() == Short[].class) {
				String[] tokens = value.replace("[", "").replace("]", "").trim().split(",");
				if (tokens.length == 0) {
					System.err.println("Tokens=" + Arrays.toString(tokens));
					return;
				}
				for (int i1 = 0; i1 < tokens.length; i1++) {
					String token = tokens[i1].trim();
					if (token == null || token.length() == 0) {
						continue;
					}
					if (token.equals("null")) {
						continue;
					}
					Short[] s = (Short[]) config.getValue();
					if (s == null) {
						continue;
					}
					if (s.length == 0) {
						s = new Short[tokens.length];
					}
					s[i1] = Short.valueOf(token);
					config.setDefaultValue(false);
					node.setConfig(key, s);
				}
			}
			if (node instanceof Shop) {
				Shop s = (Shop) node;
				s.setFromConfigs();
			}
		}
	}
	
	/**
	 * Creates a jtable.
	 * @param node the node.
	 * @return the table.
	 */
	public static JTable createTable(Node<?> node) {
		Object[][] objects = new Object[node.getDefinition().getConfigurations().size()][2];
		int index = 0;
		for (Entry<String, Configuration<?>> config : node.getDefinition().getConfigurations().entrySet() ) {
			objects[index][0] = config.getKey();
			objects[index][1] = config.getValue().getValue();
			if (config.getValue().getValue() instanceof Short[]) {
				objects[index][1] = Arrays.toString((Short[]) config.getValue().getValue());
			} else if (config.getValue().getValue() instanceof Integer[]) {
				objects[index][1] = Arrays.toString((Integer[]) config.getValue().getValue());
			} else if (config.getValue().getValue() instanceof ItemWrapper[]) {
				objects[index][1] = Arrays.toString((ItemWrapper[]) config.getValue().getValue());
			}
			index++;
		}
		return new JTable(objects, COLUMN_NAMES);
	}

	/**
	 * Gets the node.
	 * @return the node.
	 */
	public Node<?> getNode() {
		return node;
	}
	
}
