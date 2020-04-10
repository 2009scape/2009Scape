/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.editor.item;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.WindowConstants;

import com.alex.loaders.items.ItemDefinitions;
import com.alex.store.Store;
import com.alex.utils.Constants;
import com.alex.utils.Utils;
import com.editor.Main;
import javax.swing.JOptionPane;

/**
 *
 * @author Travis
 */
public class ItemSelection extends JFrame {

    private static final long serialVersionUID = -3059309913196024742L;
    public static Store STORE;

    /**
     * Creates new form ItemSelection
     */
    public ItemSelection(String cache) throws IOException {
        STORE = new Store(cache);
        setTitle("Item Selection");
        setResizable(false);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
    }

    public ItemSelection() {
        initComponents();
    }

    private void initComponents() {

        editButton = new JButton();
        addButton = new JButton();
        duplicateButton = new JButton();
        deleteButton = new JButton();
        jMenuBar1 = new JMenuBar();
        jMenu1 = new JMenu();
        exitButton = new JMenuItem();

        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        itemsListmodel = new DefaultListModel<ItemDefinitions>();
        itemsList = new JList<ItemDefinitions>(itemsListmodel);
        itemsList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        itemsList.setLayoutOrientation(JList.VERTICAL);
        itemsList.setVisibleRowCount(-1);
        JScrollPane jScrollPane1 = new JScrollPane(itemsList);

        final ItemSelection itemS = this;
        editButton.setText("Edit");
        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ItemDefinitions defs = itemsList.getSelectedValue();
                if (defs == null) {
                    return;
                }
                new ItemEditor(itemS, defs);
            }
        });

        addButton.setText("Add New");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ItemDefinitions defs = new ItemDefinitions(STORE, getNewItemID(), false);
                if (defs == null) {
                    return;
                }
                if (defs.id == -1) {
                    return;
                }
                new ItemEditor(itemS, defs);

                //new ItemEditor(itemS, new ItemDefinitions(STORE, Utils.getItemDefinitionsSize(STORE), false));
            }
        });

        duplicateButton.setText("Duplicate");
        duplicateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ItemDefinitions defs = itemsList.getSelectedValue();
                if (defs == null) {
                    return;
                }
                defs = (ItemDefinitions) defs.clone();
                if (defs == null) {
                    return;
                }
                //defs.id = Utils.getItemDefinitionsSize(STORE);
                defs.id = getNewItemID();
                if (defs.id == -1) {
                    return;
                }
                new ItemEditor(itemS, defs);
            }
        });

        deleteButton.setText("Delete");
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ItemDefinitions defs = itemsList.getSelectedValue();
                JFrame frame = new JFrame();
                int result = JOptionPane.showConfirmDialog(frame, "Do you really want to delete item "+defs.id);
                if (result == JOptionPane.YES_OPTION) {
                    if (defs == null) {
                        return;
                    }
                    STORE.getIndexes()[Constants.ITEM_DEFINITIONS_INDEX].removeFile(defs.getArchiveId(), defs.getFileId());
                    removeItemDefs(defs);
                    Main.log("ItemSelection", "Item "+defs.id+" removed.");
                }
            }
        });

        jMenu1.setText("File");

        exitButton.setText("Close");
        exitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitButtonActionPerformed(evt);
            }
        });
        jMenu1.add(exitButton);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createSequentialGroup()
                .addComponent(editButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(addButton)))
                .addGap(0, 0, Short.MAX_VALUE))
                .addGroup(layout.createSequentialGroup()
                .addComponent(duplicateButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(deleteButton)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(editButton)
                .addComponent(addButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(duplicateButton)
                .addComponent(deleteButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

        pack();
        addAllItems();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws IOException {
        STORE = new Store("cache/", false);

        /* Create and display the form */
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ItemSelection().setVisible(true);
            }
        });
    }
    
    private void exitButtonActionPerformed(ActionEvent evt) {
        this.dispose();
    }

    public int getNewItemID() {
        try {
            JFrame frame = new JFrame();
            Object result = JOptionPane.showInputDialog(frame, "Enter new Item ID:");
            return Integer.parseInt(result.toString());
        } catch (Exception e) {
            JFrame parent = new JFrame();
            JOptionPane.showMessageDialog(parent, "Please enter a valid integer!");
            Main.log("ItemSelection", "Non-valid character entered for new Item ID");
            return -1;
        }
    }

    public void addAllItems() {
        if (Utils.getItemDefinitionsSize(STORE) > 30000) {
            for (int id = 0; id < Utils.getItemDefinitionsSize(STORE) /*- 22314*/; id++) {
                addItemDefs(ItemDefinitions.getItemDefinition(STORE, id));
            }
        } else {
            for (int id = 0; id < Utils.getItemDefinitionsSize(STORE); id++) {
                addItemDefs(ItemDefinitions.getItemDefinition(STORE, id));
            }
        }
        Main.log("ItemSelection", "All Items Loaded");
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
                if (index == -1) {
                    itemsListmodel.addElement(defs);
                } else {
                    itemsListmodel.setElementAt(defs, index);
                }
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
    private JButton addButton;
    private JButton duplicateButton;
    private JButton editButton;
    private DefaultListModel<ItemDefinitions> itemsListmodel;
    private JList<ItemDefinitions> itemsList;
    private JMenu jMenu1;
    private JMenuBar jMenuBar1;
    private JMenuItem exitButton;
    private JButton deleteButton;
}
