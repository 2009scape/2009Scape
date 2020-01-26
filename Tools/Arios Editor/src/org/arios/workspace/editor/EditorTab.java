package org.arios.workspace.editor;

import java.awt.Dimension;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.arios.workspace.node.Node;

/**
 * Represents an editor tab.
 * @author Vexia
 *
 */
public abstract class EditorTab extends JPanel {
	
	/**
	 * The size of the tab panel.
	 */
	public static final Dimension SIZE = new Dimension(1280, 521);
	
	/**
	 * The max editors.
	 */
	public static final int MAX_EDITORS = 10;

	/**
	 * The serial version UID.
	 */
	private static final long serialVersionUID = 8899748585906614907L;
	
	/**
	 * The preloaded nodes for this editor.
	 */
	protected final Map<Integer, Node<?>> nodes = new HashMap<>();
	
	/**
	 * The panel used for searching nodes.
	 */
	protected final NodePanel nodePanel = new NodePanel(this);
	
	/**
	 * The opened editors.
	 */
	private Map<Integer, NodeEditor> editors = new HashMap<>();
	
	/**
	 * The name of the tab.
	 */
	private final String name;

	/**
	 * Constructs a new {@Code EditorTab} {@Code Object}
	 * @param name the name.
	 */
	public EditorTab(String name) {
		super();
		this.name = name;
		setLayout(null);
		setSize(SIZE);
		nodePanel.getNodeList().setLocation(22, -122);
		nodePanel.getNodeList().setSize(256, 300);
		nodePanel.setLocation(0, 6); 
		add(nodePanel);
	}
	
	/**
	 * Parses the configs for this editor.
	 */
	public abstract void parse();
	
	/**
	 * Saves this editor.
	 */
	public abstract boolean save();
	
	/**
	 * Initializes this tab.
	 */
	public boolean init() {
		return true;
	}
	
	/**
	 * Used to clean up & save configs.
	 */
	public void preSave() {
		for (NodeEditor editor : editors.values()) {
			editor.save();
		}
	}
	
	/**
	 * Edits a node.
	 * @param edit the edit.
	 * @return {@code True} if opened.
	 */
	public boolean edit(Node<?> edit) {
		if (editors.size() > MAX_EDITORS) {
			JOptionPane.showMessageDialog(null, "You can't have any more editors open.");
			return false;
		}
		if (editors.containsKey(edit.getId())) {
			JOptionPane.showMessageDialog(null, "Node with id - " + edit.getId() + " is already opened.");
			return false;
		}
		NodeEditor editor = getEditor(edit);
		editors.put(edit.getId(), editor);
		nodePanel.getTabbedPane().addTab(edit.toString(), editor);
		return true;
	}
	
	/**
	 * Gets the node editor.
	 * @param edit the edit.
	 * @return the editor.
	 */
	public NodeEditor getEditor(Node<?> edit) {
		return new NodeEditor(edit);
	}

	/**
	 * Closes the node editor.
	 * @param editor the editor.
	 */
	public void closeEditor(NodeEditor editor) {
		editors.remove(editor.getNode().getId());
		nodePanel.getTabbedPane().remove(editor);
	}

	/**
	 * Gets the name.
	 * @return the name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the nodes.
	 * @return the nodes.
	 */
	public Map<Integer, Node<?>> getNodes() {
		return nodes;
	}


}
