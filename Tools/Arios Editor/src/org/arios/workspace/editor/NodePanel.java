package org.arios.workspace.editor;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import org.arios.workspace.node.Node;
import org.arios.workspace.node.item.ItemWrapper;
import org.arios.workspace.node.item.shop.Shop;
import org.arios.workspace.node.item.shop.ShopEditor;
import org.arios.workspace.node.item.shop.ShopManager;


/**
 * The panel for a node.
 * @author Vexia
 *
 */
public class NodePanel extends JPanel implements ActionListener {

	/**
	 * The serial UID.
	 */
	private static final long serialVersionUID = 2776394653113735129L;

	/**
	 * The editor tab.
	 */
	private final EditorTab tab;

	/**
	 * The list for this panel.
	 */
	private final NodeList nodeList = new NodeList();

	/**
	 * The id searched.
	 */
	private JTextField idSearch;

	/**
	 * The searched name.
	 */
	private JTextField nameSearch;

	/**
	 * The search name lavel.
	 */
	private JLabel lblSearchName;

	/**
	 * The tabbed pane.
	 */
	private JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);

	/**
	 * Constructs a new {@Code NodeListPanel} {@Code Object}
	 */
	public NodePanel(EditorTab tab) {
		super();
		this.tab = tab;
		setLayout(null);
		JScrollPane nodeScroll = new JScrollPane(nodeList);
		nodeScroll.setBounds(18, 74, 260, 364);
		add(nodeScroll);
		
		nameSearch = new JTextField();
		nameSearch.setColumns(10);
		nameSearch.setBounds(95, 39, 179, 21);
		add(nameSearch);
		nameSearch.addActionListener(this);

		if (!(tab instanceof ShopEditor)) {
			JLabel lblSearchId = new JLabel("Search id:");
			lblSearchId.setBounds(6, 4, 98, 27);
			add(lblSearchId);

			idSearch = new JTextField();
			idSearch.setBounds(95, 6, 179, 21);
			add(idSearch);
			idSearch.setColumns(10);
			idSearch.addActionListener(this);

		} else {
			JButton addShop = new JButton("Add Shop");
			//	public Shop(String title, ItemWrapper[] items, boolean general, int currency, int[] npcs, boolean highAlch) {
			addShop.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JPanel panel = new JPanel(new GridLayout(0, 1));
					panel.add(new JLabel("Enter title:"));
					JTextField idField = new JTextField("");
					panel.add(idField);
					panel.add(new JLabel("Items:"));
					JTextField items = new JTextField("");
					panel.add(items);
					panel.add(new JLabel("General:"));
					JTextField general = new JTextField("false");
					panel.add(general);
					panel.add(new JLabel("Currency:"));
					JTextField currency = new JTextField("995");
					panel.add(currency);
					panel.add(new JLabel("NPCS:"));
					JTextField npcs = new JTextField("");
					panel.add(npcs);
					panel.add(new JLabel("High alch:"));
					JTextField highAlch = new JTextField("false");
					panel.add(highAlch);
					int result = JOptionPane.showConfirmDialog(null, panel, "Drop Creator", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
					if (result == JOptionPane.OK_OPTION) {
						String[] t = npcs.getText().split(",");
						int[] n = new int[t.length];
						for (int i = 0; i < n.length; i++) {
							n[i] = Integer.parseInt(t[i]);
						}
						String[] tokens = items.getText().trim().split(",");
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
						Shop shop = new Shop(idField.getText(), wraps.toArray(new ItemWrapper[] {}), Boolean.parseBoolean(general.getText()), Integer.parseInt(currency.getText()), n, Boolean.parseBoolean(highAlch.getText()));
						ShopManager.getShops().add(shop);
						EditorType.SHOP.getTab().getNodes().put(shop.getTitle().hashCode(), shop);
					}
				}
			});
			addShop.setBounds(38, 4, 200, 27);
			add(addShop);
		}
		lblSearchName = new JLabel("Search name:");
		lblSearchName.setBounds(6, 33, 98, 27);
		add(lblSearchName);

		tabbedPane.setBounds(286, 0, 964, 447);
		add(tabbedPane);
		setSize(1268, 446);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == idSearch || e.getSource() == nameSearch) {
			search(e.getSource() == idSearch? true : false);
			return;
		}
	}

	/**
	 * Searches fora node & lists them.
	 * @param id the id.
	 */
	private void search(boolean id) {
		List<Node<?>> nodes = new ArrayList<>();
		String text = id ? idSearch.getText() : nameSearch.getText();
		if (text == null || text.length() == 0) {
			return;
		}
		nodeList.getDefaultModel().clear();
		if (id) {
			int realId = 0;
			try {
				realId = Integer.parseInt(text);
			} catch (NumberFormatException e) {

			}
			nodes.add(tab.getNodes().get(realId));
		} else {
			for (Node<?> node : tab.getNodes().values()) {
				if (node.getName().toLowerCase().startsWith(text.toLowerCase())) {
					nodes.add(node);
				}
			}
		}
		for (Node<?> node : nodes) {
			nodeList.getDefaultModel().addElement(node);
		}
	}

	/**
	 * Gets the nodeList.
	 * @return the nodeList.
	 */
	public NodeList getNodeList() {
		return nodeList;
	}

	/**
	 * Gets the idSearch.
	 * @return the idSearch.
	 */
	public JTextField getIdSearch() {
		return idSearch;
	}

	/**
	 * Sets the idSearch.
	 * @param idSearch the idSearch to set
	 */
	public void setIdSearch(JTextField idSearch) {
		this.idSearch = idSearch;
	}

	/**
	 * Gets the nameSearch.
	 * @return the nameSearch.
	 */
	public JTextField getNameSearch() {
		return nameSearch;
	}

	/**
	 * Sets the nameSearch.
	 * @param nameSearch the nameSearch to set
	 */
	public void setNameSearch(JTextField nameSearch) {
		this.nameSearch = nameSearch;
	}

	/**
	 * Gets the lblSearchName.
	 * @return the lblSearchName.
	 */
	public JLabel getLblSearchName() {
		return lblSearchName;
	}

	/**
	 * Sets the lblSearchName.
	 * @param lblSearchName the lblSearchName to set
	 */
	public void setLblSearchName(JLabel lblSearchName) {
		this.lblSearchName = lblSearchName;
	}

	/**
	 * Gets the tabbedPane.
	 * @return the tabbedPane.
	 */
	public JTabbedPane getTabbedPane() {
		return tabbedPane;
	}

	/**
	 * Sets the tabbedPane.
	 * @param tabbedPane the tabbedPane to set
	 */
	public void setTabbedPane(JTabbedPane tabbedPane) {
		this.tabbedPane = tabbedPane;
	}

	/**
	 * Gets the tab.
	 * @return the tab.
	 */
	public EditorTab getTab() {
		return tab;
	}
}
