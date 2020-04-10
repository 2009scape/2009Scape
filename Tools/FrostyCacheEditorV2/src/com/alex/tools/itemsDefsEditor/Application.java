package com.alex.tools.itemsDefsEditor;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import com.alex.loaders.items.ItemDefinitions;
import com.alex.store.Store;
import com.alex.utils.Constants;
import com.alex.utils.Utils;

public class Application {

	public static Store STORE;
	private JFrame frmCacheEditorV;

	/**
	 * Launch the application.
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		STORE = new Store("cache/", false);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Application window = new Application();
					window.frmCacheEditorV.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Application() {
		initialize();
	}

	private void setLook() {
		boolean found = false;
	     for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels())  {
	         if(info.getName().equals("Nimbus"))
	             try {
	                 UIManager.setLookAndFeel(info.getClassName());
	                 found = true;
	             }catch(Exception e) {
	                 e.printStackTrace();
	             }
	     }
	     if(!found)
	             try {
	                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); 
	             }catch(Exception e) {
	                 e.printStackTrace();
	             }
	}
	
	private JList<ItemDefinitions> itemsList;
	private DefaultListModel<ItemDefinitions> itemsListmodel;
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setLook();
		frmCacheEditorV = new JFrame();
		frmCacheEditorV.setTitle("Cache Editor V0.1");
		frmCacheEditorV.setBounds(100, 100, 352, 435);
		frmCacheEditorV.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frmCacheEditorV.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Main", null, panel, null);
		panel.setLayout(null);
		
		JButton btnGenerateUkeys = new JButton("Generate Ukeys (614- Client Builts)");
		btnGenerateUkeys.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				byte[] ukeys = Utils.getArchivePacketData(255, 255, STORE.generateIndex255Archive255Outdated());
				new GeneratedUkeys(getFrame(), ukeys);
			}
		});
		btnGenerateUkeys.setBounds(33, 64, 257, 28);
		panel.add(btnGenerateUkeys);
		
		JLabel lblCreatedByAlexalso = new JLabel("Created By Alex(Also named Dragonkk)");
		lblCreatedByAlexalso.setFont(new Font("Tekton Pro Ext", Font.PLAIN, 15));
		lblCreatedByAlexalso.setBounds(6, 290, 322, 46);
		panel.add(lblCreatedByAlexalso);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Items", null, panel_1, null);
		panel_1.setLayout(null);
		itemsListmodel = new DefaultListModel<ItemDefinitions>();
		itemsList = new JList<ItemDefinitions>(itemsListmodel);
		itemsList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		itemsList.setLayoutOrientation(JList.VERTICAL);
		itemsList.setVisibleRowCount(-1);
		JScrollPane itemListscrollPane = new JScrollPane(itemsList);
		itemListscrollPane.setBounds(34, 49, 155, 254);
		panel_1.add(itemListscrollPane);
		
		JButton btnEdit = new JButton("Edit");
		final Application app = this;
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ItemDefinitions defs = itemsList.getSelectedValue();
				if(defs == null)
					return;
				new ItemDefsEditor(app, defs);
			}
		});
		btnEdit.setBounds(201, 48, 90, 28);
		panel_1.add(btnEdit);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				new ItemDefsEditor(app, new ItemDefinitions(STORE, Utils.getItemDefinitionsSize(STORE) , false));
			}
		});
		btnAdd.setBounds(201, 88, 90, 28);
		panel_1.add(btnAdd);
		
		JButton btnRemove = new JButton("Remove");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ItemDefinitions defs = itemsList.getSelectedValue();
				if(defs == null)
					return;
				STORE.getIndexes()[Constants.ITEM_DEFINITIONS_INDEX].removeFile(defs.getArchiveId(), defs.getFileId());
				removeItemDefs(defs);
			}
		});
		btnRemove.setBounds(201, 128, 90, 28);
		panel_1.add(btnRemove);
		
		JLabel label = new JLabel("Cached Items:");
		label.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
		label.setBounds(34, 18, 155, 21);
		panel_1.add(label);
		
		JButton btnDuplicate = new JButton("Clone");
		btnDuplicate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ItemDefinitions defs = itemsList.getSelectedValue();
				if(defs == null)
					return;
				defs = (ItemDefinitions) defs.clone();
				if(defs == null)
					return;
				defs.id = Utils.getItemDefinitionsSize(STORE);
				new ItemDefsEditor(app, defs);
			}
		});
		btnDuplicate.setBounds(201, 168, 90, 28);
		panel_1.add(btnDuplicate);
		addAllItems();
	}
	
	public void addAllItems() {
		for(int id = 0; id < Utils.getItemDefinitionsSize(STORE) - 22314; id++) {
			addItemDefs(ItemDefinitions.getItemDefinition(STORE, id));
		}
	}
	
	public void addItemDefs(final ItemDefinitions defs) {
		EventQueue.invokeLater(new Runnable() { 
            public void run() { 
            	itemsListmodel.addElement(defs);
            }
        });
	}
	
	public void updateItemDefs(final ItemDefinitions defs) {
		EventQueue.invokeLater(new Runnable() { 
            public void run() { 
            	int index = itemsListmodel.indexOf(defs);
            	if(index == -1)
            		itemsListmodel.addElement(defs);
            	else
            		itemsListmodel.setElementAt(defs, index);
            }
		});
	}
	
	public void removeItemDefs(final ItemDefinitions defs) {
		EventQueue.invokeLater(new Runnable() { 
            public void run() { 
            	itemsListmodel.removeElement(defs);
            }
        });
	}
	
	public JFrame getFrame() {
		return frmCacheEditorV;
	}
	
}
