/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.editor.npc;

import com.alex.loaders.npcs.NPCDefinitions;
import com.alex.store.Store;
import com.alex.utils.Constants;
import com.alex.utils.Utils;
import com.editor.Main;
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
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.WindowConstants;

/**
 *
 * @author Travis
 */
public class NPCSelection extends JFrame {

    public static Store STORE;

    /**
     * Creates new form ItemSelection
     */
    public NPCSelection(String cache) throws IOException {
        STORE = new Store(cache);
        setTitle("NPC Selection");
        setResizable(false);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
    }

    public NPCSelection() {
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
        npcsListmodel = new DefaultListModel<NPCDefinitions>();
        npcsList = new JList<NPCDefinitions>(npcsListmodel);
        npcsList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        npcsList.setLayoutOrientation(JList.VERTICAL);
        npcsList.setVisibleRowCount(-1);
        JScrollPane jScrollPane1 = new JScrollPane(npcsList);

        final NPCSelection npcS = this;
        editButton.setText("Edit");
        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                NPCDefinitions defs = npcsList.getSelectedValue();
                if (defs == null) {
                    return;
                }
                new NPCEditor(npcS, defs);
            }
        });

        addButton.setText("Add New");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                NPCDefinitions defs = new NPCDefinitions(STORE, getNewNPCID(), false);
                if (defs == null) {
                    return;
                }
                if (defs.id == -1) {
                    return;
                }
                new NPCEditor(npcS, defs);

                //new ItemEditor(itemS, new ItemDefinitions(STORE, Utils.getItemDefinitionsSize(STORE), false));
            }
        });

        duplicateButton.setText("Duplicate");
        duplicateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                NPCDefinitions defs = npcsList.getSelectedValue();
                if (defs == null) {
                    return;
                }
                defs = (NPCDefinitions) defs.clone();
                if (defs == null) {
                    return;
                }
                defs.id = getNewNPCID();
                if (defs.id == -1) {
                    return;
                }
                new NPCEditor(npcS, defs);
            }
        });

        deleteButton.setText("Delete");
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                NPCDefinitions defs = npcsList.getSelectedValue();
                JFrame frame = new JFrame();
                int result = JOptionPane.showConfirmDialog(frame, "Do you really want to delete item "+defs.id);
                if (result == JOptionPane.YES_OPTION) {
                    if (defs == null) {
                        return;
                    }
                    STORE.getIndexes()[Constants.ITEM_DEFINITIONS_INDEX].removeFile(defs.getArchiveId(), defs.getFileId());
                    removeNPCDefs(defs);
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
        addAllNPCs();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws IOException {
        STORE = new Store("cache/", false);

        /* Create and display the form */
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NPCSelection().setVisible(true);
            }
        });
    }
    
    private void exitButtonActionPerformed(ActionEvent evt) {
        this.dispose();
    }

    public int getNewNPCID() {
        try {
            JFrame frame = new JFrame();
            Object result = JOptionPane.showInputDialog(frame, "Enter new NPC ID:");
            return Integer.parseInt(result.toString());
        } catch (Exception e) {
            JFrame parent = new JFrame();
            JOptionPane.showMessageDialog(parent, "Please enter a valid integer!");
            Main.log("NPCSelection", "Non-valid character entered for new NPC ID");
            return -1;
        }
    }

    public void addAllNPCs() {
        for (int id = 0; id < Utils.getNPCDefinitionsSize(STORE) - 15615; id++) {
            addNPCDefs(NPCDefinitions.getNPCDefinition(STORE, id));
        }
        Main.log("NPCSelection", "All NPCs Loaded");
    }

    public void addNPCDefs(final NPCDefinitions defs) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                npcsListmodel.addElement(defs);
            }
        });
    }

    public void updateNPCDefs(final NPCDefinitions defs) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                int index = npcsListmodel.indexOf(defs);
                if (index == -1) {
                    npcsListmodel.addElement(defs);
                } else {
                    npcsListmodel.setElementAt(defs, index);
                }
            }
        });
    }

    public void removeNPCDefs(final NPCDefinitions defs) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                npcsListmodel.removeElement(defs);
            }
        });
    }
    private JButton addButton;
    private JButton duplicateButton;
    private JButton editButton;
    private DefaultListModel<NPCDefinitions> npcsListmodel;
    private JList<NPCDefinitions> npcsList;
    private JMenu jMenu1;
    private JMenuBar jMenuBar1;
    private JMenuItem exitButton;
    private JButton deleteButton;
    
}
