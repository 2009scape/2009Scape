package org.arios.workspace.node.npc;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import org.arios.cache.misc.StringUtils;

/**
 * The panel used for editing npc drops.
 * @author Vexia
 *
 */
public class NPCDropPanel extends JPanel {

	/**
	 * The serial UID.
	 */
	private static final long serialVersionUID = 7628103873857408009L;

	/**
	 * The npc drop tabs.
	 */
	private final JTabbedPane tabs = new JTabbedPane();

	/**
	 * The npc displaying.
	 */
	private final NPC npc;

	/**
	 * Constructs a new {@Code NPCDropPanel} {@Code Object}
	 * @param npc the npc.
	 */
	public NPCDropPanel(NPC npc) {
		super();
		this.npc = npc;
		setLayout(null);
		tabs.setBounds(0, 0, 634, 383);
		add(tabs);
		for (TableType type : TableType.values()) {
			addTab(type);
		}
	}

	/**
	 * Saves the panel.
	 */
	public void save() {
		for (int i = 0; i < 3; i++) {
			Component comp = tabs.getComponentAt(i);
			DropTable table = (DropTable) comp;
			table.save();
		}
	}

	/**
	 * Adds a drop table tab.
	 * @param type the type.
	 */
	public void addTab(TableType type) {
		tabs.addTab(StringUtils.formatDisplayName(type.name().toLowerCase()), new DropTable(npc, type));
	}

	/**
	 * Gets the npc.
	 * @return the npc.
	 */
	public NPC getNpc() {
		return npc;
	}

	/**
	 * A drop table.
	 * @author Vexia
	 *
	 */
	public class DropTable extends JPanel {

		/**
		 * The serial UID.
		 */
		private static final long serialVersionUID = -2901744614661688478L;

		/**
		 * The table type.
		 */
		private TableType type;

		/**
		 * The npc.
		 */
		private final NPC npc;

		/**
		 * The table pane.
		 */
		private JScrollPane tablePane;

		/**
		 * The table.
		 */
		private JTable table;

		/**
		 * The add drop jbutton.
		 */
		private JButton add = new JButton("Add");

		/**
		 * Constructs a new {@Code DropTable} {@Code Object}
		 * @param npc the npc.
		 * @param type the type.
		 */
		public DropTable(NPC npc, TableType type) {
			super();
			setLayout(null);
			this.npc = npc;
			this.type = type;
			createTable();
			add.setBounds(510, 10, 70, 20);
			add.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					showAddDrop();
				}
			});
			add(add);
		}
		

		/**
		 * Shows the add drop.
		 */
		public void showAddDrop() {
			JPanel panel = new JPanel(new GridLayout(0, 1));
			panel.add(new JLabel("Enter item id:"));
			JTextField idField = new JTextField("");
			panel.add(idField);
			panel.add(new JTextField("Min amount:"));
			JTextField minAmount = new JTextField("1");
			panel.add(minAmount);
			panel.add(new JTextField("Max amount:"));
			JTextField maxAmount = new JTextField("1");
			panel.add(maxAmount);
			panel.add(new JTextField("Chance rate:"));
			JTextField chanceRate = new JTextField("0.0");
			panel.add(chanceRate);
			panel.add(new JTextField("Drop frequency:"));
			JComboBox<String> combo = new JComboBox<String>(new String[] {"ALWAYS", "COMMON", "UNCOMMON", "RARE", "VERY_RARE"});
			combo.setSelectedIndex(1);
			panel.add(combo);
			panel.add(new JLabel("Set rate:"));
			JTextField rate = new JTextField("Enter rate:");
			panel.add(rate);
			int result = JOptionPane.showConfirmDialog(null, panel, "Drop Creator", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
			if (result == JOptionPane.OK_OPTION) {
				int id = Integer.parseInt(idField.getText());
				int min = Integer.parseInt(minAmount.getText());
				int max = Integer.parseInt(maxAmount.getText());
				double chance = Double.parseDouble(chanceRate.getText());
				DropFrequency freq = DropFrequency.forName((String) combo.getSelectedItem());
				int setRate = rate.getText().equals("Enter rate:") ? -1 : Integer.parseInt(rate.getText());
				NPCDrop drop = new NPCDrop(id, min, max, chance, freq, setRate);
				addDrop(drop);
				reset();
			}
		}

		/**
		 * Adds drops.
		 * @param drop the drop.
		 */
		private void addDrop(NPCDrop drop) {
			npc.addDrop(drop, type);
		}

		/**
		 * Saves the tab.
		 */
		public void save() {
			for (int i = 0; i < table.getColumnCount(); i++) {
				for (int k = 0; k < table.getRowCount(); k++) {
					Object val = table.getValueAt(k, i);
					NPCDrop drop = npc.getDrobTable(type)[k];
					saveDrop(drop, val, i);
				}
			}
		}

		/**
		 * Saves the drop.
		 * @param drop the drop.
		 * @param val the value.
		 * @param column the column.
		 */
		public void saveDrop(NPCDrop drop, Object val, int column) {
			String v = (String) val;
			switch (column) {
			case 0:
				drop.setItemId(Integer.parseInt(v));
				break;
			case 1:
				drop.setMinAmount(Integer.parseInt(v));
				break;
			case 2:
				drop.setMaxAmount(Integer.parseInt(v));
				break;
			case 3:
				drop.setChance(Double.parseDouble(v));
				break;
			case 4:
				drop.setFrequency(DropFrequency.forName(v));
				break;
			case 5:
				drop.setSetRate(Integer.parseInt(v));
				break;
			}
		}

		/**
		 * Creates a table.
		 */
		@SuppressWarnings("serial")
		public void createTable() {
			table = new JTable(asObjects(npc.getDrobTable(type)), new String[] {"Item", "Min", "Max", "Frequency", "Rate", "Set rate"}) {
				@Override
				public Component prepareRenderer(TableCellRenderer renderer,int row, int col) {
					Component comp = super.prepareRenderer(renderer, row, col);
					JComponent jcomp = (JComponent)comp;
					if (comp == jcomp) {
						if (col == 0) {
							NPCDrop drop = npc.getDrobTable(type)[row];
							jcomp.setToolTipText("" + drop.getName());
						}
					}
					return comp;
				}
			};
			table.addMouseListener(new java.awt.event.MouseAdapter() {
				@Override
				public void mouseClicked(java.awt.event.MouseEvent evt) {
					int row = table.rowAtPoint(evt.getPoint());
					int col = table.columnAtPoint(evt.getPoint());
					if (row >= 0 && col >= 0 && evt.getButton() == 3) {
						int value = JOptionPane.showConfirmDialog(null, "Delete this row?");
						if (value == 0) {
							removeDrop(row);
						}
					}
				}

			});
			tablePane = new JScrollPane(table);
			tablePane.setBounds(4, 0, 488, 338);
			tablePane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			tablePane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			tablePane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			add(tablePane);
			TableColumn sportColumn = table.getColumnModel().getColumn(4); 
			JComboBox<String> comboBox = new JComboBox<String>();
			comboBox.addItem("ALWAYS");
			comboBox.addItem("COMMON");
			comboBox.addItem("UNCOMMON");
			comboBox.addItem("RARE");
			comboBox.addItem("VERY_RARE");
			sportColumn.setCellEditor(new DefaultCellEditor(comboBox));
		}

		/**
		 * Removes the drop.
		 * @param row the row.
		 */
		private void removeDrop(int row) {
			NPCDrop drop = npc.getDrobTable(type)[row];
			if (drop != null) {
				npc.removeDrop(drop, type);
				reset();
			}
		}
		
		/**
		 * Resets the view.
		 */
		public void reset() {
			remove(tablePane);
			createTable();
		}

		/**
		 * Gets the tables as objects. 
		 * @param rows the rows.
		 * @return the rows.
		 */
		public Object[][] asObjects(NPCDrop...rows) {
			if (rows == null) {
				return new Object[][] {{1, 1, 1, 0.0, DropFrequency.COMMON}};
			}
			Object[][] data = new Object[rows.length][5];
			for (int i = 0; i < rows.length; i++) {
				data[i] = rows[i].getData();
			}
			return data;
		}

		/**
		 * Gets the npc.
		 * @return the npc.
		 */
		public NPC getNpc() {
			return npc;
		}

		/**
		 * Gets the type.
		 * @return the type.
		 */
		public TableType getType() {
			return type;
		}

		/**
		 * Sets the type.
		 * @param type the type to set
		 */
		public void setType(TableType type) {
			this.type = type;
		}

		/**
		 * Gets the tablePane.
		 * @return the tablePane.
		 */
		public JScrollPane getTablePane() {
			return tablePane;
		}

		/**
		 * Sets the tablePane.
		 * @param tablePane the tablePane to set
		 */
		public void setTablePane(JScrollPane tablePane) {
			this.tablePane = tablePane;
		}

		/**
		 * Gets the table.
		 * @return the table.
		 */
		public JTable getTable() {
			return table;
		}

		/**
		 * Sets the table.
		 * @param table the table to set
		 */
		public void setTable(JTable table) {
			this.table = table;
		}

	}
}
