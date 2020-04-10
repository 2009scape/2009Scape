package org.arios.workspace.editor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map.Entry;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.arios.workspace.WorkSpace;
import org.arios.workspace.node.Configuration;
import org.arios.workspace.node.Node;
import org.arios.workspace.node.npc.NPC;
import org.arios.workspace.node.npc.NPCDropPanel;

/**
 * Used for editing a node.
 * @author Vexia
 *
 */
public class NodeEditor extends JPanel {

	/**
	 * The serial UID.
	 */
	private static final long serialVersionUID = 8520324823671510804L;

	/**
	 * The node being eddited.
	 */
	private final Node<?> node;

	/**
	 * The node table.
	 */
	private NodeTable table;

	/**
	 * The npc drop panel.
	 */
	private NPCDropPanel dropPanel;
	
	/**
	 * Constructs a new {@Code NodeEditor} {@Code Object}
	 * @param node the node.
	 * @param o the o..?
	 */
	public NodeEditor(Node<?> node,  boolean o) {
		super();
		this.node = node;
		setLayout(null);
	}

	/**
	 * Constructs a new {@Code NodeEditor} {@Code Object}
	 * @param node the node.
	 */
	public NodeEditor(final Node<?> node) {
		super();
		this.node = node;
		this.table = new NodeTable(node);
		setLayout(null);
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WorkSpace.getWorkSpace().getEditor().closeEditor(NodeEditor.this);
			}
		});
		btnClose.setBounds(6, 6, 117, 29);
		add(btnClose);
		add(table);

		JButton btnCopyConfigurations = new JButton("Copy configurations");
		btnCopyConfigurations.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = Integer.parseInt(JOptionPane.showInputDialog("Enter the id:"));
				Node<?> n = WorkSpace.getWorkSpace().getEditor().getNodes().get(id);
				if (n == null) {
					JOptionPane.showMessageDialog(null, "Invalid id!");
					return;
				}
				for (Entry<String, Configuration<?>> s : n.getConfigurations().entrySet()) {
					Configuration<?> c = s.getValue();
					node.getConfigurations().get(s.getKey()).setValue(c.getValue());
				}
				remove(table);
				table = new NodeTable(node);
				add(table);
			}
		});
		btnCopyConfigurations.setBounds(126, 6, 163, 29);
		add(btnCopyConfigurations);
		if (node instanceof NPC) {
			dropPanel = new NPCDropPanel((NPC) node);
			dropPanel.setLocation(303, 17);
			dropPanel.setSize(633, 394);
			add(dropPanel);
		}
	}

	/**
	 * Saves the node editor.
	 */
	public void save() {
		table.save();
		if (dropPanel != null) {
			dropPanel.save();
		}
	}

	/**
	 * Gets the node.
	 * @return the node.
	 */
	public Node<?> getNode() {
		return node;
	}

	/**
	 * Gets the table.
	 * @return the table.
	 */
	public NodeTable getTable() {
		return table;
	}

}
